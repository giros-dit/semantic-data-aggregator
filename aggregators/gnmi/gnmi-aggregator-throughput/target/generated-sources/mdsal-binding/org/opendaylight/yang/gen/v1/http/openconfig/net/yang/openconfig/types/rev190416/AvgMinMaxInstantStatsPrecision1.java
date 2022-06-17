package org.opendaylight.yang.gen.v1.http.openconfig.net.yang.openconfig.types.rev190416;
import java.lang.Class;
import java.lang.Override;
import java.math.BigDecimal;
import javax.annotation.processing.Generated;
import org.eclipse.jdt.annotation.NonNull;
import org.opendaylight.yangtools.yang.binding.DataObject;
import org.opendaylight.yangtools.yang.common.QName;

/**
 * Common grouping for recording an instantaneous statistic value in addition to 
 * avg-min-max stats
 *
 * <p>
 * This class represents the following YANG schema fragment defined in module <b>openconfig-types</b>
 * <pre>
 * grouping avg-min-max-instant-stats-precision1 {
 *   leaf instant {
 *     type decimal64 {
 *       fraction-digits 1;
 *     }
 *   }
 *   uses avg-min-max-stats-precision1;
 * }
 * </pre>The schema path to identify an instance is
 * <i>openconfig-types/avg-min-max-instant-stats-precision1</i>
 *
 */
@Generated("mdsal-binding-generator")
public interface AvgMinMaxInstantStatsPrecision1
    extends
    DataObject,
    AvgMinMaxStatsPrecision1
{



    public static final @NonNull QName QNAME = $YangModuleInfoImpl.qnameOf("avg-min-max-instant-stats-precision1");

    @Override
    Class<? extends AvgMinMaxInstantStatsPrecision1> implementedInterface();
    
    /**
     * Return instant, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         The instantaneous value of the statistic.
     *     </code>
     * </pre>
     *
     * @return {@code java.math.BigDecimal} instant, or {@code null} if it is not present.
     *
     */
    BigDecimal getInstant();

}

