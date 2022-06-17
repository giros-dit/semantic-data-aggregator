package org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406;
import java.lang.Class;
import java.lang.Override;
import javax.annotation.processing.Generated;
import org.eclipse.jdt.annotation.NonNull;
import org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.subinterfaces.top.Subinterfaces;
import org.opendaylight.yangtools.yang.binding.DataObject;
import org.opendaylight.yangtools.yang.common.QName;

/**
 * Subinterface data for logical interfaces associated with a given interface
 *
 * <p>
 * This class represents the following YANG schema fragment defined in module <b>openconfig-interfaces</b>
 * <pre>
 * grouping subinterfaces-top {
 *   container subinterfaces {
 *     list subinterface {
 *       key index;
 *       leaf index {
 *         type leafref {
 *           path ../config/index;
 *         }
 *       }
 *       container config {
 *         oc-ext:telemetry-on-change;
 *         uses subinterfaces-config;
 *       }
 *       container state {
 *         config false;
 *         uses subinterfaces-config;
 *         uses subinterfaces-state;
 *       }
 *     }
 *   }
 * }
 * </pre>The schema path to identify an instance is
 * <i>openconfig-interfaces/subinterfaces-top</i>
 *
 */
@Generated("mdsal-binding-generator")
public interface SubinterfacesTop
    extends
    DataObject
{



    public static final @NonNull QName QNAME = $YangModuleInfoImpl.qnameOf("subinterfaces-top");

    @Override
    Class<? extends SubinterfacesTop> implementedInterface();
    
    /**
     * Return subinterfaces, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Enclosing container for the list of subinterfaces associated with a physical
     *         interface
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.subinterfaces.top.Subinterfaces} subinterfaces, or {@code null} if it is not present.
     *
     */
    Subinterfaces getSubinterfaces();

}

