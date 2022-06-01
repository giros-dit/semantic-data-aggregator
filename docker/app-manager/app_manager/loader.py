import logging
import os
from typing import List, Tuple

from app_manager.orchestration.flink import FlinkClient
from app_manager.orchestration.nifi import NiFiClient

logger = logging.getLogger(__name__)

# Config catalog paths
script_dir = os.path.dirname(__file__)
JARS_PATH = os.path.join(script_dir, "catalog", "flink", "jars/")
TEMPLATES_PATH = os.path.join(script_dir, "catalog", "nifi", "templates/")


def upload_local_flink_jars(flink: FlinkClient) -> List[Tuple]:
    """
    Uploads the stream processing application JARs
    stored locally in the service to the stream processing
    engine (i.e., the Flink engine).
    """
    # Upload templates
    jars = []
    for file in os.listdir(JARS_PATH):
        logger.info(
            "Uploading '%s' admin stream processing "
            "application JAR to Flink..." % file
        )
        try:
            jar = flink.upload_jar(JARS_PATH + file)
        except Exception as e:
            logger.info(str(e))
            continue
        jar_id = jar["filename"].split("/")[-1]
        flink_jar_name = jar_id.split("_")[-1].replace(".jar", "")
        jars.append(
            (flink_jar_name, jar_id)
        )

    return jars


def upload_local_nifi_templates(nifi: NiFiClient) -> List[Tuple]:
    """
    Upload NiFi templates stored locally in the service.
    """
    templates = []
    # Upload templates
    for file in os.listdir(TEMPLATES_PATH):
        logger.info("Uploading '%s' admin template to NiFi..." % file)
        try:
            template = nifi.upload_template(TEMPLATES_PATH + file)
        except Exception as e:
            logger.info(str(e))
            continue
        internal_id = template.id
        nifi_template_name = template.template.name
        templates.append(
            (nifi_template_name, internal_id)
        )

    return templates
