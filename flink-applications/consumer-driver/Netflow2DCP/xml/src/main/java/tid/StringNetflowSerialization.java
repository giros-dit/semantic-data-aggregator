package tid;

// GOOGLE JSON imports
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.google.gson.JsonSyntaxException;

// GENERATED-SOURCES imports
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.Netflow;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.TcpFlagsType;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.netflow.CollectorGoflow2;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.netflow.ExportPacket;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.netflow.export.packet.FlowDataRecord;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.aggregated.rev220302.FlowDataRecord1;

// YANG-TOOLS imports
import org.opendaylight.mdsal.binding.dom.codec.api.BindingNormalizedNodeSerializer;
import org.opendaylight.mdsal.binding.dom.codec.impl.BindingCodecContext;
import org.opendaylight.mdsal.binding.runtime.spi.BindingRuntimeHelpers;
import org.opendaylight.mdsal.binding.spec.reflect.BindingReflections;
import org.opendaylight.yangtools.yang.binding.Augmentation;
import org.opendaylight.yangtools.yang.binding.InstanceIdentifier;
import org.opendaylight.yangtools.yang.common.QName;
import org.opendaylight.yangtools.yang.common.Uint32;
import org.opendaylight.yangtools.yang.data.api.YangInstanceIdentifier;
import org.opendaylight.yangtools.yang.data.api.schema.NormalizedNode;
import org.opendaylight.yangtools.yang.data.api.schema.stream.NormalizedNodeStreamWriter;
import org.opendaylight.yangtools.yang.data.codec.gson.JSONCodecFactory;
import org.opendaylight.yangtools.yang.data.codec.gson.JSONCodecFactorySupplier;
import org.opendaylight.yangtools.yang.data.codec.gson.JsonParserStream;
import org.opendaylight.yangtools.yang.data.codec.xml.XmlParserStream;
import org.opendaylight.yangtools.yang.data.impl.schema.ImmutableNormalizedNodeStreamWriter;
import org.opendaylight.yangtools.yang.data.impl.schema.NormalizedNodeResult;
import org.opendaylight.yangtools.yang.model.api.EffectiveModelContext;
import org.opendaylight.yangtools.yang.model.api.SchemaNode;

// JAVA imports
import java.io.StringReader;
import java.lang.IllegalArgumentException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class StringNetflowSerialization {

	// VARIABLES TO SERIALIZE AND DESERIALIZE
	private static final BindingNormalizedNodeSerializer netflow_agg_codec = new BindingCodecContext(
																					BindingRuntimeHelpers.createRuntimeContext(
																							Netflow.class,
																							FlowDataRecord1.class
																						)
																					);
	private static final EffectiveModelContext schemaContext = BindingRuntimeHelpers.createEffectiveModel(BindingReflections.loadModuleInfos());;

	// CONVERSIONS FROM STRING TO NETFLOW CLASS --->
	/**
	 * Receives the String by the topic and returns a Netflow Class or null if the String is not correct.
	 * @throws FactoryConfigurationError
	 * @throws XMLStreamException
	 */
	public static Netflow String2Netflow(String netflowString) throws XMLStreamException, FactoryConfigurationError{

		JsonReader reader = new JsonReader(new StringReader(netflowString));
		StringReader stringReader = new StringReader(netflowString);
		XMLStreamReader xmlReader = XMLInputFactory.newInstance().createXMLStreamReader(stringReader);
		Netflow netflowv9 = null;
		try{
			NormalizedNodeResult result = new NormalizedNodeResult();
			NormalizedNodeStreamWriter streamWriter = ImmutableNormalizedNodeStreamWriter.from(result);

			QName qname = QName.create("http://data-aggregator.com/ns/netflow", "2021-10-08", "netflow");

			Optional<org.opendaylight.yangtools.yang.model.api.Module> moduleOptional = schemaContext.findModule(qname.getModule());

			if (moduleOptional.isPresent()) {
				org.opendaylight.yangtools.yang.model.api.Module module = moduleOptional.get();

				Optional<? extends SchemaNode> nodeOptional = module.findDataChildByName(qname);

				if (nodeOptional.isPresent()) {
					SchemaNode parentNode = nodeOptional.get();
					XmlParserStream xmlParser = XmlParserStream.create(streamWriter, schemaContext, parentNode);
					xmlParser.parse(xmlReader);
					NormalizedNode<?, ?> transformedInput = result.getResult();
					InstanceIdentifier<Netflow> netflow_iid = InstanceIdentifier.create(Netflow.class);
					YangInstanceIdentifier netflow_yiid = netflow_agg_codec.toYangInstanceIdentifier(netflow_iid);
					netflowv9 = (Netflow) netflow_agg_codec.fromNormalizedNode(netflow_yiid, transformedInput).getValue();
				}
			}


		} catch(IllegalStateException | XMLStreamException | IllegalArgumentException e){
			System.out.println("EXCEPTION: Malformed INPUT (XML)");
			e.printStackTrace();
		} catch(Exception e){
			System.out.println("Unknown exception:");
			e.printStackTrace();
		}
		
		return netflowv9;

	}

	// <--- CONVERSIONS FROM STRING TO NETFLOW CLASS

	// CONVERSIONS FROM NETFLOW CLASS TO STRING CSV --->

	// Using flow2CSV loops over all the flows and returns a multiline String in CSV
	public static String NetflowToDCP(Netflow netflow) throws NullPointerException, Exception{

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

		// Take all fields and map to the CSV schema “Raw Netflow Data + Aggregated features”
		// "ts,te,td,sa,da,sp,dp,pr,flg,fwd,stos,ipkt,ibyt,opkt,obyt,in,out,sas,das,smk,dmk,dtos,dir,nh,nhb,svln,dvln,ismc,odmc,idmc,osmc,mpls1,mpls2,mpls3,mpls4,mpls5,mpls6,mpls7,mpls8,mpls9,mpls10,cl,sl,al,ra,eng,exid,tr,pktips,pktops,bytips,bytops,bytippkt,bytoppkt,bytipo,pktipo"
		String result = new String(""); // Starting String for CSV schema
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
		result += df.format(new Date(goflow2.getTimeReceived().getValue().longValue()*1000)) + ",";	// tr
		result += "$,"; // Zeek extra field
		
		Map<Class<? extends Augmentation<FlowDataRecord>>, Augmentation<FlowDataRecord>> aug = flowRecord.augmentations();
		FlowDataRecord1 f1 = (FlowDataRecord1) aug.get(FlowDataRecord1.class);
		
		// Take all aggregated fields and map to the CSV schema
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

	// <--- CONVERSIONS FROM NETFLOW CLASS TO STRING CSV

}
