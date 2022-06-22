package org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.netflow.export.packet;
import java.lang.Class;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.Short;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.math.BigInteger;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import javax.annotation.processing.Generated;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.DirectionType;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.EngineType;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.ForwardingStatusType;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.IgmpType;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.IpVersionType;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.ProtocolType;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.SamplingModeType;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.TcpFlagsType;
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
import org.opendaylight.yangtools.concepts.Builder;
import org.opendaylight.yangtools.yang.binding.AbstractAugmentable;
import org.opendaylight.yangtools.yang.binding.Augmentation;
import org.opendaylight.yangtools.yang.binding.CodeHelpers;
import org.opendaylight.yangtools.yang.binding.DataObject;
import org.opendaylight.yangtools.yang.common.Uint16;
import org.opendaylight.yangtools.yang.common.Uint32;
import org.opendaylight.yangtools.yang.common.Uint64;
import org.opendaylight.yangtools.yang.common.Uint8;

/**
 * Class that builds {@link FlowDataRecordBuilder} instances. Overall design of the class is that of a
 * <a href="https://en.wikipedia.org/wiki/Fluent_interface">fluent interface</a>, where method chaining is used.
 * 
 * <p>
 * In general, this class is supposed to be used like this template:
 * <pre>
 *   <code>
 *     FlowDataRecordBuilder createTarget(int fooXyzzy, int barBaz) {
 *         return new FlowDataRecordBuilderBuilder()
 *             .setFoo(new FooBuilder().setXyzzy(fooXyzzy).build())
 *             .setBar(new BarBuilder().setBaz(barBaz).build())
 *             .build();
 *     }
 *   </code>
 * </pre>
 * 
 * <p>
 * This pattern is supported by the immutable nature of FlowDataRecordBuilder, as instances can be freely passed around without
 * worrying about synchronization issues.
 * 
 * <p>
 * As a side note: method chaining results in:
 * <ul>
 *   <li>very efficient Java bytecode, as the method invocation result, in this case the Builder reference, is
 *       on the stack, so further method invocations just need to fill method arguments for the next method
 *       invocation, which is terminated by {@link #build()}, which is then returned from the method</li>
 *   <li>better understanding by humans, as the scope of mutable state (the builder) is kept to a minimum and is
 *       very localized</li>
 *   <li>better optimization oportunities, as the object scope is minimized in terms of invocation (rather than
 *       method) stack, making <a href="https://en.wikipedia.org/wiki/Escape_analysis">escape analysis</a> a lot
 *       easier. Given enough compiler (JIT/AOT) prowess, the cost of th builder object can be completely
 *       eliminated</li>
 * </ul>
 * 
 * @see FlowDataRecordBuilder
 * @see Builder
 *
 */
@Generated("mdsal-binding-generator")
public class FlowDataRecordBuilder implements Builder<FlowDataRecord> {

    private Application _application;
    private Bgp _bgp;
    private Counter64 _bytesIn;
    private Counter64 _bytesOut;
    private Counter64 _bytesOutMul;
    private DirectionType _direction;
    private MacAddress _dstMacIn;
    private MacAddress _dstMacOut;
    private PortNumber _dstPort;
    private Uint8 _dstTos;
    private Uint8 _engineId;
    private EngineType _engineType;
    private Uint64 _firstSwitched;
    private Uint16 _flowActiveTout;
    private Integer _flowId;
    private Uint16 _flowInactiveTout;
    private Uint8 _flowSamplerId;
    private SamplingModeType _flowSamplerMode;
    private Uint32 _flowSamplerRandom;
    private Counter64 _flows;
    private ForwardingStatusType _forwardingStatus;
    private Uint16 _fragOffset;
    private Uint16 _icmpType;
    private String _ifDesc;
    private String _ifName;
    private IgmpType _igmpType;
    private IpVersionType _ipVersion;
    private Ipv4 _ipv4;
    private Ipv6 _ipv6;
    private Uint64 _lastSwitched;
    private Layer2PktSection _layer2PktSection;
    private Uint16 _maxPktLen;
    private Uint8 _maxTtl;
    private Uint16 _minPktLen;
    private Uint8 _minTtl;
    private Mpls _mpls;
    private PermanentFlow _permanentFlow;
    private Counter64 _pktsIn;
    private Counter64 _pktsOut;
    private Counter64 _pktsOutMul;
    private Dscp _postipDscp;
    private ProtocolType _protocol;
    private Uint32 _replFactorMul;
    private String _samplerName;
    private SamplingModeType _samplingAlgorithm;
    private Uint32 _samplingInterval;
    private Integer _snmpIn;
    private Integer _snmpOut;
    private MacAddress _srcMacIn;
    private MacAddress _srcMacOut;
    private PortNumber _srcPort;
    private Uint8 _srcTos;
    private TcpFlagsType _tcpFlags;
    private Counter64 _totBytesExp;
    private Counter64 _totFlowsExp;
    private Counter64 _totPktsExp;
    private Vlan _vlan;
    private FlowDataRecordKey key;


    Map<Class<? extends Augmentation<FlowDataRecord>>, Augmentation<FlowDataRecord>> augmentation = Collections.emptyMap();

    public FlowDataRecordBuilder() {
    }
    
    
    
    public FlowDataRecordBuilder(org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFlowFields arg) {
        this._bytesIn = arg.getBytesIn();
        this._bytesOut = arg.getBytesOut();
        this._pktsIn = arg.getPktsIn();
        this._pktsOut = arg.getPktsOut();
        this._flows = arg.getFlows();
        this._protocol = arg.getProtocol();
        this._srcTos = arg.getSrcTos();
        this._dstTos = arg.getDstTos();
        this._tcpFlags = arg.getTcpFlags();
        this._srcPort = arg.getSrcPort();
        this._dstPort = arg.getDstPort();
        this._snmpIn = arg.getSnmpIn();
        this._snmpOut = arg.getSnmpOut();
        this._bytesOutMul = arg.getBytesOutMul();
        this._pktsOutMul = arg.getPktsOutMul();
        this._firstSwitched = arg.getFirstSwitched();
        this._lastSwitched = arg.getLastSwitched();
        this._minPktLen = arg.getMinPktLen();
        this._maxPktLen = arg.getMaxPktLen();
        this._icmpType = arg.getIcmpType();
        this._igmpType = arg.getIgmpType();
        this._samplerName = arg.getSamplerName();
        this._samplingInterval = arg.getSamplingInterval();
        this._samplingAlgorithm = arg.getSamplingAlgorithm();
        this._flowActiveTout = arg.getFlowActiveTout();
        this._flowInactiveTout = arg.getFlowInactiveTout();
        this._engineType = arg.getEngineType();
        this._engineId = arg.getEngineId();
        this._totBytesExp = arg.getTotBytesExp();
        this._totPktsExp = arg.getTotPktsExp();
        this._totFlowsExp = arg.getTotFlowsExp();
        this._flowSamplerId = arg.getFlowSamplerId();
        this._flowSamplerMode = arg.getFlowSamplerMode();
        this._flowSamplerRandom = arg.getFlowSamplerRandom();
        this._minTtl = arg.getMinTtl();
        this._maxTtl = arg.getMaxTtl();
        this._srcMacIn = arg.getSrcMacIn();
        this._dstMacIn = arg.getDstMacIn();
        this._srcMacOut = arg.getSrcMacOut();
        this._dstMacOut = arg.getDstMacOut();
        this._ipVersion = arg.getIpVersion();
        this._direction = arg.getDirection();
        this._ifName = arg.getIfName();
        this._ifDesc = arg.getIfDesc();
        this._fragOffset = arg.getFragOffset();
        this._forwardingStatus = arg.getForwardingStatus();
        this._postipDscp = arg.getPostipDscp();
        this._replFactorMul = arg.getReplFactorMul();
        this._ipv4 = arg.getIpv4();
        this._ipv6 = arg.getIpv6();
        this._mpls = arg.getMpls();
        this._bgp = arg.getBgp();
        this._vlan = arg.getVlan();
        this._permanentFlow = arg.getPermanentFlow();
        this._application = arg.getApplication();
        this._layer2PktSection = arg.getLayer2PktSection();
    }
    

    public FlowDataRecordBuilder(FlowDataRecord base) {
        Map<Class<? extends Augmentation<FlowDataRecord>>, Augmentation<FlowDataRecord>> aug = base.augmentations();
        if (!aug.isEmpty()) {
            this.augmentation = new HashMap<>(aug);
        }
        this.key = base.key();
        this._flowId = base.getFlowId();
        this._application = base.getApplication();
        this._bgp = base.getBgp();
        this._bytesIn = base.getBytesIn();
        this._bytesOut = base.getBytesOut();
        this._bytesOutMul = base.getBytesOutMul();
        this._direction = base.getDirection();
        this._dstMacIn = base.getDstMacIn();
        this._dstMacOut = base.getDstMacOut();
        this._dstPort = base.getDstPort();
        this._dstTos = base.getDstTos();
        this._engineId = base.getEngineId();
        this._engineType = base.getEngineType();
        this._firstSwitched = base.getFirstSwitched();
        this._flowActiveTout = base.getFlowActiveTout();
        this._flowInactiveTout = base.getFlowInactiveTout();
        this._flowSamplerId = base.getFlowSamplerId();
        this._flowSamplerMode = base.getFlowSamplerMode();
        this._flowSamplerRandom = base.getFlowSamplerRandom();
        this._flows = base.getFlows();
        this._forwardingStatus = base.getForwardingStatus();
        this._fragOffset = base.getFragOffset();
        this._icmpType = base.getIcmpType();
        this._ifDesc = base.getIfDesc();
        this._ifName = base.getIfName();
        this._igmpType = base.getIgmpType();
        this._ipVersion = base.getIpVersion();
        this._ipv4 = base.getIpv4();
        this._ipv6 = base.getIpv6();
        this._lastSwitched = base.getLastSwitched();
        this._layer2PktSection = base.getLayer2PktSection();
        this._maxPktLen = base.getMaxPktLen();
        this._maxTtl = base.getMaxTtl();
        this._minPktLen = base.getMinPktLen();
        this._minTtl = base.getMinTtl();
        this._mpls = base.getMpls();
        this._permanentFlow = base.getPermanentFlow();
        this._pktsIn = base.getPktsIn();
        this._pktsOut = base.getPktsOut();
        this._pktsOutMul = base.getPktsOutMul();
        this._postipDscp = base.getPostipDscp();
        this._protocol = base.getProtocol();
        this._replFactorMul = base.getReplFactorMul();
        this._samplerName = base.getSamplerName();
        this._samplingAlgorithm = base.getSamplingAlgorithm();
        this._samplingInterval = base.getSamplingInterval();
        this._snmpIn = base.getSnmpIn();
        this._snmpOut = base.getSnmpOut();
        this._srcMacIn = base.getSrcMacIn();
        this._srcMacOut = base.getSrcMacOut();
        this._srcPort = base.getSrcPort();
        this._srcTos = base.getSrcTos();
        this._tcpFlags = base.getTcpFlags();
        this._totBytesExp = base.getTotBytesExp();
        this._totFlowsExp = base.getTotFlowsExp();
        this._totPktsExp = base.getTotPktsExp();
        this._vlan = base.getVlan();
    }

    /**
     * Set fields from given grouping argument. Valid argument is instance of one of following types:
     * <ul>
     * <li>org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFlowFields</li>
     * </ul>
     *
     * @param arg grouping object
     * @throws IllegalArgumentException if given argument is none of valid types or has property with incompatible value
    */
    public void fieldsFrom(DataObject arg) {
        boolean isValidArg = false;
        if (arg instanceof org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFlowFields) {
            this._bytesIn = ((org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFlowFields)arg).getBytesIn();
            this._bytesOut = ((org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFlowFields)arg).getBytesOut();
            this._pktsIn = ((org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFlowFields)arg).getPktsIn();
            this._pktsOut = ((org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFlowFields)arg).getPktsOut();
            this._flows = ((org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFlowFields)arg).getFlows();
            this._protocol = ((org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFlowFields)arg).getProtocol();
            this._srcTos = ((org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFlowFields)arg).getSrcTos();
            this._dstTos = ((org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFlowFields)arg).getDstTos();
            this._tcpFlags = ((org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFlowFields)arg).getTcpFlags();
            this._srcPort = ((org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFlowFields)arg).getSrcPort();
            this._dstPort = ((org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFlowFields)arg).getDstPort();
            this._snmpIn = ((org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFlowFields)arg).getSnmpIn();
            this._snmpOut = ((org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFlowFields)arg).getSnmpOut();
            this._bytesOutMul = ((org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFlowFields)arg).getBytesOutMul();
            this._pktsOutMul = ((org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFlowFields)arg).getPktsOutMul();
            this._firstSwitched = ((org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFlowFields)arg).getFirstSwitched();
            this._lastSwitched = ((org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFlowFields)arg).getLastSwitched();
            this._minPktLen = ((org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFlowFields)arg).getMinPktLen();
            this._maxPktLen = ((org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFlowFields)arg).getMaxPktLen();
            this._icmpType = ((org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFlowFields)arg).getIcmpType();
            this._igmpType = ((org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFlowFields)arg).getIgmpType();
            this._samplerName = ((org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFlowFields)arg).getSamplerName();
            this._samplingInterval = ((org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFlowFields)arg).getSamplingInterval();
            this._samplingAlgorithm = ((org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFlowFields)arg).getSamplingAlgorithm();
            this._flowActiveTout = ((org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFlowFields)arg).getFlowActiveTout();
            this._flowInactiveTout = ((org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFlowFields)arg).getFlowInactiveTout();
            this._engineType = ((org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFlowFields)arg).getEngineType();
            this._engineId = ((org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFlowFields)arg).getEngineId();
            this._totBytesExp = ((org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFlowFields)arg).getTotBytesExp();
            this._totPktsExp = ((org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFlowFields)arg).getTotPktsExp();
            this._totFlowsExp = ((org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFlowFields)arg).getTotFlowsExp();
            this._flowSamplerId = ((org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFlowFields)arg).getFlowSamplerId();
            this._flowSamplerMode = ((org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFlowFields)arg).getFlowSamplerMode();
            this._flowSamplerRandom = ((org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFlowFields)arg).getFlowSamplerRandom();
            this._minTtl = ((org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFlowFields)arg).getMinTtl();
            this._maxTtl = ((org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFlowFields)arg).getMaxTtl();
            this._srcMacIn = ((org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFlowFields)arg).getSrcMacIn();
            this._dstMacIn = ((org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFlowFields)arg).getDstMacIn();
            this._srcMacOut = ((org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFlowFields)arg).getSrcMacOut();
            this._dstMacOut = ((org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFlowFields)arg).getDstMacOut();
            this._ipVersion = ((org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFlowFields)arg).getIpVersion();
            this._direction = ((org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFlowFields)arg).getDirection();
            this._ifName = ((org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFlowFields)arg).getIfName();
            this._ifDesc = ((org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFlowFields)arg).getIfDesc();
            this._fragOffset = ((org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFlowFields)arg).getFragOffset();
            this._forwardingStatus = ((org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFlowFields)arg).getForwardingStatus();
            this._postipDscp = ((org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFlowFields)arg).getPostipDscp();
            this._replFactorMul = ((org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFlowFields)arg).getReplFactorMul();
            this._ipv4 = ((org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFlowFields)arg).getIpv4();
            this._ipv6 = ((org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFlowFields)arg).getIpv6();
            this._mpls = ((org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFlowFields)arg).getMpls();
            this._bgp = ((org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFlowFields)arg).getBgp();
            this._vlan = ((org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFlowFields)arg).getVlan();
            this._permanentFlow = ((org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFlowFields)arg).getPermanentFlow();
            this._application = ((org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFlowFields)arg).getApplication();
            this._layer2PktSection = ((org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFlowFields)arg).getLayer2PktSection();
            isValidArg = true;
        }
        CodeHelpers.validValue(isValidArg, arg, "[org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFlowFields]");
    }

    public FlowDataRecordKey key() {
        return key;
    }
    
    public Application getApplication() {
        return _application;
    }
    
    public Bgp getBgp() {
        return _bgp;
    }
    
    public Counter64 getBytesIn() {
        return _bytesIn;
    }
    
    public Counter64 getBytesOut() {
        return _bytesOut;
    }
    
    public Counter64 getBytesOutMul() {
        return _bytesOutMul;
    }
    
    public DirectionType getDirection() {
        return _direction;
    }
    
    public MacAddress getDstMacIn() {
        return _dstMacIn;
    }
    
    public MacAddress getDstMacOut() {
        return _dstMacOut;
    }
    
    public PortNumber getDstPort() {
        return _dstPort;
    }
    
    public Uint8 getDstTos() {
        return _dstTos;
    }
    
    public Uint8 getEngineId() {
        return _engineId;
    }
    
    public EngineType getEngineType() {
        return _engineType;
    }
    
    public Uint64 getFirstSwitched() {
        return _firstSwitched;
    }
    
    public Uint16 getFlowActiveTout() {
        return _flowActiveTout;
    }
    
    public Integer getFlowId() {
        return _flowId;
    }
    
    public Uint16 getFlowInactiveTout() {
        return _flowInactiveTout;
    }
    
    public Uint8 getFlowSamplerId() {
        return _flowSamplerId;
    }
    
    public SamplingModeType getFlowSamplerMode() {
        return _flowSamplerMode;
    }
    
    public Uint32 getFlowSamplerRandom() {
        return _flowSamplerRandom;
    }
    
    public Counter64 getFlows() {
        return _flows;
    }
    
    public ForwardingStatusType getForwardingStatus() {
        return _forwardingStatus;
    }
    
    public Uint16 getFragOffset() {
        return _fragOffset;
    }
    
    public Uint16 getIcmpType() {
        return _icmpType;
    }
    
    public String getIfDesc() {
        return _ifDesc;
    }
    
    public String getIfName() {
        return _ifName;
    }
    
    public IgmpType getIgmpType() {
        return _igmpType;
    }
    
    public IpVersionType getIpVersion() {
        return _ipVersion;
    }
    
    public Ipv4 getIpv4() {
        return _ipv4;
    }
    
    public Ipv6 getIpv6() {
        return _ipv6;
    }
    
    public Uint64 getLastSwitched() {
        return _lastSwitched;
    }
    
    public Layer2PktSection getLayer2PktSection() {
        return _layer2PktSection;
    }
    
    public Uint16 getMaxPktLen() {
        return _maxPktLen;
    }
    
    public Uint8 getMaxTtl() {
        return _maxTtl;
    }
    
    public Uint16 getMinPktLen() {
        return _minPktLen;
    }
    
    public Uint8 getMinTtl() {
        return _minTtl;
    }
    
    public Mpls getMpls() {
        return _mpls;
    }
    
    public PermanentFlow getPermanentFlow() {
        return _permanentFlow;
    }
    
    public Counter64 getPktsIn() {
        return _pktsIn;
    }
    
    public Counter64 getPktsOut() {
        return _pktsOut;
    }
    
    public Counter64 getPktsOutMul() {
        return _pktsOutMul;
    }
    
    public Dscp getPostipDscp() {
        return _postipDscp;
    }
    
    public ProtocolType getProtocol() {
        return _protocol;
    }
    
    public Uint32 getReplFactorMul() {
        return _replFactorMul;
    }
    
    public String getSamplerName() {
        return _samplerName;
    }
    
    public SamplingModeType getSamplingAlgorithm() {
        return _samplingAlgorithm;
    }
    
    public Uint32 getSamplingInterval() {
        return _samplingInterval;
    }
    
    public Integer getSnmpIn() {
        return _snmpIn;
    }
    
    public Integer getSnmpOut() {
        return _snmpOut;
    }
    
    public MacAddress getSrcMacIn() {
        return _srcMacIn;
    }
    
    public MacAddress getSrcMacOut() {
        return _srcMacOut;
    }
    
    public PortNumber getSrcPort() {
        return _srcPort;
    }
    
    public Uint8 getSrcTos() {
        return _srcTos;
    }
    
    public TcpFlagsType getTcpFlags() {
        return _tcpFlags;
    }
    
    public Counter64 getTotBytesExp() {
        return _totBytesExp;
    }
    
    public Counter64 getTotFlowsExp() {
        return _totFlowsExp;
    }
    
    public Counter64 getTotPktsExp() {
        return _totPktsExp;
    }
    
    public Vlan getVlan() {
        return _vlan;
    }

    @SuppressWarnings({ "unchecked", "checkstyle:methodTypeParameterName"})
    public <E$$ extends Augmentation<FlowDataRecord>> E$$ augmentation(Class<E$$> augmentationType) {
        return (E$$) augmentation.get(Objects.requireNonNull(augmentationType));
    }

    public FlowDataRecordBuilder withKey(final FlowDataRecordKey key) {
        this.key = key;
        return this;
    }
    
    public FlowDataRecordBuilder setApplication(final Application value) {
        this._application = value;
        return this;
    }
    
    public FlowDataRecordBuilder setBgp(final Bgp value) {
        this._bgp = value;
        return this;
    }
    
    public FlowDataRecordBuilder setBytesIn(final Counter64 value) {
        this._bytesIn = value;
        return this;
    }
    
    public FlowDataRecordBuilder setBytesOut(final Counter64 value) {
        this._bytesOut = value;
        return this;
    }
    
    public FlowDataRecordBuilder setBytesOutMul(final Counter64 value) {
        this._bytesOutMul = value;
        return this;
    }
    
    public FlowDataRecordBuilder setDirection(final DirectionType value) {
        this._direction = value;
        return this;
    }
    
    public FlowDataRecordBuilder setDstMacIn(final MacAddress value) {
        this._dstMacIn = value;
        return this;
    }
    
    public FlowDataRecordBuilder setDstMacOut(final MacAddress value) {
        this._dstMacOut = value;
        return this;
    }
    
    public FlowDataRecordBuilder setDstPort(final PortNumber value) {
        this._dstPort = value;
        return this;
    }
    
    public FlowDataRecordBuilder setDstTos(final Uint8 value) {
        this._dstTos = value;
        return this;
    }
    
    /**
     * Utility migration setter.
     *
     * @param value field value in legacy type
     * @return this builder
     * @deprecated Use {@link #setDstTos(Uint8)} instead.
     */
    @Deprecated(forRemoval = true)
    public FlowDataRecordBuilder setDstTos(final Short value) {
        return setDstTos(CodeHelpers.compatUint(value));
    }
    
    public FlowDataRecordBuilder setEngineId(final Uint8 value) {
        this._engineId = value;
        return this;
    }
    
    /**
     * Utility migration setter.
     *
     * @param value field value in legacy type
     * @return this builder
     * @deprecated Use {@link #setEngineId(Uint8)} instead.
     */
    @Deprecated(forRemoval = true)
    public FlowDataRecordBuilder setEngineId(final Short value) {
        return setEngineId(CodeHelpers.compatUint(value));
    }
    
    public FlowDataRecordBuilder setEngineType(final EngineType value) {
        this._engineType = value;
        return this;
    }
    
    public FlowDataRecordBuilder setFirstSwitched(final Uint64 value) {
        this._firstSwitched = value;
        return this;
    }
    
    /**
     * Utility migration setter.
     *
     * @param value field value in legacy type
     * @return this builder
     * @deprecated Use {@link #setFirstSwitched(Uint64)} instead.
     */
    @Deprecated(forRemoval = true)
    public FlowDataRecordBuilder setFirstSwitched(final BigInteger value) {
        return setFirstSwitched(CodeHelpers.compatUint(value));
    }
    
    public FlowDataRecordBuilder setFlowActiveTout(final Uint16 value) {
        this._flowActiveTout = value;
        return this;
    }
    
    /**
     * Utility migration setter.
     *
     * @param value field value in legacy type
     * @return this builder
     * @deprecated Use {@link #setFlowActiveTout(Uint16)} instead.
     */
    @Deprecated(forRemoval = true)
    public FlowDataRecordBuilder setFlowActiveTout(final Integer value) {
        return setFlowActiveTout(CodeHelpers.compatUint(value));
    }
    
    public FlowDataRecordBuilder setFlowId(final Integer value) {
        this._flowId = value;
        return this;
    }
    
    public FlowDataRecordBuilder setFlowInactiveTout(final Uint16 value) {
        this._flowInactiveTout = value;
        return this;
    }
    
    /**
     * Utility migration setter.
     *
     * @param value field value in legacy type
     * @return this builder
     * @deprecated Use {@link #setFlowInactiveTout(Uint16)} instead.
     */
    @Deprecated(forRemoval = true)
    public FlowDataRecordBuilder setFlowInactiveTout(final Integer value) {
        return setFlowInactiveTout(CodeHelpers.compatUint(value));
    }
    
    public FlowDataRecordBuilder setFlowSamplerId(final Uint8 value) {
        this._flowSamplerId = value;
        return this;
    }
    
    /**
     * Utility migration setter.
     *
     * @param value field value in legacy type
     * @return this builder
     * @deprecated Use {@link #setFlowSamplerId(Uint8)} instead.
     */
    @Deprecated(forRemoval = true)
    public FlowDataRecordBuilder setFlowSamplerId(final Short value) {
        return setFlowSamplerId(CodeHelpers.compatUint(value));
    }
    
    public FlowDataRecordBuilder setFlowSamplerMode(final SamplingModeType value) {
        this._flowSamplerMode = value;
        return this;
    }
    
    public FlowDataRecordBuilder setFlowSamplerRandom(final Uint32 value) {
        this._flowSamplerRandom = value;
        return this;
    }
    
    /**
     * Utility migration setter.
     *
     * @param value field value in legacy type
     * @return this builder
     * @deprecated Use {@link #setFlowSamplerRandom(Uint32)} instead.
     */
    @Deprecated(forRemoval = true)
    public FlowDataRecordBuilder setFlowSamplerRandom(final Long value) {
        return setFlowSamplerRandom(CodeHelpers.compatUint(value));
    }
    
    public FlowDataRecordBuilder setFlows(final Counter64 value) {
        this._flows = value;
        return this;
    }
    
    public FlowDataRecordBuilder setForwardingStatus(final ForwardingStatusType value) {
        this._forwardingStatus = value;
        return this;
    }
    
    public FlowDataRecordBuilder setFragOffset(final Uint16 value) {
        this._fragOffset = value;
        return this;
    }
    
    /**
     * Utility migration setter.
     *
     * @param value field value in legacy type
     * @return this builder
     * @deprecated Use {@link #setFragOffset(Uint16)} instead.
     */
    @Deprecated(forRemoval = true)
    public FlowDataRecordBuilder setFragOffset(final Integer value) {
        return setFragOffset(CodeHelpers.compatUint(value));
    }
    
    public FlowDataRecordBuilder setIcmpType(final Uint16 value) {
        this._icmpType = value;
        return this;
    }
    
    /**
     * Utility migration setter.
     *
     * @param value field value in legacy type
     * @return this builder
     * @deprecated Use {@link #setIcmpType(Uint16)} instead.
     */
    @Deprecated(forRemoval = true)
    public FlowDataRecordBuilder setIcmpType(final Integer value) {
        return setIcmpType(CodeHelpers.compatUint(value));
    }
    
    public FlowDataRecordBuilder setIfDesc(final String value) {
        this._ifDesc = value;
        return this;
    }
    
    public FlowDataRecordBuilder setIfName(final String value) {
        this._ifName = value;
        return this;
    }
    
    public FlowDataRecordBuilder setIgmpType(final IgmpType value) {
        this._igmpType = value;
        return this;
    }
    
    public FlowDataRecordBuilder setIpVersion(final IpVersionType value) {
        this._ipVersion = value;
        return this;
    }
    
    public FlowDataRecordBuilder setIpv4(final Ipv4 value) {
        this._ipv4 = value;
        return this;
    }
    
    public FlowDataRecordBuilder setIpv6(final Ipv6 value) {
        this._ipv6 = value;
        return this;
    }
    
    public FlowDataRecordBuilder setLastSwitched(final Uint64 value) {
        this._lastSwitched = value;
        return this;
    }
    
    /**
     * Utility migration setter.
     *
     * @param value field value in legacy type
     * @return this builder
     * @deprecated Use {@link #setLastSwitched(Uint64)} instead.
     */
    @Deprecated(forRemoval = true)
    public FlowDataRecordBuilder setLastSwitched(final BigInteger value) {
        return setLastSwitched(CodeHelpers.compatUint(value));
    }
    
    public FlowDataRecordBuilder setLayer2PktSection(final Layer2PktSection value) {
        this._layer2PktSection = value;
        return this;
    }
    
    public FlowDataRecordBuilder setMaxPktLen(final Uint16 value) {
        this._maxPktLen = value;
        return this;
    }
    
    /**
     * Utility migration setter.
     *
     * @param value field value in legacy type
     * @return this builder
     * @deprecated Use {@link #setMaxPktLen(Uint16)} instead.
     */
    @Deprecated(forRemoval = true)
    public FlowDataRecordBuilder setMaxPktLen(final Integer value) {
        return setMaxPktLen(CodeHelpers.compatUint(value));
    }
    
    public FlowDataRecordBuilder setMaxTtl(final Uint8 value) {
        this._maxTtl = value;
        return this;
    }
    
    /**
     * Utility migration setter.
     *
     * @param value field value in legacy type
     * @return this builder
     * @deprecated Use {@link #setMaxTtl(Uint8)} instead.
     */
    @Deprecated(forRemoval = true)
    public FlowDataRecordBuilder setMaxTtl(final Short value) {
        return setMaxTtl(CodeHelpers.compatUint(value));
    }
    
    public FlowDataRecordBuilder setMinPktLen(final Uint16 value) {
        this._minPktLen = value;
        return this;
    }
    
    /**
     * Utility migration setter.
     *
     * @param value field value in legacy type
     * @return this builder
     * @deprecated Use {@link #setMinPktLen(Uint16)} instead.
     */
    @Deprecated(forRemoval = true)
    public FlowDataRecordBuilder setMinPktLen(final Integer value) {
        return setMinPktLen(CodeHelpers.compatUint(value));
    }
    
    public FlowDataRecordBuilder setMinTtl(final Uint8 value) {
        this._minTtl = value;
        return this;
    }
    
    /**
     * Utility migration setter.
     *
     * @param value field value in legacy type
     * @return this builder
     * @deprecated Use {@link #setMinTtl(Uint8)} instead.
     */
    @Deprecated(forRemoval = true)
    public FlowDataRecordBuilder setMinTtl(final Short value) {
        return setMinTtl(CodeHelpers.compatUint(value));
    }
    
    public FlowDataRecordBuilder setMpls(final Mpls value) {
        this._mpls = value;
        return this;
    }
    
    public FlowDataRecordBuilder setPermanentFlow(final PermanentFlow value) {
        this._permanentFlow = value;
        return this;
    }
    
    public FlowDataRecordBuilder setPktsIn(final Counter64 value) {
        this._pktsIn = value;
        return this;
    }
    
    public FlowDataRecordBuilder setPktsOut(final Counter64 value) {
        this._pktsOut = value;
        return this;
    }
    
    public FlowDataRecordBuilder setPktsOutMul(final Counter64 value) {
        this._pktsOutMul = value;
        return this;
    }
    
    public FlowDataRecordBuilder setPostipDscp(final Dscp value) {
        this._postipDscp = value;
        return this;
    }
    
    public FlowDataRecordBuilder setProtocol(final ProtocolType value) {
        this._protocol = value;
        return this;
    }
    
    public FlowDataRecordBuilder setReplFactorMul(final Uint32 value) {
        this._replFactorMul = value;
        return this;
    }
    
    /**
     * Utility migration setter.
     *
     * @param value field value in legacy type
     * @return this builder
     * @deprecated Use {@link #setReplFactorMul(Uint32)} instead.
     */
    @Deprecated(forRemoval = true)
    public FlowDataRecordBuilder setReplFactorMul(final Long value) {
        return setReplFactorMul(CodeHelpers.compatUint(value));
    }
    
    public FlowDataRecordBuilder setSamplerName(final String value) {
        this._samplerName = value;
        return this;
    }
    
    public FlowDataRecordBuilder setSamplingAlgorithm(final SamplingModeType value) {
        this._samplingAlgorithm = value;
        return this;
    }
    
    public FlowDataRecordBuilder setSamplingInterval(final Uint32 value) {
        this._samplingInterval = value;
        return this;
    }
    
    /**
     * Utility migration setter.
     *
     * @param value field value in legacy type
     * @return this builder
     * @deprecated Use {@link #setSamplingInterval(Uint32)} instead.
     */
    @Deprecated(forRemoval = true)
    public FlowDataRecordBuilder setSamplingInterval(final Long value) {
        return setSamplingInterval(CodeHelpers.compatUint(value));
    }
    
    private static void checkSnmpInRange(final int value) {
        if (value >= 0) {
            return;
        }
        CodeHelpers.throwInvalidRange("[[0..2147483647]]", value);
    }
    
    public FlowDataRecordBuilder setSnmpIn(final Integer value) {
        if (value != null) {
            checkSnmpInRange(value);
            
        }
        this._snmpIn = value;
        return this;
    }
    
    private static void checkSnmpOutRange(final int value) {
        if (value >= 0) {
            return;
        }
        CodeHelpers.throwInvalidRange("[[0..2147483647]]", value);
    }
    
    public FlowDataRecordBuilder setSnmpOut(final Integer value) {
        if (value != null) {
            checkSnmpOutRange(value);
            
        }
        this._snmpOut = value;
        return this;
    }
    
    public FlowDataRecordBuilder setSrcMacIn(final MacAddress value) {
        this._srcMacIn = value;
        return this;
    }
    
    public FlowDataRecordBuilder setSrcMacOut(final MacAddress value) {
        this._srcMacOut = value;
        return this;
    }
    
    public FlowDataRecordBuilder setSrcPort(final PortNumber value) {
        this._srcPort = value;
        return this;
    }
    
    public FlowDataRecordBuilder setSrcTos(final Uint8 value) {
        this._srcTos = value;
        return this;
    }
    
    /**
     * Utility migration setter.
     *
     * @param value field value in legacy type
     * @return this builder
     * @deprecated Use {@link #setSrcTos(Uint8)} instead.
     */
    @Deprecated(forRemoval = true)
    public FlowDataRecordBuilder setSrcTos(final Short value) {
        return setSrcTos(CodeHelpers.compatUint(value));
    }
    
    public FlowDataRecordBuilder setTcpFlags(final TcpFlagsType value) {
        this._tcpFlags = value;
        return this;
    }
    
    public FlowDataRecordBuilder setTotBytesExp(final Counter64 value) {
        this._totBytesExp = value;
        return this;
    }
    
    public FlowDataRecordBuilder setTotFlowsExp(final Counter64 value) {
        this._totFlowsExp = value;
        return this;
    }
    
    public FlowDataRecordBuilder setTotPktsExp(final Counter64 value) {
        this._totPktsExp = value;
        return this;
    }
    
    public FlowDataRecordBuilder setVlan(final Vlan value) {
        this._vlan = value;
        return this;
    }
    
    /**
      * Add an augmentation to this builder's product.
      *
      * @param augmentation augmentation to be added
      * @return this builder
      * @throws NullPointerException if {@code augmentation} is null
      */
    public FlowDataRecordBuilder addAugmentation(Augmentation<FlowDataRecord> augmentation) {
        Class<? extends Augmentation<FlowDataRecord>> augmentationType = augmentation.implementedInterface();
        if (!(this.augmentation instanceof HashMap)) {
            this.augmentation = new HashMap<>();
        }
    
        this.augmentation.put(augmentationType, augmentation);
        return this;
    }
    
    /**
      * Remove an augmentation from this builder's product. If this builder does not track such an augmentation
      * type, this method does nothing.
      *
      * @param augmentationType augmentation type to be removed
      * @return this builder
      */
    public FlowDataRecordBuilder removeAugmentation(Class<? extends Augmentation<FlowDataRecord>> augmentationType) {
        if (this.augmentation instanceof HashMap) {
            this.augmentation.remove(augmentationType);
        }
        return this;
    }

    @Override
    public FlowDataRecord build() {
        return new FlowDataRecordImpl(this);
    }

    private static final class FlowDataRecordImpl
        extends AbstractAugmentable<FlowDataRecord>
        implements FlowDataRecord {
    
        private final Application _application;
        private final Bgp _bgp;
        private final Counter64 _bytesIn;
        private final Counter64 _bytesOut;
        private final Counter64 _bytesOutMul;
        private final DirectionType _direction;
        private final MacAddress _dstMacIn;
        private final MacAddress _dstMacOut;
        private final PortNumber _dstPort;
        private final Uint8 _dstTos;
        private final Uint8 _engineId;
        private final EngineType _engineType;
        private final Uint64 _firstSwitched;
        private final Uint16 _flowActiveTout;
        private final Integer _flowId;
        private final Uint16 _flowInactiveTout;
        private final Uint8 _flowSamplerId;
        private final SamplingModeType _flowSamplerMode;
        private final Uint32 _flowSamplerRandom;
        private final Counter64 _flows;
        private final ForwardingStatusType _forwardingStatus;
        private final Uint16 _fragOffset;
        private final Uint16 _icmpType;
        private final String _ifDesc;
        private final String _ifName;
        private final IgmpType _igmpType;
        private final IpVersionType _ipVersion;
        private final Ipv4 _ipv4;
        private final Ipv6 _ipv6;
        private final Uint64 _lastSwitched;
        private final Layer2PktSection _layer2PktSection;
        private final Uint16 _maxPktLen;
        private final Uint8 _maxTtl;
        private final Uint16 _minPktLen;
        private final Uint8 _minTtl;
        private final Mpls _mpls;
        private final PermanentFlow _permanentFlow;
        private final Counter64 _pktsIn;
        private final Counter64 _pktsOut;
        private final Counter64 _pktsOutMul;
        private final Dscp _postipDscp;
        private final ProtocolType _protocol;
        private final Uint32 _replFactorMul;
        private final String _samplerName;
        private final SamplingModeType _samplingAlgorithm;
        private final Uint32 _samplingInterval;
        private final Integer _snmpIn;
        private final Integer _snmpOut;
        private final MacAddress _srcMacIn;
        private final MacAddress _srcMacOut;
        private final PortNumber _srcPort;
        private final Uint8 _srcTos;
        private final TcpFlagsType _tcpFlags;
        private final Counter64 _totBytesExp;
        private final Counter64 _totFlowsExp;
        private final Counter64 _totPktsExp;
        private final Vlan _vlan;
        private final FlowDataRecordKey key;
    
        FlowDataRecordImpl(FlowDataRecordBuilder base) {
            super(base.augmentation);
            if (base.key() != null) {
                this.key = base.key();
            } else {
                this.key = new FlowDataRecordKey(base.getFlowId());
            }
            this._flowId = key.getFlowId();
            this._application = base.getApplication();
            this._bgp = base.getBgp();
            this._bytesIn = base.getBytesIn();
            this._bytesOut = base.getBytesOut();
            this._bytesOutMul = base.getBytesOutMul();
            this._direction = base.getDirection();
            this._dstMacIn = base.getDstMacIn();
            this._dstMacOut = base.getDstMacOut();
            this._dstPort = base.getDstPort();
            this._dstTos = base.getDstTos();
            this._engineId = base.getEngineId();
            this._engineType = base.getEngineType();
            this._firstSwitched = base.getFirstSwitched();
            this._flowActiveTout = base.getFlowActiveTout();
            this._flowInactiveTout = base.getFlowInactiveTout();
            this._flowSamplerId = base.getFlowSamplerId();
            this._flowSamplerMode = base.getFlowSamplerMode();
            this._flowSamplerRandom = base.getFlowSamplerRandom();
            this._flows = base.getFlows();
            this._forwardingStatus = base.getForwardingStatus();
            this._fragOffset = base.getFragOffset();
            this._icmpType = base.getIcmpType();
            this._ifDesc = base.getIfDesc();
            this._ifName = base.getIfName();
            this._igmpType = base.getIgmpType();
            this._ipVersion = base.getIpVersion();
            this._ipv4 = base.getIpv4();
            this._ipv6 = base.getIpv6();
            this._lastSwitched = base.getLastSwitched();
            this._layer2PktSection = base.getLayer2PktSection();
            this._maxPktLen = base.getMaxPktLen();
            this._maxTtl = base.getMaxTtl();
            this._minPktLen = base.getMinPktLen();
            this._minTtl = base.getMinTtl();
            this._mpls = base.getMpls();
            this._permanentFlow = base.getPermanentFlow();
            this._pktsIn = base.getPktsIn();
            this._pktsOut = base.getPktsOut();
            this._pktsOutMul = base.getPktsOutMul();
            this._postipDscp = base.getPostipDscp();
            this._protocol = base.getProtocol();
            this._replFactorMul = base.getReplFactorMul();
            this._samplerName = base.getSamplerName();
            this._samplingAlgorithm = base.getSamplingAlgorithm();
            this._samplingInterval = base.getSamplingInterval();
            this._snmpIn = base.getSnmpIn();
            this._snmpOut = base.getSnmpOut();
            this._srcMacIn = base.getSrcMacIn();
            this._srcMacOut = base.getSrcMacOut();
            this._srcPort = base.getSrcPort();
            this._srcTos = base.getSrcTos();
            this._tcpFlags = base.getTcpFlags();
            this._totBytesExp = base.getTotBytesExp();
            this._totFlowsExp = base.getTotFlowsExp();
            this._totPktsExp = base.getTotPktsExp();
            this._vlan = base.getVlan();
        }
    
        @Override
        public FlowDataRecordKey key() {
            return key;
        }
        
        @Override
        public Application getApplication() {
            return _application;
        }
        
        @Override
        public Bgp getBgp() {
            return _bgp;
        }
        
        @Override
        public Counter64 getBytesIn() {
            return _bytesIn;
        }
        
        @Override
        public Counter64 getBytesOut() {
            return _bytesOut;
        }
        
        @Override
        public Counter64 getBytesOutMul() {
            return _bytesOutMul;
        }
        
        @Override
        public DirectionType getDirection() {
            return _direction;
        }
        
        @Override
        public MacAddress getDstMacIn() {
            return _dstMacIn;
        }
        
        @Override
        public MacAddress getDstMacOut() {
            return _dstMacOut;
        }
        
        @Override
        public PortNumber getDstPort() {
            return _dstPort;
        }
        
        @Override
        public Uint8 getDstTos() {
            return _dstTos;
        }
        
        @Override
        public Uint8 getEngineId() {
            return _engineId;
        }
        
        @Override
        public EngineType getEngineType() {
            return _engineType;
        }
        
        @Override
        public Uint64 getFirstSwitched() {
            return _firstSwitched;
        }
        
        @Override
        public Uint16 getFlowActiveTout() {
            return _flowActiveTout;
        }
        
        @Override
        public Integer getFlowId() {
            return _flowId;
        }
        
        @Override
        public Uint16 getFlowInactiveTout() {
            return _flowInactiveTout;
        }
        
        @Override
        public Uint8 getFlowSamplerId() {
            return _flowSamplerId;
        }
        
        @Override
        public SamplingModeType getFlowSamplerMode() {
            return _flowSamplerMode;
        }
        
        @Override
        public Uint32 getFlowSamplerRandom() {
            return _flowSamplerRandom;
        }
        
        @Override
        public Counter64 getFlows() {
            return _flows;
        }
        
        @Override
        public ForwardingStatusType getForwardingStatus() {
            return _forwardingStatus;
        }
        
        @Override
        public Uint16 getFragOffset() {
            return _fragOffset;
        }
        
        @Override
        public Uint16 getIcmpType() {
            return _icmpType;
        }
        
        @Override
        public String getIfDesc() {
            return _ifDesc;
        }
        
        @Override
        public String getIfName() {
            return _ifName;
        }
        
        @Override
        public IgmpType getIgmpType() {
            return _igmpType;
        }
        
        @Override
        public IpVersionType getIpVersion() {
            return _ipVersion;
        }
        
        @Override
        public Ipv4 getIpv4() {
            return _ipv4;
        }
        
        @Override
        public Ipv6 getIpv6() {
            return _ipv6;
        }
        
        @Override
        public Uint64 getLastSwitched() {
            return _lastSwitched;
        }
        
        @Override
        public Layer2PktSection getLayer2PktSection() {
            return _layer2PktSection;
        }
        
        @Override
        public Uint16 getMaxPktLen() {
            return _maxPktLen;
        }
        
        @Override
        public Uint8 getMaxTtl() {
            return _maxTtl;
        }
        
        @Override
        public Uint16 getMinPktLen() {
            return _minPktLen;
        }
        
        @Override
        public Uint8 getMinTtl() {
            return _minTtl;
        }
        
        @Override
        public Mpls getMpls() {
            return _mpls;
        }
        
        @Override
        public PermanentFlow getPermanentFlow() {
            return _permanentFlow;
        }
        
        @Override
        public Counter64 getPktsIn() {
            return _pktsIn;
        }
        
        @Override
        public Counter64 getPktsOut() {
            return _pktsOut;
        }
        
        @Override
        public Counter64 getPktsOutMul() {
            return _pktsOutMul;
        }
        
        @Override
        public Dscp getPostipDscp() {
            return _postipDscp;
        }
        
        @Override
        public ProtocolType getProtocol() {
            return _protocol;
        }
        
        @Override
        public Uint32 getReplFactorMul() {
            return _replFactorMul;
        }
        
        @Override
        public String getSamplerName() {
            return _samplerName;
        }
        
        @Override
        public SamplingModeType getSamplingAlgorithm() {
            return _samplingAlgorithm;
        }
        
        @Override
        public Uint32 getSamplingInterval() {
            return _samplingInterval;
        }
        
        @Override
        public Integer getSnmpIn() {
            return _snmpIn;
        }
        
        @Override
        public Integer getSnmpOut() {
            return _snmpOut;
        }
        
        @Override
        public MacAddress getSrcMacIn() {
            return _srcMacIn;
        }
        
        @Override
        public MacAddress getSrcMacOut() {
            return _srcMacOut;
        }
        
        @Override
        public PortNumber getSrcPort() {
            return _srcPort;
        }
        
        @Override
        public Uint8 getSrcTos() {
            return _srcTos;
        }
        
        @Override
        public TcpFlagsType getTcpFlags() {
            return _tcpFlags;
        }
        
        @Override
        public Counter64 getTotBytesExp() {
            return _totBytesExp;
        }
        
        @Override
        public Counter64 getTotFlowsExp() {
            return _totFlowsExp;
        }
        
        @Override
        public Counter64 getTotPktsExp() {
            return _totPktsExp;
        }
        
        @Override
        public Vlan getVlan() {
            return _vlan;
        }
    
        private int hash = 0;
        private volatile boolean hashValid = false;
        
        @Override
        public int hashCode() {
            if (hashValid) {
                return hash;
            }
        
            final int result = FlowDataRecord.bindingHashCode(this);
            hash = result;
            hashValid = true;
            return result;
        }
    
        @Override
        public boolean equals(Object obj) {
            return FlowDataRecord.bindingEquals(this, obj);
        }
    
        @Override
        public String toString() {
            return FlowDataRecord.bindingToString(this);
        }
    }
}
