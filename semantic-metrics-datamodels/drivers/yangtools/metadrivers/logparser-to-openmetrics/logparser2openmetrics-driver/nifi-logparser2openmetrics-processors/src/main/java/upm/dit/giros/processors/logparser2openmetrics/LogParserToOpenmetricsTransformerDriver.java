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
package upm.dit.giros.processors.logparser2openmetrics;

import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715.DateAndTime;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.logparser.so.metrics.rev210927.*;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.logparser.so.metrics.rev210927.LogparserSoMetrics.Operation;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.logparser.so.metrics.rev210927.metric.set.Metrics;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.logparser.so.metrics.rev210927.metric.set.MetricsBuilder;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.logparser.so.metrics.rev210927.metric.set.metrics.Metric;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.logparser.so.metrics.rev210927.metric.set.metrics.MetricKey;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.logparser.so.metrics.rev210927.metric.set.metrics.MetricBuilder;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.LabelSet;
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
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.metric.point.set.metric.points.MetricPointsChoice;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.metric.point.set.metric.points.metric.points.choice.MetricPointListBuilder;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.metric.point.set.metric.points.metric.points.choice.MetricPointSingle;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.metric.point.set.metric.points.metric.points.choice.MetricPointSingleBuilder;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.metric.point.set.metric.points.metric.points.choice.metric.point.list.MetricPoint;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.metric.point.set.metric.points.metric.points.choice.metric.point.list.MetricPointBuilder;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.metric.point.set.metric.points.metric.points.choice.metric.point.list.MetricPointKey;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.metric.set.metrics.*;

import java.io.*;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.Map.Entry;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
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
import org.opendaylight.yangtools.yang.data.codec.gson.JsonParserStream;
import org.opendaylight.yangtools.yang.data.codec.gson.JsonWriterFactory;
import org.opendaylight.yangtools.yang.data.impl.schema.ImmutableNormalizedNodeStreamWriter;
import org.opendaylight.yangtools.yang.data.impl.schema.NormalizedNodeResult;
import org.opendaylight.yangtools.yang.model.api.ContainerSchemaNode;
import org.opendaylight.yangtools.yang.model.api.DataSchemaNode;
import org.opendaylight.yangtools.yang.model.api.EffectiveModelContext;
import org.opendaylight.yangtools.yang.model.api.IdentitySchemaNode;
import org.opendaylight.yangtools.yang.model.api.LeafSchemaNode;
import org.opendaylight.yangtools.yang.model.api.SchemaContext;
import org.opendaylight.yangtools.yang.model.api.SchemaPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * LogParser to OpenMetrics YANGTools Transformer Driver.
 */
public class LogParserToOpenmetricsTransformerDriver {
    
    // Code borrowed from:
    // https://git.opendaylight.org/gerrit/gitweb?p=jsonrpc.git;a=blob_plain;f=impl/src/
    // main/java/org/opendaylight/jsonrpc/impl/
    private static final String JSON_IO_ERROR = "I/O problem in JSON codec";
    private static final Logger LOG = LoggerFactory.getLogger(LogParserToOpenmetricsTransformerDriver.class);
    private static final JSONCodecFactorySupplier CODEC_SUPPLIER = JSONCodecFactorySupplier.RFC7951;
    private static final JsonParser PARSER = new JsonParser();
    private BindingNormalizedNodeSerializer codec;
    private EffectiveModelContext schemaContext;
    private JSONCodecFactory codecFactory;
    private String metric_family_name;
    private String metric_family_type;
    private String metric_family_unit;
    private String metric_family_help;
    
    public LogParserToOpenmetricsTransformerDriver(String metric_family_name, String metric_family_type, String metric_family_unit, String metric_family_help){
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

    public String driver(JsonReader reader) throws NoSuchFieldException, SecurityException {
        NormalizedNodeResult result = new NormalizedNodeResult();
        NormalizedNodeStreamWriter streamWriter = ImmutableNormalizedNodeStreamWriter.from(result);
        JsonParserStream jsonParser = JsonParserStream.create(streamWriter, codecFactory);
        jsonParser.parse(reader);
        NormalizedNode<?, ?> transformedInput = result.getResult();

        //Collect Identities and Leaf nodes from the logparser-so YANG model in different dictionaries
        Collection<? extends org.opendaylight.yangtools.yang.model.api.Module> yang_modules = schemaContext.getModules();
        Iterator modules = yang_modules.iterator();
        ContainerSchemaNode logparser_so_container;
        Collection <IdentitySchemaNode> logparser_so_identities;
        Map<String, String> identity_map = new HashMap<String, String>();
        Collection<LeafSchemaNode> logparser_so_leaf_nodes;
        Map<String, String> label_map = new HashMap<String, String>();
        while(modules.hasNext()){
            org.opendaylight.yangtools.yang.model.api.Module module = (org.opendaylight.yangtools.yang.model.api.Module) modules.next();
            if(module.getName().toString().equals("logparser-so")){
                logparser_so_container = (ContainerSchemaNode) module.getChildNodes().iterator().next();
                logparser_so_identities = (Collection <IdentitySchemaNode>) module.getIdentities();
                Iterator identities = logparser_so_identities.iterator();
                while(identities.hasNext()){
                    IdentitySchemaNode identity = (IdentitySchemaNode)identities.next();
                    identity_map.put(identity.getQName().getLocalName().replace("_", ""), identity.getDescription().get().replace("\n", " "));
                }
                logparser_so_leaf_nodes = (Collection<LeafSchemaNode>) logparser_so_container.getChildNodes();
                Iterator leafs = logparser_so_leaf_nodes.iterator();
                while(leafs.hasNext()){
                    DataSchemaNode child = (DataSchemaNode)leafs.next();
                    if(!child.getQName().getLocalName().equals("metrics") && !child.getQName().getLocalName().equals("Current_Time")){
                        label_map.put(child.getQName().getLocalName(), child.getDescription().get());
                    }
                }
            }
        }

        InstanceIdentifier<LogparserSoMetrics> iid = InstanceIdentifier.create(LogparserSoMetrics.class);  
        YangInstanceIdentifier yiid = codec.toYangInstanceIdentifier(iid);

        LogparserSoMetrics logparser_metrics = (LogparserSoMetrics) codec.fromNormalizedNode(yiid, transformedInput).getValue();
        MetricFamiliesBuilder metricFamiliesBuilder = new MetricFamiliesBuilder();
        
        //Translate LogParser metrics to OpenMetrics metrics
        Metrics log_metrics = logparser_metrics.getMetrics();
        org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.metric.set.MetricsBuilder prom_metricsBuilder = new org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.metric.set.MetricsBuilder();
        ArrayList<org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.metric.set.metrics.Metric> prom_metric_list = new ArrayList<org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.metric.set.metrics.Metric>();
        Map<org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.metric.set.metrics.MetricKey,org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.metric.set.metrics.Metric> prom_metric_map = new HashMap();
        // Instantiate OpenMetrics MetricPointSingleBuilder object to record single Prometheus metric or list of Prometheus metric data
        MetricPointsBuilder metricPointsBuilder = new MetricPointsBuilder();
        //Map<MetricPointKey,MetricPoint> metricPoint_map = new HashMap<MetricPointKey,MetricPoint>();

        for (Map.Entry<MetricKey, Metric> log_metric: log_metrics.getMetric().entrySet()){
            // Translate timestamp
            DateAndTime dateAndTime = logparser_metrics.getCurrentTime();
            String current_time = dateAndTime.getValue().toString();
            Instant instant = Instant.parse(current_time);
            long epoch_time = instant.toEpochMilli();
            
            //MetricPointListBuilder metricPointListBuilder = new MetricPointListBuilder();
            //MetricPointBuilder metricPointBuilder = new MetricPointBuilder();
            
            MetricPointSingleBuilder metricPointBuilder = new MetricPointSingleBuilder();

            metricPointBuilder.setTimestamp(epoch_time);
            
            // Translate time-related metric value
            Long metric_value = log_metric.getValue().getValue();
            metricPointBuilder.setValue(new java.math.BigDecimal(Long.toString(metric_value)));
            MetricPointSingle metricPoint = metricPointBuilder.build();
            
            //MetricPoint metricPoint = metricPointBuilder.build();
            //metricPoint_map.put(metricPoint.key(), metricPoint);
            
            metricPointsBuilder.setMetricPointsChoice(metricPoint);
            MetricPoints metricPoints = metricPointsBuilder.build();
            
            // Translate time-related metric key
            String metric_key = log_metric.getKey().getName().getSimpleName();
           
            // Translate labels
            LabelBuilder labelBuilder = new LabelBuilder();
            ArrayList<Label> label_list = new ArrayList<Label>();
            LabelsBuilder labels_builder = new LabelsBuilder();
            
            // Map (dictionary) with LogParser metric name and labels to calculate the Hash code required for label-set-id YANG node in OpenMetrics YANG model
            Map<String, String> dictionary_logparser_labels = new HashMap<String, String>();
            dictionary_logparser_labels.put("LogParser_Metric_Name", metric_key);
            
            /*for (int i = 0; i < 4; i++){
                switch(i){
                    case 0:
                        labelBuilder.setName("NS_ID");
                        labelBuilder.setValue(logparser_metrics.getNSID());
                        break;
                    case 1:
                        labelBuilder.setName("NSD_ID");
                        labelBuilder.setValue(logparser_metrics.getNSDID());
                        break;
                    case 2:
                        labelBuilder.setName("Operation");
                        labelBuilder.setValue(logparser_metrics.getOperation().getName());
                        break;
                    case 3:
                        labelBuilder.setName("Description");
                        String identity_description = identity_map.get(metric_key);
                        labelBuilder.setValue(identity_description);
                        break;
                }
                Label label = labelBuilder.build();
                System.out.println(label.getName() + " " + label.getValue());
                dictionary_logparser_labels.put(label.getName(), label.getName());
                label_list.add(label);
            }*/

            for (Map.Entry label_entry: label_map.entrySet()){
                labelBuilder.setName(label_entry.getKey().toString());
                switch(label_entry.getKey().toString()){
                    case "NS_ID":
                        labelBuilder.setValue(logparser_metrics.getNSID());
                        break;
                    case "NSD_ID":
                        labelBuilder.setValue(logparser_metrics.getNSDID());
                        break;
                    case "Operation":
                        labelBuilder.setValue(logparser_metrics.getOperation().getName());
                        break;
                }
                Label label = labelBuilder.build();
                dictionary_logparser_labels.put(label.getName(), label.getName());
                label_list.add(label);
            }

            //Add LogParser metric description to labels:
            labelBuilder.setName("Description");
            String identity_description = identity_map.get(metric_key);
            labelBuilder.setValue(identity_description);
            Label label = labelBuilder.build();
            dictionary_logparser_labels.put(label.getName(), label.getName());
            label_list.add(label);

            labels_builder.setLabel(label_list);
            Labels labels = labels_builder.build();

            org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.metric.set.metrics.MetricBuilder prom_metricBuilder = new  org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.metric.set.metrics.MetricBuilder();
            prom_metricBuilder.setName(metric_key+"_total");
            prom_metricBuilder.setMetricPoints(metricPoints);
            prom_metricBuilder.setLabels(labels);
            String label_set_id = Integer.toString(dictionary_logparser_labels.hashCode());
            prom_metricBuilder.setLabelSetId(label_set_id);
            org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.metric.set.metrics.Metric prom_metric = prom_metricBuilder.build();

            prom_metric_map.put(prom_metric.key(), prom_metric);
            //prom_metric_list.add(prom_metric);
        }
    
        // Building the regarding OpenMetrics Metrics object with the corresponding Prometheus metric list
        //prom_metricsBuilder.setMetric(prom_metric_list);
        prom_metricsBuilder.setMetric(prom_metric_map);
        org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.metric.set.Metrics prom_metrics = prom_metricsBuilder.build();
        
        // Instantiating and building the OpenMetrics MetricFamily object with the associated semantic data
        MetricFamilyBuilder metricFamilyBuilder = new MetricFamilyBuilder();
        metricFamilyBuilder.setMetrics(prom_metrics);
        metricFamilyBuilder.setMetricType(MetricType.Counter);
        metricFamilyBuilder.setName(LogparserSoMetrics.QNAME.getLocalName());

        if(metric_family_type != null){
            if(!metric_family_type.isBlank()){
                MetricType metricType = MetricType.forName(metric_family_type).get();
                metricFamilyBuilder.setMetricType(metricType);
            }else{
                metricFamilyBuilder.setMetricType(MetricType.Counter);
                //metricFamilyBuilder.setMetricType(null);
            }
        }else{
            metricFamilyBuilder.setMetricType(MetricType.Counter);
            //metricFamilyBuilder.setMetricType(null);
        }

        if(metric_family_name != null){
            if(!metric_family_name.isBlank()){
                metricFamilyBuilder.setName(metric_family_name);
            }else{
                metricFamilyBuilder.setName(LogparserSoMetrics.QNAME.getLocalName());
                //metricFamilyBuilder.setName(null);
            }
        }else{
            metricFamilyBuilder.setName(LogparserSoMetrics.QNAME.getLocalName());
            //metricFamilyBuilder.setName(null);
        }

        if(metric_family_help != null){
            if(!metric_family_help.isBlank()){
                metricFamilyBuilder.setHelp(metric_family_help);
            }else{
                metricFamilyBuilder.setHelp("Time-related metrics provided by the 5Growth Service Orchestrator (5Gr-SO) Log Management Tool during the network service instantiation, scaling and termination operations.");
                //metricFamilyBuilder.setHelp(null);
            }
        }else{
            metricFamilyBuilder.setHelp(null);
        }
        
        if(metric_family_unit != null){
            if(!metric_family_unit.isBlank()){
                metricFamilyBuilder.setUnit(metric_family_unit);
            }else{
                metricFamilyBuilder.setUnit("s");
                //metricFamilyBuilder.setUnit(null);
            }
        }else{
            metricFamilyBuilder.setUnit(null);
        }
        
        MetricFamily metricFamily = metricFamilyBuilder.build();

        // Instantiating and building the OpenMetrics MetricFamilies object
        ArrayList<MetricFamily> metric_family_list = new ArrayList<MetricFamily>();
        metric_family_list.add(metricFamily);
        metricFamiliesBuilder.setMetricFamily(metric_family_list);
        
        final MetricFamilies metricFamilies = metricFamiliesBuilder.build();
        InstanceIdentifier<MetricFamilies> openmetric_iid = InstanceIdentifier.create(MetricFamilies.class);
        BindingNormalizedNodeSerializer openmetric_codec = new BindingCodecContext(BindingRuntimeHelpers.createRuntimeContext(MetricFamilies.class));
        Entry<YangInstanceIdentifier, NormalizedNode<?, ?>> normalized = openmetric_codec.toNormalizedNode(openmetric_iid, metricFamilies);

        JsonObject driver_output = doConvert(schemaContext, schemaContext.getPath(), normalized.getValue());

        return driver_output.toString();
    }
    
}
