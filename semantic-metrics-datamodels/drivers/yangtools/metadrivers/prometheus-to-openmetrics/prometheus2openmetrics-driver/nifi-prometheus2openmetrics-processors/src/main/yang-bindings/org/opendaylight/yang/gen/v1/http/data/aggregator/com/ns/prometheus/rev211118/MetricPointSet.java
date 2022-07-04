package org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.prometheus.rev211118;
import java.lang.Class;
import java.lang.Override;
import javax.annotation.processing.Generated;
import org.eclipse.jdt.annotation.NonNull;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.prometheus.rev211118.metric.point.set.MetricPoints;
import org.opendaylight.yangtools.yang.binding.DataObject;
import org.opendaylight.yangtools.yang.common.QName;

/**
 * MetricPoints associated with a Metric
 *
 * <p>
 * This class represents the following YANG schema fragment defined in module <b>prometheus</b>
 * <pre>
 * grouping metric-point-set {
 *   container metric-points {
 *     choice metric-points-choice {
 *       case metric-point-single {
 *         leaf value {
 *           type decimal64 {
 *             fraction-digits 2;
 *           }
 *         }
 *         leaf timestamp {
 *           type decimal64 {
 *             fraction-digits 3;
 *           }
 *         }
 *       }
 *       case metric-point-list {
 *         list metric-point {
 *           when not(../metric-point);
 *           key timestamp;
 *           min-elements 2;
 *           leaf value {
 *             type decimal64 {
 *               fraction-digits 2;
 *             }
 *           }
 *           leaf timestamp {
 *             type decimal64 {
 *               fraction-digits 3;
 *             }
 *           }
 *         }
 *       }
 *     }
 *   }
 * }
 * </pre>The schema path to identify an instance is
 * <i>prometheus/metric-point-set</i>
 *
 */
@Generated("mdsal-binding-generator")
public interface MetricPointSet
    extends
    DataObject
{



    public static final @NonNull QName QNAME = $YangModuleInfoImpl.qnameOf("metric-point-set");

    @Override
    Class<? extends MetricPointSet> implementedInterface();
    
    /**
     * Return metricPoints, or {@code null} if it is not present.
     *
     * @return {@code org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.prometheus.rev211118.metric.point.set.MetricPoints} metricPoints, or {@code null} if it is not present.
     *
     */
    MetricPoints getMetricPoints();

}

