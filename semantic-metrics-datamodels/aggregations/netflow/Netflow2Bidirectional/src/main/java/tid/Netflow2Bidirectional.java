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
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.IpVersionType;
// GENERATED-SOURCES imports
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.Netflow;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.netflow.export.packet.FlowDataRecord;



// JAVA imports
import java.util.ArrayList;


/**
 * This Flink Streaming application takes unidirectional flows and transform them into bidirectional 
 * flows. It is used when flows are received from OpenSource project Goflow2, so FlowIdHash do not 
 * need to iterate over FlowDataRecord because there will be only one record.
 */
public class Netflow2Bidirectional {


	/**
	 * Two unidirectional flows that come from the same flow will have the same TimeFlowStart and TimeFlowEnd.
	 * Also, values for flow1.srcAddress == flow2.dstAddress, same for ports. So to identify a two unidirectional 
	 * flows, the Id will be the hash of these values ordered alphabetically, then the Id will be used as 
	 * key for the stream.
	 */
	public static String FlowIdHash(Netflow netflowv9){
		// As there is only one record there is no need to iterate over FlowDataRecord
        FlowDataRecord flow = netflowv9.getExportPacket().getFlowDataRecord().get(0);
        
        // add to array
        ArrayList<String> tuple6 = new ArrayList<String>();
        // Add IPs
		if(flow.getIpVersion() == IpVersionType.Ipv4){
			tuple6.add(flow.getIpv4().getSrcAddress().getValue());
			tuple6.add(flow.getIpv4().getDstAddress().getValue());
		} else{
			if(flow.getIpVersion() == IpVersionType.Ipv6){
				tuple6.add(flow.getIpv6().getSrcAddress().getValue());
				tuple6.add(flow.getIpv6().getDstAddress().getValue());
			}
		}
        // sort IPs
        java.util.Collections.sort(tuple6);

        // Add ports sorted ascendant
        Integer srcPort = flow.getSrcPort().getValue().intValue();
        Integer dstPort = flow.getDstPort().getValue().intValue();
        if(srcPort <= dstPort){
            tuple6.add(srcPort.toString());
            tuple6.add(dstPort.toString());
        }else{
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
	
		// make sure only valid json values are processed, then filter by permissible sources (ceos, prometheus, netflow)
		@Override
		public boolean filter(String netflowString) throws Exception {

			Netflow value = StringNetflowSerialization.String2Netflow(netflowString);

			return value!=null;
		}

	}

	public static void main(String[] args) throws Exception {

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

		// Create the key for the different partitions of the flow
		// each flow will go to a different partition
		KeySelector<String, String> keysel = new KeySelector<String, String>(){
			public String getKey(String netflowString){
				return FlowIdHash(StringNetflowSerialization.String2Netflow(netflowString));
			}
		};

		// LOGIC FOR UNI2BIDIRECTIONAL TRANSFORMATION --->
		dss.filter(new NonSerializableFilter())
		.keyBy(keysel)
		.window(GlobalWindows.create())
		.trigger(PurgingTrigger.of(new CountWithTimeoutTrigger<GlobalWindow>(2, 2500))) // PurgingTrigger makes FIRE_AND_PURGE
		.process(new Uni2BidiWindowFunc())
		.sinkTo(producer);
		// <--- LOGIC FOR UNI2BIDIRECTIONAL TRANSFORMATION

		// EXECUTE THE APPLICATION
		environment.execute("Netflow2Bidirectional");
	}

}
