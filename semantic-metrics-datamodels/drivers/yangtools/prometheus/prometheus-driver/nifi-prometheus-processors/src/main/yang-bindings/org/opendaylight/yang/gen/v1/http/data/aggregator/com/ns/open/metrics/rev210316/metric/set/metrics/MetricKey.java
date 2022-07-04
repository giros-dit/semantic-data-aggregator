package org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.metric.set.metrics;
import com.google.common.base.MoreObjects;
import java.lang.Override;
import java.lang.String;
import java.util.Objects;
import javax.annotation.processing.Generated;
import org.eclipse.jdt.annotation.NonNull;
import org.opendaylight.yangtools.yang.binding.CodeHelpers;
import org.opendaylight.yangtools.yang.binding.Identifier;

@Generated("mdsal-binding-generator")
public class MetricKey
 implements Identifier<Metric> {
    private static final long serialVersionUID = 5839430914771167524L;
    private final String _labelSetId;


    public MetricKey(@NonNull String _labelSetId) {
        this._labelSetId = CodeHelpers.requireKeyProp(_labelSetId, "labelSetId");
    }
    
    /**
     * Creates a copy from Source Object.
     *
     * @param source Source object
     */
    public MetricKey(MetricKey source) {
        this._labelSetId = source._labelSetId;
    }


    public @NonNull String getLabelSetId() {
        return _labelSetId;
    }


    @Override
    public int hashCode() {
        return CodeHelpers.wrapperHashCode(_labelSetId);
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
        if (!Objects.equals(_labelSetId, other._labelSetId)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        final MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper(MetricKey.class);
        CodeHelpers.appendValue(helper, "_labelSetId", _labelSetId);
        return helper.toString();
    }
}

