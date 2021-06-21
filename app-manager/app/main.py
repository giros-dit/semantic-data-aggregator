from fastapi import FastAPI, HTTPException, File, UploadFile
from fastapi.staticfiles import StaticFiles
from semantic_tools.clients.flink_api_rest import FlinkClient
from semantic_tools.clients.ngsi_ld import NGSILDClient
from typing import Literal, Optional

import app_manager
import logging
import nipyapi
import shutil
import os

logger = logging.getLogger(__name__)


# Init NGSI-LD API Client
ngsi = NGSILDClient(
            url="http://orion:1026",
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
    # Check Orion-LD API is up
    app_manager.check_orion_status(ngsi)
    # Check NiFi REST API is up
    app_manager.check_nifi_status()
    # Upload NiFi admin templates
    app_manager.upload_local_nifi_templates(
        ngsi, app_manager_url)
    # Check Flink REST API is up
    app_manager.check_flink_status(flink)


@app.post("/applications/")
async def onboard_application(application_type: Literal["FLINK", "NIFI"],
                              name: str,
                              description: Optional[str] = None,
                              file: UploadFile = File(...)):
    contents = await file.read()
    application = None
    if application_type == "NIFI":
        # Write template to temporary file
        temp_folder = "/tmp/nifi/templates/"
        temp_path = os.path.join(temp_folder, "%s.xml" % name)
        os.makedirs(temp_folder, exist_ok=True)
        f = open(temp_path, "wb")
        f.write(contents)
        f.close()
        try:
            # Upload application to NiFi
            # Nipyapi runs check of file format for us
            # Moreover, the only way to find out the identifier
            # of the template, i.e. name, is by uploading it
            application = app_manager.upload_nifi_template(
                ngsi, name, temp_path,
                app_manager_url, description)
        except TypeError as e:
            logger.error(str(e))
            os.remove(temp_path)
            raise HTTPException(
                status_code=400,
                detail=str(e)
            )
        except ValueError as e:
            logger.error(str(e))
            os.remove(temp_path)
            raise HTTPException(
                status_code=409,
                detail=str(e)
            )
        # Store NiFI application in local catalog
        internal_id = application.internalId.value
        f_path = "/catalog/nifi/templates/%s.xml" % internal_id
        shutil.move(temp_path, f_path)
    else:
        # Write template to temporary file
        temp_folder = "/tmp/flink/jars/"
        temp_path = os.path.join(temp_folder, "%s.jar" % name)
        os.makedirs(temp_folder, exist_ok=True)
        f = open(temp_path, "wb")
        f.write(contents)
        f.close()
        try:
            # Upload application to Flink
            application = app_manager.upload_flink_jar(
                ngsi, flink, name, temp_path,
                app_manager_url, description)
        except Exception as e:
            logger.error(str(e))
            os.remove(temp_path)
            raise HTTPException(
                status_code=400,
                detail=str(e)
            )
        # Store Flink application in local catalog
        internal_id = application.internalId.value
        f_path = "/catalog/flink/jars/%s" % internal_id
        shutil.move(temp_path, f_path)

    return {"ID": application.id}
