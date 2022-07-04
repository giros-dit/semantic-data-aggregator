package org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.prometheus.rev211118;
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
 *   revision 2021-11-18 {
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
 *   grouping metric-point-set {
 *     container metric-points {
 *       choice metric-points-choice {
 *         case metric-point-single {
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
 *         case metric-point-list {
 *           list metric-point {
 *             when not(../metric-point);
 *             key timestamp;
 *             min-elements 2;
 *             leaf value {
 *               type decimal64 {
 *                 fraction-digits 2;
 *               }
 *             }
 *             leaf timestamp {
 *               type decimal64 {
 *                 fraction-digits 3;
 *               }
 *             }
 *           }
 *         }
 *       }
 *     }
 *   }
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
 * </pre>
 *
 */
@Generated("mdsal-binding-generator")
public interface PrometheusData
    extends
    DataRoot
{




    /**
     * Return metrics, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Enclosing container for the list of Prometheus metrics.
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.prometheus.rev211118.Metrics} metrics, or {@code null} if it is not present.
     *
     */
    Metrics getMetrics();

}

