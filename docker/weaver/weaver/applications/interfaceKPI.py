import logging
import os

from redis import Redis
from semantic_tools.bindings.pipelines.clarity.gnmi import GnmiCollector
from semantic_tools.bindings.pipelines.clarity.interfaceKPI import (
    InterfaceKpiAggregator, KpiOptions)
from semantic_tools.ngsi_ld.api import NGSILDAPI
from weaver.orchestration.flink import FlinkClient

logger = logging.getLogger(__name__)

KAFKA_ADDRESS = os.getenv("KAFKA_ADDRESS", "kafka:9092")


def process_interface_kpi_aggregator(
        if_kpi_aggregator: InterfaceKpiAggregator, flink: FlinkClient,
        ngsi_ld: NGSILDAPI, redis: Redis) -> InterfaceKpiAggregator:
    logger.info("Processing %s" % (if_kpi_aggregator.id))
    if if_kpi_aggregator.action.value.value == "START":
        jar_id = None
        jar_name = None
        if if_kpi_aggregator.kpi == KpiOptions.packet_loss:
            jar_name = "PrometheusConsumerJob"  # TODO: Change to packet-loss once ready
        else:  # Then it's throughput
            jar_name = "PrometheusConsumerJob"  # TODO: Change to throughput once ready
        # Get jar ID from redis based on jar name
        jar_id = redis.hget(
                "FLINK", jar_name).decode('UTF-8')
        # Build arguments for packet-loss
        # Get input GnmiCollector entity
        gnmi_collector = GnmiCollector.parse_obj(
            ngsi_ld.retrieveEntityById(if_kpi_aggregator.has_input.object)
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
        job = flink.instantiate_job_from_task(
                if_kpi_aggregator, jar_id, flink_arguments)
        # Store job ID in redis
        redis.hset(if_kpi_aggregator.id, "FLINK", job["jobid"])
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
            flink.deleteJob(job_id)
        except Exception:
            logger.warning("Job not found")
        #
        # Delete entity
        #
        logger.info(
            "Deleting '{0}' entity...".format(if_kpi_aggregator.id))
        ngsi_ld.deleteEntity(if_kpi_aggregator.id)
