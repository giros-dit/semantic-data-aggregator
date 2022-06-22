package org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.prometheus.rev211118.metrics;
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
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.prometheus.rev211118.LabelSet;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.prometheus.rev211118.MetricPointSet;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.prometheus.rev211118.Metrics;
import org.opendaylight.yangtools.yang.binding.Augmentable;
import org.opendaylight.yangtools.yang.binding.ChildOf;
import org.opendaylight.yangtools.yang.binding.CodeHelpers;
import org.opendaylight.yangtools.yang.binding.Identifiable;
import org.opendaylight.yangtools.yang.common.QName;

/**
 * Metrics are defined by a unique LabelSet within a MetricFamily. Metrics MUST 
 * contain a list of one or more MetricPoints. Metrics with the same name for a 
 * given MetricFamily SHOULD have the same set of label names in their LabelSet. 
 * MetricPoints SHOULD NOT have explicit timestamps. If more than one MetricPoint 
 * is exposed for a Metric, then its MetricPoints MUST have monotonically 
 * increasing timestamps
 *
 * <p>
 * This class represents the following YANG schema fragment defined in module <b>prometheus</b>
 * <pre>
 * list metric {
 *   key label-set-id;
 *   leaf label-set-id {
 *     type string;
 *   }
 *   leaf name {
 *     type string;
 *   }
 *   uses label-set;
 *   uses metric-point-set;
 * }
 * </pre>The schema path to identify an instance is
 * <i>prometheus/metrics/metric</i>
 *
 * <p>To create instances of this class use {@link MetricBuilder}.
 * @see MetricBuilder
 * @see MetricKey
 *
 */
@Generated("mdsal-binding-generator")
public interface Metric
    extends
    ChildOf<Metrics>,
    Augmentable<Metric>,
    LabelSet,
    MetricPointSet,
    Identifiable<MetricKey>
{



    public static final @NonNull QName QNAME = $YangModuleInfoImpl.qnameOf("metric");

    @Override
    default Class<org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.prometheus.rev211118.metrics.Metric> implementedInterface() {
        return org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.prometheus.rev211118.metrics.Metric.class;
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
    static int bindingHashCode(final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.prometheus.rev211118.metrics.@NonNull Metric obj) {
        final int prime = 31;
        int result = 1;
        result = prime * result + Objects.hashCode(obj.getLabelSetId());
        result = prime * result + Objects.hashCode(obj.getLabels());
        result = prime * result + Objects.hashCode(obj.getMetricPoints());
        result = prime * result + Objects.hashCode(obj.getName());
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
    static boolean bindingEquals(final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.prometheus.rev211118.metrics.@NonNull Metric thisObj, final Object obj) {
        if (thisObj == obj) {
            return true;
        }
        final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.prometheus.rev211118.metrics.Metric other = CodeHelpers.checkCast(org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.prometheus.rev211118.metrics.Metric.class, obj);
        if (other == null) {
            return false;
        }
        if (!Objects.equals(thisObj.getLabelSetId(), other.getLabelSetId())) {
            return false;
        }
        if (!Objects.equals(thisObj.getName(), other.getName())) {
            return false;
        }
        if (!Objects.equals(thisObj.getLabels(), other.getLabels())) {
            return false;
        }
        if (!Objects.equals(thisObj.getMetricPoints(), other.getMetricPoints())) {
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
    static String bindingToString(final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.prometheus.rev211118.metrics.@NonNull Metric obj) {
        final MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper("Metric");
        CodeHelpers.appendValue(helper, "labelSetId", obj.getLabelSetId());
        CodeHelpers.appendValue(helper, "labels", obj.getLabels());
        CodeHelpers.appendValue(helper, "metricPoints", obj.getMetricPoints());
        CodeHelpers.appendValue(helper, "name", obj.getName());
        CodeHelpers.appendValue(helper, "augmentation", obj.augmentations().values());
        return helper.toString();
    }
    
    /**
     * Return labelSetId, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         LabelSet ID that uniquely identifies a Metric within a MetricFamily. Identifies
     *         generated from the calculated hash of the LabelSet dictionary. Prometheus
     *         internally generates hashes in order to identify label sets.
     *     </code>
     * </pre>
     *
     * @return {@code java.lang.String} labelSetId, or {@code null} if it is not present.
     *
     */
    String getLabelSetId();
    
    /**
     * Return name, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Name of the Metric. Combination of the MetricFamily's name plus one of the
     *         available suffixes - if any - given the type of the Metric. Suffixes for the
     *         respective types are: Counter: '_total', '_created' Summary: '_count', '_sum',
     *         '_created', '' (empty) Histogram: '_count', '_sum', '_bucket', '_created'
     *         GaugeHistogram: '_gcount', '_gsum', '_bucket' Info: '_info' Gauge: '' (empty)
     *         StateSet: '' (empty) Unknown: '' (empty)
     *     </code>
     * </pre>
     *
     * @return {@code java.lang.String} name, or {@code null} if it is not present.
     *
     */
    String getName();
    
    @Override
    MetricKey key();

}

