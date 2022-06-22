package org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.netconf.base._1._0.rev110601.get.config.input.source;
import javax.annotation.processing.Generated;
import org.eclipse.jdt.annotation.NonNull;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.netconf.base._1._0.rev110601.$YangModuleInfoImpl;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.netconf.base._1._0.rev110601.get.config.input.Source;
import org.opendaylight.yangtools.yang.binding.ChoiceIn;
import org.opendaylight.yangtools.yang.common.QName;

/**
 * The configuration to retrieve.
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
 * }
 * </pre>The schema path to identify an instance is
 * <i>ietf-netconf/get-config/input/source/config-source</i>
 *
 */
@Generated("mdsal-binding-generator")
public interface ConfigSource
    extends
    ChoiceIn<Source>
{



    public static final @NonNull QName QNAME = $YangModuleInfoImpl.qnameOf("config-source");


}

