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
    arguments = getStreamAppArguments(metricProcessor, input_topic, output_topic)

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

def deleteAllStreamJobs(ngsi: NGSILDClient, flink: FlinkClient):
    jobs = flink.getFlinkJobs()["jobs"]
    jobId = ""

    for job in jobs:
        if job['status'] == 'RUNNING':
        	jobId = job['id']
        	filter = "jobId=="+'"'+jobId+'"'
        	metricProcessor_entity = ngsi.queryEntities(type="MetricProcessor", q=filter)
        	metricProcessor = MetricProcessor.parse_obj(metricProcessor_entity[0])
        	logger.info("\n Delete MetricProcessor Entity with id {0}".format(metricProcessor.id, ngsi.deleteEntity(metricProcessor.id)))
        	logger.info("\n Delete Flink Job with id {0}".format(flink.deleteJob(jobId)))

def deleteStreamJob(ngsi: NGSILDClient, flink: FlinkClient, metricProcessorId: str):
    metricProcessor_entity = ngsi.retrieveEntityById(metricProcessorId)
    metricProcessor = MetricProcessor.parse_obj(metricProcessor_entity)
    jobId = metricProcessor.jobId.value
    jobs = flink.getFlinkJobs()["jobs"]

    for job in jobs:
        if job['status'] == 'RUNNING' and jobId == job['id']:
        	logger.info("\n Delete MetricProcessor Entity with id {0}".format(metricProcessor.id, ngsi.deleteEntity(metricProcessor.id)))
        	logger.info("\n Delete Flink Job with id {0}".format(flink.deleteJob(jobId)))

def getStreamAppArguments(metricProcessor: MetricProcessor, input_topic: str, output_topic: str) -> str:

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
