# Prometheus-collector

Testing prototype that deploys Prometheus and a metrics collector using Python Starlette. Prometheus is configured just to scrape itself and forward metrics to the collector. 

Prometheus metrics are sent as snappy-compressed protobuf messages to an HTTP endpoint. Thus far, collector server uncompresses messages and prints protobuf directly.

**TODO**: Compile Prometheus protobuf definitions and generate Python metaclasses. Decode protobuf messages and print them nicely.

## Quick Start
docker-compose up
