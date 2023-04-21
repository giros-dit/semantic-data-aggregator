# Measuring Latency in Any SDA Component

This directory contains the templates of Kubernetes in charge of deploying the applications needed to measure the latency of the different componentes of the SDA.

# Table of Contents

1. [event-generator](#event-generator)
2. [FlinkLatencyA and FlinkLatencyB](#flinklatencya-and-flinklatencyb)

## event-generator
This application it is used to write information into a Kafka topic with certain rate, the application has two parameters
- `-b`, `--broker` to select the kafka broker: `kafka-service:9092`
- `-d`, `--delay` to choose the delay between events are written into the kafka topic: `1` (for 1 event per second rate)

The application has been developed as a python web server that lets the user send two more parameters:
- The file where the events are going to be read to later be written into the topic
- The topic where the events are going to be written

This two parameters are passed using a GET request, if the python web server is deployed in the IP `py-web-ip` with port `31002` a request using the file `pipeline-flows/flows.txt` and writting into the topic `flows` can be requested using:
```
http://py-web-ip:31002/?filepath=/app/pipeline-flows/flows.txt&topic=flows
```

## FlinkLatencyA and FlinkLatencyB
Supossing we want to measure the Latency of the application `netflowAggregator`, this app reads from topic `bidi-flows` and write into the topic `netflow-aggregated`. 

- `FlinkLatencyA` will be configured to read from `bidi-flows`, write as second output to `topicStart` and to write into `topicA`.
- `netflowAggregator` now needs to read from `topicA` and not `bidi-flows`.
- Finally `FlinkLatencyB` will read from `bidi-flows` and write into the topic `topicEnd`
- Both applications will write into `topicStart` and `topicEnd` the event with a timestamp when the message is received, so to measure the latency, both events in the topics must be correlated using the source and destination IPs and port with the first-switched and last-switched fields
