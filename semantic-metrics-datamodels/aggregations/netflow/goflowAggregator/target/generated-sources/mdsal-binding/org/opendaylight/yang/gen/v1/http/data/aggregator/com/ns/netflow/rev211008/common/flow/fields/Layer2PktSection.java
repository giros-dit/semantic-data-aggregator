package org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.common.flow.fields;
import com.google.common.base.MoreObjects;
import java.lang.Class;
import java.lang.NullPointerException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.util.Objects;
import javax.annotation.processing.Generated;
import org.eclipse.jdt.annotation.NonNull;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.$YangModuleInfoImpl;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFlowFields;
import org.opendaylight.yangtools.yang.binding.Augmentable;
import org.opendaylight.yangtools.yang.binding.ChildOf;
import org.opendaylight.yangtools.yang.binding.CodeHelpers;
import org.opendaylight.yangtools.yang.common.QName;
import org.opendaylight.yangtools.yang.common.Uint16;

/**
 * This container collects all metrics related to the Layer 2 packet section
 *
 * <p>
 * This class represents the following YANG schema fragment defined in module <b>netflow-v9</b>
 * <pre>
 * container layer2-pkt-section {
 *   leaf offset {
 *     type uint16;
 *   }
 *   leaf size {
 *     type uint16;
 *   }
 *   leaf data {
 *     type string;
 *   }
 * }
 * </pre>The schema path to identify an instance is
 * <i>netflow-v9/common-flow-fields/layer2-pkt-section</i>
 *
 * <p>To create instances of this class use {@link Layer2PktSectionBuilder}.
 * @see Layer2PktSectionBuilder
 *
 */
@Generated("mdsal-binding-generator")
public interface Layer2PktSection
    extends
    ChildOf<CommonFlowFields>,
    Augmentable<Layer2PktSection>
{



    public static final @NonNull QName QNAME = $YangModuleInfoImpl.qnameOf("layer2-pkt-section");

    @Override
    default Class<org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.common.flow.fields.Layer2PktSection> implementedInterface() {
        return org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.common.flow.fields.Layer2PktSection.class;
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
    static int bindingHashCode(final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.common.flow.fields.@NonNull Layer2PktSection obj) {
        final int prime = 31;
        int result = 1;
        result = prime * result + Objects.hashCode(obj.getData());
        result = prime * result + Objects.hashCode(obj.getOffset());
        result = prime * result + Objects.hashCode(obj.getSize());
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
    static boolean bindingEquals(final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.common.flow.fields.@NonNull Layer2PktSection thisObj, final Object obj) {
        if (thisObj == obj) {
            return true;
        }
        final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.common.flow.fields.Layer2PktSection other = CodeHelpers.checkCast(org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.common.flow.fields.Layer2PktSection.class, obj);
        if (other == null) {
            return false;
        }
        if (!Objects.equals(thisObj.getOffset(), other.getOffset())) {
            return false;
        }
        if (!Objects.equals(thisObj.getSize(), other.getSize())) {
            return false;
        }
        if (!Objects.equals(thisObj.getData(), other.getData())) {
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
    static String bindingToString(final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.common.flow.fields.@NonNull Layer2PktSection obj) {
        final MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper("Layer2PktSection");
        CodeHelpers.appendValue(helper, "data", obj.getData());
        CodeHelpers.appendValue(helper, "offset", obj.getOffset());
        CodeHelpers.appendValue(helper, "size", obj.getSize());
        CodeHelpers.appendValue(helper, "augmentation", obj.augmentations().values());
        return helper.toString();
    }
    
    /**
     * Return offset, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Layer 2 packet section offset. Potentially a generic offset
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yangtools.yang.common.Uint16} offset, or {@code null} if it is not present.
     *
     */
    Uint16 getOffset();
    
    /**
     * Return size, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Layer 2 packet section size. Potentially a generic size
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yangtools.yang.common.Uint16} size, or {@code null} if it is not present.
     *
     */
    Uint16 getSize();
    
    /**
     * Return data, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Layer 2 packet section data
     *     </code>
     * </pre>
     *
     * @return {@code java.lang.String} data, or {@code null} if it is not present.
     *
     */
    String getData();

}

