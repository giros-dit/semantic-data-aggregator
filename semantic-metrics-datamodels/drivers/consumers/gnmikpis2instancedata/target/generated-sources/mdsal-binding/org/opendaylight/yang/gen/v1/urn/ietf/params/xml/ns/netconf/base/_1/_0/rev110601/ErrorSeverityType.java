package org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.netconf.base._1._0.rev110601;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;
import java.lang.Integer;
import java.lang.Override;
import java.lang.String;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import javax.annotation.processing.Generated;
import org.opendaylight.yangtools.yang.binding.Enumeration;

@Generated("mdsal-binding-generator")
public enum ErrorSeverityType implements Enumeration {
    /**
     * Error severity
     */
    Error(0, "error"),
    
    /**
     * Warning severity
     */
    Warning(1, "warning")
    ;

    private static final Map<String, ErrorSeverityType> NAME_MAP;
    private static final Map<Integer, ErrorSeverityType> VALUE_MAP;

    static {
        final Builder<String, ErrorSeverityType> nb = ImmutableMap.builder();
        final Builder<Integer, ErrorSeverityType> vb = ImmutableMap.builder();
        for (ErrorSeverityType enumItem : ErrorSeverityType.values()) {
            vb.put(enumItem.value, enumItem);
            nb.put(enumItem.name, enumItem);
        }

        NAME_MAP = nb.build();
        VALUE_MAP = vb.build();
    }

    private final String name;
    private final int value;

    private ErrorSeverityType(int value, String name) {
        this.value = value;
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getIntValue() {
        return value;
    }

    /**
     * Return the enumeration member whose {@link #getName()} matches specified value.
     *
     * @param name YANG assigned name
     * @return corresponding ErrorSeverityType item, if present
     * @throws NullPointerException if name is null
     */
    public static Optional<ErrorSeverityType> forName(String name) {
        return Optional.ofNullable(NAME_MAP.get(Objects.requireNonNull(name)));
    }

    /**
     * Return the enumeration member whose {@link #getIntValue()} matches specified value.
     *
     * @param intValue integer value
     * @return corresponding ErrorSeverityType item, or null if no such item exists
     */
    public static ErrorSeverityType forValue(int intValue) {
        return VALUE_MAP.get(intValue);
    }
}
