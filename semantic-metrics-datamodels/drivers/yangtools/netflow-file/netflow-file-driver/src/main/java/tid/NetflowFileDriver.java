package tid;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonStreamParser;
import com.google.gson.stream.JsonWriter;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.javatuples.Quintet;
import org.opendaylight.mdsal.binding.dom.codec.api.BindingNormalizedNodeSerializer;
import org.opendaylight.mdsal.binding.dom.codec.impl.BindingCodecContext;
import org.opendaylight.mdsal.binding.runtime.spi.BindingRuntimeHelpers;
import org.opendaylight.mdsal.binding.spec.reflect.BindingReflections;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.DirectionType;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.EngineType;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.ForwardingStatusType;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.IpVersionType;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.Netflow;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.NetflowBuilder;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.PrefixLengthIpv4;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.PrefixLengthIpv6;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.ProtocolType;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.TcpFlagsType;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.TopLabelType;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.common.flow.fields.Bgp;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.common.flow.fields.BgpBuilder;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.common.flow.fields.Ipv4;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.common.flow.fields.Ipv4Builder;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.common.flow.fields.Ipv6;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.common.flow.fields.Ipv6Builder;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.common.flow.fields.Mpls;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.common.flow.fields.MplsBuilder;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.common.flow.fields.Vlan;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.common.flow.fields.VlanBuilder;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.netflow.CollectorGoflow2;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.netflow.CollectorGoflow2Builder;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.netflow.ExportPacket;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.netflow.ExportPacketBuilder;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.netflow.export.packet.FlowDataRecord;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.netflow.export.packet.FlowDataRecordBuilder;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev130715.AsNumber;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev130715.Ipv4Address;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev130715.Ipv4Prefix;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev130715.Ipv6Address;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev130715.Ipv6FlowLabel;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev130715.PortNumber;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715.Counter32;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715.Counter64;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715.MacAddress;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715.Timestamp;
import org.opendaylight.yangtools.yang.binding.InstanceIdentifier;
import org.opendaylight.yangtools.yang.common.Uint16;
import org.opendaylight.yangtools.yang.common.Uint32;
import org.opendaylight.yangtools.yang.common.Uint64;
import org.opendaylight.yangtools.yang.common.Uint8;
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


public class NetflowFileDriver
{

    public static void main( String[] args )
    {
        
        //Set up a simple configuration that logs on the console.
        //BasicConfigurator.configure();

        try{
            File driver_input = new File(NetflowFileDriver.class.getClassLoader().getResource("driver-input.json").getFile());
            BufferedReader reader = new BufferedReader(new FileReader(driver_input)); 
            JsonStreamParser p = new JsonStreamParser(reader);
            String json_ietf = driver(p);

            // Write normalised data to a json file
            JsonObject json_ietfO = new JsonParser().parse(json_ietf).getAsJsonObject();
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            File driver_output = new File(NetflowFileDriver.class.getClassLoader().getResource("driver-output.json").getFile());
            FileWriter fileWriter = new FileWriter(driver_output);
            gson.toJson(json_ietfO, fileWriter);
            fileWriter.close();
           
            // Also print on the screen
            System.out.println(json_ietf);


        } catch(Exception ex){
            ex.printStackTrace();
           
        }
       
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
    private static final Logger LOG = LogManager.getLogger(NetflowFileDriver.class);
    private static final JSONCodecFactorySupplier CODEC_SUPPLIER =
        JSONCodecFactorySupplier.DRAFT_LHOTKA_NETMOD_YANG_JSON_02;
    private static final JsonParser PARSER = new JsonParser();
    //private static final YangInstanceIdentifier ROOT = YangInstanceIdentifier.empty();

    

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
    

    public static String driver(JsonStreamParser p) {

        // Init YANG Binding builders
        NetflowBuilder netflow_builder = new NetflowBuilder();
        CollectorGoflow2Builder goflow2_builder = new CollectorGoflow2Builder();
        ExportPacketBuilder exporter_builder = new ExportPacketBuilder();
        FlowDataRecordBuilder flow_builder = new FlowDataRecordBuilder();
        ArrayList<FlowDataRecord> flow_list = new ArrayList<FlowDataRecord>();
        Ipv4Builder ipv4_builder = new Ipv4Builder();
        Ipv6Builder ipv6_builder = new Ipv6Builder();
        MplsBuilder mpls_builder = new MplsBuilder();
        BgpBuilder bgp_builder = new BgpBuilder();
        VlanBuilder vlan_builder = new VlanBuilder();

        // Pattern for IPv4
        final String pattern = "(([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])\\.){3}([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])(%[\\p{N}\\p{L}]+)?";
    
        while (p.hasNext()){

            JsonElement jsonElement = p.next();
            JsonObject data = jsonElement.getAsJsonObject();

            LOG.info("Input Stream JSON: " + data.toString());
            
            //Map fields
            //GOFLOW2 COLLECTOR INFORMATION
            goflow2_builder.setTimeReceived(Timestamp.getDefaultInstance(data.get("TimeReceived").getAsString()));

            //Check if sampler address is an IPv4 or IPv6
            String sampler_address = data.get("SamplerAddress").getAsString();
            if(Pattern.matches(pattern, sampler_address)){
                goflow2_builder.setSamplerAddress(Ipv4Address.getDefaultInstance(data.get("SamplerAddress").getAsString()));
            
            }else{
                goflow2_builder.setSamplerAddressIpv6(Ipv6Address.getDefaultInstance(data.get("SamplerAddress").getAsString()));
            }
            

            //EXPORT PACKET INFORMATION
            //Header data
            exporter_builder.setSequenceNumber(Counter32.getDefaultInstance(data.get("SequenceNum").getAsString()));
            exporter_builder.setCount(Uint16.valueOf(data.get("Count").getAsString()));
            exporter_builder.setSystemUptime(Timestamp.getDefaultInstance(data.get("SystemUptime").getAsString()));
            exporter_builder.setUnixSeconds(Timestamp.getDefaultInstance(data.get("UnixSeconds").getAsString()));
            exporter_builder.setSourceId(Uint32.valueOf(data.get("SourceId").getAsString()));

            //Flow data record fields
            flow_builder.setDirection(DirectionType.forValue(data.get("FlowDirection").getAsInt()));
            flow_builder.setFirstSwitched(Timestamp.getDefaultInstance(data.get("TimeFlowStart").getAsString()));
            flow_builder.setLastSwitched(Timestamp.getDefaultInstance(data.get("TimeFlowEnd").getAsString()));
            flow_builder.setBytesIn(Counter64.getDefaultInstance(data.get("BytesIn").getAsString()));
            flow_builder.setPktsIn(Counter64.getDefaultInstance(data.get("PacketsIn").getAsString()));
            flow_builder.setBytesOut(Counter64.getDefaultInstance(data.get("BytesOut").getAsString()));
            flow_builder.setPktsOut(Counter64.getDefaultInstance(data.get("PacketsOut").getAsString()));
            flow_builder.setSrcPort(PortNumber.getDefaultInstance(data.get("SrcPort").getAsString()));
            flow_builder.setDstPort(PortNumber.getDefaultInstance(data.get("DstPort").getAsString()));
            flow_builder.setProtocol(ProtocolType.forValue(data.get("Proto").getAsInt()));
            
            //Bgp container
            String src_as = data.get("SrcAS").getAsString(); //Then used
            bgp_builder.setSrcAs(AsNumber.getDefaultInstance(src_as));
            bgp_builder.setDstAs(AsNumber.getDefaultInstance(data.get("DstAS").getAsString()));

            //Check if IPv4 or IPv6
            Integer etype = data.get("Etype").getAsInt();
                    
            if(etype==2048) //IPv4
            {
                flow_builder.setIpVersion(IpVersionType.forValue(4));

                //Container IPv4
                ipv4_builder.setSrcAddress(Ipv4Address.getDefaultInstance(data.get("SrcAddr").getAsString()));
                ipv4_builder.setDstAddress(Ipv4Address.getDefaultInstance(data.get("DstAddr").getAsString()));
                ipv4_builder.setSrcMask(PrefixLengthIpv4.getDefaultInstance(data.get("SrcNet").getAsString()));
                ipv4_builder.setDstMask(PrefixLengthIpv4.getDefaultInstance(data.get("DstNet").getAsString()));
                ipv4_builder.setIdentification(Uint16.valueOf(data.get("FragmentId").getAsString()));
                
                String src_preffix = data.get("SrcPrefix").getAsString();
                String dst_preffix = data.get("DstPrefix").getAsString();

                if(!src_preffix.isEmpty()){
                    ipv4_builder.setSrcPrefix(Ipv4Prefix.getDefaultInstance(data.get("SrcPrefix").getAsString()));
                }

                if(!dst_preffix.isEmpty()){
                    ipv4_builder.setDstPrefix(Ipv4Prefix.getDefaultInstance(data.get("DstPrefix").getAsString()));
                }

                Quintet<Ipv4Address, PortNumber, Ipv4Address, PortNumber, Integer> ipv4_5tuple 
                    = Quintet.with(ipv4_builder.getSrcAddress(), 
                                   flow_builder.getSrcPort(),
                                   ipv4_builder.getDstAddress(), 
                                   flow_builder.getDstPort(), 
                                   flow_builder.getProtocol().getIntValue());
                flow_builder.setFlowId(ipv4_5tuple.hashCode());
                
                String next_hop = data.get("NextHop").getAsString();
                String bgp_next_hop = data.get("BgpNextHop").getAsString();
                
                if(!next_hop.isEmpty())
                {
                  ipv4_builder.setNextHop(Ipv4Address.getDefaultInstance(next_hop));
                
                }else{
                  ipv4_builder.setNextHop(Ipv4Address.getDefaultInstance("0.0.0.0"));
                }
                
                if(!bgp_next_hop.isEmpty())
                {
                    bgp_builder.setNextHop(Ipv4Address.getDefaultInstance(bgp_next_hop));
                
                }else{
                    bgp_builder.setNextHop(Ipv4Address.getDefaultInstance("0.0.0.0"));
                }
               
                        
            } 
                    
            else if (etype==34525) //IPv6
            {
                flow_builder.setIpVersion(IpVersionType.forValue(6));

                //Container IPv6
                ipv6_builder.setSrcAddress(Ipv6Address.getDefaultInstance(data.get("SrcAddr").getAsString()));
                ipv6_builder.setDstAddress(Ipv6Address.getDefaultInstance(data.get("DstAddr").getAsString()));
                ipv6_builder.setSrcMask(PrefixLengthIpv6.getDefaultInstance(data.get("SrcNet").getAsString()));
                ipv6_builder.setDstMask(PrefixLengthIpv6.getDefaultInstance(data.get("DstNet").getAsString()));
                ipv6_builder.setFlowLabel(Ipv6FlowLabel.getDefaultInstance(data.get("IPv6FlowLabel").getAsString()));
                ipv6_builder.setOptHeaders(Uint32.valueOf(data.get("IPv6OptionHeaders").getAsString()));


                Quintet<Ipv6Address, PortNumber, Ipv6Address, PortNumber, Integer> ipv6_5tuple 
                    = Quintet.with(ipv6_builder.getSrcAddress(), 
                                   flow_builder.getSrcPort(),
                                   ipv6_builder.getDstAddress(), 
                                   flow_builder.getDstPort(), 
                                   flow_builder.getProtocol().getIntValue());
                flow_builder.setFlowId(ipv6_5tuple.hashCode());

                String next_hop = data.get("NextHop").getAsString();
                String bgp_next_hop = data.get("BgpNextHop").getAsString();
                
                if(!next_hop.isEmpty())
                {
                  ipv6_builder.setNextHop(Ipv6Address.getDefaultInstance(next_hop));
                
                }else{
                  ipv6_builder.setNextHop(Ipv6Address.getDefaultInstance("0:0:0:0:0:0:0:0"));
                }
                
                if(!bgp_next_hop.isEmpty())
                {
                    bgp_builder.setNextHopIpv6(Ipv6Address.getDefaultInstance(bgp_next_hop));
                
                }else{
                    bgp_builder.setNextHopIpv6(Ipv6Address.getDefaultInstance("0:0:0:0:0:0:0:0"));
                }
        
         
            }

            flow_builder.setSrcMacIn(MacAddress.getDefaultInstance(data.get("SrcMacIn").getAsString()));
            flow_builder.setDstMacIn(MacAddress.getDefaultInstance(data.get("DstMacIn").getAsString()));
            flow_builder.setSrcMacOut(MacAddress.getDefaultInstance(data.get("SrcMacOut").getAsString()));
            flow_builder.setDstMacOut(MacAddress.getDefaultInstance(data.get("DstMacOut").getAsString()));
            flow_builder.setSrcTos(Uint8.valueOf(data.get("SrcTos").getAsString()));
            flow_builder.setDstTos(Uint8.valueOf(data.get("DstTos").getAsString()));
            flow_builder.setForwardingStatus(ForwardingStatusType.forValue(data.get("ForwardingStatus").getAsInt()));
            flow_builder.setMinTtl(Uint8.valueOf(data.get("MinTTL").getAsString()));
            flow_builder.setMaxTtl(Uint8.valueOf(data.get("MaxTTL").getAsString()));
            flow_builder.setSamplingInterval(Uint32.valueOf(data.get("SamplingRate").getAsString()));
            flow_builder.setEngineType(EngineType.forValue(data.get("EngineType").getAsInt()));
            flow_builder.setEngineId(Uint8.valueOf(data.get("EngineId").getAsString()));
            flow_builder.setSnmpIn(data.get("InIf").getAsInt());
            flow_builder.setSnmpOut(data.get("OutIf").getAsInt());
            
                    
            //TCP Flags
            Integer flags_int = data.get("TCPFlags").getAsInt();
            String flags_bits = toBinary(flags_int, 8);
            Boolean[] flags = toBooleanArray(flags_bits);
            TcpFlagsType tcp_flags = new TcpFlagsType(flags[0],flags[1],flags[2],flags[3],flags[4],flags[5],flags[6],flags[7]);
            flow_builder.setTcpFlags(tcp_flags);
                
            //ICMP type
            Integer icmp_type = data.get("IcmpType").getAsInt();
            Integer icmp_code = data.get("IcmpCode").getAsInt();
            Integer icmp = icmp_type*256 + icmp_code;
            flow_builder.setIcmpType(Uint16.valueOf(icmp));

            //Vlan container
            vlan_builder.setSrcId(Uint16.valueOf(data.get("SrcVlan").getAsString()));
            vlan_builder.setDstId(Uint16.valueOf(data.get("DstVlan").getAsString()));

            //MPLS container
            mpls_builder.setPalRd(Uint64.valueOf(data.get("MPLSPalRd").getAsString()));
            mpls_builder.setPrefixLen(PrefixLengthIpv4.getDefaultInstance(data.get("MPLSPrefixLen").getAsString()));
            mpls_builder.setTopLabelType(TopLabelType.forValue((data.get("MPLSTopLabelType").getAsInt())));
            mpls_builder.setLabel1(Uint32.valueOf(data.get("MPLS1Label").getAsString()));
            mpls_builder.setLabel2(Uint32.valueOf(data.get("MPLS2Label").getAsString()));
            mpls_builder.setLabel3(Uint32.valueOf(data.get("MPLS3Label").getAsString()));
            mpls_builder.setLabel4(Uint32.valueOf(data.get("MPLS4Label").getAsString()));
            mpls_builder.setLabel5(Uint32.valueOf(data.get("MPLS5Label").getAsString()));
            mpls_builder.setLabel6(Uint32.valueOf(data.get("MPLS6Label").getAsString()));
            mpls_builder.setLabel7(Uint32.valueOf(data.get("MPLS7Label").getAsString()));
            mpls_builder.setLabel8(Uint32.valueOf(data.get("MPLS8Label").getAsString()));
            mpls_builder.setLabel9(Uint32.valueOf(data.get("MPLS9Label").getAsString()));
            mpls_builder.setLabel10(Uint32.valueOf(data.get("MPLS10Label").getAsString()));

            String top_label_ip = data.get("MPLSTopLabelIP").getAsString();
            if(!top_label_ip.isEmpty())
            {
                mpls_builder.setTopLabelIp(Ipv4Address.getDefaultInstance(top_label_ip));
    
            }else{
                mpls_builder.setTopLabelIp(Ipv4Address.getDefaultInstance("0.0.0.0"));
            }

            //Associate each container of a flow-data-record
            Ipv4 ipv4_container = ipv4_builder.build();
            flow_builder.setIpv4(ipv4_container);

            Ipv6 ipv6_container = ipv6_builder.build();
            flow_builder.setIpv6(ipv6_container);

            Vlan vlan_container = vlan_builder.build();
            flow_builder.setVlan(vlan_container);

            Bgp bgp_container = bgp_builder.build();
            flow_builder.setBgp(bgp_container);

            Mpls mpls_container = mpls_builder.build();
            flow_builder.setMpls(mpls_container);
            
            //Build a list with all flow fields 
            FlowDataRecord flow_record = flow_builder.build();
            flow_list.add(flow_record);
            
        }   

        //Associate to each parent node
        exporter_builder.setFlowDataRecord(flow_list);
        ExportPacket export = exporter_builder.build();
        netflow_builder.setExportPacket(export);

        CollectorGoflow2 goflow2 = goflow2_builder.build();
        netflow_builder.setCollectorGoflow2(goflow2);
        

        LOG.info("Netflow Builder: " + netflow_builder.build().toString());
        final Netflow netflow = netflow_builder.build();
        InstanceIdentifier<Netflow> iid = InstanceIdentifier.create(Netflow.class);
        
        LOG.info("Netflow InstanceIdentifier (iid): " + iid);

        JsonObject gson_obj = new JsonObject();
            
        try {
            BindingNormalizedNodeSerializer codec = new BindingCodecContext(BindingRuntimeHelpers.createRuntimeContext(Netflow.class));
            Entry<YangInstanceIdentifier, NormalizedNode<?, ?>> normalized = codec.toNormalizedNode(iid, netflow);
            gson_obj = doConvert(schemaContext.getPath(), normalized.getValue());
        
            
        } catch (Exception ex) {
                //TODO: handle exception
                ex.printStackTrace();
                StringWriter errors = new StringWriter();
                ex.printStackTrace(new PrintWriter(errors));
                LOG.error(errors.toString());
            }
            
        /**
        * Instantiate JSONObject class from org.json library (https://mvnrepository.com/artifact/org.json/json) to get 
        * the JSON Object keys (.names() fuction).
        */

        LOG.info("Driver output JSON: " + gson_obj.toString());
        Gson gson_format = new GsonBuilder().setPrettyPrinting().create();
        return gson_format.toJson(gson_obj);
        
    }


    //To convert an integer to a binary string of a specific length with leading zeros
    public static String toBinary(int x, int len)
    {
        StringBuilder result = new StringBuilder();
 
        for (int i = len - 1; i >= 0 ; i--)
        {
            int mask = 1 << i;
            result.append((x & mask) != 0 ? 1 : 0);
        }
 
        return result.toString();
    }
    

    //To convert a binary string to a boolean array and sorts it according to TcpFlags constructor  
    public static Boolean[] toBooleanArray(String bits)
    {
        Boolean[] flags = new Boolean[bits.length()]; //MSB in array position 0
        Boolean[] flags_sorted = new Boolean[bits.length()]; 
        int[] index = {3, 0, 1, 7, 4, 5, 6, 2}; //indexes placed according to TcpFlags constructor

        for (int i=0; i<bits.length(); i++)
        {
            if(bits.charAt(i)=='1')
            {
                flags[i]=true;
           
            }else{
                flags[i]=false;
            }
        }
    
        
        for (int j=0; j<flags.length; j++)
        {
            flags_sorted[j]=flags[index[j]];
        }
        
        return flags_sorted;
    }
}


