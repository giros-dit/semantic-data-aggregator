package org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.logparser.so.metrics.rev210927.metric.set.metrics;
import com.google.common.base.MoreObjects;
import java.lang.Class;
import java.lang.Long;
import java.lang.NullPointerException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.util.Objects;
import javax.annotation.processing.Generated;
import org.eclipse.jdt.annotation.NonNull;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.logparser.so.metrics.rev210927.$YangModuleInfoImpl;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.logparser.so.metrics.rev210927.MetricIdentity;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.logparser.so.metrics.rev210927.metric.set.Metrics;
import org.opendaylight.yangtools.yang.binding.Augmentable;
import org.opendaylight.yangtools.yang.binding.ChildOf;
import org.opendaylight.yangtools.yang.binding.CodeHelpers;
import org.opendaylight.yangtools.yang.binding.Identifiable;
import org.opendaylight.yangtools.yang.common.QName;

/**
 * A name-value pair for the LogParser Service Orchestrator (SO) metrics.
 *
 * <p>
 * This class represents the following YANG schema fragment defined in module <b>logparser-so</b>
 * <pre>
 * list metric {
 *   key name;
 *   leaf name {
 *     type identityref {
 *       base metric-identity;
 *     }
 *   }
 *   leaf value {
 *     type int64;
 *   }
 * }
 * </pre>The schema path to identify an instance is
 * <i>logparser-so/metric-set/metrics/metric</i>
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
    Identifiable<MetricKey>
{



    public static final @NonNull QName QNAME = $YangModuleInfoImpl.qnameOf("metric");

    @Override
    default Class<org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.logparser.so.metrics.rev210927.metric.set.metrics.Metric> implementedInterface() {
        return org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.logparser.so.metrics.rev210927.metric.set.metrics.Metric.class;
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
    static int bindingHashCode(final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.logparser.so.metrics.rev210927.metric.set.metrics.@NonNull Metric obj) {
        final int prime = 31;
        int result = 1;
        result = prime * result + Objects.hashCode(obj.getName());
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
    static boolean bindingEquals(final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.logparser.so.metrics.rev210927.metric.set.metrics.@NonNull Metric thisObj, final Object obj) {
        if (thisObj == obj) {
            return true;
        }
        final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.logparser.so.metrics.rev210927.metric.set.metrics.Metric other = CodeHelpers.checkCast(org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.logparser.so.metrics.rev210927.metric.set.metrics.Metric.class, obj);
        if (other == null) {
            return false;
        }
        if (!Objects.equals(thisObj.getName(), other.getName())) {
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
    static String bindingToString(final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.logparser.so.metrics.rev210927.metric.set.metrics.@NonNull Metric obj) {
        final MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper("Metric");
        CodeHelpers.appendValue(helper, "name", obj.getName());
        CodeHelpers.appendValue(helper, "value", obj.getValue());
        CodeHelpers.appendValue(helper, "augmentation", obj.augmentations().values());
        return helper.toString();
    }
    
    /**
     * Return name, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Key of the LogParser SO metric.
     *     </code>
     * </pre>
     *
     * @return {@code java.lang.Class} name, or {@code null} if it is not present.
     *
     */
    Class<? extends MetricIdentity> getName();
    
    /**
     * Return value, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Value of the LogParser SO metric's key.
     *     </code>
     * </pre>
     *
     * @return {@code java.lang.Long} value, or {@code null} if it is not present.
     *
     */
    Long getValue();
    
    @Override
    MetricKey key();

}

