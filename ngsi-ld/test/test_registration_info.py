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
from openapi_client.model.name import Name
globals()['EntityInfo'] = EntityInfo
globals()['Name'] = Name
from openapi_client.model.registration_info import RegistrationInfo


class TestRegistrationInfo(unittest.TestCase):
    """RegistrationInfo unit test stubs"""

    def setUp(self):
        pass

    def tearDown(self):
        pass

    def testRegistrationInfo(self):
        """Test RegistrationInfo"""
        # FIXME: construct object with mandatory attributes with example values
        # model = RegistrationInfo()  # noqa: E501
        pass


if __name__ == '__main__':
    unittest.main()