package org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.gnmic.rev210719.tag.set.tags;
import com.google.common.base.MoreObjects;
import java.lang.Override;
import java.lang.String;
import java.util.Objects;
import javax.annotation.processing.Generated;
import org.eclipse.jdt.annotation.NonNull;
import org.opendaylight.yangtools.yang.binding.CodeHelpers;
import org.opendaylight.yangtools.yang.binding.Identifier;

@Generated("mdsal-binding-generator")
public class TagKey
 implements Identifier<Tag> {
    private static final long serialVersionUID = 11067374660552373L;
    private final String _name;


    public TagKey(@NonNull String _name) {
        this._name = CodeHelpers.requireKeyProp(_name, "name");
    }
    
    /**
     * Creates a copy from Source Object.
     *
     * @param source Source object
     */
    public TagKey(TagKey source) {
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
        if (!(obj instanceof TagKey)) {
            return false;
        }
        final TagKey other = (TagKey) obj;
        if (!Objects.equals(_name, other._name)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        final MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper(TagKey.class);
        CodeHelpers.appendValue(helper, "_name", _name);
        return helper.toString();
    }
}

