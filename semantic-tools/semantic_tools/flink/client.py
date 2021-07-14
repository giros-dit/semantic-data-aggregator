from semantic_tools.flink.api import FlinkAPI
from semantic_tools.models.application import Task

import logging
import time

logger = logging.getLogger(__name__)


class FlinkClient(object):
    """
    Class encapsulating the main operations with Apache Flink.
    """

    def __init__(self, url: str = "http://flink-jobmanager:8081",
                 headers: dict = {
                    "Accept": "application/json",
                    "Content-Type": "application/json"}):
        # Init Flink REST API Client
        self.api = FlinkAPI(url, headers=headers)

    def check_flink_status(self):
        """
        Infinite loop that checks every 30 seconds
        until Flink REST API becomes available.
        """
        logger.info("Checking Flink REST API status ...")
        while True:
            if self.api.checkFlinkHealth():
                logger.info(
                    "Successfully connected to Flink REST API!")
                break
            else:
                logger.warning("Could not connect to Flink REST API. "
                               "Retrying in 30 seconds ...")
                time.sleep(30)
                continue

    def delete_job_from_task(self, task: Task) -> dict:
        """
        Deletes a Flink job from a given Task entity.
        """
        job = self.api.deleteJob(task.internalId.value)
        logger.info("Job '{0}' deleted in Flink engine.".format(
            task.internalId.value))
        return job

    def instantiate_job_from_task(self, task: Task,
                                  applicationId: str,
                                  args: dict) -> dict:
        """
        Insantiates a Flink job from a given Task entity
        and its associated Application, i.e., JAR.
        """
        # Get a entry class of the Stream Aplication
        entryClass = args.value["entryClass"]
        # Run job for JAR id
        job = self.api.submitJob(applicationId, entryClass, args)
        logger.info(
            "Job '{0}' with '{1}' JAR instantiated in Flink engine.".format(
                task.internalId.value, applicationId))
        return job
