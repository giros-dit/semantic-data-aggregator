package giros.org;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.formats.avro.registry.cloudera.ClouderaRegistryKafkaDeserializationSchema;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer;
import org.apache.flink.streaming.connectors.kafka.KafkaDeserializationSchema;
import giros.org.Message;

public class AvroDataConsumerJob {
	public static void main(String[] args) throws Exception {
		Properties props = new Properties();
		props.put("bootstrap.servers", "kafka:9092");
		props.put("group.id", "example");

		StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
		Map<String, Object> schemaRegistryConf = new HashMap<>();

		schemaRegistryConf.put("schema.registry.url", "http://schema-registry:9090/api/v1");
		// String schemaRegistryAddress = "http://schema-registry:9090/api/v1";
		KafkaDeserializationSchema<Message> schema = ClouderaRegistryKafkaDeserializationSchema
				.builder(Message.class)/* .setRegistryAddress(schemaRegistryAddress) */
				.setConfig(schemaRegistryConf).build();

		FlinkKafkaConsumer<Message> kafkaSource = new FlinkKafkaConsumer<>("message", schema, props);

		DataStream<String> source = env.addSource(kafkaSource).name("Kafka Source").uid("Kafka Source")
				.map(record -> record.getId() + "," + record.getName() + "," + record.getDescription())
				.name("ToOutputString");

		// Produce data stream for Kafka topic
		FlinkKafkaProducer<String> kafkaSink = new FlinkKafkaProducer<>("raw-message", new SimpleStringSchema(), props);

		source.addSink(kafkaSink).name("Kafka Sink").uid("Kafka Sink");

		source.print();

		env.execute("Avro Data Consumer Job");
	}
}

