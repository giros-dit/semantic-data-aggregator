package org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import java.beans.ConstructorProperties;
import java.io.Serializable;
import java.lang.Override;
import java.lang.String;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;
import javax.annotation.processing.Generated;
import javax.management.ConstructorParameters;
import org.opendaylight.yangtools.yang.binding.CodeHelpers;
import org.opendaylight.yangtools.yang.binding.ScalarTypeObject;

@Generated("mdsal-binding-generator")
public class PhysAddress
 implements ScalarTypeObject<String>, Serializable {
    private static final long serialVersionUID = 2700179118320989580L;
    public static final List<String> PATTERN_CONSTANTS = ImmutableList.of("^(?:([0-9a-fA-F]{2}(:[0-9a-fA-F]{2})*)?)$");
    private static final Pattern patterns = Pattern.compile(PATTERN_CONSTANTS.get(0));
    private static final String regexes = "([0-9a-fA-F]{2}(:[0-9a-fA-F]{2})*)?";
    private final String _value;

    private static void check_valueLength(final String value) {
    }

    @ConstructorParameters("value")
    @ConstructorProperties("value")
    public PhysAddress(String _value) {
        if (_value != null) {
            check_valueLength(_value);
        }
        
        CodeHelpers.requireValue(_value);
        CodeHelpers.checkPattern(_value, patterns, regexes);
    
        this._value = _value;
    }
    
    /**
     * Creates a copy from Source Object.
     *
     * @param source Source object
     */
    public PhysAddress(PhysAddress source) {
        this._value = source._value;
    }

    public static PhysAddress getDefaultInstance(final String defaultValue) {
        return new PhysAddress(defaultValue);
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
        if (!(obj instanceof PhysAddress)) {
            return false;
        }
        final PhysAddress other = (PhysAddress) obj;
        if (!Objects.equals(_value, other._value)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        final MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper(PhysAddress.class);
        CodeHelpers.appendValue(helper, "_value", _value);
        return helper.toString();
    }
}

