from semantic_tools.models.prometheus import PrometheusMetric
from semantic_tools.clients.ngsi_ld import ngsildClient

ngsi = ngsildClient(url="http://scorpio:9090",
                    context="https://pastebin.com/raw/PCe63jxb",
                    debug=True)

# Create entity example
metric = PrometheusMetric(id="urn:ngsi-ld:PrometheusMetric:1",
                          name={"value": "prometheus_http_requests_total"},
                          labels={"value": {"job": "prometheus"}},
                          sample={"value": "27.0"})

ngsi.createEntity(metric.dict(exclude_none=True))

# Query entities example
entities = ngsi.queryEntities(type="PrometheusMetric")
for entity in entities:
    metric = PrometheusMetric.parse_obj(entity)
    print(metric.json(indent=4, sort_keys=True, exclude_unset=True))

# Get entity by id example
response = ngsi.retrieveEntityById(entityId="urn:ngsi-ld:PrometheusMetric:1")
metric = PrometheusMetric.parse_obj(response)
print(metric.json(indent=4, sort_keys=True, exclude_unset=True))
