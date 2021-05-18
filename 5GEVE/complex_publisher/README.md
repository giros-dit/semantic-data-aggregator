# Kafka Python complex publisher

Docker image with the logic needed to enable a Python publisher for Kafka, publishing the data in the port specified as argument. This is a complex subscriber because it can be deployed without needing to execute the Python script, for troubleshooting purposes (e.g. do connectivity tests and so on).

## Build the image

```sh
$ docker build -t complex_publisher .
```

## Run the image

```sh
# Create the container and then execute the script
$ docker run --name <container_name> -d complex_publisher
$ docker exec -it <container_name> python3 publisher.py <ip>:<port> <topic> <n_metrics>

# All in one single command
$ docker run --name <container_name> -it complex_publisher python3 publisher.py <ip>:<port> <topic> <n_metrics>
```

Where:

* **container_name:** name for the container to be deployed.
* **ip:** DCM IP address in the same network than the one used by this publisher.
* **port:** port to which Kafka is listening in that network (e.g. 9092).
* **topic:** Kafka topic in which the publisher will publish the data.
* **n_metrics:** Number of publish operations performed by this publisher.
