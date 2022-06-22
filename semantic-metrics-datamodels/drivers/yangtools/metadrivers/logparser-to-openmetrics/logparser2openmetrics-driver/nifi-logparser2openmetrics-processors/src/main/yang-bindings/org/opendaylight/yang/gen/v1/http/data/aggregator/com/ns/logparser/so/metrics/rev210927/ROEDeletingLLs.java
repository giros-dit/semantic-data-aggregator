package org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.logparser.so.metrics.rev210927;
import javax.annotation.processing.Generated;
import org.eclipse.jdt.annotation.NonNull;
import org.opendaylight.yangtools.yang.common.QName;

/**
 * Time in the Resource Orchestration Engine (ROE) to interact with the Resource 
 * Layer (RL) to deallocate resources in the logical links (LLs) serving the 
 * virtual link (VL) connections between VNFs deployed in multiple Virtualized 
 * Infrastructure Managers (VIMs).
 *
 * <p>
 * This class represents the following YANG schema fragment defined in module <b>logparser-so</b>
 * <pre>
 * identity ROE_Deleting_LLs {
 *   base metric-identity;
 * }
 * </pre>The schema path to identify an instance is
 * <i>logparser-so/ROE_Deleting_LLs</i>
 *
 */
@Generated("mdsal-binding-generator")
public interface ROEDeletingLLs
    extends
    MetricIdentity
{



    public static final @NonNull QName QNAME = $YangModuleInfoImpl.qnameOf("ROE_Deleting_LLs");


}

