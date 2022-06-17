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
public class Host
 implements TypeObject, Serializable {
    private static final long serialVersionUID = -2403943164556299989L;
    private final IpAddress _ipAddress;
    private final DomainName _domainName;


    public Host(IpAddress _ipAddress) {
        super();
        this._ipAddress = _ipAddress;
        this._domainName = null;
    }
    
    public Host(DomainName _domainName) {
        super();
        this._domainName = _domainName;
        this._ipAddress = null;
    }
    /**
     * Creates a copy from Source Object.
     *
     * @param source Source object
     */
    public Host(Host source) {
        this._ipAddress = source._ipAddress;
        this._domainName = source._domainName;
    }
    
    /**
     * Return a String representing the value of this union.
     *
     * @return String representation of this union's value.
     */
    public String stringValue() {
        if (_ipAddress != null) {
            return _ipAddress.stringValue();
        }
        if (_domainName != null) {
            return _domainName.getValue().toString();
        }
    
        throw new IllegalStateException("No value assinged");
    }


    public IpAddress getIpAddress() {
        return _ipAddress;
    }
    
    public DomainName getDomainName() {
        return _domainName;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Objects.hashCode(_ipAddress);
        result = prime * result + Objects.hashCode(_domainName);
        return result;
    }

    @Override
    public final boolean equals(java.lang.Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Host)) {
            return false;
        }
        final Host other = (Host) obj;
        if (!Objects.equals(_ipAddress, other._ipAddress)) {
            return false;
        }
        if (!Objects.equals(_domainName, other._domainName)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        final MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper(Host.class);
        CodeHelpers.appendValue(helper, "_ipAddress", _ipAddress);
        CodeHelpers.appendValue(helper, "_domainName", _domainName);
        return helper.toString();
    }
}

