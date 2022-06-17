package org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008;
import com.google.common.base.MoreObjects;
import com.google.common.collect.Lists;
import java.io.Serializable;
import java.lang.Boolean;
import java.lang.Deprecated;
import java.lang.IllegalArgumentException;
import java.lang.Override;
import java.lang.String;
import java.util.List;
import java.util.Objects;
import javax.annotation.processing.Generated;
import org.opendaylight.yangtools.yang.binding.CodeHelpers;
import org.opendaylight.yangtools.yang.binding.TypeObject;

@Generated("mdsal-binding-generator")
public class TcpFlagsType
 implements TypeObject, Serializable {
    private static final long serialVersionUID = 8177359985728102221L;
    private final Boolean _fin;
    private final Boolean _syn;
    private final Boolean _rst;
    private final Boolean _psh;
    private final Boolean _ack;
    private final Boolean _urg;
    private final Boolean _ece;
    private final Boolean _cwr;


    public TcpFlagsType(Boolean _ack, Boolean _cwr, Boolean _ece, Boolean _fin, Boolean _psh, Boolean _rst, Boolean _syn, Boolean _urg) {
    
        this._fin = _fin;
        this._syn = _syn;
        this._rst = _rst;
        this._psh = _psh;
        this._ack = _ack;
        this._urg = _urg;
        this._ece = _ece;
        this._cwr = _cwr;
    }
    
    /**
     * Creates a copy from Source Object.
     *
     * @param source Source object
     */
    public TcpFlagsType(TcpFlagsType source) {
        this._fin = source._fin;
        this._syn = source._syn;
        this._rst = source._rst;
        this._psh = source._psh;
        this._ack = source._ack;
        this._urg = source._urg;
        this._ece = source._ece;
        this._cwr = source._cwr;
    }

    public static TcpFlagsType getDefaultInstance(final String defaultValue) {
        List<String> properties = Lists.newArrayList("ack",
        "cwr",
        "ece",
        "fin",
        "psh",
        "rst",
        "syn",
        "urg"
        );
        if (!properties.contains(defaultValue)) {
            throw new IllegalArgumentException("invalid default parameter");
        }
        int i = 0;
        return new TcpFlagsType(
        properties.get(i++).equals(defaultValue) ? Boolean.TRUE : null,
        properties.get(i++).equals(defaultValue) ? Boolean.TRUE : null,
        properties.get(i++).equals(defaultValue) ? Boolean.TRUE : null,
        properties.get(i++).equals(defaultValue) ? Boolean.TRUE : null,
        properties.get(i++).equals(defaultValue) ? Boolean.TRUE : null,
        properties.get(i++).equals(defaultValue) ? Boolean.TRUE : null,
        properties.get(i++).equals(defaultValue) ? Boolean.TRUE : null,
        properties.get(i++).equals(defaultValue) ? Boolean.TRUE : null
        );
    }

    public Boolean getFin() {
        return _fin;
    }
    
    @Deprecated(forRemoval = true)
    public final Boolean isFin() {
        return getFin();
    }
    
    public Boolean getSyn() {
        return _syn;
    }
    
    @Deprecated(forRemoval = true)
    public final Boolean isSyn() {
        return getSyn();
    }
    
    public Boolean getRst() {
        return _rst;
    }
    
    @Deprecated(forRemoval = true)
    public final Boolean isRst() {
        return getRst();
    }
    
    public Boolean getPsh() {
        return _psh;
    }
    
    @Deprecated(forRemoval = true)
    public final Boolean isPsh() {
        return getPsh();
    }
    
    public Boolean getAck() {
        return _ack;
    }
    
    @Deprecated(forRemoval = true)
    public final Boolean isAck() {
        return getAck();
    }
    
    public Boolean getUrg() {
        return _urg;
    }
    
    @Deprecated(forRemoval = true)
    public final Boolean isUrg() {
        return getUrg();
    }
    
    public Boolean getEce() {
        return _ece;
    }
    
    @Deprecated(forRemoval = true)
    public final Boolean isEce() {
        return getEce();
    }
    
    public Boolean getCwr() {
        return _cwr;
    }
    
    @Deprecated(forRemoval = true)
    public final Boolean isCwr() {
        return getCwr();
    }

    
    public boolean[] getValue() {
        return new boolean[]{
        _fin,
        _syn,
        _rst,
        _psh,
        _ack,
        _urg,
        _ece,
        _cwr
        };
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Objects.hashCode(_fin);
        result = prime * result + Objects.hashCode(_syn);
        result = prime * result + Objects.hashCode(_rst);
        result = prime * result + Objects.hashCode(_psh);
        result = prime * result + Objects.hashCode(_ack);
        result = prime * result + Objects.hashCode(_urg);
        result = prime * result + Objects.hashCode(_ece);
        result = prime * result + Objects.hashCode(_cwr);
        return result;
    }

    @Override
    public final boolean equals(java.lang.Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TcpFlagsType)) {
            return false;
        }
        final TcpFlagsType other = (TcpFlagsType) obj;
        if (!Objects.equals(_fin, other._fin)) {
            return false;
        }
        if (!Objects.equals(_syn, other._syn)) {
            return false;
        }
        if (!Objects.equals(_rst, other._rst)) {
            return false;
        }
        if (!Objects.equals(_psh, other._psh)) {
            return false;
        }
        if (!Objects.equals(_ack, other._ack)) {
            return false;
        }
        if (!Objects.equals(_urg, other._urg)) {
            return false;
        }
        if (!Objects.equals(_ece, other._ece)) {
            return false;
        }
        if (!Objects.equals(_cwr, other._cwr)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        final MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper(TcpFlagsType.class);
        CodeHelpers.appendValue(helper, "_fin", _fin);
        CodeHelpers.appendValue(helper, "_syn", _syn);
        CodeHelpers.appendValue(helper, "_rst", _rst);
        CodeHelpers.appendValue(helper, "_psh", _psh);
        CodeHelpers.appendValue(helper, "_ack", _ack);
        CodeHelpers.appendValue(helper, "_urg", _urg);
        CodeHelpers.appendValue(helper, "_ece", _ece);
        CodeHelpers.appendValue(helper, "_cwr", _cwr);
        return helper.toString();
    }
}

