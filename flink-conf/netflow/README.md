# Flink Streaming Application with NetFlow YANGTools Driver

1. Uploads the JAR to the Flink cluster. 

Example:
```bash
curl -X POST -H "Expect:" -F "jarfile=@netflow-driver/target/netflow-driver-1.0.jar" http://localhost:8084/jars/upload
```

2. Returns a list of all JARs previously uploaded via '/jars/upload' (find the JAR ID of the NetFlow application).

Example:
```bash
curl -X GET http://localhost:8084/jars
```

3. Submits a job by running a jar previously uploaded via '/jars/upload'. Program arguments can be passed both via the JSON request (recommended) or query parameters.

- **jarid**: String value that identifies a jar. When uploading the jar a path is returned, where the filename is the ID. This value is equivalent to the `id` field in the list of uploaded jars (/jars).

- **programArg**: Comma-separated list of program arguments (i.e., input and output Kafka topics). 

- **entry-class** (optional): String value that specifies the fully qualified name of the entry point class. Overrides the class defined in the jar file manifest.

Example 1:
```bash
curl -X POST http://localhost:8084/jars/d18cd4e3-09cf-4556-8072-3a6409270b0d_netflow-driver-1.0.jar/run?programArg="netflow-input,netflow-output"
```

4. Cancel/Stop flink job.

Example:
```bash
curl -X PATCH http://localhost:8084/jobs/dc6dcc99b55184a40e2c0390559a3b84?mode=cancel
```

5. Returns an overview over all jobs and their current state.

Example:
```bash
curl -X GET http://localhost:8084/jobs
```