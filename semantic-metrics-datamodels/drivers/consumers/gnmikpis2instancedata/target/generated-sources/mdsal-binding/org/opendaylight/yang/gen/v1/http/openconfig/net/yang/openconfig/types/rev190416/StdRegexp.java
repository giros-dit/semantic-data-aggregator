package org.opendaylight.yang.gen.v1.http.openconfig.net.yang.openconfig.types.rev190416;
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
public class StdRegexp
 implements ScalarTypeObject<String>, Serializable {
    private static final long serialVersionUID = -7902520990186322095L;
    private final String _value;


    @ConstructorParameters("value")
    @ConstructorProperties("value")
    public StdRegexp(String _value) {
        
        CodeHelpers.requireValue(_value);
    
        this._value = _value;
    }
    
    /**
     * Creates a copy from Source Object.
     *
     * @param source Source object
     */
    public StdRegexp(StdRegexp source) {
        this._value = source._value;
    }

    public static StdRegexp getDefaultInstance(final String defaultValue) {
        return new StdRegexp(defaultValue);
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
        if (!(obj instanceof StdRegexp)) {
            return false;
        }
        final StdRegexp other = (StdRegexp) obj;
        if (!Objects.equals(_value, other._value)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        final MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper(StdRegexp.class);
        CodeHelpers.appendValue(helper, "_value", _value);
        return helper.toString();
    }
}

