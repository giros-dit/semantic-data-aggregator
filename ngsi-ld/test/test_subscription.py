"""
    ETSI ISG CIM / NGSI-LD API

    This OAS file describes the NGSI-LD API defined by the ETSI ISG CIM group. This Cross-domain Context Information Management API allows to provide, consume and subscribe to context information in multiple scenarios and involving multiple stakeholders  # noqa: E501

    The version of the OpenAPI document: latest
    Contact: NGSI-LD@etsi.org
    Generated by: https://openapi-generator.tech
"""


import sys
import unittest

import openapi_client
from openapi_client.model.entity_info import EntityInfo
from openapi_client.model.geo_query import GeoQuery
from openapi_client.model.ld_context import LdContext
from openapi_client.model.name import Name
from openapi_client.model.notification_params import NotificationParams
from openapi_client.model.subscription_all_of import SubscriptionAllOf
from openapi_client.model.subscription_fragment import SubscriptionFragment
globals()['EntityInfo'] = EntityInfo
globals()['GeoQuery'] = GeoQuery
globals()['LdContext'] = LdContext
globals()['Name'] = Name
globals()['NotificationParams'] = NotificationParams
globals()['SubscriptionAllOf'] = SubscriptionAllOf
globals()['SubscriptionFragment'] = SubscriptionFragment
from openapi_client.model.subscription import Subscription


class TestSubscription(unittest.TestCase):
    """Subscription unit test stubs"""

    def setUp(self):
        pass

    def tearDown(self):
        pass

    def testSubscription(self):
        """Test Subscription"""
        # FIXME: construct object with mandatory attributes with example values
        # model = Subscription()  # noqa: E501
        pass


if __name__ == '__main__':
    unittest.main()