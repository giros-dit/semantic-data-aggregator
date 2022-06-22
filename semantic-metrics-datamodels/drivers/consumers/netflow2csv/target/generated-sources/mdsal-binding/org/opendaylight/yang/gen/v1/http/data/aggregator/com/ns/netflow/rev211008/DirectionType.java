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
public enum DirectionType implements Enumeration {
    /**
     * Ingress flow
     */
    Ingress(0, "ingress"),
    
    /**
     * Egress flow
     */
    Egress(1, "egress")
    ;

    private static final Map<String, DirectionType> NAME_MAP;
    private static final Map<Integer, DirectionType> VALUE_MAP;

    static {
        final Builder<String, DirectionType> nb = ImmutableMap.builder();
        final Builder<Integer, DirectionType> vb = ImmutableMap.builder();
        for (DirectionType enumItem : DirectionType.values()) {
            vb.put(enumItem.value, enumItem);
            nb.put(enumItem.name, enumItem);
        }

        NAME_MAP = nb.build();
        VALUE_MAP = vb.build();
    }

    private final String name;
    private final int value;

    private DirectionType(int value, String name) {
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
     * @return corresponding DirectionType item, if present
     * @throws NullPointerException if name is null
     */
    public static Optional<DirectionType> forName(String name) {
        return Optional.ofNullable(NAME_MAP.get(Objects.requireNonNull(name)));
    }

    /**
     * Return the enumeration member whose {@link #getIntValue()} matches specified value.
     *
     * @param intValue integer value
     * @return corresponding DirectionType item, or null if no such item exists
     */
    public static DirectionType forValue(int intValue) {
        return VALUE_MAP.get(intValue);
    }
}
