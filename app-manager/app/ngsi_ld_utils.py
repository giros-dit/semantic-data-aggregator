from enum import Enum
from typing import Optional
from semantic_tools.clients.ngsi_ld import NGSILDClient
from semantic_tools.models.application import Application

import logging
import time

logger = logging.getLogger(__name__)


def check_scorpio_status(ngsi: NGSILDClient):
    """
    Infinite loop that checks every 30 seconds
    until Scorpio REST API becomes available.
    """
    logger.info("Checking Scorpio REST API status ...")
    while True:
        if ngsi.checkScorpioHealth():
            logger.info(
                "Weaver successfully connected to Scorpio REST API!")
            break
        else:
            logger.warning("Could not connect to Scorpio REST API. "
                           "Retrying in 30 seconds ...")
            time.sleep(30)
            continue


def create_application_context(ngsi: NGSILDClient,
                               applicationId: str,
                               applicationType: str,
                               name: str,
                               uri: str,
                               description: Optional[str] = None):
    """
    Create context for the uploaded application
    """
    application = Application(
        id=applicationId,
        name={"value": name},
        applicationId={"value": name},
        applicationType={"value": applicationType},
        uri={"value": uri}
    )
    ngsi.createEntity(application.dict(exclude_none=True))
