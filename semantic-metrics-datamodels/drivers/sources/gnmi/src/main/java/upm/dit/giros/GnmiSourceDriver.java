package upm.dit.giros;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.JsonStreamParser;
import com.google.gson.stream.JsonWriter;

import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.opendaylight.mdsal.binding.dom.codec.api.BindingNormalizedNodeSerializer;
import org.opendaylight.mdsal.binding.dom.codec.impl.BindingCodecContext;
import org.opendaylight.mdsal.binding.runtime.spi.BindingRuntimeHelpers;
import org.opendaylight.mdsal.binding.spec.reflect.BindingReflections;
import org.opendaylight.yangtools.yang.binding.InstanceIdentifier;
import org.opendaylight.yangtools.yang.binding.util.BindingMap;
import org.opendaylight.yangtools.yang.common.Uint16;
import org.opendaylight.yangtools.yang.common.Uint32;
import org.opendaylight.yangtools.yang.data.api.YangInstanceIdentifier;
import org.opendaylight.yangtools.yang.data.api.schema.MapEntryNode;
import org.opendaylight.yangtools.yang.data.api.schema.NormalizedNode;
import org.opendaylight.yangtools.yang.data.api.schema.stream.NormalizedNodeStreamWriter;
import org.opendaylight.yangtools.yang.data.api.schema.stream.NormalizedNodeWriter;
import org.opendaylight.yangtools.yang.data.codec.gson.JSONCodecFactory;
import org.opendaylight.yangtools.yang.data.codec.gson.JSONCodecFactorySupplier;
import org.opendaylight.yangtools.yang.data.codec.gson.JSONNormalizedNodeStreamWriter;
import org.opendaylight.yangtools.yang.data.codec.gson.JsonWriterFactory;
import org.opendaylight.yangtools.yang.model.api.EffectiveModelContext;
import org.opendaylight.yangtools.yang.model.api.SchemaPath;
import org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.InterfaceCommonState.AdminStatus;
import org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.InterfaceCommonState.OperStatus;
import org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406._interface.counters.state.Counters;
import org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406._interface.counters.state.CountersBuilder;
import org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.interfaces.top.Interfaces;
import org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.interfaces.top.InterfacesBuilder;
import org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.interfaces.top.interfaces.Interface;
import org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.interfaces.top.interfaces.InterfaceBuilder;
import org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.interfaces.top.interfaces._interface.StateBuilder;
import org.opendaylight.yang.gen.v1.http.openconfig.net.yang.openconfig.types.rev190416.Timeticks64;
import org.opendaylight.yang.gen.v1.http.openconfig.net.yang.types.yang.rev210714.Counter64;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.interfaces.rev180220.InterfaceType;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715.DateAndTime;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.openconfig.interfaces.notification.wrapper.rev220513.Notification;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.openconfig.interfaces.notification.wrapper.rev220513.NotificationBuilder;

/**
 * Flink Streaming Application with gNMIc YANG Tools Source Driver. This Driver allows to normalize the data produced by a gNMIc 
 * notification into the respective YANG data model, solving the problem that the notification events generated by gNMIc 
 * do not comply with the YANG module from which the data is collected. The Driver is particularized to the openconfig-interfaces YANG model 
 * in order to provide the operational state data at the global interface level of any network device that supports the model.
 */
public class GnmiSourceDriver {

	public static void main(String[] args) throws Exception {
		
		// Configuration of Kafka consumer and producer properties
		Properties props = new Properties();
		props.put("bootstrap.servers", args[0]);
		props.put("group.id", "gnmi-source-group");
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		
		// set up the streaming execution environment
		final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

		// Consume data stream from the Kafka input topic where GoFlow2 writes the NetFlow traffic
        FlinkKafkaConsumer<String> consumer = new FlinkKafkaConsumer<String>(args[1], new SimpleStringSchema(), props);

		DataStream<String> stringInputStream = env.addSource(consumer);

		DataStream<String> json_ietf = stringInputStream.map(new MapFunction<String, String>(){
			@Override
		    public String map(String json) throws Exception{
				try {                    
                    JsonStreamParser gnmi_event = new JsonStreamParser(json);
                    json = driver(gnmi_event);
				} catch (JsonParseException | NullPointerException e) {
					e.printStackTrace();
					json = "";
				}
				return json;
		    }
		}).filter(new FilterFunction<String>() {
			//make sure only valid json values are written into the topic
			@Override
			public boolean filter(String value) throws Exception {
				// if empty do not return
				return !value.equals("");
			}
		});

        //Produce data stream on the Kafka output topic
		FlinkKafkaProducer<String> producer = new FlinkKafkaProducer<String>(args[2], new SimpleStringSchema(), props);
		json_ietf.addSink(producer);
		// execute program
		env.execute("gNMI Source Driver");
	}

	// Schema context initialization
    // Code borrowed from:
    // https://github.com/opendaylight/jsonrpc/blob/1331a9f73db2fe308e3bbde00ff42359431dbc7f/
    // binding-adapter/src/main/java/org/opendaylight/jsonrpc/binding/EmbeddedRpcInvocationAdapter.java#L38
    private static final EffectiveModelContext schemaContext = BindingRuntimeHelpers.createEffectiveModel(BindingReflections.loadModuleInfos(Notification.class.getClassLoader()));

    
    // Code borrowed from:
    // https://git.opendaylight.org/gerrit/gitweb?p=jsonrpc.git;a=blob_plain;f=impl/src/
    // main/java/org/opendaylight/jsonrpc/impl/
    // JsonConverter.java;h=ea8069c67ece073e3d9febb694c4e15b01238c10;hb=3ea331d0e57712654d9ecbf2ae2a46cb0ce02d31
    private static final String JSON_IO_ERROR = "I/O problem in JSON codec";
    private static final Logger LOG = LogManager.getLogger(GnmiSourceDriver.class);
    private static final JSONCodecFactorySupplier CODEC_SUPPLIER =
        JSONCodecFactorySupplier.RFC7951;
    private static final JsonParser PARSER = new JsonParser();
	private static BindingNormalizedNodeSerializer codec = new BindingCodecContext(BindingRuntimeHelpers.createRuntimeContext(Notification.class));



	/**
     * Performs the actual data conversion.
     *
     * @param schemaPath - schema path for data
     * @param data - Normalized Node
     * @return data converted as a JsonObject
     */
    private static JsonObject doConvert(SchemaPath schemaPath, NormalizedNode<?, ?> data) {
        try (StringWriter writer = new StringWriter();
                JsonWriter jsonWriter = JsonWriterFactory.createJsonWriter(writer)) {
            final JSONCodecFactory codecFactory = CODEC_SUPPLIER.getShared(schemaContext);
            final NormalizedNodeStreamWriter jsonStream = (data instanceof MapEntryNode)
                    ? JSONNormalizedNodeStreamWriter.createNestedWriter(codecFactory, schemaPath, null, jsonWriter)
                    : JSONNormalizedNodeStreamWriter.createExclusiveWriter(codecFactory, schemaPath, null, jsonWriter);
            try (NormalizedNodeWriter nodeWriter = NormalizedNodeWriter.forStreamWriter(jsonStream)) {
                nodeWriter.write(data);
                nodeWriter.flush();
            }
            return PARSER.parse(writer.toString()).getAsJsonObject();
        } catch (IOException e) {
            LOG.error(JSON_IO_ERROR, e);
            return null;
        }
    }

	public static String driver(JsonStreamParser gnmi) throws Exception {
		
		// Init YANG Binding builders
		NotificationBuilder notificationBuilder = new NotificationBuilder();
		InterfacesBuilder interfaces_builder = new InterfacesBuilder();
		InterfaceBuilder interface_builder = new InterfaceBuilder();
		StateBuilder state_builder = new StateBuilder();
		CountersBuilder counters_builder = new CountersBuilder();
		ArrayList<Interface> interface_list = new ArrayList<Interface>();

		ArrayList<String> timestamps_aux = new ArrayList<String>();
		String timestamp = new String();

		while (gnmi.hasNext()){

            JsonElement jsonElement = gnmi.next();
			JsonArray gnmi_events = jsonElement.getAsJsonArray();
			LOG.info("Input Stream JSON Array: " + gnmi_events.toString());

			for(int i = 0; i < gnmi_events.size(); i++){
				JsonObject gnmi_event = gnmi_events.get(i).getAsJsonObject();
				LOG.info("Input Stream JSON Object: " + gnmi_event.toString());

				timestamps_aux.add(gnmi_event.get("timestamp").getAsString());
			
				String interface_name = new String();
				if(gnmi_event.has("tags")){
					JsonObject tags_json = gnmi_event.getAsJsonObject("tags");
					for (Map.Entry<String, JsonElement> entry : tags_json.entrySet()) {
						if(entry.getKey().equals("interface_name")){
							interface_name = entry.getValue().getAsString();
							interface_builder.setName(interface_name);
						}
					}
				}

				String xpath = new String();
				String telemetry_value = new String();
				if(gnmi_event.has("values")){
					JsonObject values_json = gnmi_event.getAsJsonObject("values");
					for (Map.Entry<String, JsonElement> entry : values_json.entrySet()) {
						xpath = entry.getKey();
						telemetry_value = entry.getValue().getAsString();
					}
				}

				String leaf_node = xpath.split("/")[xpath.split("/").length-1];

				// Structuring data according to the interface-phys-config grouping
				switch(leaf_node){
					case "name":
						state_builder.setName(telemetry_value);
						break;
					case "type":
						state_builder.setType(InterfaceType.class);
						break;
					case "mtu":
						state_builder.setMtu(Uint16.valueOf(telemetry_value));
						break;
					case "loopback-mode":
						state_builder.setLoopbackMode(Boolean.valueOf(telemetry_value));
						break;
					case "description":
						state_builder.setDescription(telemetry_value);
						break;
					case "enabled":
						state_builder.setEnabled(Boolean.valueOf(telemetry_value));
						break;
					default:
						break;
				}	

				// Structuring data according to the interface-common-state grouping 
				switch(leaf_node){
					case "if-index":
						state_builder.setIfindex(Uint32.valueOf(telemetry_value));
						break;
					case "admin-status":
						state_builder.setAdminStatus(AdminStatus.valueOf(telemetry_value));
						break;
					case "oper-status":
						state_builder.setOperStatus(OperStatus.valueOf(telemetry_value));
						break;	
					case "last-change":
						state_builder.setLastChange(Timeticks64.getDefaultInstance(telemetry_value));
						break;
					case "logical":
						state_builder.setLogical(Boolean.valueOf(telemetry_value));
						break;
					case "management":
						state_builder.setManagement(Boolean.valueOf(telemetry_value));
						break;
					case "cpu":
						state_builder.setCpu(Boolean.valueOf(telemetry_value));
						break;
					default:
						break;
				}

				// Structuring data according to the interface-counters-state grouping 
				switch(leaf_node){
					case "in-octets":
						counters_builder.setInOctets(Counter64.getDefaultInstance(telemetry_value));
						break;
					case "in-pkts":
						counters_builder.setInPkts(Counter64.getDefaultInstance(telemetry_value));
						break;
					case "in-unicast-pkts":
						counters_builder.setInUnicastPkts(Counter64.getDefaultInstance(telemetry_value));
						break;
					case "in-multicast-pkts":
						counters_builder.setInMulticastPkts(Counter64.getDefaultInstance(telemetry_value));
						break;
					case "in-broadcast-pkts":
						counters_builder.setInBroadcastPkts(Counter64.getDefaultInstance(telemetry_value));
						break;
					case "in-discards":
						counters_builder.setInDiscards(Counter64.getDefaultInstance(telemetry_value));
						break;
					case "in-errors":
						counters_builder.setInErrors(Counter64.getDefaultInstance(telemetry_value));
						break;
					case "in-unknown-protos":
						counters_builder.setInUnknownProtos(Counter64.getDefaultInstance(telemetry_value));
						break;
					case "in-fcs-errors":
						counters_builder.setInFcsErrors(Counter64.getDefaultInstance(telemetry_value));
						break;
					case "out-octets":
						counters_builder.setOutOctets(Counter64.getDefaultInstance(telemetry_value));
						break;
					case "out-pkts":
						counters_builder.setOutPkts(Counter64.getDefaultInstance(telemetry_value));
						break;
					case "out-unicast-pkts":
						counters_builder.setOutUnicastPkts(Counter64.getDefaultInstance(telemetry_value));
						break;
					case "out-multicast-pkts":
						counters_builder.setOutMulticastPkts(Counter64.getDefaultInstance(telemetry_value));
						break;
					case "out-broadcast-pkts":
						counters_builder.setOutBroadcastPkts(Counter64.getDefaultInstance(telemetry_value));
						break;
					case "out-discards":
						counters_builder.setOutDiscards(Counter64.getDefaultInstance(telemetry_value));
						break;
					case "out-errors":
						counters_builder.setOutErrors(Counter64.getDefaultInstance(telemetry_value));
						break;
					case "carrier-transitions":
						counters_builder.setCarrierTransitions(Counter64.getDefaultInstance(telemetry_value));
						break;
					case "last-clear":
						counters_builder.setLastClear(Timeticks64.getDefaultInstance(telemetry_value));
						break;
					default:
						break;
				}
			}
	
		}

		Counters counters = counters_builder.build();

		state_builder.setCounters(counters);

		org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.interfaces.top.interfaces._interface.State state = state_builder.build();

		interface_builder.setState(state);

		Interface interface_gnmi = interface_builder.build();
		
		interface_list.add(interface_gnmi);

		interfaces_builder.setInterface(BindingMap.of(interface_list));

        final Interfaces interfaces = interfaces_builder.build();
		
		notificationBuilder.setInterfaces(interfaces);

		timestamp = timestamps_aux.get(0);
		for(int j = 0; j < timestamps_aux.size(); j++){
			if(Long.parseLong(timestamps_aux.get(j)) > Long.parseLong(timestamp)){
				timestamp = timestamps_aux.get(j);
			}
		}
		
		Long nano2milli = TimeUnit.NANOSECONDS.toMillis(Long.parseLong(timestamp));
		DateFormat df = new SimpleDateFormat("Y-MM-dd'T'HH:mm:ss");
		DateAndTime dateAndTime = new DateAndTime(df.format(new Date(nano2milli)).concat("Z"));
		notificationBuilder.setEventTime(dateAndTime);

		final Notification notification = notificationBuilder.build();
		InstanceIdentifier<Notification> iid = InstanceIdentifier.create(Notification.class);

		LOG.info("OpenConfig gNMI Event Notification InstanceIdentifier (iid): " + iid);

		JsonObject event_notification = new JsonObject();
		
		try {
			Entry<YangInstanceIdentifier, NormalizedNode<?, ?>> normalized = codec.toNormalizedNode(iid, notification);
            event_notification = doConvert(schemaContext.getPath(), normalized.getValue());
        } catch (Exception ex) {
                ex.printStackTrace();
                StringWriter errors = new StringWriter();
                ex.printStackTrace(new PrintWriter(errors));
                LOG.error(errors.toString());
        }
        LOG.info("OpenConfig gNMI Event Notification: " + event_notification.toString());

		Gson json_format = new GsonBuilder().setPrettyPrinting().create();

        return  json_format.toJson(event_notification);
	}
}