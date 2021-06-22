from enum import Enum
from semantic_tools.clients.ngsi_ld import NGSILDClient
from semantic_tools.models.common import State
from semantic_tools.models.ngsi_ld.subscription import Subscription

import logging
import time

logger = logging.getLogger(__name__)


class SubscriptionType(Enum):
    EVESource = "urn:ngsi-ld:Subscription:EVESource:experimenter-subs"
    MetricProcessor = "urn:ngsi-ld:Subscription:MetricProcessor:experimenter-subs"
    MetricSource = "urn:ngsi-ld:Subscription:MetricSource:experimenter-subs"
    MetricTarget = "urn:ngsi-ld:Subscription:MetricTarget:experimenter-subs"
    StreamApplication = "urn:ngsi-ld:Subscription:StreamApplication:experimenter-subs"
    TelemetrySource = "urn:ngsi-ld:Subscription:TelemetrySource:experimenter-subs"
    SOLogSource = "urn:ngsi-ld:Subscription:SOLogSource:experimenter-subs"
    Prometheus = "urn:ngsi-ld:Subscription:Prometheus:experimenter-subs"
    Device = "urn:ngsi-ld:Subscription:Device:experimenter-subs"
    Endpoint = "urn:ngsi-ld:Subscription:Endpoint:experimenter-subs"


def check_scorpio_status(ngsi: NGSILDClient):
    """
    Infinite loop that checks every 30 seconds
    until Scorpio REST API becomes available
    """
    logger.info("Checking Scorpio REST API status ...")
    while True:
        if ngsi.checkScorpioHealth():
            logger.info(
                "Experimenter successfully connected to Scorpio REST API!")
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
            "Subscribing experimenter to %s entities ..."
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
            "Experimenter is already subscribed to %s entities!"
            % subscriptionType.name
        )


def subscribeEVESource(ngsi: NGSILDClient, uri: str):
    """
    Create subscription for EVESource entity.
    """
    _subscribeToEntity(ngsi, SubscriptionType.EVESource,
                       uri, "state")


def subscribeMetricSource(ngsi: NGSILDClient, uri: str):
    """
    Create subscription for MetricSource entity
    """
    _subscribeToEntity(ngsi, SubscriptionType.MetricSource,
                       uri, "state")


def subscribeMetricTarget(ngsi: NGSILDClient, uri: str):
    """
    Create subscription for MetricTarget entity
    """
    _subscribeToEntity(ngsi, SubscriptionType.MetricTarget,
                       uri, "state")


def subscribeMetricProcessor(ngsi: NGSILDClient, uri: str):
    """
    Create subscription for MetricProcessor entity
    """
    _subscribeToEntity(ngsi, SubscriptionType.MetricProcessor,
                       uri, "state")


def subscribeStreamApplication(ngsi: NGSILDClient, uri: str):
    """
    Create subscription for StreamApplication entity
    """
    _subscribeToEntity(ngsi, SubscriptionType.StreamApplication,
                       uri, "state")


def subscribeTelemetrySource(ngsi: NGSILDClient, uri: str):
    """
    Create subscription for TelemetrySource entity
    """
    _subscribeToEntity(ngsi, SubscriptionType.TelemetrySource,
                       uri, "state")


def subscribeSOLogSource(ngsi: NGSILDClient, uri: str):
    """
    Create subscription for SOLogSource entity.
    """
    _subscribeToEntity(ngsi, SubscriptionType.SOLogSource,
                       uri, "state")


def subscribePrometheus(ngsi: NGSILDClient, uri: str):
    """
    Create subscription for Prometheus entity
    """
    _subscribeToEntity(ngsi, SubscriptionType.Prometheus,
                       uri, "state")


def subscribeDevice(ngsi: NGSILDClient, uri: str):
    """
    Create subscription for Device entity
    """
    _subscribeToEntity(ngsi, SubscriptionType.Device,
                       uri, "state")


def subscribeEndpoint(ngsi: NGSILDClient, uri: str):
    """
    Create subscription for Endpoint entity
    """
    _subscribeToEntity(ngsi, SubscriptionType.Endpoint,
                       uri, "state")
