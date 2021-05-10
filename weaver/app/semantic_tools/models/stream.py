from .ngsi_ld.entity import Entity, Property, Relationship
from .common import Agent
from typing import Literal, Optional


class NameFormat(Property):
    value: Literal["names", "pattern"]


class Offset(Property):
    value: Literal["earliest", "latest", "none"]


class EVESource(Agent):
    type: Literal["EVESource"] = "EVESource"
    collectsFrom: Relationship
    groupId: Property
    nameFormat: NameFormat
    offset: Offset
    topicName: Property


class KafkaBroker(Entity):
    type: Literal["KafkaBroker"] = "KafkaBroker"
    hasEndpoint: Relationship
    name: Property
    version: Optional[Property]
