package org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406;
import java.lang.Class;
import java.lang.Override;
import javax.annotation.processing.Generated;
import org.eclipse.jdt.annotation.NonNull;
import org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406._interface.ref.state.container.State;
import org.opendaylight.yangtools.yang.binding.DataObject;
import org.opendaylight.yangtools.yang.common.QName;

/**
 * Reusable opstate w/container for a reference to an interface or subinterface
 *
 * <p>
 * This class represents the following YANG schema fragment defined in module <b>openconfig-interfaces</b>
 * <pre>
 * grouping interface-ref-state-container {
 *   container state {
 *     config false;
 *     uses interface-ref-common;
 *   }
 * }
 * </pre>The schema path to identify an instance is
 * <i>openconfig-interfaces/interface-ref-state-container</i>
 *
 */
@Generated("mdsal-binding-generator")
public interface InterfaceRefStateContainer
    extends
    DataObject
{



    public static final @NonNull QName QNAME = $YangModuleInfoImpl.qnameOf("interface-ref-state-container");

    @Override
    Class<? extends InterfaceRefStateContainer> implementedInterface();
    
    /**
     * Return state, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Operational state for interface-ref
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406._interface.ref.state.container.State} state, or {@code null} if it is not present.
     *
     */
    State getState();

}

