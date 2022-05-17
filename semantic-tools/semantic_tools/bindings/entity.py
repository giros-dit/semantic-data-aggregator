# generated by datamodel-codegen:
#   filename:  entity.json

from __future__ import annotations

from datetime import datetime
from enum import Enum
from typing import Any, Dict, List, Literal, Optional, Union

from pydantic import (AnyUrl, BaseModel, Extra, Field, StrictBool, StrictFloat,
                      StrictStr)


class DateTime(BaseModel):
    class Config:
        allow_population_by_field_name = True

    type: Optional[Literal['DateTime']] = Field('DateTime', alias='@type')
    value: datetime = Field(..., alias='@value')


class DatasetId(BaseModel):
    class Config:
        allow_population_by_field_name = True

    __root__: StrictStr


class InstanceId(BaseModel):
    class Config:
        allow_population_by_field_name = True

    __root__: StrictStr


class ObservedAt(BaseModel):
    class Config:
        allow_population_by_field_name = True

    __root__: datetime


class Name(BaseModel):
    class Config:
        extra = Extra.forbid
        allow_population_by_field_name = True

    __root__: StrictStr = Field(..., description='NGSI-LD Name')


class PropertyNames(BaseModel):
    class Config:
        allow_population_by_field_name = True

    __root__: StrictStr


class CreatedAt(BaseModel):
    class Config:
        allow_population_by_field_name = True

    __root__: datetime


class ModifiedAt(BaseModel):
    class Config:
        allow_population_by_field_name = True

    __root__: datetime


class LdContextItem(BaseModel):
    class Config:
        allow_population_by_field_name = True

    __root__: List[Any] = Field(..., min_items=1)


class LdContext(BaseModel):
    class Config:
        allow_population_by_field_name = True

    __root__: Union[Dict[str, Any], AnyUrl, LdContextItem]


class Accept(Enum):
    application_json = 'application/json'
    application_ld_json = 'application/ld+json'


class Endpoint(BaseModel):
    class Config:
        allow_population_by_field_name = True

    uri: AnyUrl
    accept: Optional[Accept]


class EntityInfo(BaseModel):
    class Config:
        allow_population_by_field_name = True

    id: Optional[AnyUrl]
    type: Name
    id_pattern: Optional[StrictStr] = Field(None, alias='idPattern')


class ProblemDetails(BaseModel):
    class Config:
        allow_population_by_field_name = True

    type: AnyUrl
    title: Optional[StrictStr]
    detail: Optional[StrictStr]


class GeorelEnum(Enum):
    equals = 'equals'
    disjoint = 'disjoint'
    intersects = 'intersects'
    within = 'within'
    contains = 'contains'
    overlaps = 'overlaps'


class GeorelItem(BaseModel):
    class Config:
        allow_population_by_field_name = True

    __root__: StrictStr


class Georel(BaseModel):
    class Config:
        allow_population_by_field_name = True

    __root__: Union[GeorelEnum, GeorelItem]


class Position(BaseModel):
    """
    A single position
    """

    class Config:
        allow_population_by_field_name = True

    __root__: List[StrictFloat] = Field(
        ..., description='A single position', max_items=2, min_items=2
    )


class PositionArray(BaseModel):
    """
    An array of positions
    """

    class Config:
        allow_population_by_field_name = True

    __root__: List[Position] = Field(..., description='An array of positions')


class LineString(BaseModel):
    """
    An array of two or more positions
    """

    pass

    class Config:
        allow_population_by_field_name = True


class LinearRing(BaseModel):
    """
    An array of four positions where the first equals the last
    """

    pass

    class Config:
        allow_population_by_field_name = True


class Polygon(BaseModel):
    """
    An array of linear rings
    """

    class Config:
        allow_population_by_field_name = True

    __root__: List[LinearRing] = Field(
        ..., description='An array of linear rings'
    )


class Point(BaseModel):
    class Config:
        allow_population_by_field_name = True

    type: Optional[Literal['Point']]
    coordinates: Optional[Position]


class MultiPoint(BaseModel):
    class Config:
        allow_population_by_field_name = True

    type: Optional[Literal['MultiPoint']]
    coordinates: Optional[PositionArray]


class Polygon1(BaseModel):
    class Config:
        allow_population_by_field_name = True

    type: Optional[Literal['Polygon']]
    coordinates: Optional[Polygon]


class LineString1(BaseModel):
    class Config:
        allow_population_by_field_name = True

    type: Optional[Literal['LineString']]
    coordinates: Optional[LineString]


class MultiLineString(BaseModel):
    class Config:
        allow_population_by_field_name = True

    type: Optional[Literal['MultiLineString']]
    coordinates: Optional[List[LineString]]


class MultiPolygon(BaseModel):
    class Config:
        allow_population_by_field_name = True

    type: Optional[Literal['MultiPolygon']]
    coordinates: Optional[List[Polygon]]


class Geometry(BaseModel):
    class Config:
        allow_population_by_field_name = True

    __root__: Union[
        Point, MultiPoint, Polygon1, LineString1, MultiLineString, MultiPolygon
    ] = Field(..., description=' Avalid GeoJSON geometry object')


class Geometry1(Enum):
    point = 'Point'
    multi_point = 'MultiPoint'
    line_string = 'LineString'
    multi_line_string = 'MultiLineString'
    polygon = 'Polygon'
    multi_polygon = 'MultiPolygon'


class Timerel(Enum):
    before = 'before'
    after = 'after'
    between = 'between'


class Relationship(BaseModel):
    class Config:
        extra = Extra.allow
        allow_population_by_field_name = True

    type: Optional[Literal['Relationship']] = 'Relationship'
    object: StrictStr
    observed_at: Optional[ObservedAt] = Field(None, alias='observedAt')
    created_at: Optional[CreatedAt] = Field(None, alias='createdAt')
    modified_at: Optional[ModifiedAt] = Field(None, alias='modifiedAt')
    dataset_id: Optional[DatasetId] = Field(None, alias='datasetId')
    instance_id: Optional[InstanceId] = Field(None, alias='instanceId')


class GeoProperty(BaseModel):
    class Config:
        extra = Extra.allow
        allow_population_by_field_name = True

    type: Optional[Literal['GeoProperty']] = 'GeoProperty'
    value: Geometry
    observed_at: Optional[ObservedAt] = Field(None, alias='observedAt')
    created_at: Optional[CreatedAt] = Field(None, alias='createdAt')
    modified_at: Optional[ModifiedAt] = Field(None, alias='modifiedAt')
    dataset_id: Optional[DatasetId] = Field(None, alias='datasetId')
    instance_id: Optional[InstanceId] = Field(None, alias='instanceId')


class EntityFragment(BaseModel):
    class Config:
        extra = Extra.allow
        allow_population_by_field_name = True

    _context: Optional[LdContext] = Field(None, alias='@context')
    location: Optional[GeoProperty]
    observation_space: Optional[GeoProperty] = Field(
        None, alias='observationSpace'
    )
    operation_space: Optional[GeoProperty] = Field(None, alias='operationSpace')
    id: Optional[StrictStr]
    type: Optional[Name]
    created_at: Optional[CreatedAt] = Field(None, alias='createdAt')
    modified_at: Optional[ModifiedAt] = Field(None, alias='modifiedAt')


class Entity(EntityFragment):
    pass

    class Config:
        allow_population_by_field_name = True


class NgsiLdEntity(Entity):
    """
    NGSI-LD Entity
    """

    pass

    class Config:
        allow_population_by_field_name = True


class Property(BaseModel):
    class Config:
        extra = Extra.allow
        allow_population_by_field_name = True

    type: Optional[Literal['Property']] = 'Property'
    value: Union[StrictStr, StrictFloat, StrictBool, List, Dict[str, Any]]
    observed_at: Optional[ObservedAt] = Field(None, alias='observedAt')
    created_at: Optional[CreatedAt] = Field(None, alias='createdAt')
    modified_at: Optional[ModifiedAt] = Field(None, alias='modifiedAt')
    dataset_id: Optional[DatasetId] = Field(None, alias='datasetId')
    instance_id: Optional[InstanceId] = Field(None, alias='instanceId')


class Coordinates(BaseModel):
    class Config:
        allow_population_by_field_name = True

    __root__: Union[Position, PositionArray, LineString, Polygon]
