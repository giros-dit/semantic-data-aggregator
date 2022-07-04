package org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.openconfig.interfaces.aggregated.rev220513.interfaces._interface;
import com.google.common.base.MoreObjects;
import java.lang.Class;
import java.lang.NullPointerException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.util.Objects;
import javax.annotation.processing.Generated;
import org.eclipse.jdt.annotation.NonNull;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.openconfig.interfaces.aggregated.rev220513.$YangModuleInfoImpl;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.openconfig.interfaces.aggregated.rev220513.Interface1;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.openconfig.interfaces.aggregated.rev220513.interfaces._interface.throughput.kpi.notification.ThroughputKpi;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715.DateAndTime;
import org.opendaylight.yangtools.yang.binding.Augmentable;
import org.opendaylight.yangtools.yang.binding.ChildOf;
import org.opendaylight.yangtools.yang.binding.CodeHelpers;
import org.opendaylight.yangtools.yang.common.QName;

/**
 * Enclosing container for the notification event generated about the Throughput 
 * KPI.
 *
 * <p>
 * This class represents the following YANG schema fragment defined in module <b>openconfig-interfaces-kpis</b>
 * <pre>
 * container throughput-kpi-notification {
 *   config false;
 *   leaf event-time {
 *     type yang:date-and-time;
 *     units true;
 *   }
 *   container throughput-kpi {
 *     config false;
 *     leaf throughput-in {
 *       type per-decimal;
 *       units Mbit/s;
 *     }
 *     leaf throughput-out {
 *       type per-decimal;
 *       units Mbit/s;
 *     }
 *     leaf duration {
 *       type yang:timestamp;
 *       units milliseconds;
 *     }
 *   }
 * }
 * </pre>The schema path to identify an instance is
 * <i>openconfig-interfaces-kpis/interfaces/interface/(http://data-aggregator.com/ns/openconfig-interfaces-aggregated?revision=2022-05-13)throughput-kpi-notification</i>
 *
 * <p>To create instances of this class use {@link ThroughputKpiNotificationBuilder}.
 * @see ThroughputKpiNotificationBuilder
 *
 */
@Generated("mdsal-binding-generator")
public interface ThroughputKpiNotification
    extends
    ChildOf<Interface1>,
    Augmentable<ThroughputKpiNotification>
{



    public static final @NonNull QName QNAME = $YangModuleInfoImpl.qnameOf("throughput-kpi-notification");

    @Override
    default Class<org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.openconfig.interfaces.aggregated.rev220513.interfaces._interface.ThroughputKpiNotification> implementedInterface() {
        return org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.openconfig.interfaces.aggregated.rev220513.interfaces._interface.ThroughputKpiNotification.class;
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
    static int bindingHashCode(final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.openconfig.interfaces.aggregated.rev220513.interfaces._interface.@NonNull ThroughputKpiNotification obj) {
        final int prime = 31;
        int result = 1;
        result = prime * result + Objects.hashCode(obj.getEventTime());
        result = prime * result + Objects.hashCode(obj.getThroughputKpi());
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
    static boolean bindingEquals(final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.openconfig.interfaces.aggregated.rev220513.interfaces._interface.@NonNull ThroughputKpiNotification thisObj, final Object obj) {
        if (thisObj == obj) {
            return true;
        }
        final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.openconfig.interfaces.aggregated.rev220513.interfaces._interface.ThroughputKpiNotification other = CodeHelpers.checkCast(org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.openconfig.interfaces.aggregated.rev220513.interfaces._interface.ThroughputKpiNotification.class, obj);
        if (other == null) {
            return false;
        }
        if (!Objects.equals(thisObj.getEventTime(), other.getEventTime())) {
            return false;
        }
        if (!Objects.equals(thisObj.getThroughputKpi(), other.getThroughputKpi())) {
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
    static String bindingToString(final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.openconfig.interfaces.aggregated.rev220513.interfaces._interface.@NonNull ThroughputKpiNotification obj) {
        final MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper("ThroughputKpiNotification");
        CodeHelpers.appendValue(helper, "eventTime", obj.getEventTime());
        CodeHelpers.appendValue(helper, "throughputKpi", obj.getThroughputKpi());
        CodeHelpers.appendValue(helper, "augmentation", obj.augmentations().values());
        return helper.toString();
    }
    
    /**
     * Return eventTime, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Date and time in which the notification event generated (following the ISO 8601
     *         format).
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715.DateAndTime} eventTime, or {@code null} if it is not present.
     *
     */
    DateAndTime getEventTime();
    
    /**
     * Return throughputKpi, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         The effective data rate calculated as the number of bits per unit of time sent
     *         through a specific network device interface.
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.openconfig.interfaces.aggregated.rev220513.interfaces._interface.throughput.kpi.notification.ThroughputKpi} throughputKpi, or {@code null} if it is not present.
     *
     */
    ThroughputKpi getThroughputKpi();

}

