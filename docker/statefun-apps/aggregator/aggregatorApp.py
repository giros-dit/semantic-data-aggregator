import json
# from json.decoder import JSONDecodeError
# from messages_pb2 import MessageRequest, MessageResponse

from statefun import StatefulFunctions
from statefun import RequestReplyHandler
from statefun.egress_io import kafka_egress_message

from aiohttp import web

functions = StatefulFunctions()


@functions.bind("normfuncs/aggregatorApp")
# def aggregate(context, message: MessageRequest):
def aggregate(context, message):

    try:
        if(not message.is_string()):
            raise UnicodeDecodeError('utf-8', enc, 1, 2, 'Is the Input of type String utf-8?')

        request = message.typed_value.value.decode("utf-8")
        # print("MESSAGE PARSED\n", type(request), request, "\n", flush=True)

        # json2dict
        jsonvalue = json.loads(request)
        # do aggregations
        allflows = jsonvalue['netflow-v9:netflow']['fields-flow-record']

    except UnicodeDecodeError as e:
        print(e)
    except JSONDecodeError:
        print("ERROR: Malformed JSON")
        print("MESSAGE: ", type(request), request)
        print("\n", flush=True)
    except KeyError:
        print("ERROR: Non existant Key in JSON")
    else:

        for flow in allflows:
            flow['flow-duration'] = flow['last-switched'] - flow['first-switched']
            flow['pkts-in-per-second'] = flow['pkts-in'] / flow['flow-duration']
            flow['bytes-in-per-second'] = flow['bytes-in'] / flow['flow-duration']
            flow['bytes-per-packet'] = flow['bytes-in'] / flow['pkts-in']

        # dict2json and enter value in response
        response = json.dumps(jsonvalue)

        # create egress message for kafka and sent it
        egress_message = kafka_egress_message(typename="tidtopics/my-egress",
                                                topic="aggregated",
                                                key=response,
                                                value=response)
        context.send_egress(egress_message)



handler = RequestReplyHandler(functions)



#
# Serve the endpoint
#


handler = RequestReplyHandler(functions)


async def handle(request):
    req = await request.read()
    res = await handler.handle_async(req)
    return web.Response(body=res, content_type="application/octet-stream")


app = web.Application()
app.add_routes([web.post('/statefun', handle)])

if __name__ == '__main__':
    web.run_app(app, port=8060)
