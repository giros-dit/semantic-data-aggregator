package org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.logparser.so.metrics.rev210927;
import javax.annotation.processing.Generated;
import org.eclipse.jdt.annotation.NonNull;
import org.opendaylight.yangtools.yang.common.QName;

/**
 * The time it takes the Northbound Interface (NBI) to generate an ID to identify 
 * the instantiation operation.
 *
 * <p>
 * This class represents the following YANG schema fragment defined in module <b>logparser-so</b>
 * <pre>
 * identity Operation_ID_for_instantiation_op {
 *   base metric-identity;
 * }
 * </pre>The schema path to identify an instance is
 * <i>logparser-so/Operation_ID_for_instantiation_op</i>
 *
 */
@Generated("mdsal-binding-generator")
public interface OperationIDForInstantiationOp
    extends
    MetricIdentity
{



    public static final @NonNull QName QNAME = $YangModuleInfoImpl.qnameOf("Operation_ID_for_instantiation_op");


}

