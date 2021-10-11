import json
import logging

from semantic_tools.models.application import Task
from semantic_tools.ngsi_ld.client import NGSILDClient
from semantic_tools.ngsi_ld.units import UnitCode

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
        task.hasOutput.object)
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
    group_id = task.arguments.value["groupId"]

    arguments = {
        "group_id": group_id,
        "source_broker_url": source_broker_url,
        "source_topics": source_topic_name,
        "sink_broker_url": sink_broker_url,
        "sink_topic": sink_topic_name
    }
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

    # Task Input
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
        task.hasOutput.object)
    # Get sink Kafka broker
    sink_broker = ngsi_ld.get_kafka_broker_from_topic(
        sink_topic)
    # Get Endpoint for sink Kafka broker
    sink_endpoint = ngsi_ld.get_endpoint_from_infrastructure(
        sink_broker)

    xpath = task.arguments.value['XPath']
    subscription_mode = task.arguments.value['subscriptionMode']

    # Build arguments
    gnmic_topic = "gnmic-" + sink_topic.name.value
    # Get subscription mode (sample or on-change)
    subscription_mode = task.arguments.value["subscriptionMode"]
    filename = '/gnmic-cfgs/subscription' + '-' + sink_topic.name.value + '.json'

    subscription_data = {}
    subscription_data['address'] = source_endpoint.uri.value.split("://")[1]
    subscription_data['username'] = 'admin'
    subscription_data['password'] = 'xxxx'
    subscription_data['insecure'] = 'true'
    logfile = '/tmp/gnmic' + '-' + sink_topic.name.value + '.log'
    subscription_data['log-file'] = logfile
    subscriptions = {}
    if subscription_mode == "on-change":
        subscription = {}
        paths = []
        if type(xpath) is str:
            paths.append(xpath)
        elif type(xpath) is list:
            paths = xpath
        subscription['paths'] = paths
        subscription['stream-mode'] = 'on-change'
    elif subscription_mode == "sample":
        subscription = {}
        paths = []
        if type(xpath) is str:
            paths.append(xpath)
        elif type(xpath) is list:
            paths = xpath
        subscription['paths'] = paths
        subscription['stream-mode'] = 'sample'
        interval = task.arguments.value['interval']
        interval_unit = UnitCode[task.arguments.unitCode].value
        subscription['sample-interval'] = interval+interval_unit
        subscription['qos'] = 0
    subscriptions['subscription'] = subscription
    subscription_data['subscriptions'] = subscriptions
    outputs = {}
    output = {}
    output['type'] = 'kafka'
    output['address'] = 'kafka:9092'
    output['topic'] = gnmic_topic
    output['max-retry'] = 2
    output['timeout'] = '5s'
    output['recovery-wait-time'] = '10s'
    output['format'] = 'event'
    output['num-workers'] = 1
    debug = False
    output['debug'] = debug
    outputs['output'] = output
    subscription_data['outputs'] = outputs

    with open(filename, 'w') as file:
        json.dump(subscription_data, file, indent=4)

    # Collect variables for TelemetrySource
    command_arguments = "--config {0} subscribe".format(filename)
    sink_broker_url = sink_endpoint.uri.value
    sink_topic_name = sink_topic.name.value
    arguments = {
        "command": "gnmic",
        "command_arguments": command_arguments,
        "sink_broker_url": sink_broker_url,
        "sink_topic": sink_topic_name
    }
    return arguments


def config_logparser_source(task: Task, ngsi_ld: NGSILDClient) -> dict:
    """
    Builds configuration arguments for LogParserSOSource application (NiFi)
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
        task.hasOutput.object)
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
    group_id = task.arguments.value["groupId"]

    arguments = {
        "group_id": group_id,
        "source_broker_url": source_broker_url,
        "source_topics": source_topic_name,
        "sink_broker_url": sink_broker_url,
        "sink_topic": sink_topic_name
    }
    return arguments

def config_prometheus2openmetrics_transformer(task: Task, ngsi_ld: NGSILDClient) -> dict:
    """
    Builds configuration arguments for PrometheusToOpenmetricsTranformer application (NiFi)
    """
    # Collect lineage information

    # Task Input
    # Get source Kafka topic
    source_topic = ngsi_ld.get_kafka_topic(
        task.hasInput.object)

    # Task Output
    # Get sink Kafka topic
    sink_topic = ngsi_ld.get_kafka_topic(
        task.hasOutput.object)

    # Prepare variables from context arguments
    source_topic_name = source_topic.name.value
    sink_topic_name = sink_topic.name.value
    group_id = task.arguments.value["groupId"]

    arguments = {
        "group_id": group_id,
        "source_topic": source_topic_name,
        "sink_topic": sink_topic_name
    }
    arguments.update(task.arguments.value)
    return arguments

def config_flink_jobs(task: Task, ngsi_ld: NGSILDClient) -> dict:
    """
    Builds configuration arguments for stream processing applications (Flink)
    """
    # Collect lineage information

    # Task Input
    # Get source Kafka topic
    source_topic = ngsi_ld.get_kafka_topic(
        task.hasInput.object)

    # Task Output
    # Get sink Kafka topic
    sink_topic = ngsi_ld.get_kafka_topic(
        task.hasOutput.object)

    # Prepare variables from context arguments
    source_topic_name = source_topic.name.value
    sink_topic_name = sink_topic.name.value

    arguments = {
        "source_topics": source_topic_name,
        "sink_topic": sink_topic_name
    }
    arguments.update(task.arguments.value)
    return arguments


nifi_application_configs = {
    "EVESource": config_eve_source,
    "MetricSource": config_metric_source,
    "MetricTarget": config_metric_target,
    "gNMIcSource": config_telemetry_source,
    "LogParserSOSource": config_logparser_source,
    "LogParserVSSource": config_logparser_source,
    "PrometheusToOpenmetricsTransformer": config_prometheus2openmetrics_transformer
}
