# YANG to Avro Transformation Specification

This document describes the methods and design choices proposed for transforming a YANG module to an Avro schema. The contents of this document have been inspired by the [YANG to Protobuf: Transformation Specification](https://github.com/openconfig/ygot/blob/master/docs/yang-to-protobuf-transformations-spec.md) which is included in the documentation of the [ygot library](https://github.com/openconfig/ygot) .

## YANG - Avro Types Mapping

| YANG Type               | Avro Type                    | Notes         |
| ----------------------- | ----------------------------------- | ------------- |
| `binary`                | `binary`  | `bytes`: sequence of 8-bit unsigned bytes  |
| `bits`                  |  |   |
| `boolean`               | `boolean` |   |
| `decimal64`             | `double` | `double`: double precision (64-bit) IEEE 754 floating-point number  |
| `empty`                 | `boolean` |   |
| `enumeration`           | `enum`    | Embedded within a record where an `enumeration` field exists, globally defined `enum` if corresponding to a `typedef`. |
| `identityref`           | `enum`    | A global `enum` is generated for the `identityref` base `identity` |
| `instance-identifier`   |           |  |
| `int{8,16,32`           | `int`     | `int`: 32-bit signed integer  |
| `int64`                 | `long`    | `long`: 64-bit signed integer  |
| `leafref`               |  Dynamic  | Type of leafref `path` target node  |
| `string`                | `string`  |  `length` and `pattern` statements as metadata attributes of the field |
| `uint{8,16,32}`         | `int`     | `int`: 32-bit signed integer  |
| `uint64`                | `long`    | `long`: 64-bit signed integer  |
| `union`                 | `union`   | Unions are represented using JSON arrays. For example, ["null", "string"] declares a schema which may be either a null or string. |

## Field and Record Naming

We propose the same approached that Ygot specifies when it comes to the field and message naming. 

Each directory entry within the YANG schema (i.e., `list` or `container` node) maps to an Avro record. Records are named by translating the name of the data node into `CamelCase` format. 

On the other hand, fields are lower-cased, and those unsupported characters are replaced with underscores. The following quote from the Avro specification gives more details regarding the supported characters in Avro:

> Record, enums and fixed are named types. Each has a fullname that is composed of two parts; a name and a namespace. Equality of names is defined on the fullname.
>
> The name portion of a fullname, record field names, and enum symbols must:
>
>  - start with [A-Za-z_]
>  - subsequently contain only [A-Za-z0-9_]

## Enumeration Naming

TODO

## Mapping of YANG Lists

Ygot's specification for protobuf describes an implementation that slightly differs from the YANG tree hierarchy. Ygot specifies a `list` should be a repeated message that contains a field that represents the `key` within the `list`, and also contains a message that represents the remaining entities within the `list`. The reason for this design choice is to add support for cases whereby the type of the `key` is not possible to use within a YANG map.

Our proposal is to stick to the hierarchy of the YANG tree. Thus, a `list` node will be implemented in Avro as an `array` that contains `record` type items that include all the entities within the `list`. See the `port` list node in the example at the end of this document.

## Annotation of Schema Paths

We propose leveraging Avro's `doc` attribute to provide information about the YANG data node that each field represents within the Avro schema. As a result every field that is present in the Avro schema, will include a reference to its corresponding complete YANG schema tree path, e.g., `/ports/port/config/speed`.

## Annotation of Enum Values

TODO

## Anydata Representation

The `anydata` statement in YANG can be used to include data that is unknown at design time. This type of statement is represented in Avro schema by using the `bytes` primitive type.

A similar approach is followed in the [Pnda.io](http://pnda.io/pnda-guide/streamingest/data-preparation.html) project. The proposed Avro encapsulation is based on a generic Avro schema that acts as a wrapper for any kind of data ingested by the platform. This schema contains a field named `rawdata` of  `bytes` type which enables including unknown data prior to the ingestion.

## Example: YANG Model Transformation

The following snippet depicts the sample `demo-port.yang` YANG module represented in a tree format using [`pyang`](https://github.com/mbj4668/pyang) library:

```bash
bash-4.4# pyang -f tree demo-port.yang
module: demo-port
  +--rw ports
     +--rw port* [port-number]
        +--rw port-number    port-number
        +--rw config
        |  +--rw speed?   identityref
        +--ro state
           +--ro status?   boolean
```

The corresponding Avro schema for this YANG module is shown bellow:
```json
{
    "type": "record",
    "name": "Ports",
    "namespace": "giros.org",
    "fields": [
        {
            "name": "port",
            "doc": "/ports/port",
            "type": [
                "null",
                {
                    "type": "array",
                    "items": {
                        "type": "record",
                        "name": "Port",
                        "aliases": ["portType"],
                        "fields": [
                            {
                                "name": "port_number",
                                "type": "int",
                                "doc": "/ports/port/port-number"
                            },
                            {
                                "name": "config",
                                "doc": "/ports/port/config",
                                "type": [
                                    "null",
                                    {
                                        "type": "record",
                                        "name": "Config",
                                        "aliases": ["configType"],
                                        "fields": [
                                            {
                                                "name": "speed",
                                                "doc": "/ports/port/config/speed",
                                                "type": [
                                                    "null", 
                                                    {
                                                        "name": "DemoPortSPEED",
                                                        "type": "enum",
                                                        "symbols": ["SPEED", "SPEED_10GB"]
                                                    }
                                                ]
                                            }
                                        ]
                                    }
                                ]
                            },
                            {
                                "name": "state",
                                "doc": "/ports/port/state",
                                "type": [
                                    "null",
                                    {
                                        "type": "record",
                                        "name": "State",
                                        "aliases": ["stateType"],
                                        "fields": [
                                            {
                                                "name": "status",
                                                "doc": "/ports/port/state/status",
                                                "type": ["null", "boolean"]
                                            }
                                        ]
                                    }
                                ]
                            }
                        ]
                    }
                }
            ]
        }
    ]
}
```

The protobuf descriptor generated by `ygot` library for the above shown YANG module looks as follows:

```bash
bash-4.4# cat /proto/tutorial/demo_port/demo_port.proto
// tutorial.demo_port is generated by proto_generator as a protobuf
// representation of a YANG schema.
//
// Input schema modules:
//  - demo-port.yang
syntax = "proto3";

package tutorial.demo_port;

import "github.com/openconfig/ygot/proto/ywrapper/ywrapper.proto";
import "github.com/openconfig/ygot/proto/yext/yext.proto";
import "tutorial/enums/enums.proto";

message Ports {
  message Port {
    message Config {
      tutorial.enums.DemoPortSPEED speed = 349429249 [(yext.schemapath) = "/ports/port/config/speed"];
    }
    message State {
      ywrapper.BoolValue status = 431466463 [(yext.schemapath) = "/ports/port/state/status"];
    }
    Config config = 496638493 [(yext.schemapath) = "/ports/port/config"];
    State state = 309256978 [(yext.schemapath) = "/ports/port/state"];
  }
  message PortKey {
    uint64 port_number = 1 [(yext.schemapath) = "/ports/port/port-number"];
    Port port = 2;
  }
  repeated PortKey port = 44920344 [(yext.schemapath) = "/ports/port"];
}
```
