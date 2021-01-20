from semantic_tools.clients.ngsi_ld import NGSILDClient
from semantic_tools.clients.flink_api_rest import FlinkClient
from semantic_tools.models.metric import MetricProcessor, StreamApplication

import logging
import subprocess
import time

logging.basicConfig(
        level=logging.INFO,
        format="%(asctime)s - %(name)s - %(levelname)s - %(message)s")
logger = logging.getLogger(__name__)


def uploadStreamApp(streamApplication: StreamApplication, ngsi: NGSILDClient, flink: FlinkClient):

    # Fetch JAR file from URI
    jarfile_download = subprocess.Popen(["wget", streamApplication.uri.value], stdout=subprocess.PIPE, stderr=subprocess.STDOUT)
    stdout,stderr = jarfile_download.communicate()
    time.sleep(2)

    # Upload JAR file
    logger.info("\n Upload Flink jar: {0}".format(flink.uploadJar(streamApplication.fileName.value)))

    # Remove JAR file from system store
    subprocess.call(["rm", streamApplication.fileName.value])

    # Update StreamApplication Entity with JAR id and entry-class
    files = flink.getFlinkAppsJars()['files']
    print("FILES", files)
    file_id=""
    entries=""
    entry_name=""
    for file in files:
    	print(file["id"])
    	if file["name"] == streamApplication.fileName.value:
    		file_id=file["id"]
    		print("FILE ID", file_id)
    		entries=file["entry"]
    		print("ENTRIES", entries)

    for entry in entries:
    	entry_name=entry["name"]

    print("ENTRY NAME", entry_name)

    fileId_dict = {
	"fileId": {
		"type": "Property",
		"value": file_id
        }
    }

    ngsi.updateEntityAttrs(streamApplication.id, fileId_dict)

    entryClass_dict = {
    	"entryClass": {
    		"type": "Property",
    		"value": entry_name
        }
    }

    ngsi.updateEntityAttrs(streamApplication.id, entryClass_dict)

def submitStreamJob(metricProcessor: MetricProcessor, ngsi: NGSILDClient, flink: FlinkClient):
    streamapp_entity = ngsi.retrieveEntityById(metricProcessor.hasApplication.object)
    streamapp = StreamApplication.parse_obj(streamapp_entity)

    files = flink.getFlinkAppsJars()['files']
    file_id=""

    # Retrieve StreamApplication Entity JAR id
    for file in files:
    	if file["name"] == streamapp.fileName.value:
    		file_id=file["id"]

    # Run job for JAR id
    logger.info("\n Submit Flink Job: {0}".format(flink.submitJob(file_id)))

    # Update MetricProcessor Entity with Job id
    jobs = flink.getFlinkJobs()["jobs"]
    job_id=""
    for job in jobs:
        if job['status'] == 'RUNNING':
                job_id=job["id"]

    jobId_dict = {
        "jobId": {
                "type": "Property",
                "value": job_id
        }
    }
    ngsi.updateEntityAttrs(metricProcessor.id, jobId_dict)

def deleteStreamApps(flink: FlinkClient):
    jobs = flink.getFlinkJobs()["jobs"]

    for job in jobs:
        if job['status'] == 'RUNNING':
                logger.info("\n Delete Flink Job: {0}".format(flink.deleteJob(job["id"])))

"""
def createStreamAppEntity(jarfile, ngsi: NGSILDClient, flink: FlinkClient):
    # Create StreamApplication entity
    streamapp = StreamApplication(id="urn:ngsi-ld:StreamApplication:1",
                        fileName={"value": jarfile},
                        fileId={"value": "null"},
			entryClass={"value": "null"},
                        description={"value": "description"},
                        uri={"value": "http://stream-catalog:8080/{0}".format(jarfile)})

    ngsi.createEntity(streamapp.dict(exclude_none=True))

    # Get StreamApplication entity by id
    response = ngsi.retrieveEntityById(entityId="urn:ngsi-ld:StreamApplication:1")
    streamapp = StreamApplication.parse_obj(response)
    logger.info("\n" + streamapp.json(indent=4,
                                     sort_keys=True,
                                     exclude_unset=True))
"""

"""
if __name__ == '__main__':
    ngsi = NGSILDClient(url="http://scorpio:9090",
                        headers={"Accept": "application/json"},
                        context="http://context-catalog:8080/context.jsonld")
    flink = FlinkClient(url="http://flink-jobmanager:8081", headers={"Accept": "application/json", "Content-Type": "application/json"})

    streamApplication_entity = ngsi.retrieveEntityById("urn:ngsi-ld:StreamApplication:1")
    streamApplication = StreamApplication.parse_obj(streamApplication_entity)

    uploadStreamApp(streamApplication, ngsi, flink)

    metricProcessor_entity = ngsi.retrieveEntityById("urn:ngsi-ld:MetricProcessor:1")
    metricProcessor = MetricProcessor.parse_obj(metricProcessor_entity)

    submitStreamJob(metricProcessor, ngsi, flink)
"""
