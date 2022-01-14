package org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008;
import com.google.common.base.MoreObjects;
import java.lang.Class;
import java.lang.NullPointerException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.util.List;
import java.util.Objects;
import javax.annotation.processing.Generated;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.netflow.FieldsFlowRecord;
import org.opendaylight.yangtools.yang.binding.Augmentable;
import org.opendaylight.yangtools.yang.binding.ChildOf;
import org.opendaylight.yangtools.yang.binding.CodeHelpers;
import org.opendaylight.yangtools.yang.common.QName;

/**
 * Netflow Exporter or Collector data node.
 *
 * <p>
 * This class represents the following YANG schema fragment defined in module <b>netflow-v9</b>
 * <pre>
 * container netflow {
 *   config false;
 *   list fields-flow-record {
 *     ordered-by user;
 *     key name;
 *     leaf name {
 *       type string;
 *     }
 *     uses common-fields;
 *   }
 * }
 * </pre>The schema path to identify an instance is
 * <i>netflow-v9/netflow</i>
 *
 * <p>To create instances of this class use {@link NetflowBuilder}.
 * @see NetflowBuilder
 *
 */
@Generated("mdsal-binding-generator")
public interface Netflow
    extends
    ChildOf<NetflowV9Data>,
    Augmentable<Netflow>
{



    public static final @NonNull QName QNAME = $YangModuleInfoImpl.qnameOf("netflow");

    @Override
    default Class<org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.Netflow> implementedInterface() {
        return org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.Netflow.class;
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
    static int bindingHashCode(final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.@NonNull Netflow obj) {
        final int prime = 31;
        int result = 1;
        result = prime * result + Objects.hashCode(obj.getFieldsFlowRecord());
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
    static boolean bindingEquals(final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.@NonNull Netflow thisObj, final Object obj) {
        if (thisObj == obj) {
            return true;
        }
        final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.Netflow other = CodeHelpers.checkCast(org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.Netflow.class, obj);
        if (other == null) {
            return false;
        }
        if (!Objects.equals(thisObj.getFieldsFlowRecord(), other.getFieldsFlowRecord())) {
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
    static String bindingToString(final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.@NonNull Netflow obj) {
        final MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper("Netflow");
        CodeHelpers.appendValue(helper, "fieldsFlowRecord", obj.getFieldsFlowRecord());
        CodeHelpers.appendValue(helper, "augmentation", obj.augmentations().values());
        return helper.toString();
    }
    
    /**
     * Return fieldsFlowRecord, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         This list contains fields of a Netflow flow record that is transmitted by the
     *         Exporting Process or received by the Collecting Process.
     *     </code>
     * </pre>
     *
     * @return {@code java.util.List} fieldsFlowRecord, or {@code null} if it is not present.
     *
     */
    @Nullable List<FieldsFlowRecord> getFieldsFlowRecord();
    
    /**
     * Return fieldsFlowRecord, or an empty list if it is not present.
     *
     * @return {@code java.util.List} fieldsFlowRecord, or an empty list if it is not present.
     *
     */
    default @NonNull List<FieldsFlowRecord> nonnullFieldsFlowRecord() {
        return CodeHelpers.nonnull(getFieldsFlowRecord());
    }

}

