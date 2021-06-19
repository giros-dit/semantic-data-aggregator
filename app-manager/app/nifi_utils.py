from urllib3.exceptions import MaxRetryError

import logging
import nipyapi
import time
import os

logger = logging.getLogger(__name__)


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
        logger.info("Weaver successfully connected to NiFi REST API!")
        break


def upload_template(name, file_path):
    """
    Upload NiFi templates provided by user.
    """
    logger.info("Uploading '%s' template to NiFi..." % name)
    # Get root PG
    root_pg = nipyapi.canvas.get_process_group("root")
    try:
        nipyapi.templates.upload_template(
            root_pg.id, file_path)
    except ValueError:
        logger.info("'%s' already uploaded in NiFi." % name)


def upload_local_templates():
    """
    Upload NiFi templates stored locally in the service.
    """
    # Get root PG
    root_pg = nipyapi.canvas.get_process_group("root")
    # Upload templates
    templates_path = "/catalog/nifi/templates"
    for template in os.listdir(templates_path):
        try:
            nipyapi.templates.upload_template(
                root_pg.id, templates_path + "/" + template)
        except ValueError:
            logger.info("%s already uploaded in NiFi." % template)
