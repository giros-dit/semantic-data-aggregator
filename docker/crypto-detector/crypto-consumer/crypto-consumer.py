from kafka import KafkaConsumer
from kafka import TopicPartition
from kafka.errors import KafkaError, NoBrokersAvailable, KafkaConnectionError 
import logging
import argparse
import time
import requests

# CONFIGURATION GLOBAL VARIABLES
KAFKA_BROKER = ""
KAFKA_TOPIC_CONSUME = ""
TENANT_SERVICE = None
TENANT_ID = None
TOPIC_PARTITION = None

logging.basicConfig(level=logging.INFO, format='%(asctime)s:%(levelname)s:%(message)s')

class Consumer:

    def __init__(self):
        self._init_kafka_consumer()


    def _init_kafka_consumer(self):
        global TOPIC_PARTITION

        logging.info("Kafka Consumer started.")
        
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
            self.consumer = KafkaConsumer(
                bootstrap_servers=[KAFKA_BROKER],
                auto_offset_reset='latest')
            
            self.consumer.assign([TopicPartition(KAFKA_TOPIC_CONSUME, TOPIC_PARTITION)])
        else:
            self.consumer = KafkaConsumer(
                KAFKA_TOPIC_CONSUME,
                bootstrap_servers=[KAFKA_BROKER],
                auto_offset_reset='latest')
    
    def consume_from_kafka(self):
        for message in self.consumer:
            logging.info(message.value)
            with open('stats.csv', 'a') as f:
                f.write(str(message.value)+"\n")

def main(args):

    global KAFKA_BROKER
    global KAFKA_TOPIC_CONSUME
    global TENANT_SERVICE
    global TENANT_ID

    KAFKA_BROKER = args["broker"]
    KAFKA_TOPIC_CONSUME = args["consume"]
    
    if args["tenantservice"] == None and args["tenantid"] == None:
        KAFKA_BROKER = args["broker"]
        KAFKA_TOPIC_CONSUME = args["consume"]
        logging.info("Passed: -b " + KAFKA_BROKER + " -c " + KAFKA_TOPIC_CONSUME)
        logging.info("Launching consumer!")
    else:
        KAFKA_BROKER = args["broker"]
        KAFKA_TOPIC_CONSUME = args["consume"]
        TENANT_SERVICE = args["tenantservice"]
        TENANT_ID = args["tenantid"]
        logging.info("Passed: -b " + KAFKA_BROKER + " -c " + KAFKA_TOPIC_CONSUME + " -ts " + TENANT_SERVICE + " -tid " + TENANT_ID)
        logging.info("Launching consumer!")
        
    while True:
        try:
            consumer = Consumer()
            break
        except SystemExit:
            logging.exception("Good bye!")
            return
        except KafkaError as e:
            logging.exception(e)
            time.sleep(1)
            continue
        except NoBrokersAvailable as e:
            logging.exception(e)
            time.sleep(2)
            continue
        except KafkaConnectionError as e:
            logging.exception(e)
            time.sleep(1)
            continue
        except Exception as e:
            logging.exception(e)
            return

    while True:
        consumer.consume_from_kafka()

if __name__ == "__main__":

    parser = argparse.ArgumentParser(description='Create a Producer that sends traffic to the Crypto Detection System')
    parser.add_argument('-b', '--broker', metavar='path', required=True,
                        help='Address of the Kafka Broker')
    parser.add_argument('-c', '--consume', metavar='path', required=True,
                        help='Kafka Topic to consume from')
    parser.add_argument('-ts', '--tenantservice', metavar='path', required=False,
                        help='Multi-tenancy service')
    parser.add_argument('-tid', '--tenantid', metavar='path', required=False,
                        help='Tenant id')
    args = parser.parse_args()
    main(vars(args))

