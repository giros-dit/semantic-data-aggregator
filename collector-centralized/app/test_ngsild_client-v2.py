from semantic_tools.models.prometheus_entities import Metric, MetricSource, Endpoint, Prometheus
from semantic_tools.models.ngsi_ld.entity import Property, Relationship
from semantic_tools.clients.ngsi_ld import ngsildClient

ngsi = ngsildClient(url="http://scorpio:9090",
                    headers={"Accept": "application/ld+json"},
                    context="https://pastebin.com/raw/NhZbzu8f",
                    debug=True)

# Create Metric entities
metric1 = Metric(id="urn:ngsi-ld:Metric:1",
                          sample=Property(value="27.0", observedAt="2020-03-24T14:59:19.063Z", units=None),
                          hasSource=Relationship(object="urn:ngsi-ld:MetricSource:source1"))

print(metric1.id)

ngsi.createEntity(metric1.dict(exclude_none=True))

metric2 = Metric(id="urn:ngsi-ld:Metric:2",
                          sample=Property(value="100.0", observedAt="2020-03-24T14:59:19.063Z", units="bytes/s"),
                          hasSource=Relationship(object="urn:ngsi-ld:MetricSource:source2"))

ngsi.createEntity(metric2.dict(exclude_none=True))

# Query Metric entities
entities = ngsi.queryEntities(type="Metric")
for entity in entities:
    metric = Metric.parse_obj(entity)
    print(metric.json(indent=4, sort_keys=True, exclude_unset=True))

# Get Metric entity by id example
response = ngsi.retrieveEntityById(entityId="urn:ngsi-ld:Metric:1")
metric = Metric.parse_obj(response)
print(metric.json(indent=4, sort_keys=True, exclude_unset=True))


# Create MetricSource entities
metricsource1 = MetricSource(id="urn:ngsi-ld:MetricSource:source1",
                          name=Property(value="prometheus_http_requests_total"),
                          expression=Property(value={"job": "prometheus"}),
                          interval=Property(value="10000"),
                          hasEndPoint=Relationship(object="urn:ngsi-ld:Endpoint:1"),
                          javaclass=Property(value="HttpSourceConnector"),
                          topic=Property(value="source1-topic"))

ngsi.createEntity(metricsource1.dict(exclude_none=True))

metricsource2 = MetricSource(id="urn:ngsi-ld:MetricSource:source2",
                          name=Property(value="rate(node_network_receive_bytes_total[1m])"),
                          expression=Property(value={"job": "node"}),
                          interval=Property(value="60000", units="ms"),
                          hasEndPoint=Relationship(object="urn:ngsi-ld:Endpoint:1"),
                          javaclass=Property(value="HttpSourceConnector"),
                          topic=Property(value="source2-topic"))

ngsi.createEntity(metricsource2.dict(exclude_none=True))

# Query MetricSource entities
entities = ngsi.queryEntities(type="MetricSource")
for entity in entities:
    metricsource = MetricSource.parse_obj(entity)
    print(metricsource.json(indent=4, sort_keys=True, exclude_unset=True))

# Create Endpoint entity
endpoint = Endpoint(id="urn:ngsi-ld:Endpoint:1",
                          name=Property(value="prometheus-api"),
                          URI=Property(value="http://prometheus:9090/api/v1/query"))

ngsi.createEntity(endpoint.dict(exclude_none=True))

# Get Endpoint entity by id example
response = ngsi.retrieveEntityById(entityId="urn:ngsi-ld:Endpoint:1")
endpoint = Endpoint.parse_obj(response)
print(endpoint.json(indent=4, sort_keys=True, exclude_unset=True))

# Create Prometheus entity
prometheus = Prometheus(id="urn:ngsi-ld:Prometheus:1",
                          name=Property(value="prometheus-server"),
                          jobs=Property(value={"job1": "prometheus", "job2": "node"}),
                          format=Property(value="json"),
                          isComposedBy=Relationship(object=["urn:ngsi-ld:Metric:1", "urn:ngsi-ld:Metric:2"]),
                          isConnectedTo=Relationship(object="urn:ngsi-ld:Endpoint:1"))

ngsi.createEntity(prometheus.dict(exclude_none=True))

# Get Prometheus entity by id example
response = ngsi.retrieveEntityById(entityId="urn:ngsi-ld:Prometheus:1")
prometheus = Prometheus.parse_obj(response)
print(prometheus.json(indent=4, sort_keys=True, exclude_unset=True))

print(metricsource1.hasEndPoint.object)
