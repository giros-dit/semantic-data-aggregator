package org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.label.set.labels;
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
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.label.set.Labels;
import org.opendaylight.yangtools.yang.binding.Augmentable;
import org.opendaylight.yangtools.yang.binding.ChildOf;
import org.opendaylight.yangtools.yang.binding.CodeHelpers;
import org.opendaylight.yangtools.yang.binding.Identifiable;
import org.opendaylight.yangtools.yang.common.QName;

/**
 * A name-value pair. These are used in multiple places: identifying timeseries, 
 * value of INFO metrics, and exemplars in Histograms
 *
 * <p>
 * This class represents the following YANG schema fragment defined in module <b>openmetrics</b>
 * <pre>
 * list label {
 *   key name;
 *   leaf name {
 *     type string;
 *   }
 *   leaf value {
 *     type string;
 *   }
 * }
 * </pre>The schema path to identify an instance is
 * <i>openmetrics/label-set/labels/label</i>
 *
 * <p>To create instances of this class use {@link LabelBuilder}.
 * @see LabelBuilder
 * @see LabelKey
 *
 */
@Generated("mdsal-binding-generator")
public interface Label
    extends
    ChildOf<Labels>,
    Augmentable<Label>,
    Identifiable<LabelKey>
{



    public static final @NonNull QName QNAME = $YangModuleInfoImpl.qnameOf("label");

    @Override
    default Class<org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.label.set.labels.Label> implementedInterface() {
        return org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.label.set.labels.Label.class;
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
    static int bindingHashCode(final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.label.set.labels.@NonNull Label obj) {
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
    static boolean bindingEquals(final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.label.set.labels.@NonNull Label thisObj, final Object obj) {
        if (thisObj == obj) {
            return true;
        }
        final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.label.set.labels.Label other = CodeHelpers.checkCast(org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.label.set.labels.Label.class, obj);
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
    static String bindingToString(final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.label.set.labels.@NonNull Label obj) {
        final MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper("Label");
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
     *         Key of the label
     *     </code>
     * </pre>
     *
     * @return {@code java.lang.String} name, or {@code null} if it is not present.
     *
     */
    String getName();
    
    /**
     * Return value, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Value of the label's key
     *     </code>
     * </pre>
     *
     * @return {@code java.lang.String} value, or {@code null} if it is not present.
     *
     */
    String getValue();
    
    @Override
    LabelKey key();

}

