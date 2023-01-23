#!/bin/bash
USAGE="
Usage:
./create-topic-kafka.sh <topic-name>
    being:
        <topic-name>: the name of the Kafka topic to create.
"
if [[ $# -ne 1 ]]; then
    echo ""
    echo "ERROR: incorrect number of parameters."
    echo "$USAGE"
    exit 1
fi
KAFKA_POD=$(kubectl get pods | grep kafka | cut -d" " -f 1)
kubectl exec -it $KAFKA_POD -- kafka-topics.sh --create --topic $1 --bootstrap-server localhost:9092