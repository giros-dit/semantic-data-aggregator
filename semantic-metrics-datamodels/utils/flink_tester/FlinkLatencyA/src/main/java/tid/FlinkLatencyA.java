
package tid;

// JAVA imports

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
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.ProcessFunction;
import org.apache.flink.util.Collector;
import org.apache.flink.util.OutputTag;


/**
 * This Flink Streaming application it is used to measure the latency of another flink application, it is meant to be used with FlinkLatencyB
 */
public class FlinkLatencyA {



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


		// KAFKA SIDE TOPIC FOR MEASURES
		KafkaSink<String> sideTopic = KafkaSink
		.<String>builder()
		.setBootstrapServers(args[0])
		.setRecordSerializer(KafkaRecordSerializationSchema.builder()
		.setTopic(args[3])
		.setValueSerializationSchema((SerializationSchema<String>)new SimpleStringSchema())
		.build()
        )
		.setDeliverGuarantee(DeliveryGuarantee.AT_LEAST_ONCE)
		.build();


		// LOGIC OF THE APPLICATION

		final OutputTag<String> outputTag = new OutputTag<String>("side-output"){};
        

		// SOURCE DATASTREAM
		DataStreamSource<String> dss =  environment.fromSource(consumer, WatermarkStrategy.noWatermarks(), "Kafka Source");

		SingleOutputStreamOperator<String> mainDataStream = dss.process(new ProcessFunction<String, String>() {

			@Override
			public void processElement(String netflowString, Context ctx, Collector<String> out) throws Exception {


				// calculate the hash of the flow
				Integer hash = netflowString.hashCode();

				// get actual time and emit data to topic of the application to be tested
				Long startTime = System.nanoTime();
				out.collect(netflowString);
		
				// emit hash and timestamp to side output with CSV format
				ctx.output(outputTag, hash.toString() + "," + startTime.toString());
			}

		});

		mainDataStream.getSideOutput(outputTag).sinkTo(sideTopic);
		mainDataStream.sinkTo(producer);
		

		// EXECUTE THE APPLICATION
		environment.execute("FlinkLatencyA");
	}

}
