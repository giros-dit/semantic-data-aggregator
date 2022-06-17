package org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.metric.point.set.metric.points.metric.points.choice;
import com.google.common.base.MoreObjects;
import java.lang.Class;
import java.lang.Long;
import java.lang.NullPointerException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.math.BigDecimal;
import java.util.Objects;
import javax.annotation.processing.Generated;
import org.eclipse.jdt.annotation.NonNull;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.$YangModuleInfoImpl;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.metric.point.set.metric.points.MetricPointsChoice;
import org.opendaylight.yangtools.yang.binding.Augmentable;
import org.opendaylight.yangtools.yang.binding.CodeHelpers;
import org.opendaylight.yangtools.yang.binding.DataObject;
import org.opendaylight.yangtools.yang.common.QName;

/**
 * A MetricPoint in a Metric. If more than one MetricPoint is exposed for a 
 * then its MetricPoints MUST have monotonically increasing timestamps.
 *
 * <p>
 * This class represents the following YANG schema fragment defined in module <b>openmetrics</b>
 * <pre>
 * case metric-point-single {
 *   leaf value {
 *     type decimal64 {
 *       fraction-digits 2;
 *     }
 *   }
 *   leaf timestamp {
 *     type int64;
 *   }
 * }
 * </pre>The schema path to identify an instance is
 * <i>openmetrics/metric-point-set/metric-points/metric-points-choice/metric-point-single</i>
 *
 */
@Generated("mdsal-binding-generator")
public interface MetricPointSingle
    extends
    DataObject,
    Augmentable<MetricPointSingle>,
    MetricPointsChoice
{



    public static final @NonNull QName QNAME = $YangModuleInfoImpl.qnameOf("metric-point-single");

    @Override
    default Class<org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.metric.point.set.metric.points.metric.points.choice.MetricPointSingle> implementedInterface() {
        return org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.metric.point.set.metric.points.metric.points.choice.MetricPointSingle.class;
    }
    
    /**
     * Default implementation of {@link Object#hashCode()} contract for this interface.
     * Implementations of this interface are encouraged to defer to this method to get consistent hashing
     * results across all implementations.
     *
     * @param obj Object for which to generate hashCode() result.
     * @return Hash code value of data modeled by this interface.
     * @throws NullPointerException if {@code obj} is null
     */
    static int bindingHashCode(final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.metric.point.set.metric.points.metric.points.choice.@NonNull MetricPointSingle obj) {
        final int prime = 31;
        int result = 1;
        result = prime * result + Objects.hashCode(obj.getTimestamp());
        result = prime * result + Objects.hashCode(obj.getValue());
        result = prime * result + obj.augmentations().hashCode();
        return result;
    }
    
    /**
     * Default implementation of {@link Object#equals(Object)} contract for this interface.
     * Implementations of this interface are encouraged to defer to this method to get consistent equality
     * results across all implementations.
     *
     * @param thisObj Object acting as the receiver of equals invocation
     * @param obj Object acting as argument to equals invocation
     * @return True if thisObj and obj are considered equal
     * @throws NullPointerException if {@code thisObj} is null
     */
    static boolean bindingEquals(final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.metric.point.set.metric.points.metric.points.choice.@NonNull MetricPointSingle thisObj, final Object obj) {
        if (thisObj == obj) {
            return true;
        }
        final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.metric.point.set.metric.points.metric.points.choice.MetricPointSingle other = CodeHelpers.checkCast(org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.metric.point.set.metric.points.metric.points.choice.MetricPointSingle.class, obj);
        if (other == null) {
            return false;
        }
        if (!Objects.equals(thisObj.getTimestamp(), other.getTimestamp())) {
            return false;
        }
        if (!Objects.equals(thisObj.getValue(), other.getValue())) {
            return false;
        }
        return thisObj.augmentations().equals(other.augmentations());
    }
    
    /**
     * Default implementation of {@link Object#toString()} contract for this interface.
     * Implementations of this interface are encouraged to defer to this method to get consistent string
     * representations across all implementations.
     *
     * @param obj Object for which to generate toString() result.
     * @return {@link String} value of data modeled by this interface.
     * @throws NullPointerException if {@code obj} is null
     */
    static String bindingToString(final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.metric.point.set.metric.points.metric.points.choice.@NonNull MetricPointSingle obj) {
        final MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper("MetricPointSingle");
        CodeHelpers.appendValue(helper, "timestamp", obj.getTimestamp());
        CodeHelpers.appendValue(helper, "value", obj.getValue());
        CodeHelpers.appendValue(helper, "augmentation", obj.augmentations().values());
        return helper.toString();
    }
    
    /**
     * Return value, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Required. The value of the metric's sample
     *     </code>
     * </pre>
     *
     * @return {@code java.math.BigDecimal} value, or {@code null} if it is not present.
     *
     */
    BigDecimal getValue();
    
    /**
     * Return timestamp, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Optional. The measurement timestamp of the metric's sample. UNIX epoch with
     *         millisecond precision.
     *     </code>
     * </pre>
     *
     * @return {@code java.lang.Long} timestamp, or {@code null} if it is not present.
     *
     */
    Long getTimestamp();

}

