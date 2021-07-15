from semantic_tools.models.ngsi_ld.entity import Entity, Property, Relationship
from pydantic import AnyUrl
from typing import Literal, Optional


class Action(Property):
    value: Literal["START", "STOP", "END"]


class State(Property):
    value: Literal["BUILDING", "FAILED", "RUNNING", "STOPPED", "CLEANED",
                   "UPLOADED", "ENABLED", "DISABLED"]
    stateInfo: Optional[Property]


class URI(Property):
    value: AnyUrl


# Inspiration from the Asset type proposed by Apache Atlas
# The 'owner' property is included but has no further meaning
# within the semantic data aggregator (until support for tenants)
# Define a 'tags' property for tag-based queries
class Asset(Entity):
    type: Literal["Asset"] = "Asset"
    name: Property
    description: Optional[Property]
    owner: Optional[Property]
    tags: Optional[Property]


# Entity that boosts Assets with state
# This entity is not only inherited by Task,
# but also by others such as Device or Prometheus
class StatefulAsset(Asset):
    type: Literal["StatefulAsset"] = "StatefulAsset"
    action: Action
    state: Optional[State]


class Credentials(Asset):
    type: Literal["Credentials"] = "Credentials"
    authMethod: Property


class Endpoint(StatefulAsset):
    type: Literal["Endpoint"] = "Endpoint"
    hasCredentials: Optional[Relationship] = None
    hasLogin: Optional[Relationship] = None
    # Do not enforce uri in order to support
    # use cases such as Kafka broker address
    # uri: URI
    uri: Property


# Inspiration from the Infrastructure type proposed by Apache Atlas
class Infrastructure(Asset):
    type: Literal["Infrastructure"] = "Infrastructure"
    hasEndpoint: Relationship
