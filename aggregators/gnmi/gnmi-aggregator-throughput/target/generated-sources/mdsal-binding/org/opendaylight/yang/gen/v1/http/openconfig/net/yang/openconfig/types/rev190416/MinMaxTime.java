package org.opendaylight.yang.gen.v1.http.openconfig.net.yang.openconfig.types.rev190416;
import java.lang.Class;
import java.lang.Override;
import javax.annotation.processing.Generated;
import org.eclipse.jdt.annotation.NonNull;
import org.opendaylight.yangtools.yang.binding.DataObject;
import org.opendaylight.yangtools.yang.common.QName;

/**
 * Common grouping for recording the absolute time at which the minimum and 
 * values occurred in the statistics
 *
 * <p>
 * This class represents the following YANG schema fragment defined in module <b>openconfig-types</b>
 * <pre>
 * grouping min-max-time {
 *   leaf min-time {
 *     type oc-types:timeticks64;
 *   }
 *   leaf max-time {
 *     type oc-types:timeticks64;
 *   }
 * }
 * </pre>The schema path to identify an instance is
 * <i>openconfig-types/min-max-time</i>
 *
 */
@Generated("mdsal-binding-generator")
public interface MinMaxTime
    extends
    DataObject
{



    public static final @NonNull QName QNAME = $YangModuleInfoImpl.qnameOf("min-max-time");

    @Override
    Class<? extends MinMaxTime> implementedInterface();
    
    /**
     * Return minTime, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         The absolute time at which the minimum value occurred. The value is the
     *         timestamp in nanoseconds relative to the Unix Epoch (Jan 1, 1970 00:00:00 UTC).
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.http.openconfig.net.yang.openconfig.types.rev190416.Timeticks64} minTime, or {@code null} if it is not present.
     *
     */
    Timeticks64 getMinTime();
    
    /**
     * Return maxTime, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         The absolute time at which the maximum value occurred. The value is the
     *         timestamp in nanoseconds relative to the Unix Epoch (Jan 1, 1970 00:00:00 UTC).
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.http.openconfig.net.yang.openconfig.types.rev190416.Timeticks64} maxTime, or {@code null} if it is not present.
     *
     */
    Timeticks64 getMaxTime();

}

