package org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.netconf.base._1._0.rev110601.get.input;
import java.lang.Class;
import java.lang.Override;
import javax.annotation.processing.Generated;
import org.eclipse.jdt.annotation.NonNull;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.netconf.base._1._0.rev110601.$YangModuleInfoImpl;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.netconf.base._1._0.rev110601.GetInput;
import org.opendaylight.yangtools.yang.binding.ChildOf;
import org.opendaylight.yangtools.yang.binding.OpaqueObject;
import org.opendaylight.yangtools.yang.common.QName;

/**
 * This parameter specifies the portion of the system configuration and state data 
 * to retrieve.
 *
 * <p>
 * This class represents the following YANG schema fragment defined in module <b>ietf-netconf</b>
 * <pre>
 * anyxml filter {
 *   nc:get-filter-element-attributes;
 * }
 * </pre>The schema path to identify an instance is
 * <i>ietf-netconf/get/input/filter</i>
 *
 */
@Generated("mdsal-binding-generator")
public interface Filter
    extends
    OpaqueObject<Filter>,
    ChildOf<GetInput>
{



    public static final @NonNull QName QNAME = $YangModuleInfoImpl.qnameOf("filter");

    @Override
    default Class<org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.netconf.base._1._0.rev110601.get.input.Filter> implementedInterface() {
        return org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.netconf.base._1._0.rev110601.get.input.Filter.class;
    }

}

