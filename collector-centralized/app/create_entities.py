from semantic_tools.models.prometheus_entities import Metric, MetricSource, Endpoint, Prometheus
from semantic_tools.models.ngsi_ld.entity import Property, Relationship
from semantic_tools.clients.ngsi_ld import ngsildClient
import json

def createEntities(ngsi: ngsildClient):
    # Create Metric entities
    metric1 = Metric(id="urn:ngsi-ld:Metric:1",
                              sample={"value": "27.0", "observedAt": "2020-03-24T14:59:19.063Z"})

    ngsi.createEntity(metric1.dict(exclude_none=True))

    metric2 = Metric(id="urn:ngsi-ld:Metric:2",
                              sample={"value": "100.0", "observedAt": "2020-03-24T14:59:19.063Z", "units": "bytes/s"})

    ngsi.createEntity(metric2.dict(exclude_none=True))

    # Query Metric entities
    print("Metrics Entities: ")
    entities = ngsi.queryEntities(type="Metric")
    for entity in entities:
        metric = Metric.parse_obj(entity)
        print(metric.json(indent=4, sort_keys=True, exclude_unset=True))
    print("")

    # Create MetricSource entities
    metricsource1 = MetricSource(id="urn:ngsi-ld:MetricSource:source1",
                              name={"value": "prometheus_http_requests_total"},
                              expression={"value": {"job": "prometheus", "handler": "/api/v1/query", "code": "200"}},
                              interval={"value": "10000", "units": "ms"},
                              isSourceOf={"object": "urn:ngsi-ld:Metric:1"},
                              hasEndPoint={"object": "urn:ngsi-ld:Endpoint:1"},
                              javaclass={"value": "HttpSourceConnector"},
                              topic={"value": "source1-topic"})

    ngsi.createEntity(metricsource1.dict(exclude_none=True))

    metricsource2 = MetricSource(id="urn:ngsi-ld:MetricSource:source2",
                              name={"value": "rate(node_network_receive_bytes_total[1m])"},
                              #expression={"value": ""},
                              interval={"value": "60000", "units": "ms"},
                              isSourceOf={"object": "urn:ngsi-ld:Metric:2"},
                              hasEndPoint={"object": "urn:ngsi-ld:Endpoint:1"},
                              javaclass={"value": "HttpSourceConnector"},
                              topic={"value": "source2-topic"})

    ngsi.createEntity(metricsource2.dict(exclude_none=True))

    # Query MetricSource entities
    print("MetricSource Entities:")
    entities = ngsi.queryEntities(type="MetricSource")
    for entity in entities:
        metricsource = MetricSource.parse_obj(entity)
        print(metricsource.json(indent=4, sort_keys=True, exclude_unset=True))
    print("")

    # Create Endpoint entity
    endpoint = Endpoint(id="urn:ngsi-ld:Endpoint:1",
                              name={"value": "prometheus-api"},
                              URI={"value": "http://prometheus:9090/api/v1/query"})

    ngsi.createEntity(endpoint.dict(exclude_none=True))

    # Get Endpoint entity by id
    print("Endpoint entity:")
    response = ngsi.retrieveEntityById(entityId="urn:ngsi-ld:Endpoint:1")
    endpoint = Endpoint.parse_obj(response)
    print(endpoint.json(indent=4, sort_keys=True, exclude_unset=True))
    print("")

    # Create Prometheus entity
    prometheus = Prometheus(id="urn:ngsi-ld:Prometheus:1",
                              name={"value": "prometheus-server"},
                              jobs={"value": {"job1": "prometheus", "job2": "node"}},
                              format={"value": "json"},
                              isComposedBy={"object": ["urn:ngsi-ld:MetricSource:source1", "urn:ngsi-ld:MetricSource:source2"]})

    ngsi.createEntity(prometheus.dict(exclude_none=True))

    # Get Prometheus entity by id
    print("Prometheus entity:")
    response = ngsi.retrieveEntityById(entityId="urn:ngsi-ld:Prometheus:1")
    prometheus = Prometheus.parse_obj(response)
    print(prometheus.json(indent=4, sort_keys=True, exclude_unset=True))
    print("")

ngsi = ngsildClient(url="http://scorpio:9090",
                    headers={"Accept": "application/ld+json"},
                    context="https://pastebin.com/raw/NhZbzu8f")

createEntities(ngsi)

