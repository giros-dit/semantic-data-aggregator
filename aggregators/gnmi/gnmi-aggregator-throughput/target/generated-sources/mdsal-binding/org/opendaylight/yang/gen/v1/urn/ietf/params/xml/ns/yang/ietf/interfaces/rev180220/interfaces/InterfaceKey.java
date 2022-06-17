package org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.interfaces.rev180220.interfaces;
import com.google.common.base.MoreObjects;
import java.lang.Override;
import java.lang.String;
import java.util.Objects;
import javax.annotation.processing.Generated;
import org.eclipse.jdt.annotation.NonNull;
import org.opendaylight.yangtools.yang.binding.CodeHelpers;
import org.opendaylight.yangtools.yang.binding.Identifier;

@Generated("mdsal-binding-generator")
public class InterfaceKey
 implements Identifier<Interface> {
    private static final long serialVersionUID = 4501326395320375533L;
    private final String _name;


    public InterfaceKey(@NonNull String _name) {
        this._name = CodeHelpers.requireKeyProp(_name, "name");
    }
    
    /**
     * Creates a copy from Source Object.
     *
     * @param source Source object
     */
    public InterfaceKey(InterfaceKey source) {
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
        if (!(obj instanceof InterfaceKey)) {
            return false;
        }
        final InterfaceKey other = (InterfaceKey) obj;
        if (!Objects.equals(_name, other._name)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        final MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper(InterfaceKey.class);
        CodeHelpers.appendValue(helper, "_name", _name);
        return helper.toString();
    }
}

