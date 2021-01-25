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
    uri: _URI

class MetricSource(Entity):
    type: Literal["MetricSource"] = "MetricSource"
    expression: Optional[Property] = None
    hasEndpoint: Relationship
    interval: Property
    name: Property
    TSDB: Property

class MetricTarget(Entity):
    type: Literal["MetricTarget"] = "MetricTarget"
    hasInput: Relationship
    uri: _URI

class MetricProcessor(Entity):
    type: Literal["MetricProcessor"] = "MetricProcessor"
    hasInput: Relationship
    hasApplication: Relationship
    name: Property
    arguments: Optional[Property] = None
    jobId: Property

class StreamApplication(Entity):
    type: Literal["StreamApplication"] = "StreamApplication"
    fileName: Property
    fileId: Property
    entryClass: Property
    description: Property
    uri: _URI
