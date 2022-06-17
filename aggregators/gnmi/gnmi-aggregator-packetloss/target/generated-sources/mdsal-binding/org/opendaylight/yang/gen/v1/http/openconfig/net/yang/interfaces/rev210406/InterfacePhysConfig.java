package org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406;
import java.lang.Boolean;
import java.lang.Class;
import java.lang.Deprecated;
import java.lang.Override;
import java.lang.String;
import javax.annotation.processing.Generated;
import org.eclipse.jdt.annotation.NonNull;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.interfaces.rev180220.InterfaceType;
import org.opendaylight.yangtools.yang.binding.DataObject;
import org.opendaylight.yangtools.yang.common.QName;
import org.opendaylight.yangtools.yang.common.Uint16;

/**
 * Configuration data for physical interfaces
 *
 * <p>
 * This class represents the following YANG schema fragment defined in module <b>openconfig-interfaces</b>
 * <pre>
 * grouping interface-phys-config {
 *   leaf name {
 *     type string;
 *   }
 *   leaf type {
 *     type identityref {
 *       base interface-type;
 *     }
 *   }
 *   leaf mtu {
 *     type uint16;
 *   }
 *   leaf loopback-mode {
 *     type boolean;
 *     default false;
 *   }
 *   uses interface-common-config;
 * }
 * </pre>The schema path to identify an instance is
 * <i>openconfig-interfaces/interface-phys-config</i>
 *
 */
@Generated("mdsal-binding-generator")
public interface InterfacePhysConfig
    extends
    DataObject,
    InterfaceCommonConfig
{



    public static final @NonNull QName QNAME = $YangModuleInfoImpl.qnameOf("interface-phys-config");

    @Override
    Class<? extends InterfacePhysConfig> implementedInterface();
    
    /**
     * Return name, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         The name of the interface. A device MAY restrict the allowed values for this
     *         leaf, possibly depending on the type of the interface. For system-controlled
     *         interfaces, this leaf is the device-specific name of the interface. The 'config
     *         false' list interfaces/interface[name]/state contains the currently existing
     *         interfaces on the device. If a client tries to create configuration for a
     *         system-controlled interface that is not present in the corresponding state list,
     *         the server MAY reject the request if the implementation does not support
     *         pre-provisioning of interfaces or if the name refers to an interface that can
     *         never exist in the system. A NETCONF server MUST reply with an rpc-error with
     *         the error-tag 'invalid-value' in this case. The IETF model in RFC 7223 provides
     *         YANG features for the following (i.e., pre-provisioning and arbitrary-names),
     *         however they are omitted here: If the device supports pre-provisioning of
     *         interface configuration, the 'pre-provisioning' feature is advertised. If the
     *         device allows arbitrarily named user-controlled interfaces, the
     *         'arbitrary-names' feature is advertised. When a configured user-controlled
     *         interface is created by the system, it is instantiated with the same name in the
     *         /interfaces/interface[name]/state list.
     *     </code>
     * </pre>
     *
     * @return {@code java.lang.String} name, or {@code null} if it is not present.
     *
     */
    String getName();
    
    /**
     * Return type, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         The type of the interface. When an interface entry is created, a server MAY
     *         initialize the type leaf with a valid value, e.g., if it is possible to derive
     *         the type from the name of the interface. If a client tries to set the type of an
     *         interface to a value that can never be used by the system, e.g., if the type is
     *         not supported or if the type does not match the name of the interface, the
     *         server MUST reject the request. A NETCONF server MUST reply with an rpc-error
     *         with the error-tag 'invalid-value' in this case.
     *     </code>
     * </pre>
     *
     * @return {@code java.lang.Class} type, or {@code null} if it is not present.
     *
     */
    Class<? extends InterfaceType> getType();
    
    /**
     * Return mtu, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Set the max transmission unit size in octets for the physical interface. If this
     *         is not set, the mtu is set to the operational default -- e.g., 1514 bytes on an
     *         Ethernet interface.
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yangtools.yang.common.Uint16} mtu, or {@code null} if it is not present.
     *
     */
    Uint16 getMtu();
    
    /**
     * Return loopbackMode, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         When set to true, the interface is logically looped back, such that packets that
     *         are forwarded via the interface are received on the same interface.
     *     </code>
     * </pre>
     *
     * @return {@code java.lang.Boolean} loopbackMode, or {@code null} if it is not present.
     *
     */
    Boolean getLoopbackMode();
    
    @Deprecated(forRemoval = true)
    default Boolean isLoopbackMode() {
        return getLoopbackMode();
    }

}

