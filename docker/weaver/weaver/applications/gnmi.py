import json
import logging
import os

from flink_client.api.default_api import DefaultApi as FlinkClient
from redis import Redis
from semantic_tools.bindings.pipelines.clarity.gnmi import (GnmiCollector,
                                                            StreamModeOptions)
from semantic_tools.bindings.telemetry.credentials import Credentials
from semantic_tools.bindings.telemetry.device import Device
from semantic_tools.bindings.telemetry.gnmi import Gnmi
from semantic_tools.ngsi_ld.api import NGSILDAPI
from weaver.orchestration.flink import instantiate_job_from_task
from weaver.orchestration.nifi import NiFiClient

logger = logging.getLogger(__name__)

UNIT_CODES = {
    "C26": "ms"
}
KAFKA_ADDRESS = os.getenv("KAFKA_ADDRESS", "kafka:9092")


def config_nifi_gnmic_source(
        gnmi_collector: GnmiCollector, ngsi_ld: NGSILDAPI) -> dict:
    """
    Deploys a gNMIcSource NiFi template
    from a passed GnmiCollector NGSI-LD entity.
    """

    # Task Input
    # Get source Device
    source_device = Device.parse_obj(
        ngsi_ld.retrieveEntityById(gnmi_collector.has_input.object)
    )
    # Get Gnmi for source device
    source_gnmi = Gnmi.parse_obj(
        ngsi_ld.queryEntities(
            "Gnmi", q="supportedBy=={0}".format(source_device.id))[0]
    )
    # Get credentials for Credentials service
    source_gnmi_cred = Credentials.parse_obj(
        ngsi_ld.queryEntities(
            "Credentials", q="authenticates=={0}".format(source_gnmi.id))[0]
    )
    # Build arguments
    gnmic_topic = "gnmic-source-" + \
        gnmi_collector.id.split(":")[-1]  # Use last part of URN
    # Get subscription mode (sample or on-change)
    filename = (
        '/gnmic-cfgs/subscription' + '-' + gnmic_topic + '.json')
    subscription_data = {}
    subscription_data['address'] = str(source_gnmi.address.value)
    subscription_data['port'] = str(source_gnmi.port.value)
    subscription_data['username'] = source_gnmi_cred.username.value
    subscription_data['password'] = source_gnmi_cred.password.value
    subscription_data['insecure'] = 'true'
    logfile = '/tmp/' + gnmic_topic + '.log'
    subscription_data['log-file'] = logfile
    subscriptions = {}
    subscription = {
        "mode": gnmi_collector.mode.value.value
    }
    if type(gnmi_collector.paths.value) == list:
        subscription["paths"] = gnmi_collector.paths.value
    else:
        subscription["paths"] = [gnmi_collector.paths.value]
    if gnmi_collector.allow_aggregation:
        subscription[
            "allow-aggregation"] = gnmi_collector.allow_aggregation.value
    if gnmi_collector.encoding:
        subscription["encoding"] = gnmi_collector.encoding.value.value
    if gnmi_collector.heartbeat_interval:
        subscription["heartbeat-interval"] = str(
            gnmi_collector.heartbeat_interval.value)
    if gnmi_collector.prefix:
        subscription["prefix"] = gnmi_collector.prefix.value
    if gnmi_collector.qos:
        subscription["qos"] = str(gnmi_collector.qos.value)
    if gnmi_collector.sample_interval:
        if gnmi_collector.stream_mode == StreamModeOptions.on_change:
            logger.warning(
                "Sample interval not compatible with on-change subscriptions. "
                "Ignoring this parameter ...")
        else:
            interval = gnmi_collector.sample_interval.value
            interval_unit = gnmi_collector.sample_interval.unit_code
            subscription[
                'sample-interval'] = str(interval) + UNIT_CODES[interval_unit]
    if gnmi_collector.stream_mode:
        subscription[
            "stream-mode"] = gnmi_collector.stream_mode.value.value
    if gnmi_collector.suppress_redundant:
        subscription[
            "suppress-redundant"] = gnmi_collector.suppress_redundant.value
    if gnmi_collector.updates_only:
        subscription["updates-only"] = gnmi_collector.updates_only.value
    if gnmi_collector.use_models:
        subscription["models"] = gnmi_collector.use_models.value

    subscriptions["subscription"] = subscription
    subscription_data['subscriptions'] = subscriptions
    outputs = {}
    output = {}
    output['type'] = 'kafka'
    output['address'] = KAFKA_ADDRESS
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
    arguments = {
        "command": "gnmic",
        "command_arguments": command_arguments
    }
    return arguments


def process_gnmi_collector(
        gnmi_collector: GnmiCollector, flink: FlinkClient,
        nifi: NiFiClient, ngsi_ld: NGSILDAPI, redis: Redis
        ) -> GnmiCollector:
    logger.info("Processing %s" % (gnmi_collector.id))
    if gnmi_collector.action.value.value == "START":
        #
        # Instantiate NiFi flow with gNMIcSource template
        #
        # Get template ID from redis based on template name
        template_name = "gNMIcSource"
        template_id = redis.hget(
            "NIFI", template_name).decode('UTF-8')
        # Build arguments for gNMIcSouce
        nifi_arguments = config_nifi_gnmic_source(
            gnmi_collector, ngsi_ld)
        # Renew access token for NiFi API
        nifi.login()
        flow_pg = nifi.instantiate_flow_from_task(
            gnmi_collector, template_id, nifi_arguments)
        redis.hset(gnmi_collector.id, "NIFI", flow_pg.id)
        #
        # Instantiate Flink job with gNMIcDriver jar
        #
        # Get jar ID from redis based on jar name
        jar_name = "gnmi-source-1.0"
        jar_id = redis.hget(
            "FLINK", jar_name).decode('UTF-8')
        # Build arguments for gNMIcDriver
        source_topic = "gnmic-source-" + \
            gnmi_collector.id.split(":")[-1]  # Use last part of URN
        sink_topic = "gnmic-driver-" + \
            gnmi_collector.id.split(":")[-1]  # Use last part of URN
        flink_arguments = {
            "kafka_address": KAFKA_ADDRESS,
            "source_topics": source_topic,
            "sink_topic": sink_topic
        }
        job = instantiate_job_from_task(
            flink, gnmi_collector, jar_id, flink_arguments)
        # Store job ID in redis
        redis.hset(gnmi_collector.id, "FLINK", job["jobid"].value)
        return gnmi_collector

    elif gnmi_collector.action.value.value == "END":
        logger.info(
            "Deleting '{0}'...".format(gnmi_collector.id))
        #
        # Delete child NiFi flow
        #
        # Renew access token for NiFi API
        nifi.login()
        try:
            nifi.delete_flow_from_task(gnmi_collector)
        except Exception:
            logger.warning("Flow not found")
        # Clean gNMIc subscription file
        gnmic_topic = "gnmic-source-" + \
            gnmi_collector.id.split(":")[-1]  # Use last part of URN
        filename = (
            '/gnmic-cfgs/subscription' + '-' + gnmic_topic + '.json')
        os.remove(filename)
        #
        # Delete child Flink job
        #
        job_id = redis.hget(
            gnmi_collector.id, "FLINK").decode('UTF-8')
        try:
            _ = flink.jobs_jobid_patch(job_id)
        except Exception:
            logger.warning("Job not found")
        #
        # Delete entity
        #
        logger.info(
            "Deleting '{0}' entity...".format(gnmi_collector.id))
        ngsi_ld.deleteEntity(gnmi_collector.id)
