from semantic_tools.clients.ngsi_ld import NGSILDClient
from semantic_tools.models.metric import MetricSource, Endpoint

import logging

logging.basicConfig(
        level=logging.INFO,
        format="%(asctime)s - %(name)s - %(levelname)s - %(message)s")
logger = logging.getLogger(__name__)


def createEntities(ngsi: NGSILDClient):
    # Create MetricSource entities
    metricsource1 = MetricSource(
            id="urn:ngsi-ld:MetricSource:1",
            name={"value": "prometheus_http_requests_total"},
            expression={"value": {"job": "prometheus",
                                  "handler": "/api/v1/query"}},
            interval={"value": "10000", "unitCode": "ms"},
            hasEndPoint={"object": "urn:ngsi-ld:Endpoint:1"})

    ngsi.createEntity(metricsource1.dict(exclude_none=True))

    metricsource2 = MetricSource(
            id="urn:ngsi-ld:MetricSource:2",
            name={"value": "rate(node_network_receive_bytes_total[1m])"},
            interval={"value": "60000", "unitCode": "ms"},
            hasEndPoint={"object": "urn:ngsi-ld:Endpoint:1"})

    ngsi.createEntity(metricsource2.dict(exclude_none=True))

    # Query MetricSource entities
    entities = ngsi.queryEntities(type="MetricSource")
    for entity in entities:
        metricsource = MetricSource.parse_obj(entity)
        logger.info("\n" + metricsource.json(indent=4,
                                             sort_keys=True,
                                             exclude_unset=True))

    # Create Endpoint entity
    endpoint = Endpoint(id="urn:ngsi-ld:Endpoint:1",
                        name={"value": "prometheus-api"},
                        uri={"value": "http://prometheus:9090/api/v1/query"})

    ngsi.createEntity(endpoint.dict(exclude_none=True))

    # Get Endpoint entity by id
    response = ngsi.retrieveEntityById(entityId="urn:ngsi-ld:Endpoint:1")
    endpoint = Endpoint.parse_obj(response)
    logger.info("\n" + endpoint.json(indent=4,
                                     sort_keys=True,
                                     exclude_unset=True))


if __name__ == '__main__':
    ngsi = NGSILDClient(url="http://scorpio:9090",
                        headers={"Accept": "application/json"},
                        context="http://context-catalog:8080/context.jsonld")

    createEntities(ngsi)
