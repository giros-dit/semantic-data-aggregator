package org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.common.flow.fields;
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
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFlowFields;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.PrefixLengthIpv6;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev130715.Ipv6Address;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev130715.Ipv6FlowLabel;
import org.opendaylight.yangtools.yang.binding.Augmentable;
import org.opendaylight.yangtools.yang.binding.ChildOf;
import org.opendaylight.yangtools.yang.binding.CodeHelpers;
import org.opendaylight.yangtools.yang.common.QName;
import org.opendaylight.yangtools.yang.common.Uint32;

/**
 * This container collects all metrics related to IPv6
 *
 * <p>
 * This class represents the following YANG schema fragment defined in module <b>netflow-v9</b>
 * <pre>
 * container ipv6 {
 *   leaf src-address {
 *     type inet:ipv6-address;
 *   }
 *   leaf dst-address {
 *     type inet:ipv6-address;
 *   }
 *   leaf src-mask {
 *     type prefix-length-ipv6;
 *   }
 *   leaf dst-mask {
 *     type prefix-length-ipv6;
 *   }
 *   leaf next-hop {
 *     type inet:ipv6-address;
 *   }
 *   leaf flow-label {
 *     type inet:ipv6-flow-label;
 *   }
 *   leaf opt-headers {
 *     type uint32;
 *   }
 * }
 * </pre>The schema path to identify an instance is
 * <i>netflow-v9/common-flow-fields/ipv6</i>
 *
 * <p>To create instances of this class use {@link Ipv6Builder}.
 * @see Ipv6Builder
 *
 */
@Generated("mdsal-binding-generator")
public interface Ipv6
    extends
    ChildOf<CommonFlowFields>,
    Augmentable<Ipv6>
{



    public static final @NonNull QName QNAME = $YangModuleInfoImpl.qnameOf("ipv6");

    @Override
    default Class<org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.common.flow.fields.Ipv6> implementedInterface() {
        return org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.common.flow.fields.Ipv6.class;
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
    static int bindingHashCode(final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.common.flow.fields.@NonNull Ipv6 obj) {
        final int prime = 31;
        int result = 1;
        result = prime * result + Objects.hashCode(obj.getDstAddress());
        result = prime * result + Objects.hashCode(obj.getDstMask());
        result = prime * result + Objects.hashCode(obj.getFlowLabel());
        result = prime * result + Objects.hashCode(obj.getNextHop());
        result = prime * result + Objects.hashCode(obj.getOptHeaders());
        result = prime * result + Objects.hashCode(obj.getSrcAddress());
        result = prime * result + Objects.hashCode(obj.getSrcMask());
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
    static boolean bindingEquals(final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.common.flow.fields.@NonNull Ipv6 thisObj, final Object obj) {
        if (thisObj == obj) {
            return true;
        }
        final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.common.flow.fields.Ipv6 other = CodeHelpers.checkCast(org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.common.flow.fields.Ipv6.class, obj);
        if (other == null) {
            return false;
        }
        if (!Objects.equals(thisObj.getDstMask(), other.getDstMask())) {
            return false;
        }
        if (!Objects.equals(thisObj.getFlowLabel(), other.getFlowLabel())) {
            return false;
        }
        if (!Objects.equals(thisObj.getOptHeaders(), other.getOptHeaders())) {
            return false;
        }
        if (!Objects.equals(thisObj.getSrcMask(), other.getSrcMask())) {
            return false;
        }
        if (!Objects.equals(thisObj.getDstAddress(), other.getDstAddress())) {
            return false;
        }
        if (!Objects.equals(thisObj.getNextHop(), other.getNextHop())) {
            return false;
        }
        if (!Objects.equals(thisObj.getSrcAddress(), other.getSrcAddress())) {
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
    static String bindingToString(final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.common.flow.fields.@NonNull Ipv6 obj) {
        final MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper("Ipv6");
        CodeHelpers.appendValue(helper, "dstAddress", obj.getDstAddress());
        CodeHelpers.appendValue(helper, "dstMask", obj.getDstMask());
        CodeHelpers.appendValue(helper, "flowLabel", obj.getFlowLabel());
        CodeHelpers.appendValue(helper, "nextHop", obj.getNextHop());
        CodeHelpers.appendValue(helper, "optHeaders", obj.getOptHeaders());
        CodeHelpers.appendValue(helper, "srcAddress", obj.getSrcAddress());
        CodeHelpers.appendValue(helper, "srcMask", obj.getSrcMask());
        CodeHelpers.appendValue(helper, "augmentation", obj.augmentations().values());
        return helper.toString();
    }
    
    /**
     * Return srcAddress, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         IPv6 source address
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev130715.Ipv6Address} srcAddress, or {@code null} if it is not present.
     *
     */
    Ipv6Address getSrcAddress();
    
    /**
     * Return dstAddress, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         IPv6 destination address
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev130715.Ipv6Address} dstAddress, or {@code null} if it is not present.
     *
     */
    Ipv6Address getDstAddress();
    
    /**
     * Return srcMask, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Length of the IPv6 source mask in contiguous bits
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.PrefixLengthIpv6} srcMask, or {@code null} if it is not present.
     *
     */
    PrefixLengthIpv6 getSrcMask();
    
    /**
     * Return dstMask, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Length of the IPv6 destination mask in contiguous bits
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.PrefixLengthIpv6} dstMask, or {@code null} if it is not present.
     *
     */
    PrefixLengthIpv6 getDstMask();
    
    /**
     * Return nextHop, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         IPv6 address of the next-hop router
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev130715.Ipv6Address} nextHop, or {@code null} if it is not present.
     *
     */
    Ipv6Address getNextHop();
    
    /**
     * Return flowLabel, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         IPv6 flow label as defined in RFC 2460
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev130715.Ipv6FlowLabel} flowLabel, or {@code null} if it is not present.
     *
     */
    Ipv6FlowLabel getFlowLabel();
    
    /**
     * Return optHeaders, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Bit-encoded field identifying IPv6 option headers found in the flow
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yangtools.yang.common.Uint32} optHeaders, or {@code null} if it is not present.
     *
     */
    Uint32 getOptHeaders();

}

