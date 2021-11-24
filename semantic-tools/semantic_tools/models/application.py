from semantic_tools.models.common import Asset, StatefulAsset, URI
from semantic_tools.models.ngsi_ld.entity import Property, Relationship
from typing import Literal, Optional


class ApplicationType(Property):
    value: Literal["FLINK", "NIFI"]


class Application(Asset):
    type: Literal["Application"] = "Application"
    internalId: Property
    applicationType: ApplicationType
    uri: URI


class Task(StatefulAsset):
    type: Literal["Task"] = "Task"
    arguments: Optional[Property]
    internalId: Optional[Property]
    hasApplication: Relationship
    hasInput: Relationship
    hasOutput: Optional[Relationship]
