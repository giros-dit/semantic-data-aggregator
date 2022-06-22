package org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406;
import java.lang.Class;
import java.lang.Override;
import javax.annotation.processing.Generated;
import org.eclipse.jdt.annotation.NonNull;
import org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.interfaces.top.Interfaces;
import org.opendaylight.yangtools.yang.binding.DataObject;
import org.opendaylight.yangtools.yang.common.QName;

/**
 * Top-level grouping for interface configuration and operational state data
 *
 * <p>
 * This class represents the following YANG schema fragment defined in module <b>openconfig-interfaces</b>
 * <pre>
 * grouping interfaces-top {
 *   container interfaces {
 *     list interface {
 *       key name;
 *       leaf name {
 *         type leafref {
 *           path ../config/name;
 *         }
 *       }
 *       container config {
 *         oc-ext:telemetry-on-change;
 *         uses interface-phys-config;
 *       }
 *       container state {
 *         config false;
 *         uses interface-phys-config;
 *         uses interface-common-state;
 *         uses interface-counters-state;
 *       }
 *       uses interface-phys-holdtime-top;
 *       uses subinterfaces-top;
 *     }
 *   }
 * }
 * </pre>The schema path to identify an instance is
 * <i>openconfig-interfaces/interfaces-top</i>
 *
 */
@Generated("mdsal-binding-generator")
public interface InterfacesTop
    extends
    DataObject
{



    public static final @NonNull QName QNAME = $YangModuleInfoImpl.qnameOf("interfaces-top");

    @Override
    Class<? extends InterfacesTop> implementedInterface();
    
    /**
     * Return interfaces, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Top level container for interfaces, including configuration and state data.
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.interfaces.top.Interfaces} interfaces, or {@code null} if it is not present.
     *
     */
    Interfaces getInterfaces();

}

