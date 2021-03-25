from fastapi import FastAPI, status, Request
from semantic_tools.clients.ngsi_ld import NGSILDClient
from semantic_tools.models.metric import (
    MetricSource, MetricTarget,
    MetricProcessor, StreamApplication,
    TelemetrySource
)

import logging
import ngsi_ld_ops

logger = logging.getLogger(__name__)

# Init NGSI-LD API Client
ngsi = NGSILDClient(
            url="http://scorpio:9090",
            headers={"Accept": "application/json"},
            context="http://context-catalog:8080/context.jsonld")

# Consumer URI (Should be provided by external agent in the future)
consumer_uri = "http://experimenter:8080/notify"

# FastAPI specific code
tags_metadata = [
    {
        "name": "Dummy Consumer"
    }
]

# Init FastAPI server
app = FastAPI(
    title="Dummy Consumer API",
    version="1.0.0",
    openapi_tags=tags_metadata)

@app.on_event("startup")
async def startup_event():
    # Check Scorpio API is up
    ngsi_ld_ops.check_scorpio_status(ngsi)
    # Subscribe to data pipeline admin entities
    ngsi_ld_ops.subscribeMetricSource(ngsi, consumer_uri)
    ngsi_ld_ops.subscribeMetricProcessor(ngsi, consumer_uri)
    ngsi_ld_ops.subscribeStreamApplication(ngsi, consumer_uri)
    ngsi_ld_ops.subscribeMetricTarget(ngsi, consumer_uri)
    ngsi_ld_ops.subscribeTelemetrySource(ngsi, consumer_uri)

# API for consumer
"""
@app.post("/notify",
          status_code=status.HTTP_200_OK)
async def consumerHello(request: Request):
    print(await request.json())
"""

# API for consumer
@app.post("/notify",
          status_code=status.HTTP_200_OK)
async def receiveNotification(request: Request):
    notifications = await request.json()
    for notification in notifications["data"]:
        if notification["type"] == "MetricSource":
            metricSource = MetricSource.parse_obj(notification)
            print(metricSource.json(indent=4, sort_keys=True, exclude_unset=True))
            print("Notification! State: '{0}' -  State information: '{1}'".format(metricSource.state.value, metricSource.state.stateInfo.value))
        if notification["type"] == "MetricTarget":
            metricTarget = MetricTarget.parse_obj(notification)
            print(metricTarget.json(indent=4, sort_keys=True, exclude_unset=True))
            print("Notification! State: '{0}' -  State information: '{1}'".format(metricTarget.state.value, metricTarget.state.stateInfo.value))
        if notification["type"] == "MetricProcessor":
            metricProcessor = MetricProcessor.parse_obj(notification)
            print(metricProcessor.json(indent=4, sort_keys=True, exclude_unset=True))
            print("Notification! State: '{0}' -  State information: '{1}'".format(metricProcessor.state.value, metricProcessor.state.stateInfo.value))
        if notification["type"] == "StreamApplication":
            streamApplication = StreamApplication.parse_obj(notification)
            print(streamApplication.json(indent=4, sort_keys=True, exclude_unset=True))
            print("Notification! State: '{0}' -  State information: '{1}'".format(streamApplication.state.value, streamApplication.state.stateInfo.value))
        if notification["type"] == "TelemetrySource":
            telemetrySource = TelemetrySource.parse_obj(notification)
            print(telemetrySource.json(indent=4, sort_keys=True, exclude_unset=True))
            print("Notification! State: '{0}' -  State information: '{1}'".format(telemetrySource.state.value, telemetrySource.state.stateInfo.value))
