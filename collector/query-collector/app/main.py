from prometheus_api_client import PrometheusConnect, MetricSnapshotDataFrame, MetricRangeDataFrame
from starlette.applications import Starlette
from starlette.responses import PlainTextResponse
from starlette.routing import Route

import datetime as dt


def parse_labels(params):
    labels = {}
    for param, value in params.multi_items():
        if param == 'metric_name' or param == 'range':
            continue
        labels[param] = value
    return labels


async def metrics(request):
    metric_name = request.query_params['metric_name']
    labels = parse_labels(request.query_params)

    prom = PrometheusConnect(url="http://prometheus:9090", disable_ssl=True)
    # metric current values
    metric_data = prom.get_current_metric_value(
        metric_name=metric_name,
        label_config=labels
    )
    metric_df = MetricSnapshotDataFrame(metric_data)
    metric_df.pop('__name__')
    print(metric_df.to_string())

    return PlainTextResponse()


async def metrics_range(request):
    metric_name = request.query_params['metric_name']
    range = request.query_params['range']
    labels = parse_labels(request.query_params)

    prom = PrometheusConnect(url="http://prometheus:9090", disable_ssl=True)
    # metric current values
    metric_data = prom.get_metric_range_data(
        metric_name=metric_name,
        label_config=labels,
        start_time=(dt.datetime.now() - dt.timedelta(minutes=int(range))),
        end_time=dt.datetime.now()
    )
    if metric_data:
        metric_df = MetricRangeDataFrame(metric_data)
        metric_df.pop('__name__')
        print(metric_df.to_string())

    return PlainTextResponse()


routes = [
    Route("/metrics", endpoint=metrics, methods=["GET"]),
    Route("/metrics_range", endpoint=metrics_range, methods=["GET"])
]

app = Starlette(routes=routes)
