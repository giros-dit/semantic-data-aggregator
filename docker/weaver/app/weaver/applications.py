from semantic_tools.models.application import Task
from semantic_tools.ngsi_ld.client import NGSILDClient

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


def config_eve_source(task: Task, ngsi_ld: NGSILDClient) -> dict:
    """
    Builds configuration arguments for EVESource application (NiFi)
    """
    # Collect lineage information

    # Task Input
    # Get source Kafka topic
    source_topic = ngsi_ld.get_kafka_topic(
        task.hasInput.object)
    # Get source Kafka broker
    source_broker = ngsi_ld.get_kafka_broker_from_topic(
        source_topic)
    # Get Endpoint for source Kafka broker
    source_endpoint = ngsi_ld.get_endpoint_from_infrastructure(
        source_broker)

    # Task Output
    # Get sink Kafka topic
    sink_topic = ngsi_ld.get_kafka_topic(
        task.hasInput.object)
    # Get sink Kafka broker
    sink_broker = ngsi_ld.get_kafka_broker_from_topic(
        sink_topic)
    # Get Endpoint for sink Kafka broker
    sink_endpoint = ngsi_ld.get_endpoint_from_infrastructure(
        sink_broker)

    # Prepare variables from context arguments
    # Only supports one input Kafka topic
    # although NiFi allows passing multiple
    # broker urls and multiple topic names
    source_broker_url = source_endpoint.uri.value
    source_topic_name = source_topic.name.value
    sink_broker_url = sink_endpoint.uri.value
    sink_topic_name = sink_topic.name.value

    context_arguments = {
        # Avro schema hardcoded
        # although should be discovered
        # by asking registry with context information
        "avro_schema": "eve",
        "source_broker_url": source_broker_url,
        "source_topics": source_topic_name,
        "sink_broker_url": sink_broker_url,
        "sink_topic": sink_topic_name
    }
    # Combine context arguments with user arguments
    user_arguments = task.arguments.value
    arguments = {**context_arguments, **user_arguments}

    return arguments


def config_metric_source(task: Task, ngsi_ld: NGSILDClient) -> dict:
    """
    Builds configuration arguments for MetricSource application (NiFi)
    """
    # Collect lineage information

    # Task Input
    # Get source Metric
    source_metric = ngsi_ld.get_metric(
        task.hasInput.object)
    # Get source Prometheus
    source_prom = ngsi_ld.get_prometheus_from_metric(
        source_metric)
    # Get Endpoint for source Kafka broker
    source_endpoint = ngsi_ld.get_endpoint_from_infrastructure(
        source_prom)

    # Task Output
    # Get sink Kafka topic
    sink_topic = ngsi_ld.get_kafka_topic(
        task.hasOutput.object
    )
    # Get sink Kafka broker
    sink_broker = ngsi_ld.get_kafka_broker_from_topic(
        sink_topic
    )
    # Get Endpoint for sink Kafka broker
    sink_endpoint = ngsi_ld.get_endpoint_from_infrastructure(
        sink_broker
    )

    # Build URL based on optional expression
    prometheus_request = ""
    if "expression" in task.arguments.value:
        labels = _getQueryLabels(task.arguments.value["expression"])
        prometheus_request = (
            source_endpoint.uri.value +
            "?query=" + source_metric.name.value +
            "{" + labels + "}")
    else:
        prometheus_request = (source_endpoint.uri.value +
                              "?query=" + source_metric.name.value)

    # Collect variables for MetricSource
    sink_broker_url = sink_endpoint.uri.value
    sink_topic_name = sink_topic.name.value

    arguments = {
        # Avro schema hardcoded
        # although should be discovered
        # by asking registry with context information
        "avro_schema": "prometheus",
        "interval": task.arguments.value["interval"],
        "prometheus_request": prometheus_request,
        "sink_broker_url": sink_broker_url,
        "sink_topic": sink_topic_name
    }
    return arguments


def config_metric_target(task: Task, ngsi_ld: NGSILDClient) -> dict:
    # Collect lineage information

    # Task Input
    # Get source Kafka topic
    source_topic = ngsi_ld.get_kafka_topic(
        task.hasInput.object)
    # Get source Kafka broker
    source_broker = ngsi_ld.get_kafka_broker_from_topic(
        source_topic)
    # Get Endpoint for source Kafka broker
    source_endpoint = ngsi_ld.get_endpoint_from_infrastructure(
        source_broker)

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


def config_telemetry_source(task: Task, ngsi_ld: NGSILDClient) -> dict:
    """
    Deploys a TelemetrySource NiFi template
    from a passed TelemetrySource NGSI-LD entity.
    """
    # Get YANG Module as source
    module = ngsi_ld.get_yang_module(
        task.hasInput.object
    )
    # Get source Device
    source_device = ngsi_ld.get_device_from_module(
        module
    )
    # Get Endpoint for source device
    source_endpoint = ngsi_ld.get_endpoint_from_infrastructure(
        source_device)

    # Task Output
    # Get sink Kafka topic
    sink_topic = ngsi_ld.get_kafka_topic(
        task.hasInput.object)
    # Get sink Kafka broker
    sink_broker = ngsi_ld.get_kafka_broker_from_topic(
        sink_topic)
    # Get Endpoint for sink Kafka broker
    sink_endpoint = ngsi_ld.get_endpoint_from_infrastructure(
        sink_broker)

    # Build arguments
    gnmic_topic = "gnmic-" + sink_topic.name.id
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
    "EVESource": config_eve_source,
    "MetricSource": config_metric_source,
    "MetricTarget": config_metric_target,
    "TelemetrySource": config_telemetry_source
}
