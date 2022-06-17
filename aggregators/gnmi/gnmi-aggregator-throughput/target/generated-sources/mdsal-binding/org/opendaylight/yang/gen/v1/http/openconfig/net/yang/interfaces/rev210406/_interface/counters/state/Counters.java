package org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406._interface.counters.state;
import com.google.common.base.MoreObjects;
import java.lang.Class;
import java.lang.NullPointerException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.util.Objects;
import javax.annotation.processing.Generated;
import org.eclipse.jdt.annotation.NonNull;
import org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.$YangModuleInfoImpl;
import org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.InterfaceCountersState;
import org.opendaylight.yang.gen.v1.http.openconfig.net.yang.openconfig.types.rev190416.Timeticks64;
import org.opendaylight.yang.gen.v1.http.openconfig.net.yang.types.yang.rev210714.Counter64;
import org.opendaylight.yangtools.yang.binding.Augmentable;
import org.opendaylight.yangtools.yang.binding.ChildOf;
import org.opendaylight.yangtools.yang.binding.CodeHelpers;
import org.opendaylight.yangtools.yang.common.QName;

/**
 * A collection of interface-related statistics objects.
 *
 * <p>
 * This class represents the following YANG schema fragment defined in module <b>openconfig-interfaces</b>
 * <pre>
 * container counters {
 *   leaf in-octets {
 *     type oc-yang:counter64;
 *   }
 *   leaf in-pkts {
 *     type oc-yang:counter64;
 *   }
 *   leaf in-unicast-pkts {
 *     type oc-yang:counter64;
 *   }
 *   leaf in-broadcast-pkts {
 *     type oc-yang:counter64;
 *   }
 *   leaf in-multicast-pkts {
 *     type oc-yang:counter64;
 *   }
 *   leaf in-discards {
 *     type oc-yang:counter64;
 *   }
 *   leaf in-errors {
 *     type oc-yang:counter64;
 *   }
 *   leaf in-unknown-protos {
 *     type oc-yang:counter64;
 *   }
 *   leaf in-fcs-errors {
 *     type oc-yang:counter64;
 *   }
 *   leaf out-octets {
 *     type oc-yang:counter64;
 *   }
 *   leaf out-pkts {
 *     type oc-yang:counter64;
 *   }
 *   leaf out-unicast-pkts {
 *     type oc-yang:counter64;
 *   }
 *   leaf out-broadcast-pkts {
 *     type oc-yang:counter64;
 *   }
 *   leaf out-multicast-pkts {
 *     type oc-yang:counter64;
 *   }
 *   leaf out-discards {
 *     type oc-yang:counter64;
 *   }
 *   leaf out-errors {
 *     type oc-yang:counter64;
 *   }
 *   leaf carrier-transitions {
 *     type oc-yang:counter64;
 *     oc-ext:telemetry-on-change;
 *   }
 *   leaf last-clear {
 *     type oc-types:timeticks64;
 *     oc-ext:telemetry-on-change;
 *   }
 * }
 * </pre>The schema path to identify an instance is
 * <i>openconfig-interfaces/interface-counters-state/counters</i>
 *
 * <p>To create instances of this class use {@link CountersBuilder}.
 * @see CountersBuilder
 *
 */
@Generated("mdsal-binding-generator")
public interface Counters
    extends
    ChildOf<InterfaceCountersState>,
    Augmentable<Counters>
{



    public static final @NonNull QName QNAME = $YangModuleInfoImpl.qnameOf("counters");

    @Override
    default Class<org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406._interface.counters.state.Counters> implementedInterface() {
        return org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406._interface.counters.state.Counters.class;
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
    static int bindingHashCode(final org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406._interface.counters.state.@NonNull Counters obj) {
        final int prime = 31;
        int result = 1;
        result = prime * result + Objects.hashCode(obj.getCarrierTransitions());
        result = prime * result + Objects.hashCode(obj.getInBroadcastPkts());
        result = prime * result + Objects.hashCode(obj.getInDiscards());
        result = prime * result + Objects.hashCode(obj.getInErrors());
        result = prime * result + Objects.hashCode(obj.getInFcsErrors());
        result = prime * result + Objects.hashCode(obj.getInMulticastPkts());
        result = prime * result + Objects.hashCode(obj.getInOctets());
        result = prime * result + Objects.hashCode(obj.getInPkts());
        result = prime * result + Objects.hashCode(obj.getInUnicastPkts());
        result = prime * result + Objects.hashCode(obj.getInUnknownProtos());
        result = prime * result + Objects.hashCode(obj.getLastClear());
        result = prime * result + Objects.hashCode(obj.getOutBroadcastPkts());
        result = prime * result + Objects.hashCode(obj.getOutDiscards());
        result = prime * result + Objects.hashCode(obj.getOutErrors());
        result = prime * result + Objects.hashCode(obj.getOutMulticastPkts());
        result = prime * result + Objects.hashCode(obj.getOutOctets());
        result = prime * result + Objects.hashCode(obj.getOutPkts());
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
    static boolean bindingEquals(final org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406._interface.counters.state.@NonNull Counters thisObj, final Object obj) {
        if (thisObj == obj) {
            return true;
        }
        final org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406._interface.counters.state.Counters other = CodeHelpers.checkCast(org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406._interface.counters.state.Counters.class, obj);
        if (other == null) {
            return false;
        }
        if (!Objects.equals(thisObj.getCarrierTransitions(), other.getCarrierTransitions())) {
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
        if (!Objects.equals(thisObj.getInFcsErrors(), other.getInFcsErrors())) {
            return false;
        }
        if (!Objects.equals(thisObj.getInMulticastPkts(), other.getInMulticastPkts())) {
            return false;
        }
        if (!Objects.equals(thisObj.getInOctets(), other.getInOctets())) {
            return false;
        }
        if (!Objects.equals(thisObj.getInPkts(), other.getInPkts())) {
            return false;
        }
        if (!Objects.equals(thisObj.getInUnicastPkts(), other.getInUnicastPkts())) {
            return false;
        }
        if (!Objects.equals(thisObj.getInUnknownProtos(), other.getInUnknownProtos())) {
            return false;
        }
        if (!Objects.equals(thisObj.getLastClear(), other.getLastClear())) {
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
        if (!Objects.equals(thisObj.getOutPkts(), other.getOutPkts())) {
            return false;
        }
        if (!Objects.equals(thisObj.getOutUnicastPkts(), other.getOutUnicastPkts())) {
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
    static String bindingToString(final org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406._interface.counters.state.@NonNull Counters obj) {
        final MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper("Counters");
        CodeHelpers.appendValue(helper, "carrierTransitions", obj.getCarrierTransitions());
        CodeHelpers.appendValue(helper, "inBroadcastPkts", obj.getInBroadcastPkts());
        CodeHelpers.appendValue(helper, "inDiscards", obj.getInDiscards());
        CodeHelpers.appendValue(helper, "inErrors", obj.getInErrors());
        CodeHelpers.appendValue(helper, "inFcsErrors", obj.getInFcsErrors());
        CodeHelpers.appendValue(helper, "inMulticastPkts", obj.getInMulticastPkts());
        CodeHelpers.appendValue(helper, "inOctets", obj.getInOctets());
        CodeHelpers.appendValue(helper, "inPkts", obj.getInPkts());
        CodeHelpers.appendValue(helper, "inUnicastPkts", obj.getInUnicastPkts());
        CodeHelpers.appendValue(helper, "inUnknownProtos", obj.getInUnknownProtos());
        CodeHelpers.appendValue(helper, "lastClear", obj.getLastClear());
        CodeHelpers.appendValue(helper, "outBroadcastPkts", obj.getOutBroadcastPkts());
        CodeHelpers.appendValue(helper, "outDiscards", obj.getOutDiscards());
        CodeHelpers.appendValue(helper, "outErrors", obj.getOutErrors());
        CodeHelpers.appendValue(helper, "outMulticastPkts", obj.getOutMulticastPkts());
        CodeHelpers.appendValue(helper, "outOctets", obj.getOutOctets());
        CodeHelpers.appendValue(helper, "outPkts", obj.getOutPkts());
        CodeHelpers.appendValue(helper, "outUnicastPkts", obj.getOutUnicastPkts());
        CodeHelpers.appendValue(helper, "augmentation", obj.augmentations().values());
        return helper.toString();
    }
    
    /**
     * Return inOctets, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         The total number of octets received on the interface, including framing
     *         characters. Discontinuities in the value of this counter can occur at
     *         re-initialization of the management system, and at other times as indicated by
     *         the value of 'last-clear'.
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.http.openconfig.net.yang.types.yang.rev210714.Counter64} inOctets, or {@code null} if it is not present.
     *
     */
    Counter64 getInOctets();
    
    /**
     * Return inPkts, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         The total number of packets received on the interface, including all unicast,
     *         multicast, broadcast and bad packets etc.
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.http.openconfig.net.yang.types.yang.rev210714.Counter64} inPkts, or {@code null} if it is not present.
     *
     */
    Counter64 getInPkts();
    
    /**
     * Return inUnicastPkts, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         The number of packets, delivered by this sub-layer to a higher (sub-)layer, that
     *         were not addressed to a multicast or broadcast address at this sub-layer.
     *         Discontinuities in the value of this counter can occur at re-initialization of
     *         the management system, and at other times as indicated by the value of
     *         'last-clear'.
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.http.openconfig.net.yang.types.yang.rev210714.Counter64} inUnicastPkts, or {@code null} if it is not present.
     *
     */
    Counter64 getInUnicastPkts();
    
    /**
     * Return inBroadcastPkts, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         The number of packets, delivered by this sub-layer to a higher (sub-)layer, that
     *         were addressed to a broadcast address at this sub-layer. Discontinuities in the
     *         value of this counter can occur at re-initialization of the management system,
     *         and at other times as indicated by the value of 'last-clear'.
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.http.openconfig.net.yang.types.yang.rev210714.Counter64} inBroadcastPkts, or {@code null} if it is not present.
     *
     */
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
     *         system, and at other times as indicated by the value of 'last-clear'.
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.http.openconfig.net.yang.types.yang.rev210714.Counter64} inMulticastPkts, or {@code null} if it is not present.
     *
     */
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
     *         re-initialization of the management system, and at other times as indicated by
     *         the value of 'last-clear'.
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.http.openconfig.net.yang.types.yang.rev210714.Counter64} inDiscards, or {@code null} if it is not present.
     *
     */
    Counter64 getInDiscards();
    
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
     *         occur at re-initialization of the management system, and at other times as
     *         indicated by the value of 'last-clear'.
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.http.openconfig.net.yang.types.yang.rev210714.Counter64} inErrors, or {@code null} if it is not present.
     *
     */
    Counter64 getInErrors();
    
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
     *         the management system, and at other times as indicated by the value of
     *         'last-clear'.
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.http.openconfig.net.yang.types.yang.rev210714.Counter64} inUnknownProtos, or {@code null} if it is not present.
     *
     */
    Counter64 getInUnknownProtos();
    
    /**
     * Return inFcsErrors, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Number of received packets which had errors in the frame check sequence (FCS),
     *         i.e., framing errors. Discontinuities in the value of this counter can occur
     *         when the device is re-initialization as indicated by the value of 'last-clear'.
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.http.openconfig.net.yang.types.yang.rev210714.Counter64} inFcsErrors, or {@code null} if it is not present.
     *
     */
    Counter64 getInFcsErrors();
    
    /**
     * Return outOctets, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         The total number of octets transmitted out of the interface, including framing
     *         characters. Discontinuities in the value of this counter can occur at
     *         re-initialization of the management system, and at other times as indicated by
     *         the value of 'last-clear'.
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.http.openconfig.net.yang.types.yang.rev210714.Counter64} outOctets, or {@code null} if it is not present.
     *
     */
    Counter64 getOutOctets();
    
    /**
     * Return outPkts, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         The total number of packets transmitted out of the interface, including all
     *         unicast, multicast, broadcast, and bad packets etc.
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.http.openconfig.net.yang.types.yang.rev210714.Counter64} outPkts, or {@code null} if it is not present.
     *
     */
    Counter64 getOutPkts();
    
    /**
     * Return outUnicastPkts, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         The total number of packets that higher-level protocols requested be
     *         transmitted, and that were not addressed to a multicast or broadcast address at
     *         this sub-layer, including those that were discarded or not sent. Discontinuities
     *         in the value of this counter can occur at re-initialization of the management
     *         system, and at other times as indicated by the value of 'last-clear'.
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.http.openconfig.net.yang.types.yang.rev210714.Counter64} outUnicastPkts, or {@code null} if it is not present.
     *
     */
    Counter64 getOutUnicastPkts();
    
    /**
     * Return outBroadcastPkts, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         The total number of packets that higher-level protocols requested be
     *         transmitted, and that were addressed to a broadcast address at this sub-layer,
     *         including those that were discarded or not sent. Discontinuities in the value of
     *         this counter can occur at re-initialization of the management system, and at
     *         other times as indicated by the value of 'last-clear'.
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.http.openconfig.net.yang.types.yang.rev210714.Counter64} outBroadcastPkts, or {@code null} if it is not present.
     *
     */
    Counter64 getOutBroadcastPkts();
    
    /**
     * Return outMulticastPkts, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         The total number of packets that higher-level protocols requested be
     *         transmitted, and that were addressed to a multicast address at this sub-layer,
     *         including those that were discarded or not sent. For a MAC-layer protocol, this
     *         includes both Group and Functional addresses. Discontinuities in the value of
     *         this counter can occur at re-initialization of the management system, and at
     *         other times as indicated by the value of 'last-clear'.
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.http.openconfig.net.yang.types.yang.rev210714.Counter64} outMulticastPkts, or {@code null} if it is not present.
     *
     */
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
     *         system, and at other times as indicated by the value of 'last-clear'.
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.http.openconfig.net.yang.types.yang.rev210714.Counter64} outDiscards, or {@code null} if it is not present.
     *
     */
    Counter64 getOutDiscards();
    
    /**
     * Return outErrors, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         For packet-oriented interfaces, the number of outbound packets that could not be
     *         transmitted because of errors. For character-oriented or fixed-length
     *         interfaces, the number of outbound transmission units that could not be
     *         transmitted because of errors. Discontinuities in the value of this counter can
     *         occur at re-initialization of the management system, and at other times as
     *         indicated by the value of 'last-clear'.
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.http.openconfig.net.yang.types.yang.rev210714.Counter64} outErrors, or {@code null} if it is not present.
     *
     */
    Counter64 getOutErrors();
    
    /**
     * Return carrierTransitions, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Number of times the interface state has transitioned between up and down since
     *         the time the device restarted or the last-clear time, whichever is most recent.
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.http.openconfig.net.yang.types.yang.rev210714.Counter64} carrierTransitions, or {@code null} if it is not present.
     *
     */
    Counter64 getCarrierTransitions();
    
    /**
     * Return lastClear, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Timestamp of the last time the interface counters were cleared. The value is the
     *         timestamp in nanoseconds relative to the Unix Epoch (Jan 1, 1970 00:00:00 UTC).
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.http.openconfig.net.yang.openconfig.types.rev190416.Timeticks64} lastClear, or {@code null} if it is not present.
     *
     */
    Timeticks64 getLastClear();

}

