/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package tid;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.Map.Entry;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import javax.crypto.Mac;
import javax.net.ssl.HandshakeCompletedListener;
import javax.swing.text.StyledEditorKit;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.JsonStreamParser;
import com.google.gson.stream.JsonWriter;


import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.java.io.TextInputFormat;
import org.apache.flink.core.fs.Path;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.connector.file.src.FileSource;
import org.apache.flink.connector.file.src.reader.TextLineInputFormat;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.sink.PrintSinkFunction;
import org.apache.flink.streaming.api.functions.sink.SinkFunction;
import org.apache.flink.streaming.api.functions.source.SourceFunction;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.util.Collector;


import org.apache.flink.api.java.DataSet;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
//import java.util.logging.Logger;
//import java.util.logging.LogManager;
import org.opendaylight.mdsal.binding.dom.codec.api.BindingNormalizedNodeSerializer;
import org.opendaylight.mdsal.binding.dom.codec.impl.BindingCodecContext;
import org.opendaylight.mdsal.binding.dom.codec.jar.bytebuddy.utility.visitor.ExceptionTableSensitiveMethodVisitor;
import org.opendaylight.mdsal.binding.runtime.spi.BindingRuntimeHelpers;
import org.opendaylight.mdsal.binding.spec.reflect.BindingReflections;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.Ipv6FlowLabel;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.Netflow;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.NetflowBuilder;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.PrefixLengthIpv4;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.PrefixLengthIpv6;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.SysUptime;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.VlanId;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFields.IpVersion;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFields.Protocol;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFields.TcpFlags;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.netflow.FieldsFlowRecord;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.netflow.FieldsFlowRecordBuilder;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.netflow.FieldsFlowRecordKey;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev130715.AsNumber;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev130715.Ipv4Address;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev130715.Ipv6Address;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev130715.PortNumber;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715.Counter64;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715.MacAddress;
import org.opendaylight.yangtools.yang.binding.InstanceIdentifier;
import org.opendaylight.yangtools.yang.common.Uint16;
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


/**
 * Flink Streaming Application with NetFlow YANGTools driver
 */
public class NetflowDriver {

	public static void main(String[] args) throws Exception {

        Properties props = new Properties();
		props.put("bootstrap.servers", "kafka:9092");
        props.put("group.id", "netflow-group");
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        
		// Set up the streaming execution environment
		StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        // Consume data stream from the Kafka input topic, then from Goflow2 collector
        FlinkKafkaConsumer<String> consumer = new FlinkKafkaConsumer<String>(args[0], new SimpleStringSchema(), props);
        // Start reading from the earliest record
        //consumer.setStartFromEarliest();

        DataStream<String> stringInputStream = env.addSource(consumer);

        DataStream<String> json_ietf = stringInputStream.map(new MapFunction<String, String>(){
			@Override
		    public String map(String json) throws Exception {
				try {                    
                    JsonStreamParser p = new JsonStreamParser(json);
                    json = driver(p);
				} catch (JsonParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return json;
		    }
		});

        //Produce data stream on the Kafka output topic
		FlinkKafkaProducer<String> producer = new FlinkKafkaProducer<String>(args[1], new SimpleStringSchema(), props);
		json_ietf.addSink(producer);
        
        //Execute program
		env.execute("Netflow Driver");
	}

	// Schema context initialization
    // Code borrowed from:
    // https://github.com/opendaylight/jsonrpc/blob/1331a9f73db2fe308e3bbde00ff42359431dbc7f/
    // binding-adapter/src/main/java/org/opendaylight/jsonrpc/binding/EmbeddedRpcInvocationAdapter.java#L38
    private static final EffectiveModelContext schemaContext = BindingRuntimeHelpers
                .createEffectiveModel(BindingReflections.loadModuleInfos());

    
    // Code borrowed from:
    // https://git.opendaylight.org/gerrit/gitweb?p=jsonrpc.git;a=blob_plain;f=impl/src/
    // main/java/org/opendaylight/jsonrpc/impl/
    private static final String JSON_IO_ERROR = "I/O problem in JSON codec";
    private static final Logger LOG = LogManager.getLogger(NetflowDriver.class);
    private static final JSONCodecFactorySupplier CODEC_SUPPLIER =
        JSONCodecFactorySupplier.DRAFT_LHOTKA_NETMOD_YANG_JSON_02;
    private static final JsonParser PARSER = new JsonParser();

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
        Integer cont=0;

        // Init YANG Binding builders
        NetflowBuilder netflow_builder = new NetflowBuilder();
        FieldsFlowRecordBuilder fields_builder = new FieldsFlowRecordBuilder();
        ArrayList<FieldsFlowRecord> fields_list = new ArrayList<FieldsFlowRecord>();

        
        while (p.hasNext()){
            cont = cont+1;
            JsonElement jsonElement = p.next();
            JsonObject data = jsonElement.getAsJsonObject();

            LOG.info("Input Stream JSON: " + data.toString());
            
            // Parse input JSON data
            String type_version = data.get("Type").getAsString();
            String name_key = "FIELDS_" + type_version + "_"+ cont;

            //Key fields
            fields_builder.setName(name_key); //Fields flow's key
                
            //Map common fields (first check if field is empty)
            //First and last switched   
            fields_builder.setFirstSwitched(SysUptime.getDefaultInstance(data.get("TimeFlowStart").getAsString()));
            fields_builder.setLastSwitched(SysUptime.getDefaultInstance(data.get("TimeFlowEnd").getAsString()));
                
            //Bytes
            fields_builder.setBytesIn(Counter64.getDefaultInstance(data.get("Bytes").getAsString()));
                    
            //Packets
            fields_builder.setPktsIn(Counter64.getDefaultInstance(data.get("Packets").getAsString()));
                    
            //Protocol
            fields_builder.setProtocol(Protocol.forValue(data.get("Proto").getAsInt()));
                    
            //Source and destination port
            fields_builder.setSrcPort(PortNumber.getDefaultInstance(data.get("SrcPort").getAsString()));
            fields_builder.setDstPort(PortNumber.getDefaultInstance(data.get("DstPort").getAsString()));
                    
            //Snmp index
            if(data.get("InIf").getAsInt()>0) //Check if input interface index is greater than zero
            {
                fields_builder.setSnmpIn(data.get("InIf").getAsInt());
            }
                    
            if(data.get("OutIf").getAsInt()>0) //Check if output interface index is greater than zero
            {
                fields_builder.setSnmpOut(data.get("OutIf").getAsInt());
            }

            //Source and destination mac
            fields_builder.setSrcMac(MacAddress.getDefaultInstance(data.get("SrcMac").getAsString()));
            fields_builder.setDstMac(MacAddress.getDefaultInstance(data.get("DstMac").getAsString()));
                    
            //Vlan ID
            fields_builder.setSrcVlan(VlanId.getDefaultInstance(data.get("SrcVlan").getAsString()));
            fields_builder.setDstVlan(VlanId.getDefaultInstance(data.get("DstVlan").getAsString()));
                    
            //Type of Service
            fields_builder.setSrcTos(Uint8.valueOf(data.get("IPTos").getAsString()));
                    
            //TCP Flags
            Integer flags_int = data.get("TCPFlags").getAsInt();
            String flags_bits = toBinary(flags_int, 8);
            Boolean[] flags = toBooleanArray(flags_bits);
            TcpFlags tcp_flags = new TcpFlags(flags[0],flags[1],flags[2],flags[3],flags[4],flags[5],flags[6],flags[7]);
            fields_builder.setTcpFlags(tcp_flags);
                    
            //ICMP type
            Integer icmp_type = data.get("IcmpType").getAsInt();
            Integer icmp_code = data.get("IcmpCode").getAsInt();
            Integer icmp = icmp_type*256 + icmp_code;
            fields_builder.setIcmpType(Uint16.valueOf(icmp));

            //AS number
            String src_as = data.get("SrcAS").getAsString(); //Then used
            fields_builder.setSrcAs(AsNumber.getDefaultInstance(src_as));
            fields_builder.setDstAs(AsNumber.getDefaultInstance(data.get("DstAS").getAsString()));

            //Check if IPv4 or IPv6
            Integer etype = data.get("Etype").getAsInt();
                    
            if(etype==2048) //IPv4
            {
                fields_builder.setIpVersion(IpVersion.forValue(4));
                fields_builder.setSrcAddress(Ipv4Address.getDefaultInstance(data.get("SrcAddr").getAsString()));
                fields_builder.setDstAddress(Ipv4Address.getDefaultInstance(data.get("DstAddr").getAsString()));
                fields_builder.setSrcMask(PrefixLengthIpv4.getDefaultInstance(data.get("SrcNet").getAsString()));
                fields_builder.setDstMask(PrefixLengthIpv4.getDefaultInstance(data.get("DstNet").getAsString()));
                        
                        
                if(!data.get("NextHop").getAsString().isEmpty())
                {
                    if(!data.get("NextHopAS").getAsString().equals(src_as))//check if next-hop is in another AS
                    {
                        fields_builder.setBgpNextHop(Ipv4Address.getDefaultInstance(data.get("NextHop").getAsString()));
                    }
                    else{
                        fields_builder.setNextHop(Ipv4Address.getDefaultInstance(data.get("NextHop").getAsString()));
                    }
                }
                        
            } 
                    
            else if (etype==34525) //IPv6
            {
                fields_builder.setIpVersion(IpVersion.forValue(6));
                fields_builder.setSrcAddressIpv6(Ipv6Address.getDefaultInstance(data.get("SrcAddr").getAsString()));
                fields_builder.setDstAddressIpv6(Ipv6Address.getDefaultInstance(data.get("DstAddr").getAsString()));
                fields_builder.setFlowLabelIpv6(Ipv6FlowLabel.getDefaultInstance(data.get("IPv6FlowLabel").getAsString()));
                fields_builder.setSrcMaskIpv6(PrefixLengthIpv6.getDefaultInstance(data.get("SrcNet").getAsString()));
                fields_builder.setDstMaskIpv6(PrefixLengthIpv6.getDefaultInstance(data.get("DstNet").getAsString()));

                if(!data.get("NextHop").getAsString().isEmpty())
                {
                    if(!data.get("NextHopAS").getAsString().equals(src_as))//check if next-hop is in another AS
                    {
                        fields_builder.setBgpNextHopIpv6(Ipv6Address.getDefaultInstance(data.get("NextHop").getAsString()));
                    }
                    else{
                        fields_builder.setNextHopIpv6(Ipv6Address.getDefaultInstance(data.get("NextHop").getAsString()));
                    }
                }
                        
            }

            //Build a list with all flow fields 
            FieldsFlowRecord fields = fields_builder.build();
            fields_list.add(fields);
            
        }   

        //Associate to the parent node
        netflow_builder.setFieldsFlowRecord(fields_list);
        
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

