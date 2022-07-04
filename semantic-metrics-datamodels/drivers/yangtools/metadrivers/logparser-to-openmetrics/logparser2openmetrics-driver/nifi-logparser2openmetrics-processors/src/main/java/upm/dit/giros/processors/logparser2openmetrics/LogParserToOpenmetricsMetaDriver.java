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

import org.apache.nifi.components.PropertyDescriptor;
import org.apache.nifi.components.Validator;
import org.apache.nifi.expression.ExpressionLanguageScope;
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
import org.apache.nifi.processor.exception.ProcessException;
import org.apache.nifi.processor.util.StandardValidators;
import org.apache.nifi.processor.io.InputStreamCallback;
import org.apache.nifi.processor.io.OutputStreamCallback;


import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;


import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

/**
 * LogParser to Openmetrics YANGTools Meta Driver.
 */
@Tags({"LogParser to Openmetrics YANGTools Meta Driver"})
@CapabilityDescription("NiFi processor to implement the LogParser to Openmetrics YANGTools Meta Driver.")
@SeeAlso({})
@ReadsAttributes({@ReadsAttribute(attribute="", description="")})
@WritesAttributes({@WritesAttribute(attribute="", description="")})
public class LogParserToOpenmetricsMetaDriver extends AbstractProcessor {

    public static final PropertyDescriptor DRIVER_TYPE = new PropertyDescriptor
            .Builder().name("YANG-TOOLS Driver type")
            .displayName("YANG-TOOLS Driver type")
            .description("YANG-TOOLS Driver type")
            .required(true)
            .addValidator(StandardValidators.NON_EMPTY_VALIDATOR)
            .build();

    public static final PropertyDescriptor METRIC_FAMILY_NAME = new PropertyDescriptor
            .Builder().name("Prometheus Metric Family name")
            .displayName("Prometheus Metric Family name")
            .description("Prometheus Metric Family name")
            .required(false)
            .addValidator(Validator.VALID)
            .expressionLanguageSupported(ExpressionLanguageScope.VARIABLE_REGISTRY)
            .build();
    
    public static final PropertyDescriptor METRIC_FAMILY_TYPE = new PropertyDescriptor
            .Builder().name("Prometheus Metric Family type")
            .displayName("Prometheus Metric Family type")
            .description("Prometheus Metric Family type")
            .required(false)
            .addValidator(Validator.VALID)
            .expressionLanguageSupported(ExpressionLanguageScope.VARIABLE_REGISTRY)
            .build();

    public static final PropertyDescriptor METRIC_FAMILY_UNIT = new PropertyDescriptor
            .Builder().name("Prometheus Metric Family unit")
            .displayName("Prometheus Metric Family unit")
            .description("Prometheus Metric Family unit")
            .required(false)
            .addValidator(Validator.VALID)
            .expressionLanguageSupported(ExpressionLanguageScope.VARIABLE_REGISTRY)
            .build();

    public static final PropertyDescriptor METRIC_FAMILY_HELP = new PropertyDescriptor
            .Builder().name("Prometheus Metric Family help")
            .displayName("Prometheus Metric Family help")
            .description("Prometheus Metric Family help")
            .required(false)
            .addValidator(Validator.VALID)
            .expressionLanguageSupported(ExpressionLanguageScope.VARIABLE_REGISTRY)
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


    private String driver_type;

    private String metric_family_name;
    
    private String metric_family_type;
    
    private String metric_family_unit;

    private String metric_family_help;

    @Override
    protected void init(final ProcessorInitializationContext context) {
        descriptors = new ArrayList<>();
        descriptors.add(DRIVER_TYPE);
        descriptors.add(METRIC_FAMILY_NAME);
        descriptors.add(METRIC_FAMILY_TYPE);
        descriptors.add(METRIC_FAMILY_UNIT);
        descriptors.add(METRIC_FAMILY_HELP);
        descriptors = Collections.unmodifiableList(descriptors);

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
        driver_type = context.getProperty(DRIVER_TYPE).getValue();
        metric_family_name = context.getProperty(METRIC_FAMILY_NAME).evaluateAttributeExpressions().getValue();
        metric_family_type = context.getProperty(METRIC_FAMILY_TYPE).evaluateAttributeExpressions().getValue();
        metric_family_unit = context.getProperty(METRIC_FAMILY_UNIT).evaluateAttributeExpressions().getValue();
        metric_family_help = context.getProperty(METRIC_FAMILY_HELP).evaluateAttributeExpressions().getValue();
    }

    @Override
    public void onTrigger(final ProcessContext context, final ProcessSession session) {
        
        final AtomicReference<String> value = new AtomicReference<>();

        FlowFile flowfile = session.get();

        if ( flowfile == null ) {
            return;
        }
        
        // To read the input from the flowfile 
        try{
            session.read(flowfile, new InputStreamCallback() {
                @Override
                public void process(InputStream in) throws IOException {
                    try{
                        if(driver_type.equals("LogParserSource")){
                            JsonParser jsonParser = new JsonParser();
                            JsonObject jsonObject = (JsonObject) jsonParser.parse(new InputStreamReader(in, "UTF-8"));
                            LogParserSourceDriver source_driver = new LogParserSourceDriver();
                            String json_ietf = source_driver.driver(jsonObject);
                            value.set(json_ietf);                        
                        } else {
                            if(driver_type.equals("LogParserToOpenmetricsTransformer")){
                                JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
                                LogParserToOpenmetricsTransformerDriver transformer_driver = new LogParserToOpenmetricsTransformerDriver(metric_family_name, metric_family_type, metric_family_unit, metric_family_help);
                                String json_ietf = transformer_driver.driver(reader);
                                value.set(json_ietf);
                            }
                        }

                    }catch(Exception ex){
                        ex.printStackTrace();
                        StringWriter errors = new StringWriter();
                        ex.printStackTrace(new PrintWriter(errors));
                        //getLogger().error(ex.getMessage());
                        getLogger().error(errors.toString());
                        return;
                    }
                }
            });
        } catch(ProcessException pe) {
            pe.printStackTrace();
            StringWriter errors = new StringWriter();
            pe.printStackTrace(new PrintWriter(errors));
            //getLogger().error(ex.getMessage());
            getLogger().error(errors.toString());
            session.transfer(flowfile, FAILURE);
            return;
        }

        try{
            // To write the results back out of the flowfile 
            flowfile = session.write(flowfile, new OutputStreamCallback() {

                @Override
                public void process(OutputStream out) throws IOException {
                    out.write(value.get().getBytes());
                }
            });
            
            session.transfer(flowfile, SUCCESS); 
        }catch(ProcessException pe){
            pe.printStackTrace();
            StringWriter errors = new StringWriter();
            pe.printStackTrace(new PrintWriter(errors));
            //getLogger().error(ex.getMessage());
            getLogger().error(errors.toString());
            session.transfer(flowfile, FAILURE);
            return;
        }
    }
}
