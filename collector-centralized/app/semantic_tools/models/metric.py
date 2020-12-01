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


class MetricProcessor(Entity):
    type: Literal["MetricProcessor"] = "MetricProcessor"
    hasInput: Relationship
    interval: Property
    operation: Property
    window: Property


class MetricSource(Entity):
    type: Literal["MetricSource"] = "MetricSource"
    expression: Optional[Property] = None
    hasEndPoint: Relationship
    interval: Property
    name: Property


class MetricTarget(Entity):
    type: Literal["MetricTarget"] = "MetricTarget"
    hasInput: Relationship
    uri: _URI
