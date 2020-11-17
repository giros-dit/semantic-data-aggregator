from .ngsi_ld.entity import Entity, Property
from pydantic import BaseModel
from typing import Literal


class PrometheusMetric(Entity):
    type: Literal["PrometheusMetric"] = "PrometheusMetric"
    name: Property
    labels: Property
    sample: Property


class PrometheusMetricFragment(BaseModel):
    sample: Property
