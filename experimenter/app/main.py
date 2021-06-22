from fastapi import FastAPI, status, Request
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
import ngsi_ld_ops

logger = logging.getLogger(__name__)

# Init NGSI-LD API Client
ngsi = NGSILDClient(
            url="http://scorpio:9090",
            headers={"Accept": "application/json"},
            context="http://context-catalog:8080/context.jsonld")

# Experimenter URI (Should be provided by external agent in the future)
experimenter_uri = "http://experimenter:8080/notify"

# FastAPI specific code
tags_metadata = [
    {
        "name": "Dummy Experimenter"
    }
]

# Init FastAPI server
app = FastAPI(
    title="Experimenter API",
    version="1.0.0",
    openapi_tags=tags_metadata)

@app.on_event("startup")
async def startup_event():
    # Check Scorpio API is up
    ngsi_ld_ops.check_scorpio_status(ngsi)
    # Subscribe to data pipeline agent entities
    ngsi_ld_ops.subscribeEVESource(ngsi, experimenter_uri)
    ngsi_ld_ops.subscribeMetricSource(ngsi, experimenter_uri)
    ngsi_ld_ops.subscribeMetricProcessor(ngsi, experimenter_uri)
    ngsi_ld_ops.subscribeStreamApplication(ngsi, experimenter_uri)
    ngsi_ld_ops.subscribeMetricTarget(ngsi, experimenter_uri)
    ngsi_ld_ops.subscribeTelemetrySource(ngsi, experimenter_uri)
    ngsi_ld_ops.subscribeSOLogSource(ngsi, experimenter_uri)
    # Subscribe to data sources entities
    ngsi_ld_ops.subscribePrometheus(ngsi, experimenter_uri)
    ngsi_ld_ops.subscribeDevice(ngsi, experimenter_uri)
    # Subscribe to Endpoint entities
    ngsi_ld_ops.subscribeEndpoint(ngsi, experimenter_uri)

# API for experimenter
@app.post("/notify",
          status_code=status.HTTP_200_OK)
async def receiveNotification(request: Request):
    notifications = await request.json()
    for notification in notifications["data"]:
        if notification["type"] == "EVESource":
            eveSource = EVESource.parse_obj(notification)
            logger.info(eveSource.json(indent=4, sort_keys=True, exclude_unset=True))
            logger.info("Notification! State: '{0}' -  State information: '{1}'".format(eveSource.state.value, eveSource.state.stateInfo.value))
        if notification["type"] == "MetricSource":
            metricSource = MetricSource.parse_obj(notification)
            logger.info(metricSource.json(indent=4, sort_keys=True, exclude_unset=True))
            logger.info("Notification! State: '{0}' -  State information: '{1}'".format(metricSource.state.value, metricSource.state.stateInfo.value))
        if notification["type"] == "MetricTarget":
            metricTarget = MetricTarget.parse_obj(notification)
            logger.info(metricTarget.json(indent=4, sort_keys=True, exclude_unset=True))
            logger.info("Notification! State: '{0}' -  State information: '{1}'".format(metricTarget.state.value, metricTarget.state.stateInfo.value))
        if notification["type"] == "MetricProcessor":
            metricProcessor = MetricProcessor.parse_obj(notification)
            logger.info(metricProcessor.json(indent=4, sort_keys=True, exclude_unset=True))
            logger.info("Notification! State: '{0}' -  State information: '{1}'".format(metricProcessor.state.value, metricProcessor.state.stateInfo.value))
        if notification["type"] == "StreamApplication":
            streamApplication = StreamApplication.parse_obj(notification)
            logger.info(streamApplication.json(indent=4, sort_keys=True, exclude_unset=True))
            logger.info("Notification! State: '{0}' -  State information: '{1}'".format(streamApplication.state.value, streamApplication.state.stateInfo.value))
        if notification["type"] == "TelemetrySource":
            telemetrySource = TelemetrySource.parse_obj(notification)
            logger.info(telemetrySource.json(indent=4, sort_keys=True, exclude_unset=True))
            logger.info("Notification! State: '{0}' -  State information: '{1}'".format(telemetrySource.state.value, telemetrySource.state.stateInfo.value))
        if notification["type"] == "SOLogSource":
            soLogSource = SOLogSource.parse_obj(notification)
            logger.info(soLogSource.json(indent=4, sort_keys=True, exclude_unset=True))
            logger.info("Notification! State: '{0}' -  State information: '{1}'".format(soLogSource.state.value, soLogSource.state.stateInfo.value))
        if notification["type"] == "Prometheus":
            prometheus = Prometheus.parse_obj(notification)
            logger.info(prometheus.json(indent=4, sort_keys=True, exclude_unset=True))
            logger.info("Notification! State: '{0}' -  State information: '{1}'".format(prometheus.state.value, prometheus.state.stateInfo.value))
        if notification["type"] == "Device":
            device = Device.parse_obj(notification)
            logger.info(device.json(indent=4, sort_keys=True, exclude_unset=True))
            logger.info("Notification! State: '{0}' -  State information: '{1}'".format(device.state.value, device.state.stateInfo.value))
        if notification["type"] == "Endpoint":
            endpoint = Endpoint.parse_obj(notification)
            logger.info(endpoint.json(indent=4, sort_keys=True, exclude_unset=True))
            logger.info("Notification! State: '{0}' -  State information: '{1}'".format(endpoint.state.value, endpoint.state.stateInfo.value))
