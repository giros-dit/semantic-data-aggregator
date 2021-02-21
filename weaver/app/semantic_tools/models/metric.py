from .ngsi_ld.entity import Entity, Property, Relationship
from pydantic import AnyUrl
from typing import Literal, Optional


class _URI(Property):
    value: AnyUrl


class Credentials(Entity):
    type: Literal["Credentials"] = "Credentials"
    authMethod: Property


class Endpoint(Entity):
    type: Literal["Endpoint"] = "Endpoint"
    hasLogin: Optional[Relationship] = None
    name: Property
    uri: Optional[_URI] = None
    address: Optional[Property] = None

class StageMode(Property):
    value: Literal["START", "STOP", "TERMINATE"]


class ModeResult(Property):
    value: Literal["IN_PROGRESS", "SUCCESSFUL", "FAILED"]
    modeInfo: Optional[Property]


class MetricStage(Entity):
    type: Literal["MetricStage"] = "MetricStage"
    stageMode: StageMode
    modeResult: Optional[ModeResult]


class MetricSource(MetricStage):
    type: Literal["MetricSource"] = "MetricSource"
    expression: Optional[Property] = None
    hasEndpoint: Relationship
    interval: Property
    name: Property


class MetricProcessor(MetricStage):
    type: Literal["MetricProcessor"] = "MetricProcessor"
    hasInput: Relationship
    hasApplication: Relationship
    name: Property
    arguments: Optional[Property] = None
    jobId: Property


class MetricTarget(MetricStage):
    type: Literal["MetricTarget"] = "MetricTarget"
    hasInput: Relationship
    uri: _URI


class StreamApplication(Entity):
    type: Literal["StreamApplication"] = "StreamApplication"
    fileName: Property
    fileId: Property
    entryClass: Property
    description: Property
    uri: _URI


class TelemetrySource(MetricStage):
    type: Literal["TelemetrySource"] = "TelemetrySource"
    arguments: Optional[Property] = None
    hasEndpoint: Relationship
    name: Property
    subscriptionMode: Property
    interval: Optional[Property] = None
