from .ngsi_ld.entity import Entity, Property, Relationship
from pydantic import AnyUrl
from typing import Literal, Optional


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


class Credentials(Asset):
    type: Literal["Credentials"] = "Credentials"
    authMethod: Property


class Endpoint(Asset):
    type: Literal["Endpoint"] = "Endpoint"
    hasCredentials: Optional[Relationship] = None
    hasLogin: Optional[Relationship] = None
    # Do not enforce uri in order to support
    # use cases such as Kafka broker address
    # uri: URI
    uri: Property
