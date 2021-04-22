from enum import Enum
from semantic_tools.clients.ngsi_ld import NGSILDClient
from semantic_tools.models.metric import State
from semantic_tools.models.ngsi_ld.subscription import Subscription

import logging
import time

logger = logging.getLogger(__name__)


class SubscriptionType(Enum):
    MetricProcessor = "urn:ngsi-ld:Subscription:MetricProcessor:weaver-subs"
    MetricSource = "urn:ngsi-ld:Subscription:MetricSource:weaver-subs"
    MetricTarget = "urn:ngsi-ld:Subscription:MetricTarget:weaver-subs"
    StreamApplication = "urn:ngsi-ld:Subscription:StreamApplication:weaver-subs"
    TelemetrySource = "urn:ngsi-ld:Subscription:TelemetrySource:weaver-subs"
    Prometheus = "urn:ngsi-ld:Subscription:Prometheus:weaver-subs"
    Device = "urn:ngsi-ld:Subscription:Device:weaver-subs"
    Endpoint = "urn:ngsi-ld:Subscription:Endpoint:weaver-subs"


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


def _subscribeToEntity(ngsi: NGSILDClient,
                       subscriptionType: SubscriptionType,
                       uri: str, attributes: list = None):
    """
    Base method to create subscription
    for one of the available subscription types
    """
    try:
        logger.info(
            "Subscribing weaver to %s entities ..."
            % subscriptionType.name
        )
        ngsi.retrieveSubscription(
            subscriptionType.value)
    except Exception:
        subscription = Subscription(
            id=subscriptionType.value,
            entities=[
                {
                    "type": subscriptionType.name
                }
            ],
            notification={
                "endpoint": {
                    "uri": uri
                }
            }
        )
        if attributes:
            subscription.watchedAttributes = attributes
        ngsi.createSubscription((subscription.dict(exclude_none=True)))
    else:
        logger.info(
            "Weaver is already subscribed to %s entities!"
            % subscriptionType.name
        )


def appendState(ngsi: NGSILDClient, entityId: str, stateInfo_value: str):
    """
    It appends 'state' property to an entity.
    This property is set to 'BUILDING' by default.
    """
    state = {
        "state": State(
            value="BUILDING",
            stateInfo={
                "value": stateInfo_value
            }
        ).dict(exclude_none=True)
    }
    ngsi.appendEntityAttrs(entityId, state)


def stateToBuilding(ngsi: NGSILDClient, entityId: str, stateInfo_dict: dict):
    """
    Update state of a given agent entity to BUILDING.
    """
    state = {
        "state": State(
            value="BUILDING",
            stateInfo=stateInfo_dict
        ).dict(exclude_none=True)
    }
    ngsi.updateEntityAttrs(entityId, state)


def stateToFailed(ngsi: NGSILDClient, entityId: str, stateInfo_dict: dict):
    """
    Update state of a given agent entity to FAILED.
    """
    state = {
        "state": State(
            value="FAILED",
            stateInfo=stateInfo_dict
        ).dict(exclude_none=True)
    }
    ngsi.updateEntityAttrs(entityId, state)


def stateToRunning(ngsi: NGSILDClient, entityId: str, stateInfo_dict: dict):
    """
    Update state of a given agent entity to RUNNING.
    """
    state = {
        "state": State(
            value="RUNNING",
            stateInfo=stateInfo_dict
        ).dict(exclude_none=True)
    }
    ngsi.updateEntityAttrs(entityId, state)


def stateToStopped(ngsi: NGSILDClient, entityId: str, stateInfo_dict: dict):
    """
    Update state of a given agent entity to STOPPED.
    """
    state = {
        "state": State(
            value="STOPPED",
            stateInfo=stateInfo_dict
        ).dict(exclude_none=True)
    }
    ngsi.updateEntityAttrs(entityId, state)


def stateToCleaned(ngsi: NGSILDClient, entityId: str, stateInfo_dict: dict):
    """
    Update state of a given agent entity to CLEANED.
    """
    state = {
        "state": State(
            value="CLEANED",
            stateInfo=stateInfo_dict
        ).dict(exclude_none=True)
    }
    ngsi.updateEntityAttrs(entityId, state)


def stateToUploaded(ngsi: NGSILDClient, entityId: str, stateInfo_dict: dict):
    """
    Update state of a StreamApplication entity to UPLOADED.
    """
    state = {
        "state": State(
            value="UPLOADED",
            stateInfo=stateInfo_dict
        ).dict(exclude_none=True)
    }
    ngsi.updateEntityAttrs(entityId, state)


def stateToEnabled(ngsi: NGSILDClient, entityId: str, stateInfo_dict: dict):
    """
    Update state of Endpoint or data source entity to ENABLED.
    """
    state = {
        "state": State(
            value="ENABLED",
            stateInfo=stateInfo_dict
        ).dict(exclude_none=True)
    }
    ngsi.updateEntityAttrs(entityId, state)


def stateToDisabled(ngsi: NGSILDClient, entityId: str, stateInfo_dict: dict):
    """
    Update state of Endpoint or data source entity to DISABLED.
    """
    state = {
        "state": State(
            value="DISABLED",
            stateInfo=stateInfo_dict
        ).dict(exclude_none=True)
    }
    ngsi.updateEntityAttrs(entityId, state)


def subscribeMetricSource(ngsi: NGSILDClient, uri: str):
    """
    Create subscription for MetricSource entity.
    """
    _subscribeToEntity(ngsi, SubscriptionType.MetricSource,
                       uri, "action")


def subscribeMetricTarget(ngsi: NGSILDClient, uri: str):
    """
    Create subscription for MetricTarget entity.
    """
    _subscribeToEntity(ngsi, SubscriptionType.MetricTarget,
                       uri, "action")


def subscribeMetricProcessor(ngsi: NGSILDClient, uri: str):
    """
    Create subscription for MetricProcessor entity.
    """
    _subscribeToEntity(ngsi, SubscriptionType.MetricProcessor,
                       uri, "action")


def subscribeStreamApplication(ngsi: NGSILDClient, uri: str):
    """
    Create subscription for StreamApplication entity.
    """
    _subscribeToEntity(ngsi, SubscriptionType.StreamApplication,
                       uri, "action")


def subscribeTelemetrySource(ngsi: NGSILDClient, uri: str):
    """
    Create subscription for TelemetrySource entity.
    """
    _subscribeToEntity(ngsi, SubscriptionType.TelemetrySource,
                       uri, "action")


def subscribePrometheus(ngsi: NGSILDClient, uri: str):
    """
    Create subscription for Prometheus entity.
    """
    _subscribeToEntity(ngsi, SubscriptionType.Prometheus,
                       uri, "action")


def subscribeDevice(ngsi: NGSILDClient, uri: str):
    """
    Create subscription for Device entity.
    """
    _subscribeToEntity(ngsi, SubscriptionType.Device,
                       uri, "action")


def subscribeEndpoint(ngsi: NGSILDClient, uri: str):
    """
    Create subscription for Endpoint entity.
    """
    _subscribeToEntity(ngsi, SubscriptionType.Endpoint,
                       uri, "action")
