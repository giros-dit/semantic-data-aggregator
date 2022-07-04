package org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.netconf.base._1._0.rev110601.get.output;
import java.lang.Class;
import java.lang.Override;
import javax.annotation.processing.Generated;
import org.eclipse.jdt.annotation.NonNull;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.netconf.base._1._0.rev110601.$YangModuleInfoImpl;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.netconf.base._1._0.rev110601.GetOutput;
import org.opendaylight.yangtools.yang.binding.ChildOf;
import org.opendaylight.yangtools.yang.binding.OpaqueObject;
import org.opendaylight.yangtools.yang.common.QName;

/**
 * Copy of the running datastore subset and/or state data that matched the filter 
 * criteria (if any). An empty data container indicates that the request did not 
 * produce any results.
 *
 * <p>
 * This class represents the following YANG schema fragment defined in module <b>ietf-netconf</b>
 * <pre>
 * anyxml data {
 * }
 * </pre>The schema path to identify an instance is
 * <i>ietf-netconf/get/output/data</i>
 *
 */
@Generated("mdsal-binding-generator")
public interface Data
    extends
    OpaqueObject<Data>,
    ChildOf<GetOutput>
{



    public static final @NonNull QName QNAME = $YangModuleInfoImpl.qnameOf("data");

    @Override
    default Class<org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.netconf.base._1._0.rev110601.get.output.Data> implementedInterface() {
        return org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.netconf.base._1._0.rev110601.get.output.Data.class;
    }

}

