import unittest

from bindings.clarity_data_lake.bucket import Bucket
from bindings.clarity_data_lake.datalake import DataLake
from bindings.clarity_data_lake.object import Object
from bindings.clarity_data_lake.owner import Owner


class ClarityDataLake(unittest.TestCase):
    def test_bucket(self):
        self.assertIsInstance(
            Bucket.parse_file(
                "examples/clarity_data_lake/bucket/example-normalized.json"),
            Bucket)

    def test_data_lake(self):
        self.assertIsInstance(
            DataLake.parse_file(
                "examples/clarity_data_lake/datalake/example-normalized.json"),
            DataLake)

    def test_object(self):
        self.assertIsInstance(
            Object.parse_file(
                "examples/clarity_data_lake/object/example-normalized.json"),
            Object)

    def test_owner(self):
        self.assertIsInstance(
            Owner.parse_file(
                "examples/clarity_data_lake/owner/example-normalized.json"),
            Owner)
