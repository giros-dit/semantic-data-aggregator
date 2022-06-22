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
package upm.dit.giros.processors.jsontojinja;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;
import com.google.common.collect.Maps;
import com.hubspot.jinjava.Jinjava;
import com.hubspot.jinjava.JinjavaConfig;
import com.hubspot.jinjava.interpret.FatalTemplateErrorsException;
import com.hubspot.jinjava.interpret.JinjavaInterpreter;
import com.hubspot.jinjava.loader.ResourceLocator;

import org.apache.commons.io.FileUtils;
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
import org.apache.nifi.processor.exception.ProcessException;
import org.apache.nifi.processor.io.InputStreamCallback;
import org.apache.nifi.processor.io.OutputStreamCallback;
import org.apache.nifi.processor.util.StandardValidators;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Tags({ "template", "jinja", "json" })
@CapabilityDescription("Renders a new flowfile from a Jinja2 template and the input JSON flowfile")
@SeeAlso({})
@ReadsAttributes({ @ReadsAttribute(attribute = "", description = "") })
@WritesAttributes({ @WritesAttribute(attribute = "", description = "") })
public class JsonToJinja extends AbstractProcessor {

    public static final PropertyDescriptor TEMPLATE_PATH_PROPERTY = new PropertyDescriptor.Builder()
            .name("Template Path").description("Jinja2 Template Path").required(true)
            .addValidator(StandardValidators.FILE_EXISTS_VALIDATOR).build();

    public static final PropertyDescriptor RESOURCES_PATH_PROPERTY = new PropertyDescriptor.Builder()
            .name("Resources Path").description("Path to included resources for Jinja2").required(false)
            .addValidator(StandardValidators.FILE_EXISTS_VALIDATOR).build();

    public static final Relationship SUCCESS_RELATIONSHIP = new Relationship.Builder().name("success")
            .description("Success relationship").build();

    public static final Relationship FAILURE_RELATIONSHIP = new Relationship.Builder().name("failure")
            .description("Failure relationship").build();

    public static final Relationship JSON_PARSING_FAILURE_RELATIONSHIP = new Relationship.Builder().name("json_failure")
            .description("On JSON parsing failure").build();

    private List<PropertyDescriptor> descriptors;

    private Set<Relationship> relationships;

    private static final Logger LOG = LoggerFactory.getLogger(JsonToJinja.class);
    volatile Jinjava jinjava;
    volatile JsonFactory jsonFactory;
    volatile ObjectMapper jsonMapper;

    Map<String, Object> validateAndParseJSONContent(ProcessSession processSession, FlowFile flowFile)
            throws JsonParseException {
        final Map<String, Object> jsonContent = Maps.newHashMap();

        try {
            processSession.read(flowFile, new InputStreamCallback() {
                @Override
                public void process(InputStream in) throws IOException {
                    JsonParser jp = jsonFactory.createParser(in);
                    jsonContent.putAll(jp.readValueAs(Map.class));
                }
            });
        } catch (ProcessException e) {
            if (e.getCause() instanceof JsonParseException) {
                throw (JsonParseException) e.getCause();
            } else {
                throw e;
            }
        }

        return jsonContent;
    }

    Map<String, Object> templateContextFromFlowFile(final ProcessContext context, final ProcessSession session,
            final FlowFile flowFile) throws JsonParseException {

        Map<String, Object> templateContext = Maps.newHashMap();
        templateContext.put("attributes", flowFile.getAttributes());

        Map<String, Object> jsonContent = validateAndParseJSONContent(session, flowFile);
        templateContext.put("content", jsonContent);

        return templateContext;
    }

    String getTemplate(final ProcessContext context) throws IOException {
        String templateFilePath = context.getProperty(TEMPLATE_PATH_PROPERTY).getValue();
        return FileUtils.readFileToString(new File(templateFilePath), Charsets.UTF_8);
    }

    public final String pathToResource(final ProcessContext context, String fullName) {
        String templateResourcesPath = context.getProperty(RESOURCES_PATH_PROPERTY).getValue();

        if (templateResourcesPath != null) {
            return Paths.get(templateResourcesPath, fullName).toAbsolutePath().toString();
        }

        String templateFilePath = context.getProperty(TEMPLATE_PATH_PROPERTY).getValue();

        if (templateFilePath == null) {
            return null;
        }

        String templateFileContainingPath = Paths.get(templateFilePath).getParent().toAbsolutePath().toString();

        return Paths.get(templateFileContainingPath, fullName).toAbsolutePath().toString();
    }

    @Override
    protected void init(final ProcessorInitializationContext context) {
        descriptors = new ArrayList<>();
        descriptors.add(RESOURCES_PATH_PROPERTY);
        descriptors.add(TEMPLATE_PATH_PROPERTY);
        descriptors = Collections.unmodifiableList(descriptors);

        relationships = new HashSet<>();
        relationships.add(SUCCESS_RELATIONSHIP);
        relationships.add(FAILURE_RELATIONSHIP);
        relationships.add(JSON_PARSING_FAILURE_RELATIONSHIP);
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

        jsonMapper = new ObjectMapper();
        jsonFactory = jsonMapper.getFactory();

        JinjavaConfig config = new JinjavaConfig();
        jinjava = new Jinjava(config);

        jinjava.setResourceLocator(new ResourceLocator() {
            @Override
            public String getString(String fullName, Charset encoding, JinjavaInterpreter interpreter)
                    throws IOException {
                String pathToResource = pathToResource(context, fullName);

                try {
                    return FileUtils.readFileToString(new File(pathToResource), encoding);
                } catch (IOException e) {
                    return null;
                }
            }
        });
    }

    @Override
    public void onTrigger(final ProcessContext context, final ProcessSession session) {
        final AtomicReference<String> value = new AtomicReference<>();
        FlowFile flowfile = session.get();
        if (flowfile == null) {
            return;
        }

        String template;
        try {
            template = getTemplate(context);
        } catch (IOException e) {
            LOG.error("Could not read the template file.");
            session.transfer(flowfile, FAILURE_RELATIONSHIP);
            return;
        }

        Map<String, Object> templateContext;
        try {
            templateContext = templateContextFromFlowFile(context, session, flowfile);
        } catch (JsonParseException e) {
            LOG.error("FlowFile {} did not have valid JSON content.", new Object[] { flowfile });
            session.transfer(flowfile, JSON_PARSING_FAILURE_RELATIONSHIP);
            return;
        }

        // Set value with rendered template
        try {
            value.set(jinjava.render(template, templateContext));
        } catch (FatalTemplateErrorsException e) {
            LOG.error("Template rendering problem: {}", new Object[] { e.toString() });
            session.transfer(flowfile, FAILURE_RELATIONSHIP);
            return;
        }

        // To write the results back out of the flowfile
        flowfile = session.write(flowfile, new OutputStreamCallback() {
            @Override
            public void process(OutputStream out) throws IOException {
                out.write(value.get().getBytes());
            }
        });
        session.transfer(flowfile, SUCCESS_RELATIONSHIP);
    }
}
