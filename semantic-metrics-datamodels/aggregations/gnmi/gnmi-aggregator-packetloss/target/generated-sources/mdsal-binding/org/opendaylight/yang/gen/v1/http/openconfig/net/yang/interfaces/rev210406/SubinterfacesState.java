package org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import javax.annotation.processing.Generated;
import org.eclipse.jdt.annotation.NonNull;
import org.opendaylight.yangtools.yang.binding.DataObject;
import org.opendaylight.yangtools.yang.common.QName;

/**
 * Operational state data for subinterfaces
 *
 * <p>
 * This class represents the following YANG schema fragment defined in module <b>openconfig-interfaces</b>
 * <pre>
 * grouping subinterfaces-state {
 *   oc-ext:operational;
 *   leaf name {
 *     type string;
 *     oc-ext:telemetry-on-change;
 *   }
 *   uses interface-common-state;
 *   uses interface-counters-state;
 * }
 * </pre>The schema path to identify an instance is
 * <i>openconfig-interfaces/subinterfaces-state</i>
 *
 */
@Generated("mdsal-binding-generator")
public interface SubinterfacesState
    extends
    DataObject,
    InterfaceCommonState,
    InterfaceCountersState
{



    public static final @NonNull QName QNAME = $YangModuleInfoImpl.qnameOf("subinterfaces-state");

    @Override
    Class<? extends SubinterfacesState> implementedInterface();
    
    /**
     * Return name, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         The system-assigned name for the sub-interface. This MAY be a combination of the
     *         base interface name and the subinterface index, or some other convention used by
     *         the system.
     *     </code>
     * </pre>
     *
     * @return {@code java.lang.String} name, or {@code null} if it is not present.
     *
     */
    String getName();

}

