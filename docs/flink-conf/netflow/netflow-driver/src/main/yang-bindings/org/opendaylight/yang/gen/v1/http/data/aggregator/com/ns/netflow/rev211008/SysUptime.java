package org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008;
import com.google.common.base.MoreObjects;
import java.beans.ConstructorProperties;
import java.io.Serializable;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import javax.annotation.processing.Generated;
import javax.management.ConstructorParameters;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715.Timeticks;
import org.opendaylight.yangtools.yang.binding.CodeHelpers;
import org.opendaylight.yangtools.yang.common.Uint32;

@Generated("mdsal-binding-generator")
public class SysUptime extends Timeticks
 implements Serializable {
    private static final long serialVersionUID = 8502073119175313317L;
    public static final String _UNITS = "milliseconds";


    @ConstructorParameters("value")
    @ConstructorProperties("value")
    public SysUptime(Uint32 _value) {
        super(_value);
        
        CodeHelpers.requireValue(_value);
    
    }
    
    /**
     * Utility migration constructor.
     *
     * @param _value value in legacy Java type
     * @deprecated Use {@link #SysUptime(Uint32)} instead.
     */
    @Deprecated(forRemoval = true)
    public SysUptime(Long _value) {
        this(CodeHelpers.compatUint(_value));
    }
    
    /**
     * Creates a copy from Source Object.
     *
     * @param source Source object
     */
    public SysUptime(SysUptime source) {
        super(source);
    }
    /**
     * Creates a new instance from Timeticks
     *
     * @param source Source object
     */
    public SysUptime(Timeticks source) {
        super(source);
    }

    public static SysUptime getDefaultInstance(final String defaultValue) {
        return new SysUptime(Uint32.valueOf(defaultValue));
    }





    @Override
    public String toString() {
        final MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper(SysUptime.class);
        CodeHelpers.appendValue(helper, "_UNITS", _UNITS);
        return helper.toString();
    }
}

