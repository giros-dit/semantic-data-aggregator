from fastapi import FastAPI, status, Request
from semantic_tools.clients.ngsi_ld import NGSILDClient
from semantic_tools.clients.flink_api_rest import FlinkClient
from semantic_tools.models.metric import MetricSource, MetricTarget, MetricProcessor, StreamApplication

import logging
import nifi_ops
import flink_ops
import nipyapi

logger = logging.getLogger(__name__)

# Init NGSI-LD API Client
ngsi = NGSILDClient(url="http://scorpio:9090",
                    headers={"Accept": "application/json"},
                    context="http://context-catalog:8080/context.jsonld")

# Init Flink REST API Client
flink = FlinkClient(url="http://flink-jobmanager:8081", headers={"Accept": "application/json", "Content-Type": "application/json"})

# Init NiFi REST API Client
nipyapi.config.nifi_config.host = "http://nifi:8080/nifi-api"

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


@app.post("/notify",
          status_code=status.HTTP_200_OK)
async def receiveNotification(request: Request):
    notifications = await request.json()
    print("NOTA", notifications)
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
