package upm.dit.giros;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Map;
import java.util.Map.Entry;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.JsonStreamParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;
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
import org.opendaylight.yangtools.yang.data.api.YangInstanceIdentifier;
import org.opendaylight.yangtools.yang.data.api.schema.MapEntryNode;
import org.opendaylight.yangtools.yang.data.api.schema.NormalizedNode;
import org.opendaylight.yangtools.yang.data.api.schema.stream.NormalizedNodeStreamWriter;
import org.opendaylight.yangtools.yang.data.api.schema.stream.NormalizedNodeWriter;
import org.opendaylight.yangtools.yang.data.codec.gson.JSONCodecFactory;
import org.opendaylight.yangtools.yang.data.codec.gson.JSONCodecFactorySupplier;
import org.opendaylight.yangtools.yang.data.codec.gson.JSONNormalizedNodeStreamWriter;
import org.opendaylight.yangtools.yang.data.codec.gson.JsonParserStream;
import org.opendaylight.yangtools.yang.data.codec.gson.JsonWriterFactory;
import org.opendaylight.yangtools.yang.data.impl.schema.ImmutableNormalizedNodeStreamWriter;
import org.opendaylight.yangtools.yang.data.impl.schema.NormalizedNodeResult;
import org.opendaylight.yangtools.yang.model.api.AugmentationSchemaNode;
import org.opendaylight.yangtools.yang.model.api.ContainerSchemaNode;
import org.opendaylight.yangtools.yang.model.api.DataSchemaNode;
import org.opendaylight.yangtools.yang.model.api.EffectiveModelContext;
import org.opendaylight.yangtools.yang.model.api.LeafSchemaNode;
import org.opendaylight.yangtools.yang.model.api.SchemaPath;
import org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.interfaces.top.Interfaces;
import org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.interfaces.top.interfaces.Interface;
import org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.interfaces.top.interfaces.InterfaceKey;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.instance.data.rev220217.InstanceDataSet;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.instance.data.rev220217.InstanceDataSetBuilder;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.instance.data.rev220217.ModuleWithRevisionDate;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.instance.data.rev220217.instance.data.set.ContentSchemaBuilder;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.instance.data.rev220217.instance.data.set.content.schema.content.schema.spec.SimplifiedInlineBuilder;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.openconfig.interfaces.aggregated.rev220513.Interface1;

/**
 * Flink Streaming Application with gNMI YANGTools Consumer Driver. This Driver allows to normalize the data provided from a gNMI KPI  
 * notification (i.e., the KPIs computed from the gNMI aggregation applications) into the Instance Data YANG data model (RFC 9195). 
 */
public class GnmiConsumerDriver {

	private static String yang_instance_name;

	public static void main(String[] args) throws Exception {
		
		// Configuration of Kafka consumer and producer properties
		Properties props = new Properties();
		props.put("bootstrap.servers", args[0]);
		props.put("group.id", "gnmi-consumer-group");
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

		// Set YANG instance data name
		yang_instance_name = args[3];
		
		// set up the streaming execution environment
		final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

		// Consume data stream from the Kafka input topic where the gNMI related KPIs are registered
        FlinkKafkaConsumer<String> consumer = new FlinkKafkaConsumer<String>(args[1], new SimpleStringSchema(), props);

		DataStream<String> stringInputStream = env.addSource(consumer);

		DataStream<String> json_ietf = stringInputStream.map(new ConsumerDriverMapper(yang_instance_name))
		.filter(new FilterFunction<String>() {
			// make sure only valid json values are written into the topic
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
		env.execute("gNMI Consumer Driver");
	}

	// Schema context initialization
    // Code borrowed from:
    // https://github.com/opendaylight/jsonrpc/blob/1331a9f73db2fe308e3bbde00ff42359431dbc7f/
    // binding-adapter/src/main/java/org/opendaylight/jsonrpc/binding/EmbeddedRpcInvocationAdapter.java#L38
    private static final EffectiveModelContext schemaContext = BindingRuntimeHelpers.createEffectiveModel(BindingReflections.loadModuleInfos());
    // Code borrowed from:
    // https://git.opendaylight.org/gerrit/gitweb?p=jsonrpc.git;a=blob_plain;f=impl/src/
    // main/java/org/opendaylight/jsonrpc/impl/
    // JsonConverter.java;h=ea8069c67ece073e3d9febb694c4e15b01238c10;hb=3ea331d0e57712654d9ecbf2ae2a46cb0ce02d31
    private static final String JSON_IO_ERROR = "I/O problem in JSON codec";
    private static final Logger LOG = LogManager.getLogger(GnmiConsumerDriver.class);
    private static final JSONCodecFactorySupplier CODEC_SUPPLIER =
        JSONCodecFactorySupplier.RFC7951;
	private static final JSONCodecFactory codecFactory = CODEC_SUPPLIER.getShared(schemaContext);
    private static final JsonParser PARSER = new JsonParser();
	private static BindingNormalizedNodeSerializer codec = new BindingCodecContext(BindingRuntimeHelpers.createRuntimeContext());

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

	private static String driver(JsonReader reader, String json, String instance_data_name) throws Exception {
		Interfaces interfaces_kpis = null;
		try{
            NormalizedNodeResult result = new NormalizedNodeResult();
            NormalizedNodeStreamWriter streamWriter = ImmutableNormalizedNodeStreamWriter.from(result);
            JsonParserStream jsonParser = JsonParserStream.create(streamWriter, codecFactory);
            jsonParser.parse(reader);
            NormalizedNode<?, ?> transformedInput = result.getResult();
            InstanceIdentifier<Interfaces> interfaces_kpis_iid = InstanceIdentifier.create(Interfaces.class);
            YangInstanceIdentifier interfaces_kpis_yiid = codec.toYangInstanceIdentifier(interfaces_kpis_iid);
            interfaces_kpis = (Interfaces) codec.fromNormalizedNode(interfaces_kpis_yiid, transformedInput).getValue();
        } catch(IllegalStateException | JsonSyntaxException | IllegalArgumentException e){
			LOG.error("EXCEPTION: Malformed INPUT (JSON-IETF)");
			e.printStackTrace();
		} catch(Exception e){
			LOG.error("Unknown exception:");
			e.printStackTrace();
		}

		//Collect Identities and Leaf nodes from the logparser-so YANG model in different dictionaries
        Collection<? extends org.opendaylight.yangtools.yang.model.api.Module> yang_modules = schemaContext.getModules();
		String revision = new String();
		String instance_data_module_name = new String();
		String instance_data_container_name = new String();
        Iterator modules = yang_modules.iterator();
        ContainerSchemaNode op_ifaces_kpis_container;
		Iterator augmentation;
		Iterator container;
		Iterator nodes;
		Iterator data;
		AugmentationSchemaNode augmentationSchemaNode;
		ContainerSchemaNode kpi = null;
		LeafSchemaNode leaf = null;
        Map<String, String> container_description_map = new HashMap<String, String>();
        while(modules.hasNext()){
            org.opendaylight.yangtools.yang.model.api.Module module = (org.opendaylight.yangtools.yang.model.api.Module) modules.next();
            if(module.getName().toString().equals("openconfig-interfaces-kpis")){
				revision=module.getRevision().get().toString();
				augmentation = module.getAugmentations().iterator();
				while(augmentation.hasNext()){
					augmentationSchemaNode = (AugmentationSchemaNode) augmentation.next();
					container = augmentationSchemaNode.getChildNodes().iterator();
					while(container.hasNext()){	
						op_ifaces_kpis_container = (ContainerSchemaNode) container.next();
						nodes = op_ifaces_kpis_container.getChildNodes().iterator();
						while(nodes.hasNext()){
							DataSchemaNode dataSchemaNode = (DataSchemaNode) nodes.next();
							if(dataSchemaNode instanceof ContainerSchemaNode){
								kpi = (ContainerSchemaNode) dataSchemaNode;
								data = kpi.getChildNodes().iterator();
								while(data.hasNext()){
									DataSchemaNode leafSchemaNode = (DataSchemaNode) data.next();
									if(leafSchemaNode instanceof LeafSchemaNode){
										leaf = (LeafSchemaNode) leafSchemaNode;
										container_description_map.put(leaf.getQName().getLocalName(), leaf.getDescription().get());
									}
								}
							}
						}
					}
				}          
            } else {
				if(module.getName().toString().equals("ietf-yang-instance-data")){
					instance_data_module_name = module.getName();
					Iterator instance_data_iterator = module.getChildNodes().iterator();
					while(instance_data_iterator.hasNext()){
						DataSchemaNode instance_data_node = (DataSchemaNode) instance_data_iterator.next();
						if(instance_data_node instanceof ContainerSchemaNode){
							ContainerSchemaNode instance_data_container = (ContainerSchemaNode) instance_data_node;
							instance_data_container_name = instance_data_container.getQName().getLocalName();
						}
					}
					
				}
			}
        }
		
		InstanceDataSetBuilder instanceDataSetBuilder = new InstanceDataSetBuilder();

		for (Map.Entry<InterfaceKey, Interface> interface_entry: interfaces_kpis.getInterface().entrySet()) {
			Interface1 iface = (Interface1) interface_entry.getValue().augmentations().values().iterator().next();
			if(iface.getPacketLossKpiNotification() != null){
				instanceDataSetBuilder.setName(instance_data_name);
				instanceDataSetBuilder.setTimestamp(iface.getPacketLossKpiNotification().getEventTime());
				List<String> desc = new ArrayList<String>();
				for(Map.Entry<String, String> entry: container_description_map.entrySet()){
					if(entry.getKey().equals("packet-loss-in")){
						desc.add(entry.getValue());
					}else{
						if(entry.getKey().equals("packet-loss-out")){
							desc.add(entry.getValue());
						}
					}
				}
				instanceDataSetBuilder.setDescription(desc);
			} else {
				if(iface.getThroughputKpiNotification() != null){
					instanceDataSetBuilder.setName(instance_data_name);
					instanceDataSetBuilder.setTimestamp(iface.getThroughputKpiNotification().getEventTime());
					List<String> desc = new ArrayList<String>();
					for(Map.Entry<String, String> entry: container_description_map.entrySet()){
						if(entry.getKey().equals("throughput-in")){
							desc.add(entry.getValue());
						}else{
							if(entry.getKey().equals("throughput-out")){
								desc.add(entry.getValue());
							}
						}
					}
					instanceDataSetBuilder.setDescription(desc);
				}
			}
		}
		ContentSchemaBuilder contentSchemaBuilder = new ContentSchemaBuilder();
		SimplifiedInlineBuilder simplifiedInlineBuilder = new SimplifiedInlineBuilder();
		List<ModuleWithRevisionDate> simplifiedInline = new ArrayList<ModuleWithRevisionDate>();
		simplifiedInline.add(ModuleWithRevisionDate.getDefaultInstance("openconfig-interfaces-kpis"+"@"+revision));
		simplifiedInlineBuilder.setModule(simplifiedInline);

		contentSchemaBuilder.setContentSchemaSpec(simplifiedInlineBuilder.build());
		instanceDataSetBuilder.setContentSchema(contentSchemaBuilder.build());

		final InstanceDataSet instanceDataSet = instanceDataSetBuilder.build();

		InstanceIdentifier<InstanceDataSet> iid = InstanceIdentifier.create(InstanceDataSet.class);
        
		LOG.info("Instance Data InstanceIdentifier (iid): " + iid);

		JsonObject instance_data = new JsonObject();
		
		try {
			Entry<YangInstanceIdentifier, NormalizedNode<?, ?>> normalized = codec.toNormalizedNode(iid, instanceDataSet);
            instance_data = doConvert(schemaContext.getPath(), normalized.getValue());
        } catch (Exception ex) {
                ex.printStackTrace();
                StringWriter errors = new StringWriter();
                ex.printStackTrace(new PrintWriter(errors));
                LOG.error(errors.toString());
        }

		/**
		 * The ietf-yang-instance-data defines the content-data node, where the content of the gNMI-related KPIs are included, 
		 * as an anydata statement. However, it also presents difficulties when handling it with the YANG Tools library. 
		 * To overcome this problem, it has been changed to a container node. To overcome this problem, the consumer driver 
		 * includes this content-data field as a JSON element after the data has been normalized according to the 
		 * ietf-yang-instance-data YANG module.
		 */
		JsonStreamParser content_data = new JsonStreamParser(json);
		JsonElement content_data_jsonElement = content_data.next();

		JsonObject instance_data_set_jsonObject = instance_data.get(instance_data_module_name+":"+instance_data_container_name).getAsJsonObject();
		instance_data_set_jsonObject.add("content-data", content_data_jsonElement);

		instance_data.add(instance_data_module_name+":"+instance_data_container_name, instance_data_set_jsonObject);
		LOG.info("Instance Data: " + instance_data.toString());

		Gson json_format = new GsonBuilder().setPrettyPrinting().create();

        return  json_format.toJson(instance_data);
	}

	private static class ConsumerDriverMapper implements MapFunction<String, String>{

		private String instance_data_name;

		ConsumerDriverMapper(String instance_data_name){
			this.instance_data_name = instance_data_name;
		}

		@Override
		public String map(String json) throws Exception {
			try {                    
				JsonReader gnmi_event = new JsonReader(new StringReader(json));
				json = driver(gnmi_event, json, instance_data_name);
			} catch (JsonParseException | NullPointerException e) {
				e.printStackTrace();
				json = "";
			}
			return json;
		}

	}
}
