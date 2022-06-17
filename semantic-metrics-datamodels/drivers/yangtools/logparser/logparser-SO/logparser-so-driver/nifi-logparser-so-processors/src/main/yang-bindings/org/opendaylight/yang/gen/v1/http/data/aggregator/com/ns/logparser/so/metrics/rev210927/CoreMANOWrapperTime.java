package org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.logparser.so.metrics.rev210927;
import javax.annotation.processing.Generated;
import org.eclipse.jdt.annotation.NonNull;
import org.opendaylight.yangtools.yang.common.QName;

/**
 * For instantiation operation: Time spent in the Core MANO Wrapper module during 
 * the instantiation process to create virtual network supporting the Virtual 
 * (VLs), the VMs supporting the VNFs, and update the Network Service 
 * Resource (NSIR) database. For termination operation: Time spent in the Core 
 * Wrapper module during the termination process to delete virtual network 
 * supporting the vitual links (VLs) and the VMs supporting the VNFs.
 *
 * <p>
 * This class represents the following YANG schema fragment defined in module <b>logparser-so</b>
 * <pre>
 * identity CoreMANO_Wrapper_time {
 *   base metric-identity;
 * }
 * </pre>The schema path to identify an instance is
 * <i>logparser-so/CoreMANO_Wrapper_time</i>
 *
 */
@Generated("mdsal-binding-generator")
public interface CoreMANOWrapperTime
    extends
    MetricIdentity
{



    public static final @NonNull QName QNAME = $YangModuleInfoImpl.qnameOf("CoreMANO_Wrapper_time");


}

