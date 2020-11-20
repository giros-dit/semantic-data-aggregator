from json import loads
from kafka import KafkaConsumer
from semantic_tools.clients.ngsi_ld import ngsildClient
from threading import Thread
from datetime import datetime
import json

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
                "observedAt": datetimestamp  # [Timestamp, value]
            }
        }
        ngsi.updateEntityAttrs(metric_id, sample)
        # DELETEME: Used for debugging.
        print("METRIC", metric_id,":", message)
        print("")
        print("METRIC", metric_id,":", metric)
        print("")
        print("METRIC", metric_id,":", ngsi.retrieveEntityById(metric_id))
        print("")

if __name__ == '__main__':
    # Init NGSI-LD API Client
    ngsi = ngsildClient(context="https://pastebin.com/raw/NhZbzu8f")
    # Open threads for Kafka consumers
    topic1 = "source1-topic"
    metric1_id = "urn:ngsi-ld:Metric:1"
    topic2 = "source2-topic"
    metric2_id = "urn:ngsi-ld:Metric:2"
    thread1 = createKafkaConsumerThread(topic1, metric1_id, ngsi)
    #thread1.join()  # Wait for thread to end
    thread2 = createKafkaConsumerThread(topic2, metric2_id, ngsi)
    thread1.join()  # Wait for thread to end
    thread2.join()  # Wait for thread to end

