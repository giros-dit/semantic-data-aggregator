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
package upm.dit.giros.processors.logparser.so;

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
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715.DateAndTime;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.logparser.so.metrics.rev210927.*;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.logparser.so.metrics.rev210927.LogparserSoMetrics.Operation;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.logparser.so.metrics.rev210927.metric.set.Metrics;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.logparser.so.metrics.rev210927.metric.set.MetricsBuilder;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.logparser.so.metrics.rev210927.metric.set.metrics.Metric;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.logparser.so.metrics.rev210927.metric.set.metrics.MetricBuilder;

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

@Tags({"LogParser Service Orchestrator (SO) metrics YANGTools Driver"})
@CapabilityDescription("NiFi processor to implement the LogParser Service Orchestrator (SO) metrics YANGTools Driver.")
@SeeAlso({})
@ReadsAttributes({@ReadsAttribute(attribute="", description="")})
@WritesAttributes({@WritesAttribute(attribute="", description="")})
public class LogParserSODriver extends AbstractProcessor {

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
    private static final Logger LOG = LoggerFactory.getLogger(LogParserSODriver.class);
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

        LogparserSoMetricsBuilder so_metrics_builder = new LogparserSoMetricsBuilder();

        String current_time = metrics_data.get("Current_time").getAsString();
        DateAndTime dateAndTime = new DateAndTime(current_time);
        so_metrics_builder.setCurrentTime(dateAndTime);

        String ns_id = metrics_data.get("NS_ID").getAsString();
        so_metrics_builder.setNSID(ns_id);

        String nsd_id = metrics_data.get("NSD_ID").getAsString();
        so_metrics_builder.setNSDID(nsd_id);

        switch(metrics_data.get("Operation").getAsString()){
            case "Instantiation":
            so_metrics_builder.setOperation(Operation.Instantiation);
            driver_output = instantiation_metrics_builder(metrics_data, so_metrics_builder);
            break;
            case "termination":
            so_metrics_builder.setOperation(Operation.Termination);
            driver_output = termination_metrics_builder(metrics_data, so_metrics_builder);
            break;
            default:
            driver_output = null;
        }

        getLogger().info("Driver output JSON: " + driver_output.toString());

        return driver_output.toString();
    }

    private JsonObject instantiation_metrics_builder(JsonObject metrics_data, LogparserSoMetricsBuilder so_metrics_builder){

        MetricBuilder metricBuilder = new MetricBuilder();
        ArrayList<Metric> metric_list = new ArrayList<Metric>();
        MetricsBuilder metricsBuilder = new MetricsBuilder();
        Metric metric;

        Long total_instantiation_time = metrics_data.get("Total_instantiation_time").getAsLong();
        metricBuilder.setName(TotalInstantiationTime.class);
        metricBuilder.setValue(total_instantiation_time);
        metric = metricBuilder.build();
        metric_list.add(metric);

        Long soe_time = metrics_data.get("SOE_time").getAsLong();
        metricBuilder.setName(SOETime.class);
        metricBuilder.setValue(soe_time);
        metric = metricBuilder.build();
        metric_list.add(metric);

        Long roe_time = metrics_data.get("ROE_time").getAsLong();
        metricBuilder.setName(ROETime.class);
        metricBuilder.setValue(roe_time);
        metric = metricBuilder.build();
        metric_list.add(metric);

        Long operation_id_for_instantiation_op = metrics_data.get("Operation_ID_for_instantiation_op").getAsLong();
        metricBuilder.setName(OperationIDForInstantiationOp.class);
        metricBuilder.setValue(operation_id_for_instantiation_op);
        metric = metricBuilder.build();
        metric_list.add(metric);

        Long hierarchical_soe_dispatching_soepsoec = metrics_data.get("Hierarchical_SOE_dispatching_SOEpSOEc").getAsLong();
        metricBuilder.setName(HierarchicalSOEDispatchingSOEpSOEc.class);
        metricBuilder.setValue(hierarchical_soe_dispatching_soepsoec);
        metric = metricBuilder.build();
        metric_list.add(metric);

        Long roe_created_vls = metrics_data.get("ROE_created_VLs").getAsLong();
        metricBuilder.setName(ROECreatedVLs.class);
        metricBuilder.setValue(roe_created_vls);
        metric = metricBuilder.build();
        metric_list.add(metric);

        Long roe_retrieve_rl_resources = metrics_data.get("ROE_retrieve_RL_resources").getAsLong();
        metricBuilder.setName(ROERetrieveRLResources.class);
        metricBuilder.setValue(roe_retrieve_rl_resources);
        metric = metricBuilder.build();
        metric_list.add(metric);

        Long roe_parsing_nsds = metrics_data.get("ROE_parsing_NSDs").getAsLong();
        metricBuilder.setName(ROEParsingNSDs.class);
        metricBuilder.setValue(roe_parsing_nsds);
        metric = metricBuilder.build();
        metric_list.add(metric);

        Long roe_updating_dbs = metrics_data.get("ROE_updating_DBs").getAsLong();
        metricBuilder.setName(ROEUpdatingDBs.class);
        metricBuilder.setValue(roe_updating_dbs);
        metric = metricBuilder.build();
        metric_list.add(metric);

        Long roe_extract_vls = metrics_data.get("ROE_extract_VLs").getAsLong();
        metricBuilder.setName(ROEExtractVLs.class);
        metricBuilder.setValue(roe_extract_vls);
        metric = metricBuilder.build();
        metric_list.add(metric);

        Long retrieving_descriptor_from_catalogue_dbs = metrics_data.get("Retrieving_descriptor_from_catalogue_DBs").getAsLong();
        metricBuilder.setName(RetrievingDescriptorFromCatalogueDBs.class);
        metricBuilder.setValue(retrieving_descriptor_from_catalogue_dbs);
        metric = metricBuilder.build();
        metric_list.add(metric);

        Long pa_calculation = metrics_data.get("PA_calculation").getAsLong();
        metricBuilder.setName(PACalculation.class);
        metricBuilder.setValue(pa_calculation);
        metric = metricBuilder.build();
        metric_list.add(metric);

        Long create_threshold_based_alerts = metrics_data.get("Create_threshold_based_alerts").getAsLong();
        metricBuilder.setName(CreateThresholdBasedAlerts.class);
        metricBuilder.setValue(create_threshold_based_alerts);
        metric = metricBuilder.build();
        metric_list.add(metric);

        Long create_monitoring_jobs = metrics_data.get("Create_monitoring_jobs").getAsLong();
        metricBuilder.setName(CreateMonitoringJobs.class);
        metricBuilder.setValue(create_monitoring_jobs);
        metric = metricBuilder.build();
        metric_list.add(metric);

        Long create_aiml_alerts = metrics_data.get("Create_AIML_alerts").getAsLong();
        metricBuilder.setName(CreateAIMLAlerts.class);
        metricBuilder.setValue(create_aiml_alerts);
        metric = metricBuilder.build();
        metric_list.add(metric);

        Long coremano_wrapper_time = metrics_data.get("CoreMANO_Wrapper_time").getAsLong();
        metricBuilder.setName(CoreMANOWrapperTime.class);
        metricBuilder.setValue(coremano_wrapper_time);
        metric = metricBuilder.build();
        metric_list.add(metric);

        metricsBuilder.setMetric(metric_list);

        Metrics metrics = metricsBuilder.build();

        so_metrics_builder.setMetrics(metrics);

        getLogger().info("LogParser SO instantiation metrics builder: " + so_metrics_builder.build().toString());

        final LogparserSoMetrics instantiation_metrics = so_metrics_builder.build();
        InstanceIdentifier<LogparserSoMetrics> iid = InstanceIdentifier.create(LogparserSoMetrics.class);
        getLogger().info("LogParser SO instantiation metrics InstanceIdentifier (iid): " + iid);
        BindingNormalizedNodeSerializer codec = new BindingCodecContext(BindingRuntimeHelpers.createRuntimeContext(LogparserSoMetrics.class));
        Entry<YangInstanceIdentifier, NormalizedNode<?, ?>> normalized = codec.toNormalizedNode(iid, instantiation_metrics);

        JsonObject json_obj = doConvert(schemaContext.getPath(), normalized.getValue());

        return json_obj;
    }

    private JsonObject termination_metrics_builder(JsonObject metrics_data, LogparserSoMetricsBuilder so_metrics_builder){
        
        MetricBuilder metricBuilder = new MetricBuilder();
        ArrayList<Metric> metric_list = new ArrayList<Metric>();
        MetricsBuilder metricsBuilder = new MetricsBuilder();
        Metric metric;

        Long total_termination_time = metrics_data.get("Total_termination_time").getAsLong();
        metricBuilder.setName(TotalTerminationTime.class);
        metricBuilder.setValue(total_termination_time);
        metric = metricBuilder.build();
        metric_list.add(metric);

        Long soe_time = metrics_data.get("SOE_time").getAsLong();
        metricBuilder.setName(SOETime.class);
        metricBuilder.setValue(soe_time);
        metric = metricBuilder.build();
        metric_list.add(metric);

        Long roe_time = metrics_data.get("ROE_time").getAsLong();
        metricBuilder.setName(ROETime.class);
        metricBuilder.setValue(roe_time);
        metric = metricBuilder.build();
        metric_list.add(metric);

        Long operation_id_for_termination_op = metrics_data.get("Operation_ID_for_Termination_op").getAsLong();
        metricBuilder.setName(OperationIDForTerminationOp.class);
        metricBuilder.setValue(operation_id_for_termination_op);
        metric = metricBuilder.build();
        metric_list.add(metric);

        Long hierarchical_soe_dispatching_soepsoec = metrics_data.get("Hierarchical_SOE_dispatching_SOEpSOEc").getAsLong();
        metricBuilder.setName(HierarchicalSOEDispatchingSOEpSOEc.class);
        metricBuilder.setValue(hierarchical_soe_dispatching_soepsoec);
        metric = metricBuilder.build();
        metric_list.add(metric);

        Long roe_deleting_lls = metrics_data.get("ROE_deleting_LLs").getAsLong();
        metricBuilder.setName(ROEDeletingLLs.class);
        metricBuilder.setValue(roe_deleting_lls);
        metric = metricBuilder.build();
        metric_list.add(metric);

        Long roe_updating_dbs = metrics_data.get("ROE_updating_DBs").getAsLong();
        metricBuilder.setName(ROEUpdatingDBs.class);
        metricBuilder.setValue(roe_updating_dbs);
        metric = metricBuilder.build();
        metric_list.add(metric);

        Long terminating_threshold_based_alerts = metrics_data.get("Terminating_Threshold_based_alerts").getAsLong();
        metricBuilder.setName(TerminatingThresholdBasedAlerts.class);
        metricBuilder.setValue(terminating_threshold_based_alerts);
        metric = metricBuilder.build();
        metric_list.add(metric);

        Long terminating_monitoring_jobs = metrics_data.get("Terminating_Monitoring_jobs").getAsLong();
        metricBuilder.setName(TerminatingMonitoringJobs.class);
        metricBuilder.setValue(terminating_monitoring_jobs);
        metric = metricBuilder.build();
        metric_list.add(metric);

        Long terminating_aiml_alert_jobs = metrics_data.get("Terminating_AIML_alert_jobs").getAsLong();
        metricBuilder.setName(TerminatingAIMLAlertJobs.class);
        metricBuilder.setValue(terminating_aiml_alert_jobs);
        metric = metricBuilder.build();
        metric_list.add(metric);

        Long coremano_wrapper_time = metrics_data.get("CoreMANO_Wrapper_time").getAsLong();
        metricBuilder.setName(CoreMANOWrapperTime.class);
        metricBuilder.setValue(coremano_wrapper_time);
        metric = metricBuilder.build();
        metric_list.add(metric);

        metricsBuilder.setMetric(metric_list);

        Metrics metrics = metricsBuilder.build();

        so_metrics_builder.setMetrics(metrics);

        getLogger().info("LogParser SO termination metrics builder: " + so_metrics_builder.build().toString());

        final LogparserSoMetrics termination_metrics = so_metrics_builder.build();
        InstanceIdentifier<LogparserSoMetrics> iid = InstanceIdentifier.create(LogparserSoMetrics.class);
        getLogger().info("LogParser SO termination metrics InstanceIdentifier (iid): " + iid);
        BindingNormalizedNodeSerializer codec = new BindingCodecContext(BindingRuntimeHelpers.createRuntimeContext(LogparserSoMetrics.class));
        Entry<YangInstanceIdentifier, NormalizedNode<?, ?>> normalized = codec.toNormalizedNode(iid, termination_metrics);

        JsonObject json_obj = doConvert(schemaContext.getPath(), normalized.getValue());

        return json_obj;
    }
}
