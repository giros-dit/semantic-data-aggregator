import logging
import os

from redis import Redis
from semantic_tools.bindings.clarity_data_lake.datalake import DataLake
from semantic_tools.bindings.pipelines.clarity.datalake import \
    DataLakeDispatcher
from semantic_tools.bindings.pipelines.clarity.interfaceKPI import \
    InterfaceKpiAggregator
from semantic_tools.ngsi_ld.api import NGSILDAPI
from weaver.orchestration.flink import FlinkClient
from weaver.orchestration.nifi import NiFiClient

logger = logging.getLogger(__name__)

KAFKA_ADDRESS = os.getenv("KAFKA_ADDRESS", "kafka:9092")


def config_nifi_data_lake_consumer(
        data_lake_dispatcher: DataLakeDispatcher, ngsi_ld: NGSILDAPI) -> dict:
    """
    Deploys a DataLakeConsumer NiFi template
    from the specified DataLakeDispatcher NGSI-LD entity.
    """

    # Task Output
    # Get output DataLake
    out_data_lake = DataLake.parse_obj(
        ngsi_ld.retrieveEntityById(data_lake_dispatcher.has_output.object)
    )
    # Build arguments
    api_gw_endpoint = str(out_data_lake.uri.value)
    api_gw_key = out_data_lake.api_key.value
    bucket = data_lake_dispatcher.bucket.value
    instance_file_name = data_lake_dispatcher.instance_file_name.value
    source_group_id = "sda"
    source_topic = "yang-instance-driver-" + \
                   data_lake_dispatcher.id.split(":")[-1]  # Use last part of URN
    # Collect variables for DataLakeConsumer
    arguments = {
        "api_gw_endpoint": api_gw_endpoint,
        "api_gw_key": api_gw_key,
        "bucket": bucket,
        "group_id": source_group_id,
        "kafka_address": KAFKA_ADDRESS,
        "instance_file_name": instance_file_name,
        "source_topics": source_topic,
    }
    return arguments


def process_data_lake_dispatcher(
        data_lake_dispatcher: DataLakeDispatcher, flink: FlinkClient,
        nifi: NiFiClient, ngsi_ld: NGSILDAPI, redis: Redis
        ) -> DataLakeDispatcher:
    logger.info("Processing %s" % (data_lake_dispatcher.id))
    if data_lake_dispatcher.action.value.value == "START":
        #
        # Instantiate Flink job with YangInstanceDriver jar
        #
        # Get jar ID from redis based on jar name
        jar_name = "PrometheusConsumerJob"  # TODO: Change to YangInstanceDriver once ready
        jar_id = redis.hget(
            "FLINK", jar_name).decode('UTF-8')
        # Build arguments for YangInstanceDriver
        # Get input InterfaceKpiAggregator entity
        if_kpi_aggregator = InterfaceKpiAggregator.parse_obj(
            ngsi_ld.retrieveEntityById(data_lake_dispatcher.has_input.object)
        )
        source_topic = "interface-kpi-aggregator-" + \
            if_kpi_aggregator.id.split(":")[-1]  # Use last part of URN
        sink_topic = "yang-instance-driver-" + \
            data_lake_dispatcher.id.split(":")[-1]  # Use last part of URN
        instance_file_name = data_lake_dispatcher.instance_file_name.value
        flink_arguments = {
            "kafka_address": KAFKA_ADDRESS,
            "source_topics": source_topic,
            "sink_topic": sink_topic,
            "instance_file_name": instance_file_name
        }
        job = flink.instantiate_job_from_task(
                data_lake_dispatcher, jar_id, flink_arguments)
        # Store job ID in redis
        redis.hset(data_lake_dispatcher.id, "FLINK", job["jobid"])
        #
        # Instantiate NiFi flow with DataLakeConsumer template
        #
        # Get template ID from redis based on template name
        template_name = "DataLakeConsumer"
        template_id = redis.hget(
            "NIFI", template_name).decode('UTF-8')
        # Build arguments for DataLakeConsumer
        nifi_arguments = config_nifi_data_lake_consumer(
            data_lake_dispatcher, ngsi_ld)
        # Renew access token for NiFi API
        nifi.login()
        flow_pg = nifi.instantiate_flow_from_task(
            data_lake_dispatcher, template_id, nifi_arguments)
        redis.hset(data_lake_dispatcher.id, "NIFI", flow_pg.id)
        return data_lake_dispatcher

    elif data_lake_dispatcher.action.value.value == "END":
        logger.info(
            "Deleting '{0}'...".format(data_lake_dispatcher.id))
        #
        # Delete child Flink job
        #
        job_id = redis.hget(
            data_lake_dispatcher.id, "FLINK").decode('UTF-8')
        try:
            flink.deleteJob(job_id)
        except Exception:
            logger.warning("Job not found")
        #
        # Delete child NiFi flow
        #
        # Renew access token for NiFi API
        nifi.login()
        try:
            nifi.delete_flow_from_task(data_lake_dispatcher)
        except Exception:
            logger.warning("Flow not found")
        #
        # Delete entity
        #
        logger.info(
            "Deleting '{0}' entity...".format(data_lake_dispatcher.id))
        ngsi_ld.deleteEntity(data_lake_dispatcher.id)
