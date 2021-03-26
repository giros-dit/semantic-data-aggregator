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


class Device(Entity):
    type: Literal["Device"] = "Device"
    hasEndpoint: Relationship
    name: Property
    protocol: Property


class Action(Property):
    value: Literal["START", "STOP", "END"]


class State(Property):
    value: Literal["BUILDING", "RUNNING", "STOPPED", "CLEANED", "FAILED", "UPLOADED"]
    stateInfo: Optional[Property]


class Agent(Entity):
    type: Literal["Agent"] = "Agent"
    action: Action
    state: Optional[State]


class TelemetrySource(Agent):
    type: Literal["TelemetrySource"] = "TelemetrySource"
    collectsFrom: Relationship
    XPath: Property
    subscriptionMode: Property
    #interval: Optional[Property] = None
