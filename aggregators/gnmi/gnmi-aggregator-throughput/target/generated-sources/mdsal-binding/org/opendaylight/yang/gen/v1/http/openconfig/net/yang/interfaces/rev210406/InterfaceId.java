package org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406;
import com.google.common.base.MoreObjects;
import java.beans.ConstructorProperties;
import java.io.Serializable;
import java.lang.Override;
import java.lang.String;
import java.util.Objects;
import javax.annotation.processing.Generated;
import javax.management.ConstructorParameters;
import org.opendaylight.yangtools.yang.binding.CodeHelpers;
import org.opendaylight.yangtools.yang.binding.ScalarTypeObject;

@Generated("mdsal-binding-generator")
public class InterfaceId
 implements ScalarTypeObject<String>, Serializable {
    private static final long serialVersionUID = -8179125288898239514L;
    private final String _value;


    @ConstructorParameters("value")
    @ConstructorProperties("value")
    public InterfaceId(String _value) {
        
        CodeHelpers.requireValue(_value);
    
        this._value = _value;
    }
    
    /**
     * Creates a copy from Source Object.
     *
     * @param source Source object
     */
    public InterfaceId(InterfaceId source) {
        this._value = source._value;
    }

    public static InterfaceId getDefaultInstance(final String defaultValue) {
        return new InterfaceId(defaultValue);
    }

    @Override
    public String getValue() {
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
        if (!(obj instanceof InterfaceId)) {
            return false;
        }
        final InterfaceId other = (InterfaceId) obj;
        if (!Objects.equals(_value, other._value)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        final MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper(InterfaceId.class);
        CodeHelpers.appendValue(helper, "_value", _value);
        return helper.toString();
    }
}

