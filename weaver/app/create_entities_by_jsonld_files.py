from semantic_tools.clients.ngsi_ld import ngsildClient
import json

def createEntities(ngsi: ngsildClient, metric_id: str, metricsource_id: str, prometheus_id: str = None, endpoint_id: str = None):

    metric_split = metric_id.split(":")

    with open('semantic_tools/models/prometheus-datamodel-ngsild/metric{0}.jsonld'.format(metric_split[3])) as file:
        metric_datamodel = json.load(file)

    ngsi.createEntity(metric_datamodel)
    metric_entity=ngsi.retrieveEntityById(metric_id)
    print("Metric Entity:")
    print(metric_entity)
    print("")

    with open('semantic_tools/models/prometheus-datamodel-ngsild/metric-source{0}.jsonld'.format(metric_split[3])) as file:
        metricsource_datamodel = json.load(file)
    ngsi.createEntity(metricsource_datamodel)
    metricsource_entity=ngsi.retrieveEntityById(metricsource_id)
    print("MetricSource Entity:")
    print(metricsource_entity)
    print("")

    if prometheus_id:
        with open('semantic_tools/models/prometheus-datamodel-ngsild/prometheus.jsonld') as file:
            prometheus_datamodel = json.load(file)
        ngsi.createEntity(prometheus_datamodel)
        prometheus_entity=ngsi.retrieveEntityById(prometheus_id)
        print("Prometheus Entity:")
        print(prometheus_entity)
        print("")

    if endpoint_id:
        with open('semantic_tools/models/prometheus-datamodel-ngsild/endpoint.jsonld') as file:
            endpoint_datamodel = json.load(file)
            ngsi.createEntity(endpoint_datamodel)
            endpoint_entity=ngsi.retrieveEntityById(endpoint_id)
            print("Endpoint Entity:")
            print(endpoint_entity)
            print("")

ngsi = ngsildClient(url="http://scorpio:9090",
                    headers={"Accept": "application/ld+json", "Content-Type": "application/ld+json"},
                    context="http://context-catalog:8080/prometheus-context.jsonld")

createEntities(ngsi, "urn:ngsi-ld:Metric:1", "urn:ngsi-ld:MetricSource:source1", "urn:ngsi-ld:Prometheus:1", "urn:ngsi-ld:Endpoint:1")

createEntities(ngsi, "urn:ngsi-ld:Metric:2", "urn:ngsi-ld:MetricSource:source2")
