#!/usr/bin/python3

from binding import openmetrics

import hashlib
import json
import pyangbind.lib.pybindJSON as pybindJSON
import sys

def build_metrics(obj):
    resultType = obj["data"]["resultType"]
    metrics = obj["data"]["result"]
    om = openmetrics()
    for metric in metrics:
        # For now assume family name equals metric's name
        #
        # TODO: function that obtains family name
        # based on the metric name
        #
        family_name = metric["metric"]["__name__"]
        mf = None
        if family_name in om.metric_families.metric_family:
            mf = om.metric_families.metric_family[family_name]
        else:
            mf = om.metric_families.metric_family.add(
                    family_name)
        # Calculate hash from metric's labels
        labels = json.dumps(metric["metric"], sort_keys=True)
        label_set_id = hashlib.md5(
            labels.encode("utf-8")).hexdigest()
        m = mf.metrics.metric.add(label_set_id)
        m._set_name(metric["metric"]["__name__"])

        for name, value in metric["metric"].items():
            if name == "__name__":
                continue
            label = m.labels.label.add(name)
            label._set_value(value)

        if resultType == "vector":
            m.metric_points._set_value(metric["value"][1])
            m.metric_points._set_timestamp(metric["value"][0])

        elif resultType == "matrix":
            for value in metric["values"]:
                timestamp = value[0]
                mp = m.metric_points.metric_point.add(timestamp)
                mp._set_value(value[1])

        else:
            raise Exception("Unkown result type")

    return om

# Read incoming FlowFile from stdin
obj = json.load(sys.stdin.buffer)
om = build_metrics(obj)
ietf_json = pybindJSON.dumps(om, mode="ietf")
# Remove root container
# Represented by top Avro record
# (same as Protobuf ang gNMI)
ietf_json_dict = json.loads(ietf_json)
root_container = list(ietf_json_dict)[0]
ietf_json_dict = ietf_json_dict[root_container]
output_json = json.dumps(ietf_json_dict)
# Replace dashes with underscores
# This is a quick hack that
# could break some values
output_json = output_json.replace("-", "_")
sys.stdout.write("{0}\n".format(output_json))
