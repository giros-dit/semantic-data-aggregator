from semantic_tools.clients.ngsi_ld import NGSILDClient
from semantic_tools.models.common import Endpoint
from semantic_tools.models.metric import Prometheus
from semantic_tools.models.telemetry import Device
from ops import ngsi_ld_ops

import json
import logging
import requests
import subprocess

logger = logging.getLogger(__name__)


def processDeviceState(device: Device, ngsi: NGSILDClient):
    """
    Process Telemetry-based data source context information entities (i.e., Device entities) for telemetry collection.
    """
    ngsi_ld_ops.appendState(ngsi, device.id, "Building the Device data source context information entity...")
    endpoint_id = device.hasEndpoint.object
    endpoint_exists = checkEndpointExistence(endpoint_id, ngsi)
    if device.action.value == "START":
        if endpoint_exists == True:
            logger.info(
                "Create a new '{0}' Device data source context information entity.".format(device.id)
            )
            endpoint_entity = ngsi.retrieveEntityById(endpoint_id)
            endpoint = Endpoint.parse_obj(endpoint_entity)

            filename = '/gnmic-cfgs/cfg-capabilities.json'
            with open(filename, 'r') as file:
                data = json.load(file)
                data['address'] = endpoint.uri.value.split("://")[1]

            os.remove(filename)

            with open(filename, 'w') as file:
                json.dump(data, file, indent=4)

            capabilities = subprocess.Popen(
                ['gnmic', '--config', filename, 'capabilities',
                 '--format', 'protojson'], stdout=subprocess.PIPE,
                stderr=subprocess.STDOUT)
            stdout, stderr = capabilities.communicate()
            try:
                output = json.loads(stdout)

                version = output['gNMIVersion']
                version_dict = {
                    "version": {
                        "type": "Property",
                        "value": version
                    }
                }
                ngsi.appendEntityAttrs(device.id, version_dict)
                ngsi_ld_ops.stateToEnabled(ngsi, device.id, {"value": "SUCCESS! Device data source enabled successfully."})
            except ValueError as err:
                output = stdout.decode("utf-8")
                logger.info(
                    "The action failed. An error exception occurred: {0}".format(output)
                )
                ngsi_ld_ops.stateToFailed(ngsi, device.id, {"value": "ERROR! The '{0}' Endpoint entity is not a correct telemetry endpoint.".format(endpoint_id)})
                logger.info("Delete the '{0}' Device data source entity.".format(device.id))
                ngsi.deleteEntity(device.id)
                logger.info("Delete the '{0}' Endpoint entity.".format(endpoint_id))
                ngsi.deleteEntity(endpoint_id)
        else:
            logger.info(
                "Create a new '{0}' Device data source context information entity. The action failed. The specified endpoint does not exist.".format(device.id)
            )
            ngsi_ld_ops.stateToFailed(ngsi, device.id, {"value": "ERROR! The '{0}' Endpoint entity does not exist.".format(endpoint_id)})
            logger.info("Delete the '{0}' Device data source entity.".format(device.id))
            ngsi.deleteEntity(device.id)
    elif device.action.value == "END":
        logger.info(
            "Delete the '{0}' Device data source entity. ".format(device.id)
        )
        ngsi_ld_ops.stateToDisabled(ngsi, device.id, {"value": "SUCCESS! Device data source disabled successfully."})
        ngsi.deleteEntity(device.id)


def processEndpointState(endpoint: Endpoint, ngsi: NGSILDClient):
    """
    Process Endpoint entities of data sources for data collection.
    """
    ngsi_ld_ops.appendState(ngsi, endpoint.id, "Building the Endpoint entity...")
    if endpoint.action.value == "START":
        logger.info("Create a new '{0}' Endpoint entity.".format(endpoint.id))
        ngsi_ld_ops.stateToEnabled(
            ngsi, endpoint.id, {"value": "SUCCESS! Endpoint enabled successfully."})
    elif endpoint.action.value == "END":
        logger.info("Delete the '{0}' Endpoint entity. ".format(endpoint.id))
        ngsi_ld_ops.stateToDisabled(ngsi, endpoint.id, {"value": "SUCCESS! Endpoint disabled successfully.".format(endpoint.id)})
        ngsi.deleteEntity(endpoint.id)


def processPrometheusState(prometheus: Prometheus, ngsi: NGSILDClient):
    """
    Process Prometheus-based data source context
    information entities for metric collection.
    """
    ngsi_ld_ops.appendState(
        ngsi, prometheus.id,
        "Building the Prometheus data source context information entity...")
    endpoint_id = prometheus.hasEndpoint.object
    if prometheus.action.value == "START":
        if endpoint_exists == True:
            logger.info(
                "Create a new '{0}' Prometheus data source "
                "context information entity.".format(prometheus.id)
            )
            endpoint_entity = ngsi.retrieveEntityById(endpoint_id)
            endpoint = Endpoint.parse_obj(endpoint_entity)
            try:
                response = requests.get(
                    "{0}?query=prometheus_build_info".format(
                        endpoint.uri.value))
                response.raise_for_status()
                version = response.json()[
                    'data']['result'][0]['metric']['version']
                version_dict = {
                    "version": {
                        "type": "Property",
                        "value": version
                    }
                }
                ngsi.appendEntityAttrs(prometheus.id, version_dict)
                ngsi_ld_ops.stateToEnabled(
                    ngsi, prometheus.id, {"value": "SUCCESS! Prometheus data source enabled successfully."})
            except requests.exceptions.RequestException as err:
                logger.info(
                    "The action failed. A request error exception occurred: '{0}'".format(err)
                )
                ngsi_ld_ops.stateToFailed(ngsi, prometheus.id, {"value": "ERROR! The '{0}' Endpoint entity is not a correct Prometheus endpoint.".format(endpoint_id)})
                logger.info("Delete the '{0}' Prometheus data source entity.".format(prometheus.id))
                ngsi.deleteEntity(prometheus.id)
                logger.info("Delete the '{0}' Endpoint entity.".format(endpoint_id))
                ngsi.deleteEntity(endpoint_id)
        else:
            logger.info(
                "Create a new '{0}' Prometheus data source context information entity. The action failed. The specified endpoint does not exist.".format(prometheus.id)
            )
            ngsi_ld_ops.stateToFailed(ngsi, prometheus.id, {"value": "ERROR! The '{0}' Endpoint entity does not exist.".format(endpoint_id)})
            logger.info("Delete the '{0}' Prometheus data source entity.".format(prometheus.id))
            ngsi.deleteEntity(prometheus.id)
    elif prometheus.action.value == "END":
        logger.info("Delete the '{0}' Prometheus data source entity. ".format(prometheus.id))
        ngsi_ld_ops.stateToDisabled(ngsi, prometheus.id, {"value": "SUCCESS! Prometheus data source disabled successfully."})
        ngsi.deleteEntity(prometheus.id)
