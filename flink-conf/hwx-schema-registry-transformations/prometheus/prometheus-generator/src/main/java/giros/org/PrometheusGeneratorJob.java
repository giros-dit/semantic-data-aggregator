package giros.org;

import org.apache.flink.formats.avro.registry.cloudera.ClouderaRegistryKafkaSerializationSchema;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.source.ParallelSourceFunction;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer;
import org.apache.flink.streaming.connectors.kafka.KafkaSerializationSchema;

import giros.org.Metric;
import org.apache.commons.lang3.RandomStringUtils;

import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Generates random Metrics to a Kafka topic.
 */
public class PrometheusGeneratorJob {

	public static void main(String[] args) throws Exception {
		Properties props = new Properties();
		props.put("bootstrap.servers", "kafka:9092");
		props.put("group.id", "metric-source");
		StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

		Map<String, Object> schemaRegistryConf = new HashMap<>();

		schemaRegistryConf.put("schema.registry.url", "http://schema-registry:9090/api/v1");
		// String schemaRegistryAddress = "http://schema-registry:9090/api/v1";
		KafkaSerializationSchema<Metric> schema = ClouderaRegistryKafkaSerializationSchema
				.<Metric>builder("metricsource-2")/* .setRegistryAddress(schemaRegistryAddress) */
				.setConfig(schemaRegistryConf).setKey(Metric::getName).build();

		FlinkKafkaProducer<Metric> kafkaSink = new FlinkKafkaProducer<>("metricsource-2", schema, props,
				FlinkKafkaProducer.Semantic.AT_LEAST_ONCE);

		DataStream<Metric> input = env.addSource(new DataGeneratorSource()).name("Prometheus Generator Source");

		input.addSink(kafkaSink).name("Kafka Sink").uid("Kafka Sink");

		input.print();

		env.execute("Avro Prometheus Generator Job");
	}

	/**
	 * Generates Metric objects with random content at random interval.
	 */
	public static class DataGeneratorSource implements ParallelSourceFunction<Metric> {

		private volatile boolean isRunning = true;
		private ArrayList<Label> label_list = new ArrayList<Label>();
		@Override
		public void run(SourceContext<Metric> ctx) throws Exception {
			ThreadLocalRandom rnd = ThreadLocalRandom.current();
                        Label label1 = new Label("device", "eth0");
			Label label2 = new Label("instance", "node-exporter-1:9100");
                        Label label3 = new Label("job", "node-1");
			label_list.add(label1);
			label_list.add(label2);
                        label_list.add(label3);
			Labels labels = new Labels(label_list);
			while (isRunning) {
				synchronized (ctx.getCheckpointLock()) {
					ctx.collect(new Metric("1620651516.921", 8184.000, "node_network_transmit_packets_total", labels));
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

