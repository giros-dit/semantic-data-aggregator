from semantic_tools.clients.ngsi_ld import ngsildClient
from semantic_tools.clients.kafka_connect import kafkaConnectClient
from semantic_tools.models.prometheus_entities import MetricSource
from kafka import KafkaClient, KafkaAdminClient, KafkaConsumer
from kafka.errors import KafkaError
import time

# Init NGSI-LD API Client
ngsi = ngsildClient(url="http://scorpio:9090", headers={"Accept": "application/ld+json"}, context="http://context-catalog:8080/prometheus-context.jsonld")

# Init Kafka Connect API Client
kafka_connect_client = kafkaConnectClient(url="http://kafka-connect:8083")

# Init Kafka Admin API Client
kafka_admin_client = KafkaAdminClient(bootstrap_servers="kafka:9092")

# Init Kafka Consumer API to get topics
kafka_consumer = KafkaConsumer(bootstrap_servers="kafka:9092")

# Init Kafka Client for asynchronous request/response operations
kafka_client = KafkaClient(bootstrap_servers="kafka:9092")

# Query MetricSource NGSI-LD Entities
metricsource_entities = ngsi.queryEntities(type="MetricSource")

# A list of Kafka connector topic names to delete
connector_topics = []

print("Kafka connectors before deletion:", kafka_connect_client.getConnectors())
print("")

print("Deleting Kafka Connectors...")
print("")
for i in range(0, len(metricsource_entities)):
    metricsource_entity=ngsi.retrieveEntityById(metricsource_entities[i]['id'])
    metricsource=MetricSource.parse_obj(metricsource_entity)
    connector_topics.append(metricsource.topic.value)
    kafka_connect_client.deleteConnector("prometheus-source-"+str(i+1))

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

