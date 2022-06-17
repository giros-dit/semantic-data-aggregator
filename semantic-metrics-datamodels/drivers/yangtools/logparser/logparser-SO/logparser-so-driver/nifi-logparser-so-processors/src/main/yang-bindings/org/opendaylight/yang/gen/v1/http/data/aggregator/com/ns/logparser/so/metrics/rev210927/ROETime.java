package org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.logparser.so.metrics.rev210927;
import javax.annotation.processing.Generated;
import org.eclipse.jdt.annotation.NonNull;
import org.opendaylight.yangtools.yang.common.QName;

/**
 * Time spent in the Resource Orchestration Engine (ROE) module during the 
 * instantiation or termination process.
 *
 * <p>
 * This class represents the following YANG schema fragment defined in module <b>logparser-so</b>
 * <pre>
 * identity ROE_time {
 *   base metric-identity;
 * }
 * </pre>The schema path to identify an instance is
 * <i>logparser-so/ROE_time</i>
 *
 */
@Generated("mdsal-binding-generator")
public interface ROETime
    extends
    MetricIdentity
{



    public static final @NonNull QName QNAME = $YangModuleInfoImpl.qnameOf("ROE_time");


}

