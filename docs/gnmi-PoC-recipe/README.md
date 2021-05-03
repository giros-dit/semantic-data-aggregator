# gNMI Telemetry Proof of Concept - Recipe

The purpose of this prototype is collect data of [`gNMI](https://github.com/openconfig/reference/blob/master/rpc/gnmi/gnmi-specification.md) sources from the `Semantic Data Aggregator` (`SDA`). For this proof of concept with gNMI data sources, the prototype has two main resources: docker instances of [`Arista cEOS`](https://www.arista.com/en/products/software-controlled-container-networking) routers as network devices and YANG-based data sources that support the gNMI management protocol and a CLI client that provides a full support of gNMI RPCs called [`gNMIc`](https://gnmic.kmrd.dev/) to request the configuration and operational status from these telemetry-based network devices.

Here are the most important steps to be able to run the gNMI-based data collection prototype and extract telemetry information of `Arista cEOS` routers using the `gNMIc` client from the `SDA`:

1) Before starting docker-compose it is necessary to import the `Arista cEOS` router docker image. Specifically, the scenario uses one of the latest available `Arista cEOS` versions `cEOS-lab-4.24.5M`. Download it first from the [Arista software section](https://www.arista.com/en/support/software-download) (it is the non-64-bit version).

2) The command to import the image is:
```bash
docker import cEOS-lab-4.24.5M.tar ceos-image:4.24.5M
```

3) Then you can start the docker-compose, which raises two instances of `Arista cEOS` routers (`ceos1` and `ceos2` docker services). These docker services are responsible for configuring environment variables, configuring TCP access ports for `gNMI`, and creating two P2P networks between both routers. The command to start the docker-compose is:
```bash
docker-compose -f docker-compose-arista.yml up
```

4) Once the docker-compose is up, it is necessary to perform a very basic configuration of the `ceos1` and `ceos2` routers to enable the `gNMI` protocol. Example for `ceos1`:
- To access the CLI of ceos1:
```bash
docker exec -it ceos1 Cli
localhost>enable
```
- `gRPC/gNMI` configuration:

```bash
localhost#configure terminal
localhost(config)#hostname ceos1
ceos1(config)#username admin secret xxxx
ceos1(config)#management api gnmi
ceos1(config-mgmt-api-gnmi)#transport grpc default
ceos1(config-gnmi-transport-default)#end
ceos1#write memory
```

5) In order to play with the `gNMIc` client, a series of steps and RPCs execution examples are shown:
- The `nifi` docker container has `gNMIc` installed.
- To learn about the `gNMI` capabilities of `ceos1` (which `gNMI` version the device runs, what models it is loaded with and which encoding it understands).
```bash
root@nifi:/opt/nifi/nifi-current# gnmic -a ceos1:6030 -u admin -p xxxx --insecure capabilities
Capabilities Response:
gNMI version: 0.7.0
supported models:
  - openconfig-bgp-policy, OpenConfig working group, 5.0.1
  - openconfig-packet-match-types, OpenConfig working group, 1.0.1
  - arista-intf-deviations, Arista Networks, Inc.,
  ...
  - openconfig-isis, OpenConfig working group, 0.6.0
supported encodings:
  - JSON
  - JSON_IETF
  - ASCII
```
- Arista community publishes its YANG models in a [GitHub repository](https://github.com/aristanetworks/yang.git). To analyze and understand the structure of YANG models, the Python tool [`pyang`](https://github.com/mbj4668/pyang) is very useful (they have not published the updated models for version `4.24.4M`, and `4.24.2F` is the latest available). As an example, we can parse the `openconfig-interfaces.yang` module using the `pyang` command:
```bash
pyang -f tree -p yang yang/EOS-4.24.2F/openconfig/public/release/models/interfaces/openconfig-interfaces.yang
```
- To request information (Get RPC) about a specific leaf node of an available YANG tree module (example for the `openconfig-interfaces.yang` module and the `in-octets` node):
```bash
root@nifi:/opt/nifi/nifi-current# gnmic -a ceos1:6030 -u admin -p xxxx --insecure get --path "/interfaces/interface[name=Ethernet1]/state/counters/in-octets"
Get Response:
[
  {
    "time": "1970-01-01T00:00:00Z",
    "updates": [
      {
        "Path": "interfaces/interface[name=Ethernet1]/state/counters/in-octets",
        "values": {
          "interfaces/interface/state/counters/in-octets": 37484
        }
      }
    ]
  }
]
```
- To subscribe to information (Subscribe RPC) about a specific leaf node of an available YANG tree module ((1) `on-change` or (2) `sample` subscription modes):
```bash
(1) root@nifi:/opt/nifi/nifi-current# gnmic -a ceos1:6030 -u admin -p xxxx --insecure subscribe --path "/interfaces/interface[name=Ethernet1]/state/counters/in-octets" --stream-mode on_change --qos 0

(2) root@nifi:/opt/nifi/nifi-current# gnmic -a ceos1:6030 -u admin -p xxxx --insecure subscribe --path "/interfaces/interface[name=Ethernet1]/state/counters/in-octets" --stream-mode sample --sample-interval 5s --qos 0
```
- [`/gnmic-cfgs/cfg-kafka.json`](../../gnmic-cfgs/cfg-kafka.json) is the configuration file used to automate the gNMI subscriptions and store the information monitored in the Kafka data data substrate of the aggregator. Weaver is the building block in charge of parameterizing the file values regarding the type and subscription interval, the YANG path of the data to be monitored, the address and port of the endpoint and the Kafka topic. To be able to do a subscription test with `gNMIc` using this configuration file, you can run the following example commands ((1) `sample` or (2) `on-change` subscription modes):
```bash
(1) root@nifi:/opt/nifi/nifi-current# gnmic --config /gnmic-cfgs/cfg-kafka.json subscribe --name sample

(2) root@nifi:/opt/nifi/nifi-current# gnmic --config /gnmic-cfgs/cfg-kafka.json subscribe --name on-change
```
In the message bus of the `kafka` docker container, a new topic will appear where new entries will be written according to the type of subscription chosen.
- [`NGSI-LD API Orchestrator`](../../postman_collections/NGSI-LD%20API%20Orchestrator.postman_collection.json) Postman collection has a set of requests that can be used to model a `NGSI-LD` datapipeline with `TelemetrySource` entities and extract the telemetry information of the `Arista cEOS` devices from the `Semantic Data Aggregator` using `gNMIc`. 
