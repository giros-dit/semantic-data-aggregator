from fastapi import FastAPI, status, Request
from semantic_tools.clients.flink_api_rest import FlinkClient
from semantic_tools.clients.ngsi_ld import NGSILDClient
from semantic_tools.models.common import Endpoint
from semantic_tools.models.metric import (
    MetricSource, MetricTarget,
    MetricProcessor, StreamApplication,
    Prometheus
)
from semantic_tools.models.telemetry import TelemetrySource, Device
from semantic_tools.models.stream import EVESource, SOLogSource

import logging
import nifi_ops
import flink_ops
import orchestration_ops
import nipyapi
import ngsi_ld_ops

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
    # Check NiFi REST API is up
    nifi_ops.check_nifi_status()
    # Upload NiFi templates
    nifi_ops.upload_templates()
    # Check Flink REST API is up
    flink_ops.check_flink_status(flink)
    # Check Scorpio API is up
    ngsi_ld_ops.check_scorpio_status(ngsi)
    # Subscribe to data pipeline agent entities
    ngsi_ld_ops.subscribeEVESource(ngsi, weaver_uri)
    ngsi_ld_ops.subscribeMetricSource(ngsi, weaver_uri)
    ngsi_ld_ops.subscribeMetricProcessor(ngsi, weaver_uri)
    ngsi_ld_ops.subscribeStreamApplication(ngsi, weaver_uri)
    ngsi_ld_ops.subscribeMetricTarget(ngsi, weaver_uri)
    ngsi_ld_ops.subscribeTelemetrySource(ngsi, weaver_uri)
    ngsi_ld_ops.subscribeSOLogSource(ngsi, weaver_uri)
    # Subscribe to data source entities
    ngsi_ld_ops.subscribePrometheus(ngsi, weaver_uri)
    ngsi_ld_ops.subscribeDevice(ngsi, weaver_uri)
    # Subscribe to Endpoint entities
    ngsi_ld_ops.subscribeEndpoint(ngsi, weaver_uri)

@app.post("/notify",
          status_code=status.HTTP_200_OK)
async def receiveNotification(request: Request):
    notifications = await request.json()
    for notification in notifications["data"]:
        if notification["type"] == "EVESource":
            eveSource = EVESource.parse_obj(notification)
            orchestration_ops.processStreamSourceState(eveSource, ngsi)
        if notification["type"] == "MetricSource":
            metricSource = MetricSource.parse_obj(notification)
            orchestration_ops.processMetricSourceState(metricSource, ngsi)
        if notification["type"] == "MetricTarget":
            metricTarget = MetricTarget.parse_obj(notification)
            orchestration_ops.processMetricTargetState(metricTarget, ngsi)
        if notification["type"] == "MetricProcessor":
            metricProcessor = MetricProcessor.parse_obj(notification)
            orchestration_ops.processMetricProcessorState(metricProcessor, ngsi, flink)
        if notification["type"] == "StreamApplication":
            streamApplication = StreamApplication.parse_obj(notification)
            orchestration_ops.processStreamApplicationState(streamApplication, ngsi, flink)
        if notification["type"] == "TelemetrySource":
            telemetrySource = TelemetrySource.parse_obj(notification)
            orchestration_ops.processTelemetrySourceState(telemetrySource, ngsi)
        if notification["type"] == "SOLogSource":
            soLogSource = SOLogSource.parse_obj(notification)
            orchestration_ops.processStreamSourceState(soLogSource, ngsi)
        if notification["type"] == "Prometheus":
            prometheus = Prometheus.parse_obj(notification)
            orchestration_ops.processPrometheusState(prometheus, ngsi)
        if notification["type"] == "Device":
            device = Device.parse_obj(notification)
            orchestration_ops.processDeviceState(device, ngsi)
        if notification["type"] == "Endpoint":
            endpoint = Endpoint.parse_obj(notification)
            orchestration_ops.processEndpointState(endpoint, ngsi)

