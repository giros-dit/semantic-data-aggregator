# openapi_client.BatchOperationsApi

All URIs are relative to *http://localhost*

Method | HTTP request | Description
------------- | ------------- | -------------
[**batch_entity_creation**](BatchOperationsApi.md#batch_entity_creation) | **POST** /entityOperations/create | 
[**batch_entity_delete**](BatchOperationsApi.md#batch_entity_delete) | **POST** /entityOperations/delete | 
[**batch_entity_update**](BatchOperationsApi.md#batch_entity_update) | **POST** /entityOperations/update | 
[**batch_entity_upsert**](BatchOperationsApi.md#batch_entity_upsert) | **POST** /entityOperations/upsert | 


# **batch_entity_creation**
> BatchOperationResult batch_entity_creation(entity_list)



Batch Entity creation

### Example


```python
import time
import openapi_client
from openapi_client.api import batch_operations_api
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
    api_instance = batch_operations_api.BatchOperationsApi(api_client)
    entity_list = EntityList([
        Entity(None),
    ]) # EntityList | 

    # example passing only required values which don't have defaults set
    try:
        api_response = api_instance.batch_entity_creation(entity_list)
        pprint(api_response)
    except openapi_client.ApiException as e:
        print("Exception when calling BatchOperationsApi->batch_entity_creation: %s\n" % e)
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
from openapi_client.api import batch_operations_api
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
    api_instance = batch_operations_api.BatchOperationsApi(api_client)
    request_body = [
        "request_body_example",
    ] # [str] | 

    # example passing only required values which don't have defaults set
    try:
        api_response = api_instance.batch_entity_delete(request_body)
        pprint(api_response)
    except openapi_client.ApiException as e:
        print("Exception when calling BatchOperationsApi->batch_entity_delete: %s\n" % e)
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
from openapi_client.api import batch_operations_api
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
    api_instance = batch_operations_api.BatchOperationsApi(api_client)
    entity_list = EntityList([
        Entity(None),
    ]) # EntityList | 
    options = "noOverwrite" # str |  (optional) if omitted the server will use the default value of "noOverwrite"

    # example passing only required values which don't have defaults set
    try:
        api_response = api_instance.batch_entity_update(entity_list)
        pprint(api_response)
    except openapi_client.ApiException as e:
        print("Exception when calling BatchOperationsApi->batch_entity_update: %s\n" % e)

    # example passing only required values which don't have defaults set
    # and optional values
    try:
        api_response = api_instance.batch_entity_update(entity_list, options=options)
        pprint(api_response)
    except openapi_client.ApiException as e:
        print("Exception when calling BatchOperationsApi->batch_entity_update: %s\n" % e)
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
from openapi_client.api import batch_operations_api
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
    api_instance = batch_operations_api.BatchOperationsApi(api_client)
    entity_list = EntityList([
        Entity(None),
    ]) # EntityList | 
    options = "replace" # str |  (optional)

    # example passing only required values which don't have defaults set
    try:
        api_response = api_instance.batch_entity_upsert(entity_list)
        pprint(api_response)
    except openapi_client.ApiException as e:
        print("Exception when calling BatchOperationsApi->batch_entity_upsert: %s\n" % e)

    # example passing only required values which don't have defaults set
    # and optional values
    try:
        api_response = api_instance.batch_entity_upsert(entity_list, options=options)
        pprint(api_response)
    except openapi_client.ApiException as e:
        print("Exception when calling BatchOperationsApi->batch_entity_upsert: %s\n" % e)
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

