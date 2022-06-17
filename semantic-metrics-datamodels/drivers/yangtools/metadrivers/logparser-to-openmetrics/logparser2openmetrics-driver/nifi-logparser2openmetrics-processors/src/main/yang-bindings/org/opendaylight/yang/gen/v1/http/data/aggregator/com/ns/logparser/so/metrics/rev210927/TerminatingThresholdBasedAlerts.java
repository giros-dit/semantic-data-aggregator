package org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.logparser.so.metrics.rev210927;
import javax.annotation.processing.Generated;
import org.eclipse.jdt.annotation.NonNull;
import org.opendaylight.yangtools.yang.common.QName;

/**
 * The time required in the interaction between Service Orchestration Engine (SOE) 
 * and SLA Manager to remove alert-based objects in case they have been configured 
 * in the 5Gr-VoMS.
 *
 * <p>
 * This class represents the following YANG schema fragment defined in module <b>logparser-so</b>
 * <pre>
 * identity Terminating_Threshold_Based_Alerts {
 *   base metric-identity;
 * }
 * </pre>The schema path to identify an instance is
 * <i>logparser-so/Terminating_Threshold_Based_Alerts</i>
 *
 */
@Generated("mdsal-binding-generator")
public interface TerminatingThresholdBasedAlerts
    extends
    MetricIdentity
{



    public static final @NonNull QName QNAME = $YangModuleInfoImpl.qnameOf("Terminating_Threshold_Based_Alerts");


}
