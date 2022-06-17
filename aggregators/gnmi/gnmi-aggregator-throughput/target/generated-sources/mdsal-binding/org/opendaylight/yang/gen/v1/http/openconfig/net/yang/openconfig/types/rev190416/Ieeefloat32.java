package org.opendaylight.yang.gen.v1.http.openconfig.net.yang.openconfig.types.rev190416;
import com.google.common.base.MoreObjects;
import java.beans.ConstructorProperties;
import java.io.Serializable;
import java.lang.Override;
import java.lang.String;
import java.util.Arrays;
import java.util.Base64;
import javax.annotation.processing.Generated;
import javax.management.ConstructorParameters;
import org.opendaylight.yangtools.yang.binding.CodeHelpers;
import org.opendaylight.yangtools.yang.binding.ScalarTypeObject;

@Generated("mdsal-binding-generator")
public class Ieeefloat32
 implements ScalarTypeObject<byte[]>, Serializable {
    private static final long serialVersionUID = -3128969029667028000L;
    private final byte[] _value;

    private static void check_valueLength(final byte[] value) {
        final int length = value.length;
        if (length == 4) {
            return;
        }
        CodeHelpers.throwInvalidLength("[[4..4]]", value);
    }

    @ConstructorParameters("value")
    @ConstructorProperties("value")
    public Ieeefloat32(byte[] _value) {
        if (_value != null) {
            check_valueLength(_value);
        }
        
        CodeHelpers.requireValue(_value);
    
        this._value = _value.clone();
    }
    
    /**
     * Creates a copy from Source Object.
     *
     * @param source Source object
     */
    public Ieeefloat32(Ieeefloat32 source) {
        this._value = source._value;
    }

    public static Ieeefloat32 getDefaultInstance(final String defaultValue) {
        return new Ieeefloat32(Base64.getDecoder().decode(defaultValue));
    }

    @Override
    public byte[] getValue() {
        return _value.clone();
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
        if (!(obj instanceof Ieeefloat32)) {
            return false;
        }
        final Ieeefloat32 other = (Ieeefloat32) obj;
        if (!Arrays.equals(_value, other._value)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        final MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper(Ieeefloat32.class);
        CodeHelpers.appendValue(helper, "_value", _value);
        return helper.toString();
    }
}

