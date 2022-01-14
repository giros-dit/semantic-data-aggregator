package org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.netflow;
import com.google.common.base.MoreObjects;
import java.lang.Class;
import java.lang.NullPointerException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.util.Objects;
import javax.annotation.processing.Generated;
import org.eclipse.jdt.annotation.NonNull;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.$YangModuleInfoImpl;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFields;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.Netflow;
import org.opendaylight.yangtools.yang.binding.Augmentable;
import org.opendaylight.yangtools.yang.binding.ChildOf;
import org.opendaylight.yangtools.yang.binding.CodeHelpers;
import org.opendaylight.yangtools.yang.binding.Identifiable;
import org.opendaylight.yangtools.yang.common.QName;

/**
 * This list contains fields of a Netflow flow record that is transmitted by the 
 * Exporting Process or received by the Collecting Process.
 *
 * <p>
 * This class represents the following YANG schema fragment defined in module <b>netflow-v9</b>
 * <pre>
 * list fields-flow-record {
 *   ordered-by user;
 *   key name;
 *   leaf name {
 *     type string;
 *   }
 *   uses common-fields;
 * }
 * </pre>The schema path to identify an instance is
 * <i>netflow-v9/netflow/fields-flow-record</i>
 *
 * <p>To create instances of this class use {@link FieldsFlowRecordBuilder}.
 * @see FieldsFlowRecordBuilder
 * @see FieldsFlowRecordKey
 *
 */
@Generated("mdsal-binding-generator")
public interface FieldsFlowRecord
    extends
    ChildOf<Netflow>,
    Augmentable<FieldsFlowRecord>,
    CommonFields,
    Identifiable<FieldsFlowRecordKey>
{



    public static final @NonNull QName QNAME = $YangModuleInfoImpl.qnameOf("fields-flow-record");

    @Override
    default Class<org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.netflow.FieldsFlowRecord> implementedInterface() {
        return org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.netflow.FieldsFlowRecord.class;
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
    static int bindingHashCode(final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.netflow.@NonNull FieldsFlowRecord obj) {
        final int prime = 31;
        int result = 1;
        result = prime * result + Objects.hashCode(obj.getBgpNextHop());
        result = prime * result + Objects.hashCode(obj.getBgpNextHopIpv6());
        result = prime * result + Objects.hashCode(obj.getBytesIn());
        result = prime * result + Objects.hashCode(obj.getBytesMul());
        result = prime * result + Objects.hashCode(obj.getBytesOut());
        result = prime * result + Objects.hashCode(obj.getDirection());
        result = prime * result + Objects.hashCode(obj.getDstAddress());
        result = prime * result + Objects.hashCode(obj.getDstAddressIpv6());
        result = prime * result + Objects.hashCode(obj.getDstAs());
        result = prime * result + Objects.hashCode(obj.getDstMac());
        result = prime * result + Objects.hashCode(obj.getDstMask());
        result = prime * result + Objects.hashCode(obj.getDstMaskIpv6());
        result = prime * result + Objects.hashCode(obj.getDstPort());
        result = prime * result + Objects.hashCode(obj.getDstTos());
        result = prime * result + Objects.hashCode(obj.getDstVlan());
        result = prime * result + Objects.hashCode(obj.getFirstSwitched());
        result = prime * result + Objects.hashCode(obj.getFlowLabelIpv6());
        result = prime * result + Objects.hashCode(obj.getFlows());
        result = prime * result + Objects.hashCode(obj.getIcmpType());
        result = prime * result + Objects.hashCode(obj.getIgmpType());
        result = prime * result + Objects.hashCode(obj.getIpVersion());
        result = prime * result + Objects.hashCode(obj.getLastSwitched());
        result = prime * result + Objects.hashCode(obj.getName());
        result = prime * result + Objects.hashCode(obj.getNextHop());
        result = prime * result + Objects.hashCode(obj.getNextHopIpv6());
        result = prime * result + Objects.hashCode(obj.getOptHeadersIpv6());
        result = prime * result + Objects.hashCode(obj.getPktsIn());
        result = prime * result + Objects.hashCode(obj.getPktsMul());
        result = prime * result + Objects.hashCode(obj.getPktsOut());
        result = prime * result + Objects.hashCode(obj.getProtocol());
        result = prime * result + Objects.hashCode(obj.getSnmpIn());
        result = prime * result + Objects.hashCode(obj.getSnmpOut());
        result = prime * result + Objects.hashCode(obj.getSrcAddress());
        result = prime * result + Objects.hashCode(obj.getSrcAddressIpv6());
        result = prime * result + Objects.hashCode(obj.getSrcAs());
        result = prime * result + Objects.hashCode(obj.getSrcMac());
        result = prime * result + Objects.hashCode(obj.getSrcMask());
        result = prime * result + Objects.hashCode(obj.getSrcMaskIpv6());
        result = prime * result + Objects.hashCode(obj.getSrcPort());
        result = prime * result + Objects.hashCode(obj.getSrcTos());
        result = prime * result + Objects.hashCode(obj.getSrcVlan());
        result = prime * result + Objects.hashCode(obj.getTcpFlags());
        result = prime * result + Objects.hashCode(obj.getTotBytesExp());
        result = prime * result + Objects.hashCode(obj.getTotFlowsExp());
        result = prime * result + Objects.hashCode(obj.getTotPktsExp());
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
    static boolean bindingEquals(final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.netflow.@NonNull FieldsFlowRecord thisObj, final Object obj) {
        if (thisObj == obj) {
            return true;
        }
        final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.netflow.FieldsFlowRecord other = CodeHelpers.checkCast(org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.netflow.FieldsFlowRecord.class, obj);
        if (other == null) {
            return false;
        }
        if (!Objects.equals(thisObj.getBytesIn(), other.getBytesIn())) {
            return false;
        }
        if (!Objects.equals(thisObj.getBytesMul(), other.getBytesMul())) {
            return false;
        }
        if (!Objects.equals(thisObj.getBytesOut(), other.getBytesOut())) {
            return false;
        }
        if (!Objects.equals(thisObj.getDstAs(), other.getDstAs())) {
            return false;
        }
        if (!Objects.equals(thisObj.getDstMask(), other.getDstMask())) {
            return false;
        }
        if (!Objects.equals(thisObj.getDstMaskIpv6(), other.getDstMaskIpv6())) {
            return false;
        }
        if (!Objects.equals(thisObj.getDstPort(), other.getDstPort())) {
            return false;
        }
        if (!Objects.equals(thisObj.getDstTos(), other.getDstTos())) {
            return false;
        }
        if (!Objects.equals(thisObj.getDstVlan(), other.getDstVlan())) {
            return false;
        }
        if (!Objects.equals(thisObj.getFirstSwitched(), other.getFirstSwitched())) {
            return false;
        }
        if (!Objects.equals(thisObj.getFlowLabelIpv6(), other.getFlowLabelIpv6())) {
            return false;
        }
        if (!Objects.equals(thisObj.getFlows(), other.getFlows())) {
            return false;
        }
        if (!Objects.equals(thisObj.getIcmpType(), other.getIcmpType())) {
            return false;
        }
        if (!Objects.equals(thisObj.getIgmpType(), other.getIgmpType())) {
            return false;
        }
        if (!Objects.equals(thisObj.getLastSwitched(), other.getLastSwitched())) {
            return false;
        }
        if (!Objects.equals(thisObj.getOptHeadersIpv6(), other.getOptHeadersIpv6())) {
            return false;
        }
        if (!Objects.equals(thisObj.getPktsIn(), other.getPktsIn())) {
            return false;
        }
        if (!Objects.equals(thisObj.getPktsMul(), other.getPktsMul())) {
            return false;
        }
        if (!Objects.equals(thisObj.getPktsOut(), other.getPktsOut())) {
            return false;
        }
        if (!Objects.equals(thisObj.getSnmpIn(), other.getSnmpIn())) {
            return false;
        }
        if (!Objects.equals(thisObj.getSnmpOut(), other.getSnmpOut())) {
            return false;
        }
        if (!Objects.equals(thisObj.getSrcAs(), other.getSrcAs())) {
            return false;
        }
        if (!Objects.equals(thisObj.getSrcMask(), other.getSrcMask())) {
            return false;
        }
        if (!Objects.equals(thisObj.getSrcMaskIpv6(), other.getSrcMaskIpv6())) {
            return false;
        }
        if (!Objects.equals(thisObj.getSrcPort(), other.getSrcPort())) {
            return false;
        }
        if (!Objects.equals(thisObj.getSrcTos(), other.getSrcTos())) {
            return false;
        }
        if (!Objects.equals(thisObj.getSrcVlan(), other.getSrcVlan())) {
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
        if (!Objects.equals(thisObj.getBgpNextHop(), other.getBgpNextHop())) {
            return false;
        }
        if (!Objects.equals(thisObj.getBgpNextHopIpv6(), other.getBgpNextHopIpv6())) {
            return false;
        }
        if (!Objects.equals(thisObj.getDstAddress(), other.getDstAddress())) {
            return false;
        }
        if (!Objects.equals(thisObj.getDstAddressIpv6(), other.getDstAddressIpv6())) {
            return false;
        }
        if (!Objects.equals(thisObj.getDstMac(), other.getDstMac())) {
            return false;
        }
        if (!Objects.equals(thisObj.getName(), other.getName())) {
            return false;
        }
        if (!Objects.equals(thisObj.getNextHop(), other.getNextHop())) {
            return false;
        }
        if (!Objects.equals(thisObj.getNextHopIpv6(), other.getNextHopIpv6())) {
            return false;
        }
        if (!Objects.equals(thisObj.getSrcAddress(), other.getSrcAddress())) {
            return false;
        }
        if (!Objects.equals(thisObj.getSrcAddressIpv6(), other.getSrcAddressIpv6())) {
            return false;
        }
        if (!Objects.equals(thisObj.getSrcMac(), other.getSrcMac())) {
            return false;
        }
        if (!Objects.equals(thisObj.getTcpFlags(), other.getTcpFlags())) {
            return false;
        }
        if (!Objects.equals(thisObj.getDirection(), other.getDirection())) {
            return false;
        }
        if (!Objects.equals(thisObj.getIpVersion(), other.getIpVersion())) {
            return false;
        }
        if (!Objects.equals(thisObj.getProtocol(), other.getProtocol())) {
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
    static String bindingToString(final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.netflow.@NonNull FieldsFlowRecord obj) {
        final MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper("FieldsFlowRecord");
        CodeHelpers.appendValue(helper, "bgpNextHop", obj.getBgpNextHop());
        CodeHelpers.appendValue(helper, "bgpNextHopIpv6", obj.getBgpNextHopIpv6());
        CodeHelpers.appendValue(helper, "bytesIn", obj.getBytesIn());
        CodeHelpers.appendValue(helper, "bytesMul", obj.getBytesMul());
        CodeHelpers.appendValue(helper, "bytesOut", obj.getBytesOut());
        CodeHelpers.appendValue(helper, "direction", obj.getDirection());
        CodeHelpers.appendValue(helper, "dstAddress", obj.getDstAddress());
        CodeHelpers.appendValue(helper, "dstAddressIpv6", obj.getDstAddressIpv6());
        CodeHelpers.appendValue(helper, "dstAs", obj.getDstAs());
        CodeHelpers.appendValue(helper, "dstMac", obj.getDstMac());
        CodeHelpers.appendValue(helper, "dstMask", obj.getDstMask());
        CodeHelpers.appendValue(helper, "dstMaskIpv6", obj.getDstMaskIpv6());
        CodeHelpers.appendValue(helper, "dstPort", obj.getDstPort());
        CodeHelpers.appendValue(helper, "dstTos", obj.getDstTos());
        CodeHelpers.appendValue(helper, "dstVlan", obj.getDstVlan());
        CodeHelpers.appendValue(helper, "firstSwitched", obj.getFirstSwitched());
        CodeHelpers.appendValue(helper, "flowLabelIpv6", obj.getFlowLabelIpv6());
        CodeHelpers.appendValue(helper, "flows", obj.getFlows());
        CodeHelpers.appendValue(helper, "icmpType", obj.getIcmpType());
        CodeHelpers.appendValue(helper, "igmpType", obj.getIgmpType());
        CodeHelpers.appendValue(helper, "ipVersion", obj.getIpVersion());
        CodeHelpers.appendValue(helper, "lastSwitched", obj.getLastSwitched());
        CodeHelpers.appendValue(helper, "name", obj.getName());
        CodeHelpers.appendValue(helper, "nextHop", obj.getNextHop());
        CodeHelpers.appendValue(helper, "nextHopIpv6", obj.getNextHopIpv6());
        CodeHelpers.appendValue(helper, "optHeadersIpv6", obj.getOptHeadersIpv6());
        CodeHelpers.appendValue(helper, "pktsIn", obj.getPktsIn());
        CodeHelpers.appendValue(helper, "pktsMul", obj.getPktsMul());
        CodeHelpers.appendValue(helper, "pktsOut", obj.getPktsOut());
        CodeHelpers.appendValue(helper, "protocol", obj.getProtocol());
        CodeHelpers.appendValue(helper, "snmpIn", obj.getSnmpIn());
        CodeHelpers.appendValue(helper, "snmpOut", obj.getSnmpOut());
        CodeHelpers.appendValue(helper, "srcAddress", obj.getSrcAddress());
        CodeHelpers.appendValue(helper, "srcAddressIpv6", obj.getSrcAddressIpv6());
        CodeHelpers.appendValue(helper, "srcAs", obj.getSrcAs());
        CodeHelpers.appendValue(helper, "srcMac", obj.getSrcMac());
        CodeHelpers.appendValue(helper, "srcMask", obj.getSrcMask());
        CodeHelpers.appendValue(helper, "srcMaskIpv6", obj.getSrcMaskIpv6());
        CodeHelpers.appendValue(helper, "srcPort", obj.getSrcPort());
        CodeHelpers.appendValue(helper, "srcTos", obj.getSrcTos());
        CodeHelpers.appendValue(helper, "srcVlan", obj.getSrcVlan());
        CodeHelpers.appendValue(helper, "tcpFlags", obj.getTcpFlags());
        CodeHelpers.appendValue(helper, "totBytesExp", obj.getTotBytesExp());
        CodeHelpers.appendValue(helper, "totFlowsExp", obj.getTotFlowsExp());
        CodeHelpers.appendValue(helper, "totPktsExp", obj.getTotPktsExp());
        CodeHelpers.appendValue(helper, "augmentation", obj.augmentations().values());
        return helper.toString();
    }
    
    /**
     * Return name, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         The name that identifies the fields of a Netflow flow record according to a
     *         certain export flow format.
     *     </code>
     * </pre>
     *
     * @return {@code java.lang.String} name, or {@code null} if it is not present.
     *
     */
    String getName();
    
    @Override
    FieldsFlowRecordKey key();

}

