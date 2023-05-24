import signal
import requests
import sys
import time
import threading
import numpy as np
import joblib
import argparse
import logging

from kafka.errors import KafkaError, NoBrokersAvailable, KafkaConnectionError

from kafka import KafkaProducer
from kafka import KafkaConsumer
from kafka import TopicPartition

# CONFIGURATION GLOBAL VARIABLES
KAFKA_BROKER = ""
KAFKA_TOPIC_CONSUME = ""
KAFKA_TOPIC_PRODUCE = ""
TENANT_SERVICE = None
TENANT_ID = None
TOPIC_PARTITION = None

logging.basicConfig(level=logging.INFO, format='%(asctime)s:%(levelname)s:%(message)s')

def crypto_detector():

    global TOPIC_PARTITION

    logging.info("Crypto Detection Engine started.")
    
    if TENANT_SERVICE != None and TENANT_ID != None:
        url = TENANT_SERVICE + TENANT_ID
        try:
            response = requests.get(url, timeout=5)
            response.raise_for_status()  

            logging.info('\nResponse code: %s\n' % (response.status_code))

            logging.info('Tenant server response: %s\n' % (response.text))

            logging.info('Topic partition: %s\n' % (response.json()['partition']))

            TOPIC_PARTITION = response.json()['partition']

        except requests.exceptions.RequestException as e:
            logging.info('\nRequest error: %s\n' % (e))
            exit(0)

    # Kafka Source Topic
    if TENANT_SERVICE != None and TENANT_ID != None:
        consumer = KafkaConsumer(
            bootstrap_servers=[KAFKA_BROKER],
            auto_offset_reset='latest')
        
        consumer.assign([TopicPartition(KAFKA_TOPIC_CONSUME, TOPIC_PARTITION)])
    else:
        consumer = KafkaConsumer(
            KAFKA_TOPIC_CONSUME,
            bootstrap_servers=[KAFKA_BROKER],
            auto_offset_reset='latest')
    
    # Kafka Sink Topic
    producer = KafkaProducer(bootstrap_servers=[KAFKA_BROKER])


    # Load the Predictor
    rf = joblib.load("RandomForestTrained.joblib")
    logging.info("ML module loaded.")
    logging.info("Detector Running!")


    # READ from kafka topic when messages available
    for received in consumer:
        logging.info("\nRECEIVED: %s\n" % (received.value))
        # DECODE the message
        message = None
        try:
            message = received.value.decode("utf-8")
            message = message.replace('"', '')
        except UnicodeDecodeError as e:
            logging.error(e)

        if(message is not None):

            # PROCESS MESSAGE to obtain necessary values (transform into numpy array)
            featuresl = message.split(",")

            # This will change if index of Anonymized & Preprocessed Netflow Data schema changes
            features_a = featuresl[49:57]
            logging.info("FEATURES: %s\n" % (features_a))
            
            agg_features_with_zero = 0
            for feature in features_a:
                if feature == "0.0":
                    agg_features_with_zero += 1
            
            # ONLY MAKE PREDICTION IF THE SDA ADDED THE AGGREGATED FEATURES (NOT ALL AGGREGATED FEATURES WITH DEFAULT VALUE 0.0)
            if agg_features_with_zero != 8:
                features_a = np.array(features_a).reshape(1,-1)
                # MAKE PREDICTION
                cryptoproba = rf.predict_proba(features_a)
                output = featuresl + ["Crypto", "Malware", str(cryptoproba[0][1])]
                output = ",".join(output)

                # WRITE PREDICTION in kafka topic
                logging.info("OUTPUT: %s\n" % (output))
                if TENANT_SERVICE != None and TENANT_ID != None:
                    producer.send(topic=KAFKA_TOPIC_PRODUCE, key=output.encode('utf-8'), value=output.encode('utf-8'), timestamp_ms=round(time.time() * 1000)-received.timestamp, partition=TOPIC_PARTITION)
                else:
                    producer.send(topic=KAFKA_TOPIC_PRODUCE, key=output.encode('utf-8'), value=output.encode('utf-8'), timestamp_ms=round(time.time() * 1000)-received.timestamp)
                producer.flush()
            else:
                logging.exception("No SDA Processing... \n")


def handler(number, frame):
    sys.exit(0)


def safe_loop(fn):
    while True:
        try:
            fn()
        except SystemExit:
            logging.exception("Good bye!")
            return
        except KafkaError as e:
            logging.exception(e)
            time.sleep(1)
            continue
        except NoBrokersAvailable as e:
            logging.exception(e)
            time.sleep(1)
            continue
        except KafkaConnectionError as e:
            logging.exception(e)
            time.sleep(1)
            continue
        except Exception as e:
            logging.exception(e)
            return


def main(args):

    global KAFKA_BROKER
    global KAFKA_TOPIC_CONSUME
    global KAFKA_TOPIC_PRODUCE
    global TENANT_SERVICE
    global TENANT_ID

    if args["tenantservice"] == None and args["tenantid"] == None:
        KAFKA_BROKER = args["broker"]
        KAFKA_TOPIC_CONSUME = args["consume"]
        KAFKA_TOPIC_PRODUCE = args["produce"]
        logging.info("Passed: -b " + KAFKA_BROKER + " -c " + KAFKA_TOPIC_CONSUME + " -p " + KAFKA_TOPIC_PRODUCE)
        logging.info("Launching detector!")
    else:
        KAFKA_BROKER = args["broker"]
        KAFKA_TOPIC_CONSUME = args["consume"]
        KAFKA_TOPIC_PRODUCE = args["produce"]
        TENANT_SERVICE = args["tenantservice"]
        TENANT_ID = args["tenantid"]
        logging.info("Passed: -b " + KAFKA_BROKER + " -c " + KAFKA_TOPIC_CONSUME + " -p " + KAFKA_TOPIC_PRODUCE + " -ts " + TENANT_SERVICE + " -tid " + TENANT_ID)
        logging.info("Launching detector!")        

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
    parser.add_argument('-ts', '--tenantservice', metavar='path', required=False,
                        help='Multi-tenancy service')
    parser.add_argument('-tid', '--tenantid', metavar='path', required=False,
                        help='Tenant id')
    args = parser.parse_args()
    main(vars(args))
