#!/bin/bash
USAGE="
Usage:
./list-topics-kafka.sh
"
if [[ $# -ne 0 ]]; then
    echo ""
    echo "ERROR: incorrect number of parameters."
    echo "$USAGE"
    exit 1
fi

KAFKA_POD=$(kubectl get pods | grep kafka | cut -d" " -f 1)
kubectl exec -it $KAFKA_POD -- kafka-topics.sh --list --bootstrap-server localhost:9092