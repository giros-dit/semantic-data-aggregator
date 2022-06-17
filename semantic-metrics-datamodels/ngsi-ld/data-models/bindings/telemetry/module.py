# generated by datamodel-codegen:
#   filename:  telemetry/module.json

from __future__ import annotations

from typing import List, Literal, Optional, Union

from pydantic import Extra, Field, StrictStr

from ..entity import Entity, Property, Relationship


class Deviation(Property):
    """
    Property. Name of YANG module that deviates this YANG module.
    """

    class Config:
        validate_assignment = True
        extra = Extra.forbid
        allow_population_by_field_name = True

    value: StrictStr


class Feature(Property):
    """
    Property. Name of feature that the target network device implements for this YANG module.
    """

    class Config:
        validate_assignment = True
        extra = Extra.forbid
        allow_population_by_field_name = True

    value: StrictStr


class Name(Property):
    """
    Property. Name of the YANG module.
    """

    class Config:
        validate_assignment = True

    value: StrictStr


class Namespace(Property):
    """
    Property. Namespace of the YANG module.
    """

    class Config:
        validate_assignment = True
        extra = Extra.forbid
        allow_population_by_field_name = True

    value: StrictStr


class Revision(Property):
    """
    Property. Revision date of the YANG module.
    """

    class Config:
        validate_assignment = True
        extra = Extra.forbid
        allow_population_by_field_name = True

    value: StrictStr


class ImplementedBy(Relationship):
    """
    Relationship. Network device that implements this YANG module.
    """

    class Config:
        validate_assignment = True
        extra = Extra.forbid
        allow_population_by_field_name = True

    object: StrictStr
    deviation: Optional[Deviation]
    feature: Optional[Feature]


class Module(Entity):
    """
    Entity that represents an implementation of a YANG module
    """

    class Config:
        validate_assignment = True
        extra = Extra.forbid
        allow_population_by_field_name = True

    type: Optional[Literal['Module']] = Field(
        'Module', description='NGSI-LD Entity identifier. It has to be Module'
    )
    implemented_by: Union[List[ImplementedBy], ImplementedBy] = Field(
        ..., alias='implementedBy'
    )
    name: Name
    namespace: Namespace
    revision: Revision