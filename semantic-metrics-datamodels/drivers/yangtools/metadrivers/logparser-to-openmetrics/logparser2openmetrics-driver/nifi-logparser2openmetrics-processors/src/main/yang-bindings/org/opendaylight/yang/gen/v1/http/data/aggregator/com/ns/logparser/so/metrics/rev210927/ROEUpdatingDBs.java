package org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.logparser.so.metrics.rev210927;
import javax.annotation.processing.Generated;
import org.eclipse.jdt.annotation.NonNull;
import org.opendaylight.yangtools.yang.common.QName;

/**
 * Time to update databases (DBs) to declare the network service as operative and 
 * the instantiation or termination operation as successful.
 *
 * <p>
 * This class represents the following YANG schema fragment defined in module <b>logparser-so</b>
 * <pre>
 * identity ROE_Updating_DBs {
 *   base metric-identity;
 * }
 * </pre>The schema path to identify an instance is
 * <i>logparser-so/ROE_Updating_DBs</i>
 *
 */
@Generated("mdsal-binding-generator")
public interface ROEUpdatingDBs
    extends
    MetricIdentity
{



    public static final @NonNull QName QNAME = $YangModuleInfoImpl.qnameOf("ROE_Updating_DBs");


}

