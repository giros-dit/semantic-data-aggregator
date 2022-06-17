package org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.logparser.so.metrics.rev210927;
import java.lang.Class;
import java.lang.Override;
import javax.annotation.processing.Generated;
import org.eclipse.jdt.annotation.NonNull;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.logparser.so.metrics.rev210927.metric.set.Metrics;
import org.opendaylight.yangtools.yang.binding.DataObject;
import org.opendaylight.yangtools.yang.common.QName;

/**
 * Metric set associated with a LogParser Service Orchestrator (SO) operation log.
 *
 * <p>
 * This class represents the following YANG schema fragment defined in module <b>logparser-so</b>
 * <pre>
 * grouping metric-set {
 *   container metrics {
 *     list metric {
 *       key name;
 *       leaf name {
 *         type identityref {
 *           base metric-identity;
 *         }
 *       }
 *       leaf value {
 *         type int64;
 *       }
 *     }
 *   }
 * }
 * </pre>The schema path to identify an instance is
 * <i>logparser-so/metric-set</i>
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
     *         Enclosing container for the list of time-related metrics associated with a
     *         LogParser SO operation log.
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.logparser.so.metrics.rev210927.metric.set.Metrics} metrics, or {@code null} if it is not present.
     *
     */
    Metrics getMetrics();

}

