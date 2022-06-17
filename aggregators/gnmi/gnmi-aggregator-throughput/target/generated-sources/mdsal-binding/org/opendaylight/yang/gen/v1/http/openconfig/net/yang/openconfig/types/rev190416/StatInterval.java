package org.opendaylight.yang.gen.v1.http.openconfig.net.yang.openconfig.types.rev190416;
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
public class StatInterval
 implements ScalarTypeObject<Uint64>, Serializable {
    private static final long serialVersionUID = 6458398287982778474L;
    public static final String _UNITS = "nanoseconds";
    private final Uint64 _value;

    private static void check_valueRange(final long value) {
    }

    @ConstructorParameters("value")
    @ConstructorProperties("value")
    public StatInterval(Uint64 _value) {
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
     * @deprecated Use {@link #StatInterval(Uint64)} instead.
     */
    @Deprecated(forRemoval = true)
    public StatInterval(BigInteger _value) {
        this(CodeHelpers.compatUint(_value));
    }
    
    /**
     * Creates a copy from Source Object.
     *
     * @param source Source object
     */
    public StatInterval(StatInterval source) {
        this._value = source._value;
    }

    public static StatInterval getDefaultInstance(final String defaultValue) {
        return new StatInterval(Uint64.valueOf(defaultValue));
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
        if (!(obj instanceof StatInterval)) {
            return false;
        }
        final StatInterval other = (StatInterval) obj;
        if (!Objects.equals(_value, other._value)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        final MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper(StatInterval.class);
        CodeHelpers.appendValue(helper, "_value", _value);
        CodeHelpers.appendValue(helper, "_UNITS", _UNITS);
        return helper.toString();
    }
}

