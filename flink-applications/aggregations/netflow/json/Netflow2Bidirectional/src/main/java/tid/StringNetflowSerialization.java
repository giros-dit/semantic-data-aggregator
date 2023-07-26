package tid;


// GOOGLE JSON imports
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.google.gson.JsonSyntaxException;

// GENERATED-SOURCES imports
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.Netflow;


// YANG-TOOLS imports
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
import org.opendaylight.yangtools.yang.model.api.EffectiveModelContext;
import org.opendaylight.yangtools.yang.model.api.SchemaPath;


// JAVA imports
import java.util.Map.Entry;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.IllegalArgumentException;

public class StringNetflowSerialization {

	// VARIABLES TO SERIALIZE AND DESERIALIZE
	private static final JSONCodecFactorySupplier CODEC_SUPPLIER = JSONCodecFactorySupplier.RFC7951;
	private static final BindingNormalizedNodeSerializer netflow_agg_codec = new BindingCodecContext(
																					BindingRuntimeHelpers.createRuntimeContext(
																							Netflow.class
																						)
																					);
	private static final EffectiveModelContext schemaContext = BindingRuntimeHelpers.createEffectiveModel(BindingReflections.loadModuleInfos());;
	private static final JSONCodecFactory codecFactory = CODEC_SUPPLIER.getShared(schemaContext);
	private static final JsonParser PARSER = new JsonParser();

	// CONVERSIONS FROM STRING TO NETFLOW CLASS --->
	/**
	 * Receives the String by the topic and returns a Netflow Class or null if the String is not correct.
	 */
	public static Netflow String2Netflow(String netflowString){

		JsonReader reader = new JsonReader(new StringReader(netflowString));

		Netflow netflowv9 = null;
		try{
			NormalizedNodeResult result = new NormalizedNodeResult();
			NormalizedNodeStreamWriter streamWriter = ImmutableNormalizedNodeStreamWriter.from(result);
			JsonParserStream jsonParser = JsonParserStream.create(streamWriter, codecFactory);
			jsonParser.parse(reader);
			NormalizedNode<?, ?> transformedInput = result.getResult();
			InstanceIdentifier<Netflow> netflow_iid = InstanceIdentifier.create(Netflow.class);
			YangInstanceIdentifier netflow_yiid = netflow_agg_codec.toYangInstanceIdentifier(netflow_iid);
			netflowv9 = (Netflow) netflow_agg_codec.fromNormalizedNode(netflow_yiid, transformedInput).getValue();

		} catch(IllegalStateException | JsonSyntaxException | IllegalArgumentException e){
			System.out.println("EXCEPTION: Malformed INPUT (JSON-IETF)");
			e.printStackTrace();
		} catch(Exception e){
			System.out.println("Unknown exception:");
			e.printStackTrace();
		}
		
		return netflowv9;

	}

	// <--- CONVERSIONS FROM STRING TO NETFLOW CLASS



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
			return null;
		}
	}

	/**
	 * Given a Netflow class serialize it into a String
	 */
	public static String Netflow2String(Netflow netflow){

		String serialized = "";
		if(netflow != null){

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
	
			serialized =  gson_obj.toString();
		}

		return serialized;

	}

	// <--- CONVERSIONS FROM NETFLOW CLASS TO JSON STRING
}
