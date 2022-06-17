package org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.prometheus.rev211118.metric.point.set;
import com.google.common.base.MoreObjects;
import java.lang.Class;
import java.lang.NullPointerException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.util.Objects;
import javax.annotation.processing.Generated;
import org.eclipse.jdt.annotation.NonNull;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.prometheus.rev211118.$YangModuleInfoImpl;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.prometheus.rev211118.MetricPointSet;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.prometheus.rev211118.metric.point.set.metric.points.MetricPointsChoice;
import org.opendaylight.yangtools.yang.binding.Augmentable;
import org.opendaylight.yangtools.yang.binding.ChildOf;
import org.opendaylight.yangtools.yang.binding.CodeHelpers;
import org.opendaylight.yangtools.yang.common.QName;

/**
 *
 * <p>
 * This class represents the following YANG schema fragment defined in module <b>prometheus</b>
 * <pre>
 * container metric-points {
 *   choice metric-points-choice {
 *     case metric-point-single {
 *       leaf value {
 *         type decimal64 {
 *           fraction-digits 2;
 *         }
 *       }
 *       leaf timestamp {
 *         type decimal64 {
 *           fraction-digits 3;
 *         }
 *       }
 *     }
 *     case metric-point-list {
 *       list metric-point {
 *         when not(../metric-point);
 *         key timestamp;
 *         min-elements 2;
 *         leaf value {
 *           type decimal64 {
 *             fraction-digits 2;
 *           }
 *         }
 *         leaf timestamp {
 *           type decimal64 {
 *             fraction-digits 3;
 *           }
 *         }
 *       }
 *     }
 *   }
 * }
 * </pre>The schema path to identify an instance is
 * <i>prometheus/metric-point-set/metric-points</i>
 *
 * <p>To create instances of this class use {@link MetricPointsBuilder}.
 * @see MetricPointsBuilder
 *
 */
@Generated("mdsal-binding-generator")
public interface MetricPoints
    extends
    ChildOf<MetricPointSet>,
    Augmentable<MetricPoints>
{



    public static final @NonNull QName QNAME = $YangModuleInfoImpl.qnameOf("metric-points");

    @Override
    default Class<org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.prometheus.rev211118.metric.point.set.MetricPoints> implementedInterface() {
        return org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.prometheus.rev211118.metric.point.set.MetricPoints.class;
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
    static int bindingHashCode(final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.prometheus.rev211118.metric.point.set.@NonNull MetricPoints obj) {
        final int prime = 31;
        int result = 1;
        result = prime * result + Objects.hashCode(obj.getMetricPointsChoice());
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
    static boolean bindingEquals(final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.prometheus.rev211118.metric.point.set.@NonNull MetricPoints thisObj, final Object obj) {
        if (thisObj == obj) {
            return true;
        }
        final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.prometheus.rev211118.metric.point.set.MetricPoints other = CodeHelpers.checkCast(org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.prometheus.rev211118.metric.point.set.MetricPoints.class, obj);
        if (other == null) {
            return false;
        }
        if (!Objects.equals(thisObj.getMetricPointsChoice(), other.getMetricPointsChoice())) {
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
    static String bindingToString(final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.prometheus.rev211118.metric.point.set.@NonNull MetricPoints obj) {
        final MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper("MetricPoints");
        CodeHelpers.appendValue(helper, "metricPointsChoice", obj.getMetricPointsChoice());
        CodeHelpers.appendValue(helper, "augmentation", obj.augmentations().values());
        return helper.toString();
    }
    
    /**
     * Return metricPointsChoice, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Enclosing container for the list of MetricPoints associated with a Metric
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.prometheus.rev211118.metric.point.set.metric.points.MetricPointsChoice} metricPointsChoice, or {@code null} if it is not present.
     *
     */
    MetricPointsChoice getMetricPointsChoice();

}

