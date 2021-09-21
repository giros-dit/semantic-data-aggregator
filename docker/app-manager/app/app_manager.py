import logging
import os
from typing import Optional
from xml.etree import ElementTree as et

from semantic_tools.flink.client import FlinkClient
from semantic_tools.models.application import Application
from semantic_tools.ngsi_ld.client import NGSILDClient
from semantic_tools.nifi.client import NiFiClient

logger = logging.getLogger(__name__)


def upload_local_flink_jars(flink: FlinkClient,
                            ngsi_ld: NGSILDClient,
                            app_manager_url: str):
    """
    Uploads the stream processing application JARs stored locally in the service
    to the stream processing engine (i.e., the Flink engine).
    """
    # Upload templates
    jars_path = "/catalog/flink/jars"
    for file in os.listdir(jars_path):
        logger.info("Uploading '%s' admin stream processing application JAR to Flink..." % file)
        try:
            jar = flink.upload_jar(
                        jars_path + "/" + file)
        except Exception as e:
            logger.info(str(e))
            continue
        jar_id = jar["filename"].split("/")[-1]
        application_name = jar_id.split("_")[-1].replace(".jar","")
        # Register Application context
        application_uri = app_manager_url + jars_path + "/" + file
        application_id = "urn:ngsi-ld:Application:{0}".format(
            jar_id.split("_")[0]
        )
        ngsi_ld.create_application(
            application_id, jar_id, "FLINK",
            application_name, application_uri, "Uploaded by admin")


def upload_flink_jar(flink: FlinkClient,
                     ngsi_ld: NGSILDClient,
                     name: str,
                     file_path: str,
                     app_manager_url: str,
                     description: Optional[str] = None) -> Application:
    """
    Uploads a stream processing application JAR provided by user
    to the stream processing engine (i.e., the Flink engine).
    """
    # Upload JAR file
    # Rename file as Flink sets the JAR name from the file name
    upload_response = flink.upload_jar(file_path)
    jar_id = upload_response["filename"].split("/")[-1]
    logger.info(
        "Application '{0}' with name '{1}' uploaded in Flink.".format(
            jar_id, name))
    # Register Application context
    local_path = "/catalog/flink/jars/%s" % jar_id
    application_uri = app_manager_url + local_path
    application_id = "urn:ngsi-ld:Application:{0}".format(
        jar_id.split("_")[0]
    )
    return ngsi_ld.create_application(
        application_id, jar_id, "FLINK",
        name, application_uri, description)


def upload_local_nifi_templates(
        nifi: NiFiClient,
        ngsi_ld: NGSILDClient,
        app_manager_url: str):
    """
    Upload NiFi templates stored locally in the service.
    """
    # Upload templates
    templates_path = "/catalog/nifi/templates"
    for file in os.listdir(templates_path):
        logger.info("Uploading '%s' admin template to NiFi..." % file)
        try:
            template = nifi.upload_template(
                        templates_path + "/" + file)
        except Exception as e:
            logger.info(str(e))
            continue
        internal_id = template.id
        application_name = template.template.name
        # Register Application context
        application_uri = app_manager_url + templates_path + "/" + file
        application_id = "urn:ngsi-ld:Application:{0}".format(
            internal_id
        )
        ngsi_ld.create_application(
            application_id, internal_id, "NIFI",
            application_name, application_uri, "Uploaded by admin")


def upload_nifi_template(
                nifi: NiFiClient,
                ngsi_ld: NGSILDClient,
                name: str,
                file_path: str,
                app_manager_url: str,
                description: Optional[str] = None) -> Application:
    """
    Upload NiFi templates provided by user.
    """
    logger.info("Uploading '%s' template to NiFi..." % name)
    # Update name and description of template
    tree = et.parse(file_path)
    tree.find('./name').text = name
    if description:
        tree.find('./description').text = description
    tree.write(file_path)
    # Upload template
    template = nifi.upload_template(file_path)
    # Register Application context
    internal_id = template.id
    local_path = "/catalog/nifi/templates/%s.xml" % internal_id
    application_uri = app_manager_url + local_path
    application_id = "urn:ngsi-ld:Application:{0}".format(
        internal_id
    )
    application = ngsi_ld.create_application(
        application_id, internal_id, "NIFI",
        name, application_uri, description)
    return application
