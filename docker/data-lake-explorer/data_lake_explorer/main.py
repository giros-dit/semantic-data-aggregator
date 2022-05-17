import argparse
import json
import logging
import sys
import xml.etree.ElementTree as ET
from typing import List, Tuple

from semantic_tools.bindings.clarity_data_lake.bucket import Bucket
from semantic_tools.bindings.clarity_data_lake.datalake import DataLake
from semantic_tools.bindings.clarity_data_lake.object import Object
from semantic_tools.bindings.clarity_data_lake.owner import Owner
from semantic_tools.bindings.entity import DateTime
from semantic_tools.ngsi_ld.api import Options
from semantic_tools.ngsi_ld.client import NGSILDAPI

from data_lake_explorer.clients.data_lake import APIGateway

logger = logging.getLogger(__name__)

DATA_LAKE_NAME = "IDCC"
NS = {"": "http://s3.amazonaws.com/doc/2006-03-01/"}


def _chunks(lst, n):
    """
    Yield successive n-sized chunks from lst.

    Reference: https://stackoverflow.com/questions/312443/
               how-do-you-split-a-list-into-evenly-sized-chunks
    """
    for i in range(0, len(lst), n):
        yield lst[i: i + n]


def build_data_lake(dl_key: str, dl_uri: str, dl_region: str) -> DataLake:
    # Build data lake context
    dl_entity = DataLake(
        id="urn:ngsi-ld:DataLake:{0}".format(DATA_LAKE_NAME),
        api_key={"value": dl_key},
        region={"value": dl_region},
        uri={"value": dl_uri}
    )
    logger.info("Building {0}".format(dl_entity.id))
    return dl_entity


def discover_buckets(
    ag_client: APIGateway, data_lake: DataLake
) -> Tuple[List[Bucket], Owner]:
    # Collect bucket context
    b_xml = ag_client.list_buckets().text
    b_xml_root = ET.fromstring(b_xml)
    # Build owner context
    owner_id = b_xml_root.find("Owner", NS)[0].text
    owner = Owner(
        id="urn:ngsi-ld:Owner:{0}".format(owner_id),
        owner_id={"value": owner_id},
        member_of={"object": data_lake.id},
    )
    logger.info("Building {0}".format(owner.id))
    # Build bucket context
    buckets = []
    b_xml_buckets = b_xml_root.find("Buckets", NS)
    for b_xml_bucket in b_xml_buckets:
        b_name = b_xml_bucket.find("Name", NS).text
        b_cdate = b_xml_bucket.find("CreationDate", NS).text
        bucket_entity = Bucket(
            id="urn:ngsi-ld:Bucket:{0}".format(b_name),
            creation_date={"value": DateTime(value=b_cdate)},
            name={"value": b_name},
            belongs_to={"object": data_lake.id},
            owned_by={"object": owner.id},
        )
        logger.info("Building {0}".format(bucket_entity.id))
        buckets.append(bucket_entity)

    return buckets, owner


def discover_objects(
    ag_client: APIGateway, data_lake: DataLake, bucket: Bucket
) -> List[Tuple[Object, Owner]]:
    objects = []
    #  Collect object context
    o_xml = ag_client.list_objects(bucket.name.value).text
    o_xml_root = ET.fromstring(o_xml)
    o_xml_objects = o_xml_root.findall("Contents", NS)
    for o_xml_object in o_xml_objects:
        # Build owner context
        owner_id = o_xml_object.find("Owner", NS)[0].text
        owner = Owner(
            id="urn:ngsi-ld:Owner:{0}".format(owner_id),
            owner_id={"value": owner_id},
            member_of={"object": data_lake.id},
        )
        logger.info("Building {0}".format(owner.id))
        # Build object context
        o_etag = o_xml_object.find("ETag", NS).text.replace('"', "")
        o_key = o_xml_object.find("Key", NS).text
        o_lm = o_xml_object.find("LastModified", NS).text
        o_size = o_xml_object.find("Size", NS).text
        o_sc = o_xml_object.find("StorageClass", NS).text
        object = Object(
            id="urn:ngsi-ld:Object:{0}:{1}".format(bucket.name.value, o_key),
            e_tag={"value": o_etag},
            key={"value": o_key},
            last_modified={"value": DateTime(value=o_lm)},
            size={"value": int(o_size)},
            storage_class={"value": o_sc},
            contained_in={"object": bucket.id},
            owned_by={"object": owner.id},
        )
        logger.info("Building {0}".format(object.id))
        objects.append((object, owner))

    return objects


def main(ngsi_ld_client: NGSILDAPI, ag_client: APIGateway):
    logger.info("Data-lake-explorer service started!")
    # Build and upsert Data Lake
    data_lake = build_data_lake(
        ag_client.api_key,
        ag_client.url,
        ag_client.region)
    # https://github.com/samuelcolvin/pydantic/issues/1409
    ngsi_ld_client.batchEntityUpsert(
        [json.loads(data_lake.json(
            exclude_none=True, by_alias=True))], Options.update.value
    )
    # Discover and upsert Buckets plus Owner
    logger.info("Collecting Bucket context information")
    buckets, owner = discover_buckets(ag_client, data_lake)
    ngsi_ld_client.batchEntityUpsert(
        [json.loads(bucket.json(
            exclude_none=True, by_alias=True)) for bucket in buckets],
        Options.update.value
    )
    ngsi_ld_client.batchEntityUpsert(
        [json.loads(owner.json(
            exclude_none=True, by_alias=True))], Options.update.value
    )
    # Discover and upsert Objects
    logger.info("Collecting Object context information.")
    for bucket in buckets:
        objects = discover_objects(ag_client, data_lake, bucket)
        # Flatten list of tuples object,owner
        entities = [item for sublist in objects for item in sublist]
        # Split list into chunks due to NGSI-LD API limit
        entity_iterator = _chunks(entities, 20)
        while True:
            try:
                entities_chunk = next(entity_iterator)
                ngsi_ld_client.batchEntityUpsert(
                    [json.loads(
                        e.json(
                            exclude_none=True,
                            by_alias=True)) for e in entities_chunk],
                    Options.update.value,
                )
            except StopIteration:
                break


if __name__ == "__main__":
    logging.basicConfig(
        stream=sys.stdout,
        level=logging.INFO,
        format="'%(asctime)s - %(name)s - %(levelname)s - %(message)s'",
    )

    parser = argparse.ArgumentParser()
    parser.add_argument(
        "--broker-uri",
        dest="broker_uri",
        default="http://localhost:8080",
        required=False,
        help="NGSI-LD Context Broker URI.",
    )
    parser.add_argument(
        "--context-catalog-uri",
        dest="context_catalog_uri",
        default="http://context-catalog:8080/context.jsonld",
        required=False,
        help="Context Catalog URI.",
    )
    parser.add_argument(
        "--data-lake-uri", dest="data_lake_uri",
        required=True, help="Data Lake URI."
    )
    parser.add_argument(
        "--data-lake-key",
        dest="data_lake_key",
        required=True,
        help="Data Lake API Key.",
    )
    parser.add_argument(
        "--data-lake-region",
        dest="data_lake_region",
        required=True,
        help="Data Lake AWS Region.",
    )
    argv = sys.argv[1:]
    known_args, _ = parser.parse_known_args(argv)

    # Init NGSI-LD REST API Client
    headers = {"Accept": "application/json"}
    debug = True
    ngsi_ld_client = NGSILDAPI(
        known_args.broker_uri,
        headers=headers,
        context=known_args.context_catalog_uri,
        debug=debug,
    )

    # Init IDCC API Gateway Client
    ag_client = APIGateway(
        known_args.data_lake_uri,
        known_args.data_lake_key,
        known_args.data_lake_region
    )

    # Call main method
    main(ngsi_ld_client, ag_client)
