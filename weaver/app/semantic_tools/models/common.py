from .ngsi_ld.entity import Entity, Property, Relationship
from pydantic import AnyUrl
from typing import Literal, Optional


class URI(Property):
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
    # Do not enforce uri in order to support 
    # use cases such as Kafka broker address
    # uri: URI
    uri: Property
