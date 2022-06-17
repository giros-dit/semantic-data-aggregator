/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package upm.dit.giros.processors.eve;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonWriter;

import org.apache.nifi.annotation.behavior.ReadsAttribute;
import org.apache.nifi.annotation.behavior.ReadsAttributes;
import org.apache.nifi.annotation.behavior.WritesAttribute;
import org.apache.nifi.annotation.behavior.WritesAttributes;
import org.apache.nifi.annotation.documentation.CapabilityDescription;
import org.apache.nifi.annotation.documentation.SeeAlso;
import org.apache.nifi.annotation.documentation.Tags;
import org.apache.nifi.annotation.lifecycle.OnScheduled;
import org.apache.nifi.components.PropertyDescriptor;
import org.apache.nifi.flowfile.FlowFile;
import org.apache.nifi.processor.AbstractProcessor;
import org.apache.nifi.processor.ProcessContext;
import org.apache.nifi.processor.ProcessSession;
import org.apache.nifi.processor.ProcessorInitializationContext;
import org.apache.nifi.processor.Relationship;
import org.apache.nifi.processor.io.InputStreamCallback;
import org.apache.nifi.processor.io.OutputStreamCallback;
import org.opendaylight.mdsal.binding.dom.codec.api.BindingNormalizedNodeSerializer;
import org.opendaylight.mdsal.binding.dom.codec.impl.BindingCodecContext;
import org.opendaylight.mdsal.binding.runtime.spi.BindingRuntimeHelpers;
import org.opendaylight.mdsal.binding.spec.reflect.BindingReflections;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.eve.rev210504.EveRecord;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.eve.rev210504.EveRecordBuilder;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.eve.rev210504.context.set.LabelsBuilder;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.eve.rev210504.context.set.labels.Label;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.eve.rev210504.context.set.labels.LabelBuilder;
import org.opendaylight.yangtools.yang.binding.InstanceIdentifier;
import org.opendaylight.yangtools.yang.binding.util.BindingMap;
import org.opendaylight.yangtools.yang.data.api.YangInstanceIdentifier;
import org.opendaylight.yangtools.yang.data.api.schema.MapEntryNode;
import org.opendaylight.yangtools.yang.data.api.schema.NormalizedNode;
import org.opendaylight.yangtools.yang.data.api.schema.stream.NormalizedNodeStreamWriter;
import org.opendaylight.yangtools.yang.data.api.schema.stream.NormalizedNodeWriter;
import org.opendaylight.yangtools.yang.data.codec.gson.JSONCodecFactory;
import org.opendaylight.yangtools.yang.data.codec.gson.JSONCodecFactorySupplier;
import org.opendaylight.yangtools.yang.data.codec.gson.JSONNormalizedNodeStreamWriter;
import org.opendaylight.yangtools.yang.data.codec.gson.JsonWriterFactory;
import org.opendaylight.yangtools.yang.model.api.EffectiveModelContext;
import org.opendaylight.yangtools.yang.model.api.SchemaPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Tags({ "example" })
@CapabilityDescription("Provide a description")
@SeeAlso({})
@ReadsAttributes({ @ReadsAttribute(attribute = "", description = "") })
@WritesAttributes({ @WritesAttribute(attribute = "", description = "") })
public class EveDriver extends AbstractProcessor {

    public static final Relationship SUCCESS = new Relationship.Builder().name("SUCCESS")
            .description("Success relationship").build();

    public static final Relationship FAILURE = new Relationship.Builder().name("FAILURE")
            .description("Failure relationship").build();

    private List<PropertyDescriptor> descriptors;

    private Set<Relationship> relationships;

    @Override
    protected void init(final ProcessorInitializationContext context) {
        relationships = new HashSet<>();
        relationships.add(SUCCESS);
        relationships.add(FAILURE);
        relationships = Collections.unmodifiableSet(relationships);
    }

    @Override
    public Set<Relationship> getRelationships() {
        return this.relationships;
    }

    @Override
    public final List<PropertyDescriptor> getSupportedPropertyDescriptors() {
        return descriptors;
    }

    @OnScheduled
    public void onScheduled(final ProcessContext context) {

    }

    @Override
    public void onTrigger(final ProcessContext context, final ProcessSession session) {
        final AtomicReference<String> value = new AtomicReference<>();
        FlowFile flowfile = session.get();
        if (flowfile == null) {
            return;
        }
        // To read the input from the flowfile
        session.read(flowfile, new InputStreamCallback() {
            @Override
            public void process(InputStream in) throws IOException {
                try {
                    // String json = IOUtils.toString(in);
                    JsonParser jsonParser = new JsonParser();
                    JsonObject json = (JsonObject) jsonParser.parse(new InputStreamReader(in, "UTF-8"));
                    String json_ietf = driver(json);
                    // String json_ietf = test(json);
                    value.set(json_ietf);
                } catch (Exception ex) {
                    // ex.printStackTrace();
                    // getLogger().error(ex.getMessage());
                    ex.printStackTrace();
                    StringWriter errors = new StringWriter();
                    ex.printStackTrace(new PrintWriter(errors));
                    getLogger().error(errors.toString());
                }
            }
        });
        // To write the results back out of the flowfile
        flowfile = session.write(flowfile, new OutputStreamCallback() {
            @Override
            public void process(OutputStream out) throws IOException {
                out.write(value.get().getBytes());
            }
        });
        session.transfer(flowfile, SUCCESS);
    }

    // Code borrowed from:
    // https://git.opendaylight.org/gerrit/gitweb?p=jsonrpc.git;a=blob_plain;f=impl/src/
    // main/java/org/opendaylight/jsonrpc/impl/
    // JsonConverter.java;h=ea8069c67ece073e3d9febb694c4e15b01238c10;hb=3ea331d0e57712654d9ecbf2ae2a46cb0ce02d31
    private static final String JSON_IO_ERROR = "I/O problem in JSON codec";
    private static final Logger LOG = LoggerFactory.getLogger(EveDriver.class);
    private static final JSONCodecFactorySupplier CODEC_SUPPLIER = JSONCodecFactorySupplier.DRAFT_LHOTKA_NETMOD_YANG_JSON_02;
    private static final JsonParser PARSER = new JsonParser();
    private static final YangInstanceIdentifier ROOT = YangInstanceIdentifier.empty();

    // Schema context initialization
    // Code borrowed from:
    // https://github.com/opendaylight/jsonrpc/blob/1331a9f73db2fe308e3bbde00ff42359431dbc7f/
    // binding-adapter/src/main/java/org/opendaylight/jsonrpc/binding/EmbeddedRpcInvocationAdapter.java#L38
    private static final EffectiveModelContext schemaContext = BindingRuntimeHelpers
            .createEffectiveModel(BindingReflections.loadModuleInfos());

    /**
     * Performs the actual data conversion.
     *
     * @param schemaPath - schema path for data
     * @param data       - Normalized Node
     * @return data converted as a JsonObject
     */
    private static final JsonObject doConvert(SchemaPath schemaPath, NormalizedNode<?, ?> data) {
        try (StringWriter writer = new StringWriter();
                JsonWriter jsonWriter = JsonWriterFactory.createJsonWriter(writer)) {
            final JSONCodecFactory codecFactory = CODEC_SUPPLIER.getShared(schemaContext);
            final NormalizedNodeStreamWriter jsonStream = (data instanceof MapEntryNode)
                    ? JSONNormalizedNodeStreamWriter.createNestedWriter(codecFactory, schemaPath, null, jsonWriter)
                    : JSONNormalizedNodeStreamWriter.createExclusiveWriter(codecFactory, schemaPath, null, jsonWriter);
            try (NormalizedNodeWriter nodeWriter = NormalizedNodeWriter.forStreamWriter(jsonStream)) {
                nodeWriter.write(data);
                nodeWriter.flush();
            }
            return PARSER.parse(writer.toString()).getAsJsonObject();
        } catch (IOException e) {
            LOG.error(JSON_IO_ERROR, e);
            return null;
        }
    }

    public String driver(JsonObject data) {
        // Init YANG Binding builders
        EveRecordBuilder record_builder = new EveRecordBuilder();

        // Parse input JSON data
        if (data.has("metric_value")) {
            record_builder.setValue(new java.math.BigDecimal(data.get("metric_value").getAsString()));
        }
        // Then it is "kpi_value"
        else {
            record_builder.setValue(new java.math.BigDecimal(data.get("kpi_value").getAsString()));
        }
        record_builder.setTimestamp(new java.math.BigDecimal(data.get("timestamp").getAsString()));
        record_builder.setUnit(data.get("unit").getAsString());
        if (data.has("device_id")) {
            record_builder.setDeviceId(data.get("device_id").getAsString());
        }
        if (data.has("context")) {
            // Init builders
            LabelsBuilder labels_builder = new LabelsBuilder();
            ArrayList<Label> label_list = new ArrayList<Label>();
            String[] context = data.get("context").getAsString().split("\\s+");
            for (String label : context) {
                LabelBuilder label_builder = new LabelBuilder();
                // Split into name and value
                String[] pair = label.split("=");
                label_builder.setName(pair[0]);
                label_builder.setValue(pair[1]);
                Label lb = label_builder.build();
                label_list.add(lb);
            }
            labels_builder.setLabel(BindingMap.of(label_list));
            record_builder.setLabels(labels_builder.build());

        }
        final EveRecord record = record_builder.build();
        InstanceIdentifier<EveRecord> iid = InstanceIdentifier.create(EveRecord.class);
        BindingNormalizedNodeSerializer codec = new BindingCodecContext(
                BindingRuntimeHelpers.createRuntimeContext(EveRecord.class));
        Entry<YangInstanceIdentifier, NormalizedNode<?, ?>> normalized = codec.toNormalizedNode(iid, record);
        JsonObject gson_obj = doConvert(schemaContext.getPath(), normalized.getValue());
        return gson_obj.toString();
    }

}
