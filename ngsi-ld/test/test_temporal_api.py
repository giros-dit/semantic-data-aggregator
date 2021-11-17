"""
    ETSI ISG CIM / NGSI-LD API

    This OAS file describes the NGSI-LD API defined by the ETSI ISG CIM group. This Cross-domain Context Information Management API allows to provide, consume and subscribe to context information in multiple scenarios and involving multiple stakeholders  # noqa: E501

    The version of the OpenAPI document: latest
    Contact: NGSI-LD@etsi.org
    Generated by: https://openapi-generator.tech
"""


import unittest

import openapi_client
from openapi_client.api.temporal_api import TemporalApi  # noqa: E501


class TestTemporalApi(unittest.TestCase):
    """TemporalApi unit test stubs"""

    def setUp(self):
        self.api = TemporalApi()  # noqa: E501

    def tearDown(self):
        pass

    def test_add_temporal_entity_attrs(self):
        """Test case for add_temporal_entity_attrs

        """
        pass

    def test_create_update_entity_temporal(self):
        """Test case for create_update_entity_temporal

        """
        pass

    def test_modify_entity_temporal_attr_instance(self):
        """Test case for modify_entity_temporal_attr_instance

        """
        pass

    def test_query_temporal_entities(self):
        """Test case for query_temporal_entities

        """
        pass

    def test_remove_entity_temporal_attr(self):
        """Test case for remove_entity_temporal_attr

        """
        pass

    def test_remove_entity_temporal_attr_instance(self):
        """Test case for remove_entity_temporal_attr_instance

        """
        pass

    def test_remove_entity_temporal_by_id(self):
        """Test case for remove_entity_temporal_by_id

        """
        pass

    def test_retrieve_entity_temporal_by_id(self):
        """Test case for retrieve_entity_temporal_by_id

        """
        pass


if __name__ == '__main__':
    unittest.main()
