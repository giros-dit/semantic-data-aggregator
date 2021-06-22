package giros.org;

import org.apache.flink.formats.avro.registry.cloudera.ClouderaRegistryKafkaSerializationSchema;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.source.ParallelSourceFunction;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer;
import org.apache.flink.streaming.connectors.kafka.KafkaSerializationSchema;

import giros.org.EveRecord;
import org.apache.commons.lang3.RandomStringUtils;

import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Generates random EVE Records to a Kafka topic.
 */
public class EveGeneratorJob {

	public static void main(String[] args) throws Exception {
		Properties props = new Properties();
		props.put("bootstrap.servers", "kafka:9092");
		props.put("group.id", "eve-source");
		StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

		Map<String, Object> schemaRegistryConf = new HashMap<>();

		schemaRegistryConf.put("schema.registry.url", "http://schema-registry:9090/api/v1");
		// String schemaRegistryAddress = "http://schema-registry:9090/api/v1";
		KafkaSerializationSchema<EveRecord> schema = ClouderaRegistryKafkaSerializationSchema
				.<EveRecord>builder("eve-1")/* .setRegistryAddress(schemaRegistryAddress) */
				.setConfig(schemaRegistryConf).setKey(EveRecord::getDeviceId).build();

		FlinkKafkaProducer<EveRecord> kafkaSink = new FlinkKafkaProducer<>("eve-1", schema, props,
				FlinkKafkaProducer.Semantic.AT_LEAST_ONCE);

		DataStream<EveRecord> input = env.addSource(new DataGeneratorSource()).name("EVE Generator Source");

		input.addSink(kafkaSink).name("Kafka Sink").uid("Kafka Sink");

		input.print();

		env.execute("Avro EVE Generator Job");
	}

	/**
	 * Generates EveRecord objects with fix content at random interval.
	 */
	public static class DataGeneratorSource implements ParallelSourceFunction<EveRecord> {

		private volatile boolean isRunning = true;
		private ArrayList<Label> label_list = new ArrayList<Label>();

		@Override
		public void run(SourceContext<EveRecord> ctx) throws Exception {
			ThreadLocalRandom rnd = ThreadLocalRandom.current();
			String timestamp = "1619778809.0177128";
			double value = -1.8017399486024122;
			String unit = "random";
			String device_id = "vnf-1";
			Label label1 = new Label("value1", "1");
			Label label2 = new Label("value2", "2");

			label_list.add(label1);
			label_list.add(label2);
			Labels labels = new Labels(label_list);

			while (isRunning) {
				synchronized (ctx.getCheckpointLock()) {
					ctx.collect(new EveRecord(timestamp, value, unit, device_id, labels));
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
