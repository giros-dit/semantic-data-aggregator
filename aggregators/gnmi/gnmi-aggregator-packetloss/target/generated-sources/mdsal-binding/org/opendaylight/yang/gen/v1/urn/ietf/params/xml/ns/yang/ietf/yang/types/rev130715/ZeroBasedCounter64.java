package org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715;
import java.beans.ConstructorProperties;
import java.io.Serializable;
import java.math.BigInteger;
import javax.annotation.processing.Generated;
import javax.management.ConstructorParameters;
import org.opendaylight.yangtools.yang.binding.CodeHelpers;
import org.opendaylight.yangtools.yang.common.Uint64;

@Generated("mdsal-binding-generator")
public class ZeroBasedCounter64 extends Counter64
 implements Serializable {
    private static final long serialVersionUID = -4163687090528367476L;


    @ConstructorParameters("value")
    @ConstructorProperties("value")
    public ZeroBasedCounter64(Uint64 _value) {
        super(_value);
        
        CodeHelpers.requireValue(_value);
    
    }
    
    /**
     * Utility migration constructor.
     *
     * @param _value value in legacy Java type
     * @deprecated Use {@link #ZeroBasedCounter64(Uint64)} instead.
     */
    @Deprecated(forRemoval = true)
    public ZeroBasedCounter64(BigInteger _value) {
        this(CodeHelpers.compatUint(_value));
    }
    
    /**
     * Creates a copy from Source Object.
     *
     * @param source Source object
     */
    public ZeroBasedCounter64(ZeroBasedCounter64 source) {
        super(source);
    }
    /**
     * Creates a new instance from Counter64
     *
     * @param source Source object
     */
    public ZeroBasedCounter64(Counter64 source) {
        super(source);
    }

    public static ZeroBasedCounter64 getDefaultInstance(final String defaultValue) {
        return new ZeroBasedCounter64(Uint64.valueOf(defaultValue));
    }





}

