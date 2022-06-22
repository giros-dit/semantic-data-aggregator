package org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.common.flow.fields;
import com.google.common.base.MoreObjects;
import java.lang.Class;
import java.lang.NullPointerException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.util.Objects;
import javax.annotation.processing.Generated;
import org.eclipse.jdt.annotation.NonNull;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.$YangModuleInfoImpl;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFlowFields;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.PrefixLengthIpv4;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.TopLabelType;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev130715.Ipv4Address;
import org.opendaylight.yangtools.yang.binding.Augmentable;
import org.opendaylight.yangtools.yang.binding.ChildOf;
import org.opendaylight.yangtools.yang.binding.CodeHelpers;
import org.opendaylight.yangtools.yang.common.QName;
import org.opendaylight.yangtools.yang.common.Uint32;
import org.opendaylight.yangtools.yang.common.Uint64;

/**
 * This container collects all the metrics associated with MPLS (MultiProtocol 
 * Label Switching)
 * 
 * <p>
 * This class represents the following YANG schema fragment defined in module <b>netflow-v9</b>
 * <pre>
 * container mpls {
 *   leaf pal-rd {
 *     type uint64;
 *   }
 *   leaf prefix-len {
 *     type prefix-length-ipv4;
 *   }
 *   leaf top-label-type {
 *     type net-v9:top-label-type;
 *   }
 *   leaf top-label-ip {
 *     type inet:ipv4-address;
 *   }
 *   leaf label-1 {
 *     type uint32 {
 *       range 0..16777215;
 *     }
 *   }
 *   leaf label-2 {
 *     type uint32 {
 *       range 0..16777215;
 *     }
 *   }
 *   leaf label-3 {
 *     type uint32 {
 *       range 0..16777215;
 *     }
 *   }
 *   leaf label-4 {
 *     type uint32 {
 *       range 0..16777215;
 *     }
 *   }
 *   leaf label-5 {
 *     type uint32 {
 *       range 0..16777215;
 *     }
 *   }
 *   leaf label-6 {
 *     type uint32 {
 *       range 0..16777215;
 *     }
 *   }
 *   leaf label-7 {
 *     type uint32 {
 *       range 0..16777215;
 *     }
 *   }
 *   leaf label-8 {
 *     type uint32 {
 *       range 0..16777215;
 *     }
 *   }
 *   leaf label-9 {
 *     type uint32 {
 *       range 0..16777215;
 *     }
 *   }
 *   leaf label-10 {
 *     type uint32 {
 *       range 0..16777215;
 *     }
 *   }
 * }
 * </pre>The schema path to identify an instance is
 * <i>netflow-v9/common-flow-fields/mpls</i>
 * 
 * <p>To create instances of this class use {@link MplsBuilder}.
 * @see MplsBuilder
 *
 */
@Generated("mdsal-binding-generator")
public interface Mpls
    extends
    ChildOf<CommonFlowFields>,
    Augmentable<Mpls>
{



    public static final @NonNull QName QNAME = $YangModuleInfoImpl.qnameOf("mpls");

    @Override
    default Class<org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.common.flow.fields.Mpls> implementedInterface() {
        return org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.common.flow.fields.Mpls.class;
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
    static int bindingHashCode(final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.common.flow.fields.@NonNull Mpls obj) {
        final int prime = 31;
        int result = 1;
        result = prime * result + Objects.hashCode(obj.getLabel1());
        result = prime * result + Objects.hashCode(obj.getLabel10());
        result = prime * result + Objects.hashCode(obj.getLabel2());
        result = prime * result + Objects.hashCode(obj.getLabel3());
        result = prime * result + Objects.hashCode(obj.getLabel4());
        result = prime * result + Objects.hashCode(obj.getLabel5());
        result = prime * result + Objects.hashCode(obj.getLabel6());
        result = prime * result + Objects.hashCode(obj.getLabel7());
        result = prime * result + Objects.hashCode(obj.getLabel8());
        result = prime * result + Objects.hashCode(obj.getLabel9());
        result = prime * result + Objects.hashCode(obj.getPalRd());
        result = prime * result + Objects.hashCode(obj.getPrefixLen());
        result = prime * result + Objects.hashCode(obj.getTopLabelIp());
        result = prime * result + Objects.hashCode(obj.getTopLabelType());
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
    static boolean bindingEquals(final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.common.flow.fields.@NonNull Mpls thisObj, final Object obj) {
        if (thisObj == obj) {
            return true;
        }
        final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.common.flow.fields.Mpls other = CodeHelpers.checkCast(org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.common.flow.fields.Mpls.class, obj);
        if (other == null) {
            return false;
        }
        if (!Objects.equals(thisObj.getLabel1(), other.getLabel1())) {
            return false;
        }
        if (!Objects.equals(thisObj.getLabel10(), other.getLabel10())) {
            return false;
        }
        if (!Objects.equals(thisObj.getLabel2(), other.getLabel2())) {
            return false;
        }
        if (!Objects.equals(thisObj.getLabel3(), other.getLabel3())) {
            return false;
        }
        if (!Objects.equals(thisObj.getLabel4(), other.getLabel4())) {
            return false;
        }
        if (!Objects.equals(thisObj.getLabel5(), other.getLabel5())) {
            return false;
        }
        if (!Objects.equals(thisObj.getLabel6(), other.getLabel6())) {
            return false;
        }
        if (!Objects.equals(thisObj.getLabel7(), other.getLabel7())) {
            return false;
        }
        if (!Objects.equals(thisObj.getLabel8(), other.getLabel8())) {
            return false;
        }
        if (!Objects.equals(thisObj.getLabel9(), other.getLabel9())) {
            return false;
        }
        if (!Objects.equals(thisObj.getPalRd(), other.getPalRd())) {
            return false;
        }
        if (!Objects.equals(thisObj.getPrefixLen(), other.getPrefixLen())) {
            return false;
        }
        if (!Objects.equals(thisObj.getTopLabelIp(), other.getTopLabelIp())) {
            return false;
        }
        if (!Objects.equals(thisObj.getTopLabelType(), other.getTopLabelType())) {
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
    static String bindingToString(final org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.common.flow.fields.@NonNull Mpls obj) {
        final MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper("Mpls");
        CodeHelpers.appendValue(helper, "label1", obj.getLabel1());
        CodeHelpers.appendValue(helper, "label10", obj.getLabel10());
        CodeHelpers.appendValue(helper, "label2", obj.getLabel2());
        CodeHelpers.appendValue(helper, "label3", obj.getLabel3());
        CodeHelpers.appendValue(helper, "label4", obj.getLabel4());
        CodeHelpers.appendValue(helper, "label5", obj.getLabel5());
        CodeHelpers.appendValue(helper, "label6", obj.getLabel6());
        CodeHelpers.appendValue(helper, "label7", obj.getLabel7());
        CodeHelpers.appendValue(helper, "label8", obj.getLabel8());
        CodeHelpers.appendValue(helper, "label9", obj.getLabel9());
        CodeHelpers.appendValue(helper, "palRd", obj.getPalRd());
        CodeHelpers.appendValue(helper, "prefixLen", obj.getPrefixLen());
        CodeHelpers.appendValue(helper, "topLabelIp", obj.getTopLabelIp());
        CodeHelpers.appendValue(helper, "topLabelType", obj.getTopLabelType());
        CodeHelpers.appendValue(helper, "augmentation", obj.augmentations().values());
        return helper.toString();
    }
    
    /**
     * Return palRd, or {@code null} if it is not present.
     * 
     * <pre>
     *     <code>
     *         MPLS PAL Route Distinguisher
     *     </code>
     * </pre>
     * 
     * @return {@code org.opendaylight.yangtools.yang.common.Uint64} palRd, or {@code null} if it is not present.
     *
     */
    Uint64 getPalRd();
    
    /**
     * Return prefixLen, or {@code null} if it is not present.
     * 
     * <pre>
     *     <code>
     *         Number of consecutive bits in the MPLS prefix length
     *     </code>
     * </pre>
     * 
     * @return {@code org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.PrefixLengthIpv4} prefixLen, or {@code null} if it is not present.
     *
     */
    PrefixLengthIpv4 getPrefixLen();
    
    /**
     * Return topLabelType, or {@code null} if it is not present.
     * 
     * <pre>
     *     <code>
     *         MPLS Top Label Type
     *     </code>
     * </pre>
     * 
     * @return {@code org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.TopLabelType} topLabelType, or {@code null} if it is not present.
     *
     */
    TopLabelType getTopLabelType();
    
    /**
     * Return topLabelIp, or {@code null} if it is not present.
     * 
     * <pre>
     *     <code>
     *         Forwarding Equivalent Class corresponding to the MPLS Top Label
     *     </code>
     * </pre>
     * 
     * @return {@code org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev130715.Ipv4Address} topLabelIp, or {@code null} if it is not present.
     *
     */
    Ipv4Address getTopLabelIp();
    
    /**
     * Return label1, or {@code null} if it is not present.
     * 
     * <pre>
     *     <code>
     *         MPLS label at position 1 in the stack. This comprises 20 bits of MPLS label, 3
     *         EXP (experimental) bits and 1 S (end-of-stack) bit
     *     </code>
     * </pre>
     * 
     * @return {@code org.opendaylight.yangtools.yang.common.Uint32} label1, or {@code null} if it is not present.
     *
     */
    Uint32 getLabel1();
    
    /**
     * Return label2, or {@code null} if it is not present.
     * 
     * <pre>
     *     <code>
     *         MPLS label at position 2 in the stack. This comprises 20 bits of MPLS label, 3
     *         EXP (experimental) bits and 1 S (end-of-stack) bit
     *     </code>
     * </pre>
     * 
     * @return {@code org.opendaylight.yangtools.yang.common.Uint32} label2, or {@code null} if it is not present.
     *
     */
    Uint32 getLabel2();
    
    /**
     * Return label3, or {@code null} if it is not present.
     * 
     * <pre>
     *     <code>
     *         MPLS label at position 3 in the stack. This comprises 20 bits of MPLS label, 3
     *         EXP (experimental) bits and 1 S (end-of-stack) bit
     *     </code>
     * </pre>
     * 
     * @return {@code org.opendaylight.yangtools.yang.common.Uint32} label3, or {@code null} if it is not present.
     *
     */
    Uint32 getLabel3();
    
    /**
     * Return label4, or {@code null} if it is not present.
     * 
     * <pre>
     *     <code>
     *         MPLS label at position 4 in the stack. This comprises 20 bits of MPLS label, 3
     *         EXP (experimental) bits and 1 S (end-of-stack) bit
     *     </code>
     * </pre>
     * 
     * @return {@code org.opendaylight.yangtools.yang.common.Uint32} label4, or {@code null} if it is not present.
     *
     */
    Uint32 getLabel4();
    
    /**
     * Return label5, or {@code null} if it is not present.
     * 
     * <pre>
     *     <code>
     *         MPLS label at position 5 in the stack. This comprises 20 bits of MPLS label, 3
     *         EXP (experimental) bits and 1 S (end-of-stack) bit
     *     </code>
     * </pre>
     * 
     * @return {@code org.opendaylight.yangtools.yang.common.Uint32} label5, or {@code null} if it is not present.
     *
     */
    Uint32 getLabel5();
    
    /**
     * Return label6, or {@code null} if it is not present.
     * 
     * <pre>
     *     <code>
     *         MPLS label at position 6 in the stack. This comprises 20 bits of MPLS label, 3
     *         EXP (experimental) bits and 1 S (end-of-stack) bit
     *     </code>
     * </pre>
     * 
     * @return {@code org.opendaylight.yangtools.yang.common.Uint32} label6, or {@code null} if it is not present.
     *
     */
    Uint32 getLabel6();
    
    /**
     * Return label7, or {@code null} if it is not present.
     * 
     * <pre>
     *     <code>
     *         MPLS label at position 7 in the stack. This comprises 20 bits of MPLS label, 3
     *         EXP (experimental) bits and 1 S (end-of-stack) bit
     *     </code>
     * </pre>
     * 
     * @return {@code org.opendaylight.yangtools.yang.common.Uint32} label7, or {@code null} if it is not present.
     *
     */
    Uint32 getLabel7();
    
    /**
     * Return label8, or {@code null} if it is not present.
     * 
     * <pre>
     *     <code>
     *         MPLS label at position 8 in the stack. This comprises 20 bits of MPLS label, 3
     *         EXP (experimental) bits and 1 S (end-of-stack) bit
     *     </code>
     * </pre>
     * 
     * @return {@code org.opendaylight.yangtools.yang.common.Uint32} label8, or {@code null} if it is not present.
     *
     */
    Uint32 getLabel8();
    
    /**
     * Return label9, or {@code null} if it is not present.
     * 
     * <pre>
     *     <code>
     *         MPLS label at position 9 in the stack. This comprises 20 bits of MPLS label, 3
     *         EXP (experimental) bits and 1 S (end-of-stack) bit
     *     </code>
     * </pre>
     * 
     * @return {@code org.opendaylight.yangtools.yang.common.Uint32} label9, or {@code null} if it is not present.
     *
     */
    Uint32 getLabel9();
    
    /**
     * Return label10, or {@code null} if it is not present.
     * 
     * <pre>
     *     <code>
     *         MPLS label at position 10 in the stack. This comprises 20 bits of MPLS label, 3
     *         EXP (experimental) bits and 1 S (end-of-stack) bit
     *     </code>
     * </pre>
     * 
     * @return {@code org.opendaylight.yangtools.yang.common.Uint32} label10, or {@code null} if it is not present.
     *
     */
    Uint32 getLabel10();

}

