from enum import Enum
from semantic_tools.models.common import Endpoint, Infrastructure, State
from semantic_tools.models.application import Application, Task
from semantic_tools.models.metric import Metric, Prometheus
from semantic_tools.models.stream import KafkaBroker, KafkaTopic
from semantic_tools.models.telemetry import Device, YANGModule
from semantic_tools.models.ngsi_ld.entity import Entity, Property
from semantic_tools.models.ngsi_ld.subscription import Subscription
from semantic_tools.ngsi_ld.api import NGSILDAPI

import logging
import time

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
                 context: str = "http://context-catalog:8080/context.jsonld"):
        # Init NGSI-LD REST API Client
        self.api = NGSILDAPI(url, headers=headers, context=context)

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
            if self.checkScorpioHealth():
                logger.info(
                    "Successfully connected to Scorpio REST API!")
                break
            else:
                logger.warning("Could not connect to Scorpio REST API. "
                               "Retrying in 30 seconds ...")
                time.sleep(30)
                continue

    def delete_entity(self, entity: Entity):
        """
        Delete Entity wrapper
        """
        logger.debug("Deleting entity with id '%s'." % entity.id)
        self.api.deleteEntity(entity.id)

    def get_application(self, id: str) -> Application:
        """
        Get Application entity for a given ID
        """
        logger.debug("Retrieving Application entity by id '%s'" % id)
        application_entity = self.api.retrieveEntityById(id)
        application = Application.parse_obj(application_entity)
        return application

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

    def get_kafka_topic(self, id: str) -> KafkaTopic:
        """
        Get KafkaTopic entity
        """
        logger.debug(
            "Retrieving KafkaTopic entity with id '%s'." % id)
        kafka_topic_entity = self.api.retrieveEntityById(id)
        return KafkaTopic.parse_obj(kafka_topic_entity)

    def get_metric(self, id: str) -> Metric:
        """
        Get Metric entity for a given ID
        """
        logger.debug("Retrieving Metric entity by id '%s'" % id)
        metric_entity = self.api.retrieveEntityById(id)
        metric = Metric.parse_obj(metric_entity)
        return metric

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
