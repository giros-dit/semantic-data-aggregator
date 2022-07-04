package org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.logparser.so.metrics.rev210927;
import javax.annotation.processing.Generated;
import org.eclipse.jdt.annotation.NonNull;
import org.opendaylight.yangtools.yang.common.QName;

/**
 * Time in the interaction between Resource Orchestration Engine (ROE) and 
 * Layer (RL) to allocate resources in the Logical Links (LLs) based on the ROE 
 * extract request.
 *
 * <p>
 * This class represents the following YANG schema fragment defined in module <b>logparser-so</b>
 * <pre>
 * identity ROE_Created_VLs {
 *   base metric-identity;
 * }
 * </pre>The schema path to identify an instance is
 * <i>logparser-so/ROE_Created_VLs</i>
 *
 */
@Generated("mdsal-binding-generator")
public interface ROECreatedVLs
    extends
    MetricIdentity
{



    public static final @NonNull QName QNAME = $YangModuleInfoImpl.qnameOf("ROE_Created_VLs");


}

