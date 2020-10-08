# Prometheus-collector

Testing prototype that deploys Prometheus and a metrics collector using Python Starlette. Prometheus is configured just to scrape itself. Two types of collectors are available: query-based and telemetry-based.

## Telemetry-based collector

Prometheus pushes metrics values as snappy-compressed protobuf messages to an HTTP endpoint. Thus far, collector server uncompresses and decodes protobuf messages. Then it prints the first timeseries for each write request message. Note a remote write request message from Prometheus may contain multiple timeseries messages.

```bash
docker-compose -f docker-compose-telemetry.yml
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

### Comments

We use [jaegertracing/docker-protobuf](https://github.com/jaegertracing/docker-protobuf)  image to compile the Prometheus protobuf definitions. Code is already available in the repository. The following commands are left as a reminder when compiling protobuf in future works:

```bash
PROMPB=${PWD}/prometheus/prompb

docker run --rm -u $(id -u) -v ${PROMPB}:${PROMPB} -w ${PROMPB} jaegertracing/protobuf:latest --proto_path=${PROMPB} --python_out=${PROMPB} -I/usr/include/github.com/gogo/protobuf ${PROMPB}/*.proto
```

## Query-based collector

This collector follows a polling approach by requesting Prometheus metrics through the Prometheus HTTP API. The collector runs a Starlette web server that enables collecting either the current value of metrics or the value of metrics throughout a given time range. The collector process the parameters of the request and retrieves the metric values from Prometheus HTTP API. The web server leverages [prometheus-api-client-python](https://github.com/AICoE/prometheus-api-client-python) library to interact with the Prometheus HTTP API.

```bash
docker-compose -f docker-compose-query.yml
```

After requesting Prometheus, the found metric values are printed as tables in the collector logs. The reason for this decision is because prometheus-api-client-python library manages Prometheus metric values as Panda DataFrames.

```
query-collector_1  |              code   handler        instance         job value
query-collector_1  | timestamp                                                    
query-collector_1  | 1.602162e+09  200  /metrics  localhost:9090  prometheus    28
query-collector_1  | 1.602162e+09  200  /metrics  localhost:9090  prometheus    29
query-collector_1  | 1.602162e+09  200  /metrics  localhost:9090  prometheus    30
query-collector_1  | 1.602162e+09  200  /metrics  localhost:9090  prometheus    31
query-collector_1  | 1.602162e+09  200  /metrics  localhost:9090  prometheus    32
query-collector_1  | 1.602162e+09  200  /metrics  localhost:9090  prometheus    33
query-collector_1  | 1.602162e+09  200  /metrics  localhost:9090  prometheus    34
query-collector_1  | 1.602162e+09  200  /metrics  localhost:9090  prometheus    35
query-collector_1  | 1.602162e+09  200  /metrics  localhost:9090  prometheus    36
query-collector_1  | 1.602162e+09  200  /metrics  localhost:9090  prometheus    37
query-collector_1  | 1.602162e+09  200  /metrics  localhost:9090  prometheus    38
query-collector_1  | 1.602162e+09  200  /metrics  localhost:9090  prometheus    39
query-collector_1  | 1.602162e+09  200  /metrics  localhost:9090  prometheus    40
query-collector_1  | 1.602162e+09  200  /metrics  localhost:9090  prometheus    41
query-collector_1  | 1.602162e+09  200  /metrics  localhost:9090  prometheus    42
query-collector_1  | 1.602162e+09  200  /metrics  localhost:9090  prometheus    43
query-collector_1  | 1.602163e+09  200  /metrics  localhost:9090  prometheus    44
query-collector_1  | 1.602163e+09  200  /metrics  localhost:9090  prometheus    45
query-collector_1  | 1.602163e+09  200  /metrics  localhost:9090  prometheus    46
query-collector_1  | 1.602163e+09  200  /metrics  localhost:9090  prometheus    47
query-collector_1  | 1.602163e+09  200  /metrics  localhost:9090  prometheus    48
query-collector_1  | 1.602163e+09  200  /metrics  localhost:9090  prometheus    49
query-collector_1  | INFO:     172.18.0.1:36252 - "GET /metrics_range?metric_name=prometheus_http_requests_total&job=prometheus&range=30&handler=/metrics HTTP/1.1" 200 OK
```


### GET current value

To get the current value of metric(s) send request as follows:
```
curl -X GET "http://localhost:80/metrics?metric_name=<name_of_metric>&<label_1>=<label_1_value>&<label_2>=<label_2_value>..."
```

Example:
```
curl -X GET "http://localhost:80/metrics?metric_name=prometheus_http_requests_total&job=prometheus"
```

### GET range value

To get the value of metric(s) during a given range of time send request as follows:
```
curl -X GET "http://localhost:80/metrics_range?metric_name=<name_of_metric>&range=<minutes>&<label_1>=<label_1_value>&<label_2>=<label_2_value>..."
```

Example:
```
curl -X GET "http://localhost:80/metrics_range?metric_name=prometheus_http_requests_total&range=30&job=prometheus"
```