package org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.netflow;
import com.google.common.base.MoreObjects;
import java.lang.Override;
import java.lang.String;
import java.util.Objects;
import javax.annotation.processing.Generated;
import org.eclipse.jdt.annotation.NonNull;
import org.opendaylight.yangtools.yang.binding.CodeHelpers;
import org.opendaylight.yangtools.yang.binding.Identifier;

@Generated("mdsal-binding-generator")
public class FieldsFlowRecordKey
 implements Identifier<FieldsFlowRecord> {
    private static final long serialVersionUID = -5249434012486204110L;
    private final String _name;


    public FieldsFlowRecordKey(@NonNull String _name) {
        this._name = CodeHelpers.requireKeyProp(_name, "name");
    }
    
    /**
     * Creates a copy from Source Object.
     *
     * @param source Source object
     */
    public FieldsFlowRecordKey(FieldsFlowRecordKey source) {
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
        if (!(obj instanceof FieldsFlowRecordKey)) {
            return false;
        }
        final FieldsFlowRecordKey other = (FieldsFlowRecordKey) obj;
        if (!Objects.equals(_name, other._name)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        final MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper(FieldsFlowRecordKey.class);
        CodeHelpers.appendValue(helper, "_name", _name);
        return helper.toString();
    }
}

