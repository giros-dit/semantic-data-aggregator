package org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.netflow.export.packet;
import com.google.common.base.MoreObjects;
import java.lang.Integer;
import java.lang.Override;
import java.lang.String;
import java.util.Objects;
import javax.annotation.processing.Generated;
import org.eclipse.jdt.annotation.NonNull;
import org.opendaylight.yangtools.yang.binding.CodeHelpers;
import org.opendaylight.yangtools.yang.binding.Identifier;

@Generated("mdsal-binding-generator")
public class FlowDataRecordKey
 implements Identifier<FlowDataRecord> {
    private static final long serialVersionUID = -2136154672042847250L;
    private final Integer _flowId;


    public FlowDataRecordKey(@NonNull Integer _flowId) {
        this._flowId = CodeHelpers.requireKeyProp(_flowId, "flowId");
    }
    
    /**
     * Creates a copy from Source Object.
     *
     * @param source Source object
     */
    public FlowDataRecordKey(FlowDataRecordKey source) {
        this._flowId = source._flowId;
    }


    public @NonNull Integer getFlowId() {
        return _flowId;
    }


    @Override
    public int hashCode() {
        return CodeHelpers.wrapperHashCode(_flowId);
    }

    @Override
    public final boolean equals(java.lang.Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FlowDataRecordKey)) {
            return false;
        }
        final FlowDataRecordKey other = (FlowDataRecordKey) obj;
        if (!Objects.equals(_flowId, other._flowId)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        final MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper(FlowDataRecordKey.class);
        CodeHelpers.appendValue(helper, "_flowId", _flowId);
        return helper.toString();
    }
}

