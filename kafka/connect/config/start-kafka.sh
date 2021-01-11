#!/bin/bash -e

# connector start command here.
exec "/opt/kafka_2.13-2.7.0/bin/connect-distributed.sh" "/opt/kafka_2.13-2.7.0/config/connect-distributed.properties"
