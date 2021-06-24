from fastapi import FastAPI, status, Request
from semantic_tools.flink.client import FlinkClient
from semantic_tools.flink.utils import check_flink_status
from semantic_tools.models.application import Task
from semantic_tools.models.common import Endpoint
from semantic_tools.models.metric import Prometheus
from semantic_tools.models.telemetry import Device
from semantic_tools.nifi.utils import check_nifi_status
from semantic_tools.ngsi_ld.client import NGSILDClient
from weaver.health import (
    processDeviceState,
    processEndpointState,
    processPrometheusState
)
from semantic_tools.ngsi_ld.utils import (
    check_scorpio_status,
    subscribeTask,
    subscribeDevice,
    subscribeEndpoint,
    subscribePrometheus
)
from weaver.orchestration import process_task

import logging
import nipyapi

logger = logging.getLogger(__name__)

# Init NGSI-LD API Client
ngsi = NGSILDClient(
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
nipyapi.config.nifi_config.host = "http://nifi:8080/nifi-api"

# Weaver URI (Should be provided by external agent in the future)
weaver_uri = "http://weaver:8080/notify"

# Init FastAPI server
app = FastAPI(
    title="Weaver API",
    version="1.0.0")


@app.on_event("startup")
async def startup_event():
    # Check Scorpio API is up
    check_scorpio_status(ngsi)
    # Subscribe to data pipeline agent entities
    subscribeTask(ngsi, weaver_uri)
    # Subscribe to data source entities
    # subscribePrometheus(ngsi, weaver_uri)
    # subscribeDevice(ngsi, weaver_uri)
    # Subscribe to Endpoint entities
    # subscribeEndpoint(ngsi, weaver_uri)
    # Check NiFi REST API is up
    check_nifi_status()
    # Check Flink REST API is up
    check_flink_status(flink)


@app.post("/notify",
          status_code=status.HTTP_200_OK)
async def receiveNotification(request: Request):
    notifications = await request.json()
    for notification in notifications["data"]:
        if notification["type"] == "Task":
            logger.info("task received")
            task = Task.parse_obj(notification)
            process_task(task, ngsi, flink)
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

