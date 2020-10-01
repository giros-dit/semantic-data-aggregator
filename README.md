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
