import sys
import time
import argparse

from kafka.errors import NoBrokersAvailable
from kafka import KafkaProducer

from flask import Flask
from flask import request

app = Flask(__name__)


KAFKA_BROKER = ""
DELAY_BETWEEN_EVENTS = ""

@app.route("/")
def generate_events():

    global KAFKA_BROKER
    global DELAY_BETWEEN_EVENTS

    FILE_PATH = request.args.get("filepath", type=str)
    KAFKA_TOPIC = request.args.get("topic", type=str)
    print(FILE_PATH)
    print(KAFKA_TOPIC)
    

    print("Generation of events started")
    
    # Read events from indicated file
    try:
        with open(FILE_PATH) as fd:
            lines = fd.read().splitlines()
    except FileNotFoundError as e:
        print(e)
        sys.exit()

    producer = None
    try:
        # Kafka Sink Topic
        producer = KafkaProducer(bootstrap_servers=[KAFKA_BROKER])
    except NoBrokersAvailable:
        sys.exit()

    print("PRODUCER:\n", producer)    

    # READ from kafka topic when messages available
    for event in lines:

        # Write the event in kafka topic
        print("Event: %s\n" % (event), flush=True)
        producer.send(topic=KAFKA_TOPIC, key=event.encode('utf-8'), value=event.encode('utf-8'))
        producer.flush()
        print(DELAY_BETWEEN_EVENTS)
        time.sleep(DELAY_BETWEEN_EVENTS)

    return "All events generated"


#
# Serve the endpoint
#

def main(args):
    # copy arguments in global variables
    global KAFKA_BROKER
    global DELAY_BETWEEN_EVENTS

    KAFKA_BROKER = args["broker"]
    DELAY_BETWEEN_EVENTS = int(args["delay"])
    
    # run webserver
    app.run(host="0.0.0.0", port=8088)


if __name__ == '__main__':
    # parse arguments
    parser = argparse.ArgumentParser(description='Create a Crypto Detection System connected with Kafka')
    parser.add_argument('-b', '--broker', metavar='path', required=True,
                        help='Address of the Kafka Broker')
    parser.add_argument('-d', '--delay', metavar='path', required=True,
                        help='Delay between events')
    args = parser.parse_args()
    main(vars(args))
