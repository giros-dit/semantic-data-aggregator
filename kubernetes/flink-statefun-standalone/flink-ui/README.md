# Accessing the web UI of the JobManager (Master)

To be able to access the web UI of the JobManager the service must be accessible from outside of the Kubernetes cluster.
 There are to main solutions to achieve this.

# Table of Contents

1. [Using LoadBalancer service type](#using-loadbalancer-service-type)
2. [Using Ingress](#using-ingress)
3. [Which one to choose?](#which-one-to-choose)

## Using LoadBalancer service type
Is the standar way to expose a service to the internet, but every service that expose to the internet will need its own IP direction. It is also needed a load balancer, in the case of kubernetes cloud deployments, for example with [GKE](https://cloud.google.com/kubernetes-engine?hl=es) or [AWS Kubernetes](https://aws.amazon.com/es/kubernetes/), a Load Balancer is automatically created when defining a service as LoadBalancer type. But in a Prem solutions there is a need to install a Load Balancer. In our case [metallb](https://metallb.universe.tf/) is installed. Metallb will have a pool of IPs configured, as every service needs an IP we could have as many services accessible depending of the number of the IPs in the pool. If the EXTERNAL-IP choosen by the service with LoadBalancer type is *192.168.159.9* the service can be accessed with:
```
http://192.168.159.9:31353
```
where *31353* is the NodePort choosen randomly by kubernetes or stablished in the configuration file with.


## Using Ingress
It is not a type of service but it is used to expose traffic to the internet. Acts as an intelligent router, it is like a HTTP LoadBalancer. It is really usefull if you want to expose multiple services over the same IP address and all this services uses the same L7 protocol (normally HTTP). An ingress file needs to be configured to indicate the host, the name and port of the service, the path and the ip of the load balancer. In this case [NGINX-ingress-controller](https://kubernetes.github.io/ingress-nginx/) is used to route the traffic. An example of configuration file is [flink-master-ingress.yaml](). To use Ingress we have to apply this ingress file with:
```
kubectl apply -f flink-master-ingress.yaml
```
And the type of the service in this case should be ClusterIP as the nginx-ingress service is going to communicate to the service we want to expose, so the only LoadBalancer type service should be the nginx-ingress controller. If the host configured is:
```
flinkmaster.192.168.159.8.nip.io
```
And the path is `/` we can access the flink web UI with the following url:
```
http://flinkmaster.192.168.159.8.nip.io
```

## Which one to choose?

|Method|Pros|Cons|
|---|---|---|
|LoadBalancer|No further configuration needed|Limited to the pool of IPs available to the Load Balancer|
|Ingress|Multiple service to one single IP|Need to configure Ingress and use Nginx|

If the Pool of IP is limited or lower than the number of services you want to expose the best option is to use Ingress and assign different services to different paths but the same URL. If the Number of services is limited you may want to use LoadBalancer as needs less configuration and the IPs are assigned automatically.
