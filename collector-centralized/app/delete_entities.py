from semantic_tools.clients.ngsi_ld import ngsildClient

def deleteEntities(ngsi: ngsildClient):

    ngsi.deleteEntity("urn:ngsi-ld:Metric:1")

    ngsi.deleteEntity("urn:ngsi-ld:Metric:2")

    ngsi.deleteEntity("urn:ngsi-ld:Prometheus:1")

    ngsi.deleteEntity("urn:ngsi-ld:MetricSource:source1")

    ngsi.deleteEntity("urn:ngsi-ld:MetricSource:source2")

    ngsi.deleteEntity("urn:ngsi-ld:Endpoint:1")

ngsi = ngsildClient(url="http://scorpio:9090",
                    headers={"Accept": "application/ld+json"},
                    context="https://pastebin.com/raw/NhZbzu8f",
                    debug=True)
deleteEntities(ngsi)
