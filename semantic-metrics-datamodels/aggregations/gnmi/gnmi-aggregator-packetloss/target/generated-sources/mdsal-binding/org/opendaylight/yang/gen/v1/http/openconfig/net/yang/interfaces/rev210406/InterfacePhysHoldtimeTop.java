package org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406;
import java.lang.Class;
import java.lang.Override;
import javax.annotation.processing.Generated;
import org.eclipse.jdt.annotation.NonNull;
import org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406._interface.phys.holdtime.top.HoldTime;
import org.opendaylight.yangtools.yang.binding.DataObject;
import org.opendaylight.yangtools.yang.common.QName;

/**
 * Top-level grouping for setting link transition dampening on physical and other 
 * types of interfaces.
 *
 * <p>
 * This class represents the following YANG schema fragment defined in module <b>openconfig-interfaces</b>
 * <pre>
 * grouping interface-phys-holdtime-top {
 *   container hold-time {
 *     container config {
 *       oc-ext:telemetry-on-change;
 *       uses interface-phys-holdtime-config;
 *     }
 *     container state {
 *       config false;
 *       uses interface-phys-holdtime-config;
 *       uses interface-phys-holdtime-state;
 *     }
 *   }
 * }
 * </pre>The schema path to identify an instance is
 * <i>openconfig-interfaces/interface-phys-holdtime-top</i>
 *
 */
@Generated("mdsal-binding-generator")
public interface InterfacePhysHoldtimeTop
    extends
    DataObject
{



    public static final @NonNull QName QNAME = $YangModuleInfoImpl.qnameOf("interface-phys-holdtime-top");

    @Override
    Class<? extends InterfacePhysHoldtimeTop> implementedInterface();
    
    /**
     * Return holdTime, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Top-level container for hold-time settings to enable dampening advertisements of
     *         interface transitions.
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406._interface.phys.holdtime.top.HoldTime} holdTime, or {@code null} if it is not present.
     *
     */
    HoldTime getHoldTime();

}

