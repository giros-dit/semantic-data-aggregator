import logging
import os

from fastapi import FastAPI, Request, status
from semantic_tools.bindings.notification import NgsiLdNotification
from semantic_tools.bindings.subscription import Subscription
from semantic_tools.ngsi_ld.api import NGSILDAPI

from telemetry_explorer.registration import register_device

logger = logging.getLogger(__name__)

# NGSI-LD Context Broker
BROKER_URI = os.getenv("BROKER_URI", "http://scorpio:9090")
# Context Catalog
CONTEXT_CATALOG_URI = os.getenv("CONTEXT_CATALOG_URI",
                                "http://context-catalog:8080/context.jsonld")
# Telmetry Explorer
TELEMETRY_EXPLORER_SUBSCRIPTION_ID = os.getenv(
    "TELEMETRY_EXPLORER_SUBSCRIPTION_ID",
    "urn:ngsi-ld:Subscription:Device:telemetry-explorer-subs")
TELEMETRY_EXPLORER_URI = os.getenv(
    "TELEMETRY_EXPLORER_URI", "http://telemetry-explorer:8080/notify")


# Init NGSI-LD API
ngsi_ld = NGSILDAPI(
        url=BROKER_URI,
        context=CONTEXT_CATALOG_URI
    )

# Init FastAPI server
app = FastAPI(
    title="Telemetry Explorer API",
    version="1.0.0")


@app.on_event("startup")
async def startup_event():
    logger.info("Starting telemetry-explorer service...")
    # Subscribe to Device entity
    try:
        ngsi_ld.createSubscription(
            Subscription(
                id=TELEMETRY_EXPLORER_SUBSCRIPTION_ID,
                entities=[
                    {
                        "type": "Device"
                    }
                ],
                notification={
                    "endpoint": {
                        "uri": TELEMETRY_EXPLORER_URI
                    }
                }
            ).dict(exclude_none=True, by_alias=True)
        )
    except Exception:
        logger.info(
            "Subscription {0} already created".format(
                TELEMETRY_EXPLORER_SUBSCRIPTION_ID))

    logger.info("telemetry-explorer service ready!")


@app.post("/notify",
          status_code=status.HTTP_200_OK)
async def receiveNotification(request: Request):
    notification_json = await request.json()
    notification = NgsiLdNotification.parse_obj(notification_json)
    for entity in notification.data:
        register_device(ngsi_ld, entity)
