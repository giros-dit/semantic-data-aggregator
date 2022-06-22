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
public enum ForwardingStatusType implements Enumeration {
    /**
     * Status bits are 00. Unknown
     */
    Unknown(0, "unknown"),
    
    /**
     * Status bits are 01 (Forwarded). Unknown
     */
    UnknownForwarded(64, "unknown-forwarded"),
    
    /**
     * Status bits are 01 (Forwarded). Forwarded Fragmented
     */
    ForwardedFrag(65, "forwarded-frag"),
    
    /**
     * Status bits are 01 (Forwarded). Forwarded not Fragmented
     */
    ForwardedNotFrag(66, "forwarded-not-frag"),
    
    /**
     * Status bits are 10 (Dropped). Unknown
     */
    UnknownDropped(128, "unknown-dropped"),
    
    /**
     * Status bits are 10 (Dropped). Drop ACL Deny
     */
    AclDeny(129, "acl-deny"),
    
    /**
     * Status bits are 10 (Dropped). Drop ACL drop
     */
    AclDrop(130, "acl-drop"),
    
    /**
     * Status bits are 10 (Dropped). Drop Unroutable
     */
    Unroutable(131, "unroutable"),
    
    /**
     * Status bits are 10 (Dropped). Drop Adjacency
     */
    Adjacency(132, "adjacency"),
    
    /**
     * Status bits are 10 (Dropped). Drop Fragmentation &amp; DF set
     */
    FragmentDf(133, "fragment-df"),
    
    /**
     * Status bits are 10 (Dropped). Drop Bad header checksum
     */
    BadHeaderCheck(134, "bad-header-check"),
    
    /**
     * Status bits are 10 (Dropped). Drop Bad total Length
     */
    BadTotLen(135, "bad-tot-len"),
    
    /**
     * Status bits are 10 (Dropped). Drop Bad Header Length
     */
    BadHeaderLen(136, "bad-header-len"),
    
    /**
     * Status bits are 10 (Dropped). Drop bad TTL
     */
    BadTtl(137, "bad-ttl"),
    
    /**
     * Status bits are 10 (Dropped). Drop Policer
     */
    Policer(138, "policer"),
    
    /**
     * Status bits are 10 (Dropped). Drop WRED
     */
    Wred(139, "wred"),
    
    /**
     * Status bits are 10 (Dropped). Drop RPF
     */
    Rpf(140, "rpf"),
    
    /**
     * Status bits are 10 (Dropped). Drop For us
     */
    ForUs(141, "for-us"),
    
    /**
     * Status bits are 10 (Dropped). Drop Bad output interface
     */
    BadOutInterf(142, "bad-out-interf"),
    
    /**
     * Status bits are 10 (Dropped). Drop Hardware
     */
    Hardware(143, "hardware"),
    
    /**
     * Status bits are 11 (Consumed). Unknown
     */
    UnknownConsumed(192, "unknown-consumed"),
    
    /**
     * Status bits are 11 (Consumed). Terminate Punt Adjacency
     */
    TermPuntAdjacency(193, "term-punt-adjacency"),
    
    /**
     * Status bits are 11 (Consumed). Terminate Incomplete Adjacency
     */
    TermIncompAdjacency(194, "term-incomp-adjacency"),
    
    /**
     * Status bits are 11 (Consumed). Terminate For us
     */
    TermForUs(195, "term-for-us")
    ;

    private static final Map<String, ForwardingStatusType> NAME_MAP;
    private static final Map<Integer, ForwardingStatusType> VALUE_MAP;

    static {
        final Builder<String, ForwardingStatusType> nb = ImmutableMap.builder();
        final Builder<Integer, ForwardingStatusType> vb = ImmutableMap.builder();
        for (ForwardingStatusType enumItem : ForwardingStatusType.values()) {
            vb.put(enumItem.value, enumItem);
            nb.put(enumItem.name, enumItem);
        }

        NAME_MAP = nb.build();
        VALUE_MAP = vb.build();
    }

    private final String name;
    private final int value;

    private ForwardingStatusType(int value, String name) {
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
     * @return corresponding ForwardingStatusType item, if present
     * @throws NullPointerException if name is null
     */
    public static Optional<ForwardingStatusType> forName(String name) {
        return Optional.ofNullable(NAME_MAP.get(Objects.requireNonNull(name)));
    }

    /**
     * Return the enumeration member whose {@link #getIntValue()} matches specified value.
     *
     * @param intValue integer value
     * @return corresponding ForwardingStatusType item, or null if no such item exists
     */
    public static ForwardingStatusType forValue(int intValue) {
        return VALUE_MAP.get(intValue);
    }
}
