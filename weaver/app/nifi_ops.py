from nipyapi.nifi.models.process_group_entity import ProcessGroupEntity
from semantic_tools.clients.ngsi_ld import NGSILDClient
from semantic_tools.models.metric import Endpoint, MetricSource, MetricTarget, TelemetrySource
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
    endpoint_entity = ngsi.retrieveEntityById(metricSource.hasEndpoint.object)
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

"""
def deployTelemetrySource(telemetrySource: TelemetrySource, ngsi: NGSILDClient) -> ProcessGroupEntity:
    # Get topic name from input ID
    entity_id = telemetrySource.id.strip("urn:ngsi-ld:").replace(":", "-").lower()
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
    nipyapi.canvas.update_variable_registry(ts_pg, [("arguments", telemetrySource.arguments.value)])

    # Deploy TS template
    ts_template = nipyapi.templates.get_template("TelemetrySource")
    ts_pg_flow = nipyapi.templates.deploy_template(ts_pg.id, ts_template.id)

    return ts_pg
"""

def deployTelemetrySource(telemetrySource: TelemetrySource, ngsi: NGSILDClient) -> ProcessGroupEntity:
    """
    Deploys a TelemetrySource NiFi template
    from a passed TelemetrySource NGSI-LD entity
    """
    # Get gNMI server address from hasEndpoint relationship
    endpoint_entity = ngsi.retrieveEntityById(telemetrySource.hasEndpoint.object)
    endpoint = Endpoint.parse_obj(endpoint_entity)
    # Get topic name from TelemetrySource entity ID
    entity_id = telemetrySource.id.strip("urn:ngsi-ld:").replace(":", "-").lower()
    # Get subscription mode (sample or on-change)
    subscription_mode = telemetrySource.subscriptionMode.value
    filename = '/gnmic-cfgs/cfg-kafka.json'
    with open(filename, 'r') as file:
        data = json.load(file)
        # outputs = data['outputs']
        # output = outputs['output']
        # topic = output['topic']
        data['address'] = endpoint.address.value
        data['outputs']['output']['topic'] = entity_id
        if subscription_mode == "on-change":
            # Get the names of the telemetry data paths to subscribe (path names of the YANG module data nodes)
            telemetry_data = []
            if type(telemetrySource.name.value) is str:
                telemetry_data.append(telemetrySource.name.value)
            elif type(telemetrySource.name.value) is list:
                telemetry_data = telemetrySource.name.value
            data['subscriptions']['on-change']['paths'] = telemetry_data
        elif subscription_mode['mode'] == "sample": #else: #elif subscription_mode == "sample":
            # Get the names of the telemetry data paths to subscribe (path names of the YANG module data nodes)
            telemetry_data = []
            if type(telemetrySource.name.value) is str:
                telemetry_data.append(telemetrySource.name.value)
            elif type(telemetrySource.name.value) is list:
                telemetry_data = telemetrySource.name.value
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
    arguments = "--config /gnmic-cfgs/cfg-kafka.json subscribe --name {0}".format(subscription_mode)
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

def processMetricSourceMode(metricSource: MetricSource,
                            ngsi: NGSILDClient):
    """
    Process MetricSource resources (i.e., NiFi flow)
    based on the value of the stageMode property
    """
    ngsi_ld_ops.appendModeResult(ngsi, metricSource.id)
    ms_pg = getMetricSourcePG(metricSource)
    if metricSource.stageMode.value == "START":
        if ms_pg:
            logger.info(
                "Upgrade '{0}' NiFi flow.".format(
                    metricSource.id)
            )
            upgradeMetricSource(metricSource, ngsi)
        else:
            logger.info(
                "Instantiate new '{0}' NiFi flow.".format(
                    metricSource.id)
            )
            instantiateMetricSource(metricSource, ngsi)
        ngsi_ld_ops.stageToSuccessful(ngsi, metricSource.id)
    if metricSource.stageMode.value == "STOP":
        if ms_pg:
            logger.info(
                "Stop '{0}' NiFi flow.".format(
                    metricSource.id)
            )
            stopMetricSource(metricSource)
        else:
            logger.info(
                "New '{0}' to STOP.".format(
                    metricSource.id)
            )
            deployMetricSource(metricSource, ngsi)
        ngsi_ld_ops.stageToSuccessful(ngsi, metricSource.id)
    if metricSource.stageMode.value == "TERMINATE":
        logger.info(
            "Terminate '{0}' NiFi flow.".format(
                metricSource.id)
        )
        deleteMetricSource(metricSource)
        ngsi_ld_ops.stageToSuccessful(ngsi, metricSource.id)
        logger.info("Delete '{0}' entity".format(metricSource.id))
        ngsi.deleteEntity(metricSource.id)


def processMetricTargetMode(metricTarget: MetricTarget,
                            ngsi: NGSILDClient):
    """
    Process MetricTarget resources (i.e., NiFi flow)
    based on the value of the stageMode property
    """
    ngsi_ld_ops.appendModeResult(ngsi, metricTarget.id)
    mt_pg = getMetricTargetPG(metricTarget)
    if metricTarget.stageMode.value == "START":
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
        ngsi_ld_ops.stageToSuccessful(ngsi, metricTarget.id)
    if metricTarget.stageMode.value == "STOP":
        if mt_pg:
            logger.info(
                "Stop '{0}' NiFi flow.".format(
                    metricTarget.id)
            )
            stopMetricTarget(metricTarget)
        else:
            logger.info(
                "New '{0}' to STOP.".format(
                    metricTarget.id)
            )
            deployMetricTarget(metricTarget)
        ngsi_ld_ops.stageToSuccessful(ngsi, metricTarget.id)
    if metricTarget.stageMode.value == "TERMINATE":
        logger.info(
            "Terminate '{0}' NiFi flow.".format(
                metricTarget.id)
        )
        deleteMetricTarget(metricTarget)
        ngsi_ld_ops.stageToSuccessful(ngsi, metricTarget.id)
        logger.info("Delete '{0}' entity".format(metricTarget.id))
        ngsi.deleteEntity(metricTarget.id)

def processTelemetrySourceMode(telemetrySource: TelemetrySource,
                            ngsi: NGSILDClient):
    """
    Process TelemetrySource resources (i.e., NiFi flow)
    based on the value of the stageMode property
    """
    ngsi_ld_ops.appendModeResult(ngsi, telemetrySource.id)
    ts_pg = getTelemetrySourcePG(telemetrySource)
    if telemetrySource.stageMode.value == "START":
        if ts_pg:
            logger.info(
                "Upgrade '{0}' NiFi flow.".format(
                    telemetrySource.id)
            )
            upgradeTelemetrySource(telemetrySource, ngsi)
        else:
            logger.info(
                "Instantiate new '{0}' NiFi flow.".format(
                    telemetrySource.id)
            )
            instantiateTelemetrySource(telemetrySource, ngsi)
        ngsi_ld_ops.stageToSuccessful(ngsi, telemetrySource.id)
    if telemetrySource.stageMode.value == "STOP":
        if ts_pg:
            logger.info(
                "Stop '{0}' NiFi flow.".format(
                    telemetrySource.id)
            )
            stopTelemetrySource(telemetrySource)
        else:
            logger.info(
                "New '{0}' to STOP.".format(
                    telemetrySource.id)
            )
            deployTelemetrySource(telemetrySource, ngsi)
        ngsi_ld_ops.stageToSuccessful(ngsi, telemetrySource.id)
    if telemetrySource.stageMode.value == "TERMINATE":
        logger.info(
            "Terminate '{0}' NiFi flow.".format(
                telemetrySource.id)
        )
        deleteTelemetrySource(telemetrySource)
        ngsi_ld_ops.stageToSuccessful(ngsi, telemetrySource.id)
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
    endpoint_entity = ngsi.retrieveEntityById(metricSource.hasEndpoint.object)
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
    endpoint_entity = ngsi.retrieveEntityById(telemetrySource.hasEndpoint.object)
    endpoint = Endpoint.parse_obj(endpoint_entity)
    # Get topic name from TelemetrySource entity ID
    entity_id = telemetrySource.id.strip("urn:ngsi-ld:").replace(":", "-").lower()
    # Get subscription mode (sample or on-change)
    subscription_mode = telemetrySource.subscriptionMode.value
    filename = '/gnmic-cfgs/cfg-kafka.json'
    with open(filename, 'r') as file:
        data = json.load(file)
        # outputs = data['outputs']
        # output = outputs['output']
        # topic = output['topic']
        data['address'] = endpoint.address.value
        data['outputs']['output']['topic'] = entity_id
        if subscription_mode == "on-change":
            # Get the names of the telemetry data paths to subscribe (path names of the YANG module data nodes)
            telemetry_data = []
            if type(telemetrySource.name.value) is str:
                telemetry_data.append(telemetrySource.name.value)
            elif type(telemetrySource.name.value) is list:
                telemetry_data = telemetrySource.name.value
            data['subscriptions']['on-change']['paths'] = telemetry_data
        elif subscription_mode['mode'] == "sample": #else: #elif subscription_mode == "sample":
            # Get the names of the telemetry data paths to subscribe (path names of the YANG module data nodes)
            telemetry_data = []
            if type(telemetrySource.name.value) is str:
                telemetry_data.append(telemetrySource.name.value)
            elif type(telemetrySource.name.value) is list:
                telemetry_data = telemetrySource.name.value
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
    arguments = "--config /gnmic-cfgs/cfg-kafka.json subscribe --name {0}".format(subscription_mode)
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
