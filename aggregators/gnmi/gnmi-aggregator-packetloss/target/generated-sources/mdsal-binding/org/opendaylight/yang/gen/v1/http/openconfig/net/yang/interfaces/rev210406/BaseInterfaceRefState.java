package org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406;
import java.lang.Class;
import java.lang.Override;
import javax.annotation.processing.Generated;
import org.eclipse.jdt.annotation.NonNull;
import org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.base._interface.ref.state.State;
import org.opendaylight.yangtools.yang.binding.DataObject;
import org.opendaylight.yangtools.yang.common.QName;

/**
 * Reusable opstate w/container for a reference to a base interface (no 
 * subinterface).
 *
 * <p>
 * This class represents the following YANG schema fragment defined in module <b>openconfig-interfaces</b>
 * <pre>
 * grouping base-interface-ref-state {
 *   container state {
 *     config false;
 *     leaf interface {
 *       type base-interface-ref;
 *     }
 *   }
 * }
 * </pre>The schema path to identify an instance is
 * <i>openconfig-interfaces/base-interface-ref-state</i>
 *
 */
@Generated("mdsal-binding-generator")
public interface BaseInterfaceRefState
    extends
    DataObject
{



    public static final @NonNull QName QNAME = $YangModuleInfoImpl.qnameOf("base-interface-ref-state");

    @Override
    Class<? extends BaseInterfaceRefState> implementedInterface();
    
    /**
     * Return state, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Operational state for base interface reference
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.base._interface.ref.state.State} state, or {@code null} if it is not present.
     *
     */
    State getState();

}

