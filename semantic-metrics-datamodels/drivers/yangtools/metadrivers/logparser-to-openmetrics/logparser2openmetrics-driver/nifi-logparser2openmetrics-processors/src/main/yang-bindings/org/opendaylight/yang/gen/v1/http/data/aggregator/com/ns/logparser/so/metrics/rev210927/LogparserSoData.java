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
 *   identity Total_Instantiation_Time {
 *     base metric-identity;
 *   }
 *   identity Operation_ID_For_Instantiation_Op {
 *     base metric-identity;
 *   }
 *   identity ROE_Created_VLs {
 *     base metric-identity;
 *   }
 *   identity ROE_Retrieve_RL_Resources {
 *     base metric-identity;
 *   }
 *   identity ROE_Parsing_NSDs {
 *     base metric-identity;
 *   }
 *   identity ROE_Extract_VLs {
 *     base metric-identity;
 *   }
 *   identity Retrieving_Descriptor_From_Catalogue_DBs {
 *     base metric-identity;
 *   }
 *   identity PA_Calculation {
 *     base metric-identity;
 *   }
 *   identity Create_Threshold_Based_Alerts {
 *     base metric-identity;
 *   }
 *   identity Create_Monitoring_Jobs {
 *     base metric-identity;
 *   }
 *   identity Create_AIML_Alerts {
 *     base metric-identity;
 *   }
 *   identity Total_Termination_Time {
 *     base metric-identity;
 *   }
 *   identity Operation_ID_For_Termination_Op {
 *     base metric-identity;
 *   }
 *   identity ROE_Deleting_LLs {
 *     base metric-identity;
 *   }
 *   identity Terminating_Threshold_Based_Alerts {
 *     base metric-identity;
 *   }
 *   identity Terminating_Monitoring_Jobs {
 *     base metric-identity;
 *   }
 *   identity Terminating_AIML_Alert_Jobs {
 *     base metric-identity;
 *   }
 *   identity SOE_Time {
 *     base metric-identity;
 *   }
 *   identity ROE_Time {
 *     base metric-identity;
 *   }
 *   identity Hierarchical_SOE_Dispatching_SOEpSOEc {
 *     base metric-identity;
 *   }
 *   identity ROE_Updating_DBs {
 *     base metric-identity;
 *   }
 *   identity CoreMANO_Wrapper_Time {
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
 *     leaf Current_Time {
 *       type ietf-types:date-and-time;
 *     }
 *     leaf Operation {
 *       type enumeration {
 *         enum instantiation;
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

