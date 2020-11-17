from .ngsi_ld.entity import Entity, Property
from typing import Literal


class PrometheusMetric(Entity):
    type: Literal["PrometheusMetric"]
    name: Property
    labels: Property
    sample: Property


class PrometheusMetricFragment(Entity):
    sample: Property
