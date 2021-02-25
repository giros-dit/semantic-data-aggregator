# Semantic Data Aggregator

Docker-based prototype that deploys the semantic data aggregator.

The aggregator is composed of three main elements: the context broker, the context registry, and the data fabric. The first two elements are represented by the Scorpio Broker while the latter is a combination of agents that ingest/deliver data to/from the so-called data substrate represented by Kafka. These agents are dynamically configured by the weaver which subscribes to the context broker for news definitions of data sources and data consumers.

The weaver leverages Apache NiFi to distribute data among data sources and data consumers that are attached to the semantic data aggregator. NiFi enables the definition of graph flows that implement data ingestion mechanisms such as retrieving Prometheus metrics by polling Prometheus REST API, or data delivery mechanisms such as fetching data from Kafka and sending it out to HTTP-based data consumers.

Kafka plays the role of the data substrate in the semantic data aggregator, hence NiFi relies on Kafka as the distributed reliable storage system to read/write data in the defined graph flows. For the sake of simplicity, the weaver configures NiFi processors to connect to the same Kafka instance that Scorpio runs for its internal communication bus.

Additionally, a dummy context consumer is deployed based on a simple docker image that runs a FastAPI webserver. Basically, the webserver includes an additional REST endpoint that the context consumer can leverage to receive notifications from HTTP subscriptions.

![Docker Prototype](docs/data-aggregator-prototype.png)

# Requirements

- Docker (_Tested with version 19.03.13_)
- Docker-compose (_Tested with version 1.27.4_)

# Quick Start

## Build Docker images

The prototype is composed by some containers which require building their Docker images. Utility script `build-docker.sh` eases the process of building such images. So far, it only supports building the `schema-registry:0.9.0` image. To do so, run the following command:
```bash
./build-docker.sh build-registry
```
It is expected for future works to support more building procedures for images such as `weaver`.

## Run the prototype

Start the prototype by running docker-compose:
```bash
docker-compose up
```

In case you are interested in running the prototype in background (kafka or scorpio logs may be annoying), use the following command:
```bash
docker-compose up -d
```

If you are interested to run the gNMI-based data collection prototype, follow the next steps:

1) Before starting docker-compose it is necessary to import the Arista cEOS router docker image. Specifically, the scenario uses one of the latest available versions "cEOS-lab-4.24.4M.tar". Download it first from the [Arista software section](https://www.arista.com/en/support/software-download) (it is the non-64-bit version).

2) The command to import the image is:
```bash
docker import cEOS-lab-4.24.4M.tar ceos-image:4.24.4M
```

3) Then you can start the docker-compose:
```bash
docker-compose -f docker-compose-arista.yml up
```

The purpose of this prototype is collect data of [gNMI](https://github.com/openconfig/reference/blob/master/rpc/gnmi/gnmi-specification.md) sources from the semantic data aggregator. For this proof of concept with gNMI data sources, the prototype has two main resources: docker instances of [Arista cEOS routers](https://www.arista.com/en/products/software-controlled-container-networking) as network devices and YANG-based data sources that support the gNMI management protocol and a CLI client that provides a full support of gNMI RPCs called [gNMIc](https://gnmic.kmrd.dev/) to request the configuration and operational status from these telemetry-based network devices.

To get a fine-grained view on how to extract telemetry information of Arista cEOS routers using the gNMIc client from our semantic data aggregator, follow the [`gNMI Telemetry Proof of Concept Recipe`](semantic-data-aggregator/new/gnmi/docs/gnmi-PoC-recipe/README.md).


# Postman Collections

This repository contains Postman collections that you can use to play with the REST APIs of some of the components present in the prototype. We recommend downloading [Postman Desktop](https://www.postman.com/downloads/) for an better user experience.

- [NGSI-LD Prometheus and gNMI.postman_collection.json](postman_collections/NGSI-LD%20Prometheus%20and%20gNMI.postman_collection.json) is a set of requests that can be used to interact with the [NGSI-LD Scorpio Broker](https://github.com/ScorpioBroker/ScorpioBroker). This collection includes most of the Entity, Subscription, and Context Source operations that are commonly used in NGSI-LD. The requests contained in this collection can be utilized with other NGSI-LD compliant broker such as [Orion-LD](https://github.com/FIWARE/context.Orion-LD) or [Stellio](https://github.com/stellio-hub/stellio-context-broker).

- [API REST Flink.postman_collection.json](postman_collections/API%20REST%20Flink.postman_collection.json) provides example requests for the supported operations in [Apache Flink REST API](https://ci.apache.org/projects/flink/flink-docs-release-1.12/ops/rest_api.html).
