package org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.subinterfaces.top.subinterfaces;
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
import org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.subinterfaces.top.Subinterfaces;
import org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.subinterfaces.top.subinterfaces.subinterface.Config;
import org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.subinterfaces.top.subinterfaces.subinterface.State;
import org.opendaylight.yangtools.yang.binding.Augmentable;
import org.opendaylight.yangtools.yang.binding.ChildOf;
import org.opendaylight.yangtools.yang.binding.CodeHelpers;
import org.opendaylight.yangtools.yang.binding.Identifiable;
import org.opendaylight.yangtools.yang.common.QName;
import org.opendaylight.yangtools.yang.common.Uint32;

/**
 * The list of subinterfaces (logical interfaces) associated with a physical 
 * interface
 *
 * <p>
 * This class represents the following YANG schema fragment defined in module <b>openconfig-interfaces</b>
 * <pre>
 * list subinterface {
 *   key index;
 *   leaf index {
 *     type leafref {
 *       path ../config/index;
 *     }
 *   }
 *   container config {
 *     oc-ext:telemetry-on-change;
 *     uses subinterfaces-config;
 *   }
 *   container state {
 *     config false;
 *     uses subinterfaces-config;
 *     uses subinterfaces-state;
 *   }
 * }
 * </pre>The schema path to identify an instance is
 * <i>openconfig-interfaces/subinterfaces-top/subinterfaces/subinterface</i>
 *
 * <p>To create instances of this class use {@link SubinterfaceBuilder}.
 * @see SubinterfaceBuilder
 * @see SubinterfaceKey
 *
 */
@Generated("mdsal-binding-generator")
public interface Subinterface
    extends
    ChildOf<Subinterfaces>,
    Augmentable<Subinterface>,
    Identifiable<SubinterfaceKey>
{



    public static final @NonNull QName QNAME = $YangModuleInfoImpl.qnameOf("subinterface");

    @Override
    default Class<org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.subinterfaces.top.subinterfaces.Subinterface> implementedInterface() {
        return org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.subinterfaces.top.subinterfaces.Subinterface.class;
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
    static int bindingHashCode(final org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.subinterfaces.top.subinterfaces.@NonNull Subinterface obj) {
        final int prime = 31;
        int result = 1;
        result = prime * result + Objects.hashCode(obj.getConfig());
        result = prime * result + Objects.hashCode(obj.getIndex());
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
    static boolean bindingEquals(final org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.subinterfaces.top.subinterfaces.@NonNull Subinterface thisObj, final Object obj) {
        if (thisObj == obj) {
            return true;
        }
        final org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.subinterfaces.top.subinterfaces.Subinterface other = CodeHelpers.checkCast(org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.subinterfaces.top.subinterfaces.Subinterface.class, obj);
        if (other == null) {
            return false;
        }
        if (!Objects.equals(thisObj.getIndex(), other.getIndex())) {
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
    static String bindingToString(final org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.subinterfaces.top.subinterfaces.@NonNull Subinterface obj) {
        final MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper("Subinterface");
        CodeHelpers.appendValue(helper, "config", obj.getConfig());
        CodeHelpers.appendValue(helper, "index", obj.getIndex());
        CodeHelpers.appendValue(helper, "state", obj.getState());
        CodeHelpers.appendValue(helper, "augmentation", obj.augmentations().values());
        return helper.toString();
    }
    
    /**
     * Return index, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         The index number of the subinterface -- used to address the logical interface
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yangtools.yang.common.Uint32} index, or {@code null} if it is not present.
     *
     */
    Uint32 getIndex();
    
    /**
     * Return config, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Configurable items at the subinterface level
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.subinterfaces.top.subinterfaces.subinterface.Config} config, or {@code null} if it is not present.
     *
     */
    Config getConfig();
    
    /**
     * Return state, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Operational state data for logical interfaces
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.subinterfaces.top.subinterfaces.subinterface.State} state, or {@code null} if it is not present.
     *
     */
    State getState();
    
    @Override
    SubinterfaceKey key();

}

