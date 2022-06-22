package org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.metric.families;
import com.google.common.base.MoreObjects;
import java.lang.Override;
import java.lang.String;
import java.util.Objects;
import javax.annotation.processing.Generated;
import org.eclipse.jdt.annotation.NonNull;
import org.opendaylight.yangtools.yang.binding.CodeHelpers;
import org.opendaylight.yangtools.yang.binding.Identifier;

@Generated("mdsal-binding-generator")
public class MetricFamilyKey
 implements Identifier<MetricFamily> {
    private static final long serialVersionUID = -8849492196515422498L;
    private final String _name;


    public MetricFamilyKey(@NonNull String _name) {
        this._name = CodeHelpers.requireKeyProp(_name, "name");
    }
    
    /**
     * Creates a copy from Source Object.
     *
     * @param source Source object
     */
    public MetricFamilyKey(MetricFamilyKey source) {
        this._name = source._name;
    }


    public @NonNull String getName() {
        return _name;
    }


    @Override
    public int hashCode() {
        return CodeHelpers.wrapperHashCode(_name);
    }

    @Override
    public final boolean equals(java.lang.Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MetricFamilyKey)) {
            return false;
        }
        final MetricFamilyKey other = (MetricFamilyKey) obj;
        if (!Objects.equals(_name, other._name)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        final MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper(MetricFamilyKey.class);
        CodeHelpers.appendValue(helper, "_name", _name);
        return helper.toString();
    }
}

