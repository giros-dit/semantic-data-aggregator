# Prometheus-collector

Testing prototype that deploys Prometheus and a metrics collector using Python Starlette. Prometheus is configured just to scrape itself and forward metrics to the collector. 

Prometheus metrics are sent as snappy-compressed protobuf messages to an HTTP endpoint. Thus far, collector server uncompresses and decodes protobuf messages. Then it prints the first timeseries for each write request message. Note a remote write request message from Prometheus may contain multiple timeseries messages.

## Protobuf compilation

We use [jaegertracing/docker-protobuf](https://github.com/jaegertracing/docker-protobuf)  image to compile the Prometheus protobuf definitions.

```bash
PROMPB=${PWD}/prometheus/prompb

docker run --rm -u $(id -u) -v ${PROMPB}:${PROMPB} -w ${PROMPB} jaegertracing/protobuf:latest --proto_path=${PROMPB} --python_out=${PROMPB} -I/usr/include/github.com/gogo/protobuf ${PROMPB}/*.proto
```

## Quick Start
```bash
docker-compose up
```

Then the logs from prometheus and the metrics collector will be shown on the screen. Collector prints the first time series sample of every write request that is received from prometheus:

```
collector_1   | INFO:     172.19.0.1:33956 - "POST /metrics HTTP/1.1" 200 OK
collector_1   | labels {
collector_1   |   name: "__name__"
collector_1   |   value: "prometheus_tsdb_reloads_total"
collector_1   | }
collector_1   | labels {
collector_1   |   name: "instance"
collector_1   |   value: "localhost:9090"
collector_1   | }
collector_1   | labels {
collector_1   |   name: "job"
collector_1   |   value: "prometheus"
collector_1   | }
collector_1   | labels {
collector_1   |   name: "monitor"
collector_1   |   value: "codelab-monitor"
collector_1   | }
collector_1   | samples {
collector_1   |   value: 1.0
collector_1   |   timestamp: 1601969704965
collector_1   | }
```
