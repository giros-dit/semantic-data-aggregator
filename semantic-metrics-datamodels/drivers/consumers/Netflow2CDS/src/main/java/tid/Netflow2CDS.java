package tid;

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
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.Netflow;



/**
 * Flink Streaming Application with NetFlow YANGTools Consumer Driver to serialize the NetFlow data to CSV format.
 */
public class Netflow2CDS {


	public static class Serialize2CDSMap implements MapFunction<String, String> {

		@Override
		public String map(String input) throws Exception {

			// get Object of class Netflow from the input (JSON-IETF)
			Netflow netflowv9_netflow = StringNetflowSerialization.String2Netflow(input);

			String serialized = "";

			// Map the values in the Netflow Object to confluence csv
			try {
				serialized = StringNetflowSerialization.NetflowToCDS(netflowv9_netflow);
			} catch (NullPointerException e) {
				System.out.println("EXCEPTION: One of the fields is Null");
			} catch (Exception e) {
				e.printStackTrace();
			}

			return serialized;
		}
	}

	public static class NonSerializableFilter implements FilterFunction<String> {
	
		// make sure only valid json values are processed, then filter by permissible sources (ceos, prometheus, netflow)
		@Override
		public boolean filter(String netflowString) throws Exception {

			Netflow value = StringNetflowSerialization.String2Netflow(netflowString);

			return value!=null;
		}

	}


	public static void main(String[] args) throws Exception{

		// GET EXECUTION ENVIRONMENT
		StreamExecutionEnvironment environment = StreamExecutionEnvironment.getExecutionEnvironment();


		// KAFKA CONSUMER
		KafkaSource<String> consumer = KafkaSource.<String>builder()
		.setTopics(args[1])
		.setGroupId("netflow-aggregation-group")
		.setBootstrapServers(args[0])
		.setStartingOffsets(OffsetsInitializer.latest())
		.setValueOnlyDeserializer((DeserializationSchema<String>)new SimpleStringSchema())
		.build();


		// KAFKA PRODUCER
		KafkaSink<String> producer = KafkaSink
		.<String>builder()
		.setBootstrapServers(args[0])
		.setRecordSerializer(KafkaRecordSerializationSchema.builder()
		.setTopic(args[2])
		.setValueSerializationSchema((SerializationSchema<String>)new SimpleStringSchema())
		.build()
        )
		.setDeliverGuarantee(DeliveryGuarantee.AT_LEAST_ONCE)
		.build();


		// SOURCE DATASTREAM
		DataStreamSource<String> dss =  environment.fromSource(consumer, WatermarkStrategy.noWatermarks(), "Kafka Source");


		DataStream<String> serializedds = dss
		.filter(new NonSerializableFilter())
		.map(new Serialize2CDSMap())
		.filter(new FilterFunction<String>() {
			// make sure only valid json values are processed, then filter by permissible sources (ceos, prometheus, netflow)
			@Override
			public boolean filter(String value) throws Exception {
				// if empty do not return
				return !value.equals("");
			}
		});

		serializedds.sinkTo(producer);

		environment.execute("Netflow2CDS");

	}

}
