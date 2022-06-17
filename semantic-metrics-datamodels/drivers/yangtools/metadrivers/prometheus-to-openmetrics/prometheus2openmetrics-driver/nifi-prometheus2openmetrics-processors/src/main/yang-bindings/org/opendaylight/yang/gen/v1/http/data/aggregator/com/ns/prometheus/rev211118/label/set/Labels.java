package org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.prometheus.rev211118.label.set;
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
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.prometheus.rev211118.$YangModuleInfoImpl;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.prometheus.rev211118.LabelSet;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.prometheus.rev211118.label.set.labels.Label;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.prometheus.rev211118.label.set.labels.LabelKey;
import org.opendaylight.yangtools.yang.binding.Augmentable;
import org.opendaylight.yangtools.yang.binding.ChildOf;
import org.opendaylight.yangtools.yang.binding.CodeHelpers;
import org.opendaylight.yangtools.yang.common.QName;

/**
 * Enclosing container for the list of Labels associated with a Metric
 *
 * <p>
 * This class represents the following YANG schema fragment defined in module <b>prometheus</b>
 * <pre>
 * container labels {
 *   list label {
 *     key name;
 *     leaf name {
 *       type string;
 *     }
 *     leaf value {
 *       type string;
 *     }
 *   }
 * }
 * </pre>The schema path to identify an instance is
 * <i>prometheus/label-set/labels</i>
 *
 * <p>To create instances of this class use {@link LabelsBuilder}.
 * @see LabelsBuilder
 *
 */
@Generated("mdsal-binding-generator")
public interface Labels
    extends
    ChildOf<LabelSet>,
    Augmentable<Labels>
{



    public static final @NonNull QName QNAME = $YangModuleInfoImpl.qnameOf("labels");

    @Override
    default Class<org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.prometheus.rev211118.label.set.Labels> implementedInterface() {
        return org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.prometheus.rev211118.label.set.Labels.class;
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
    static int bindingHashCode(final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.prometheus.rev211118.label.set.@NonNull Labels obj) {
        final int prime = 31;
        int result = 1;
        result = prime * result + Objects.hashCode(obj.getLabel());
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
    static boolean bindingEquals(final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.prometheus.rev211118.label.set.@NonNull Labels thisObj, final Object obj) {
        if (thisObj == obj) {
            return true;
        }
        final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.prometheus.rev211118.label.set.Labels other = CodeHelpers.checkCast(org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.prometheus.rev211118.label.set.Labels.class, obj);
        if (other == null) {
            return false;
        }
        if (!Objects.equals(thisObj.getLabel(), other.getLabel())) {
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
    static String bindingToString(final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.prometheus.rev211118.label.set.@NonNull Labels obj) {
        final MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper("Labels");
        CodeHelpers.appendValue(helper, "label", obj.getLabel());
        CodeHelpers.appendValue(helper, "augmentation", obj.augmentations().values());
        return helper.toString();
    }
    
    /**
     * Return label, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         A name-value pair. These are used in multiple places: identifying timeseries,
     *         value of INFO metrics, and exemplars in Histograms
     *     </code>
     * </pre>
     *
     * @return {@code java.util.Map} label, or {@code null} if it is not present.
     *
     */
    @Nullable Map<LabelKey, Label> getLabel();
    
    /**
     * Return label, or an empty list if it is not present.
     *
     * @return {@code java.util.Map} label, or an empty list if it is not present.
     *
     */
    default @NonNull Map<LabelKey, Label> nonnullLabel() {
        return CodeHelpers.nonnull(getLabel());
    }

}

