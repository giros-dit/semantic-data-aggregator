from starlette.applications import Starlette
from starlette.responses import PlainTextResponse
from starlette.routing import Route

import snappy

async def metrics(request):

    message = await request.body()
    print(snappy.uncompress(message))
    return PlainTextResponse("Hello World!!")

routes = [
    Route("/metrics", endpoint=metrics, methods=["POST"])
]

app = Starlette(routes=routes)
