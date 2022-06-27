import logging
import os
from time import sleep

from fastapi import FastAPI, Request, status
from ngsi_ld_client.api.subscriptions_api import \
    SubscriptionsApi as NGSILDSubscriptionsApi
from ngsi_ld_client.api_client import ApiClient as NGSILDClient
from ngsi_ld_client.configuration import Configuration as NGSILDConfiguration
from ngsi_ld_client.exceptions import ApiException

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


# Init NGSI-LD Client
configuration = NGSILDConfiguration(host=BROKER_URI)
ngsi_ld = NGSILDClient(configuration=configuration)
ngsi_ld.set_default_header(
    header_name="Link",
    header_value='<{0}>; '
                 'rel="http://www.w3.org/ns/json-ld#context"; '
                 'type="application/ld+json"'.format(CONTEXT_CATALOG_URI)
)
ngsi_ld.set_default_header(
    header_name="Accept",
    header_value="application/json"
)

# Init FastAPI server
app = FastAPI(
    title="Telemetry Explorer API",
    version="1.0.0")


@app.on_event("startup")
async def startup_event():
    logger.info("Starting telemetry-explorer service...")
    api_instance = NGSILDSubscriptionsApi(ngsi_ld)
    while True:
        # Subscribe to Device entity
        try:
            _ = api_instance.create_subscription(
                body={
                    "id": TELEMETRY_EXPLORER_SUBSCRIPTION_ID,
                    "type": "Subscription",
                    "entities": [
                        {
                            "type": "Device"
                        }
                    ],
                    "notification": {
                        "endpoint": {
                            "uri": TELEMETRY_EXPLORER_URI
                        }
                    }
                }
            )
        except ApiException as e:
            if e.status == 409:
                logger.info(
                    "Subscription {0} already created".format(
                        TELEMETRY_EXPLORER_SUBSCRIPTION_ID))
                break
            else:
                logger.warning("NGSI-LD REST API not available. "
                               "Retrying after 10 seconds...")
                sleep(10)

    logger.info("telemetry-explorer service ready!")


@app.post("/notify",
          status_code=status.HTTP_200_OK)
async def receiveNotification(request: Request):
    notification = await request.json()
    for entity in notification["data"]:
        register_device(ngsi_ld, entity)
