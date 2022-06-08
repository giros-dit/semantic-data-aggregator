import logging
import os
import time

from fastapi import FastAPI, Request, status
from redis import Redis
from semantic_tools.bindings.notification import NgsiLdNotification
from semantic_tools.bindings.pipelines.clarity.datalake import \
    DataLakeDispatcher
from semantic_tools.bindings.pipelines.clarity.gnmi import GnmiCollector
from semantic_tools.bindings.pipelines.clarity.interfaceKPI import \
    InterfaceKpiAggregator
from semantic_tools.bindings.subscription import Subscription
from semantic_tools.ngsi_ld.api import NGSILDAPI

from weaver.applications.gnmi import process_gnmi_collector
from weaver.applications.interfaceKPI import process_interface_kpi_aggregator
from weaver.orchestration.flink import FlinkClient
from weaver.orchestration.nifi import NiFiClient

logger = logging.getLogger(__name__)

# List of tasks supported by weaver.
# Specifying each type won't be needed once NGSI-LD API
# adds support for entity type inheritance
PIPELINE_TASKS = [
    "GnmiCollector",
    "InterfaceKPIAggregator",
    "DataLakeDispatcher"
]

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
# Redis
REDIS_HOST = os.getenv("REDIS_HOST", "redis")
REDIS_PORT = os.getenv("REDIS_PORT", "6379")
REDIS_PASSWORD = os.getenv("REDIS_PASSWORD")
# Weaver
WEAVER_URI = os.getenv("WEAVER_URI", "http://weaver:8080/notify")

# Init Flink REST API Client
flink = FlinkClient(url=FLINK_MANAGER_URI)
# Init NGSI-LD Client
ngsi_ld = NGSILDAPI(url=BROKER_URI, context=CONTEXT_CATALOG_URI)
# Init NiFi REST API Client
nifi = NiFiClient(username=NIFI_USERNAME,
                  password=NIFI_PASSWORD,
                  url=NIFI_URI)
# Init Redis Client
redis = Redis(
    host=REDIS_HOST,
    port=REDIS_PORT,
    password=REDIS_PASSWORD)

# Init FastAPI server
app = FastAPI(
    title="Weaver API",
    version="1.0.0")


@app.on_event("startup")
async def startup_event():
    # Subscribe to data pipeline task entities
    for task_type in PIPELINE_TASKS:
        subscription_id = "urn:ngsi-ld:Subscription:weaver:{0}".format(
                                task_type)
        try:
            ngsi_ld.createSubscription(
                Subscription(
                    id=subscription_id,
                    entities=[
                        {
                            "type": task_type
                        }
                    ],
                    notification={
                        "endpoint": {
                            "uri": WEAVER_URI
                        }
                    }
                ).dict(exclude_none=True, by_alias=True)
            )
        except Exception:
            logger.info(
                "Subscription {0} already created".format(subscription_id))
            continue
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
    notification_json = await request.json()
    notification = NgsiLdNotification.parse_obj(notification_json)
    for entity in notification.data:
        if entity.type.__root__ == "GnmiCollector":
            gnmi_collector = GnmiCollector.parse_obj(
                entity.dict(exclude_none=True, by_alias=True))
            process_gnmi_collector(
                gnmi_collector, flink, nifi, ngsi_ld, redis)
        if entity.type.__root__ == "InterfaceKpiAggregator":
            if_kpi_aggr = InterfaceKpiAggregator.parse_obj(
                entity.dict(exclude_none=True, by_alias=True))
            process_interface_kpi_aggregator(
                if_kpi_aggr, flink, ngsi_ld, redis)
        if entity.type.__root__ == "DataLakeDispatcher":
            data_lake_dispatcher = DataLakeDispatcher.parse_obj(
                entity.dict(exclude_none=True, by_alias=True))
