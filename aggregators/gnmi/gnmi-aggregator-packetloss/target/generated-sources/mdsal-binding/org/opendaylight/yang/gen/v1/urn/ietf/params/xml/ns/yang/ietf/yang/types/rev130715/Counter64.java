package org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715;
import com.google.common.base.MoreObjects;
import java.beans.ConstructorProperties;
import java.io.Serializable;
import java.lang.Override;
import java.lang.String;
import java.math.BigInteger;
import java.util.Objects;
import javax.annotation.processing.Generated;
import javax.management.ConstructorParameters;
import org.opendaylight.yangtools.yang.binding.CodeHelpers;
import org.opendaylight.yangtools.yang.binding.ScalarTypeObject;
import org.opendaylight.yangtools.yang.common.Uint64;

@Generated("mdsal-binding-generator")
public class Counter64
 implements ScalarTypeObject<Uint64>, Serializable {
    private static final long serialVersionUID = -1099590625836683370L;
    private final Uint64 _value;

    private static void check_valueRange(final long value) {
    }

    @ConstructorParameters("value")
    @ConstructorProperties("value")
    public Counter64(Uint64 _value) {
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
     * @deprecated Use {@link #Counter64(Uint64)} instead.
     */
    @Deprecated(forRemoval = true)
    public Counter64(BigInteger _value) {
        this(CodeHelpers.compatUint(_value));
    }
    
    /**
     * Creates a copy from Source Object.
     *
     * @param source Source object
     */
    public Counter64(Counter64 source) {
        this._value = source._value;
    }

    public static Counter64 getDefaultInstance(final String defaultValue) {
        return new Counter64(Uint64.valueOf(defaultValue));
    }

    @Override
    public Uint64 getValue() {
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
        if (!(obj instanceof Counter64)) {
            return false;
        }
        final Counter64 other = (Counter64) obj;
        if (!Objects.equals(_value, other._value)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        final MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper(Counter64.class);
        CodeHelpers.appendValue(helper, "_value", _value);
        return helper.toString();
    }
}

