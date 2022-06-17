package org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316;
import java.lang.Class;
import java.lang.Override;
import javax.annotation.processing.Generated;
import org.eclipse.jdt.annotation.NonNull;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.metric.set.Metrics;
import org.opendaylight.yangtools.yang.binding.DataObject;
import org.opendaylight.yangtools.yang.common.QName;

/**
 * Metrics associated with a MetricFamily
 *
 * <p>
 * This class represents the following YANG schema fragment defined in module <b>openmetrics</b>
 * <pre>
 * grouping metric-set {
 *   container metrics {
 *     list metric {
 *       key label-set-id;
 *       leaf label-set-id {
 *         type string;
 *       }
 *       leaf name {
 *         type string;
 *       }
 *       uses label-set;
 *       uses metric-point-set;
 *     }
 *   }
 * }
 * </pre>The schema path to identify an instance is
 * <i>openmetrics/metric-set</i>
 *
 */
@Generated("mdsal-binding-generator")
public interface MetricSet
    extends
    DataObject
{



    public static final @NonNull QName QNAME = $YangModuleInfoImpl.qnameOf("metric-set");

    @Override
    Class<? extends MetricSet> implementedInterface();
    
    /**
     * Return metrics, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Enclosing container for the list of Metrics associated with a MetricFamily
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.metric.set.Metrics} metrics, or {@code null} if it is not present.
     *
     */
    Metrics getMetrics();

}

