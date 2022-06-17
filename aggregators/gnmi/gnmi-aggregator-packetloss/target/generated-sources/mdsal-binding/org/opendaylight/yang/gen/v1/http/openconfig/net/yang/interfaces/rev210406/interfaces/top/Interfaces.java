package org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.interfaces.top;
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
import org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.$YangModuleInfoImpl;
import org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.InterfacesTop;
import org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.interfaces.top.interfaces.Interface;
import org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.interfaces.top.interfaces.InterfaceKey;
import org.opendaylight.yangtools.yang.binding.Augmentable;
import org.opendaylight.yangtools.yang.binding.ChildOf;
import org.opendaylight.yangtools.yang.binding.CodeHelpers;
import org.opendaylight.yangtools.yang.common.QName;

/**
 * Top level container for interfaces, including configuration and state data.
 *
 * <p>
 * This class represents the following YANG schema fragment defined in module <b>openconfig-interfaces</b>
 * <pre>
 * container interfaces {
 *   list interface {
 *     key name;
 *     leaf name {
 *       type leafref {
 *         path ../config/name;
 *       }
 *     }
 *     container config {
 *       oc-ext:telemetry-on-change;
 *       uses interface-phys-config;
 *     }
 *     container state {
 *       config false;
 *       uses interface-phys-config;
 *       uses interface-common-state;
 *       uses interface-counters-state;
 *     }
 *     uses interface-phys-holdtime-top;
 *     uses subinterfaces-top;
 *   }
 * }
 * </pre>The schema path to identify an instance is
 * <i>openconfig-interfaces/interfaces-top/interfaces</i>
 *
 * <p>To create instances of this class use {@link InterfacesBuilder}.
 * @see InterfacesBuilder
 *
 */
@Generated("mdsal-binding-generator")
public interface Interfaces
    extends
    ChildOf<InterfacesTop>,
    Augmentable<Interfaces>
{



    public static final @NonNull QName QNAME = $YangModuleInfoImpl.qnameOf("interfaces");

    @Override
    default Class<org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.interfaces.top.Interfaces> implementedInterface() {
        return org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.interfaces.top.Interfaces.class;
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
    static int bindingHashCode(final org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.interfaces.top.@NonNull Interfaces obj) {
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
    static boolean bindingEquals(final org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.interfaces.top.@NonNull Interfaces thisObj, final Object obj) {
        if (thisObj == obj) {
            return true;
        }
        final org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.interfaces.top.Interfaces other = CodeHelpers.checkCast(org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.interfaces.top.Interfaces.class, obj);
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
    static String bindingToString(final org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.interfaces.top.@NonNull Interfaces obj) {
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
     *         The list of named interfaces on the device.
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

