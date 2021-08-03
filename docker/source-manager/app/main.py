from discovery.eve import DCMAPI
from fastapi import FastAPI
from semantic_tools.ngsi_ld.client import NGSILDClient

import logging
import uuid

logger = logging.getLogger(__name__)


# Init NGSI-LD Client
ngsi_ld = NGSILDClient(
            url="http://scorpio:9090",
            headers={"Accept": "application/json"},
            context="http://context-catalog:8080/context.jsonld",
            debug=False)

# Init FastAPI server
app = FastAPI(
    title="Source Manager API",
    version="1.0.0")


@app.on_event("startup")
async def startup_event():
    # Check Scorpio API is up
    ngsi_ld.check_scorpio_status()


@app.post("/discover-eve/")
async def discover_eve_topics(dcm_address: str, eve_broker_id: str,
                              usecase: str):
    """
    Discovers Kafka topics from 5G EVE
    filtering their names by a usecase string
    """
    dcm = DCMAPI(dcm_address)
    filtered_topic_names = dcm.get_topics_by_usecase(usecase)["topics"]
    kafka_broker = ngsi_ld.get_kafka_broker(eve_broker_id)
    for topic_name in filtered_topic_names:
        matching_name_topics = ngsi_ld.get_kafka_topics_by_name(topic_name)
        topic_registered = False
        for matching_name_topic in matching_name_topics:
            # Already registered
            if matching_name_topic.hasKafkaBroker.object == kafka_broker.id:
                topic_registered = True
                break
        if not topic_registered:
            id = "urn:ngsi-ld:KafkaTopic:{0}".format(str(uuid.uuid4()))
            topic = ngsi_ld.create_kafka_topic(
                kafka_broker, id, topic_name)
            logger.info("KafkaTopic '%s' discovered!" % topic.id)
        else:
            logger.warning(
                "KafkaTopic with name '%s' already registered for '%s'"
                % (topic_name, kafka_broker.id))
