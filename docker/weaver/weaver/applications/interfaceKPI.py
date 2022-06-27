import json
import logging
import os

from flink_client.api.default_api import DefaultApi as FlinkDefaultApi
from flink_client.api_client import ApiClient as FlinkClient
from ngsi_ld_client.api.entities_api import EntitiesApi as NGSILDEntitiesApi
from ngsi_ld_client.api_client import ApiClient as NGSILDClient
from redis import Redis
from semantic_tools.bindings.pipelines.clarity.gnmi import GnmiCollector
from semantic_tools.bindings.pipelines.clarity.interfaceKPI import (
    InterfaceKpiAggregator, KpiOptions)
from weaver.orchestration.flink import instantiate_job_from_task

logger = logging.getLogger(__name__)

KAFKA_ADDRESS = os.getenv("KAFKA_ADDRESS", "kafka:9092")


def process_interface_kpi_aggregator(
        if_kpi_aggregator: InterfaceKpiAggregator, flink: FlinkClient,
        ngsi_ld: NGSILDClient, redis: Redis) -> InterfaceKpiAggregator:
    logger.info("Processing %s" % (if_kpi_aggregator.id))
    if if_kpi_aggregator.action.value.value == "START":
        jar_id = None
        jar_name = None
        if if_kpi_aggregator.kpi == KpiOptions.packet_loss:
            jar_name = "gnmi-aggregator-packetloss-1.0"
        else:  # Then it's throughput
            jar_name = "gnmi-aggregator-throughput-1.0"
        # Get jar ID from redis based on jar name
        jar_id = redis.hget(
                "FLINK", jar_name).decode('UTF-8')
        # Build arguments for packet-loss
        # Get input GnmiCollector entity
        gnmi_collector = GnmiCollector.parse_obj(
            json.loads(
                NGSILDEntitiesApi(ngsi_ld).retrieve_entity_by_id(
                    path_params={
                        "entityId": if_kpi_aggregator.has_input.object
                    },
                    skip_deserialization=True
                ).response.data
            )
        )

        source_topic = "gnmic-driver-" + \
            gnmi_collector.id.split(":")[-1]  # Use last part of URN
        sink_topic = "interface-kpi-aggregator-" + \
            if_kpi_aggregator.id.split(":")[-1]  # Use last part of URN
        flink_arguments = {
            "kafka_address": KAFKA_ADDRESS,
            "source_topics": source_topic,
            "sink_topic": sink_topic,
            "window_size": str(if_kpi_aggregator.window_size.value)
        }
        job = instantiate_job_from_task(
                flink, if_kpi_aggregator, jar_id, flink_arguments)
        # Store job ID in redis
        redis.hset(if_kpi_aggregator.id, "FLINK", job["jobid"].value)
        return if_kpi_aggregator

    elif if_kpi_aggregator.action.value.value == "END":
        logger.info(
            "Deleting '{0}'...".format(if_kpi_aggregator.id))
        #
        # Delete child Flink job
        #
        job_id = redis.hget(
            if_kpi_aggregator.id, "FLINK").decode('UTF-8')
        try:
            _ = FlinkDefaultApi(flink).jobs_jobid_patch(job_id)
        except Exception:
            logger.warning("Job not found")
        #
        # Delete entity
        #
        logger.info(
            "Deleting '{0}' entity...".format(if_kpi_aggregator.id))
        _ = NGSILDEntitiesApi(ngsi_ld).remove_entity_by_id(
            path_params={
                "entityId": if_kpi_aggregator.id
            },
            skip_deserialization=True
        )
