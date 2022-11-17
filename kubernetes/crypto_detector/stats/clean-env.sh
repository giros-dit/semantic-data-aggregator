#!/bin/bash
# Deletion of stats file
rm stats.csv
# Deletion of CDS, consumer and producer pods
kubectl delete deployment consumer producer crypto-detector
kubectl delete service consumer-service producer-service crypto-service
# Deletion of Zookeeper and Kafka pods
helm delete kafka
