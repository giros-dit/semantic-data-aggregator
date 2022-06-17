package org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715;
import java.beans.ConstructorProperties;
import java.io.Serializable;
import java.lang.Long;
import javax.annotation.processing.Generated;
import javax.management.ConstructorParameters;
import org.opendaylight.yangtools.yang.binding.CodeHelpers;
import org.opendaylight.yangtools.yang.common.Uint32;

@Generated("mdsal-binding-generator")
public class Timestamp extends Timeticks
 implements Serializable {
    private static final long serialVersionUID = -1618618198504997448L;


    @ConstructorParameters("value")
    @ConstructorProperties("value")
    public Timestamp(Uint32 _value) {
        super(_value);
        
        CodeHelpers.requireValue(_value);
    
    }
    
    /**
     * Utility migration constructor.
     *
     * @param _value value in legacy Java type
     * @deprecated Use {@link #Timestamp(Uint32)} instead.
     */
    @Deprecated(forRemoval = true)
    public Timestamp(Long _value) {
        this(CodeHelpers.compatUint(_value));
    }
    
    /**
     * Creates a copy from Source Object.
     *
     * @param source Source object
     */
    public Timestamp(Timestamp source) {
        super(source);
    }
    /**
     * Creates a new instance from Timeticks
     *
     * @param source Source object
     */
    public Timestamp(Timeticks source) {
        super(source);
    }

    public static Timestamp getDefaultInstance(final String defaultValue) {
        return new Timestamp(Uint32.valueOf(defaultValue));
    }





}

