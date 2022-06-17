package org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.openconfig.interfaces.notification.wrapper.rev220513;
import com.google.common.base.MoreObjects;
import java.lang.Class;
import java.lang.NullPointerException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.util.Objects;
import javax.annotation.processing.Generated;
import org.eclipse.jdt.annotation.NonNull;
import org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.InterfacesTop;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715.DateAndTime;
import org.opendaylight.yangtools.yang.binding.Augmentable;
import org.opendaylight.yangtools.yang.binding.ChildOf;
import org.opendaylight.yangtools.yang.binding.CodeHelpers;
import org.opendaylight.yangtools.yang.common.QName;

/**
 * Enclosing container for the notification event generated about the 
 * openconfig-interfaces module.
 *
 * <p>
 * This class represents the following YANG schema fragment defined in module <b>openconfig-interfaces-notification-wrapper</b>
 * <pre>
 * container notification {
 *   config false;
 *   leaf event-time {
 *     type yang:date-and-time;
 *     units true;
 *   }
 *   uses oc-if:interfaces-top;
 * }
 * </pre>The schema path to identify an instance is
 * <i>openconfig-interfaces-notification-wrapper/notification</i>
 *
 * <p>To create instances of this class use {@link NotificationBuilder}.
 * @see NotificationBuilder
 *
 */
@Generated("mdsal-binding-generator")
public interface Notification
    extends
    ChildOf<OpenconfigInterfacesNotificationWrapperData>,
    Augmentable<Notification>,
    InterfacesTop
{



    public static final @NonNull QName QNAME = $YangModuleInfoImpl.qnameOf("notification");

    @Override
    default Class<org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.openconfig.interfaces.notification.wrapper.rev220513.Notification> implementedInterface() {
        return org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.openconfig.interfaces.notification.wrapper.rev220513.Notification.class;
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
    static int bindingHashCode(final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.openconfig.interfaces.notification.wrapper.rev220513.@NonNull Notification obj) {
        final int prime = 31;
        int result = 1;
        result = prime * result + Objects.hashCode(obj.getEventTime());
        result = prime * result + Objects.hashCode(obj.getInterfaces());
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
    static boolean bindingEquals(final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.openconfig.interfaces.notification.wrapper.rev220513.@NonNull Notification thisObj, final Object obj) {
        if (thisObj == obj) {
            return true;
        }
        final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.openconfig.interfaces.notification.wrapper.rev220513.Notification other = CodeHelpers.checkCast(org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.openconfig.interfaces.notification.wrapper.rev220513.Notification.class, obj);
        if (other == null) {
            return false;
        }
        if (!Objects.equals(thisObj.getEventTime(), other.getEventTime())) {
            return false;
        }
        if (!Objects.equals(thisObj.getInterfaces(), other.getInterfaces())) {
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
    static String bindingToString(final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.openconfig.interfaces.notification.wrapper.rev220513.@NonNull Notification obj) {
        final MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper("Notification");
        CodeHelpers.appendValue(helper, "eventTime", obj.getEventTime());
        CodeHelpers.appendValue(helper, "interfaces", obj.getInterfaces());
        CodeHelpers.appendValue(helper, "augmentation", obj.augmentations().values());
        return helper.toString();
    }
    
    /**
     * Return eventTime, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Date and time in which the notification event was generated (following the ISO
     *         8601 format).
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715.DateAndTime} eventTime, or {@code null} if it is not present.
     *
     */
    DateAndTime getEventTime();

}

