from .ngsi_ld.entity import Entity, Property, Relationship
from pydantic import AnyUrl
from typing import Literal, Optional


class _URI(Property):
    value: AnyUrl


class Credentials(Entity):
    type: Literal["Credentials"] = "Credentials"
    authMethod: Property

"""
class Endpoint(Entity):
    type: Literal["Endpoint"] = "Endpoint"
    hasLogin: Optional[Relationship] = None
    name: Property
    uri: _URI
"""
"""
class Prometheus(Entity):
    type: Literal["Prometheus"] = "Prometheus"
    hasEndpoint: Relationship
    name: Property
    version: Property
"""

class Action(Property):
    value: Literal["START", "STOP", "END"]


class State(Property):
    value: Literal["BUILDING", "RUNNING", "STOPPED", "CLEANED", "FAILED", "UPLOADED"]
    stateInfo: Optional[Property]


class Agent(Entity):
    type: Literal["Agent"] = "Agent"
    action: Action
    state: Optional[State]


class Endpoint(Agent):
    type: Literal["Endpoint"] = "Endpoint"
    hasLogin: Optional[Relationship] = None
    name: Property
    uri: _URI


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
    uri: _URI


class StreamApplication(Agent):
    type: Literal["StreamApplication"] = "StreamApplication"
    fileName: Property
    fileId: Optional[Property]
    entryClass: Optional[Property]
    description: Property
    uri: _URI
