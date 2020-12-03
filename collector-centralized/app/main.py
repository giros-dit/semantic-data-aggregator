from fastapi import FastAPI, status, Request
from fastapi.responses import JSONResponse
from semantic_tools.clients.ngsi_ld import NGSILDClient
from semantic_tools.clients.kafka_connect import KafkaConnectClient
from semantic_tools.models.metric import Endpoint, MetricSource, MetricTarget

import logging
import sys

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


def initCollector():
    # Init NGSI-LD API Client
    ngsi = NGSILDClient(url="http://scorpio:9090",
                        headers={"Accept": "application/json"},
                        context="http://context-catalog:8080/context.jsonld")
    ngsi.checkScorpioHealth()

    # Init Kafka Connect API Client
    kafka_connect = KafkaConnectClient(url="http://kafka-connect:8083")
    connect = kafka_connect.getAPIConnect()
    logger.info("API Connect: {0}".format(connect))
    connect_plugins = kafka_connect.getConnectorsPlugins()
    logger.info("API Connect Plugins: {0}".format(connect_plugins))

    # Find MetricSource entities and config Kafka HTTP Source connectors
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

# Run  FastAPI server
try:
    initCollector()
except Exception as e:
    logger.exception(e)
    sys.exit(1)

# Init FastAPI server
app = FastAPI(
    title="Weaver API",
    version="1.0.0")

# API for NGSI-LD notifications
@app.post("/notify",
          status_code=status.HTTP_200_OK)
async def receiveNotification(request: Request):
    notifications = await request.json()
    for notification in notifications["data"]:
        metricTarget = MetricTarget.parse_obj(notification)
        logger.info(metricTarget.json(indent=4,
                                      sort_keys=True,
                                      exclude_unset=True))
