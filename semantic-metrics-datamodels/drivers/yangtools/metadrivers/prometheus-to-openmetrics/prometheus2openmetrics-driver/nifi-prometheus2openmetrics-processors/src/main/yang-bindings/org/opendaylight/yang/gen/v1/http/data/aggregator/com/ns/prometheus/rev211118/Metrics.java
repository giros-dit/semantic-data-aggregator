package org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.prometheus.rev211118;
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
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.prometheus.rev211118.metrics.Metric;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.prometheus.rev211118.metrics.MetricKey;
import org.opendaylight.yangtools.yang.binding.Augmentable;
import org.opendaylight.yangtools.yang.binding.ChildOf;
import org.opendaylight.yangtools.yang.binding.CodeHelpers;
import org.opendaylight.yangtools.yang.common.QName;

/**
 * Enclosing container for the list of Prometheus metrics.
 *
 * <p>
 * This class represents the following YANG schema fragment defined in module <b>prometheus</b>
 * <pre>
 * container metrics {
 *   list metric {
 *     key label-set-id;
 *     leaf label-set-id {
 *       type string;
 *     }
 *     leaf name {
 *       type string;
 *     }
 *     uses label-set;
 *     uses metric-point-set;
 *   }
 * }
 * </pre>The schema path to identify an instance is
 * <i>prometheus/metrics</i>
 *
 * <p>To create instances of this class use {@link MetricsBuilder}.
 * @see MetricsBuilder
 *
 */
@Generated("mdsal-binding-generator")
public interface Metrics
    extends
    ChildOf<PrometheusData>,
    Augmentable<Metrics>
{



    public static final @NonNull QName QNAME = $YangModuleInfoImpl.qnameOf("metrics");

    @Override
    default Class<org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.prometheus.rev211118.Metrics> implementedInterface() {
        return org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.prometheus.rev211118.Metrics.class;
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
    static int bindingHashCode(final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.prometheus.rev211118.@NonNull Metrics obj) {
        final int prime = 31;
        int result = 1;
        result = prime * result + Objects.hashCode(obj.getMetric());
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
    static boolean bindingEquals(final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.prometheus.rev211118.@NonNull Metrics thisObj, final Object obj) {
        if (thisObj == obj) {
            return true;
        }
        final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.prometheus.rev211118.Metrics other = CodeHelpers.checkCast(org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.prometheus.rev211118.Metrics.class, obj);
        if (other == null) {
            return false;
        }
        if (!Objects.equals(thisObj.getMetric(), other.getMetric())) {
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
    static String bindingToString(final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.prometheus.rev211118.@NonNull Metrics obj) {
        final MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper("Metrics");
        CodeHelpers.appendValue(helper, "metric", obj.getMetric());
        CodeHelpers.appendValue(helper, "augmentation", obj.augmentations().values());
        return helper.toString();
    }
    
    /**
     * Return metric, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Metrics are defined by a unique LabelSet within a MetricFamily. Metrics MUST
     *         contain a list of one or more MetricPoints. Metrics with the same name for a
     *         given MetricFamily SHOULD have the same set of label names in their LabelSet.
     *         MetricPoints SHOULD NOT have explicit timestamps. If more than one MetricPoint
     *         is exposed for a Metric, then its MetricPoints MUST have monotonically
     *         increasing timestamps
     *     </code>
     * </pre>
     *
     * @return {@code java.util.Map} metric, or {@code null} if it is not present.
     *
     */
    @Nullable Map<MetricKey, Metric> getMetric();
    
    /**
     * Return metric, or an empty list if it is not present.
     *
     * @return {@code java.util.Map} metric, or an empty list if it is not present.
     *
     */
    default @NonNull Map<MetricKey, Metric> nonnullMetric() {
        return CodeHelpers.nonnull(getMetric());
    }

}

