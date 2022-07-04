package org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.subinterfaces.top.subinterfaces;
import com.google.common.base.MoreObjects;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.util.Objects;
import javax.annotation.processing.Generated;
import org.eclipse.jdt.annotation.NonNull;
import org.opendaylight.yangtools.yang.binding.CodeHelpers;
import org.opendaylight.yangtools.yang.binding.Identifier;
import org.opendaylight.yangtools.yang.common.Uint32;

@Generated("mdsal-binding-generator")
public class SubinterfaceKey
 implements Identifier<Subinterface> {
    private static final long serialVersionUID = -2082832482964520193L;
    private final Uint32 _index;


    public SubinterfaceKey(@NonNull Uint32 _index) {
        this._index = CodeHelpers.requireKeyProp(_index, "index");
    }
    
    /**
     * Utility migration constructor.
     *
     * @param _index index in legacy Java type
     * @deprecated Use {@link #SubinterfaceKey(Uint32)} instead.
     */
    @Deprecated(forRemoval = true)
    public SubinterfaceKey(Long _index) {
        this(CodeHelpers.compatUint(_index));
    }
    
    /**
     * Creates a copy from Source Object.
     *
     * @param source Source object
     */
    public SubinterfaceKey(SubinterfaceKey source) {
        this._index = source._index;
    }


    public @NonNull Uint32 getIndex() {
        return _index;
    }


    @Override
    public int hashCode() {
        return CodeHelpers.wrapperHashCode(_index);
    }

    @Override
    public final boolean equals(java.lang.Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SubinterfaceKey)) {
            return false;
        }
        final SubinterfaceKey other = (SubinterfaceKey) obj;
        if (!Objects.equals(_index, other._index)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        final MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper(SubinterfaceKey.class);
        CodeHelpers.appendValue(helper, "_index", _index);
        return helper.toString();
    }
}

