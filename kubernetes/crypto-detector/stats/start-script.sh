#!/bin/bash
# 0. File routes
CDSDockerfile="../../../docker/crypto-detector/."
ProducerDockerfile="../../../docker/crypto-detector/producer/."
ConsumerDockerfile="../../../docker/crypto-detector/consumer/."
# 1. Zookeeper and Kafka pods deployment
helm repo add bitnami https://charts.bitnami.com/bitnami
helm install kafka bitnami/kafka --set persistence.enabled=false,zookeeper.persistence.enabled=false
# 2. CDS, Consumer and Producer pod images built
docker build -t cryto_detector:latest $CDSDockerfile
docker build -t producer:latest $ProducerDockerfile
docker build -t consumer:latest $ConsumerDockerfile
sleep 10
# 3. Pods deployment
kubectl apply -f ../crypto_detection.yaml
kubectl apply -f ../producer.yaml
kubectl apply -f ../consumer.yaml
sleep $1
# 4. Move csv stat file out of the cluster
consumer=$(kubectl get pods --no-headers -o custom-columns=":metadata.name" -l app=consumer-app)
echo "" > stats.csv
kubectl cp $consumer:/app/stats.csv ./stats.csv
# 5. Script to plot statistics
python3 stats.py