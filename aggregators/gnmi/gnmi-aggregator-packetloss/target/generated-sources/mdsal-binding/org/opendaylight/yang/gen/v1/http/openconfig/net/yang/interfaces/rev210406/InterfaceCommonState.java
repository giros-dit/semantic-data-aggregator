package org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;
import java.lang.Boolean;
import java.lang.Class;
import java.lang.Deprecated;
import java.lang.Integer;
import java.lang.Override;
import java.lang.String;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import javax.annotation.processing.Generated;
import org.eclipse.jdt.annotation.NonNull;
import org.opendaylight.yang.gen.v1.http.openconfig.net.yang.openconfig.types.rev190416.Timeticks64;
import org.opendaylight.yangtools.yang.binding.DataObject;
import org.opendaylight.yangtools.yang.binding.Enumeration;
import org.opendaylight.yangtools.yang.common.QName;
import org.opendaylight.yangtools.yang.common.Uint32;

/**
 * Operational state data (in addition to intended configuration) at the global 
 * level for this interface
 *
 * <p>
 * This class represents the following YANG schema fragment defined in module <b>openconfig-interfaces</b>
 * <pre>
 * grouping interface-common-state {
 *   oc-ext:operational;
 *   leaf ifindex {
 *     type uint32;
 *     oc-ext:telemetry-on-change;
 *   }
 *   leaf admin-status {
 *     type enumeration {
 *       enum UP {
 *       }
 *       enum DOWN {
 *       }
 *       enum TESTING {
 *       }
 *     }
 *     oc-ext:telemetry-on-change;
 *   }
 *   leaf oper-status {
 *     type enumeration {
 *       enum UP {
 *         value 1;
 *       }
 *       enum DOWN {
 *         value 2;
 *       }
 *       enum TESTING {
 *         value 3;
 *       }
 *       enum UNKNOWN {
 *         value 4;
 *       }
 *       enum DORMANT {
 *         value 5;
 *       }
 *       enum NOT_PRESENT {
 *         value 6;
 *       }
 *       enum LOWER_LAYER_DOWN {
 *         value 7;
 *       }
 *     }
 *     oc-ext:telemetry-on-change;
 *   }
 *   leaf last-change {
 *     type oc-types:timeticks64;
 *     oc-ext:telemetry-on-change;
 *   }
 *   leaf logical {
 *     type boolean;
 *     oc-ext:telemetry-on-change;
 *   }
 *   leaf management {
 *     type boolean;
 *     oc-ext:telemetry-on-change;
 *   }
 *   leaf cpu {
 *     type boolean;
 *     oc-ext:telemetry-on-change;
 *   }
 * }
 * </pre>The schema path to identify an instance is
 * <i>openconfig-interfaces/interface-common-state</i>
 *
 */
@Generated("mdsal-binding-generator")
public interface InterfaceCommonState
    extends
    DataObject
{


    @Generated("mdsal-binding-generator")
    public enum AdminStatus implements Enumeration {
        /**
         * Ready to pass packets.
         */
        UP(0, "UP"),
        
        /**
         * Not ready to pass packets and not in some test mode.
         */
        DOWN(1, "DOWN"),
        
        /**
         * In some test mode.
         */
        TESTING(2, "TESTING")
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
        UP(1, "UP"),
        
        /**
         * The interface does not pass any packets.
         */
        DOWN(2, "DOWN"),
        
        /**
         * In some test mode.  No operational packets can
         * be passed.
         */
        TESTING(3, "TESTING"),
        
        /**
         * Status cannot be determined for some reason.
         */
        UNKNOWN(4, "UNKNOWN"),
        
        /**
         * Waiting for some external event.
         */
        DORMANT(5, "DORMANT"),
        
        /**
         * Some component (typically hardware) is missing.
         */
        NOTPRESENT(6, "NOT_PRESENT"),
        
        /**
         * Down due to state of lower-layer interface(s).
         */
        LOWERLAYERDOWN(7, "LOWER_LAYER_DOWN")
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

    public static final @NonNull QName QNAME = $YangModuleInfoImpl.qnameOf("interface-common-state");

    @Override
    Class<? extends InterfaceCommonState> implementedInterface();
    
    /**
     * Return ifindex, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         System assigned number for each interface. Corresponds to ifIndex object in SNMP
     *         Interface MIB
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yangtools.yang.common.Uint32} ifindex, or {@code null} if it is not present.
     *
     */
    Uint32 getIfindex();
    
    /**
     * Return adminStatus, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         The desired state of the interface. In RFC 7223 this leaf has the same read
     *         semantics as ifAdminStatus. Here, it reflects the administrative state as set by
     *         enabling or disabling the interface.
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.InterfaceCommonState.AdminStatus} adminStatus, or {@code null} if it is not present.
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
     * @return {@code org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.InterfaceCommonState.OperStatus} operStatus, or {@code null} if it is not present.
     *
     */
    OperStatus getOperStatus();
    
    /**
     * Return lastChange, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         This timestamp indicates the absolute time of the last state change of the
     *         interface (e.g., up-to-down transition). This is different than the SNMP
     *         ifLastChange object in the standard interface MIB in that it is not relative to
     *         the system boot time (i.e,. sysUpTime). The value is the timestamp in
     *         nanoseconds relative to the Unix Epoch (Jan 1, 1970 00:00:00 UTC).
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.http.openconfig.net.yang.openconfig.types.rev190416.Timeticks64} lastChange, or {@code null} if it is not present.
     *
     */
    Timeticks64 getLastChange();
    
    /**
     * Return logical, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         When set to true, the interface is a logical interface which does not have an
     *         associated physical port or channel on the system.
     *     </code>
     * </pre>
     *
     * @return {@code java.lang.Boolean} logical, or {@code null} if it is not present.
     *
     */
    Boolean getLogical();
    
    @Deprecated(forRemoval = true)
    default Boolean isLogical() {
        return getLogical();
    }
    
    /**
     * Return management, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         When set to true, the interface is a dedicated management interface that is not
     *         connected to dataplane interfaces. It may be used to connect the system to an
     *         out-of-band management network, for example.
     *     </code>
     * </pre>
     *
     * @return {@code java.lang.Boolean} management, or {@code null} if it is not present.
     *
     */
    Boolean getManagement();
    
    @Deprecated(forRemoval = true)
    default Boolean isManagement() {
        return getManagement();
    }
    
    /**
     * Return cpu, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         When set to true, the interface is for traffic that is handled by the system
     *         CPU, sometimes also called the control plane interface. On systems that
     *         represent the CPU interface as an Ethernet interface, for example, this leaf
     *         should be used to distinguish the CPU interface from dataplane interfaces.
     *     </code>
     * </pre>
     *
     * @return {@code java.lang.Boolean} cpu, or {@code null} if it is not present.
     *
     */
    Boolean getCpu();
    
    @Deprecated(forRemoval = true)
    default Boolean isCpu() {
        return getCpu();
    }

}

