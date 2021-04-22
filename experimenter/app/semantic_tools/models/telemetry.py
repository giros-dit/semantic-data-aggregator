from .ngsi_ld.entity import Entity, Property, Relationship
from pydantic import AnyUrl
from typing import Literal, Optional


class _URI(Property):
    value: AnyUrl


class Credentials(Entity):
    type: Literal["Credentials"] = "Credentials"
    authMethod: Property


class Action(Property):
    value: Literal["START", "STOP", "END"]


class State(Property):
    value: Literal["BUILDING", "FAILED", "RUNNING", "STOPPED", "CLEANED", "UPLOADED", "ENABLED", "DISABLED"]
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


class Device(Agent):
    type: Literal["Device"] = "Device"
    hasEndpoint: Relationship
    name: Property
    protocol: Property
    version: Optional[Property]


class Module(Entity):
    type: Literal["Module"] = "Module"
    name: Property
    org: Property
    version: Property


class TelemetrySource(Agent):
    type: Literal["TelemetrySource"] = "TelemetrySource"
    collectsFrom: Relationship
    XPath: Property
    subscriptionMode: Property
    #interval: Optional[Property] = None
