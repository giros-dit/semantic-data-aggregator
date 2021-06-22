from semantic_tools.clients.ngsi_ld import NGSILDClient
from semantic_tools.models.application import Task
from semantic_tools.models.common import Endpoint
from semantic_tools.models.metric import Metric, Prometheus
from semantic_tools.models.telemetry import Device, Module
from semantic_tools.models.stream import KafkaBroker, KafkaTopic

import json
import logging
import os

logger = logging.getLogger(__name__)


def _getQueryLabels(expression: dict) -> str:
    """
    Print Prometheus labels to make them consumable
    by Prometheus REST API.
    """
    labels = []
    for label, value in expression.items():
        labels.append("{0}='{1}'".format(label, value))

    return ",".join(labels)


def configEVESource(task: Task, ngsi: NGSILDClient) -> dict:
    # Get source Kafka topic
    source_topic_entity = ngsi.retrieveEntityById(task.hasInput.object)
    source_topic = KafkaTopic.parse_obj(source_topic_entity)
    # Get source Kafka broker
    source_broker_entity = ngsi.retrieveEntityById(
        source_topic.hasKafkaBroker.object)
    source_broker = KafkaBroker.parse_obj(source_broker_entity)
    # Get Endpoint for source Kafka broker
    source_endpoint_entity = ngsi.retrieveEntityById(
        source_broker.hasEndpoint.object)
    source_endpoint = Endpoint.parse_obj(source_endpoint_entity)
    # Get sink Kafka topic
    sink_topic_entity = ngsi.retrieveEntityById(task.hasOutput.object)
    sink_topic = KafkaTopic.parse_obj(sink_topic_entity)
    # Get sink Kafka broker
    sink_broker_entity = ngsi.retrieveEntityById(
        sink_topic.hasKafkaBroker.object)
    sink_broker = KafkaBroker.parse_obj(sink_broker_entity)
    # Get Endpoint for sink Kafka broker
    sink_endpoint_entity = ngsi.retrieveEntityById(
        sink_broker.hasEndpoint.object)
    sink_endpoint = Endpoint.parse_obj(sink_endpoint_entity)

    # Collect variables for EVESource
    source_broker_url = source_endpoint.uri.value
    # Only supports one input Kafka topic
    # although NiFi allows passing multiple
    # topic names for consumption
    source_topic_name = source_topic.name.value
    sink_broker_url = sink_endpoint.uri.value
    sink_topic_name = sink_topic.name.value

    arguments = {
        # Avro schema hardcoded
        # although should be discovered
        # by asking registry with context information
        "avro_schema": "eve",
        "group_id": task.arguments.value["group_id"],
        "source_broker_url": source_broker_url,
        "source_topics": source_topic_name,
        "sink_broker_url": sink_broker_url,
        "sink_topic": sink_topic_name
    }
    return arguments


def configMetricSource(task: Task, ngsi: NGSILDClient) -> dict:
    # Get source Metric
    source_metric_entity = ngsi.retrieveEntityById(task.hasInput.object)
    source_metric = Metric.parse_obj(source_metric_entity)
    # Get source Prometheus
    source_prom_entity = ngsi.retrieveEntityById(
        source_metric.hasPrometheus.object)
    source_prom = Prometheus.parse_obj(source_prom_entity)
    # Get Endpoint for source Kafka broker
    source_endpoint_entity = ngsi.retrieveEntityById(
        source_prom.hasEndpoint.object)
    source_endpoint = Endpoint.parse_obj(source_endpoint_entity)
    # Get sink Kafka topic
    sink_topic_entity = ngsi.retrieveEntityById(task.hasOutput.object)
    sink_topic = KafkaTopic.parse_obj(sink_topic_entity)
    # Get sink Kafka broker
    sink_broker_entity = ngsi.retrieveEntityById(
        sink_topic.hasKafkaBroker.object)
    sink_broker = KafkaBroker.parse_obj(sink_broker_entity)
    # Get Endpoint for sink Kafka broker
    sink_endpoint_entity = ngsi.retrieveEntityById(
        sink_broker.hasEndpoint.object)
    sink_endpoint = Endpoint.parse_obj(sink_endpoint_entity)

    # Build URL based on optional expression
    prometheus_request = ""
    if "expression" in task.arguments.value:
        labels = _getQueryLabels(task.arguments.value["expression"])
        prometheus_request = (source_endpoint.uri.value + "?query=" + task.name.value + "{" + labels + "}")
    else:
        prometheus_request = source_endpoint.uri.value + "?query=" + task.name.value

    # Collect variables for MetricSource
    sink_broker_url = sink_endpoint.uri.value
    sink_topic_name = sink_topic.name.value

    arguments = {
        # Avro schema hardcoded
        # although should be discovered
        # by asking registry with context information
        "avro_schema": "prometheus",
        "promtheus_request": prometheus_request,
        "sink_broker_url": sink_broker_url,
        "sink_topic": sink_topic_name
    }
    return arguments


def configMetricTarget(task: Task, ngsi: NGSILDClient) -> dict:
    # Get source Kafka topic
    source_topic_entity = ngsi.retrieveEntityById(task.hasInput.object)
    source_topic = KafkaTopic.parse_obj(source_topic_entity)
    # Get source Kafka broker
    source_broker_entity = ngsi.retrieveEntityById(
        source_topic.hasKafkaBroker.object)
    source_broker = KafkaBroker.parse_obj(source_broker_entity)
    # Get Endpoint for source Kafka broker
    source_endpoint_entity = ngsi.retrieveEntityById(
        source_broker.hasEndpoint.object)
    source_endpoint = Endpoint.parse_obj(source_endpoint_entity)

    # Collect variables for EVESource
    source_broker_url = source_endpoint.uri.value
    # Only supports one input Kafka topic
    # although NiFi allows passing multiple
    # topic names for consumption
    source_topic_name = source_topic.name.value
    consumer_url = task.expression.value["consumer_url"]

    arguments = {
        # Avro schema hardcoded
        # although should be discovered
        # by asking registry with context information
        "source_broker_url": source_broker_url,
        "source_topics": source_topic_name,
        "consumer_url": consumer_url
    }
    return arguments


def configTelemetrySource(task: Task, ngsi: NGSILDClient) -> dict:
    """
    Deploys a TelemetrySource NiFi template
    from a passed TelemetrySource NGSI-LD entity.
    """
    # Get YANG Module as source
    module_entity = ngsi.retrieveEntityById(task.hasInput.object)
    module = Module.parse_obj(module_entity)
    # Get gNMI server address from hasEndpoint relationship
    device_entity = ngsi.retrieveEntityById(module.hasDevice.object)
    device = Device.parse_obj(device_entity)
    # Get Endpoint for device
    source_endpoint_entity = ngsi.retrieveEntityById(device.hasEndpoint.object)
    source_endpoint = Endpoint.parse_obj(source_endpoint_entity)
    # Get sink Kafka topic
    sink_topic_entity = ngsi.retrieveEntityById(task.hasOutput.object)
    sink_topic = KafkaTopic.parse_obj(sink_topic_entity)
    gnmic_topic = "gnmic-" + sink_topic.name.id
    # Get sink Kafka broker
    sink_broker_entity = ngsi.retrieveEntityById(
        sink_topic.hasKafkaBroker.object)
    sink_broker = KafkaBroker.parse_obj(sink_broker_entity)
    # Get Endpoint for sink Kafka broker
    sink_endpoint_entity = ngsi.retrieveEntityById(
        sink_broker.hasEndpoint.object)
    sink_endpoint = Endpoint.parse_obj(sink_endpoint_entity)

    # Get subscription mode (sample or on-change)
    subscription_mode = task.arguments.value["subscriptionMode"]
    filename = '/gnmic-cfgs/cfg-subscriptions.json'
    subscription_name = ""
    with open(filename, 'r') as file:
        data = json.load(file)
        data['address'] = source_endpoint.uri.value.split("://")[1]
        data['outputs']['output']['topic'] = gnmic_topic
        data['outputs']['output']['format'] = "event"
        xPath = task.arguments.value["xPath"]
        telemetry_data = []
        if type(xPath) is str:
            telemetry_data.append(xPath)
        elif type(xPath) is list:
            telemetry_data = xPath
        if subscription_mode == "on-change":
            data['subscriptions']['on-change']['paths'] = telemetry_data
        elif subscription_mode == "sample":
            data['subscriptions']['sample']['paths'] = telemetry_data
            # Get interval value
            interval = task.arguments.value["interval"]
            # Enforce miliseconds for interval
            data[
                'subscriptions']['sample']['sample-interval'] = interval + "ms"

    os.remove(filename)
    with open(filename, 'w') as file:
        json.dump(data, file, indent=4)

    # Collect variables for TelemetrySource
    # arguments = telemetrySource.arguments.value+" --name {0}".format(
    # subscription_mode)
    command_arguments = "--config {0} subscribe --name {1}".format(
        filename, subscription_name)
    sink_broker_url = sink_endpoint.uri.value
    sink_topic_name = sink_topic.name.value
    arguments = {
        # Avro schema hardcoded
        # although should be discovered
        # by asking registry with context information
        "avro_schema": "gnmic-event",
        "command": "gnmic",
        "command_arguments": command_arguments,
        "sink_broker_url": sink_broker_url,
        "sink_topic": sink_topic_name
    }
    return arguments


application_configs = {
    "EVESource": configEVESource,
    "MetricSource": configMetricSource,
    "MetricTarget": configMetricTarget,
    "TelemetrySource": configTelemetrySource
}
