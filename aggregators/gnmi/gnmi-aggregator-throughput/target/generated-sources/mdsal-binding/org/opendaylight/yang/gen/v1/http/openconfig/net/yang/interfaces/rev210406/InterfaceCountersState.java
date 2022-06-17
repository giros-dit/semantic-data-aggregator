package org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406;
import java.lang.Class;
import java.lang.Override;
import javax.annotation.processing.Generated;
import org.eclipse.jdt.annotation.NonNull;
import org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406._interface.counters.state.Counters;
import org.opendaylight.yangtools.yang.binding.DataObject;
import org.opendaylight.yangtools.yang.common.QName;

/**
 * Operational state representing interface counters and statistics.
 *
 * <p>
 * This class represents the following YANG schema fragment defined in module <b>openconfig-interfaces</b>
 * <pre>
 * grouping interface-counters-state {
 *   oc-ext:operational;
 *   container counters {
 *     leaf in-octets {
 *       type oc-yang:counter64;
 *     }
 *     leaf in-pkts {
 *       type oc-yang:counter64;
 *     }
 *     leaf in-unicast-pkts {
 *       type oc-yang:counter64;
 *     }
 *     leaf in-broadcast-pkts {
 *       type oc-yang:counter64;
 *     }
 *     leaf in-multicast-pkts {
 *       type oc-yang:counter64;
 *     }
 *     leaf in-discards {
 *       type oc-yang:counter64;
 *     }
 *     leaf in-errors {
 *       type oc-yang:counter64;
 *     }
 *     leaf in-unknown-protos {
 *       type oc-yang:counter64;
 *     }
 *     leaf in-fcs-errors {
 *       type oc-yang:counter64;
 *     }
 *     leaf out-octets {
 *       type oc-yang:counter64;
 *     }
 *     leaf out-pkts {
 *       type oc-yang:counter64;
 *     }
 *     leaf out-unicast-pkts {
 *       type oc-yang:counter64;
 *     }
 *     leaf out-broadcast-pkts {
 *       type oc-yang:counter64;
 *     }
 *     leaf out-multicast-pkts {
 *       type oc-yang:counter64;
 *     }
 *     leaf out-discards {
 *       type oc-yang:counter64;
 *     }
 *     leaf out-errors {
 *       type oc-yang:counter64;
 *     }
 *     leaf carrier-transitions {
 *       type oc-yang:counter64;
 *       oc-ext:telemetry-on-change;
 *     }
 *     leaf last-clear {
 *       type oc-types:timeticks64;
 *       oc-ext:telemetry-on-change;
 *     }
 *   }
 * }
 * </pre>The schema path to identify an instance is
 * <i>openconfig-interfaces/interface-counters-state</i>
 *
 */
@Generated("mdsal-binding-generator")
public interface InterfaceCountersState
    extends
    DataObject
{



    public static final @NonNull QName QNAME = $YangModuleInfoImpl.qnameOf("interface-counters-state");

    @Override
    Class<? extends InterfaceCountersState> implementedInterface();
    
    /**
     * Return counters, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         A collection of interface-related statistics objects.
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406._interface.counters.state.Counters} counters, or {@code null} if it is not present.
     *
     */
    Counters getCounters();

}

