from enum import Enum
from semantic_tools.models.common import State
from semantic_tools.models.ngsi_ld.subscription import Subscription
from semantic_tools.ngsi_ld.client import NGSILDClient


import logging
import time

logger = logging.getLogger(__name__)


class SubscriptionType(Enum):
    Device = "urn:ngsi-ld:Subscription:Device:weaver-subs"
    Endpoint = "urn:ngsi-ld:Subscription:Endpoint:weaver-subs"
    Prometheus = "urn:ngsi-ld:Subscription:Prometheus:weaver-subs"
    Task = "urn:ngsi-ld:Subscription:Task:weaver-subs"


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


def subscribePrometheus(ngsi: NGSILDClient, uri: str):
    """
    Create subscription for Prometheus entity.
    """
    _subscribeToEntity(ngsi, SubscriptionType.Prometheus,
                       uri, "action")


def subscribeTask(ngsi: NGSILDClient, uri: str):
    """
    Create subscription for Task entity.
    """
    _subscribeToEntity(ngsi, SubscriptionType.Task,
                       uri, "action")
