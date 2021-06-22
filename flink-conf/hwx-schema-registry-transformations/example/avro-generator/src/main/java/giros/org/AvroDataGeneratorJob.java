package giros.org;

import org.apache.flink.formats.avro.registry.cloudera.ClouderaRegistryKafkaSerializationSchema;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.source.ParallelSourceFunction;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer;
import org.apache.flink.streaming.connectors.kafka.KafkaSerializationSchema;

import giros.org.Message;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Generates random Messages to a Kafka topic.
 */
public class AvroDataGeneratorJob {

	public static void main(String[] args) throws Exception {
		Properties props = new Properties();
		props.put("bootstrap.servers", "kafka:9092");
		props.put("group.id", "example");
		StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

		Map<String, Object> schemaRegistryConf = new HashMap<>();

		schemaRegistryConf.put("schema.registry.url", "http://schema-registry:9090/api/v1");
		//String schemaRegistryAddress = "http://schema-registry:9090/api/v1";
		KafkaSerializationSchema<Message> schema = ClouderaRegistryKafkaSerializationSchema
				.<Message>builder("message")/* .setRegistryAddress(schemaRegistryAddress) */
				.setConfig(schemaRegistryConf).setKey(Message::getId).build();

		FlinkKafkaProducer<Message> kafkaSink = new FlinkKafkaProducer<>("message", schema, props,
				FlinkKafkaProducer.Semantic.AT_LEAST_ONCE);

		DataStream<Message> input = env.addSource(new DataGeneratorSource()).name("Data Generator Source");

		input.addSink(kafkaSink).name("Kafka Sink").uid("Kafka Sink");

		input.print();

		env.execute("Avro Data Generator Job");
	}

	/**
	 * Generates Message objects with random content at random interval.
	 */
	public static class DataGeneratorSource implements ParallelSourceFunction<Message> {

		private volatile boolean isRunning = true;

		@Override
		public void run(SourceContext<Message> ctx) throws Exception {
			ThreadLocalRandom rnd = ThreadLocalRandom.current();
			while (isRunning) {
				synchronized (ctx.getCheckpointLock()) {
					ctx.collect(new Message(System.currentTimeMillis(), RandomStringUtils.randomAlphabetic(10),
							RandomStringUtils.randomAlphanumeric(20)));
				}

				Thread.sleep(Math.abs(rnd.nextInt()) % 1000);
			}
		}

		@Override
		public void cancel() {
			isRunning = false;
		}
	}
}

