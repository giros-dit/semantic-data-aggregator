from semantic_tools.clients.ngsi_ld import NGSILDClient


def deleteEntities(ngsi: NGSILDClient):

    ngsi.deleteEntity("urn:ngsi-ld:MetricSource:1")
    ngsi.deleteEntity("urn:ngsi-ld:MetricSource:2")
    ngsi.deleteEntity("urn:ngsi-ld:Endpoint:1")


if __name__ == '__main__':
    ngsi = NGSILDClient(url="http://scorpio:9090",
                        headers={"Accept": "application/ld+json"},
                        context="http://context-catalog:8080/context.jsonld")
    deleteEntities(ngsi)
