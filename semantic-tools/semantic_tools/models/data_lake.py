from typing import Literal

from semantic_tools.models.ngsi_ld.entity import Entity, Property, Relationship


class Bucket(Entity):
    type: Literal["Bucket"] = "Bucket"
    creationDate: Property
    name: Property
    belongsTo: Relationship
    ownedBy: Relationship


class DataLake(Entity):
    type: Literal["DataLake"] = "DataLake"
    region: Property
    uri: Property


class Object(Entity):
    type: Literal["Object"] = "Object"
    eTag: Property
    key: Property
    lastModified: Property
    size: Property
    storageClass: Property
    containedIn: Relationship
    ownedBy: Relationship


class Owner(Entity):
    type: Literal["Owner"] = "Owner"
    ownerId: Property
    memberOf: Relationship
