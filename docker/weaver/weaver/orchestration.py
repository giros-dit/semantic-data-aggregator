import logging

from redis import Redis
from semantic_tools.bindings.entity import Entity
from semantic_tools.bindings.pipelines.clarity.datalake import \
    DataLakeDispatcher
from semantic_tools.bindings.pipelines.clarity.gnmi import GnmiCollector
from semantic_tools.bindings.pipelines.clarity.interfaceKPI import \
    InterfaceKpiAggregator
from semantic_tools.bindings.pipelines.task import StateOptions
from semantic_tools.flink.client import FlinkClient
from semantic_tools.ngsi_ld.client import NGSILDClient
from semantic_tools.nifi.client import NiFiClient

from weaver.applications.gnmi import process_gnmi_collector

logger = logging.getLogger(__name__)


def process_task(entity: Entity, flink: FlinkClient,
                 nifi: NiFiClient, ngsi_ld: NGSILDClient, redis: Redis):
    """
    Process Task entity
    """
    if entity.type == "GnmiCollector":
        gnmi_collector = GnmiCollector.parse_obj(entity)
        process_gnmi_collector(gnmi_collector, flink, nifi, ngsi_ld, redis)
    if entity.type == "InterfaceKpiAggregator":
        if_kpi_aggr = InterfaceKpiAggregator.parse_obj(entity)
    if entity.type == "DataLakeDispatcher":
        data_lake_dispatcher = DataLakeDispatcher.parse_obj(entity)

