import logging
import os
import time

from redis import Redis
from semantic_tools.flink.client import FlinkClient
from semantic_tools.nifi.client import NiFiClient

from app_manager import loader

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
flink = FlinkClient(url=FLINK_MANAGER_URI)
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
    flink.check_flink_status()
    # Upload Flink admin JARs
    jars = loader.upload_local_flink_jars(flink)
    for jar in jars:
        redis.hset("FLINK", jar[0], jar[1])


if __name__ == "__main__":
    main()
