# Subscription


## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**context** | [**LdContext**](LdContext.md) |  | [optional] 
**entities** | [**[EntityInfo]**](EntityInfo.md) |  | [optional] 
**name** | **str** |  | [optional] 
**description** | **str** |  | [optional] 
**watched_attributes** | [**[Name]**](Name.md) |  | [optional] 
**time_interval** | **float** |  | [optional] 
**expires** | **datetime** |  | [optional] 
**is_active** | **bool** |  | [optional] 
**throttling** | **float** |  | [optional] 
**q** | **str** |  | [optional] 
**geo_q** | [**GeoQuery**](GeoQuery.md) |  | [optional] 
**csf** | **str** |  | [optional] 
**id** | **str** |  | [optional] 
**type** | **str** |  | [optional]  if omitted the server will use the default value of "Subscription"
**notification** | [**NotificationParams**](NotificationParams.md) |  | [optional] 
**status** | **str** |  | [optional] 
**created_at** | **datetime** |  | [optional] 
**modified_at** | **datetime** |  | [optional] 
**any string name** | **bool, date, datetime, dict, float, int, list, str, none_type** | any string name can be used but the value must be the correct type | [optional]

[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)


