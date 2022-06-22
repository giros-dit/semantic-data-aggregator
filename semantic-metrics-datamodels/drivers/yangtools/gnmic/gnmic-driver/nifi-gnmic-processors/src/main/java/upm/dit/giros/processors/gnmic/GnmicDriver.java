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
package upm.dit.giros.processors.gnmic;

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
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

import com.google.gson.JsonElement;
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
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.gnmic.rev210719.GnmicEvent;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.gnmic.rev210719.GnmicEventBuilder;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.gnmic.rev210719.tag.set.TagsBuilder;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.gnmic.rev210719.tag.set.tags.Tag;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.gnmic.rev210719.tag.set.tags.TagBuilder;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.gnmic.rev210719.value.set.ValuesBuilder;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.gnmic.rev210719.value.set.values.Value;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.gnmic.rev210719.value.set.values.ValueBuilder;
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

@Tags({ "driver", "source" })
@CapabilityDescription("Provide a description")
@SeeAlso({})
@ReadsAttributes({ @ReadsAttribute(attribute = "", description = "") })
@WritesAttributes({ @WritesAttribute(attribute = "", description = "") })
public class GnmicDriver extends AbstractProcessor {

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
    private static final Logger LOG = LoggerFactory.getLogger(GnmicDriver.class);
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
        GnmicEventBuilder event_builder = new GnmicEventBuilder();
        // Parse input JSON data
        event_builder.setName(data.get("name").getAsString());
        event_builder.setTimestamp(Long.parseLong(data.get("timestamp").getAsString()));
        if (data.has("tags")) {
            // Init builders
            TagsBuilder tags_builder = new TagsBuilder();
            ArrayList<Tag> tag_list = new ArrayList<Tag>();
            JsonObject tags_json = data.getAsJsonObject("tags");
            for (Map.Entry<String, JsonElement> entry : tags_json.entrySet()) {
                TagBuilder tag_builder = new TagBuilder();
                tag_builder.setName(entry.getKey());
                tag_builder.setValue(entry.getValue().getAsString());
                Tag tag = tag_builder.build();
                tag_list.add(tag);
            }
            tags_builder.setTag(BindingMap.of(tag_list));
            event_builder.setTags(tags_builder.build());
        }
        if (data.has("values")) {
            // Init builders
            ValuesBuilder values_builder = new ValuesBuilder();
            ArrayList<Value> value_list = new ArrayList<Value>();
            JsonObject values_json = data.getAsJsonObject("values");
            for (Map.Entry<String, JsonElement> entry : values_json.entrySet()) {
                ValueBuilder value_builder = new ValueBuilder();
                value_builder.setName(entry.getKey());
                value_builder.setValue(entry.getValue().getAsString());
                Value value = value_builder.build();
                value_list.add(value);
            }
            values_builder.setValue(BindingMap.of(value_list));
            event_builder.setValues(values_builder.build());
        }

        final GnmicEvent record = event_builder.build();
        InstanceIdentifier<GnmicEvent> iid = InstanceIdentifier.create(GnmicEvent.class);
        BindingNormalizedNodeSerializer codec = new BindingCodecContext(
                BindingRuntimeHelpers.createRuntimeContext(GnmicEvent.class));
        Entry<YangInstanceIdentifier, NormalizedNode<?, ?>> normalized = codec.toNormalizedNode(iid, record);
        JsonObject gson_obj = doConvert(schemaContext.getPath(), normalized.getValue());
        return gson_obj.toString();
    }
}
