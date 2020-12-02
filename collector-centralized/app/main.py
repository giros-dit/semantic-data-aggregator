from semantic_tools.clients.ngsi_ld import NGSILDClient
from semantic_tools.clients.kafka_connect import KafkaConnectClient
from semantic_tools.models.metric import MetricSource, Endpoint

import logging

logging.basicConfig(
        level=logging.INFO,
        format="%(asctime)s - %(name)s - %(levelname)s - %(message)s")
logger = logging.getLogger(__name__)


def buildHTTPSourceConnector(metricSource: MetricSource,
                             ngsi: NGSILDClient) -> dict:
    """
    Generate HTTP Source Connector configuration
    from a passed MetricSource entity
    """
    endpoint_entity = ngsi.retrieveEntityById(metricSource.hasEndpoint.object)
    endpoint = Endpoint.parse_obj(endpoint_entity)

    url = ""
    if metricSource.expression:
        labels = getQueryLabels(metricSource)
        url = (endpoint.uri.value + "?query=" +
               metricSource.name.value + "{" + labels + "}")
    else:
        url = endpoint.uri.value + "?query=" + metricSource.name.value

    entity_id = metricSource.id.strip("urn:ngsi-ld:").replace(":", "-").lower()
    config = {
        "name": "{0}-{1}".format(endpoint.name.value, entity_id),
        "config": {
            "connector.class": "HttpSourceConnector",
            "tasks.max": 1,
            "http.request.url": url,
            "http.request.method": "GET",
            "http.request.headers": "Accept: application/json",
            "http.throttler.interval.millis": metricSource.interval.value,
            "kafka.topic": entity_id
        }
    }
    return config


def getQueryLabels(metricSource: MetricSource) -> str:
    """
    Print Prometheus labels to make them consumable
    by Prometheus REST API
    """
    expression = metricSource.expression.value
    labels = []
    for label, value in expression.items():
        labels.append("{0}='{1}'".format(label, value))

    return ",".join(labels)


if __name__ == '__main__':

    # Init NGSI-LD API Client
    ngsi = NGSILDClient(url="http://scorpio:9090",
                        headers={"Accept": "application/json"},
                        context="http://context-catalog:8080/context.jsonld")

    # Init Kafka Connect API Client
    kafka_connect = KafkaConnectClient(url="http://kafka-connect:8083")
    connect = kafka_connect.getAPIConnect()
    logger.info("API Connect: {0}".format(connect))
    connect_plugins = kafka_connect.getConnectorsPlugins()
    logger.info("API Connect Plugins: {0}".format(connect_plugins))

    connectors = kafka_connect.getConnectors()

    # Find MetricSources entities and donfig Kafka HTTP Source connectors
    metricSources = [
        MetricSource.parse_obj(x) for x in ngsi.queryEntities(
            type="MetricSource")
    ]
    for metricSource in metricSources:
        config = buildHTTPSourceConnector(metricSource, ngsi)
        kafka_connect.createConnector(config)
        logger.info("MetricSource '{0}' configuration:\n{1}".format(
                                                        metricSource.id,
                                                        config))

    while True:
        pass
