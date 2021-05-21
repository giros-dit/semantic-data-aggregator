package giros.org;

import org.apache.flink.formats.avro.registry.confluent.ConfluentRegistryAvroSerializationSchema;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.source.ParallelSourceFunction;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer;

import giros.org.Interfaces;

import java.awt.List;
import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Generates random telemetry data to a Kafka topic.
 */
public class OpenconfigInterfacesGeneratorJob {

	public static void main(String[] args) throws Exception {
		Properties props = new Properties();
		props.setProperty("bootstrap.servers", "kafka:9092");
		props.setProperty("group.id", "telemetry-source");
		String schemaRegistryUrl = "http://schema-registry-confluent:8081";
		String subject = "telemetrysource-1";

		StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

		FlinkKafkaProducer<Interfaces> avroFlinkKafkaProducer = new FlinkKafkaProducer<>("telemetrysource-1",
				ConfluentRegistryAvroSerializationSchema.forSpecific(Interfaces.class, subject, schemaRegistryUrl), props);
		DataStream<Interfaces> input = env.addSource(new DataGeneratorSource()).name("OpenconfigInterfaces Generator Source");
		input.addSink(avroFlinkKafkaProducer);

		env.execute("Avro Openconfig Interfaces Generator Job");
	}

        /**
         * Generates Interfaces objects with fix content at random interval.
         */
        public static class DataGeneratorSource implements ParallelSourceFunction<Interfaces> {

                private volatile boolean isRunning = true;
                private ArrayList<Interface> interface_list = new ArrayList<Interface>();

                @Override
                public void run(SourceContext<Interfaces> ctx) throws Exception {
                        ThreadLocalRandom rnd = ThreadLocalRandom.current();

                        Counters counters1 = new Counters((long)10, null, null, null, null);
                        //Counters counters2 = new Counters((long)11, null, null, null, null);
                        State state1 = new State(null, null, null, null, null, null, counters1);
                        //State state2 = new State(null, null, null, null, null, null, counters2);
                        //Config config1 = new Config(name, type, mtu, loopback_mode, description, enabled);
                        //Config config2 = new Config(name, type, mtu, loopback_mode, description, enabled);
                        Interface interface1 = new Interface("Ethernet1", null, state1);
                        //Interface interface2 = new Interface("Ethernet2", null, state2);
                        interface_list.add(interface1);
                        //interface_list.add(interface2);
                        while (isRunning) {
                                synchronized (ctx.getCheckpointLock()) {
                                        ctx.collect(new Interfaces(interface_list));
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
