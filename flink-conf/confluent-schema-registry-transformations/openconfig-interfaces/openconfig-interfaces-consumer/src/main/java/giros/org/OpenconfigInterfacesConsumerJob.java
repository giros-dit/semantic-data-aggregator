package giros.org;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.formats.avro.registry.confluent.ConfluentRegistryAvroDeserializationSchema;
import org.apache.flink.formats.avro.registry.confluent.ConfluentRegistryAvroSerializationSchema;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer;
import org.apache.flink.streaming.connectors.kafka.KafkaDeserializationSchema;

import giros.org.Interfaces;
import org.apache.avro.specific.SpecificRecordBase;

import java.util.Properties;

public class OpenconfigInterfacesConsumerJob {

	public static void main(String[] args) throws Exception {

		Properties props = new Properties();
		props.setProperty("bootstrap.servers", "kafka:9092");
		props.setProperty("group.id", "telemetry-source");
		String schemaRegistryUrl = "http://schema-registry-confluent:8081";

		StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

		DataStreamSource<Interfaces> input = env.addSource(new FlinkKafkaConsumer<>("telemetrysource-1",
				ConfluentRegistryAvroDeserializationSchema.forSpecific(Interfaces.class, schemaRegistryUrl), props));

		SingleOutputStreamOperator<String> mapToString = input.name("Kafka Source").uid("Kafka Source")
                                //.map(record -> record.getInterface$().get(0).getName() + "," + record.getInterface$().get(0).getState().getCounters().getInOctets().intValue())
                                .map((MapFunction<Interfaces, String>) SpecificRecordBase::toString);

		FlinkKafkaProducer<String> stringFlinkKafkaProducer = new FlinkKafkaProducer<>(
				"raw-telemetrysource-1", new SimpleStringSchema(), props);

		mapToString.addSink(stringFlinkKafkaProducer).name("Kafka Sink").uid("Kafka Sink");

		env.execute("Avro Openconfig Interfaces Consumer Job");
	}
}

