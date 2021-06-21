from semantic_tools.clients.ngsi_ld import NGSILDClient, Options
from semantic_tools.clients.flink_api_rest import FlinkClient
from semantic_tools.models.application import Application
from typing import Optional
from urllib3.exceptions import MaxRetryError
from xml.etree import ElementTree as et

import logging
import nipyapi
import time
import os

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
                "App-manager successfully connected to Flink REST API!")
            break
        else:
            logger.warning("Could not connect to Flink REST API. "
                           "Retrying in 30 seconds ...")
            time.sleep(30)
            continue


def check_nifi_status():
    """
    Infinite loop that checks every 30 seconds
    until NiFi REST API becomes available.
    """
    logger.info("Checking NiFi REST API status ...")
    while True:
        try:
            nipyapi.system.get_nifi_version_info()
        except MaxRetryError:
            logger.warning("Could not connect to NiFi REST API. "
                           "Retrying in 30 seconds ...")
            time.sleep(30)
            continue
        logger.info("App-manager successfully connected to NiFi REST API!")
        break


def check_orion_status(ngsi: NGSILDClient):
    """
    Infinite loop that checks every 30 seconds
    until Orion REST API becomes available.
    """
    logger.info("Checking Orion-LD REST API status ...")
    while True:
        if ngsi.checkOrionHealth():
            logger.info(
                "App-manager successfully connected to Orion-LD REST API!")
            break
        else:
            logger.warning("Could not connect to Orion-LD REST API. "
                           "Retrying in 30 seconds ...")
            time.sleep(30)
            continue


def check_scorpio_status(ngsi: NGSILDClient):
    """
    Infinite loop that checks every 30 seconds
    until Scorpio REST API becomes available.
    """
    logger.info("Checking Scorpio REST API status ...")
    while True:
        if ngsi.checkScorpioHealth():
            logger.info(
                "App-manager successfully connected to Scorpio REST API!")
            break
        else:
            logger.warning("Could not connect to Scorpio REST API. "
                           "Retrying in 30 seconds ...")
            time.sleep(30)
            continue


def create_application_context(
                ngsi: NGSILDClient,
                applicationId: str,
                internalId: str,
                applicationType: str,
                name: str,
                uri: str,
                description: Optional[str] = None) -> Application:
    """
    Create context for the uploaded application
    """
    if description:
        application = Application(
            id=applicationId,
            name={"value": name},
            internalId={"value": internalId},
            applicationType={"value": applicationType},
            uri={"value": uri},
            description={"value": description}
        )
    else:
        application = Application(
            id=applicationId,
            name={"value": name},
            internalId={"value": internalId},
            applicationType={"value": applicationType},
            uri={"value": uri}
        )
    ngsi.createEntity(application.dict(exclude_none=True))
    return application


def upload_flink_jar(ngsi: NGSILDClient,
                     flink: FlinkClient,
                     name: str,
                     file_path: str,
                     app_manager_url: str,
                     description: Optional[str] = None) -> Application:
    """
    Uploads a stream processing application JAR
    to the stream processing engine (i.e., the Flink engine).
    """
    # Upload JAR file
    # Rename file as Flink sets the JAR name from the file name
    upload_response = flink.uploadJar(file_path)
    file_id = upload_response["filename"].split("/")[-1]
    jar_id = file_id.replace(" ", "_")
    logger.info(
        "Application '{0}' with name '{1}' uploaded in Flink.".format(
            jar_id, name))
    # Register Application context
    local_path = "/catalog/flink/jars/%s" % jar_id
    application_uri = app_manager_url + local_path
    application_id = "urn:ngsi-ld:Application:{0}".format(
        jar_id.split("_")[0]
    )
    return create_application_context(
        ngsi, application_id, jar_id, "FLINK",
        name, application_uri, description)


def upload_local_nifi_templates(ngsi: NGSILDClient, app_manager_url: str):
    """
    Upload NiFi templates stored locally in the service.
    """
    # Get root PG
    root_pg = nipyapi.canvas.get_process_group("root")
    # Upload templates
    templates_path = "/catalog/nifi/templates"
    for file in os.listdir(templates_path):
        logger.info("Uploading '%s' admin template to NiFi..." % file)
        try:
            template = nipyapi.templates.upload_template(
                        root_pg.id, templates_path + "/" + file)
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
        create_application_context(
            ngsi, application_id, internal_id, "NIFI",
            application_name, application_uri, "Uploaded by admin")


def upload_nifi_template(
                ngsi: NGSILDClient,
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
    # Get root PG
    root_pg = nipyapi.canvas.get_process_group("root")
    template = nipyapi.templates.upload_template(root_pg.id, file_path)
    # Register Application context
    internal_id = template.id
    local_path = "/catalog/nifi/templates/%s.xml" % internal_id
    application_uri = app_manager_url + local_path
    application_id = "urn:ngsi-ld:Application:{0}".format(
        internal_id
    )
    return create_application_context(
        ngsi, application_id, internal_id, "NIFI",
        name, application_uri, description)
