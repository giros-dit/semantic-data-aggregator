package org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev210222;
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
public class AsNumber
 implements ScalarTypeObject<Uint32>, Serializable {
    private static final long serialVersionUID = 3880971349126258475L;
    private final Uint32 _value;

    private static void check_valueRange(final long value) {
    }

    @ConstructorParameters("value")
    @ConstructorProperties("value")
    public AsNumber(Uint32 _value) {
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
     * @deprecated Use {@link #AsNumber(Uint32)} instead.
     */
    @Deprecated(forRemoval = true)
    public AsNumber(Long _value) {
        this(CodeHelpers.compatUint(_value));
    }
    
    /**
     * Creates a copy from Source Object.
     *
     * @param source Source object
     */
    public AsNumber(AsNumber source) {
        this._value = source._value;
    }

    public static AsNumber getDefaultInstance(final String defaultValue) {
        return new AsNumber(Uint32.valueOf(defaultValue));
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
        if (!(obj instanceof AsNumber)) {
            return false;
        }
        final AsNumber other = (AsNumber) obj;
        if (!Objects.equals(_value, other._value)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        final MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper(AsNumber.class);
        CodeHelpers.appendValue(helper, "_value", _value);
        return helper.toString();
    }
}

