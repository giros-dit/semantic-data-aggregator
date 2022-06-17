################################################################################
#  Licensed to the Apache Software Foundation (ASF) under one
#  or more contributor license agreements.  See the NOTICE file
#  distributed with this work for additional information
#  regarding copyright ownership.  The ASF licenses this file
#  to you under the Apache License, Version 2.0 (the
#  "License"); you may not use this file except in compliance
#  with the License.  You may obtain a copy of the License at
#
#      http://www.apache.org/licenses/LICENSE-2.0
#
#  Unless required by applicable law or agreed to in writing, software
#  distributed under the License is distributed on an "AS IS" BASIS,
#  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
#  See the License for the specific language governing permissions and
# limitations under the License.
################################################################################

import signal
import sys
import time
import threading

import random

from kafka.errors import NoBrokersAvailable

from messages_pb2 import MessageRequest, MessageResponse

from kafka import KafkaProducer
from kafka import KafkaConsumer

KAFKA_BROKER = "kafka:9092"
KAFKA_TOPIC_PRODUCE = "raw"
KAFKA_TOPIC_CONSUME = "aggregated"
NAMES = ["Jerry", "George", "Elaine", "Kramer", "Newman", "Frank"]
NETFLOW_MSGS = [
    '{"netflow-v9:netflow":{"fields-flow-record":[{"name":"FIELDS_NETFLOW_V9_1","dst-port":44797,"src-mac":"00:00:00:00:00:00","first-switched":1637147439,"dst-mac":"00:00:00:00:00:00","src-as":0,"dst-as":0,"protocol":"tcp","src-vlan":0,"icmp-type":0,"tcp-flags":"finsynpshack","bytes-in":262353,"src-mask":0,"pkts-in":208,"src-address":"192.168.123.102","ip-version":"ipv4","dst-mask":0,"last-switched":1637147649,"src-port":1024,"src-tos":0,"dst-address":"192.168.123.180","dst-vlan":0},{"name":"FIELDS_NETFLOW_V9_2","dst-port":1024,"src-mac":"00:00:00:00:00:00","first-switched":1637147439,"dst-mac":"00:00:00:00:00:00","src-as":0,"dst-as":0,"protocol":"tcp","src-vlan":0,"icmp-type":0,"tcp-flags":"finsynpshack","bytes-in":2351,"src-mask":0,"pkts-in":41,"src-address":"192.168.123.180","ip-version":"ipv4","dst-mask":0,"last-switched":1637147649,"src-port":44797,"src-tos":0,"dst-address":"192.168.123.102","dst-vlan":0}]}}',

    '{"netflow-v9:netflow":{"fields-flow-record":[{"name":"FIELDS_NETFLOW_V9_5","dst-port":54578,"src-mac":"00:00:00:00:00:00","first-switched":1637147440,"dst-mac":"00:00:00:00:00:00","src-as":0,"dst-as":0,"protocol":"tcp","src-vlan":0,"icmp-type":0,"tcp-flags":"finsynpshack","bytes-in":328056,"src-mask":0,"pkts-in":260,"src-address":"192.168.123.104","ip-version":"ipv4","dst-mask":0,"last-switched":1637147667,"src-port":1024,"src-tos":0,"dst-address":"192.168.123.181","dst-vlan":0},{"name":"FIELDS_NETFLOW_V9_6","dst-port":1024,"src-mac":"00:00:00:00:00:00","first-switched":1637147440,"dst-mac":"00:00:00:00:00:00","src-as":0,"dst-as":0,"protocol":"tcp","src-vlan":0,"icmp-type":0,"tcp-flags":"finsynpshack","bytes-in":5545,"src-mask":0,"pkts-in":102,"src-address":"192.168.123.181","ip-version":"ipv4","dst-mask":0,"last-switched":1637147667,"src-port":54578,"src-tos":0,"dst-address":"192.168.123.104","dst-vlan":0}]}}'
]

def random_requests():
    """Generate infinite sequence of random MessageRequests."""
    while True:
        request = random.choice(NETFLOW_MSGS)
        yield request


def produce():
    if len(sys.argv) == 2:
        delay_seconds = int(sys.argv[1])
    else:
        delay_seconds = 1
    producer = KafkaProducer(bootstrap_servers=[KAFKA_BROKER])
    for request in random_requests():
        print("PRODUCER\n%s,%s\n\n" % (type(request), request), flush=True)
        producer.send(topic=KAFKA_TOPIC_PRODUCE, key=request.encode('utf-8'), value=request.encode('utf-8'))
        producer.flush()
        time.sleep(delay_seconds)


def consume():
    consumer = KafkaConsumer(
        KAFKA_TOPIC_CONSUME,
        bootstrap_servers=[KAFKA_BROKER],
        auto_offset_reset='earliest',
        group_id='event-gen')
    for message in consumer:
        print("CONSUMER\n%s,%s\n\n" % (type(message.value), message.value), flush=True)


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


def main():
    signal.signal(signal.SIGTERM, handler)

    producer = threading.Thread(target=safe_loop, args=[produce])
    producer.start()

    consumer = threading.Thread(target=safe_loop, args=[consume])
    consumer.start()

    producer.join()
    consumer.join()


if __name__ == "__main__":
    main()
