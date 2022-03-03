import logging
import os
import shutil
import time
from typing import Literal, Optional

from fastapi import FastAPI, File, HTTPException, UploadFile
from fastapi.staticfiles import StaticFiles
from semantic_tools.flink.client import FlinkClient
from semantic_tools.ngsi_ld.client import NGSILDClient
from semantic_tools.nifi.client import NiFiClient

from app_manager import loader

logger = logging.getLogger(__name__)


# Application Manager
APP_MANAGER_URI = os.getenv("APP_MANAGER_URI", "http://app-manager:8080")
# NGSI-LD Context Broker
BROKER_URI = os.getenv("BROKER_URI", "http://scorpio:9090")
# Context Catalog
CONTEXT_CATALOG_URI = os.getenv("CONTEXT_CATALOG_URI",
                                "http://context-catalog:8080/context.jsonld")
# Flink
FLINK_MANAGER_URI = os.getenv("FLINK_MANAGER_URI",
                              "http://flink-jobmanager:8081")
# NiFi
NIFI_URI = os.getenv("NIFI_URI", "https://nifi:8443/nifi-api")
NIFI_USERNAME = os.getenv("NIFI_USERNAME")
NIFI_PASSWORD = os.getenv("NIFI_PASSWORD")

# Init NGSI-LD Client
ngsi_ld = NGSILDClient(url=BROKER_URI, context=CONTEXT_CATALOG_URI)
# Init Flink REST API Client
flink = FlinkClient(url=FLINK_MANAGER_URI)
# Init NiFi REST API Client
nifi = NiFiClient(username=NIFI_USERNAME,
                  password=NIFI_PASSWORD,
                  url=NIFI_URI)

# Init FastAPI server
app = FastAPI(
    title="Application Manager API",
    version="1.0.0")

# Mount static catalog
script_dir = os.path.dirname(__file__)
st_abs_file_path = os.path.join(script_dir, "catalog")
app.mount("/catalog", StaticFiles(directory=st_abs_file_path), name="catalog")


@app.on_event("startup")
async def startup_event():
    # Check NiFi REST API is up
    # Hack for startup
    while True:
        try:
            nifi.login()
            break
        except Exception:
            logger.warning("NiFi REST API not available. "
                           "Retrying after 10 seconds...")
            time.sleep(10)
    # Upload NiFi admin templates
    loader.upload_local_nifi_templates(
        nifi, ngsi_ld, APP_MANAGER_URI)
    # Check Flink REST API is up
    flink.check_flink_status()
    # Upload Flink admin JARs
    loader.upload_local_flink_jars(
        flink, ngsi_ld, APP_MANAGER_URI)


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
            application = loader.upload_nifi_template(
                nifi, ngsi_ld, name, temp_path,
                APP_MANAGER_URI, description)
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
            application = loader.upload_flink_jar(
                flink, ngsi_ld, name, temp_path,
                APP_MANAGER_URI, description)
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
