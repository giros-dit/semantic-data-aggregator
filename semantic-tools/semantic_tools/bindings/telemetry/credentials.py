# generated by datamodel-codegen:
#   filename:  telemetry/credentials.json

from __future__ import annotations

from typing import List, Literal, Optional, Union

from pydantic import Extra, Field, StrictStr

from ..entity import Entity, Property, Relationship


class Authenticates(Relationship):
    """
    Relationship. Network management protocol that these credentials authenticate.
    """

    class Config:
        validate_assignment = True
        extra = Extra.forbid
        allow_population_by_field_name = True

    object: StrictStr


class Password(Property):
    """
    Property. Password of these credentials.
    """

    class Config:
        validate_assignment = True
        extra = Extra.forbid
        allow_population_by_field_name = True

    value: StrictStr


class Username(Property):
    """
    Property. Basic username of these credentials.
    """

    class Config:
        validate_assignment = True
        extra = Extra.forbid
        allow_population_by_field_name = True

    value: StrictStr


class Credentials(Entity):
    """
    Entity that represents a basic authentication credentials to access network management protocols of a specific network device
    """

    class Config:
        validate_assignment = True
        extra = Extra.forbid
        allow_population_by_field_name = True

    type: Optional[Literal['Credentials']] = Field(
        'Credentials',
        description='NGSI-LD Entity identifier. It has to be Credentials',
    )
    authenticates: Union[List[Authenticates], Authenticates]
    password: Password
    username: Username