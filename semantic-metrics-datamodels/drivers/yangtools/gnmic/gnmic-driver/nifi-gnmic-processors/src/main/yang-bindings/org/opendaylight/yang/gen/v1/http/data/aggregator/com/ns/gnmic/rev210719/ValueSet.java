package org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.gnmic.rev210719;
import java.lang.Class;
import java.lang.Override;
import javax.annotation.processing.Generated;
import org.eclipse.jdt.annotation.NonNull;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.gnmic.rev210719.value.set.Values;
import org.opendaylight.yangtools.yang.binding.DataObject;
import org.opendaylight.yangtools.yang.common.QName;

/**
 * The values of the event's sample.
 * 
 * <p>
 * This class represents the following YANG schema fragment defined in module <b>gnmic</b>
 * <pre>
 * grouping value-set {
 *   container values {
 *     list value {
 *       key name;
 *       leaf name {
 *         type string;
 *       }
 *       leaf value {
 *         type string;
 *       }
 *     }
 *   }
 * }
 * </pre>The schema path to identify an instance is
 * <i>gnmic/value-set</i>
 *
 */
@Generated("mdsal-binding-generator")
public interface ValueSet
    extends
    DataObject
{



    public static final @NonNull QName QNAME = $YangModuleInfoImpl.qnameOf("value-set");

    @Override
    Class<? extends ValueSet> implementedInterface();
    
    /**
     * Return values, or {@code null} if it is not present.
     * 
     * <pre>
     *     <code>
     *         Enclosing container for the list of values associated with an event.
     *     </code>
     * </pre>
     * 
     * @return {@code org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.gnmic.rev210719.value.set.Values} values, or {@code null} if it is not present.
     *
     */
    Values getValues();

}

