package org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008;
import com.google.common.base.MoreObjects;
import java.beans.ConstructorProperties;
import java.io.Serializable;
import java.lang.Integer;
import java.lang.Override;
import java.lang.String;
import java.util.Objects;
import javax.annotation.processing.Generated;
import javax.management.ConstructorParameters;
import org.opendaylight.yangtools.yang.binding.CodeHelpers;
import org.opendaylight.yangtools.yang.binding.ScalarTypeObject;
import org.opendaylight.yangtools.yang.common.Uint16;

@Generated("mdsal-binding-generator")
public class VlanId
 implements ScalarTypeObject<Uint16>, Serializable {
    private static final long serialVersionUID = -3708880084115267596L;
    private final Uint16 _value;

    private static void check_valueRange(final int value) {
        if (value <= 4095) {
            return;
        }
        CodeHelpers.throwInvalidRange("[[0..4095]]", value);
    }

    @ConstructorParameters("value")
    @ConstructorProperties("value")
    public VlanId(Uint16 _value) {
        if (_value != null) {
            check_valueRange(_value.intValue());
        }
        
        CodeHelpers.requireValue(_value);
    
        this._value = _value;
    }
    
    /**
     * Utility migration constructor.
     *
     * @param _value value in legacy Java type
     * @deprecated Use {@link #VlanId(Uint16)} instead.
     */
    @Deprecated(forRemoval = true)
    public VlanId(Integer _value) {
        this(CodeHelpers.compatUint(_value));
    }
    
    /**
     * Creates a copy from Source Object.
     *
     * @param source Source object
     */
    public VlanId(VlanId source) {
        this._value = source._value;
    }

    public static VlanId getDefaultInstance(final String defaultValue) {
        return new VlanId(Uint16.valueOf(defaultValue));
    }

    @Override
    public Uint16 getValue() {
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
        if (!(obj instanceof VlanId)) {
            return false;
        }
        final VlanId other = (VlanId) obj;
        if (!Objects.equals(_value, other._value)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        final MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper(VlanId.class);
        CodeHelpers.appendValue(helper, "_value", _value);
        return helper.toString();
    }
}

