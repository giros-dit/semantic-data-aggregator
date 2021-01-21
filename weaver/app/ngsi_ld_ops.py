from enum import Enum
from semantic_tools.clients.ngsi_ld import NGSILDClient
from semantic_tools.models.metric import MetricProcessor, MetricSource, MetricTarget
from semantic_tools.models.ngsi_ld.subscription import Subscription

import json
import logging

logger = logging.getLogger(__name__)

class SubscriptionType(Enum):
    MetricProcessor = "urn:ngsi-ld:Subscription:MetricProcessor"
    MetricSource = "urn:ngsi-ld:Subscription:MetricSource"
    MetricTarget = "urn:ngsi-ld:Subscription:MetricTarget"
    StreamApplication = "urn:ngsi-ld:Subscription:StreamApplication"

def _subscribeToEntity(ngsi: NGSILDClient,
                      subscriptionType: SubscriptionType,
                      uri: str):
    """
    Base method to create subscription
    for one of the available subscription types
    """
    try:
        logger.info(
            "Subscribing weaver to %s entities ..."
            % subscriptionType.name
        )
        response = ngsi.retrieveSubscription(
            subscriptionType.value)
    except:
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
        ngsi.createSubscription((subscription.dict(exclude_none=True)))
    else:
        logger.info(
            "Weaver is already subscribed to %s entities!"
            % subscriptionType.name
        )

def subscribeMetricProcessor(ngsi: NGSILDClient, uri: str):
    """
    Create subscription for MetricProcessor entity
    """
    _subscribeToEntity(ngsi, SubscriptionType.MetricProcessor, uri)

def subscribeMetricSource(ngsi: NGSILDClient, uri: str):
    """
    Create subscription for MetricSource entity
    """
    _subscribeToEntity(ngsi, SubscriptionType.MetricSource, uri)

def subscribeMetricTarget(ngsi: NGSILDClient, uri: str):
    """
    Create subscription for MetricTarget entity
    """
    _subscribeToEntity(ngsi, SubscriptionType.MetricTarget, uri)

def subscribeStreamApplication(ngsi: NGSILDClient, uri: str):
    """
    Create subscription for StreamApplication entity
    """
    _subscribeToEntity(ngsi, SubscriptionType.StreamApplication, uri)
