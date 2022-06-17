package org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406;
import java.lang.Class;
import java.lang.Override;
import javax.annotation.processing.Generated;
import org.eclipse.jdt.annotation.NonNull;
import org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406._interface.ref.state.InterfaceRef;
import org.opendaylight.yangtools.yang.binding.DataObject;
import org.opendaylight.yangtools.yang.common.QName;

/**
 * Reusable opstate w/container for a reference to an interface or subinterface
 *
 * <p>
 * This class represents the following YANG schema fragment defined in module <b>openconfig-interfaces</b>
 * <pre>
 * grouping interface-ref-state {
 *   container interface-ref {
 *     uses interface-ref-state-container;
 *   }
 * }
 * </pre>The schema path to identify an instance is
 * <i>openconfig-interfaces/interface-ref-state</i>
 *
 */
@Generated("mdsal-binding-generator")
public interface InterfaceRefState
    extends
    DataObject
{



    public static final @NonNull QName QNAME = $YangModuleInfoImpl.qnameOf("interface-ref-state");

    @Override
    Class<? extends InterfaceRefState> implementedInterface();
    
    /**
     * Return interfaceRef, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Reference to an interface or subinterface
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406._interface.ref.state.InterfaceRef} interfaceRef, or {@code null} if it is not present.
     *
     */
    InterfaceRef getInterfaceRef();

}

