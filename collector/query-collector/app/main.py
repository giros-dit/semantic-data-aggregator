from fastapi import FastAPI, status
from prometheus_api_client import PrometheusConnect, MetricSnapshotDataFrame
from typing import Optional

import datetime as dt

# Prometheus Config
prom = PrometheusConnect(url="http://prometheus:9099", disable_ssl=True)

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
async def queryEntities(type: str, attrs: Optional[str] = None,
                        q: Optional[str] = None):
    # metric current values
    metric_data = prom.get_current_metric_value(
        metric_name="prometheus_http_requests_total",
        label_config=labels)
    metric_df = MetricSnapshotDataFrame(metric_data)
    metric_df.pop('__name__')
    print(metric_df.to_string())
