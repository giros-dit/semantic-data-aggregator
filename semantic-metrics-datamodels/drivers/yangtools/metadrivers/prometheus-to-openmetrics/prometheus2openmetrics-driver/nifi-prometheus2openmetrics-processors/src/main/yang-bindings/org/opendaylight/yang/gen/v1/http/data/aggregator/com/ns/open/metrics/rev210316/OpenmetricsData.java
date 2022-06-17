package org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316;
import javax.annotation.processing.Generated;
import org.opendaylight.yangtools.yang.binding.DataRoot;

/**
 * YANG module to represent metrics. This module has been inspired by OpenMetrics 
 * specification.
 *
 * <p>
 * This class represents the following YANG schema fragment defined in module <b>openmetrics</b>
 * <pre>
 * module openmetrics {
 *   yang-version 1.1;
 *   namespace "http://data-aggregator.com/ns/open-metrics";
 *   prefix om;
 *   revision 2021-03-16 {
 *   }
 *   typedef metric-type {
 *     type enumeration {
 *       enum unknown {
 *       }
 *       enum gauge {
 *       }
 *       enum counter {
 *       }
 *       enum state_set {
 *       }
 *       enum info {
 *       }
 *       enum histogram {
 *       }
 *       enum gauge_histogram {
 *       }
 *       enum summary {
 *       }
 *     }
 *   }
 *   grouping metric-set {
 *     container metrics {
 *       list metric {
 *         key label-set-id;
 *         leaf label-set-id {
 *           type string;
 *         }
 *         leaf name {
 *           type string;
 *         }
 *         uses label-set;
 *         uses metric-point-set;
 *       }
 *     }
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
 *             type int64;
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
 *               type int64;
 *             }
 *           }
 *         }
 *       }
 *     }
 *   }
 *   container metric-families {
 *     config false;
 *     list metric-family {
 *       key name;
 *       leaf name {
 *         type string;
 *       }
 *       leaf metric-type {
 *         type metric-type;
 *       }
 *       leaf unit {
 *         type string;
 *       }
 *       leaf help {
 *         type string;
 *       }
 *       uses metric-set;
 *     }
 *   }
 * }
 * </pre>
 *
 */
@Generated("mdsal-binding-generator")
public interface OpenmetricsData
    extends
    DataRoot
{




    /**
     * Return metricFamilies, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         A MetricSet is the top level object exposed by OpenMetrics. It MUST consist of
     *         MetricFamilies and MAY be empty. Each MetricFamily name MUST be unique. The same
     *         label name and value SHOULD NOT appear on every Metric within a MetricSet. There
     *         is no specific ordering of MetricFamilies required within a MetricSet. An
     *         exposer MAY make an exposition easier to read for humans, for example sort
     *         alphabetically if the performance tradeoff makes sense.
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.MetricFamilies} metricFamilies, or {@code null} if it is not present.
     *
     */
    MetricFamilies getMetricFamilies();

}

