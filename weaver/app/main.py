from create_entities import createEntities
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

def buildHTTPSinkConnector(metricTarget: MetricTarget) -> dict:
    """
    Generate HTTP Sink Connector configuration
    from a passed MetricTarget entity
    """
    # Get topic name from input ID
    input_id = metricTarget.hasInput.object.strip(
                    "urn:ngsi-ld:").replace(":", "-").lower()
    target_id = metricTarget.id.strip(
                    "urn:ngsi-ld:").replace(":", "-").lower()
    # Reference config from:
    # https://medium.com/@ishagupta_75770/
    # how-to-use-confluent-http-sink-connector-419565c88146
    config = {
        "name": "{0}-{1}".format(target_id, input_id),
        "config": {
            "connector.class": "io.confluent.connect.http.HttpSinkConnector",
            "http.api.url": metricTarget.uri.value,
            "request.method": "POST",
            "topics": input_id,
            "tasks.max": "1",
            "value.converter":
                "org.apache.kafka.connect.storage.StringConverter",
            "confluent.topic.bootstrap.servers": "kafka:9092",
            "confluent.topic.replication.factor": "1",
            "reporter.bootstrap.servers": "kafka:9092",
            "reporter.result.topic.name": "success-responses",
            "reporter.result.topic.replication.factor": "1",
            "reporter.error.topic.name": "error-responses",
            "reporter.error.topic.replication.factor": "1"
        }
        
    }
    return config


def buildHTTPSourceConnector(metricSource: MetricSource,
                             ngsi: NGSILDClient) -> dict:
    """
    Generate HTTP Source Connector configuration
    from a passed MetricSource entity
    """
    endpoint_entity = ngsi.retrieveEntityById(metricSource.hasEndpoint.object)
    endpoint = Endpoint.parse_obj(endpoint_entity)
    # Build URL based on optional expression
    url = ""
    if metricSource.expression:
        labels = getQueryLabels(metricSource)
        url = (endpoint.uri.value + "?query=" +
               metricSource.name.value + "{" + labels + "}")
    else:
        url = endpoint.uri.value + "?query=" + metricSource.name.value
    # Get topic name from input ID
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


def loadEntities(ngsi: NGSILDClient):
    # Wait until Scorpio is up
    ngsi.checkScorpioHealth()
    # Create pre-defined MetricSources for demo
    try:
        createEntities(ngsi)
    except Exception as e:
        logger.warning("Could not load NGSI-LD entities. Keep running...") 

def loadKafkaConnectors(ngsi: NGSILDClient,    
                        kafka_connect: KafkaConnectClient):
    # Display Kafka API connect info
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
        try:
            kafka_connect.createConnector(config)
            logger.info("MetricSource '{0}' configuration:\n{1}".format(
                                                    metricSource.id,
                                                    config))
        except Exception as e:
            logger.warning("Could not load kafka connectors. Keep running...")

# Init NGSI-LD API Client
ngsi = NGSILDClient(url="http://scorpio:9090",
                    headers={"Accept": "application/json"},
                    context="http://context-catalog:8080/context.jsonld")
# Init Kafka Connect API Client
kafka_connect = KafkaConnectClient(url="http://kafka-connect:8083")    
# Load demo entities and source connectors
loadEntities(ngsi)
loadKafkaConnectors(ngsi, kafka_connect)
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
        config = buildHTTPSinkConnector(metricTarget)
        kafka_connect.createConnector(config)
        logger.info("MetricTarget '{0}' configuration:\n{1}".format(
                                                        metricTarget.id,
                                                        config))
