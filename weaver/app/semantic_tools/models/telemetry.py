from .ngsi_ld.entity import Property, Relationship
from .common import Asset
from typing import Literal, Optional


class Device(Asset):
    type: Literal["Device"] = "Device"
    hasEndpoint: Relationship
    protocol: Property
    version: Optional[Property]


class Module(Asset):
    type: Literal["Module"] = "Module"
    org: Property
    version: Property
