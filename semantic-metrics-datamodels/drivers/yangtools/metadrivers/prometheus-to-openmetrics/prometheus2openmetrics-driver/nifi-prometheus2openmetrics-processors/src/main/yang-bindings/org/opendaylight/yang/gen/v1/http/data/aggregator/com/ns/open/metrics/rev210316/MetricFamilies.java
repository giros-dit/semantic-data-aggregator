package org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316;
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
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.metric.families.MetricFamily;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.metric.families.MetricFamilyKey;
import org.opendaylight.yangtools.yang.binding.Augmentable;
import org.opendaylight.yangtools.yang.binding.ChildOf;
import org.opendaylight.yangtools.yang.binding.CodeHelpers;
import org.opendaylight.yangtools.yang.common.QName;

/**
 * A MetricSet is the top level object exposed by OpenMetrics. It MUST consist of 
 * MetricFamilies and MAY be empty. Each MetricFamily name MUST be unique. The 
 * label name and value SHOULD NOT appear on every Metric within a MetricSet. 
 * is no specific ordering of MetricFamilies required within a MetricSet. An 
 * exposer MAY make an exposition easier to read for humans, for example sort 
 * alphabetically if the performance tradeoff makes sense.
 *
 * <p>
 * This class represents the following YANG schema fragment defined in module <b>openmetrics</b>
 * <pre>
 * container metric-families {
 *   config false;
 *   list metric-family {
 *     key name;
 *     leaf name {
 *       type string;
 *     }
 *     leaf metric-type {
 *       type metric-type;
 *     }
 *     leaf unit {
 *       type string;
 *     }
 *     leaf help {
 *       type string;
 *     }
 *     uses metric-set;
 *   }
 * }
 * </pre>The schema path to identify an instance is
 * <i>openmetrics/metric-families</i>
 *
 * <p>To create instances of this class use {@link MetricFamiliesBuilder}.
 * @see MetricFamiliesBuilder
 *
 */
@Generated("mdsal-binding-generator")
public interface MetricFamilies
    extends
    ChildOf<OpenmetricsData>,
    Augmentable<MetricFamilies>
{



    public static final @NonNull QName QNAME = $YangModuleInfoImpl.qnameOf("metric-families");

    @Override
    default Class<org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.MetricFamilies> implementedInterface() {
        return org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.MetricFamilies.class;
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
    static int bindingHashCode(final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.@NonNull MetricFamilies obj) {
        final int prime = 31;
        int result = 1;
        result = prime * result + Objects.hashCode(obj.getMetricFamily());
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
    static boolean bindingEquals(final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.@NonNull MetricFamilies thisObj, final Object obj) {
        if (thisObj == obj) {
            return true;
        }
        final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.MetricFamilies other = CodeHelpers.checkCast(org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.MetricFamilies.class, obj);
        if (other == null) {
            return false;
        }
        if (!Objects.equals(thisObj.getMetricFamily(), other.getMetricFamily())) {
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
    static String bindingToString(final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.@NonNull MetricFamilies obj) {
        final MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper("MetricFamilies");
        CodeHelpers.appendValue(helper, "metricFamily", obj.getMetricFamily());
        CodeHelpers.appendValue(helper, "augmentation", obj.augmentations().values());
        return helper.toString();
    }
    
    /**
     * Return metricFamily, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         A MetricFamily MAY have zero or more Metrics. A MetricFamily MUST have a name,
     *         HELP, TYPE, and UNIT metadata. Every Metric within a MetricFamily MUST have a
     *         unique LabelSet.
     *     </code>
     * </pre>
     *
     * @return {@code java.util.Map} metricFamily, or {@code null} if it is not present.
     *
     */
    @Nullable Map<MetricFamilyKey, MetricFamily> getMetricFamily();
    
    /**
     * Return metricFamily, or an empty list if it is not present.
     *
     * @return {@code java.util.Map} metricFamily, or an empty list if it is not present.
     *
     */
    default @NonNull Map<MetricFamilyKey, MetricFamily> nonnullMetricFamily() {
        return CodeHelpers.nonnull(getMetricFamily());
    }

}

