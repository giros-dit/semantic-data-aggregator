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
package upm.dit.giros.processors.logparser.vs;

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
import org.apache.nifi.processor.AbstractProcessor;
import org.apache.nifi.processor.ProcessContext;
import org.apache.nifi.processor.ProcessSession;
import org.apache.nifi.processor.ProcessorInitializationContext;
import org.apache.nifi.processor.Relationship;
import org.apache.nifi.processor.util.StandardValidators;
import org.apache.nifi.processor.io.InputStreamCallback;
import org.apache.nifi.processor.io.OutputStreamCallback;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.logparser.vs.metrics.rev210927.EVEVSSInstantiationTime;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.logparser.vs.metrics.rev210927.EVEVSSReadyTime;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.logparser.vs.metrics.rev210927.LogparserVsMetrics;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.logparser.vs.metrics.rev210927.LogparserVsMetricsBuilder;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.logparser.vs.metrics.rev210927.VerticalServiceInstantiationTime;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.logparser.vs.metrics.rev210927.LogparserVsMetrics.Operation;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.logparser.vs.metrics.rev210927.metric.set.Metrics;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.logparser.vs.metrics.rev210927.metric.set.MetricsBuilder;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.logparser.vs.metrics.rev210927.metric.set.metrics.Metric;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.logparser.vs.metrics.rev210927.metric.set.metrics.MetricBuilder;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715.DateAndTime;

import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

import java.util.Map;
import java.util.Map.Entry;

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

@Tags({"LogParser Vertical Slicer (VS) metrics YANGTools Driver"})
@CapabilityDescription("NiFi processor to implement the LogParser Vertical Slicer (VS) metrics YANGTools Driver.")
@SeeAlso({})
@ReadsAttributes({@ReadsAttribute(attribute="", description="")})
@WritesAttributes({@WritesAttribute(attribute="", description="")})
public class LogParserVSDriver extends AbstractProcessor {

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
        //descriptors.add(MY_PROPERTY);
        //descriptors = Collections.unmodifiableList(descriptors);

        relationships = new HashSet<>();
        //relationships.add(MY_RELATIONSHIP);
        relationships.add(SUCCESS);
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

        if ( flowfile == null ) {
            return;
        }
        
        // To read the input from the flowfile 
        session.read(flowfile, new InputStreamCallback() {
            @Override
            public void process(InputStream in) throws IOException {
                try{
                    JsonParser jsonParser = new JsonParser();
                    JsonObject json = (JsonObject) jsonParser.parse(new InputStreamReader(in, "UTF-8"));
                    String json_ietf = driver(json);
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
    private static final EffectiveModelContext schemaContext = BindingRuntimeHelpers
                .createEffectiveModel(BindingReflections.loadModuleInfos());


    // Code borrowed from:
    // https://git.opendaylight.org/gerrit/gitweb?p=jsonrpc.git;a=blob_plain;f=impl/src/
    // main/java/org/opendaylight/jsonrpc/impl/
    // JsonConverter.java;h=ea8069c67ece073e3d9febb694c4e15b01238c10;hb=3ea331d0e57712654d9ecbf2ae2a46cb0ce02d31
    private static final String JSON_IO_ERROR = "I/O problem in JSON codec";
    private static final Logger LOG = LoggerFactory.getLogger(LogParserVSDriver.class);
    private static final JSONCodecFactorySupplier CODEC_SUPPLIER =
        JSONCodecFactorySupplier.DRAFT_LHOTKA_NETMOD_YANG_JSON_02;
    private static final JsonParser PARSER = new JsonParser();
    //private static final YangInstanceIdentifier ROOT = YangInstanceIdentifier.empty();

    /**
     * Performs the actual data conversion.
     *
     * @param schemaPath - schema path for data
     * @param data - Normalized Node
     * @return data converted as a JsonObject
     */
    private static JsonObject doConvert(SchemaPath schemaPath, NormalizedNode<?, ?> data) {
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

    private String driver(JsonObject metrics_data) {   

        getLogger().info("InputStream JSON: " + metrics_data.toString());

        JsonObject driver_output = new JsonObject();

        LogparserVsMetricsBuilder vs_metrics_builder = new LogparserVsMetricsBuilder();

        String current_time = metrics_data.get("Current_time").getAsString();
        DateAndTime dateAndTime = new DateAndTime(current_time);
        vs_metrics_builder.setCurrentTime(dateAndTime);

        switch(metrics_data.get("Operation").getAsString()){
            case "Instantiation_CSMF_Level_Integration":
            vs_metrics_builder.setOperation(Operation.InstantiationCSMFLevelIntegration);
            driver_output = instantiation_CSMF_level_integration_metrics_builder(metrics_data, vs_metrics_builder);
            break;
            default:
            driver_output = null;
        }

        getLogger().info("Driver output JSON: " + driver_output.toString());

        return driver_output.toString();
    }

    private JsonObject instantiation_CSMF_level_integration_metrics_builder(JsonObject metrics_data, LogparserVsMetricsBuilder vs_metrics_builder){  

        getLogger().info("InputStream JSON: " + metrics_data.toString());

        String vsi_id = metrics_data.get("VSI_ID").getAsString();
        vs_metrics_builder.setVSIID(vsi_id);

        String vssi_id = metrics_data.get("VSSI_ID").getAsString();
        vs_metrics_builder.setVSSIID(vssi_id);

        String vsb_id = metrics_data.get("VSB_ID").getAsString();
        vs_metrics_builder.setVSBID(vsb_id);

        String instance_name = metrics_data.get("Instance_Name").getAsString();
        vs_metrics_builder.setInstanceName(instance_name);

        MetricBuilder metricBuilder = new MetricBuilder();
        ArrayList<Metric> metric_list = new ArrayList<Metric>();
        MetricsBuilder metricsBuilder = new MetricsBuilder();
        Metric metric;

        Long vertical_service_instantiation_time = metrics_data.get("Vertical_Service_Instantiation_Time").getAsLong();
        metricBuilder.setName(VerticalServiceInstantiationTime.class);
        metricBuilder.setValue(vertical_service_instantiation_time);
        metric = metricBuilder.build();
        metric_list.add(metric);

        Long eve_vss_instantiation_time = metrics_data.get("EVE_VSS_Instantiation_Time").getAsLong();
        metricBuilder.setName(EVEVSSInstantiationTime.class);
        metricBuilder.setValue(eve_vss_instantiation_time);
        metric = metricBuilder.build();
        metric_list.add(metric);

        Long eve_vss_ready_time = metrics_data.get("EVE_VSS_Ready_Time").getAsLong();
        metricBuilder.setName(EVEVSSReadyTime.class);
        metricBuilder.setValue(eve_vss_ready_time);
        metric = metricBuilder.build();
        metric_list.add(metric);

        metricsBuilder.setMetric(metric_list);

        Metrics metrics = metricsBuilder.build();

        vs_metrics_builder.setMetrics(metrics);

        getLogger().info("LogParser VS Instantiation CSMF Level Integration metrics builder: " + vs_metrics_builder.build().toString());

        final LogparserVsMetrics instantiation_CSMF_level_integration_metrics = vs_metrics_builder.build();
        InstanceIdentifier<LogparserVsMetrics> iid = InstanceIdentifier.create(LogparserVsMetrics.class);
        getLogger().info("LogParser VS Instantiation CSMF Level Integration metrics InstanceIdentifier (iid): " + iid);
        BindingNormalizedNodeSerializer codec = new BindingCodecContext(BindingRuntimeHelpers.createRuntimeContext(LogparserVsMetrics.class));
        Entry<YangInstanceIdentifier, NormalizedNode<?, ?>> normalized = codec.toNormalizedNode(iid, instantiation_CSMF_level_integration_metrics);

        JsonObject driver_output = doConvert(schemaContext.getPath(), normalized.getValue());

        getLogger().info("Driver output JSON: " + driver_output.toString());

        return driver_output;
    }
}
