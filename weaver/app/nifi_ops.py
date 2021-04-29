from nipyapi.nifi.models.process_group_entity import ProcessGroupEntity
from nipyapi.nifi.models.controller_service_entity import ControllerServiceEntity
from semantic_tools.clients.ngsi_ld import NGSILDClient
from semantic_tools.models.metric import Endpoint, Prometheus, MetricSource, MetricTarget
from semantic_tools.models.telemetry import Endpoint, Device, TelemetrySource
from semantic_tools.utils.units import UnitCode
from urllib3.exceptions import MaxRetryError

import logging
import nipyapi
import ngsi_ld_ops
import time
import sys
import json
import os

logger = logging.getLogger(__name__)


def check_nifi_status():
    """
    Infinite loop that checks every 30 seconds
    until NiFi REST API becomes available.
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
    Delete MetricSource flow.
    """
    ms_pg = nipyapi.canvas.get_process_group(metricSource.id)

    # Disable controller services
    controllers = nipyapi.canvas.list_all_controllers(ms_pg.id, False)
    registry_controller = None
    for controller in controllers:
        # Registry cannot be disabled as it has dependants
        if controller.component.name == "HortonworksSchemaRegistry":
            registry_controller = controller
            continue
        if controller.status.run_status == 'ENABLED':
            logger.debug("Disabling controller %s ..." % controller.component.name)
            nipyapi.canvas.schedule_controller(controller, False, True)
    # Disable registry controller
    logger.debug("Disabling controller %s ..." % registry_controller.component.name)
    nipyapi.canvas.schedule_controller(registry_controller, False, True)
    # Delete MS PG
    nipyapi.canvas.delete_process_group(ms_pg, True)
    logger.info("MetricSource '{0}' flow deleted in NiFi.".format(metricSource.id))


def deleteMetricTarget(metricTarget: MetricTarget):
    """
    Delete MetricTarget flow.
    """
    ms_pg = nipyapi.canvas.get_process_group(metricTarget.id)
    nipyapi.canvas.delete_process_group(ms_pg, True)
    logger.info("MetricSource '{0}' flow deleted in NiFi.".format(metricTarget.id))



def deleteTelemetrySource(telemetrySource: TelemetrySource):
    """
    Delete TelemetrySource flow.
    """
    ts_pg = stopTelemetrySource(telemetrySource)
    # Disable controller services
    controllers = nipyapi.canvas.list_all_controllers(ts_pg.id, False)
    registry_controller = None
    for controller in controllers:
        # Registry cannot be disabled as it has dependants
        if controller.component.name == "HortonworksSchemaRegistry":
            registry_controller = controller
            continue
        if controller.status.run_status == 'ENABLED':
            logger.debug("Disabling controller %s ..." % controller.component.name)
            nipyapi.canvas.schedule_controller(controller, False, True)
    # Disable registry controller
    logger.debug("Disabling controller %s ..." % registry_controller.component.name)
    nipyapi.canvas.schedule_controller(registry_controller, False, True)
    # Delete TS PG
    nipyapi.canvas.delete_process_group(ts_pg, True)
    logger.info("TelemetrySource '{0}' flow deleted in NiFi.".format(telemetrySource.id))


def deployMetricSource(metricSource: MetricSource,
                       ngsi: NGSILDClient) -> ProcessGroupEntity:
    """
    Deploys a MetricSource NiFi template
    from a passed MetricSource NGSI-LD entity.
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
    nipyapi.canvas.update_variable_registry(ms_pg, [("avro_schema", "openmetrics")])

     # Deploy MS template
    ms_template = nipyapi.templates.get_template("MetricSource")
    ms_pg_flow = nipyapi.templates.deploy_template(ms_pg.id,
                                                   ms_template.id,
                                                   -250, 200)

    # Enable controller services
    # Start with the registry controller
    logger.debug("Enabling controller HortonworksSchemaRegistry...")
    registry_controller = getControllerService(ms_pg, "HortonworksSchemaRegistry")
    nipyapi.canvas.schedule_controller(registry_controller, True)
    controllers = nipyapi.canvas.list_all_controllers(ms_pg.id, False)
    for controller in controllers:
        logger.debug("Enabling controller %s ..." % controller.component.name)
        if controller.status.run_status != 'ENABLED':
            nipyapi.canvas.schedule_controller(controller, True)

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
    logger.info("MetricSource '{0}' flow deployed in NiFi.".format(metricSource.id))
    return ms_pg


def deployMetricTarget(metricTarget: MetricTarget) -> ProcessGroupEntity:
    """
    Deploys a MetricTarget NiFi template
    from a passed MetricTarget NGSI-LD entity.
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
    logger.info("MetricTarget '{0}' flow deployed in NiFi.".format(metricTarget.id))
    return mt_pg


def deployTelemetrySource(telemetrySource: TelemetrySource, ngsi: NGSILDClient) -> ProcessGroupEntity:
    """
    Deploys a TelemetrySource NiFi template
    from a passed TelemetrySource NGSI-LD entity.
    """
    # Get gNMI server address from hasEndpoint relationship
    device_entity = ngsi.retrieveEntityById(telemetrySource.collectsFrom.object)
    device = Device.parse_obj(device_entity)
    endpoint_entity = ngsi.retrieveEntityById(device.hasEndpoint.object)
    endpoint = Endpoint.parse_obj(endpoint_entity)
    # Get topic name from TelemetrySource entity ID
    entity_id = telemetrySource.id.strip("urn:ngsi-ld:").replace(":", "-").lower()
    gnmic_topic = "gnmic-" + entity_id
    # Get subscription mode (sample or on-change)
    subscription_mode = telemetrySource.subscriptionMode.value
    filename = '/gnmic-cfgs/cfg-subscriptions.json'
    subscription_name = ""
    with open(filename, 'r') as file:
        data = json.load(file)
        data['address'] = endpoint.uri.value.split("://")[1]
        data['outputs']['output']['topic'] = gnmic_topic
        data['outputs']['output']['format'] = "protojson"
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
    # Y multiply ID last integer by 200, X fixed to 750 for TS PGs
    ts_pg = nipyapi.canvas.create_process_group(
                    root_pg,
                    telemetrySource.id,
                    (750, 200*source_id_number)
    )
    # Set variable for TS PG
    # Avro schema hardcoded to openconfig-interfaces
    nipyapi.canvas.update_variable_registry(ts_pg, [("avro_schema", "openconfig-interfaces")])
    nipyapi.canvas.update_variable_registry(ts_pg, [("topic", entity_id)])
    nipyapi.canvas.update_variable_registry(ts_pg, [("command", "gnmic")])
    # arguments = telemetrySource.arguments.value+" --name {0}".format(subscription_mode)
    arguments = "--config {0} subscribe --name {1}".format(filename, subscription_name)
    nipyapi.canvas.update_variable_registry(ts_pg, [("arguments", arguments)])
    # Deploy TS template
    ts_template = nipyapi.templates.get_template("TelemetrySource")
    ts_pg_flow = nipyapi.templates.deploy_template(ts_pg.id, ts_template.id)
    # Enable controller services
    # Start with the registry controller
    logger.debug("Enabling controller HortonworksSchemaRegistry...")
    registry_controller = getControllerService(ts_pg, "HortonworksSchemaRegistry")
    nipyapi.canvas.schedule_controller(registry_controller, True)
    controllers = nipyapi.canvas.list_all_controllers(ts_pg.id, False)
    for controller in controllers:
        logger.debug("Enabling controller %s ..." % controller.component.name)
        if controller.status.run_status != 'ENABLED':
            nipyapi.canvas.schedule_controller(controller, True)
    logger.info("TelemetrySource '{0}' flow deployed in NiFi.".format(telemetrySource.id))
    return ts_pg


def getControllerService(pg: ProcessGroupEntity, name: str) -> ControllerServiceEntity:
    """
    Get Controller Service by name within a given ProcessGroup.
    """
    controllers = nipyapi.canvas.list_all_controllers(pg.id, False)
    for controller in controllers:
        if controller.component.name == name:
            return controller


def getMetricSourcePG(metricSource: MetricSource) -> ProcessGroupEntity:
    """
    Get NiFi flow (Process Group) from MetricSource.
    """
    return nipyapi.canvas.get_process_group(metricSource.id)


def getMetricTargetPG(metricTarget: MetricTarget) -> ProcessGroupEntity:
    """
    Get NiFi flow (Process Group) from MetricTarget.
    """
    return nipyapi.canvas.get_process_group(metricTarget.id)


def getTelemetrySourcePG(telemetrySource: TelemetrySource) -> ProcessGroupEntity:
    """
    Get NiFi flow (Process Group) from TelemetrySource.
    """
    return nipyapi.canvas.get_process_group(telemetrySource.id)


def getQueryLabels(metricSource: MetricSource) -> str:
    """
    Print Prometheus labels to make them consumable
    by Prometheus REST API.
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
    a MetricSource entity.
    """
    ms_pg = deployMetricSource(metricSource, ngsi)
    # Schedule MS PG
    nipyapi.canvas.schedule_process_group(ms_pg.id, True)
    logger.info(
        "MetricSource '{0}' flow scheduled in NiFi.".format(
            metricSource.id))


def instantiateMetricTarget(metricTarget: MetricTarget):
    """
    Deploys and starts MetricTarget template given
    a MetricTarget entity.
    """
    mt_pg = deployMetricTarget(metricTarget)
    # Schedule MT PG
    nipyapi.canvas.schedule_process_group(mt_pg.id, True)
    logger.info(
        "MetricTarget '{0}' flow scheduled in NiFi.".format(
            metricTarget.id))


def instantiateTelemetrySource(telemetrySource: TelemetrySource, ngsi: NGSILDClient):
    """
    Deploys and starts TelemetrySource template given
    a TelemetrySource entity.
    """
    ts_pg = deployTelemetrySource(telemetrySource, ngsi)
    # Schedule TS PG
    nipyapi.canvas.schedule_process_group(ts_pg.id, True)
    logger.info(
        "TelemetrySource '{0}' flow scheduled in NiFi.".format(
            telemetrySource.id))


def stopMetricSource(metricSource: MetricSource) -> ProcessGroupEntity:
    """
    Stop NiFi flow (Process Group) from MetricSource.
    """
    ms_pg = nipyapi.canvas.get_process_group(metricSource.id)
    nipyapi.canvas.schedule_process_group(ms_pg.id, False)
    return ms_pg


def stopMetricTarget(metricTarget: MetricTarget) -> ProcessGroupEntity:
    """
    Stop NiFi flow (Process Group) from MetricTarget.
    """
    mt_pg = nipyapi.canvas.get_process_group(metricTarget.id)
    nipyapi.canvas.schedule_process_group(mt_pg.id, False)
    return mt_pg


def stopTelemetrySource(telemetrySource: TelemetrySource) -> ProcessGroupEntity:
    """
    Stop NiFi flow (Process Group) from TelemetrySource.
    """
    ts_pg = nipyapi.canvas.get_process_group(telemetrySource.id)
    nipyapi.canvas.schedule_process_group(ts_pg.id, False)
    return ts_pg


def upgradeMetricSource(metricSource: MetricSource, ngsi: NGSILDClient):
    """
    Stops flow, updates variables and re-starts flow
    for a given MetricSource entity.
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
    nipyapi.canvas.update_variable_registry(ms_pg, [("avro_schema", "openmetrics")])

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
    logger.info("MetricSource '{0}' flow upgraded in NiFi.".format(metricSource.id))
    # Enable controller services
    controllers = nipyapi.canvas.list_all_controllers(ms_pg.id, False)
    for controller in controllers:
        if controller.status.run_status != 'ENABLED':
            nipyapi.canvas.schedule_controller(controller, True, True)


def upgradeMetricTarget(metricTarget: MetricTarget):
    """
    Stops flow, updates variables and restarts flow
    for a given MetricTarget entity.
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
    logger.info("MetricTarget '{0}' flow upgraded in NiFi.".format(metricTarget.id))


def upgradeTelemetrySource(telemetrySource: TelemetrySource, ngsi: NGSILDClient):
    """
    Stops flow, updates variables and restarts flow
    for a given TelemetrySource entity.
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
    gnmic_topic = "gnmic-" + entity_id
    # Get subscription mode (sample or on-change)
    subscription_mode = telemetrySource.subscriptionMode.value
    filename = '/gnmic-cfgs/cfg-subscriptions.json'
    subscription_name = ""
    with open(filename, 'r') as file:
        data = json.load(file)
        data['address'] = endpoint.uri.value.split("://")[1]
        data['outputs']['output']['topic'] = gnmic_topic
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
    # Avro schema hardcoded to openconfig-interfaces
    nipyapi.canvas.update_variable_registry(ts_pg, [("avro_schema", "openconfig-interfaces")])
    nipyapi.canvas.update_variable_registry(ts_pg, [("topic", entity_id)])
    nipyapi.canvas.update_variable_registry(ts_pg, [("command", "gnmic")])
    # arguments = telemetrySource.arguments.value+" --name {0}".format(subscription_mode)
    arguments = "--config {0} subscribe --name {1}".format(filename, subscription_name)
    nipyapi.canvas.update_variable_registry(ts_pg, [("arguments", arguments)])
    # Restart TS PG
    nipyapi.canvas.schedule_process_group(ts_pg.id, True)
    # Enable controller services
    controllers = nipyapi.canvas.list_all_controllers(ts_pg.id, False)
    for controller in controllers:
        if controller.status.run_status != 'ENABLED':
            nipyapi.canvas.schedule_controller(controller, True, True)
    logger.info("TelemetrySource '{0}' flow upgraded in NiFi.".format(telemetrySource.id))


def upload_templates():
    """
    Upload MetricSource, MetricTarget and TelemetrySource templates
    to the root process group.
    """
    # Get root PG
    root_pg = nipyapi.canvas.get_process_group("root")
    # Upload templates
    try:
        nipyapi.templates.upload_template(
            root_pg.id, "/app/config/templates/MetricSource.xml")
    except ValueError:
        logger.info("MetricSource already uploaded in NiFi.")
    try:
        nipyapi.templates.upload_template(
            root_pg.id, "/app/config/templates/MetricTarget.xml")
    except ValueError:
        logger.info("MetricTarget already uploaded in NiFi.")
    try:
        nipyapi.templates.upload_template(
            root_pg.id, "/app/config/templates/TelemetrySource.xml")
    except ValueError:
        logger.info("TelemetrySource already uploaded in NiFi.")
