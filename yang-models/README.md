# NetFlow version 9

## YANG model

### Overview

In this directory called `yang-models` you can find the YANG model developed for the NetFlow version 9 protocol. For its implementation, a Cisco white paper related to this service has been used as a basis [1]. Specifically, it has been modelled to the NetFlow Export Packet level, i.e. both header and Flow Data Record fields have been taken into account. GoFlow2 has been chosen as the NetFlow traffic collector [2]. Metrics specific to this collector, that do not appear as such in the Cisco paper, have been modelled as child nodes within a different container. This has been decided so that if you want to have a generic model that is not specific to any collector, you could define this container as an augmentation of the generic YANG model. It is also worth mentioning that an attempt has been made to define rigorously all nodes of the model, so other validated YANG data types have been used (hence two existing models have been imported such as `ietf-inet-types` and `ietf-yang-types`). Futhermore, this model would be able to support both IPv4 and IPv6. The YANG tree diagram of the developed model is shown below.

### YANG tree diagram
```
module: netflow-v9
  +--ro netflow
     +--ro collector-goflow2
     |  +--ro time-received           yang:timestamp
     |  +--ro sampler-address?        inet:ipv4-address
     |  +--ro sampler-address-ipv6?   inet:ipv6-address
     +--ro export-packet
        +--ro sequence-number     yang:counter32
        +--ro count?              uint16
        +--ro system-uptime?      yang:timestamp
        +--ro unix-seconds?       yang:timestamp
        +--ro source-id?          uint32
        +--ro flow-data-record* [flow-id]
           +--ro flow-id                int32
           +--ro bytes-in               yang:counter64
           +--ro bytes-out?             yang:counter64
           +--ro pkts-in                yang:counter64
           +--ro pkts-out?              yang:counter64
           +--ro flows?                 yang:counter64
           +--ro protocol               net-v9:protocol-type
           +--ro src-tos?               uint8
           +--ro dst-tos?               uint8
           +--ro tcp-flags?             net-v9:tcp-flags-type
           +--ro src-port               inet:port-number
           +--ro dst-port               inet:port-number
           +--ro snmp-in?               int32
           +--ro snmp-out?              int32
           +--ro bytes-out-mul?         yang:counter64
           +--ro pkts-out-mul?          yang:counter64
           +--ro first-switched         uint64
           +--ro last-switched          uint64
           +--ro min-pkt-len?           uint16
           +--ro max-pkt-len?           uint16
           +--ro icmp-type?             uint16
           +--ro igmp-type?             net-v9:igmp-type
           +--ro sampler-name?          string
           +--ro sampling-interval?     uint32
           +--ro sampling-algorithm?    net-v9:sampling-mode-type
           +--ro flow-active-tout?      uint16
           +--ro flow-inactive-tout?    uint16
           +--ro engine-type?           net-v9:engine-type
           +--ro engine-id?             uint8
           +--ro tot-bytes-exp?         yang:counter64
           +--ro tot-pkts-exp?          yang:counter64
           +--ro tot-flows-exp?         yang:counter64
           +--ro flow-sampler-id?       uint8
           +--ro flow-sampler-mode?     net-v9:sampling-mode-type
           +--ro flow-sampler-random?   uint32
           +--ro min-ttl?               uint8
           +--ro max-ttl?               uint8
           +--ro src-mac-in             yang:mac-address
           +--ro dst-mac-in             yang:mac-address
           +--ro src-mac-out?           yang:mac-address
           +--ro dst-mac-out?           yang:mac-address
           +--ro ip-version             net-v9:ip-version-type
           +--ro direction?             net-v9:direction-type
           +--ro if-name?               string
           +--ro if-desc?               string
           +--ro frag-offset?           uint16
           +--ro forwarding-status?     net-v9:forwarding-status-type
           +--ro postip-dscp?           inet:dscp
           +--ro repl-factor-mul?       uint32
           +--ro ipv4
           |  +--ro src-address?      inet:ipv4-address
           |  +--ro dst-address?      inet:ipv4-address
           |  +--ro src-mask?         prefix-length-ipv4
           |  +--ro dst-mask?         prefix-length-ipv4
           |  +--ro src-prefix?       inet:ipv4-prefix
           |  +--ro dst-prefix?       inet:ipv4-prefix
           |  +--ro next-hop?         inet:ipv4-address
           |  +--ro identification?   uint16
           +--ro ipv6
           |  +--ro src-address?   inet:ipv6-address
           |  +--ro dst-address?   inet:ipv6-address
           |  +--ro src-mask?      prefix-length-ipv6
           |  +--ro dst-mask?      prefix-length-ipv6
           |  +--ro next-hop?      inet:ipv6-address
           |  +--ro flow-label?    inet:ipv6-flow-label
           |  +--ro opt-headers?   uint32
           +--ro mpls
           |  +--ro pal-rd?           uint64
           |  +--ro prefix-len?       prefix-length-ipv4
           |  +--ro top-label-type?   net-v9:top-label-type
           |  +--ro top-label-ip?     inet:ipv4-address
           |  +--ro label-1?          uint32
           |  +--ro label-2?          uint32
           |  +--ro label-3?          uint32
           |  +--ro label-4?          uint32
           |  +--ro label-5?          uint32
           |  +--ro label-6?          uint32
           |  +--ro label-7?          uint32
           |  +--ro label-8?          uint32
           |  +--ro label-9?          uint32
           |  +--ro label-10?         uint32
           +--ro bgp
           |  +--ro src-as?           inet:as-number
           |  +--ro dst-as?           inet:as-number
           |  +--ro next-hop?         inet:ipv4-address
           |  +--ro next-hop-ipv6?    inet:ipv6-address
           |  +--ro src-traffic-id?   uint32
           |  +--ro dst-traffic-id?   uint32
           +--ro vlan
           |  +--ro src-id?   uint16
           |  +--ro dst-id?   uint16
           +--ro permanent-flow
           |  +--ro bytes-in?   yang:counter64
           |  +--ro pkts-in?    yang:counter64
           +--ro application
           |  +--ro desc?   string
           |  +--ro tag?    string
           |  +--ro name?   string
           +--ro layer2-pkt-section
              +--ro offset?   uint16
              +--ro size?     uint16
              +--ro data?     string
```

## YANG model for aggregated data

Also in this `yang-models` directory, the YANG model created for the Netflow data aggregation application has been included. As can be seen, an augmentation of the original YANG model has been created by taking advantage of the features to extend or modify other existing models offered by the YANG language. In this case, new metrics have been generated from data collected in a Netflow version 9 export package. Specifically, the flow duration, the number of bytes per second, the number of packets per second and the number of bytes per packet associated with an IP flow have been developed. For the last three metrics, both incoming and outgoing metrics have been calculated. The reason why it has been decided to calculate these metrics is because they can be further used in deep learning algorithms to detect cybersecurity threats. As with the original YANG model, the tree diagram of this new model is shown below (augmented part only).


### YANG tree diagram

```
module: netflow-v9-agg
  augment /net-v9:netflow/net-v9:export-packet/net-v9:flow-data-record:
    +--ro flow-duration?            yang:timestamp
    +--ro bytes-in-per-second?      per-decimal
    +--ro bytes-out-per-second?     per-decimal
    +--ro pkts-in-per-second?       per-decimal
    +--ro pkts-out-per-second?      per-decimal
    +--ro bytes-in-per-packet?      per-decimal
    +--ro bytes-out-per-packet?     per-decimal
    +--ro ratio-bytes-in-per-out?   per-decimal
    +--ro ratio-pkts-in-per-out?    per-decimal
```

### References

  [1] Cisco Systems, “NetFlow Version 9 Flow-Record Format,” 2011. https://www.cisco.com/en/US/technologies/tk648/tk362/technologies_white_paper09186a00800a3db9.html
  
  [2] GitHub repository, “netsampler/goflow2: High performance sFlow/IPFIX/NetFlow Collector.” https://github.com/netsampler/goflow2
