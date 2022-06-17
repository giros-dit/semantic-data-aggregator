package org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008;
import java.lang.Class;
import java.lang.Integer;
import java.lang.Override;
import java.lang.String;
import javax.annotation.processing.Generated;
import org.eclipse.jdt.annotation.NonNull;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.common.flow.fields.Application;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.common.flow.fields.Bgp;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.common.flow.fields.Ipv4;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.common.flow.fields.Ipv6;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.common.flow.fields.Layer2PktSection;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.common.flow.fields.Mpls;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.common.flow.fields.PermanentFlow;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.common.flow.fields.Vlan;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev130715.Dscp;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev130715.PortNumber;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715.Counter64;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715.MacAddress;
import org.opendaylight.yangtools.yang.binding.DataObject;
import org.opendaylight.yangtools.yang.common.QName;
import org.opendaylight.yangtools.yang.common.Uint16;
import org.opendaylight.yangtools.yang.common.Uint32;
import org.opendaylight.yangtools.yang.common.Uint64;
import org.opendaylight.yangtools.yang.common.Uint8;

/**
 * Fields of a Netflow version 9 Flow Data Record. The names and semantics of 
 * fields used follow table 6 of the Cisco white paper (mentioned in the 
 * section).
 *
 * <p>
 * This class represents the following YANG schema fragment defined in module <b>netflow-v9</b>
 * <pre>
 * grouping common-flow-fields {
 *   leaf bytes-in {
 *     type yang:counter64;
 *     units bytes;
 *   }
 *   leaf bytes-out {
 *     type yang:counter64;
 *     units bytes;
 *   }
 *   leaf pkts-in {
 *     type yang:counter64;
 *     units packets;
 *   }
 *   leaf pkts-out {
 *     type yang:counter64;
 *     units packets;
 *   }
 *   leaf flows {
 *     type yang:counter64;
 *     units flows;
 *   }
 *   leaf protocol {
 *     type net-v9:protocol-type;
 *   }
 *   leaf src-tos {
 *     type uint8;
 *   }
 *   leaf dst-tos {
 *     type uint8;
 *   }
 *   leaf tcp-flags {
 *     type net-v9:tcp-flags-type;
 *   }
 *   leaf src-port {
 *     type inet:port-number;
 *   }
 *   leaf dst-port {
 *     type inet:port-number;
 *   }
 *   leaf snmp-in {
 *     type int32 {
 *       range 0..2147483647;
 *     }
 *   }
 *   leaf snmp-out {
 *     type int32 {
 *       range 0..2147483647;
 *     }
 *   }
 *   leaf bytes-out-mul {
 *     type yang:counter64;
 *     units bytes;
 *   }
 *   leaf pkts-out-mul {
 *     type yang:counter64;
 *     units packets;
 *   }
 *   leaf first-switched {
 *     type uint64;
 *     units milliseconds;
 *   }
 *   leaf last-switched {
 *     type uint64;
 *     units milliseconds;
 *   }
 *   leaf min-pkt-len {
 *     type uint16;
 *   }
 *   leaf max-pkt-len {
 *     type uint16;
 *   }
 *   leaf icmp-type {
 *     type uint16;
 *   }
 *   leaf igmp-type {
 *     type net-v9:igmp-type;
 *   }
 *   leaf sampler-name {
 *     type string;
 *   }
 *   leaf sampling-interval {
 *     type uint32;
 *   }
 *   leaf sampling-algorithm {
 *     type net-v9:sampling-mode-type;
 *   }
 *   leaf flow-active-tout {
 *     type uint16;
 *     units seconds;
 *     default 1800;
 *   }
 *   leaf flow-inactive-tout {
 *     type uint16;
 *     units seconds;
 *     default 15;
 *   }
 *   leaf engine-type {
 *     type net-v9:engine-type;
 *   }
 *   leaf engine-id {
 *     type uint8;
 *   }
 *   leaf tot-bytes-exp {
 *     type yang:counter64;
 *     units bytes;
 *   }
 *   leaf tot-pkts-exp {
 *     type yang:counter64;
 *     units packets;
 *   }
 *   leaf tot-flows-exp {
 *     type yang:counter64;
 *     units flows;
 *   }
 *   leaf flow-sampler-id {
 *     type uint8;
 *   }
 *   leaf flow-sampler-mode {
 *     type net-v9:sampling-mode-type;
 *   }
 *   leaf flow-sampler-random {
 *     type uint32;
 *   }
 *   leaf min-ttl {
 *     type uint8;
 *   }
 *   leaf max-ttl {
 *     type uint8;
 *   }
 *   leaf src-mac-in {
 *     type yang:mac-address;
 *   }
 *   leaf dst-mac-in {
 *     type yang:mac-address;
 *   }
 *   leaf src-mac-out {
 *     type yang:mac-address;
 *   }
 *   leaf dst-mac-out {
 *     type yang:mac-address;
 *   }
 *   leaf ip-version {
 *     type net-v9:ip-version-type;
 *   }
 *   leaf direction {
 *     type net-v9:direction-type;
 *   }
 *   leaf if-name {
 *     type string;
 *   }
 *   leaf if-desc {
 *     type string;
 *   }
 *   leaf frag-offset {
 *     type uint16;
 *   }
 *   leaf forwarding-status {
 *     type net-v9:forwarding-status-type;
 *   }
 *   leaf postip-dscp {
 *     type inet:dscp;
 *   }
 *   leaf repl-factor-mul {
 *     type uint32;
 *   }
 *   container ipv4 {
 *     leaf src-address {
 *       type inet:ipv4-address;
 *     }
 *     leaf dst-address {
 *       type inet:ipv4-address;
 *     }
 *     leaf src-mask {
 *       type prefix-length-ipv4;
 *     }
 *     leaf dst-mask {
 *       type prefix-length-ipv4;
 *     }
 *     leaf src-prefix {
 *       type inet:ipv4-prefix;
 *     }
 *     leaf dst-prefix {
 *       type inet:ipv4-prefix;
 *     }
 *     leaf next-hop {
 *       type inet:ipv4-address;
 *     }
 *     leaf identification {
 *       type uint16;
 *     }
 *   }
 *   container ipv6 {
 *     leaf src-address {
 *       type inet:ipv6-address;
 *     }
 *     leaf dst-address {
 *       type inet:ipv6-address;
 *     }
 *     leaf src-mask {
 *       type prefix-length-ipv6;
 *     }
 *     leaf dst-mask {
 *       type prefix-length-ipv6;
 *     }
 *     leaf next-hop {
 *       type inet:ipv6-address;
 *     }
 *     leaf flow-label {
 *       type inet:ipv6-flow-label;
 *     }
 *     leaf opt-headers {
 *       type uint32;
 *     }
 *   }
 *   container mpls {
 *     leaf pal-rd {
 *       type uint64;
 *     }
 *     leaf prefix-len {
 *       type prefix-length-ipv4;
 *     }
 *     leaf top-label-type {
 *       type net-v9:top-label-type;
 *     }
 *     leaf top-label-ip {
 *       type inet:ipv4-address;
 *     }
 *     leaf label-1 {
 *       type uint32 {
 *         range 0..16777215;
 *       }
 *     }
 *     leaf label-2 {
 *       type uint32 {
 *         range 0..16777215;
 *       }
 *     }
 *     leaf label-3 {
 *       type uint32 {
 *         range 0..16777215;
 *       }
 *     }
 *     leaf label-4 {
 *       type uint32 {
 *         range 0..16777215;
 *       }
 *     }
 *     leaf label-5 {
 *       type uint32 {
 *         range 0..16777215;
 *       }
 *     }
 *     leaf label-6 {
 *       type uint32 {
 *         range 0..16777215;
 *       }
 *     }
 *     leaf label-7 {
 *       type uint32 {
 *         range 0..16777215;
 *       }
 *     }
 *     leaf label-8 {
 *       type uint32 {
 *         range 0..16777215;
 *       }
 *     }
 *     leaf label-9 {
 *       type uint32 {
 *         range 0..16777215;
 *       }
 *     }
 *     leaf label-10 {
 *       type uint32 {
 *         range 0..16777215;
 *       }
 *     }
 *   }
 *   container bgp {
 *     leaf src-as {
 *       type inet:as-number;
 *     }
 *     leaf dst-as {
 *       type inet:as-number;
 *     }
 *     leaf next-hop {
 *       type inet:ipv4-address;
 *     }
 *     leaf next-hop-ipv6 {
 *       type inet:ipv6-address;
 *     }
 *     leaf src-traffic-id {
 *       type uint32;
 *     }
 *     leaf dst-traffic-id {
 *       type uint32;
 *     }
 *   }
 *   container vlan {
 *     leaf src-id {
 *       type uint16 {
 *         range 0..4095;
 *       }
 *     }
 *     leaf dst-id {
 *       type uint16 {
 *         range 0..4095;
 *       }
 *     }
 *   }
 *   container permanent-flow {
 *     leaf bytes-in {
 *       type yang:counter64;
 *       units bytes;
 *     }
 *     leaf pkts-in {
 *       type yang:counter64;
 *       units packets;
 *     }
 *   }
 *   container application {
 *     leaf desc {
 *       type string;
 *     }
 *     leaf tag {
 *       type string;
 *     }
 *     leaf name {
 *       type string;
 *     }
 *   }
 *   container layer2-pkt-section {
 *     leaf offset {
 *       type uint16;
 *     }
 *     leaf size {
 *       type uint16;
 *     }
 *     leaf data {
 *       type string;
 *     }
 *   }
 * }
 * </pre>The schema path to identify an instance is
 * <i>netflow-v9/common-flow-fields</i>
 *
 */
@Generated("mdsal-binding-generator")
public interface CommonFlowFields
    extends
    DataObject
{



    public static final @NonNull QName QNAME = $YangModuleInfoImpl.qnameOf("common-flow-fields");

    @Override
    Class<? extends CommonFlowFields> implementedInterface();
    
    /**
     * Return bytesIn, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Incoming counter for the number of bytes associated with an IP flow.
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715.Counter64} bytesIn, or {@code null} if it is not present.
     *
     */
    Counter64 getBytesIn();
    
    /**
     * Return bytesOut, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Outgoing counter for the number of bytes associated with an IP flow.
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715.Counter64} bytesOut, or {@code null} if it is not present.
     *
     */
    Counter64 getBytesOut();
    
    /**
     * Return pktsIn, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Incoming counter for the number of packets associated with an IP flow.
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715.Counter64} pktsIn, or {@code null} if it is not present.
     *
     */
    Counter64 getPktsIn();
    
    /**
     * Return pktsOut, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Outgoing counter for the number of packets associated with an IP flow.
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715.Counter64} pktsOut, or {@code null} if it is not present.
     *
     */
    Counter64 getPktsOut();
    
    /**
     * Return flows, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Number of flows that were aggregated
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715.Counter64} flows, or {@code null} if it is not present.
     *
     */
    Counter64 getFlows();
    
    /**
     * Return protocol, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         IP protocol byte
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.ProtocolType} protocol, or {@code null} if it is not present.
     *
     */
    ProtocolType getProtocol();
    
    /**
     * Return srcTos, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Type of service byte setting when entering incoming interface
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yangtools.yang.common.Uint8} srcTos, or {@code null} if it is not present.
     *
     */
    Uint8 getSrcTos();
    
    /**
     * Return dstTos, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Type of service byte setting when exiting outgoing interface
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yangtools.yang.common.Uint8} dstTos, or {@code null} if it is not present.
     *
     */
    Uint8 getDstTos();
    
    /**
     * Return tcpFlags, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Cumulative of all the TCP flags seen in an IP flow
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.TcpFlagsType} tcpFlags, or {@code null} if it is not present.
     *
     */
    TcpFlagsType getTcpFlags();
    
    /**
     * Return srcPort, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         TCP/UDP source port number used in the flow
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev130715.PortNumber} srcPort, or {@code null} if it is not present.
     *
     */
    PortNumber getSrcPort();
    
    /**
     * Return dstPort, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         TCP/UDP destination port number used in the flow
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev130715.PortNumber} dstPort, or {@code null} if it is not present.
     *
     */
    PortNumber getDstPort();
    
    /**
     * Return snmpIn, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Input interface index
     *     </code>
     * </pre>
     *
     * @return {@code java.lang.Integer} snmpIn, or {@code null} if it is not present.
     *
     */
    Integer getSnmpIn();
    
    /**
     * Return snmpOut, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Output interface index
     *     </code>
     * </pre>
     *
     * @return {@code java.lang.Integer} snmpOut, or {@code null} if it is not present.
     *
     */
    Integer getSnmpOut();
    
    /**
     * Return bytesOutMul, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         IP multicast outgoing byte counter associated with the IP flow
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715.Counter64} bytesOutMul, or {@code null} if it is not present.
     *
     */
    Counter64 getBytesOutMul();
    
    /**
     * Return pktsOutMul, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         IP multicast outgoing packet counter associated with the IP flow
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715.Counter64} pktsOutMul, or {@code null} if it is not present.
     *
     */
    Counter64 getPktsOutMul();
    
    /**
     * Return firstSwitched, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         System uptime at which the first packet of this flow was switched
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yangtools.yang.common.Uint64} firstSwitched, or {@code null} if it is not present.
     *
     */
    Uint64 getFirstSwitched();
    
    /**
     * Return lastSwitched, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         System uptime at which the last packet of this flow was switched
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yangtools.yang.common.Uint64} lastSwitched, or {@code null} if it is not present.
     *
     */
    Uint64 getLastSwitched();
    
    /**
     * Return minPktLen, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Minimum IP packet length on incoming packets of the flow
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yangtools.yang.common.Uint16} minPktLen, or {@code null} if it is not present.
     *
     */
    Uint16 getMinPktLen();
    
    /**
     * Return maxPktLen, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Maximum IP packet length on incoming packets of the flow
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yangtools.yang.common.Uint16} maxPktLen, or {@code null} if it is not present.
     *
     */
    Uint16 getMaxPktLen();
    
    /**
     * Return icmpType, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Internet Control Message Protocol (ICMP) packet type; reported as ((ICMP
     *         Type*256) + ICMP code)
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yangtools.yang.common.Uint16} icmpType, or {@code null} if it is not present.
     *
     */
    Uint16 getIcmpType();
    
    /**
     * Return igmpType, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Internet Group Management Protocol (IGMP) packet type
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.IgmpType} igmpType, or {@code null} if it is not present.
     *
     */
    IgmpType getIgmpType();
    
    /**
     * Return samplerName, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Name of the flow sampler
     *     </code>
     * </pre>
     *
     * @return {@code java.lang.String} samplerName, or {@code null} if it is not present.
     *
     */
    String getSamplerName();
    
    /**
     * Return samplingInterval, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         When using sampled NetFlow, the rate at which packets are sampled
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yangtools.yang.common.Uint32} samplingInterval, or {@code null} if it is not present.
     *
     */
    Uint32 getSamplingInterval();
    
    /**
     * Return samplingAlgorithm, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         The type of algorithm used for sampled NetFlow
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.SamplingModeType} samplingAlgorithm, or {@code null} if it is not present.
     *
     */
    SamplingModeType getSamplingAlgorithm();
    
    /**
     * Return flowActiveTout, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Timeout value (in seconds) for active flow entries in the NetFlow cache
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yangtools.yang.common.Uint16} flowActiveTout, or {@code null} if it is not present.
     *
     */
    Uint16 getFlowActiveTout();
    
    /**
     * Return flowInactiveTout, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Timeout value (in seconds) for inactive flow entries in the NetFlow cache
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yangtools.yang.common.Uint16} flowInactiveTout, or {@code null} if it is not present.
     *
     */
    Uint16 getFlowInactiveTout();
    
    /**
     * Return engineType, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Type of flow switching engine
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.EngineType} engineType, or {@code null} if it is not present.
     *
     */
    EngineType getEngineType();
    
    /**
     * Return engineId, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         ID number of the flow switching engine
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yangtools.yang.common.Uint8} engineId, or {@code null} if it is not present.
     *
     */
    Uint8 getEngineId();
    
    /**
     * Return totBytesExp, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Counter for the number of bytes exported by the Observation Domain
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715.Counter64} totBytesExp, or {@code null} if it is not present.
     *
     */
    Counter64 getTotBytesExp();
    
    /**
     * Return totPktsExp, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Counter for the number of packets exported by the Observation Domain
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715.Counter64} totPktsExp, or {@code null} if it is not present.
     *
     */
    Counter64 getTotPktsExp();
    
    /**
     * Return totFlowsExp, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Counter for the number of flows exported by the Observation Domain
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715.Counter64} totFlowsExp, or {@code null} if it is not present.
     *
     */
    Counter64 getTotFlowsExp();
    
    /**
     * Return flowSamplerId, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Identifier shown in 'show flow-sampler'
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yangtools.yang.common.Uint8} flowSamplerId, or {@code null} if it is not present.
     *
     */
    Uint8 getFlowSamplerId();
    
    /**
     * Return flowSamplerMode, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         The type of algorithm used for sampling data
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.SamplingModeType} flowSamplerMode, or {@code null} if it is not present.
     *
     */
    SamplingModeType getFlowSamplerMode();
    
    /**
     * Return flowSamplerRandom, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Packet interval at which to sample when Random Sampling is used
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yangtools.yang.common.Uint32} flowSamplerRandom, or {@code null} if it is not present.
     *
     */
    Uint32 getFlowSamplerRandom();
    
    /**
     * Return minTtl, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Minimum TTL on incoming packets of the flow
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yangtools.yang.common.Uint8} minTtl, or {@code null} if it is not present.
     *
     */
    Uint8 getMinTtl();
    
    /**
     * Return maxTtl, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Maximum TTL on incoming packets of the flow
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yangtools.yang.common.Uint8} maxTtl, or {@code null} if it is not present.
     *
     */
    Uint8 getMaxTtl();
    
    /**
     * Return srcMacIn, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Incoming source MAC address
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715.MacAddress} srcMacIn, or {@code null} if it is not present.
     *
     */
    MacAddress getSrcMacIn();
    
    /**
     * Return dstMacIn, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Incoming destination MAC address
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715.MacAddress} dstMacIn, or {@code null} if it is not present.
     *
     */
    MacAddress getDstMacIn();
    
    /**
     * Return srcMacOut, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Outgoing source MAC address
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715.MacAddress} srcMacOut, or {@code null} if it is not present.
     *
     */
    MacAddress getSrcMacOut();
    
    /**
     * Return dstMacOut, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Outgoing destination MAC address
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715.MacAddress} dstMacOut, or {@code null} if it is not present.
     *
     */
    MacAddress getDstMacOut();
    
    /**
     * Return ipVersion, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         IP Protocol version. If not present, version 4 is assumed
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.IpVersionType} ipVersion, or {@code null} if it is not present.
     *
     */
    IpVersionType getIpVersion();
    
    /**
     * Return direction, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Flow direction
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.DirectionType} direction, or {@code null} if it is not present.
     *
     */
    DirectionType getDirection();
    
    /**
     * Return ifName, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Shortened interface name
     *     </code>
     * </pre>
     *
     * @return {@code java.lang.String} ifName, or {@code null} if it is not present.
     *
     */
    String getIfName();
    
    /**
     * Return ifDesc, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Full interface name
     *     </code>
     * </pre>
     *
     * @return {@code java.lang.String} ifDesc, or {@code null} if it is not present.
     *
     */
    String getIfDesc();
    
    /**
     * Return fragOffset, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         The fragment-offset value from fragmented IP packets
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yangtools.yang.common.Uint16} fragOffset, or {@code null} if it is not present.
     *
     */
    Uint16 getFragOffset();
    
    /**
     * Return forwardingStatus, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Forwarding status is encoded on 1 byte with the 2 left bits giving the status
     *         and the 6 remaining bits giving the reason code
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.ForwardingStatusType} forwardingStatus, or {@code null} if it is not present.
     *
     */
    ForwardingStatusType getForwardingStatus();
    
    /**
     * Return postipDscp, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         The value of a Differentiated Services Code Point (DSCP) encoded in the
     *         Differentiated Services Field, after modification
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev130715.Dscp} postipDscp, or {@code null} if it is not present.
     *
     */
    Dscp getPostipDscp();
    
    /**
     * Return replFactorMul, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Multicast replication factor
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yangtools.yang.common.Uint32} replFactorMul, or {@code null} if it is not present.
     *
     */
    Uint32 getReplFactorMul();
    
    /**
     * Return ipv4, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         This container collects all metrics related to IPv4
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.common.flow.fields.Ipv4} ipv4, or {@code null} if it is not present.
     *
     */
    Ipv4 getIpv4();
    
    /**
     * Return ipv6, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         This container collects all metrics related to IPv6
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.common.flow.fields.Ipv6} ipv6, or {@code null} if it is not present.
     *
     */
    Ipv6 getIpv6();
    
    /**
     * Return mpls, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         This container collects all the metrics associated with MPLS (MultiProtocol
     *         Label Switching)
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.common.flow.fields.Mpls} mpls, or {@code null} if it is not present.
     *
     */
    Mpls getMpls();
    
    /**
     * Return bgp, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         This container collects all the metrics associated with BGP (Border Gateway
     *         Protocol)
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.common.flow.fields.Bgp} bgp, or {@code null} if it is not present.
     *
     */
    Bgp getBgp();
    
    /**
     * Return vlan, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         This container collects the metrics related to a VLAN
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.common.flow.fields.Vlan} vlan, or {@code null} if it is not present.
     *
     */
    Vlan getVlan();
    
    /**
     * Return permanentFlow, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         This container collects all metrics related to a permanent flow
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.common.flow.fields.PermanentFlow} permanentFlow, or {@code null} if it is not present.
     *
     */
    PermanentFlow getPermanentFlow();
    
    /**
     * Return application, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         This container collects all application-related metrics
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.common.flow.fields.Application} application, or {@code null} if it is not present.
     *
     */
    Application getApplication();
    
    /**
     * Return layer2PktSection, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         This container collects all metrics related to the Layer 2 packet section
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.common.flow.fields.Layer2PktSection} layer2PktSection, or {@code null} if it is not present.
     *
     */
    Layer2PktSection getLayer2PktSection();

}

