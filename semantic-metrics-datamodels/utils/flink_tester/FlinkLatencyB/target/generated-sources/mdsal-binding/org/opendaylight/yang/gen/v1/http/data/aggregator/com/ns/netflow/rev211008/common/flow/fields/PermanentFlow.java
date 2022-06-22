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
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715.Counter64;
import org.opendaylight.yangtools.yang.binding.Augmentable;
import org.opendaylight.yangtools.yang.binding.ChildOf;
import org.opendaylight.yangtools.yang.binding.CodeHelpers;
import org.opendaylight.yangtools.yang.common.QName;

/**
 * This container collects all metrics related to a permanent flow
 * 
 * <p>
 * This class represents the following YANG schema fragment defined in module <b>netflow-v9</b>
 * <pre>
 * container permanent-flow {
 *   leaf bytes-in {
 *     type yang:counter64;
 *     units bytes;
 *   }
 *   leaf pkts-in {
 *     type yang:counter64;
 *     units packets;
 *   }
 * }
 * </pre>The schema path to identify an instance is
 * <i>netflow-v9/common-flow-fields/permanent-flow</i>
 * 
 * <p>To create instances of this class use {@link PermanentFlowBuilder}.
 * @see PermanentFlowBuilder
 *
 */
@Generated("mdsal-binding-generator")
public interface PermanentFlow
    extends
    ChildOf<CommonFlowFields>,
    Augmentable<PermanentFlow>
{



    public static final @NonNull QName QNAME = $YangModuleInfoImpl.qnameOf("permanent-flow");

    @Override
    default Class<org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.common.flow.fields.PermanentFlow> implementedInterface() {
        return org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.common.flow.fields.PermanentFlow.class;
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
    static int bindingHashCode(final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.common.flow.fields.@NonNull PermanentFlow obj) {
        final int prime = 31;
        int result = 1;
        result = prime * result + Objects.hashCode(obj.getBytesIn());
        result = prime * result + Objects.hashCode(obj.getPktsIn());
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
    static boolean bindingEquals(final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.common.flow.fields.@NonNull PermanentFlow thisObj, final Object obj) {
        if (thisObj == obj) {
            return true;
        }
        final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.common.flow.fields.PermanentFlow other = CodeHelpers.checkCast(org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.common.flow.fields.PermanentFlow.class, obj);
        if (other == null) {
            return false;
        }
        if (!Objects.equals(thisObj.getBytesIn(), other.getBytesIn())) {
            return false;
        }
        if (!Objects.equals(thisObj.getPktsIn(), other.getPktsIn())) {
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
    static String bindingToString(final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.common.flow.fields.@NonNull PermanentFlow obj) {
        final MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper("PermanentFlow");
        CodeHelpers.appendValue(helper, "bytesIn", obj.getBytesIn());
        CodeHelpers.appendValue(helper, "pktsIn", obj.getPktsIn());
        CodeHelpers.appendValue(helper, "augmentation", obj.augmentations().values());
        return helper.toString();
    }
    
    /**
     * Return bytesIn, or {@code null} if it is not present.
     * 
     * <pre>
     *     <code>
     *         Running byte counter for a permanent flow
     *     </code>
     * </pre>
     * 
     * @return {@code org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715.Counter64} bytesIn, or {@code null} if it is not present.
     *
     */
    Counter64 getBytesIn();
    
    /**
     * Return pktsIn, or {@code null} if it is not present.
     * 
     * <pre>
     *     <code>
     *         Running packet counter for a permanent flow
     *     </code>
     * </pre>
     * 
     * @return {@code org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715.Counter64} pktsIn, or {@code null} if it is not present.
     *
     */
    Counter64 getPktsIn();

}

