package org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715;
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
public class Xpath10
 implements ScalarTypeObject<String>, Serializable {
    private static final long serialVersionUID = -7666326053651229181L;
    private final String _value;


    @ConstructorParameters("value")
    @ConstructorProperties("value")
    public Xpath10(String _value) {
        
        CodeHelpers.requireValue(_value);
    
        this._value = _value;
    }
    
    /**
     * Creates a copy from Source Object.
     *
     * @param source Source object
     */
    public Xpath10(Xpath10 source) {
        this._value = source._value;
    }

    public static Xpath10 getDefaultInstance(final String defaultValue) {
        return new Xpath10(defaultValue);
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
        if (!(obj instanceof Xpath10)) {
            return false;
        }
        final Xpath10 other = (Xpath10) obj;
        if (!Objects.equals(_value, other._value)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        final MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper(Xpath10.class);
        CodeHelpers.appendValue(helper, "_value", _value);
        return helper.toString();
    }
}

