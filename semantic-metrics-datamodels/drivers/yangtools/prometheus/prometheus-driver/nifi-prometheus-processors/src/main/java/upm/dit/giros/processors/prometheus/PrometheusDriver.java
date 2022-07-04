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
package upm.dit.giros.processors.prometheus;

import org.apache.commons.io.IOUtils;
import org.apache.nifi.components.PropertyDescriptor;
import org.apache.nifi.flowfile.FlowFile;
import org.apache.nifi.annotation.behavior.ReadsAttribute;
import org.apache.nifi.annotation.behavior.ReadsAttributes;
import org.apache.nifi.annotation.behavior.WritesAttribute;
import org.apache.nifi.annotation.behavior.WritesAttributes;
import org.apache.nifi.annotation.lifecycle.OnScheduled;
import org.apache.nifi.annotation.documentation.CapabilityDescription;
import org.apache.nifi.annotation.documentation.SeeAlso;
import org.apache.nifi.annotation.documentation.Tags;
import org.apache.nifi.processor.exception.ProcessException;
import org.apache.nifi.processor.AbstractProcessor;
import org.apache.nifi.processor.ProcessContext;
import org.apache.nifi.processor.ProcessSession;
import org.apache.nifi.processor.ProcessorInitializationContext;
import org.apache.nifi.processor.Relationship;
import org.apache.nifi.processor.util.StandardValidators;
import org.apache.nifi.processor.io.InputStreamCallback;
import org.apache.nifi.processor.io.OutputStreamCallback;

import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

import java.util.Map;
import java.util.Map.Entry;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonWriter;
//import org.json.*;

import org.opendaylight.mdsal.binding.dom.codec.api.BindingNormalizedNodeSerializer;
import org.opendaylight.mdsal.binding.dom.codec.impl.BindingCodecContext;
import org.opendaylight.mdsal.binding.runtime.spi.BindingRuntimeHelpers;
import org.opendaylight.mdsal.binding.spec.reflect.BindingReflections;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.MetricFamilies;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.prometheus.rev210504.Metric;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.prometheus.rev210504.MetricBuilder;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.prometheus.rev210504.label.set.LabelsBuilder;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.prometheus.rev210504.label.set.Labels;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.prometheus.rev210504.label.set.labels.LabelBuilder;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.prometheus.rev210504.label.set.labels.Label;
import org.opendaylight.yangtools.yang.binding.InstanceIdentifier;
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

@Tags({"Prometheus YANGTools Driver"})
@CapabilityDescription("NiFi processor to implement the Prometheus YANGTools Driver.")
@SeeAlso({})
@ReadsAttributes({@ReadsAttribute(attribute="", description="")})
@WritesAttributes({@WritesAttribute(attribute="", description="")})
public class PrometheusDriver extends AbstractProcessor {

    public static final PropertyDescriptor DATA_SOURCE_YANG_MODEL_PATH = new PropertyDescriptor
            .Builder().name("Data Source YANG model path")
            .displayName("Data Source YANG model path")
            .description("Data Source YANG model path")
            .required(true)
            .addValidator(StandardValidators.FILE_EXISTS_VALIDATOR)
            .build();

    public static final PropertyDescriptor MY_PROPERTY = new PropertyDescriptor
            .Builder().name("MY_PROPERTY")
            .displayName("My property")
            .description("Example Property")
            .required(true)
            .addValidator(StandardValidators.NON_EMPTY_VALIDATOR)
            .build();

    public static final Relationship MY_RELATIONSHIP = new Relationship.Builder()
            .name("MY_RELATIONSHIP")
            .description("Example relationship")
            .build();
    
    public static final Relationship SUCCESS = new Relationship.Builder()
            .name("SUCCESS")
            .description("Success relationship")
            .build();

    public static final Relationship FAILURE = new Relationship.Builder()
            .name("FAILURE")
            .description("Failure relationship")
            .build();

    private List<PropertyDescriptor> descriptors;

    private Set<Relationship> relationships;

    @Override
    protected void init(final ProcessorInitializationContext context) {
        //descriptors = new ArrayList<>();
        //descriptors.add(DATA_SOURCE_YANG_MODEL_PATH);
        //descriptors.add(MY_PROPERTY);
        //descriptors = Collections.unmodifiableList(descriptors);

        relationships = new HashSet<>();
        //relationships.add(MY_RELATIONSHIP);
        relationships.add(SUCCESS);
        relationships.add(FAILURE);
        relationships = Collections.unmodifiableSet(relationships);
    }

    @Override
    public Set<Relationship> getRelationships() {
        return this.relationships;
    }

        // Code borrowed from:
    // https://git.opendaylight.org/gerrit/gitweb?p=jsonrpc.git;a=blob_plain;f=impl/src/
    // main/java/org/opendaylight/jsonrpc/impl/
    // JsonConverter.java;h=ea8069c67ece073e3d9febb694c4e15b01238c10;hb=3ea331d0e57712654d9ecbf2ae2a46cb0ce02d31
    private static final String JSON_IO_ERROR = "I/O problem in JSON codec";
    private static final Logger LOG = LoggerFactory.getLogger(PrometheusDriver.class);
    private static final JSONCodecFactorySupplier CODEC_SUPPLIER = JSONCodecFactorySupplier.DRAFT_LHOTKA_NETMOD_YANG_JSON_02;
    private static final JsonParser PARSER = new JsonParser();
    //private static final YangInstanceIdentifier ROOT = YangInstanceIdentifier.empty();
    private BindingNormalizedNodeSerializer prometheus_codec;
    private BindingNormalizedNodeSerializer openmetric_codec;
    private EffectiveModelContext schemaContext;
    //private static EffectiveModelContext prometheus_schemaContext;
    //private static EffectiveModelContext openmetrics_schemaContext;
    //private static JSONCodecFactory prometheus_codecFactory;
    //private static JSONCodecFactory openmetrics_codecFactory;
    private JSONCodecFactory codecFactory;

    @Override
    public final List<PropertyDescriptor> getSupportedPropertyDescriptors() {
        return descriptors;
    }

    @OnScheduled
    public void onScheduled(final ProcessContext context) {
        //String prometheus_yangFilePath = context.getProperty(DATA_SOURCE_YANG_MODEL_PATH).getValue();
        //String openmetrics_yangFilePath = context.getProperty(DATA_CONSUMER_YANG_MODEL_PATH).getValue();

        // Schema context initialization
        // Code borrowed from:
        // https://github.com/opendaylight/jsonrpc/blob/1331a9f73db2fe308e3bbde00ff42359431dbc7f/
        // binding-adapter/src/main/java/org/opendaylight/jsonrpc/binding/EmbeddedRpcInvocationAdapter.java#L38
        
        /*prometheus_schemaContext = BindingRuntimeHelpers.createEffectiveModel(BindingReflections.loadModuleInfos(Metric.class.getClassLoader()));
        LOG.warn(prometheus_schemaContext.toString());
        openmetrics_schemaContext = BindingRuntimeHelpers.createEffectiveModel(BindingReflections.loadModuleInfos(MetricFamilies.class.getClassLoader()));
        LOG.warn(openmetrics_schemaContext.toString());*/

        schemaContext = BindingRuntimeHelpers.createEffectiveModel(BindingReflections.loadModuleInfos());
        
        /*YangTextSchemaSource prometheus_yangTextSchemaSource = YangTextSchemaSource.forFile(new File(prometheus_yangFilePath));
        YangTextSchemaSource openmetrics_yangTextSchemaSource = YangTextSchemaSource.forFile(new File(openmetrics_yangFilePath));
        StatementStreamSource prometheus_yangModuleSource = YangStatementStreamSource.create(prometheus_yangTextSchemaSource);
        StatementStreamSource openmetrics_yangModuleSource = YangStatementStreamSource.create(openmetrics_yangTextSchemaSource);

        CrossSourceStatementReactor.BuildAction prometheus_reactor = DefaultReactors.defaultReactor().newBuild();
        CrossSourceStatementReactor.BuildAction openmetrics_reactor = DefaultReactors.defaultReactor().newBuild();
        prometheus_reactor.addSources(prometheus_yangModuleSource);
        openmetrics_reactor.addSources(openmetrics_yangModuleSource);
        prometheus_schemaContext = prometheus_reactor.buildEffective();
        LOG.warn(prometheus_schemaContext.toString());
        openmetrics_schemaContext = openmetrics_reactor.buildEffective();
        LOG.warn(openmetrics_schemaContext.toString());*/
        

        prometheus_codec = new BindingCodecContext(BindingRuntimeHelpers.createRuntimeContext(Metric.class)); 
        openmetric_codec = new BindingCodecContext(BindingRuntimeHelpers.createRuntimeContext(MetricFamilies.class));

        codecFactory = CODEC_SUPPLIER.getShared(schemaContext);
        //prometheus_codecFactory = CODEC_SUPPLIER.getShared(prometheus_schemaContext);
        //openmetrics_codecFactory = CODEC_SUPPLIER.getShared(openmetrics_schemaContext);
    }

    @Override
    public void onTrigger(final ProcessContext context, final ProcessSession session) {
        
        final AtomicReference<String> value = new AtomicReference<>();

        FlowFile flowfile = session.get();

        if ( flowfile == null ) {
            return;
        }
        
        // To read the input from the flowfile 
        session.read(flowfile, new InputStreamCallback() {
            @Override
            public void process(InputStream in) throws IOException {
                try{
                    //String json = IOUtils.toString(in);
                    JsonParser jsonParser = new JsonParser();
                    JsonObject json = (JsonObject) jsonParser.parse(new InputStreamReader(in, "UTF-8"));
                    String json_ietf = driver(json);
                    //String json_ietf = test(json);
                    value.set(json_ietf);
                }catch(Exception ex){
                    //ex.printStackTrace();
                    //getLogger().error(ex.getMessage());
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

    // Schema context initialization
    // Code borrowed from:
    // https://github.com/opendaylight/jsonrpc/blob/1331a9f73db2fe308e3bbde00ff42359431dbc7f/
    // binding-adapter/src/main/java/org/opendaylight/jsonrpc/binding/EmbeddedRpcInvocationAdapter.java#L38
    //private static final EffectiveModelContext schemaContext = BindingRuntimeHelpers
                //.createEffectiveModel(BindingReflections.loadModuleInfos());


    // Code borrowed from:
    // https://git.opendaylight.org/gerrit/gitweb?p=jsonrpc.git;a=blob_plain;f=impl/src/
    // main/java/org/opendaylight/jsonrpc/impl/
    // JsonConverter.java;h=ea8069c67ece073e3d9febb694c4e15b01238c10;hb=3ea331d0e57712654d9ecbf2ae2a46cb0ce02d31
    /*private static final String JSON_IO_ERROR = "I/O problem in JSON codec";
    private static final Logger LOG = LoggerFactory.getLogger(PrometheusDriver.class);
    private static final JSONCodecFactorySupplier CODEC_SUPPLIER =
        JSONCodecFactorySupplier.DRAFT_LHOTKA_NETMOD_YANG_JSON_02;
    private static final JsonParser PARSER = new JsonParser();
    private static final YangInstanceIdentifier ROOT = YangInstanceIdentifier.empty();*/

    /**
     * Performs the actual data conversion.
     *
     * @param schemaPath - schema path for data
     * @param data - Normalized Node
     * @return data converted as a JsonObject
     */
    private JsonObject doConvert(SchemaPath schemaPath, NormalizedNode<?, ?> data) {
        try (StringWriter writer = new StringWriter();
                JsonWriter jsonWriter = JsonWriterFactory.createJsonWriter(writer)) {
            //final JSONCodecFactory codecFactory = CODEC_SUPPLIER.getShared(schemaContext);
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
    
    public String test(JsonObject data) {
        return data.toString();
    }

    public String driver(JsonObject data) {
        getLogger().info("InputStream JSON: " + data.toString());
        MetricBuilder metric_builder = new MetricBuilder();
        LabelBuilder label_builder = new LabelBuilder();
        ArrayList<Label> label_list = new ArrayList<Label>();
        LabelsBuilder labels_builder = new LabelsBuilder();

        JsonObject metric_key = data.get("metric").getAsJsonObject();

        Map<String, String> jsonMap = new Gson().fromJson(metric_key, new TypeToken<Map<String, String>>(){}.getType());

        for (Map.Entry<String, String> entry: jsonMap.entrySet()){
            if(entry.getKey().equals("__name__")){
                String name = metric_key.get(entry.getKey()).getAsString();
                metric_builder.setName(name);
            } else {
                label_builder.setName(entry.getKey().toString());
                String value = metric_key.get(entry.getKey()).getAsString();
                label_builder.setValue(value);
                Label label = label_builder.build();
                label_list.add(label);
            }
        }
        labels_builder.setLabel(label_list);
        Labels labels = labels_builder.build();
        metric_builder.setLabels(labels);

        JsonArray value_key = data.get("value").getAsJsonArray();
        java.math.BigDecimal timestamp = new java.math.BigDecimal(value_key.get(0).getAsString());
        java.math.BigDecimal value = new java.math.BigDecimal(value_key.get(1).getAsString());
        metric_builder.setTimestamp(timestamp);
        metric_builder.setValue(value);

        System.out.println(metric_builder.build().toString());

        getLogger().info("Metric Builder: " + metric_builder.build().toString());

        final Metric metric = metric_builder.build();

        InstanceIdentifier<Metric> iid = InstanceIdentifier.create(Metric.class);
        getLogger().info("Metric InstanceIdentifier (iid): " + iid);
        
        JsonObject gson_obj = new JsonObject();
        
        try {
            //BindingNormalizedNodeSerializer codec = new BindingCodecContext(BindingRuntimeHelpers.createRuntimeContext(Metric.class));
            //BindingNormalizedNodeSerializer open_codec = new BindingCodecContext(BindingRuntimeHelpers.createRuntimeContext(MetricFamilies.class));
            Entry<YangInstanceIdentifier, NormalizedNode<?, ?>> normalized = prometheus_codec.toNormalizedNode(iid, metric);
            gson_obj = doConvert(schemaContext.getPath(), normalized.getValue());
        } catch (Exception ex) {
            //TODO: handle exception
            ex.printStackTrace();
            StringWriter errors = new StringWriter();
            ex.printStackTrace(new PrintWriter(errors));
            getLogger().error(errors.toString());
        }
        
        /**
         * Instantiate JSONObject class from org.json library (https://mvnrepository.com/artifact/org.json/json) to get 
         * the JSON Object keys (.names() fuction).
         */
        //JSONObject json_obj = new JSONObject(gson_obj.getAsJsonObject().toString());
        //String root_container = json_obj.names().get(0).toString();
        //System.out.println(gson_obj.toString()/*.get(root_container)*/);

        getLogger().info("Driver output JSON: " + gson_obj.toString());

        return gson_obj.toString();
    }
}
