from semantic_tools.clients.ngsi_ld import NGSILDClient
from semantic_tools.clients.flink_api_rest import FlinkClient
from semantic_tools.models.metric import (
    MetricProcessor,
    StreamApplication
)

import logging
import subprocess
import time

logger = logging.getLogger(__name__)


def check_flink_status(flink: FlinkClient):
    """
    Infinite loop that checks every 30 seconds
    until Flink REST API becomes available.
    """
    logger.info("Checking Flink REST API status ...")
    while True:
        if flink.checkFlinkHealth():
            logger.info(
                "Weaver successfully connected to Flink REST API!")
            break
        else:
            logger.warning("Could not connect to Flink REST API. "
                           "Retrying in 30 seconds ...")
            time.sleep(30)
            continue


def uploadStreamApp(streamApplication: StreamApplication,
                    ngsi: NGSILDClient,
                    flink: FlinkClient) -> bool:
    """
    Uploads a stream processing application JAR to the stream processing engine (i.e., the Flink engine).
    """
    # Fetch JAR file from URI
    jarfile_download = subprocess.Popen(["wget", streamApplication.uri.value],
                                        stdout=subprocess.PIPE,
                                        stderr=subprocess.STDOUT)
    stdout, stderr = jarfile_download.communicate()
    uploaded = False
    if jarfile_download.returncode == 0:
        time.sleep(2)
        # Upload JAR file
        flink.uploadJar(streamApplication.fileName.value)
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
        ngsi.appendEntityAttrs(streamApplication.id, fileId_dict)

        entryClass_dict = {
            "entryClass": {
                "type": "Property",
                "value": entry_name
            }
        }
        ngsi.appendEntityAttrs(streamApplication.id, entryClass_dict)

        uploaded = True
        logger.info("StreamApplication '{0}' with '{1}' JAR uploaded in Flink engine.".format(streamApplication.id, streamApplication.fileName.value))
    else:
        output = ""
        output_lines = stdout.decode("utf-8").split('\n')
        for output_line in output_lines:
            output += output_line
        logger.info(
            "StreamApplication '{0}' with '{1}' JAR not uploaded in Flink engine. The download operation from the '{2}' URL failed: '{3}'"
	    .format(streamApplication.id, streamApplication.fileName.value, streamApplication.uri.value, output)
        )
        uploaded = False

    return uploaded


def submitStreamJob(metricProcessor: MetricProcessor,
                    ngsi: NGSILDClient,
                    flink: FlinkClient) -> str:
    """
    Submits a stream processing Job instance to the stream processing engine (i.e., the Flink engine).
    """
    streamApplication_entity = ngsi.retrieveEntityById(
                                metricProcessor.hasApplication.object)
    streamApplication = StreamApplication.parse_obj(streamApplication_entity)
    # Retrieve StreamApplication Entity JAR id
    jarfile_id = streamApplication.fileId.value
    # Infer input_topic argument from hasInput relationship
    input_topic = metricProcessor.hasInput.object.strip(
        "urn:ngsi-ld:").replace(":", "-").lower()
    # Infer output_topic argument from id property
    output_topic = metricProcessor.id.strip(
        "urn:ngsi-ld:").replace(":", "-").lower()
    # Get a list of arguments separated by commas (e.g. arg1, arg2, ...)
    # to run the Flink Job
    arguments = getStreamAppArguments(metricProcessor,
                                      input_topic,
                                      output_topic)
    # Get a entry class of the Stream Aplication
    entryClass = streamApplication.entryClass.value
    # Run job for JAR id
    job_submitted = flink.submitJob(jarfile_id, entryClass, arguments)
    # Update MetricProcessor Entity with Job id
    job_id = job_submitted['jobid']
    jobId_dict = {
        "jobId": {
                "type": "Property",
                "value": job_id
        }
    }
    ngsi.appendEntityAttrs(metricProcessor.id, jobId_dict)
    logger.info("MetricProcessor '{0}' with '{1}' Job submitted in Flink engine.".format(metricProcessor.id, metricProcessor.name.value))

    return job_id


def getStreamAppArguments(metricProcessor: MetricProcessor,
                          input_topic: str,
                          output_topic: str) -> str:
    """
    Gets all the attributes of the 'arguments' property from a specific MetricProcessor entity.
    """
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

