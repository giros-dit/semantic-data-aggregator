from semantic_tools.models.common import Asset, Infrastructure
from semantic_tools.models.ngsi_ld.entity import Property, Relationship
from typing import Literal, Optional


class KafkaBroker(Infrastructure):
    type: Literal["KafkaBroker"] = "KafkaBroker"
    version: Optional[Property]


class KafkaTopic(Asset):
    type: Literal["KafkaTopic"] = "KafkaTopic"
    hasKafkaBroker: Relationship
    schemaURL: Optional[Property]
