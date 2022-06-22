package org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.gnmic.rev210719.value.set;
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
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.gnmic.rev210719.$YangModuleInfoImpl;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.gnmic.rev210719.ValueSet;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.gnmic.rev210719.value.set.values.Value;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.gnmic.rev210719.value.set.values.ValueKey;
import org.opendaylight.yangtools.yang.binding.Augmentable;
import org.opendaylight.yangtools.yang.binding.ChildOf;
import org.opendaylight.yangtools.yang.binding.CodeHelpers;
import org.opendaylight.yangtools.yang.common.QName;

/**
 * Enclosing container for the list of values associated with an event.
 * 
 * <p>
 * This class represents the following YANG schema fragment defined in module <b>gnmic</b>
 * <pre>
 * container values {
 *   list value {
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
 * <i>gnmic/value-set/values</i>
 * 
 * <p>To create instances of this class use {@link ValuesBuilder}.
 * @see ValuesBuilder
 *
 */
@Generated("mdsal-binding-generator")
public interface Values
    extends
    ChildOf<ValueSet>,
    Augmentable<Values>
{



    public static final @NonNull QName QNAME = $YangModuleInfoImpl.qnameOf("values");

    @Override
    default Class<org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.gnmic.rev210719.value.set.Values> implementedInterface() {
        return org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.gnmic.rev210719.value.set.Values.class;
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
    static int bindingHashCode(final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.gnmic.rev210719.value.set.@NonNull Values obj) {
        final int prime = 31;
        int result = 1;
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
    static boolean bindingEquals(final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.gnmic.rev210719.value.set.@NonNull Values thisObj, final Object obj) {
        if (thisObj == obj) {
            return true;
        }
        final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.gnmic.rev210719.value.set.Values other = CodeHelpers.checkCast(org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.gnmic.rev210719.value.set.Values.class, obj);
        if (other == null) {
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
    static String bindingToString(final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.gnmic.rev210719.value.set.@NonNull Values obj) {
        final MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper("Values");
        CodeHelpers.appendValue(helper, "value", obj.getValue());
        CodeHelpers.appendValue(helper, "augmentation", obj.augmentations().values());
        return helper.toString();
    }
    
    /**
     * Return value, or {@code null} if it is not present.
     * 
     * <pre>
     *     <code>
     *         A name-value pair.
     *     </code>
     * </pre>
     * 
     * @return {@code java.util.Map} value, or {@code null} if it is not present.
     *
     */
    @Nullable Map<ValueKey, Value> getValue();
    
    /**
     * Return value, or an empty list if it is not present.
     * 
     * @return {@code java.util.Map} value, or an empty list if it is not present.
     *
     */
    default @NonNull Map<ValueKey, Value> nonnullValue() {
        return CodeHelpers.nonnull(getValue());
    }

}

