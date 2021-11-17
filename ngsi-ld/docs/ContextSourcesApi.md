# openapi_client.ContextSourcesApi

All URIs are relative to *http://localhost*

Method | HTTP request | Description
------------- | ------------- | -------------
[**create_c_source_subscription**](ContextSourcesApi.md#create_c_source_subscription) | **POST** /csourceSubscriptions/ | 
[**query_csources**](ContextSourcesApi.md#query_csources) | **GET** /csourceRegistrations/ | 
[**register_csource**](ContextSourcesApi.md#register_csource) | **POST** /csourceRegistrations/ | 
[**remove_c_source_subscription**](ContextSourcesApi.md#remove_c_source_subscription) | **DELETE** /csourceSubscriptions/{subscriptionId} | 
[**remove_csource**](ContextSourcesApi.md#remove_csource) | **DELETE** /csourceRegistrations/{registrationId} | 
[**retrieve_c_source_subscriptions**](ContextSourcesApi.md#retrieve_c_source_subscriptions) | **GET** /csourceSubscriptions/ | 
[**retrieve_c_source_subscriptions_by_id**](ContextSourcesApi.md#retrieve_c_source_subscriptions_by_id) | **GET** /csourceSubscriptions/{subscriptionId} | 
[**retrieve_csource**](ContextSourcesApi.md#retrieve_csource) | **GET** /csourceRegistrations/{registrationId} | 
[**update_c_source_subscription**](ContextSourcesApi.md#update_c_source_subscription) | **PATCH** /csourceSubscriptions/{subscriptionId} | 


# **create_c_source_subscription**
> create_c_source_subscription(subscription)



Creates a context source discovery Subscription within an NGSI-LD system

### Example


```python
import time
import openapi_client
from openapi_client.api import context_sources_api
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
    api_instance = context_sources_api.ContextSourcesApi(api_client)
    subscription = Subscription(None) # Subscription | 

    # example passing only required values which don't have defaults set
    try:
        api_instance.create_c_source_subscription(subscription)
    except openapi_client.ApiException as e:
        print("Exception when calling ContextSourcesApi->create_c_source_subscription: %s\n" % e)
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

# **query_csources**
> ContextSourceRegistrationList query_csources()



Retrieve a set of context sources which matches a specific query from an NGSI-LD system

### Example


```python
import time
import openapi_client
from openapi_client.api import context_sources_api
from openapi_client.model.geometry import Geometry
from openapi_client.model.coordinates import Coordinates
from openapi_client.model.context_source_registration_list import ContextSourceRegistrationList
from openapi_client.model.problem_details import ProblemDetails
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
    api_instance = context_sources_api.ContextSourcesApi(api_client)
    id = "id_example" # str | Comma separated list of URIs to be retrieved (optional)
    id_pattern = "idPattern_example" # str | Regular expression that must be matched by Entity ids (optional)
    type = "type_example" # str | Comma separated list of Entity type names to be retrieved (optional)
    attrs = "attrs_example" # str | Comma separated list of attribute names (properties or relationships) to be retrieved (optional)
    q = "q_example" # str | Query (optional)
    georel = Georel(None) # Georel | Geo-relationship (optional)
    geometry = Geometry("Point") # Geometry | Geometry (optional)
    coordinates = Coordinates(None) # Coordinates | Coordinates serialized as a string (optional)
    geoproperty = "geoproperty_example" # str | The name of the property that contains the geo-spatial data that will be used to resolve the geoquery (optional)
    limit = 1 # int | Pagination limit (optional)

    # example passing only required values which don't have defaults set
    # and optional values
    try:
        api_response = api_instance.query_csources(id=id, id_pattern=id_pattern, type=type, attrs=attrs, q=q, georel=georel, geometry=geometry, coordinates=coordinates, geoproperty=geoproperty, limit=limit)
        pprint(api_response)
    except openapi_client.ApiException as e:
        print("Exception when calling ContextSourcesApi->query_csources: %s\n" % e)
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
 **limit** | **int**| Pagination limit | [optional]

### Return type

[**ContextSourceRegistrationList**](ContextSourceRegistrationList.md)

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

# **register_csource**
> register_csource(context_source_registration)



Registers a new context source within an NGSI-LD system

### Example


```python
import time
import openapi_client
from openapi_client.api import context_sources_api
from openapi_client.model.context_source_registration import ContextSourceRegistration
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
    api_instance = context_sources_api.ContextSourcesApi(api_client)
    context_source_registration = ContextSourceRegistration(None) # ContextSourceRegistration | 

    # example passing only required values which don't have defaults set
    try:
        api_instance.register_csource(context_source_registration)
    except openapi_client.ApiException as e:
        print("Exception when calling ContextSourcesApi->register_csource: %s\n" % e)
```


### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **context_source_registration** | [**ContextSourceRegistration**](ContextSourceRegistration.md)|  |

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
**201** | Created. Contains the resource URI of the created Registration |  -  |
**400** | Bad request |  -  |
**409** | Already exists |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **remove_c_source_subscription**
> remove_c_source_subscription(subscription_id)



Removes a specific Context Source Subscription from an NGSI-LD system

### Example


```python
import time
import openapi_client
from openapi_client.api import context_sources_api
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
    api_instance = context_sources_api.ContextSourcesApi(api_client)
    subscription_id = "subscriptionId_example" # str | Subscription Id

    # example passing only required values which don't have defaults set
    try:
        api_instance.remove_c_source_subscription(subscription_id)
    except openapi_client.ApiException as e:
        print("Exception when calling ContextSourcesApi->remove_c_source_subscription: %s\n" % e)
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

# **remove_csource**
> remove_csource(registration_id)



Removes an specific context source registration within an NGSI-LD system

### Example


```python
import time
import openapi_client
from openapi_client.api import context_sources_api
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
    api_instance = context_sources_api.ContextSourcesApi(api_client)
    registration_id = "registrationId_example" # str | Registration Id

    # example passing only required values which don't have defaults set
    try:
        api_instance.remove_csource(registration_id)
    except openapi_client.ApiException as e:
        print("Exception when calling ContextSourcesApi->remove_csource: %s\n" % e)
```


### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **registration_id** | **str**| Registration Id |

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
**204** | No Content. The Registration was removed successfully |  -  |
**400** | Bad Request |  -  |
**404** | Not Found |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **retrieve_c_source_subscriptions**
> SubscriptionList retrieve_c_source_subscriptions()



Retrieves the context source discovery subscriptions available in an NGSI-LD system

### Example


```python
import time
import openapi_client
from openapi_client.api import context_sources_api
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
    api_instance = context_sources_api.ContextSourcesApi(api_client)
    limit = 1 # int | Pagination limit (optional)

    # example passing only required values which don't have defaults set
    # and optional values
    try:
        api_response = api_instance.retrieve_c_source_subscriptions(limit=limit)
        pprint(api_response)
    except openapi_client.ApiException as e:
        print("Exception when calling ContextSourcesApi->retrieve_c_source_subscriptions: %s\n" % e)
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

# **retrieve_c_source_subscriptions_by_id**
> Subscription retrieve_c_source_subscriptions_by_id(subscription_id)



Retrieves a specific Subscription from an NGSI-LD system

### Example


```python
import time
import openapi_client
from openapi_client.api import context_sources_api
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
    api_instance = context_sources_api.ContextSourcesApi(api_client)
    subscription_id = "subscriptionId_example" # str | Subscription Id

    # example passing only required values which don't have defaults set
    try:
        api_response = api_instance.retrieve_c_source_subscriptions_by_id(subscription_id)
        pprint(api_response)
    except openapi_client.ApiException as e:
        print("Exception when calling ContextSourcesApi->retrieve_c_source_subscriptions_by_id: %s\n" % e)
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

# **retrieve_csource**
> ContextSourceRegistration retrieve_csource(registration_id)



Retrieves a specific context source registration from an NGSI-LD system

### Example


```python
import time
import openapi_client
from openapi_client.api import context_sources_api
from openapi_client.model.context_source_registration import ContextSourceRegistration
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
    api_instance = context_sources_api.ContextSourcesApi(api_client)
    registration_id = "registrationId_example" # str | Registration Id

    # example passing only required values which don't have defaults set
    try:
        api_response = api_instance.retrieve_csource(registration_id)
        pprint(api_response)
    except openapi_client.ApiException as e:
        print("Exception when calling ContextSourcesApi->retrieve_csource: %s\n" % e)
```


### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **registration_id** | **str**| Registration Id |

### Return type

[**ContextSourceRegistration**](ContextSourceRegistration.md)

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

# **update_c_source_subscription**
> update_c_source_subscription(subscription_id, subscription_fragment)



Updates a specific context source discovery Subscription within an NGSI-LD system

### Example


```python
import time
import openapi_client
from openapi_client.api import context_sources_api
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
    api_instance = context_sources_api.ContextSourcesApi(api_client)
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
        api_instance.update_c_source_subscription(subscription_id, subscription_fragment)
    except openapi_client.ApiException as e:
        print("Exception when calling ContextSourcesApi->update_c_source_subscription: %s\n" % e)
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

