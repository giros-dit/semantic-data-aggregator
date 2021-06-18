from .common import URI
from .ngsi_ld.entity import Asset, Property, Relationship
from typing import Literal, Optional


class Action(Property):
    value: Literal["START", "STOP", "END"]


class ApplicationType(Property):
    value: Literal["FLINK", "NIFI"]


class State(Property):
    value: Literal["BUILDING", "FAILED", "RUNNING", "STOPPED", "CLEANED",
                   "UPLOADED", "ENABLED", "DISABLED"]
    stateInfo: Optional[Property]


class Application(Asset):
    type: Literal["Application"] = "Application"
    applicationId: Property
    applicationType: ApplicationType
    uri: URI


class Task(Asset):
    type: Literal["Task"] = "Task"
    action: Action
    arguments: Optional[Property]
    hasApplication: Relationship
    hasInput: Relationship
    hasOutput: Relationship
    state: State
    taskId: Property
