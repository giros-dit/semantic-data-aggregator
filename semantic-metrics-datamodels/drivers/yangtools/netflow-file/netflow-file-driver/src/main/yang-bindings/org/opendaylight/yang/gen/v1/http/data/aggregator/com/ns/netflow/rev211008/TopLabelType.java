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
public enum TopLabelType implements Enumeration {
    /**
     * The MPLS label type is not known
     */
    Unknown(0, "unknown"),
    
    /**
     * Any TE tunnel mid-point or tail label
     */
    TeMidpt(1, "te-midpt"),
    
    /**
     * Cisco AToM based label
     */
    Atom(2, "atom"),
    
    /**
     * Any label associated with VPN
     */
    Vpn(3, "vpn"),
    
    /**
     * Any label associated with BGP or BGP routing
     */
    Bgp(4, "bgp"),
    
    /**
     * Any label associated with dynamically assigned labels using LDP
     */
    Ldp(5, "ldp")
    ;

    private static final Map<String, TopLabelType> NAME_MAP;
    private static final Map<Integer, TopLabelType> VALUE_MAP;

    static {
        final Builder<String, TopLabelType> nb = ImmutableMap.builder();
        final Builder<Integer, TopLabelType> vb = ImmutableMap.builder();
        for (TopLabelType enumItem : TopLabelType.values()) {
            vb.put(enumItem.value, enumItem);
            nb.put(enumItem.name, enumItem);
        }

        NAME_MAP = nb.build();
        VALUE_MAP = vb.build();
    }

    private final String name;
    private final int value;

    private TopLabelType(int value, String name) {
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
     * @return corresponding TopLabelType item, if present
     * @throws NullPointerException if name is null
     */
    public static Optional<TopLabelType> forName(String name) {
        return Optional.ofNullable(NAME_MAP.get(Objects.requireNonNull(name)));
    }

    /**
     * Return the enumeration member whose {@link #getIntValue()} matches specified value.
     *
     * @param intValue integer value
     * @return corresponding TopLabelType item, or null if no such item exists
     */
    public static TopLabelType forValue(int intValue) {
        return VALUE_MAP.get(intValue);
    }
}
