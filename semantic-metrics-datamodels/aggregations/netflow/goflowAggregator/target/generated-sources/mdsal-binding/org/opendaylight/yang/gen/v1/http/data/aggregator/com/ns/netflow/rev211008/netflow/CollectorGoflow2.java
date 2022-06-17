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
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.Netflow;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev130715.Ipv4Address;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev130715.Ipv6Address;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715.Timestamp;
import org.opendaylight.yangtools.yang.binding.Augmentable;
import org.opendaylight.yangtools.yang.binding.ChildOf;
import org.opendaylight.yangtools.yang.binding.CodeHelpers;
import org.opendaylight.yangtools.yang.common.QName;

/**
 * Within this container those data that are specific to the Goflow2 collector are 
 * included
 *
 * <p>
 * This class represents the following YANG schema fragment defined in module <b>netflow-v9</b>
 * <pre>
 * container collector-goflow2 {
 *   leaf time-received {
 *     type yang:timestamp;
 *     units seconds;
 *   }
 *   leaf sampler-address {
 *     type inet:ipv4-address;
 *   }
 *   leaf sampler-address-ipv6 {
 *     type inet:ipv6-address;
 *   }
 * }
 * </pre>The schema path to identify an instance is
 * <i>netflow-v9/netflow/collector-goflow2</i>
 *
 * <p>To create instances of this class use {@link CollectorGoflow2Builder}.
 * @see CollectorGoflow2Builder
 *
 */
@Generated("mdsal-binding-generator")
public interface CollectorGoflow2
    extends
    ChildOf<Netflow>,
    Augmentable<CollectorGoflow2>
{



    public static final @NonNull QName QNAME = $YangModuleInfoImpl.qnameOf("collector-goflow2");

    @Override
    default Class<org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.netflow.CollectorGoflow2> implementedInterface() {
        return org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.netflow.CollectorGoflow2.class;
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
    static int bindingHashCode(final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.netflow.@NonNull CollectorGoflow2 obj) {
        final int prime = 31;
        int result = 1;
        result = prime * result + Objects.hashCode(obj.getSamplerAddress());
        result = prime * result + Objects.hashCode(obj.getSamplerAddressIpv6());
        result = prime * result + Objects.hashCode(obj.getTimeReceived());
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
    static boolean bindingEquals(final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.netflow.@NonNull CollectorGoflow2 thisObj, final Object obj) {
        if (thisObj == obj) {
            return true;
        }
        final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.netflow.CollectorGoflow2 other = CodeHelpers.checkCast(org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.netflow.CollectorGoflow2.class, obj);
        if (other == null) {
            return false;
        }
        if (!Objects.equals(thisObj.getTimeReceived(), other.getTimeReceived())) {
            return false;
        }
        if (!Objects.equals(thisObj.getSamplerAddress(), other.getSamplerAddress())) {
            return false;
        }
        if (!Objects.equals(thisObj.getSamplerAddressIpv6(), other.getSamplerAddressIpv6())) {
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
    static String bindingToString(final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.netflow.@NonNull CollectorGoflow2 obj) {
        final MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper("CollectorGoflow2");
        CodeHelpers.appendValue(helper, "samplerAddress", obj.getSamplerAddress());
        CodeHelpers.appendValue(helper, "samplerAddressIpv6", obj.getSamplerAddressIpv6());
        CodeHelpers.appendValue(helper, "timeReceived", obj.getTimeReceived());
        CodeHelpers.appendValue(helper, "augmentation", obj.augmentations().values());
        return helper.toString();
    }
    
    /**
     * Return timeReceived, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Timestamp of when a NetFlow Export Packet was received at the collector
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715.Timestamp} timeReceived, or {@code null} if it is not present.
     *
     */
    Timestamp getTimeReceived();
    
    /**
     * Return samplerAddress, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         IPv4 address of the device that generated the NetFlow Export Packet, i.e.
     *         Netflow Exporter
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev130715.Ipv4Address} samplerAddress, or {@code null} if it is not present.
     *
     */
    Ipv4Address getSamplerAddress();
    
    /**
     * Return samplerAddressIpv6, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         IPv6 address of the device that generated the NetFlow Export Packet, i.e.
     *         Netflow Exporter
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev130715.Ipv6Address} samplerAddressIpv6, or {@code null} if it is not present.
     *
     */
    Ipv6Address getSamplerAddressIpv6();

}

