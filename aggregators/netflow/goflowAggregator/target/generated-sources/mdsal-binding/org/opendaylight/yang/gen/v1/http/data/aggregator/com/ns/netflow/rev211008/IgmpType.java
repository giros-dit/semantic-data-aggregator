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
public enum IgmpType implements Enumeration {
    /**
     * IGMP Membership Query
     */
    MembershipQuery(17, "membership-query"),
    
    /**
     * IGMPv1 Membership Report
     */
    MembershipReportV1(18, "membership-report-v1"),
    
    /**
     * DVMRP
     */
    Dvmrp(19, "dvmrp"),
    
    /**
     * PIM version 1
     */
    PimV1(20, "pim-v1"),
    
    /**
     * Cisco Trace Messages
     */
    TraceMessages(21, "trace-messages"),
    
    /**
     * IGMPv2 Membership Report
     */
    MembershipReportV2(22, "membership-report-v2"),
    
    /**
     * IGMPv2 Leave Group
     */
    LeaveGroupV2(23, "leave-group-v2"),
    
    /**
     * Multicast Traceroute Response
     */
    MulTracerouteResp(30, "mul-traceroute-resp"),
    
    /**
     * Multicast Traceroute
     */
    MulTraceroute(31, "mul-traceroute"),
    
    /**
     * IGMPv3 Membership Report
     */
    MembershipReportV3(34, "membership-report-v3"),
    
    /**
     * Multicast Router Advertisement
     */
    MulRouterAdvert(48, "mul-router-advert"),
    
    /**
     * Multicast Router Solicitation
     */
    MulRouterSol(49, "mul-router-sol"),
    
    /**
     * Multicast Router Termination
     */
    MulRouterTerm(50, "mul-router-term")
    ;

    private static final Map<String, IgmpType> NAME_MAP;
    private static final Map<Integer, IgmpType> VALUE_MAP;

    static {
        final Builder<String, IgmpType> nb = ImmutableMap.builder();
        final Builder<Integer, IgmpType> vb = ImmutableMap.builder();
        for (IgmpType enumItem : IgmpType.values()) {
            vb.put(enumItem.value, enumItem);
            nb.put(enumItem.name, enumItem);
        }

        NAME_MAP = nb.build();
        VALUE_MAP = vb.build();
    }

    private final String name;
    private final int value;

    private IgmpType(int value, String name) {
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
     * @return corresponding IgmpType item, if present
     * @throws NullPointerException if name is null
     */
    public static Optional<IgmpType> forName(String name) {
        return Optional.ofNullable(NAME_MAP.get(Objects.requireNonNull(name)));
    }

    /**
     * Return the enumeration member whose {@link #getIntValue()} matches specified value.
     *
     * @param intValue integer value
     * @return corresponding IgmpType item, or null if no such item exists
     */
    public static IgmpType forValue(int intValue) {
        return VALUE_MAP.get(intValue);
    }
}
