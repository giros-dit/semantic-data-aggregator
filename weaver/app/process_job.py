from ops import ngsi_ld_ops
from ops import flink_ops
from semantic_tools.clients.ngsi_ld import NGSILDClient
from semantic_tools.clients.flink_api_rest import FlinkClient
from semantic_tools.models.application import Application

import logging

logger = logging.getLogger(__name__)


def processapplicationState(application: Application, ngsi:
                            NGSILDClient, flink: FlinkClient):
    """
    Process application entity resources
    (i.e., stream processing application JARs)
    based on the value of the action property.
    """
    ngsi_ld_ops.appendState(
        ngsi, application.id, "Building the stream processing application...")
    jarId = ""
    jar_exists = False
    if application.fileId:
        jarId = application.fileId.value
        jar_exists = True
    if application.action.value == "START":
        if jar_exists is False:
            logger.info(
                "Upload new '{0}' stream processing application JAR.".format(
                    application.id))
            uploaded = flink_ops.uploadStreamApp(application, ngsi, flink)
            if uploaded:
                ngsi_ld_ops.stateToUploaded(
                    ngsi, application.id, {"value": "SUCCESS! Stream application JAR uploaded successfully."})
            else:
                ngsi_ld_ops.stateToFailed(ngsi, application.id, {"value": "ERROR! Stream application JAR was not found in {0}.".format(application.uri.value)})
                logger.info("Delete the '{0}' application entity.".format(application.id))
                ngsi.deleteEntity(application.id)
        else:
            # Delete current JAR and create another one with new configuration
            logger.info("Delete '{0}' stream processing application JAR.".format(application.id))
            flink.deleteJar(jarId)
            logger.info("application '{0}' with '{1}' JAR deleted from Flink engine.".format(application.id, application.fileName.value))
            logger.info("Upgrade the previous '{0}' stream processing application JAR.".format(application.id))
            uploaded = flink_ops.uploadStreamApp(application, ngsi, flink)
            if uploaded == True:
                ngsi_ld_ops.stateToUploaded(ngsi, application.id, {"value": "SUCCESS! Stream application JAR upgraded successfully."})
            else:
                ngsi_ld_ops.stateToFailed(ngsi, application.id, {"value": "ERROR! Stream application JAR was not found in {0}.".format(application.uri.value)})
                logger.info("Delete the '{0}' application entity.".format(application.id))
                ngsi.deleteEntity(application.id)
    elif application.action.value == "END":
        logger.info("Delete '{0}' stream processing application JAR.".format(application.id))
        flink.deleteJar(jarId)
        logger.info("application '{0}' with '{1}' JAR deleted from Flink engine.".format(application.id, application.fileName.value))
        ngsi_ld_ops.stateToCleaned(ngsi, application.id, {"value": "SUCCESS! Stream application JAR deleted successfully."})
        logger.info("Delete the '{0}' application entity.".format(application.id))
        ngsi.deleteEntity(application.id)



