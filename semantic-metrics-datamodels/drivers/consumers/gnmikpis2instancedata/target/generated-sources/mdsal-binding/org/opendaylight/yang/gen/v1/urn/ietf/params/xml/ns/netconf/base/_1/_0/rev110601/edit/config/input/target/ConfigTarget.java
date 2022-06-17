package org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.netconf.base._1._0.rev110601.edit.config.input.target;
import javax.annotation.processing.Generated;
import org.eclipse.jdt.annotation.NonNull;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.netconf.base._1._0.rev110601.$YangModuleInfoImpl;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.netconf.base._1._0.rev110601.edit.config.input.Target;
import org.opendaylight.yangtools.yang.binding.ChoiceIn;
import org.opendaylight.yangtools.yang.common.QName;

/**
 * The configuration target.
 *
 * <p>
 * This class represents the following YANG schema fragment defined in module <b>ietf-netconf</b>
 * <pre>
 * choice config-target {
 *   case candidate {
 *     leaf candidate {
 *       if-feature candidate;
 *       type empty;
 *     }
 *   }
 *   case running {
 *     leaf running {
 *       if-feature writable-running;
 *       type empty;
 *     }
 *   }
 * }
 * </pre>The schema path to identify an instance is
 * <i>ietf-netconf/edit-config/input/target/config-target</i>
 *
 */
@Generated("mdsal-binding-generator")
public interface ConfigTarget
    extends
    ChoiceIn<Target>
{



    public static final @NonNull QName QNAME = $YangModuleInfoImpl.qnameOf("config-target");


}

