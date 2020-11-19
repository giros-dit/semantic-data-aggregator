from semantic_tools.models.prometheus_entities import Metric, MetricSource, Endpoint, Prometheus
from semantic_tools.models.ngsi_ld.entity import Property, Relationship
from semantic_tools.clients.ngsi_ld import ngsildClient
from semantic_tools.clients.kafka_connect import kafkaConnectClient
import json

def deleteEntities(ngsi: ngsildClient):

    ngsi.deleteEntity("urn:ngsi-ld:Metric:1")

    ngsi.deleteEntity("urn:ngsi-ld:Metric:2")

    ngsi.deleteEntity("urn:ngsi-ld:Prometheus:1")

    ngsi.deleteEntity("urn:ngsi-ld:MetricSource:source1")

    ngsi.deleteEntity("urn:ngsi-ld:MetricSource:source2")

    ngsi.deleteEntity("urn:ngsi-ld:Endpoint:1")

def createEntities(ngsi: ngsildClient):
    # Create Metric entities
    metric1 = Metric(id="urn:ngsi-ld:Metric:1",
                              sample=Property(value="27.0", observedAt="2020-03-24T14:59:19.063Z", units=None),
                              hasSource=Relationship(object="urn:ngsi-ld:MetricSource:source1"))

    ngsi.createEntity(metric1.dict(exclude_none=True))

    metric2 = Metric(id="urn:ngsi-ld:Metric:2",
                              sample=Property(value="100.0", observedAt="2020-03-24T14:59:19.063Z", units="bytes/s"),
                              hasSource=Relationship(object="urn:ngsi-ld:MetricSource:source2"))

    ngsi.createEntity(metric2.dict(exclude_none=True))

    # Query Metric entities
    print("Metrics Entities: ")
    entities = ngsi.queryEntities(type="Metric")
    for entity in entities:
        metric = Metric.parse_obj(entity)
        print(metric.json(indent=4, sort_keys=True, exclude_unset=True))
    print("")

    # Create MetricSource entities
    metricsource1 = MetricSource(id="urn:ngsi-ld:MetricSource:source1",
                              name=Property(value="prometheus_http_requests_total"),
                              expression=Property(value={"job": "prometheus"}),
                              interval=Property(value="10000"),
                              hasEndPoint=Relationship(object="urn:ngsi-ld:Endpoint:1"),
                              javaclass=Property(value="HttpSourceConnector"),
                              topic=Property(value="source1-topic"))

    ngsi.createEntity(metricsource1.dict(exclude_none=True))

    metricsource2 = MetricSource(id="urn:ngsi-ld:MetricSource:source2",
                              name=Property(value="rate(node_network_receive_bytes_total[1m])"),
                              expression=Property(value={"job": "node"}),
                              interval=Property(value="60000", units="ms"),
                              hasEndPoint=Relationship(object="urn:ngsi-ld:Endpoint:1"),
                              javaclass=Property(value="HttpSourceConnector"),
                              topic=Property(value="source2-topic"))

    ngsi.createEntity(metricsource2.dict(exclude_none=True))

    # Query MetricSource entities
    print("MetricSource Entities:")
    entities = ngsi.queryEntities(type="MetricSource")
    for entity in entities:
        metricsource = MetricSource.parse_obj(entity)
        print(metricsource.json(indent=4, sort_keys=True, exclude_unset=True))
    print("")

    # Create Endpoint entity
    endpoint = Endpoint(id="urn:ngsi-ld:Endpoint:1",
                              name=Property(value="prometheus-api"),
                              URI=Property(value="http://prometheus:9090/api/v1/query"))

    ngsi.createEntity(endpoint.dict(exclude_none=True))

    # Get Endpoint entity by id
    print("Endpoint entity:")
    response = ngsi.retrieveEntityById(entityId="urn:ngsi-ld:Endpoint:1")
    endpoint = Endpoint.parse_obj(response)
    print(endpoint.json(indent=4, sort_keys=True, exclude_unset=True))
    print("")

    # Create Prometheus entity
    prometheus = Prometheus(id="urn:ngsi-ld:Prometheus:1",
                              name=Property(value="prometheus-server"),
                              jobs=Property(value={"job1": "prometheus", "job2": "node"}),
                              format=Property(value="json"),
                              isComposedBy=Relationship(object=["urn:ngsi-ld:Metric:1", "urn:ngsi-ld:Metric:2"]),
                              isConnectedTo=Relationship(object="urn:ngsi-ld:Endpoint:1"))

    ngsi.createEntity(prometheus.dict(exclude_none=True))

    # Get Prometheus entity by id
    print("Prometheus entity:")
    response = ngsi.retrieveEntityById(entityId="urn:ngsi-ld:Prometheus:1")
    prometheus = Prometheus.parse_obj(response)
    print(prometheus.json(indent=4, sort_keys=True, exclude_unset=True))
    print("")

def getSourceConnectorConfig(ngsi: ngsildClient, metricsource_id: str):
        configuration = {}
        metricsource_entity=ngsi.retrieveEntityById(metricsource_id)
        metricsource=MetricSource.parse_obj(metricsource_entity)

        configuration['query'] = metricsource.name.value

        configuration['interval'] = metricsource.interval.value

        configuration['class'] = metricsource.javaclass.value

        configuration['topic'] = metricsource.topic.value

        endpoint_id = metricsource.hasEndPoint.object

        endpoint_entity=ngsi.retrieveEntityById(endpoint_id)
        endpoint=Endpoint.parse_obj(endpoint_entity)

        configuration['URI'] = endpoint.URI.value

        return configuration

ngsi = ngsildClient(url="http://scorpio:9090",
                    headers={"Accept": "application/ld+json"},
                    context="https://pastebin.com/raw/NhZbzu8f")

#deleteEntities(ngsi)

createEntities(ngsi)

kafka_connect = kafkaConnectClient(url="http://kafka-connect:8083")

connect=kafka_connect.getAPIConnect()

print("API Connect: ", connect)

connect_plugins=kafka_connect.getConnectorsPlugins()

print("API Connect Plugins: ", connect_plugins)

connectors=kafka_connect.getConnectors()

print("Kafka Connectors: ", connectors)

connector_config1 = getSourceConnectorConfig(ngsi, "urn:ngsi-ld:MetricSource:source1")

config1 = {
    "name": "prometheus-source-1",
    "config": {
        "connector.class": connector_config1['class'],
        "tasks.max": 1,
        "http.request.url": connector_config1['URI']+"?query="+connector_config1['query'],
        "http.request.method": "GET",
        "http.request.headers": "Accept: application/json",
        "http.throttler.interval.millis": connector_config1['interval'],
        "kafka.topic": connector_config1['topic']
    }
}

kafka_connect.createConnector(config1)

connector_config2 = getSourceConnectorConfig(ngsi, "urn:ngsi-ld:MetricSource:source2")

config2 = {
    "name": "prometheus-source-2",
    "config": {
        "connector.class": connector_config2['class'],
        "tasks.max": 1,
        "http.request.url": connector_config2['URI']+"?query="+connector_config2['query'],
        "http.request.method": "GET",
        "http.request.headers": "Accept: application/json",
        "http.throttler.interval.millis": connector_config2['interval'],
        "kafka.topic": connector_config2['topic']
    }
}

kafka_connect.createConnector(config2)

connectors=kafka_connect.getConnectors()

print("Kafka Connectors: ", connectors)

#TO GET KAFKA CONNECTORS INFORMATION FROM ITS API REST

"""
connector=kafka_connect.getConnector("prometheus-source-1")

print("Kafka HttpSourceConnector1: ", connector)

connector_config=kafka_connect.getConnectorConfig("prometheus-source-1")

print("Kafka HttpSourceConnector1 configuration: ", connector_config)

connector_status=kafka_connect.getConnectorStatus("prometheus-source-1")

print("Kafka HttpSourceConnector1 status: ", connector_status)

connector_topic=kafka_connect.getConnectorTopics("prometheus-source-1")

print("Kafka HttpSourceConnector1 topics: ", connector_topic)


connector=kafka_connect.getConnector("prometheus-source-2")

print("Kafka HttpSourceConnector2: ", connector)

connector_config=kafka_connect.getConnectorConfig("prometheus-source-2")

print("Kafka HttpSourceConnector2 configuration: ", connector_config)

connector_status=kafka_connect.getConnectorStatus("prometheus-source-2")

print("Kafka HttpSourceConnector2 status: ", connector_status)

connector_topic=kafka_connect.getConnectorTopics("prometheus-source-2")

print("Kafka HttpSourceConnector2 topics: ", connector_topic)
"""
