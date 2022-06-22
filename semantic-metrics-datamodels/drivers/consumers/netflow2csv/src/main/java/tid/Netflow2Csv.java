package tid;

import com.google.gson.JsonParser;
import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.serialization.DeserializationSchema;
import org.apache.flink.api.common.serialization.SerializationSchema;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.sink.SinkFunction;
import org.apache.flink.streaming.api.functions.source.SourceFunction;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.Netflow;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.TcpFlagsType;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.netflow.export.packet.FlowDataRecord;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.netflow.CollectorGoflow2;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.netflow.ExportPacket;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715.Timestamp;


import java.io.StringReader;
import java.util.*;
import java.lang.IllegalArgumentException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.stream.JsonReader;
import com.google.gson.JsonSyntaxException;

import org.opendaylight.mdsal.binding.dom.codec.api.BindingNormalizedNodeSerializer;
import org.opendaylight.mdsal.binding.dom.codec.impl.BindingCodecContext;
import org.opendaylight.mdsal.binding.runtime.spi.BindingRuntimeHelpers;
import org.opendaylight.mdsal.binding.spec.reflect.BindingReflections;
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
import org.opendaylight.yangtools.yang.common.Uint32;


/**
 * Flink Streaming Application with NetFlow YANGTools Consumer Driver to serialize the NetFlow data to CSV format.
 */
public class Netflow2Csv {


	public static class serialize2CSVMap implements MapFunction<String, String> {

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

				// Map the values in the Netflow Object to confluence csv
				try {
					serialized = ConfluenceSerializeToCSV(netflowv9_netflow);
				} catch (NullPointerException e) {
					System.out.println("EXCEPTION: collector-goflow2, export-packet or flow-data-record is null");

				} catch (Exception e) {
					System.out.println("StackTrace:");
					e.printStackTrace();
					System.out.println();
				}
			}

			return serialized;
		}
	}



	public static void main(String[] args) throws Exception{

		Properties props = new Properties();
		props.put("bootstrap.servers", args[0]);
		props.put("group.id", "netflow-consumer-group");
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		StreamExecutionEnvironment environment = StreamExecutionEnvironment.getExecutionEnvironment();
		FlinkKafkaConsumer<String> consumer = new FlinkKafkaConsumer<String>(args[1], (DeserializationSchema<String>)new SimpleStringSchema(), props);
		DataStreamSource<String> dss = environment.addSource((SourceFunction<String>)consumer);


		DataStream<String> serializedds = dss.map(new serialize2CSVMap()).filter(new FilterFunction<String>() {
			// make sure only valid json values are processed, then filter by permissible sources (ceos, prometheus, netflow)
			@Override
			public boolean filter(String value) throws Exception {
				// if empty do not return
				return !value.equals("");
			}
		});

		FlinkKafkaProducer<String> producer = new FlinkKafkaProducer<>(args[2], (SerializationSchema<String>)new SimpleStringSchema(), props);
		serializedds.addSink((SinkFunction<String>)producer);

		environment.execute("netflow2csv");

	}


	// DESERIALIZING VARIABLES
	private static final JSONCodecFactorySupplier CODEC_SUPPLIER = JSONCodecFactorySupplier.RFC7951;
	private static final BindingNormalizedNodeSerializer netflow_codec = new BindingCodecContext(
			BindingRuntimeHelpers.createRuntimeContext(
					Netflow.class
			)
	);
	private static final EffectiveModelContext schemaContext = BindingRuntimeHelpers.createEffectiveModel(BindingReflections.loadModuleInfos());;
	private static final JSONCodecFactory codecFactory = CODEC_SUPPLIER.getShared(schemaContext);
	private static final JsonParser PARSER = new JsonParser();


	public static Netflow input2NetflowClass(JsonReader reader) throws Exception {

		NormalizedNodeResult result = new NormalizedNodeResult();
		NormalizedNodeStreamWriter streamWriter = ImmutableNormalizedNodeStreamWriter.from(result);
		JsonParserStream jsonParser = JsonParserStream.create(streamWriter, codecFactory);
		jsonParser.parse(reader);
		NormalizedNode<?, ?> transformedInput = result.getResult();
		InstanceIdentifier<Netflow> netflow_iid = InstanceIdentifier.create(Netflow.class);
		YangInstanceIdentifier netflow_yiid = netflow_codec.toYangInstanceIdentifier(netflow_iid);
		return (Netflow) netflow_codec.fromNormalizedNode(netflow_yiid, transformedInput).getValue();

	}

	// Using flow2CSV loops over all the flows and returns a multiline String in CSV
	public static String ConfluenceSerializeToCSV(Netflow netflow) throws NullPointerException, Exception{

		StringBuilder serialized = new StringBuilder(new String("")); // string that will contain all the CSV rows

		// Get ExportPacket and iterate
		ExportPacket export_packet = netflow.getExportPacket();
		CollectorGoflow2 collector_goflow2 = netflow.getCollectorGoflow2();
		netflow.getExportPacket().getFlowDataRecord();
		List<FlowDataRecord> flow_list = export_packet.getFlowDataRecord();

		int fsize = flow_list.size();
		int cont = 0;

		for (FlowDataRecord flow : flow_list) {

			serialized.append(flow2CSV(collector_goflow2, export_packet, flow));
			if(cont<fsize-1){
				serialized.append("\n");
			}
			cont++;
			
		}

		return serialized.toString();
	}


	public static String tcpFlags2String(TcpFlagsType tcpflags) throws Exception{

		StringBuilder tcps = new StringBuilder(new String(""));

		if(tcpflags.getUrg())
			tcps.append("U");
		else
			tcps.append(".");

		if(tcpflags.getAck())
			tcps.append("A");
		else
			tcps.append(".");

		if(tcpflags.getPsh())
			tcps.append("P");
		else
			tcps.append(".");

		if(tcpflags.getRst())
			tcps.append("R");
		else
			tcps.append(".");

		if(tcpflags.getSyn())
			tcps.append("S");
		else
			tcps.append(".");

		if(tcpflags.getFin())
			tcps.append("F");
		else
			tcps.append(".");

		return tcps.toString();
	}


	private static String mplsLabelFormat(Uint32 mplsLabel){
		
		int ivalue = mplsLabel.intValue();
		String lbl = Integer.toString((ivalue >> 12) & 0xFFFFF);
		String exp = Integer.toString((ivalue >> 9) & 0x7);
		String s = Integer.toString((ivalue >> 8) & 0x1);

		return new String(lbl+"-"+exp+"-"+s);
	}



	public static String flow2CSV(CollectorGoflow2 goflow2, ExportPacket epacket, FlowDataRecord flowRecord) throws Exception{

		DateFormat df = new SimpleDateFormat("Y-MM-dd HH:mm:ss");

		// Take all fields and map to CSV in Confluence
		// "ts,te,td,sa,da,sp,dp,pr,flg,fwd,stos,ipkt,ibyt,opkt,obyt,in,out,sas,das,smk,dmk,dtos,dir,nh,nhb,svln,dvln,ismc,odmc,idmc,osmc,mpls1,mpls2,mpls3,mpls4,mpls5,mpls6,mpls7,mpls8,mpls9,mpls10,cl,sl,al,ra,eng,exid,tr"
		String result = new String(""); // starting String
		result += df.format(new Date(flowRecord.getFirstSwitched().longValue())) + ",";	// ts
		result += df.format(new Date(flowRecord.getLastSwitched().longValue())) + ",";	// te
		result += Float.valueOf(flowRecord.getLastSwitched().longValue() - flowRecord.getFirstSwitched().longValue())/1000 + ","; // td -> duration in seconds with 3 decimal places of precision
		result += flowRecord.getIpv4().getSrcAddress().getValue() + ",";	// sa
		result += flowRecord.getIpv4().getDstAddress().getValue() + ",";	// da
		result += flowRecord.getSrcPort().getValue() + ",";		// sp
		result += flowRecord.getDstPort().getValue() + ",";		// dp
		result += flowRecord.getProtocol().getName().toUpperCase() + ",";	// pr
		result += tcpFlags2String(flowRecord.getTcpFlags()) + ","; // flg
		result += flowRecord.getForwardingStatus().getIntValue() + ","; // fwd
		result += flowRecord.getSrcTos().toCanonicalString() + ",";	//stos
		result += flowRecord.getPktsIn().getValue() + ",";		// ipkt
		result += flowRecord.getBytesIn().getValue() + ",";	// ibyt
		result += flowRecord.getPktsOut().getValue() + ",";	// opkt
		result += flowRecord.getBytesOut().getValue() + ",";	// obyt
		result += flowRecord.getSnmpIn().intValue() + ",";	// in
		result += flowRecord.getSnmpOut().intValue() + ",";	// out
		result += flowRecord.getBgp().getSrcAs().getValue() + ",";	// sas
		result += flowRecord.getBgp().getDstAs().getValue() + ",";	// das
		result += flowRecord.getIpv4().getSrcMask().getValue() + ",";	// smk
		result += flowRecord.getIpv4().getDstMask().getValue() + ",";	// dmk
		result += flowRecord.getDstTos().toCanonicalString() + ",";	// dtos
		result += flowRecord.getDirection().getIntValue() + ",";	// dir
		result += flowRecord.getIpv4().getNextHop().getValue() + ",";	// nh
		result += flowRecord.getBgp().getNextHop().getValue() + ",";	// nhb
		result += flowRecord.getVlan().getSrcId().toCanonicalString() + ",";	// svln
		result += flowRecord.getVlan().getDstId().toCanonicalString() + ",";	// dvln
		result += flowRecord.getSrcMacIn().getValue() + ",";	// ismc
		result += flowRecord.getDstMacOut().getValue() + ",";	// odmc
		result += flowRecord.getDstMacIn().getValue() + ",";	// idmc
		result += flowRecord.getSrcMacOut().getValue() + ",";	// osmc
		result += mplsLabelFormat(flowRecord.getMpls().getLabel1()) + ",";	// mpls1
		result += mplsLabelFormat(flowRecord.getMpls().getLabel2()) + ",";	// mpls2
		result += mplsLabelFormat(flowRecord.getMpls().getLabel3()) + ",";	// mpls3
		result += mplsLabelFormat(flowRecord.getMpls().getLabel4()) + ",";	// mpls4
		result += mplsLabelFormat(flowRecord.getMpls().getLabel5()) + ",";	// mpls5
		result += mplsLabelFormat(flowRecord.getMpls().getLabel6()) + ",";	// mpls6
		result += mplsLabelFormat(flowRecord.getMpls().getLabel7()) + ",";	// mpls7
		result += mplsLabelFormat(flowRecord.getMpls().getLabel8()) + ",";	// mpls8
		result += mplsLabelFormat(flowRecord.getMpls().getLabel9()) + ",";	// mpls9
		result += mplsLabelFormat(flowRecord.getMpls().getLabel10()) + ",";	// mpls10
		result += "0.000,";	// cl
		result += "0.000,";	// sl
		result += "0.000,";	// al
		result += goflow2.getSamplerAddress().getValue() + ",";	// ra
		result += flowRecord.getEngineType().getIntValue()+"/"+flowRecord.getEngineId().intValue() + ",";	// eng
		result += epacket.getSourceId().toCanonicalString() + ",";	// exid
		result += df.format(new Date(goflow2.getTimeReceived().getValue().longValue()*1000));	// tr



		return result;
	}

}
