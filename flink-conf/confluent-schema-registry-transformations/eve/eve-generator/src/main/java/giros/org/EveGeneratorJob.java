package giros.org;

import org.apache.flink.formats.avro.registry.confluent.ConfluentRegistryAvroSerializationSchema;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.source.ParallelSourceFunction;
import org.apache.flink.streaming.api.functions.source.SourceFunction.SourceContext;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer;

import giros.org.EveRecord;

import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Generates random EveRecords to a Kafka topic.
 */
public class EveGeneratorJob {

	public static void main(String[] args) throws Exception {
		Properties props = new Properties();
		props.setProperty("bootstrap.servers", "kafka:9092");
		props.setProperty("group.id", "eve-source");
		String schemaRegistryUrl = "http://schema-registry-confluent:8081";
		String subject = "eve-1";

		StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

		FlinkKafkaProducer<EveRecord> avroFlinkKafkaProducer = new FlinkKafkaProducer<>("eve-1",
				ConfluentRegistryAvroSerializationSchema.forSpecific(EveRecord.class, subject, schemaRegistryUrl),
				props);
		DataStream<EveRecord> input = env.addSource(new DataGeneratorSource()).name("EVE Generator Source");
		input.addSink(avroFlinkKafkaProducer);

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
