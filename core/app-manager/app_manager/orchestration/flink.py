import logging
import time

from semantic_tools.flink.api import FlinkAPI

logger = logging.getLogger(__name__)


class FlinkClient(FlinkAPI):
    """
    Class encapsulating the main operations with Apache Flink.
    """

    def __init__(self, url: str = "http://flink-jobmanager:8081",
                 headers: dict = {
                    "Accept": "application/json",
                    "Content-Type": "application/json"}):
        # Init NGSI-LD REST API Client
        super().__init__(url=url, headers=headers)

    def check_flink_status(self):
        """
        Infinite loop that checks every 30 seconds
        until Flink REST API becomes available.
        """
        logger.info("Checking Flink REST API status ...")
        while True:
            if self.checkFlinkHealth():
                logger.info(
                    "Successfully connected to Flink REST API!")
                break
            else:
                logger.warning("Could not connect to Flink REST API. "
                               "Retrying in 30 seconds ...")
                time.sleep(30)
                continue

    def upload_jar(self, file_path: str) -> dict:
        """
        Upload JAR file to Flink from a given path.
        """
        return self.uploadJar(file_path)
