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
public enum IpVersionType implements Enumeration {
    /**
     * The IPv4 protocol as defined in RFC 791
     */
    Ipv4(4, "ipv4"),
    
    /**
     * The IPv6 protocol as defined in RFC 2460
     */
    Ipv6(6, "ipv6")
    ;

    private static final Map<String, IpVersionType> NAME_MAP;
    private static final Map<Integer, IpVersionType> VALUE_MAP;

    static {
        final Builder<String, IpVersionType> nb = ImmutableMap.builder();
        final Builder<Integer, IpVersionType> vb = ImmutableMap.builder();
        for (IpVersionType enumItem : IpVersionType.values()) {
            vb.put(enumItem.value, enumItem);
            nb.put(enumItem.name, enumItem);
        }

        NAME_MAP = nb.build();
        VALUE_MAP = vb.build();
    }

    private final String name;
    private final int value;

    private IpVersionType(int value, String name) {
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
     * @return corresponding IpVersionType item, if present
     * @throws NullPointerException if name is null
     */
    public static Optional<IpVersionType> forName(String name) {
        return Optional.ofNullable(NAME_MAP.get(Objects.requireNonNull(name)));
    }

    /**
     * Return the enumeration member whose {@link #getIntValue()} matches specified value.
     *
     * @param intValue integer value
     * @return corresponding IpVersionType item, or null if no such item exists
     */
    public static IpVersionType forValue(int intValue) {
        return VALUE_MAP.get(intValue);
    }
}
