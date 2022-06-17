package org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.netconf.base._1._0.rev110601.get.config.input;
import java.lang.Class;
import java.lang.Override;
import javax.annotation.processing.Generated;
import org.eclipse.jdt.annotation.NonNull;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.netconf.base._1._0.rev110601.$YangModuleInfoImpl;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.netconf.base._1._0.rev110601.GetConfigInput;
import org.opendaylight.yangtools.yang.binding.ChildOf;
import org.opendaylight.yangtools.yang.binding.OpaqueObject;
import org.opendaylight.yangtools.yang.common.QName;

/**
 * Subtree or XPath filter to use.
 *
 * <p>
 * This class represents the following YANG schema fragment defined in module <b>ietf-netconf</b>
 * <pre>
 * anyxml filter {
 *   nc:get-filter-element-attributes;
 * }
 * </pre>The schema path to identify an instance is
 * <i>ietf-netconf/get-config/input/filter</i>
 *
 */
@Generated("mdsal-binding-generator")
public interface Filter
    extends
    OpaqueObject<Filter>,
    ChildOf<GetConfigInput>
{



    public static final @NonNull QName QNAME = $YangModuleInfoImpl.qnameOf("filter");

    @Override
    default Class<org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.netconf.base._1._0.rev110601.get.config.input.Filter> implementedInterface() {
        return org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.netconf.base._1._0.rev110601.get.config.input.Filter.class;
    }

}

