package tid;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;

import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.serialization.DeserializationSchema;
import org.apache.flink.api.common.serialization.SerializationSchema;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.connector.base.DeliveryGuarantee;
import org.apache.flink.connector.kafka.sink.KafkaRecordSerializationSchema;
import org.apache.flink.connector.kafka.sink.KafkaSink;
import org.apache.flink.connector.kafka.source.KafkaSource;
import org.apache.flink.connector.kafka.source.enumerator.initializer.OffsetsInitializer;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.partitioner.FlinkKafkaPartitioner;
import org.apache.kafka.common.TopicPartition;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.Netflow;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * Flink Streaming Application with NetFlow YANGTools Consumer Driver to
 * serialize the Netflow data (including the aggregated features) according to the CSV schema “Raw Netflow Data +
 * Aggregated features” suitable for the DCP PALANTIR component.
 * This application is designed for YANG-based JSON data encoding format.
 */
public class Netflow2DCP {

	public static class Serialize2DCPMap implements MapFunction<String, String> {

		@Override
		public String map(String input) throws Exception {

			// get Object of class Netflow from the input (JSON-IETF)
			Netflow netflowv9_netflow = StringNetflowSerialization.String2Netflow(input);

			String serialized = "";

			// Map the values in the Netflow Object to confluence csv
			try {
				serialized = StringNetflowSerialization.NetflowToDCP(netflowv9_netflow);
			} catch (NullPointerException e) {
				System.out.println("EXCEPTION: One of the fields is Null");
			} catch (Exception e) {
				e.printStackTrace();
			}

			return serialized;
		}
	}

	public static class NonSerializableFilter implements FilterFunction<String> {

		// make sure only valid json values are processed, then filter by permissible
		// sources (netflow)
		@Override
		public boolean filter(String netflowString) throws Exception {

			Netflow value = StringNetflowSerialization.String2Netflow(netflowString);

			return value != null;
		}

	}

	public static void main(String[] args) throws Exception {

		if(args.length == 5){
            multitenants(args);
        } else {
            if(args.length == 3){
              singletenant(args);
            }else{
                System.exit(1);
            }
        }
		
	}

	public static void singletenant(String[] args) throws Exception {

		// GET EXECUTION ENVIRONMENT
		StreamExecutionEnvironment environment = StreamExecutionEnvironment.getExecutionEnvironment();

		// KAFKA CONSUMER
		KafkaSource<String> consumer = KafkaSource.<String>builder()
				.setTopics(args[1])
				.setGroupId("netflow-consumer-group")
				.setBootstrapServers(args[0])
				.setStartingOffsets(OffsetsInitializer.latest())
				.setValueOnlyDeserializer((DeserializationSchema<String>) new SimpleStringSchema())
				.build();

		// KAFKA PRODUCER
		KafkaSink<String> producer = KafkaSink
				.<String>builder()
				.setBootstrapServers(args[0])
				.setRecordSerializer(KafkaRecordSerializationSchema.builder()
						.setTopic(args[2])
						.setValueSerializationSchema((SerializationSchema<String>) new SimpleStringSchema())
						.build())
				.setDeliverGuarantee(DeliveryGuarantee.AT_LEAST_ONCE)
				.build();

		// SOURCE DATASTREAM
		DataStreamSource<String> dss = environment.fromSource(consumer, WatermarkStrategy.noWatermarks(),
				"Kafka Source");

		DataStream<String> serializedds = dss
				.filter(new NonSerializableFilter())
				.map(new Serialize2DCPMap())
				.filter(new FilterFunction<String>() {
					// make sure only valid json values are processed, then filter by permissible
					// sources (netflow)
					@Override
					public boolean filter(String value) throws Exception {
						// if empty do not return
						return !value.equals("");
					}
				});

		serializedds.sinkTo(producer);

		environment.execute("Netflow2DCP");
	}

	public static void multitenants(String[] args) throws Exception {

		int topic_partition = getPartition(args[3], args[4]);

		FlinkKafkaPartitioner<String> customPartitioner = new MyCustomPartitioner(topic_partition);

		// GET EXECUTION ENVIRONMENT
		StreamExecutionEnvironment environment = StreamExecutionEnvironment.getExecutionEnvironment();

		final HashSet<TopicPartition> consumerPartitionSet = new HashSet<>(Arrays.asList(
				new TopicPartition(args[1], topic_partition)));

		// KAFKA CONSUMER
		KafkaSource<String> consumer = KafkaSource.<String>builder()
				.setPartitions(consumerPartitionSet)
				.setGroupId("netflow-consumer-group")
				.setBootstrapServers(args[0])
				.setStartingOffsets(OffsetsInitializer.latest())
				.setValueOnlyDeserializer((DeserializationSchema<String>) new SimpleStringSchema())
				.build();

		// KAFKA PRODUCER
		KafkaSink<String> producer = KafkaSink
				.<String>builder()
				.setBootstrapServers(args[0])
				.setRecordSerializer(KafkaRecordSerializationSchema.builder()
						.setTopic(args[2])
						.setPartitioner(customPartitioner)
						.setValueSerializationSchema((SerializationSchema<String>) new SimpleStringSchema())
						.build())
				.setDeliverGuarantee(DeliveryGuarantee.AT_LEAST_ONCE)
				.build();

		// SOURCE DATASTREAM
		DataStreamSource<String> dss = environment.fromSource(consumer, WatermarkStrategy.noWatermarks(),
				"Kafka Source");

		DataStream<String> serializedds = dss
				.filter(new NonSerializableFilter())
				.map(new Serialize2DCPMap())
				.filter(new FilterFunction<String>() {
					// make sure only valid json values are processed, then filter by permissible
					// sources (netflow)
					@Override
					public boolean filter(String value) throws Exception {
						// if empty do not return
						return !value.equals("");
					}
				});

		serializedds.sinkTo(producer);

		environment.execute("Netflow2DCP");
	}

	/**
	 * Method for obtaining the topic partition associated with a tenant id by
	 * making a call to the tenant service.
	 * 
	 * @param tenant_service_url
	 * @param tenant_id
	 * @return
	 */
	public static int getPartition(String tenant_service_url, String tenant_id) {
		int topic_partition = 0;
		try {
			// URL of tenant-service
			URL url = new URL(tenant_service_url + tenant_id);

			// Opening HTTP connection
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setConnectTimeout(2000);
			connection.setRequestMethod("GET");

			int responseCode = connection.getResponseCode();

			if (responseCode >= 200 && responseCode <= 299) {
				// Reading server response
				BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String line;
				StringBuilder response = new StringBuilder();
				while ((line = reader.readLine()) != null) {
					response.append(line);
				}

				Gson json = new Gson();
				JsonObject jsonObject = json.fromJson(response.toString(), JsonObject.class);
				String partition = jsonObject.get("partition").getAsString();

				reader.close();

				topic_partition = Integer.parseInt(partition);
			} else {
				System.exit(1);
			}

			// Closing connection
			connection.disconnect();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return topic_partition;
	}

	/**
     * Custom implementation of FlinkKafkaPartitioner
     */
	public static class MyCustomPartitioner extends FlinkKafkaPartitioner<String> {

		private int partition;

		MyCustomPartitioner(int partition) {
			this.partition = partition;
		}

		@Override
		public int partition(String record, byte[] key, byte[] value, String targetTopic, int[] partitions) {

			return partition;
		}
	}

}
