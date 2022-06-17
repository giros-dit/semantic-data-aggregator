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
public class IpAddressNoZone
 implements TypeObject, Serializable {
    private static final long serialVersionUID = 4932204310424522945L;
    private final Ipv4AddressNoZone _ipv4AddressNoZone;
    private final Ipv6AddressNoZone _ipv6AddressNoZone;


    public IpAddressNoZone(Ipv4AddressNoZone _ipv4AddressNoZone) {
        super();
        this._ipv4AddressNoZone = _ipv4AddressNoZone;
        this._ipv6AddressNoZone = null;
    }
    
    public IpAddressNoZone(Ipv6AddressNoZone _ipv6AddressNoZone) {
        super();
        this._ipv6AddressNoZone = _ipv6AddressNoZone;
        this._ipv4AddressNoZone = null;
    }
    /**
     * Creates a copy from Source Object.
     *
     * @param source Source object
     */
    public IpAddressNoZone(IpAddressNoZone source) {
        this._ipv4AddressNoZone = source._ipv4AddressNoZone;
        this._ipv6AddressNoZone = source._ipv6AddressNoZone;
    }
    
    /**
     * Return a String representing the value of this union.
     *
     * @return String representation of this union's value.
     */
    public String stringValue() {
        if (_ipv4AddressNoZone != null) {
            return _ipv4AddressNoZone.getValue().toString();
        }
        if (_ipv6AddressNoZone != null) {
            return _ipv6AddressNoZone.getValue().toString();
        }
    
        throw new IllegalStateException("No value assinged");
    }


    public Ipv4AddressNoZone getIpv4AddressNoZone() {
        return _ipv4AddressNoZone;
    }
    
    public Ipv6AddressNoZone getIpv6AddressNoZone() {
        return _ipv6AddressNoZone;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Objects.hashCode(_ipv4AddressNoZone);
        result = prime * result + Objects.hashCode(_ipv6AddressNoZone);
        return result;
    }

    @Override
    public final boolean equals(java.lang.Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof IpAddressNoZone)) {
            return false;
        }
        final IpAddressNoZone other = (IpAddressNoZone) obj;
        if (!Objects.equals(_ipv4AddressNoZone, other._ipv4AddressNoZone)) {
            return false;
        }
        if (!Objects.equals(_ipv6AddressNoZone, other._ipv6AddressNoZone)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        final MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper(IpAddressNoZone.class);
        CodeHelpers.appendValue(helper, "_ipv4AddressNoZone", _ipv4AddressNoZone);
        CodeHelpers.appendValue(helper, "_ipv6AddressNoZone", _ipv6AddressNoZone);
        return helper.toString();
    }
}

