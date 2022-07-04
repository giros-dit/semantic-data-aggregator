package org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.logparser.so.metrics.rev210927;
import javax.annotation.processing.Generated;
import org.eclipse.jdt.annotation.NonNull;
import org.opendaylight.yangtools.yang.common.QName;

/**
 * Network service total termination time. The time it takes to the 5Gr-SO to 
 * perform the termination operation since the request arrives to the Northbound 
 * Interface (NBI).
 *
 * <p>
 * This class represents the following YANG schema fragment defined in module <b>logparser-so</b>
 * <pre>
 * identity Total_Termination_Time {
 *   base metric-identity;
 * }
 * </pre>The schema path to identify an instance is
 * <i>logparser-so/Total_Termination_Time</i>
 *
 */
@Generated("mdsal-binding-generator")
public interface TotalTerminationTime
    extends
    MetricIdentity
{



    public static final @NonNull QName QNAME = $YangModuleInfoImpl.qnameOf("Total_Termination_Time");


}

