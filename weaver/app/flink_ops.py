from semantic_tools.clients.ngsi_ld import NGSILDClient
from semantic_tools.clients.flink_api_rest import FlinkClient
from semantic_tools.models.metric import MetricSource, MetricProcessor, StreamApplication

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
    jar_uploaded = flink.uploadJar(streamApplication.fileName.value)
    logger.info("\n Upload Flink jar: {0}".format(jar_uploaded))

    # Remove JAR file from Weaver system store
    subprocess.call(["rm", streamApplication.fileName.value])

    # Update StreamApplication Entity with JAR id and entry-class
    jarfiles = flink.getFlinkAppsJars()['files']
    jarfile_id=""
    entries=""
    entry_name=""
    for jarfile in jarfiles:
    	if jarfile["name"] == streamApplication.fileName.value:
    		jarfile_id=jarfile["id"]
    		entries=jarfile["entry"]

    for entry in entries:
    	entry_name=entry["name"]

    fileId_dict = {
	"fileId": {
		"type": "Property",
		"value": jarfile_id
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
    streamApplication_entity = ngsi.retrieveEntityById(metricProcessor.hasApplication.object)
    streamApplication = StreamApplication.parse_obj(streamApplication_entity)

    # Retrieve StreamApplication Entity JAR id
    jarfile_id = streamApplication.fileId.value

    # Infer input_topic argument from hasInput relationship
    input_topic = metricProcessor.hasInput.object.strip("urn:ngsi-ld:").replace(":", "-").lower()

    # Infer output_topic argument from id argument
    output_topic = metricProcessor.id.strip("urn:ngsi-ld:").replace(":", "-").lower()

    # Get a list of arguments separated by commas (e.g. arg1, arg2, ...) to run the Flink job
    arguments = getArguments(metricProcessor, input_topic, output_topic)

    # Get a entry class of the Stream Aplication
    entryClass = streamApplication.entryClass.value

    # Run job for JAR id
    job_submitted = flink.submitJob(jarfile_id, entryClass, arguments)
    logger.info("\n Submit Flink Job: {0}".format(job_submitted))

    # Update MetricProcessor Entity with Job id
    job_id = job_submitted['jobid']
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


def getArguments(metricProcessor: MetricProcessor, input_topic: str, output_topic: str) -> str:

    arguments_list = []
    arguments_list.append(input_topic)
    arguments_list.append(output_topic)

    if(metricProcessor.arguments):
    	arguments_property = metricProcessor.arguments.value
    	for key, value in arguments_property.items():
    		arguments_list.append(value)

    arguments = ""
    for i in range(0,len(arguments_list)):
        if(i < (len(arguments_list)-1)):
                arguments += arguments_list[i]+","
        else:
                arguments += arguments_list[i]

    return arguments

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
