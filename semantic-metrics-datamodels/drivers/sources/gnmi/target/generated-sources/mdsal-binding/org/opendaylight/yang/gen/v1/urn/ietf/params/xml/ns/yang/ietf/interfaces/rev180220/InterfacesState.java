package org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.interfaces.rev180220;
import com.google.common.base.MoreObjects;
import java.lang.Class;
import java.lang.Deprecated;
import java.lang.NullPointerException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.util.Map;
import java.util.Objects;
import javax.annotation.processing.Generated;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.interfaces.rev180220.interfaces.state.Interface;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.interfaces.rev180220.interfaces.state.InterfaceKey;
import org.opendaylight.yangtools.yang.binding.Augmentable;
import org.opendaylight.yangtools.yang.binding.ChildOf;
import org.opendaylight.yangtools.yang.binding.CodeHelpers;
import org.opendaylight.yangtools.yang.common.QName;

/**
 * Data nodes for the operational state of interfaces.
 *
 * <p>
 * This class represents the following YANG schema fragment defined in module <b>ietf-interfaces</b>
 * <pre>
 * container interfaces-state {
 *   config false;
 *   status deprecated;
 *   list interface {
 *     key name;
 *     status deprecated;
 *     leaf name {
 *       type string;
 *       status deprecated;
 *     }
 *     leaf type {
 *       type identityref {
 *         base interface-type;
 *       }
 *       status deprecated;
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
 *       status deprecated;
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
 *       status deprecated;
 *     }
 *     leaf last-change {
 *       type yang:date-and-time;
 *       status deprecated;
 *     }
 *     leaf if-index {
 *       if-feature if-mib;
 *       type int32 {
 *         range 1..2147483647;
 *       }
 *       status deprecated;
 *     }
 *     leaf phys-address {
 *       type yang:phys-address;
 *       status deprecated;
 *     }
 *     leaf-list higher-layer-if {
 *       type interface-state-ref;
 *       status deprecated;
 *     }
 *     leaf-list lower-layer-if {
 *       type interface-state-ref;
 *       status deprecated;
 *     }
 *     leaf speed {
 *       type yang:gauge64;
 *       units bits/second;
 *       status deprecated;
 *     }
 *     container statistics {
 *       status deprecated;
 *       leaf discontinuity-time {
 *         type yang:date-and-time;
 *         status deprecated;
 *       }
 *       leaf in-octets {
 *         type yang:counter64;
 *         status deprecated;
 *       }
 *       leaf in-unicast-pkts {
 *         type yang:counter64;
 *         status deprecated;
 *       }
 *       leaf in-broadcast-pkts {
 *         type yang:counter64;
 *         status deprecated;
 *       }
 *       leaf in-multicast-pkts {
 *         type yang:counter64;
 *         status deprecated;
 *       }
 *       leaf in-discards {
 *         type yang:counter32;
 *         status deprecated;
 *       }
 *       leaf in-errors {
 *         type yang:counter32;
 *         status deprecated;
 *       }
 *       leaf in-unknown-protos {
 *         type yang:counter32;
 *         status deprecated;
 *       }
 *       leaf out-octets {
 *         type yang:counter64;
 *         status deprecated;
 *       }
 *       leaf out-unicast-pkts {
 *         type yang:counter64;
 *         status deprecated;
 *       }
 *       leaf out-broadcast-pkts {
 *         type yang:counter64;
 *         status deprecated;
 *       }
 *       leaf out-multicast-pkts {
 *         type yang:counter64;
 *         status deprecated;
 *       }
 *       leaf out-discards {
 *         type yang:counter32;
 *         status deprecated;
 *       }
 *       leaf out-errors {
 *         type yang:counter32;
 *         status deprecated;
 *       }
 *     }
 *   }
 * }
 * </pre>The schema path to identify an instance is
 * <i>ietf-interfaces/interfaces-state</i>
 *
 * <p>To create instances of this class use {@link InterfacesStateBuilder}.
 * @see InterfacesStateBuilder
 *
 */
@Deprecated
@Generated("mdsal-binding-generator")
public interface InterfacesState
    extends
    ChildOf<IetfInterfacesData>,
    Augmentable<InterfacesState>
{



    public static final @NonNull QName QNAME = $YangModuleInfoImpl.qnameOf("interfaces-state");

    @Override
    default Class<org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.interfaces.rev180220.InterfacesState> implementedInterface() {
        return org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.interfaces.rev180220.InterfacesState.class;
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
    static int bindingHashCode(final org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.interfaces.rev180220.@NonNull InterfacesState obj) {
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
    static boolean bindingEquals(final org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.interfaces.rev180220.@NonNull InterfacesState thisObj, final Object obj) {
        if (thisObj == obj) {
            return true;
        }
        final org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.interfaces.rev180220.InterfacesState other = CodeHelpers.checkCast(org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.interfaces.rev180220.InterfacesState.class, obj);
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
    static String bindingToString(final org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.interfaces.rev180220.@NonNull InterfacesState obj) {
        final MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper("InterfacesState");
        CodeHelpers.appendValue(helper, "interface", obj.getInterface());
        CodeHelpers.appendValue(helper, "augmentation", obj.augmentations().values());
        return helper.toString();
    }
    
    /**
     * Return interface, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         The list of interfaces on the device. System-controlled interfaces created by
     *         the system are always present in this list, whether or not they are configured.
     *     </code>
     * </pre>
     *
     * @return {@code java.util.Map} interface, or {@code null} if it is not present.
     *
     */
    @Deprecated
    @Nullable Map<InterfaceKey, Interface> getInterface();
    
    /**
     * Return interface, or an empty list if it is not present.
     *
     * @return {@code java.util.Map} interface, or an empty list if it is not present.
     *
     */
    @Deprecated
    default @NonNull Map<InterfaceKey, Interface> nonnullInterface() {
        return CodeHelpers.nonnull(getInterface());
    }

}

