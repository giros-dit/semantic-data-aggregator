from fastapi import FastAPI, status
from fastapi.responses import JSONResponse
from prometheus_api_client import PrometheusConnect
from typing import Optional

from datetime import datetime

import random

METRIC_KV = """
[{
        "id": "urn:ngsi-ld:PromMetric:5",
        "type": "PromMetric",
        "hasLabels": "urn:ngsi-ld:PromLabel:1",
        "sample": "28.0",
        "name": "prometheus_http_requests_total",
        "@context": [
            "https://pastebin.com/raw/ueLM6utf"
        ]
    }
]
"""


def buildMetric(sample, timestamp, metric_name, labels):
    metric = {
        "id": "urn:ngsi-ld:PromMetric:1",
        "type": "PromMetric",
        "hasLabels": {
            "type": "Property",
            "value": labels
        },
        "sample": {
            "type": "Property",
            "value": sample,
            "observedAt": timestamp
        },
        "name": {
            "type": "Property",
            "value": metric_name
        },
        "@context": [
            "https://pastebin.com/raw/ueLM6utf"
        ]
    }
    return metric

# Prometheus Config
prom = PrometheusConnect(url="http://prometheus:9090", disable_ssl=True)

# FastAPI specific code
tags_metadata = [
    {
        "name": "Context Information",
        "description": "NGSI-LD Entities",
    }
]

app = FastAPI(
    title="NGSI-LD API",
    version="1.0.0",
    openapi_tags=tags_metadata)


@app.get("/ngsi-ld/v1/entities/",
         summary="Query entities",
         description="Retrieve a set of entities which matches \
                      a specific query from an NGSI-LD system",
         tags=["Context Information"],
         status_code=status.HTTP_200_OK)
async def queryEntities(type: str,
                        attrs: Optional[str] = None,
                        q: Optional[str] = None,
                        options: Optional[str] = None):
    entity_list = []
    metric_name = "prometheus_http_requests_total"
    metric_data = prom.get_current_metric_value(
        metric_name=metric_name)
    for data in metric_data:
        dt = datetime.utcfromtimestamp(data['value'][0]).isoformat()
        labels = data['metric']
        metric = buildMetric(data['value'][1], dt, metric_name, labels)
        metric["id"] = metric["id"] + str(random.randint(3, 9))
        entity_list.append(metric)

    headers = {"Content-Type": "application/ld+json"}
    return JSONResponse(content=entity_list, headers=headers)
