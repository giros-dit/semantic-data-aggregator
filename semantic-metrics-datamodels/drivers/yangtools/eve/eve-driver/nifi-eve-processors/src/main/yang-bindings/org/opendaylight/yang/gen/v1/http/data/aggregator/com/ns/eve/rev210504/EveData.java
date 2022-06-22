package org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.eve.rev210504;
import javax.annotation.processing.Generated;
import org.opendaylight.yangtools.yang.binding.DataRoot;

/**
 * YANG module to represent 5G-EVE records of metrics/KPIs.
 * 
 * <p>
 * This class represents the following YANG schema fragment defined in module <b>eve</b>
 * <pre>
 * module eve {
 *   yang-version 1.1;
 *   namespace "http://data-aggregator.com/ns/eve";
 *   prefix eve;
 *   revision 2021-05-04 {
 *   }
 *   revision 2021-04-30 {
 *   }
 *   grouping context-set {
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
 *   container eve-record {
 *     config false;
 *     leaf timestamp {
 *       type decimal64 {
 *         fraction-digits 6;
 *       }
 *     }
 *     leaf value {
 *       type decimal64 {
 *         fraction-digits 6;
 *       }
 *     }
 *     leaf unit {
 *       type string;
 *     }
 *     leaf device-id {
 *       type string;
 *     }
 *     uses context-set;
 *   }
 * }
 * </pre>
 *
 */
@Generated("mdsal-binding-generator")
public interface EveData
    extends
    DataRoot
{




    /**
     * Return eveRecord, or {@code null} if it is not present.
     * 
     * <pre>
     *     <code>
     *         Enclosing container for the record of a given metric/KPI in 5G-EVE.
     *     </code>
     * </pre>
     * 
     * @return {@code org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.eve.rev210504.EveRecord} eveRecord, or {@code null} if it is not present.
     *
     */
    EveRecord getEveRecord();

}

