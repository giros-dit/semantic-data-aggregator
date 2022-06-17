package org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.logparser.so.metrics.rev210927;
import javax.annotation.processing.Generated;
import org.eclipse.jdt.annotation.NonNull;
import org.opendaylight.yangtools.yang.common.QName;

/**
 * Time interaction between SOE-Monitoring Manager modules of 5Gr-SO to determine 
 * the monitoring jobs (exporters) and dashboards to be configured at the 5Gr-VoMS 
 * plus the interaction to configure them and receive the associated object 
 * identifiers and update the information in Network Service Instantiation 
 * (NSIR) database.
 *
 * <p>
 * This class represents the following YANG schema fragment defined in module <b>logparser-so</b>
 * <pre>
 * identity Create_monitoring_jobs {
 *   base metric-identity;
 * }
 * </pre>The schema path to identify an instance is
 * <i>logparser-so/Create_monitoring_jobs</i>
 *
 */
@Generated("mdsal-binding-generator")
public interface CreateMonitoringJobs
    extends
    MetricIdentity
{



    public static final @NonNull QName QNAME = $YangModuleInfoImpl.qnameOf("Create_monitoring_jobs");


}

