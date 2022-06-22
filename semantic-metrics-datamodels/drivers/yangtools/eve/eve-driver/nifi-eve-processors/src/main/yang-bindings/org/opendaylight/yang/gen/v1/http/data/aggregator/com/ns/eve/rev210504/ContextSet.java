package org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.eve.rev210504;
import java.lang.Class;
import java.lang.Override;
import javax.annotation.processing.Generated;
import org.eclipse.jdt.annotation.NonNull;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.eve.rev210504.context.set.Labels;
import org.opendaylight.yangtools.yang.binding.DataObject;
import org.opendaylight.yangtools.yang.common.QName;

/**
 * Optional. Context set associated with a record.
 * 
 * <p>
 * This class represents the following YANG schema fragment defined in module <b>eve</b>
 * <pre>
 * grouping context-set {
 *   container labels {
 *     list label {
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
 * <i>eve/context-set</i>
 *
 */
@Generated("mdsal-binding-generator")
public interface ContextSet
    extends
    DataObject
{



    public static final @NonNull QName QNAME = $YangModuleInfoImpl.qnameOf("context-set");

    @Override
    Class<? extends ContextSet> implementedInterface();
    
    /**
     * Return labels, or {@code null} if it is not present.
     * 
     * <pre>
     *     <code>
     *         Enclosing container for the list of Labels associated with a record.
     *     </code>
     * </pre>
     * 
     * @return {@code org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.eve.rev210504.context.set.Labels} labels, or {@code null} if it is not present.
     *
     */
    Labels getLabels();

}

