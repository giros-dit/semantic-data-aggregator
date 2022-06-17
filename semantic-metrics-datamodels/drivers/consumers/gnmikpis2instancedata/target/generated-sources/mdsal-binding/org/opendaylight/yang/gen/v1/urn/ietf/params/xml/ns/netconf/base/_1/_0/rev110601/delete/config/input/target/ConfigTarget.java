package org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.netconf.base._1._0.rev110601.delete.config.input.target;
import javax.annotation.processing.Generated;
import org.eclipse.jdt.annotation.NonNull;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.netconf.base._1._0.rev110601.$YangModuleInfoImpl;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.netconf.base._1._0.rev110601.delete.config.input.Target;
import org.opendaylight.yangtools.yang.binding.ChoiceIn;
import org.opendaylight.yangtools.yang.common.QName;

/**
 * The configuration target to delete.
 *
 * <p>
 * This class represents the following YANG schema fragment defined in module <b>ietf-netconf</b>
 * <pre>
 * choice config-target {
 *   case startup {
 *     leaf startup {
 *       if-feature startup;
 *       type empty;
 *     }
 *   }
 *   case url {
 *     leaf url {
 *       if-feature url;
 *       type inet:uri;
 *     }
 *   }
 * }
 * </pre>The schema path to identify an instance is
 * <i>ietf-netconf/delete-config/input/target/config-target</i>
 *
 */
@Generated("mdsal-binding-generator")
public interface ConfigTarget
    extends
    ChoiceIn<Target>
{



    public static final @NonNull QName QNAME = $YangModuleInfoImpl.qnameOf("config-target");


}

