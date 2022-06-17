package org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.aggregated.rev220302;
import com.google.common.base.MoreObjects;
import com.google.common.collect.Range;
import java.beans.ConstructorProperties;
import java.io.Serializable;
import java.lang.Override;
import java.lang.String;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.Objects;
import javax.annotation.processing.Generated;
import javax.management.ConstructorParameters;
import org.opendaylight.yangtools.yang.binding.CodeHelpers;
import org.opendaylight.yangtools.yang.binding.ScalarTypeObject;

@Generated("mdsal-binding-generator")
public class PerDecimal
 implements ScalarTypeObject<BigDecimal>, Serializable {
    private static final long serialVersionUID = -1008577403014883358L;
    private final BigDecimal _value;

    private static final Range<java.math.BigDecimal>[] CHECK_VALUERANGE_RANGES;
    static {
        @SuppressWarnings("unchecked")
        final Range<java.math.BigDecimal>[] a = (Range<java.math.BigDecimal>[]) Array.newInstance(Range.class, 1);
        a[0] = Range.closed(new java.math.BigDecimal("0.00000"), new java.math.BigDecimal("92233720368547.75807"));
        CHECK_VALUERANGE_RANGES = a;
    }
    private static void check_valueRange(final java.math.BigDecimal value) {
        for (Range<java.math.BigDecimal> r : CHECK_VALUERANGE_RANGES) {
            if (r.contains(value)) {
                return;
            }
        }
        CodeHelpers.throwInvalidRange(CHECK_VALUERANGE_RANGES, value);
    }

    @ConstructorParameters("value")
    @ConstructorProperties("value")
    public PerDecimal(BigDecimal _value) {
        if (_value != null) {
            check_valueRange(_value);
        }
        
        CodeHelpers.requireValue(_value);
    
        this._value = _value;
    }
    
    /**
     * Creates a copy from Source Object.
     *
     * @param source Source object
     */
    public PerDecimal(PerDecimal source) {
        this._value = source._value;
    }

    public static PerDecimal getDefaultInstance(final String defaultValue) {
        return new PerDecimal(new BigDecimal(defaultValue));
    }

    @Override
    public BigDecimal getValue() {
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
        if (!(obj instanceof PerDecimal)) {
            return false;
        }
        final PerDecimal other = (PerDecimal) obj;
        if (!Objects.equals(_value, other._value)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        final MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper(PerDecimal.class);
        CodeHelpers.appendValue(helper, "_value", _value);
        return helper.toString();
    }
}

