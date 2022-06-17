import unittest

from bindings.telemetry.credentials import Credentials
from bindings.telemetry.device import Device
from bindings.telemetry.gnmi import Gnmi
from bindings.telemetry.module import Module
from bindings.telemetry.netconf import Netconf


class Telemetry(unittest.TestCase):
    def test_credentials(self):
        self.assertIsInstance(
            Credentials.parse_file(
                "examples/telemetry/credentials/example-normalized.json"),
            Credentials)

    def test_device(self):
        self.assertIsInstance(
            Device.parse_file(
                "examples/telemetry/device/example-normalized.json"),
            Device)

    def test_gnmi(self):
        self.assertIsInstance(
            Gnmi.parse_file(
                "examples/telemetry/gnmi/example-normalized.json"),
            Gnmi)

    def test_module(self):
        self.assertIsInstance(
            Module.parse_file(
                "examples/telemetry/module/example-normalized.json"),
            Module)

    def test_netconf(self):
        self.assertIsInstance(
            Netconf.parse_file(
                "examples/telemetry/netconf/example-normalized.json"),
            Netconf)
