package org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev210222;
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
public class HostName extends DomainName
 implements Serializable {
    private static final long serialVersionUID = -8004882970763734437L;
    public static final List<String> PATTERN_CONSTANTS = ImmutableList.of("^(?:[a-zA-Z0-9\\-\\.]+)$");
    private static final Pattern patterns = Pattern.compile(PATTERN_CONSTANTS.get(0));
    private static final String regexes = "[a-zA-Z0-9\\-\\.]+";

    private static void check_valueLength(final String value) {
        final int length = value.codePointCount(0, value.length());
        if (length >= 2 && length <= 253) {
            return;
        }
        CodeHelpers.throwInvalidLength("[[2..253]]", value);
    }

    @ConstructorParameters("value")
    @ConstructorProperties("value")
    public HostName(String _value) {
        super(_value);
        if (_value != null) {
            check_valueLength(_value);
        }
        
        CodeHelpers.requireValue(_value);
        CodeHelpers.checkPattern(_value, patterns, regexes);
    
    }
    
    /**
     * Creates a copy from Source Object.
     *
     * @param source Source object
     */
    public HostName(HostName source) {
        super(source);
    }
    /**
     * Creates a new instance from DomainName
     *
     * @param source Source object
     */
    public HostName(DomainName source) {
        super(source);
        CodeHelpers.checkPattern(getValue(), patterns, regexes);
    }

    public static HostName getDefaultInstance(final String defaultValue) {
        return new HostName(defaultValue);
    }





}

