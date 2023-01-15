# Semantic Data Models

This repository contains NGSI-LD information models that characterize those data sources and data consumers currently supported by the [semantic data aggregator (SDA)](https://github.com/giros-dit/semantic-data-aggregator).

On the other hand, YANG.

## Pre-requisites (ONLY Windows users)

Window's implementation of Git does not support filenames longer than 260 characters ([see](https://stackoverflow.com/questions/22575662/filename-too-long-in-git-for-windows)). Before cloning this repository, it is required to run the following command in order to support longer filenames (requires admin privileges):
```bash
git config --system core.longpaths true
```

### NGSI-LD Models

This repository contains a collection of NGSI-LD models for the different domains addressed by the Semantic Data Aggregation (aka `SDA`). The repository is composed of the following two main folders:

- [information-models](ngsi-ld/information-models) includes property graphs that depict the NGSI-LD information models related to each domain.
- [data-models](ngsi-ld/data-models) includes data models for each of the NGSI-LD entities defined in the respective information models.

More details on the contents of these data models are described in each folder.

### YANG Models

The role of the semantic data aggregator is the aggregation of data ingested from monitoring sources, and the transformation of the resulting data into a format that is suitable for those interested data consumers. For this purpose, the data aggregator follows a model-driven approach that establishes an agreement between data sources and data consumers.

#### YANG language

One of the goals in this project is to achieve interoperability by introducing data models that are agnostic to the input/output serialization format. To this end, we leverage the [YANG](https://tools.ietf.org/html/rfc7950) modeling language which enables the creation of data models that provide an abstraction layer over the underlying complexity of the technology that is used. YANG is a highly a descriptive and humand readable modeling language. YANG supports model evolution by means of augmentations and deviations, and enables differentiating configuration data from operational data.

The YANG language models data following a tree structure. Subscriptions to data sources that rely on YANG models, produce snapshots of the subscribed YANG tree at given time. These snapshots represent subtrees of the YANG tree that include those data nodes that match the criteria of the subscription (XPath, subtree filtering). Subscriptions can be configured to trigger notifications periodically or upon detecting changes on the data nodes. The concept of a YANG event notification is described in detail in [RFC 5227](https://tools.ietf.org/html/rfc5277).

We propose that the semantic data aggregator manages data internally as YANG event notifications. The notification includes an event time paremeter along with the subtree snapshot. A notification shall be encoded in a format such as XML (NETCONF) or Protobuf (gNMI). The semantic data aggregator must choose a performant encoding format for the YANG even notifications.

#### Collection Agents

The integration of new data sources with the semantic data aggregator requires the configuration of a collection agents. These agents have to address the following two aspects:

1. **Transport**: Mechanism that collects data from the source. This mechanism could be either push-based or pull-based.
2. **Encoding**: Data is sent from the source in a particular format which may follow a structure. The agent must parse and transform the data into a format that is suitable for future consumption.

Regarding the encoding aspect, the collection agent leverages a special component called the `driver` which is reponsible for transforming the ingested events into a YANG data model. By "translating" the event into a YANG data model tree, the SDA is able to structure data using the encoding format of best interest such as Apache Avro or Protobuf. Notice here the use of the term `event`. Each YANG data model is specifically built to cope with individual events collected from a data source. Hence, the role of the `driver` is the transformation of data on an event basis. Beware that this approach requires the collection agent to include additional mechanisms to pre-process the ingested data in order to provide the `driver` with individual events.

Once the driver finishes the job, the collection agent applies the required mechanisms that write the encoded structured data in the Data Substrate, e.g., Kafka producer.

#### Data Source Driver Implementation

In order to facilitate the adoption of new data sources, we propose a methodology for implementing the so-called data source drivers. The following figure depicts a first approach to this implementation:

![data-source-driver](docs/miscellaneous/miscellaneous-data-source-driver.png)

The above shown approach is agnostic to the chosen tools. In the case of our current semantic data aggregator prototype, Apache NiFi, Apache Kafka, and JSON IETF encoding are the chosen pieces for building data source drivers:

![data-source-driver-nifi](docs/miscellaneous/miscellaneous-data-source-driver-nifi-java.png)

Each data source driver is implemented with the [YANG-TOOLS](https://docs.opendaylight.org/en/stable-aluminium/developer-guide/yang-tools.html) Java library as custom NiFi processor. These drivers are built on Java class bindings that YANG-TOOLS generate for the YANG model(s) associated to each data source. The NiFi processor is responsible for normalizing the collected data by parsing the raw data from the source and producing data structured as per the associated YANG model. For the sake of interoperabiltiy, the produced data are encoded into the JSON IETF format.

#### How-to-develop-my-custom-driver (COMING SOON!)
