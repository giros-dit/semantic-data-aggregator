package org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.netconf.base._1._0.rev110601.edit.config.input;
import javax.annotation.processing.Generated;
import org.eclipse.jdt.annotation.NonNull;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.netconf.base._1._0.rev110601.$YangModuleInfoImpl;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.netconf.base._1._0.rev110601.EditConfigInput;
import org.opendaylight.yangtools.yang.binding.ChoiceIn;
import org.opendaylight.yangtools.yang.common.QName;

/**
 * The content for the edit operation.
 *
 * <p>
 * This class represents the following YANG schema fragment defined in module <b>ietf-netconf</b>
 * <pre>
 * choice edit-content {
 *   case config {
 *     anyxml config {
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
 * <i>ietf-netconf/edit-config/input/edit-content</i>
 *
 */
@Generated("mdsal-binding-generator")
public interface EditContent
    extends
    ChoiceIn<EditConfigInput>
{



    public static final @NonNull QName QNAME = $YangModuleInfoImpl.qnameOf("edit-content");


}

