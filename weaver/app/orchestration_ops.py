from semantic_tools.clients.ngsi_ld import NGSILDClient
from semantic_tools.clients.flink_api_rest import FlinkClient
from semantic_tools.models.metric import Endpoint, Prometheus, MetricSource, MetricTarget, StreamApplication, MetricProcessor
from semantic_tools.models.telemetry import Endpoint, Device, TelemetrySource, Module
from semantic_tools.models.ngsi_ld.entity import Entity
from semantic_tools.utils.units import UnitCode

import logging
import ngsi_ld_ops
import nifi_ops
import flink_ops
import sys
import json
import os
import requests
import subprocess

logger = logging.getLogger(__name__)


def processMetricSourceState(metricSource: MetricSource, ngsi: NGSILDClient):
    """
    Process MetricSource entity resources (i.e., NiFi flows) based on the value of the action property.
    """
    ngsi_ld_ops.appendState(ngsi, metricSource.id, "Building the collection agent...")
    ms_pg = nifi_ops.getMetricSourcePG(metricSource)
    if metricSource.action.value == "START":
        prometheus_id = metricSource.collectsFrom.object
        prometheus_exists = checkDataSourceExistence(prometheus_id, ngsi)
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
                    nifi_ops.upgradeMetricSource(metricSource, ngsi)
                    ngsi_ld_ops.stateToRunning(ngsi, metricSource.id, {"value": "SUCCESS! Collection agent successfully upgraded."})
                else:
                    logger.info(
                        "Instantiate new '{0}' NiFi flow.".format(metricSource.id)
                    )
                    nifi_ops.instantiateMetricSource(metricSource, ngsi)
                    ngsi_ld_ops.stateToRunning(ngsi, metricSource.id, {"value": "SUCCESS! Collection agent successfully started."})
            else:
                logger.info("Instantiate '{0}' NiFi flow. A processing error was found. The specified endpoint does not exist.".format(metricSource.id))
                ngsi_ld_ops.stateToFailed(ngsi, metricSource.id, {"value": "ERROR! The '{0}' Endpoint entity does not exist.".format(endpoint_id)})
                logger.info("Delete the '{0}' MetricSource collection agent entity.".format(metricSource.id))
                ngsi.deleteEntity(metricSource.id)
        else:
            logger.info("Instantiate '{0}' NiFi flow. A processing error was found. The specified Prometheus data source does not exist.".format(metricSource.id))
            ngsi_ld_ops.stateToFailed(ngsi, metricSource.id, {"value": "ERROR! The '{0}' Prometheus data source entity does not exist.".format(prometheus_id)})
            logger.info("Delete the '{0}' MetricSource collection agent entity.".format(metricSource.id))
            ngsi.deleteEntity(metricSource.id)
    elif metricSource.action.value == "STOP":
        prometheus_id = metricSource.collectsFrom.object
        prometheus_exists = checkDataSourceExistence(prometheus_id, ngsi)
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
                    nifi_ops.stopMetricSource(metricSource)
                    logger.info("MetricSource '{0}' flow stopped in NiFi.".format(metricSource.id))
                    ngsi_ld_ops.stateToStopped(ngsi, metricSource.id, {"value": "SUCCESS! Collection agent successfully stopped."})
                else:
                    logger.info(
                        "New '{0}' NiFi flow to stop.".format(metricSource.id)
                    )
                    nifi_ops.deployMetricSource(metricSource, ngsi)
                    ngsi_ld_ops.stateToStopped(ngsi, metricSource.id, {"value": "SUCCESS! Collection agent successfully deployed."})
            else:
                logger.info("Stop '{0}' NiFi flow. A processing error was found. The specified endpoint does not exist.".format(metricSource.id))
                ngsi_ld_ops.stateToFailed(ngsi, metricSource.id, {"value": "ERROR! The '{0}' Endpoint entity does not exist.".format(endpoint_id)})
                logger.info("Delete '{0}' agent entity.".format(metricSource.id))
                ngsi.deleteEntity(metricSource.id)
        else:
            logger.info("Stop '{0}' NiFi flow. A processing error was found. The specified Prometheus data source does not exist.".format(metricSource.id))
            ngsi_ld_ops.stateToFailed(ngsi, metricSource.id, {"value": "ERROR! The '{0}' Prometheus data source entity does not exist.".format(prometheus_id)})
            logger.info("Delete the '{0}' MetricSource collection agent entity.".format(metricSource.id))
            ngsi.deleteEntity(metricSource.id)
    elif metricSource.action.value == "END":
        output_exists = checkOutputExistence(metricSource, ngsi)
        if output_exists == False:
            logger.info(
                "Delete '{0}' NiFi flow.".format(metricSource.id)
            )
            nifi_ops.deleteMetricSource(metricSource)
            ngsi_ld_ops.stateToCleaned(ngsi, metricSource.id, {"value": "SUCCESS! Collection agent successfully deleted."})
            logger.info("Delete the '{0}' MetricSource collection agent entity.".format(metricSource.id))
            ngsi.deleteEntity(metricSource.id)
        else:
            logger.info("Delete '{0}' NiFi flow. A processing error was found. The NiFi flow is being used by output agent entities.".format(metricSource.id))


def processMetricTargetState(metricTarget: MetricTarget, ngsi: NGSILDClient):
    """
    Process MetricTarget entity resources (i.e., NiFi flows) based on the value of the action property.
    """
    ngsi_ld_ops.appendState(ngsi, metricTarget.id, "Building the dispatch agent...")
    mt_pg = nifi_ops.getMetricTargetPG(metricTarget)
    if metricTarget.action.value == "START":
        input_exists = checkInputExistence(metricTarget, ngsi)
        if input_exists == True:
            if mt_pg:
                logger.info(
                    "Upgrade '{0}' NiFi flow.".format(
                        metricTarget.id)
                )
                nifi_ops.upgradeMetricTarget(metricTarget)
                ngsi_ld_ops.stateToRunning(ngsi, metricTarget.id, {"value": "SUCCESS! Dispatch agent successfully upgraded."})
            else:
                logger.info(
                    "Instantiate new '{0}' NiFi flow.".format(
                        metricTarget.id)
                )
                nifi_ops.instantiateMetricTarget(metricTarget)
                ngsi_ld_ops.stateToRunning(ngsi, metricTarget.id, {"value": "SUCCESS! Dispatch agent successfully started."})
        else:
            logger.info("Instantiate '{0}' NiFi flow. A processing error was found. The specified input agent does not exist.".format(metricTarget.id))
            ngsi_ld_ops.stateToFailed(ngsi, metricTarget.id, {"value": "ERROR! The '{0}' input agent entity does not exist.".format(metricTarget.hasInput.object)})
            logger.info("Delete the '{0}' MetricTarget dispatch agent entity.".format(metricTarget.id))
            ngsi.deleteEntity(metricTarget.id)
    elif metricTarget.action.value == "STOP":
        input_exists = checkInputExistence(metricTarget, ngsi)
        if input_exists == True:
            if mt_pg:
                logger.info(
                    "Stop '{0}' NiFi flow.".format(
                        metricTarget.id)
                )
                nifi_ops.stopMetricTarget(metricTarget)
                logger.info("MetricTarget '{0}' flow stopped in NiFi.".format(metricTarget.id))
                ngsi_ld_ops.stateToStopped(ngsi, metricTarget.id, {"value": "SUCCESS! Dispatch agent successfully stopped."})
            else:
                logger.info(
                    "New '{0}' NiFi flow to stop.".format(
                        metricTarget.id)
                )
                nifi_ops.deployMetricTarget(metricTarget)
                ngsi_ld_ops.stateToStopped(ngsi, metricTarget.id, {"value": "SUCCESS! Dispatch agent successfully deployed."})
        else:
            logger.info("Stop '{0}' NiFi flow. A processing error was found. The specified input agent does not exist.".format(metricTarget.id))
            ngsi_ld_ops.stateToFailed(ngsi, metricTarget.id, {"value": "ERROR! The '{0}' input agent entity does not exist.".format(metricTarget.hasInput.object)})
            logger.info("Delete the '{0}' MetricTarget dispatch agent entity.".format(metricTarget.id))
            ngsi.deleteEntity(metricTarget.id)
    elif metricTarget.action.value == "END":
        logger.info(
            "Delete '{0}' NiFi flow.".format(metricTarget.id)
        )
        nifi_ops.deleteMetricTarget(metricTarget)
        ngsi_ld_ops.stateToCleaned(ngsi, metricTarget.id, {"value": "SUCCESS! Dispatch agent successfully deleted."})
        logger.info("Delete the '{0}' MetricTarget dispatch agent entity.".format(metricTarget.id))
        ngsi.deleteEntity(metricTarget.id)


def processTelemetrySourceState(telemetrySource: TelemetrySource, ngsi: NGSILDClient):
    """
    Process TelemetrySource entity resources (i.e., NiFi flows) based on the value of the action property.
    """
    ngsi_ld_ops.appendState(ngsi, telemetrySource.id, "Building the collection agent...")
    ts_pg = nifi_ops.getTelemetrySourcePG(telemetrySource)
    if telemetrySource.action.value == "START":
        device_id = telemetrySource.collectsFrom.object
        device_exists = checkDataSourceExistence(device_id, ngsi)
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
                    nifi_ops.upgradeTelemetrySource(telemetrySource, ngsi)
                    ngsi_ld_ops.stateToRunning(ngsi, telemetrySource.id, {"value": "SUCCESS! Collection agent successfully upgraded."})
                else:
                    logger.info(
                        "Instantiate new '{0}' NiFi flow.".format(telemetrySource.id)
                    )
                    nifi_ops.instantiateTelemetrySource(telemetrySource, ngsi)
                    ngsi_ld_ops.stateToRunning(ngsi, telemetrySource.id, {"value": "SUCCESS! Collection agent successfully started."})
            else:
                logger.info("Instantiate '{0}' NiFi flow. A processing error was found. The specified endpoint does not exist.".format(telemetrySource.id))
                ngsi_ld_ops.stateToFailed(ngsi, telemetrySource.id, {"value": "ERROR! The '{0}' Endpoint entity does not exist.".format(endpoint_id)})
                logger.info("Delete the '{0}' TelemetrySource collection agent entity".format(telemetrySource.id))
                ngsi.deleteEntity(telemetrySource.id)
        else:
            logger.info("Instantiate '{0}' NiFi flow. A processing error was found. The specified device data source does not exist.".format(telemetrySource.id))
            ngsi_ld_ops.stateToFailed(ngsi, telemetrySource.id, {"value": "ERROR! The '{0}' Device data source entity does not exist.".format(device_id)})
            logger.info("Delete the '{0}' Telemetry collection agent entity.".format(telemetrySource.id))
            ngsi.deleteEntity(telemetrySource.id)
    elif telemetrySource.action.value == "STOP":
        device_id = telemetrySource.collectsFrom.object
        device_exists = checkDataSourceExistence(device_id, ngsi)
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
                    nifi_ops.stopTelemetrySource(telemetrySource)
                    logger.info("TelemetrySource '{0}' flow stopped in NiFi.".format(telemetrySource.id))
                    ngsi_ld_ops.stateToStopped(ngsi, telemetrySource.id, {"value": "SUCCESS! Collection agent successfully stopped."})
                else:
                    logger.info(
                        "New '{0}' NiFi flow to stop.".format(telemetrySource.id)
                    )
                    nifi_ops.deployTelemetrySource(telemetrySource, ngsi)
                    ngsi_ld_ops.stateToStopped(ngsi, telemetrySource.id, {"value": "SUCCESS! Collection agent successfully deployed."})
            else:
                logger.info("Stop '{0}' NiFi flow. A processing error was found. The specified endpoint does not exist.".format(telemetrySource.id))
                ngsi_ld_ops.stateToFailed(ngsi, telemetrySource.id, {"value": "ERROR! The '{0}' Endpoint entity does not exist.".format(endpoint_id)})
                logger.info("Delete the '{0}' TelemetrySource collection agent entity".format(telemetrySource.id))
                ngsi.deleteEntity(telemetrySource.id)
        else:
            logger.info("Stop '{0}' NiFi flow. A processing error was found. The specified device data source does not exist.".format(telemetrySource.id))
            ngsi_ld_ops.stateToFailed(ngsi, telemetrySource.id, {"value": "ERROR! The '{0}' Device data source entity does not exist.".format(device_id)})
            logger.info("Delete the '{0}' TelemetrySource collection agent entity.".format(telemetrySource.id))
            ngsi.deleteEntity(telemetrySource.id)
    elif telemetrySource.action.value == "END":
        output_exists = checkOutputExistence(telemetrySource, ngsi)
        if output_exists == False:
            logger.info(
                "Delete '{0}' NiFi flow.".format(telemetrySource.id)
            )
            nifi_ops.deleteTelemetrySource(telemetrySource)
            ngsi_ld_ops.stateToCleaned(ngsi, telemetrySource.id, {"value": "SUCCESS! Collection agent successfully deleted."})
            logger.info("Delete the '{0}' TelemetrySource collection agent entity.".format(telemetrySource.id))
            ngsi.deleteEntity(telemetrySource.id)
        else:
            logger.info("Delete '{0}' NiFi flow. A processing error was found. The NiFi flow is being used by output agent entities.".format(telemetrySource.id))


def processStreamApplicationState(streamApplication: StreamApplication, ngsi: NGSILDClient, flink: FlinkClient):
    """
    Process StreamApplication entity resources (i.e., stream processing application JARs) based on the value of the action property.
    """
    ngsi_ld_ops.appendState(ngsi, streamApplication.id, "Building the stream processing application...")
    jarId = ""
    jar_exists = False
    if streamApplication.fileId:
        jarId = streamApplication.fileId.value
        jar_exists = True
    if streamApplication.action.value == "START":
        if jar_exists == False:
            logger.info("Upload new '{0}' stream processing application JAR.".format(streamApplication.id))
            uploaded = flink_ops.uploadStreamApp(streamApplication, ngsi, flink)
            if uploaded == True:
                ngsi_ld_ops.stateToUploaded(ngsi, streamApplication.id, {"value": "SUCCESS! Stream application JAR successfully uploaded."})
            else:
                ngsi_ld_ops.stateToFailed(ngsi, streamApplication.id, {"value": "ERROR! Stream application JAR was not found in {0}.".format(streamApplication.uri.value)})
                logger.info("Delete the '{0}' StreamApplication entity.".format(streamApplication.id))
                ngsi.deleteEntity(streamApplication.id)
        else:
            # Delete current JAR and create another one with new configuration
            logger.info("Delete '{0}' stream processing application JAR.".format(streamApplication.id))
            flink.deleteJar(jarId)
            logger.info("StreamApplication '{0}' with '{1}' JAR deleted from Flink engine.".format(streamApplication.id, streamApplication.fileName.value))
            logger.info("Upgrade the previous '{0}' stream processing application JAR.".format(streamApplication.id))
            uploaded = flink_ops.uploadStreamApp(streamApplication, ngsi, flink)
            if uploaded == True:
                ngsi_ld_ops.stateToUploaded(ngsi, streamApplication.id, {"value": "SUCCESS! Stream application JAR successfully upgraded."})
            else:
                ngsi_ld_ops.stateToFailed(ngsi, streamApplication.id, {"value": "ERROR! Stream application JAR was not found in {0}.".format(streamApplication.uri.value)})
                logger.info("Delete the '{0}' StreamApplication entity.".format(streamApplication.id))
                ngsi.deleteEntity(streamApplication.id)
    elif streamApplication.action.value == "END":
        logger.info("Delete '{0}' stream processing application JAR.".format(streamApplication.id))
        flink.deleteJar(jarId)
        logger.info("StreamApplication '{0}' with '{1}' JAR deleted from Flink engine.".format(streamApplication.id, streamApplication.fileName.value))
        ngsi_ld_ops.stateToCleaned(ngsi, streamApplication.id, {"value": "SUCCESS! Stream application JAR successfully deleted."})
        logger.info("Delete the '{0}' StreamApplication entity.".format(streamApplication.id))
        ngsi.deleteEntity(streamApplication.id)


def processMetricProcessorState(metricProcessor: MetricProcessor, ngsi: NGSILDClient, flink: FlinkClient):
    """
    Process MetricProcess entity resources (i.e., stream processing application Jobs) based on the value of the action property.
    """
    ngsi_ld_ops.appendState(ngsi, metricProcessor.id, "Building the aggregation agent...")
    job = ""
    job_exists = False
    if metricProcessor.jobId:
        job = flink.getFlinkJob(metricProcessor.jobId.value)
        job_exists = True
    if metricProcessor.action.value == "START":
        streamApplication_exists = checkStreamApplicationExistence(metricProcessor, ngsi)
        input_exists = checkInputExistence(metricProcessor, ngsi)
        if streamApplication_exists == True and input_exists == True:
            if job_exists == False or job['state'] == 'CANCELED':
                logger.info("Submit new '{0}' stream processing application Job.".format(metricProcessor.id))
                flink_ops.submitStreamJob(metricProcessor, ngsi, flink)
            elif job_exists and job['state'] == 'RUNNING':
                # Cancel the current Job and create another one with new configuration
                logger.info("Cancel '{0}' stream processing application Job.".format(metricProcessor.id))
                flink.deleteJob(job['jid'])
                logger.info("MetricProcessor '{0}' with '{1}' Job cancelled in Flink engine.".format(metricProcessor.id, metricProcessor.name.value))
                logger.info("Upgrade the previous '{0}' stream processing application Job.".format(metricProcessor.id))
                flink_ops.submitStreamJob(metricProcessor, ngsi, flink)
            ngsi_ld_ops.stateToRunning(ngsi, metricProcessor.id, {"value": "SUCCESS! Aggregation agent successfully upgraded."})
        else:
            if streamApplication_exists == False:
                logger.info("Submit new '{0}' stream processing application Job. A processing error was found. The specified stream application does not exist.".format(metricProcessor.id))
                ngsi_ld_ops.stateToFailed(ngsi, metricProcessor.id, {"value": "ERROR! The '{0}' StreamApplication entity does not exist.".format(metricProcessor.hasApplication.object)})
            if input_exists == False:
                logger.info("Submit new '{0}' stream processing application Job. A processing error was found. The specified input does not exist.".format(metricProcessor.id))
                ngsi_ld_ops.stateToFailed(ngsi, metricProcessor.id, {"value": "ERROR! The '{0}' input entity does not exist.".format(metricProcessor.hasInput.object)})
            logger.info("Delete the '{0}' MetricProcessor aggregation agent entity.".format(metricProcessor.id))
            ngsi.deleteEntity(metricProcessor.id)
    elif metricProcessor.action.value == "STOP":
        if job_exists == False:
            logger.info(
                "Define new '{0}' stream processing application Job.".format(metricProcessor.id)
            )
            ngsi_ld_ops.stateToStopped(ngsi, metricProcessor.id, {"value": "SUCCESS! Aggregation agent successfully defined."})
        elif job['state'] == 'CANCELED':
            logger.info(
                "Stop '{0}' stream processing application Job. The Job is already stopped.".format(metricProcessor.id)
            )
            ngsi_ld_ops.stateToFailed(ngsi, metricProcessor.id, {"value": "ERROR! The '{0}' stream processing Job is already stopped.".format(metricProcessor.name.value)})
        elif job_exists and job['state'] == 'RUNNING':
            logger.info(
                "Stop '{0}' stream processing application Job.".format(metricProcessor.id)
            )
            flink.deleteJob(job['jid'])
            logger.info(
                "MetricProcessor '{0}' with '{1}' Job stopped in Flink engine.".format(metricProcessor.id, metricProcessor.name.value)
            )
            ngsi_ld_ops.stateToStopped(ngsi, metricProcessor.id, {"value": "SUCCESS! Aggregation agent successfully stopped."})
    elif metricProcessor.action.value == "END":
        output_exists = checkOutputExistence(metricProcessor, ngsi)
        if output_exists == False:
            if job['state'] == 'RUNNING':
                logger.info("Delete '{0}' stream processing application Job.".format(metricProcessor.id))
                flink.deleteJob(job['jid'])
                logger.info("MetricProcessor '{0}' with '{1}' Job deleted from Flink engine.".format(metricProcessor.id, metricProcessor.name.value))
            else:
                logger.info("Delete '{0}' stream processing application Job. The Job is not running.".format(metricProcessor.id))
            ngsi_ld_ops.stateToCleaned(ngsi, metricProcessor.id, {"value": "SUCCESS! Aggregation agent successfully deleted."})
            logger.info("Delete the '{0}' MetricProcessor aggregation agent entity.".format(metricProcessor.id))
            ngsi.deleteEntity(metricProcessor.id)
        else:
            logger.info("Delete '{0}' stream processing application Job. A processing error was found. The stream processing Job is being used by output agent entities.".format(metricProcessor.id))


def processPrometheusState(prometheus: Prometheus, ngsi: NGSILDClient):
    """
    Process Prometheus-based data source context information entities for metric collection.
    """
    ngsi_ld_ops.appendState(ngsi, prometheus.id, "Building the Prometheus data source context information entity...")
    endpoint_id = prometheus.hasEndpoint.object
    endpoint_exists = checkEndpointExistence(endpoint_id, ngsi)
    if prometheus.action.value == "START":
        if endpoint_exists == True:
            logger.info(
                "Create a new '{0}' Prometheus data source context information entity.".format(prometheus.id)
            )
            endpoint_entity = ngsi.retrieveEntityById(endpoint_id)
            endpoint = Endpoint.parse_obj(endpoint_entity)
            try:
                response = requests.get("{0}?query=prometheus_build_info".format(endpoint.uri.value))
                response.raise_for_status()
                version = response.json()['data']['result'][0]['metric']['version']
                version_dict = {
                    "version": {
                        "type": "Property",
                        "value": version
                    }
                }
                ngsi.appendEntityAttrs(prometheus.id, version_dict)
                ngsi_ld_ops.stateToRunning(ngsi, prometheus.id, {"value": "SUCCESS! Prometheus data source context information entity successfully started."})
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
                "Create a new '{0}' Prometheus data source context source entity. The action failed. The specified endpoint does not exist.".format(prometheus.id)
            )
            ngsi_ld_ops.stateToFailed(ngsi, prometheus.id, {"value": "ERROR! The '{0}' Endpoint entity does not exist.".format(endpoint_id)})
            logger.info("Delete the '{0}' Prometheus data source entity.".format(prometheus.id))
            ngsi.deleteEntity(prometheus.id)
    elif prometheus.action.value == "END":
        logger.info("Delete the '{0}' Prometheus data source entity. ".format(prometheus.id))
        ngsi_ld_ops.stateToCleaned(ngsi, prometheus.id, {"value": "SUCCESS! Prometheus data source context information entity successfully deleted."})
        ngsi.deleteEntity(prometheus.id)


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

            capabilities = subprocess.Popen(['gnmic', '--config', filename, 'capabilities', '--format', 'protojson'], stdout=subprocess.PIPE, stderr=subprocess.STDOUT)
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
                ngsi_ld_ops.stateToRunning(ngsi, device.id, {"value": "SUCCESS! Device data source context information entity started successfully."})
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
        ngsi_ld_ops.stateToCleaned(ngsi, device.id, {"value": "SUCCESS! Device data source context information entity deleted successfully."})
        ngsi.deleteEntity(device.id)


def processEndpointState(endpoint: Endpoint, ngsi: NGSILDClient):
    """
    Process Endpoint entities of data sources for data collection.
    """
    ngsi_ld_ops.appendState(ngsi, endpoint.id, "Building the Endpoint entity...")
    if endpoint.action.value == "START":
        logger.info("Create a new '{0}' Endpoint entity.".format(endpoint.id))
        ngsi_ld_ops.stateToRunning(ngsi, endpoint.id, {"value": "SUCCESS! Endpoint entity started successfully."})
    elif endpoint.action.value == "END":
        logger.info("Delete the '{0}' Endpoint entity. ".format(endpoint.id))
        ngsi_ld_ops.stateToCleaned(ngsi, endpoint.id, {"value": "SUCCESS! Endpoint entity deleted successfully.".format(endpoint.id)})
        ngsi.deleteEntity(endpoint.id)


def checkInputExistence(entity: Entity, ngsi: NGSILDClient) -> bool:
    """
    Checking if different entities in the data pipeline have an input relationship with an existing entity. This checking between entities is used for two different cases:
    - 1. If dispatch agent has an existing input collection or aggregation agent.
    - 2. If aggregation agent has an existing input collection or aggregation agent.

    The reason for this check is to verify that the different stages of data processing are established between correct and existing entities of the data pipeline.
    """
    input_id = entity.hasInput.object
    candidate_input_entities = ngsi.queryEntities(type=input_id.split(":")[2])
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
    Checking if different agent entities in the data pipeline have an output relationship with another existing agent entity. This checking between agent entities is used for two different cases:
    - 1. If collection agent has an existing output aggregation or dispatch agent.
    - 2. If aggregation agent has an existing output aggregation or dispatch agent.

    The reason for this check is to determine that the activity of a data processing stage depends on the previous data processing stage, establishing dependency relationships between agent
    entities of the data pipeline.
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
        ngsi_ld_ops.stateToFailed(ngsi, entity.id, {"value": "ERROR! The '{0}' entity has output: '{1}'.".format(entity.id, output_entities_id)})
        output_exists = True

    return output_exists


def checkDataSourceExistence(datasource_id: str, ngsi: NGSILDClient) -> bool:
    """
    Checking if collection agent entity (i.e., MetricSource, TelemetrySource, ...) has a relationship with a data source entity (i.e., Prometheus, Device, ...)
    """
    datasource_entities = ngsi.queryEntities(type=datasource_id.split(":")[2])
    datasource_exists = False
    if len(datasource_entities) > 0:
        for datasource_entity in datasource_entities:
            if datasource_entity['id'] == datasource_id:
                datasource_exists = True
                break
            else:
                datasource_exists = False
    else:
        datasource_exists = False

    return datasource_exists


def checkEndpointExistence(endpoint_id: str, ngsi: NGSILDClient) -> bool:
    """
    Checking if data source entity (i.e., Prometheus, Device, ...) has a relationship with an Endpoint entity.
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


def checkStreamApplicationExistence(metricProcessor: MetricProcessor, ngsi: NGSILDClient) -> bool:
    """
    Checking if MetricProcesor entity has a relationship with an already created StreamApplication entity (and the regarding application JAR already uploaded in stream processing engine).
    """
    streamApplication_entities = ngsi.queryEntities(type="StreamApplication")
    streamApplication_exists = False
    if len(streamApplication_entities) > 0:
        for streamApplication_entity in streamApplication_entities:
            if streamApplication_entity['id'] == metricProcessor.hasApplication.object:
                streamApplication_exists = True
                break
            else:
                streamApplication_exists = False
    else:
        streamApplication_exists = False

    return streamApplication_exists
