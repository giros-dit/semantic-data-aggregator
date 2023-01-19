from kafka import KafkaConsumer
from kafka.errors import KafkaError, NoBrokersAvailable, KafkaConnectionError 
import logging
import argparse
import time

logging.basicConfig(level=logging.INFO, format='%(asctime)s:%(levelname)s:%(message)s')

class Consumer:

    def __init__(self):
        self._init_kafka_consumer()


    def _init_kafka_consumer(self):
        self.consumer = KafkaConsumer(
            KAFKA_TOPIC_CONSUME,
            bootstrap_servers=[KAFKA_BROKER],
        )
    
    def consume_from_kafka(self):
        for message in self.consumer:
            logging.info(message.value)
            with open('stats.csv', 'a') as f:
                f.write(str(message.value)+"\n")

def main(args):

    global KAFKA_BROKER
    global KAFKA_TOPIC_CONSUME

    KAFKA_BROKER = args["broker"]
    KAFKA_TOPIC_CONSUME = args["consume"]
        
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
    args = parser.parse_args()
    main(vars(args))

