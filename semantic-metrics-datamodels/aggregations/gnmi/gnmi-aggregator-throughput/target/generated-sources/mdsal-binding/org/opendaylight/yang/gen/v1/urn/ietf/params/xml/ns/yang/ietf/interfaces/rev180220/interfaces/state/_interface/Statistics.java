package org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.interfaces.rev180220.interfaces.state._interface;
import com.google.common.base.MoreObjects;
import java.lang.Class;
import java.lang.Deprecated;
import java.lang.NullPointerException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.util.Objects;
import javax.annotation.processing.Generated;
import org.eclipse.jdt.annotation.NonNull;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.interfaces.rev180220.$YangModuleInfoImpl;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.interfaces.rev180220.interfaces.state.Interface;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715.Counter32;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715.Counter64;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715.DateAndTime;
import org.opendaylight.yangtools.yang.binding.Augmentable;
import org.opendaylight.yangtools.yang.binding.ChildOf;
import org.opendaylight.yangtools.yang.binding.CodeHelpers;
import org.opendaylight.yangtools.yang.common.QName;

/**
 * A collection of interface-related statistics objects.
 *
 * <p>
 * This class represents the following YANG schema fragment defined in module <b>ietf-interfaces</b>
 * <pre>
 * container statistics {
 *   status deprecated;
 *   leaf discontinuity-time {
 *     type yang:date-and-time;
 *     status deprecated;
 *   }
 *   leaf in-octets {
 *     type yang:counter64;
 *     status deprecated;
 *   }
 *   leaf in-unicast-pkts {
 *     type yang:counter64;
 *     status deprecated;
 *   }
 *   leaf in-broadcast-pkts {
 *     type yang:counter64;
 *     status deprecated;
 *   }
 *   leaf in-multicast-pkts {
 *     type yang:counter64;
 *     status deprecated;
 *   }
 *   leaf in-discards {
 *     type yang:counter32;
 *     status deprecated;
 *   }
 *   leaf in-errors {
 *     type yang:counter32;
 *     status deprecated;
 *   }
 *   leaf in-unknown-protos {
 *     type yang:counter32;
 *     status deprecated;
 *   }
 *   leaf out-octets {
 *     type yang:counter64;
 *     status deprecated;
 *   }
 *   leaf out-unicast-pkts {
 *     type yang:counter64;
 *     status deprecated;
 *   }
 *   leaf out-broadcast-pkts {
 *     type yang:counter64;
 *     status deprecated;
 *   }
 *   leaf out-multicast-pkts {
 *     type yang:counter64;
 *     status deprecated;
 *   }
 *   leaf out-discards {
 *     type yang:counter32;
 *     status deprecated;
 *   }
 *   leaf out-errors {
 *     type yang:counter32;
 *     status deprecated;
 *   }
 * }
 * </pre>The schema path to identify an instance is
 * <i>ietf-interfaces/interfaces-state/interface/statistics</i>
 *
 * <p>To create instances of this class use {@link StatisticsBuilder}.
 * @see StatisticsBuilder
 *
 */
@Deprecated
@Generated("mdsal-binding-generator")
public interface Statistics
    extends
    ChildOf<Interface>,
    Augmentable<Statistics>
{



    public static final @NonNull QName QNAME = $YangModuleInfoImpl.qnameOf("statistics");

    @Override
    default Class<org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.interfaces.rev180220.interfaces.state._interface.Statistics> implementedInterface() {
        return org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.interfaces.rev180220.interfaces.state._interface.Statistics.class;
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
    static int bindingHashCode(final org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.interfaces.rev180220.interfaces.state._interface.@NonNull Statistics obj) {
        final int prime = 31;
        int result = 1;
        result = prime * result + Objects.hashCode(obj.getDiscontinuityTime());
        result = prime * result + Objects.hashCode(obj.getInBroadcastPkts());
        result = prime * result + Objects.hashCode(obj.getInDiscards());
        result = prime * result + Objects.hashCode(obj.getInErrors());
        result = prime * result + Objects.hashCode(obj.getInMulticastPkts());
        result = prime * result + Objects.hashCode(obj.getInOctets());
        result = prime * result + Objects.hashCode(obj.getInUnicastPkts());
        result = prime * result + Objects.hashCode(obj.getInUnknownProtos());
        result = prime * result + Objects.hashCode(obj.getOutBroadcastPkts());
        result = prime * result + Objects.hashCode(obj.getOutDiscards());
        result = prime * result + Objects.hashCode(obj.getOutErrors());
        result = prime * result + Objects.hashCode(obj.getOutMulticastPkts());
        result = prime * result + Objects.hashCode(obj.getOutOctets());
        result = prime * result + Objects.hashCode(obj.getOutUnicastPkts());
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
    static boolean bindingEquals(final org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.interfaces.rev180220.interfaces.state._interface.@NonNull Statistics thisObj, final Object obj) {
        if (thisObj == obj) {
            return true;
        }
        final org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.interfaces.rev180220.interfaces.state._interface.Statistics other = CodeHelpers.checkCast(org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.interfaces.rev180220.interfaces.state._interface.Statistics.class, obj);
        if (other == null) {
            return false;
        }
        if (!Objects.equals(thisObj.getInBroadcastPkts(), other.getInBroadcastPkts())) {
            return false;
        }
        if (!Objects.equals(thisObj.getInDiscards(), other.getInDiscards())) {
            return false;
        }
        if (!Objects.equals(thisObj.getInErrors(), other.getInErrors())) {
            return false;
        }
        if (!Objects.equals(thisObj.getInMulticastPkts(), other.getInMulticastPkts())) {
            return false;
        }
        if (!Objects.equals(thisObj.getInOctets(), other.getInOctets())) {
            return false;
        }
        if (!Objects.equals(thisObj.getInUnicastPkts(), other.getInUnicastPkts())) {
            return false;
        }
        if (!Objects.equals(thisObj.getInUnknownProtos(), other.getInUnknownProtos())) {
            return false;
        }
        if (!Objects.equals(thisObj.getOutBroadcastPkts(), other.getOutBroadcastPkts())) {
            return false;
        }
        if (!Objects.equals(thisObj.getOutDiscards(), other.getOutDiscards())) {
            return false;
        }
        if (!Objects.equals(thisObj.getOutErrors(), other.getOutErrors())) {
            return false;
        }
        if (!Objects.equals(thisObj.getOutMulticastPkts(), other.getOutMulticastPkts())) {
            return false;
        }
        if (!Objects.equals(thisObj.getOutOctets(), other.getOutOctets())) {
            return false;
        }
        if (!Objects.equals(thisObj.getOutUnicastPkts(), other.getOutUnicastPkts())) {
            return false;
        }
        if (!Objects.equals(thisObj.getDiscontinuityTime(), other.getDiscontinuityTime())) {
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
    static String bindingToString(final org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.interfaces.rev180220.interfaces.state._interface.@NonNull Statistics obj) {
        final MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper("Statistics");
        CodeHelpers.appendValue(helper, "discontinuityTime", obj.getDiscontinuityTime());
        CodeHelpers.appendValue(helper, "inBroadcastPkts", obj.getInBroadcastPkts());
        CodeHelpers.appendValue(helper, "inDiscards", obj.getInDiscards());
        CodeHelpers.appendValue(helper, "inErrors", obj.getInErrors());
        CodeHelpers.appendValue(helper, "inMulticastPkts", obj.getInMulticastPkts());
        CodeHelpers.appendValue(helper, "inOctets", obj.getInOctets());
        CodeHelpers.appendValue(helper, "inUnicastPkts", obj.getInUnicastPkts());
        CodeHelpers.appendValue(helper, "inUnknownProtos", obj.getInUnknownProtos());
        CodeHelpers.appendValue(helper, "outBroadcastPkts", obj.getOutBroadcastPkts());
        CodeHelpers.appendValue(helper, "outDiscards", obj.getOutDiscards());
        CodeHelpers.appendValue(helper, "outErrors", obj.getOutErrors());
        CodeHelpers.appendValue(helper, "outMulticastPkts", obj.getOutMulticastPkts());
        CodeHelpers.appendValue(helper, "outOctets", obj.getOutOctets());
        CodeHelpers.appendValue(helper, "outUnicastPkts", obj.getOutUnicastPkts());
        CodeHelpers.appendValue(helper, "augmentation", obj.augmentations().values());
        return helper.toString();
    }
    
    /**
     * Return discontinuityTime, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         The time on the most recent occasion at which any one or more of this
     *         interface's counters suffered a discontinuity. If no such discontinuities have
     *         occurred since the last re-initialization of the local management subsystem,
     *         then this node contains the time the local management subsystem re-initialized
     *         itself.
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715.DateAndTime} discontinuityTime, or {@code null} if it is not present.
     *
     */
    @Deprecated
    DateAndTime getDiscontinuityTime();
    
    /**
     * Return inOctets, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         The total number of octets received on the interface, including framing
     *         characters. Discontinuities in the value of this counter can occur at
     *         re-initialization of the management system and at other times as indicated by
     *         the value of 'discontinuity-time'.
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715.Counter64} inOctets, or {@code null} if it is not present.
     *
     */
    @Deprecated
    Counter64 getInOctets();
    
    /**
     * Return inUnicastPkts, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         The number of packets, delivered by this sub-layer to a higher (sub-)layer, that
     *         were not addressed to a multicast or broadcast address at this sub-layer.
     *         Discontinuities in the value of this counter can occur at re-initialization of
     *         the management system and at other times as indicated by the value of
     *         'discontinuity-time'.
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715.Counter64} inUnicastPkts, or {@code null} if it is not present.
     *
     */
    @Deprecated
    Counter64 getInUnicastPkts();
    
    /**
     * Return inBroadcastPkts, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         The number of packets, delivered by this sub-layer to a higher (sub-)layer, that
     *         were addressed to a broadcast address at this sub-layer. Discontinuities in the
     *         value of this counter can occur at re-initialization of the management system
     *         and at other times as indicated by the value of 'discontinuity-time'.
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715.Counter64} inBroadcastPkts, or {@code null} if it is not present.
     *
     */
    @Deprecated
    Counter64 getInBroadcastPkts();
    
    /**
     * Return inMulticastPkts, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         The number of packets, delivered by this sub-layer to a higher (sub-)layer, that
     *         were addressed to a multicast address at this sub-layer. For a MAC-layer
     *         protocol, this includes both Group and Functional addresses. Discontinuities in
     *         the value of this counter can occur at re-initialization of the management
     *         system and at other times as indicated by the value of 'discontinuity-time'.
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715.Counter64} inMulticastPkts, or {@code null} if it is not present.
     *
     */
    @Deprecated
    Counter64 getInMulticastPkts();
    
    /**
     * Return inDiscards, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         The number of inbound packets that were chosen to be discarded even though no
     *         errors had been detected to prevent their being deliverable to a higher-layer
     *         protocol. One possible reason for discarding such a packet could be to free up
     *         buffer space. Discontinuities in the value of this counter can occur at
     *         re-initialization of the management system and at other times as indicated by
     *         the value of 'discontinuity-time'.
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715.Counter32} inDiscards, or {@code null} if it is not present.
     *
     */
    @Deprecated
    Counter32 getInDiscards();
    
    /**
     * Return inErrors, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         For packet-oriented interfaces, the number of inbound packets that contained
     *         errors preventing them from being deliverable to a higher-layer protocol. For
     *         character- oriented or fixed-length interfaces, the number of inbound
     *         transmission units that contained errors preventing them from being deliverable
     *         to a higher-layer protocol. Discontinuities in the value of this counter can
     *         occur at re-initialization of the management system and at other times as
     *         indicated by the value of 'discontinuity-time'.
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715.Counter32} inErrors, or {@code null} if it is not present.
     *
     */
    @Deprecated
    Counter32 getInErrors();
    
    /**
     * Return inUnknownProtos, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         For packet-oriented interfaces, the number of packets received via the interface
     *         that were discarded because of an unknown or unsupported protocol. For
     *         character-oriented or fixed-length interfaces that support protocol
     *         multiplexing, the number of transmission units received via the interface that
     *         were discarded because of an unknown or unsupported protocol. For any interface
     *         that does not support protocol multiplexing, this counter is not present.
     *         Discontinuities in the value of this counter can occur at re-initialization of
     *         the management system and at other times as indicated by the value of
     *         'discontinuity-time'.
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715.Counter32} inUnknownProtos, or {@code null} if it is not present.
     *
     */
    @Deprecated
    Counter32 getInUnknownProtos();
    
    /**
     * Return outOctets, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         The total number of octets transmitted out of the interface, including framing
     *         characters. Discontinuities in the value of this counter can occur at
     *         re-initialization of the management system and at other times as indicated by
     *         the value of 'discontinuity-time'.
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715.Counter64} outOctets, or {@code null} if it is not present.
     *
     */
    @Deprecated
    Counter64 getOutOctets();
    
    /**
     * Return outUnicastPkts, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         The total number of packets that higher-level protocols requested be transmitted
     *         and that were not addressed to a multicast or broadcast address at this
     *         sub-layer, including those that were discarded or not sent. Discontinuities in
     *         the value of this counter can occur at re-initialization of the management
     *         system and at other times as indicated by the value of 'discontinuity-time'.
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715.Counter64} outUnicastPkts, or {@code null} if it is not present.
     *
     */
    @Deprecated
    Counter64 getOutUnicastPkts();
    
    /**
     * Return outBroadcastPkts, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         The total number of packets that higher-level protocols requested be transmitted
     *         and that were addressed to a broadcast address at this sub-layer, including
     *         those that were discarded or not sent. Discontinuities in the value of this
     *         counter can occur at re-initialization of the management system and at other
     *         times as indicated by the value of 'discontinuity-time'.
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715.Counter64} outBroadcastPkts, or {@code null} if it is not present.
     *
     */
    @Deprecated
    Counter64 getOutBroadcastPkts();
    
    /**
     * Return outMulticastPkts, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         The total number of packets that higher-level protocols requested be transmitted
     *         and that were addressed to a multicast address at this sub-layer, including
     *         those that were discarded or not sent. For a MAC-layer protocol, this includes
     *         both Group and Functional addresses. Discontinuities in the value of this
     *         counter can occur at re-initialization of the management system and at other
     *         times as indicated by the value of 'discontinuity-time'.
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715.Counter64} outMulticastPkts, or {@code null} if it is not present.
     *
     */
    @Deprecated
    Counter64 getOutMulticastPkts();
    
    /**
     * Return outDiscards, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         The number of outbound packets that were chosen to be discarded even though no
     *         errors had been detected to prevent their being transmitted. One possible reason
     *         for discarding such a packet could be to free up buffer space. Discontinuities
     *         in the value of this counter can occur at re-initialization of the management
     *         system and at other times as indicated by the value of 'discontinuity-time'.
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715.Counter32} outDiscards, or {@code null} if it is not present.
     *
     */
    @Deprecated
    Counter32 getOutDiscards();
    
    /**
     * Return outErrors, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         For packet-oriented interfaces, the number of outbound packets that could not be
     *         transmitted because of errors. For character-oriented or fixed-length
     *         interfaces, the number of outbound transmission units that could not be
     *         transmitted because of errors. Discontinuities in the value of this counter can
     *         occur at re-initialization of the management system and at other times as
     *         indicated by the value of 'discontinuity-time'.
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715.Counter32} outErrors, or {@code null} if it is not present.
     *
     */
    @Deprecated
    Counter32 getOutErrors();

}

