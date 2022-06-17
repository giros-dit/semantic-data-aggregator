package org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.interfaces.rev180220.interfaces;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;
import java.lang.Boolean;
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
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.interfaces.rev180220.Interfaces;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.interfaces.rev180220.interfaces._interface.Statistics;
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
 * The list of interfaces on the device. The status of an interface is available 
 * this list in the operational state. If the configuration of a system-controlled 
 * interface cannot be used by the system (e.g., the interface hardware present 
 * does not match the interface type), then the configuration is not applied to 
 * system-controlled interface shown in the operational state. If the 
 * of a user-controlled interface cannot be used by the system, the configured 
 * interface is not instantiated in the operational state. System-controlled 
 * interfaces created by the system are always present in this list in the 
 * operational state, whether or not they are configured.
 *
 * <p>
 * This class represents the following YANG schema fragment defined in module <b>ietf-interfaces</b>
 * <pre>
 * list interface {
 *   key name;
 *   leaf name {
 *     type string;
 *   }
 *   leaf description {
 *     type string;
 *   }
 *   leaf type {
 *     type identityref {
 *       base interface-type;
 *     }
 *   }
 *   leaf enabled {
 *     type boolean;
 *     default true;
 *   }
 *   leaf link-up-down-trap-enable {
 *     if-feature if-mib;
 *     type enumeration {
 *       enum enabled {
 *         value 1;
 *       }
 *       enum disabled {
 *         value 2;
 *       }
 *     }
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
 *     config false;
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
 *     config false;
 *   }
 *   leaf last-change {
 *     type yang:date-and-time;
 *     config false;
 *   }
 *   leaf if-index {
 *     if-feature if-mib;
 *     type int32 {
 *       range 1..2147483647;
 *     }
 *     config false;
 *   }
 *   leaf phys-address {
 *     type yang:phys-address;
 *     config false;
 *   }
 *   leaf-list higher-layer-if {
 *     type interface-ref;
 *     config false;
 *   }
 *   leaf-list lower-layer-if {
 *     type interface-ref;
 *     config false;
 *   }
 *   leaf speed {
 *     type yang:gauge64;
 *     units bits/second;
 *     config false;
 *   }
 *   container statistics {
 *     config false;
 *     leaf discontinuity-time {
 *       type yang:date-and-time;
 *     }
 *     leaf in-octets {
 *       type yang:counter64;
 *     }
 *     leaf in-unicast-pkts {
 *       type yang:counter64;
 *     }
 *     leaf in-broadcast-pkts {
 *       type yang:counter64;
 *     }
 *     leaf in-multicast-pkts {
 *       type yang:counter64;
 *     }
 *     leaf in-discards {
 *       type yang:counter32;
 *     }
 *     leaf in-errors {
 *       type yang:counter32;
 *     }
 *     leaf in-unknown-protos {
 *       type yang:counter32;
 *     }
 *     leaf out-octets {
 *       type yang:counter64;
 *     }
 *     leaf out-unicast-pkts {
 *       type yang:counter64;
 *     }
 *     leaf out-broadcast-pkts {
 *       type yang:counter64;
 *     }
 *     leaf out-multicast-pkts {
 *       type yang:counter64;
 *     }
 *     leaf out-discards {
 *       type yang:counter32;
 *     }
 *     leaf out-errors {
 *       type yang:counter32;
 *     }
 *   }
 * }
 * </pre>The schema path to identify an instance is
 * <i>ietf-interfaces/interfaces/interface</i>
 *
 * <p>To create instances of this class use {@link InterfaceBuilder}.
 * @see InterfaceBuilder
 * @see InterfaceKey
 *
 */
@Generated("mdsal-binding-generator")
public interface Interface
    extends
    ChildOf<Interfaces>,
    Augmentable<Interface>,
    Identifiable<InterfaceKey>
{


    @Generated("mdsal-binding-generator")
    public enum LinkUpDownTrapEnable implements Enumeration {
        /**
         * The device will generate linkUp/linkDown SNMP
         * notifications for this interface.
         */
        Enabled(1, "enabled"),
        
        /**
         * The device will not generate linkUp/linkDown SNMP
         * notifications for this interface.
         */
        Disabled(2, "disabled")
        ;
    
        private static final Map<String, LinkUpDownTrapEnable> NAME_MAP;
        private static final Map<Integer, LinkUpDownTrapEnable> VALUE_MAP;
    
        static {
            final Builder<String, LinkUpDownTrapEnable> nb = ImmutableMap.builder();
            final Builder<Integer, LinkUpDownTrapEnable> vb = ImmutableMap.builder();
            for (LinkUpDownTrapEnable enumItem : LinkUpDownTrapEnable.values()) {
                vb.put(enumItem.value, enumItem);
                nb.put(enumItem.name, enumItem);
            }
    
            NAME_MAP = nb.build();
            VALUE_MAP = vb.build();
        }
    
        private final String name;
        private final int value;
    
        private LinkUpDownTrapEnable(int value, String name) {
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
         * @return corresponding LinkUpDownTrapEnable item, if present
         * @throws NullPointerException if name is null
         */
        public static Optional<LinkUpDownTrapEnable> forName(String name) {
            return Optional.ofNullable(NAME_MAP.get(Objects.requireNonNull(name)));
        }
    
        /**
         * Return the enumeration member whose {@link #getIntValue()} matches specified value.
         *
         * @param intValue integer value
         * @return corresponding LinkUpDownTrapEnable item, or null if no such item exists
         */
        public static LinkUpDownTrapEnable forValue(int intValue) {
            return VALUE_MAP.get(intValue);
        }
    }
    
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
    default Class<org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.interfaces.rev180220.interfaces.Interface> implementedInterface() {
        return org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.interfaces.rev180220.interfaces.Interface.class;
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
    static int bindingHashCode(final org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.interfaces.rev180220.interfaces.@NonNull Interface obj) {
        final int prime = 31;
        int result = 1;
        result = prime * result + Objects.hashCode(obj.getAdminStatus());
        result = prime * result + Objects.hashCode(obj.getDescription());
        result = prime * result + Objects.hashCode(obj.getEnabled());
        result = prime * result + Objects.hashCode(obj.getHigherLayerIf());
        result = prime * result + Objects.hashCode(obj.getIfIndex());
        result = prime * result + Objects.hashCode(obj.getLastChange());
        result = prime * result + Objects.hashCode(obj.getLinkUpDownTrapEnable());
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
    static boolean bindingEquals(final org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.interfaces.rev180220.interfaces.@NonNull Interface thisObj, final Object obj) {
        if (thisObj == obj) {
            return true;
        }
        final org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.interfaces.rev180220.interfaces.Interface other = CodeHelpers.checkCast(org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.interfaces.rev180220.interfaces.Interface.class, obj);
        if (other == null) {
            return false;
        }
        if (!Objects.equals(thisObj.getEnabled(), other.getEnabled())) {
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
        if (!Objects.equals(thisObj.getDescription(), other.getDescription())) {
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
        if (!Objects.equals(thisObj.getLinkUpDownTrapEnable(), other.getLinkUpDownTrapEnable())) {
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
    static String bindingToString(final org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.interfaces.rev180220.interfaces.@NonNull Interface obj) {
        final MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper("Interface");
        CodeHelpers.appendValue(helper, "adminStatus", obj.getAdminStatus());
        CodeHelpers.appendValue(helper, "description", obj.getDescription());
        CodeHelpers.appendValue(helper, "enabled", obj.getEnabled());
        CodeHelpers.appendValue(helper, "higherLayerIf", obj.getHigherLayerIf());
        CodeHelpers.appendValue(helper, "ifIndex", obj.getIfIndex());
        CodeHelpers.appendValue(helper, "lastChange", obj.getLastChange());
        CodeHelpers.appendValue(helper, "linkUpDownTrapEnable", obj.getLinkUpDownTrapEnable());
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
     *         The name of the interface. A device MAY restrict the allowed values for this
     *         leaf, possibly depending on the type of the interface. For system-controlled
     *         interfaces, this leaf is the device-specific name of the interface. If a client
     *         tries to create configuration for a system-controlled interface that is not
     *         present in the operational state, the server MAY reject the request if the
     *         implementation does not support pre-provisioning of interfaces or if the name
     *         refers to an interface that can never exist in the system. A Network
     *         Configuration Protocol (NETCONF) server MUST reply with an rpc-error with the
     *         error-tag 'invalid-value' in this case. If the device supports pre-provisioning
     *         of interface configuration, the 'pre-provisioning' feature is advertised. If the
     *         device allows arbitrarily named user-controlled interfaces, the
     *         'arbitrary-names' feature is advertised. When a configured user-controlled
     *         interface is created by the system, it is instantiated with the same name in the
     *         operational state. A server implementation MAY map this leaf to the ifName MIB
     *         object. Such an implementation needs to use some mechanism to handle the
     *         differences in size and characters allowed between this leaf and ifName. The
     *         definition of such a mechanism is outside the scope of this document.
     *     </code>
     * </pre>
     *
     * @return {@code java.lang.String} name, or {@code null} if it is not present.
     *
     */
    String getName();
    
    /**
     * Return description, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         A textual description of the interface. A server implementation MAY map this
     *         leaf to the ifAlias MIB object. Such an implementation needs to use some
     *         mechanism to handle the differences in size and characters allowed between this
     *         leaf and ifAlias. The definition of such a mechanism is outside the scope of
     *         this document. Since ifAlias is defined to be stored in non-volatile storage,
     *         the MIB implementation MUST map ifAlias to the value of 'description' in the
     *         persistently stored configuration.
     *     </code>
     * </pre>
     *
     * @return {@code java.lang.String} description, or {@code null} if it is not present.
     *
     */
    String getDescription();
    
    /**
     * Return type, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         The type of the interface. When an interface entry is created, a server MAY
     *         initialize the type leaf with a valid value, e.g., if it is possible to derive
     *         the type from the name of the interface. If a client tries to set the type of an
     *         interface to a value that can never be used by the system, e.g., if the type is
     *         not supported or if the type does not match the name of the interface, the
     *         server MUST reject the request. A NETCONF server MUST reply with an rpc-error
     *         with the error-tag 'invalid-value' in this case.
     *     </code>
     * </pre>
     *
     * @return {@code java.lang.Class} type, or {@code null} if it is not present.
     *
     */
    Class<? extends InterfaceType> getType();
    
    /**
     * Return enabled, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         This leaf contains the configured, desired state of the interface. Systems that
     *         implement the IF-MIB use the value of this leaf in the intended configuration to
     *         set IF-MIB.ifAdminStatus to 'up' or 'down' after an ifEntry has been
     *         initialized, as described in RFC 2863. Changes in this leaf in the intended
     *         configuration are reflected in ifAdminStatus.
     *     </code>
     * </pre>
     *
     * @return {@code java.lang.Boolean} enabled, or {@code null} if it is not present.
     *
     */
    Boolean getEnabled();
    
    @Deprecated(forRemoval = true)
    default Boolean isEnabled() {
        return getEnabled();
    }
    
    /**
     * Return linkUpDownTrapEnable, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Controls whether linkUp/linkDown SNMP notifications should be generated for this
     *         interface. If this node is not configured, the value 'enabled' is operationally
     *         used by the server for interfaces that do not operate on top of any other
     *         interface (i.e., there are no 'lower-layer-if' entries), and 'disabled'
     *         otherwise.
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.interfaces.rev180220.interfaces.Interface.LinkUpDownTrapEnable} linkUpDownTrapEnable, or {@code null} if it is not present.
     *
     */
    LinkUpDownTrapEnable getLinkUpDownTrapEnable();
    
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
     * @return {@code org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.interfaces.rev180220.interfaces.Interface.AdminStatus} adminStatus, or {@code null} if it is not present.
     *
     */
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
     * @return {@code org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.interfaces.rev180220.interfaces.Interface.OperStatus} operStatus, or {@code null} if it is not present.
     *
     */
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
     * @return {@code org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.interfaces.rev180220.interfaces._interface.Statistics} statistics, or {@code null} if it is not present.
     *
     */
    Statistics getStatistics();
    
    @Override
    InterfaceKey key();

}

