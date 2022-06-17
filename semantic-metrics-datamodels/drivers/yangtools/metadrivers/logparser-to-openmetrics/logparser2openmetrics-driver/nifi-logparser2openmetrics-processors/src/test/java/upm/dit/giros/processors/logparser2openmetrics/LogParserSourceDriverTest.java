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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;

import com.google.gson.JsonParser;

import org.apache.commons.io.IOUtils;
import org.apache.nifi.util.MockFlowFile;
import org.apache.nifi.util.TestRunner;
import org.apache.nifi.util.TestRunners;
import org.junit.Before;
import org.junit.Test;


public class LogParserSourceDriverTest {

    private TestRunner testRunner;
    private JsonParser parser;

    @Before
    public void init() {
        testRunner = TestRunners.newTestRunner(LogParserToOpenmetricsMetaDriver.class);
        parser = new JsonParser();
    }

    @Test
    public void testProcessor() {
        testRunner.setProperty(LogParserToOpenmetricsMetaDriver.DRIVER_TYPE, "LogParserSource");
        ClassLoader classLoader = getClass().getClassLoader();
        // Content to be pre-loaded a json file
        InputStream content = classLoader.getResourceAsStream("source-instantiation-input.json");
        // Add the content to the runner
        testRunner.enqueue(content);
        // Run the enqueued content, it also takes an int = number of contents queued
        testRunner.run(1);
        // All results were processed with out failure
        testRunner.assertQueueEmpty();
        // Access output FlowFile
        List<MockFlowFile> results = testRunner.getFlowFilesForRelationship(LogParserToOpenmetricsMetaDriver.SUCCESS);
        assertTrue("1 match", results.size() == 1);
        MockFlowFile result = results.get(0);
        String generate_result = new String(testRunner.getContentAsByteArray(result));
        try {
            // Content to be pre-loaded a json file
            String expected_result = IOUtils.toString(classLoader.getResourceAsStream("source-instantiation-output.json"), StandardCharsets.UTF_8);
            // Test attributes and content
            System.out.println("Generated result value: " + generate_result);
            System.out.println("Expected result value: " + expected_result);
            //assertEquals(parser.parse(expected_result.toString()), parser.parse(generate_result.toString()));
        } catch (Exception ex) {
            // TODO: handle exception
            ex.printStackTrace();
        }

    }

}
