from semantic_tools.models.prometheus_entities import Metric, MetricSource, Endpoint, Prometheus
from semantic_tools.clients.ngsi_ld import ngsildClient
from semantic_tools.clients.kafka_connect import kafkaConnectClient
from kafka import KafkaConsumer
from threading import Thread
from datetime import datetime
import json
from json import loads

#Get Entity attributes for the Kafka SourceConnector configuration
def getSourceConnectorConfig(ngsi: ngsildClient, metricsource_id: str):
    configuration = {}
    metricsource_entity=ngsi.retrieveEntityById(metricsource_id)
    metricsource=MetricSource.parse_obj(metricsource_entity)

    configuration['query'] = metricsource.name.value

    if("expression" in metricsource_entity):

      expression = metricsource.expression.value

      labels=""
      expression_keys = []
      expression_values = []

      for key in expression.keys():
        expression_keys.append(key)

      for value in expression.values():
        expression_values.append(value)

      cont=0
      for x in range(0,len(expression_keys)):
        labels+=expression_keys[x]+"="+'"'+expression_values[x]+'"'
        cont=cont+1
        if(cont < len(expression_keys)):
          labels+=", "

      configuration['expression'] = labels

    configuration['interval'] = metricsource.interval.value

    configuration['class'] = metricsource.javaclass.value

    configuration['topic'] = metricsource.topic.value

    endpoint_id = metricsource.hasEndPoint.object

    endpoint_entity=ngsi.retrieveEntityById(endpoint_id)
    endpoint=Endpoint.parse_obj(endpoint_entity)

    configuration['URI'] = endpoint.URI.value

    return configuration

def createKafkaConsumerThread(topic: str,
                              metric_id: str,
                              ngsi: ngsildClient) -> Thread:
    thread = Thread(target=subscribeKafka,
                    args=(topic, metric_id, ngsi),
                    daemon=True)
    thread.start()
    return thread

def subscribeKafka(topic: str, metric_id: str, ngsi: ngsildClient):
    # Config Kafka Consumer
    consumer = KafkaConsumer(
                topic,
                bootstrap_servers=['kafka:9092'],
                auto_offset_reset='earliest',
                enable_auto_commit=True,
                group_id='my-group',
                value_deserializer=lambda x: loads(x.decode('utf-8')))

    # Subscribe to new messages in metric-topic
    for message in consumer:
        try:
            message_value = message.value
            value = message_value['value']
            value = json.loads(value)
            data = value['data']
            result = data['result']
            metric = result[0]
            timestamp = metric['value'][0]
            datetimestamp = datetime.fromtimestamp(timestamp).isoformat()
            sample = {
                "sample": {
                    "type": "Property",
                    "value": metric['value'][1],
                    "observedAt": datetimestamp  # [timestamp, value]
                }
            }
            ngsi.updateEntityAttrs(metric_id, sample)
            # DELETEME: Used for debugging.
            print("METRIC", metric_id,":", ngsi.retrieveEntityById(metric_id))
            print("")

        except IndexError:
            continue

if __name__ == '__main__':

    # Init NGSI-LD API Client
    ngsi = ngsildClient(url="http://scorpio:9090",
                    headers={"Accept": "application/ld+json"},
                    context="http://context-catalog:8080/prometheus-context.jsonld")

    # Init Kafka Connect API Client
    kafka_connect = kafkaConnectClient(url="http://kafka-connect:8083")

    connect=kafka_connect.getAPIConnect()

    print("API Connect: ", connect)

    print("")

    connect_plugins=kafka_connect.getConnectorsPlugins()

    print("API Connect Plugins: ", connect_plugins)

    print("")

    connectors=kafka_connect.getConnectors()

    print("Kafka Connectors before: ", connectors)

    print("")

    metricsource_entities = ngsi.queryEntities(type="MetricSource")

    for i in range(0, len(metricsource_entities)):
        connector_config = getSourceConnectorConfig(ngsi, metricsource_entities[i]['id'])
        url=""
        if("expression" in metricsource_entities[i]):
            url = connector_config['URI']+"?query="+connector_config['query']+"{"+connector_config['expression']+"}"
        else :
            url = connector_config['URI']+"?query="+connector_config['query']

        config = {
            "name": "prometheus-source-"+str(i+1),
            "config": {
                "connector.class": connector_config['class'],
                "tasks.max": 1,
                "http.request.url": url,
                "http.request.method": "GET",
                "http.request.headers": "Accept: application/json",
                "http.throttler.interval.millis": connector_config['interval'],
                "kafka.topic": connector_config['topic']
            }
        }
        kafka_connect.createConnector(config)

    connectors=kafka_connect.getConnectors()

    print("Kafka Connectors after: ", connectors)

    print("")

    threads = []
    for i in range(0, len(metricsource_entities)):
        metricsource=MetricSource.parse_obj(metricsource_entities[i])
        # Open threads for Kafka consumers
        topic = metricsource.topic.value
        metric_id = metricsource.isSourceOf.object
        print("Subscribing to metric", metric_id, "in topic", topic,"...")
        print("")
        thread = createKafkaConsumerThread(topic, metric_id, ngsi)
        threads.append(thread)

    for t in threads:
        t.join()


