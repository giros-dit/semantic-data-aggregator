from json import loads
from kafka import KafkaConsumer
from semantic_tools.clients.ngsi_ld import ngsildClient
from threading import Thread


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
        message = message.value
        sample = {
            "sample": {
                "type": "Property",
                "value": message['value'][1],
                "observedAt": message['value'][0]  # [Timestamp, value]
            }
        }
        ngsi.updateEntityAttrs(metric_id, sample)
        print(ngsi.retrieveEntityById(metric_id))  # DELETEME: Used for debugging.


if __name__ == '__main__':
    # Init NGSI-LD API Client
    ngsi = ngsildClient(context="https://pastebin.com/raw/NhZbzu8f")
    # Open threads for Kafka consumers
    topic = "metric-topic"
    metric_id = "urn:ngsi-ld:Metric:1"
    thread = createKafkaConsumerThread(topic, metric_id, ngsi)
    thread.join()  # Wait for thread to end
