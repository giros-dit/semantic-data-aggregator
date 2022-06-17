package org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008;
import com.google.common.base.MoreObjects;
import java.lang.Class;
import java.lang.NullPointerException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.util.Objects;
import javax.annotation.processing.Generated;
import org.eclipse.jdt.annotation.NonNull;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.netflow.CollectorGoflow2;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.netflow.ExportPacket;
import org.opendaylight.yangtools.yang.binding.Augmentable;
import org.opendaylight.yangtools.yang.binding.ChildOf;
import org.opendaylight.yangtools.yang.binding.CodeHelpers;
import org.opendaylight.yangtools.yang.common.QName;

/**
 * This container collects all information related to the NetFlow collecting or 
 * exporting process
 *
 * <p>
 * This class represents the following YANG schema fragment defined in module <b>netflow-v9</b>
 * <pre>
 * container netflow {
 *   config false;
 *   container collector-goflow2 {
 *     leaf time-received {
 *       type yang:timestamp;
 *       units seconds;
 *     }
 *     leaf sampler-address {
 *       type inet:ipv4-address;
 *     }
 *     leaf sampler-address-ipv6 {
 *       type inet:ipv6-address;
 *     }
 *   }
 *   container export-packet {
 *     leaf sequence-number {
 *       type yang:counter32;
 *     }
 *     leaf count {
 *       type uint16;
 *     }
 *     leaf system-uptime {
 *       type yang:timestamp;
 *       units milliseconds;
 *     }
 *     leaf unix-seconds {
 *       type yang:timestamp;
 *       units seconds;
 *     }
 *     leaf source-id {
 *       type uint32;
 *     }
 *     list flow-data-record {
 *       ordered-by user;
 *       key flow-id;
 *       leaf flow-id {
 *         type int32;
 *       }
 *       uses common-flow-fields;
 *     }
 *   }
 * }
 * </pre>The schema path to identify an instance is
 * <i>netflow-v9/netflow</i>
 *
 * <p>To create instances of this class use {@link NetflowBuilder}.
 * @see NetflowBuilder
 *
 */
@Generated("mdsal-binding-generator")
public interface Netflow
    extends
    ChildOf<NetflowV9Data>,
    Augmentable<Netflow>
{



    public static final @NonNull QName QNAME = $YangModuleInfoImpl.qnameOf("netflow");

    @Override
    default Class<org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.Netflow> implementedInterface() {
        return org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.Netflow.class;
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
    static int bindingHashCode(final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.@NonNull Netflow obj) {
        final int prime = 31;
        int result = 1;
        result = prime * result + Objects.hashCode(obj.getCollectorGoflow2());
        result = prime * result + Objects.hashCode(obj.getExportPacket());
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
    static boolean bindingEquals(final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.@NonNull Netflow thisObj, final Object obj) {
        if (thisObj == obj) {
            return true;
        }
        final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.Netflow other = CodeHelpers.checkCast(org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.Netflow.class, obj);
        if (other == null) {
            return false;
        }
        if (!Objects.equals(thisObj.getCollectorGoflow2(), other.getCollectorGoflow2())) {
            return false;
        }
        if (!Objects.equals(thisObj.getExportPacket(), other.getExportPacket())) {
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
    static String bindingToString(final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.@NonNull Netflow obj) {
        final MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper("Netflow");
        CodeHelpers.appendValue(helper, "collectorGoflow2", obj.getCollectorGoflow2());
        CodeHelpers.appendValue(helper, "exportPacket", obj.getExportPacket());
        CodeHelpers.appendValue(helper, "augmentation", obj.augmentations().values());
        return helper.toString();
    }
    
    /**
     * Return collectorGoflow2, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Within this container those data that are specific to the Goflow2 collector are
     *         included
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.netflow.CollectorGoflow2} collectorGoflow2, or {@code null} if it is not present.
     *
     */
    CollectorGoflow2 getCollectorGoflow2();
    
    /**
     * Return exportPacket, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         This container collects all fields that appear in the NetFlow Export Packet
     *         header and possible fields of each Flow Data Record that is transmitted in this
     *         packet The names and semantics of the fields used follow the Cisco white paper
     *         (mentioned in the references section)
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.netflow.ExportPacket} exportPacket, or {@code null} if it is not present.
     *
     */
    ExportPacket getExportPacket();

}

