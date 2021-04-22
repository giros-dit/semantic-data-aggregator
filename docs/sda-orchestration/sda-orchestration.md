# Semantic Data Aggregator Orchestration

A mechanism to orchestrate the `Semantic Data Aggregator` (SDA) life cycle management by an external application or system using the NGSI-LD API like an interface that allows translating orders from an external orchestrator component to requests to the `SDA` and extending the NGSI-LD data model for that.

This is an approach to orchestrate the state transitions of those NGSI-LD entities that represent the different stages in the data pipelines and model the actions of the Data Aggregator agents.

To deal with the life cycle management of the data aggregator we define the `Agent` NGSI-LD Entity. This is a parent entity for every life cycle stage of the agent entities in the data pipeline: `MetricSource`, `TelemetrySource`, `MetricTarget`, `MetricProcessor` and `StreamApplication`.

Users or applications can declaratively express the desired state for each agent of the Data Aggregator (`collector`, `aggregator` and `dispatcher`).

The `Agent` parent entity has the following properties:
-	`action`: property value set by users to change the agent state.
-	`state`: property value updated by the `Weaver` during the action triggered by users to indicate the agent state.
- `stateInfo`: optional cross-domain property to describe errors when the processed action fails or feedback information about the agent state.

The information model for the `Agent` parent entity is depicted below.

![agent-model-Agent-entity](img/agent-model-Agent-entity.png)

Also, a short version of the state transition diagram for these Agent-type entities is represented (different possible values that the `state` property of `Agent` entities can take). The transition between states is triggered through the operation determined by the value of the `action` property (transition action).

![agent-model-state-transitions-short-version](img/agent-model-state-transitions-short-version.png)

Furthermore, it is important to note that among the desired states for these agent entities (shown in the previous diagram), there are intermediate transition states to determine the possibility or not of changing the state of the entity. These intermediate transition states are `BUILDING` and `FAILED`. The following is a part of the state transition diagram to give an example of the state changes made for the start and the end of an `Agent` entity. 

![agent-model-state-transitions-building-failed-states](img/agent-model-state-transitions-building-failed-states.png)

When there is no error in the processing activities to trigger a new state change in the entity (for example, from the `RUNNING` state to the `CLEANED` state), the `BUILDING` state serves as an intermediate transition state. If an error occurs to carry out this change of state, it will go from the `BUILDING` state to the `FAILED` state, indicating the cause of the error in the `stateInfo` property, to finally return to the original state.

## Table of Contents

1. [Collection and Dispatch Agents Orchestration](#collection-and-dispatch-agents-orchestration)
2. [Aggregation Agents Orchestration](#aggregation-agents-orchestration)
3. [Enpoint and Data Source context information entities Orchestration](#endpoints-and-data-sources-context-information-entities-orchestration)
4. [Final thoughts on SDA orchestration](#final-thoughts-on-sda-orchestration)


## Collection and Dispatch Agents Orchestration

`Collector` and `Dispatcher` are two of the types of agents that `SDA` orchestrates, managing the life cycle of these agents that works as a NiFi processing flows using metadata from the information models.

In the following subsections, different sequence diagrams are detailed to show the state transition management of those NGSI-LD entities that represent the metadata for the collection agents.

### I - Instantiate and upgrade collection agent

The following sequence diagram shows the steps that the `SDA` framework follows to allow the collection agent instantiation. This diagram corresponds to the `START(0)` transition action of the state transition diagram.

![SDA-orchestration-sequence-diagrams-collection-agent-instantiation](img/SDA-orchestration-sequence-diagrams-collection-agent-instantiation.png)

The steps followed by `SDA` for the collection agent instantiation are as follows:

1.	First of all, the user has to model and create a new NGSI-LD collection agent entity (e.g., `MetriSource` or `TelemetrySource` entity) in `Scorpio Broker` to describe collection agent metadata. In the entity the user have to define the `action` property (inherited from the `Agent` parent entity) initialized to the `START` value.

2.	The entity creation triggers a notification to the `Weaver` component. The `Weaver` then update the entity with the `state` property initialized to the `BUILDING` value while the collection agent initialization is being processed. 

3.	Then, the `Weaver` manages the configuration and instantiation of the collection agent with a `NiFi` processing flow.

4.	When `NiFi` successfully instantiates the processor, the `Weaver` update the `state` property of the collection agent entity to the `RUNNING` value and the `stateInfo` cross-domain property with feedback information about the successfully operation.

5.	Finally, the user is notified that the collection agent has been successfully instantiated.

![SDA-orchestration-sequence-diagrams-collection-agent-endpoint-error](img/SDA-orchestration-sequence-diagrams-collection-agent-endpoint-error.png)

The previous sequence diagram shows an example of `Endpoint` error detection. After creating the collection agent entity and before configuring and instantiating the `NiFI` processing flow for the collection agent, `Weaver` must check whether or not there is an `Endpoint` entity that defines the data source endpoint from which the information is extracted. If this `Endpoint` entity does not exist, the collection agent configuration and the processor execution will fail. In that case, the `Weaver` will update the `state` property of the collection agent entity to the `FAILED` value and the `stateInfo` cross-domain property with feedback information about the failed operation. Finally, the user is notified that the collection agent could not be instantiated.

![SDA-orchestration-sequence-diagrams-collection-agent-upgrade](img/SDA-orchestration-sequence-diagrams-collection-agent-upgrade.png)

The previous sequence diagram shows the steps that the framework follows to allow the collection agent upgrade. This diagram corresponds to the `START(1)` or the `START(3)` transition action of the state transition diagram. The steps are the following:

1.	First of all, user has to update the previously created NGSI-LD collection agent entity (e.g., `MetriSource` or `TelemetrySource` entity) in `Scorpio Broker`. To do this, the `action` property must be updated to the `START` value.

2.	The entity upgrade triggers a notification to the `Weaver` component. The `Weaver` then update the entity with the `state` property initialized to the `BUILDING` value while the collection agent upgrade is being processed. 

3.	Then, the `Weaver` manages the configuration and upgrade of the collection agent with a `NiFi` processing flow.

4.	When `NiFi` successfully upgrades the processor, the `Weaver` update the `state` property of the collection agent entity to the `RUNNING` value and the `stateInfo` cross-domain property with feedback information about the successfully operation.

5.	Finally, the user is notified that the collection agent has been successfully updated.

### II - Deploy and stop collection agent

The following sequence diagram shows the steps that the `SDA` framework follows to allow the collection agent deployment (deploy stopped agent). This diagram corresponds to the `STOP(0)` transition action of the state transition diagram.

![SDA-orchestration-sequence-diagrams-collection-agent-stop-deployment](img/SDA-orchestration-sequence-diagrams-collection-agent-stop-deployment.png)

The steps followed by `SDA` for the collection agent deployment are as follows:

1.	First of all, the user has to model and create a new NGSI-LD collection agent entity (e.g., `MetriSource` or `TelemetrySource` entity) in `Scorpio Broker` to describe collection agent metadata. In the entity the user has to define the `action` property (inherited from the `Agent` parent entity) initialized to the `STOP` value.

2.	The entity creation triggers a notification to the `Weaver` component. The `Weaver` then update the entity with the `state` property initialized to the `BUILDING` value while the collection agent deployment is being processed. 

3.	Then, the `Weaver` manages the configuration and creation of the collection agent with a `NiFi` processing flow. The `NiFi` flow will be deployed but will not be started.

4.	When `NiFi` successfully deploys the processor, the `Weaver` update the `state` property of the agent entity to the `RUNNING` value and the `stateInfo` cross-domain property with feedback information about the successfully operation.

5.	Finally, the user is notified that the collection agent has been successfully deployed and stopped.

![SDA-orchestration-sequence-diagrams-collection-agent-stop](img/SDA-orchestration-sequence-diagrams-collection-agent-stop.png)

The previous sequence diagram shows the steps that the framework follows to allow the collection agent to stop. This diagram corresponds to the `STOP(2)` transition action of the state transition diagram. The steps are the following:

1.	First of all, user has to update the previously created NGSI-LD collection agent entity (e.g., `MetriSource` or `TelemetrySource` entity) in `Scorpio Broker`. To do this, the `action` property must be updated to the `STOP` value.

2.	The entity upgrade triggers a notification to the `Weaver` component. The Weaver then update the entity with the `state` property initialized to the `BUILDING` value while the collection agent stop is being processed. 

3.	Then, the `Weaver` manages the stop of the collection agent with a `NiFi` processing flow.

4.	When `NiFi` successfully stops the processor, the `Weaver` update the `state` property of the agent entity to the `STOPPED` value and the `stateInfo` cross-domain property with feedback information about the successfully operation.

5.	Finally, the user is notified that the collection agent has been successfully stopped.

### III - Delete collection agent

The following sequence diagram shows the steps that the `SDA` framework follows to allow the collection agent deletion. This diagram corresponds to the `END(4)` or `END(5)` transition action of the state transition diagram.

![SDA-orchestration-sequence-diagrams-collection-agent-deletion](img/SDA-orchestration-sequence-diagrams-collection-agent-deletion.png)

The steps followed by `SDA` for the collection agent deletion are as follows:

1.	First of all, user has to update the previously created NGSI-LD collection agent entity (e.g., `MetriSource` or `TelemetrySource` entity) in `Scorpio Broker`. To do this, the `action` property must be updated to the `END` value.

2.	The entity upgrade triggers a notification to the `Weaver` component. The `Weaver` then update the entity with the `state` property initialized to the `BUILDING` value while the collection agent deletion is being processed. 

3.	Then, the `Weaver` manages the deletion of the collection agent with a `NiFi` processing flow.

4.	When `NiFi` successfully deletes the processor, the `Weaver` update the `state` property of the agent entity to the `CLEANED` value and the `stateInfo` cross-domain property with feedback information about the successfully operation.

5.	Finally, the user is notified that the collection agent has been successfully deleted.


## Aggregation Agents Orchestration

`Aggregator` is the other type of agent that `SDA` orchestrates, managing the life cycle of this agents that works as a stream processing engine (`Flink`) using metadata from the information models.

The aggregation agents are in charge of managing both the upload of stream processing applications (defined as JAR files) and the execution of their instances (defined as Job instances) within the `Flink` engine.

For more information on how `SDA` internally manages the uploading and execution of stream processing applications, see [`Stream Processing Applications Management`](../stream-processing/stream-processing-management.md).

In the following subsections, different sequence diagrams are detailed to show the state transition management of those NGSI-LD entities (i.e., `StreamApplication` and `MetricProcessor` entities) that represent the metadata for the aggregation agents.

### I - Upload and delete stream application JAR 

It should be noted that to orchestrate the upload of stream application JARs to the `Flink` engine, and allow `SDA` to manage the life cycle of NGSI-LD `StreamApplication` entities that contain context information necessary for this operation, a new `UPLOADED` value is defined for the `state` property of the `Agent` parent entity.

`SDA` is responsible for orchestrating the creation and deletion of these `StreamApplication` entities and determining the availability of the associated JAR files, as well as uploading them to the stream processing engine (`Flink`).

Then, the state transition diagram for these `StreamApplication` entities is depicted.

![agent-model-state-transitions-stream-applications-domain](img/agent-model-state-transitions-stream-applications-domain.png)

The `UPLOADED` state determines that the JAR file of the stream processing application was available and has been uploaded to the `Flink` engine. In case of failure (for example, the JAR file was not found), the `state` property of the `StreamApplication` will be changed to `FAILED` and the cause of failure will be notified (from the `stateInfo` cross-domain property).

The following sequence diagram shows the steps that the `SDA` framework follows to allow uploading a stream processing application JAR to the aggregation agent (`Flink` engine).

![SDA-orchestration-sequence-diagrams-stream-application-upload](img/SDA-orchestration-sequence-diagrams-stream-application-upload.png)

The steps followed by `SDA` for the stream application JAR upload are as follows:

1.	First of all, the user has to model and create a new NGSI-LD `StreamApplication` entity in `Scorpio Broker` to describe stream processing application metadata. In the entity the user has to define the `action` property (inherited from the `Agent` parent entity) initialized to the `START` value.

2.	The entity creation triggers a notification to the `Weaver` component. The `Weaver` then update the entity with the `state` property initialized to the `BUILDING` value while the upload of the streaming application is being processed.

3.	Then, the `Weaver` manages the upload of the application JAR to the `Flink` engine.

4.	When the application has been successfully uploaded to the `Flink` engine, the `Weaver` update the `state` property of the `StreamApplication` entity to the `UPLOADED` value and the `stateInfo` cross-domain property with feedback information about the successfully operation.

5.	Finally, the user is notified that the stream processing application has been successfully uploaded.

![SDA-orchestration-sequence-diagrams-stream-application-deletion](img/SDA-orchestration-sequence-diagrams-stream-application-deletion.png)

The previous sequence diagram shows the steps that the framework follows to allow deleting a stream processing application JAR from the aggregation agent. The steps are the following:

1.	First of all, the user has to update the previously created NGSI-LD `StreamApplication` entity in `Scorpio Broker`. To do this, the `action` property must be updated to the `END` value.

2.	The entity upgrade triggers a notification to the `Weaver` component. The `Weaver` then update the entity with the `state` property initialized to the `BUILDING` value while the stream application deletion is being processed. 

3.	Then, the `Weaver` manages the deletion of the stream application JAR from the `Flink` engine.

4.	When the application has been successfully deleted from the `Flink` engine, the `Weaver` update the `state` property of the agent entity to the `CLEANED` value and the `stateInfo` cross-domain property with feedback information about the successfully operation.

5.	Finally, the user is notified that the stream processing application has been successfully deleted.

### II - Submit and upgrade Job instance

The previous sequence diagram shows the steps that the `SDA` framework follows to allow the Job instance submission to the aggregation agent. This diagram corresponds to the `START(0)` transition action of the state transition diagram. 

![SDA-orchestration-sequence-diagrams-aggregation-agent-submit](img/SDA-orchestration-sequence-diagrams-aggregation-agent-submit.png)

The steps followed by `SDA` for submitting the Job instance are as follows:

1.	First of all, the user has to model and create a new NGSI-LD `MetricProcessor` entity in `Scorpio Broker` to describe stream processing Job metadata. In the entity we have to define the `action` property (inherited from the `Agent` parent entity) initialized to the `START` value.

2.	The entity creation triggers a notification to the `Weaver` component. The `Weaver` then update the entity with the `state` property initialized to the `BUILDING` value while the submit of the Job instance is being processed. 

3.	Then, the `Weaver` manages the submit of the Job instance to the `Flink` engine.

4.	When the Job has been successfully submitted to the `Flink` engine, the `Weaver` update the `state` property of the agent entity to the `RUNNING` value and the `stateInfo` cross-domain property with feedback information about the successfully operation.

5.	Finally, the user is notified that the Job instance has been successfully submitted and is running.

![SDA-orchestration-sequence-diagrams-aggregation-agent-stream-application-error](img/SDA-orchestration-sequence-diagrams-aggregation-agent-stream-application-error.png)

The previous sequence diagram shows an example of `StreamApplication` error detection. After creating the `MetricProcessor` entity and before configuring and submitting the Job instance to the aggregation agent (`Flink` engine), `Weaver` must check whether or not there is a `StreamApplication` entity that identifies the JAR of the stream application to run on the `Flink` engine. If this `StreamApplication` entity does not exist, the aggregation agent configuration and the Job execution will fail. In that case, the `Weaver` will update the `state` property of the agent entity to the `FAILED` value and the `stateInfo` cross-domain property with feedback information about the failed operation. Finally, the user is notified that the Job instance could not be submitted and executed in the `Flink` engine.

![SDA-orchestration-sequence-diagrams-aggregation-agent-upgrade](img/SDA-orchestration-sequence-diagrams-aggregation-agent-upgrade.png)

The previous sequence diagram shows the steps that the framework follows to allow the Job instance upgrade to the aggregation agent. This diagram corresponds to the `START(1)` or `START(3)` transition action of the state transition diagram. The steps are the following:

1.	First of all, the user has to update the previously created NGSI-LD `MetricProcessor` entity in `Scorpio Broker`. To do this, the `action` property must be updated to the `START` value.

2.	The entity upgrade triggers a notification to the `Weaver` component. The `Weaver` then update the entity with the `state` property initialized to the `BUILDING` value while the Job instance upgrade is being processed.

3.	Then, the `Weaver` manages the upgrade of the Job instance to the `Flink` engine.

4.	When the Job has successfully updated from the `Flink` engine, the `Weaver` update the `state` property of the agent entity to the `RUNNING` value and the `stateInfo` cross-domain property with feedback information about the successfully operation.

5.	Finally, the user is notified that the Job instance has been successfully updated and is running.

### III - Define and stop Job instance

The previous sequence diagram shows the steps that the `SDA` framework follows to allow the Job definition for the aggregation agent. This diagram corresponds to the `STOP(0)` transition action of the state transition diagram.

![SDA-orchestration-sequence-diagrams-aggregation-agent-stop-definition](img/SDA-orchestration-sequence-diagrams-aggregation-agent-stop-definition.png)

The steps followed by `SDA` for the Job instance definition are as follows:

1.	First of all, the user has to model and create a new NGSI-LD `MetricProcessor` entity in `Scorpio Broker` to describe stream processing Job metadata. In the entity we have to define the `action` property (inherited from the `Agent` parent entity) initialized to the `STOP` value.

2.	The entity creation triggers a notification to the `Weaver` component. The `Weaver` then update the entity with the `state` property initialized to the `BUILDING` value while the definition of the Job instance is being processed. 

3.	Then, the `Weaver` manages the Job instance definition. That is, th Job instance will be defined but will not be submitted to the `Flink`engine.

4.	When the Job has successfully defined, the `Weaver` update the `state` property of the agent entity to the `STOPPED` value and the `stateInfo` cross-domain property with feedback information about the successfully operation.

5.	Finally, the user is notified that the Job instance has been successfully defined and is stopped.

![SDA-orchestration-sequence-diagrams-aggregation-agent-stop](img/SDA-orchestration-sequence-diagrams-aggregation-agent-stop.png)

The previous sequence diagram shows the steps that the framework follows to allow stopping the Job instance from the aggregation agent. This diagram corresponds to the `STOP(2)` transition action of the state transition diagram. The steps are the following:

1.	First of all, the user has to update the previously created NGSI-LD `MetricProcessor` entity in `Scorpio Broker`. To do this, the `action` property must be updated to the `STOP` value.

2.	The entity upgrade triggers a notification to the `Weaver` component. The `Weaver` then update the entity with the `state` property initialized to the `BUILDING` value while the Job instance stop is being processed.

3.	Then, the `Weaver` manages the stop of the Job instance from the `Flink` engine.

4.	When the Job has successfully stopped from the `Flink` engine, the `Weaver` update the `state` property of the agent entity to the `STOPPED` value and the `stateInfo` cross-domain property with feedback information about the successfully operation.

5.	Finally, the user is notified that the Job instance has been successfully cancelled and stopped.

### IV - Delete Job instance

The previous sequence diagram shows the steps that the `SDA` framework follows to allow deleting the Job instance from the aggregation agent. This diagram corresponds to the `END(4)` or `END(5)` transition action of the state transition diagram.

![SDA-orchestration-sequence-diagrams-aggregation-agent-deletion](img/SDA-orchestration-sequence-diagrams-aggregation-agent-deletion.png)

The steps followed by `SDA` for the Job instance deletion are as follows:

1.	First of all, user has to update the previously created NGSI-LD `MetricProcessor` entity in `Scorpio Broker`. To do this, the `action` property must be updated to the `END` value.

2.	The entity upgrade triggers a notification to the `Weaver` component. The `Weaver` then update the entity with the `state` property initialized to the `BUILDING` value while the Job instance deletion is being processed.

3.	Then, the `Weaver` manages the deletion of the Job instance from the `Flink` engine.

4.	When the Job has successfully deleted from the `Flink` engine, the `Weaver` update the `state` property of the agent entity to the `CLEANED` value and the `stateInfo` cross-domain property with feedback information about the successfully operation.

5.	Finally, the user is notified that the Job instance has been successfully cancelled and deleted.


## Enpoint and Data Source context information entities Orchestration

In addition to orchestrating through state changes the agents that allow managing the collection and delivery of data (from NiFi processing flows), as well as data aggregation (from Flink stream processing applications), `SDA` must manage the life cycle of those NGSI-LD entities that define the context information for the input data sources and their endpoint services. 

So far there are two types of data source entities defined:
1.	`Prometheus`: Prometheus-based data source context information entities for metric collection.
2.  `Device`: Telemetry-based data source context information entities for telemetry data collection.

In addition, `Endpoint` type entities allow you to define the connection point with the data sources.

`SDA` is responsible for orchestrating the creation and deletion of these data source and endpoint entities and determining their availability.

Then, a short version of the state transition diagram for these data sources and endpoint entities is depicted.

![agent-model-state-transitions-endpoints-datasources-domain](img/agent-model-state-transitions-endpoints-datasources-domain.png)

Two states are defined to determine whether the entities are enabled or not (`ENABLED` and `DISABLED`). The `START` action allows entities to be created and enabled, while the `END` action allows them to be deleted and disabled. 

When data source entities are created (e.g., `Prometheus` or `Device` entities), it is determined whether they are reachable from their associated endpoint service (related `Endpoint` entity). In case the data source is available and accessible from its endpoint, it will be enabled (the `state` property will change to `ENABLED`). Otherwise, the `state` property of this entity will be changed to `FAILED` and the cause of failure will be notified (from the `stateInfo` cross-domain property)


## Final thoughts on SDA orchestration

Finally, the full version of the state transition diagram is presented depending on the associated entity domain. In addition, the transition actions carried out by `Weaver` to manage the change of states are detailed.

![agent-model-state-transitions-full-version](img/agent-model-state-transitions-full-version.png)

[`NGSI-LD API Orchestrator`](../../postman_collections/NGSI-LD%20API%20Orchestrator.postman_collection.json) Postman collection has a set of requests that can be used to model a full NGSI-LD datapipeline and orchestrate the life cycle of the entities involved based on the NGSI-LD API.
