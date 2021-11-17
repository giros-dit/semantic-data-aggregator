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
from openapi_client.model.line_string import LineString
from openapi_client.model.polygon import Polygon
from openapi_client.model.position import Position
from openapi_client.model.position_array import PositionArray
globals()['LineString'] = LineString
globals()['Polygon'] = Polygon
globals()['Position'] = Position
globals()['PositionArray'] = PositionArray
from openapi_client.model.coordinates import Coordinates


class TestCoordinates(unittest.TestCase):
    """Coordinates unit test stubs"""

    def setUp(self):
        pass

    def tearDown(self):
        pass

    def testCoordinates(self):
        """Test Coordinates"""
        # FIXME: construct object with mandatory attributes with example values
        # model = Coordinates()  # noqa: E501
        pass


if __name__ == '__main__':
    unittest.main()
