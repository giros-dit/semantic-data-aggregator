package org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.logparser.so.metrics.rev210927;
import javax.annotation.processing.Generated;
import org.eclipse.jdt.annotation.NonNull;
import org.opendaylight.yangtools.yang.common.QName;

/**
 * The time at Resource Orchestration Engine (ROE) submodule to parse NSD and 
 * of an network service to get the required information for Placement Algorithm 
 * (PA).
 *
 * <p>
 * This class represents the following YANG schema fragment defined in module <b>logparser-so</b>
 * <pre>
 * identity ROE_parsing_NSDs {
 *   base metric-identity;
 * }
 * </pre>The schema path to identify an instance is
 * <i>logparser-so/ROE_parsing_NSDs</i>
 *
 */
@Generated("mdsal-binding-generator")
public interface ROEParsingNSDs
    extends
    MetricIdentity
{



    public static final @NonNull QName QNAME = $YangModuleInfoImpl.qnameOf("ROE_parsing_NSDs");


}

