package org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008;
import com.google.common.base.MoreObjects;
import java.beans.ConstructorProperties;
import java.io.Serializable;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.util.Objects;
import javax.annotation.processing.Generated;
import javax.management.ConstructorParameters;
import org.opendaylight.yangtools.yang.binding.CodeHelpers;
import org.opendaylight.yangtools.yang.binding.ScalarTypeObject;
import org.opendaylight.yangtools.yang.common.Uint32;

@Generated("mdsal-binding-generator")
public class Ipv6FlowLabel
 implements ScalarTypeObject<Uint32>, Serializable {
    private static final long serialVersionUID = -7589288865525379836L;
    private final Uint32 _value;

    private static void check_valueRange(final long value) {
        if (value <= 1048575L) {
            return;
        }
        CodeHelpers.throwInvalidRange("[[0..1048575]]", value);
    }

    @ConstructorParameters("value")
    @ConstructorProperties("value")
    public Ipv6FlowLabel(Uint32 _value) {
        if (_value != null) {
            check_valueRange(_value.longValue());
        }
        
        CodeHelpers.requireValue(_value);
    
        this._value = _value;
    }
    
    /**
     * Utility migration constructor.
     *
     * @param _value value in legacy Java type
     * @deprecated Use {@link #Ipv6FlowLabel(Uint32)} instead.
     */
    @Deprecated(forRemoval = true)
    public Ipv6FlowLabel(Long _value) {
        this(CodeHelpers.compatUint(_value));
    }
    
    /**
     * Creates a copy from Source Object.
     *
     * @param source Source object
     */
    public Ipv6FlowLabel(Ipv6FlowLabel source) {
        this._value = source._value;
    }

    public static Ipv6FlowLabel getDefaultInstance(final String defaultValue) {
        return new Ipv6FlowLabel(Uint32.valueOf(defaultValue));
    }

    @Override
    public Uint32 getValue() {
        return _value;
    }


    @Override
    public int hashCode() {
        return CodeHelpers.wrapperHashCode(_value);
    }

    @Override
    public final boolean equals(java.lang.Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Ipv6FlowLabel)) {
            return false;
        }
        final Ipv6FlowLabel other = (Ipv6FlowLabel) obj;
        if (!Objects.equals(_value, other._value)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        final MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper(Ipv6FlowLabel.class);
        CodeHelpers.appendValue(helper, "_value", _value);
        return helper.toString();
    }
}

