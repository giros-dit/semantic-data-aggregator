from fastapi import FastAPI, status, Request, File, UploadFile
from fastapi.staticfiles import StaticFiles
from semantic_tools.clients.flink_api_rest import FlinkClient
from semantic_tools.clients.ngsi_ld import NGSILDClient
from flink.utils import check_flink_status
from nifi_utils import (
    check_nifi_status,
    upload_template,
    upload_local_templates
)
from ngsi_ld_utils import check_scorpio_status, create_application_context
from typing import Literal, Optional

import logging
import nipyapi

logger = logging.getLogger(__name__)


# Init NGSI-LD API Client
ngsi = NGSILDClient(
            url="http://orion-ld:1026",
            headers={"Accept": "application/json"},
            context="http://context-catalog:8080/context.jsonld")

# Init Flink REST API Client
flink = FlinkClient(
            url="http://flink-jobmanager:8081",
            headers={
                "Accept": "application/json",
                "Content-Type": "application/json"})

# Init NiFi REST API Client
nipyapi.config.nifi_config.host = "http://nifi:8080/nifi-api"

# Set app manager
app_manager_url = "http://app-manager:8080"

# Init FastAPI server
app = FastAPI(
    title="Application Manager API",
    version="1.0.0")

# Mount static catalog
app.mount("/catalog", StaticFiles(directory="/catalog"), name="catalog")

@app.on_event("startup")
async def startup_event():
    # Check Scorpio API is up
    check_scorpio_status(ngsi)
    # Check NiFi REST API is up
    check_nifi_status()
    # Upload MetricSource and MetricTarget templates
    upload_local_templates()
#     # Check Flink REST API is up
#     check_flink_status(flink)
#

@app.post("/applications/")
async def onboard_application(name: str,
                              application_type: Literal["FLINK", "NIFI"],
                              description: Optional[str] = None,
                              file: UploadFile = File(...)):
    contents = await file.read()
    if application_type == "NIFI":
        # Store NiFi application in local catalog
        f_path = "/catalog/nifi/templates/%s" % file.name
        f = open(f_path, "wb")
        f.write(contents)
        f.close()
        # Upload application to Flink
        upload_template(name, f_path)
    else:
        # Store Flink application in local catalog
        f_path = "/catalog/flink/jars/%s" % file.name
        f = open(f_path, "wb")
        f.write(contents)
        f.close()
        # Upload application to NiFi
        pass
    # Register Application context
    application_uri = app_manager_url + f_path
    application_id = "urn:ngsi-ld:Application:%s"
    create_application_context(
        ngsi, application_type, application_id,
        name, application_uri, description)
    return {"applicationId": application_id}
