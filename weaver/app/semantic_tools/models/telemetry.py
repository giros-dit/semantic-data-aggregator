from .ngsi_ld.entity import Entity, Property, Relationship
from .common import Agent
from typing import Literal, Optional


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
