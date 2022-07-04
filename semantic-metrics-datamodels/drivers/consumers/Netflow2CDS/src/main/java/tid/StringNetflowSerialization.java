package tid;


// GOOGLE JSON imports
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.google.gson.JsonSyntaxException;

import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.aggregated.rev220302.FlowDataRecord1;
// GENERATED-SOURCES imports
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.Netflow;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.netflow.export.packet.FlowDataRecord;
// YANG-TOOLS imports
import org.opendaylight.mdsal.binding.dom.codec.api.BindingNormalizedNodeSerializer;
import org.opendaylight.mdsal.binding.dom.codec.impl.BindingCodecContext;
import org.opendaylight.mdsal.binding.runtime.spi.BindingRuntimeHelpers;
import org.opendaylight.mdsal.binding.spec.reflect.BindingReflections;
import org.opendaylight.yangtools.yang.binding.Augmentation;
import org.opendaylight.yangtools.yang.binding.InstanceIdentifier;
import org.opendaylight.yangtools.yang.data.api.YangInstanceIdentifier;
import org.opendaylight.yangtools.yang.data.api.schema.NormalizedNode;
import org.opendaylight.yangtools.yang.data.api.schema.stream.NormalizedNodeStreamWriter;
import org.opendaylight.yangtools.yang.data.codec.gson.JSONCodecFactory;
import org.opendaylight.yangtools.yang.data.codec.gson.JSONCodecFactorySupplier;
import org.opendaylight.yangtools.yang.data.codec.gson.JsonParserStream;
import org.opendaylight.yangtools.yang.data.impl.schema.ImmutableNormalizedNodeStreamWriter;
import org.opendaylight.yangtools.yang.data.impl.schema.NormalizedNodeResult;
import org.opendaylight.yangtools.yang.model.api.EffectiveModelContext;


// JAVA imports
import java.io.StringReader;
import java.lang.IllegalArgumentException;
import java.util.List;
import java.util.Map;

public class StringNetflowSerialization {

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

	// CONVERSIONS FROM NETFLOW CLASS TO STRING CSV FOR CDS --->

	// Using flow2CSV loops over all the flows and returns a multiline String in CSV
	public static String NetflowToCDS(Netflow netflow) throws NullPointerException, Exception{

		StringBuilder serialized = new StringBuilder(new String("")); // string that will contain all the CSV rows

		// Get ExportPacket and iterate
		List<FlowDataRecord> flow_list = netflow.getExportPacket().getFlowDataRecord();

		int fsize = flow_list.size();
		int cont = 0;

		for (FlowDataRecord flow : flow_list) {

			serialized.append(flow2CDS(flow));
			if(cont<fsize-1){
				serialized.append("\n");
			}
			cont++;
			
		}

		return serialized.toString();
	}


	public static String flow2CDS(FlowDataRecord flowRecord) throws Exception{

		
		Map<Class<? extends Augmentation<FlowDataRecord>>, Augmentation<FlowDataRecord>> aug = flowRecord.augmentations();
		FlowDataRecord1 f1 = (FlowDataRecord1) aug.get(FlowDataRecord1.class);
		
		// Take all fields and map to CSV for CDS
		String result = new String(""); // starting String
		result += f1.getPktsInPerSecond().getValue().floatValue() + ",";	// 'inbound_packets_per_second'
		result += f1.getPktsOutPerSecond().getValue().floatValue() + ",";	// 'outbound_packets_per_second'
		result += f1.getBytesInPerSecond().getValue().floatValue() + ",";	// 'inbound_unique_bytes_per_second'
		result += f1.getBytesOutPerSecond().getValue().floatValue() + ",";	// 'outbound_unique_bytes_per_second'
		result += f1.getBytesInPerPacket().getValue().floatValue() + ",";	// 'inbound_bytes_per_packet'
		result += f1.getBytesOutPerPacket().getValue().floatValue() + ",";	// 'outbound_bytes_per_packet'
		result += f1.getRatioBytesInPerOut().getValue().floatValue() + ",";	// 'ratio_bytes_inbound/bytes_outbound'
		result += f1.getRatioPktsInPerOut().getValue().floatValue();	// 'ratio_packets_inbound/packets_outbound'

		return result;
	}

	// <--- CONVERSIONS FROM NETFLOW CLASS TO STRING CSV FOR CDS

}
