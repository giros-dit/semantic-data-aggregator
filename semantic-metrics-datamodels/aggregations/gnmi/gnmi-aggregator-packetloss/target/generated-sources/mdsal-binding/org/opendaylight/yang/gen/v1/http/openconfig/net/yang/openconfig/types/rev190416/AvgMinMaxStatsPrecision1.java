package org.opendaylight.yang.gen.v1.http.openconfig.net.yang.openconfig.types.rev190416;
import java.lang.Class;
import java.lang.Override;
import java.math.BigDecimal;
import javax.annotation.processing.Generated;
import org.eclipse.jdt.annotation.NonNull;
import org.opendaylight.yangtools.yang.binding.DataObject;
import org.opendaylight.yangtools.yang.common.QName;

/**
 * Common nodes for recording average, minimum, and maximum values for a 
 * These values all have fraction-digits set to 1. Statistics are computed and 
 * reported based on a moving time interval (e.g., the last 30s). If supported by 
 * the device, the time interval over which the statistics are computed is also 
 * reported.
 *
 * <p>
 * This class represents the following YANG schema fragment defined in module <b>openconfig-types</b>
 * <pre>
 * grouping avg-min-max-stats-precision1 {
 *   leaf avg {
 *     type decimal64 {
 *       fraction-digits 1;
 *     }
 *   }
 *   leaf min {
 *     type decimal64 {
 *       fraction-digits 1;
 *     }
 *   }
 *   leaf max {
 *     type decimal64 {
 *       fraction-digits 1;
 *     }
 *   }
 *   uses stat-interval-state;
 *   uses min-max-time;
 * }
 * </pre>The schema path to identify an instance is
 * <i>openconfig-types/avg-min-max-stats-precision1</i>
 *
 */
@Generated("mdsal-binding-generator")
public interface AvgMinMaxStatsPrecision1
    extends
    DataObject,
    StatIntervalState,
    MinMaxTime
{



    public static final @NonNull QName QNAME = $YangModuleInfoImpl.qnameOf("avg-min-max-stats-precision1");

    @Override
    Class<? extends AvgMinMaxStatsPrecision1> implementedInterface();
    
    /**
     * Return avg, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         The arithmetic mean value of the statistic over the time interval.
     *     </code>
     * </pre>
     *
     * @return {@code java.math.BigDecimal} avg, or {@code null} if it is not present.
     *
     */
    BigDecimal getAvg();
    
    /**
     * Return min, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         The minimum value of the statistic over the time interval.
     *     </code>
     * </pre>
     *
     * @return {@code java.math.BigDecimal} min, or {@code null} if it is not present.
     *
     */
    BigDecimal getMin();
    
    /**
     * Return max, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         The maximum value of the statitic over the time interval.
     *     </code>
     * </pre>
     *
     * @return {@code java.math.BigDecimal} max, or {@code null} if it is not present.
     *
     */
    BigDecimal getMax();

}

