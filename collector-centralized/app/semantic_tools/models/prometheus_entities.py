from .ngsi_ld.entity import Entity, Property, Relationship
from pydantic import BaseModel
from typing import Literal, Optional


class Metric(Entity):
    type: Literal["Metric"] = "Metric"
    sample: Property

class MetricSource(Entity):
    type: Literal["MetricSource"] = "MetricSource"
    name: Property
    expression: Optional[Property] = None
    interval: Property
    isSourceOf: Relationship
    hasEndPoint: Relationship
    javaclass: Property
    topic: Property

class Endpoint(Entity):
    type: Literal["Endpoint"] = "Endpoint"
    name: Property
    URI: Property

class Prometheus(Entity):
    type: Literal["Prometheus"] = "Prometheus"
    name: Property
    jobs: Property
    format: Property
    isComposedBy: Relationship
