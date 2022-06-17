package org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.metric.point.set.metric.points;
import javax.annotation.processing.Generated;
import org.eclipse.jdt.annotation.NonNull;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.$YangModuleInfoImpl;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.metric.point.set.MetricPoints;
import org.opendaylight.yangtools.yang.binding.ChoiceIn;
import org.opendaylight.yangtools.yang.common.QName;

/**
 * Enclosing container for the list of MetricPoints associated with a Metric
 *
 * <p>
 * This class represents the following YANG schema fragment defined in module <b>openmetrics</b>
 * <pre>
 * choice metric-points-choice {
 *   case metric-point-single {
 *     leaf value {
 *       type decimal64 {
 *         fraction-digits 2;
 *       }
 *     }
 *     leaf timestamp {
 *       type int64;
 *     }
 *   }
 *   case metric-point-list {
 *     list metric-point {
 *       when not(../metric-point);
 *       key timestamp;
 *       min-elements 2;
 *       leaf value {
 *         type decimal64 {
 *           fraction-digits 2;
 *         }
 *       }
 *       leaf timestamp {
 *         type int64;
 *       }
 *     }
 *   }
 * }
 * </pre>The schema path to identify an instance is
 * <i>openmetrics/metric-point-set/metric-points/metric-points-choice</i>
 *
 */
@Generated("mdsal-binding-generator")
public interface MetricPointsChoice
    extends
    ChoiceIn<MetricPoints>
{



    public static final @NonNull QName QNAME = $YangModuleInfoImpl.qnameOf("metric-points-choice");


}

