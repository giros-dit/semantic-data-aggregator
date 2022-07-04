package upm.dit.giros;

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
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.prometheus.rev211118.Metrics;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.prometheus.rev211118.MetricsBuilder;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.prometheus.rev211118.metric.point.set.MetricPoints;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.prometheus.rev211118.metric.point.set.MetricPointsBuilder;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.prometheus.rev211118.metric.point.set.metric.points.metric.points.choice.MetricPointList;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.prometheus.rev211118.metric.point.set.metric.points.metric.points.choice.MetricPointListBuilder;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.prometheus.rev211118.metric.point.set.metric.points.metric.points.choice.MetricPointSingle;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.prometheus.rev211118.metric.point.set.metric.points.metric.points.choice.MetricPointSingleBuilder;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.prometheus.rev211118.metric.point.set.metric.points.metric.points.choice.metric.point.list.MetricPoint;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.prometheus.rev211118.metric.point.set.metric.points.metric.points.choice.metric.point.list.MetricPointBuilder;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.prometheus.rev211118.metric.point.set.metric.points.metric.points.choice.metric.point.list.MetricPointKey;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.prometheus.rev211118.metrics.Metric;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.prometheus.rev211118.metrics.MetricBuilder;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.prometheus.rev211118.metrics.MetricKey;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.prometheus.rev211118.label.set.LabelsBuilder;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.prometheus.rev211118.label.set.Labels;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.prometheus.rev211118.label.set.labels.LabelBuilder;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.prometheus.rev211118.label.set.labels.Label;
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
 * Prometheus YANGTools Source Driver Tester.
 */
public class PrometheusSourceDriverTester extends Thread {

    // Code borrowed from:
    // https://git.opendaylight.org/gerrit/gitweb?p=jsonrpc.git;a=blob_plain;f=impl/src/
    // main/java/org/opendaylight/jsonrpc/impl/
    private static final String JSON_IO_ERROR = "I/O problem in JSON codec";
    private static final Logger LOG = LoggerFactory.getLogger(PrometheusSourceDriverTester.class);
    private static final JSONCodecFactorySupplier CODEC_SUPPLIER = JSONCodecFactorySupplier.RFC7951;
    private static final JsonParser PARSER = new JsonParser();
    private BindingNormalizedNodeSerializer codec;
    private EffectiveModelContext schemaContext;
    private JSONCodecFactory codecFactory;

    public PrometheusSourceDriverTester(){
        schemaContext = BindingRuntimeHelpers.createEffectiveModel(BindingReflections.loadModuleInfos(Metric.class.getClassLoader()));
        codec = new BindingCodecContext(BindingRuntimeHelpers.createRuntimeContext(Metric.class));
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
        while (n < 100){
            File driver_input = new File(PrometheusToOpenmetricsMetaDriverTester.class.getClassLoader().getResource("source-driver-input.json").getFile());
            FileInputStream inputStream;
            try {
                inputStream = new FileInputStream(driver_input);
                JsonParser jsonParser = new JsonParser();
                JsonArray data = (JsonArray) jsonParser.parse(new InputStreamReader(inputStream, "UTF-8"));
                MetricsBuilder metrics_builder = new MetricsBuilder();
                Map<MetricKey,Metric> metric_map = new HashMap<MetricKey,Metric>();
                JsonArray prom_metrics = data.getAsJsonArray();
                for(int i = 0; i < prom_metrics.size(); i++){
                    JsonObject prom_metric = prom_metrics.get(i).getAsJsonObject();
                    JsonObject metric_key = prom_metric.get("metric").getAsJsonObject();
                    MetricBuilder metric_builder = new MetricBuilder();
                    LabelBuilder label_builder = new LabelBuilder();
                    ArrayList<Label> label_list = new ArrayList<Label>();
                    LabelsBuilder labels_builder = new LabelsBuilder();
        
                    // Map (dictionary) with Prometheus metric name and labels to calculate the Hash code required for label-set-id YANG node on Prometheus YANG model
                    Map<String, String> dictionary_prom_labels = new HashMap<String, String>();
                    Map<String, String> jsonMap = new Gson().fromJson(metric_key, new TypeToken<Map<String, String>>(){}.getType());
                    for (Map.Entry<String, String> entry: jsonMap.entrySet()){
                        if(entry.getKey().equals("__name__")){
                            String name = metric_key.get(entry.getKey()).getAsString();
                            metric_builder.setName(name);
                            // Adding Prometheus metric name in the dictionary for calculating the Hash code
                            dictionary_prom_labels.put("__name__", name);
                        } else {
                            label_builder.setName(entry.getKey().toString());
                            String value = metric_key.get(entry.getKey()).getAsString();
                            label_builder.setValue(value);
                            Label label = label_builder.build();
                            label_list.add(label);
                            // Adding Prometheus labels in the dictionary for calculating the Hash code 
                            dictionary_prom_labels.put(entry.getKey().toString(), value);
                        }
                    }
                    labels_builder.setLabel(label_list);
                    Labels labels = labels_builder.build();
                    metric_builder.setLabels(labels);
                    metric_builder.setLabelSetId(Integer.toString(dictionary_prom_labels.hashCode()));
                    MetricPointsBuilder metricPointsBuilder = new MetricPointsBuilder();
                   
                    if(prom_metric.has("value")){
                        // Instantiate Prometheus MetricPointSingleBuilder object to record single Prometheus metric data
                        MetricPointSingleBuilder metricPointBuilder = new MetricPointSingleBuilder();
                        JsonArray value_key = prom_metric.get("value").getAsJsonArray();
                        java.math.BigDecimal timestamp = new java.math.BigDecimal(value_key.get(0).getAsString());
                        java.math.BigDecimal value = new java.math.BigDecimal(value_key.get(1).getAsString());
        
                        metricPointBuilder.setTimestamp(timestamp);
                        metricPointBuilder.setValue(value);
                        // Building Prometheus MetricPoint object with single Prometheus metric information
                        MetricPointSingle metricPoint = metricPointBuilder.build();
                        metricPointsBuilder.setMetricPointsChoice(metricPoint);
                    } else {
                        if(prom_metric.has("values")){
                            // Instantiate Prometheus MetricPointListBuilder object to record single Prometheus metric data
                            MetricPointListBuilder metricPointListBuilder = new MetricPointListBuilder();
                            MetricPointBuilder metricPointBuilder = new MetricPointBuilder();
                            Map<MetricPointKey,MetricPoint> metricPoint_map = new HashMap<MetricPointKey,MetricPoint>();
                            JsonArray values_key = prom_metric.get("values").getAsJsonArray();
                            for(int j = 0; j < values_key.size(); j++){
                                java.math.BigDecimal timestamp = new java.math.BigDecimal(values_key.get(j).getAsJsonArray().get(0).getAsString());
                                metricPointBuilder.setTimestamp(timestamp);
                                java.math.BigDecimal value = new java.math.BigDecimal(values_key.get(j).getAsJsonArray().get(1).getAsString());
                                metricPointBuilder.setValue(value);
                                MetricPoint metricPoint = metricPointBuilder.build();
                                metricPoint_map.put(metricPoint.key(), metricPoint);
                            }
                            metricPointListBuilder.setMetricPoint(metricPoint_map);
                            // Building Prometheus MetricPointList object with a list of Prometheus metrics information
                            MetricPointList metricPointList = metricPointListBuilder.build();
                            metricPointsBuilder.setMetricPointsChoice(metricPointList);
                        }
                    }
                    MetricPoints metricPoints = metricPointsBuilder.build();
                    metric_builder.setMetricPoints(metricPoints);
        
                    Metric metric = metric_builder.build();
                    //metric_list.add(metric);
                    metric_map.put(metric.key(), metric);
                }
        
                metrics_builder.setMetric(metric_map);
                Metrics metrics = metrics_builder.build();
        
                InstanceIdentifier<Metrics> iid = InstanceIdentifier.create(Metrics.class);
                
                JsonObject gson_obj = new JsonObject();
                
                try {
                    Entry<YangInstanceIdentifier, NormalizedNode<?, ?>> normalized = codec.toNormalizedNode(iid, metrics);
                    gson_obj = doConvert(schemaContext, schemaContext.getPath(), normalized.getValue());
                } catch (Exception ex) {
                    //TODO: handle exception
                    ex.printStackTrace();
                    StringWriter errors = new StringWriter();
                    ex.printStackTrace(new PrintWriter(errors));
                    System.out.println(errors.toString());
                }
                System.out.println("MetricSource driver output: " + gson_obj.toString());
                System.out.println("");
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
}
