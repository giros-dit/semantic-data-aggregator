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
import org.opendaylight.yangtools.yang.binding.Augmentable;
import org.opendaylight.yangtools.yang.binding.CodeHelpers;
import org.opendaylight.yangtools.yang.binding.RpcInput;
import org.opendaylight.yangtools.yang.common.Empty;
import org.opendaylight.yangtools.yang.common.QName;
import org.opendaylight.yangtools.yang.common.Uint32;

/**
 *
 * <p>
 * This class represents the following YANG schema fragment defined in module <b>ietf-netconf</b>
 * <pre>
 * input input {
 *   leaf confirmed {
 *     if-feature confirmed-commit;
 *     type empty;
 *   }
 *   leaf confirm-timeout {
 *     if-feature confirmed-commit;
 *     type uint32 {
 *       range 1..max;
 *     }
 *     units seconds;
 *     default 600;
 *   }
 *   leaf persist {
 *     if-feature confirmed-commit;
 *     type string;
 *   }
 *   leaf persist-id {
 *     if-feature confirmed-commit;
 *     type string;
 *   }
 * }
 * </pre>The schema path to identify an instance is
 * <i>ietf-netconf/commit/input</i>
 *
 */
@Generated("mdsal-binding-generator")
public interface CommitInput
    extends
    RpcInput,
    Augmentable<CommitInput>
{



    public static final @NonNull QName QNAME = $YangModuleInfoImpl.qnameOf("input");

    @Override
    default Class<org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.netconf.base._1._0.rev110601.CommitInput> implementedInterface() {
        return org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.netconf.base._1._0.rev110601.CommitInput.class;
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
    static int bindingHashCode(final org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.netconf.base._1._0.rev110601.@NonNull CommitInput obj) {
        final int prime = 31;
        int result = 1;
        result = prime * result + Objects.hashCode(obj.getConfirmTimeout());
        result = prime * result + Objects.hashCode(obj.getConfirmed());
        result = prime * result + Objects.hashCode(obj.getPersist());
        result = prime * result + Objects.hashCode(obj.getPersistId());
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
    static boolean bindingEquals(final org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.netconf.base._1._0.rev110601.@NonNull CommitInput thisObj, final Object obj) {
        if (thisObj == obj) {
            return true;
        }
        final org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.netconf.base._1._0.rev110601.CommitInput other = CodeHelpers.checkCast(org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.netconf.base._1._0.rev110601.CommitInput.class, obj);
        if (other == null) {
            return false;
        }
        if (!Objects.equals(thisObj.getConfirmTimeout(), other.getConfirmTimeout())) {
            return false;
        }
        if (!Objects.equals(thisObj.getConfirmed(), other.getConfirmed())) {
            return false;
        }
        if (!Objects.equals(thisObj.getPersist(), other.getPersist())) {
            return false;
        }
        if (!Objects.equals(thisObj.getPersistId(), other.getPersistId())) {
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
    static String bindingToString(final org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.netconf.base._1._0.rev110601.@NonNull CommitInput obj) {
        final MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper("CommitInput");
        CodeHelpers.appendValue(helper, "confirmTimeout", obj.getConfirmTimeout());
        CodeHelpers.appendValue(helper, "confirmed", obj.getConfirmed());
        CodeHelpers.appendValue(helper, "persist", obj.getPersist());
        CodeHelpers.appendValue(helper, "persistId", obj.getPersistId());
        CodeHelpers.appendValue(helper, "augmentation", obj.augmentations().values());
        return helper.toString();
    }
    
    /**
     * Return confirmed, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Requests a confirmed commit.
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yangtools.yang.common.Empty} confirmed, or {@code null} if it is not present.
     *
     */
    Empty getConfirmed();
    
    /**
     * Return confirmTimeout, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         The timeout interval for a confirmed commit.
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yangtools.yang.common.Uint32} confirmTimeout, or {@code null} if it is not present.
     *
     */
    Uint32 getConfirmTimeout();
    
    /**
     * Return persist, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         This parameter is used to make a confirmed commit persistent. A persistent
     *         confirmed commit is not aborted if the NETCONF session terminates. The only way
     *         to abort a persistent confirmed commit is to let the timer expire, or to use the
     *         &amp;lt;cancel-commit&amp;gt; operation. The value of this parameter is a token
     *         that must be given in the 'persist-id' parameter of &amp;lt;commit&amp;gt; or
     *         &amp;lt;cancel-commit&amp;gt; operations in order to confirm or cancel the
     *         persistent confirmed commit. The token should be a random string.
     *     </code>
     * </pre>
     *
     * @return {@code java.lang.String} persist, or {@code null} if it is not present.
     *
     */
    String getPersist();
    
    /**
     * Return persistId, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         This parameter is given in order to commit a persistent confirmed commit. The
     *         value must be equal to the value given in the 'persist' parameter to the
     *         &amp;lt;commit&amp;gt; operation. If it does not match, the operation fails with
     *         an 'invalid-value' error.
     *     </code>
     * </pre>
     *
     * @return {@code java.lang.String} persistId, or {@code null} if it is not present.
     *
     */
    String getPersistId();

}

