"""
    ETSI ISG CIM / NGSI-LD API

    This OAS file describes the NGSI-LD API defined by the ETSI ISG CIM group. This Cross-domain Context Information Management API allows to provide, consume and subscribe to context information in multiple scenarios and involving multiple stakeholders  # noqa: E501

    The version of the OpenAPI document: latest
    Contact: NGSI-LD@etsi.org
    Generated by: https://openapi-generator.tech
"""


import unittest

import openapi_client
from openapi_client.api.context_information_api import ContextInformationApi  # noqa: E501


class TestContextInformationApi(unittest.TestCase):
    """ContextInformationApi unit test stubs"""

    def setUp(self):
        self.api = ContextInformationApi()  # noqa: E501

    def tearDown(self):
        pass

    def test_append_entity_attrs(self):
        """Test case for append_entity_attrs

        """
        pass

    def test_create_entity(self):
        """Test case for create_entity

        """
        pass

    def test_partial_attr_update(self):
        """Test case for partial_attr_update

        """
        pass

    def test_query_entities(self):
        """Test case for query_entities

        """
        pass

    def test_remove_entity_attr(self):
        """Test case for remove_entity_attr

        """
        pass

    def test_remove_entity_by_id(self):
        """Test case for remove_entity_by_id

        """
        pass

    def test_retrieve_entity_by_id(self):
        """Test case for retrieve_entity_by_id

        """
        pass

    def test_update_entity_attrs(self):
        """Test case for update_entity_attrs

        """
        pass


if __name__ == '__main__':
    unittest.main()
