
from semantic_tools.clients.flink_api_rest import FlinkClient
from semantic_tools.clients.ngsi_ld import NGSILDClient
from semantic_tools.models.application import Application, Task
from weaver.flink import utils as flink_ops
from weaver.nifi import utils as nifi_ops
from weaver.ngsi_ld import utils as ngsi_ld_ops
from weaver.applications import application_configs


import logging

logger = logging.getLogger(__name__)


def process_task(task: Task, ngsi: NGSILDClient, flink: FlinkClient):
    """
    Process Task entity
    """
    application_entity = ngsi.retrieveEntityById(task.hasApplication.object)
    application = Application.parse_obj(application_entity)
    ngsi_ld_ops.appendState(ngsi, task.id, "Configuring task...")
    if task.action.value == "START":
        logger.info(
            "Instantiating new '{0}'...".format(task.id))
        # Config task by combining user and context arguments
        # The generation of arguments should be performed
        # by an intermidiate microservice
        # Weaver should only receive the final list of arguments
        # and configure the task with them
        user_arguments = task.arguments.value
        context_arguments = application_configs[
            application.name.value](task, ngsi)
        arguments = {**user_arguments, **context_arguments}
        if application.applicationType.value == "NIFI":
            nifi_ops.instantiateTask(
                task, application.name.value, arguments)
        elif application.applicationType.value == "FLINK":
            flink_ops.submitStreamJob(task, ngsi, flink)
        ngsi_ld_ops.stateToRunning(
            ngsi, task.id,
            {"value": "SUCCESS! Task started successfully."})
    elif task.action.value == "END":
        logger.info(
            "Deleting '{0}'...".format(task.id))
        if application.applicationType.value == "NIFI":
            nifi_ops.deleteTask(task)
        elif application.applicationType.value == "FLINK":
            pass
        ngsi_ld_ops.stateToCleaned(
            ngsi, task.id,
            {"value": "SUCCESS! Task deleted successfully."})
        logger.info(
            "Deleting the '{0}' entity...".format(task.id))
        ngsi.deleteEntity(task.id)
