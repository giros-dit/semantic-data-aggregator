from kafka import KafkaProducer
from kafka.errors import KafkaError
from kafka.future import log
import json
import random
import time
import re
import csv
import sys

# usage: python3 publisher.py {broker_ip_address}:{port} {topic} {metrics_published}
# e.g.   python3 publisher.py 192.168.11.51:9092 uc.4.france_nice.infrastructure_metric.expb_metricId 10

def publish(producer, topic_name, metric_value, timestamp, unit, device_id, context):
    value = {'records': [{ 'value': { 'metric_value':(metric_value), 'timestamp':(timestamp), 'unit':(unit), 'device_id':(device_id), 'context':(context) }}]}
    print("Publishing in Kafka: %s", value)
    futures = producer.send(topic=topic_name, value=value)
    response = futures.get()
    print(response)

broker_ip_address = sys.argv[1]
topic_name = sys.argv[2]
device_id = "vnf-1"
unit = "random"
interval = "5s"
interval_value = int(re.compile('([0-9]+)([a-zA-Z]+)').match(interval).group(1))
interval_unit = re.compile('([0-9]+)([a-zA-Z]+)').match(interval).group(2)
context = "value1=1"

# Transform the interval_value consequently
if interval_unit == "s":
    interval_value = interval_value/1;
elif interval_unit == "ms":
    interval_value = interval_value/1000;

# In this example, metrics are generated randomly
print("Start capturing metrics")

producer = KafkaProducer(bootstrap_servers = broker_ip_address, value_serializer=lambda x: json.dumps(x).encode('utf-8'))

n_iter=int(sys.argv[3]) # for avoiding an infinite loop
for i in range(n_iter):
    print("Metric value ", i+1)
    metric_value = random.uniform(-2,2)
    timestamp = time.time()
    publish(producer, topic_name, metric_value, timestamp, unit, device_id, context)
    time.sleep(interval_value)

print("Script finished")
