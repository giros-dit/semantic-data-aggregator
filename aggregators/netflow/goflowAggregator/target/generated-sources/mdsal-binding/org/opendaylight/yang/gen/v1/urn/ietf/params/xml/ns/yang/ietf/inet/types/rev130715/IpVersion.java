package org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev130715;
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
public enum IpVersion implements Enumeration {
    /**
     * An unknown or unspecified version of the Internet
     * protocol.
     */
    Unknown(0, "unknown"),
    
    /**
     * The IPv4 protocol as defined in RFC 791.
     */
    Ipv4(1, "ipv4"),
    
    /**
     * The IPv6 protocol as defined in RFC 2460.
     */
    Ipv6(2, "ipv6")
    ;

    private static final Map<String, IpVersion> NAME_MAP;
    private static final Map<Integer, IpVersion> VALUE_MAP;

    static {
        final Builder<String, IpVersion> nb = ImmutableMap.builder();
        final Builder<Integer, IpVersion> vb = ImmutableMap.builder();
        for (IpVersion enumItem : IpVersion.values()) {
            vb.put(enumItem.value, enumItem);
            nb.put(enumItem.name, enumItem);
        }

        NAME_MAP = nb.build();
        VALUE_MAP = vb.build();
    }

    private final String name;
    private final int value;

    private IpVersion(int value, String name) {
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
     * @return corresponding IpVersion item, if present
     * @throws NullPointerException if name is null
     */
    public static Optional<IpVersion> forName(String name) {
        return Optional.ofNullable(NAME_MAP.get(Objects.requireNonNull(name)));
    }

    /**
     * Return the enumeration member whose {@link #getIntValue()} matches specified value.
     *
     * @param intValue integer value
     * @return corresponding IpVersion item, or null if no such item exists
     */
    public static IpVersion forValue(int intValue) {
        return VALUE_MAP.get(intValue);
    }
}
