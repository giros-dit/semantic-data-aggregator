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

import giros.org.Metric;
import org.apache.avro.specific.SpecificRecordBase;

import java.util.Properties;

public class PrometheusConsumerJob {

	public static void main(String[] args) throws Exception {

		Properties props = new Properties();
		props.setProperty("bootstrap.servers", "kafka:9092");
		props.setProperty("group.id", "metric-source");
		String schemaRegistryUrl = "http://schema-registry-confluent:8081";

		StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

		DataStreamSource<Metric> input = env.addSource(new FlinkKafkaConsumer<>("metricsource-2",
				ConfluentRegistryAvroDeserializationSchema.forSpecific(Metric.class, schemaRegistryUrl), props));

		SingleOutputStreamOperator<String> mapToString = input.name("Kafka Source").uid("Kafka Source")
                                //.map(record -> record.getName() + "," + record.getValue() + "," + record.getTimestamp())
                                //.map(record -> record.getSchema().toString())
                                .map((MapFunction<Metric, String>) SpecificRecordBase::toString);

		FlinkKafkaProducer<String> stringFlinkKafkaProducer = new FlinkKafkaProducer<>(
				"raw-metricsource-2", new SimpleStringSchema(), props);

		mapToString.addSink(stringFlinkKafkaProducer).name("Kafka Sink").uid("Kafka Sink");

		env.execute("Avro Prometheus Consumer Job");
	}
}

