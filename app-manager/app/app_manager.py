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


def uploadStreamApp(application: Application,
                    ngsi: NGSILDClient,
                    flink: FlinkClient) -> bool:
    """
    Uploads a stream processing application JAR to the stream processing engine (i.e., the Flink engine).
    """
    # Fetch JAR file from URI
    jarfile_download = subprocess.Popen(["wget", application.uri.value],
                                        stdout=subprocess.PIPE,
                                        stderr=subprocess.STDOUT)
    stdout, stderr = jarfile_download.communicate()
    uploaded = False
    if jarfile_download.returncode == 0:
        time.sleep(2)
        # Upload JAR file
        flink.uploadJar(application.fileName.value)
        # Remove JAR file from Weaver system store
        subprocess.call(["rm", application.fileName.value])
        # Update application Entity with JAR id and entry-class
        jarfiles = flink.getFlinkAppJars()['files']
        jarfile_id = ""
        entries = ""
        entry_name = ""
        for jarfile in jarfiles:
            if jarfile["name"] == application.fileName.value:
                jarfile_id = jarfile["id"]
                entries = jarfile["entry"]

        for entry in entries:
            entry_name = entry["name"]

        fileId_dict = {
            "fileId": {
                "type": "Property",
                "value": jarfile_id
            }
        }
        ngsi.appendEntityAttrs(application.id, fileId_dict)

        entryClass_dict = {
            "entryClass": {
                "type": "Property",
                "value": entry_name
            }
        }
        ngsi.appendEntityAttrs(application.id, entryClass_dict)

        uploaded = True
        logger.info("application '{0}' with '{1}' JAR uploaded in Flink engine.".format(application.id, application.fileName.value))
    else:
        output = ""
        output_lines = stdout.decode("utf-8").split('\n')
        for output_line in output_lines:
            output += output_line
        logger.info(
            "application '{0}' with '{1}' JAR not uploaded in Flink engine. The download operation from the '{2}' URL failed: '{3}'"
            .format(application.id, application.fileName.value, application.uri.value, output)
        )
        uploaded = False

    return uploaded
