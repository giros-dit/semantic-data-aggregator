package org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008;
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
public enum SamplingModeType implements Enumeration {
    /**
     * Deterministic Sampling is used
     */
    Deterministic(1, "deterministic"),
    
    /**
     * Random Sampling is used
     */
    Random(2, "random")
    ;

    private static final Map<String, SamplingModeType> NAME_MAP;
    private static final Map<Integer, SamplingModeType> VALUE_MAP;

    static {
        final Builder<String, SamplingModeType> nb = ImmutableMap.builder();
        final Builder<Integer, SamplingModeType> vb = ImmutableMap.builder();
        for (SamplingModeType enumItem : SamplingModeType.values()) {
            vb.put(enumItem.value, enumItem);
            nb.put(enumItem.name, enumItem);
        }

        NAME_MAP = nb.build();
        VALUE_MAP = vb.build();
    }

    private final String name;
    private final int value;

    private SamplingModeType(int value, String name) {
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
     * @return corresponding SamplingModeType item, if present
     * @throws NullPointerException if name is null
     */
    public static Optional<SamplingModeType> forName(String name) {
        return Optional.ofNullable(NAME_MAP.get(Objects.requireNonNull(name)));
    }

    /**
     * Return the enumeration member whose {@link #getIntValue()} matches specified value.
     *
     * @param intValue integer value
     * @return corresponding SamplingModeType item, or null if no such item exists
     */
    public static SamplingModeType forValue(int intValue) {
        return VALUE_MAP.get(intValue);
    }
}
