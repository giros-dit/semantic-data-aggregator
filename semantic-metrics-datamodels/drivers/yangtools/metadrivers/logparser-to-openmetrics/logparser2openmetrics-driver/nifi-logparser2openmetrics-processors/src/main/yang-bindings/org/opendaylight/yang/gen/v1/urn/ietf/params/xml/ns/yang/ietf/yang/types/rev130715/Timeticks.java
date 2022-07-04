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
public class Timeticks
 implements ScalarTypeObject<Uint32>, Serializable {
    private static final long serialVersionUID = 1624106397879808914L;
    private final Uint32 _value;

    private static void check_valueRange(final long value) {
    }

    @ConstructorParameters("value")
    @ConstructorProperties("value")
    public Timeticks(Uint32 _value) {
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
     * @deprecated Use {@link #Timeticks(Uint32)} instead.
     */
    @Deprecated(forRemoval = true)
    public Timeticks(Long _value) {
        this(CodeHelpers.compatUint(_value));
    }
    
    /**
     * Creates a copy from Source Object.
     *
     * @param source Source object
     */
    public Timeticks(Timeticks source) {
        this._value = source._value;
    }

    public static Timeticks getDefaultInstance(final String defaultValue) {
        return new Timeticks(Uint32.valueOf(defaultValue));
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
        if (!(obj instanceof Timeticks)) {
            return false;
        }
        final Timeticks other = (Timeticks) obj;
        if (!Objects.equals(_value, other._value)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        final MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper(Timeticks.class);
        CodeHelpers.appendValue(helper, "_value", _value);
        return helper.toString();
    }
}

