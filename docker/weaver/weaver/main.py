import logging
import os
import time

from fastapi import FastAPI, Request, status
from semantic_tools.flink.client import FlinkClient
from semantic_tools.models.application import Task
from semantic_tools.ngsi_ld.client import NGSILDClient
from semantic_tools.nifi.client import NiFiClient

from weaver.orchestration import process_task

logger = logging.getLogger(__name__)

# NGSI-LD Context Broker
BROKER_URI = os.getenv("BROKER_URI", "http://scorpio:9090")
# Context Catalog
CONTEXT_CATALOG_URI = os.getenv("CONTEXT_CATALOG_URI",
                                "http://context-catalog:8080/context.jsonld")
# Flink
FLINK_MANAGER_URI = os.getenv("FLINK_MANAGER_URI",
                              "http://flink-jobmanager:8081")
# NiFi
NIFI_URI = os.getenv("NIFI_URI", "https://nifi:8443/nifi-api")
NIFI_USERNAME = os.getenv("NIFI_USERNAME")
NIFI_PASSWORD = os.getenv("NIFI_PASSWORD")
# Weaver
WEAVER_URI = os.getenv("WEAVER_URI", "http://weaver:8080/notify")


# Init NGSI-LD Client
ngsi_ld = NGSILDClient(url=BROKER_URI, context=CONTEXT_CATALOG_URI)
# Init Flink REST API Client
flink = FlinkClient(url=FLINK_MANAGER_URI)
# Init NiFi REST API Client
nifi = NiFiClient(username=NIFI_USERNAME,
                  password=NIFI_PASSWORD,
                  url=NIFI_URI)

# Init FastAPI server
app = FastAPI(
    title="Weaver API",
    version="1.0.0")


@app.on_event("startup")
async def startup_event():
    # Subscribe to data pipeline agent entities
    ngsi_ld.subscribe_weaver_to_task(WEAVER_URI)
    # Check NiFi REST API is up
    # Hack for startup
    while True:
        try:
            nifi.login()
            break
        except Exception:
            logger.warning("NiFi REST API not available. "
                           "Retrying after 10 seconds...")
            time.sleep(10)
    # Deploy DistributedMapCacheServer in root PG
    nifi.deploy_distributed_map_cache_server()
    # Deploy exporter-service PG in root PG
    nifi.deploy_exporter_service()
    # Check Flink REST API is up
    flink.check_flink_status()


@app.post("/notify",
          status_code=status.HTTP_200_OK)
async def receiveNotification(request: Request):
    notifications = await request.json()
    for notification in notifications["data"]:
        if notification["type"] == "Task":
            task = Task.parse_obj(notification)
            process_task(task, flink, nifi, ngsi_ld)
        else:
            logger.error(
                "Weaver does not support %s entity type."
                % notification["type"])
