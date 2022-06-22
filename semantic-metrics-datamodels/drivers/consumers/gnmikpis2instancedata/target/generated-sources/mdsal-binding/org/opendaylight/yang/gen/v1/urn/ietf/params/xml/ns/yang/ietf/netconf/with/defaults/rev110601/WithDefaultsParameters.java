package org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.netconf.with.defaults.rev110601;
import java.lang.Class;
import java.lang.Override;
import javax.annotation.processing.Generated;
import org.eclipse.jdt.annotation.NonNull;
import org.opendaylight.yangtools.yang.binding.DataObject;
import org.opendaylight.yangtools.yang.common.QName;

/**
 * Contains the &lt;with-defaults&gt; parameter for control of defaults in NETCONF 
 * retrieval operations.
 *
 * <p>
 * This class represents the following YANG schema fragment defined in module <b>ietf-netconf-with-defaults</b>
 * <pre>
 * grouping with-defaults-parameters {
 *   leaf with-defaults {
 *     type with-defaults-mode;
 *   }
 * }
 * </pre>The schema path to identify an instance is
 * <i>ietf-netconf-with-defaults/with-defaults-parameters</i>
 *
 */
@Generated("mdsal-binding-generator")
public interface WithDefaultsParameters
    extends
    DataObject
{



    public static final @NonNull QName QNAME = $YangModuleInfoImpl.qnameOf("with-defaults-parameters");

    @Override
    Class<? extends WithDefaultsParameters> implementedInterface();
    
    /**
     * Return withDefaults, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         The explicit defaults processing mode requested.
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.netconf.with.defaults.rev110601.WithDefaultsMode} withDefaults, or {@code null} if it is not present.
     *
     */
    WithDefaultsMode getWithDefaults();

}

