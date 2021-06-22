package giros.org;

import org.apache.flink.formats.avro.registry.confluent.ConfluentRegistryAvroSerializationSchema;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.source.ParallelSourceFunction;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer;

import giros.org.SoInstantiationMetrics;

import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Generates random 5Gr-SO Log Parser metrics to a Kafka topic.
 */
public class SoLogParserGeneratorJob {

	public static void main(String[] args) throws Exception {
		Properties props = new Properties();
		props.setProperty("bootstrap.servers", "kafka:9092");
		props.setProperty("group.id", "so-logs");
		String schemaRegistryUrl = "http://schema-registry-confluent:8081";
		String subject = "so-instantiation-metrics";

		StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

		FlinkKafkaProducer<SoInstantiationMetrics> avroFlinkKafkaProducer = new FlinkKafkaProducer<>("so-instantiation-metrics",
				ConfluentRegistryAvroSerializationSchema.forSpecific(SoInstantiationMetrics.class, subject, schemaRegistryUrl), props);
		DataStream<SoInstantiationMetrics> input = env.addSource(new DataGeneratorSource()).name("SO Log Parser Generator Source");
		input.addSink(avroFlinkKafkaProducer);

		env.execute("Avro SO Log Parser Generator Job");
	}

	/**
	 * Generates SoInstatiationMetrics objects with fix content at random interval.
	 */
	public static class DataGeneratorSource implements ParallelSourceFunction<SoInstantiationMetrics> {

		private volatile boolean isRunning = true;

		@Override
		public void run(SourceContext<SoInstantiationMetrics> ctx) throws Exception {
			ThreadLocalRandom rnd = ThreadLocalRandom.current();
			String current_time = "2021-05-17T07:53:13Z";
			String operation = "instantiation";
			String nsID = "fgt-a3f272b-9af6-4ba5-ae52-7fbea8465413";
			String nsdID = "vCDN_aiml";
			long total_instantiation_time = 42631;
		    long SOE_time = 1637;
		    long ROE_time = 2331;
			long operation_ID_for_instantiation_op_datetime_difference = 4;
			long hierarchical_SOE_dispatching_datetime_difference = 6;
			long ROE_created_VLs_start_datetime_difference = 3;
			long ROE_retrieve_RL_resources_start_datetime_difference = 2310;
			long ROE_parsing_NSDs_start_datetime_difference = 0;
			long ROE_updating_DBs_start_datetime_difference = 12;
			long ROE_extract_VLs_start_datetime_difference = 5;
			long retrieving_descriptor_from_catalogue_DBs_start_datetime_difference = 4;
			long PA_calculation_start_datetime_difference = 1;
		    long create_threshold_based_alerts_start_datetime_difference = 9;
		    long create_monitoring_jobs_start_datetime_difference = 144;
		    long create_AIML_alerts_start_datetime_difference = 1474;
		    long CoreMANO_wrapper_time = 38661;
			while (isRunning) {
				synchronized (ctx.getCheckpointLock()) {
					ctx.collect(new SoInstantiationMetrics(current_time, operation, nsID, nsdID, total_instantiation_time, SOE_time, ROE_time, operation_ID_for_instantiation_op_datetime_difference, hierarchical_SOE_dispatching_datetime_difference, ROE_created_VLs_start_datetime_difference, ROE_retrieve_RL_resources_start_datetime_difference, ROE_parsing_NSDs_start_datetime_difference, ROE_updating_DBs_start_datetime_difference, ROE_extract_VLs_start_datetime_difference, retrieving_descriptor_from_catalogue_DBs_start_datetime_difference, PA_calculation_start_datetime_difference, create_threshold_based_alerts_start_datetime_difference, create_monitoring_jobs_start_datetime_difference, create_AIML_alerts_start_datetime_difference, CoreMANO_wrapper_time));
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
