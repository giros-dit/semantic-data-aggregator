package org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406;
import java.lang.Class;
import java.lang.Override;
import javax.annotation.processing.Generated;
import org.eclipse.jdt.annotation.NonNull;
import org.opendaylight.yangtools.yang.binding.DataObject;
import org.opendaylight.yangtools.yang.common.QName;

/**
 * Operational state data for interface hold-time.
 *
 * <p>
 * This class represents the following YANG schema fragment defined in module <b>openconfig-interfaces</b>
 * <pre>
 * grouping interface-phys-holdtime-state {
 * }
 * </pre>The schema path to identify an instance is
 * <i>openconfig-interfaces/interface-phys-holdtime-state</i>
 *
 */
@Generated("mdsal-binding-generator")
public interface InterfacePhysHoldtimeState
    extends
    DataObject
{



    public static final @NonNull QName QNAME = $YangModuleInfoImpl.qnameOf("interface-phys-holdtime-state");

    @Override
    Class<? extends InterfacePhysHoldtimeState> implementedInterface();

}

