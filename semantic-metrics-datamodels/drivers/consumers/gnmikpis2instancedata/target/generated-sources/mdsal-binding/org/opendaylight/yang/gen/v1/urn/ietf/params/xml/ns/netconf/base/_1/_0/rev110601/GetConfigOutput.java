package org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.netconf.base._1._0.rev110601;
import com.google.common.base.MoreObjects;
import java.lang.Class;
import java.lang.NullPointerException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.util.Objects;
import javax.annotation.processing.Generated;
import org.eclipse.jdt.annotation.NonNull;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.netconf.base._1._0.rev110601.get.config.output.Data;
import org.opendaylight.yangtools.yang.binding.Augmentable;
import org.opendaylight.yangtools.yang.binding.CodeHelpers;
import org.opendaylight.yangtools.yang.binding.RpcOutput;
import org.opendaylight.yangtools.yang.common.QName;

/**
 *
 * <p>
 * This class represents the following YANG schema fragment defined in module <b>ietf-netconf</b>
 * <pre>
 * output output {
 *   anyxml data {
 *   }
 * }
 * </pre>The schema path to identify an instance is
 * <i>ietf-netconf/get-config/output</i>
 *
 */
@Generated("mdsal-binding-generator")
public interface GetConfigOutput
    extends
    RpcOutput,
    Augmentable<GetConfigOutput>
{



    public static final @NonNull QName QNAME = $YangModuleInfoImpl.qnameOf("output");

    @Override
    default Class<org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.netconf.base._1._0.rev110601.GetConfigOutput> implementedInterface() {
        return org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.netconf.base._1._0.rev110601.GetConfigOutput.class;
    }
    
    /**
     * Default implementation of {@link Object#hashCode()} contract for this interface.
     * Implementations of this interface are encouraged to defer to this method to get consistent hashing
     * results across all implementations.
     *
     * @param obj Object for which to generate hashCode() result.
     * @return Hash code value of data modeled by this interface.
     * @throws NullPointerException if {@code obj} is null
     */
    static int bindingHashCode(final org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.netconf.base._1._0.rev110601.@NonNull GetConfigOutput obj) {
        final int prime = 31;
        int result = 1;
        result = prime * result + Objects.hashCode(obj.getData());
        result = prime * result + obj.augmentations().hashCode();
        return result;
    }
    
    /**
     * Default implementation of {@link Object#equals(Object)} contract for this interface.
     * Implementations of this interface are encouraged to defer to this method to get consistent equality
     * results across all implementations.
     *
     * @param thisObj Object acting as the receiver of equals invocation
     * @param obj Object acting as argument to equals invocation
     * @return True if thisObj and obj are considered equal
     * @throws NullPointerException if {@code thisObj} is null
     */
    static boolean bindingEquals(final org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.netconf.base._1._0.rev110601.@NonNull GetConfigOutput thisObj, final Object obj) {
        if (thisObj == obj) {
            return true;
        }
        final org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.netconf.base._1._0.rev110601.GetConfigOutput other = CodeHelpers.checkCast(org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.netconf.base._1._0.rev110601.GetConfigOutput.class, obj);
        if (other == null) {
            return false;
        }
        if (!Objects.equals(thisObj.getData(), other.getData())) {
            return false;
        }
        return thisObj.augmentations().equals(other.augmentations());
    }
    
    /**
     * Default implementation of {@link Object#toString()} contract for this interface.
     * Implementations of this interface are encouraged to defer to this method to get consistent string
     * representations across all implementations.
     *
     * @param obj Object for which to generate toString() result.
     * @return {@link String} value of data modeled by this interface.
     * @throws NullPointerException if {@code obj} is null
     */
    static String bindingToString(final org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.netconf.base._1._0.rev110601.@NonNull GetConfigOutput obj) {
        final MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper("GetConfigOutput");
        CodeHelpers.appendValue(helper, "data", obj.getData());
        CodeHelpers.appendValue(helper, "augmentation", obj.augmentations().values());
        return helper.toString();
    }
    
    /**
     * Return data, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Copy of the source datastore subset that matched the filter criteria (if any).
     *         An empty data container indicates that the request did not produce any results.
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.netconf.base._1._0.rev110601.get.config.output.Data} data, or {@code null} if it is not present.
     *
     */
    Data getData();

}

