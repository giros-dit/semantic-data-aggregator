package org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.metric.families;
import com.google.common.base.MoreObjects;
import java.lang.Class;
import java.lang.NullPointerException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.util.Objects;
import javax.annotation.processing.Generated;
import org.eclipse.jdt.annotation.NonNull;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.$YangModuleInfoImpl;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.MetricFamilies;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.MetricSet;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.MetricType;
import org.opendaylight.yangtools.yang.binding.Augmentable;
import org.opendaylight.yangtools.yang.binding.ChildOf;
import org.opendaylight.yangtools.yang.binding.CodeHelpers;
import org.opendaylight.yangtools.yang.binding.Identifiable;
import org.opendaylight.yangtools.yang.common.QName;

/**
 * A MetricFamily MAY have zero or more Metrics. A MetricFamily MUST have a name, 
 * HELP, TYPE, and UNIT metadata. Every Metric within a MetricFamily MUST have a 
 * unique LabelSet.
 *
 * <p>
 * This class represents the following YANG schema fragment defined in module <b>openmetrics</b>
 * <pre>
 * list metric-family {
 *   key name;
 *   leaf name {
 *     type string;
 *   }
 *   leaf metric-type {
 *     type metric-type;
 *   }
 *   leaf unit {
 *     type string;
 *   }
 *   leaf help {
 *     type string;
 *   }
 *   uses metric-set;
 * }
 * </pre>The schema path to identify an instance is
 * <i>openmetrics/metric-families/metric-family</i>
 *
 * <p>To create instances of this class use {@link MetricFamilyBuilder}.
 * @see MetricFamilyBuilder
 * @see MetricFamilyKey
 *
 */
@Generated("mdsal-binding-generator")
public interface MetricFamily
    extends
    ChildOf<MetricFamilies>,
    Augmentable<MetricFamily>,
    MetricSet,
    Identifiable<MetricFamilyKey>
{



    public static final @NonNull QName QNAME = $YangModuleInfoImpl.qnameOf("metric-family");

    @Override
    default Class<org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.metric.families.MetricFamily> implementedInterface() {
        return org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.metric.families.MetricFamily.class;
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
    static int bindingHashCode(final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.metric.families.@NonNull MetricFamily obj) {
        final int prime = 31;
        int result = 1;
        result = prime * result + Objects.hashCode(obj.getHelp());
        result = prime * result + Objects.hashCode(obj.getMetricType());
        result = prime * result + Objects.hashCode(obj.getMetrics());
        result = prime * result + Objects.hashCode(obj.getName());
        result = prime * result + Objects.hashCode(obj.getUnit());
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
    static boolean bindingEquals(final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.metric.families.@NonNull MetricFamily thisObj, final Object obj) {
        if (thisObj == obj) {
            return true;
        }
        final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.metric.families.MetricFamily other = CodeHelpers.checkCast(org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.metric.families.MetricFamily.class, obj);
        if (other == null) {
            return false;
        }
        if (!Objects.equals(thisObj.getHelp(), other.getHelp())) {
            return false;
        }
        if (!Objects.equals(thisObj.getName(), other.getName())) {
            return false;
        }
        if (!Objects.equals(thisObj.getUnit(), other.getUnit())) {
            return false;
        }
        if (!Objects.equals(thisObj.getMetricType(), other.getMetricType())) {
            return false;
        }
        if (!Objects.equals(thisObj.getMetrics(), other.getMetrics())) {
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
    static String bindingToString(final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.metric.families.@NonNull MetricFamily obj) {
        final MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper("MetricFamily");
        CodeHelpers.appendValue(helper, "help", obj.getHelp());
        CodeHelpers.appendValue(helper, "metricType", obj.getMetricType());
        CodeHelpers.appendValue(helper, "metrics", obj.getMetrics());
        CodeHelpers.appendValue(helper, "name", obj.getName());
        CodeHelpers.appendValue(helper, "unit", obj.getUnit());
        CodeHelpers.appendValue(helper, "augmentation", obj.augmentations().values());
        return helper.toString();
    }
    
    /**
     * Return name, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         MetricFamily names are a string and MUST be unique within a MetricSet. Names
     *         SHOULD be in snake_case. Metric names MUST follow the restrictions in the ABNF
     *         section. Colons in MetricFamily names are RESERVED to signal that the
     *         MetricFamily is the result of a calculation or aggregation of a general purpose
     *         monitoring system. MetricFamily names beginning with underscores are RESERVED
     *         and MUST NOT be used unless specified by this standard. The name of a
     *         MetricFamily MUST NOT result in a potential clash for sample metric names as per
     *         the ABNF with another MetricFamily in the Text Format within a MetricSet. An
     *         example would be a gauge called 'foo_created' as a counter called 'foo' could
     *         create a 'foo_created' in the text format.
     *     </code>
     * </pre>
     *
     * @return {@code java.lang.String} name, or {@code null} if it is not present.
     *
     */
    String getName();
    
    /**
     * Return metricType, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Type specifies the MetricFamily type
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.MetricType} metricType, or {@code null} if it is not present.
     *
     */
    MetricType getMetricType();
    
    /**
     * Return unit, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Unit specifies MetricFamily units. If non-empty, it MUST be a suffix of the
     *         MetricFamily name separated by an underscore. Be aware that further generation
     *         rules might make it an infix in the text format.
     *     </code>
     * </pre>
     *
     * @return {@code java.lang.String} unit, or {@code null} if it is not present.
     *
     */
    String getUnit();
    
    /**
     * Return help, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Help is a string and SHOULD be non-empty. It is used to give a brief description
     *         of the MetricFamily for human consumption and SHOULD be short enough to be used
     *         as a tooltip.
     *     </code>
     * </pre>
     *
     * @return {@code java.lang.String} help, or {@code null} if it is not present.
     *
     */
    String getHelp();
    
    @Override
    MetricFamilyKey key();

}

