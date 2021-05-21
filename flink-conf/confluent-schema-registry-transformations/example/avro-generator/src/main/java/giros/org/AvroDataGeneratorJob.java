package giros.org;

import org.apache.flink.formats.avro.registry.confluent.ConfluentRegistryAvroSerializationSchema;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.source.ParallelSourceFunction;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer;

import giros.org.Message;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Generates random Messages to a Kafka topic.
 */
public class AvroDataGeneratorJob {

	public static void main(String[] args) throws Exception {
		Properties props = new Properties();
		props.setProperty("bootstrap.servers", "kafka:9092");
		props.setProperty("group.id", "example");
		String schemaRegistryUrl = "http://schema-registry-confluent:8081";
		String subject = "message";

		StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

		FlinkKafkaProducer<Message> avroFlinkKafkaProducer = new FlinkKafkaProducer<>("message",
				ConfluentRegistryAvroSerializationSchema.forSpecific(Message.class, subject, schemaRegistryUrl), props);
		DataStream<Message> input = env.addSource(new DataGeneratorSource()).name("Data Generator Source");
		input.addSink(avroFlinkKafkaProducer);

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
