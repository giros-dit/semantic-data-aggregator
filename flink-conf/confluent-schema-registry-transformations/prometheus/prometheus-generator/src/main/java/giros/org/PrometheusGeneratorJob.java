package giros.org;

import org.apache.flink.formats.avro.registry.confluent.ConfluentRegistryAvroSerializationSchema;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.source.ParallelSourceFunction;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer;

import giros.org.Metric;

import java.awt.List;
import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Generates random Metrics to a Kafka topic.
 */
public class PrometheusGeneratorJob {

	public static void main(String[] args) throws Exception {
		Properties props = new Properties();
		props.setProperty("bootstrap.servers", "kafka:9092");
		props.setProperty("group.id", "metric-source");
		String schemaRegistryUrl = "http://schema-registry-confluent:8081";
		String subject = "metricsource-2";

		StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

		FlinkKafkaProducer<Metric> avroFlinkKafkaProducer = new FlinkKafkaProducer<>("metricsource-2",
				ConfluentRegistryAvroSerializationSchema.forSpecific(Metric.class, subject, schemaRegistryUrl), props);
		DataStream<Metric> input = env.addSource(new DataGeneratorSource()).name("Prometheus Generator Source");
		input.addSink(avroFlinkKafkaProducer);

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
