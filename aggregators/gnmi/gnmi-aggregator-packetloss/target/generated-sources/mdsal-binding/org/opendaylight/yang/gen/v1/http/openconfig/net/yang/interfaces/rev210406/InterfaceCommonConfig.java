package org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406;
import java.lang.Boolean;
import java.lang.Class;
import java.lang.Deprecated;
import java.lang.Override;
import java.lang.String;
import javax.annotation.processing.Generated;
import org.eclipse.jdt.annotation.NonNull;
import org.opendaylight.yangtools.yang.binding.DataObject;
import org.opendaylight.yangtools.yang.common.QName;

/**
 * Configuration data data nodes common to physical interfaces and subinterfaces
 *
 * <p>
 * This class represents the following YANG schema fragment defined in module <b>openconfig-interfaces</b>
 * <pre>
 * grouping interface-common-config {
 *   leaf description {
 *     type string;
 *   }
 *   leaf enabled {
 *     type boolean;
 *     default true;
 *   }
 * }
 * </pre>The schema path to identify an instance is
 * <i>openconfig-interfaces/interface-common-config</i>
 *
 */
@Generated("mdsal-binding-generator")
public interface InterfaceCommonConfig
    extends
    DataObject
{



    public static final @NonNull QName QNAME = $YangModuleInfoImpl.qnameOf("interface-common-config");

    @Override
    Class<? extends InterfaceCommonConfig> implementedInterface();
    
    /**
     * Return description, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         A textual description of the interface. A server implementation MAY map this
     *         leaf to the ifAlias MIB object. Such an implementation needs to use some
     *         mechanism to handle the differences in size and characters allowed between this
     *         leaf and ifAlias. The definition of such a mechanism is outside the scope of
     *         this document. Since ifAlias is defined to be stored in non-volatile storage,
     *         the MIB implementation MUST map ifAlias to the value of 'description' in the
     *         persistently stored datastore. Specifically, if the device supports ':startup',
     *         when ifAlias is read the device MUST return the value of 'description' in the
     *         'startup' datastore, and when it is written, it MUST be written to the 'running'
     *         and 'startup' datastores. Note that it is up to the implementation to decide
     *         whether to modify this single leaf in 'startup' or perform an implicit
     *         copy-config from 'running' to 'startup'. If the device does not support
     *         ':startup', ifAlias MUST be mapped to the 'description' leaf in the 'running'
     *         datastore.
     *     </code>
     * </pre>
     *
     * @return {@code java.lang.String} description, or {@code null} if it is not present.
     *
     */
    String getDescription();
    
    /**
     * Return enabled, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         This leaf contains the configured, desired state of the interface. Systems that
     *         implement the IF-MIB use the value of this leaf in the 'running' datastore to
     *         set IF-MIB.ifAdminStatus to 'up' or 'down' after an ifEntry has been
     *         initialized, as described in RFC 2863. Changes in this leaf in the 'running'
     *         datastore are reflected in ifAdminStatus, but if ifAdminStatus is changed over
     *         SNMP, this leaf is not affected.
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

