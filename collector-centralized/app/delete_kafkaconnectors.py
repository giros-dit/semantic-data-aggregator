from semantic_tools.clients.ngsi_ld import NGSILDClient
from semantic_tools.clients.kafka_connect import KafkaConnectClient
from semantic_tools.models.metric import MetricSource
from kafka import KafkaClient, KafkaAdminClient, KafkaConsumer
from kafka.errors import KafkaError
import time

# Init NGSI-LD API Client
ngsi = NGSILDClient(url="http://scorpio:9090",
                    headers={"Accept": "application/ld+json"},
                    context="http://context-catalog:8080/context.jsonld")

# Init Kafka Connect API Client
kafka_connect_client = KafkaConnectClient(url="http://kafka-connect:8083")

# Init Kafka Admin API Client
kafka_admin_client = KafkaAdminClient(bootstrap_servers="kafka:9092")

# Init Kafka Consumer API to get topics
kafka_consumer = KafkaConsumer(bootstrap_servers="kafka:9092")

# Init Kafka Client for asynchronous request/response operations
kafka_client = KafkaClient(bootstrap_servers="kafka:9092")

# Query MetricSource NGSI-LD Entities
metricSources = [
    MetricSource.parse_obj(x) for x in ngsi.queryEntities(
        type="MetricSource")
]

# A list of Kafka connector topic names to delete
connector_topics = []

print("Kafka connectors before deletion:", kafka_connect_client.getConnectors())
print("")

print("Deleting Kafka Connectors...")
print("")
for metricSource in metricSources:
    entity_id = metricSource.id.strip("urn:ngsi-ld:").replace(":", "-").lower()
    connector_topics.append(entity_id)
    kafka_connect_client.deleteConnector("prometheus-{0}".format(entity_id))

print("Kafka connectors after deletion:", kafka_connect_client.getConnectors())
print("")

time.sleep(2)

total_topics = kafka_consumer.topics()
print("Total Kafka topics before Kafka Connector topics deletion:", total_topics)
print("")

print("Deleting Kafka Connector topics...")
print("")

try:
    kafka_admin_client.delete_topics(connector_topics)
    future = kafka_client.cluster.request_update()
    kafka_client.poll(future=future)
    time.sleep(2)
except KafkaError:
    pass

total_topics = kafka_consumer.topics()

try:
    kafka_admin_client.delete_topics(connector_topics)
    future = kafka_client.cluster.request_update()
    kafka_client.poll(future=future)
    time.sleep(2)
except KafkaError:
    pass

total_topics = kafka_consumer.topics()
print("Total Kafka topics after Kafka Connector topics deletion:", total_topics)
print("")

