import unittest

from bindings.pipelines.clarity.datalake import DataLakeDispatcher
from bindings.pipelines.clarity.gnmi import GnmiCollector
from bindings.pipelines.clarity.interfaceKPI import InterfaceKpiAggregator


class Pipelines(unittest.TestCase):
    def test_data_lake_dispatcher_packet_loss(self):
        self.assertIsInstance(
            DataLakeDispatcher.parse_file(
                "examples/pipelines/datalake/example-normalized-packet-loss.json"),
            DataLakeDispatcher)

    def test_data_lake_dispatcher_throughput(self):
        self.assertIsInstance(
            DataLakeDispatcher.parse_file(
                "examples/pipelines/datalake/example-normalized-throughput.json"),
            DataLakeDispatcher)

    def test_gnmi_collector_on_change(self):
        self.assertIsInstance(
            GnmiCollector.parse_file(
                "examples/pipelines/gnmi/example-normalized-on-change.json"),
            GnmiCollector)

    def test_gnmi_collector_periodic(self):
        self.assertIsInstance(
            GnmiCollector.parse_file(
                "examples/pipelines/gnmi/example-normalized-periodic.json"),
            GnmiCollector)

    def test_interface_kpi_aggregator_packet_loss(self):
        self.assertIsInstance(
            InterfaceKpiAggregator.parse_file(
                "examples/pipelines/interfaceKPI/example-normalized-packet-loss.json"),
            InterfaceKpiAggregator)

    def test_interface_kpi_aggregator_throughput(self):
        self.assertIsInstance(
            InterfaceKpiAggregator.parse_file(
                "examples/pipelines/interfaceKPI/example-normalized-throughput.json"),
            InterfaceKpiAggregator)
