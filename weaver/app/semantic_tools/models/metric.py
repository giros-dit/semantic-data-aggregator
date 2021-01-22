from .ngsi_ld.entity import Entity, Property, Relationship
from enum import Enum
from pydantic import AnyUrl
from typing import Literal, Optional


class _URI(Property):
    value: AnyUrl


class ModeResult(Enum):
    in_progress = "IN_PROGRESS"
    successful = "SUCCESSFUL"
    failed = "FAILED"


class StageMode(Enum):
    start = "START"
    stop = "STOP"
    failed = "TERMINATE"


class Credentials(Entity):
    type: Literal["Credentials"] = "Credentials"
    authMethod: Property


class Endpoint(Entity):
    type: Literal["Endpoint"] = "Endpoint"
    hasLogin: Optional[Relationship] = None
    name: Property
    uri: _URI


class ModeResultProperty(Property):
    modeInfo: Optional[Property]


class MetricStage(Entity):
    type: Literal["MetricStage"] = "MetricStage"
    stageMode: Property
    modeResult: ModeResultProperty


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
