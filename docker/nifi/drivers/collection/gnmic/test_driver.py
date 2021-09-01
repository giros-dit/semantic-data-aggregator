#!/usr/bin/python3

from driver import parse_event

import json
import pyangbind.lib.pybindJSON as pybindJSON
import sys

with open('examples/driver-input.json') as json_file:
    data = json.load(json_file)
    for data in data:
        yang_obj = parse_event(data)
        ietf_json = pybindJSON.dumps(yang_obj, mode="ietf")
        # Remove root container
        # Represented by top Avro record
        # (same as Protobuf and gNMI)
        ietf_json_dict = json.loads(ietf_json)
        root_container = list(ietf_json_dict)[0]
        ietf_json_dict = ietf_json_dict[root_container]
        output_json = json.dumps(ietf_json_dict)
        sys.stdout.write("{0}\n".format(output_json))