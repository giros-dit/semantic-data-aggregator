package org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.gnmic.rev210719;
import com.google.common.base.MoreObjects;
import java.lang.Class;
import java.lang.Long;
import java.lang.NullPointerException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.util.Objects;
import javax.annotation.processing.Generated;
import org.eclipse.jdt.annotation.NonNull;
import org.opendaylight.yangtools.yang.binding.Augmentable;
import org.opendaylight.yangtools.yang.binding.ChildOf;
import org.opendaylight.yangtools.yang.binding.CodeHelpers;
import org.opendaylight.yangtools.yang.common.QName;

/**
 * Enclosing container for a sample of telemetry data in event format collected by 
 * the gNMIc client through Subscribe RPC.
 * 
 * <p>
 * This class represents the following YANG schema fragment defined in module <b>gnmic</b>
 * <pre>
 * container gnmic-event {
 *   leaf name {
 *     type string;
 *   }
 *   leaf timestamp {
 *     type int64;
 *   }
 *   uses value-set;
 *   uses tag-set;
 * }
 * </pre>The schema path to identify an instance is
 * <i>gnmic/gnmic-event</i>
 * 
 * <p>To create instances of this class use {@link GnmicEventBuilder}.
 * @see GnmicEventBuilder
 *
 */
@Generated("mdsal-binding-generator")
public interface GnmicEvent
    extends
    ChildOf<GnmicData>,
    Augmentable<GnmicEvent>,
    ValueSet,
    TagSet
{



    public static final @NonNull QName QNAME = $YangModuleInfoImpl.qnameOf("gnmic-event");

    @Override
    default Class<org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.gnmic.rev210719.GnmicEvent> implementedInterface() {
        return org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.gnmic.rev210719.GnmicEvent.class;
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
    static int bindingHashCode(final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.gnmic.rev210719.@NonNull GnmicEvent obj) {
        final int prime = 31;
        int result = 1;
        result = prime * result + Objects.hashCode(obj.getName());
        result = prime * result + Objects.hashCode(obj.getTags());
        result = prime * result + Objects.hashCode(obj.getTimestamp());
        result = prime * result + Objects.hashCode(obj.getValues());
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
    static boolean bindingEquals(final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.gnmic.rev210719.@NonNull GnmicEvent thisObj, final Object obj) {
        if (thisObj == obj) {
            return true;
        }
        final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.gnmic.rev210719.GnmicEvent other = CodeHelpers.checkCast(org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.gnmic.rev210719.GnmicEvent.class, obj);
        if (other == null) {
            return false;
        }
        if (!Objects.equals(thisObj.getTimestamp(), other.getTimestamp())) {
            return false;
        }
        if (!Objects.equals(thisObj.getName(), other.getName())) {
            return false;
        }
        if (!Objects.equals(thisObj.getTags(), other.getTags())) {
            return false;
        }
        if (!Objects.equals(thisObj.getValues(), other.getValues())) {
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
    static String bindingToString(final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.gnmic.rev210719.@NonNull GnmicEvent obj) {
        final MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper("GnmicEvent");
        CodeHelpers.appendValue(helper, "name", obj.getName());
        CodeHelpers.appendValue(helper, "tags", obj.getTags());
        CodeHelpers.appendValue(helper, "timestamp", obj.getTimestamp());
        CodeHelpers.appendValue(helper, "values", obj.getValues());
        CodeHelpers.appendValue(helper, "augmentation", obj.augmentations().values());
        return helper.toString();
    }
    
    /**
     * Return name, or {@code null} if it is not present.
     * 
     * <pre>
     *     <code>
     *         Name of the gNMI subscription.
     *     </code>
     * </pre>
     * 
     * @return {@code java.lang.String} name, or {@code null} if it is not present.
     *
     */
    String getName();
    
    /**
     * Return timestamp, or {@code null} if it is not present.
     * 
     * <pre>
     *     <code>
     *         The measurement timestamp of the event's sample.
     *     </code>
     * </pre>
     * 
     * @return {@code java.lang.Long} timestamp, or {@code null} if it is not present.
     *
     */
    Long getTimestamp();

}

