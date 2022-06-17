package org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.openconfig.interfaces.aggregated.rev220513;
import com.google.common.base.MoreObjects;
import java.lang.Class;
import java.lang.NullPointerException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.util.Objects;
import javax.annotation.processing.Generated;
import org.eclipse.jdt.annotation.NonNull;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.openconfig.interfaces.aggregated.rev220513.interfaces._interface.PacketLossKpiNotification;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.openconfig.interfaces.aggregated.rev220513.interfaces._interface.ThroughputKpiNotification;
import org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.interfaces.top.interfaces.Interface;
import org.opendaylight.yangtools.yang.binding.Augmentation;
import org.opendaylight.yangtools.yang.binding.CodeHelpers;

@Generated("mdsal-binding-generator")
public interface Interface1
    extends
    Augmentation<Interface>
{




    @Override
    default Class<org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.openconfig.interfaces.aggregated.rev220513.Interface1> implementedInterface() {
        return org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.openconfig.interfaces.aggregated.rev220513.Interface1.class;
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
    static int bindingHashCode(final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.openconfig.interfaces.aggregated.rev220513.@NonNull Interface1 obj) {
        final int prime = 31;
        int result = 1;
        result = prime * result + Objects.hashCode(obj.getPacketLossKpiNotification());
        result = prime * result + Objects.hashCode(obj.getThroughputKpiNotification());
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
    static boolean bindingEquals(final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.openconfig.interfaces.aggregated.rev220513.@NonNull Interface1 thisObj, final Object obj) {
        if (thisObj == obj) {
            return true;
        }
        final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.openconfig.interfaces.aggregated.rev220513.Interface1 other = CodeHelpers.checkCast(org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.openconfig.interfaces.aggregated.rev220513.Interface1.class, obj);
        if (other == null) {
            return false;
        }
        if (!Objects.equals(thisObj.getPacketLossKpiNotification(), other.getPacketLossKpiNotification())) {
            return false;
        }
        if (!Objects.equals(thisObj.getThroughputKpiNotification(), other.getThroughputKpiNotification())) {
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
    static String bindingToString(final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.openconfig.interfaces.aggregated.rev220513.@NonNull Interface1 obj) {
        final MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper("Interface1");
        CodeHelpers.appendValue(helper, "packetLossKpiNotification", obj.getPacketLossKpiNotification());
        CodeHelpers.appendValue(helper, "throughputKpiNotification", obj.getThroughputKpiNotification());
        return helper.toString();
    }
    
    /**
     * Return throughputKpiNotification, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Enclosing container for the notification event generated about the Throughput
     *         KPI.
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.openconfig.interfaces.aggregated.rev220513.interfaces._interface.ThroughputKpiNotification} throughputKpiNotification, or {@code null} if it is not present.
     *
     */
    ThroughputKpiNotification getThroughputKpiNotification();
    
    /**
     * Return packetLossKpiNotification, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Enclosing container for the notification event generated about the Packet Loss
     *         KPI.
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.openconfig.interfaces.aggregated.rev220513.interfaces._interface.PacketLossKpiNotification} packetLossKpiNotification, or {@code null} if it is not present.
     *
     */
    PacketLossKpiNotification getPacketLossKpiNotification();

}

