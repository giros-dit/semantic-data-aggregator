import logging

from semantic_tools.flink.client import FlinkClient
from semantic_tools.models.application import Task
from semantic_tools.ngsi_ld.client import NGSILDClient
from semantic_tools.nifi.client import NiFiClient

from weaver.applications import config_flink_jobs, nifi_application_configs

logger = logging.getLogger(__name__)


def process_task(task: Task, flink: FlinkClient,
                 nifi: NiFiClient, ngsi_ld: NGSILDClient):
    """
    Process Task entity
    """
    application = ngsi_ld.get_application_from_task(task)
    logger.info("Processing task %s with application %s" % (
        task.id, application.name.value))
    ngsi_ld.append_state(task, "Configuring task...")
    if task.action.value == "START":
        logger.info(
            "Instantiating new '{0}'...".format(task.id))
        # TODO: The generation of arguments should be performed
        # by an intermidiate microservice
        # Weaver should only receive the final list of arguments
        # and configure the task with them
        if application.applicationType.value == "NIFI":
            # Renew access token for NiFi API
            nifi.login()
            arguments = nifi_application_configs[
                application.name.value](task, ngsi_ld)
            #
            # [S-96] Hack to configure MetricTargetExporter
            # using NiFi parameter context. Migrate in the future.
            #
            if application.name.value == "MetricTargetExporter":
                task_pg = nifi.deploy_flow_from_task(
                    task, application.internalId.value,
                    arguments)
                nifi.set_parameter_context(task_pg, arguments)
                nifi.start_flow_from_task(task)
            else:
                task_pg = nifi.instantiate_flow_from_task(
                    task, application.internalId.value,
                    arguments)
            ngsi_ld.append_internal_id(task, task_pg.id)
        elif application.applicationType.value == "FLINK":
            arguments = config_flink_jobs(task, ngsi_ld)
            job = flink.instantiate_job_from_task(
                task, application.internalId.value,
                arguments)
            ngsi_ld.append_internal_id(task, job["jobid"])
        ngsi_ld.state_to_running(
            task,
            {"value": "SUCCESS! Task started successfully."})
    elif task.action.value == "END":
        logger.info(
            "Deleting '{0}'...".format(task.id))
        if application.applicationType.value == "NIFI":
            # Renew access token for NiFi API
            nifi.login()
            nifi.delete_flow_from_task(task)
        elif application.applicationType.value == "FLINK":
            flink.delete_job_from_task(task)
        ngsi_ld.state_to_cleaned(
            task,
            {"value": "SUCCESS! Task deleted successfully."})
        logger.info(
            "Deleting the '{0}' entity...".format(task.id))
        ngsi_ld.delete_entity(task)
    else:
        error_msg = "Unknown %s action" % task.action.value
        logger.error(error_msg)
        ngsi_ld.state_to_failed(task, {"value": error_msg})
