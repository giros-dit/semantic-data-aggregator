from semantic_tools.flink.client import FlinkClient
from semantic_tools.flink import utils as flink_ops
from semantic_tools.models.application import Application, Task
from semantic_tools.nifi import utils as nifi_ops
from semantic_tools.ngsi_ld.client import NGSILDClient
from semantic_tools.ngsi_ld import utils as ngsi_ld_ops
from weaver.applications import application_configs

import logging

logger = logging.getLogger(__name__)


def process_task(task: Task, ngsi: NGSILDClient, flink: FlinkClient):
    """
    Process Task entity
    """
    application_entity = ngsi.retrieveEntityById(task.hasApplication.object)
    application = Application.parse_obj(application_entity)
    logger.info("Processing task %s with application %s" % (
        task.id, application.name.value))
    ngsi_ld_ops.appendState(ngsi, task.id, "Configuring task...")
    if task.action.value == "START":
        logger.info(
            "Instantiating new '{0}'...".format(task.id))
        # TODO: The generation of arguments should be performed
        # by an intermidiate microservice
        # Weaver should only receive the final list of arguments
        # and configure the task with them
        arguments = application_configs[
            application.name.value](task, ngsi)
        if application.applicationType.value == "NIFI":
            task_pg = nifi_ops.instantiateTask(
                task, application.internalId.value,
                arguments)
            ngsi_ld_ops.appendInternalId(ngsi, task.id, task_pg.id)
        elif application.applicationType.value == "FLINK":
            job = flink_ops.submitStreamJob(
                task, flink,
                application.internalId.value,
                arguments)
            ngsi_ld_ops.appendInternalId(ngsi, task.id, job["jobid"])
        ngsi_ld_ops.stateToRunning(
            ngsi, task.id,
            {"value": "SUCCESS! Task started successfully."})
    elif task.action.value == "END":
        logger.info(
            "Deleting '{0}'...".format(task.id))
        if application.applicationType.value == "NIFI":
            nifi_ops.deleteTask(task)
        elif application.applicationType.value == "FLINK":
            flink_ops.deleteTask(task, flink)
        ngsi_ld_ops.stateToCleaned(
            ngsi, task.id,
            {"value": "SUCCESS! Task deleted successfully."})
        logger.info(
            "Deleting the '{0}' entity...".format(task.id))
        ngsi.deleteEntity(task.id)
    else:
        error_msg = "Unknown %s action" % task.action.value
        logger.error(error_msg)
        ngsi_ld_ops.stateToFailed(
            ngsi, task.id,
            {"value": error_msg})
