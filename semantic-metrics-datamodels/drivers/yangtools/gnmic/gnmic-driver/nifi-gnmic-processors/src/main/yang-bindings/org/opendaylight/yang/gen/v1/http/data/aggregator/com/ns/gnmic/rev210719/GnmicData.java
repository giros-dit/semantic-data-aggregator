package org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.gnmic.rev210719;
import javax.annotation.processing.Generated;
import org.opendaylight.yangtools.yang.binding.DataRoot;

/**
 * YANG module to represent the event output format for telemetry data collected 
 * from a gNMI client (i.e., gNMIc) through Subscribe RPC.
 * 
 * <p>
 * This class represents the following YANG schema fragment defined in module <b>gnmic</b>
 * <pre>
 * module gnmic {
 *   yang-version 1.1;
 *   namespace "http://data-aggregator.com/ns/gnmic";
 *   prefix gnmic;
 *   revision 2021-07-19 {
 *   }
 *   revision 2021-06-20 {
 *   }
 *   grouping value-set {
 *     container values {
 *       list value {
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
 *   grouping tag-set {
 *     container tags {
 *       list tag {
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
 *   container gnmic-event {
 *     leaf name {
 *       type string;
 *     }
 *     leaf timestamp {
 *       type int64;
 *     }
 *     uses value-set;
 *     uses tag-set;
 *   }
 * }
 * </pre>
 *
 */
@Generated("mdsal-binding-generator")
public interface GnmicData
    extends
    DataRoot
{




    /**
     * Return gnmicEvent, or {@code null} if it is not present.
     * 
     * <pre>
     *     <code>
     *         Enclosing container for a sample of telemetry data in event format collected by
     *         the gNMIc client through Subscribe RPC.
     *     </code>
     * </pre>
     * 
     * @return {@code org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.gnmic.rev210719.GnmicEvent} gnmicEvent, or {@code null} if it is not present.
     *
     */
    GnmicEvent getGnmicEvent();

}

