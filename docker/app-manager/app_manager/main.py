import logging
import os
import sys
import time

from flink_client.api.default_api import DefaultApi as FlinkClient
from flink_client.api_client import ApiClient
from flink_client.configuration import Configuration
from redis import Redis

from app_manager import loader
from app_manager.orchestration.nifi import NiFiClient

logger = logging.getLogger(__name__)

# Flink
FLINK_MANAGER_URI = os.getenv("FLINK_MANAGER_URI",
                              "http://flink-jobmanager:8081")
# NiFi
NIFI_URI = os.getenv("NIFI_URI", "https://nifi:8443/nifi-api")
NIFI_USERNAME = os.getenv("NIFI_USERNAME")
NIFI_PASSWORD = os.getenv("NIFI_PASSWORD")
# Redis
REDIS_HOST = os.getenv("REDIS_HOST", "redis")
REDIS_PORT = os.getenv("REDIS_PORT", "6379")
REDIS_PASSWORD = os.getenv("REDIS_PASSWORD")

# Init Flink REST API Client
configuration = Configuration(host=FLINK_MANAGER_URI)
api_client = ApiClient(configuration=configuration)
flink = FlinkClient(api_client=api_client)
# Init NiFi REST API Client
nifi = NiFiClient(username=NIFI_USERNAME,
                  password=NIFI_PASSWORD,
                  url=NIFI_URI)
redis = Redis(
    host=REDIS_HOST,
    port=REDIS_PORT,
    password=REDIS_PASSWORD)


def main():
    # Check NiFi REST API is up
    # Hack for startup
    while True:
        try:
            nifi.login()
            break
        except Exception:
            logger.warning("NiFi REST API not available. "
                           "Retrying after 10 seconds...")
            time.sleep(10)
    # Upload NiFi admin templates
    templates = loader.upload_local_nifi_templates(nifi)
    for template in templates:
        redis.hset("NIFI", template[0], template[1])
    # Check Flink REST API is up
    logger.info("Checking Flink REST API status ...")
    while True:
        try:
            _ = flink.config_get()
            logger.info(
                "Successfully connected to Flink REST API!")
            break
        except Exception:
            logger.warning("Could not connect to Flink REST API. "
                           "Retrying in 30 seconds ...")
            time.sleep(30)
            continue
    # Upload Flink admin JARs
    jars = loader.upload_local_flink_jars(flink)
    for jar in jars:
        redis.hset("FLINK", jar[0], jar[1])


if __name__ == "__main__":
    logging.basicConfig(
        stream=sys.stdout, level=logging.INFO,
        format="%(asctime)s - %(name)s - %(levelname)s - %(message)s")

    main()
