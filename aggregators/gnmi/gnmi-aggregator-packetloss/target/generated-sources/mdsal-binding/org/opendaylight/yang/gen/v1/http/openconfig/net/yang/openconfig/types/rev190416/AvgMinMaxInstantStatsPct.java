package org.opendaylight.yang.gen.v1.http.openconfig.net.yang.openconfig.types.rev190416;
import java.lang.Class;
import java.lang.Override;
import javax.annotation.processing.Generated;
import org.eclipse.jdt.annotation.NonNull;
import org.opendaylight.yangtools.yang.binding.DataObject;
import org.opendaylight.yangtools.yang.common.QName;

/**
 * Common grouping for percentage statistics. Values include the instantaneous, 
 * average, minimum, and maximum statistics. Statistics are computed and reported 
 * based on a moving time interval (e.g., the last 30s). If supported by the 
 * device, the time interval over which the statistics are computed, and the times 
 * at which the minimum and maximum values occurred, are also reported.
 *
 * <p>
 * This class represents the following YANG schema fragment defined in module <b>openconfig-types</b>
 * <pre>
 * grouping avg-min-max-instant-stats-pct {
 *   leaf instant {
 *     type oc-types:percentage;
 *   }
 *   leaf avg {
 *     type oc-types:percentage;
 *   }
 *   leaf min {
 *     type oc-types:percentage;
 *   }
 *   leaf max {
 *     type oc-types:percentage;
 *   }
 *   uses stat-interval-state;
 *   uses min-max-time;
 * }
 * </pre>The schema path to identify an instance is
 * <i>openconfig-types/avg-min-max-instant-stats-pct</i>
 *
 */
@Generated("mdsal-binding-generator")
public interface AvgMinMaxInstantStatsPct
    extends
    DataObject,
    StatIntervalState,
    MinMaxTime
{



    public static final @NonNull QName QNAME = $YangModuleInfoImpl.qnameOf("avg-min-max-instant-stats-pct");

    @Override
    Class<? extends AvgMinMaxInstantStatsPct> implementedInterface();
    
    /**
     * Return instant, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         The instantaneous percentage value.
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.http.openconfig.net.yang.openconfig.types.rev190416.Percentage} instant, or {@code null} if it is not present.
     *
     */
    Percentage getInstant();
    
    /**
     * Return avg, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         The arithmetic mean value of the percentage measure of the statistic over the
     *         time interval.
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.http.openconfig.net.yang.openconfig.types.rev190416.Percentage} avg, or {@code null} if it is not present.
     *
     */
    Percentage getAvg();
    
    /**
     * Return min, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         The minimum value of the percentage measure of the statistic over the time
     *         interval.
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.http.openconfig.net.yang.openconfig.types.rev190416.Percentage} min, or {@code null} if it is not present.
     *
     */
    Percentage getMin();
    
    /**
     * Return max, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         The maximum value of the percentage measure of the statistic over the time
     *         interval.
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.http.openconfig.net.yang.openconfig.types.rev190416.Percentage} max, or {@code null} if it is not present.
     *
     */
    Percentage getMax();

}

