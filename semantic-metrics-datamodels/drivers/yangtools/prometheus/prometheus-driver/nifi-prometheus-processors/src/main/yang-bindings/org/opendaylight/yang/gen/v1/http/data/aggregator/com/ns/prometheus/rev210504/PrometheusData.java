package org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.prometheus.rev210504;
import javax.annotation.processing.Generated;
import org.opendaylight.yangtools.yang.binding.DataRoot;

/**
 * YANG module to represent metrics collected from Prometheus HTTP API.
 * 
 * <p>
 * This class represents the following YANG schema fragment defined in module <b>prometheus</b>
 * <pre>
 * module prometheus {
 *   yang-version 1.1;
 *   namespace "http://data-aggregator.com/ns/prometheus";
 *   prefix prom;
 *   revision 2021-05-04 {
 *   }
 *   grouping label-set {
 *     container labels {
 *       list label {
 *         key name;
 *         leaf name {
 *           type string;
 *         }
 *         leaf value {
 *           type string;
 *         }
 *       }
 *     }
 *   }
 *   container metric {
 *     leaf timestamp {
 *       type decimal64 {
 *         fraction-digits 3;
 *       }
 *     }
 *     leaf value {
 *       type decimal64 {
 *         fraction-digits 3;
 *       }
 *     }
 *     leaf name {
 *       type string;
 *     }
 *     uses label-set;
 *   }
 * }
 * </pre>
 *
 */
@Generated("mdsal-binding-generator")
public interface PrometheusData
    extends
    DataRoot
{




    /**
     * Return metric, or {@code null} if it is not present.
     * 
     * <pre>
     *     <code>
     *         Enclosing container for a Prometheus metric.
     *     </code>
     * </pre>
     * 
     * @return {@code org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.prometheus.rev210504.Metric} metric, or {@code null} if it is not present.
     *
     */
    Metric getMetric();

}

