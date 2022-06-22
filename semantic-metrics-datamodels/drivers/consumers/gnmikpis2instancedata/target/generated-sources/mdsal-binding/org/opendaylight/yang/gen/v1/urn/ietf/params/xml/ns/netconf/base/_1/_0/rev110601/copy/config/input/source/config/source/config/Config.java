package org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.netconf.base._1._0.rev110601.copy.config.input.source.config.source.config;
import java.lang.Class;
import java.lang.Override;
import javax.annotation.processing.Generated;
import org.eclipse.jdt.annotation.NonNull;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.netconf.base._1._0.rev110601.$YangModuleInfoImpl;
import org.opendaylight.yangtools.yang.binding.ChildOf;
import org.opendaylight.yangtools.yang.binding.OpaqueObject;
import org.opendaylight.yangtools.yang.common.QName;

/**
 * Inline Config content: &lt;config&gt; element. Represents an entire 
 * configuration datastore, not a subset of the running datastore.
 *
 * <p>
 * This class represents the following YANG schema fragment defined in module <b>ietf-netconf</b>
 * <pre>
 * anyxml config {
 * }
 * </pre>The schema path to identify an instance is
 * <i>ietf-netconf/copy-config/input/source/config-source/config/config</i>
 *
 */
@Generated("mdsal-binding-generator")
public interface Config
    extends
    OpaqueObject<Config>,
    ChildOf<org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.netconf.base._1._0.rev110601.copy.config.input.source.config.source.Config>
{



    public static final @NonNull QName QNAME = $YangModuleInfoImpl.qnameOf("config");

    @Override
    default Class<org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.netconf.base._1._0.rev110601.copy.config.input.source.config.source.config.Config> implementedInterface() {
        return org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.netconf.base._1._0.rev110601.copy.config.input.source.config.source.config.Config.class;
    }

}

