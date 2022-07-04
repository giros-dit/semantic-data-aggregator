package org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev130715;
import com.google.common.base.MoreObjects;
import java.io.Serializable;
import java.lang.Override;
import java.lang.String;
import java.util.Objects;
import javax.annotation.processing.Generated;
import org.opendaylight.yangtools.yang.binding.CodeHelpers;
import org.opendaylight.yangtools.yang.binding.TypeObject;

@Generated("mdsal-binding-generator")
public class IpPrefix
 implements TypeObject, Serializable {
    private static final long serialVersionUID = 776725407829840705L;
    private final Ipv4Prefix _ipv4Prefix;
    private final Ipv6Prefix _ipv6Prefix;


    public IpPrefix(Ipv4Prefix _ipv4Prefix) {
        super();
        this._ipv4Prefix = _ipv4Prefix;
        this._ipv6Prefix = null;
    }
    
    public IpPrefix(Ipv6Prefix _ipv6Prefix) {
        super();
        this._ipv6Prefix = _ipv6Prefix;
        this._ipv4Prefix = null;
    }
    /**
     * Creates a copy from Source Object.
     *
     * @param source Source object
     */
    public IpPrefix(IpPrefix source) {
        this._ipv4Prefix = source._ipv4Prefix;
        this._ipv6Prefix = source._ipv6Prefix;
    }
    
    /**
     * Return a String representing the value of this union.
     *
     * @return String representation of this union's value.
     */
    public String stringValue() {
        if (_ipv4Prefix != null) {
            return _ipv4Prefix.getValue().toString();
        }
        if (_ipv6Prefix != null) {
            return _ipv6Prefix.getValue().toString();
        }
    
        throw new IllegalStateException("No value assinged");
    }


    public Ipv4Prefix getIpv4Prefix() {
        return _ipv4Prefix;
    }
    
    public Ipv6Prefix getIpv6Prefix() {
        return _ipv6Prefix;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Objects.hashCode(_ipv4Prefix);
        result = prime * result + Objects.hashCode(_ipv6Prefix);
        return result;
    }

    @Override
    public final boolean equals(java.lang.Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof IpPrefix)) {
            return false;
        }
        final IpPrefix other = (IpPrefix) obj;
        if (!Objects.equals(_ipv4Prefix, other._ipv4Prefix)) {
            return false;
        }
        if (!Objects.equals(_ipv6Prefix, other._ipv6Prefix)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        final MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper(IpPrefix.class);
        CodeHelpers.appendValue(helper, "_ipv4Prefix", _ipv4Prefix);
        CodeHelpers.appendValue(helper, "_ipv6Prefix", _ipv6Prefix);
        return helper.toString();
    }
}

