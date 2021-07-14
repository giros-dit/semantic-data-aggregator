from nipyapi.nifi.models.controller_service_entity import (
    ControllerServiceEntity
)
from nipyapi.nifi.models.process_group_entity import ProcessGroupEntity
from nipyapi.nifi.models.process_group_flow_entity import (
    ProcessGroupFlowEntity
)
from nipyapi.nifi.models.template_entity import TemplateEntity
from semantic_tools.models.application import Task
from urllib3.exceptions import MaxRetryError

import logging
import nipyapi
import time

logger = logging.getLogger(__name__)


class NiFiClient(object):
    """
    Class encapsulating the main operations with Apache NiFi.
    """

    def __init__(self, url: str = "http://nifi:8080/nifi-api"):
        # Init NiFi REST API Client
        nipyapi.config.nifi_config.host = url

    def check_nifi_status(self):
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
            logger.info("Successfully connected to NiFi REST API!")
            break

    def delete_flow_from_task(self, task: Task):
        """
        Delete Task flow.
        """
        # Stop process group
        task_pg = self.stop_flow_from_task(task)
        # Disable controller services (if any)
        controllers = nipyapi.canvas.list_all_controllers(
            task_pg.id, False)
        if controllers:
            registry_controller = None
            for controller in controllers:
                # Registry cannot be disabled as it has dependants
                if "ConfluentSchemaRegistry" == controller.component.name:
                    registry_controller = controller
                    continue
                if controller.status.run_status == 'ENABLED':
                    logger.debug("Disabling controller %s ..."
                                 % controller.component.name)
                    nipyapi.canvas.schedule_controller(
                        controller, False, True)
            # Disable registry controller
            logger.debug("Disabling controller %s ..."
                         % registry_controller.component.name)
            nipyapi.canvas.schedule_controller(
                registry_controller, False, True)
        # Delete Task PG
        nipyapi.canvas.delete_process_group(task_pg, True)
        logger.debug("'{0}' flow deleted in NiFi.".format(task.id))

    def deploy_flow_from_task(self, task: Task, application_id: str,
                              args: dict) -> ProcessGroupEntity:
        """
        Deploys a NiFi template
        from a passed Task NGSI-LD entity.
        """
        # We assume last string is an integer value
        source_id_number = int(task.id.split(":")[-1])
        # Get root PG
        root_pg = nipyapi.canvas.get_process_group("root")
        # Y multiply ID last integer by 200, X fixed to -250 for MS PGs
        task_pg = nipyapi.canvas.create_process_group(
                        root_pg,
                        task.id,
                        (-250, 200*source_id_number)
        )
        logger.debug("Deploy with arguments %s" % args)
        # Set variables for Task PG
        for argument, value in args.items():
            nipyapi.canvas.update_variable_registry(
                task_pg, [(argument, value)])

        # Deploy Task template
        task_template = nipyapi.templates.get_template(application_id, "id")
        task_pg_flow = nipyapi.templates.deploy_template(
                                            task_pg.id,
                                            task_template.id,
                                            -250, 200)
        # Enable controller services (if any)
        controllers = nipyapi.canvas.list_all_controllers(task_pg.id, False)
        if controllers:
            # Enable controller services
            # Start with the registry controller
            logger.debug("Enabling controller ConfluentSchemaRegistry...")
            registry_controller = self.get_controller_service(
                task_pg, "ConfluentSchemaRegistry")
            nipyapi.canvas.schedule_controller(registry_controller, True)
            for controller in controllers:
                if controller.id == registry_controller.id:
                    continue
                logger.debug(
                    "Enabling controller %s ..."
                    % controller.component.name)
                if controller.status.run_status != 'ENABLED':
                    nipyapi.canvas.schedule_controller(controller, True)
        # Hack to support scheduling for a given processor
        if "interval" in args:
            self.set_polling_interval(task_pg_flow, args["interval"])

        logger.debug("'{0}' flow deployed in NiFi.".format(task.id))
        return task_pg

    def instantiate_flow_from_task(self, task: Task, application_id: str,
                                   args: dict) -> ProcessGroupEntity:
        """
        Deploys and starts NiFi template given
        a Task entity.
        """
        task_pg = self.deploy_flow_from_task(task, application_id, args)
        # Schedule PG
        nipyapi.canvas.schedule_process_group(task_pg.id, True)
        logger.debug(
            "'{0}' flow scheduled in NiFi.".format(task.id))
        return task_pg

    def get_controller_service(self, pg: ProcessGroupEntity,
                               name: str) -> ControllerServiceEntity:
        """
        Get Controller Service by name within a given ProcessGroup.
        """
        controllers = nipyapi.canvas.list_all_controllers(pg.id, False)
        for controller in controllers:
            if controller.component.name == name:
                return controller

    def get_pg_from_task(self, task: Task) -> ProcessGroupEntity:
        """
        Get NiFi flow (Process Group) from Task.
        """
        return nipyapi.canvas.get_process_group(task.id)

    def set_polling_interval(self, pg_flow: ProcessGroupFlowEntity,
                             interval: str):
        logger.debug("Set polling interval to %s milliseconds" % interval)
        # Retrieve Polling processor
        # so far rely on "Polling" string
        http_ps = None
        for ps in pg_flow.flow.processors:
            if "Polling" in ps.status.name:
                logger.debug("Updating %s processor" % ps.status.name)
                http_ps = ps
                break
        # Enforce interval unit to miliseconds
        interval_unit = "ms"
        nipyapi.canvas.update_processor(
            http_ps,
            nipyapi.nifi.ProcessorConfigDTO(
                scheduling_period='{0}{1}'.format(interval,
                                                  interval_unit)))

    def stop_flow_from_task(self, task: Task) -> ProcessGroupEntity:
        """
        Stop NiFi flow (Process Group) from Task.
        """
        task_pg = nipyapi.canvas.get_process_group(task.id)
        nipyapi.canvas.schedule_process_group(task_pg.id, False)
        return task_pg

    def upload_template(self, template_path: str) -> TemplateEntity:
        """
        Uploads template to root process group
        """
        # Get root PG
        root_pg = nipyapi.canvas.get_process_group("root")
        template = nipyapi.templates.upload_template(
            root_pg.id, template_path)
        return template
