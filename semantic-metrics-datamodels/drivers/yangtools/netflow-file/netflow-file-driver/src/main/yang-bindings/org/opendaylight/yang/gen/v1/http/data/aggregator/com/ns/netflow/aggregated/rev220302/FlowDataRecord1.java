package org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.aggregated.rev220302;
import com.google.common.base.MoreObjects;
import java.lang.Class;
import java.lang.NullPointerException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.util.Objects;
import javax.annotation.processing.Generated;
import org.eclipse.jdt.annotation.NonNull;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.netflow.export.packet.FlowDataRecord;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715.Timestamp;
import org.opendaylight.yangtools.yang.binding.Augmentation;
import org.opendaylight.yangtools.yang.binding.CodeHelpers;

@Generated("mdsal-binding-generator")
public interface FlowDataRecord1
    extends
    Augmentation<FlowDataRecord>
{




    @Override
    default Class<org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.aggregated.rev220302.FlowDataRecord1> implementedInterface() {
        return org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.aggregated.rev220302.FlowDataRecord1.class;
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
    static int bindingHashCode(final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.aggregated.rev220302.@NonNull FlowDataRecord1 obj) {
        final int prime = 31;
        int result = 1;
        result = prime * result + Objects.hashCode(obj.getBytesInPerPacket());
        result = prime * result + Objects.hashCode(obj.getBytesInPerSecond());
        result = prime * result + Objects.hashCode(obj.getBytesOutPerPacket());
        result = prime * result + Objects.hashCode(obj.getBytesOutPerSecond());
        result = prime * result + Objects.hashCode(obj.getFlowDuration());
        result = prime * result + Objects.hashCode(obj.getPktsInPerSecond());
        result = prime * result + Objects.hashCode(obj.getPktsOutPerSecond());
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
    static boolean bindingEquals(final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.aggregated.rev220302.@NonNull FlowDataRecord1 thisObj, final Object obj) {
        if (thisObj == obj) {
            return true;
        }
        final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.aggregated.rev220302.FlowDataRecord1 other = CodeHelpers.checkCast(org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.aggregated.rev220302.FlowDataRecord1.class, obj);
        if (other == null) {
            return false;
        }
        if (!Objects.equals(thisObj.getBytesInPerPacket(), other.getBytesInPerPacket())) {
            return false;
        }
        if (!Objects.equals(thisObj.getBytesInPerSecond(), other.getBytesInPerSecond())) {
            return false;
        }
        if (!Objects.equals(thisObj.getBytesOutPerPacket(), other.getBytesOutPerPacket())) {
            return false;
        }
        if (!Objects.equals(thisObj.getBytesOutPerSecond(), other.getBytesOutPerSecond())) {
            return false;
        }
        if (!Objects.equals(thisObj.getFlowDuration(), other.getFlowDuration())) {
            return false;
        }
        if (!Objects.equals(thisObj.getPktsInPerSecond(), other.getPktsInPerSecond())) {
            return false;
        }
        if (!Objects.equals(thisObj.getPktsOutPerSecond(), other.getPktsOutPerSecond())) {
            return false;
        }
        return true;
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
    static String bindingToString(final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.aggregated.rev220302.@NonNull FlowDataRecord1 obj) {
        final MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper("FlowDataRecord1");
        CodeHelpers.appendValue(helper, "bytesInPerPacket", obj.getBytesInPerPacket());
        CodeHelpers.appendValue(helper, "bytesInPerSecond", obj.getBytesInPerSecond());
        CodeHelpers.appendValue(helper, "bytesOutPerPacket", obj.getBytesOutPerPacket());
        CodeHelpers.appendValue(helper, "bytesOutPerSecond", obj.getBytesOutPerSecond());
        CodeHelpers.appendValue(helper, "flowDuration", obj.getFlowDuration());
        CodeHelpers.appendValue(helper, "pktsInPerSecond", obj.getPktsInPerSecond());
        CodeHelpers.appendValue(helper, "pktsOutPerSecond", obj.getPktsOutPerSecond());
        return helper.toString();
    }
    
    /**
     * Return flowDuration, or {@code null} if it is not present.
     * 
     * <pre>
     *     <code>
     *         Duration of the flow. Difference between last-switched and first-switched leafs
     *     </code>
     * </pre>
     * 
     * @return {@code org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715.Timestamp} flowDuration, or {@code null} if it is not present.
     *
     */
    Timestamp getFlowDuration();
    
    /**
     * Return bytesInPerSecond, or {@code null} if it is not present.
     * 
     * <pre>
     *     <code>
     *         Incoming counter for the number of bytes per second associated with an IP flow.
     *     </code>
     * </pre>
     * 
     * @return {@code org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.aggregated.rev220302.PerDecimal} bytesInPerSecond, or {@code null} if it is not present.
     *
     */
    PerDecimal getBytesInPerSecond();
    
    /**
     * Return bytesOutPerSecond, or {@code null} if it is not present.
     * 
     * <pre>
     *     <code>
     *         Outgoing counter for the number of bytes per second associated with an IP flow.
     *     </code>
     * </pre>
     * 
     * @return {@code org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.aggregated.rev220302.PerDecimal} bytesOutPerSecond, or {@code null} if it is not present.
     *
     */
    PerDecimal getBytesOutPerSecond();
    
    /**
     * Return pktsInPerSecond, or {@code null} if it is not present.
     * 
     * <pre>
     *     <code>
     *         Incoming counter for the number of packets per second associated with an IP
     *         flow.
     *     </code>
     * </pre>
     * 
     * @return {@code org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.aggregated.rev220302.PerDecimal} pktsInPerSecond, or {@code null} if it is not present.
     *
     */
    PerDecimal getPktsInPerSecond();
    
    /**
     * Return pktsOutPerSecond, or {@code null} if it is not present.
     * 
     * <pre>
     *     <code>
     *         Outgoing counter for the number of packets per second associated with an IP
     *         flow.
     *     </code>
     * </pre>
     * 
     * @return {@code org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.aggregated.rev220302.PerDecimal} pktsOutPerSecond, or {@code null} if it is not present.
     *
     */
    PerDecimal getPktsOutPerSecond();
    
    /**
     * Return bytesInPerPacket, or {@code null} if it is not present.
     * 
     * <pre>
     *     <code>
     *         Incoming counter for the number of bytes per packet associated with an IP flow.
     *     </code>
     * </pre>
     * 
     * @return {@code org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.aggregated.rev220302.PerDecimal} bytesInPerPacket, or {@code null} if it is not present.
     *
     */
    PerDecimal getBytesInPerPacket();
    
    /**
     * Return bytesOutPerPacket, or {@code null} if it is not present.
     * 
     * <pre>
     *     <code>
     *         Outgoing counter for the number of bytes per packet associated with an IP flow.
     *     </code>
     * </pre>
     * 
     * @return {@code org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.aggregated.rev220302.PerDecimal} bytesOutPerPacket, or {@code null} if it is not present.
     *
     */
    PerDecimal getBytesOutPerPacket();

}

