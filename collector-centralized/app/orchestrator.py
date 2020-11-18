#from semantic_tools.models.prometheus import PrometheusMetric
#from semantic_tools.models.ngsi_ld.entity import Property
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

def createEntities(ngsi: ngsildClient, metric_id: str, metricsource_id: str, prometheus_id: str = None, endpoint_id: str = None):

    metric_split = metric_id.split(":")

    print(metric_split[3])

    with open('semantic_tools/models/prometheus-datamodel-ngsild/metric{0}.jsonld'.format(metric_split[3])) as file:
        metric_datamodel = json.load(file)

    ngsi.createEntity(metric_datamodel)
    metric_entity=ngsi.retrieveEntityById(metric_id)
    print(metric_entity)

    if prometheus_id:
        with open('semantic_tools/models/prometheus-datamodel-ngsild/prometheus.jsonld') as file:
            prometheus_datamodel = json.load(file)
        ngsi.createEntity(prometheus_datamodel)
        prometheus_entity=ngsi.retrieveEntityById(prometheus_id)
        print(prometheus_entity)

    if endpoint_id:
        with open('semantic_tools/models/prometheus-datamodel-ngsild/endpoint.jsonld') as file:
            endpoint_datamodel = json.load(file)
            ngsi.createEntity(endpoint_datamodel)
            endpoint_entity=ngsi.retrieveEntityById(endpoint_id)
            print(endpoint_entity)

    with open('semantic_tools/models/prometheus-datamodel-ngsild/metric-source{0}.jsonld'.format(metric_split[3])) as file:
        metricsource_datamodel = json.load(file)
    ngsi.createEntity(metricsource_datamodel)
    metricsource_entity=ngsi.retrieveEntityById(metricsource_id)
    print(metricsource_entity)

def getSourceConnectorConfig(ngsi: ngsildClient, metricsource_id: str):
        configuration = {}
        metricsource_entity=ngsi.retrieveEntityById(metricsource_id)

        interval_property = metricsource_entity['interval']
        configuration['interval'] = interval_property['value']

        class_property = metricsource_entity['class']
        configuration['class'] = class_property['value']

        topic_property = metricsource_entity['topic']
        configuration['topic'] = topic_property['value']

        endpoint_property = metricsource_entity['hasEndPoint']
        endpoint_id = endpoint_property['object']
        endpoint_entity=ngsi.retrieveEntityById(endpoint_id)
        uri_property = endpoint_entity['URI']
        configuration['URI'] = uri_property['value']

        name_property = metricsource_entity['name']
        configuration['query'] = name_property['value']

        return configuration

ngsi = ngsildClient(url="http://localhost:9090", headers={"Accept": "application/ld+json", "Content-Type": "application/ld+json"}, context="https://pastebin.com/raw/NhZbzu8f")

deleteEntities(ngsi)

createEntities(ngsi, "urn:ngsi-ld:Metric:1", "urn:ngsi-ld:MetricSource:source1", "urn:ngsi-ld:Prometheus:1", "urn:ngsi-ld:Endpoint:1")

createEntities(ngsi, "urn:ngsi-ld:Metric:2", "urn:ngsi-ld:MetricSource:source2")


kafka_connect = kafkaConnectClient()

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

