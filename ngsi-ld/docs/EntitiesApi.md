# openapi_client.EntitiesApi

All URIs are relative to *http://localhost*

Method | HTTP request | Description
------------- | ------------- | -------------
[**append_entity_attrs**](EntitiesApi.md#append_entity_attrs) | **POST** /entities/{entityId}/attrs/ | 
[**batch_entity_creation**](EntitiesApi.md#batch_entity_creation) | **POST** /entityOperations/create | 
[**batch_entity_delete**](EntitiesApi.md#batch_entity_delete) | **POST** /entityOperations/delete | 
[**batch_entity_update**](EntitiesApi.md#batch_entity_update) | **POST** /entityOperations/update | 
[**batch_entity_upsert**](EntitiesApi.md#batch_entity_upsert) | **POST** /entityOperations/upsert | 
[**create_entity**](EntitiesApi.md#create_entity) | **POST** /entities/ | 
[**partial_attr_update**](EntitiesApi.md#partial_attr_update) | **PATCH** /entities/{entityId}/attrs/{attrId} | 
[**query_entities**](EntitiesApi.md#query_entities) | **GET** /entities/ | 
[**remove_entity_attr**](EntitiesApi.md#remove_entity_attr) | **DELETE** /entities/{entityId}/attrs/{attrId} | 
[**remove_entity_by_id**](EntitiesApi.md#remove_entity_by_id) | **DELETE** /entities/{entityId} | 
[**retrieve_entity_by_id**](EntitiesApi.md#retrieve_entity_by_id) | **GET** /entities/{entityId} | 
[**update_entity_attrs**](EntitiesApi.md#update_entity_attrs) | **PATCH** /entities/{entityId}/attrs/ | 


# **append_entity_attrs**
> append_entity_attrs(entity_id, entity_fragment)



Append new Entity attributes to an existing Entity within an NGSI-LD system

### Example


```python
import time
import openapi_client
from openapi_client.api import entities_api
from openapi_client.model.update_result import UpdateResult
from openapi_client.model.entity_fragment import EntityFragment
from openapi_client.model.problem_details import ProblemDetails
from pprint import pprint
# Defining the host is optional and defaults to http://localhost
# See configuration.py for a list of all supported configuration parameters.
configuration = openapi_client.Configuration(
    host = "http://localhost"
)


# Enter a context with an instance of the API client
with openapi_client.ApiClient() as api_client:
    # Create an instance of the API class
    api_instance = entities_api.EntitiesApi(api_client)
    entity_id = "entityId_example" # str | Entity Id
    entity_fragment = EntityFragment(
        key=None,
    ) # EntityFragment | 
    options = "noOverwrite" # str | Indicates that no attribute overwrite shall be performed (optional) if omitted the server will use the default value of "noOverwrite"

    # example passing only required values which don't have defaults set
    try:
        api_instance.append_entity_attrs(entity_id, entity_fragment)
    except openapi_client.ApiException as e:
        print("Exception when calling EntitiesApi->append_entity_attrs: %s\n" % e)

    # example passing only required values which don't have defaults set
    # and optional values
    try:
        api_instance.append_entity_attrs(entity_id, entity_fragment, options=options)
    except openapi_client.ApiException as e:
        print("Exception when calling EntitiesApi->append_entity_attrs: %s\n" % e)
```


### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **entity_id** | **str**| Entity Id |
 **entity_fragment** | [**EntityFragment**](EntityFragment.md)|  |
 **options** | **str**| Indicates that no attribute overwrite shall be performed | [optional] if omitted the server will use the default value of "noOverwrite"

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
**207** | Partial Success. Only the attributes included in the response payload were successfully appended |  -  |
**400** | Bad request |  -  |
**404** | Not Found |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **batch_entity_creation**
> BatchOperationResult batch_entity_creation(entity_list)



Batch Entity creation

### Example


```python
import time
import openapi_client
from openapi_client.api import entities_api
from openapi_client.model.problem_details import ProblemDetails
from openapi_client.model.batch_operation_result import BatchOperationResult
from openapi_client.model.entity_list import EntityList
from pprint import pprint
# Defining the host is optional and defaults to http://localhost
# See configuration.py for a list of all supported configuration parameters.
configuration = openapi_client.Configuration(
    host = "http://localhost"
)


# Enter a context with an instance of the API client
with openapi_client.ApiClient() as api_client:
    # Create an instance of the API class
    api_instance = entities_api.EntitiesApi(api_client)
    entity_list = EntityList([
        Entity(None),
    ]) # EntityList | 

    # example passing only required values which don't have defaults set
    try:
        api_response = api_instance.batch_entity_creation(entity_list)
        pprint(api_response)
    except openapi_client.ApiException as e:
        print("Exception when calling EntitiesApi->batch_entity_creation: %s\n" % e)
```


### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **entity_list** | [**EntityList**](EntityList.md)|  |

### Return type

[**BatchOperationResult**](BatchOperationResult.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json;application/ld+json
 - **Accept**: application/json;application/ld+json


### HTTP response details

| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Success |  -  |
**400** | Bad request |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **batch_entity_delete**
> BatchOperationResult batch_entity_delete(request_body)



Batch Entity delete

### Example


```python
import time
import openapi_client
from openapi_client.api import entities_api
from openapi_client.model.problem_details import ProblemDetails
from openapi_client.model.batch_operation_result import BatchOperationResult
from pprint import pprint
# Defining the host is optional and defaults to http://localhost
# See configuration.py for a list of all supported configuration parameters.
configuration = openapi_client.Configuration(
    host = "http://localhost"
)


# Enter a context with an instance of the API client
with openapi_client.ApiClient() as api_client:
    # Create an instance of the API class
    api_instance = entities_api.EntitiesApi(api_client)
    request_body = [
        "request_body_example",
    ] # [str] | 

    # example passing only required values which don't have defaults set
    try:
        api_response = api_instance.batch_entity_delete(request_body)
        pprint(api_response)
    except openapi_client.ApiException as e:
        print("Exception when calling EntitiesApi->batch_entity_delete: %s\n" % e)
```


### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **request_body** | **[str]**|  |

### Return type

[**BatchOperationResult**](BatchOperationResult.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json;application/ld+json
 - **Accept**: application/json;application/ld+json


### HTTP response details

| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Success |  -  |
**400** | Bad request |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **batch_entity_update**
> BatchOperationResult batch_entity_update(entity_list)



Batch Entity update

### Example


```python
import time
import openapi_client
from openapi_client.api import entities_api
from openapi_client.model.problem_details import ProblemDetails
from openapi_client.model.batch_operation_result import BatchOperationResult
from openapi_client.model.entity_list import EntityList
from pprint import pprint
# Defining the host is optional and defaults to http://localhost
# See configuration.py for a list of all supported configuration parameters.
configuration = openapi_client.Configuration(
    host = "http://localhost"
)


# Enter a context with an instance of the API client
with openapi_client.ApiClient() as api_client:
    # Create an instance of the API class
    api_instance = entities_api.EntitiesApi(api_client)
    entity_list = EntityList([
        Entity(None),
    ]) # EntityList | 
    options = "noOverwrite" # str |  (optional) if omitted the server will use the default value of "noOverwrite"

    # example passing only required values which don't have defaults set
    try:
        api_response = api_instance.batch_entity_update(entity_list)
        pprint(api_response)
    except openapi_client.ApiException as e:
        print("Exception when calling EntitiesApi->batch_entity_update: %s\n" % e)

    # example passing only required values which don't have defaults set
    # and optional values
    try:
        api_response = api_instance.batch_entity_update(entity_list, options=options)
        pprint(api_response)
    except openapi_client.ApiException as e:
        print("Exception when calling EntitiesApi->batch_entity_update: %s\n" % e)
```


### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **entity_list** | [**EntityList**](EntityList.md)|  |
 **options** | **str**|  | [optional] if omitted the server will use the default value of "noOverwrite"

### Return type

[**BatchOperationResult**](BatchOperationResult.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json;application/ld+json
 - **Accept**: application/json;application/ld+json


### HTTP response details

| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Success |  -  |
**400** | Bad request |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **batch_entity_upsert**
> BatchOperationResult batch_entity_upsert(entity_list)



Batch Entity upsert

### Example


```python
import time
import openapi_client
from openapi_client.api import entities_api
from openapi_client.model.problem_details import ProblemDetails
from openapi_client.model.batch_operation_result import BatchOperationResult
from openapi_client.model.entity_list import EntityList
from pprint import pprint
# Defining the host is optional and defaults to http://localhost
# See configuration.py for a list of all supported configuration parameters.
configuration = openapi_client.Configuration(
    host = "http://localhost"
)


# Enter a context with an instance of the API client
with openapi_client.ApiClient() as api_client:
    # Create an instance of the API class
    api_instance = entities_api.EntitiesApi(api_client)
    entity_list = EntityList([
        Entity(None),
    ]) # EntityList | 
    options = "replace" # str |  (optional)

    # example passing only required values which don't have defaults set
    try:
        api_response = api_instance.batch_entity_upsert(entity_list)
        pprint(api_response)
    except openapi_client.ApiException as e:
        print("Exception when calling EntitiesApi->batch_entity_upsert: %s\n" % e)

    # example passing only required values which don't have defaults set
    # and optional values
    try:
        api_response = api_instance.batch_entity_upsert(entity_list, options=options)
        pprint(api_response)
    except openapi_client.ApiException as e:
        print("Exception when calling EntitiesApi->batch_entity_upsert: %s\n" % e)
```


### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **entity_list** | [**EntityList**](EntityList.md)|  |
 **options** | **str**|  | [optional]

### Return type

[**BatchOperationResult**](BatchOperationResult.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json;application/ld+json
 - **Accept**: application/json;application/ld+json


### HTTP response details

| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Success |  -  |
**400** | Bad request |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **create_entity**
> create_entity(entity)



Create a new Entity within an NGSI-LD system

### Example


```python
import time
import openapi_client
from openapi_client.api import entities_api
from openapi_client.model.entity import Entity
from openapi_client.model.problem_details import ProblemDetails
from pprint import pprint
# Defining the host is optional and defaults to http://localhost
# See configuration.py for a list of all supported configuration parameters.
configuration = openapi_client.Configuration(
    host = "http://localhost"
)


# Enter a context with an instance of the API client
with openapi_client.ApiClient() as api_client:
    # Create an instance of the API class
    api_instance = entities_api.EntitiesApi(api_client)
    entity = Entity(None) # Entity | 

    # example passing only required values which don't have defaults set
    try:
        api_instance.create_entity(entity)
    except openapi_client.ApiException as e:
        print("Exception when calling EntitiesApi->create_entity: %s\n" % e)
```


### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **entity** | [**Entity**](Entity.md)|  |

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
**400** | Bad request |  -  |
**409** | Already exists |  -  |
**422** | Unprocessable Entity |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **partial_attr_update**
> partial_attr_update(entity_id, attr_id, entity_fragment)



Update existing Entity attributes within an NGSI-LD system

### Example


```python
import time
import openapi_client
from openapi_client.api import entities_api
from openapi_client.model.entity_fragment import EntityFragment
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
    api_instance = entities_api.EntitiesApi(api_client)
    entity_id = "entityId_example" # str | Entity Id
    attr_id = Name("_") # Name | Attribute Id
    entity_fragment = EntityFragment(
        key=None,
    ) # EntityFragment | 

    # example passing only required values which don't have defaults set
    try:
        api_instance.partial_attr_update(entity_id, attr_id, entity_fragment)
    except openapi_client.ApiException as e:
        print("Exception when calling EntitiesApi->partial_attr_update: %s\n" % e)
```


### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **entity_id** | **str**| Entity Id |
 **attr_id** | **Name**| Attribute Id |
 **entity_fragment** | [**EntityFragment**](EntityFragment.md)|  |

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
**204** | No Content. |  -  |
**400** | Bad Request |  -  |
**404** | Not Found |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **query_entities**
> EntityList query_entities()



Retrieve a set of entities which matches a specific query from an NGSI-LD system

### Example


```python
import time
import openapi_client
from openapi_client.api import entities_api
from openapi_client.model.geometry import Geometry
from openapi_client.model.coordinates import Coordinates
from openapi_client.model.problem_details import ProblemDetails
from openapi_client.model.entity_list import EntityList
from openapi_client.model.georel import Georel
from pprint import pprint
# Defining the host is optional and defaults to http://localhost
# See configuration.py for a list of all supported configuration parameters.
configuration = openapi_client.Configuration(
    host = "http://localhost"
)


# Enter a context with an instance of the API client
with openapi_client.ApiClient() as api_client:
    # Create an instance of the API class
    api_instance = entities_api.EntitiesApi(api_client)
    id = "id_example" # str | Comma separated list of URIs to be retrieved (optional)
    id_pattern = "idPattern_example" # str | Regular expression that must be matched by Entity ids (optional)
    type = "type_example" # str | Comma separated list of Entity type names to be retrieved (optional)
    attrs = "attrs_example" # str | Comma separated list of attribute names (properties or relationships) to be retrieved (optional)
    q = "q_example" # str | Query (optional)
    georel = Georel(None) # Georel | Geo-relationship (optional)
    geometry = Geometry("Point") # Geometry | Geometry (optional)
    coordinates = Coordinates(None) # Coordinates | Coordinates serialized as a string (optional)
    geoproperty = "geoproperty_example" # str | The name of the property that contains the geo-spatial data that will be used to resolve the geoquery (optional)
    csf = "csf_example" # str | Context Source Filter (optional)
    limit = 1 # int | Pagination limit (optional)
    options = "keyValues" # str | Options dictionary (optional)

    # example passing only required values which don't have defaults set
    # and optional values
    try:
        api_response = api_instance.query_entities(id=id, id_pattern=id_pattern, type=type, attrs=attrs, q=q, georel=georel, geometry=geometry, coordinates=coordinates, geoproperty=geoproperty, csf=csf, limit=limit, options=options)
        pprint(api_response)
    except openapi_client.ApiException as e:
        print("Exception when calling EntitiesApi->query_entities: %s\n" % e)
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
 **csf** | **str**| Context Source Filter | [optional]
 **limit** | **int**| Pagination limit | [optional]
 **options** | **str**| Options dictionary | [optional]

### Return type

[**EntityList**](EntityList.md)

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

# **remove_entity_attr**
> remove_entity_attr(entity_id, attr_id)



Removes an existing Entity attribute within an NGSI-LD system

### Example


```python
import time
import openapi_client
from openapi_client.api import entities_api
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
    api_instance = entities_api.EntitiesApi(api_client)
    entity_id = "entityId_example" # str | Entity Id
    attr_id = Name("_") # Name | Attribute Id

    # example passing only required values which don't have defaults set
    try:
        api_instance.remove_entity_attr(entity_id, attr_id)
    except openapi_client.ApiException as e:
        print("Exception when calling EntitiesApi->remove_entity_attr: %s\n" % e)
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

# **remove_entity_by_id**
> remove_entity_by_id(entity_id)



Removes an specific Entity from an NGSI-LD system

### Example


```python
import time
import openapi_client
from openapi_client.api import entities_api
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
    api_instance = entities_api.EntitiesApi(api_client)
    entity_id = "entityId_example" # str | Entity Id
    type = Name("_") # Name | Entity Type (optional)

    # example passing only required values which don't have defaults set
    try:
        api_instance.remove_entity_by_id(entity_id)
    except openapi_client.ApiException as e:
        print("Exception when calling EntitiesApi->remove_entity_by_id: %s\n" % e)

    # example passing only required values which don't have defaults set
    # and optional values
    try:
        api_instance.remove_entity_by_id(entity_id, type=type)
    except openapi_client.ApiException as e:
        print("Exception when calling EntitiesApi->remove_entity_by_id: %s\n" % e)
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

# **retrieve_entity_by_id**
> Entity retrieve_entity_by_id(entity_id)



Retrieve an specific Entity from an NGSI-LD system. It's possible to specify the Entity attributes to be retrieved by using query parameters

### Example


```python
import time
import openapi_client
from openapi_client.api import entities_api
from openapi_client.model.entity import Entity
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
    api_instance = entities_api.EntitiesApi(api_client)
    entity_id = "entityId_example" # str | Entity Id
    attrs = "attrs_example" # str | Comma separated list of attribute names (properties or relationships) to be retrieved (optional)
    type = Name("_") # Name | Entity Type (optional)
    options = "keyValues" # str | Options dictionary (optional)

    # example passing only required values which don't have defaults set
    try:
        api_response = api_instance.retrieve_entity_by_id(entity_id)
        pprint(api_response)
    except openapi_client.ApiException as e:
        print("Exception when calling EntitiesApi->retrieve_entity_by_id: %s\n" % e)

    # example passing only required values which don't have defaults set
    # and optional values
    try:
        api_response = api_instance.retrieve_entity_by_id(entity_id, attrs=attrs, type=type, options=options)
        pprint(api_response)
    except openapi_client.ApiException as e:
        print("Exception when calling EntitiesApi->retrieve_entity_by_id: %s\n" % e)
```


### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **entity_id** | **str**| Entity Id |
 **attrs** | **str**| Comma separated list of attribute names (properties or relationships) to be retrieved | [optional]
 **type** | **Name**| Entity Type | [optional]
 **options** | **str**| Options dictionary | [optional]

### Return type

[**Entity**](Entity.md)

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

# **update_entity_attrs**
> update_entity_attrs(entity_id, entity_fragment)



Update existing Entity attributes within an NGSI-LD system

### Example


```python
import time
import openapi_client
from openapi_client.api import entities_api
from openapi_client.model.update_result import UpdateResult
from openapi_client.model.entity_fragment import EntityFragment
from openapi_client.model.problem_details import ProblemDetails
from pprint import pprint
# Defining the host is optional and defaults to http://localhost
# See configuration.py for a list of all supported configuration parameters.
configuration = openapi_client.Configuration(
    host = "http://localhost"
)


# Enter a context with an instance of the API client
with openapi_client.ApiClient() as api_client:
    # Create an instance of the API class
    api_instance = entities_api.EntitiesApi(api_client)
    entity_id = "entityId_example" # str | Entity Id
    entity_fragment = EntityFragment(
        key=None,
    ) # EntityFragment | 

    # example passing only required values which don't have defaults set
    try:
        api_instance.update_entity_attrs(entity_id, entity_fragment)
    except openapi_client.ApiException as e:
        print("Exception when calling EntitiesApi->update_entity_attrs: %s\n" % e)
```


### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **entity_id** | **str**| Entity Id |
 **entity_fragment** | [**EntityFragment**](EntityFragment.md)|  |

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
**204** | No Content. |  -  |
**207** | Partial Success. Only the attributes included in the response payload were successfully updated |  -  |
**400** | Bad Request |  -  |
**404** | Not Found |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

