import logging

from fastapi import FastAPI, Request, status
from semantic_tools.flink.client import FlinkClient
from semantic_tools.models.application import Task
from semantic_tools.ngsi_ld.client import NGSILDClient
from semantic_tools.nifi.client import NiFiClient

from weaver.orchestration import process_task

logger = logging.getLogger(__name__)

# Weaver URL (Should be provided by external agent in the future)
WEAVER_URL = "http://weaver:8080/notify"

# Init NGSI-LD Client
ngsi_ld = NGSILDClient(
    url="http://scorpio:9090",
    headers={"Accept": "application/json"},
    context="http://context-catalog:8080/context.jsonld")

# Init Flink REST API Client
flink = FlinkClient(
    url="http://flink-jobmanager:8081",
    headers={
        "Accept": "application/json",
        "Content-Type": "application/json"})

# Init NiFi REST API Client
nifi = NiFiClient(username="admin",
                  password="ctsBtRBKHRAx69EqUghvvgEvjnaLjFEB",
                  url="https://nifi:8443/nifi-api")

# Init FastAPI server
app = FastAPI(
    title="Weaver API",
    version="1.0.0")


@app.on_event("startup")
async def startup_event():
    # Check Scorpio API is up
    ngsi_ld.check_scorpio_status()
    # Subscribe to data pipeline agent entities
    ngsi_ld.subscribe_weaver_to_task(WEAVER_URL)
    # Subscribe to data source entities
    # subscribePrometheus(ngsi, weaver_uri)
    # subscribeDevice(ngsi, weaver_uri)
    # Subscribe to Endpoint entities
    # subscribeEndpoint(ngsi, weaver_uri)
    # Check NiFi REST API is up
    nifi.login()
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
        # elif notification["type"] == "Device":
        #     device = Device.parse_obj(notification)
        #     processDeviceState(device, ngsi)
        # elif notification["type"] == "Endpoint":
        #     endpoint = Endpoint.parse_obj(notification)
        #     processEndpointState(endpoint, ngsi)
        # elif notification["type"] == "Prometheus":
        #     prometheus = Prometheus.parse_obj(notification)
        #     processPrometheusState(prometheus, ngsi)
        else:
            logger.error(
                "Weaver does not support %s entity type."
                % notification["type"])
