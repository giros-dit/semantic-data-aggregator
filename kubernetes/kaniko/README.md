# Automating docker image building with Kaniko and Maven
The previous way of deploying a flink application is to use Maven locally to package the application into a .jar, that jar is uploaded to Github and used into a Dockerfile to be copied with the flink cluster. This method makes difficult to maintain changes into the applications because every modification will later need to delete the previous .jar file and upload a new one. The .jar file is copied into the Docker image locally using `docker build` and `docker push` to build and push the image into the private registry. The aim of this documentation is to explain how to use Maven to automate the building of a Java application, in this specific case for Apache Flink, so it is not needed to upload any .jar file into the Github repository, and to automate the building and pushing of the Docker images with Kaniko.

# Table of Contents

1. [Kaniko](#kaniko)
2. [Maven and Kaniko](#maven-and-kaniko)
3. [Problem with Kaniko and local directories](#problem-with-kaniko-and-local-directories)


## Kaniko
Kaniko is a toool to build contianer images from a Dockerfile using Kubernetes, this project intends to rely entirely on Kubernetes, abstracting us from other frameworks such as Apache Flink. Kaniko has the posibility to build from different contexts (Github, locally, S3 Bucket and much more), it can also push the images into different repositories (Docker Hub, GRC, private registries, etc), it also has an extense and well explained [documentation](https://github.com/GoogleContainerTools/kaniko).

In this specific case we are going to use the code from Github and push the resulting image into a private registry in our Kubernetes cluster.

We only need to specify:

- "--context", indicates where the build context is, in this case the this github repository. A sub-path can be specified with "--context-sub-path".
- "--dockerfile", indicates the name and position of the docker file from the context indicated before.
- "--destination", which registry are we going to push the image into.
- Other args ("--insecure", "--skip-tls-verify", "--verbosity=debug") are used to be abel to push the image into an insecure registry and to show the debug information while building and pushing

An example of building an image with Kaniko could be seen in `kanikoDemo/kaniko-template.yaml` or `mvnKaniko/kaniko-template.yaml`.

To build and push the image we only need to create/apply the template as any other Kubernetes template:
```
kubectl apply -f mvnKaniko/kaniko-template.yaml
```


## Maven and Kaniko
The solution is to copy the source code into the image and not the .jar file, later use the installed dependencies of Maven inside of the image to package the application. In order to reduce significantly the size of the builds, [Docker multi-stage builds](https://docs.docker.com/develop/develop-images/multistage-build/) can be used so the final image only contains the generated .jar and not the Maven libraries and the source code.

The example for this Dockerfile is in `mvnKaniko/Dockerfile`.


## Problem with Kaniko and local directories
Using Github repository as context appears to be more slow than using local directories, hence, some tests with local directories has been done but there are problems with the dockerfile path (--dockerfile argument), there is a [tutorial](https://github.com/GoogleContainerTools/kaniko/blob/main/docs/tutorial.md) in the Kaniko repository, but even following this tutorial the same error has appear, apparently there are issues and other people who has suffered from the same exact error but with different conditions. In our case the build failed with local directories, but this may be usefull at some point in time.
