from semantic_tools.clients.ngsi_ld import NGSILDClient
from semantic_tools.clients.flink_api_rest import FlinkClient
from semantic_tools.models.application import Application, Task

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


def submitStreamJob(task: Task,
                    ngsi: NGSILDClient,
                    flink: FlinkClient) -> str:
    """
    Submits a stream processing Job instance to the stream processing engine (i.e., the Flink engine).
    """
    application_entity = ngsi.retrieveEntityById(
                                task.hasApplication.object)
    application = Application.parse_obj(application_entity)
    # Retrieve application Entity JAR id
    jarfile_id = application.fileId.value
    # Infer input_topic argument from hasInput relationship
    input_topic = task.hasInput.object.strip(
        "urn:ngsi-ld:").replace(":", "-").lower()
    # Infer output_topic argument from id property
    output_topic = task.id.strip(
        "urn:ngsi-ld:").replace(":", "-").lower()
    # Get a list of arguments separated by commas (e.g. arg1, arg2, ...)
    # to run the Flink Job
    arguments = getStreamAppArguments(task,
                                      input_topic,
                                      output_topic)
    # Get a entry class of the Stream Aplication
    entryClass = application.entryClass.value
    # Run job for JAR id
    job_submitted = flink.submitJob(jarfile_id, entryClass, arguments)
    # Update task Entity with Job id
    job_id = job_submitted['jobid']
    jobId_dict = {
        "jobId": {
                "type": "Property",
                "value": job_id
        }
    }
    ngsi.appendEntityAttrs(task.id, jobId_dict)
    logger.info("'{0}' with '{1}' Job submitted in Flink engine.".format(
        task.id, task.name.value))

    return job_id


def getStreamAppArguments(task: Task,
                          input_topic: str,
                          output_topic: str) -> str:
    """
    Gets all the attributes of the 'arguments' property from a specific task entity.
    """
    arguments_list = []
    arguments_list.append(input_topic)
    arguments_list.append(output_topic)
    if(task.arguments):
        arguments_property = task.arguments.value
        for key, value in arguments_property.items():
            arguments_list.append(value)

    arguments = ""
    for i in range(0, len(arguments_list)):
        if(i < (len(arguments_list)-1)):
            arguments += arguments_list[i]+","
        else:
            arguments += arguments_list[i]

    return arguments
