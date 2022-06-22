package org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev130715;
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
public class Ipv6AddressNoZone extends Ipv6Address
 implements Serializable {
    private static final long serialVersionUID = -8132834319977195251L;
    public static final List<String> PATTERN_CONSTANTS = ImmutableList.of("^(?:[0-9a-fA-F:\\.]*)$");
    private static final Pattern patterns = Pattern.compile(PATTERN_CONSTANTS.get(0));
    private static final String regexes = "[0-9a-fA-F:\\.]*";


    @ConstructorParameters("value")
    @ConstructorProperties("value")
    public Ipv6AddressNoZone(String _value) {
        super(_value);
        
        CodeHelpers.requireValue(_value);
        CodeHelpers.checkPattern(_value, patterns, regexes);
    
    }
    
    /**
     * Creates a copy from Source Object.
     *
     * @param source Source object
     */
    public Ipv6AddressNoZone(Ipv6AddressNoZone source) {
        super(source);
    }
    /**
     * Creates a new instance from Ipv6Address
     *
     * @param source Source object
     */
    public Ipv6AddressNoZone(Ipv6Address source) {
        super(source);
        CodeHelpers.checkPattern(getValue(), patterns, regexes);
    }

    public static Ipv6AddressNoZone getDefaultInstance(final String defaultValue) {
        return new Ipv6AddressNoZone(defaultValue);
    }





}

