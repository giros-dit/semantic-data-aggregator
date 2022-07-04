package org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.logparser.so.metrics.rev210927;
import javax.annotation.processing.Generated;
import org.eclipse.jdt.annotation.NonNull;
import org.opendaylight.yangtools.yang.common.QName;

/**
 * The time the hierarchical Service Orchestration Engine (SOE) uses to select the 
 * appropriate instantiation or termination process based on the nature of the 
 * service (single NS, composite NS).
 *
 * <p>
 * This class represents the following YANG schema fragment defined in module <b>logparser-so</b>
 * <pre>
 * identity Hierarchical_SOE_Dispatching_SOEpSOEc {
 *   base metric-identity;
 * }
 * </pre>The schema path to identify an instance is
 * <i>logparser-so/Hierarchical_SOE_Dispatching_SOEpSOEc</i>
 *
 */
@Generated("mdsal-binding-generator")
public interface HierarchicalSOEDispatchingSOEpSOEc
    extends
    MetricIdentity
{



    public static final @NonNull QName QNAME = $YangModuleInfoImpl.qnameOf("Hierarchical_SOE_Dispatching_SOEpSOEc");


}

