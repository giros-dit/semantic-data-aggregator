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
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev130715.AsNumber;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev130715.Ipv4Address;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev130715.Ipv6Address;
import org.opendaylight.yangtools.yang.binding.Augmentable;
import org.opendaylight.yangtools.yang.binding.ChildOf;
import org.opendaylight.yangtools.yang.binding.CodeHelpers;
import org.opendaylight.yangtools.yang.common.QName;
import org.opendaylight.yangtools.yang.common.Uint32;

/**
 * This container collects all the metrics associated with BGP (Border Gateway 
 * Protocol)
 *
 * <p>
 * This class represents the following YANG schema fragment defined in module <b>netflow-v9</b>
 * <pre>
 * container bgp {
 *   leaf src-as {
 *     type inet:as-number;
 *   }
 *   leaf dst-as {
 *     type inet:as-number;
 *   }
 *   leaf next-hop {
 *     type inet:ipv4-address;
 *   }
 *   leaf next-hop-ipv6 {
 *     type inet:ipv6-address;
 *   }
 *   leaf src-traffic-id {
 *     type uint32;
 *   }
 *   leaf dst-traffic-id {
 *     type uint32;
 *   }
 * }
 * </pre>The schema path to identify an instance is
 * <i>netflow-v9/common-flow-fields/bgp</i>
 *
 * <p>To create instances of this class use {@link BgpBuilder}.
 * @see BgpBuilder
 *
 */
@Generated("mdsal-binding-generator")
public interface Bgp
    extends
    ChildOf<CommonFlowFields>,
    Augmentable<Bgp>
{



    public static final @NonNull QName QNAME = $YangModuleInfoImpl.qnameOf("bgp");

    @Override
    default Class<org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.common.flow.fields.Bgp> implementedInterface() {
        return org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.common.flow.fields.Bgp.class;
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
    static int bindingHashCode(final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.common.flow.fields.@NonNull Bgp obj) {
        final int prime = 31;
        int result = 1;
        result = prime * result + Objects.hashCode(obj.getDstAs());
        result = prime * result + Objects.hashCode(obj.getDstTrafficId());
        result = prime * result + Objects.hashCode(obj.getNextHop());
        result = prime * result + Objects.hashCode(obj.getNextHopIpv6());
        result = prime * result + Objects.hashCode(obj.getSrcAs());
        result = prime * result + Objects.hashCode(obj.getSrcTrafficId());
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
    static boolean bindingEquals(final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.common.flow.fields.@NonNull Bgp thisObj, final Object obj) {
        if (thisObj == obj) {
            return true;
        }
        final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.common.flow.fields.Bgp other = CodeHelpers.checkCast(org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.common.flow.fields.Bgp.class, obj);
        if (other == null) {
            return false;
        }
        if (!Objects.equals(thisObj.getDstAs(), other.getDstAs())) {
            return false;
        }
        if (!Objects.equals(thisObj.getDstTrafficId(), other.getDstTrafficId())) {
            return false;
        }
        if (!Objects.equals(thisObj.getSrcAs(), other.getSrcAs())) {
            return false;
        }
        if (!Objects.equals(thisObj.getSrcTrafficId(), other.getSrcTrafficId())) {
            return false;
        }
        if (!Objects.equals(thisObj.getNextHop(), other.getNextHop())) {
            return false;
        }
        if (!Objects.equals(thisObj.getNextHopIpv6(), other.getNextHopIpv6())) {
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
    static String bindingToString(final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.common.flow.fields.@NonNull Bgp obj) {
        final MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper("Bgp");
        CodeHelpers.appendValue(helper, "dstAs", obj.getDstAs());
        CodeHelpers.appendValue(helper, "dstTrafficId", obj.getDstTrafficId());
        CodeHelpers.appendValue(helper, "nextHop", obj.getNextHop());
        CodeHelpers.appendValue(helper, "nextHopIpv6", obj.getNextHopIpv6());
        CodeHelpers.appendValue(helper, "srcAs", obj.getSrcAs());
        CodeHelpers.appendValue(helper, "srcTrafficId", obj.getSrcTrafficId());
        CodeHelpers.appendValue(helper, "augmentation", obj.augmentations().values());
        return helper.toString();
    }
    
    /**
     * Return srcAs, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Source BGP autonomous system number
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev130715.AsNumber} srcAs, or {@code null} if it is not present.
     *
     */
    AsNumber getSrcAs();
    
    /**
     * Return dstAs, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Destination BGP autonomous system number
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev130715.AsNumber} dstAs, or {@code null} if it is not present.
     *
     */
    AsNumber getDstAs();
    
    /**
     * Return nextHop, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Next-hop router's IPv4 in the BGP domain
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev130715.Ipv4Address} nextHop, or {@code null} if it is not present.
     *
     */
    Ipv4Address getNextHop();
    
    /**
     * Return nextHopIpv6, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Next-hop router's IPv6 in the BGP domain
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev130715.Ipv6Address} nextHopIpv6, or {@code null} if it is not present.
     *
     */
    Ipv6Address getNextHopIpv6();
    
    /**
     * Return srcTrafficId, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         BGP Policy Accounting Source Traffic Index
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yangtools.yang.common.Uint32} srcTrafficId, or {@code null} if it is not present.
     *
     */
    Uint32 getSrcTrafficId();
    
    /**
     * Return dstTrafficId, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         BGP Policy Accounting Destination Traffic Index
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yangtools.yang.common.Uint32} dstTrafficId, or {@code null} if it is not present.
     *
     */
    Uint32 getDstTrafficId();

}

