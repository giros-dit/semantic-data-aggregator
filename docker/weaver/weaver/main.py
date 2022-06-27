import logging
import os
import time

from fastapi import FastAPI, Request, status
from flink_client.api.default_api import DefaultApi as FlinkDefaultApi
from flink_client.api_client import ApiClient as FlinkClient
from flink_client.configuration import Configuration as FlinkConfiguration
from ngsi_ld_client.api.subscriptions_api import \
    SubscriptionsApi as NGSILDSubscriptionsApi
from ngsi_ld_client.api_client import ApiClient as NGSILDClient
from ngsi_ld_client.configuration import Configuration as NGSILDConfiguration
from ngsi_ld_client.exceptions import ApiException
from redis import Redis
from semantic_tools.bindings.pipelines.clarity.datalake import \
    DataLakeDispatcher
from semantic_tools.bindings.pipelines.clarity.gnmi import GnmiCollector
from semantic_tools.bindings.pipelines.clarity.interfaceKPI import \
    InterfaceKpiAggregator

from weaver.applications.datalake import process_data_lake_dispatcher
from weaver.applications.gnmi import process_gnmi_collector
from weaver.applications.interfaceKPI import process_interface_kpi_aggregator
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
BROKER_URI = os.getenv("BROKER_URI", "http://scorpio:9090/ngsi-ld/v1")
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
configuration = FlinkConfiguration(host=FLINK_MANAGER_URI)
flink = FlinkClient(configuration=configuration)
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
    logger.info("Checking Flink REST API status ...")
    while True:
        try:
            api_instance = FlinkDefaultApi(flink)
            _ = api_instance.config_get()
            logger.info(
                "Successfully connected to Flink REST API!")
            break
        except Exception:
            logger.warning("Could not connect to Flink REST API. "
                           "Retrying in 30 seconds ...")
            time.sleep(30)
            continue

    # Subscribe to data pipeline task entities
    for task_type in PIPELINE_TASKS:
        subscription_id = "urn:ngsi-ld:Subscription:weaver:{0}".format(
                                task_type)
        api_instance = NGSILDSubscriptionsApi(ngsi_ld)
        try:
            _ = api_instance.create_subscription(
                body={
                    "id": subscription_id,
                    "type": "Subscription",
                    "entities": [
                        {
                            "type": task_type
                        }
                    ],
                    "notification": {
                        "endpoint": {
                            "uri": WEAVER_URI
                        }
                    }
                }
            )
        except ApiException as e:
            if e.status == 409:
                logger.info(
                    "Subscription {0} already created".format(
                        subscription_id))
            else:
                logger.exception(e)
            continue


@app.post("/notify",
          status_code=status.HTTP_200_OK)
async def receiveNotification(request: Request):
    notification = await request.json()
    for entity in notification["data"]:
        if entity["type"] == "GnmiCollector":
            gnmi_collector = GnmiCollector.parse_obj(entity)
            process_gnmi_collector(
                gnmi_collector, flink, nifi, ngsi_ld, redis)
        if entity["type"] == "InterfaceKPIAggregator":
            if_kpi_aggr = InterfaceKpiAggregator.parse_obj(entity)
            process_interface_kpi_aggregator(
                if_kpi_aggr, flink, ngsi_ld, redis)
        if entity["type"] == "DataLakeDispatcher":
            data_lake_dispatcher = DataLakeDispatcher.parse_obj(entity)
            process_data_lake_dispatcher(
                data_lake_dispatcher, flink, nifi, ngsi_ld, redis)
