# openapi_client.TemporalApi

All URIs are relative to *http://localhost*

Method | HTTP request | Description
------------- | ------------- | -------------
[**add_temporal_entity_attrs**](TemporalApi.md#add_temporal_entity_attrs) | **POST** /temporal/entities/{entityId}/attrs/ | 
[**create_update_entity_temporal**](TemporalApi.md#create_update_entity_temporal) | **POST** /temporal/entities/ | 
[**modify_entity_temporal_attr_instance**](TemporalApi.md#modify_entity_temporal_attr_instance) | **PATCH** /temporal/entities/{entityId}/attrs/{attrId}/{instanceId} | 
[**query_temporal_entities**](TemporalApi.md#query_temporal_entities) | **GET** /temporal/entities/ | 
[**remove_entity_temporal_attr**](TemporalApi.md#remove_entity_temporal_attr) | **DELETE** /temporal/entities/{entityId}/attrs/{attrId} | 
[**remove_entity_temporal_attr_instance**](TemporalApi.md#remove_entity_temporal_attr_instance) | **DELETE** /temporal/entities/{entityId}/attrs/{attrId}/{instanceId} | 
[**remove_entity_temporal_by_id**](TemporalApi.md#remove_entity_temporal_by_id) | **DELETE** /temporal/entities/{entityId} | 
[**retrieve_entity_temporal_by_id**](TemporalApi.md#retrieve_entity_temporal_by_id) | **GET** /temporal/entities/{entityId} | 


# **add_temporal_entity_attrs**
> add_temporal_entity_attrs(entity_id, entity_temporal_fragment)



Add new attributes to an existing Temporal Entity within an NGSI-LD system

### Example


```python
import time
import openapi_client
from openapi_client.api import temporal_api
from openapi_client.model.problem_details import ProblemDetails
from openapi_client.model.entity_temporal_fragment import EntityTemporalFragment
from pprint import pprint
# Defining the host is optional and defaults to http://localhost
# See configuration.py for a list of all supported configuration parameters.
configuration = openapi_client.Configuration(
    host = "http://localhost"
)


# Enter a context with an instance of the API client
with openapi_client.ApiClient() as api_client:
    # Create an instance of the API class
    api_instance = temporal_api.TemporalApi(api_client)
    entity_id = "entityId_example" # str | Entity Id
    entity_temporal_fragment = EntityTemporalFragment(
        key=None,
    ) # EntityTemporalFragment | 

    # example passing only required values which don't have defaults set
    try:
        api_instance.add_temporal_entity_attrs(entity_id, entity_temporal_fragment)
    except openapi_client.ApiException as e:
        print("Exception when calling TemporalApi->add_temporal_entity_attrs: %s\n" % e)
```


### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **entity_id** | **str**| Entity Id |
 **entity_temporal_fragment** | [**EntityTemporalFragment**](EntityTemporalFragment.md)|  |

### Return type

void (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json;application/ld+json
 - **Accept**: application/json;application/ld+json


### HTTP response details

| Status code | Description | Response headers |
|-------------|-------------|------------------|
**204** | No Content |  -  |
**400** | Bad request |  -  |
**404** | Not Found |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **create_update_entity_temporal**
> create_update_entity_temporal(entity_temporal)



Create or update temporal representation of an Entity within an NGSI-LD system

### Example


```python
import time
import openapi_client
from openapi_client.api import temporal_api
from openapi_client.model.problem_details import ProblemDetails
from openapi_client.model.entity_temporal import EntityTemporal
from pprint import pprint
# Defining the host is optional and defaults to http://localhost
# See configuration.py for a list of all supported configuration parameters.
configuration = openapi_client.Configuration(
    host = "http://localhost"
)


# Enter a context with an instance of the API client
with openapi_client.ApiClient() as api_client:
    # Create an instance of the API class
    api_instance = temporal_api.TemporalApi(api_client)
    entity_temporal = EntityTemporal(None) # EntityTemporal | 

    # example passing only required values which don't have defaults set
    try:
        api_instance.create_update_entity_temporal(entity_temporal)
    except openapi_client.ApiException as e:
        print("Exception when calling TemporalApi->create_update_entity_temporal: %s\n" % e)
```


### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **entity_temporal** | [**EntityTemporal**](EntityTemporal.md)|  |

### Return type

void (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json;application/ld+json
 - **Accept**: application/json;application/ld+json


### HTTP response details

| Status code | Description | Response headers |
|-------------|-------------|------------------|
**201** | Created. Contains the resource URI of the created Entity |  -  |
**204** | Updated. No Content |  -  |
**400** | Bad request |  -  |
**409** | Already exists |  -  |
**422** | Unprocessable Entity |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **modify_entity_temporal_attr_instance**
> modify_entity_temporal_attr_instance(entity_id, attr_id, instance_id, entity_temporal_fragment)



Allows modifying a specific Attribute (Property or Relationship) instance, identified by its instanceId, of a Temporal Representation of an Entity.

### Example


```python
import time
import openapi_client
from openapi_client.api import temporal_api
from openapi_client.model.problem_details import ProblemDetails
from openapi_client.model.entity_temporal_fragment import EntityTemporalFragment
from openapi_client.model.name import Name
from pprint import pprint
# Defining the host is optional and defaults to http://localhost
# See configuration.py for a list of all supported configuration parameters.
configuration = openapi_client.Configuration(
    host = "http://localhost"
)


# Enter a context with an instance of the API client
with openapi_client.ApiClient() as api_client:
    # Create an instance of the API class
    api_instance = temporal_api.TemporalApi(api_client)
    entity_id = "entityId_example" # str | Entity Id
    attr_id = Name("_") # Name | Attribute Id
    instance_id = "instanceId_example" # str | Instance Id
    entity_temporal_fragment = EntityTemporalFragment(
        key=None,
    ) # EntityTemporalFragment | 

    # example passing only required values which don't have defaults set
    try:
        api_instance.modify_entity_temporal_attr_instance(entity_id, attr_id, instance_id, entity_temporal_fragment)
    except openapi_client.ApiException as e:
        print("Exception when calling TemporalApi->modify_entity_temporal_attr_instance: %s\n" % e)
```


### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **entity_id** | **str**| Entity Id |
 **attr_id** | **Name**| Attribute Id |
 **instance_id** | **str**| Instance Id |
 **entity_temporal_fragment** | [**EntityTemporalFragment**](EntityTemporalFragment.md)|  |

### Return type

void (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json;application/ld+json
 - **Accept**: application/json;application/ld+json


### HTTP response details

| Status code | Description | Response headers |
|-------------|-------------|------------------|
**204** | No Content |  -  |
**400** | Bad request |  -  |
**404** | Not Found |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **query_temporal_entities**
> EntityTemporalList query_temporal_entities()



Query temporal evolution of Entities from an NGSI-LD system

### Example


```python
import time
import openapi_client
from openapi_client.api import temporal_api
from openapi_client.model.geometry import Geometry
from openapi_client.model.coordinates import Coordinates
from openapi_client.model.problem_details import ProblemDetails
from openapi_client.model.entity_temporal_list import EntityTemporalList
from openapi_client.model.timerel import Timerel
from openapi_client.model.georel import Georel
from openapi_client.model.name import Name
from pprint import pprint
# Defining the host is optional and defaults to http://localhost
# See configuration.py for a list of all supported configuration parameters.
configuration = openapi_client.Configuration(
    host = "http://localhost"
)


# Enter a context with an instance of the API client
with openapi_client.ApiClient() as api_client:
    # Create an instance of the API class
    api_instance = temporal_api.TemporalApi(api_client)
    id = "id_example" # str | Comma separated list of URIs to be retrieved (optional)
    id_pattern = "idPattern_example" # str | Regular expression that must be matched by Entity ids (optional)
    type = "type_example" # str | Comma separated list of Entity type names to be retrieved (optional)
    attrs = "attrs_example" # str | Comma separated list of attribute names (properties or relationships) to be retrieved (optional)
    q = "q_example" # str | Query (optional)
    georel = Georel(None) # Georel | Geo-relationship (optional)
    geometry = Geometry("Point") # Geometry | Geometry (optional)
    coordinates = Coordinates(None) # Coordinates | Coordinates serialized as a string (optional)
    geoproperty = "geoproperty_example" # str | The name of the property that contains the geo-spatial data that will be used to resolve the geoquery (optional)
    timerel = Timerel("before") # Timerel | Time relationship (optional)
    timeproperty = Name("_") # Name | The name of the property that contains the temporal data that will be used to resolve the temporal query (optional)
    time = dateutil_parser('1970-01-01T00:00:00.00Z') # datetime | start time for temporal query (optional)
    end_time = dateutil_parser('1970-01-01T00:00:00.00Z') # datetime | end time for temporal query (optional)
    csf = "csf_example" # str | Context Source Filter (optional)
    limit = 1 # int | Pagination limit (optional)
    options = "temporalValues" # str | Options dictionary (optional)
    last_n = 1 # int | Only retrieve last N instances (optional)

    # example passing only required values which don't have defaults set
    # and optional values
    try:
        api_response = api_instance.query_temporal_entities(id=id, id_pattern=id_pattern, type=type, attrs=attrs, q=q, georel=georel, geometry=geometry, coordinates=coordinates, geoproperty=geoproperty, timerel=timerel, timeproperty=timeproperty, time=time, end_time=end_time, csf=csf, limit=limit, options=options, last_n=last_n)
        pprint(api_response)
    except openapi_client.ApiException as e:
        print("Exception when calling TemporalApi->query_temporal_entities: %s\n" % e)
```


### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **str**| Comma separated list of URIs to be retrieved | [optional]
 **id_pattern** | **str**| Regular expression that must be matched by Entity ids | [optional]
 **type** | **str**| Comma separated list of Entity type names to be retrieved | [optional]
 **attrs** | **str**| Comma separated list of attribute names (properties or relationships) to be retrieved | [optional]
 **q** | **str**| Query | [optional]
 **georel** | **Georel**| Geo-relationship | [optional]
 **geometry** | **Geometry**| Geometry | [optional]
 **coordinates** | **Coordinates**| Coordinates serialized as a string | [optional]
 **geoproperty** | **str**| The name of the property that contains the geo-spatial data that will be used to resolve the geoquery | [optional]
 **timerel** | **Timerel**| Time relationship | [optional]
 **timeproperty** | **Name**| The name of the property that contains the temporal data that will be used to resolve the temporal query | [optional]
 **time** | **datetime**| start time for temporal query | [optional]
 **end_time** | **datetime**| end time for temporal query | [optional]
 **csf** | **str**| Context Source Filter | [optional]
 **limit** | **int**| Pagination limit | [optional]
 **options** | **str**| Options dictionary | [optional]
 **last_n** | **int**| Only retrieve last N instances | [optional]

### Return type

[**EntityTemporalList**](EntityTemporalList.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json;application/ld+json


### HTTP response details

| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK |  -  |
**400** | Bad request |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **remove_entity_temporal_attr**
> remove_entity_temporal_attr(entity_id, attr_id)



Attribute from Temporal Representation of Entity deletion

### Example


```python
import time
import openapi_client
from openapi_client.api import temporal_api
from openapi_client.model.problem_details import ProblemDetails
from openapi_client.model.name import Name
from pprint import pprint
# Defining the host is optional and defaults to http://localhost
# See configuration.py for a list of all supported configuration parameters.
configuration = openapi_client.Configuration(
    host = "http://localhost"
)


# Enter a context with an instance of the API client
with openapi_client.ApiClient() as api_client:
    # Create an instance of the API class
    api_instance = temporal_api.TemporalApi(api_client)
    entity_id = "entityId_example" # str | Entity Id
    attr_id = Name("_") # Name | Attribute Id

    # example passing only required values which don't have defaults set
    try:
        api_instance.remove_entity_temporal_attr(entity_id, attr_id)
    except openapi_client.ApiException as e:
        print("Exception when calling TemporalApi->remove_entity_temporal_attr: %s\n" % e)
```


### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **entity_id** | **str**| Entity Id |
 **attr_id** | **Name**| Attribute Id |

### Return type

void (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json;application/ld+json


### HTTP response details

| Status code | Description | Response headers |
|-------------|-------------|------------------|
**204** | No Content. |  -  |
**400** | Bad Request |  -  |
**404** | Not Found |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **remove_entity_temporal_attr_instance**
> remove_entity_temporal_attr_instance(entity_id, attr_id, instance_id)



Attribute Instance deletion by instance id.

### Example


```python
import time
import openapi_client
from openapi_client.api import temporal_api
from openapi_client.model.problem_details import ProblemDetails
from openapi_client.model.name import Name
from pprint import pprint
# Defining the host is optional and defaults to http://localhost
# See configuration.py for a list of all supported configuration parameters.
configuration = openapi_client.Configuration(
    host = "http://localhost"
)


# Enter a context with an instance of the API client
with openapi_client.ApiClient() as api_client:
    # Create an instance of the API class
    api_instance = temporal_api.TemporalApi(api_client)
    entity_id = "entityId_example" # str | Entity Id
    attr_id = Name("_") # Name | Attribute Id
    instance_id = "instanceId_example" # str | Instance Id

    # example passing only required values which don't have defaults set
    try:
        api_instance.remove_entity_temporal_attr_instance(entity_id, attr_id, instance_id)
    except openapi_client.ApiException as e:
        print("Exception when calling TemporalApi->remove_entity_temporal_attr_instance: %s\n" % e)
```


### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **entity_id** | **str**| Entity Id |
 **attr_id** | **Name**| Attribute Id |
 **instance_id** | **str**| Instance Id |

### Return type

void (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json;application/ld+json


### HTTP response details

| Status code | Description | Response headers |
|-------------|-------------|------------------|
**204** | No Content. |  -  |
**400** | Bad Request |  -  |
**404** | Not Found |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **remove_entity_temporal_by_id**
> remove_entity_temporal_by_id(entity_id)



Removes the temporal representation of an Entity from an NGSI-LD system

### Example


```python
import time
import openapi_client
from openapi_client.api import temporal_api
from openapi_client.model.problem_details import ProblemDetails
from openapi_client.model.name import Name
from pprint import pprint
# Defining the host is optional and defaults to http://localhost
# See configuration.py for a list of all supported configuration parameters.
configuration = openapi_client.Configuration(
    host = "http://localhost"
)


# Enter a context with an instance of the API client
with openapi_client.ApiClient() as api_client:
    # Create an instance of the API class
    api_instance = temporal_api.TemporalApi(api_client)
    entity_id = "entityId_example" # str | Entity Id
    type = Name("_") # Name | Entity Type (optional)

    # example passing only required values which don't have defaults set
    try:
        api_instance.remove_entity_temporal_by_id(entity_id)
    except openapi_client.ApiException as e:
        print("Exception when calling TemporalApi->remove_entity_temporal_by_id: %s\n" % e)

    # example passing only required values which don't have defaults set
    # and optional values
    try:
        api_instance.remove_entity_temporal_by_id(entity_id, type=type)
    except openapi_client.ApiException as e:
        print("Exception when calling TemporalApi->remove_entity_temporal_by_id: %s\n" % e)
```


### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **entity_id** | **str**| Entity Id |
 **type** | **Name**| Entity Type | [optional]

### Return type

void (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json;application/ld+json


### HTTP response details

| Status code | Description | Response headers |
|-------------|-------------|------------------|
**204** | No Content. The entity was removed successfully |  -  |
**400** | Bad Request |  -  |
**404** | Not Found |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **retrieve_entity_temporal_by_id**
> EntityTemporal retrieve_entity_temporal_by_id(entity_id)



Retrieve the temporal representation of an specific Entity from an NGSI-LD system. It's possible to specify the Entity attributes to be retrieved by using query parameters

### Example


```python
import time
import openapi_client
from openapi_client.api import temporal_api
from openapi_client.model.problem_details import ProblemDetails
from openapi_client.model.entity_temporal import EntityTemporal
from openapi_client.model.timerel import Timerel
from openapi_client.model.name import Name
from pprint import pprint
# Defining the host is optional and defaults to http://localhost
# See configuration.py for a list of all supported configuration parameters.
configuration = openapi_client.Configuration(
    host = "http://localhost"
)


# Enter a context with an instance of the API client
with openapi_client.ApiClient() as api_client:
    # Create an instance of the API class
    api_instance = temporal_api.TemporalApi(api_client)
    entity_id = "entityId_example" # str | Entity Id
    attrs = "attrs_example" # str | Comma separated list of attribute names (properties or relationships) to be retrieved (optional)
    type = Name("_") # Name | Entity Type (optional)
    options = "temporalValues" # str | Options dictionary (optional)
    timerel = Timerel("before") # Timerel | Time relationship (optional)
    timeproperty = Name("_") # Name | The name of the property that contains the temporal data that will be used to resolve the temporal query (optional)
    time = dateutil_parser('1970-01-01T00:00:00.00Z') # datetime | start time for temporal query (optional)
    end_time = dateutil_parser('1970-01-01T00:00:00.00Z') # datetime | end time for temporal query (optional)
    last_n = 1 # int | Only retrieve last N instances (optional)

    # example passing only required values which don't have defaults set
    try:
        api_response = api_instance.retrieve_entity_temporal_by_id(entity_id)
        pprint(api_response)
    except openapi_client.ApiException as e:
        print("Exception when calling TemporalApi->retrieve_entity_temporal_by_id: %s\n" % e)

    # example passing only required values which don't have defaults set
    # and optional values
    try:
        api_response = api_instance.retrieve_entity_temporal_by_id(entity_id, attrs=attrs, type=type, options=options, timerel=timerel, timeproperty=timeproperty, time=time, end_time=end_time, last_n=last_n)
        pprint(api_response)
    except openapi_client.ApiException as e:
        print("Exception when calling TemporalApi->retrieve_entity_temporal_by_id: %s\n" % e)
```


### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **entity_id** | **str**| Entity Id |
 **attrs** | **str**| Comma separated list of attribute names (properties or relationships) to be retrieved | [optional]
 **type** | **Name**| Entity Type | [optional]
 **options** | **str**| Options dictionary | [optional]
 **timerel** | **Timerel**| Time relationship | [optional]
 **timeproperty** | **Name**| The name of the property that contains the temporal data that will be used to resolve the temporal query | [optional]
 **time** | **datetime**| start time for temporal query | [optional]
 **end_time** | **datetime**| end time for temporal query | [optional]
 **last_n** | **int**| Only retrieve last N instances | [optional]

### Return type

[**EntityTemporal**](EntityTemporal.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json;application/ld+json


### HTTP response details

| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK |  -  |
**400** | Bad request |  -  |
**404** | Not Found |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

