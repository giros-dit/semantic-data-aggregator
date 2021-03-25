from semantic_tools.clients.ngsi_ld import NGSILDClient
from semantic_tools.clients.flink_api_rest import FlinkClient
from semantic_tools.models.metric import (
    MetricProcessor,
    StreamApplication
)
from semantic_tools.models.ngsi_ld.entity import Entity

import logging
import ngsi_ld_ops
import subprocess
import time

logging.basicConfig(
        level=logging.INFO,
        format="%(asctime)s - %(name)s - %(levelname)s - %(message)s")
logger = logging.getLogger(__name__)


def uploadStreamApp(streamApplication: StreamApplication,
                    ngsi: NGSILDClient,
                    flink: FlinkClient):

    # Fetch JAR file from URI
    jarfile_download = subprocess.Popen(["wget", streamApplication.uri.value],
                                        stdout=subprocess.PIPE,
                                        stderr=subprocess.STDOUT)
    stdout, stderr = jarfile_download.communicate()
    time.sleep(2)

    # Upload JAR file
    jar_uploaded = flink.uploadJar(streamApplication.fileName.value)
    logger.info("Upload Flink JAR: {0}".format(jar_uploaded))

    # Remove JAR file from Weaver system store
    subprocess.call(["rm", streamApplication.fileName.value])

    # Update StreamApplication Entity with JAR id and entry-class
    jarfiles = flink.getFlinkAppJars()['files']
    jarfile_id = ""
    entries = ""
    entry_name = ""
    for jarfile in jarfiles:
        if jarfile["name"] == streamApplication.fileName.value:
            jarfile_id = jarfile["id"]
            entries = jarfile["entry"]

    for entry in entries:
        entry_name = entry["name"]

    fileId_dict = {
        "fileId": {
            "type": "Property",
            "value": jarfile_id
        }
    }

    ngsi.updateEntityAttrs(streamApplication.id, fileId_dict)

    entryClass_dict = {
        "entryClass": {
            "type": "Property",
            "value": entry_name
        }
    }

    ngsi.updateEntityAttrs(streamApplication.id, entryClass_dict)


def submitStreamJob(metricProcessor: MetricProcessor,
                    ngsi: NGSILDClient,
                    flink: FlinkClient) -> str:
    streamApplication_entity = ngsi.retrieveEntityById(
                                metricProcessor.hasApplication.object)
    streamApplication = StreamApplication.parse_obj(streamApplication_entity)

    # Retrieve StreamApplication Entity JAR id
    jarfile_id = streamApplication.fileId.value

    # Infer input_topic argument from hasInput relationship
    input_topic = metricProcessor.hasInput.object.strip(
        "urn:ngsi-ld:").replace(":", "-").lower()

    # Infer output_topic argument from id argument
    output_topic = metricProcessor.id.strip(
        "urn:ngsi-ld:").replace(":", "-").lower()

    # Get a list of arguments separated by commas (e.g. arg1, arg2, ...)
    # to run the Flink job
    arguments = getStreamAppArguments(metricProcessor,
                                      input_topic,
                                      output_topic)

    # Get a entry class of the Stream Aplication
    entryClass = streamApplication.entryClass.value

    # Run job for JAR id
    job_submitted = flink.submitJob(jarfile_id, entryClass, arguments)
    logger.info("Submit Flink Job: {0}".format(job_submitted))

    # Update MetricProcessor Entity with Job id
    job_id = job_submitted['jobid']
    jobId_dict = {
        "jobId": {
                "type": "Property",
                "value": job_id
        }
    }
    ngsi.updateEntityAttrs(metricProcessor.id, jobId_dict)
    return job_id


def deleteAllStreamJobs(ngsi: NGSILDClient, flink: FlinkClient):
    jobs = flink.getFlinkJobs()["jobs"]
    jobId = ""

    for job in jobs:
        if job['status'] == 'RUNNING':
            jobId = job['id']
            filter = "jobId=="+'"'+jobId+'"'
            metricProcessor_entity = ngsi.queryEntities(type="MetricProcessor",
                                                        q=filter)
            metricProcessor = MetricProcessor.parse_obj(
                                metricProcessor_entity[0])
            logger.info(
                "Delete MetricProcessor Entity with id {0}".format(
                    metricProcessor.id))
            ngsi.deleteEntity(metricProcessor.id)
            logger.info(
                "Delete Flink Job with id {0}".format(
                    flink.deleteJob(jobId)))


def deleteStreamJob(metricProcessor: MetricProcessor,
                    ngsi: NGSILDClient,
                    flink: FlinkClient):
    jobId = metricProcessor.jobId.value
    jobs = flink.getFlinkJobs()["jobs"]

    for job in jobs:
        if job['status'] == 'RUNNING' and jobId == job['id']:
            logger.info(
                "Delete Flink Job with id {0}".format(
                    flink.deleteJob(jobId)))


def getStreamAppArguments(metricProcessor: MetricProcessor,
                          input_topic: str,
                          output_topic: str) -> str:

    arguments_list = []
    arguments_list.append(input_topic)
    arguments_list.append(output_topic)

    if(metricProcessor.arguments):
        arguments_property = metricProcessor.arguments.value
        for key, value in arguments_property.items():
            arguments_list.append(value)

    arguments = ""
    for i in range(0, len(arguments_list)):
        if(i < (len(arguments_list)-1)):
            arguments += arguments_list[i]+","
        else:
            arguments += arguments_list[i]

    return arguments

def processStreamApplicationState(streamApplication: StreamApplication,
                               ngsi: NGSILDClient,
                               flink: FlinkClient):
    """
    Process StreamApplication resources (i.e., application jar)
    based on the value of the action property
    """
    ngsi_ld_ops.appendState(ngsi, streamApplication.id, "Building the stream application...")
    jar = ""
    jar_exists = False
    if streamApplication.fileId.value != "":
        jarId = streamApplication.fileId.value
        jar_exists = True
    if streamApplication.action.value == "START":
        if jar_exists == False:
            uploadStreamApp(streamApplication, ngsi, flink)
            logger.info(
                "Upload new '{0}' Flink application jar.".format(streamApplication.id)
                )
        else:
            # Delete jar and create another one with new configuration
            logger.info("Delete Flink application jar with id {0}".format(jarId))
            flink.deleteJar(jarId)
            uploadStreamApp(streamApplication, ngsi, flink)
            logger.info(
                "Upgrade '{0}' Flink application jar.".format(streamApplication.id)
                )
        ngsi_ld_ops.stateToUploaded(ngsi, streamApplication.id, {"value": "SUCCESS! Stream application uploaded successfully."})
    elif streamApplication.action.value == "END":
        if jar_exists == False:
            logger.info(
                "New '{0}' Flink application jar does not exist.".format(streamApplication.id)
            )
        else:
            logger.info("Delete Flink application jar with id {0}".format(jarId))
            flink.deleteJar(jarId)
        ngsi_ld_ops.stateToCleaned(ngsi, streamApplication.id, {"value": "SUCCESS! Stream application deleted successfully."})
        logger.info("Delete '{0}' entity".format(streamApplication.id))
        ngsi.deleteEntity(streamApplication.id)

def processMetricProcessorState(metricProcessor: MetricProcessor,
                               ngsi: NGSILDClient,
                               flink: FlinkClient):
    """
    Process MetricProcess resources (i.e., Flink job)
    based on the value of the action property
    """
    ngsi_ld_ops.appendState(ngsi, metricProcessor.id, "Building the aggregation agent...")
    job = ""
    job_exists = False
    if metricProcessor.jobId.value != "":
        job = flink.getFlinkJob(metricProcessor.jobId.value)
        job_exists = True
    if metricProcessor.action.value == "START":
        streamApplication_exists = checkStreamApplicationExistence(metricProcessor, ngsi)
        input_exists = checkInputExistence(metricProcessor, ngsi)
        if streamApplication_exists == True and input_exists == True:
            if job_exists == False or job['state'] == 'CANCELED':
                submitStreamJob(metricProcessor, ngsi, flink)
                logger.info(
                    "Submit new '{0}' Flink Job.".format(metricProcessor.id)
                    )
            elif job_exists and job['state'] == 'RUNNING':
                # Cancel job and create another with new configuration
                #deleteStreamJob(metricProcessor, ngsi, flink)
                logger.info(
                    "Cancel Flink Job with id {0}".format(
                        flink.deleteJob(job['jid'])))
                submitStreamJob(metricProcessor, ngsi, flink)
                logger.info(
                    "Upgrade '{0}' Flink Job.".format(metricProcessor.id)
                    )
            ngsi_ld_ops.stateToRunning(ngsi, metricProcessor.id, {"value": "SUCCESS! Aggregation agent started successfully."})
        else:
            if streamApplication_exists == False:
            	logger.info("Submit new '{0}' Flink Job. The job execution failed.".format(metricProcessor.id))
            	ngsi_ld_ops.stateToFailed(ngsi, metricProcessor.id, {"value": "ERROR! The '{0}' StreamApplication entity doesn't exist.".format(metricProcessor.hasApplication.object)})

            if input_exists == False:
            	logger.info("Submit new '{0}' Flink Job. The job execution failed.".format(metricProcessor.id))
            	ngsi_ld_ops.stateToFailed(ngsi, metricProcessor.id, {"value": "ERROR! The '{0}' input entity doesn't exist.".format(metricProcessor.hasInput.object)})

            logger.info("Delete '{0}' entity".format(metricProcessor.id))
            ngsi.deleteEntity(metricProcessor.id)
    elif metricProcessor.action.value == "STOP":
        if job_exists == False or job['state'] == 'CANCELED':
            logger.info(
                "New '{0}' Flink Job already stopped. Moving on.".format(
                    metricProcessor.id)
            )
        elif job_exists and job['state'] == 'RUNNING':
            #deleteStreamJob(metricProcessor, ngsi, flink)
            logger.info(
                "Cancel Flink Job with id {0}".format(
                    flink.deleteJob(job['jid'])))
            logger.info(
                "Stop '{0}' Flink Job.".format(metricProcessor.id)
            )
        ngsi_ld_ops.stateToStopped(ngsi, metricProcessor.id, {"value": "SUCCESS! Aggregation agent stopped successfully."})
    elif metricProcessor.action.value == "END":
        output_exists = checkOutputExistence(metricProcessor, ngsi)
        if output_exists == False:
            if job_exists == False:
                logger.info(
                    "New '{0}' Flink Job not started.".format(metricProcessor.id)
                )
            else:
                if job['state'] == 'RUNNING':
                    #deleteStreamJob(metricProcessor, ngsi, flink)
                    logger.info(
                        "Cancel Flink Job with id {0}".format(
                            flink.deleteJob(job['jid'])))
                logger.info(
                    "Delete '{0}' Flink Job.".format(
                        metricProcessor.id)
                    )
            ngsi_ld_ops.stateToCleaned(ngsi, metricProcessor.id, {"value": "SUCCESS! Aggregation agent deleted successfully."})
            logger.info("Delete '{0}' entity".format(metricProcessor.id))
            ngsi.deleteEntity(metricProcessor.id)

def checkStreamApplicationExistence(metricProcessor: MetricProcessor, ngsi: NGSILDClient) -> bool:
    """
    Checking if MetricProcesor has a StreamApplication already created
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
    #if streamApplication_exists:
        #ngsi_ld_ops.stateToFailed(ngsi, metricProcessor.id, {"value": "ERROR! The '{0}' StreamApplication entity doesn't exist.".format(metricProcessor.hasApplication.object)})
        #logger.info("Submit new '{0}' Flink Job. The job execution failed.".format(metricProcessor.id))
        #logger.info("Delete '{0}' entity".format(metricProcessor.id))
        #ngsi.deleteEntity(metricProcessor.id)
    return streamApplication_exists

def checkInputExistence(entity: Entity, ngsi: NGSILDClient) -> bool:
    """
    Checking if aggregation agent entity has an input
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
    Checking if aggregation agent entity has an output
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
        logger.info("Delete a '{0}' NiFi flow. The processor deletion failed.".format(entity.id))
        ngsi_ld_ops.stateToFailed(ngsi, entity.id, {"value": "ERROR! The '{0}' entity has an output: '{1}'.".format(entity.id, output_entities_id)})
        output_exists = True

    return output_exists
