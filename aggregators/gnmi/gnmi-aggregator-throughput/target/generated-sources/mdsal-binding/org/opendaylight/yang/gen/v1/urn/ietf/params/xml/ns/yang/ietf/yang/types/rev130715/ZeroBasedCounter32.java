package org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715;
import java.beans.ConstructorProperties;
import java.io.Serializable;
import java.lang.Long;
import javax.annotation.processing.Generated;
import javax.management.ConstructorParameters;
import org.opendaylight.yangtools.yang.binding.CodeHelpers;
import org.opendaylight.yangtools.yang.common.Uint32;

@Generated("mdsal-binding-generator")
public class ZeroBasedCounter32 extends Counter32
 implements Serializable {
    private static final long serialVersionUID = 7512027574588325721L;


    @ConstructorParameters("value")
    @ConstructorProperties("value")
    public ZeroBasedCounter32(Uint32 _value) {
        super(_value);
        
        CodeHelpers.requireValue(_value);
    
    }
    
    /**
     * Utility migration constructor.
     *
     * @param _value value in legacy Java type
     * @deprecated Use {@link #ZeroBasedCounter32(Uint32)} instead.
     */
    @Deprecated(forRemoval = true)
    public ZeroBasedCounter32(Long _value) {
        this(CodeHelpers.compatUint(_value));
    }
    
    /**
     * Creates a copy from Source Object.
     *
     * @param source Source object
     */
    public ZeroBasedCounter32(ZeroBasedCounter32 source) {
        super(source);
    }
    /**
     * Creates a new instance from Counter32
     *
     * @param source Source object
     */
    public ZeroBasedCounter32(Counter32 source) {
        super(source);
    }

    public static ZeroBasedCounter32 getDefaultInstance(final String defaultValue) {
        return new ZeroBasedCounter32(Uint32.valueOf(defaultValue));
    }





}

