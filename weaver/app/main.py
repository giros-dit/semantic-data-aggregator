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
    # Check Scorpio API is up
    ngsi_ld_ops.check_scorpio_status(ngsi)
    # Subscribe to data pipeline stage entities
    ngsi_ld_ops.subscribeMetricSource(ngsi, weaver_uri)
    ngsi_ld_ops.subscribeMetricProcessor(ngsi, weaver_uri)
    # Fix to avoid loops when creating StreamApplications
    ngsi_ld_ops.subscribeStreamApplication(ngsi, weaver_uri, "fileName")
    ngsi_ld_ops.subscribeMetricTarget(ngsi, weaver_uri)

@app.post("/notify",
          status_code=status.HTTP_200_OK)
async def receiveNotification(request: Request):
    notifications = await request.json()
    for notification in notifications["data"]:
        if notification["type"] == "MetricSource":
            metricSource = MetricSource.parse_obj(notification)
            # Query entity by id to get the 'unitCode'
            # from MetricSource (notification doesn't receive it)
            metricSource_entity = ngsi.retrieveEntityById(metricSource.id)
            metricSource = MetricSource.parse_obj(metricSource_entity)
            nifi_ops.processMetricSourceMode(metricSource, ngsi)
        if notification["type"] == "MetricTarget":
            metricTarget = MetricTarget.parse_obj(notification)
            nifi_ops.processMetricTargetMode(metricTarget, ngsi)
        if notification["type"] == "MetricProcessor":
            metricProcessor = MetricProcessor.parse_obj(notification)
            # Checking if MetricProcesor has a StreamApplication already created
            streamApplication_entities = ngsi.queryEntities(type="StreamApplication")
            failed = False
            if len(streamApplication_entities) > 0:
                for streamApplication_entity in streamApplication_entities:
                    if streamApplication_entity['id'] == metricProcessor.hasApplication.object:
                        flink_ops.processMetricProcessorMode(metricProcessor, ngsi, flink)
                        failed = False
                    else:
                        failed = True
            else:
                failed = True
            if failed:
                ngsi.deleteEntity(metricProcessor.id)
        if notification["type"] == "StreamApplication":
            streamApplication = StreamApplication.parse_obj(notification)
            flink_ops.uploadStreamApp(streamApplication, ngsi, flink)
