package org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.netflow.export.packet;
import com.google.common.base.MoreObjects;
import java.lang.Class;
import java.lang.Integer;
import java.lang.NullPointerException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.util.Objects;
import javax.annotation.processing.Generated;
import org.eclipse.jdt.annotation.NonNull;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.$YangModuleInfoImpl;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFlowFields;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.netflow.ExportPacket;
import org.opendaylight.yangtools.yang.binding.Augmentable;
import org.opendaylight.yangtools.yang.binding.ChildOf;
import org.opendaylight.yangtools.yang.binding.CodeHelpers;
import org.opendaylight.yangtools.yang.binding.Identifiable;
import org.opendaylight.yangtools.yang.common.QName;

/**
 * This list contains all possible fields of a Flow Data Record sent in a Export 
 * Packet
 * 
 * <p>
 * This class represents the following YANG schema fragment defined in module <b>netflow-v9</b>
 * <pre>
 * list flow-data-record {
 *   ordered-by user;
 *   key flow-id;
 *   leaf flow-id {
 *     type int32;
 *   }
 *   uses common-flow-fields;
 * }
 * </pre>The schema path to identify an instance is
 * <i>netflow-v9/netflow/export-packet/flow-data-record</i>
 * 
 * <p>To create instances of this class use {@link FlowDataRecordBuilder}.
 * @see FlowDataRecordBuilder
 * @see FlowDataRecordKey
 *
 */
@Generated("mdsal-binding-generator")
public interface FlowDataRecord
    extends
    ChildOf<ExportPacket>,
    Augmentable<FlowDataRecord>,
    CommonFlowFields,
    Identifiable<FlowDataRecordKey>
{



    public static final @NonNull QName QNAME = $YangModuleInfoImpl.qnameOf("flow-data-record");

    @Override
    default Class<org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.netflow.export.packet.FlowDataRecord> implementedInterface() {
        return org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.netflow.export.packet.FlowDataRecord.class;
    }
    
    /**
     * Default implementation of {@link Object#hashCode()} contract for this interface.
     * Implementations of this interface are encouraged to defer to this method to get consistent hashing
     * results across all implementations.
     *
     * @param obj Object for which to generate hashCode() result.
     * @return Hash code value of data modeled by this interface.
     * @throws NullPointerException if {@code obj} is null
     */
    static int bindingHashCode(final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.netflow.export.packet.@NonNull FlowDataRecord obj) {
        final int prime = 31;
        int result = 1;
        result = prime * result + Objects.hashCode(obj.getApplication());
        result = prime * result + Objects.hashCode(obj.getBgp());
        result = prime * result + Objects.hashCode(obj.getBytesIn());
        result = prime * result + Objects.hashCode(obj.getBytesOut());
        result = prime * result + Objects.hashCode(obj.getBytesOutMul());
        result = prime * result + Objects.hashCode(obj.getDirection());
        result = prime * result + Objects.hashCode(obj.getDstMacIn());
        result = prime * result + Objects.hashCode(obj.getDstMacOut());
        result = prime * result + Objects.hashCode(obj.getDstPort());
        result = prime * result + Objects.hashCode(obj.getDstTos());
        result = prime * result + Objects.hashCode(obj.getEngineId());
        result = prime * result + Objects.hashCode(obj.getEngineType());
        result = prime * result + Objects.hashCode(obj.getFirstSwitched());
        result = prime * result + Objects.hashCode(obj.getFlowActiveTout());
        result = prime * result + Objects.hashCode(obj.getFlowId());
        result = prime * result + Objects.hashCode(obj.getFlowInactiveTout());
        result = prime * result + Objects.hashCode(obj.getFlowSamplerId());
        result = prime * result + Objects.hashCode(obj.getFlowSamplerMode());
        result = prime * result + Objects.hashCode(obj.getFlowSamplerRandom());
        result = prime * result + Objects.hashCode(obj.getFlows());
        result = prime * result + Objects.hashCode(obj.getForwardingStatus());
        result = prime * result + Objects.hashCode(obj.getFragOffset());
        result = prime * result + Objects.hashCode(obj.getIcmpType());
        result = prime * result + Objects.hashCode(obj.getIfDesc());
        result = prime * result + Objects.hashCode(obj.getIfName());
        result = prime * result + Objects.hashCode(obj.getIgmpType());
        result = prime * result + Objects.hashCode(obj.getIpVersion());
        result = prime * result + Objects.hashCode(obj.getIpv4());
        result = prime * result + Objects.hashCode(obj.getIpv6());
        result = prime * result + Objects.hashCode(obj.getLastSwitched());
        result = prime * result + Objects.hashCode(obj.getLayer2PktSection());
        result = prime * result + Objects.hashCode(obj.getMaxPktLen());
        result = prime * result + Objects.hashCode(obj.getMaxTtl());
        result = prime * result + Objects.hashCode(obj.getMinPktLen());
        result = prime * result + Objects.hashCode(obj.getMinTtl());
        result = prime * result + Objects.hashCode(obj.getMpls());
        result = prime * result + Objects.hashCode(obj.getPermanentFlow());
        result = prime * result + Objects.hashCode(obj.getPktsIn());
        result = prime * result + Objects.hashCode(obj.getPktsOut());
        result = prime * result + Objects.hashCode(obj.getPktsOutMul());
        result = prime * result + Objects.hashCode(obj.getPostipDscp());
        result = prime * result + Objects.hashCode(obj.getProtocol());
        result = prime * result + Objects.hashCode(obj.getReplFactorMul());
        result = prime * result + Objects.hashCode(obj.getSamplerName());
        result = prime * result + Objects.hashCode(obj.getSamplingAlgorithm());
        result = prime * result + Objects.hashCode(obj.getSamplingInterval());
        result = prime * result + Objects.hashCode(obj.getSnmpIn());
        result = prime * result + Objects.hashCode(obj.getSnmpOut());
        result = prime * result + Objects.hashCode(obj.getSrcMacIn());
        result = prime * result + Objects.hashCode(obj.getSrcMacOut());
        result = prime * result + Objects.hashCode(obj.getSrcPort());
        result = prime * result + Objects.hashCode(obj.getSrcTos());
        result = prime * result + Objects.hashCode(obj.getTcpFlags());
        result = prime * result + Objects.hashCode(obj.getTotBytesExp());
        result = prime * result + Objects.hashCode(obj.getTotFlowsExp());
        result = prime * result + Objects.hashCode(obj.getTotPktsExp());
        result = prime * result + Objects.hashCode(obj.getVlan());
        result = prime * result + obj.augmentations().hashCode();
        return result;
    }
    
    /**
     * Default implementation of {@link Object#equals(Object)} contract for this interface.
     * Implementations of this interface are encouraged to defer to this method to get consistent equality
     * results across all implementations.
     *
     * @param thisObj Object acting as the receiver of equals invocation
     * @param obj Object acting as argument to equals invocation
     * @return True if thisObj and obj are considered equal
     * @throws NullPointerException if {@code thisObj} is null
     */
    static boolean bindingEquals(final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.netflow.export.packet.@NonNull FlowDataRecord thisObj, final Object obj) {
        if (thisObj == obj) {
            return true;
        }
        final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.netflow.export.packet.FlowDataRecord other = CodeHelpers.checkCast(org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.netflow.export.packet.FlowDataRecord.class, obj);
        if (other == null) {
            return false;
        }
        if (!Objects.equals(thisObj.getBytesIn(), other.getBytesIn())) {
            return false;
        }
        if (!Objects.equals(thisObj.getBytesOut(), other.getBytesOut())) {
            return false;
        }
        if (!Objects.equals(thisObj.getBytesOutMul(), other.getBytesOutMul())) {
            return false;
        }
        if (!Objects.equals(thisObj.getDstPort(), other.getDstPort())) {
            return false;
        }
        if (!Objects.equals(thisObj.getDstTos(), other.getDstTos())) {
            return false;
        }
        if (!Objects.equals(thisObj.getEngineId(), other.getEngineId())) {
            return false;
        }
        if (!Objects.equals(thisObj.getFirstSwitched(), other.getFirstSwitched())) {
            return false;
        }
        if (!Objects.equals(thisObj.getFlowActiveTout(), other.getFlowActiveTout())) {
            return false;
        }
        if (!Objects.equals(thisObj.getFlowId(), other.getFlowId())) {
            return false;
        }
        if (!Objects.equals(thisObj.getFlowInactiveTout(), other.getFlowInactiveTout())) {
            return false;
        }
        if (!Objects.equals(thisObj.getFlowSamplerId(), other.getFlowSamplerId())) {
            return false;
        }
        if (!Objects.equals(thisObj.getFlowSamplerRandom(), other.getFlowSamplerRandom())) {
            return false;
        }
        if (!Objects.equals(thisObj.getFlows(), other.getFlows())) {
            return false;
        }
        if (!Objects.equals(thisObj.getFragOffset(), other.getFragOffset())) {
            return false;
        }
        if (!Objects.equals(thisObj.getIcmpType(), other.getIcmpType())) {
            return false;
        }
        if (!Objects.equals(thisObj.getLastSwitched(), other.getLastSwitched())) {
            return false;
        }
        if (!Objects.equals(thisObj.getMaxPktLen(), other.getMaxPktLen())) {
            return false;
        }
        if (!Objects.equals(thisObj.getMaxTtl(), other.getMaxTtl())) {
            return false;
        }
        if (!Objects.equals(thisObj.getMinPktLen(), other.getMinPktLen())) {
            return false;
        }
        if (!Objects.equals(thisObj.getMinTtl(), other.getMinTtl())) {
            return false;
        }
        if (!Objects.equals(thisObj.getPktsIn(), other.getPktsIn())) {
            return false;
        }
        if (!Objects.equals(thisObj.getPktsOut(), other.getPktsOut())) {
            return false;
        }
        if (!Objects.equals(thisObj.getPktsOutMul(), other.getPktsOutMul())) {
            return false;
        }
        if (!Objects.equals(thisObj.getPostipDscp(), other.getPostipDscp())) {
            return false;
        }
        if (!Objects.equals(thisObj.getReplFactorMul(), other.getReplFactorMul())) {
            return false;
        }
        if (!Objects.equals(thisObj.getSamplingInterval(), other.getSamplingInterval())) {
            return false;
        }
        if (!Objects.equals(thisObj.getSnmpIn(), other.getSnmpIn())) {
            return false;
        }
        if (!Objects.equals(thisObj.getSnmpOut(), other.getSnmpOut())) {
            return false;
        }
        if (!Objects.equals(thisObj.getSrcPort(), other.getSrcPort())) {
            return false;
        }
        if (!Objects.equals(thisObj.getSrcTos(), other.getSrcTos())) {
            return false;
        }
        if (!Objects.equals(thisObj.getTotBytesExp(), other.getTotBytesExp())) {
            return false;
        }
        if (!Objects.equals(thisObj.getTotFlowsExp(), other.getTotFlowsExp())) {
            return false;
        }
        if (!Objects.equals(thisObj.getTotPktsExp(), other.getTotPktsExp())) {
            return false;
        }
        if (!Objects.equals(thisObj.getDstMacIn(), other.getDstMacIn())) {
            return false;
        }
        if (!Objects.equals(thisObj.getDstMacOut(), other.getDstMacOut())) {
            return false;
        }
        if (!Objects.equals(thisObj.getIfDesc(), other.getIfDesc())) {
            return false;
        }
        if (!Objects.equals(thisObj.getIfName(), other.getIfName())) {
            return false;
        }
        if (!Objects.equals(thisObj.getSamplerName(), other.getSamplerName())) {
            return false;
        }
        if (!Objects.equals(thisObj.getSrcMacIn(), other.getSrcMacIn())) {
            return false;
        }
        if (!Objects.equals(thisObj.getSrcMacOut(), other.getSrcMacOut())) {
            return false;
        }
        if (!Objects.equals(thisObj.getTcpFlags(), other.getTcpFlags())) {
            return false;
        }
        if (!Objects.equals(thisObj.getApplication(), other.getApplication())) {
            return false;
        }
        if (!Objects.equals(thisObj.getBgp(), other.getBgp())) {
            return false;
        }
        if (!Objects.equals(thisObj.getDirection(), other.getDirection())) {
            return false;
        }
        if (!Objects.equals(thisObj.getEngineType(), other.getEngineType())) {
            return false;
        }
        if (!Objects.equals(thisObj.getFlowSamplerMode(), other.getFlowSamplerMode())) {
            return false;
        }
        if (!Objects.equals(thisObj.getForwardingStatus(), other.getForwardingStatus())) {
            return false;
        }
        if (!Objects.equals(thisObj.getIgmpType(), other.getIgmpType())) {
            return false;
        }
        if (!Objects.equals(thisObj.getIpVersion(), other.getIpVersion())) {
            return false;
        }
        if (!Objects.equals(thisObj.getIpv4(), other.getIpv4())) {
            return false;
        }
        if (!Objects.equals(thisObj.getIpv6(), other.getIpv6())) {
            return false;
        }
        if (!Objects.equals(thisObj.getLayer2PktSection(), other.getLayer2PktSection())) {
            return false;
        }
        if (!Objects.equals(thisObj.getMpls(), other.getMpls())) {
            return false;
        }
        if (!Objects.equals(thisObj.getPermanentFlow(), other.getPermanentFlow())) {
            return false;
        }
        if (!Objects.equals(thisObj.getProtocol(), other.getProtocol())) {
            return false;
        }
        if (!Objects.equals(thisObj.getSamplingAlgorithm(), other.getSamplingAlgorithm())) {
            return false;
        }
        if (!Objects.equals(thisObj.getVlan(), other.getVlan())) {
            return false;
        }
        return thisObj.augmentations().equals(other.augmentations());
    }
    
    /**
     * Default implementation of {@link Object#toString()} contract for this interface.
     * Implementations of this interface are encouraged to defer to this method to get consistent string
     * representations across all implementations.
     *
     * @param obj Object for which to generate toString() result.
     * @return {@link String} value of data modeled by this interface.
     * @throws NullPointerException if {@code obj} is null
     */
    static String bindingToString(final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.netflow.export.packet.@NonNull FlowDataRecord obj) {
        final MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper("FlowDataRecord");
        CodeHelpers.appendValue(helper, "application", obj.getApplication());
        CodeHelpers.appendValue(helper, "bgp", obj.getBgp());
        CodeHelpers.appendValue(helper, "bytesIn", obj.getBytesIn());
        CodeHelpers.appendValue(helper, "bytesOut", obj.getBytesOut());
        CodeHelpers.appendValue(helper, "bytesOutMul", obj.getBytesOutMul());
        CodeHelpers.appendValue(helper, "direction", obj.getDirection());
        CodeHelpers.appendValue(helper, "dstMacIn", obj.getDstMacIn());
        CodeHelpers.appendValue(helper, "dstMacOut", obj.getDstMacOut());
        CodeHelpers.appendValue(helper, "dstPort", obj.getDstPort());
        CodeHelpers.appendValue(helper, "dstTos", obj.getDstTos());
        CodeHelpers.appendValue(helper, "engineId", obj.getEngineId());
        CodeHelpers.appendValue(helper, "engineType", obj.getEngineType());
        CodeHelpers.appendValue(helper, "firstSwitched", obj.getFirstSwitched());
        CodeHelpers.appendValue(helper, "flowActiveTout", obj.getFlowActiveTout());
        CodeHelpers.appendValue(helper, "flowId", obj.getFlowId());
        CodeHelpers.appendValue(helper, "flowInactiveTout", obj.getFlowInactiveTout());
        CodeHelpers.appendValue(helper, "flowSamplerId", obj.getFlowSamplerId());
        CodeHelpers.appendValue(helper, "flowSamplerMode", obj.getFlowSamplerMode());
        CodeHelpers.appendValue(helper, "flowSamplerRandom", obj.getFlowSamplerRandom());
        CodeHelpers.appendValue(helper, "flows", obj.getFlows());
        CodeHelpers.appendValue(helper, "forwardingStatus", obj.getForwardingStatus());
        CodeHelpers.appendValue(helper, "fragOffset", obj.getFragOffset());
        CodeHelpers.appendValue(helper, "icmpType", obj.getIcmpType());
        CodeHelpers.appendValue(helper, "ifDesc", obj.getIfDesc());
        CodeHelpers.appendValue(helper, "ifName", obj.getIfName());
        CodeHelpers.appendValue(helper, "igmpType", obj.getIgmpType());
        CodeHelpers.appendValue(helper, "ipVersion", obj.getIpVersion());
        CodeHelpers.appendValue(helper, "ipv4", obj.getIpv4());
        CodeHelpers.appendValue(helper, "ipv6", obj.getIpv6());
        CodeHelpers.appendValue(helper, "lastSwitched", obj.getLastSwitched());
        CodeHelpers.appendValue(helper, "layer2PktSection", obj.getLayer2PktSection());
        CodeHelpers.appendValue(helper, "maxPktLen", obj.getMaxPktLen());
        CodeHelpers.appendValue(helper, "maxTtl", obj.getMaxTtl());
        CodeHelpers.appendValue(helper, "minPktLen", obj.getMinPktLen());
        CodeHelpers.appendValue(helper, "minTtl", obj.getMinTtl());
        CodeHelpers.appendValue(helper, "mpls", obj.getMpls());
        CodeHelpers.appendValue(helper, "permanentFlow", obj.getPermanentFlow());
        CodeHelpers.appendValue(helper, "pktsIn", obj.getPktsIn());
        CodeHelpers.appendValue(helper, "pktsOut", obj.getPktsOut());
        CodeHelpers.appendValue(helper, "pktsOutMul", obj.getPktsOutMul());
        CodeHelpers.appendValue(helper, "postipDscp", obj.getPostipDscp());
        CodeHelpers.appendValue(helper, "protocol", obj.getProtocol());
        CodeHelpers.appendValue(helper, "replFactorMul", obj.getReplFactorMul());
        CodeHelpers.appendValue(helper, "samplerName", obj.getSamplerName());
        CodeHelpers.appendValue(helper, "samplingAlgorithm", obj.getSamplingAlgorithm());
        CodeHelpers.appendValue(helper, "samplingInterval", obj.getSamplingInterval());
        CodeHelpers.appendValue(helper, "snmpIn", obj.getSnmpIn());
        CodeHelpers.appendValue(helper, "snmpOut", obj.getSnmpOut());
        CodeHelpers.appendValue(helper, "srcMacIn", obj.getSrcMacIn());
        CodeHelpers.appendValue(helper, "srcMacOut", obj.getSrcMacOut());
        CodeHelpers.appendValue(helper, "srcPort", obj.getSrcPort());
        CodeHelpers.appendValue(helper, "srcTos", obj.getSrcTos());
        CodeHelpers.appendValue(helper, "tcpFlags", obj.getTcpFlags());
        CodeHelpers.appendValue(helper, "totBytesExp", obj.getTotBytesExp());
        CodeHelpers.appendValue(helper, "totFlowsExp", obj.getTotFlowsExp());
        CodeHelpers.appendValue(helper, "totPktsExp", obj.getTotPktsExp());
        CodeHelpers.appendValue(helper, "vlan", obj.getVlan());
        CodeHelpers.appendValue(helper, "augmentation", obj.augmentations().values());
        return helper.toString();
    }
    
    /**
     * Return flowId, or {@code null} if it is not present.
     * 
     * <pre>
     *     <code>
     *         Hash code of the 5-tuple identifying a Flow Data Record according to a certain
     *         export flow format (version 9)
     *     </code>
     * </pre>
     * 
     * @return {@code java.lang.Integer} flowId, or {@code null} if it is not present.
     *
     */
    Integer getFlowId();
    
    @Override
    FlowDataRecordKey key();

}

