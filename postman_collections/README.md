# Running Collections with Newman

[Newman](https://learning.postman.com/docs/running-collections/using-newman-cli/command-line-integration-with-newman/) enanbles running Postman collections through the command line.

## Installation

Node.js must be installed in the system. The following command shows how to install nodejs v14 in Ubuntu:
```bash
curl -sL https://deb.nodesource.com/setup_14.x | sudo -E bash -
sudo apt install nodejs npm
```

Then newman can be installed by issuing:
```bash
npm install -g newman
```

## EFACEC Use Case

## Setup

Config Newman to deploy a pipeline that collects metrics from 5G-VINNI's Prometheus and sends them to a local instance of Prometheus (notice the `Setup` folder is specified):
```bash
newman run Prometheus_5GVINNI_Demo.postman_collection.json -e input-linklatency.json --export-environment output-env-1.json --bail --folder Setup
```

## Teardown

Destroy the preivous pipeline by passing newman the environment variables files that was exported after finishing the previous execution (notice the `Teardown` folder is specified):
```bash
newman run Prometheus_5GVINNI_Demo.postman_collection.json -e output-env-1.json --export-environment output-env-1.json --bail --folder Teardown
```

# Flink API REST

1. Uploads a jar to the cluster. The jar must be sent as multi-part data. Make sure that the "Content-Type" header is set to "application/x-java-archive", as some http libraries do not add the header by default. Using 'curl' you can upload a jar via 'curl -X POST -H "Expect:" -F "jarfile=@path/to/flink-job.jar" http://hostname:port/jars/upload'.

Example:
```bash
curl -X POST -H "Expect:" -F "jarfile=@flink-conf/flink-kafka-uc1/target/flinkkafka-0.0.1-SNAPSHOT.jar" http://localhost:8084/jars/upload
```

2. Returns a list of all jars previously uploaded via '/jars/upload'.

Example:
```bash
curl -X GET http://localhost:8084/jars
```

3. Submits a job by running a jar previously uploaded via '/jars/upload'. Program arguments can be passed both via the JSON request (recommended) or query parameters.

- **jarid** - String value that identifies a jar. When uploading the jar a path is returned, where the filename is the ID. This value is equivalent to the `id` field in the list of uploaded jars (/jars).

- **entry-class** (optional): String value that specifies the fully qualified name of the entry point class. Overrides the class defined in the jar file manifest.

Example:
```bash
curl -X POST http://localhost:8084/jars/c9c6063e-a8e1-4839-9972-5cf48b7db998_flinkkafka-0.0.1-SNAPSHOT.jar/run?entry-class=flinkkafka.TrafficAvg
```

4. Cancel/Stop flink job.

Example:
```bash
curl -X PATCH http://localhost:8084/jobs/dc6dcc99b55184a40e2c0390559a3b84?mode=cancel
```

5. Returns an overview over all jobs and their current state.

Example:
```bash
curl -X GET http://localhost9:8084/jobs
```

6. Provides access to aggregated job metrics.

Example:
```bash
curl -X GET http://localhost:8084/jobs/metrics
```

7. Returns an overview over all jobs.

Example:
```bash
curl -X GET http://localhost:8084/jobs/overview
```

8. Returns the cluster (jobmanager) configuration.

Example:
```bash
curl -X GET http://localhost:8084/jobmanager/config
```

9. Returns the list of log files on the JobManager.

Example:
```bash
curl -X GET http://localhost:8084/jobmanager/logs
```

10. Provides access to job manager metrics.

Example:
```bash
curl -X GET http://localhost:8084/jobmanager/metrics
```

11) Returns the configuration of the WebUI.

Example:
```bash
curl -X GET http://localhost:8084/config
```
