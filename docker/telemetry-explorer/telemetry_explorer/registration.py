import json
import logging
from typing import List

from ncclient import manager
from pygnmi.client import gNMIclient
from semantic_tools.bindings.entity import Entity
from semantic_tools.bindings.telemetry.credentials import Credentials
from semantic_tools.bindings.telemetry.device import Device
from semantic_tools.bindings.telemetry.gnmi import Gnmi, GnmiEncodings
from semantic_tools.bindings.telemetry.module import ImplementedBy, Module
from semantic_tools.bindings.telemetry.netconf import Netconf
from semantic_tools.ngsi_ld.api import NGSILDAPI

logger = logging.getLogger(__name__)


def discover_gnmi_protocol(ngsi_ld: NGSILDAPI, gnmi: Gnmi) -> Gnmi:
    # Get credentials for gnmi service
    credentials_entity = ngsi_ld.queryEntities(
        "Credentials", q='authenticates=={0}'.format(gnmi.id))[0]
    credentials = Credentials.parse_obj(credentials_entity)
    gc = gNMIclient(
        target=(str(gnmi.address.value), str(gnmi.port.value)),
        username=credentials.username.value,
        password=credentials.password.value,
        insecure=True,
        debug=True,
    )
    gc.connect()
    capabilities = gc.capabilities()
    raw_encodings = capabilities["supported_encodings"]
    encodings = [GnmiEncodings[ec] for ec in raw_encodings]
    version = capabilities["gnmi_version"]
    gnmi.encodings = {"value": encodings}
    gnmi.version = {"value": version}
    return gnmi


def discover_netconf_protocol(
        ngsi_ld: NGSILDAPI, netconf: Netconf) -> Netconf:
    # Get credentials for gnmi service
    credentials_entity = ngsi_ld.queryEntities(
        "Credentials", q='authenticates=={0}'.format(netconf.id))[0]
    credentials = Credentials.parse_obj(credentials_entity)
    # https://community.cisco.com/t5/devnet-sandbox/
    # connect-to-ios-xr-always-on-sandbox-using-ncclient/td-p/4442858
    nc = manager.connect(
        host=str(netconf.address.value),
        port=netconf.port.value,
        timeout=30,
        username=credentials.username.value,
        password=credentials.password.value,
        hostkey_verify=False,
        look_for_keys=False,
        allow_agent=False,
    )
    capabilities = nc.server_capabilities
    nc_capabilities = []
    for c_key in capabilities:
        capability = capabilities[c_key]
        # NETCONF protocol capabilities
        if "module" not in capability.parameters:
            nc_capabilities.append(capability)
    # Update entity
    netconf.capabilities = {
        "value": [
            capability.namespace_uri for capability in nc_capabilities
        ]
    }
    return netconf


def discover_yang_modules(
        ngsi_ld: NGSILDAPI, netconf: Netconf,
        device: Device) -> List[Module]:
    # Get credentials for gnmi service
    credentials_entity = ngsi_ld.queryEntities(
        "Credentials", q='authenticates=={0}'.format(netconf.id))[0]
    credentials = Credentials.parse_obj(credentials_entity)
    # https://community.cisco.com/t5/devnet-sandbox/
    # connect-to-ios-xr-always-on-sandbox-using-ncclient/td-p/4442858
    nc = manager.connect(
        host=str(netconf.address.value),
        port=netconf.port.value,
        timeout=30,
        username=credentials.username.value,
        password=credentials.password.value,
        hostkey_verify=False,
        look_for_keys=False,
        allow_agent=False,
    )
    capabilities = nc.server_capabilities
    nc_modules = []
    for c_key in capabilities:
        capability = capabilities[c_key]
        # YANG modules
        if "module" in capability.parameters:
            nc_modules.append(capability)

    modules = []
    for nc_module in nc_modules:
        name = nc_module.parameters["module"]
        revision = nc_module.parameters["revision"]
        # Build Module entity
        deviation = None
        feature = None
        if "deviations" in nc_module.parameters:
            deviation = {
                "value": nc_module.parameters["deviations"].split(",")}
            logger.warning("Found deviation %s" % deviation)
        if "features" in nc_module.parameters:
            feature = {
                "value": nc_module.parameters["features"].split(",")}
            logger.warning("Found feature %s" % feature)
        implemented_by_rel = ImplementedBy(
            object=device.id,
            deviation=deviation,
            feature=feature,
            dataset_id=device.id,
        )
        module = Module(
            id="urn:ngsi-ld:Module:{0}:{1}".format(name, revision),
            name={"value": name},
            revision={"value": revision},
            namespace={"value": nc_module.namespace_uri},
            implemented_by=implemented_by_rel,
        )
        modules.append(module)

    return modules


def register_device(
        ngsi_ld: NGSILDAPI,
        entity: Entity):

    logger.info("Processing registration of network device...")
    device = Device.parse_obj(entity.dict())

    # Check gNMI support
    gnmi_entities = ngsi_ld.queryEntities(
        "Gnmi", q='supportedBy=={0}'.format(device.id))
    if gnmi_entities:
        # Device can only support one gnmi service
        gnmi = Gnmi.parse_obj(gnmi_entities[0])
        gnmi_updated = discover_gnmi_protocol(ngsi_ld, gnmi)
        logger.info("Updating %s" % gnmi_updated.id)
        # https://github.com/samuelcolvin/pydantic/issues/1409
        ngsi_ld.batchEntityUpsert(
            [json.loads(gnmi_updated.json(
                exclude_none=True, by_alias=True))], "update"
        )

    # Discover NETCONF
    netconf_entities = ngsi_ld.queryEntities(
        "Netconf", q='supportedBy=={0}'.format(device.id))
    # Device can only support one netconf service
    netconf = Netconf.parse_obj(netconf_entities[0])
    netconf_updated = discover_netconf_protocol(ngsi_ld, netconf)
    logger.info("Creating %s" % netconf_updated.id)
    ngsi_ld.batchEntityUpsert(
        [json.loads(netconf_updated.json(
            exclude_none=True, by_alias=True))], "update"
    )
    # Thus far, rely on NETCONF capabilities to discover YANG modules
    # NETCONF hello retrieves features, deviations,
    # and submodules (as other modules though)
    logger.info("Collecting implemented YANG modules")
    modules = discover_yang_modules(ngsi_ld, netconf, device)
    res = ngsi_ld.batchEntityUpsert(
        [json.loads(module.json(
            exclude_none=True, by_alias=True)) for module in modules],
        "update"
    )
    logger.error(res.request.headers)
    logger.error(res.request.path_url)
    logger.error(res.request.body)
