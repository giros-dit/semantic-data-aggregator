from .ngsi_ld.entity import Property, Relationship
from .common import Asset, StatefulAsset
from typing import Literal, Optional


class Metric(Asset):
    type: Literal["Metric"] = "Metric"
    labels: Property
    hasMetricFamily: Relationship
    hasPrometheus: Relationship


class MetricFamily(Asset):
    type: Literal["MetricFamily"] = "MetricFamily"
    familyType: Property
    help: Optional[Property]
    unit: Optional[Property]


class Prometheus(StatefulAsset):
    type: Literal["Prometheus"] = "Prometheus"
    hasEndpoint: Relationship
    version: Optional[Property]