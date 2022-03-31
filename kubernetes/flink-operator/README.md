# Running Flink clusters in Application mode on Kubernetes

Spotify has developed a Kubernetes operator called [flink-on-k8s-operator](https://github.com/spotify/flink-on-k8s-operator) that enables managing the lifecycle of Apache Flink applications. The operator introduces the concept of Flink Cluster as CRD (Custom Resource Definition) in Kubernetes. It allows setting up `session-based` and `job-based` Flink clusters in Kubernetes – the second type refers to the [Application mode](https://nightlies.apache.org/flink/flink-docs-master/docs/deployment/overview/#application-mode) deployment. Basically, this deployment mode allows deploying a dedicated Flink cluster for submitting a particular job. So the Flink cluster only runs this job and then exits. Additionally, this operator allows for submitting Application-mode jobs that may build on JAR files or Python scripts that leverage PyFlink API.

In summary, this Kubernetes operator allows orchestrating Flink jobs executed in Application mode. Following the official [flink-on-k8s-operator user guide](https://github.com/spotify/flink-on-k8s-operator/blob/master/docs/user_guide.md), below are the basic steps to install the afomentioned Flink operator and an example of deploying Flink clusters in Application mode running a sample job. In addition, an example use case is included to be able to run the NetFlow driver as a Flink job in Application mode.


# Table of Contents

1. [Prerequisites](#prerequisites)
2. [Installing Kubernetes Operator for Apache Flink](#installing-kubernetes-operator-for-apache-flink)
3. [Deployment and management of Flink clusters in Application mode](#deployment-and-management-of-flink-clusters-in-application-mode)
4. [Use case: Deploying a NetFlow driver as a Flink job in Application mode](#use-case-deploying-a-netflow-driver-as-a-flink-job-in-application-mode)


## Prerequisites

- Get a running Kubernetes cluster. You can verify the cluster info with:

  ```bash
  kubectl cluster-info
  ```

- Install `cert-manager` (version 1.6.1) to automate certificate management in Kubernetes cluster:

  ```bash
  kubectl apply -f https://github.com/jetstack/cert-manager/releases/download/v1.6.1/cert-manager.yaml
  ```

- Install a private registry for Docker images in Kubernetes cluster.


## Installing Kubernetes Operator for Apache Flink

To install the Flink operator on Kubernetes cluster, the easiest option is using the `kubectl` command line tool. There are different [realeses](https://github.com/spotify/flink-on-k8s-operator/releases) available for the Flink operator, but we recommend to use the version `0.3.9`. Then, simply run:
```bash
kubectl apply -f https://github.com/spotify/flink-on-k8s-operator/releases/download/v0.3.9/flink-operator.yaml
```

There are other [installation alternatives](https://github.com/spotify/flink-on-k8s-operator/blob/master/docs/user_guide.md#deploy-the-operator-to-a-kubernetes-cluster) for the Flink operator using the `make deploy` command or using `Helm Chart`.

After deploying the operator, you can verify CRD `flinkclusters.flinkoperator.k8s.io` has been created:

```bash
kubectl get crds | grep flinkclusters.flinkoperator.k8s.io
```

View the details of the CRD:

```bash
kubectl describe crds/flinkclusters.flinkoperator.k8s.io
```

Find out the deployment:

```bash
kubectl get deployments -n flink-operator-system
```

Verify the operator Pod is up and running:

```bash
kubectl get pods -n flink-operator-system
```

Check the operator logs:

```bash
kubectl logs -n flink-operator-system -l app=flink-operator --all-containers
```

you should be able see logs like:

```
INFO    setup   Starting manager
INFO    controller-runtime.certwatcher  Starting certificate watcher
INFO    controller-runtime.controller   Starting workers        {"controller": "flinkcluster", "worker count": 1}
```

Undeploy the operator and CRDs from the Kubernetes cluster with:

```
kubectl delete -f https://github.com/spotify/flink-on-k8s-operator/releases/download/v0.3.9/flink-operator.yaml
```

## Deployment and management of Flink clusters in Application mode

After deploying the Flink CRDs and Operator in a Kubernetes cluster, the operator serves as a control plane for Flink applications. In other words, previously the cluster only understands the language of Kubernetes, now it understands the language of Flink. Then, you can create custom resources representing Flink clusters in Application mode.

Deploy a [sample Flink cluster in Application mode](../flink-operator/flink-cluster-templates/flinkoperator-flinkjobcluster-sample.yaml) custom resource with:

```bash
kubectl apply -f flink-cluster-templates/flinkoperator-flinkjobcluster-sample.yaml
```

And verify that the related pods and services are up and running with:

```
kubectl get pods,svc -n default | grep "flinkjobcluster"
```

Before deploying the Flink cluster, within the [`flink-cluster-templates/flinkoperator-flinkjobcluster-sample.yaml`](../flink-operator/flink-cluster-templates/flinkoperator-flinkjobcluster-sample.yaml) template we can configure different interesting properties. With the `replicas` parameter in `taskManager` section, we can select the number of TaskManager in the Flink cluster. Also, in the resources option in `jobManager` and `tasManager` sections, we can configure the CPU and memory resources that we want to allocate to the JobManager and TaskManager nodes of the Flink cluster. In addition, in the `job` section we need to specify some properties relative to the Flink application, such as the executable JAR file (i.e., the `jarFile` field), the name of the Java main class (i.e., the `className` field), and the arguments (i.e., the `args` field) required to run the Flink job. Finally, properties related to [job scheduling](https://nightlies.apache.org/flink/flink-docs-master/docs/internals/job_scheduling/) such as number of Task Slots and parallelism can be configured.

By default, Flink cluster's TaskManager will get terminated once the sample job is completed.

You can check the operator logs at any time with:

```bash
kubectl logs -n flink-operator-system -l app=flink-operator --all-containers -f --tail=1000
```

After deploying the Flink cluster with the operator, you can find the cluster custom resources with:

```bash
kubectl get flinkclusters
```

And check the cluster status with:

```bash
kubectl describe flinkclusters <CLUSTER-NAME>
```

In a Flink cluster in Application mode, the job is automatically submitted by the operator. The operator creates a submitter for a Flink job. The job submitter itself is created as a Kubernetes job. When the job submitter starts, it first checks the status of Flink job manager. And it submits a Flink job when confirmed that Flink job manager is ready and then terminates.

You can check the Flink job submission status and logs with:

```bash
kubectl describe jobs <CLUSTER-NAME>-job-submitter
kubectl logs jobs/<CLUSTER-NAME>-job-submitter -f
```

You can also access the Flink web UI, [REST API](https://ci.apache.org/projects/flink/flink-docs-stable/monitoring/rest_api.html) and [CLI](https://nightlies.apache.org/flink/flink-docs-master/docs/deployment/cli/) by first creating a port forward from you local machine to the JobManager service UI port (8081 by default).

```bash
kubectl port-forward svc/[FLINK_CLUSTER_NAME]-jobmanager 8081:8081
```

then access the web UI with your browser through the following URL:

```bash
http://localhost:8081
```

call the Flink REST API, e.g., list jobs:

```bash
curl http://localhost:8081/jobs
```

or run the Flink CLI, e.g., list jobs:

```bash
flink list -m localhost:8081
```

Finally, you can delete the Flink cluster with the following command regardless of its current status:

```
kubectl delete flinkclusters <CLUSTER-NAME>
```

The operator will cancel the current job and then shutdown the Flink cluster.

## Use case: Deploying a NetFlow driver as a Flink job in Application mode

The NetFlow driver is a Java application responsible for normalizing NetFlow-based monitoring data by parsing the raw data provided by a NetFlow collector and producing data structured according to an associated YANG model. This driver is implemented based on the [YANG Tools](https://github.com/opendaylight/yangtools) OpenDayLight project and particularized for a [GoFlow2](https://github.com/netsampler/goflow2) collector. Here is a detailed guide on how to run the NetFlow driver as a Flink job in Application mode.

First, a Kafka service will be deployed in Kubernetes to normalize the flow samples generated by the GoFlow2 collector, which are written to an input topic (called `flows`). In addition, the flows already normalized by the NetFlow driver's application will be aggregated in a specific Kafka output topic (called `netflow-output`). To achieve the goal, first you must create a custom GoFlow2 docker image, so from the directory [`docker/goflow2-collector`](../../docker/goflow2-collector) we must do the following:

```bash
docker build -t goflow2 .
```

To use this custom Docker image in your Kubernetes cluster, you must have a private registry for Docker images installed as a service in the cluster. Having a private Docker registry can significantly improve your productivity by reducing the time spent in uploading and downloading Docker images. Once this private registry service is available, it will allow uploading custom Docker images. Let’s assume the private insecure registry is available on the Kubernetes cluster at endpoint reachable via `<k8s-registry>` (which should be the cluster address) and port `5000`. The images we build need to be tagged with the registry endpoint before pushing them:

```bash
docker tag <IMAGE ID> <k8s-registry>:5000/goflow2:latest
```

Pushing the tagged image at this point will fail because the local Docker does not trust the private insecure registry. The docker daemon used for building images should be configured to trust the private insecure registry. This is done by marking the registry endpoint in `/etc/docker/daemon.json` file:

```bash
{
  "insecure-registries" : ["<k8s-registry>:5000"]
}
```

Restart the Docker daemon on the host to load the new configuration:

```bash
sudo systemctl restart docker
```

Now that the image is tagged correctly and the registry is trusted, the image can be pushed to the registry with:

```bash
docker push <k8s-registry>:5000/goflow2:latest
```

Once this is done, we can deploy the GoFlow2 service, a Kafka broker and the dependent Zookeeper service using the `kubectl` command line tool:

```bash
kubectl apply -f flink-cluster-templates/netflow-driver/services/zookeeper.yaml
kubectl apply -f flink-cluster-templates/netflow-driver/services/kafka.yaml
kubectl apply -f flink-cluster-templates/netflow-driver/services/goflow2.yaml
```

In order to deploy a Flink cluster in Application mode with the NetFlow driver related job running, we need to customize the Flink Docker image to include the application's executable JAR file. The [`flink-cluster-templates/netflow-driver/Dockerfile`](../flink-operator/flink-cluster-templates/netflow-driver/Dockerfile) file is a simple Dockerfile example to include the NetFlow driver application related JAR in the Flink Docker image. To do this, follow the same procedure as with the custom GoFlow2 docker image, i.e:

```bash
docker build -t netflow-driver flink-cluster-templates/netflow-driver/.
docker tag <IMAGE ID> <k8s-registry>:5000/netflow-driver:latest
docker push <k8s-registry>:5000/netflow-driver:latest
```

To check if the Docker images have been created and stored correctly you can use the Docker registry API, which allows you to list the Docker images available at any time in the private Kubernetes registry with:

```bash
curl -k https://<k8s-registry>:5000/v2/_catalog
```

you should be able to see output like:

```bash
{"repositories":["netflow-driver", "goflow2"]}
````

Also, you can check the list of tags associated with the different realeases of a specific Docker image with:

```bash
curl -k https://<k8s-registry>:5000/v2/netflow-driver/tags/list
```

you should be able to see output like:

```bash
{"name":"netflow-driver","tags":["latest"]}
````

Now, we can deploy the [Flink cluster with the NetFlow driver job](../flink-operator/flink-cluster-templates/netflow-driver/flinkoperator-flinkjobcluster-netflow.yaml) as a custom resource in Kubernetes with:

```bash
kubectl apply -f flink-cluster-templates/netflow-driver/flinkoperator-flinkjobcluster-netflow.yaml
```

This will deploy a Flink cluster in Application mode that makes use of the pre-loaded custom Docker image with the NetFlow driver's Flink application.

Within the [`flink-cluster-templates/netflow-driver/flinkoperator-flinkjobcluster-netflow.yaml`](../flink-operator/flink-cluster-templates/flinkoperator-flinkjobcluster-sample.yaml) template, in the `job` section we need to specify some properties relative to the Flink application, such as the executable JAR file (i.e., the `jarFile` field), the name of the Java main class (i.e., the `className` field), and the arguments (i.e., the `args` field) required to run the Flink job. In the case of the NetFlow driver's Flink application, we have to specify the Kafka endpoint (`advertised-host-name:advertised-port`) and the output topic name. It has been decided to specify the Kafka endpoint as an input argument to avoid having to modify the Java code if the Kafka endpoint changes. The flow samples generated by the GoFlow2 collector are written to the topic called `flows`. Then, the NetFlow driver's Flink application will basically consume the flows from this topic, normalize the NetFlow monitoring data according to its YANG model, and write the flows already normalized in the Kafka output topic.

If we want to test the NetFlow driver, we need to check that the input GoFlow2 flows are written in the correct topic. To do this, we can access to the container associated with the Kafka service Pod from the CLI client `kubectl`. First, we can discover the name of the Kafka Service Pod running on the Kubernetes cluster with:

```bash
kubectl get pods | grep "kafka"
```

After that, we can access the Kafka container's bash with:

```bash
kubectl exec -it <kafka-pod-name> -- bash
```

Once inside the Kafka container, if we list the current topics with:

```bash
kafka-topics.sh --list --bootstrap-server <kafka-endpoint>
```

We will discover that there is a `flows` topic from where the NetFlow driver expects to consume the NetFlow monitoring data. This data can be read by a Kafka consumer by running the following:

```bash
kafka-console-consumer.sh --topic flows --from-beginning --bootstrap-server <kafka-endpoint>
```

You should be able to see flows like:
```bash
{
    "Type":"NETFLOW_V9",
    "TimeReceived":1646565661,
    "SamplerAddress":"192.168.165.138",
    "SamplingRate":1,
    "SequenceNum":1172678,
    "Count":104,
    "SystemUptime":746774518,
    "UnixSeconds":1646564761,
    "SourceId":0,
    "FlowDirection":0,
    "TimeFlowStart":1646564299,
    "TimeFlowEnd":1646564427,
    "BytesIn":228,
    "PacketsIn":3,
    "BytesOut":0,
    "PacketsOut":0,
    "Etype":2048,
    "Proto":17,
    "SrcAddr":"10.0.27.116",
    "DstAddr":"37.235.53.152",
    "NextHop":"",
    "SrcPort":123,
    "DstPort":123,
    "InIf":0,
    "OutIf":0,
    "SrcMacIn":"00:00:00:00:00:00",
    "DstMacIn":"00:00:00:00:00:00",
    "SrcMacOut":"00:00:00:00:00:00",
    "DstMacOut":"00:00:00:00:00:00",
    "SrcVlan":0,
    "DstVlan":0,
    "VlanId":0,
    "SrcTos":184,
    "DstTos":0,
    "ForwardingStatus":0,
    "MinTTL":0,
    "MaxTTL":0,
    "TCPFlags":0,
    "IcmpType":0,
    "IcmpCode":0,
    "IPv6FlowLabel":0,
    "IPv6OptionHeaders":0,
    "FragmentId":0,
    "FragmentOffset":0,
    "BiFlowDirection":0,
    "SrcAS":0,
    "DstAS":0,
    "BgpNextHop":"",
    "SrcNet":0,
    "DstNet":0,
    "SrcPrefix":"",
    "DstPrefix":"",
    "MPLSPalRd":0,
    "MPLSPrefixLen":0,
    "MPLSTopLabelType":0,
    "MPLSTopLabelIP":"",
    "MPLS1Label":0,
    "MPLS2Label":0,
    "MPLS3Label":0,
    "MPLS4Label":0,
    "MPLS5Label":0,
    "MPLS6Label":0,
    "MPLS7Label":0,
    "MPLS8Label":0,
    "MPLS9Label":0,
    "MPLS10Label":0,
    "EngineType":0,
    "EngineId":0,
    "EtypeName":"IPv4",
    "ProtoName":"UDP",
    "IcmpName":""
}
```

Once this has been checked, now the NetFlow driver's Flink application will normalize the data flow and write the results in an output topic called `netflow-output`.

Finally, we can read the normalized data flows from the `netflow-output` topic thanks to the other Kafka consumer with:

```bash
kafka-console-consumer.sh --topic netflow-output --from-beginning --bootstrap-server <kafka-endpoint>
```

and you should be able to see output like:

```bash
{
  "netflow-v9:netflow": {
    "collector-goflow2": {
      "time-received": 1646565661,
      "sampler-address": "192.168.165.138"
    },
    "export-packet": {
      "count": 104,
      "flow-data-record": [
        {
          "flow-id": 1958795234,
          "dst-port": 123,
          "bgp": {
            "src-as": 0,
            "next-hop": "0.0.0.0",
            "dst-as": 0
          },
          "forwarding-status": "unknown",
          "engine-type": "rp",
          "snmp-in": 0,
          "dst-mac-out": "00:00:00:00:00:00",
          "protocol": "udp",
          "icmp-type": 0,
          "bytes-in": 228,
          "max-ttl": 0,
          "bytes-out": 0,
          "src-mac-out": "00:00:00:00:00:00",
          "last-switched": 1646564427,
          "src-tos": 184,
          "dst-tos": 0,
          "sampling-interval": 1,
          "first-switched": 1646564299,
          "src-mac-in": "00:00:00:00:00:00",
          "vlan": {
            "dst-id": 0,
            "src-id": 0
          },
          "direction": "ingress",
          "tcp-flags": "",
          "pkts-in": 3,
          "dst-mac-in": "00:00:00:00:00:00",
          "ip-version": "ipv4",
          "ipv4": {
            "src-address": "10.0.27.116",
            "dst-mask": 0,
            "dst-address": "37.235.53.152",
            "next-hop": "0.0.0.0",
            "src-mask": 0,
            "identification": 0
          },
          "mpls": {
            "label-9": 0,
            "label-7": 0,
            "label-8": 0,
            "label-5": 0,
            "label-6": 0,
            "prefix-len": 0,
            "top-label-ip": "0.0.0.0",
            "label-3": 0,
            "pal-rd": 0,
            "label-4": 0,
            "label-1": 0,
            "label-2": 0,
            "top-label-type": "unknown",
            "label-10": 0
          },
          "min-ttl": 0,
          "src-port": 123,
          "pkts-out": 0,
          "snmp-out": 0,
          "engine-id": 0
        }
      ],
      "sequence-number": 1172678,
      "system-uptime": 746774518,
      "unix-seconds": 1646564761,
      "source-id": 0
    }
  }
}

```

For managing the lifecycle of the Flink cluster deployed in Application mode and the relative job in execution, follow the steps indicated in [Deployment and management of Flink clusters in Application mode](#deployment-and-management-of-flink-clusters-in-application-mode).
