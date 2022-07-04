package org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.logparser.vs.metrics.rev210927;
import javax.annotation.processing.Generated;
import org.opendaylight.yangtools.yang.binding.DataRoot;

/**
 * YANG module to represent the time-related metrics provided by the 5Growth 
 * Vertical Slircer (5Gr-VS) Log Management Tool during the network service 
 * instantiation and termination operations.
 *
 * <p>
 * This class represents the following YANG schema fragment defined in module <b>logparser-vs</b>
 * <pre>
 * module logparser-vs {
 *   yang-version 1.1;
 *   namespace "http://data-aggregator.com/ns/logparser/vs/metrics";
 *   prefix logparser-vs-metrics;
 *   import ietf-yang-types {
 *     prefix ietf-types;
 *   }
 *   revision 2021-09-27 {
 *   }
 *   identity metric-identity {
 *   }
 *   identity Vertical_Service_Instantiation_Time {
 *     base metric-identity;
 *   }
 *   identity EVE_VSS_Instantiation_Time {
 *     base metric-identity;
 *   }
 *   identity EVE_VSS_Ready_Time {
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
 *   container logparser-vs-metrics {
 *     config false;
 *     leaf Current_time {
 *       type ietf-types:date-and-time;
 *     }
 *     leaf Operation {
 *       type enumeration {
 *         enum Instantiation_CSMF_Level_Integration;
 *       }
 *     }
 *     leaf VSI_ID {
 *       type string;
 *     }
 *     leaf VSSI_ID {
 *       type string;
 *     }
 *     leaf VSB_ID {
 *       type string;
 *     }
 *     leaf Instance_Name {
 *       type string;
 *     }
 *     uses metric-set;
 *   }
 * }
 * </pre>
 *
 */
@Generated("mdsal-binding-generator")
public interface LogparserVsData
    extends
    DataRoot
{




    /**
     * Return logparserVsMetrics, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Enclosing container for the time-related metrics provided by the 5Growth
     *         Vertical Slicer related logs during the network service instantiation and
     *         termination operations.
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.logparser.vs.metrics.rev210927.LogparserVsMetrics} logparserVsMetrics, or {@code null} if it is not present.
     *
     */
    LogparserVsMetrics getLogparserVsMetrics();

}

