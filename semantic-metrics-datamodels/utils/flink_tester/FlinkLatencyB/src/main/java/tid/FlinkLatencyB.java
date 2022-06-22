
package tid;

import org.apache.flink.api.common.eventtime.WatermarkStrategy;
// FLINK imports
import org.apache.flink.api.common.serialization.DeserializationSchema;
import org.apache.flink.api.common.serialization.SerializationSchema;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.connector.base.DeliveryGuarantee;
import org.apache.flink.connector.kafka.sink.KafkaRecordSerializationSchema;
import org.apache.flink.connector.kafka.sink.KafkaSink;
import org.apache.flink.connector.kafka.source.KafkaSource;
import org.apache.flink.connector.kafka.source.enumerator.initializer.OffsetsInitializer;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.ProcessFunction;
import org.apache.flink.util.Collector;


/**
 * This Flink Streaming application it is used to measure the latency of another flink application, it is meant to be used with FlinkLatencyB
 */
public class FlinkLatencyB {


	public static void main(String[] args) throws Exception {

		// GET EXECUTION ENVIRONMENT
		StreamExecutionEnvironment environment = StreamExecutionEnvironment.getExecutionEnvironment();


		// KAFKA CONSUMER
		KafkaSource<String> consumer = KafkaSource.<String>builder()
		.setTopics(args[1])
		.setGroupId("netflow-testing-group")
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
		DataStreamSource<String> mainDataStream =  environment.fromSource(consumer, WatermarkStrategy.noWatermarks(), "Kafka Source");

		mainDataStream.process(new ProcessFunction<String, String>() {

			@Override
			public void processElement(String netflowString, Context ctx, Collector<String> out) throws Exception {

				// First thing is to take the timestamp of the event
				Long timeEnd = System.nanoTime();
				
				// calculate hash
				Integer hash = netflowString.hashCode();

				// emit hash and timestamp
				out.collect(hash.toString() + "," + timeEnd);
	
			}

		}).sinkTo(producer);

		// EXECUTE THE APPLICATION
		environment.execute("FlinkLatencyB");
	}

}
