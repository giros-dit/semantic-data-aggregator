package org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406;
import java.lang.Class;
import java.lang.Override;
import javax.annotation.processing.Generated;
import org.eclipse.jdt.annotation.NonNull;
import org.opendaylight.yangtools.yang.binding.DataObject;
import org.opendaylight.yangtools.yang.common.QName;

/**
 * Reusable definition for a reference to an interface or subinterface
 *
 * <p>
 * This class represents the following YANG schema fragment defined in module <b>openconfig-interfaces</b>
 * <pre>
 * grouping interface-ref {
 *   container interface-ref {
 *     container config {
 *       oc-ext:telemetry-on-change;
 *       uses interface-ref-common;
 *     }
 *     uses interface-ref-state-container;
 *   }
 * }
 * </pre>The schema path to identify an instance is
 * <i>openconfig-interfaces/interface-ref</i>
 *
 */
@Generated("mdsal-binding-generator")
public interface InterfaceRef
    extends
    DataObject
{



    public static final @NonNull QName QNAME = $YangModuleInfoImpl.qnameOf("interface-ref");

    @Override
    Class<? extends InterfaceRef> implementedInterface();
    
    /**
     * Return interfaceRef, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Reference to an interface or subinterface
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406._interface.ref.InterfaceRef} interfaceRef, or {@code null} if it is not present.
     *
     */
    org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406._interface.ref.InterfaceRef getInterfaceRef();

}

