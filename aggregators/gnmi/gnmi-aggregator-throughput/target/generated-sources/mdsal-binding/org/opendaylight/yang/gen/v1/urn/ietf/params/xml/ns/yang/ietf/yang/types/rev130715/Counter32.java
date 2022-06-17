package org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715;
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
public class Counter32
 implements ScalarTypeObject<Uint32>, Serializable {
    private static final long serialVersionUID = -3046049553917040756L;
    private final Uint32 _value;

    private static void check_valueRange(final long value) {
    }

    @ConstructorParameters("value")
    @ConstructorProperties("value")
    public Counter32(Uint32 _value) {
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
     * @deprecated Use {@link #Counter32(Uint32)} instead.
     */
    @Deprecated(forRemoval = true)
    public Counter32(Long _value) {
        this(CodeHelpers.compatUint(_value));
    }
    
    /**
     * Creates a copy from Source Object.
     *
     * @param source Source object
     */
    public Counter32(Counter32 source) {
        this._value = source._value;
    }

    public static Counter32 getDefaultInstance(final String defaultValue) {
        return new Counter32(Uint32.valueOf(defaultValue));
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
        if (!(obj instanceof Counter32)) {
            return false;
        }
        final Counter32 other = (Counter32) obj;
        if (!Objects.equals(_value, other._value)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        final MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper(Counter32.class);
        CodeHelpers.appendValue(helper, "_value", _value);
        return helper.toString();
    }
}

