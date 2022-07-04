package org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715;
import com.google.common.collect.ImmutableList;
import java.beans.ConstructorProperties;
import java.io.Serializable;
import java.lang.String;
import java.util.List;
import java.util.regex.Pattern;
import javax.annotation.processing.Generated;
import javax.management.ConstructorParameters;
import org.opendaylight.yangtools.yang.binding.CodeHelpers;

@Generated("mdsal-binding-generator")
public class ObjectIdentifier128 extends ObjectIdentifier
 implements Serializable {
    private static final long serialVersionUID = 1412134080859977896L;
    public static final List<String> PATTERN_CONSTANTS = ImmutableList.of("^(?:\\d*(\\.\\d*){1,127})$");
    private static final Pattern patterns = Pattern.compile(PATTERN_CONSTANTS.get(0));
    private static final String regexes = "\\d*(\\.\\d*){1,127}";


    @ConstructorParameters("value")
    @ConstructorProperties("value")
    public ObjectIdentifier128(String _value) {
        super(_value);
        
        CodeHelpers.requireValue(_value);
        CodeHelpers.checkPattern(_value, patterns, regexes);
    
    }
    
    /**
     * Creates a copy from Source Object.
     *
     * @param source Source object
     */
    public ObjectIdentifier128(ObjectIdentifier128 source) {
        super(source);
    }
    /**
     * Creates a new instance from ObjectIdentifier
     *
     * @param source Source object
     */
    public ObjectIdentifier128(ObjectIdentifier source) {
        super(source);
        CodeHelpers.checkPattern(getValue(), patterns, regexes);
    }

    public static ObjectIdentifier128 getDefaultInstance(final String defaultValue) {
        return new ObjectIdentifier128(defaultValue);
    }





}

