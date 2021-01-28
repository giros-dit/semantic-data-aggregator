import unittest

from semantic_tools.clients.ngsi_ld import NGSILDClient
from semantic_tools.models.metric import MetricSource, Endpoint


class TestScorpio(unittest.TestCase):
    def setUp(self):
        self.ngsi = NGSILDClient(
                        url="http://localhost:9090",
                        headers={"Accept": "application/json"},
                        context="http://context-catalog:8080/context.jsonld")
        self.entities = []

    def test_check_scorpio_status(self):
        self.assertTrue(self.ngsi.checkScorpioHealth())

    def test_create_entity(self):
        # Create Endpoint entity
        endpoint = Endpoint(
                        id="urn:ngsi-ld:Endpoint:1",
                        name={"value": "prometheus-api"},
                        uri={"value": "http://prometheus:9090/api/v1/query"})

        self.ngsi.createEntity(endpoint.dict(exclude_none=True))

        # Get Endpoint entity by id
        response = self.ngsi.retrieveEntityById(
                                entityId="urn:ngsi-ld:Endpoint:1")

        self.assertIsNotNone(Endpoint.parse_obj(response))

    def test_delete_entity(self):
        # Delete Endpoint entity
        self.assertTrue(self.ngsi.deleteEntity(
                entityId="urn:ngsi-ld:Endpoint:1"))

    def test_create_demo_entities(self):
        # Create Endpoint entity
        endpoint = Endpoint(
                        id="urn:ngsi-ld:Endpoint:1",
                        name={"value": "prometheus-api"},
                        uri={"value": "http://prometheus:9090/api/v1/query"})
        # Create MetricSource entities
        metricsource1 = MetricSource(
                id="urn:ngsi-ld:MetricSource:1",
                name={"value": "prometheus_http_requests_total"},
                expression={"value": {"job": "prometheus",
                                      "handler": "/api/v1/query"}},
                interval={"value": "10000", "unitCode": "C26"},
                hasEndpoint={"object": "urn:ngsi-ld:Endpoint:1"})

        metricsource2 = MetricSource(
                id="urn:ngsi-ld:MetricSource:2",
                # name={"value": "rate(node_network_receive_bytes_total[1m])"},
                name={"value": "node_network_transmit_packets_total"},
                interval={"value": "10000", "unitCode": "C26"},
                hasEndpoint={"object": "urn:ngsi-ld:Endpoint:1"})

        metricsource3 = MetricSource(
                id="urn:ngsi-ld:MetricSource:3",
                name={"value": "node_load1"},
                expression={"value": {"job": "node-1"}},
                interval={"value": "60000", "unitCode": "C26"},
                hasEndpoint={"object": "urn:ngsi-ld:Endpoint:1"})

        metricsource4 = MetricSource(
                id="urn:ngsi-ld:MetricSource:4",
                name={"value": "node_load1"},
                expression={"value": {"job": "node-2"}},
                interval={"value": "60000", "unitCode": "C26"},
                hasEndpoint={"object": "urn:ngsi-ld:Endpoint:1"})

        entities = [
            endpoint,
            metricsource1,
            metricsource2,
            metricsource3,
            metricsource4
        ]
        for entity in entities:
            # Create MetricSource entity
            self.ngsi.createEntity(entity.dict(exclude_none=True))
            # Get MetricSource entity by ID
            response = self.ngsi.retrieveEntityById(entityId=entity.id)
            self.assertIsNotNone(MetricSource.parse_obj(response))

    def test_delete_demo_entities(self):
        entities = [
            "urn:ngsi-ld:Endpoint:1",
            "urn:ngsi-ld:MetricSource:1",
            "urn:ngsi-ld:MetricSource:2",
            "urn:ngsi-ld:MetricSource:3",
            "urn:ngsi-ld:MetricSource:4",
        ]
        for entity in entities:
            self.assertTrue(self.ngsi.deleteEntity(entityId=entity))
