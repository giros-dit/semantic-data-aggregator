package org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev210222;
import com.google.common.base.MoreObjects;
import java.io.Serializable;
import java.lang.Override;
import java.lang.String;
import java.util.Objects;
import javax.annotation.processing.Generated;
import org.opendaylight.yangtools.yang.binding.CodeHelpers;
import org.opendaylight.yangtools.yang.binding.TypeObject;

@Generated("mdsal-binding-generator")
public class IpAddressAndPrefix
 implements TypeObject, Serializable {
    private static final long serialVersionUID = -8446557592058704581L;
    private final Ipv4AddressAndPrefix _ipv4AddressAndPrefix;
    private final Ipv6AddressAndPrefix _ipv6AddressAndPrefix;


    public IpAddressAndPrefix(Ipv4AddressAndPrefix _ipv4AddressAndPrefix) {
        super();
        this._ipv4AddressAndPrefix = _ipv4AddressAndPrefix;
        this._ipv6AddressAndPrefix = null;
    }
    
    public IpAddressAndPrefix(Ipv6AddressAndPrefix _ipv6AddressAndPrefix) {
        super();
        this._ipv6AddressAndPrefix = _ipv6AddressAndPrefix;
        this._ipv4AddressAndPrefix = null;
    }
    /**
     * Creates a copy from Source Object.
     *
     * @param source Source object
     */
    public IpAddressAndPrefix(IpAddressAndPrefix source) {
        this._ipv4AddressAndPrefix = source._ipv4AddressAndPrefix;
        this._ipv6AddressAndPrefix = source._ipv6AddressAndPrefix;
    }
    
    /**
     * Return a String representing the value of this union.
     *
     * @return String representation of this union's value.
     */
    public String stringValue() {
        if (_ipv4AddressAndPrefix != null) {
            return _ipv4AddressAndPrefix.getValue().toString();
        }
        if (_ipv6AddressAndPrefix != null) {
            return _ipv6AddressAndPrefix.getValue().toString();
        }
    
        throw new IllegalStateException("No value assinged");
    }


    public Ipv4AddressAndPrefix getIpv4AddressAndPrefix() {
        return _ipv4AddressAndPrefix;
    }
    
    public Ipv6AddressAndPrefix getIpv6AddressAndPrefix() {
        return _ipv6AddressAndPrefix;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Objects.hashCode(_ipv4AddressAndPrefix);
        result = prime * result + Objects.hashCode(_ipv6AddressAndPrefix);
        return result;
    }

    @Override
    public final boolean equals(java.lang.Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof IpAddressAndPrefix)) {
            return false;
        }
        final IpAddressAndPrefix other = (IpAddressAndPrefix) obj;
        if (!Objects.equals(_ipv4AddressAndPrefix, other._ipv4AddressAndPrefix)) {
            return false;
        }
        if (!Objects.equals(_ipv6AddressAndPrefix, other._ipv6AddressAndPrefix)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        final MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper(IpAddressAndPrefix.class);
        CodeHelpers.appendValue(helper, "_ipv4AddressAndPrefix", _ipv4AddressAndPrefix);
        CodeHelpers.appendValue(helper, "_ipv6AddressAndPrefix", _ipv6AddressAndPrefix);
        return helper.toString();
    }
}

