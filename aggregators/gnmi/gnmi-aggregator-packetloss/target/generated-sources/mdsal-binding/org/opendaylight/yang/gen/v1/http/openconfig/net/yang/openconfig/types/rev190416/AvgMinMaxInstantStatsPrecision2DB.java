package org.opendaylight.yang.gen.v1.http.openconfig.net.yang.openconfig.types.rev190416;
import java.lang.Class;
import java.lang.Override;
import java.math.BigDecimal;
import javax.annotation.processing.Generated;
import org.eclipse.jdt.annotation.NonNull;
import org.opendaylight.yangtools.yang.binding.DataObject;
import org.opendaylight.yangtools.yang.common.QName;

/**
 * Common grouping for recording dB values with 2 decimal precision. Values 
 * the instantaneous, average, minimum, and maximum statistics. Statistics are 
 * computed and reported based on a moving time interval (e.g., the last 30s). If 
 * supported by the device, the time interval over which the statistics are 
 * computed, and the times at which the minimum and maximum values occurred, are 
 * also reported.
 *
 * <p>
 * This class represents the following YANG schema fragment defined in module <b>openconfig-types</b>
 * <pre>
 * grouping avg-min-max-instant-stats-precision2-dB {
 *   leaf instant {
 *     type decimal64 {
 *       fraction-digits 2;
 *     }
 *     units dB;
 *   }
 *   leaf avg {
 *     type decimal64 {
 *       fraction-digits 2;
 *     }
 *     units dB;
 *   }
 *   leaf min {
 *     type decimal64 {
 *       fraction-digits 2;
 *     }
 *     units dB;
 *   }
 *   leaf max {
 *     type decimal64 {
 *       fraction-digits 2;
 *     }
 *     units dB;
 *   }
 *   uses stat-interval-state;
 *   uses min-max-time;
 * }
 * </pre>The schema path to identify an instance is
 * <i>openconfig-types/avg-min-max-instant-stats-precision2-dB</i>
 *
 */
@Generated("mdsal-binding-generator")
public interface AvgMinMaxInstantStatsPrecision2DB
    extends
    DataObject,
    StatIntervalState,
    MinMaxTime
{



    public static final @NonNull QName QNAME = $YangModuleInfoImpl.qnameOf("avg-min-max-instant-stats-precision2-dB");

    @Override
    Class<? extends AvgMinMaxInstantStatsPrecision2DB> implementedInterface();
    
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
     *         The maximum value of the statistic over the time interval.
     *     </code>
     * </pre>
     *
     * @return {@code java.math.BigDecimal} max, or {@code null} if it is not present.
     *
     */
    BigDecimal getMax();

}

