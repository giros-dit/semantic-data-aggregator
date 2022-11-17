# Cryptomining Detection System (CDS) Latency Performance Script
This script measures the latency introduced by the CDS. To execute the script type:
```
./start-script.sh n
```
Change n with the number of seconds that the script will be collecting statistics.

To delete the scenario after being deployed:
```
./clean-env.sh
```
### Requirements
- Kubernetes cluster
- Helm package manager
- Docker
- kubectl

### Important notes:
- The script creates a local cluster with Zookeeper and Kafka in Step 1. Comment these lines if not needed
- The images built are taken from existing DockerFiles in the docker/crypto-detector folder. Change routes if needed.
- The "producer" pod created writes data to topic "entrada" every second. Change this value in producer.yaml file. 
- The "crypto-detector" pod created reads data from topic "entrada" and writes data to topic "salida". Change these values in crypto_detection.yaml file. 
- The "consumer" pod created reads data from topic "salida". Change this value in consumer.yaml file. 
- Comment the "kubectl apply" lines of the pods that you do not want to deploy.
