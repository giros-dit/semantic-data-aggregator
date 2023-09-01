#!/bin/bash
USAGE="
Usage:
./producer-topic-kafka.sh <topic-name>
    being:
        <topic-name>: the name of the Kafka topic where to publish data to.
"
if [[ $# -ne 1 ]]; then
    echo ""
    echo "ERROR: incorrect number of parameters."
    echo "$USAGE"
    exit 1
fi
KAFKA_POD=$(kubectl get pods | grep kafka | cut -d" " -f 1)
kubectl exec -it $KAFKA_POD -- kafka-console-producer.sh --topic $1 --bootstrap-server localhost:9092
