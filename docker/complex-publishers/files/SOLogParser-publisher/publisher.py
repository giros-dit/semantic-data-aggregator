from kafka import KafkaProducer
from kafka.errors import KafkaError
from kafka.future import log
import json
import random
import time
import re
import csv
import sys

# usage: python3 publisher.py {broker_ip_address}:{port} {topic} {number_logs_published} {metrics_sample_input}
# e.g.   python3 publisher.py kafka:9092 logparser-topic-1 10 instantiation-metrics-sample-input.json

def publish(producer, topic_name):
    with open(sys.argv[4]) as json_file:
    	log = json.load(json_file)
    print("Publishing in Kafka: %s", log)
    futures = producer.send(topic=topic_name, value=log)
    response = futures.get()
    print(response)

broker_ip_address = sys.argv[1]
topic_name = sys.argv[2]
interval = "5s"
interval_value = int(re.compile('([0-9]+)([a-zA-Z]+)').match(interval).group(1))
interval_unit = re.compile('([0-9]+)([a-zA-Z]+)').match(interval).group(2)

# Transform the interval_value consequently
if interval_unit == "s":
    interval_value = interval_value/1;
elif interval_unit == "ms":
    interval_value = interval_value/1000;

# In this example, logs are generated statically
print("Start capturing logs")

producer = KafkaProducer(bootstrap_servers = broker_ip_address, value_serializer=lambda x: json.dumps(x).encode('utf-8'))

n_iter=int(sys.argv[3]) # for avoiding an infinite loop
for i in range(n_iter):
    publish(producer, topic_name)
    time.sleep(interval_value)

print("Script finished")
