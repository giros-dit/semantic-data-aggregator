from enum import Enum
from semantic_tools.clients.ngsi_ld import NGSILDClient
from semantic_tools.models.metric import ModeResult

from semantic_tools.models.ngsi_ld.subscription import Subscription

import logging
import time

logger = logging.getLogger(__name__)


class SubscriptionType(Enum):
    MetricProcessor = "urn:ngsi-ld:Subscription:MetricProcessor"
    MetricSource = "urn:ngsi-ld:Subscription:MetricSource"
    MetricTarget = "urn:ngsi-ld:Subscription:MetricTarget"
    StreamApplication = "urn:ngsi-ld:Subscription:StreamApplication"


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


def appendModeResult(ngsi: NGSILDClient, entityId: str):
    """
    Appends 'modeResult' property to an entity.
    This property is set to 'IN_PROGRESS' by default.
    """
    result = {
        "modeResult": ModeResult(
            value="IN_PROGRESS",
            modeInfo={
                "value": ""
            }
        ).dict(exclude_none=True)
    }
    ngsi.appendEntityAttrs(entityId, result)


def check_scorpio_status(ngsi: NGSILDClient):
    """
    Infinite loop that checks every 30 seconds
    until Scorpio REST API becomes available
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


def stageToInProgress(ngsi: NGSILDClient, entityId: str):
    """
    Update modeResult of a given stage to IN_PROGRESS
    """
    result = {
        "modeResult": ModeResult(
            value="IN_PROGRESS"
        ).dict(exclude_none=True)
    }
    ngsi.updateEntityAttrs(entityId, result)


def stageToFailed(ngsi: NGSILDClient, entityId: str):
    """
    Update modeResult of a given stage to FAILED
    """
    result = {
        "modeResult": ModeResult(
            value="FAILED",
            modeInfo={"value": "ERROR. Add Python traceback here."}
        ).dict(exclude_none=True)
    }
    ngsi.updateEntityAttrs(entityId, result)


def stageToSuccessful(ngsi: NGSILDClient, entityId: str):
    """
    Update modeResult of a given stage to SUCCESSFUL
    """
    result = {
        "modeResult": ModeResult(
            value="SUCCESSFUL"
        ).dict(exclude_none=True)
    }
    ngsi.updateEntityAttrs(entityId, result)


def subscribeMetricProcessor(ngsi: NGSILDClient, uri: str):
    """
    Create subscription for MetricProcessor entity
    """
    _subscribeToEntity(ngsi, SubscriptionType.MetricProcessor,
                       uri, "stageMode")


def subscribeMetricSource(ngsi: NGSILDClient, uri: str):
    """
    Create subscription for MetricSource entity
    """
    _subscribeToEntity(ngsi, SubscriptionType.MetricSource,
                       uri, "stageMode"),


def subscribeMetricTarget(ngsi: NGSILDClient, uri: str):
    """
    Create subscription for MetricTarget entity
    """
    _subscribeToEntity(ngsi, SubscriptionType.MetricTarget,
                       uri, "stageMode")


def subscribeStreamApplication(ngsi: NGSILDClient, uri: str, attribute: str):
    """
    Create subscription for StreamApplication entity
    """
    _subscribeToEntity(ngsi, SubscriptionType.StreamApplication,
                       uri, attribute)
