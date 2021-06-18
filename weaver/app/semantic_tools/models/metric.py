from .ngsi_ld.entity import Asset, Property, Relationship
from typing import Literal, Optional


class Metric(Asset):
    type: Literal["Metric"] = "Metric"
    labels: Property
    hasMetricFamily: Relationship
    hasPrometheus: Relationship


class MetricFamily(Asset):
    type: Literal["MetricFamily"] = "MetricFamily"
    familyName: Property
    familyType: Property
    help: Optional[Property]
    unit: Optional[Property]


class Prometheus(Asset):
    type: Literal["Prometheus"] = "Prometheus"
    hasEndpoint: Relationship
    version: Optional[Property]
