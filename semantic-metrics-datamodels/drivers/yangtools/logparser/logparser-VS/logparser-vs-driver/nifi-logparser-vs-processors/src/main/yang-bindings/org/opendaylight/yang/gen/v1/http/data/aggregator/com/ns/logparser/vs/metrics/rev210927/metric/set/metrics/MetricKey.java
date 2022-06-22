package org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.logparser.vs.metrics.rev210927.metric.set.metrics;
import com.google.common.base.MoreObjects;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.util.Objects;
import javax.annotation.processing.Generated;
import org.eclipse.jdt.annotation.NonNull;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.logparser.vs.metrics.rev210927.MetricIdentity;
import org.opendaylight.yangtools.yang.binding.CodeHelpers;
import org.opendaylight.yangtools.yang.binding.Identifier;

@Generated("mdsal-binding-generator")
public class MetricKey
 implements Identifier<Metric> {
    private static final long serialVersionUID = -8342354262285728699L;
    private final Class<? extends MetricIdentity> _name;


    public MetricKey(@NonNull Class<? extends MetricIdentity> _name) {
        this._name = CodeHelpers.requireKeyProp(_name, "name");
    }
    
    /**
     * Creates a copy from Source Object.
     *
     * @param source Source object
     */
    public MetricKey(MetricKey source) {
        this._name = source._name;
    }


    public @NonNull Class<? extends MetricIdentity> getName() {
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
        if (!(obj instanceof MetricKey)) {
            return false;
        }
        final MetricKey other = (MetricKey) obj;
        if (!Objects.equals(_name, other._name)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        final MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper(MetricKey.class);
        CodeHelpers.appendValue(helper, "_name", _name);
        return helper.toString();
    }
}

