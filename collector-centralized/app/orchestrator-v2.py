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
                              sample={"value": "27.0", "observedAt": "2020-03-24T14:59:19.063Z", "units": None})

    ngsi.createEntity(metric1.dict(exclude_none=True))

    metric2 = Metric(id="urn:ngsi-ld:Metric:2",
                              sample={"value": "100.0", "observedAt": "2020-03-24T14:59:19.063Z", "units": "bytes/s"})

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
                              name={"value": "prometheus_http_requests_total"},
                              expression={"value": {"job": "prometheus", "handler": "/api/v1/query", "code": "200"}},
                              interval={"value": "10000", "units": "ms"},
                              isSourceOf={"object": "urn:ngsi-ld:Metric:1"},
                              hasEndPoint={"object": "urn:ngsi-ld:Endpoint:1"},
                              javaclass={"value": "HttpSourceConnector"},
                              topic={"value": "source1-topic"})

    ngsi.createEntity(metricsource1.dict(exclude_none=True))

    metricsource2 = MetricSource(id="urn:ngsi-ld:MetricSource:source2",
                              name={"value": "rate(node_network_receive_bytes_total[1m])"},
                              expression={"value": ""},
                              interval={"value": "60000", "units": "ms"},
                              isSourceOf={"object": "urn:ngsi-ld:Metric:2"},
                              hasEndPoint={"object": "urn:ngsi-ld:Endpoint:1"},
                              javaclass={"value": "HttpSourceConnector"},
                              topic={"value": "source2-topic"})

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
                              name={"value": "prometheus-api"},
                              URI={"value": "http://prometheus:9090/api/v1/query"})

    ngsi.createEntity(endpoint.dict(exclude_none=True))

    # Get Endpoint entity by id
    print("Endpoint entity:")
    response = ngsi.retrieveEntityById(entityId="urn:ngsi-ld:Endpoint:1")
    endpoint = Endpoint.parse_obj(response)
    print(endpoint.json(indent=4, sort_keys=True, exclude_unset=True))
    print("")

    # Create Prometheus entity
    prometheus = Prometheus(id="urn:ngsi-ld:Prometheus:1",
                              name={"value": "prometheus-server"},
                              jobs={"value": {"job1": "prometheus", "job2": "node"}},
                              format={"value": "json"},
                              isComposedBy={"object": ["urn:ngsi-ld:MetricSource:source1", "urn:ngsi-ld:MetricSource:source2"]})

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

        expression = metricsource.expression.value

        labels=""

        if(len(expression) > 0 ):
            expression_keys = []
            expression_values = []

            for key in expression.keys():
              expression_keys.append(key)

            for value in expression.values():
              expression_values.append(value)

            labels=""
            cont=0
            for x in range(0,len(expression_keys)):
                    labels+=expression_keys[x]+"="+'"'+expression_values[x]+'"'
                    cont=cont+1
                    if(cont < len(expression_keys)):
                       labels+=", "


        configuration['expression'] = labels

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

#createEntities(ngsi)

kafka_connect = kafkaConnectClient(url="http://kafka-connect:8083")

connect=kafka_connect.getAPIConnect()

print("API Connect: ", connect)

connect_plugins=kafka_connect.getConnectorsPlugins()

print("API Connect Plugins: ", connect_plugins)

connectors=kafka_connect.getConnectors()

print("Kafka Connectors: ", connectors)

connector_config1 = getSourceConnectorConfig(ngsi, "urn:ngsi-ld:MetricSource:source1")

url1=""

if(len(connector_config1['expression']) > 0):
    url1 = connector_config1['URI']+"?query="+connector_config1['query']+"{"+connector_config1['expression']+"}"
else :
    url1 = connector_config1['URI']+"?query="+connector_config1['query']

config1 = {
    "name": "prometheus-source-1",
    "config": {
        "connector.class": connector_config1['class'],
        "tasks.max": 1,
        "http.request.url": url1,
        "http.request.method": "GET",
        "http.request.headers": "Accept: application/json",
        "http.throttler.interval.millis": connector_config1['interval'],
        "kafka.topic": connector_config1['topic']
    }
}

kafka_connect.createConnector(config1)

connector_config2 = getSourceConnectorConfig(ngsi, "urn:ngsi-ld:MetricSource:source2")

url2=""

if(len(connector_config2['expression']) > 0):
    url2 = connector_config2['URI']+"?query="+connector_config2['query']+"{"+connector_config2['expression']+"}"
else :
    url2 = connector_config2['URI']+"?query="+connector_config2['query']

config2 = {
    "name": "prometheus-source-2",
    "config": {
        "connector.class": connector_config2['class'],
        "tasks.max": 1,
        "http.request.url": url2,
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
