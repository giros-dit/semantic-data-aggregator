package org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.logparser.so.metrics.rev210927;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;
import java.lang.Class;
import java.lang.Integer;
import java.lang.NullPointerException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import javax.annotation.processing.Generated;
import org.eclipse.jdt.annotation.NonNull;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715.DateAndTime;
import org.opendaylight.yangtools.yang.binding.Augmentable;
import org.opendaylight.yangtools.yang.binding.ChildOf;
import org.opendaylight.yangtools.yang.binding.CodeHelpers;
import org.opendaylight.yangtools.yang.binding.Enumeration;
import org.opendaylight.yangtools.yang.common.QName;

/**
 * Enclosing container for the time-related metrics provided by the 5Growth 
 * Orchestrator related logs during the network service instantiation, termination 
 * and scaling operations.
 *
 * <p>
 * This class represents the following YANG schema fragment defined in module <b>logparser-so</b>
 * <pre>
 * container logparser-so-metrics {
 *   config false;
 *   leaf Current_Time {
 *     type ietf-types:date-and-time;
 *   }
 *   leaf Operation {
 *     type enumeration {
 *       enum instantiation;
 *       enum scaling;
 *       enum termination;
 *     }
 *   }
 *   leaf NS_ID {
 *     type string;
 *   }
 *   leaf NSD_ID {
 *     type string;
 *   }
 *   uses metric-set;
 * }
 * </pre>The schema path to identify an instance is
 * <i>logparser-so/logparser-so-metrics</i>
 *
 * <p>To create instances of this class use {@link LogparserSoMetricsBuilder}.
 * @see LogparserSoMetricsBuilder
 *
 */
@Generated("mdsal-binding-generator")
public interface LogparserSoMetrics
    extends
    ChildOf<LogparserSoData>,
    Augmentable<LogparserSoMetrics>,
    MetricSet
{


    @Generated("mdsal-binding-generator")
    public enum Operation implements Enumeration {
        Instantiation(0, "instantiation"),
        
        Scaling(1, "scaling"),
        
        Termination(2, "termination")
        ;
    
        private static final Map<String, Operation> NAME_MAP;
        private static final Map<Integer, Operation> VALUE_MAP;
    
        static {
            final Builder<String, Operation> nb = ImmutableMap.builder();
            final Builder<Integer, Operation> vb = ImmutableMap.builder();
            for (Operation enumItem : Operation.values()) {
                vb.put(enumItem.value, enumItem);
                nb.put(enumItem.name, enumItem);
            }
    
            NAME_MAP = nb.build();
            VALUE_MAP = vb.build();
        }
    
        private final String name;
        private final int value;
    
        private Operation(int value, String name) {
            this.value = value;
            this.name = name;
        }
    
        @Override
        public String getName() {
            return name;
        }
    
        @Override
        public int getIntValue() {
            return value;
        }
    
        /**
         * Return the enumeration member whose {@link #getName()} matches specified value.
         *
         * @param name YANG assigned name
         * @return corresponding Operation item, if present
         * @throws NullPointerException if name is null
         */
        public static Optional<Operation> forName(String name) {
            return Optional.ofNullable(NAME_MAP.get(Objects.requireNonNull(name)));
        }
    
        /**
         * Return the enumeration member whose {@link #getIntValue()} matches specified value.
         *
         * @param intValue integer value
         * @return corresponding Operation item, or null if no such item exists
         */
        public static Operation forValue(int intValue) {
            return VALUE_MAP.get(intValue);
        }
    }

    public static final @NonNull QName QNAME = $YangModuleInfoImpl.qnameOf("logparser-so-metrics");

    @Override
    default Class<org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.logparser.so.metrics.rev210927.LogparserSoMetrics> implementedInterface() {
        return org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.logparser.so.metrics.rev210927.LogparserSoMetrics.class;
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
    static int bindingHashCode(final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.logparser.so.metrics.rev210927.@NonNull LogparserSoMetrics obj) {
        final int prime = 31;
        int result = 1;
        result = prime * result + Objects.hashCode(obj.getCurrentTime());
        result = prime * result + Objects.hashCode(obj.getMetrics());
        result = prime * result + Objects.hashCode(obj.getNSDID());
        result = prime * result + Objects.hashCode(obj.getNSID());
        result = prime * result + Objects.hashCode(obj.getOperation());
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
    static boolean bindingEquals(final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.logparser.so.metrics.rev210927.@NonNull LogparserSoMetrics thisObj, final Object obj) {
        if (thisObj == obj) {
            return true;
        }
        final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.logparser.so.metrics.rev210927.LogparserSoMetrics other = CodeHelpers.checkCast(org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.logparser.so.metrics.rev210927.LogparserSoMetrics.class, obj);
        if (other == null) {
            return false;
        }
        if (!Objects.equals(thisObj.getCurrentTime(), other.getCurrentTime())) {
            return false;
        }
        if (!Objects.equals(thisObj.getNSDID(), other.getNSDID())) {
            return false;
        }
        if (!Objects.equals(thisObj.getNSID(), other.getNSID())) {
            return false;
        }
        if (!Objects.equals(thisObj.getMetrics(), other.getMetrics())) {
            return false;
        }
        if (!Objects.equals(thisObj.getOperation(), other.getOperation())) {
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
    static String bindingToString(final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.logparser.so.metrics.rev210927.@NonNull LogparserSoMetrics obj) {
        final MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper("LogparserSoMetrics");
        CodeHelpers.appendValue(helper, "currentTime", obj.getCurrentTime());
        CodeHelpers.appendValue(helper, "metrics", obj.getMetrics());
        CodeHelpers.appendValue(helper, "nSDID", obj.getNSDID());
        CodeHelpers.appendValue(helper, "nSID", obj.getNSID());
        CodeHelpers.appendValue(helper, "operation", obj.getOperation());
        CodeHelpers.appendValue(helper, "augmentation", obj.augmentations().values());
        return helper.toString();
    }
    
    /**
     * Return currentTime, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Date and time in which the time-related metric values have been collected
     *         (following the ISO 8601 format).
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715.DateAndTime} currentTime, or {@code null} if it is not present.
     *
     */
    DateAndTime getCurrentTime();
    
    /**
     * Return operation, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Identifier of the type of operation (i.e., instantiation, scaling or
     *         termination).
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.logparser.so.metrics.rev210927.LogparserSoMetrics.Operation} operation, or {@code null} if it is not present.
     *
     */
    Operation getOperation();
    
    /**
     * Return nSID, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Network Service Identifier.
     *     </code>
     * </pre>
     *
     * @return {@code java.lang.String} nSID, or {@code null} if it is not present.
     *
     */
    String getNSID();
    
    /**
     * Return nSDID, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Network Service Descriptor Identifier.
     *     </code>
     * </pre>
     *
     * @return {@code java.lang.String} nSDID, or {@code null} if it is not present.
     *
     */
    String getNSDID();

}

