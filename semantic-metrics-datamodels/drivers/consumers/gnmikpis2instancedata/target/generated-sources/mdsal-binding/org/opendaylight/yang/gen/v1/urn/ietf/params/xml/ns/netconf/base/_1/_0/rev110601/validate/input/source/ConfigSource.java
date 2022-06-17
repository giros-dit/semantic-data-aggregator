package org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.netconf.base._1._0.rev110601.validate.input.source;
import javax.annotation.processing.Generated;
import org.eclipse.jdt.annotation.NonNull;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.netconf.base._1._0.rev110601.$YangModuleInfoImpl;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.netconf.base._1._0.rev110601.validate.input.Source;
import org.opendaylight.yangtools.yang.binding.ChoiceIn;
import org.opendaylight.yangtools.yang.common.QName;

/**
 * The configuration source to validate.
 *
 * <p>
 * This class represents the following YANG schema fragment defined in module <b>ietf-netconf</b>
 * <pre>
 * choice config-source {
 *   case candidate {
 *     leaf candidate {
 *       if-feature candidate;
 *       type empty;
 *     }
 *   }
 *   case running {
 *     leaf running {
 *       type empty;
 *     }
 *   }
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
 *   case config {
 *     anyxml config {
 *     }
 *   }
 * }
 * </pre>The schema path to identify an instance is
 * <i>ietf-netconf/validate/input/source/config-source</i>
 *
 */
@Generated("mdsal-binding-generator")
public interface ConfigSource
    extends
    ChoiceIn<Source>
{



    public static final @NonNull QName QNAME = $YangModuleInfoImpl.qnameOf("config-source");


}

