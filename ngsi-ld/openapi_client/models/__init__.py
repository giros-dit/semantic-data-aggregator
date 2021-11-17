# flake8: noqa

# import all models into this package
# if you have many models here with many references from one model to another this may
# raise a RecursionError
# to avoid this, import only the models that you directly need like:
# from from openapi_client.model.pet import Pet
# or import this package, but before doing it, use:
# import sys
# sys.setrecursionlimit(n)

from openapi_client.model.batch_entity_error import BatchEntityError
from openapi_client.model.batch_operation_result import BatchOperationResult
from openapi_client.model.context_source_registration import ContextSourceRegistration
from openapi_client.model.context_source_registration_all_of import ContextSourceRegistrationAllOf
from openapi_client.model.context_source_registration_fragment import ContextSourceRegistrationFragment
from openapi_client.model.context_source_registration_list import ContextSourceRegistrationList
from openapi_client.model.coordinates import Coordinates
from openapi_client.model.endpoint import Endpoint
from openapi_client.model.entity import Entity
from openapi_client.model.entity_fragment import EntityFragment
from openapi_client.model.entity_info import EntityInfo
from openapi_client.model.entity_list import EntityList
from openapi_client.model.entity_temporal import EntityTemporal
from openapi_client.model.entity_temporal_fragment import EntityTemporalFragment
from openapi_client.model.entity_temporal_list import EntityTemporalList
from openapi_client.model.geo_property import GeoProperty
from openapi_client.model.geo_query import GeoQuery
from openapi_client.model.geometry import Geometry
from openapi_client.model.georel import Georel
from openapi_client.model.georel_one_of import GeorelOneOf
from openapi_client.model.ld_context import LdContext
from openapi_client.model.line_string import LineString
from openapi_client.model.linear_ring import LinearRing
from openapi_client.model.model_property import ModelProperty
from openapi_client.model.multi_line_string import MultiLineString
from openapi_client.model.multi_point import MultiPoint
from openapi_client.model.multi_polygon import MultiPolygon
from openapi_client.model.name import Name
from openapi_client.model.not_updated_details import NotUpdatedDetails
from openapi_client.model.notification_params import NotificationParams
from openapi_client.model.point import Point
from openapi_client.model.polygon import Polygon
from openapi_client.model.position import Position
from openapi_client.model.position_array import PositionArray
from openapi_client.model.problem_details import ProblemDetails
from openapi_client.model.registration_info import RegistrationInfo
from openapi_client.model.relationship import Relationship
from openapi_client.model.subscription import Subscription
from openapi_client.model.subscription_all_of import SubscriptionAllOf
from openapi_client.model.subscription_fragment import SubscriptionFragment
from openapi_client.model.subscription_list import SubscriptionList
from openapi_client.model.time_interval import TimeInterval
from openapi_client.model.timerel import Timerel
from openapi_client.model.update_result import UpdateResult
