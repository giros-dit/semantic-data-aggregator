from nipyapi.nifi.models.process_group_entity import ProcessGroupEntity
from semantic_tools.clients.ngsi_ld import NGSILDClient
from semantic_tools.models.metric import Endpoint, MetricSource, MetricTarget, Prometheus
from semantic_tools.models.telemetry import Endpoint, Device, TelemetrySource
from semantic_tools.models.ngsi_ld.entity import Entity
from semantic_tools.utils.units import UnitCode
from urllib3.exceptions import MaxRetryError

import logging
import nipyapi
import ngsi_ld_ops
import time
import sys
import ruamel.yaml
import json
import os

logger = logging.getLogger(__name__)


def check_nifi_status():
    """
    Infinite loop that checks every 30 seconds
    until NiFi REST API becomes available
    """
    logger.info("Checking NiFi REST API status ...")
    while True:
        try:
            nipyapi.system.get_nifi_version_info()
        except MaxRetryError:
            logger.warning("Could not connect to NiFi REST API. "
                           "Retrying in 30 seconds ...")
            time.sleep(30)
            continue
        logger.info("Weaver successfully connected to NiFi REST API!")
        break


def deleteMetricSource(metricSource: MetricSource):
    """
    Delete MetricSource flow
    """
    ms_pg = nipyapi.canvas.get_process_group(metricSource.id)
    nipyapi.canvas.delete_process_group(ms_pg, True)

def deleteMetricTarget(metricTarget: MetricTarget):
    """
    Delete MetricTarget flow
    """
    ms_pg = nipyapi.canvas.get_process_group(metricTarget.id)
    nipyapi.canvas.delete_process_group(ms_pg, True)

def deleteTelemetrySource(telemetrySource: TelemetrySource):
    """
    Delete TelemetrySource flow
    """
    ts_pg = nipyapi.canvas.get_process_group(telemetrySource.id)
    nipyapi.canvas.delete_process_group(ts_pg, True)

def deployMetricSource(metricSource: MetricSource,
                       ngsi: NGSILDClient) -> ProcessGroupEntity:
    """
    Deploys a MetricSource NiFi template
    from a passed MetricSource NGSI-LD entity
    """
    # Get Endpoint
    prometheus_entity = ngsi.retrieveEntityById(metricSource.collectsFrom.object)
    prometheus = Prometheus.parse_obj(prometheus_entity)
    endpoint_entity = ngsi.retrieveEntityById(prometheus.hasEndpoint.object)
    endpoint = Endpoint.parse_obj(endpoint_entity)
    # Build URL based on optional expression
    url = ""
    if metricSource.expression:
        labels = getQueryLabels(metricSource)
        url = (endpoint.uri.value + "?query=" +
               metricSource.name.value + "{" + labels + "}")
    else:
        url = endpoint.uri.value + "?query=" + metricSource.name.value
    # Get topic name from MetricSource entity ID
    entity_id = metricSource.id.strip("urn:ngsi-ld:").replace(":", "-").lower()
    # We assume last string is an integer value
    source_id_number = int(metricSource.id.split(":")[-1])
    # Get root PG
    root_pg = nipyapi.canvas.get_process_group("root")
    # Y multiply ID last integer by 200, X fixed to -250 for MS PGs
    ms_pg = nipyapi.canvas.create_process_group(
                    root_pg,
                    metricSource.id,
                    (-250, 200*source_id_number)
    )
    # Set variable for MS PG
    nipyapi.canvas.update_variable_registry(ms_pg, [("topic", entity_id)])
    nipyapi.canvas.update_variable_registry(ms_pg,
                                            [("prometheus_request", url)])
    # Deploy MS template
    ms_template = nipyapi.templates.get_template("MetricSource")
    ms_pg_flow = nipyapi.templates.deploy_template(ms_pg.id,
                                                   ms_template.id,
                                                   -250, 200)
    # Retrieve GET Prometheus API processor
    http_ps = None
    for ps in ms_pg_flow.flow.processors:
        if ps.status.name == "GET Prometheus API":
            http_ps = ps
            break
    # Update GET Prometheus API interval to the requested value
    interval_unit = UnitCode[metricSource.interval.unitCode].value
    # interval_unit = "ms"
    nipyapi.canvas.update_processor(
        http_ps,
        nipyapi.nifi.ProcessorConfigDTO(
            scheduling_period='{0}{1}'.format(metricSource.interval.value,
                                              interval_unit)))
    return ms_pg

def deployMetricTarget(metricTarget: MetricTarget) -> ProcessGroupEntity:
    """
    Deploys a MetricTarget NiFi template
    from a passed MetricTarget NGSI-LD entity
    """
    # Get topic name from input ID
    input_id = metricTarget.hasInput.object.strip(
                    "urn:ngsi-ld:").replace(":", "-").lower()
    # We assume last string is an integer value
    target_id_number = int(metricTarget.id.split(":")[-1])
    # Get root PG
    root_pg = nipyapi.canvas.get_process_group("root")
    # Y multiply ID last integer by 200, X fixed to 250 for MT PGs
    mt_pg = nipyapi.canvas.create_process_group(
                    root_pg,
                    metricTarget.id,
                    (250, 200*target_id_number))
    # Deploy MT template
    mt_template = nipyapi.templates.get_template("MetricTarget")
    nipyapi.templates.deploy_template(mt_pg.id, mt_template.id)
    # Set variables for MT PG
    nipyapi.canvas.update_variable_registry(
        mt_pg, [("topic", input_id)])
    nipyapi.canvas.update_variable_registry(
        mt_pg, [("consumer_url", metricTarget.uri.value)])
    return mt_pg

def deployTelemetrySource(telemetrySource: TelemetrySource, ngsi: NGSILDClient) -> ProcessGroupEntity:
    """
    Deploys a TelemetrySource NiFi template
    from a passed TelemetrySource NGSI-LD entity
    """
    # Get gNMI server address from hasEndpoint relationship
    device_entity = ngsi.retrieveEntityById(telemetrySource.collectsFrom.object)
    device = Device.parse_obj(device_entity)
    endpoint_entity = ngsi.retrieveEntityById(device.hasEndpoint.object)
    endpoint = Endpoint.parse_obj(endpoint_entity)
    # Get topic name from TelemetrySource entity ID
    entity_id = telemetrySource.id.strip("urn:ngsi-ld:").replace(":", "-").lower()
    # Get subscription mode (sample or on-change)
    subscription_mode = telemetrySource.subscriptionMode.value
    filename = '/gnmic-cfgs/cfg-kafka.json'
    subscription_name = ""
    with open(filename, 'r') as file:
        data = json.load(file)
        data['address'] = endpoint.uri.value.split("://")[1]
        data['outputs']['output']['topic'] = entity_id
        if subscription_mode == "on-change":
            # Get the subscription mode name
            subscription_name = subscription_mode
            # Get the names of the telemetry data paths to subscribe (path names of the YANG module data nodes)
            telemetry_data = []
            if type(telemetrySource.XPath.value) is str:
                telemetry_data.append(telemetrySource.XPath.value)
            elif type(telemetrySource.XPath.value) is list:
                telemetry_data = telemetrySource.XPath.value
            data['subscriptions']['on-change']['paths'] = telemetry_data
        elif subscription_mode['mode'] == "sample": #else: #elif subscription_mode == "sample":
            # Get the subscription mode name
            subscription_name = subscription_mode['mode']
            # Get the names of the telemetry data paths to subscribe (path names of the YANG module data nodes)
            telemetry_data = []
            if type(telemetrySource.XPath.value) is str:
                telemetry_data.append(telemetrySource.XPath.value)
            elif type(telemetrySource.XPath.value) is list:
                telemetry_data = telemetrySource.XPath.value
            data['subscriptions']['sample']['paths'] = telemetry_data
            # Get interval value
            interval = subscription_mode['interval'] #telemetrySource.interval.value
            # Get interval unit
            interval_unit = UnitCode[telemetrySource.subscriptionMode.unitCode].value #UnitCode[telemetrySource.interval.unitCode].value
            data['subscriptions']['sample']['sample-interval'] = interval+interval_unit

    os.remove(filename)
    with open(filename, 'w') as file:
        json.dump(data, file, indent=4)

    # We assume last string is an integer value
    source_id_number = int(telemetrySource.id.split(":")[-1])
    # Get root PG
    root_pg = nipyapi.canvas.get_process_group("root")
    # Y multiply ID last integer by 200, X fixed to 500 for TS PGs
    ts_pg = nipyapi.canvas.create_process_group(
                    root_pg,
                    telemetrySource.id,
                    (500, 200*source_id_number)
    )
    # Set variable for TS PG
    nipyapi.canvas.update_variable_registry(ts_pg, [("command", "gnmic")])
    # arguments = telemetrySource.arguments.value+" --name {0}".format(subscription_mode)
    arguments = "--config {0} subscribe --name {1}".format(filename, subscription_name)
    nipyapi.canvas.update_variable_registry(ts_pg, [("arguments", arguments)])

    # Deploy TS template
    ts_template = nipyapi.templates.get_template("TelemetrySource")
    ts_pg_flow = nipyapi.templates.deploy_template(ts_pg.id, ts_template.id)

    return ts_pg

def getMetricSourcePG(metricSource: MetricSource) -> ProcessGroupEntity:
    """
    Get NiFi flow (Process Group) from MetricSource
    """
    return nipyapi.canvas.get_process_group(metricSource.id)


def getMetricTargetPG(metricTarget: MetricTarget) -> ProcessGroupEntity:
    """
    Get NiFi flow (Process Group) from MetricTarget
    """
    return nipyapi.canvas.get_process_group(metricTarget.id)

def getTelemetrySourcePG(telemetrySource: TelemetrySource) -> ProcessGroupEntity:
    """
    Get NiFi flow (Process Group) from TelemetrySource
    """
    return nipyapi.canvas.get_process_group(telemetrySource.id)

def getQueryLabels(metricSource: MetricSource) -> str:
    """
    Print Prometheus labels to make them consumable
    by Prometheus REST API
    """
    expression = metricSource.expression.value
    labels = []
    for label, value in expression.items():
        labels.append("{0}='{1}'".format(label, value))

    return ",".join(labels)

def instantiateMetricSource(metricSource: MetricSource,
                            ngsi: NGSILDClient):
    """
    Deploys and starts MetricSource template given
    a MetricSource entity
    """
    ms_pg = deployMetricSource(metricSource, ngsi)
    # Schedule MS PG
    nipyapi.canvas.schedule_process_group(ms_pg.id, True)
    logger.info(
        "MetricSource '{0}' scheduled in NiFi.".format(
            metricSource.id))


def instantiateMetricTarget(metricTarget: MetricTarget):
    """
    Deploys and starts MetricTarget template given
    a MetricTarget entity
    """
    mt_pg = deployMetricTarget(metricTarget)
    # Schedule MT PG
    nipyapi.canvas.schedule_process_group(mt_pg.id, True)
    logger.info(
        "MetricTarget '{0}' scheduled in NiFi.".format(
            metricTarget.id))

def instantiateTelemetrySource(telemetrySource: TelemetrySource, ngsi: NGSILDClient):
    """
    Deploys and starts TelemetrySource template given
    a TelemetrySource entity
    """
    ts_pg = deployTelemetrySource(telemetrySource, ngsi)
    # Schedule TS PG
    nipyapi.canvas.schedule_process_group(ts_pg.id, True)
    logger.info(
        "TelemetrySource '{0}' scheduled in NiFi.".format(
            telemetrySource.id))

def processMetricSourceState(metricSource: MetricSource,
                            ngsi: NGSILDClient):
    """
    Process MetricSource resources (i.e., NiFi flow)
    based on the value of the action property
    """
    ngsi_ld_ops.appendState(ngsi, metricSource.id, "Building the collection agent...")
    ms_pg = getMetricSourcePG(metricSource)
    if metricSource.action.value == "START":
        prometheus_id = metricSource.collectsFrom.object
        prometheus_exists = checkSourceExistence(prometheus_id, ngsi)
        if prometheus_exists == True:
            prometheus_entity = ngsi.retrieveEntityById(prometheus_id)
            prometheus = Prometheus.parse_obj(prometheus_entity)
            endpoint_id = prometheus.hasEndpoint.object
            endpoint_exists = checkEndpointExistence(endpoint_id, ngsi)
            if endpoint_exists == True:
                if ms_pg:
                    logger.info(
                        "Upgrade '{0}' NiFi flow.".format(metricSource.id)
                    )
                    upgradeMetricSource(metricSource, ngsi)
                else:
                    logger.info(
                        "Instantiate new '{0}' NiFi flow.".format(metricSource.id)
                    )
                    instantiateMetricSource(metricSource, ngsi)
                ngsi_ld_ops.stateToRunning(ngsi, metricSource.id, {"value": "SUCCESS! Collection agent started successfully."})
            else:
                logger.info("Instantiate '{0}' NiFi flow. The processor execution failed.".format(metricSource.id))
                ngsi_ld_ops.stateToFailed(ngsi, metricSource.id, {"value": "ERROR! The '{0}' Endpoint entity doesn't exist.".format(endpoint_id)})
                logger.info("Delete '{0}' entity".format(metricSource.id))
                ngsi.deleteEntity(metricSource.id)

        else:
            logger.info("Instantiate '{0}' NiFi flow. The processor execution failed.".format(metricSource.id))
            ngsi_ld_ops.stateToFailed(ngsi, metricSource.id, {"value": "ERROR! The '{0}' Source entity doesn't exist.".format(prometheus_id)})
            logger.info("Delete '{0}' entity".format(metricSource.id))
            ngsi.deleteEntity(metricSource.id)
    elif metricSource.action.value == "STOP":
        prometheus_id = metricSource.collectsFrom.object
        prometheus_exists = checkSourceExistence(prometheus_id, ngsi)
        if prometheus_exists == True:
            prometheus_entity = ngsi.retrieveEntityById(prometheus_id)
            prometheus = Prometheus.parse_obj(prometheus_entity)
            endpoint_id = prometheus.hasEndpoint.object
            endpoint_exists = checkEndpointExistence(endpoint_id, ngsi)
            if endpoint_exists == True:
                if ms_pg:
                    logger.info(
                        "Stop '{0}' NiFi flow.".format(metricSource.id)
                    )
                    stopMetricSource(metricSource)
                else:
                    logger.info(
                        "New '{0}' NiFi flow to STOP.".format(metricSource.id)
                    )
                    deployMetricSource(metricSource, ngsi)
                ngsi_ld_ops.stateToStopped(ngsi, metricSource.id, {"value": "SUCCESS! Collection agent stopped successfully."})

            else:
                logger.info("Stop '{0}' NiFi flow. The processor execution failed.".format(metricSource.id))
                ngsi_ld_ops.stateToFailed(ngsi, metricSource.id, {"value": "ERROR! The '{0}' Endpoint entity doesn't exist.".format(endpoint_id)})
                logger.info("Delete '{0}' entity".format(metricSource.id))
                ngsi.deleteEntity(metricSource.id)

        else:
            logger.info("Stop '{0}' NiFi flow. The processor execution failed.".format(metricSource.id))
            ngsi_ld_ops.stateToFailed(ngsi, metricSource.id, {"value": "ERROR! The '{0}' Source entity doesn't exist.".format(prometheus_id)})
            logger.info("Delete '{0}' entity".format(metricSource.id))
            ngsi.deleteEntity(metricSource.id)
    elif metricSource.action.value == "END":
        output_exists = checkOutputExistence(metricSource, ngsi)
        if output_exists == False:
            logger.info(
                "Delete '{0}' NiFi flow.".format(
                    metricSource.id)
            )
            deleteMetricSource(metricSource)
            ngsi_ld_ops.stateToCleaned(ngsi, metricSource.id, {"value": "SUCCESS! Collection agent deleted successfully."})
            logger.info("Delete '{0}' entity".format(metricSource.id))
            ngsi.deleteEntity(metricSource.id)

def processMetricTargetState(metricTarget: MetricTarget,
                            ngsi: NGSILDClient):
    """
    Process MetricTarget resources (i.e., NiFi flow)
    based on the value of the action property
    """
    ngsi_ld_ops.appendState(ngsi, metricTarget.id, "Building the collection agent...")
    mt_pg = getMetricTargetPG(metricTarget)
    if metricTarget.action.value == "START":
        input_exists = checkInputExistence(metricTarget, ngsi)
        if input_exists == True:
            if mt_pg:
                logger.info(
                    "Upgrade '{0}' NiFi flow.".format(
                        metricTarget.id)
                )
                upgradeMetricTarget(metricTarget)
            else:
                logger.info(
                    "Instantiate new '{0}' NiFi flow.".format(
                        metricTarget.id)
                )
                instantiateMetricTarget(metricTarget)
            ngsi_ld_ops.stateToRunning(ngsi, metricTarget.id, {"value": "SUCCESS! Dispatch agent started successfully."})
        else:
            logger.info("Instantiate '{0}' NiFi flow. The processor execution failed.".format(metricTarget.id))
            ngsi_ld_ops.stateToFailed(ngsi, metricTarget.id, {"value": "ERROR! The '{0}' input entity doesn't exist.".format(metricTarget.hasInput.object)})
            logger.info("Delete '{0}' entity".format(metricTarget.id))
            ngsi.deleteEntity(metricTarget.id)
    elif metricTarget.action.value == "STOP":
        input_exists = checkInputExistence(metricTarget, ngsi)
        if input_exists == True:
            if mt_pg:
                logger.info(
                    "Stop '{0}' NiFi flow.".format(
                        metricTarget.id)
                )
                stopMetricTarget(metricTarget)
            else:
                logger.info(
                    "New '{0}' NiFi flow to STOP.".format(
                        metricTarget.id)
                )
                deployMetricTarget(metricTarget)
            ngsi_ld_ops.stateToStopped(ngsi, metricTarget.id, {"value": "SUCCESS! Dispatch agent stopped successfully."})
        else:
            logger.info("Stop '{0}' NiFi flow. The processor execution failed.".format(metricTarget.id))
            ngsi_ld_ops.stateToFailed(ngsi, metricTarget.id, {"value": "ERROR! The '{0}' input entity doesn't exist.".format(metricTarget.hasInput.object)})
            logger.info("Delete '{0}' entity".format(metricTarget.id))
            ngsi.deleteEntity(metricTarget.id)
    elif metricTarget.action.value == "END":
        logger.info(
            "Delete '{0}' NiFi flow.".format(
                metricTarget.id)
        )
        deleteMetricTarget(metricTarget)
        ngsi_ld_ops.stateToCleaned(ngsi, metricTarget.id, {"value": "SUCCESS! Dispatch agent deleted successfully."})
        logger.info("Delete '{0}' entity".format(metricTarget.id))
        ngsi.deleteEntity(metricTarget.id)

def processTelemetrySourceState(telemetrySource: TelemetrySource,
                            ngsi: NGSILDClient):
    """
    Process TelemetrySource resources (i.e., NiFi flow)
    based on the value of the action property
    """
    ngsi_ld_ops.appendState(ngsi, telemetrySource.id, "Building the collection agent...")
    ts_pg = getTelemetrySourcePG(telemetrySource)
    if telemetrySource.action.value == "START":
        device_id = telemetrySource.collectsFrom.object
        device_exists = checkSourceExistence(device_id, ngsi)
        if device_exists == True:
            device_entity = ngsi.retrieveEntityById(device_id)
            device = Device.parse_obj(device_entity)
            endpoint_id = device.hasEndpoint.object
            endpoint_exists = checkEndpointExistence(endpoint_id, ngsi)
            if endpoint_exists == True:
                if ts_pg:
                    logger.info(
                        "Upgrade '{0}' NiFi flow.".format(telemetrySource.id)
                    )
                    upgradeTelemetrySource(telemetrySource, ngsi)
                else:
                    logger.info(
                        "Instantiate new '{0}' NiFi flow.".format(telemetrySource.id)
                    )
                    instantiateTelemetrySource(telemetrySource, ngsi)
                ngsi_ld_ops.stateToRunning(ngsi, telemetrySource.id, {"value": "SUCCESS! Collection agent started successfully."})
            else:
                logger.info("Instantiate '{0}' NiFi flow. The processor execution failed.".format(telemetrySource.id))
                ngsi_ld_ops.stateToFailed(ngsi, telemetrySource.id, {"value": "ERROR! The '{0}' Endpoint entity doesn't exist.".format(endpoint_id)})
                logger.info("Delete '{0}' entity".format(telemetrySource.id))
                ngsi.deleteEntity(telemetrySource.id)

        else:
            logger.info("Instantiate '{0}' NiFi flow. The processor execution failed.".format(telemetrySource.id))
            ngsi_ld_ops.stateToFailed(ngsi, telemetrySource.id, {"value": "ERROR! The '{0}' Source entity doesn't exist.".format(device_id)})
            logger.info("Delete '{0}' entity".format(telemetrySource.id))
            ngsi.deleteEntity(telemetrySource.id)
    elif telemetrySource.action.value == "STOP":
        device_id = telemetrySource.collectsFrom.object
        device_exists = checkSourceExistence(device_id, ngsi)
        if device_exists == True:
            device_entity = ngsi.retrieveEntityById(device_id)
            device = Device.parse_obj(device_entity)
            endpoint_id = device.hasEndpoint.object
            endpoint_exists = checkEndpointExistence(endpoint_id, ngsi)
            if endpoint_exists == True:
                if ts_pg:
                    logger.info(
                        "Stop '{0}' NiFi flow.".format(telemetrySource.id)
                    )
                    stopTelemetrySource(telemetrySource)
                else:
                    logger.info(
                        "New '{0}' NiFi flow to STOP.".format(telemetrySource.id)
                    )
                    deployTelemetrySource(telemetrySource, ngsi)
                ngsi_ld_ops.stateToStopped(ngsi, telemetrySource.id, {"value": "SUCCESS! Collection agent stopped successfully."})

            else:
                logger.info("Stop '{0}' NiFi flow. The processor execution failed.".format(telemetrySource.id))
                ngsi_ld_ops.stateToFailed(ngsi, telemetrySource.id, {"value": "ERROR! The '{0}' Endpoint entity doesn't exist.".format(endpoint_id)})
                logger.info("Delete '{0}' entity".format(telemetrySource.id))
                ngsi.deleteEntity(telemetrySource.id)

        else:
            logger.info("Stop '{0}' NiFi flow. The processor execution failed.".format(telemetrySource.id))
            ngsi_ld_ops.stateToFailed(ngsi, telemetrySource.id, {"value": "ERROR! The '{0}' Source entity doesn't exist.".format(device_id)})
            logger.info("Delete '{0}' entity".format(telemetrySource.id))
            ngsi.deleteEntity(telemetrySource.id)
    elif telemetrySource.action.value == "END":
        output_exists = checkOutputExistence(telemetrySource, ngsi)
        if output_exists == False:
            logger.info(
                "Delete '{0}' NiFi flow.".format(
                    telemetrySource.id)
            )
            deleteTelemetrySource(telemetrySource)
            ngsi_ld_ops.stateToCleaned(ngsi, telemetrySource.id, {"value": "SUCCESS! Collection agent deleted successfully."})
            logger.info("Delete '{0}' entity".format(telemetrySource.id))
            ngsi.deleteEntity(telemetrySource.id)

def stopMetricSource(metricSource: MetricSource) -> ProcessGroupEntity:
    """
    Stop NiFi flow (Process Group) from MetricSource
    """
    ms_pg = nipyapi.canvas.get_process_group(metricSource.id)
    return nipyapi.canvas.schedule_process_group(ms_pg.id, False)

def stopMetricTarget(metricTarget: MetricTarget) -> ProcessGroupEntity:
    """
    Stop NiFi flow (Process Group) from MetricTarget
    """
    mt_pg = nipyapi.canvas.get_process_group(metricTarget.id)
    return nipyapi.canvas.schedule_process_group(mt_pg.id, False)

def stopTelemetrySource(telemetrySource: TelemetrySource) -> ProcessGroupEntity:
    """
    Stop NiFi flow (Process Group) from TelemetrySource
    """
    ts_pg = nipyapi.canvas.get_process_group(telemetrySource.id)
    return nipyapi.canvas.schedule_process_group(ts_pg.id, False)

def upgradeMetricSource(metricSource: MetricSource, ngsi: NGSILDClient):
    """
    Stops flow, updates variables and re-starts flow
    for a given MetricSource entity
    """
    ms_pg = nipyapi.canvas.get_process_group(metricSource.id)
    # Stop MS PG
    nipyapi.canvas.schedule_process_group(ms_pg.id, False)
    # Get Endpoint
    prometheus_entity = ngsi.retrieveEntityById(metricSource.collectsFrom.object)
    prometheus = Prometheus.parse_obj(prometheus_entity)
    endpoint_entity = ngsi.retrieveEntityById(prometheus.hasEndpoint.object)
    endpoint = Endpoint.parse_obj(endpoint_entity)
    # Build URL based on optional expression
    url = ""
    if metricSource.expression:
        labels = getQueryLabels(metricSource)
        url = (endpoint.uri.value + "?query=" +
               metricSource.name.value + "{" + labels + "}")
    else:
        url = endpoint.uri.value + "?query=" + metricSource.name.value
    # Get topic name from MetricSource entity ID
    entity_id = metricSource.id.strip("urn:ngsi-ld:").replace(":", "-").lower()
    # Set variable for MS PG
    nipyapi.canvas.update_variable_registry(ms_pg, [("topic", entity_id)])
    nipyapi.canvas.update_variable_registry(ms_pg,
                                            [("prometheus_request", url)])
    # Retrieve GET Prometheus API processor
    http_ps = None
    ms_pg_flow = nipyapi.canvas.get_flow(ms_pg.id).process_group_flow
    for ps in ms_pg_flow.flow.processors:
        if ps.status.name == "GET Prometheus API":
            http_ps = ps
            break
    # Update GET Prometheus API interval to the requested value
    interval_unit = UnitCode[metricSource.interval.unitCode].value
    # interval_unit = "ms"
    nipyapi.canvas.update_processor(
        http_ps,
        nipyapi.nifi.ProcessorConfigDTO(
            scheduling_period='{0}{1}'.format(metricSource.interval.value,
                                              interval_unit)))
    # Restart MS PG
    nipyapi.canvas.schedule_process_group(ms_pg.id, True)

def upgradeMetricTarget(metricTarget: MetricTarget):
    """
    Stops flow, updates variables and restarts flow
    for a given MetricTarget entity
    """
    mt_pg = nipyapi.canvas.get_process_group(metricTarget.id)
    # Stop MT PG
    nipyapi.canvas.schedule_process_group(mt_pg.id, False)
    # Get topic name from input ID
    input_id = metricTarget.hasInput.object.strip(
                    "urn:ngsi-ld:").replace(":", "-").lower()
    # Set variables for MT PG
    nipyapi.canvas.update_variable_registry(
        mt_pg, [("topic", input_id)])
    nipyapi.canvas.update_variable_registry(
        mt_pg, [("consumer_url", metricTarget.uri.value)])
    # Restart MT PG
    nipyapi.canvas.schedule_process_group(mt_pg.id, True)

def upgradeTelemetrySource(telemetrySource: TelemetrySource, ngsi: NGSILDClient):
    """
    Stops flow, updates variables and restarts flow
    for a given TelemetrySource entity
    """
    ts_pg = nipyapi.canvas.get_process_group(telemetrySource.id)
    # Stop TS PG
    nipyapi.canvas.schedule_process_group(ts_pg.id, False)
    # Get gNMI server address from hasEndpoint relationship
    device_entity = ngsi.retrieveEntityById(telemetrySource.collectsFrom.object)
    device = Device.parse_obj(device_entity)
    endpoint_entity = ngsi.retrieveEntityById(device.hasEndpoint.object)
    endpoint = Endpoint.parse_obj(endpoint_entity)
    # Get topic name from TelemetrySource entity ID
    entity_id = telemetrySource.id.strip("urn:ngsi-ld:").replace(":", "-").lower()
    # Get subscription mode (sample or on-change)
    subscription_mode = telemetrySource.subscriptionMode.value
    filename = '/gnmic-cfgs/cfg-kafka.json'
    subscription_name = ""
    with open(filename, 'r') as file:
        data = json.load(file)
        data['address'] = endpoint.uri.value.split("://")[1]
        data['outputs']['output']['topic'] = entity_id
        if subscription_mode == "on-change":
            # Get the subscription mode name
            subscription_name = subscription_mode
            # Get the names of the telemetry data paths to subscribe (path names of the YANG module data nodes)
            telemetry_data = []
            if type(telemetrySource.XPath.value) is str:
                telemetry_data.append(telemetrySource.XPath.value)
            elif type(telemetrySource.XPath.value) is list:
                telemetry_data = telemetrySource.XPath.value
            data['subscriptions']['on-change']['paths'] = telemetry_data
        elif subscription_mode['mode'] == "sample": #else: #elif subscription_mode == "sample":
            # Get the subscription mode name
            subscription_name = subscription_mode['mode']
            # Get the names of the telemetry data paths to subscribe (path names of the YANG module data nodes)
            telemetry_data = []
            if type(telemetrySource.XPath.value) is str:
                telemetry_data.append(telemetrySource.XPath.value)
            elif type(telemetrySource.XPath.value) is list:
                telemetry_data = telemetrySource.XPath.value
            data['subscriptions']['sample']['paths'] = telemetry_data
            # Get interval value
            interval = subscription_mode['interval'] #telemetrySource.interval.value
            # Get interval unit
            interval_unit = UnitCode[telemetrySource.subscriptionMode.unitCode].value #UnitCode[telemetrySource.interval.unitCode].value
            data['subscriptions']['sample']['sample-interval'] = interval+interval_unit

    os.remove(filename)
    with open(filename, 'w') as file:
        json.dump(data, file, indent=4)
    # Set variables for TS PG
    nipyapi.canvas.update_variable_registry(ts_pg, [("command", "gnmic")])
    # arguments = telemetrySource.arguments.value+" --name {0}".format(subscription_mode)
    arguments = "--config {0} subscribe --name {1}".format(filename, subscription_name)
    nipyapi.canvas.update_variable_registry(ts_pg, [("arguments", arguments)])
    # Restart TS PG
    nipyapi.canvas.schedule_process_group(ts_pg.id, True)

def upload_templates():
    """
    Upload MetricSource, MetricTarget and TelemetrySource templates
    to the root process group
    """
    # Get root PG
    root_pg = nipyapi.canvas.get_process_group("root")
    # Upload templates
    try:
        nipyapi.templates.upload_template(
            root_pg.id, "/app/config/templates/MetricSource.xml")
    except ValueError:
        logger.info("MetricSource already uploaded in NiFi")
    try:
        nipyapi.templates.upload_template(
            root_pg.id, "/app/config/templates/MetricTarget.xml")
    except ValueError:
        logger.info("MetricTarget already uploaded in NiFi")
    try:
        nipyapi.templates.upload_template(
            root_pg.id, "/app/config/templates/TelemetrySource.xml")
    except ValueError:
        logger.info("TelemetrySource already uploaded in NiFi")

def checkEndpointExistence(entity: Entity, ngsi: NGSILDClient) -> bool:
    """
    Checking if collection agent entity has an endpoint
    """
    endpoint_entities = ngsi.queryEntities(type="Endpoint")
    endpoint_exists = False
    if len(endpoint_entities) > 0:
        for endpoint_entity in endpoint_entities:
            if endpoint_entity['id'] == entity.hasEndpoint.object:
                endpoint_exists = True
                break
            else:
                endpoint_exists = False
    else:
        endpoint_exists = False

    return endpoint_exists

def checkEndpointExistence(endpoint_id: str, ngsi: NGSILDClient) -> bool:
    """
    Checking if collection agent entity has an endpoint
    """
    endpoint_entities = ngsi.queryEntities(type="Endpoint")
    endpoint_exists = False
    if len(endpoint_entities) > 0:
        for endpoint_entity in endpoint_entities:
            if endpoint_entity['id'] == endpoint_id:
                endpoint_exists = True
                break
            else:
                endpoint_exists = False
    else:
        endpoint_exists = False

    return endpoint_exists

def checkSourceExistence(source_id: str, ngsi: NGSILDClient) -> bool:
    """
    Checking if collection agent entity has a source (i.e., Prometheus, Device, ...)
    """
    source_entities = []
    if source_id.split(":")[2] == "Prometheus":
        source_entities = ngsi.queryEntities(type="Prometheus")
    elif source_id.split(":")[2] == "Device":
        source_entities = ngsi.queryEntities(type="Device")
    source_exists = False
    if len(source_entities) > 0:
        for source_entity in source_entities:
            if source_entity['id'] == source_id:
                source_exists = True
                break
            else:
                source_exists = False
    else:
        source_exists = False

    return source_exists

def checkInputExistence(entity: Entity, ngsi: NGSILDClient) -> bool:
    """
    Checking if dispatcher agent entity has an input
    """
    candidate_input_entities = []
    input_id = entity.hasInput.object
    if input_id.split(":")[2] == "MetricSource":
        candidate_input_entities = ngsi.queryEntities(type="MetricSource")
    elif input_id.split(":")[2] == "MetricProcessor":
        candidate_input_entities = ngsi.queryEntities(type="MetricProcessor")
    input_exists = False
    if len(candidate_input_entities) > 0:
        for candidate_input_entity in candidate_input_entities:
            if candidate_input_entity['id'] == input_id:
                input_exists = True
                break
            else:
                input_exists = False
    else:
        input_exists = False

    return input_exists

def checkOutputExistence(entity: Entity, ngsi: NGSILDClient) -> bool:
    """
    Checking if collection agent entity has an output
    """
    target_entities = ngsi.queryEntities(type="MetricTarget")
    processor_entities = ngsi.queryEntities(type="MetricProcessor")

    target_output_entities_id = []
    processor_output_entities_id = []
    output_entities_id = []

    target_output_exists = False
    processor_output_exists = False
    output_exists = False

    if len(target_entities) > 0:
        for target_entity in target_entities:
            if target_entity['hasInput']['object'] == entity.id:
                target_output_exists = True
                target_output_entities_id.append(target_entity['id'])
        if len(target_output_entities_id) > 0:
            output_entities_id.extend(target_output_entities_id)
        else:
            target_output_exists = False
    else:
        target_output_exists = False

    if len(processor_entities) > 0:
        for processor_entity in processor_entities:
            if processor_entity['hasInput']['object'] == entity.id:
                processor_output_exists = True
                processor_output_entities_id.append(processor_entity['id'])
        if len(processor_output_entities_id) > 0:
            output_entities_id.extend(processor_output_entities_id)
        else:
            processor_output_exists = False
    else:
        processor_output_exists = False

    if target_output_exists == True or processor_output_exists == True:
        logger.info("Delete '{0}' NiFi flow. The processor deletion failed.".format(entity.id))
        ngsi_ld_ops.stateToFailed(ngsi, entity.id, {"value": "ERROR! The '{0}' entity has an output: '{1}'.".format(entity.id, output_entities_id)})
        output_exists = True

    return output_exists
