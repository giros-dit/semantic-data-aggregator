package org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.interfaces.top.interfaces._interface;
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
import org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.InterfacePhysConfig;
import org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.interfaces.top.interfaces.Interface;
import org.opendaylight.yangtools.yang.binding.Augmentable;
import org.opendaylight.yangtools.yang.binding.ChildOf;
import org.opendaylight.yangtools.yang.binding.CodeHelpers;
import org.opendaylight.yangtools.yang.common.QName;

/**
 * Configurable items at the global, physical interface level
 *
 * <p>
 * This class represents the following YANG schema fragment defined in module <b>openconfig-interfaces</b>
 * <pre>
 * container config {
 *   oc-ext:telemetry-on-change;
 *   uses interface-phys-config;
 * }
 * </pre>The schema path to identify an instance is
 * <i>openconfig-interfaces/interfaces-top/interfaces/interface/config</i>
 *
 * <p>To create instances of this class use {@link ConfigBuilder}.
 * @see ConfigBuilder
 *
 */
@Generated("mdsal-binding-generator")
public interface Config
    extends
    ChildOf<Interface>,
    Augmentable<Config>,
    InterfacePhysConfig
{



    public static final @NonNull QName QNAME = $YangModuleInfoImpl.qnameOf("config");

    @Override
    default Class<org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.interfaces.top.interfaces._interface.Config> implementedInterface() {
        return org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.interfaces.top.interfaces._interface.Config.class;
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
    static int bindingHashCode(final org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.interfaces.top.interfaces._interface.@NonNull Config obj) {
        final int prime = 31;
        int result = 1;
        result = prime * result + Objects.hashCode(obj.getDescription());
        result = prime * result + Objects.hashCode(obj.getEnabled());
        result = prime * result + Objects.hashCode(obj.getLoopbackMode());
        result = prime * result + Objects.hashCode(obj.getMtu());
        result = prime * result + Objects.hashCode(obj.getName());
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
    static boolean bindingEquals(final org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.interfaces.top.interfaces._interface.@NonNull Config thisObj, final Object obj) {
        if (thisObj == obj) {
            return true;
        }
        final org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.interfaces.top.interfaces._interface.Config other = CodeHelpers.checkCast(org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.interfaces.top.interfaces._interface.Config.class, obj);
        if (other == null) {
            return false;
        }
        if (!Objects.equals(thisObj.getEnabled(), other.getEnabled())) {
            return false;
        }
        if (!Objects.equals(thisObj.getLoopbackMode(), other.getLoopbackMode())) {
            return false;
        }
        if (!Objects.equals(thisObj.getMtu(), other.getMtu())) {
            return false;
        }
        if (!Objects.equals(thisObj.getType(), other.getType())) {
            return false;
        }
        if (!Objects.equals(thisObj.getDescription(), other.getDescription())) {
            return false;
        }
        if (!Objects.equals(thisObj.getName(), other.getName())) {
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
    static String bindingToString(final org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.interfaces.top.interfaces._interface.@NonNull Config obj) {
        final MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper("Config");
        CodeHelpers.appendValue(helper, "description", obj.getDescription());
        CodeHelpers.appendValue(helper, "enabled", obj.getEnabled());
        CodeHelpers.appendValue(helper, "loopbackMode", obj.getLoopbackMode());
        CodeHelpers.appendValue(helper, "mtu", obj.getMtu());
        CodeHelpers.appendValue(helper, "name", obj.getName());
        CodeHelpers.appendValue(helper, "type", obj.getType());
        CodeHelpers.appendValue(helper, "augmentation", obj.augmentations().values());
        return helper.toString();
    }

}

