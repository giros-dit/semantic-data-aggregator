from .ngsi_ld.entity import Entity, Property, Relationship
from .common import Agent, URI
from typing import Literal, Optional


class Prometheus(Agent):
    type: Literal["Prometheus"] = "Prometheus"
    hasEndpoint: Relationship
    name: Property
    version: Optional[Property]


class MetricSource(Agent):
    type: Literal["MetricSource"] = "MetricSource"
    expression: Optional[Property] = None
    collectsFrom: Relationship
    interval: Property
    name: Property


class MetricProcessor(Agent):
    type: Literal["MetricProcessor"] = "MetricProcessor"
    hasInput: Relationship
    hasApplication: Relationship
    name: Property
    arguments: Optional[Property] = None
    jobId: Optional[Property]


class MetricTarget(Agent):
    type: Literal["MetricTarget"] = "MetricTarget"
    hasInput: Relationship
    uri: URI


class StreamApplication(Agent):
    type: Literal["StreamApplication"] = "StreamApplication"
    fileName: Property
    fileId: Optional[Property]
    entryClass: Optional[Property]
    description: Property
    uri: URI
