package upm.dit.giros;

import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.sink.SinkFunction;
import org.apache.flink.streaming.api.functions.source.SourceFunction;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Map;
import java.util.Collection;
import java.util.Iterator;

import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.functions.windowing.ProcessAllWindowFunction;
import org.apache.flink.streaming.api.windowing.windows.GlobalWindow;
import org.apache.flink.util.Collector;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer;
import org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.interfaces.top.Interfaces;
import org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.interfaces.top.InterfacesBuilder;
import org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.interfaces.top.interfaces.Interface;
import org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.interfaces.top.interfaces.InterfaceBuilder;
import org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.interfaces.top.interfaces.InterfaceKey;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715.DateAndTime;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715.Timestamp;

import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.openconfig.interfaces.aggregated.rev220513.Interface1;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.openconfig.interfaces.aggregated.rev220513.Interface1Builder;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.openconfig.interfaces.aggregated.rev220513.PerDecimal;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.openconfig.interfaces.aggregated.rev220513.interfaces._interface.PacketLossKpiNotificationBuilder;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.openconfig.interfaces.aggregated.rev220513.interfaces._interface.packet.loss.kpi.notification.PacketLossKpiBuilder;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.openconfig.interfaces.notification.wrapper.rev220513.Notification;

/**
 * Flink aggregation app for calculating Packet Loss KPI based on gNMI subscription to the operational state data 
 * related to the openconfig-interfaces YANG model.
 */
public class GnmiAggregatorPacketLoss {

	private static String duration;

	public static void main(String[] args) throws Exception {

		// Configuration of Kafka consumer and producer properties
		Properties props = new Properties();
		props.put("bootstrap.servers", args[0]);
		props.put("group.id", "gnmi-aggregation-group");
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

		// set up the streaming execution environment
		final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

		// Consume data stream from the Kafka input topic
        FlinkKafkaConsumer<String> consumer = new FlinkKafkaConsumer<String>(args[1], /*(DeserializationSchema<String>)*/new SimpleStringSchema(), props);

		//Produce data stream on the Kafka output topic
		FlinkKafkaProducer<String> producer = new FlinkKafkaProducer<String>(args[2], new SimpleStringSchema(), props);

		//Set interval duration to ms unit
		duration = String.valueOf(Integer.parseInt(args[3])/1000);
		
		DataStreamSource<String> dss  = env.addSource((SourceFunction<String>)consumer);
		dss.countWindowAll(2,1).process(new EventsAggregation(duration)).addSink((SinkFunction<String>) producer);
		
		// execute program
		env.execute("gNMI-related aggregation app for Packet Loss KPI");
	}

	private static Interfaces gnmiPacketLossAggregation(Interfaces interfaces1, Interfaces interfaces2, String duration){
		
		List<Interface> new_interface_list = new ArrayList<Interface>();
	
		Double iface1_in_packets = 0.0;
		Double iface1_in_discards = 0.0;
		Double iface1_out_packets = 0.0;
		Double iface1_out_discards = 0.0;
		Double iface2_in_packets = 0.0;
		Double iface2_in_discards = 0.0;
		Double iface2_out_packets = 0.0;
		Double iface2_out_discards = 0.0;
		Double count_in_packets = 0.0;
		Double count_out_packets = 0.0;
		Double count_in_discards = 0.0;
		Double count_out_discards = 0.0;
		
		for (Map.Entry<InterfaceKey, Interface> interface1_entry: interfaces1.getInterface().entrySet()) {
			InterfaceBuilder interface1Builder = new InterfaceBuilder(interface1_entry.getValue()); // create builder for adding aggregation for interface counters
			Interface1Builder interfaceAugmentationBuilder = new Interface1Builder(); // create builder for performing interface counter aggregations
			PacketLossKpiNotificationBuilder pl_kpi_noti_builder = new PacketLossKpiNotificationBuilder();
			PacketLossKpiBuilder pl_kpi_builder = new PacketLossKpiBuilder();
			if(interface1_entry.getValue().getState().getCounters().getInUnicastPkts() != null && interface1_entry.getValue().getState().getCounters().getInMulticastPkts() != null &&
			   interface1_entry.getValue().getState().getCounters().getInBroadcastPkts() != null && interface1_entry.getValue().getState().getCounters().getInDiscards() != null){
				iface1_in_packets = interface1_entry.getValue().getState().getCounters().getInUnicastPkts().getValue().doubleValue() + interface1_entry.getValue().getState().getCounters().getInMulticastPkts().getValue().doubleValue()
				+ interface1_entry.getValue().getState().getCounters().getInBroadcastPkts().getValue().doubleValue() + interface1_entry.getValue().getState().getCounters().getInDiscards().getValue().doubleValue(); 
				iface1_in_discards = interface1_entry.getValue().getState().getCounters().getInDiscards().getValue().doubleValue(); 
			}
			
			if(interface1_entry.getValue().getState().getCounters().getOutUnicastPkts() != null && interface1_entry.getValue().getState().getCounters().getOutMulticastPkts() != null &&
			   interface1_entry.getValue().getState().getCounters().getOutBroadcastPkts() != null && interface1_entry.getValue().getState().getCounters().getOutDiscards() != null){
				iface1_out_packets = interface1_entry.getValue().getState().getCounters().getOutUnicastPkts().getValue().doubleValue() + interface1_entry.getValue().getState().getCounters().getOutMulticastPkts().getValue().doubleValue()
				+ interface1_entry.getValue().getState().getCounters().getOutBroadcastPkts().getValue().doubleValue() + interface1_entry.getValue().getState().getCounters().getOutDiscards().getValue().doubleValue(); 
				iface1_out_discards = interface1_entry.getValue().getState().getCounters().getOutDiscards().getValue().doubleValue(); 
			}

			for (Map.Entry<InterfaceKey, Interface> interface2_entry: interfaces2.getInterface().entrySet()) {
				if(interface2_entry.getValue().getState().getCounters().getInUnicastPkts() != null && interface2_entry.getValue().getState().getCounters().getInMulticastPkts() != null &&
				   interface2_entry.getValue().getState().getCounters().getInBroadcastPkts() != null && interface2_entry.getValue().getState().getCounters().getInDiscards() != null){
					iface2_in_packets = interface2_entry.getValue().getState().getCounters().getInUnicastPkts().getValue().doubleValue() + interface2_entry.getValue().getState().getCounters().getInMulticastPkts().getValue().doubleValue()
					+ interface2_entry.getValue().getState().getCounters().getInBroadcastPkts().getValue().doubleValue() + interface2_entry.getValue().getState().getCounters().getInDiscards().getValue().doubleValue();
					iface2_in_discards = interface2_entry.getValue().getState().getCounters().getInDiscards().getValue().doubleValue(); 
					count_in_packets = (iface2_in_packets - iface1_in_packets);
					count_in_discards = (iface2_in_discards - iface1_in_discards);
					pl_kpi_builder.setPacketLossIn(PerDecimal.getDefaultInstance(String.valueOf((count_in_discards/count_in_packets)*100)));
				}
				 
				if(interface2_entry.getValue().getState().getCounters().getOutUnicastPkts() != null && interface2_entry.getValue().getState().getCounters().getOutMulticastPkts() != null &&
				   interface2_entry.getValue().getState().getCounters().getOutBroadcastPkts() != null && interface2_entry.getValue().getState().getCounters().getOutDiscards() != null){
					iface2_out_packets = interface2_entry.getValue().getState().getCounters().getOutUnicastPkts().getValue().doubleValue() + interface2_entry.getValue().getState().getCounters().getOutMulticastPkts().getValue().doubleValue()
					+ interface2_entry.getValue().getState().getCounters().getOutBroadcastPkts().getValue().doubleValue() + interface2_entry.getValue().getState().getCounters().getOutDiscards().getValue().doubleValue();
					iface2_out_discards = interface2_entry.getValue().getState().getCounters().getOutDiscards().getValue().doubleValue(); 
					count_out_packets = (iface2_out_packets - iface1_out_packets);
					count_out_discards = (iface2_out_discards - iface1_out_discards);
					pl_kpi_builder.setPacketLossOut(PerDecimal.getDefaultInstance(String.valueOf((count_out_discards/count_out_packets)*100)));
				}
			}
			pl_kpi_builder.setDuration(Timestamp.getDefaultInstance(duration));

			pl_kpi_noti_builder.setPacketLossKpi(pl_kpi_builder.build());
			DateFormat df = new SimpleDateFormat("Y-MM-dd'T'HH:mm:ss");
			Date date = new Date();
			pl_kpi_noti_builder.setEventTime(DateAndTime.getDefaultInstance(df.format(date).concat("Z")));
			// build the object into an aggregations Interface
			interfaceAugmentationBuilder.setPacketLossKpiNotification(pl_kpi_noti_builder.build());
			Interface1 interface_augmentation = interfaceAugmentationBuilder.build();
			// add augmentations to the interfaceBuilder
			interface1Builder.addAugmentation(interface_augmentation);
			interface1Builder.setState(null);
			// build new Interface
			Interface new_interface = interface1Builder.build();

			new_interface_list.add(new_interface);
		}

		InterfacesBuilder interfacesBuilder = new InterfacesBuilder();
		interfacesBuilder.setInterface(new_interface_list);
		
		return interfacesBuilder.build();
	}

	public static class EventsAggregation extends ProcessAllWindowFunction<String, String, GlobalWindow>{

		private String duration;

		public EventsAggregation(String duration){
			this.duration = duration;
		}

		private int size(Iterable data) {
	
			if (data instanceof Collection) {
				return ((Collection<?>) data).size();
			}
			int counter = 0;
			for (Object i : data) {
				counter++;
			}
			return counter;
		}
		
		@Override
		public void process(Context context, Iterable<String> elements, Collector<String> out) throws Exception {
			int windowSize = size(elements);
			if(windowSize == 2){
				Iterator<String> it = elements.iterator();
				Notification notification1 = YangNormalization.input2OpenconfigInterfacesNotificationClass(it.next());
				Notification notification2 = YangNormalization.input2OpenconfigInterfacesNotificationClass(it.next());
				Interfaces interfaces = gnmiPacketLossAggregation(notification1.getInterfaces(), notification2.getInterfaces(), duration);
				out.collect(YangNormalization.serialize2JSONstring(interfaces));
			}   
		}
	} 

}
