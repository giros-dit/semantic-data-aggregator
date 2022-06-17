package org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.prometheus.rev211118;
import java.lang.Class;
import java.lang.Override;
import javax.annotation.processing.Generated;
import org.eclipse.jdt.annotation.NonNull;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.prometheus.rev211118.label.set.Labels;
import org.opendaylight.yangtools.yang.binding.DataObject;
import org.opendaylight.yangtools.yang.common.QName;

/**
 * LabelSet associated with a Metric
 *
 * <p>
 * This class represents the following YANG schema fragment defined in module <b>prometheus</b>
 * <pre>
 * grouping label-set {
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
 * <i>prometheus/label-set</i>
 *
 */
@Generated("mdsal-binding-generator")
public interface LabelSet
    extends
    DataObject
{



    public static final @NonNull QName QNAME = $YangModuleInfoImpl.qnameOf("label-set");

    @Override
    Class<? extends LabelSet> implementedInterface();
    
    /**
     * Return labels, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Enclosing container for the list of Labels associated with a Metric
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.prometheus.rev211118.label.set.Labels} labels, or {@code null} if it is not present.
     *
     */
    Labels getLabels();

}

