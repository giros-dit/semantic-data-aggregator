package org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.netconf.with.defaults.rev110601;
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
public enum WithDefaultsMode implements Enumeration {
    /**
     * All default data is reported.
     */
    ReportAll(0, "report-all"),
    
    /**
     * All default data is reported.
     * Any nodes considered to be default data
     * will contain a 'default' XML attribute,
     * set to 'true' or '1'.
     */
    ReportAllTagged(1, "report-all-tagged"),
    
    /**
     * Values are not reported if they contain the default.
     */
    Trim(2, "trim"),
    
    /**
     * Report values that contain the definition of
     * explicitly set data.
     */
    Explicit(3, "explicit")
    ;

    private static final Map<String, WithDefaultsMode> NAME_MAP;
    private static final Map<Integer, WithDefaultsMode> VALUE_MAP;

    static {
        final Builder<String, WithDefaultsMode> nb = ImmutableMap.builder();
        final Builder<Integer, WithDefaultsMode> vb = ImmutableMap.builder();
        for (WithDefaultsMode enumItem : WithDefaultsMode.values()) {
            vb.put(enumItem.value, enumItem);
            nb.put(enumItem.name, enumItem);
        }

        NAME_MAP = nb.build();
        VALUE_MAP = vb.build();
    }

    private final String name;
    private final int value;

    private WithDefaultsMode(int value, String name) {
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
     * @return corresponding WithDefaultsMode item, if present
     * @throws NullPointerException if name is null
     */
    public static Optional<WithDefaultsMode> forName(String name) {
        return Optional.ofNullable(NAME_MAP.get(Objects.requireNonNull(name)));
    }

    /**
     * Return the enumeration member whose {@link #getIntValue()} matches specified value.
     *
     * @param intValue integer value
     * @return corresponding WithDefaultsMode item, or null if no such item exists
     */
    public static WithDefaultsMode forValue(int intValue) {
        return VALUE_MAP.get(intValue);
    }
}
