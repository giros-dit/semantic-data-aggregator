package org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.interfaces.rev180220.interfaces.state;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;
import java.lang.Class;
import java.lang.Deprecated;
import java.lang.Integer;
import java.lang.NullPointerException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import javax.annotation.processing.Generated;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.interfaces.rev180220.$YangModuleInfoImpl;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.interfaces.rev180220.InterfaceType;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.interfaces.rev180220.InterfacesState;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.interfaces.rev180220.interfaces.state._interface.Statistics;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715.DateAndTime;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715.Gauge64;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715.PhysAddress;
import org.opendaylight.yangtools.yang.binding.Augmentable;
import org.opendaylight.yangtools.yang.binding.ChildOf;
import org.opendaylight.yangtools.yang.binding.CodeHelpers;
import org.opendaylight.yangtools.yang.binding.Enumeration;
import org.opendaylight.yangtools.yang.binding.Identifiable;
import org.opendaylight.yangtools.yang.common.QName;

/**
 * The list of interfaces on the device. System-controlled interfaces created by 
 * the system are always present in this list, whether or not they are configured.
 *
 * <p>
 * This class represents the following YANG schema fragment defined in module <b>ietf-interfaces</b>
 * <pre>
 * list interface {
 *   key name;
 *   status deprecated;
 *   leaf name {
 *     type string;
 *     status deprecated;
 *   }
 *   leaf type {
 *     type identityref {
 *       base interface-type;
 *     }
 *     status deprecated;
 *   }
 *   leaf admin-status {
 *     if-feature if-mib;
 *     type enumeration {
 *       enum up {
 *         value 1;
 *       }
 *       enum down {
 *         value 2;
 *       }
 *       enum testing {
 *         value 3;
 *       }
 *     }
 *     status deprecated;
 *   }
 *   leaf oper-status {
 *     type enumeration {
 *       enum up {
 *         value 1;
 *       }
 *       enum down {
 *         value 2;
 *       }
 *       enum testing {
 *         value 3;
 *       }
 *       enum unknown {
 *         value 4;
 *       }
 *       enum dormant {
 *         value 5;
 *       }
 *       enum not-present {
 *         value 6;
 *       }
 *       enum lower-layer-down {
 *         value 7;
 *       }
 *     }
 *     status deprecated;
 *   }
 *   leaf last-change {
 *     type yang:date-and-time;
 *     status deprecated;
 *   }
 *   leaf if-index {
 *     if-feature if-mib;
 *     type int32 {
 *       range 1..2147483647;
 *     }
 *     status deprecated;
 *   }
 *   leaf phys-address {
 *     type yang:phys-address;
 *     status deprecated;
 *   }
 *   leaf-list higher-layer-if {
 *     type interface-state-ref;
 *     status deprecated;
 *   }
 *   leaf-list lower-layer-if {
 *     type interface-state-ref;
 *     status deprecated;
 *   }
 *   leaf speed {
 *     type yang:gauge64;
 *     units bits/second;
 *     status deprecated;
 *   }
 *   container statistics {
 *     status deprecated;
 *     leaf discontinuity-time {
 *       type yang:date-and-time;
 *       status deprecated;
 *     }
 *     leaf in-octets {
 *       type yang:counter64;
 *       status deprecated;
 *     }
 *     leaf in-unicast-pkts {
 *       type yang:counter64;
 *       status deprecated;
 *     }
 *     leaf in-broadcast-pkts {
 *       type yang:counter64;
 *       status deprecated;
 *     }
 *     leaf in-multicast-pkts {
 *       type yang:counter64;
 *       status deprecated;
 *     }
 *     leaf in-discards {
 *       type yang:counter32;
 *       status deprecated;
 *     }
 *     leaf in-errors {
 *       type yang:counter32;
 *       status deprecated;
 *     }
 *     leaf in-unknown-protos {
 *       type yang:counter32;
 *       status deprecated;
 *     }
 *     leaf out-octets {
 *       type yang:counter64;
 *       status deprecated;
 *     }
 *     leaf out-unicast-pkts {
 *       type yang:counter64;
 *       status deprecated;
 *     }
 *     leaf out-broadcast-pkts {
 *       type yang:counter64;
 *       status deprecated;
 *     }
 *     leaf out-multicast-pkts {
 *       type yang:counter64;
 *       status deprecated;
 *     }
 *     leaf out-discards {
 *       type yang:counter32;
 *       status deprecated;
 *     }
 *     leaf out-errors {
 *       type yang:counter32;
 *       status deprecated;
 *     }
 *   }
 * }
 * </pre>The schema path to identify an instance is
 * <i>ietf-interfaces/interfaces-state/interface</i>
 *
 * <p>To create instances of this class use {@link InterfaceBuilder}.
 * @see InterfaceBuilder
 * @see InterfaceKey
 *
 */
@Deprecated
@Generated("mdsal-binding-generator")
public interface Interface
    extends
    ChildOf<InterfacesState>,
    Augmentable<Interface>,
    Identifiable<InterfaceKey>
{


    @Generated("mdsal-binding-generator")
    public enum AdminStatus implements Enumeration {
        /**
         * Ready to pass packets.
         */
        Up(1, "up"),
        
        /**
         * Not ready to pass packets and not in some test mode.
         */
        Down(2, "down"),
        
        /**
         * In some test mode.
         */
        Testing(3, "testing")
        ;
    
        private static final Map<String, AdminStatus> NAME_MAP;
        private static final Map<Integer, AdminStatus> VALUE_MAP;
    
        static {
            final Builder<String, AdminStatus> nb = ImmutableMap.builder();
            final Builder<Integer, AdminStatus> vb = ImmutableMap.builder();
            for (AdminStatus enumItem : AdminStatus.values()) {
                vb.put(enumItem.value, enumItem);
                nb.put(enumItem.name, enumItem);
            }
    
            NAME_MAP = nb.build();
            VALUE_MAP = vb.build();
        }
    
        private final String name;
        private final int value;
    
        private AdminStatus(int value, String name) {
            this.value = value;
            this.name = name;
        }
    
        @Override
        public String getName() {
            return name;
        }
    
        @Override
        public int getIntValue() {
            return value;
        }
    
        /**
         * Return the enumeration member whose {@link #getName()} matches specified value.
         *
         * @param name YANG assigned name
         * @return corresponding AdminStatus item, if present
         * @throws NullPointerException if name is null
         */
        public static Optional<AdminStatus> forName(String name) {
            return Optional.ofNullable(NAME_MAP.get(Objects.requireNonNull(name)));
        }
    
        /**
         * Return the enumeration member whose {@link #getIntValue()} matches specified value.
         *
         * @param intValue integer value
         * @return corresponding AdminStatus item, or null if no such item exists
         */
        public static AdminStatus forValue(int intValue) {
            return VALUE_MAP.get(intValue);
        }
    }
    
    @Generated("mdsal-binding-generator")
    public enum OperStatus implements Enumeration {
        /**
         * Ready to pass packets.
         */
        Up(1, "up"),
        
        /**
         * The interface does not pass any packets.
         */
        Down(2, "down"),
        
        /**
         * In some test mode.  No operational packets can
         * be passed.
         */
        Testing(3, "testing"),
        
        /**
         * Status cannot be determined for some reason.
         */
        Unknown(4, "unknown"),
        
        /**
         * Waiting for some external event.
         */
        Dormant(5, "dormant"),
        
        /**
         * Some component (typically hardware) is missing.
         */
        NotPresent(6, "not-present"),
        
        /**
         * Down due to state of lower-layer interface(s).
         */
        LowerLayerDown(7, "lower-layer-down")
        ;
    
        private static final Map<String, OperStatus> NAME_MAP;
        private static final Map<Integer, OperStatus> VALUE_MAP;
    
        static {
            final Builder<String, OperStatus> nb = ImmutableMap.builder();
            final Builder<Integer, OperStatus> vb = ImmutableMap.builder();
            for (OperStatus enumItem : OperStatus.values()) {
                vb.put(enumItem.value, enumItem);
                nb.put(enumItem.name, enumItem);
            }
    
            NAME_MAP = nb.build();
            VALUE_MAP = vb.build();
        }
    
        private final String name;
        private final int value;
    
        private OperStatus(int value, String name) {
            this.value = value;
            this.name = name;
        }
    
        @Override
        public String getName() {
            return name;
        }
    
        @Override
        public int getIntValue() {
            return value;
        }
    
        /**
         * Return the enumeration member whose {@link #getName()} matches specified value.
         *
         * @param name YANG assigned name
         * @return corresponding OperStatus item, if present
         * @throws NullPointerException if name is null
         */
        public static Optional<OperStatus> forName(String name) {
            return Optional.ofNullable(NAME_MAP.get(Objects.requireNonNull(name)));
        }
    
        /**
         * Return the enumeration member whose {@link #getIntValue()} matches specified value.
         *
         * @param intValue integer value
         * @return corresponding OperStatus item, or null if no such item exists
         */
        public static OperStatus forValue(int intValue) {
            return VALUE_MAP.get(intValue);
        }
    }

    public static final @NonNull QName QNAME = $YangModuleInfoImpl.qnameOf("interface");

    @Override
    default Class<org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.interfaces.rev180220.interfaces.state.Interface> implementedInterface() {
        return org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.interfaces.rev180220.interfaces.state.Interface.class;
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
    static int bindingHashCode(final org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.interfaces.rev180220.interfaces.state.@NonNull Interface obj) {
        final int prime = 31;
        int result = 1;
        result = prime * result + Objects.hashCode(obj.getAdminStatus());
        result = prime * result + Objects.hashCode(obj.getHigherLayerIf());
        result = prime * result + Objects.hashCode(obj.getIfIndex());
        result = prime * result + Objects.hashCode(obj.getLastChange());
        result = prime * result + Objects.hashCode(obj.getLowerLayerIf());
        result = prime * result + Objects.hashCode(obj.getName());
        result = prime * result + Objects.hashCode(obj.getOperStatus());
        result = prime * result + Objects.hashCode(obj.getPhysAddress());
        result = prime * result + Objects.hashCode(obj.getSpeed());
        result = prime * result + Objects.hashCode(obj.getStatistics());
        result = prime * result + Objects.hashCode(obj.getType());
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
    static boolean bindingEquals(final org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.interfaces.rev180220.interfaces.state.@NonNull Interface thisObj, final Object obj) {
        if (thisObj == obj) {
            return true;
        }
        final org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.interfaces.rev180220.interfaces.state.Interface other = CodeHelpers.checkCast(org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.interfaces.rev180220.interfaces.state.Interface.class, obj);
        if (other == null) {
            return false;
        }
        if (!Objects.equals(thisObj.getIfIndex(), other.getIfIndex())) {
            return false;
        }
        if (!Objects.equals(thisObj.getSpeed(), other.getSpeed())) {
            return false;
        }
        if (!Objects.equals(thisObj.getType(), other.getType())) {
            return false;
        }
        if (!Objects.equals(thisObj.getLastChange(), other.getLastChange())) {
            return false;
        }
        if (!Objects.equals(thisObj.getName(), other.getName())) {
            return false;
        }
        if (!Objects.equals(thisObj.getPhysAddress(), other.getPhysAddress())) {
            return false;
        }
        if (!Objects.equals(thisObj.getAdminStatus(), other.getAdminStatus())) {
            return false;
        }
        if (!Objects.equals(thisObj.getHigherLayerIf(), other.getHigherLayerIf())) {
            return false;
        }
        if (!Objects.equals(thisObj.getLowerLayerIf(), other.getLowerLayerIf())) {
            return false;
        }
        if (!Objects.equals(thisObj.getOperStatus(), other.getOperStatus())) {
            return false;
        }
        if (!Objects.equals(thisObj.getStatistics(), other.getStatistics())) {
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
    static String bindingToString(final org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.interfaces.rev180220.interfaces.state.@NonNull Interface obj) {
        final MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper("Interface");
        CodeHelpers.appendValue(helper, "adminStatus", obj.getAdminStatus());
        CodeHelpers.appendValue(helper, "higherLayerIf", obj.getHigherLayerIf());
        CodeHelpers.appendValue(helper, "ifIndex", obj.getIfIndex());
        CodeHelpers.appendValue(helper, "lastChange", obj.getLastChange());
        CodeHelpers.appendValue(helper, "lowerLayerIf", obj.getLowerLayerIf());
        CodeHelpers.appendValue(helper, "name", obj.getName());
        CodeHelpers.appendValue(helper, "operStatus", obj.getOperStatus());
        CodeHelpers.appendValue(helper, "physAddress", obj.getPhysAddress());
        CodeHelpers.appendValue(helper, "speed", obj.getSpeed());
        CodeHelpers.appendValue(helper, "statistics", obj.getStatistics());
        CodeHelpers.appendValue(helper, "type", obj.getType());
        CodeHelpers.appendValue(helper, "augmentation", obj.augmentations().values());
        return helper.toString();
    }
    
    /**
     * Return name, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         The name of the interface. A server implementation MAY map this leaf to the
     *         ifName MIB object. Such an implementation needs to use some mechanism to handle
     *         the differences in size and characters allowed between this leaf and ifName. The
     *         definition of such a mechanism is outside the scope of this document.
     *     </code>
     * </pre>
     *
     * @return {@code java.lang.String} name, or {@code null} if it is not present.
     *
     */
    @Deprecated
    String getName();
    
    /**
     * Return type, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         The type of the interface.
     *     </code>
     * </pre>
     *
     * @return {@code java.lang.Class} type, or {@code null} if it is not present.
     *
     */
    @Deprecated
    Class<? extends InterfaceType> getType();
    
    /**
     * Return adminStatus, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         The desired state of the interface. This leaf has the same read semantics as
     *         ifAdminStatus.
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.interfaces.rev180220.interfaces.state.Interface.AdminStatus} adminStatus, or {@code null} if it is not present.
     *
     */
    @Deprecated
    AdminStatus getAdminStatus();
    
    /**
     * Return operStatus, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         The current operational state of the interface. This leaf has the same semantics
     *         as ifOperStatus.
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.interfaces.rev180220.interfaces.state.Interface.OperStatus} operStatus, or {@code null} if it is not present.
     *
     */
    @Deprecated
    OperStatus getOperStatus();
    
    /**
     * Return lastChange, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         The time the interface entered its current operational state. If the current
     *         state was entered prior to the last re-initialization of the local network
     *         management subsystem, then this node is not present.
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715.DateAndTime} lastChange, or {@code null} if it is not present.
     *
     */
    @Deprecated
    DateAndTime getLastChange();
    
    /**
     * Return ifIndex, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         The ifIndex value for the ifEntry represented by this interface.
     *     </code>
     * </pre>
     *
     * @return {@code java.lang.Integer} ifIndex, or {@code null} if it is not present.
     *
     */
    @Deprecated
    Integer getIfIndex();
    
    /**
     * Return physAddress, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         The interface's address at its protocol sub-layer. For example, for an 802.x
     *         interface, this object normally contains a Media Access Control (MAC) address.
     *         The interface's media-specific modules must define the bit and byte ordering and
     *         the format of the value of this object. For interfaces that do not have such an
     *         address (e.g., a serial line), this node is not present.
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715.PhysAddress} physAddress, or {@code null} if it is not present.
     *
     */
    @Deprecated
    PhysAddress getPhysAddress();
    
    /**
     * Return higherLayerIf, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         A list of references to interfaces layered on top of this interface.
     *     </code>
     * </pre>
     *
     * @return {@code java.util.List} higherLayerIf, or {@code null} if it is not present.
     *
     */
    @Deprecated
    @Nullable List<String> getHigherLayerIf();
    
    /**
     * Return lowerLayerIf, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         A list of references to interfaces layered underneath this interface.
     *     </code>
     * </pre>
     *
     * @return {@code java.util.List} lowerLayerIf, or {@code null} if it is not present.
     *
     */
    @Deprecated
    @Nullable List<String> getLowerLayerIf();
    
    /**
     * Return speed, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         An estimate of the interface's current bandwidth in bits per second. For
     *         interfaces that do not vary in bandwidth or for those where no accurate
     *         estimation can be made, this node should contain the nominal bandwidth. For
     *         interfaces that have no concept of bandwidth, this node is not present.
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715.Gauge64} speed, or {@code null} if it is not present.
     *
     */
    @Deprecated
    Gauge64 getSpeed();
    
    /**
     * Return statistics, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         A collection of interface-related statistics objects.
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.interfaces.rev180220.interfaces.state._interface.Statistics} statistics, or {@code null} if it is not present.
     *
     */
    @Deprecated
    Statistics getStatistics();
    
    @Override
    InterfaceKey key();

}

