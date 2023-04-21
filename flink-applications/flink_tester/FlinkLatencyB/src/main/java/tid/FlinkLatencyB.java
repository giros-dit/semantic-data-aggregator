
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



        // This function can be used to directly caclulate the flowHash instead of calculating it
        // With other script
        // public static Integer flowHash(Netflow netflowv9){
        //      // As there is only one record there is no need to iterate over FlowDataRecord
    //     FlowDataRecord flow = netflowv9.getExportPacket().getFlowDataRecord().get(0);

    //     // add to array
    //     ArrayList<String> tuple6 = new ArrayList<String>();
    //     // Add IPs
    //     tuple6.add(flow.getIpv4().getSrcAddress().getValue());
    //     tuple6.add(flow.getIpv4().getDstAddress().getValue());


    //     // Add ports sorted ascendant
    //     Integer srcPort = flow.getSrcPort().getValue().intValue();
    //     Integer dstPort = flow.getDstPort().getValue().intValue();
    //     tuple6.add(srcPort.toString());
        //      tuple6.add(dstPort.toString());

    //     // Add start and End of the flows
    //     tuple6.add(flow.getFirstSwitched().toString());
    //     tuple6.add(flow.getLastSwitched().toString());

    //     // concatenate strings
    //     String joined = String.join("-", tuple6);

        //      // keyBy function is going to hash it so it is not necessary to do a hash here
        //      return joined.hashCode();
        // }



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
