# generated by datamodel-codegen:
#   filename:  clarity_data_lake/datalake.json

from __future__ import annotations

from typing import Literal, Optional

from pydantic import AnyUrl, Extra, Field, StrictStr

from ..entity import Entity, Property


class ApiKey(Property):
    """
    Property. AWS ApiKey credentials.
    """

    class Config:
        extra = Extra.forbid
        allow_population_by_field_name = True

    value: StrictStr


class Region(Property):
    """
    Property. AWS region. Thus far, only supported region is 'eu-west-2'.
    """

    class Config:
        extra = Extra.forbid
        allow_population_by_field_name = True

    value: Literal['eu-west-2']


class Uri(Property):
    """
    Property. DataLake API Gateway URI.
    """

    class Config:
        extra = Extra.forbid
        allow_population_by_field_name = True

    value: AnyUrl


class DataLake(Entity):
    """
    Entity that represents a 5G-CLARITY's DataLake platform.
    """

    class Config:
        extra = Extra.forbid
        allow_population_by_field_name = True

    type: Optional[Literal['DataLake']] = Field(
        'DataLake',
        description='NGSI-LD Entity identifier. It has to be DataLake',
    )
    api_key: ApiKey = Field(..., alias='apiKey')
    region: Region
    uri: Uri
