# Running Flink clusters in Application-mode on Kubernetes

Spotify has developed a Kubernetes operator called [flink-on-k8s-operator](https://github.com/spotify/flink-on-k8s-operator) that enables managing the lifecycle of Apache Flink applications. The operator introduces the concept of Flink Cluster as CRD (Custom Resource Definition) in Kubernetes. It allows setting up `session-based` and `job-based` Flink clusters in Kubernetes – the second type refers to the [Application-mode] deployment (https://nightlies.apache.org/flink/flink-docs-master/docs/deployment/overview/#application-mode). Basically, this deployment mode allows deploying a dedicated Flink cluster for submitting a particular job. So the Flink cluster only runs this job and then exits. Additionally, this operator allows for submitting Application-mode jobs that may build on JAR files or Python scripts that leverage PyFlink API.

In summary, this Kubernetes operator allows orchestrating Flink jobs executed in Application-mode. Below are the basic steps to install the aforementioned operator and an example of deploying Flink clusters in Application-mode running a sample job. Following the official `flink-on-k8s-operator` [user guide](https://github.com/spotify/flink-on-k8s-operator/blob/master/docs/user_guide.md), below are the basic steps to install the afomentioned operator and an example of deploying `job-based` Flink clusters running a sample job.


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



## Installation of the Kubernetes Operator for Apache Flink

To install the Flink operator on Kubernetes cluster, the easiest option is using the `kubectl` command line tool. There are different [realeses](https://github.com/spotify/flink-on-k8s-operator/releases) available for the Flink operator, but we recommend to use the version `0.3.9`. Then, simply run:
```bash
kubectl apply -f https://github.com/spotify/flink-on-k8s-operator/releases/download/v0.3.9/flink-operator.yaml
```

There are [installation alternatives](https://github.com/spotify/flink-on-k8s-operator/blob/master/docs/user_guide.md#deploy-the-operator-to-a-kubernetes-cluster) for the Flink operator using the `make deploy` command or using `Helm Chart`.

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

## Deployment and management of Flink clusters in Application-mode
After deploying the Flink CRDs and the Flink Operator to a Kubernetes cluster, the operator serves as a control plane for Flink applications. In other words, previously the cluster only understands the language of Kubernetes, now it understands the language of Flink. Then, you can create custom resources representing Flink clusters in Application-mode.

Deploy a [sample Flink cluster in Application-mode](./flink-cluster-templates/flinkoperator-flinkjobcluster-sample.yaml) custom resource with:
```bash
kubectl apply -f flink-cluster-templates/flinkoperator-flinkjobcluster-sample.yaml
```

And verify that the related pods and services are up and running with:
```
kubectl get pods,svc -n default | grep "flinkjobcluster"
```
By default, Flink cluster's TaskManager will get terminated once  the sample job is completed.


After deploying the Flink cluster with the operator, you can find the cluster custom resources with:
```bash
kubectl get flinkclusters
```

And check the cluster status with:
```bash
kubectl describe flinkclusters <CLUSTER-NAME>
```

In a Flink cluster in Application-mode, the job is automatically submitted by the operator. The operator creates a submitter for a Flink job. The job submitter itself is created as a Kubernetes job. When the job submitter starts, it first checks the status of Flink job manager. And it submits a Flink job when confirmed that Flink job manager is ready and then terminates.

You can check the Flink job submission status and logs with:

```bash
kubectl describe jobs <CLUSTER-NAME>-job-submitter
kubectl logs jobs/<CLUSTER-NAME>-job-submitter -f
```