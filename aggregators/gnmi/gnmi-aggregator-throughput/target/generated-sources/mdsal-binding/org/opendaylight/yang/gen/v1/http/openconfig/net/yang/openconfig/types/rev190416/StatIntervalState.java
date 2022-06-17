package org.opendaylight.yang.gen.v1.http.openconfig.net.yang.openconfig.types.rev190416;
import java.lang.Class;
import java.lang.Override;
import javax.annotation.processing.Generated;
import org.eclipse.jdt.annotation.NonNull;
import org.opendaylight.yangtools.yang.binding.DataObject;
import org.opendaylight.yangtools.yang.common.QName;

/**
 * Reusable leaf definition for stats computation interval
 *
 * <p>
 * This class represents the following YANG schema fragment defined in module <b>openconfig-types</b>
 * <pre>
 * grouping stat-interval-state {
 *   leaf interval {
 *     type oc-types:stat-interval;
 *   }
 * }
 * </pre>The schema path to identify an instance is
 * <i>openconfig-types/stat-interval-state</i>
 *
 */
@Generated("mdsal-binding-generator")
public interface StatIntervalState
    extends
    DataObject
{



    public static final @NonNull QName QNAME = $YangModuleInfoImpl.qnameOf("stat-interval-state");

    @Override
    Class<? extends StatIntervalState> implementedInterface();
    
    /**
     * Return interval, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         If supported by the system, this reports the time interval over which the
     *         min/max/average statistics are computed by the system.
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.http.openconfig.net.yang.openconfig.types.rev190416.StatInterval} interval, or {@code null} if it is not present.
     *
     */
    StatInterval getInterval();

}

