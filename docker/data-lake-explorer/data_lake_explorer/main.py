import logging
import os

from fastapi import FastAPI, Request, status
from semantic_tools.ngsi_ld.client import NGSILDClient

from data_lake_explorer.clients.data_lake import APIGateway
from data_lake_explorer.registration import register_data_lake

logger = logging.getLogger(__name__)

# NGSI-LD Context Broker
BROKER_URI = os.getenv("BROKER_URI", "http://scorpio:9090")
# Context Catalog
CONTEXT_CATALOG_URI = os.getenv("CONTEXT_CATALOG_URI",
                                "http://context-catalog:8080/context.jsonld")
# Data Lake Explorer
DATA_LAKE_KEY = os.getenv("DATA_LAKE_KEY")
DATA_LAKE_REGION = os.getenv("DATA_LAKE_REGION", "eu-west-2")
DATA_LAKE_URI = os.getenv(
    "DATA_LAKE_URI",
    "https://2yd7m1wqii.execute-api.eu-west-2.amazonaws.com/v1")
DATA_LAKE_EXPLORER_SUBSCRIPTION_ID = os.getenv(
    "DATA_LAKE_SUBSCRIPTION_ID",
    "urn:ngsi-ld:Subscription:DataLake:data-lake-explorer-subs")
DATA_LAKE_EXPLORER_URI = os.getenv(
    "DATA_LAKE_EXPLORER_URI", "http://data-lake-explorer:8080/notify")


# Init NGSI-LD API
ngsi_ld = NGSILDClient(
        url=BROKER_URI,
        context=CONTEXT_CATALOG_URI
    )

# Init IDCC API Gateway
agw = APIGateway(
    api_key=DATA_LAKE_KEY,
    region=DATA_LAKE_REGION,
    url=DATA_LAKE_URI
)

# Init FastAPI server
app = FastAPI(
    title="Data Lake Explorer API",
    version="1.0.0")


@app.on_event("startup")
async def startup_event():
    logger.info("Started data-lake-explorer service!")
    # Subscribe to DataLake entity
    ngsi_ld.subscribe_to_entity_type(
        entity_type="DataLake",
        endpoint=DATA_LAKE_EXPLORER_URI,
        subscription_id=DATA_LAKE_EXPLORER_SUBSCRIPTION_ID)


@app.post("/notify",
          status_code=status.HTTP_200_OK)
async def receiveNotification(request: Request):
    notifications = await request.json()
    for notification in notifications["data"]:
        if notification["type"] == "DataLake":
            register_data_lake(ngsi_ld, agw, notification)
        else:
            logger.error(
                "Data Lake Explorer does not support %s entity type."
                % notification["type"])
