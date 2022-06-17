package org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.interfaces.rev180220;
import com.google.common.base.MoreObjects;
import java.lang.Class;
import java.lang.NullPointerException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.util.Map;
import java.util.Objects;
import javax.annotation.processing.Generated;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.interfaces.rev180220.interfaces.Interface;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.interfaces.rev180220.interfaces.InterfaceKey;
import org.opendaylight.yangtools.yang.binding.Augmentable;
import org.opendaylight.yangtools.yang.binding.ChildOf;
import org.opendaylight.yangtools.yang.binding.CodeHelpers;
import org.opendaylight.yangtools.yang.common.QName;

/**
 * Interface parameters.
 *
 * <p>
 * This class represents the following YANG schema fragment defined in module <b>ietf-interfaces</b>
 * <pre>
 * container interfaces {
 *   list interface {
 *     key name;
 *     leaf name {
 *       type string;
 *     }
 *     leaf description {
 *       type string;
 *     }
 *     leaf type {
 *       type identityref {
 *         base interface-type;
 *       }
 *     }
 *     leaf enabled {
 *       type boolean;
 *       default true;
 *     }
 *     leaf link-up-down-trap-enable {
 *       if-feature if-mib;
 *       type enumeration {
 *         enum enabled {
 *           value 1;
 *         }
 *         enum disabled {
 *           value 2;
 *         }
 *       }
 *     }
 *     leaf admin-status {
 *       if-feature if-mib;
 *       type enumeration {
 *         enum up {
 *           value 1;
 *         }
 *         enum down {
 *           value 2;
 *         }
 *         enum testing {
 *           value 3;
 *         }
 *       }
 *       config false;
 *     }
 *     leaf oper-status {
 *       type enumeration {
 *         enum up {
 *           value 1;
 *         }
 *         enum down {
 *           value 2;
 *         }
 *         enum testing {
 *           value 3;
 *         }
 *         enum unknown {
 *           value 4;
 *         }
 *         enum dormant {
 *           value 5;
 *         }
 *         enum not-present {
 *           value 6;
 *         }
 *         enum lower-layer-down {
 *           value 7;
 *         }
 *       }
 *       config false;
 *     }
 *     leaf last-change {
 *       type yang:date-and-time;
 *       config false;
 *     }
 *     leaf if-index {
 *       if-feature if-mib;
 *       type int32 {
 *         range 1..2147483647;
 *       }
 *       config false;
 *     }
 *     leaf phys-address {
 *       type yang:phys-address;
 *       config false;
 *     }
 *     leaf-list higher-layer-if {
 *       type interface-ref;
 *       config false;
 *     }
 *     leaf-list lower-layer-if {
 *       type interface-ref;
 *       config false;
 *     }
 *     leaf speed {
 *       type yang:gauge64;
 *       units bits/second;
 *       config false;
 *     }
 *     container statistics {
 *       config false;
 *       leaf discontinuity-time {
 *         type yang:date-and-time;
 *       }
 *       leaf in-octets {
 *         type yang:counter64;
 *       }
 *       leaf in-unicast-pkts {
 *         type yang:counter64;
 *       }
 *       leaf in-broadcast-pkts {
 *         type yang:counter64;
 *       }
 *       leaf in-multicast-pkts {
 *         type yang:counter64;
 *       }
 *       leaf in-discards {
 *         type yang:counter32;
 *       }
 *       leaf in-errors {
 *         type yang:counter32;
 *       }
 *       leaf in-unknown-protos {
 *         type yang:counter32;
 *       }
 *       leaf out-octets {
 *         type yang:counter64;
 *       }
 *       leaf out-unicast-pkts {
 *         type yang:counter64;
 *       }
 *       leaf out-broadcast-pkts {
 *         type yang:counter64;
 *       }
 *       leaf out-multicast-pkts {
 *         type yang:counter64;
 *       }
 *       leaf out-discards {
 *         type yang:counter32;
 *       }
 *       leaf out-errors {
 *         type yang:counter32;
 *       }
 *     }
 *   }
 * }
 * </pre>The schema path to identify an instance is
 * <i>ietf-interfaces/interfaces</i>
 *
 * <p>To create instances of this class use {@link InterfacesBuilder}.
 * @see InterfacesBuilder
 *
 */
@Generated("mdsal-binding-generator")
public interface Interfaces
    extends
    ChildOf<IetfInterfacesData>,
    Augmentable<Interfaces>
{



    public static final @NonNull QName QNAME = $YangModuleInfoImpl.qnameOf("interfaces");

    @Override
    default Class<org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.interfaces.rev180220.Interfaces> implementedInterface() {
        return org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.interfaces.rev180220.Interfaces.class;
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
    static int bindingHashCode(final org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.interfaces.rev180220.@NonNull Interfaces obj) {
        final int prime = 31;
        int result = 1;
        result = prime * result + Objects.hashCode(obj.getInterface());
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
    static boolean bindingEquals(final org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.interfaces.rev180220.@NonNull Interfaces thisObj, final Object obj) {
        if (thisObj == obj) {
            return true;
        }
        final org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.interfaces.rev180220.Interfaces other = CodeHelpers.checkCast(org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.interfaces.rev180220.Interfaces.class, obj);
        if (other == null) {
            return false;
        }
        if (!Objects.equals(thisObj.getInterface(), other.getInterface())) {
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
    static String bindingToString(final org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.interfaces.rev180220.@NonNull Interfaces obj) {
        final MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper("Interfaces");
        CodeHelpers.appendValue(helper, "interface", obj.getInterface());
        CodeHelpers.appendValue(helper, "augmentation", obj.augmentations().values());
        return helper.toString();
    }
    
    /**
     * Return interface, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         The list of interfaces on the device. The status of an interface is available in
     *         this list in the operational state. If the configuration of a system-controlled
     *         interface cannot be used by the system (e.g., the interface hardware present
     *         does not match the interface type), then the configuration is not applied to the
     *         system-controlled interface shown in the operational state. If the configuration
     *         of a user-controlled interface cannot be used by the system, the configured
     *         interface is not instantiated in the operational state. System-controlled
     *         interfaces created by the system are always present in this list in the
     *         operational state, whether or not they are configured.
     *     </code>
     * </pre>
     *
     * @return {@code java.util.Map} interface, or {@code null} if it is not present.
     *
     */
    @Nullable Map<InterfaceKey, Interface> getInterface();
    
    /**
     * Return interface, or an empty list if it is not present.
     *
     * @return {@code java.util.Map} interface, or an empty list if it is not present.
     *
     */
    default @NonNull Map<InterfaceKey, Interface> nonnullInterface() {
        return CodeHelpers.nonnull(getInterface());
    }

}

