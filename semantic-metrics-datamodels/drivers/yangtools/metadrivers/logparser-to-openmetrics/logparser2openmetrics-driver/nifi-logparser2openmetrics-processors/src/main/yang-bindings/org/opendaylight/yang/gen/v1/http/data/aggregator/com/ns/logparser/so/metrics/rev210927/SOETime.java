package org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.logparser.so.metrics.rev210927;
import javax.annotation.processing.Generated;
import org.eclipse.jdt.annotation.NonNull;
import org.opendaylight.yangtools.yang.common.QName;

/**
 * Time spent in the Service Orchestration Engine (SOE) module (both at SOE 
 * SOE child sub-modules) during the instantiation or termination process.
 *
 * <p>
 * This class represents the following YANG schema fragment defined in module <b>logparser-so</b>
 * <pre>
 * identity SOE_Time {
 *   base metric-identity;
 * }
 * </pre>The schema path to identify an instance is
 * <i>logparser-so/SOE_Time</i>
 *
 */
@Generated("mdsal-binding-generator")
public interface SOETime
    extends
    MetricIdentity
{



    public static final @NonNull QName QNAME = $YangModuleInfoImpl.qnameOf("SOE_Time");


}

