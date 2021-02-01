from enum import Enum
from semantic_tools.clients.ngsi_ld import NGSILDClient
from semantic_tools.models.metric import (
    ModeResult,
    ModeResultType,
    MetricProcessor,
    MetricSource,
    MetricTarget
)
from semantic_tools.models.ngsi_ld.subscription import Subscription

import logging

logger = logging.getLogger(__name__)


class SubscriptionType(Enum):
    metricProcessor = "urn:ngsi-ld:Subscription:MetricProcessor"
    metricSource = "urn:ngsi-ld:Subscription:MetricSource"
    metricTarget = "urn:ngsi-ld:Subscription:MetricTarget"
    streamApplication = "urn:ngsi-ld:Subscription:StreamApplication"


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
            value="SUCCESSFUL",
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


def subscribeMetricProcessor(ngsi: NGSILDClient, uri: str, attribute: str):
    """
    Create subscription for MetricProcessor entity
    """
    _subscribeToEntity(ngsi, SubscriptionType.metricProcessor, uri, attribute)


def subscribeMetricSource(ngsi: NGSILDClient, uri: str):
    """
    Create subscription for MetricSource entity
    """
    _subscribeToEntity(ngsi, SubscriptionType.metricSource, uri)


def subscribeMetricTarget(ngsi: NGSILDClient, uri: str):
    """
    Create subscription for MetricTarget entity
    """
    _subscribeToEntity(ngsi, SubscriptionType.metricTarget, uri)


def subscribeStreamApplication(ngsi: NGSILDClient, uri: str, attribute: str):
    """
    Create subscription for StreamApplication entity
    """
    _subscribeToEntity(ngsi, SubscriptionType.streamApplication,
                       uri, attribute)
