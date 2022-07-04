package org.opendaylight.yang.gen.v1.http.openconfig.net.yang.openconfig.types.rev190416;
import com.google.common.base.MoreObjects;
import java.beans.ConstructorProperties;
import java.io.Serializable;
import java.lang.Override;
import java.lang.Short;
import java.lang.String;
import java.util.Objects;
import javax.annotation.processing.Generated;
import javax.management.ConstructorParameters;
import org.opendaylight.yangtools.yang.binding.CodeHelpers;
import org.opendaylight.yangtools.yang.binding.ScalarTypeObject;
import org.opendaylight.yangtools.yang.common.Uint8;

@Generated("mdsal-binding-generator")
public class Percentage
 implements ScalarTypeObject<Uint8>, Serializable {
    private static final long serialVersionUID = 482899018947419815L;
    private final Uint8 _value;

    private static void check_valueRange(final short value) {
        if (value <= (short)100) {
            return;
        }
        CodeHelpers.throwInvalidRange("[[0..100]]", value);
    }

    @ConstructorParameters("value")
    @ConstructorProperties("value")
    public Percentage(Uint8 _value) {
        if (_value != null) {
            check_valueRange(_value.shortValue());
        }
        
        CodeHelpers.requireValue(_value);
    
        this._value = _value;
    }
    
    /**
     * Utility migration constructor.
     *
     * @param _value value in legacy Java type
     * @deprecated Use {@link #Percentage(Uint8)} instead.
     */
    @Deprecated(forRemoval = true)
    public Percentage(Short _value) {
        this(CodeHelpers.compatUint(_value));
    }
    
    /**
     * Creates a copy from Source Object.
     *
     * @param source Source object
     */
    public Percentage(Percentage source) {
        this._value = source._value;
    }

    public static Percentage getDefaultInstance(final String defaultValue) {
        return new Percentage(Uint8.valueOf(defaultValue));
    }

    @Override
    public Uint8 getValue() {
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
        if (!(obj instanceof Percentage)) {
            return false;
        }
        final Percentage other = (Percentage) obj;
        if (!Objects.equals(_value, other._value)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        final MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper(Percentage.class);
        CodeHelpers.appendValue(helper, "_value", _value);
        return helper.toString();
    }
}

