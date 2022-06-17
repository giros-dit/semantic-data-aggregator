package org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406;
import java.lang.Class;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import javax.annotation.processing.Generated;
import org.eclipse.jdt.annotation.NonNull;
import org.opendaylight.yangtools.yang.binding.DataObject;
import org.opendaylight.yangtools.yang.common.QName;

/**
 * Reference leafrefs to interface / subinterface
 *
 * <p>
 * This class represents the following YANG schema fragment defined in module <b>openconfig-interfaces</b>
 * <pre>
 * grouping interface-ref-common {
 *   leaf interface {
 *     type leafref {
 *       path /oc-if:interfaces/oc-if:interface/oc-if:name;
 *     }
 *   }
 *   leaf subinterface {
 *     type leafref {
 *       path /oc-if:interfaces/oc-if:interface[oc-if:name=current()/../interface]/oc-if:subinterfaces/oc-if:subinterface/oc-if:index;
 *     }
 *   }
 * }
 * </pre>The schema path to identify an instance is
 * <i>openconfig-interfaces/interface-ref-common</i>
 *
 */
@Generated("mdsal-binding-generator")
public interface InterfaceRefCommon
    extends
    DataObject
{



    public static final @NonNull QName QNAME = $YangModuleInfoImpl.qnameOf("interface-ref-common");

    @Override
    Class<? extends InterfaceRefCommon> implementedInterface();
    
    /**
     * Return interface, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Reference to a base interface. If a reference to a subinterface is required,
     *         this leaf must be specified to indicate the base interface.
     *     </code>
     * </pre>
     *
     * @return {@code java.lang.String} interface, or {@code null} if it is not present.
     *
     */
    String getInterface();
    
    /**
     * Return subinterface, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Reference to a subinterface -- this requires the base interface to be specified
     *         using the interface leaf in this container. If only a reference to a base
     *         interface is requuired, this leaf should not be set.
     *     </code>
     * </pre>
     *
     * @return {@code java.lang.Object} subinterface, or {@code null} if it is not present.
     *
     */
    Object getSubinterface();

}

