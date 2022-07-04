package org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406._interface.phys.holdtime.top.hold.time;
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
import org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.InterfacePhysHoldtimeConfig;
import org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.InterfacePhysHoldtimeState;
import org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406._interface.phys.holdtime.top.HoldTime;
import org.opendaylight.yangtools.yang.binding.Augmentable;
import org.opendaylight.yangtools.yang.binding.ChildOf;
import org.opendaylight.yangtools.yang.binding.CodeHelpers;
import org.opendaylight.yangtools.yang.common.QName;

/**
 * Operational state data for interface hold-time.
 *
 * <p>
 * This class represents the following YANG schema fragment defined in module <b>openconfig-interfaces</b>
 * <pre>
 * container state {
 *   config false;
 *   uses interface-phys-holdtime-config;
 *   uses interface-phys-holdtime-state;
 * }
 * </pre>The schema path to identify an instance is
 * <i>openconfig-interfaces/interface-phys-holdtime-top/hold-time/state</i>
 *
 * <p>To create instances of this class use {@link StateBuilder}.
 * @see StateBuilder
 *
 */
@Generated("mdsal-binding-generator")
public interface State
    extends
    ChildOf<HoldTime>,
    Augmentable<State>,
    InterfacePhysHoldtimeConfig,
    InterfacePhysHoldtimeState
{



    public static final @NonNull QName QNAME = $YangModuleInfoImpl.qnameOf("state");

    @Override
    default Class<org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406._interface.phys.holdtime.top.hold.time.State> implementedInterface() {
        return org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406._interface.phys.holdtime.top.hold.time.State.class;
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
    static int bindingHashCode(final org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406._interface.phys.holdtime.top.hold.time.@NonNull State obj) {
        final int prime = 31;
        int result = 1;
        result = prime * result + Objects.hashCode(obj.getDown());
        result = prime * result + Objects.hashCode(obj.getUp());
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
    static boolean bindingEquals(final org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406._interface.phys.holdtime.top.hold.time.@NonNull State thisObj, final Object obj) {
        if (thisObj == obj) {
            return true;
        }
        final org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406._interface.phys.holdtime.top.hold.time.State other = CodeHelpers.checkCast(org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406._interface.phys.holdtime.top.hold.time.State.class, obj);
        if (other == null) {
            return false;
        }
        if (!Objects.equals(thisObj.getDown(), other.getDown())) {
            return false;
        }
        if (!Objects.equals(thisObj.getUp(), other.getUp())) {
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
    static String bindingToString(final org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406._interface.phys.holdtime.top.hold.time.@NonNull State obj) {
        final MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper("State");
        CodeHelpers.appendValue(helper, "down", obj.getDown());
        CodeHelpers.appendValue(helper, "up", obj.getUp());
        CodeHelpers.appendValue(helper, "augmentation", obj.augmentations().values());
        return helper.toString();
    }

}

