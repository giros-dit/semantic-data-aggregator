package org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.metric.point.set.metric.points.metric.points.choice;
import com.google.common.base.MoreObjects;
import java.lang.Class;
import java.lang.NullPointerException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.util.Map;
import java.util.Objects;
import javax.annotation.processing.Generated;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.$YangModuleInfoImpl;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.metric.point.set.metric.points.MetricPointsChoice;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.metric.point.set.metric.points.metric.points.choice.metric.point.list.MetricPoint;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.metric.point.set.metric.points.metric.points.choice.metric.point.list.MetricPointKey;
import org.opendaylight.yangtools.yang.binding.Augmentable;
import org.opendaylight.yangtools.yang.binding.CodeHelpers;
import org.opendaylight.yangtools.yang.binding.DataObject;
import org.opendaylight.yangtools.yang.common.QName;

/**
 *
 * <p>
 * This class represents the following YANG schema fragment defined in module <b>openmetrics</b>
 * <pre>
 * case metric-point-list {
 *   list metric-point {
 *     when not(../metric-point);
 *     key timestamp;
 *     min-elements 2;
 *     leaf value {
 *       type decimal64 {
 *         fraction-digits 2;
 *       }
 *     }
 *     leaf timestamp {
 *       type int64;
 *     }
 *   }
 * }
 * </pre>The schema path to identify an instance is
 * <i>openmetrics/metric-point-set/metric-points/metric-points-choice/metric-point-list</i>
 *
 */
@Generated("mdsal-binding-generator")
public interface MetricPointList
    extends
    DataObject,
    Augmentable<MetricPointList>,
    MetricPointsChoice
{



    public static final @NonNull QName QNAME = $YangModuleInfoImpl.qnameOf("metric-point-list");

    @Override
    default Class<org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.metric.point.set.metric.points.metric.points.choice.MetricPointList> implementedInterface() {
        return org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.metric.point.set.metric.points.metric.points.choice.MetricPointList.class;
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
    static int bindingHashCode(final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.metric.point.set.metric.points.metric.points.choice.@NonNull MetricPointList obj) {
        final int prime = 31;
        int result = 1;
        result = prime * result + Objects.hashCode(obj.getMetricPoint());
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
    static boolean bindingEquals(final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.metric.point.set.metric.points.metric.points.choice.@NonNull MetricPointList thisObj, final Object obj) {
        if (thisObj == obj) {
            return true;
        }
        final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.metric.point.set.metric.points.metric.points.choice.MetricPointList other = CodeHelpers.checkCast(org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.metric.point.set.metric.points.metric.points.choice.MetricPointList.class, obj);
        if (other == null) {
            return false;
        }
        if (!Objects.equals(thisObj.getMetricPoint(), other.getMetricPoint())) {
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
    static String bindingToString(final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.metric.point.set.metric.points.metric.points.choice.@NonNull MetricPointList obj) {
        final MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper("MetricPointList");
        CodeHelpers.appendValue(helper, "metricPoint", obj.getMetricPoint());
        CodeHelpers.appendValue(helper, "augmentation", obj.augmentations().values());
        return helper.toString();
    }
    
    /**
     * Return metricPoint, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         A list of MetricPoints in a Metric. If more than one MetricPoint is exposed for
     *         a Metric, then its MetricPoints MUST have monotonically increasing timestamps.
     *     </code>
     * </pre>
     *
     * @return {@code java.util.Map} metricPoint, or {@code null} if it is not present.
     *
     */
    @Nullable Map<MetricPointKey, MetricPoint> getMetricPoint();
    
    /**
     * Return metricPoint, or an empty list if it is not present.
     *
     * @return {@code java.util.Map} metricPoint, or an empty list if it is not present.
     *
     */
    default @NonNull Map<MetricPointKey, MetricPoint> nonnullMetricPoint() {
        return CodeHelpers.nonnull(getMetricPoint());
    }

}

