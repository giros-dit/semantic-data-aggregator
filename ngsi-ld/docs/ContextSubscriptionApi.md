# openapi_client.ContextSubscriptionApi

All URIs are relative to *http://localhost*

Method | HTTP request | Description
------------- | ------------- | -------------
[**create_subscription**](ContextSubscriptionApi.md#create_subscription) | **POST** /subscriptions/ | 
[**remove_subscription**](ContextSubscriptionApi.md#remove_subscription) | **DELETE** /subscriptions/{subscriptionId} | 
[**retrieve_subscription_by_id**](ContextSubscriptionApi.md#retrieve_subscription_by_id) | **GET** /subscriptions/{subscriptionId} | 
[**retrieve_subscriptions**](ContextSubscriptionApi.md#retrieve_subscriptions) | **GET** /subscriptions/ | 
[**update_subscription**](ContextSubscriptionApi.md#update_subscription) | **PATCH** /subscriptions/{subscriptionId} | 


# **create_subscription**
> create_subscription(subscription)



Creates a new Subscription within an NGSI-LD system

### Example


```python
import time
import openapi_client
from openapi_client.api import context_subscription_api
from openapi_client.model.subscription import Subscription
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
    api_instance = context_subscription_api.ContextSubscriptionApi(api_client)
    subscription = Subscription(None) # Subscription | 

    # example passing only required values which don't have defaults set
    try:
        api_instance.create_subscription(subscription)
    except openapi_client.ApiException as e:
        print("Exception when calling ContextSubscriptionApi->create_subscription: %s\n" % e)
```


### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **subscription** | [**Subscription**](Subscription.md)|  |

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
**201** | Created. Contains the resource URI of the created Subscription |  -  |
**400** | Bad request |  -  |
**409** | Already exists |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **remove_subscription**
> remove_subscription(subscription_id)



Removes a specific Subscription from an NGSI-LD system

### Example


```python
import time
import openapi_client
from openapi_client.api import context_subscription_api
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
    api_instance = context_subscription_api.ContextSubscriptionApi(api_client)
    subscription_id = "subscriptionId_example" # str | Subscription Id

    # example passing only required values which don't have defaults set
    try:
        api_instance.remove_subscription(subscription_id)
    except openapi_client.ApiException as e:
        print("Exception when calling ContextSubscriptionApi->remove_subscription: %s\n" % e)
```


### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **subscription_id** | **str**| Subscription Id |

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
**204** | No Content. The Subscription was removed successfully |  -  |
**400** | Bad Request |  -  |
**404** | Not Found |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **retrieve_subscription_by_id**
> Subscription retrieve_subscription_by_id(subscription_id)



Retrieves a specific Subscription from an NGSI-LD system

### Example


```python
import time
import openapi_client
from openapi_client.api import context_subscription_api
from openapi_client.model.subscription import Subscription
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
    api_instance = context_subscription_api.ContextSubscriptionApi(api_client)
    subscription_id = "subscriptionId_example" # str | Subscription Id

    # example passing only required values which don't have defaults set
    try:
        api_response = api_instance.retrieve_subscription_by_id(subscription_id)
        pprint(api_response)
    except openapi_client.ApiException as e:
        print("Exception when calling ContextSubscriptionApi->retrieve_subscription_by_id: %s\n" % e)
```


### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **subscription_id** | **str**| Subscription Id |

### Return type

[**Subscription**](Subscription.md)

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

# **retrieve_subscriptions**
> SubscriptionList retrieve_subscriptions()



Retrieves the subscriptions available in an NGSI-LD system

### Example


```python
import time
import openapi_client
from openapi_client.api import context_subscription_api
from openapi_client.model.problem_details import ProblemDetails
from openapi_client.model.subscription_list import SubscriptionList
from pprint import pprint
# Defining the host is optional and defaults to http://localhost
# See configuration.py for a list of all supported configuration parameters.
configuration = openapi_client.Configuration(
    host = "http://localhost"
)


# Enter a context with an instance of the API client
with openapi_client.ApiClient() as api_client:
    # Create an instance of the API class
    api_instance = context_subscription_api.ContextSubscriptionApi(api_client)
    limit = 1 # int | Pagination limit (optional)

    # example passing only required values which don't have defaults set
    # and optional values
    try:
        api_response = api_instance.retrieve_subscriptions(limit=limit)
        pprint(api_response)
    except openapi_client.ApiException as e:
        print("Exception when calling ContextSubscriptionApi->retrieve_subscriptions: %s\n" % e)
```


### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **limit** | **int**| Pagination limit | [optional]

### Return type

[**SubscriptionList**](SubscriptionList.md)

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

# **update_subscription**
> update_subscription(subscription_id, subscription_fragment)



Updates a specific Subscription within an NGSI-LD system

### Example


```python
import time
import openapi_client
from openapi_client.api import context_subscription_api
from openapi_client.model.subscription_fragment import SubscriptionFragment
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
    api_instance = context_subscription_api.ContextSubscriptionApi(api_client)
    subscription_id = "subscriptionId_example" # str | Subscription Id
    subscription_fragment = SubscriptionFragment(
        context=LdContext(None),
        entities=[
            EntityInfo(
                id="id_example",
                type=Name("_"),
                id_pattern="id_pattern_example",
            ),
        ],
        name="name_example",
        description="description_example",
        watched_attributes=[
            Name("watched_attributes_example"),
        ],
        time_interval=0,
        expires=dateutil_parser('1970-01-01T00:00:00.00Z'),
        is_active=True,
        throttling=1,
        q="q_example",
        geo_q=GeoQuery(
            georel=Georel(None),
            coordinates=Coordinates(None),
            geometry=Geometry("Point"),
        ),
        csf="csf_example",
    ) # SubscriptionFragment | 

    # example passing only required values which don't have defaults set
    try:
        api_instance.update_subscription(subscription_id, subscription_fragment)
    except openapi_client.ApiException as e:
        print("Exception when calling ContextSubscriptionApi->update_subscription: %s\n" % e)
```


### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **subscription_id** | **str**| Subscription Id |
 **subscription_fragment** | [**SubscriptionFragment**](SubscriptionFragment.md)|  |

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
**204** | No Content. The Subscription was updated successfully |  -  |
**400** | Bad Request |  -  |
**404** | Not Found |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

