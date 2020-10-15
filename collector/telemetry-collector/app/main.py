from remote_pb2 import WriteRequest
from starlette.applications import Starlette
from starlette.responses import PlainTextResponse
from starlette.routing import Route

import snappy


async def metrics(request):
    message = await request.body()
    uncompressed_msg = snappy.uncompress(message)
    metric = WriteRequest()
    metric.ParseFromString(uncompressed_msg)
    print(metric.timeseries[0])
    #print(len(metric.timeseries))
    return PlainTextResponse()

routes = [
    Route("/metrics", endpoint=metrics, methods=["POST"])
]

app = Starlette(routes=routes)
