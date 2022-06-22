package org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.logparser.so.metrics.rev210927;
import javax.annotation.processing.Generated;
import org.eclipse.jdt.annotation.NonNull;
import org.opendaylight.yangtools.yang.common.QName;

/**
 * The time it takes the Resource Orchestration Engine (ROE) to determine the 
 * request of the different Virtual Links (VLs) needing resources in the Logical 
 * Links (LLs) because connected VNFs have been deployed in multiple Virtualized 
 * Infrastructure Managers (VIMs).
 *
 * <p>
 * This class represents the following YANG schema fragment defined in module <b>logparser-so</b>
 * <pre>
 * identity ROE_extract_VLs {
 *   base metric-identity;
 * }
 * </pre>The schema path to identify an instance is
 * <i>logparser-so/ROE_extract_VLs</i>
 *
 */
@Generated("mdsal-binding-generator")
public interface ROEExtractVLs
    extends
    MetricIdentity
{



    public static final @NonNull QName QNAME = $YangModuleInfoImpl.qnameOf("ROE_extract_VLs");


}

