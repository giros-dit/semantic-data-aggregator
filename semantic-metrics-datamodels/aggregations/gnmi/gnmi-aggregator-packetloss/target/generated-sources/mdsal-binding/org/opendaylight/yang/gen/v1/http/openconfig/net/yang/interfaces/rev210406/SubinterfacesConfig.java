package org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406;
import java.lang.Class;
import java.lang.Override;
import javax.annotation.processing.Generated;
import org.eclipse.jdt.annotation.NonNull;
import org.opendaylight.yangtools.yang.binding.DataObject;
import org.opendaylight.yangtools.yang.common.QName;
import org.opendaylight.yangtools.yang.common.Uint32;

/**
 * Configuration data for subinterfaces
 *
 * <p>
 * This class represents the following YANG schema fragment defined in module <b>openconfig-interfaces</b>
 * <pre>
 * grouping subinterfaces-config {
 *   leaf index {
 *     type uint32;
 *     default 0;
 *   }
 *   uses interface-common-config;
 * }
 * </pre>The schema path to identify an instance is
 * <i>openconfig-interfaces/subinterfaces-config</i>
 *
 */
@Generated("mdsal-binding-generator")
public interface SubinterfacesConfig
    extends
    DataObject,
    InterfaceCommonConfig
{



    public static final @NonNull QName QNAME = $YangModuleInfoImpl.qnameOf("subinterfaces-config");

    @Override
    Class<? extends SubinterfacesConfig> implementedInterface();
    
    /**
     * Return index, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         The index of the subinterface, or logical interface number. On systems with no
     *         support for subinterfaces, or not using subinterfaces, this value should default
     *         to 0, i.e., the default subinterface.
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yangtools.yang.common.Uint32} index, or {@code null} if it is not present.
     *
     */
    Uint32 getIndex();

}

