# Flink experiments for data processing in Avro format

There are experimental `Flink` Java application examples to play with `Avro` data serialization/deserialization for different data source use cases using `Confluent` and `Cloudera` schema registries. 

For each data source and each schema registry type (`Confluent` or `Cloudera`) there is a `Flink` stream application to generate samples of serialized data in `Avro` format according to its own schema. In addition, for each data source and each schema registry type (`Confluent` or `Cloudera`) there is a `Flink` stream application to consume and deserialize data samples that come in `Avro` format (from the previous `Flink` application or from a `NiFi` flow).

To upload the `Flink` stream applications to `Flink` engine, you have to make use of its REST API.

An example of how to upload the JAR application to generate data samples of a `Prometheus` metric serialized in `Avro` from the REST API is (making use of `Confluent` schema registry):
```
curl -X POST -H "Expect:" -F "jarfile=@flink-conf/confluent-schema-registry-transformations/prometheus/prometheus-generator/target/flink.PrometheusGeneratorJob-0.0.1-SNAPSHOT.jar" http://localhost:8084/jars/upload
```

An example of how to upload the JAR application to consume a deserialize data samples of a `Prometheus` metric serialized in `Avro` from the REST API is (making use of `Confluent` schema registry):
```
curl -X POST -H "Expect:" -F "jarfile=@flink-conf/confluent-schema-registry-transformations/prometheus/prometheus-consumer/target/flink.PrometheusConsumerJob-0.0.1-SNAPSHOT.jar" http://localhost:8084/jars/upload
```

Instead, the approach followed in the `Semantic Data Aggregator` (`SDA`) framework can be used to manage the upload and execution of stream processing applications to the `Flink` engine (for more information see [`Stream Processing Applications Management`](../../stream-processing/README.md)).
