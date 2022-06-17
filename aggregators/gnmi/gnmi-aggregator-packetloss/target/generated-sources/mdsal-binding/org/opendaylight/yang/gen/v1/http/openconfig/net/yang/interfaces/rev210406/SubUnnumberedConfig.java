package org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406;
import java.lang.Boolean;
import java.lang.Class;
import java.lang.Deprecated;
import java.lang.Override;
import javax.annotation.processing.Generated;
import org.eclipse.jdt.annotation.NonNull;
import org.opendaylight.yangtools.yang.binding.DataObject;
import org.opendaylight.yangtools.yang.common.QName;

/**
 * Configuration data for unnumbered subinterfaces
 *
 * <p>
 * This class represents the following YANG schema fragment defined in module <b>openconfig-interfaces</b>
 * <pre>
 * grouping sub-unnumbered-config {
 *   leaf enabled {
 *     type boolean;
 *     default false;
 *   }
 * }
 * </pre>The schema path to identify an instance is
 * <i>openconfig-interfaces/sub-unnumbered-config</i>
 *
 */
@Generated("mdsal-binding-generator")
public interface SubUnnumberedConfig
    extends
    DataObject
{



    public static final @NonNull QName QNAME = $YangModuleInfoImpl.qnameOf("sub-unnumbered-config");

    @Override
    Class<? extends SubUnnumberedConfig> implementedInterface();
    
    /**
     * Return enabled, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Indicates that the subinterface is unnumbered. By default the subinterface is
     *         numbered, i.e., expected to have an IP address configuration.
     *     </code>
     * </pre>
     *
     * @return {@code java.lang.Boolean} enabled, or {@code null} if it is not present.
     *
     */
    Boolean getEnabled();
    
    @Deprecated(forRemoval = true)
    default Boolean isEnabled() {
        return getEnabled();
    }

}

