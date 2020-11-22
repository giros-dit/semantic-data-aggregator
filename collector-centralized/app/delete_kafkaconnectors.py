from semantic_tools.clients.ngsi_ld import ngsildClient
from semantic_tools.clients.kafka_connect import kafkaConnectClient
from semantic_tools.models.prometheus_entities import MetricSource
from kafka import KafkaAdminClient

# Init NGSI-LD API Client
ngsi = ngsildClient(url="http://scorpio:9090", headers={"Accept": "application/ld+json"}, context="http://context-catalog:8080/prometheus-context.jsonld")

# Init Kafka Connect API Client
kafka_connect_client = kafkaConnectClient(url="http://kafka-connect:8083")

# Init Kafka Admin API Client
kafka_admin_client = KafkaAdminClient(bootstrap_servers="kafka:9092")

# Query MetricSource NGSI-LD Entities
metricsource_entities = ngsi.queryEntities(type="MetricSource")

# A list of topic names to delete
topics = []

for i in range(0, len(metricsource_entities)):
    metricsource_entity=ngsi.retrieveEntityById(metricsource_entities[i]['id'])
    metricsource=MetricSource.parse_obj(metricsource_entity)
    topics.append(metricsource.topic.value)
    kafka_connect_client.deleteConnector("prometheus-source-"+str(i+1))

kafka_admin_client.delete_topics(topics)

