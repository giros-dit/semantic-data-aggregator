from .ngsi_ld.entity import Property, Relationship
from .common import Asset
from typing import Literal, Optional


class KafkaBroker(Asset):
    type: Literal["KafkaBroker"] = "KafkaBroker"
    hasEndpoint: Relationship
    version: Optional[Property]


class KafkaTopic(Asset):
    type: Literal["KafkaTopic"] = "KafkaTopic"
    hasKafkaBroker: Relationship
    schemaURL: Optional[Property]
