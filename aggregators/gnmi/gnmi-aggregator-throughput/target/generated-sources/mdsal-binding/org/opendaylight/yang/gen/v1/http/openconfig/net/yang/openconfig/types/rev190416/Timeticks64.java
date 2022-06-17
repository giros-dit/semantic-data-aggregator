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
public class Timeticks64
 implements ScalarTypeObject<Uint64>, Serializable {
    private static final long serialVersionUID = 3190694943467642405L;
    public static final String _UNITS = "nanoseconds";
    private final Uint64 _value;

    private static void check_valueRange(final long value) {
    }

    @ConstructorParameters("value")
    @ConstructorProperties("value")
    public Timeticks64(Uint64 _value) {
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
     * @deprecated Use {@link #Timeticks64(Uint64)} instead.
     */
    @Deprecated(forRemoval = true)
    public Timeticks64(BigInteger _value) {
        this(CodeHelpers.compatUint(_value));
    }
    
    /**
     * Creates a copy from Source Object.
     *
     * @param source Source object
     */
    public Timeticks64(Timeticks64 source) {
        this._value = source._value;
    }

    public static Timeticks64 getDefaultInstance(final String defaultValue) {
        return new Timeticks64(Uint64.valueOf(defaultValue));
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
        if (!(obj instanceof Timeticks64)) {
            return false;
        }
        final Timeticks64 other = (Timeticks64) obj;
        if (!Objects.equals(_value, other._value)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        final MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper(Timeticks64.class);
        CodeHelpers.appendValue(helper, "_value", _value);
        CodeHelpers.appendValue(helper, "_UNITS", _UNITS);
        return helper.toString();
    }
}

