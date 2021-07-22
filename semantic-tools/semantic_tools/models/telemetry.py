from semantic_tools.models.common import Asset, Infrastructure
from semantic_tools.models.ngsi_ld.entity import Property, Relationship
from typing import Literal, Optional


class Device(Infrastructure):
    type: Literal["Device"] = "Device"
    protocol: Property
    version: Optional[Property]
    encodings: Optional[Property]
    hasModule: Optional[Relationship]


class YANGModule(Asset):
    type: Literal["YANGModule"] = "YANGModule"
    org: Property
    revision: Property
    hasDevice: Relationship