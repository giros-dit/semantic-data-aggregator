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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.nifi.util.MockFlowFile;
import org.apache.nifi.util.TestRunner;
import org.apache.nifi.util.TestRunners;
import org.junit.Before;
import org.junit.Test;

public class JsonToJinjaTest {

    private TestRunner testRunner;

    @Before
    public void init() {
        testRunner = TestRunners.newTestRunner(JsonToJinja.class);
    }

    @Test
    public void testProcessor() {
        testRunner.setProperty(JsonToJinja.TEMPLATE_PATH_PROPERTY,
                Paths.get("src/test/resources/openmetrics-template.j2").toAbsolutePath().toString());

        ClassLoader classLoader = getClass().getClassLoader();
        // Content to be pre-loaded a json file
        InputStream content = classLoader.getResourceAsStream("driver-input.json");
        // Add the content to the runner
        testRunner.enqueue(content);
        // Run the enqueued content, it also takes an int = number of contents queued
        testRunner.run(1);
        // All results were processed with out failure
        testRunner.assertQueueEmpty();
        // Access output FlowFile
        List<MockFlowFile> results = testRunner.getFlowFilesForRelationship(JsonToJinja.SUCCESS_RELATIONSHIP);
        assertTrue("1 match", results.size() == 1);
        MockFlowFile result = results.get(0);
        String resultValue = new String(testRunner.getContentAsByteArray(result));
        try {
            // Content to be pre-loaded a json file
            String expected_result = IOUtils.toString(classLoader.getResourceAsStream("driver-output.txt"),
                    StandardCharsets.UTF_8);
            // Test attributes and content
            assertEquals(resultValue, expected_result);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

}
