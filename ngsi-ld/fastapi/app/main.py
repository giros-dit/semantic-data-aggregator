from fastapi import FastAPI, status
from typing import Optional

# FastAPI specific code
tags_metadata = [
    {
        "name": "Context Information",
        "description": "NGSI-LD Entities",
    }
]

app = FastAPI(
    title="NGSI-LD API",
    version="1.0.0",
    openapi_tags=tags_metadata)

@app.get("/ngsi-ld/v1/entities/",
         summary="Query entities",
         description="Retrieve a set of entities which matches \
                      a specific query from an NGSI-LD system",
         tags= ["Context Information"],
         status_code=status.HTTP_200_OK)
async def queryEntities(type: str, attrs: Optional[str]=None, q: Optional[str]=None):
    pass