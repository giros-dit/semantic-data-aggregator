package org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406;
import java.lang.Class;
import java.lang.Override;
import javax.annotation.processing.Generated;
import org.eclipse.jdt.annotation.NonNull;
import org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.sub.unnumbered.top.Unnumbered;
import org.opendaylight.yangtools.yang.binding.DataObject;
import org.opendaylight.yangtools.yang.common.QName;

/**
 * Top-level grouping unnumbered subinterfaces
 *
 * <p>
 * This class represents the following YANG schema fragment defined in module <b>openconfig-interfaces</b>
 * <pre>
 * grouping sub-unnumbered-top {
 *   container unnumbered {
 *     container config {
 *       oc-ext:telemetry-on-change;
 *       uses sub-unnumbered-config;
 *     }
 *     container state {
 *       config false;
 *       uses sub-unnumbered-config;
 *       uses sub-unnumbered-state;
 *     }
 *     uses oc-if:interface-ref;
 *   }
 * }
 * </pre>The schema path to identify an instance is
 * <i>openconfig-interfaces/sub-unnumbered-top</i>
 *
 */
@Generated("mdsal-binding-generator")
public interface SubUnnumberedTop
    extends
    DataObject
{



    public static final @NonNull QName QNAME = $YangModuleInfoImpl.qnameOf("sub-unnumbered-top");

    @Override
    Class<? extends SubUnnumberedTop> implementedInterface();
    
    /**
     * Return unnumbered, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Top-level container for setting unnumbered interfaces. Includes reference the
     *         interface that provides the address information
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.sub.unnumbered.top.Unnumbered} unnumbered, or {@code null} if it is not present.
     *
     */
    Unnumbered getUnnumbered();

}

