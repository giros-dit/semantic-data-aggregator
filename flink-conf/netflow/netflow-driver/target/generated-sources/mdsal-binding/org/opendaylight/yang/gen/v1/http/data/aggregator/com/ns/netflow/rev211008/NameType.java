package org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008;
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
public class NameType
 implements ScalarTypeObject<String>, Serializable {
    private static final long serialVersionUID = 5608113870313253132L;
    public static final List<String> PATTERN_CONSTANTS = ImmutableList.of("^(?:\\S(.*\\S)?)$");
    private static final Pattern patterns = Pattern.compile(PATTERN_CONSTANTS.get(0));
    private static final String regexes = "\\S(.*\\S)?";
    private final String _value;

    private static void check_valueLength(final String value) {
        final int length = value.codePointCount(0, value.length());
        if (length >= 1) {
            return;
        }
        CodeHelpers.throwInvalidLength("[[1..2147483647]]", value);
    }

    @ConstructorParameters("value")
    @ConstructorProperties("value")
    public NameType(String _value) {
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
    public NameType(NameType source) {
        this._value = source._value;
    }

    public static NameType getDefaultInstance(final String defaultValue) {
        return new NameType(defaultValue);
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
        if (!(obj instanceof NameType)) {
            return false;
        }
        final NameType other = (NameType) obj;
        if (!Objects.equals(_value, other._value)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        final MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper(NameType.class);
        CodeHelpers.appendValue(helper, "_value", _value);
        return helper.toString();
    }
}

