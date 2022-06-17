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
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.PrefixLengthIpv4;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev130715.Ipv4Address;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev130715.Ipv4Prefix;
import org.opendaylight.yangtools.yang.binding.Augmentable;
import org.opendaylight.yangtools.yang.binding.ChildOf;
import org.opendaylight.yangtools.yang.binding.CodeHelpers;
import org.opendaylight.yangtools.yang.common.QName;
import org.opendaylight.yangtools.yang.common.Uint16;

/**
 * This container collects all metrics related to IPv4
 *
 * <p>
 * This class represents the following YANG schema fragment defined in module <b>netflow-v9</b>
 * <pre>
 * container ipv4 {
 *   leaf src-address {
 *     type inet:ipv4-address;
 *   }
 *   leaf dst-address {
 *     type inet:ipv4-address;
 *   }
 *   leaf src-mask {
 *     type prefix-length-ipv4;
 *   }
 *   leaf dst-mask {
 *     type prefix-length-ipv4;
 *   }
 *   leaf src-prefix {
 *     type inet:ipv4-prefix;
 *   }
 *   leaf dst-prefix {
 *     type inet:ipv4-prefix;
 *   }
 *   leaf next-hop {
 *     type inet:ipv4-address;
 *   }
 *   leaf identification {
 *     type uint16;
 *   }
 * }
 * </pre>The schema path to identify an instance is
 * <i>netflow-v9/common-flow-fields/ipv4</i>
 *
 * <p>To create instances of this class use {@link Ipv4Builder}.
 * @see Ipv4Builder
 *
 */
@Generated("mdsal-binding-generator")
public interface Ipv4
    extends
    ChildOf<CommonFlowFields>,
    Augmentable<Ipv4>
{



    public static final @NonNull QName QNAME = $YangModuleInfoImpl.qnameOf("ipv4");

    @Override
    default Class<org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.common.flow.fields.Ipv4> implementedInterface() {
        return org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.common.flow.fields.Ipv4.class;
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
    static int bindingHashCode(final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.common.flow.fields.@NonNull Ipv4 obj) {
        final int prime = 31;
        int result = 1;
        result = prime * result + Objects.hashCode(obj.getDstAddress());
        result = prime * result + Objects.hashCode(obj.getDstMask());
        result = prime * result + Objects.hashCode(obj.getDstPrefix());
        result = prime * result + Objects.hashCode(obj.getIdentification());
        result = prime * result + Objects.hashCode(obj.getNextHop());
        result = prime * result + Objects.hashCode(obj.getSrcAddress());
        result = prime * result + Objects.hashCode(obj.getSrcMask());
        result = prime * result + Objects.hashCode(obj.getSrcPrefix());
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
    static boolean bindingEquals(final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.common.flow.fields.@NonNull Ipv4 thisObj, final Object obj) {
        if (thisObj == obj) {
            return true;
        }
        final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.common.flow.fields.Ipv4 other = CodeHelpers.checkCast(org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.common.flow.fields.Ipv4.class, obj);
        if (other == null) {
            return false;
        }
        if (!Objects.equals(thisObj.getDstMask(), other.getDstMask())) {
            return false;
        }
        if (!Objects.equals(thisObj.getIdentification(), other.getIdentification())) {
            return false;
        }
        if (!Objects.equals(thisObj.getSrcMask(), other.getSrcMask())) {
            return false;
        }
        if (!Objects.equals(thisObj.getDstAddress(), other.getDstAddress())) {
            return false;
        }
        if (!Objects.equals(thisObj.getDstPrefix(), other.getDstPrefix())) {
            return false;
        }
        if (!Objects.equals(thisObj.getNextHop(), other.getNextHop())) {
            return false;
        }
        if (!Objects.equals(thisObj.getSrcAddress(), other.getSrcAddress())) {
            return false;
        }
        if (!Objects.equals(thisObj.getSrcPrefix(), other.getSrcPrefix())) {
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
    static String bindingToString(final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.common.flow.fields.@NonNull Ipv4 obj) {
        final MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper("Ipv4");
        CodeHelpers.appendValue(helper, "dstAddress", obj.getDstAddress());
        CodeHelpers.appendValue(helper, "dstMask", obj.getDstMask());
        CodeHelpers.appendValue(helper, "dstPrefix", obj.getDstPrefix());
        CodeHelpers.appendValue(helper, "identification", obj.getIdentification());
        CodeHelpers.appendValue(helper, "nextHop", obj.getNextHop());
        CodeHelpers.appendValue(helper, "srcAddress", obj.getSrcAddress());
        CodeHelpers.appendValue(helper, "srcMask", obj.getSrcMask());
        CodeHelpers.appendValue(helper, "srcPrefix", obj.getSrcPrefix());
        CodeHelpers.appendValue(helper, "augmentation", obj.augmentations().values());
        return helper.toString();
    }
    
    /**
     * Return srcAddress, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         IPv4 source address
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev130715.Ipv4Address} srcAddress, or {@code null} if it is not present.
     *
     */
    Ipv4Address getSrcAddress();
    
    /**
     * Return dstAddress, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         IPv4 destination address of the IP flow
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev130715.Ipv4Address} dstAddress, or {@code null} if it is not present.
     *
     */
    Ipv4Address getDstAddress();
    
    /**
     * Return srcMask, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         The number of contiguous bits in the source address subnet mask i.e.: the
     *         submask in slash notation
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.PrefixLengthIpv4} srcMask, or {@code null} if it is not present.
     *
     */
    PrefixLengthIpv4 getSrcMask();
    
    /**
     * Return dstMask, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         The number of contiguous bits in the destination address subnet mask i.e.: the
     *         submask in slash notation
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.PrefixLengthIpv4} dstMask, or {@code null} if it is not present.
     *
     */
    PrefixLengthIpv4 getDstMask();
    
    /**
     * Return srcPrefix, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         IPv4 source address prefix (specific for Catalyst architecture)
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev130715.Ipv4Prefix} srcPrefix, or {@code null} if it is not present.
     *
     */
    Ipv4Prefix getSrcPrefix();
    
    /**
     * Return dstPrefix, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         IPv4 destination address prefix (specific for Catalyst architecture)
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev130715.Ipv4Prefix} dstPrefix, or {@code null} if it is not present.
     *
     */
    Ipv4Prefix getDstPrefix();
    
    /**
     * Return nextHop, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         IPv4 address of the next-hop router
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev130715.Ipv4Address} nextHop, or {@code null} if it is not present.
     *
     */
    Ipv4Address getNextHop();
    
    /**
     * Return identification, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         The IP v4 identification field
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yangtools.yang.common.Uint16} identification, or {@code null} if it is not present.
     *
     */
    Uint16 getIdentification();

}

