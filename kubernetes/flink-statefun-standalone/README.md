# Deployment as an [standalone Flink cluster](https://nightlies.apache.org/flink/flink-docs-release-1.14/docs/deployment/resource-providers/standalone/overview/) on Kubernetes
For flink-statefun clusters the working solution of using spotify operator for flink is not working. In this case the deployment of a standalone flink-statefun-cluster on kubernetes is proposed as solution.
There are two possible solutions documented, both are indeed the same but with a minor difference in the way of giving configuration parameters to the statefun cluster relative to the application module (ingress, egress, functions).

# Table of Contents

1. [First solution (Full template deployment)](#first-solution-full-template-deployment)
2. [Second solution (Using custom flink-statefun images)](#second-solution-using-custom-flink-statefun-images)
3. [Deploy with Helm](#deploy-with-helm)
4. [Deploy flink remote application](#deploy-flink-remote-application)
   - [General considerations](#general-considerations)
   - [Testing the app](#testing-the-app)

## First solution (Full template deployment)
As proposed in [Deployment flink stateful](https://nightlies.apache.org/flink/flink-statefun-docs-release-3.2/docs/deployment/overview/) use the base [flink-statefun image](https://hub.docker.com/layers/flink-statefun/apache/flink-statefun/3.2.0/images/sha256-c8bbd916cf5c22ae56764d36d546e4b9746d491e4eee8bffc7084228bb3a3b08?context=explore) in the `master-deployment.yaml` and `worker-deployment.yaml`, with this approach there is no need to create custom image for flink-statefun cluster and push it into the kubernetes registry. We just need to create another kubernetes template [application-module.yaml](https://github.com/giros-dit/semantic-data-aggregator/blob/68bf7b027b387ba7e7cd54897007ec0d9e39d3fa/kubernetes/flink-statefun-standalone/method1/flink-cluster-k8s/templates/application-module.yaml) that indicates the `module.yaml` configurations of the application:

Now in the [master-deployment.yaml](https://github.com/giros-dit/semantic-data-aggregator/blob/68bf7b027b387ba7e7cd54897007ec0d9e39d3fa/kubernetes/flink-statefun-standalone/method1/flink-cluster-k8s/templates/master-deployment.yaml) and [worker-deployment.yaml](https://github.com/giros-dit/semantic-data-aggregator/blob/68bf7b027b387ba7e7cd54897007ec0d9e39d3fa/kubernetes/flink-statefun-standalone/method1/flink-cluster-k8s/templates/worker-deployment.yaml) templates, the configuration of the yaml file needs to be included in volumeMounts inside the container as follows:

```
          volumeMounts:
            - name: flink-config-volume
              mountPath: /opt/flink/conf
            - name: aggregatorapp-module
              mountPath: /opt/statefun/modules/aggregatorapp-module
```

and in volumes:
```
      volumes:
        - name: flink-config-volume
          configMap:
            name: flink-config
            items:
              - key: flink-conf.yaml
                path: flink-conf.yaml
              - key: log4j-console.properties
                path: log4j-console.properties
        - name: application-module
          configMap:
            name: application-module
            items:
              - key: module.yaml
                path: module.yaml
```

## Second solution (Using custom flink-statefun images)
The [Github repo of flink-statefun](https://github.com/apache/flink-statefun/tree/master/tools/k8s/templates) is prepared for another way of deploying the cluster.
In this case a custom flink images is used, the module.yaml (configuration of the application) is embedded in the image with the [Dockerfile](https://github.com/giros-dit/semantic-data-aggregator/blob/68bf7b027b387ba7e7cd54897007ec0d9e39d3fa/docker/statefun-apps/Dockerfile):
```
RUN mkdir -p /opt/statefun/modules/aggregatorApp
ADD module.yaml /opt/statefun/modules/aggregatorApp
```
Being [module.yaml](https://github.com/giros-dit/semantic-data-aggregator/blob/68bf7b027b387ba7e7cd54897007ec0d9e39d3fa/docker/statefun-apps/module.yaml) the configuration of the statefun application (ingresses, egresses, routing):

Then create an image and push it to the kubernetes registry with:
```
sudo docker build -t <kubernetes_registry_address>:<kubernetes_registry_port>/<image name> .
sudo docker push <kubernetes_registry_address>:<kubernetes_registry_port>/<image name>
```
In the `master-deployment.yaml` and `worker-deployment.yaml` templates use this image. With this method there is no need to create an extra template for the module.yaml configurations, but the creation of custom images is needed.

## Deploy with Helm
To ease the deployment a custom Helm chart has been configured for both of the methods
Helm can be installed fetching the binary as described in the [official documentation](https://helm.sh/docs/intro/install/#from-the-binary-releases), for AMD64:
```
wget "https://get.helm.sh/helm-v3.8.2-linux-amd64.tar.gz"
tar -zxvf helm-v3.8.2-linux-amd64.tar.gz
mv linux-amd64/helm /usr/local/bin/helm
```

To deploy use the command:
```
helm install <full name override> <chart name>/ --values <chart name>/values.yaml
```
An example for method1 would be:
```
helm install flink-statefun-chart method1/flink-cluster-k8s --values method1/flink-cluster-k8s/values.yaml
```

You can stop the deployment with:
```
helm uninstall flink-statefun-chart 
```  


## Deploy flink remote application
In this case the application deployed will be a python appllication that aggregates in a single-flow way. Using the flink-statefun cluter we can develop and deploy applications in any language with SDK for flink (Javascript, Python, Java, Golang)

### General considerations
The app must be created using the flink-statefun library for the language used, Python in this case. This can be done by installing and using the statefun package:
```
pip install apache-flink-statefun==3.2.0
```
Then, any package to create http servers could be used to listen in an specific port, this will be the point of communication between the `flink-statfun worker` and the python application. In this case `AIOHTTP` is used.

### Testing the app
As the [application-module.yaml](https://github.com/giros-dit/semantic-data-aggregator/blob/68bf7b027b387ba7e7cd54897007ec0d9e39d3fa/kubernetes/flink-statefun-standalone/method1/flink-cluster-k8s/templates/application-module.yaml) indicates, the ingress will be the topic `raw` and the egress will be what indicates in the [aggregatorApp.py](https://github.com/giros-dit/semantic-data-aggregator/blob/68bf7b027b387ba7e7cd54897007ec0d9e39d3fa/docker/statefun-apps/aggregator/aggregatorApp.py) in this case the topic `aggregated`, hence, to test the application we will get a bash shell in the kafka pod to create a producer and consumer, then, it will be seen if the app is working fine. To do this we first execute:
```
kubectl exec -it <pod/name_of_pod> -- /bin/bash
```
Now we have a bash prepare to create producer and consumers. In this case, as the configuration of the `application-module.yaml` indicates, the ingress is of type `io.statefun.kafka.v1/ingress`, this implies that it is necessary to also send a UTF-8 key set for each record, :warning: *it cannot be null*, hence we need to use the following command to create the producer:
```
kafka-console-producer.sh --bootstrap-server kafka-service:9092 --topic raw --property "parse.key=true" --property "key.separator=:"
```
After that we can send a message in the form of:
```
<key>:<value>
```
If the message is a JSON maybe is better to choose another key.separator that is not used in JSON format, `;` for example could be a better option.
In our case the value is a JSON, as an example we have used:
```
{"netflow-v9:netflow":{"fields-flow-record":[{"name":"FIELDS_NETFLOW_V9_1","dst-port":44797,"src-mac":"00:00:00:00:00:00","first-switched":1637147439,"dst-mac":"00:00:00:00:00:00","src-as":0,"dst-as":0,"protocol":"tcp","src-vlan":0,"icmp-type":0,"tcp-flags":"finsynpshack","bytes-in":262353,"src-mask":0,"pkts-in":208,"src-address":"192.168.123.102","ip-version":"ipv4","dst-mask":0,"last-switched":1637147649,"src-port":1024,"src-tos":0,"dst-address":"192.168.123.180","dst-vlan":0},{"name":"FIELDS_NETFLOW_V9_2","dst-port":1024,"src-mac":"00:00:00:00:00:00","first-switched":1637147439,"dst-mac":"00:00:00:00:00:00","src-as":0,"dst-as":0,"protocol":"tcp","src-vlan":0,"icmp-type":0,"tcp-flags":"finsynpshack","bytes-in":2351,"src-mask":0,"pkts-in":41,"src-address":"192.168.123.180","ip-version":"ipv4","dst-mask":0,"last-switched":1637147649,"src-port":44797,"src-tos":0,"dst-address":"192.168.123.102","dst-vlan":0}]}}
```
:warning: This JSON does not correspond with the actualized JSON_IETF outputed taking into consideration the newest YANG model for netflow_v9 traffic.  


:warning: Be sure to enable the `parse.key` option and send a key. If it is not done the following error will appear:
```
java.lang.IllegalStateException: The io.statefun.kafka.v1/ingress ingress requires a UTF-8 key set for each record.
        at org.apache.flink.statefun.flink.io.kafka.binders.ingress.v1.RoutableKafkaIngressDeserializer.requireNonNullKey(RoutableKafkaIngressDeserializer.java:70) ~[statefun-flink-distribution.jar:3.2.0]
        at org.apache.flink.statefun.flink.io.kafka.binders.ingress.v1.RoutableKafkaIngressDeserializer.deserialize(RoutableKafkaIngressDeserializer.java:48) ~[statefun-flink-distribution.jar:3.2.0]
        at org.apache.flink.statefun.flink.io.kafka.binders.ingress.v1.RoutableKafkaIngressDeserializer.deserialize(RoutableKafkaIngressDeserializer.java:29) ~[statefun-flink-distribution.jar:3.2.0]
        at org.apache.flink.statefun.flink.io.kafka.KafkaDeserializationSchemaDelegate.deserialize(KafkaDeserializationSchemaDelegate.java:46) ~[statefun-flink-distribution.jar:3.2.0]
        at org.apache.flink.streaming.connectors.kafka.KafkaDeserializationSchema.deserialize(KafkaDeserializationSchema.java:79) ~[statefun-flink-distribution.jar:3.2.0]
        at org.apache.flink.streaming.connectors.kafka.internals.KafkaFetcher.partitionConsumerRecordsHandler(KafkaFetcher.java:179) ~[statefun-flink-distribution.jar:3.2.0]
        at org.apache.flink.streaming.connectors.kafka.internals.KafkaFetcher.runFetchLoop(KafkaFetcher.java:142) ~[statefun-flink-distribution.jar:3.2.0]
        at org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumerBase.run(FlinkKafkaConsumerBase.java:826) ~[statefun-flink-distribution.jar:3.2.0]
        at org.apache.flink.streaming.api.operators.StreamSource.run(StreamSource.java:110) ~[flink-dist_2.12-1.14.3.jar:1.14.3]
        at org.apache.flink.streaming.api.operators.StreamSource.run(StreamSource.java:67) ~[flink-dist_2.12-1.14.3.jar:1.14.3]
        at org.apache.flink.streaming.runtime.tasks.SourceStreamTask$LegacySourceFunctionThread.run(SourceStreamTask.java:323) ~[flink-dist_2.12-1.14.3.jar:1.14.3]
```

Now that the producer has been created, we can create a consumer to see the aggregated flows with the following command:
```
kafka-console-consumer.sh  --from-beginning --bootstrap-server kafka-service:9092 --topic aggregated
```

An example of aggregated output for the JSON sent before will be:
```
{"netflow-v9:netflow": {"fields-flow-record": [{"name": "FIELDS_NETFLOW_V9_1", "dst-port": 44797, "src-mac": "00:00:00:00:00:00", "first-switched": 1637147439, "dst-mac": "00:00:00:00:00:00", "src-as": 0, "dst-as": 0, "protocol": "tcp", "src-vlan": 0, "icmp-type": 0, "tcp-flags": "finsynpshack", "bytes-in": 262353, "src-mask": 0, "pkts-in": 208, "src-address": "192.168.123.102", "ip-version": "ipv4", "dst-mask": 0, "last-switched": 1637147649, "src-port": 1024, "src-tos": 0, "dst-address": "192.168.123.180", "dst-vlan": 0, "flow-duration": 210, "pkts-in-per-second": 0.9904761904761905, "bytes-in-per-second": 1249.3, "bytes-per-packet": 1261.3125}, {"name": "FIELDS_NETFLOW_V9_2", "dst-port": 1024, "src-mac": "00:00:00:00:00:00", "first-switched": 1637147439, "dst-mac": "00:00:00:00:00:00", "src-as": 0, "dst-as": 0, "protocol": "tcp", "src-vlan": 0, "icmp-type": 0, "tcp-flags": "finsynpshack", "bytes-in": 2351, "src-mask": 0, "pkts-in": 41, "src-address": "192.168.123.180", "ip-version": "ipv4", "dst-mask": 0, "last-switched": 1637147649, "src-port": 44797, "src-tos": 0, "dst-address": "192.168.123.102", "dst-vlan": 0, "flow-duration": 210, "pkts-in-per-second": 0.19523809523809524, "bytes-in-per-second": 11.195238095238095, "bytes-per-packet": 57.34146341463415}]}}
```
