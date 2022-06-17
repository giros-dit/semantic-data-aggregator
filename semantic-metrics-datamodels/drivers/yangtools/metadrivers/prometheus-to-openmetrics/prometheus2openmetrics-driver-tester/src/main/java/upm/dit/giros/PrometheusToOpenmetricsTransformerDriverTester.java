package upm.dit.giros;

import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.MetricFamilies;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.MetricFamiliesBuilder;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.MetricType;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.label.set.Labels;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.label.set.LabelsBuilder;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.label.set.labels.Label;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.label.set.labels.LabelBuilder;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.metric.families.MetricFamily;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.metric.families.MetricFamilyBuilder;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.metric.point.set.MetricPoints;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.metric.point.set.MetricPointsBuilder;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.metric.point.set.metric.points.metric.points.choice.MetricPointList;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.metric.point.set.metric.points.metric.points.choice.MetricPointListBuilder;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.metric.point.set.metric.points.metric.points.choice.MetricPointSingle;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.metric.point.set.metric.points.metric.points.choice.MetricPointSingleBuilder;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.metric.point.set.metric.points.metric.points.choice.metric.point.list.MetricPoint;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.metric.point.set.metric.points.metric.points.choice.metric.point.list.MetricPointBuilder;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.metric.point.set.metric.points.metric.points.choice.metric.point.list.MetricPointKey;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.prometheus.rev211118.Metrics;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.prometheus.rev211118.label.set.labels.LabelKey;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.prometheus.rev211118.metric.point.set.metric.points.MetricPointsChoice;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.prometheus.rev211118.metrics.Metric;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.prometheus.rev211118.metrics.MetricKey;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;

import java.util.Map.Entry;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
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
import org.opendaylight.yangtools.yang.data.codec.gson.JsonParserStream;
import org.opendaylight.yangtools.yang.data.codec.gson.JsonWriterFactory;
import org.opendaylight.yangtools.yang.data.impl.schema.ImmutableNormalizedNodeStreamWriter;
import org.opendaylight.yangtools.yang.data.impl.schema.NormalizedNodeResult;
import org.opendaylight.yangtools.yang.model.api.EffectiveModelContext;
import org.opendaylight.yangtools.yang.model.api.SchemaPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Prometheus to OpenMetrics YANGTools Transformer Driver Tester.
 */
public class PrometheusToOpenmetricsTransformerDriverTester extends Thread {
    
    // Code borrowed from:
    // https://git.opendaylight.org/gerrit/gitweb?p=jsonrpc.git;a=blob_plain;f=impl/src/
    // main/java/org/opendaylight/jsonrpc/impl/
    private static final String JSON_IO_ERROR = "I/O problem in JSON codec";
    private static final Logger LOG = LoggerFactory.getLogger(PrometheusToOpenmetricsTransformerDriverTester.class);
    private static final JSONCodecFactorySupplier CODEC_SUPPLIER = JSONCodecFactorySupplier.RFC7951;
    private static final JsonParser PARSER = new JsonParser();
    private BindingNormalizedNodeSerializer codec;
    private EffectiveModelContext schemaContext;
    private JSONCodecFactory codecFactory;
    private String metric_family_name;
    private String metric_family_type;
    private String metric_family_unit;
    private String metric_family_help;

    public PrometheusToOpenmetricsTransformerDriverTester(String metric_family_name, String metric_family_type, String metric_family_unit, String metric_family_help){
        schemaContext = BindingRuntimeHelpers.createEffectiveModel(BindingReflections.loadModuleInfos());
        codec = new BindingCodecContext(BindingRuntimeHelpers.createRuntimeContext());
        codecFactory = CODEC_SUPPLIER.getShared(schemaContext);
        this.metric_family_name = metric_family_name;
        this.metric_family_type = metric_family_type;
        this.metric_family_unit = metric_family_unit;
        this.metric_family_help = metric_family_help;
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
    
    public void run(){
        int i = 0;
        while (i < 100){
            File driver_input = new File(PrometheusToOpenmetricsMetaDriverTester.class.getClassLoader().getResource("transformer-driver-input.json").getFile());
            FileInputStream inputStream;
            try {
                inputStream = new FileInputStream(driver_input);
                JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));
                NormalizedNodeResult result = new NormalizedNodeResult();
                NormalizedNodeStreamWriter streamWriter = ImmutableNormalizedNodeStreamWriter.from(result);
                JsonParserStream jsonParser = JsonParserStream.create(streamWriter, codecFactory);
                jsonParser.parse(reader);
                NormalizedNode<?, ?> transformedInput = result.getResult();
                InstanceIdentifier<Metrics> prometheus_iid = InstanceIdentifier.create(Metrics.class);
                YangInstanceIdentifier prometheus_yiid = codec.toYangInstanceIdentifier(prometheus_iid);
                Metrics prom_metrics = (Metrics) codec.fromNormalizedNode(prometheus_yiid, transformedInput).getValue();
                // Instantiate OpenMetrics MetricsBuilder object
                org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.metric.set.MetricsBuilder openmetrics_metricsBuilder = new org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.metric.set.MetricsBuilder();
                // Instantiate array list object for Prometheus metrics
                ArrayList<org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.metric.set.metrics.Metric> openmetrics_metric_list = new ArrayList<org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.metric.set.metrics.Metric>();
        
                // Mapping/translate Prometheus labels to OpenMetrics labels
                for (Map.Entry<MetricKey, Metric> prom_metric: prom_metrics.getMetric().entrySet()){
                    
                    // Instantiate OpenMetrics MetricPointSingleBuilder object to record single Prometheus metric or list of Prometheus metric data
                    MetricPointsBuilder metricPointsBuilder = new MetricPointsBuilder();
        
                    MetricPointsChoice metricPointsChoice = prom_metric.getValue().getMetricPoints().getMetricPointsChoice();
                    if(metricPointsChoice instanceof org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.prometheus.rev211118.metric.point.set.metric.points.metric.points.choice.MetricPointSingle){
                        org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.prometheus.rev211118.metric.point.set.metric.points.metric.points.choice.MetricPointSingle metricPointSingle = (org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.prometheus.rev211118.metric.point.set.metric.points.metric.points.choice.MetricPointSingle) metricPointsChoice;
                        BigDecimal timestamp = metricPointSingle.getTimestamp();
                        BigDecimal value = metricPointSingle.getValue();
                        // Instantiate Prometheus MetricPointSingleBuilder object to record single Prometheus metric data
                        MetricPointSingleBuilder metricPointBuilder = new MetricPointSingleBuilder();
                        // Translate Prometheus metric timestamp
                        metricPointBuilder.setTimestamp(timestamp.multiply(new BigDecimal(1000)).longValue());
                        // Translate Prometheus metric value
                        metricPointBuilder.setValue(value);
                        // Building Openmetrics MetricPoint object with single Prometheus metric information
                        MetricPointSingle metricPoint = metricPointBuilder.build();
                        metricPointsBuilder.setMetricPointsChoice(metricPoint);
                    }else{
                        if(metricPointsChoice instanceof org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.prometheus.rev211118.metric.point.set.metric.points.metric.points.choice.MetricPointList){
                            org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.prometheus.rev211118.metric.point.set.metric.points.metric.points.choice.MetricPointList prom_metricPointList = (org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.prometheus.rev211118.metric.point.set.metric.points.metric.points.choice.MetricPointList) metricPointsChoice;
                            // Instantiate OpenMetrics MetricPointListBuilder object to record single Prometheus metric data
                            MetricPointListBuilder metricPointListBuilder = new MetricPointListBuilder();
                            MetricPointBuilder metricPointBuilder = new MetricPointBuilder();
                            Map<MetricPointKey,MetricPoint> metricPoint_map = new HashMap<MetricPointKey,MetricPoint>();
                            for(Map.Entry<org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.prometheus.rev211118.metric.point.set.metric.points.metric.points.choice.metric.point.list.MetricPointKey, org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.prometheus.rev211118.metric.point.set.metric.points.metric.points.choice.metric.point.list.MetricPoint> prom_metricPoint: prom_metricPointList.getMetricPoint().entrySet()){
                                java.math.BigDecimal timestamp = prom_metricPoint.getValue().getTimestamp();
                                metricPointBuilder.setTimestamp(timestamp.multiply(new BigDecimal(1000)).longValue());
                                java.math.BigDecimal value = prom_metricPoint.getValue().getTimestamp();
                                metricPointBuilder.setValue(value);
                                MetricPoint metricPoint = metricPointBuilder.build();
                                metricPoint_map.put(metricPoint.key(), metricPoint);
                            }
                            metricPointListBuilder.setMetricPoint(metricPoint_map);
                            // Building OpenMetrics MetricPointList object with a list of Prometheus metrics information
                            MetricPointList openmetrics_metricPointList = metricPointListBuilder.build();
                            metricPointsBuilder.setMetricPointsChoice(openmetrics_metricPointList);
                        }
                    }
        
                    MetricPoints metricPoints = metricPointsBuilder.build();
        
                    // Get Prometheus metric name
                    String metric_name = prom_metric.getValue().getName();
        
                    // Translate Prometheus metric labels, instantiating builder objects
                    LabelBuilder openmetrics_labelBuilder = new LabelBuilder();
                    ArrayList<Label> openmetrics_label_list = new ArrayList<Label>();
                    LabelsBuilder openmetrics_labels_builder = new LabelsBuilder();
        
                    // Get Prometheus metric labels
                    org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.prometheus.rev211118.label.set.Labels prom_labels = prom_metric.getValue().getLabels();
                    
                    // Map (dictionary) with Prometheus metric name and labels to calculate the Hash code required for label-set-id YANG node in OpenMetrics YANG model
                    Map<String, String> dictionary_prom_labels = new HashMap<String, String>();
                    // Adding Prometheus metric name in the dictionary for calculating the Hash code
                    dictionary_prom_labels.put("__name__", metric_name);
                    
                    // Mapping/translate Prometheus labels to OpenMetrics labels
                    for (Map.Entry<LabelKey, org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.prometheus.rev211118.label.set.labels.Label> entry: prom_labels.getLabel().entrySet()){
                        openmetrics_labelBuilder.setName(entry.getValue().getName());
                        openmetrics_labelBuilder.setValue(entry.getValue().getValue());
                        Label openmetrics_label = openmetrics_labelBuilder.build();
                        openmetrics_label_list.add(openmetrics_label);
                        // Adding Prometheus labels in the dictionary for calculating the Hash code 
                        dictionary_prom_labels.put(entry.getValue().getName(), entry.getValue().getValue());
                    }
        
                    // Building OpenMetrics Labels
                    openmetrics_labels_builder.setLabel(openmetrics_label_list);
                    Labels openmetrics_labels = openmetrics_labels_builder.build();
        
                    // Instantiate Openmetrics MetricBuilder object and building the regarding Metric with the corresponding metric point, labels and label-set-id
                    org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.metric.set.metrics.MetricBuilder openmetrics_metricBuilder = new  org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.metric.set.metrics.MetricBuilder();
                    openmetrics_metricBuilder.setName(metric_name);
                    openmetrics_metricBuilder.setMetricPoints(metricPoints);
                    openmetrics_metricBuilder.setLabels(openmetrics_labels);
                    openmetrics_metricBuilder.setLabelSetId(Integer.toString(dictionary_prom_labels.hashCode()));
                    org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.metric.set.metrics.Metric openmetrics_metric = openmetrics_metricBuilder.build();
                    openmetrics_metric_list.add(openmetrics_metric);
                }
        
                // Building the regarding OpenMetrics Metrics object with the corresponding Prometheus metric list
                openmetrics_metricsBuilder.setMetric(openmetrics_metric_list);
                org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.metric.set.Metrics openmetrics_metrics = openmetrics_metricsBuilder.build();
        
                // Instantiating and building the OpenMetrics MetricFamily object with the associated semantic data
                MetricFamilyBuilder metricFamilyBuilder = new MetricFamilyBuilder();
                metricFamilyBuilder.setMetrics(openmetrics_metrics);
                MetricType metricType = MetricType.forName(metric_family_type).get();
                metricFamilyBuilder.setMetricType(metricType);
                metricFamilyBuilder.setName(metric_family_name);
        
                if(metric_family_help != null){
                    if(!metric_family_help.isBlank()){
                        metricFamilyBuilder.setHelp(metric_family_help);
                    }else{
                        metricFamilyBuilder.setHelp(null);
                    }
                }else{
                    metricFamilyBuilder.setHelp(null);
                }
                
                if(metric_family_unit != null){
                    if(!metric_family_unit.isBlank()){
                        metricFamilyBuilder.setUnit(metric_family_unit);
                    }else{
                        metricFamilyBuilder.setUnit(null);
                    }
                }else{
                    metricFamilyBuilder.setUnit(null);
                }
            
                final MetricFamily metricFamily = metricFamilyBuilder.build();
        
                // Instantiating and building the OpenMetrics MetricFamilies object
                ArrayList<MetricFamily> metric_family_list = new ArrayList<MetricFamily>();
                metric_family_list.add(metricFamily);
                MetricFamiliesBuilder metricFamiliesBuilder = new MetricFamiliesBuilder();
                metricFamiliesBuilder.setMetricFamily(metric_family_list);
                final MetricFamilies metricFamilies = metricFamiliesBuilder.build();
        
                InstanceIdentifier<MetricFamilies> openmetric_iid = InstanceIdentifier.create(MetricFamilies.class);
                Entry<YangInstanceIdentifier, NormalizedNode<?, ?>> normalized = codec.toNormalizedNode(openmetric_iid, metricFamilies);
                
                JsonObject driver_output = doConvert(schemaContext, schemaContext.getPath(), normalized.getValue());
                
                System.out.println("PromethesToOpenmetrics transformer driver output: " + driver_output.toString());
                System.out.println("");
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            i++;
        }
    }
}
