# Prometheus-collector

Docker-based prototype that deploys a Prometheus metrics collector along with Scorpio Broker as the NGSI-LD context broker. 

The metrics collector leverages Kafka Connect to configure Source/Sink Kafka Connectors that provide functionalities such as retrieving Prometheus metrics by polling Prometheus REST API. For the sake of simplicity, the metrics collector configures Kafka Connectors to use the same Kafka instance that Scorpio runs for its internal communication bus.

Additionally, we use the metrics collector's Docker image - that runs a webserver programmed with FastAPI - to deploy a dummy context consumer. Basically, the webserver includes an additional REST endpoint that the context consumer can leverage to receive notifications from NGSI-LD subscriptions.

![Docker Prototype](docs/collectors-docker-prototype.png)

# Requirements

- Docker (_Tested with version 19.03.13_)
- Docker-compose (_Tested with version 1.27.4_)

# Quick Start

Start the prototype by running docker-compose:
```bash
docker-compose up
```

In case you are interested in running the prototype in background (kafka or scorpio logs may be annoying), use the following command:
```bash
docker-compose up -d
```

# Postman Collections

This repository contains Postman collections that you can use to play with the REST APIs of some of the components present in the prototype. We recommend downloading [Postman Desktop](https://www.postman.com/downloads/) for an better user experience.

- [NGSI-LD Prometheus.postman_collection](NGSI-LD%20Prometheus.postman_collection.json) is a set of requests that can be used to interact with the [NGSI-LD Scorpio Broker](https://github.com/ScorpioBroker/ScorpioBroker). This collection includes most of the Entity, Subscription, and Context Source operations that are commonly used in NGSI-LD. The requests contained in this collection can be utilized with other NGSI-LD compliant broker such as [Orion-LD](https://github.com/FIWARE/context.Orion-LD) or [Stellio](https://github.com/stellio-hub/stellio-context-broker).
  
- [API REST Kafka Connect.postman_collection](API%20REST%20Kafka%20Connect.postman_collection.json) provides example requests for all the supported operations in [Kafka Connect REST API](https://docs.confluent.io/current/connect/references/restapi.html).
    
