
package tid;


// GOOGLE JSON imports
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.google.gson.JsonSyntaxException;


// FLINK imports
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.serialization.DeserializationSchema;
import org.apache.flink.api.common.serialization.SerializationSchema;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.connector.base.DeliveryGuarantee;
import org.apache.flink.connector.kafka.sink.KafkaRecordSerializationSchema;
import org.apache.flink.connector.kafka.sink.KafkaSink;
import org.apache.flink.connector.kafka.source.KafkaSource;
import org.apache.flink.connector.kafka.source.enumerator.initializer.OffsetsInitializer;


// GENERATED-SOURCES imports
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.aggregated.rev220302.PerDecimal;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.NetflowBuilder;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.netflow.ExportPacketBuilder;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.netflow.export.packet.FlowDataRecord;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.aggregated.rev220302.FlowDataRecord1;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.aggregated.rev220302.FlowDataRecord1Builder;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.netflow.export.packet.FlowDataRecordBuilder;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715.Timestamp;


// YANG-TOOLS imports
import org.opendaylight.mdsal.binding.dom.codec.api.BindingNormalizedNodeSerializer;
import org.opendaylight.mdsal.binding.dom.codec.impl.BindingCodecContext;
import org.opendaylight.mdsal.binding.runtime.spi.BindingRuntimeHelpers;
import org.opendaylight.mdsal.binding.spec.reflect.BindingReflections;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.Netflow;
import org.opendaylight.yangtools.yang.binding.InstanceIdentifier;
import org.opendaylight.yangtools.yang.common.Uint32;
import org.opendaylight.yangtools.yang.data.api.YangInstanceIdentifier;
import org.opendaylight.yangtools.yang.data.api.schema.MapEntryNode;
import org.opendaylight.yangtools.yang.data.api.schema.NormalizedNode;
import org.opendaylight.yangtools.yang.data.api.schema.stream.NormalizedNodeStreamWriter;
import org.opendaylight.yangtools.yang.data.api.schema.stream.NormalizedNodeWriter;
import org.opendaylight.yangtools.yang.data.codec.gson.*;
import org.opendaylight.yangtools.yang.data.impl.schema.ImmutableNormalizedNodeStreamWriter;
import org.opendaylight.yangtools.yang.data.impl.schema.NormalizedNodeResult;
import org.opendaylight.yangtools.yang.model.api.EffectiveModelContext;
import org.opendaylight.yangtools.yang.model.api.SchemaPath;


// JAVA imports
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.IllegalArgumentException;


/**
 * Flink Streaming Application that make stateless aggregation on goflow2 traffic received in JSON_IETF format.
 *
 * <p>To create bindings of your application, run 'mvn generate-bindings' on the command line.
 * Do not forget to make sure your "yangFilesPath" points to the yang-models folder
 *
 * <p>To package your application into a JAR file for execution, run
 * 'mvn package' on the command line.
 *
 * <p>If you change the name of the main class (with the public static void main(String[] args))
 * method, change the respective entry in the POM.xml file (simply search for 'mainClass').
 */
public class NetflowAggregator {


	public static class netflowAggregateMap implements MapFunction<String, String> {

		@Override
		public String map(String input) throws Exception {

			// get Object of class Netflow from the input (JSON-IETF)
			JsonReader reader = new JsonReader(new StringReader(input));
			Netflow netflowv9_netflow  = null;
			try{
				netflowv9_netflow = input2NetflowClass(reader);

			} catch(IllegalStateException | JsonSyntaxException | IllegalArgumentException e){
				System.out.println("EXCEPTION: Malformed INPUT (JSON-IETF)");
				e.printStackTrace();
			} catch(Exception e){
                System.out.println("Unknown exception:");
				e.printStackTrace();
            }

			String serialized = "";
			if(netflowv9_netflow != null){
				// make aggregations
				Netflow newnetflow = netflowAggregation(netflowv9_netflow);

				// serialize to string
				serialized = serialize2JSONstring(newnetflow);

			}

			return serialized;
		}
	}

	public static void main(String[] args) throws Exception {

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


		DataStream<String> serializedds = dss.map(new netflowAggregateMap()).filter(new FilterFunction<String>() {
			// make sure only valid json values are processed, then filter by permissible sources (ceos, prometheus, netflow)
			@Override
			public boolean filter(String value) throws Exception {
				// if empty do not return
				return !value.equals("");
			}
		});

		serializedds.sinkTo(producer);

		environment.execute("netflowAggregator");
	}


	private static Netflow netflowAggregation(Netflow netflowv9){
		// get Object of class Netflow from the input (JSON-IETF)

		List<FlowDataRecord> newlist = new ArrayList<>();
		// iterate over the FlowDataRecord List and create newlist with AUGMENTED aggregations
		for (FlowDataRecord fdr : netflowv9.getExportPacket().getFlowDataRecord()) {

			FlowDataRecordBuilder flowbuilder = new FlowDataRecordBuilder(fdr); // create builder for adding aggregations

			// create builder for flow aggregations
			FlowDataRecord1Builder fdr1_builder = new FlowDataRecord1Builder();

			// AGGREGATIONS --->
			// FLOW DURATION
			Float flowduration = Float.valueOf(fdr.getLastSwitched().longValue()-fdr.getFirstSwitched().longValue()); //duration in milliseconds
			Float durationSeconds = flowduration/1000; // duration in seconds
			fdr1_builder.setFlowDuration(new Timestamp(Uint32.valueOf(flowduration.intValue())));

			Float temp;

			if(durationSeconds!=0){

				// BytesInPerSecond
				temp = fdr.getBytesIn().getValue().floatValue()/durationSeconds;
				fdr1_builder.setBytesInPerSecond(PerDecimal.getDefaultInstance(temp.toString()));

				// PktsInPerSecond
				temp = fdr.getPktsIn().getValue().floatValue()/durationSeconds;
				fdr1_builder.setPktsInPerSecond(PerDecimal.getDefaultInstance(temp.toString()));

				// BytesOutPerSecond
				temp = fdr.getBytesOut().getValue().floatValue()/durationSeconds;
				fdr1_builder.setBytesOutPerSecond(PerDecimal.getDefaultInstance(temp.toString()));


				// PktsOutPerSecond
				temp = fdr.getPktsOut().getValue().floatValue()/durationSeconds;
				fdr1_builder.setPktsOutPerSecond(PerDecimal.getDefaultInstance(temp.toString()));
			}

			if(fdr.getPktsIn().getValue().floatValue()!=0){
				// BytesInPerPacket
				temp = fdr.getBytesIn().getValue().floatValue()/fdr.getPktsIn().getValue().floatValue();
				fdr1_builder.setBytesInPerPacket(PerDecimal.getDefaultInstance(temp.toString()));
			}

			if(fdr.getPktsOut().getValue().floatValue()!=0){
				// BytesOutPerPacket
				temp = fdr.getBytesOut().getValue().floatValue()/fdr.getPktsOut().getValue().floatValue();
				fdr1_builder.setBytesOutPerPacket(PerDecimal.getDefaultInstance(temp.toString()));
			}

			if(fdr.getBytesOut().getValue().floatValue()!=0){
				// RatioBytesInPerOut
				temp = fdr.getBytesIn().getValue().floatValue()/fdr.getBytesOut().getValue().floatValue();
				fdr1_builder.setRatioBytesInPerOut(PerDecimal.getDefaultInstance(temp.toString()));
			}

			if(fdr.getPktsOut().getValue().floatValue()!=0){
				// RatioPktsInPerOut
				temp = fdr.getPktsIn().getValue().floatValue()/fdr.getPktsOut().getValue().floatValue();
				fdr1_builder.setRatioPktsInPerOut(PerDecimal.getDefaultInstance(temp.toString()));
			}
			// <--- AGGREGATIONS



			// build the object into an aggregations FlowDataRecord
			FlowDataRecord1 fdr1 = fdr1_builder.build(); // ready to add to an augmentation
			// Add augmentations to the flowbuilder
			flowbuilder.addAugmentation(fdr1);
			// build new FlowDataRecord
			FlowDataRecord newfdr = flowbuilder.build();

			// change the old FlowDataRecord with the aggregated one
			newlist.add(newfdr);
		}

		// aggregate newlist to old netflowv9 value
		NetflowBuilder nb = new NetflowBuilder(netflowv9);
		ExportPacketBuilder eb = new ExportPacketBuilder(netflowv9.getExportPacket());
		eb.setFlowDataRecord(newlist);
		nb.setExportPacket(eb.build());
		return nb.build();
	}


	// VARIABLES TO SERIALIZE AND DESERIALIZE
	private static final JSONCodecFactorySupplier CODEC_SUPPLIER = JSONCodecFactorySupplier.RFC7951;
	private static final BindingNormalizedNodeSerializer netflow_agg_codec = new BindingCodecContext(
																					BindingRuntimeHelpers.createRuntimeContext(
																							Netflow.class,
																							FlowDataRecord1.class
																						)
																					);
	private static final EffectiveModelContext schemaContext = BindingRuntimeHelpers.createEffectiveModel(BindingReflections.loadModuleInfos());;
	private static final JSONCodecFactory codecFactory = CODEC_SUPPLIER.getShared(schemaContext);
	private static final JsonParser PARSER = new JsonParser();



	public static Netflow input2NetflowClass(JsonReader reader){

		NormalizedNodeResult result = new NormalizedNodeResult();
		NormalizedNodeStreamWriter streamWriter = ImmutableNormalizedNodeStreamWriter.from(result);
		JsonParserStream jsonParser = JsonParserStream.create(streamWriter, codecFactory);
		jsonParser.parse(reader);
		NormalizedNode<?, ?> transformedInput = result.getResult();
		InstanceIdentifier<Netflow> netflow_iid = InstanceIdentifier.create(Netflow.class);
		YangInstanceIdentifier netflow_yiid = netflow_agg_codec.toYangInstanceIdentifier(netflow_iid);
		return (Netflow) netflow_agg_codec.fromNormalizedNode(netflow_yiid, transformedInput).getValue();

	}


	// CONVERSIONS FROM NETFLOW CLASS TO JSON STRING --->

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
//			LOG.error(JSON_IO_ERROR, e);
			return null;
		}
	}


	private static String serialize2JSONstring(Netflow netflow){

		InstanceIdentifier<Netflow> iid = InstanceIdentifier.create(Netflow.class);
		JsonObject gson_obj = new JsonObject();

		try {
			Entry<YangInstanceIdentifier, NormalizedNode<?, ?>> normalized = netflow_agg_codec.toNormalizedNode(iid, netflow);
			gson_obj = doConvert(schemaContext.getPath(), normalized.getValue());
		} catch (Exception ex) {
			ex.printStackTrace();
			StringWriter errors = new StringWriter();
			ex.printStackTrace(new PrintWriter(errors));
		}

		return gson_obj.toString();

	}

	// <--- CONVERSIONS FROM NETFLOW CLASS TO JSON STRING

}
