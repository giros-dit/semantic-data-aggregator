from semantic_tools.models.common import Asset, Infrastructure
from semantic_tools.models.ngsi_ld.entity import Property, Relationship
from typing import Literal, Optional


class Metric(Asset):
    type: Literal["Metric"] = "Metric"
    hasLabels: Relationship
    hasMetricFamily: Relationship
    hasPrometheus: Relationship
    hasPrometheusExporter: Relationship


class MetricFamily(Asset):
    type: Literal["MetricFamily"] = "MetricFamily"
    familyType: Property
    help: Optional[Property]
    unit: Optional[Property]


class Prometheus(Infrastructure):
    type: Literal["Prometheus"] = "Prometheus"
    version: Optional[Property]


class PrometheusExporter(Infrastructure):
    type: Literal["PrometheusExporter"] = "PrometheusExporter"
