package org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.subinterfaces.top;
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
import org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.SubinterfacesTop;
import org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.subinterfaces.top.subinterfaces.Subinterface;
import org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.subinterfaces.top.subinterfaces.SubinterfaceKey;
import org.opendaylight.yangtools.yang.binding.Augmentable;
import org.opendaylight.yangtools.yang.binding.ChildOf;
import org.opendaylight.yangtools.yang.binding.CodeHelpers;
import org.opendaylight.yangtools.yang.common.QName;

/**
 * Enclosing container for the list of subinterfaces associated with a physical 
 * interface
 *
 * <p>
 * This class represents the following YANG schema fragment defined in module <b>openconfig-interfaces</b>
 * <pre>
 * container subinterfaces {
 *   list subinterface {
 *     key index;
 *     leaf index {
 *       type leafref {
 *         path ../config/index;
 *       }
 *     }
 *     container config {
 *       oc-ext:telemetry-on-change;
 *       uses subinterfaces-config;
 *     }
 *     container state {
 *       config false;
 *       uses subinterfaces-config;
 *       uses subinterfaces-state;
 *     }
 *   }
 * }
 * </pre>The schema path to identify an instance is
 * <i>openconfig-interfaces/subinterfaces-top/subinterfaces</i>
 *
 * <p>To create instances of this class use {@link SubinterfacesBuilder}.
 * @see SubinterfacesBuilder
 *
 */
@Generated("mdsal-binding-generator")
public interface Subinterfaces
    extends
    ChildOf<SubinterfacesTop>,
    Augmentable<Subinterfaces>
{



    public static final @NonNull QName QNAME = $YangModuleInfoImpl.qnameOf("subinterfaces");

    @Override
    default Class<org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.subinterfaces.top.Subinterfaces> implementedInterface() {
        return org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.subinterfaces.top.Subinterfaces.class;
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
    static int bindingHashCode(final org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.subinterfaces.top.@NonNull Subinterfaces obj) {
        final int prime = 31;
        int result = 1;
        result = prime * result + Objects.hashCode(obj.getSubinterface());
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
    static boolean bindingEquals(final org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.subinterfaces.top.@NonNull Subinterfaces thisObj, final Object obj) {
        if (thisObj == obj) {
            return true;
        }
        final org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.subinterfaces.top.Subinterfaces other = CodeHelpers.checkCast(org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.subinterfaces.top.Subinterfaces.class, obj);
        if (other == null) {
            return false;
        }
        if (!Objects.equals(thisObj.getSubinterface(), other.getSubinterface())) {
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
    static String bindingToString(final org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.subinterfaces.top.@NonNull Subinterfaces obj) {
        final MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper("Subinterfaces");
        CodeHelpers.appendValue(helper, "subinterface", obj.getSubinterface());
        CodeHelpers.appendValue(helper, "augmentation", obj.augmentations().values());
        return helper.toString();
    }
    
    /**
     * Return subinterface, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         The list of subinterfaces (logical interfaces) associated with a physical
     *         interface
     *     </code>
     * </pre>
     *
     * @return {@code java.util.Map} subinterface, or {@code null} if it is not present.
     *
     */
    @Nullable Map<SubinterfaceKey, Subinterface> getSubinterface();
    
    /**
     * Return subinterface, or an empty list if it is not present.
     *
     * @return {@code java.util.Map} subinterface, or an empty list if it is not present.
     *
     */
    default @NonNull Map<SubinterfaceKey, Subinterface> nonnullSubinterface() {
        return CodeHelpers.nonnull(getSubinterface());
    }

}

