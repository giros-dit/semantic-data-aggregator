package upm.dit.giros;

import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715.DateAndTime;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.logparser.so.metrics.rev210927.*;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.logparser.so.metrics.rev210927.LogparserSoMetrics.Operation;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.logparser.so.metrics.rev210927.metric.set.Metrics;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.logparser.so.metrics.rev210927.metric.set.MetricsBuilder;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.logparser.so.metrics.rev210927.metric.set.metrics.Metric;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.logparser.so.metrics.rev210927.metric.set.metrics.MetricBuilder;

import java.io.*;
import java.util.*;

import java.util.Map;
import java.util.Map.Entry;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonWriter;

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

/**
 * LogParser YANGTools Source Driver Tester.
 */
public class LogParserSourceDriverTester extends Thread {

    // Code borrowed from:
    // https://git.opendaylight.org/gerrit/gitweb?p=jsonrpc.git;a=blob_plain;f=impl/src/
    // main/java/org/opendaylight/jsonrpc/impl/
    private static final String JSON_IO_ERROR = "I/O problem in JSON codec";
    private static final Logger LOG = LoggerFactory.getLogger(LogParserSourceDriverTester.class);
    private static final JSONCodecFactorySupplier CODEC_SUPPLIER = JSONCodecFactorySupplier.RFC7951;
    private static final JsonParser PARSER = new JsonParser();
    private BindingNormalizedNodeSerializer codec;
    private EffectiveModelContext schemaContext;
    private JSONCodecFactory codecFactory;

    public LogParserSourceDriverTester(){
        schemaContext = BindingRuntimeHelpers.createEffectiveModel(BindingReflections.loadModuleInfos(LogparserSoMetrics.class.getClassLoader()));
        codec = new BindingCodecContext(BindingRuntimeHelpers.createRuntimeContext(LogparserSoMetrics.class));
        codecFactory = CODEC_SUPPLIER.getShared(schemaContext);
    }


    /**
     * Performs the actual data conversion.
     *
     * @param schemaPath - schema path for data
     * @param data - Normalized Node
     * @return data converted as a JsonObject
     */
    private JsonObject doConvert(EffectiveModelContext schemaContext, SchemaPath schemaPath, NormalizedNode<?, ?> data) {
        try (StringWriter writer = new StringWriter();
                JsonWriter jsonWriter = JsonWriterFactory.createJsonWriter(writer)) {
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

    public void run() {   
        int n = 0;
        while (n < 100) {
            File driver_input = new File(LogParserToOpenmetricsMetaDriverTester.class.getClassLoader().getResource("source-instantiation-input.json").getFile());
            FileInputStream inputStream;
            try {
                inputStream = new FileInputStream(driver_input);
                JsonParser jsonParser = new JsonParser();
                JsonObject metrics_data = (JsonObject) jsonParser.parse(new InputStreamReader(inputStream, "UTF-8"));

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
                    case "instantiation":
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
                System.out.println("LogParserSource driver output: " + driver_output.toString());
                System.out.println(" ");
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (JsonIOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (JsonSyntaxException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            n++;
        }
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

        final LogparserSoMetrics instantiation_metrics = so_metrics_builder.build();
        InstanceIdentifier<LogparserSoMetrics> iid = InstanceIdentifier.create(LogparserSoMetrics.class);
        BindingNormalizedNodeSerializer codec = new BindingCodecContext(BindingRuntimeHelpers.createRuntimeContext(LogparserSoMetrics.class));
        Entry<YangInstanceIdentifier, NormalizedNode<?, ?>> normalized = codec.toNormalizedNode(iid, instantiation_metrics);

        JsonObject json_obj = doConvert(schemaContext, schemaContext.getPath(), normalized.getValue());

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

        final LogparserSoMetrics termination_metrics = so_metrics_builder.build();
        InstanceIdentifier<LogparserSoMetrics> iid = InstanceIdentifier.create(LogparserSoMetrics.class);
        BindingNormalizedNodeSerializer codec = new BindingCodecContext(BindingRuntimeHelpers.createRuntimeContext(LogparserSoMetrics.class));
        Entry<YangInstanceIdentifier, NormalizedNode<?, ?>> normalized = codec.toNormalizedNode(iid, termination_metrics);

        JsonObject json_obj = doConvert(schemaContext, schemaContext.getPath(), normalized.getValue());

        return json_obj;
    }

}

