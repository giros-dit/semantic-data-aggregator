import json
import logging
from typing import List

from ncclient import manager
from ngsi_ld_client.api.entities_api import EntitiesApi as NGSILDEntitiesApi
from ngsi_ld_client.api_client import ApiClient as NGSILDClient
from ngsi_ld_client.model.entity import Entity
from ngsi_ld_client.model.entity_list import EntityList
from pygnmi.client import gNMIclient
from semantic_tools.bindings.telemetry.credentials import Credentials
from semantic_tools.bindings.telemetry.device import Device
from semantic_tools.bindings.telemetry.gnmi import Gnmi, GnmiEncodings
from semantic_tools.bindings.telemetry.module import ImplementedBy, Module
from semantic_tools.bindings.telemetry.netconf import Netconf

logger = logging.getLogger(__name__)


def discover_gnmi_protocol(ngsi_ld: NGSILDClient, gnmi: Gnmi) -> Gnmi:
    # Get credentials for gnmi service
    credentials = Credentials.parse_obj(
        json.loads(
            NGSILDEntitiesApi(ngsi_ld).query_entities(
                query_params={
                    "type": "Credentials",
                    "q": "authenticates=={0}".format(gnmi.id)
                },
                skip_deserialization=True
            ).response.data
        )[0]  # Assume there is only one entity
    )
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
        ngsi_ld: NGSILDClient, netconf: Netconf) -> Netconf:
    # Get credentials for netconf service
    credentials = Credentials.parse_obj(
        json.loads(
            NGSILDEntitiesApi(ngsi_ld).query_entities(
                query_params={
                    "type": "Credentials",
                    "q": "authenticates=={0}".format(netconf.id)
                },
                skip_deserialization=True
            ).response.data
        )[0]  # Assume there is only one entity
    )
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
        ngsi_ld: NGSILDClient, netconf: Netconf,
        device: Device) -> List[Module]:
    # Get credentials for gnmi service
    credentials = Credentials.parse_obj(
        json.loads(
            NGSILDEntitiesApi(ngsi_ld).query_entities(
                query_params={
                    "type": "Credentials",
                    "q": "authenticates=={0}".format(netconf.id)
                },
                skip_deserialization=True
            ).response.data
        )[0]  # Assume there is only one entity
    )
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
        ngsi_ld: NGSILDClient,
        entity: dict):

    logger.info("Processing registration of network device...")
    device = Device.parse_obj(entity)

    # Check gNMI support
    gnmi_entities = NGSILDEntitiesApi(ngsi_ld).query_entities(
        query_params={
            "type": "Gnmi",
            "q": "supportedBy=={0}".format(device.id)
        },
        skip_deserialization=True
    ).response.data

    if gnmi_entities:
        # Device can only support one gnmi service
        gnmi = Gnmi.parse_obj(
            json.loads(gnmi_entities)[0]
        )
        gnmi_updated = discover_gnmi_protocol(ngsi_ld, gnmi)
        logger.info("Updating %s" % gnmi_updated.id)
        # https://github.com/samuelcolvin/pydantic/issues/1409
        _ = NGSILDEntitiesApi(ngsi_ld).batch_entity_upsert(
            query_params={
                "options": "update"
            },
            body=EntityList([
                Entity(json.loads(
                    gnmi_updated.json(exclude_none=True, by_alias=True)))
            ])
        )

    # Discover NETCONF
    netconf = Netconf.parse_obj(
        json.loads(
            NGSILDEntitiesApi(ngsi_ld).query_entities(
                query_params={
                    "type": "Netconf",
                    "q": "supportedBy=={0}".format(device.id)
                },
                skip_deserialization=True
            ).response.data
        )[0]  # Device can only support one netconf service
    )
    netconf_updated = discover_netconf_protocol(ngsi_ld, netconf)
    logger.info("Creating %s" % netconf_updated.id)
    _ = NGSILDEntitiesApi(ngsi_ld).batch_entity_upsert(
            query_params={
                "options": "update"
            },
            body=EntityList([
                Entity(json.loads(
                    netconf_updated.json(exclude_none=True, by_alias=True)))
            ])
        )
    # Thus far, rely on NETCONF capabilities to discover YANG modules
    # NETCONF hello retrieves features, deviations,
    # and submodules (as other modules though)
    logger.info("Collecting implemented YANG modules")
    modules = discover_yang_modules(ngsi_ld, netconf, device)
    _ = NGSILDEntitiesApi(ngsi_ld).batch_entity_upsert(
            query_params={
                "options": "update"
            },
            body=EntityList([
                Entity(json.loads(
                    module.json(exclude_none=True, by_alias=True)))
                for module in modules])
        )
