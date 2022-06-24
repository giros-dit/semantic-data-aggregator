# Semantic Data Aggregator

Docker-based prototype that deploys the `Semantic Data Aggregator (SDA)`.

> **IMPORTANT NOTE:**
>
> In 5G-CLARITY project this work is known as the `Data Semantics Fabric (DSF)`.

The aggregator is composed of three main elements: the context broker, the context registry, and the data fabric. The first two elements are represented by the [`Scorpio Broker`](https://github.com/ScorpioBroker/ScorpioBroker) while the latter is a combination of agents that ingest/deliver data to/from the so-called data substrate represented by [`Kafka`](https://kafka.apache.org/). These agents are dynamically configured by the `Weaver` which subscribes to the context broker for news definitions of data sources and data consumers.

The `Weaver` leverages [`Apache NiFi`](https://nifi.apache.org/) to distribute data among data sources and data consumers that are attached to the `Semantic Data Aggregator`. `NiFi` enables the definition of graph flows that implement data ingestion mechanisms such as retrieving [`Prometheus`](https://Prometheus.io/) metrics by polling `Prometheus` REST API, or data delivery mechanisms such as fetching data from `Kafka` and sending it out to HTTP-based `data consumers`.

`Kafka` plays the role of the data substrate in the `SDA`, hence `NiFi` relies on `Kafka` as the distributed reliable storage system to read/write data in the defined graph flows. For the sake of simplicity, the weaver configures `NiFi` processors to connect to the same Kafka instance that `Scorpio` runs for its internal communication bus.

![Architecture](docs/architecture/data-aggregator-architecture-D44-full.png)

# Table of Contents

1. [Prototype](#prototype)
    1. [Microservices](#microservices)
        1. [Weaver](#weaver)
        2. [Application Manager](#application-manager)
        3. [Context Catalog](#context-catalog)
        4. [Source Manager](#source-manager)
        5. [Complex Publisher](#complex-publisher)
        6. [Experimenter](#experimenter)
    2.  [Developing microservices using Poetry](#developing-microservices-using-poetry)

- [Semantic Data Aggregator](#semantic-data-aggregator)
- [Table of Contents](#table-of-contents)
- [Prototype](#prototype)
  - [Requirements](#requirements)
  - [Microservices](#microservices)
    - [Weaver](#weaver)
    - [Application-Manager](#application-manager)
    - [Context-Catalog](#context-catalog)
    - [Source-Manager](#source-manager)
    - [Complex-Publisher](#complex-publisher)
    - [Experimenter](#experimenter)
  - [Developing microservices using Poetry](#developing-microservices-using-poetry)
- [Scenarios](#scenarios)
  - [All-in-one Scenario](#all-in-one-scenario)
  - [Data Source Specific Scenarios](#data-source-specific-scenarios)
    - [Prometheus-based Data Sources](#prometheus-based-data-sources)
    - [gNMI-based Data Sources (Arista cEOS)](#gnmi-based-data-sources-arista-ceos)
    - [Kafka-based Data Sources](#kafka-based-data-sources)
- [SDA Orchestration](#sda-orchestration)
- [Stream Processing Aplications Management](#stream-processing-aplications-management)
- [Postman Collections](#postman-collections)
- [Acknowledgements](#acknowledgements)
- [License](#license)

# Prototype

## Requirements

- Docker (_Tested with version 19.03.13_)
- Docker-compose (_Tested with version 1.27.4_)
- Python 3.9
- [Poetry](https://python-poetry.org/docs/)

## Microservices

The SDA is composed of several microservices that are deployed as Docker containers. These microservices are briefly introduced in this section.

### Weaver

The [weaver](docker/weaver) container implements the orchestration of Apache NiFi flows and Apache Flink jobs by consuming context information defined in the Context Broker.

### Application-Manager

The [app-manager](docker/app-manager) container offers a catalog of applications that can be used in the SDA. Within the scope of the SDA, these applications are NiFi templates and Flink applications, i.e., JARs. The app-manager implements a REST API that facilitates the on-boarding of applications in the SDA. This REST API takes as input the type of application, a name, a description, and the file of the application. The app-manager gathers all this information and makes sure that the application is properly uploaded in the target runner - NiFi or Flink - and that the context associated to the application is created in the Context Broker.

The full OpenAPI specification is available [here](docs/architecture/microservices/app-manager/openapi.json).

### Context-Catalog

The [context-catalog](docker/context-catalog) container provides a static web server where JSON-LD vocabularies can be uploaded. Interactions with the NGSI-LD API can link to this webserver rather than appending the JSON-LD vocabulary in the request's body.

### Source-Manager

The [source-manager](docker/source-manager) utility automates the discovery of Kafka topics available in a 5G-EVE monitoring platform. The source-manager provides a REST API through which users can specify the address of 5G-EVE's Kafka Broker and a string filter - usecase - to discover Kafka topics with maching names. Using this information the source-manager sends a request to the DCM service in 5G-EVE. Lastly, those discovered topics are created as new context information within the Context Broker.

The full OpenAPI specification is available [here](docs/architecture/microservices/source-manager/openapi.json).

### Complex-Publisher

The [complex-publishers](docker/complex-publishers) utility allows for generating synthetic metrics compliant with 5G-EVE's data model or 5Gr-LogParser's data model.

### Experimenter

The [experimenter](docker/experimenter) container emulates an application that receives updates from Tasks running in the SDA. This container subscribes to the Context Broker in order to receive NGSI-LD notifications related with the Tasks. These notifications are sent to stdout. The experimenter container serves as a first step towards a GUI to visualize the status of Tasks running in the SDA.

## Developing microservices using Poetry

Most of SDA microservices are applications based on Python. To ease management of Python dependencies we rely on poetry tool. Poetry takes care of solving dependency conflicts and also configures virtual environments to develop our Python application.

The following guidelines are proposed to develop Python-based microservices for the SDA:

1. Create folder for your application following a structure similar to [this](docker/app-manager)
2. Start poetry project with `poetry init`
3. Init virtual environment  with `poetry env use python3.9`. This should create a folder `.venv`.
4. Activate virtual environment for development using `poetry shell`. Make sure venv is configured for your shell. In case this command fails, you must activate the environment manually by running `source <path to bin/activate script within venv>
5. Include dependencies running `poetry add <my-dependency>`
6. Install dependencies in your virtual environment with `poetry install`
7. Now you can start developing your application within a fully configured virtual environment

Once you are done with you application, we proposed a structure to containerize the application using a Dockerfile plus a  `docker-entrypoint.sh`.

- The Dockerfile is mostly generic for all microservices execpt for the `APP_NAME`variable. This container will copy poetry files to replicate your virtual environment within the Docker image.
- The `docker-entrypoint.sh` is the interesting part. This script works as wrapper to call your python application.
- When developing your containerized application, you can mount the application code's folder as done in the docker-compose files existing in this repository. For example [see](docker-compose.yml)

# Scenarios

## All-in-one Scenario

To deploy a scenario that comprises the SDA along with all the supported data sources, execute the following command:
```bash
docker-compose up
```

In case you are interested in running the prototype in background (`kafka` or `scorpio` logs may be annoying), use the following command:
```bash
docker-compose up -d
```

Tear the scenario down as follows:
```bash
docker-compose down
```

## Data Source Specific Scenarios

In addition to the all-in-one setup, this repository includes different docker-compose files that deploy scenarios in which the SDA is integrated with a specific data source. These scenarios come handy for debugging purposes and showcasing demos.

### Prometheus-based Data Sources

This scenario deploys a Prometheus instance that is integrated as a data source for the SDA. Additionally, two node-exporter microservices are deployed to emulate two separate machines that send metrics to Prometheus - although both node exporters collect data from the same machine (host machine).

1) Deploy the scenario with the following command:
```bash
docker-compose up  -f docker-compose-prometheus.yml -d
```

2) The [`MetricSource_Demo`](postman_collections/MetricSource_Demo.postman_collection.json) Postman collection provides a demo pipeline that periodically fetches a metric from Prometheus.

3) Lastly, tear the scenario down with:
```bash
docker-compose -f docker-compose-prometheus.yml down
```

### gNMI-based Data Sources (Arista cEOS)

If you are interested in running the gNMI-based data collection prototype, follow the next steps:

The purpose of this prototype is collect data of [`gNMI`](https://github.com/openconfig/reference/blob/master/rpc/gnmi/gnmi-specification.md) telemetry-based sources from the `Semantic Data Aggregator`. For this proof of concept with gNMI data sources, the prototype has two main resources: docker instances of `Arista cEOS` routers as network devices and YANG-based data sources that support the `gNMI` management protocol and a CLI client that provides a full support of `gNMI` RPCs called [`gNMIc`](https://gnmic.kmrd.dev/) to request the configuration and operational status from these telemetry-based network devices.

To get a fine-grained view on how to extract telemetry information of `Arista cEOS` routers using the `gNMIc` client from our semantic data aggregator, follow the [`gNMI Telemetry Proof of Concept Recipe`](docs/gnmi-telemetry-recipe/README.md).

1) Before starting docker-compose it is necessary to import the [`Arista cEOS`](https://www.arista.com/en/products/software-controlled-container-networking) router docker image. Specifically, the scenario uses one of the latest available Arista cEOS versions `cEOS-lab-4.27.4M`. Download it first from the [Arista software section](https://www.arista.com/en/support/software-download) (it is the 64-bit version).

2) The command to import the image is:
```bash
docker import cEOS64-lab-4.27.4M.tar.xz ceos-image:4.27.4M
```

3) Then you can start the docker-compose:
```bash
docker-compose -f docker-compose-arista.yml up
```

4) Run the utility script [ceos-startup.sh](docker/ceos-arista/ceos-startup.sh) to configure a simple network topology with two Arista cEOS devices interconnected through a single link::
```bash
cd docker/ceos-arista
./ceos-startup.sh
```
Following picture depicts the network topology and addressing:
![single-link-topology](docs/gnmi-telemetry-recipe/single-link-topology.png)

5) The [`TelemetrySource_Demo`](postman_collections/TelemetrySource_Demo.postman_collection.json) Postman collection provides a demo pipeline that subscribes to a YANG XPath of a network device through the gNMI protocol.

6) Tear the scenario down with:
```bash
docker-compose -f docker-compose-arista.yml down
```

### Kafka-based Data Sources

Kafka is another type of data source supported by the SDA. More precisely, the ICT-17 5G-EVE project represents a use case that provides monitoring data through Kafka. In this use case, the SDA enables interoperability by integrating a data source from an ICT-17 environment, and then aggregating and delivering the data to other domains such as 5Growth.

1) For the 5G-EVE use case, we simply re-use the same Kafka instance to work as both the data source and the SDA's data substrate. Let's deploy the all-in-one scenario:
```bash
docker-compose up  -d
```

2) The [`EVESource_Demo`](postman_collections/EVESource_Demo.postman_collection.json) Postman collection provides a demo pipeline that subscribes to a Kafka topic.

3) To generate synthetic data compliant with 5G-EVE data model, the [complex-publisher](docker/complex-publishers) microservice offers a utility that generates random data into a Kafka topic. For instance, execute the following command to generate 10 new metrics:
```bash
docker exec -it complex-publisher python3 /5GEVE-publisher/publisher.py kafka:9092 spain-5tonic.topic-1 10
```

4) Once you are done, tear the scenario down with:
```bash
docker-compose down
```


# SDA Orchestration

In order to orchestrate the life cycle management of the `Semantic Data Aggregator` by an external application or system, the prototype uses he `NGSI-LD` API like an interface that allows translating orders from an external orchestrator component to requests to the `SDA` and extending the `NGSI-LD` data model for that. This is an approach to orchestrate the state of those `NGSI-LD` entities that represent the different stages in the data pipelines and model the activity of the Data Aggregator agents.

To get a full view of the `SDA` orchestration process and to be able to build a full data pipeline for data aggregation, see [`Semantic Data Aggregator Orchestration`](docs/sda-orchestration/README.md).

# Stream Processing Aplications Management

The `SDA` makes use of the [`Apache Flink`](https://flink.apache.org/) engine, as part of its aggregation agent, to allow the execution of stream processing applications. The `SDA` allows to dynamically orchestrate the upload and submission of stream processing applications to the `Flink` engine in order to easily manage their execution.

For more information on how `SDA` internally manages the uploading and execution of stream processing applications, see [`Stream Processing Applications Management`](docs/stream-processing/README.md).

# Postman Collections

This repository contains Postman collections that you can use to play with the REST APIs of some of the components present in the prototype. We recommend downloading [Postman Desktop](https://www.postman.com/downloads/) for an better user experience.

- [`NGSI-LD API Orchestrator`](postman_collections/NGSI-LD%20API%20Orchestrator.postman_collection.json) Postman collection has a set of requests that can be used to interact with the [`NGSI-LD Scorpio Broker`](https://github.com/ScorpioBroker/ScorpioBroker) in order to model a full `NGSI-LD` datapipeline for data aggregation and orchestrate the life cycle of the entities involved in it, based on the `NGSI-LD` API. This collection includes most of the Entity, Subscription, and Context Source operations that are commonly used in `NGSI-LD`. The requests contained in this collection can be utilized with other `NGSI-LD` compliant broker such as [Orion-LD](https://github.com/FIWARE/context.Orion-LD) or [Stellio](https://github.com/stellio-hub/stellio-context-broker).

- [`Flink REST API`](postman_collections/API%20REST%20Flink.postman_collection.json) provides example requests for the supported operations in [Apache Flink REST API](https://ci.apache.org/projects/flink/flink-docs-release-1.12/ops/rest_api.html).

- [`YANG Catalog REST API`](postman_collections/yangcatalog.postman_collection.json) provides a collection of sample requests for interacting with the REST API of [YANG Catalog](https://yangcatalog.org). For more details, visit [Contribute to YANG Catalog](https://yangcatalog.org/contribute.html) webpage.

# Acknowledgements

[![eu-comission](docs/logos/european-comission-h2020.png)](https://ec.europa.eu/info/research-and-innovation/funding/funding-opportunities/funding-programmes-and-open-calls/horizon-2020_en)

This work has been partly funded by the European Union’s Research and Innovation Programme Horizon 2020 under the following Grant Agreements:



[5GROWTH](https://5growth.eu)<br />(No. 856709) | [5G-CLARITY](https://www.5gclarity.com)<br />(No. 871428) | [PALANTIR](https://www.palantir-project.eu)<br />(No. 883335)
:-------------------------:|:-------------------------:|:-------------------------:
[![](docs/logos/5growth.png)](https://5growth.eu) | [![](docs/logos/5g-clarity.png)](https://www.5gclarity.com) | [![](docs/logos/palantir.png)](https://www.palantir-project.eu)

# License

This project is licensed under [Apache-2.0](https://www.apache.org/licenses/LICENSE-2.0).
