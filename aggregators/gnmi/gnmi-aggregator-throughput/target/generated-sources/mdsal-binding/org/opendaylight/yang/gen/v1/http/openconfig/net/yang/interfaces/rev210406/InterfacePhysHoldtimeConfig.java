package org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406;
import java.lang.Class;
import java.lang.Override;
import javax.annotation.processing.Generated;
import org.eclipse.jdt.annotation.NonNull;
import org.opendaylight.yangtools.yang.binding.DataObject;
import org.opendaylight.yangtools.yang.common.QName;
import org.opendaylight.yangtools.yang.common.Uint32;

/**
 * Configuration data for interface hold-time settings -- applies to physical 
 * interfaces.
 *
 * <p>
 * This class represents the following YANG schema fragment defined in module <b>openconfig-interfaces</b>
 * <pre>
 * grouping interface-phys-holdtime-config {
 *   leaf up {
 *     type uint32;
 *     units milliseconds;
 *     default 0;
 *   }
 *   leaf down {
 *     type uint32;
 *     units milliseconds;
 *     default 0;
 *   }
 * }
 * </pre>The schema path to identify an instance is
 * <i>openconfig-interfaces/interface-phys-holdtime-config</i>
 *
 */
@Generated("mdsal-binding-generator")
public interface InterfacePhysHoldtimeConfig
    extends
    DataObject
{



    public static final @NonNull QName QNAME = $YangModuleInfoImpl.qnameOf("interface-phys-holdtime-config");

    @Override
    Class<? extends InterfacePhysHoldtimeConfig> implementedInterface();
    
    /**
     * Return up, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Dampens advertisement when the interface transitions from down to up. A zero
     *         value means dampening is turned off, i.e., immediate notification.
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yangtools.yang.common.Uint32} up, or {@code null} if it is not present.
     *
     */
    Uint32 getUp();
    
    /**
     * Return down, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Dampens advertisement when the interface transitions from up to down. A zero
     *         value means dampening is turned off, i.e., immediate notification.
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yangtools.yang.common.Uint32} down, or {@code null} if it is not present.
     *
     */
    Uint32 getDown();

}

