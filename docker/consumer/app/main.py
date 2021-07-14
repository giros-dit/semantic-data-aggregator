from fastapi import FastAPI, status, Request

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
    print(await request.json())
