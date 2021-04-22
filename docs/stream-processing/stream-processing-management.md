# Stream Processing Applications Management

In `Apache Flink` engine, the stream processing applications are packaged as JAR files. These JAR files can be uploaded to the stream processing engine via REST API or GUI.

![`data-aggregator-stream-app-sequence`](../data-aggregator-stream-app-sequence.png)

The previous sequence diagram shows the steps that the `Semantic Data Aggregator` (SDA) framework follows to allow the upload of the stream applications to the `Flink` engine. The steps are the following:

1. First of all, the obvious but most important thing is that a properly compiled and packaged `Flink` stream processing application is available as a JAR file. To do this, the application developer or the own user must upload the JAR application to a secure external repository. A docker service called `stream-catalog` is available in the framework that works as a repository accessible via HTTP where the stream processing applications can be uploaded and downloaded at the time the user wishes. In this way, the user can upload the JAR applications in the `stream-catalog` repository to be uploaded to the `Flink` engine later.

2. Secondly, the user has to model and create a new `StreamApplication` NGSI-LD entity in `Scorpio Broker` to describe the JAR application metadata. The `StreamApplication` entity creation looks like this:

```
curl --location --request POST 'http://localhost:9090/ngsi-ld/v1/entities/' \
--header 'Content-Type: application/json' \
--header 'Link: <https://fiware.github.io/data-models/full-context.jsonld>; rel="http://www.w3.org/ns/json-ld#context"; type="application/ld+json"' \
--data-raw '{
    "id": "urn:ngsi-ld:StreamApplication:1",
    "type": "StreamApplication",
    "action": {
        "type": "Property",
        "value": "START"
    },
    "fileName": {
        "type": "Property",
        "value": "flink.TrafficRate-0.0.1-SNAPSHOT.jar"
    },
    "fileId": {
        "type": "Property",
        "value": ""
    },
    "entryClass": {
        "type": "Property",
        "value": ""
    },
    "description": {
        "type": "Property",
        "value": "A Flink stream processing application that calculates the traffic packet rate sent through a specific interface of a device between two instants of time."
    },
    "uri": {
        "type": "Property",
        "value": "http://stream-catalog:8080/flink.TrafficRate-0.0.1-SNAPSHOT.jar"
    }
}'
```

The `StreamApplication` entity has the following properties:
- `fileName`: the JAR application name.
- `fileId`: the ID generated after uploading the JAR to the stream processing engine. 
- `entryClass`: the name of the main Java class.
- `description`: optional description of the stream processing application.
- `URI`: the address of the external repository with JARs.

3. The entity creation triggers a notification to the `Weaver` component. The `Weaver` then manages the JAR application metadata to fetch the JAR from the external repository and uploads its to the stream processing engine.

4. When `Weaver` uploads the JAR application to the `Flink` engine, the latter returns an ID that identifies the JAR that has just been uploaded. The value of this ID is updated in the `fileId` property of the `StreamApplication` entity. This ID is required to run the stream processing application as a Job instance in `Flink`.

5. Finally, the user is notified that the JAR application has been successfully uploaded to the `Flink` engine.


To run the stream processing applications, a related Job instance must be submitted to the `Flink` engine. A `Flink` application can be uploaded to run one or multiple `Flink` Jobs. 

![`data-aggregator-stream-job-sequence`](../data-aggregator-stream-job-sequence.png)

The previous sequence diagram shows the steps that the framework follows to allow the submit of a Job instance to the `Flink` engine. The steps are the following:

1. First of all, the user has to model and create a new `MetricProcessor` NGSI-LD entity in `Scorpio Broker` to describe the Job instance metadata. The `MetricProcessor` entity creation looks like this:

```
curl --location --request POST 'http://localhost:9090/ngsi-ld/v1/entities/' \
--header 'Content-Type: application/json' \
--header 'Link: <https://fiware.github.io/data-models/full-context.jsonld>; rel="http://www.w3.org/ns/json-ld#context"; type="application/ld+json"' \
--data-raw '{
    "id": "urn:ngsi-ld:MetricProcessor:1",
    "type": "MetricProcessor",
    "action": {
        "type": "Property",
        "value": "START"
    },
    "hasInput": {
        "type": "Relationship",
        "object": "urn:ngsi-ld:MetricSource:2"
    },
    "hasApplication": {
        "type": "Relationship",
        "object": "urn:ngsi-ld:StreamApplication:1"
    },
    "name": {
        "type": "Property",
        "value": "TrafficRate"
    },
    "jobId": {
        "type": "Property",
        "value": ""
    },
    "arguments": {
        "type": "Property",
        "value": {
            "window-class": "time",
            "window-type": "slide",
            "interval": "10",
            "timeout": "600"
        }
    }
}'
```

The `StreamApplication` entity has the following attributes:
- `name` property: the Job name.
- `hasInput` relationship:
  - `object`: `MetricSource` entity Id.
  - Data to be consumed (data delivery by `MetricSource:2` entity).
- `hasApplication` relationship:
  - `object`: `StreamApplication` entity Id.
  - JAR application to be run (JAR ID of `StreamApplication:1` entity)
- `jobId` property: the ID generated after submitting the Job instance to the stream processing engine.
- `arguments` property: custom input arguments for the stream processing application execution (e.g., the type and class of window used or the interval and execution time). Argument values are expressed as a list of key-value pairs.

2. The entity creation triggers a notification to the `Weaver` component. The `Weaver` then manages the JAR application metadata to retrieve the JAR ID from the `StreamApplication` entity previously created in the `Scorpio broker` and submit a Job instance execution for this JAR ID to the `Flink` engine.

3. When `Weaver` submit the Job instance to `Flink` engine, the latter returns an ID that identifies the Job that has just been submitted. The value of this ID is updated in the `jobId` property of the `MetricProcessor` entity. This ID is required to control the Job activity (e.g., to cancel the Job instance execution).

4. Finally, the user is notified that the Job instance has been successfully submitted and the stream application is running on the `Flink` engine.
