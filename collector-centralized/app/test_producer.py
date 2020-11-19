from json import dumps
from kafka import KafkaProducer
from kafka.errors import KafkaError
from prometheus_api_client import PrometheusConnect

# This an example Kafka Producer that obtains a metric
# from Prometheus HTTP API and writes the JSON
# into a Kafka topic named "metric-topic"
# Use this code as a reference when trying KafkaProducer out

# Config Kafka Producer
producer = KafkaProducer(bootstrap_servers=['kafka:9092'],
                         value_serializer=lambda x:
                         dumps(x).encode('utf-8'))
# Config Prometheus API Client
prom = PrometheusConnect(url="http://prometheus:9090", disable_ssl=True)

# Fetch one metric for the example
metric_name = "prometheus_http_requests_total"
metric_data = prom.get_current_metric_value(
    metric_name=metric_name)
# Take the first time series for this metric
metric = metric_data[0]
future = producer.send('metric-topic', value=metric)

# Block for 'synchronous' sends
try:
    record_metadata = future.get(timeout=10)
except KafkaError:
    # Decide what to do if produce request failed...
    pass