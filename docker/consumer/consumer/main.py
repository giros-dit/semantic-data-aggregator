import json

from fastapi import FastAPI, Request, status

# FastAPI specific code
tags_metadata = [
    {
        "name": "Dummy Consumer"
    }
]

# Init FastAPI server
app = FastAPI(
    title="Dummy Consumer API",
    version="1.0.0",
    openapi_tags=tags_metadata)


# API for consumer
@app.post("/notify",
          status_code=status.HTTP_200_OK)
async def consumerHello(request: Request):
    req_json = await request.json()
    print(json.dumps(req_json))
