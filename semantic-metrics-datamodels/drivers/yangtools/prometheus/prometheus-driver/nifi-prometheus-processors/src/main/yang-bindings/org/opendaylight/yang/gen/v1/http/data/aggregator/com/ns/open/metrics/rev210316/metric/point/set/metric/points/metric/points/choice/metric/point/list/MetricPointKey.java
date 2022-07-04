package org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.metric.point.set.metric.points.metric.points.choice.metric.point.list;
import com.google.common.base.MoreObjects;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.util.Objects;
import javax.annotation.processing.Generated;
import org.eclipse.jdt.annotation.NonNull;
import org.opendaylight.yangtools.yang.binding.CodeHelpers;
import org.opendaylight.yangtools.yang.binding.Identifier;

@Generated("mdsal-binding-generator")
public class MetricPointKey
 implements Identifier<MetricPoint> {
    private static final long serialVersionUID = -2907666901564007794L;
    private final Long _timestamp;


    public MetricPointKey(@NonNull Long _timestamp) {
        this._timestamp = CodeHelpers.requireKeyProp(_timestamp, "timestamp");
    }
    
    /**
     * Creates a copy from Source Object.
     *
     * @param source Source object
     */
    public MetricPointKey(MetricPointKey source) {
        this._timestamp = source._timestamp;
    }


    public @NonNull Long getTimestamp() {
        return _timestamp;
    }


    @Override
    public int hashCode() {
        return CodeHelpers.wrapperHashCode(_timestamp);
    }

    @Override
    public final boolean equals(java.lang.Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MetricPointKey)) {
            return false;
        }
        final MetricPointKey other = (MetricPointKey) obj;
        if (!Objects.equals(_timestamp, other._timestamp)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        final MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper(MetricPointKey.class);
        CodeHelpers.appendValue(helper, "_timestamp", _timestamp);
        return helper.toString();
    }
}

