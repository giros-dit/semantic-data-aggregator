package org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406._interface.ref;
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
import org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.InterfaceRefStateContainer;
import org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406._interface.ref._interface.ref.Config;
import org.opendaylight.yangtools.yang.binding.Augmentable;
import org.opendaylight.yangtools.yang.binding.ChildOf;
import org.opendaylight.yangtools.yang.binding.CodeHelpers;
import org.opendaylight.yangtools.yang.common.QName;

/**
 * Reference to an interface or subinterface
 *
 * <p>
 * This class represents the following YANG schema fragment defined in module <b>openconfig-interfaces</b>
 * <pre>
 * container interface-ref {
 *   container config {
 *     oc-ext:telemetry-on-change;
 *     uses interface-ref-common;
 *   }
 *   uses interface-ref-state-container;
 * }
 * </pre>The schema path to identify an instance is
 * <i>openconfig-interfaces/interface-ref/interface-ref</i>
 *
 * <p>To create instances of this class use {@link InterfaceRefBuilder}.
 * @see InterfaceRefBuilder
 *
 */
@Generated("mdsal-binding-generator")
public interface InterfaceRef
    extends
    ChildOf<org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.InterfaceRef>,
    Augmentable<InterfaceRef>,
    InterfaceRefStateContainer
{



    public static final @NonNull QName QNAME = $YangModuleInfoImpl.qnameOf("interface-ref");

    @Override
    default Class<org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406._interface.ref.InterfaceRef> implementedInterface() {
        return org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406._interface.ref.InterfaceRef.class;
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
    static int bindingHashCode(final org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406._interface.ref.@NonNull InterfaceRef obj) {
        final int prime = 31;
        int result = 1;
        result = prime * result + Objects.hashCode(obj.getConfig());
        result = prime * result + Objects.hashCode(obj.getState());
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
    static boolean bindingEquals(final org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406._interface.ref.@NonNull InterfaceRef thisObj, final Object obj) {
        if (thisObj == obj) {
            return true;
        }
        final org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406._interface.ref.InterfaceRef other = CodeHelpers.checkCast(org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406._interface.ref.InterfaceRef.class, obj);
        if (other == null) {
            return false;
        }
        if (!Objects.equals(thisObj.getConfig(), other.getConfig())) {
            return false;
        }
        if (!Objects.equals(thisObj.getState(), other.getState())) {
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
    static String bindingToString(final org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406._interface.ref.@NonNull InterfaceRef obj) {
        final MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper("InterfaceRef");
        CodeHelpers.appendValue(helper, "config", obj.getConfig());
        CodeHelpers.appendValue(helper, "state", obj.getState());
        CodeHelpers.appendValue(helper, "augmentation", obj.augmentations().values());
        return helper.toString();
    }
    
    /**
     * Return config, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Configured reference to interface / subinterface
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406._interface.ref._interface.ref.Config} config, or {@code null} if it is not present.
     *
     */
    Config getConfig();

}

