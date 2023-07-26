package tid;

import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.functions.FilterFunction;
// FLINK imports
import org.apache.flink.api.common.serialization.DeserializationSchema;
import org.apache.flink.api.common.serialization.SerializationSchema;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.connector.base.DeliveryGuarantee;
import org.apache.flink.connector.kafka.sink.KafkaRecordSerializationSchema;
import org.apache.flink.connector.kafka.sink.KafkaSink;
import org.apache.flink.connector.kafka.source.KafkaSource;
import org.apache.flink.connector.kafka.source.enumerator.initializer.OffsetsInitializer;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.assigners.GlobalWindows;
import org.apache.flink.streaming.api.windowing.triggers.PurgingTrigger;
import org.apache.flink.streaming.api.windowing.windows.GlobalWindow;
import org.apache.flink.streaming.connectors.kafka.partitioner.FlinkKafkaPartitioner;
import org.apache.kafka.common.TopicPartition;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.IpVersionType;
// GENERATED-SOURCES imports
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.Netflow;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.netflow.export.packet.FlowDataRecord;
import org.xml.sax.SAXException;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
// JAVA imports
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;

/**
 * This Flink Streaming application takes unidirectional flows and transform
 * them into bidirectional flows. It is used when flows are received from OpenSource project Goflow2, so
 * FlowIdHash do not need to iterate over FlowDataRecord because there will be only one record.
 * This application is designed for YANG-based XML data encoding format.
 */
public class Netflow2Bidirectional {

	/**
	 * Two unidirectional flows that come from the same flow will have the same
	 * TimeFlowStart and TimeFlowEnd.
	 * Also, values for flow1.srcAddress == flow2.dstAddress, same for ports. So to
	 * identify a two unidirectional flows, the Id will be the hash of these values ordered alphabetically, then
	 * the Id will be used as key for the stream.
	 */
	public static String FlowIdHash(Netflow netflowv9) {
		// As there is only one record there is no need to iterate over FlowDataRecord
		FlowDataRecord flow = netflowv9.getExportPacket().getFlowDataRecord().get(0);

		// add to array
		ArrayList<String> tuple6 = new ArrayList<String>();
		// Add IPs
		if (flow.getIpVersion() == IpVersionType.Ipv4) {
			tuple6.add(flow.getIpv4().getSrcAddress().getValue());
			tuple6.add(flow.getIpv4().getDstAddress().getValue());
		} else {
			if (flow.getIpVersion() == IpVersionType.Ipv6) {
				tuple6.add(flow.getIpv6().getSrcAddress().getValue());
				tuple6.add(flow.getIpv6().getDstAddress().getValue());
			}
		}
		// sort IPs
		java.util.Collections.sort(tuple6);

		// Add ports sorted ascendant
		Integer srcPort = flow.getSrcPort().getValue().intValue();
		Integer dstPort = flow.getDstPort().getValue().intValue();
		if (srcPort <= dstPort) {
			tuple6.add(srcPort.toString());
			tuple6.add(dstPort.toString());
		} else {
			tuple6.add(dstPort.toString());
			tuple6.add(srcPort.toString());
		}

		// Add start and End of the flows
		tuple6.add(flow.getFirstSwitched().toString());
		tuple6.add(flow.getLastSwitched().toString());

		// concatenate strings
		String joined = String.join("-", tuple6);

		// keyBy function is going to hash it so it is not necessary to do a hash here
		return joined;
	}

	public static class NonSerializableFilter implements FilterFunction<String> {

		// make sure only valid values are processed, then filter by permissible
		// sources (netflow)
		@Override
		public boolean filter(String netflowString) throws Exception {

			Netflow value = StringNetflowSerialization.String2Netflow(netflowString);

			return value != null;
		}

	}

	public static void main(String[] args) throws Exception {

		if (args.length == 5) {
			multitenants(args);
		} else {
			if (args.length == 3) {
				singletenant(args);
			} else {
				System.exit(1);
			}
		}

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
				.setGroupId("netflow-aggregation-group")
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

		// Create the key for the different partitions of the flow
		// each flow will go to a different partition
		KeySelector<String, String> keysel = new KeySelector<String, String>() {
			public String getKey(String netflowString) throws SAXException, IOException, ParserConfigurationException, XMLStreamException {
				return FlowIdHash(StringNetflowSerialization.String2Netflow(netflowString));
			}
		};

		// LOGIC FOR UNI2BIDIRECTIONAL TRANSFORMATION --->
		dss.filter(new NonSerializableFilter())
				.keyBy(keysel)
				.window(GlobalWindows.create())
				.trigger(PurgingTrigger.of(new CountWithTimeoutTrigger<GlobalWindow>(2, 2500))) // PurgingTrigger makes
																								// FIRE_AND_PURGE
				.process(new Uni2BidiWindowFunc())
				.sinkTo(producer);
		// <--- LOGIC FOR UNI2BIDIRECTIONAL TRANSFORMATION

		// EXECUTE THE APPLICATION
		environment.execute("Netflow2Bidirectional");
	}

	public static void singletenant(String[] args) throws Exception {
		
		// GET EXECUTION ENVIRONMENT
		StreamExecutionEnvironment environment = StreamExecutionEnvironment.getExecutionEnvironment();

		// KAFKA CONSUMER
		KafkaSource<String> consumer = KafkaSource.<String>builder()
				.setTopics(args[1])
				.setGroupId("netflow-aggregation-group")
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

		// Create the key for the different partitions of the flow
		// each flow will go to a different partition
		KeySelector<String, String> keysel = new KeySelector<String, String>() {
			public String getKey(String netflowString) throws SAXException, IOException, ParserConfigurationException, XMLStreamException {
				return FlowIdHash(StringNetflowSerialization.String2Netflow(netflowString));
			}
		};

		// LOGIC FOR UNI2BIDIRECTIONAL TRANSFORMATION --->
		dss.filter(new NonSerializableFilter())
				.keyBy(keysel)
				.window(GlobalWindows.create())
				.trigger(PurgingTrigger.of(new CountWithTimeoutTrigger<GlobalWindow>(2, 2500))) // PurgingTrigger makes
																								// FIRE_AND_PURGE
				.process(new Uni2BidiWindowFunc())
				.sinkTo(producer);
		// <--- LOGIC FOR UNI2BIDIRECTIONAL TRANSFORMATION

		// EXECUTE THE APPLICATION
		environment.execute("Netflow2Bidirectional");
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
