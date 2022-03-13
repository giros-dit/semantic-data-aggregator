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

First, a Kafka service will be deployed in Kubernetes in order to aggregate flow samples generated by the GoFlow2 collector to a particular input topic. In addition, the flows already normalized by the NetFlow driver's application will be aggregated in a specific Kafka output topic. To do this, using the `kubectl` command line tool, we will deploy a Kafka broker and the dependent Zookeeper service:

```bash
kubectl apply -f flink-cluster-templates/netflow-driver/kafka-service/zookeeper.yaml
kubectl apply -f flink-cluster-templates/netflow-driver/kafka-service/kafka.yaml
```

In order to deploy a Flink cluster in Application mode with the NetFlow driver related job running, we need to customize the Flink Docker image to include the application's executable JAR file. The [`flink-cluster-templates/netflow-driver/Dockerfile`](../flink-operator/flink-cluster-templates/netflow-driver/Dockerfile) file is a simple Dockerfile example to include the NetFlow driver application related JAR in the Flink Docker image. Build the related Docker image with:

```bash
docker build -t netflow-driver flink-cluster-templates/netflow-driver/.
```

To use this custom Docker image in your Kubernetes cluster, you must have a private registry for Docker images installed as a service in the cluster. Having a private Docker registry can significantly improve your productivity by reducing the time spent in uploading and downloading Docker images. Once this private registry service is available, it will allow uploading custom Docker images.

Let’s assume the private insecure registry is available on the Kubernetes cluster at endpoint reachable via the `k8s-cluster` domain name and port `5000`. The images we build need to be tagged with the registry endpoint before pushing them:

```bash
docker tag <IMAGE ID> k8s-cluster:5000/netflow-driver:latest
```

Pushing the tagged image at this point will fail because the local Docker does not trust the private insecure registry. The docker daemon used for building images should be configured to trust the private insecure registry. This is done by marking the registry endpoint in `/etc/docker/daemon.json` file:

```bash
{
  "insecure-registries" : ["k8s-cluster:5000"]
}
```

Restart the Docker daemon on the host to load the new configuration:

```bash
sudo systemctl restart docker
```

Now that the image is tagged correctly and the registry is trusted, the image can be pushed to the registry with:

```bash
docker push k8s-cluster:5000/netflow-driver:latest
```

Using the Docker registry API, we can list the Docker images available on each time in the Kubernetes private registry with:

```bash
curl k8s-cluster:5000/v2/_catalog
```

you should be able to see output like:

```bash
{"repositories":["netflow-driver"]}
````
Also, you can check the list of tags associated with the different realeases of the Docker image with:

```bash
curl k8s-cluster:5000/v2/netflow-driver/tags/list
```

you should be able to see output like:

```bash
{"name":"netflow-driver","tags":["latest"]}
````

Now, we can deploy the [Flink cluster with the NetFlow driver job](../flink-operator/flink-cluster-templates/flinkoperator-flinkjobcluster-sample.yaml) as a custom resource in Kubernetes with:

```bash
kubectl apply -f flink-cluster-templates/netflow-driver/flinkoperator-flinkjobcluster-netflow.yaml
```

This will deploy a Flink cluster in Application mode that makes use of the pre-loaded custom Docker image with the NetFlow driver's Flink application.

Within the [`flink-cluster-templates/netflow-driver/flinkoperator-flinkjobcluster-netflow.yaml`](../flink-operator/flink-cluster-templates/flinkoperator-flinkjobcluster-sample.yaml) template, in the `job` section we need to specify some properties relative to the Flink application, such as the executable JAR file (i.e., the `jarFile` field), the name of the Java main class (i.e., the `className` field), and the arguments (i.e., the `args` field) required to run the Flink job. In the case of the NetFlow driver's Flink application, we have to specify the Kafka input and output topics. The Kafka input topic aggregates the flow samples generated by the GoFlow2 collector. Then, the NetFlow driver's Flink application will basically consume the flows from this input topic, normalize the NetFlow monitoring data according to its YANG model, and write the flows already normalized in the Kafka output topic.

If we want to test the NetFlow driver, we need to generate flow samples in the Kafka input topic called `netflow-input-1`. To do this, we can access to the container associated with the Kafka service Pod from the CLI client `kubectl`. First, we can discover the name of the Kafka Service Pod running on the Kubernetes cluster with:

```bash
kubectl get pods | grep "kafka"
```

After that, we can access the Kafka container's bash with:

```bash
kubectl exec -it <kafka-pod-name> -- bash
```

Once inside the Kafka container, if we list the current topics with:

```bash
kafka-topics.sh --list --bootstrap-server localhost:9092
```

we will discover that there is a `netflow-input-1` topic from where the NetFlow driver expects to consume the NetFlow monitoring data.

Now, we need to produce sample NetFlow flows in the `netflow-input-1` topic. In the [`flink-cluster-templates/netflow-driver/goflow2-sample.json`](../flink-operator/flink-cluster-templates/netflow-driver/goflow2-sample.json) file there is a sample of flow generated by the GoFlow2 collector.

We can write copies of that flow sample in the `netflow-input-1` topic thanks to the Kafka producer with:

```bash
kafka-console-producer.sh --topic netflow-input-1 --bootstrap-server localhost:9092
```

Once the flow samples have been written, the NetFlow driver's Flink application will normalize the data flow and write the results in an output topic called `netflow-output-1`.

Finally, we can read the normalized data flows from the `netflow-output-1` topic thanks to the Kafka consumer with:

```bash
kafka-console-consumer.sh --topic netflow-output-1 --bootstrap-server localhost:9092 --from-beginning
```

you should be able to see output like:

```bash
{
  "netflow-v9:netflow": {
    "fields-flow-record": [
      {
        "name": "FIELDS_NETFLOW_V9_1",
        "dst-port": 443,
        "src-mac": "00:00:00:00:00:00",
        "first-switched": 1636369754,
        "dst-mac": "00:00:00:00:00:00",
        "src-as": 0,
        "dst-as": 0,
        "protocol": "tcp",
        "src-vlan": 0,
        "icmp-type": 0,
        "tcp-flags": "syn psh ack",
        "bytes-in": 636,
        "src-mask": 0,
        "pkts-in": 5,
        "src-address": "192.168.123.182",
        "ip-version": "ipv4",
        "dst-mask": 0,
        "last-switched": 1636369800,
        "src-port": 47172,
        "src-tos": 0,
        "dst-address": "192.168.123.102",
        "dst-vlan": 0
      }
    ]
  }
}
```

For managing the lifecycle of the Flink cluster deployed in Application mode and the relative job in execution, follow the steps indicated in [Deployment and management of Flink clusters in Application mode](#deployment-and-management-of-flink-clusters-in-application-mode).
