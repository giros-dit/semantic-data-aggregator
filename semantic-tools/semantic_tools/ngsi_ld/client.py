import logging
import time
import uuid
from enum import Enum
from typing import List, Optional

from pydantic import parse_obj_as
from semantic_tools.models.application import Application, Task
from semantic_tools.models.common import Endpoint, Infrastructure, State
from semantic_tools.models.metric import (Metric, MetricFamily, Prometheus,
                                          PrometheusExporter)
from semantic_tools.models.ngsi_ld.entity import Entity, Property
from semantic_tools.models.ngsi_ld.subscription import Subscription
from semantic_tools.models.stream import KafkaBroker, KafkaTopic
from semantic_tools.models.telemetry import Device, YANGModule
from semantic_tools.ngsi_ld.api import NGSILDAPI

logger = logging.getLogger(__name__)


class WeaverSubscriptionType(Enum):
    Device = "urn:ngsi-ld:Subscription:Device:weaver-subs"
    Endpoint = "urn:ngsi-ld:Subscription:Endpoint:weaver-subs"
    Prometheus = "urn:ngsi-ld:Subscription:Prometheus:weaver-subs"
    Task = "urn:ngsi-ld:Subscription:Task:weaver-subs"


class NGSILDClient(object):
    """
    Class encapsulating the main operations with NGSI-LD.
    """

    def __init__(self, url: str = "http://scorpio:9090",
                 headers: dict = {"Accept": "application/json"},
                 context: str = "http://context-catalog:8080/context.jsonld",
                 debug: bool = False):
        # Init NGSI-LD REST API Client
        self.api = NGSILDAPI(url, headers=headers,
                             context=context, debug=debug)

    def check_orion_status(self):
        """
        Infinite loop that checks every 30 seconds
        until Orion REST API becomes available.
        """
        logger.info("Checking Orion-LD REST API status ...")
        while True:
            if self.api.checkOrionHealth():
                logger.info(
                    "Successfully connected to Orion-LD REST API!")
                break
            else:
                logger.warning("Could not connect to Orion-LD REST API. "
                               "Retrying in 30 seconds ...")
                time.sleep(30)
                continue

    def check_scorpio_status(self):
        """
        Infinite loop that checks every 30 seconds
        until Scorpio REST API becomes available.
        """
        logger.info("Checking Scorpio REST API status ...")
        while True:
            if self.api.checkScorpioHealth():
                logger.info(
                    "Successfully connected to Scorpio REST API!")
                break
            else:
                logger.warning("Could not connect to Scorpio REST API. "
                               "Retrying in 30 seconds ...")
                time.sleep(30)
                continue

    def create_application(
            self,
            application_id: str,
            internal_id: str,
            application_type: str,
            name: str,
            uri: str,
            description: Optional[str] = None) -> Application:
        """
        Create Application entity based on the provided configuration.
        """
        logger.debug("Creating Application entity id '%s'" % id)
        application = Application(
            id=application_id,
            name={"value": name},
            internalId={"value": internal_id},
            applicationType={"value": application_type},
            uri={"value": uri}
        )
        if description:
            application.description = {"value": description}

        self.api.createEntity(application.dict(exclude_none=True))
        return application

    def delete_entity(self, entity: Entity):
        """
        Delete Entity wrapper
        """
        logger.debug("Deleting entity with id '%s'." % entity.id)
        self.api.deleteEntity(entity.id)

    def delete_entity_by_id(self, id: str):
        """
        Delete Entity by id wrapper
        """
        logger.debug("Deleting entity with id '%s'." % id)
        self.api.deleteEntity(id)

    def get_application(self, id: str) -> Application:
        """
        Get Application entity for a given ID
        """
        logger.debug("Retrieving Application entity by id '%s'" % id)
        application_entity = self.api.retrieveEntityById(id)
        application = Application.parse_obj(application_entity)
        return application

    def get_applications_by_name(self, name: str) -> List[Application]:
        """
        Finds Applications based on the provided name
        """
        logger.debug("Retrieving Application entity by name '%s'" % name)
        application_entities = self.api.queryEntities(
            type="Application",
            q='name=="{0}"'.format(name))
        return parse_obj_as(List[Application], application_entities)

    def get_application_from_task(self, task: Task) -> Application:
        """
        Get Application entity from a given Task
        """
        logger.debug(
            "Retrieving Application entity for task '%s'." % task.id)
        application_entity = self.api.retrieveEntityById(
            task.hasApplication.object)
        return Application.parse_obj(application_entity)

    def get_device(self, id: str) -> Device:
        """
        Get Device entity for a given ID
        """
        logger.debug("Retrieving Device entity by id '%s'" % id)
        device_entity = self.api.retrieveEntityById(id)
        device = Device.parse_obj(device_entity)
        return device

    def get_device_from_module(self, module: YANGModule) -> Device:
        """
        Get Device entity from a given YANGModule
        """
        logger.debug(
            "Retrieving Device entity for YANG module '%s'." % module.id)
        device_entity = self.api.retrieveEntityById(
            module.hasDevice.object)
        return Device.parse_obj(device_entity)

    def get_endpoint_from_infrastructure(
            self, infra: Infrastructure) -> Endpoint:
        """
        Get Endpoint entity from a given Asset
        """
        logger.debug(
            "Retrieving Endpoint entity for infrastructure '%s'."
            % infra.id)
        infra_entity = self.api.retrieveEntityById(
            infra.hasEndpoint.object)
        return Endpoint.parse_obj(infra_entity)

    def get_kafka_broker(self, id: str) -> KafkaBroker:
        """
        Get KafkaBroker entity
        """
        logger.debug(
            "Retrieving KafkaBroker entity with id '%s'." % id)
        kafka_broker_entity = self.api.retrieveEntityById(id)
        return KafkaBroker.parse_obj(kafka_broker_entity)

    def get_kafka_broker_from_topic(self, topic: KafkaTopic) -> KafkaBroker:
        """
        Get KafkaBroker entity from a given KafkaTopic
        """
        logger.debug(
            "Retrieving KafkaBroker entity from KafkaTopic with id '%s'."
            % topic.id)
        kafka_broker_entity = self.api.retrieveEntityById(
            topic.hasKafkaBroker.object)
        return KafkaBroker.parse_obj(kafka_broker_entity)

    def create_kafka_topic(
            self,
            broker: KafkaBroker,
            id: str,
            name: str,
            description: Optional[str] = None) -> KafkaTopic:
        """
        Create KafkaTopic entity for a given KafkaBroker
        """
        logger.debug(
            "Creating KafkaTopic entity with id '%s' for KafkaBroker ''%s." % (
                id, broker.id))
        kafka_topic = KafkaTopic(
            id=id,
            name={"value": name},
            hasKafkaBroker={"object": broker.id}
        )
        if description:
            kafka_topic.description = {"value": description}

        self.api.createEntity(kafka_topic.dict(exclude_none=True))
        return kafka_topic

    def get_kafka_topic(self, id: str) -> KafkaTopic:
        """
        Get KafkaTopic entity
        """
        logger.debug(
            "Retrieving KafkaTopic entity with id '%s'." % id)
        kafka_topic_entity = self.api.retrieveEntityById(id)
        return KafkaTopic.parse_obj(kafka_topic_entity)

    def get_kafka_topics_by_name(self, name: str) -> List[KafkaTopic]:
        """
        Get KafkaTopic entities from a given name
        """
        logger.debug(
            "Retrieving KafkaTopic entities with name '%s'." % name)
        kafka_topic_entities = self.api.queryEntities(
            type="KafkaTopic",
            q='name=="{0}"'.format(name))
        return parse_obj_as(List[KafkaTopic], kafka_topic_entities)

    def get_metric(self, id: str) -> Metric:
        """
        Get Metric entity for a given ID
        """
        logger.debug("Retrieving Metric entity by id '%s'" % id)
        metric_entity = self.api.retrieveEntityById(id)
        metric = Metric.parse_obj(metric_entity)
        return metric

    def get_metric_family(self, id: str) -> MetricFamily:
        """
        Get MetricFamily entity for a given ID
        """
        logger.debug(
            "Retrieving MetricFamily entity with id '%s'."
            % id)
        mf_entity = self.api.retrieveEntityById(id)
        return MetricFamily.parse_obj(mf_entity)

    def get_prometheus(self, id: str) -> Prometheus:
        """
        Get Prometheus entity for a given ID
        """
        logger.debug("Retrieving Prometheus entity by id '%s'" % id)
        prometheus_entity = self.api.retrieveEntityById(id)
        prometheus = Prometheus.parse_obj(prometheus_entity)
        return prometheus

    def get_prometheus_from_metric(self, metric: Metric) -> Prometheus:
        """
        Get Prometheus entity from a given Metric
        """
        logger.debug(
            "Retrieving Prometheus entity from Metric with id '%s'."
            % metric.id)
        prometheus_entity = self.api.retrieveEntityById(
            metric.hasPrometheus.object)
        return Prometheus.parse_obj(prometheus_entity)

    def get_prometheus_exporter(self, id: str) -> PrometheusExporter:
        """
        Get PrometheusExporter entity for a given ID
        """
        logger.debug("Retrieving PrometheusExporter entity by id '%s'" % id)
        exporter_entity = self.api.retrieveEntityById(id)
        exporter = PrometheusExporter.parse_obj(exporter_entity)
        return exporter

    def get_tasks_by_application_name(self, name: str) -> List[Task]:
        """
        Get a list of Task entities that run an Application
        that have the specified name.

        Method assumes that Applications entities have unique names.
        """
        logger.debug(
            "Retrieving Task entities with Application name '%s'" % name)
        applications = self.get_applications_by_name(name)
        if applications:
            application = applications[0]  # Unique name assumption
            task_entities = self.api.queryEntities(
                type="Task",
                q='hasApplication=="{0}"'.format(application.id))
            return parse_obj_as(List[Task], task_entities)

    def get_tasks_by_input_kafka_topic(self, topic: KafkaTopic) -> Task:
        """
        Get a list of Task entities with a given KafkaTopic as input
        """
        logger.debug(
            "Retrieving Task entities with input KafkaTopic '%s'" % topic.id)
        task_entities = self.api.queryEntities(
            type="Task",
            q='hasInput=="{0}"'.format(topic.id))
        return parse_obj_as(List[Task], task_entities)

    def get_tasks_by_output_kafka_topic(self, topic: KafkaTopic) -> Task:
        """
        Get a list of Task entities with a given KafkaTopic as output
        """
        logger.debug(
            "Retrieving Task entities with output KafkaTopic '%s'" % topic.id)
        task_entities = self.api.queryEntities(
            type="Task",
            q='hasOutput=="{0}"'.format(topic.id))
        return parse_obj_as(List[Task], task_entities)

    def get_yang_module(self, id: str) -> YANGModule:
        """
        Get YANGModule entity for a given ID
        """
        logger.debug("Retrieving YANG module entity by id '%s'" % id)
        yang_module_entity = self.api.retrieveEntityById(id)
        yang_module = YANGModule.parse_obj(yang_module_entity)
        return yang_module

    def subscribe_to_entity(self, entity_type: str,
                            endpoint: str,
                            attributes: list = None,
                            subscription_id: str = None):
        """
        Base method to create subscription for the specified entity
        """
        logger.debug("Subscribing to '%s' entity type ..."
                     % entity_type)
        if subscription_id:
            try:
                self.api.retrieveSubscription(
                    subscription_id)
            except Exception:
                subscription = Subscription(
                    id=subscription_id,
                    entities=[
                        {
                            "type": entity_type
                        }
                    ],
                    notification={
                        "endpoint": {
                            "uri": endpoint
                        }
                    }
                )
                if attributes:
                    subscription.watchedAttributes = attributes
                self.api.createSubscription(
                    (subscription.dict(exclude_none=True)))
            else:
                logger.info(
                    "Subscription %s already configured."
                    % subscription_id
                )
        else:
            subscription = Subscription(
                entities=[
                    {
                        "type": entity_type
                    }
                ],
                notification={
                    "endpoint": {
                        "uri": endpoint
                    }
                }
            )
            self.api.createSubscription(
                (subscription.dict(exclude_none=True)))

    def append_internal_id(self, entity: Entity,
                           internal_id: str):
        """
        It appends 'state' attribute to an entity.
        This attribute is set to 'BUILDING' by default.
        """
        attribute = {
            "internalId": Property(
                value=internal_id,
            ).dict(exclude_none=True)
        }
        self.api.appendEntityAttrs(entity.id, attribute)

    def append_state(self, entity: Entity, state_info: str):
        """
        It appends 'state' attribute to an entity.
        This attribute is set to 'BUILDING' by default.
        """
        attribute = {
            "state": State(
                value="BUILDING",
                stateInfo={
                    "value": state_info
                }
            ).dict(exclude_none=True)
        }
        self.api.appendEntityAttrs(entity.id, attribute)

    def state_to_building(self, entity: Entity,
                          state_info: dict):
        """
        Update state of a given agent entity to BUILDING.
        """
        attribute = {
            "state": State(
                value="BUILDING",
                stateInfo=state_info
            ).dict(exclude_none=True)
        }
        self.api.updateEntityAttrs(entity.id, attribute)

    def state_to_cleaned(self, entity: Entity,
                         state_info: dict):
        """
        Update state of a given agent entity to CLEANED.
        """
        attribute = {
            "state": State(
                value="CLEANED",
                stateInfo=state_info
            ).dict(exclude_none=True)
        }
        self.api.updateEntityAttrs(entity.id, attribute)

    def state_to_failed(self, entity: Entity,
                        state_info: dict):
        """
        Update state of a given agent entity to FAILED.
        """
        attribute = {
            "state": State(
                value="FAILED",
                stateInfo=state_info
            ).dict(exclude_none=True)
        }
        self.api.updateEntityAttrs(entity.id, attribute)

    def state_to_running(self, entity: Entity,
                         state_info: dict):
        """
        Update state of a given agent entity to RUNNING.
        """
        attribute = {
            "state": State(
                value="RUNNING",
                stateInfo=state_info
            ).dict(exclude_none=True)
        }
        self.api.updateEntityAttrs(entity.id, attribute)

    def state_to_stopped(self, entity: Entity,
                         state_info: dict):
        """
        Update state of a given agent entity to STOPPED.
        """
        attribute = {
            "state": State(
                value="STOPPED",
                stateInfo=state_info
            ).dict(exclude_none=True)
        }
        self.api.updateEntityAttrs(entity.id, attribute)

    def state_to_uploaded(self, entity: Entity,
                          state_info: dict):
        """
        Update state of a StreamApplication entity to UPLOADED.
        """
        attribute = {
            "state": State(
                value="UPLOADED",
                stateInfo=state_info
            ).dict(exclude_none=True)
        }
        self.api.updateEntityAttrs(entity.id, attribute)

    def state_to_enabled(self, entity: Entity,
                         state_info: dict):
        """
        Update state of Endpoint or data source entity to ENABLED.
        """
        attribute = {
            "state": State(
                value="ENABLED",
                stateInfo=state_info
            ).dict(exclude_none=True)
        }
        self.api.updateEntityAttrs(entity.id, attribute)

    def state_to_disabled(self, entity: Entity,
                          state_info: dict):
        """
        Update state of Endpoint or data source entity to DISABLED.
        """
        attribute = {
            "state": State(
                value="DISABLED",
                stateInfo=state_info
            ).dict(exclude_none=True)
        }
        self.api.updateEntityAttrs(entity.id, attribute)

    def subscribe_weaver_to_device(self, device: Device, uri: str):
        """
        Create subscription for Weaver to Device entity.
        """
        self.subscribe_to_entity(
            device, uri,
            "action", WeaverSubscriptionType.Device.value)

    def subscribe_weaver_to_endpoint(self, endpoint: Endpoint, uri: str):
        """
        Create subscription for Weaver to Endpoint entity.
        """
        self.subscribe_to_entity(
            endpoint, uri,
            "action", WeaverSubscriptionType.Endpoint.value)

    def subscribe_weaver_to_prometheus(self, prometheus: Prometheus, uri: str):
        """
        Create subscription for Weaver to Prometheus entity.
        """
        self.subscribe_to_entity(
            prometheus, uri,
            "action", WeaverSubscriptionType.Prometheus.value)

    def subscribe_weaver_to_task(self, uri: str):
        """
        Create subscription for Weaver to Task entity.
        """
        self.subscribe_to_entity(
            "Task", uri,
            "action", WeaverSubscriptionType.Task.value)
