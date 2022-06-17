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
public enum ProtocolType implements Enumeration {
    /**
     * The protocol used is ICMP (Internet Control Message Protocol)
     */
    Icmp(1, "icmp"),
    
    /**
     * The protocol used is IGMP (Internet Group Management Protocol)
     */
    Igmp(2, "igmp"),
    
    /**
     * The protocol used is TCP (Transmission Control Protocol)
     */
    Tcp(6, "tcp"),
    
    /**
     * The protocol used is EGP (Exterior Gateway Protocol)
     */
    Egp(8, "egp"),
    
    /**
     * The protocol used is IGP (Interior Gateway Protocol)
     */
    Igp(9, "igp"),
    
    /**
     * The protocol used is UDP (User Datagram Protocol)
     */
    Udp(17, "udp"),
    
    /**
     * The protocol used is IPv6-ICMP (ICMP for IPv6)
     */
    IcmpIpv6(58, "icmp-ipv6"),
    
    /**
     * The protocol used is PIM (Protocol Independent Multicast)
     */
    Pim(103, "pim"),
    
    /**
     * The protocol used is L2TP (Layer Two Tunneling Protocol)
     */
    L2tp(115, "l2tp"),
    
    /**
     * The protocol used is SCTP (Stream Control Transmission Protocol)
     */
    Sctp(132, "sctp"),
    
    /**
     * The protocol used is Ethernet
     */
    Eth(143, "eth")
    ;

    private static final Map<String, ProtocolType> NAME_MAP;
    private static final Map<Integer, ProtocolType> VALUE_MAP;

    static {
        final Builder<String, ProtocolType> nb = ImmutableMap.builder();
        final Builder<Integer, ProtocolType> vb = ImmutableMap.builder();
        for (ProtocolType enumItem : ProtocolType.values()) {
            vb.put(enumItem.value, enumItem);
            nb.put(enumItem.name, enumItem);
        }

        NAME_MAP = nb.build();
        VALUE_MAP = vb.build();
    }

    private final String name;
    private final int value;

    private ProtocolType(int value, String name) {
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
     * @return corresponding ProtocolType item, if present
     * @throws NullPointerException if name is null
     */
    public static Optional<ProtocolType> forName(String name) {
        return Optional.ofNullable(NAME_MAP.get(Objects.requireNonNull(name)));
    }

    /**
     * Return the enumeration member whose {@link #getIntValue()} matches specified value.
     *
     * @param intValue integer value
     * @return corresponding ProtocolType item, or null if no such item exists
     */
    public static ProtocolType forValue(int intValue) {
        return VALUE_MAP.get(intValue);
    }
}
