package org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.openconfig.interfaces.aggregated.rev220513.interfaces._interface.throughput.kpi.notification;
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
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.openconfig.interfaces.aggregated.rev220513.PerDecimal;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.openconfig.interfaces.aggregated.rev220513.interfaces._interface.ThroughputKpiNotification;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715.Timestamp;
import org.opendaylight.yangtools.yang.binding.Augmentable;
import org.opendaylight.yangtools.yang.binding.ChildOf;
import org.opendaylight.yangtools.yang.binding.CodeHelpers;
import org.opendaylight.yangtools.yang.common.QName;

/**
 * Enclosing container for the Throughput KPI metadata.
 *
 * <p>
 * This class represents the following YANG schema fragment defined in module <b>openconfig-interfaces-kpis</b>
 * <pre>
 * container throughput-kpi {
 *   config false;
 *   leaf throughput-in {
 *     type per-decimal;
 *     units bit/s;
 *   }
 *   leaf throughput-out {
 *     type per-decimal;
 *     units bit/s;
 *   }
 *   leaf duration {
 *     type yang:timestamp;
 *     units seconds;
 *   }
 * }
 * </pre>The schema path to identify an instance is
 * <i>openconfig-interfaces-kpis/interfaces/interface/(http://data-aggregator.com/ns/openconfig-interfaces-aggregated?revision=2022-05-13)throughput-kpi-notification/throughput-kpi</i>
 *
 * <p>To create instances of this class use {@link ThroughputKpiBuilder}.
 * @see ThroughputKpiBuilder
 *
 */
@Generated("mdsal-binding-generator")
public interface ThroughputKpi
    extends
    ChildOf<ThroughputKpiNotification>,
    Augmentable<ThroughputKpi>
{



    public static final @NonNull QName QNAME = $YangModuleInfoImpl.qnameOf("throughput-kpi");

    @Override
    default Class<org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.openconfig.interfaces.aggregated.rev220513.interfaces._interface.throughput.kpi.notification.ThroughputKpi> implementedInterface() {
        return org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.openconfig.interfaces.aggregated.rev220513.interfaces._interface.throughput.kpi.notification.ThroughputKpi.class;
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
    static int bindingHashCode(final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.openconfig.interfaces.aggregated.rev220513.interfaces._interface.throughput.kpi.notification.@NonNull ThroughputKpi obj) {
        final int prime = 31;
        int result = 1;
        result = prime * result + Objects.hashCode(obj.getDuration());
        result = prime * result + Objects.hashCode(obj.getThroughputIn());
        result = prime * result + Objects.hashCode(obj.getThroughputOut());
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
    static boolean bindingEquals(final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.openconfig.interfaces.aggregated.rev220513.interfaces._interface.throughput.kpi.notification.@NonNull ThroughputKpi thisObj, final Object obj) {
        if (thisObj == obj) {
            return true;
        }
        final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.openconfig.interfaces.aggregated.rev220513.interfaces._interface.throughput.kpi.notification.ThroughputKpi other = CodeHelpers.checkCast(org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.openconfig.interfaces.aggregated.rev220513.interfaces._interface.throughput.kpi.notification.ThroughputKpi.class, obj);
        if (other == null) {
            return false;
        }
        if (!Objects.equals(thisObj.getDuration(), other.getDuration())) {
            return false;
        }
        if (!Objects.equals(thisObj.getThroughputIn(), other.getThroughputIn())) {
            return false;
        }
        if (!Objects.equals(thisObj.getThroughputOut(), other.getThroughputOut())) {
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
    static String bindingToString(final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.openconfig.interfaces.aggregated.rev220513.interfaces._interface.throughput.kpi.notification.@NonNull ThroughputKpi obj) {
        final MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper("ThroughputKpi");
        CodeHelpers.appendValue(helper, "duration", obj.getDuration());
        CodeHelpers.appendValue(helper, "throughputIn", obj.getThroughputIn());
        CodeHelpers.appendValue(helper, "throughputOut", obj.getThroughputOut());
        CodeHelpers.appendValue(helper, "augmentation", obj.augmentations().values());
        return helper.toString();
    }
    
    /**
     * Return throughputIn, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         The effective input data rate calculated as the number of bits per unit of time
     *         sent through a specific network device interface.
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.openconfig.interfaces.aggregated.rev220513.PerDecimal} throughputIn, or {@code null} if it is not present.
     *
     */
    PerDecimal getThroughputIn();
    
    /**
     * Return throughputOut, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         The effective output data rate calculated as the number of bits per unit of time
     *         sent through a specific network device interface.
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.openconfig.interfaces.aggregated.rev220513.PerDecimal} throughputOut, or {@code null} if it is not present.
     *
     */
    PerDecimal getThroughputOut();
    
    /**
     * Return duration, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Interval of the measurement to compute the KPI.
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715.Timestamp} duration, or {@code null} if it is not present.
     *
     */
    Timestamp getDuration();

}

