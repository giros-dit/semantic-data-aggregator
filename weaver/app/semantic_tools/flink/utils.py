from semantic_tools.flink.client import FlinkClient
from semantic_tools.models.application import Application, Task

import logging
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
                "Successfully connected to Flink REST API!")
            break
        else:
            logger.warning("Could not connect to Flink REST API. "
                           "Retrying in 30 seconds ...")
            time.sleep(30)
            continue


def deleteTask(task: Task,
               flink: FlinkClient) -> dict:
    """
    Submits a stream processing Job instance to the
    stream processing engine (i.e., the Flink engine).
    """
    # Run job for JAR id
    job = flink.deleteJob(task.internalId.value)
    logger.info("Job '{0}' deleted in Flink engine.".format(
        task.internalId.value))
    return job


def instantiateTask(task: Task,
                    flink: FlinkClient,
                    applicationId: str,
                    args: dict) -> dict:
    """
    Submits a stream processing Job instance to the
    stream processing engine (i.e., the Flink engine).
    """
    # Get a entry class of the Stream Aplication
    entryClass = args.value["entryClass"]
    # Run job for JAR id
    job = flink.submitJob(applicationId, entryClass, args)
    logger.info(
        "Job '{0}' with '{1}' JAR instantiated in Flink engine.".format(
            task.internalId.value, applicationId))
    return job
