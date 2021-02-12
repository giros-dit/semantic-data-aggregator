from semantic_tools.clients.ngsi_ld import NGSILDClient
from semantic_tools.clients.flink_api_rest import FlinkClient
from semantic_tools.models.metric import (
    MetricProcessor,
    StreamApplication
)

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
    jarfiles = flink.getFlinkAppsJars()['files']
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

def processMetricProcessorMode(metricProcessor: MetricProcessor,
                               ngsi: NGSILDClient,
                               flink: FlinkClient):
    """
    Process MetricProcess resources (i.e., Flink job)
    based on the value of the stageMode property
    """
    ngsi_ld_ops.appendModeResult(ngsi, metricProcessor.id)
    job = ""
    job_exist = False
    if metricProcessor.jobId.value != "":
        job = flink.getFlinkJob(metricProcessor.jobId.value)
        job_exist = True
    if metricProcessor.stageMode.value == "START":
        modeResult_failed = checkStreamApplicationExistence(metricProcessor, ngsi)
        if modeResult_failed == False:
            if job_exist == False or job['state'] == 'CANCELED':
                submitStreamJob(metricProcessor, ngsi, flink)
                logger.info(
                    "Start new '{0}' MetricProcessor.".format(metricProcessor.id)
                    )
            elif job_exist and job['state'] == 'RUNNING':
                # Cancel job and create another with new configuration
                #deleteStreamJob(metricProcessor, ngsi, flink)
                logger.info(
                    "Delete Flink Job with id {0}".format(
                        flink.deleteJob(job['jid'])))
                submitStreamJob(metricProcessor, ngsi, flink)
                logger.info(
                    "Upgrade '{0}' MetricProcessor.".format(metricProcessor.id)
                    )
            ngsi_ld_ops.stageToSuccessful(ngsi, metricProcessor.id)
    if metricProcessor.stageMode.value == "STOP":
        if job_exist == False or job['state'] == 'CANCELED':
            logger.info(
                "New '{0}' MetricProcessor already stopped. Moving on.".format(
                    metricProcessor.id)
            )
        elif job_exist and job['state'] == 'RUNNING':
            #deleteStreamJob(metricProcessor, ngsi, flink)
            logger.info(
                "Delete Flink Job with id {0}".format(
                    flink.deleteJob(job['jid'])))
            logger.info(
                "Stop '{0}' MetricProcessor.".format(metricProcessor.id)
            )
        ngsi_ld_ops.stageToSuccessful(ngsi, metricProcessor.id)
    if metricProcessor.stageMode.value == "TERMINATE":
        if job_exist == False:
            logger.info(
                "New '{0}' MetricProcessor not started.".format(metricProcessor.id)
            )
        else:
            logger.info(
                "Terminate '{0}' MetricProcessor.".format(
                    metricProcessor.id)
                )
            if job['state'] == 'RUNNING':
                #deleteStreamJob(metricProcessor, ngsi, flink)
                logger.info(
                    "Delete Flink Job with id {0}".format(
                        flink.deleteJob(job['jid'])))
                logger.info(
                    "Cancel '{0}' MetricProcessor.".format(metricProcessor.id)
                    )

        ngsi_ld_ops.stageToSuccessful(ngsi, metricProcessor.id)
        logger.info("Delete '{0}' entity".format(metricProcessor.id))
        ngsi.deleteEntity(metricProcessor.id)

def checkStreamApplicationExistence(metricProcessor: MetricProcessor, ngsi: NGSILDClient) -> bool:
    """
    Checking if MetricProcesor has a StreamApplication already created
    """
    streamApplication_entities = ngsi.queryEntities(type="StreamApplication")
    modeResult_failed = False
    if len(streamApplication_entities) > 0:
        for streamApplication_entity in streamApplication_entities:
            if streamApplication_entity['id'] == metricProcessor.hasApplication.object:
                modeResult_failed = False
            else:
                modeResult_failed = True
    else:
        modeResult_failed = True
    if modeResult_failed:
        ngsi_ld_ops.stageToFailed(ngsi, metricProcessor.id, {"value": "ERROR. The '{0}' StreamApplication doesn't exist.".format(metricProcessor.hasApplication.object)})
        logger.info("Start new '{0}' MetricProcessor. The job execution failed.".format(metricProcessor.id))
    return modeResult_failed
