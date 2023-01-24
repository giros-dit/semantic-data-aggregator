package org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.netflow;
import java.lang.Class;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.Short;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import javax.annotation.processing.Generated;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFields.Direction;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFields.IpVersion;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFields.Protocol;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFields.TcpFlags;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.Ipv6FlowLabel;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.PrefixLengthIpv4;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.PrefixLengthIpv6;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.SysUptime;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.VlanId;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev130715.AsNumber;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev130715.Ipv4Address;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev130715.Ipv6Address;
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
import org.opendaylight.yangtools.yang.common.Uint8;

/**
 * Class that builds {@link FieldsFlowRecordBuilder} instances. Overall design of the class is that of a
 * <a href="https://en.wikipedia.org/wiki/Fluent_interface">fluent interface</a>, where method chaining is used.
 *
 * <p>
 * In general, this class is supposed to be used like this template:
 * <pre>
 *   <code>
 *     FieldsFlowRecordBuilder createTarget(int fooXyzzy, int barBaz) {
 *         return new FieldsFlowRecordBuilderBuilder()
 *             .setFoo(new FooBuilder().setXyzzy(fooXyzzy).build())
 *             .setBar(new BarBuilder().setBaz(barBaz).build())
 *             .build();
 *     }
 *   </code>
 * </pre>
 *
 * <p>
 * This pattern is supported by the immutable nature of FieldsFlowRecordBuilder, as instances can be freely passed around without
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
 * @see FieldsFlowRecordBuilder
 * @see Builder
 *
 */
@Generated("mdsal-binding-generator")
public class FieldsFlowRecordBuilder implements Builder<FieldsFlowRecord> {

    private Ipv4Address _bgpNextHop;
    private Ipv6Address _bgpNextHopIpv6;
    private Counter64 _bytesIn;
    private Counter64 _bytesMul;
    private Counter64 _bytesOut;
    private Direction _direction;
    private Ipv4Address _dstAddress;
    private Ipv6Address _dstAddressIpv6;
    private AsNumber _dstAs;
    private MacAddress _dstMac;
    private PrefixLengthIpv4 _dstMask;
    private PrefixLengthIpv6 _dstMaskIpv6;
    private PortNumber _dstPort;
    private Uint8 _dstTos;
    private VlanId _dstVlan;
    private SysUptime _firstSwitched;
    private Ipv6FlowLabel _flowLabelIpv6;
    private Counter64 _flows;
    private Uint16 _icmpType;
    private Uint8 _igmpType;
    private IpVersion _ipVersion;
    private SysUptime _lastSwitched;
    private String _name;
    private Ipv4Address _nextHop;
    private Ipv6Address _nextHopIpv6;
    private Uint32 _optHeadersIpv6;
    private Counter64 _pktsIn;
    private Counter64 _pktsMul;
    private Counter64 _pktsOut;
    private Protocol _protocol;
    private Integer _snmpIn;
    private Integer _snmpOut;
    private Ipv4Address _srcAddress;
    private Ipv6Address _srcAddressIpv6;
    private AsNumber _srcAs;
    private MacAddress _srcMac;
    private PrefixLengthIpv4 _srcMask;
    private PrefixLengthIpv6 _srcMaskIpv6;
    private PortNumber _srcPort;
    private Uint8 _srcTos;
    private VlanId _srcVlan;
    private TcpFlags _tcpFlags;
    private Counter64 _totBytesExp;
    private Counter64 _totFlowsExp;
    private Counter64 _totPktsExp;
    private FieldsFlowRecordKey key;


    Map<Class<? extends Augmentation<FieldsFlowRecord>>, Augmentation<FieldsFlowRecord>> augmentation = Collections.emptyMap();

    public FieldsFlowRecordBuilder() {
    }
    
    
    
    public FieldsFlowRecordBuilder(org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFields arg) {
        this._bytesIn = arg.getBytesIn();
        this._bytesOut = arg.getBytesOut();
        this._bytesMul = arg.getBytesMul();
        this._pktsIn = arg.getPktsIn();
        this._pktsOut = arg.getPktsOut();
        this._pktsMul = arg.getPktsMul();
        this._firstSwitched = arg.getFirstSwitched();
        this._lastSwitched = arg.getLastSwitched();
        this._flows = arg.getFlows();
        this._direction = arg.getDirection();
        this._ipVersion = arg.getIpVersion();
        this._protocol = arg.getProtocol();
        this._srcTos = arg.getSrcTos();
        this._dstTos = arg.getDstTos();
        this._tcpFlags = arg.getTcpFlags();
        this._srcPort = arg.getSrcPort();
        this._dstPort = arg.getDstPort();
        this._srcAddress = arg.getSrcAddress();
        this._dstAddress = arg.getDstAddress();
        this._nextHop = arg.getNextHop();
        this._srcAddressIpv6 = arg.getSrcAddressIpv6();
        this._dstAddressIpv6 = arg.getDstAddressIpv6();
        this._nextHopIpv6 = arg.getNextHopIpv6();
        this._srcMask = arg.getSrcMask();
        this._dstMask = arg.getDstMask();
        this._srcMaskIpv6 = arg.getSrcMaskIpv6();
        this._dstMaskIpv6 = arg.getDstMaskIpv6();
        this._srcMac = arg.getSrcMac();
        this._dstMac = arg.getDstMac();
        this._flowLabelIpv6 = arg.getFlowLabelIpv6();
        this._optHeadersIpv6 = arg.getOptHeadersIpv6();
        this._snmpIn = arg.getSnmpIn();
        this._snmpOut = arg.getSnmpOut();
        this._srcAs = arg.getSrcAs();
        this._dstAs = arg.getDstAs();
        this._bgpNextHop = arg.getBgpNextHop();
        this._bgpNextHopIpv6 = arg.getBgpNextHopIpv6();
        this._srcVlan = arg.getSrcVlan();
        this._dstVlan = arg.getDstVlan();
        this._icmpType = arg.getIcmpType();
        this._igmpType = arg.getIgmpType();
        this._totBytesExp = arg.getTotBytesExp();
        this._totPktsExp = arg.getTotPktsExp();
        this._totFlowsExp = arg.getTotFlowsExp();
    }
    

    public FieldsFlowRecordBuilder(FieldsFlowRecord base) {
        Map<Class<? extends Augmentation<FieldsFlowRecord>>, Augmentation<FieldsFlowRecord>> aug = base.augmentations();
        if (!aug.isEmpty()) {
            this.augmentation = new HashMap<>(aug);
        }
        this.key = base.key();
        this._name = base.getName();
        this._bgpNextHop = base.getBgpNextHop();
        this._bgpNextHopIpv6 = base.getBgpNextHopIpv6();
        this._bytesIn = base.getBytesIn();
        this._bytesMul = base.getBytesMul();
        this._bytesOut = base.getBytesOut();
        this._direction = base.getDirection();
        this._dstAddress = base.getDstAddress();
        this._dstAddressIpv6 = base.getDstAddressIpv6();
        this._dstAs = base.getDstAs();
        this._dstMac = base.getDstMac();
        this._dstMask = base.getDstMask();
        this._dstMaskIpv6 = base.getDstMaskIpv6();
        this._dstPort = base.getDstPort();
        this._dstTos = base.getDstTos();
        this._dstVlan = base.getDstVlan();
        this._firstSwitched = base.getFirstSwitched();
        this._flowLabelIpv6 = base.getFlowLabelIpv6();
        this._flows = base.getFlows();
        this._icmpType = base.getIcmpType();
        this._igmpType = base.getIgmpType();
        this._ipVersion = base.getIpVersion();
        this._lastSwitched = base.getLastSwitched();
        this._nextHop = base.getNextHop();
        this._nextHopIpv6 = base.getNextHopIpv6();
        this._optHeadersIpv6 = base.getOptHeadersIpv6();
        this._pktsIn = base.getPktsIn();
        this._pktsMul = base.getPktsMul();
        this._pktsOut = base.getPktsOut();
        this._protocol = base.getProtocol();
        this._snmpIn = base.getSnmpIn();
        this._snmpOut = base.getSnmpOut();
        this._srcAddress = base.getSrcAddress();
        this._srcAddressIpv6 = base.getSrcAddressIpv6();
        this._srcAs = base.getSrcAs();
        this._srcMac = base.getSrcMac();
        this._srcMask = base.getSrcMask();
        this._srcMaskIpv6 = base.getSrcMaskIpv6();
        this._srcPort = base.getSrcPort();
        this._srcTos = base.getSrcTos();
        this._srcVlan = base.getSrcVlan();
        this._tcpFlags = base.getTcpFlags();
        this._totBytesExp = base.getTotBytesExp();
        this._totFlowsExp = base.getTotFlowsExp();
        this._totPktsExp = base.getTotPktsExp();
    }

    /**
     * Set fields from given grouping argument. Valid argument is instance of one of following types:
     * <ul>
     * <li>org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFields</li>
     * </ul>
     *
     * @param arg grouping object
     * @throws IllegalArgumentException if given argument is none of valid types or has property with incompatible value
    */
    public void fieldsFrom(DataObject arg) {
        boolean isValidArg = false;
        if (arg instanceof org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFields) {
            this._bytesIn = ((org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFields)arg).getBytesIn();
            this._bytesOut = ((org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFields)arg).getBytesOut();
            this._bytesMul = ((org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFields)arg).getBytesMul();
            this._pktsIn = ((org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFields)arg).getPktsIn();
            this._pktsOut = ((org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFields)arg).getPktsOut();
            this._pktsMul = ((org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFields)arg).getPktsMul();
            this._firstSwitched = ((org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFields)arg).getFirstSwitched();
            this._lastSwitched = ((org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFields)arg).getLastSwitched();
            this._flows = ((org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFields)arg).getFlows();
            this._direction = ((org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFields)arg).getDirection();
            this._ipVersion = ((org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFields)arg).getIpVersion();
            this._protocol = ((org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFields)arg).getProtocol();
            this._srcTos = ((org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFields)arg).getSrcTos();
            this._dstTos = ((org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFields)arg).getDstTos();
            this._tcpFlags = ((org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFields)arg).getTcpFlags();
            this._srcPort = ((org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFields)arg).getSrcPort();
            this._dstPort = ((org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFields)arg).getDstPort();
            this._srcAddress = ((org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFields)arg).getSrcAddress();
            this._dstAddress = ((org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFields)arg).getDstAddress();
            this._nextHop = ((org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFields)arg).getNextHop();
            this._srcAddressIpv6 = ((org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFields)arg).getSrcAddressIpv6();
            this._dstAddressIpv6 = ((org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFields)arg).getDstAddressIpv6();
            this._nextHopIpv6 = ((org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFields)arg).getNextHopIpv6();
            this._srcMask = ((org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFields)arg).getSrcMask();
            this._dstMask = ((org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFields)arg).getDstMask();
            this._srcMaskIpv6 = ((org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFields)arg).getSrcMaskIpv6();
            this._dstMaskIpv6 = ((org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFields)arg).getDstMaskIpv6();
            this._srcMac = ((org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFields)arg).getSrcMac();
            this._dstMac = ((org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFields)arg).getDstMac();
            this._flowLabelIpv6 = ((org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFields)arg).getFlowLabelIpv6();
            this._optHeadersIpv6 = ((org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFields)arg).getOptHeadersIpv6();
            this._snmpIn = ((org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFields)arg).getSnmpIn();
            this._snmpOut = ((org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFields)arg).getSnmpOut();
            this._srcAs = ((org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFields)arg).getSrcAs();
            this._dstAs = ((org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFields)arg).getDstAs();
            this._bgpNextHop = ((org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFields)arg).getBgpNextHop();
            this._bgpNextHopIpv6 = ((org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFields)arg).getBgpNextHopIpv6();
            this._srcVlan = ((org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFields)arg).getSrcVlan();
            this._dstVlan = ((org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFields)arg).getDstVlan();
            this._icmpType = ((org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFields)arg).getIcmpType();
            this._igmpType = ((org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFields)arg).getIgmpType();
            this._totBytesExp = ((org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFields)arg).getTotBytesExp();
            this._totPktsExp = ((org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFields)arg).getTotPktsExp();
            this._totFlowsExp = ((org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFields)arg).getTotFlowsExp();
            isValidArg = true;
        }
        CodeHelpers.validValue(isValidArg, arg, "[org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFields]");
    }

    public FieldsFlowRecordKey key() {
        return key;
    }
    
    public Ipv4Address getBgpNextHop() {
        return _bgpNextHop;
    }
    
    public Ipv6Address getBgpNextHopIpv6() {
        return _bgpNextHopIpv6;
    }
    
    public Counter64 getBytesIn() {
        return _bytesIn;
    }
    
    public Counter64 getBytesMul() {
        return _bytesMul;
    }
    
    public Counter64 getBytesOut() {
        return _bytesOut;
    }
    
    public Direction getDirection() {
        return _direction;
    }
    
    public Ipv4Address getDstAddress() {
        return _dstAddress;
    }
    
    public Ipv6Address getDstAddressIpv6() {
        return _dstAddressIpv6;
    }
    
    public AsNumber getDstAs() {
        return _dstAs;
    }
    
    public MacAddress getDstMac() {
        return _dstMac;
    }
    
    public PrefixLengthIpv4 getDstMask() {
        return _dstMask;
    }
    
    public PrefixLengthIpv6 getDstMaskIpv6() {
        return _dstMaskIpv6;
    }
    
    public PortNumber getDstPort() {
        return _dstPort;
    }
    
    public Uint8 getDstTos() {
        return _dstTos;
    }
    
    public VlanId getDstVlan() {
        return _dstVlan;
    }
    
    public SysUptime getFirstSwitched() {
        return _firstSwitched;
    }
    
    public Ipv6FlowLabel getFlowLabelIpv6() {
        return _flowLabelIpv6;
    }
    
    public Counter64 getFlows() {
        return _flows;
    }
    
    public Uint16 getIcmpType() {
        return _icmpType;
    }
    
    public Uint8 getIgmpType() {
        return _igmpType;
    }
    
    public IpVersion getIpVersion() {
        return _ipVersion;
    }
    
    public SysUptime getLastSwitched() {
        return _lastSwitched;
    }
    
    public String getName() {
        return _name;
    }
    
    public Ipv4Address getNextHop() {
        return _nextHop;
    }
    
    public Ipv6Address getNextHopIpv6() {
        return _nextHopIpv6;
    }
    
    public Uint32 getOptHeadersIpv6() {
        return _optHeadersIpv6;
    }
    
    public Counter64 getPktsIn() {
        return _pktsIn;
    }
    
    public Counter64 getPktsMul() {
        return _pktsMul;
    }
    
    public Counter64 getPktsOut() {
        return _pktsOut;
    }
    
    public Protocol getProtocol() {
        return _protocol;
    }
    
    public Integer getSnmpIn() {
        return _snmpIn;
    }
    
    public Integer getSnmpOut() {
        return _snmpOut;
    }
    
    public Ipv4Address getSrcAddress() {
        return _srcAddress;
    }
    
    public Ipv6Address getSrcAddressIpv6() {
        return _srcAddressIpv6;
    }
    
    public AsNumber getSrcAs() {
        return _srcAs;
    }
    
    public MacAddress getSrcMac() {
        return _srcMac;
    }
    
    public PrefixLengthIpv4 getSrcMask() {
        return _srcMask;
    }
    
    public PrefixLengthIpv6 getSrcMaskIpv6() {
        return _srcMaskIpv6;
    }
    
    public PortNumber getSrcPort() {
        return _srcPort;
    }
    
    public Uint8 getSrcTos() {
        return _srcTos;
    }
    
    public VlanId getSrcVlan() {
        return _srcVlan;
    }
    
    public TcpFlags getTcpFlags() {
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

    @SuppressWarnings({ "unchecked", "checkstyle:methodTypeParameterName"})
    public <E$$ extends Augmentation<FieldsFlowRecord>> E$$ augmentation(Class<E$$> augmentationType) {
        return (E$$) augmentation.get(Objects.requireNonNull(augmentationType));
    }

    public FieldsFlowRecordBuilder withKey(final FieldsFlowRecordKey key) {
        this.key = key;
        return this;
    }
    
    public FieldsFlowRecordBuilder setBgpNextHop(final Ipv4Address value) {
        this._bgpNextHop = value;
        return this;
    }
    
    public FieldsFlowRecordBuilder setBgpNextHopIpv6(final Ipv6Address value) {
        this._bgpNextHopIpv6 = value;
        return this;
    }
    
    public FieldsFlowRecordBuilder setBytesIn(final Counter64 value) {
        this._bytesIn = value;
        return this;
    }
    
    public FieldsFlowRecordBuilder setBytesMul(final Counter64 value) {
        this._bytesMul = value;
        return this;
    }
    
    public FieldsFlowRecordBuilder setBytesOut(final Counter64 value) {
        this._bytesOut = value;
        return this;
    }
    
    public FieldsFlowRecordBuilder setDirection(final Direction value) {
        this._direction = value;
        return this;
    }
    
    public FieldsFlowRecordBuilder setDstAddress(final Ipv4Address value) {
        this._dstAddress = value;
        return this;
    }
    
    public FieldsFlowRecordBuilder setDstAddressIpv6(final Ipv6Address value) {
        this._dstAddressIpv6 = value;
        return this;
    }
    
    public FieldsFlowRecordBuilder setDstAs(final AsNumber value) {
        this._dstAs = value;
        return this;
    }
    
    public FieldsFlowRecordBuilder setDstMac(final MacAddress value) {
        this._dstMac = value;
        return this;
    }
    
    public FieldsFlowRecordBuilder setDstMask(final PrefixLengthIpv4 value) {
        this._dstMask = value;
        return this;
    }
    
    public FieldsFlowRecordBuilder setDstMaskIpv6(final PrefixLengthIpv6 value) {
        this._dstMaskIpv6 = value;
        return this;
    }
    
    public FieldsFlowRecordBuilder setDstPort(final PortNumber value) {
        this._dstPort = value;
        return this;
    }
    
    public FieldsFlowRecordBuilder setDstTos(final Uint8 value) {
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
    public FieldsFlowRecordBuilder setDstTos(final Short value) {
        return setDstTos(CodeHelpers.compatUint(value));
    }
    
    public FieldsFlowRecordBuilder setDstVlan(final VlanId value) {
        this._dstVlan = value;
        return this;
    }
    
    public FieldsFlowRecordBuilder setFirstSwitched(final SysUptime value) {
        this._firstSwitched = value;
        return this;
    }
    
    public FieldsFlowRecordBuilder setFlowLabelIpv6(final Ipv6FlowLabel value) {
        this._flowLabelIpv6 = value;
        return this;
    }
    
    public FieldsFlowRecordBuilder setFlows(final Counter64 value) {
        this._flows = value;
        return this;
    }
    
    public FieldsFlowRecordBuilder setIcmpType(final Uint16 value) {
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
    public FieldsFlowRecordBuilder setIcmpType(final Integer value) {
        return setIcmpType(CodeHelpers.compatUint(value));
    }
    
    public FieldsFlowRecordBuilder setIgmpType(final Uint8 value) {
        this._igmpType = value;
        return this;
    }
    
    /**
     * Utility migration setter.
     *
     * @param value field value in legacy type
     * @return this builder
     * @deprecated Use {@link #setIgmpType(Uint8)} instead.
     */
    @Deprecated(forRemoval = true)
    public FieldsFlowRecordBuilder setIgmpType(final Short value) {
        return setIgmpType(CodeHelpers.compatUint(value));
    }
    
    public FieldsFlowRecordBuilder setIpVersion(final IpVersion value) {
        this._ipVersion = value;
        return this;
    }
    
    public FieldsFlowRecordBuilder setLastSwitched(final SysUptime value) {
        this._lastSwitched = value;
        return this;
    }
    
    public FieldsFlowRecordBuilder setName(final String value) {
        this._name = value;
        return this;
    }
    
    public FieldsFlowRecordBuilder setNextHop(final Ipv4Address value) {
        this._nextHop = value;
        return this;
    }
    
    public FieldsFlowRecordBuilder setNextHopIpv6(final Ipv6Address value) {
        this._nextHopIpv6 = value;
        return this;
    }
    
    public FieldsFlowRecordBuilder setOptHeadersIpv6(final Uint32 value) {
        this._optHeadersIpv6 = value;
        return this;
    }
    
    /**
     * Utility migration setter.
     *
     * @param value field value in legacy type
     * @return this builder
     * @deprecated Use {@link #setOptHeadersIpv6(Uint32)} instead.
     */
    @Deprecated(forRemoval = true)
    public FieldsFlowRecordBuilder setOptHeadersIpv6(final Long value) {
        return setOptHeadersIpv6(CodeHelpers.compatUint(value));
    }
    
    public FieldsFlowRecordBuilder setPktsIn(final Counter64 value) {
        this._pktsIn = value;
        return this;
    }
    
    public FieldsFlowRecordBuilder setPktsMul(final Counter64 value) {
        this._pktsMul = value;
        return this;
    }
    
    public FieldsFlowRecordBuilder setPktsOut(final Counter64 value) {
        this._pktsOut = value;
        return this;
    }
    
    public FieldsFlowRecordBuilder setProtocol(final Protocol value) {
        this._protocol = value;
        return this;
    }
    
    private static void checkSnmpInRange(final int value) {
        if (value >= 1) {
            return;
        }
        CodeHelpers.throwInvalidRange("[[1..2147483647]]", value);
    }
    
    public FieldsFlowRecordBuilder setSnmpIn(final Integer value) {
        if (value != null) {
            checkSnmpInRange(value);
            
        }
        this._snmpIn = value;
        return this;
    }
    
    private static void checkSnmpOutRange(final int value) {
        if (value >= 1) {
            return;
        }
        CodeHelpers.throwInvalidRange("[[1..2147483647]]", value);
    }
    
    public FieldsFlowRecordBuilder setSnmpOut(final Integer value) {
        if (value != null) {
            checkSnmpOutRange(value);
            
        }
        this._snmpOut = value;
        return this;
    }
    
    public FieldsFlowRecordBuilder setSrcAddress(final Ipv4Address value) {
        this._srcAddress = value;
        return this;
    }
    
    public FieldsFlowRecordBuilder setSrcAddressIpv6(final Ipv6Address value) {
        this._srcAddressIpv6 = value;
        return this;
    }
    
    public FieldsFlowRecordBuilder setSrcAs(final AsNumber value) {
        this._srcAs = value;
        return this;
    }
    
    public FieldsFlowRecordBuilder setSrcMac(final MacAddress value) {
        this._srcMac = value;
        return this;
    }
    
    public FieldsFlowRecordBuilder setSrcMask(final PrefixLengthIpv4 value) {
        this._srcMask = value;
        return this;
    }
    
    public FieldsFlowRecordBuilder setSrcMaskIpv6(final PrefixLengthIpv6 value) {
        this._srcMaskIpv6 = value;
        return this;
    }
    
    public FieldsFlowRecordBuilder setSrcPort(final PortNumber value) {
        this._srcPort = value;
        return this;
    }
    
    public FieldsFlowRecordBuilder setSrcTos(final Uint8 value) {
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
    public FieldsFlowRecordBuilder setSrcTos(final Short value) {
        return setSrcTos(CodeHelpers.compatUint(value));
    }
    
    public FieldsFlowRecordBuilder setSrcVlan(final VlanId value) {
        this._srcVlan = value;
        return this;
    }
    
    public FieldsFlowRecordBuilder setTcpFlags(final TcpFlags value) {
        this._tcpFlags = value;
        return this;
    }
    
    public FieldsFlowRecordBuilder setTotBytesExp(final Counter64 value) {
        this._totBytesExp = value;
        return this;
    }
    
    public FieldsFlowRecordBuilder setTotFlowsExp(final Counter64 value) {
        this._totFlowsExp = value;
        return this;
    }
    
    public FieldsFlowRecordBuilder setTotPktsExp(final Counter64 value) {
        this._totPktsExp = value;
        return this;
    }
    
    /**
      * Add an augmentation to this builder's product.
      *
      * @param augmentation augmentation to be added
      * @return this builder
      * @throws NullPointerException if {@code augmentation} is null
      */
    public FieldsFlowRecordBuilder addAugmentation(Augmentation<FieldsFlowRecord> augmentation) {
        Class<? extends Augmentation<FieldsFlowRecord>> augmentationType = augmentation.implementedInterface();
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
    public FieldsFlowRecordBuilder removeAugmentation(Class<? extends Augmentation<FieldsFlowRecord>> augmentationType) {
        if (this.augmentation instanceof HashMap) {
            this.augmentation.remove(augmentationType);
        }
        return this;
    }

    @Override
    public FieldsFlowRecord build() {
        return new FieldsFlowRecordImpl(this);
    }

    private static final class FieldsFlowRecordImpl
        extends AbstractAugmentable<FieldsFlowRecord>
        implements FieldsFlowRecord {
    
        private final Ipv4Address _bgpNextHop;
        private final Ipv6Address _bgpNextHopIpv6;
        private final Counter64 _bytesIn;
        private final Counter64 _bytesMul;
        private final Counter64 _bytesOut;
        private final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFields.Direction _direction;
        private final Ipv4Address _dstAddress;
        private final Ipv6Address _dstAddressIpv6;
        private final AsNumber _dstAs;
        private final MacAddress _dstMac;
        private final PrefixLengthIpv4 _dstMask;
        private final PrefixLengthIpv6 _dstMaskIpv6;
        private final PortNumber _dstPort;
        private final Uint8 _dstTos;
        private final VlanId _dstVlan;
        private final SysUptime _firstSwitched;
        private final Ipv6FlowLabel _flowLabelIpv6;
        private final Counter64 _flows;
        private final Uint16 _icmpType;
        private final Uint8 _igmpType;
        private final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFields.IpVersion _ipVersion;
        private final SysUptime _lastSwitched;
        private final String _name;
        private final Ipv4Address _nextHop;
        private final Ipv6Address _nextHopIpv6;
        private final Uint32 _optHeadersIpv6;
        private final Counter64 _pktsIn;
        private final Counter64 _pktsMul;
        private final Counter64 _pktsOut;
        private final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFields.Protocol _protocol;
        private final Integer _snmpIn;
        private final Integer _snmpOut;
        private final Ipv4Address _srcAddress;
        private final Ipv6Address _srcAddressIpv6;
        private final AsNumber _srcAs;
        private final MacAddress _srcMac;
        private final PrefixLengthIpv4 _srcMask;
        private final PrefixLengthIpv6 _srcMaskIpv6;
        private final PortNumber _srcPort;
        private final Uint8 _srcTos;
        private final VlanId _srcVlan;
        private final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFields.TcpFlags _tcpFlags;
        private final Counter64 _totBytesExp;
        private final Counter64 _totFlowsExp;
        private final Counter64 _totPktsExp;
        private final FieldsFlowRecordKey key;
    
        FieldsFlowRecordImpl(FieldsFlowRecordBuilder base) {
            super(base.augmentation);
            if (base.key() != null) {
                this.key = base.key();
            } else {
                this.key = new FieldsFlowRecordKey(base.getName());
            }
            this._name = key.getName();
            this._bgpNextHop = base.getBgpNextHop();
            this._bgpNextHopIpv6 = base.getBgpNextHopIpv6();
            this._bytesIn = base.getBytesIn();
            this._bytesMul = base.getBytesMul();
            this._bytesOut = base.getBytesOut();
            this._direction = base.getDirection();
            this._dstAddress = base.getDstAddress();
            this._dstAddressIpv6 = base.getDstAddressIpv6();
            this._dstAs = base.getDstAs();
            this._dstMac = base.getDstMac();
            this._dstMask = base.getDstMask();
            this._dstMaskIpv6 = base.getDstMaskIpv6();
            this._dstPort = base.getDstPort();
            this._dstTos = base.getDstTos();
            this._dstVlan = base.getDstVlan();
            this._firstSwitched = base.getFirstSwitched();
            this._flowLabelIpv6 = base.getFlowLabelIpv6();
            this._flows = base.getFlows();
            this._icmpType = base.getIcmpType();
            this._igmpType = base.getIgmpType();
            this._ipVersion = base.getIpVersion();
            this._lastSwitched = base.getLastSwitched();
            this._nextHop = base.getNextHop();
            this._nextHopIpv6 = base.getNextHopIpv6();
            this._optHeadersIpv6 = base.getOptHeadersIpv6();
            this._pktsIn = base.getPktsIn();
            this._pktsMul = base.getPktsMul();
            this._pktsOut = base.getPktsOut();
            this._protocol = base.getProtocol();
            this._snmpIn = base.getSnmpIn();
            this._snmpOut = base.getSnmpOut();
            this._srcAddress = base.getSrcAddress();
            this._srcAddressIpv6 = base.getSrcAddressIpv6();
            this._srcAs = base.getSrcAs();
            this._srcMac = base.getSrcMac();
            this._srcMask = base.getSrcMask();
            this._srcMaskIpv6 = base.getSrcMaskIpv6();
            this._srcPort = base.getSrcPort();
            this._srcTos = base.getSrcTos();
            this._srcVlan = base.getSrcVlan();
            this._tcpFlags = base.getTcpFlags();
            this._totBytesExp = base.getTotBytesExp();
            this._totFlowsExp = base.getTotFlowsExp();
            this._totPktsExp = base.getTotPktsExp();
        }
    
        @Override
        public FieldsFlowRecordKey key() {
            return key;
        }
        
        @Override
        public Ipv4Address getBgpNextHop() {
            return _bgpNextHop;
        }
        
        @Override
        public Ipv6Address getBgpNextHopIpv6() {
            return _bgpNextHopIpv6;
        }
        
        @Override
        public Counter64 getBytesIn() {
            return _bytesIn;
        }
        
        @Override
        public Counter64 getBytesMul() {
            return _bytesMul;
        }
        
        @Override
        public Counter64 getBytesOut() {
            return _bytesOut;
        }
        
        @Override
        public org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFields.Direction getDirection() {
            return _direction;
        }
        
        @Override
        public Ipv4Address getDstAddress() {
            return _dstAddress;
        }
        
        @Override
        public Ipv6Address getDstAddressIpv6() {
            return _dstAddressIpv6;
        }
        
        @Override
        public AsNumber getDstAs() {
            return _dstAs;
        }
        
        @Override
        public MacAddress getDstMac() {
            return _dstMac;
        }
        
        @Override
        public PrefixLengthIpv4 getDstMask() {
            return _dstMask;
        }
        
        @Override
        public PrefixLengthIpv6 getDstMaskIpv6() {
            return _dstMaskIpv6;
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
        public VlanId getDstVlan() {
            return _dstVlan;
        }
        
        @Override
        public SysUptime getFirstSwitched() {
            return _firstSwitched;
        }
        
        @Override
        public Ipv6FlowLabel getFlowLabelIpv6() {
            return _flowLabelIpv6;
        }
        
        @Override
        public Counter64 getFlows() {
            return _flows;
        }
        
        @Override
        public Uint16 getIcmpType() {
            return _icmpType;
        }
        
        @Override
        public Uint8 getIgmpType() {
            return _igmpType;
        }
        
        @Override
        public org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFields.IpVersion getIpVersion() {
            return _ipVersion;
        }
        
        @Override
        public SysUptime getLastSwitched() {
            return _lastSwitched;
        }
        
        @Override
        public String getName() {
            return _name;
        }
        
        @Override
        public Ipv4Address getNextHop() {
            return _nextHop;
        }
        
        @Override
        public Ipv6Address getNextHopIpv6() {
            return _nextHopIpv6;
        }
        
        @Override
        public Uint32 getOptHeadersIpv6() {
            return _optHeadersIpv6;
        }
        
        @Override
        public Counter64 getPktsIn() {
            return _pktsIn;
        }
        
        @Override
        public Counter64 getPktsMul() {
            return _pktsMul;
        }
        
        @Override
        public Counter64 getPktsOut() {
            return _pktsOut;
        }
        
        @Override
        public org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFields.Protocol getProtocol() {
            return _protocol;
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
        public Ipv4Address getSrcAddress() {
            return _srcAddress;
        }
        
        @Override
        public Ipv6Address getSrcAddressIpv6() {
            return _srcAddressIpv6;
        }
        
        @Override
        public AsNumber getSrcAs() {
            return _srcAs;
        }
        
        @Override
        public MacAddress getSrcMac() {
            return _srcMac;
        }
        
        @Override
        public PrefixLengthIpv4 getSrcMask() {
            return _srcMask;
        }
        
        @Override
        public PrefixLengthIpv6 getSrcMaskIpv6() {
            return _srcMaskIpv6;
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
        public VlanId getSrcVlan() {
            return _srcVlan;
        }
        
        @Override
        public org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFields.TcpFlags getTcpFlags() {
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
    
        private int hash = 0;
        private volatile boolean hashValid = false;
        
        @Override
        public int hashCode() {
            if (hashValid) {
                return hash;
            }
        
            final int result = FieldsFlowRecord.bindingHashCode(this);
            hash = result;
            hashValid = true;
            return result;
        }
    
        @Override
        public boolean equals(Object obj) {
            return FieldsFlowRecord.bindingEquals(this, obj);
        }
    
        @Override
        public String toString() {
            return FieldsFlowRecord.bindingToString(this);
        }
    }
}
