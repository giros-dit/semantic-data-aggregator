from fastapi import FastAPI, status, Request
from semantic_tools.clients.flink_api_rest import FlinkClient
from semantic_tools.clients.ngsi_ld import NGSILDClient
from semantic_tools.models.metric import (
    MetricSource, MetricTarget,
    MetricProcessor, StreamApplication
)

import flink_ops
import logging
import nifi_ops
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
    # Upload MetricSource and MetricTarget templates
    nifi_ops.upload_templates()
    # Subscribe to data pipeline stage entities
    ngsi_ld_ops.subscribeMetricSource(ngsi, weaver_uri)
    ngsi_ld_ops.subscribeMetricProcessor(ngsi, weaver_uri)
    ngsi_ld_ops.subscribeStreamApplication(ngsi, weaver_uri)
    ngsi_ld_ops.subscribeMetricTarget(ngsi, weaver_uri)


@app.post("/notify",
          status_code=status.HTTP_200_OK)
async def receiveNotification(request: Request):
    notifications = await request.json()
    for notification in notifications["data"]:
        if notification["type"] == "MetricSource":
            metricSource = MetricSource.parse_obj(notification)
            nifi_ops.instantiateMetricSource(metricSource, ngsi)
        if notification["type"] == "MetricTarget":
            metricTarget = MetricTarget.parse_obj(notification)
            nifi_ops.instantiateMetricTarget(metricTarget)
        if notification["type"] == "MetricProcessor":
            metricProcessor = MetricProcessor.parse_obj(notification)
            flink_ops.submitStreamJob(metricProcessor, ngsi, flink)
        if notification["type"] == "StreamApplication":
            streamApplication = StreamApplication.parse_obj(notification)
            flink_ops.uploadStreamApp(streamApplication, ngsi, flink)
