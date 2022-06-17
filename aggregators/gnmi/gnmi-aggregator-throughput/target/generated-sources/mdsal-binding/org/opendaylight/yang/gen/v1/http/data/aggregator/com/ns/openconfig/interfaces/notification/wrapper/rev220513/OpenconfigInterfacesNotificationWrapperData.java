package org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.openconfig.interfaces.notification.wrapper.rev220513;
import javax.annotation.processing.Generated;
import org.opendaylight.yangtools.yang.binding.DataRoot;

/**
 * YANG model that provides a wrapper for the notification about the 
 * openconfig-interfaces module.
 *
 * <p>
 * This class represents the following YANG schema fragment defined in module <b>openconfig-interfaces-notification-wrapper</b>
 * <pre>
 * module openconfig-interfaces-notification-wrapper {
 *   yang-version 1.1;
 *   namespace "http://data-aggregator.com/ns/openconfig-interfaces-notification-wrapper";
 *   prefix oc-if-wrapper;
 *   import openconfig-interfaces {
 *     prefix oc-if;
 *   }
 *   import ietf-yang-types {
 *     prefix yang;
 *   }
 *   revision 2022-05-13 {
 *   }
 *   container notification {
 *     config false;
 *     leaf event-time {
 *       type yang:date-and-time;
 *       units true;
 *     }
 *     uses oc-if:interfaces-top;
 *   }
 * }
 * </pre>
 *
 */
@Generated("mdsal-binding-generator")
public interface OpenconfigInterfacesNotificationWrapperData
    extends
    DataRoot
{




    /**
     * Return notification, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Enclosing container for the notification event generated about the
     *         openconfig-interfaces module.
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.openconfig.interfaces.notification.wrapper.rev220513.Notification} notification, or {@code null} if it is not present.
     *
     */
    Notification getNotification();

}

