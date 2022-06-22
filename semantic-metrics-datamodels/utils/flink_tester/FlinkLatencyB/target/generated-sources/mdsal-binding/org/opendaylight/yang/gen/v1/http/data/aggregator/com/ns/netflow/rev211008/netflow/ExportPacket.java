package org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.netflow;
import com.google.common.base.MoreObjects;
import java.lang.Class;
import java.lang.NullPointerException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.util.List;
import java.util.Objects;
import javax.annotation.processing.Generated;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.$YangModuleInfoImpl;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.Netflow;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.netflow.export.packet.FlowDataRecord;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715.Counter32;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715.Timestamp;
import org.opendaylight.yangtools.yang.binding.Augmentable;
import org.opendaylight.yangtools.yang.binding.ChildOf;
import org.opendaylight.yangtools.yang.binding.CodeHelpers;
import org.opendaylight.yangtools.yang.common.QName;
import org.opendaylight.yangtools.yang.common.Uint16;
import org.opendaylight.yangtools.yang.common.Uint32;

/**
 * This container collects all fields that appear in the NetFlow Export Packet 
 * header and possible fields of each Flow Data Record that is transmitted in this 
 * packet The names and semantics of the fields used follow the Cisco white paper 
 * (mentioned in the references section)
 * 
 * <p>
 * This class represents the following YANG schema fragment defined in module <b>netflow-v9</b>
 * <pre>
 * container export-packet {
 *   leaf sequence-number {
 *     type yang:counter32;
 *   }
 *   leaf count {
 *     type uint16;
 *   }
 *   leaf system-uptime {
 *     type yang:timestamp;
 *     units milliseconds;
 *   }
 *   leaf unix-seconds {
 *     type yang:timestamp;
 *     units seconds;
 *   }
 *   leaf source-id {
 *     type uint32;
 *   }
 *   list flow-data-record {
 *     ordered-by user;
 *     key flow-id;
 *     leaf flow-id {
 *       type int32;
 *     }
 *     uses common-flow-fields;
 *   }
 * }
 * </pre>The schema path to identify an instance is
 * <i>netflow-v9/netflow/export-packet</i>
 * 
 * <p>To create instances of this class use {@link ExportPacketBuilder}.
 * @see ExportPacketBuilder
 *
 */
@Generated("mdsal-binding-generator")
public interface ExportPacket
    extends
    ChildOf<Netflow>,
    Augmentable<ExportPacket>
{



    public static final @NonNull QName QNAME = $YangModuleInfoImpl.qnameOf("export-packet");

    @Override
    default Class<org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.netflow.ExportPacket> implementedInterface() {
        return org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.netflow.ExportPacket.class;
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
    static int bindingHashCode(final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.netflow.@NonNull ExportPacket obj) {
        final int prime = 31;
        int result = 1;
        result = prime * result + Objects.hashCode(obj.getCount());
        result = prime * result + Objects.hashCode(obj.getFlowDataRecord());
        result = prime * result + Objects.hashCode(obj.getSequenceNumber());
        result = prime * result + Objects.hashCode(obj.getSourceId());
        result = prime * result + Objects.hashCode(obj.getSystemUptime());
        result = prime * result + Objects.hashCode(obj.getUnixSeconds());
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
    static boolean bindingEquals(final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.netflow.@NonNull ExportPacket thisObj, final Object obj) {
        if (thisObj == obj) {
            return true;
        }
        final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.netflow.ExportPacket other = CodeHelpers.checkCast(org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.netflow.ExportPacket.class, obj);
        if (other == null) {
            return false;
        }
        if (!Objects.equals(thisObj.getCount(), other.getCount())) {
            return false;
        }
        if (!Objects.equals(thisObj.getSequenceNumber(), other.getSequenceNumber())) {
            return false;
        }
        if (!Objects.equals(thisObj.getSourceId(), other.getSourceId())) {
            return false;
        }
        if (!Objects.equals(thisObj.getSystemUptime(), other.getSystemUptime())) {
            return false;
        }
        if (!Objects.equals(thisObj.getUnixSeconds(), other.getUnixSeconds())) {
            return false;
        }
        if (!Objects.equals(thisObj.getFlowDataRecord(), other.getFlowDataRecord())) {
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
    static String bindingToString(final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.netflow.@NonNull ExportPacket obj) {
        final MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper("ExportPacket");
        CodeHelpers.appendValue(helper, "count", obj.getCount());
        CodeHelpers.appendValue(helper, "flowDataRecord", obj.getFlowDataRecord());
        CodeHelpers.appendValue(helper, "sequenceNumber", obj.getSequenceNumber());
        CodeHelpers.appendValue(helper, "sourceId", obj.getSourceId());
        CodeHelpers.appendValue(helper, "systemUptime", obj.getSystemUptime());
        CodeHelpers.appendValue(helper, "unixSeconds", obj.getUnixSeconds());
        CodeHelpers.appendValue(helper, "augmentation", obj.augmentations().values());
        return helper.toString();
    }
    
    /**
     * Return sequenceNumber, or {@code null} if it is not present.
     * 
     * <pre>
     *     <code>
     *         Incremental sequence counter of all Export Packets sent by this export device
     *     </code>
     * </pre>
     * 
     * @return {@code org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715.Counter32} sequenceNumber, or {@code null} if it is not present.
     *
     */
    Counter32 getSequenceNumber();
    
    /**
     * Return count, or {@code null} if it is not present.
     * 
     * <pre>
     *     <code>
     *         Number of FlowSet records (both template and data) contained within this packet
     *     </code>
     * </pre>
     * 
     * @return {@code org.opendaylight.yangtools.yang.common.Uint16} count, or {@code null} if it is not present.
     *
     */
    Uint16 getCount();
    
    /**
     * Return systemUptime, or {@code null} if it is not present.
     * 
     * <pre>
     *     <code>
     *         Time in milliseconds since this device was first booted
     *     </code>
     * </pre>
     * 
     * @return {@code org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715.Timestamp} systemUptime, or {@code null} if it is not present.
     *
     */
    Timestamp getSystemUptime();
    
    /**
     * Return unixSeconds, or {@code null} if it is not present.
     * 
     * <pre>
     *     <code>
     *         Seconds since 0000 Coordinated Universal Time (UTC) 1970 at which the Export
     *         Packet leaves the Exporter.
     *     </code>
     * </pre>
     * 
     * @return {@code org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715.Timestamp} unixSeconds, or {@code null} if it is not present.
     *
     */
    Timestamp getUnixSeconds();
    
    /**
     * Return sourceId, or {@code null} if it is not present.
     * 
     * <pre>
     *     <code>
     *         A 32-bit value that is used to guarantee uniqueness for all flows exported from
     *         a particular device. The format of this field is vendor specific. In the Cisco
     *         implementation, the first two bytes are reserved for future expansion, and will
     *         always be zero. Byte 3 provides uniqueness with respect to the routing engine on
     *         the exporting device. Byte 4 provides uniqueness with respect to the particular
     *         line card or Versatile Interface Processor on the exporting device
     *     </code>
     * </pre>
     * 
     * @return {@code org.opendaylight.yangtools.yang.common.Uint32} sourceId, or {@code null} if it is not present.
     *
     */
    Uint32 getSourceId();
    
    /**
     * Return flowDataRecord, or {@code null} if it is not present.
     * 
     * <pre>
     *     <code>
     *         This list contains all possible fields of a Flow Data Record sent in a Export
     *         Packet
     *     </code>
     * </pre>
     * 
     * @return {@code java.util.List} flowDataRecord, or {@code null} if it is not present.
     *
     */
    @Nullable List<FlowDataRecord> getFlowDataRecord();
    
    /**
     * Return flowDataRecord, or an empty list if it is not present.
     * 
     * @return {@code java.util.List} flowDataRecord, or an empty list if it is not present.
     *
     */
    default @NonNull List<FlowDataRecord> nonnullFlowDataRecord() {
        return CodeHelpers.nonnull(getFlowDataRecord());
    }

}

