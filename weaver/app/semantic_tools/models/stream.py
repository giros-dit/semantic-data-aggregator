from .ngsi_ld.entity import Entity, Property, Relationship
from .common import Agent
from typing import Literal, Optional


class Offset(Property):
    value: Literal["earliest", "latest", "none"]


class EVESource(Agent):
    type: Literal["EVESource"] = "EVESource"
    hasInput: Relationship
    hasOutput: Relationship
    groupId: Property
    offset: Offset
    topicName: Property


class KafkaBroker(Entity):
    type: Literal["KafkaBroker"] = "KafkaBroker"
    hasEndpoint: Relationship
    name: Property
    version: Optional[Property]


class KafkaTopic(Entity):
    type: Literal["KafkaTopic"] = "KafkaTopic"
    hasKafkaBroker: Relationship
    description: Optional[Property]
    name: Property
    schemaURL: Optional[Property]