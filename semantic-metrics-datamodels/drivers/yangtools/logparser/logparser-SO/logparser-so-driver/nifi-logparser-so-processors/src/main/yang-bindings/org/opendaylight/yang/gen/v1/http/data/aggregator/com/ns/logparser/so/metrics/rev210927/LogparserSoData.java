package org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.logparser.so.metrics.rev210927;
import javax.annotation.processing.Generated;
import org.opendaylight.yangtools.yang.binding.DataRoot;

/**
 * YANG module to represent the time-related metrics provided by the 5Growth 
 * Service Orchestrator (5Gr-SO) Log Management Tool during the network service 
 * instantiation, scaling and termination operations.
 *
 * <p>
 * This class represents the following YANG schema fragment defined in module <b>logparser-so</b>
 * <pre>
 * module logparser-so {
 *   yang-version 1.1;
 *   namespace "http://data-aggregator.com/ns/logparser/so/metrics";
 *   prefix logparser-so-metrics;
 *   import ietf-yang-types {
 *     prefix ietf-types;
 *   }
 *   revision 2021-09-27 {
 *   }
 *   identity metric-identity {
 *   }
 *   identity Total_instantiation_time {
 *     base metric-identity;
 *   }
 *   identity Operation_ID_for_instantiation_op {
 *     base metric-identity;
 *   }
 *   identity ROE_created_VLs {
 *     base metric-identity;
 *   }
 *   identity ROE_retrieve_RL_resources {
 *     base metric-identity;
 *   }
 *   identity ROE_parsing_NSDs {
 *     base metric-identity;
 *   }
 *   identity ROE_extract_VLs {
 *     base metric-identity;
 *   }
 *   identity Retrieving_descriptor_from_catalogue_DBs {
 *     base metric-identity;
 *   }
 *   identity PA_calculation {
 *     base metric-identity;
 *   }
 *   identity Create_threshold_based_alerts {
 *     base metric-identity;
 *   }
 *   identity Create_monitoring_jobs {
 *     base metric-identity;
 *   }
 *   identity Create_AIML_alerts {
 *     base metric-identity;
 *   }
 *   identity Total_termination_time {
 *     base metric-identity;
 *   }
 *   identity Operation_ID_for_Termination_op {
 *     base metric-identity;
 *   }
 *   identity ROE_deleting_LLs {
 *     base metric-identity;
 *   }
 *   identity Terminating_Threshold_based_alerts {
 *     base metric-identity;
 *   }
 *   identity Terminating_Monitoring_jobs {
 *     base metric-identity;
 *   }
 *   identity Terminating_AIML_alert_jobs {
 *     base metric-identity;
 *   }
 *   identity SOE_time {
 *     base metric-identity;
 *   }
 *   identity ROE_time {
 *     base metric-identity;
 *   }
 *   identity Hierarchical_SOE_dispatching_SOEpSOEc {
 *     base metric-identity;
 *   }
 *   identity ROE_updating_DBs {
 *     base metric-identity;
 *   }
 *   identity CoreMANO_Wrapper_time {
 *     base metric-identity;
 *   }
 *   grouping metric-set {
 *     container metrics {
 *       list metric {
 *         key name;
 *         leaf name {
 *           type identityref {
 *             base metric-identity;
 *           }
 *         }
 *         leaf value {
 *           type int64;
 *         }
 *       }
 *     }
 *   }
 *   container logparser-so-metrics {
 *     config false;
 *     leaf Current_time {
 *       type ietf-types:date-and-time;
 *     }
 *     leaf Operation {
 *       type enumeration {
 *         enum Instantiation;
 *         enum scaling;
 *         enum termination;
 *       }
 *     }
 *     leaf NS_ID {
 *       type string;
 *     }
 *     leaf NSD_ID {
 *       type string;
 *     }
 *     uses metric-set;
 *   }
 * }
 * </pre>
 *
 */
@Generated("mdsal-binding-generator")
public interface LogparserSoData
    extends
    DataRoot
{




    /**
     * Return logparserSoMetrics, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Enclosing container for the time-related metrics provided by the 5Growth Service
     *         Orchestrator related logs during the network service instantiation, termination
     *         and scaling operations.
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.logparser.so.metrics.rev210927.LogparserSoMetrics} logparserSoMetrics, or {@code null} if it is not present.
     *
     */
    LogparserSoMetrics getLogparserSoMetrics();

}

