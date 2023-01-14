#!/bin/bash
USAGE="
Usage:
./consumer-topic-kafka-from-beginning.sh <topic-name>
    being:
        <topic-name>: the name of the Kafka topic where subscribe.
"
if [[ $# -ne 1 ]]; then
    echo ""
    echo "ERROR: incorrect number of parameters."
    echo "$USAGE"
    exit 1
fi
KAFKA_POD=$(kubectl get pods | grep kafka | cut -d" " -f 1)
kubectl exec -it $KAFKA_POD -- kafka-console-consumer.sh --topic $1 --bootstrap-server localhost:9092 --from-beginning