import json
import os
import unittest

from pygnmi.client import gNMIclient
from semantic_tools.bindings.telemetry.credentials import Credentials
from semantic_tools.bindings.telemetry.device import Device
from semantic_tools.bindings.telemetry.gnmi import Gnmi
from semantic_tools.bindings.telemetry.netconf import Netconf
from semantic_tools.ngsi_ld.api import Options
from semantic_tools.ngsi_ld.client import NGSILDClient
from telemetry_explorer.registration import (discover_gnmi_protocol,
                                             discover_netconf_protocol,
                                             discover_yang_modules,
                                             register_device)

# NGSI-LD Context Broker
BROKER_URI = os.getenv("BROKER_URI", "http://localhost:9090")
# Context Catalog
CONTEXT_CATALOG_URI = os.getenv("CONTEXT_CATALOG_URI",
                                "http://context-catalog:8080/context.jsonld")


class TelemetryExplorer(unittest.TestCase):

    def setUp(self):
        self.ngsi_ld = NGSILDClient(
            url=BROKER_URI,
            context=CONTEXT_CATALOG_URI
        )

    def test_gnmi_discovery(self):
        gnmi_entity = self.ngsi_ld.retrieveEntityById("urn:ngsi-ld:Gnmi:r1")
        gnmi = Gnmi.parse_obj(gnmi_entity)
        gnmi_updated = discover_gnmi_protocol(self.ngsi_ld, gnmi)
        # https://github.com/samuelcolvin/pydantic/issues/1409
        self.ngsi_ld.batchEntityUpsert(
            [json.loads(gnmi_updated.json(
                exclude_none=True, by_alias=True))], Options.update.value
        )

    def test_netconf_discovery(self):
        netconf_entity = self.ngsi_ld.retrieveEntityById(
            "urn:ngsi-ld:Netconf:r1")
        netconf = Netconf.parse_obj(netconf_entity)
        netconf_updated = discover_netconf_protocol(self.ngsi_ld, netconf)
        # https://github.com/samuelcolvin/pydantic/issues/1409
        self.ngsi_ld.batchEntityUpsert(
            [json.loads(netconf_updated.json(
                exclude_none=True, by_alias=True))], Options.update.value
        )

    def test_yang_modules_discovery(self):
        device_entity = self.ngsi_ld.retrieveEntityById(
            "urn:ngsi-ld:Device:r1")
        device = Device.parse_obj(device_entity)
        netconf_entity = self.ngsi_ld.retrieveEntityById(
            "urn:ngsi-ld:Netconf:r1")
        netconf = Netconf.parse_obj(netconf_entity)
        modules = discover_yang_modules(self.ngsi_ld, netconf, device)
        # https://github.com/samuelcolvin/pydantic/issues/1409
        self.ngsi_ld.batchEntityUpsert(
            [json.loads(module.json(
                exclude_none=True, by_alias=True)) for module in modules],
            Options.update.value
        )

    def test_register_device(self):
        device_entity = self.ngsi_ld.retrieveEntityById(
            "urn:ngsi-ld:Device:r1")
        device = Device.parse_obj(device_entity)
        register_device(self.ngsi_ld, device)
