package org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.logparser.so.metrics.rev210927;
import javax.annotation.processing.Generated;
import org.eclipse.jdt.annotation.NonNull;
import org.opendaylight.yangtools.yang.common.QName;

/**
 * Time interaction between SOE-SLA Manager modules of the 5Gr-SO to determine the 
 * threshold-alerts objects (if there is not AI/ML treatment) to be configured at 
 * the 5Gr-VoMS plus the interaction to configure them at the 5GR-VoMs and receive 
 * the associated object identifiers and update the information in Network Service 
 * Instantiation Resource (NSIR) database.
 *
 * <p>
 * This class represents the following YANG schema fragment defined in module <b>logparser-so</b>
 * <pre>
 * identity Create_Threshold_Based_Alerts {
 *   base metric-identity;
 * }
 * </pre>The schema path to identify an instance is
 * <i>logparser-so/Create_Threshold_Based_Alerts</i>
 *
 */
@Generated("mdsal-binding-generator")
public interface CreateThresholdBasedAlerts
    extends
    MetricIdentity
{



    public static final @NonNull QName QNAME = $YangModuleInfoImpl.qnameOf("Create_Threshold_Based_Alerts");


}

