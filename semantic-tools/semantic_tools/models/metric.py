from typing import Literal, Optional

from semantic_tools.models.common import Asset, Infrastructure
from semantic_tools.models.ngsi_ld.entity import Property, Relationship


class Metric(Asset):
    type: Literal["Metric"] = "Metric"
    hasLabels: Relationship
    hasMetricFamily: Relationship
    hasPrometheus: Optional[Relationship]
    hasPrometheusExporter: Optional[Relationship]


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
