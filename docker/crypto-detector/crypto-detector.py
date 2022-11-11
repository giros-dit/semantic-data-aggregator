import signal
import sys
import time
import threading
import numpy as np
import joblib
import argparse

from kafka.errors import NoBrokersAvailable

from kafka import KafkaProducer
from kafka import KafkaConsumer

# CONFIGURATION GLOBAL VARIABLES
KAFKA_BROKER = ""
KAFKA_TOPIC_CONSUME = ""
KAFKA_TOPIC_PRODUCE = ""


def crypto_detector():

    print("Crypto Detection Engine started")
    
    # Kafka Source Topic
    consumer = KafkaConsumer(
        KAFKA_TOPIC_CONSUME,
        bootstrap_servers=[KAFKA_BROKER],
        auto_offset_reset='latest',
        group_id='event-gen')

    # Kafka Sink Topic
    producer = KafkaProducer(bootstrap_servers=[KAFKA_BROKER])


    # Load the Predictor
    rf = joblib.load("RandomForestTrained.joblib")
    print("ML module loaded")
    print("Detector Running!")


    # READ from kafka topic when messages available
    for received in consumer:
        print("RECEIVED\n%s,%s\n\n" % (type(received.value), received.value), flush=True)
        # DECODE the message
        message = None
        try:
            message = received.value.decode("utf-8")
            message = message.replace('"', '')
        except UnicodeDecodeError as e:
            print(e)

        if(message is not None):

            # PROCESS MESSAGE to obtain necessary values (transform into numpy array)
            featuresl = message.split(",")

            # This will change if index of Anonymized & Preprocessed Netflow Data schema changes
            features_a = featuresl[62:]
            features_a = np.array(features_a).reshape(1,-1)


            # MAKE PREDICTION
            cryptoproba = rf.predict_proba(features_a)
            output = featuresl + ["Crypto", "Malware", str(cryptoproba[0][1])]
            output = ",".join(output)

            # WRITE PREDICTION in kafka topic
            print("OUTPUT: %s\n" % (output), flush=True)
            producer.send(topic=KAFKA_TOPIC_PRODUCE, key=output.encode('utf-8'), value=output.encode('utf-8'), timestamp_ms=round(time.time() * 1000)-received.timestamp)
            producer.flush()


def handler(number, frame):
    sys.exit(0)


def safe_loop(fn):
    while True:
        try:
            fn()
        except SystemExit:
            print("Good bye!")
            return
        except NoBrokersAvailable:
            time.sleep(2)
            continue
        except Exception as e:
            print(e)
            return


def main(args):

    global KAFKA_BROKER
    global KAFKA_TOPIC_CONSUME
    global KAFKA_TOPIC_PRODUCE

    KAFKA_BROKER = args["broker"]
    KAFKA_TOPIC_CONSUME = args["consume"]
    KAFKA_TOPIC_PRODUCE = args["produce"]

    print("Passed: -b " + KAFKA_BROKER + " -c " + KAFKA_TOPIC_CONSUME + " -p " + KAFKA_TOPIC_PRODUCE)
    print("Launching detector")

    signal.signal(signal.SIGTERM, handler)
    detection = threading.Thread(target=safe_loop, args=[crypto_detector])
    detection.start()
    detection.join()


if __name__ == "__main__":

    parser = argparse.ArgumentParser(description='Create a Crypto Detection System connected with Kafka')
    parser.add_argument('-b', '--broker', metavar='path', required=True,
                        help='Address of the Kafka Broker')
    parser.add_argument('-c', '--consume', metavar='path', required=True,
                        help='Kafka Topic to consume from')
    parser.add_argument('-p', '--produce', metavar='path', required=True,
                        help='Kafka Topic to write the result to')
    args = parser.parse_args()
    main(vars(args))
