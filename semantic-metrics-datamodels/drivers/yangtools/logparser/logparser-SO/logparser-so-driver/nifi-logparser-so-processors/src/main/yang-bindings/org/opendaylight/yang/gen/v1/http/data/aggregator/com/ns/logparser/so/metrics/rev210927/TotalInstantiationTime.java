package org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.logparser.so.metrics.rev210927;
import javax.annotation.processing.Generated;
import org.eclipse.jdt.annotation.NonNull;
import org.opendaylight.yangtools.yang.common.QName;

/**
 * Network service total instantiation time. The time it takes since the 5Gr-SO 
 * created the service identifier for a network service until it has been totally 
 * instantiated.
 *
 * <p>
 * This class represents the following YANG schema fragment defined in module <b>logparser-so</b>
 * <pre>
 * identity Total_instantiation_time {
 *   base metric-identity;
 * }
 * </pre>The schema path to identify an instance is
 * <i>logparser-so/Total_instantiation_time</i>
 *
 */
@Generated("mdsal-binding-generator")
public interface TotalInstantiationTime
    extends
    MetricIdentity
{



    public static final @NonNull QName QNAME = $YangModuleInfoImpl.qnameOf("Total_instantiation_time");


}

