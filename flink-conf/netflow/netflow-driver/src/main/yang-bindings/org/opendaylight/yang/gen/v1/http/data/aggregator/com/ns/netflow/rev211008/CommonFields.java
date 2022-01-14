package org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;
import java.lang.Boolean;
import java.lang.Class;
import java.lang.Deprecated;
import java.lang.Integer;
import java.lang.Override;
import java.lang.String;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import javax.annotation.processing.Generated;
import org.eclipse.jdt.annotation.NonNull;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev130715.AsNumber;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev130715.Ipv4Address;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev130715.Ipv6Address;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev130715.PortNumber;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715.Counter64;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715.MacAddress;
import org.opendaylight.yangtools.yang.binding.CodeHelpers;
import org.opendaylight.yangtools.yang.binding.DataObject;
import org.opendaylight.yangtools.yang.binding.Enumeration;
import org.opendaylight.yangtools.yang.binding.TypeObject;
import org.opendaylight.yangtools.yang.common.QName;
import org.opendaylight.yangtools.yang.common.Uint16;
import org.opendaylight.yangtools.yang.common.Uint32;
import org.opendaylight.yangtools.yang.common.Uint8;

/**
 * Fields of a Netflow flow record version 9. Field's names and semantics 
 * correspond to the managed objects in RFC 3954, Section 8.
 *
 * <p>
 * This class represents the following YANG schema fragment defined in module <b>netflow-v9</b>
 * <pre>
 * grouping common-fields {
 *   leaf bytes-in {
 *     type yang:counter64;
 *     units bytes;
 *   }
 *   leaf bytes-out {
 *     type yang:counter64;
 *     units bytes;
 *   }
 *   leaf bytes-mul {
 *     type yang:counter64;
 *     units bytes;
 *   }
 *   leaf pkts-in {
 *     type yang:counter64;
 *     units packets;
 *   }
 *   leaf pkts-out {
 *     type yang:counter64;
 *     units packets;
 *   }
 *   leaf pkts-mul {
 *     type yang:counter64;
 *     units packets;
 *   }
 *   leaf first-switched {
 *     type sys-uptime;
 *   }
 *   leaf last-switched {
 *     type sys-uptime;
 *   }
 *   leaf flows {
 *     type yang:counter64;
 *     units flows;
 *   }
 *   leaf direction {
 *     type enumeration {
 *       enum ingress {
 *         value 0;
 *       }
 *       enum egress {
 *         value 1;
 *       }
 *     }
 *   }
 *   leaf ip-version {
 *     type enumeration {
 *       enum ipv4 {
 *         value 4;
 *       }
 *       enum ipv6 {
 *         value 6;
 *       }
 *     }
 *   }
 *   leaf protocol {
 *     type enumeration {
 *       enum icmp {
 *         value 1;
 *       }
 *       enum tcp {
 *         value 6;
 *       }
 *       enum udp {
 *         value 17;
 *       }
 *     }
 *   }
 *   leaf src-tos {
 *     type uint8;
 *   }
 *   leaf dst-tos {
 *     type uint8;
 *   }
 *   leaf tcp-flags {
 *     type bits {
 *       bit fin {
 *         position 0;
 *       }
 *       bit syn {
 *         position 1;
 *       }
 *       bit rst {
 *         position 2;
 *       }
 *       bit psh {
 *         position 3;
 *       }
 *       bit ack {
 *         position 4;
 *       }
 *       bit urg {
 *         position 5;
 *       }
 *       bit ece {
 *         position 6;
 *       }
 *       bit cwr {
 *         position 7;
 *       }
 *     }
 *   }
 *   leaf src-port {
 *     type inet:port-number;
 *   }
 *   leaf dst-port {
 *     type inet:port-number;
 *   }
 *   leaf src-address {
 *     type inet:ipv4-address;
 *   }
 *   leaf dst-address {
 *     type inet:ipv4-address;
 *   }
 *   leaf next-hop {
 *     type inet:ipv4-address;
 *   }
 *   leaf src-address-ipv6 {
 *     type inet:ipv6-address;
 *   }
 *   leaf dst-address-ipv6 {
 *     type inet:ipv6-address;
 *   }
 *   leaf next-hop-ipv6 {
 *     type inet:ipv6-address;
 *   }
 *   leaf src-mask {
 *     type prefix-length-ipv4;
 *   }
 *   leaf dst-mask {
 *     type prefix-length-ipv4;
 *   }
 *   leaf src-mask-ipv6 {
 *     type prefix-length-ipv6;
 *   }
 *   leaf dst-mask-ipv6 {
 *     type prefix-length-ipv6;
 *   }
 *   leaf src-mac {
 *     type yang:mac-address;
 *   }
 *   leaf dst-mac {
 *     type yang:mac-address;
 *   }
 *   leaf flow-label-ipv6 {
 *     type ipv6-flow-label;
 *   }
 *   leaf opt-headers-ipv6 {
 *     type uint32;
 *   }
 *   leaf snmp-in {
 *     type int32 {
 *       range 1..2147483647;
 *     }
 *   }
 *   leaf snmp-out {
 *     type int32 {
 *       range 1..2147483647;
 *     }
 *   }
 *   leaf src-as {
 *     type inet:as-number;
 *   }
 *   leaf dst-as {
 *     type inet:as-number;
 *   }
 *   leaf bgp-next-hop {
 *     type inet:ipv4-address;
 *   }
 *   leaf bgp-next-hop-ipv6 {
 *     type inet:ipv6-address;
 *   }
 *   leaf src-vlan {
 *     type vlan-id;
 *   }
 *   leaf dst-vlan {
 *     type vlan-id;
 *   }
 *   leaf icmp-type {
 *     type uint16;
 *   }
 *   leaf igmp-type {
 *     type uint8;
 *   }
 *   leaf tot-bytes-exp {
 *     type yang:counter64;
 *     units bytes;
 *   }
 *   leaf tot-pkts-exp {
 *     type yang:counter64;
 *     units packets;
 *   }
 *   leaf tot-flows-exp {
 *     type yang:counter64;
 *     units flows;
 *   }
 * }
 * </pre>The schema path to identify an instance is
 * <i>netflow-v9/common-fields</i>
 *
 */
@Generated("mdsal-binding-generator")
public interface CommonFields
    extends
    DataObject
{

    public static final class TcpFlags
     implements TypeObject {
        private final Boolean _fin;
        private final Boolean _syn;
        private final Boolean _rst;
        private final Boolean _psh;
        private final Boolean _ack;
        private final Boolean _urg;
        private final Boolean _ece;
        private final Boolean _cwr;
    
    
        public TcpFlags(Boolean _ack, Boolean _cwr, Boolean _ece, Boolean _fin, Boolean _psh, Boolean _rst, Boolean _syn, Boolean _urg) {
        
            this._fin = _fin;
            this._syn = _syn;
            this._rst = _rst;
            this._psh = _psh;
            this._ack = _ack;
            this._urg = _urg;
            this._ece = _ece;
            this._cwr = _cwr;
        }
        
        /**
         * Creates a copy from Source Object.
         *
         * @param source Source object
         */
        public TcpFlags(TcpFlags source) {
            this._fin = source._fin;
            this._syn = source._syn;
            this._rst = source._rst;
            this._psh = source._psh;
            this._ack = source._ack;
            this._urg = source._urg;
            this._ece = source._ece;
            this._cwr = source._cwr;
        }
    
    
        public Boolean getFin() {
            return _fin;
        }
        
        @Deprecated(forRemoval = true)
        public final Boolean isFin() {
            return getFin();
        }
        
        public Boolean getSyn() {
            return _syn;
        }
        
        @Deprecated(forRemoval = true)
        public final Boolean isSyn() {
            return getSyn();
        }
        
        public Boolean getRst() {
            return _rst;
        }
        
        @Deprecated(forRemoval = true)
        public final Boolean isRst() {
            return getRst();
        }
        
        public Boolean getPsh() {
            return _psh;
        }
        
        @Deprecated(forRemoval = true)
        public final Boolean isPsh() {
            return getPsh();
        }
        
        public Boolean getAck() {
            return _ack;
        }
        
        @Deprecated(forRemoval = true)
        public final Boolean isAck() {
            return getAck();
        }
        
        public Boolean getUrg() {
            return _urg;
        }
        
        @Deprecated(forRemoval = true)
        public final Boolean isUrg() {
            return getUrg();
        }
        
        public Boolean getEce() {
            return _ece;
        }
        
        @Deprecated(forRemoval = true)
        public final Boolean isEce() {
            return getEce();
        }
        
        public Boolean getCwr() {
            return _cwr;
        }
        
        @Deprecated(forRemoval = true)
        public final Boolean isCwr() {
            return getCwr();
        }
    
    
        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + Objects.hashCode(_fin);
            result = prime * result + Objects.hashCode(_syn);
            result = prime * result + Objects.hashCode(_rst);
            result = prime * result + Objects.hashCode(_psh);
            result = prime * result + Objects.hashCode(_ack);
            result = prime * result + Objects.hashCode(_urg);
            result = prime * result + Objects.hashCode(_ece);
            result = prime * result + Objects.hashCode(_cwr);
            return result;
        }
    
        @Override
        public final boolean equals(java.lang.Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof TcpFlags)) {
                return false;
            }
            final TcpFlags other = (TcpFlags) obj;
            if (!Objects.equals(_fin, other._fin)) {
                return false;
            }
            if (!Objects.equals(_syn, other._syn)) {
                return false;
            }
            if (!Objects.equals(_rst, other._rst)) {
                return false;
            }
            if (!Objects.equals(_psh, other._psh)) {
                return false;
            }
            if (!Objects.equals(_ack, other._ack)) {
                return false;
            }
            if (!Objects.equals(_urg, other._urg)) {
                return false;
            }
            if (!Objects.equals(_ece, other._ece)) {
                return false;
            }
            if (!Objects.equals(_cwr, other._cwr)) {
                return false;
            }
            return true;
        }
    
        @Override
        public String toString() {
            final MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper(TcpFlags.class);
            CodeHelpers.appendValue(helper, "_fin", _fin);
            CodeHelpers.appendValue(helper, "_syn", _syn);
            CodeHelpers.appendValue(helper, "_rst", _rst);
            CodeHelpers.appendValue(helper, "_psh", _psh);
            CodeHelpers.appendValue(helper, "_ack", _ack);
            CodeHelpers.appendValue(helper, "_urg", _urg);
            CodeHelpers.appendValue(helper, "_ece", _ece);
            CodeHelpers.appendValue(helper, "_cwr", _cwr);
            return helper.toString();
        }
    }
    

    @Generated("mdsal-binding-generator")
    public enum Direction implements Enumeration {
        /**
         * Ingress flow
         */
        Ingress(0, "ingress"),
        
        /**
         * Egress flow
         */
        Egress(1, "egress")
        ;
    
        private static final Map<String, Direction> NAME_MAP;
        private static final Map<Integer, Direction> VALUE_MAP;
    
        static {
            final Builder<String, Direction> nb = ImmutableMap.builder();
            final Builder<Integer, Direction> vb = ImmutableMap.builder();
            for (Direction enumItem : Direction.values()) {
                vb.put(enumItem.value, enumItem);
                nb.put(enumItem.name, enumItem);
            }
    
            NAME_MAP = nb.build();
            VALUE_MAP = vb.build();
        }
    
        private final String name;
        private final int value;
    
        private Direction(int value, String name) {
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
         * @return corresponding Direction item, if present
         * @throws NullPointerException if name is null
         */
        public static Optional<Direction> forName(String name) {
            return Optional.ofNullable(NAME_MAP.get(Objects.requireNonNull(name)));
        }
    
        /**
         * Return the enumeration member whose {@link #getIntValue()} matches specified value.
         *
         * @param intValue integer value
         * @return corresponding Direction item, or null if no such item exists
         */
        public static Direction forValue(int intValue) {
            return VALUE_MAP.get(intValue);
        }
    }
    
    @Generated("mdsal-binding-generator")
    public enum IpVersion implements Enumeration {
        /**
         * The IPv4 protocol as defined in RFC 791.
         */
        Ipv4(4, "ipv4"),
        
        /**
         * The IPv6 protocol as defined in RFC 2460.
         */
        Ipv6(6, "ipv6")
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
    
    @Generated("mdsal-binding-generator")
    public enum Protocol implements Enumeration {
        /**
         * The protocol used is ICMP.
         */
        Icmp(1, "icmp"),
        
        /**
         * The protocol used is TCP.
         */
        Tcp(6, "tcp"),
        
        /**
         * The protocol used is UDP.
         */
        Udp(17, "udp")
        ;
    
        private static final Map<String, Protocol> NAME_MAP;
        private static final Map<Integer, Protocol> VALUE_MAP;
    
        static {
            final Builder<String, Protocol> nb = ImmutableMap.builder();
            final Builder<Integer, Protocol> vb = ImmutableMap.builder();
            for (Protocol enumItem : Protocol.values()) {
                vb.put(enumItem.value, enumItem);
                nb.put(enumItem.name, enumItem);
            }
    
            NAME_MAP = nb.build();
            VALUE_MAP = vb.build();
        }
    
        private final String name;
        private final int value;
    
        private Protocol(int value, String name) {
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
         * @return corresponding Protocol item, if present
         * @throws NullPointerException if name is null
         */
        public static Optional<Protocol> forName(String name) {
            return Optional.ofNullable(NAME_MAP.get(Objects.requireNonNull(name)));
        }
    
        /**
         * Return the enumeration member whose {@link #getIntValue()} matches specified value.
         *
         * @param intValue integer value
         * @return corresponding Protocol item, or null if no such item exists
         */
        public static Protocol forValue(int intValue) {
            return VALUE_MAP.get(intValue);
        }
    }

    public static final @NonNull QName QNAME = $YangModuleInfoImpl.qnameOf("common-fields");

    @Override
    Class<? extends CommonFields> implementedInterface();
    
    /**
     * Return bytesIn, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Incoming counter for the number of bytes associated with the IP flow.
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715.Counter64} bytesIn, or {@code null} if it is not present.
     *
     */
    Counter64 getBytesIn();
    
    /**
     * Return bytesOut, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Outgoing counter for the number of bytes associated with the IP flow.
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715.Counter64} bytesOut, or {@code null} if it is not present.
     *
     */
    Counter64 getBytesOut();
    
    /**
     * Return bytesMul, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         IP multicast outgoing byte counter associated with the IP flow.
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715.Counter64} bytesMul, or {@code null} if it is not present.
     *
     */
    Counter64 getBytesMul();
    
    /**
     * Return pktsIn, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Incoming counter for the number of packets associated with the IP flow.
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715.Counter64} pktsIn, or {@code null} if it is not present.
     *
     */
    Counter64 getPktsIn();
    
    /**
     * Return pktsOut, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Outgoing counter for the number of packets associated with the IP flow.
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715.Counter64} pktsOut, or {@code null} if it is not present.
     *
     */
    Counter64 getPktsOut();
    
    /**
     * Return pktsMul, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         IP multicast outgoing packet counter associated with the IP flow.
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715.Counter64} pktsMul, or {@code null} if it is not present.
     *
     */
    Counter64 getPktsMul();
    
    /**
     * Return firstSwitched, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         sysUpTime in msec at which the first packet in the IP flow was switched.
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.SysUptime} firstSwitched, or {@code null} if it is not present.
     *
     */
    SysUptime getFirstSwitched();
    
    /**
     * Return lastSwitched, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         sysUpTime in msec at which the last packet in the IP flow was switched.
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.SysUptime} lastSwitched, or {@code null} if it is not present.
     *
     */
    SysUptime getLastSwitched();
    
    /**
     * Return flows, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Number of IP Flows that were aggregated.
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715.Counter64} flows, or {@code null} if it is not present.
     *
     */
    Counter64 getFlows();
    
    /**
     * Return direction, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Flow direction whose value shall be 0 for ingress flow and 1 for egress flow.
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFields.Direction} direction, or {@code null} if it is not present.
     *
     */
    Direction getDirection();
    
    /**
     * Return ipVersion, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         IP Protocol version whose value shall be 4 for IPv4 and 6 for IPv6. If not
     *         present, version 4 is assumed.
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFields.IpVersion} ipVersion, or {@code null} if it is not present.
     *
     */
    IpVersion getIpVersion();
    
    /**
     * Return protocol, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Byte de protocolo IP. Indica el tipo de paquete de transporte incluido en la
     *         parte de datos del datagrama IP.
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFields.Protocol} protocol, or {@code null} if it is not present.
     *
     */
    Protocol getProtocol();
    
    /**
     * Return srcTos, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Type of service byte when entering incoming interface.
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yangtools.yang.common.Uint8} srcTos, or {@code null} if it is not present.
     *
     */
    Uint8 getSrcTos();
    
    /**
     * Return dstTos, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Type of service byte when exiting outgoing interface.
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yangtools.yang.common.Uint8} dstTos, or {@code null} if it is not present.
     *
     */
    Uint8 getDstTos();
    
    /**
     * Return tcpFlags, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Cumulative TCP flags seen in an IP flow.
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.CommonFields.TcpFlags} tcpFlags, or {@code null} if it is not present.
     *
     */
    TcpFlags getTcpFlags();
    
    /**
     * Return srcPort, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         TCP/UDP source port number used in the flow.
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev130715.PortNumber} srcPort, or {@code null} if it is not present.
     *
     */
    PortNumber getSrcPort();
    
    /**
     * Return dstPort, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         TCP/UDP destination port number used in the flow.
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev130715.PortNumber} dstPort, or {@code null} if it is not present.
     *
     */
    PortNumber getDstPort();
    
    /**
     * Return srcAddress, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         IPv4 source address of the IP flow.
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev130715.Ipv4Address} srcAddress, or {@code null} if it is not present.
     *
     */
    Ipv4Address getSrcAddress();
    
    /**
     * Return dstAddress, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         IPv4 destination address of the IP flow.
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev130715.Ipv4Address} dstAddress, or {@code null} if it is not present.
     *
     */
    Ipv4Address getDstAddress();
    
    /**
     * Return nextHop, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         IPv4 address of the next-hop router 
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev130715.Ipv4Address} nextHop, or {@code null} if it is not present.
     *
     */
    Ipv4Address getNextHop();
    
    /**
     * Return srcAddressIpv6, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         IPv6 source address of the IP flow.
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev130715.Ipv6Address} srcAddressIpv6, or {@code null} if it is not present.
     *
     */
    Ipv6Address getSrcAddressIpv6();
    
    /**
     * Return dstAddressIpv6, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         IPv6 destination address of the IP flow.
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev130715.Ipv6Address} dstAddressIpv6, or {@code null} if it is not present.
     *
     */
    Ipv6Address getDstAddressIpv6();
    
    /**
     * Return nextHopIpv6, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         IPv6 address of the next-hop router.
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev130715.Ipv6Address} nextHopIpv6, or {@code null} if it is not present.
     *
     */
    Ipv6Address getNextHopIpv6();
    
    /**
     * Return srcMask, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Source subnet mask (in slash notation) of the IP flow.
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.PrefixLengthIpv4} srcMask, or {@code null} if it is not present.
     *
     */
    PrefixLengthIpv4 getSrcMask();
    
    /**
     * Return dstMask, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Destination subnet mask (in slash notation) of the IP flow.
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.PrefixLengthIpv4} dstMask, or {@code null} if it is not present.
     *
     */
    PrefixLengthIpv4 getDstMask();
    
    /**
     * Return srcMaskIpv6, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Length of the IPv6 source mask in contiguous bits.
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.PrefixLengthIpv6} srcMaskIpv6, or {@code null} if it is not present.
     *
     */
    PrefixLengthIpv6 getSrcMaskIpv6();
    
    /**
     * Return dstMaskIpv6, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Length of the IPv6 destination mask in contiguous bits.
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.PrefixLengthIpv6} dstMaskIpv6, or {@code null} if it is not present.
     *
     */
    PrefixLengthIpv6 getDstMaskIpv6();
    
    /**
     * Return srcMac, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Source MAC address.
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715.MacAddress} srcMac, or {@code null} if it is not present.
     *
     */
    MacAddress getSrcMac();
    
    /**
     * Return dstMac, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Destination MAC address.
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715.MacAddress} dstMac, or {@code null} if it is not present.
     *
     */
    MacAddress getDstMac();
    
    /**
     * Return flowLabelIpv6, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         IPv6 flow label as defined in RFC 2460.
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.Ipv6FlowLabel} flowLabelIpv6, or {@code null} if it is not present.
     *
     */
    Ipv6FlowLabel getFlowLabelIpv6();
    
    /**
     * Return optHeadersIpv6, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Bit-encoded field identifying IPv6 option headers found in the flow.
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yangtools.yang.common.Uint32} optHeadersIpv6, or {@code null} if it is not present.
     *
     */
    Uint32 getOptHeadersIpv6();
    
    /**
     * Return snmpIn, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Input interface index.
     *     </code>
     * </pre>
     *
     * @return {@code java.lang.Integer} snmpIn, or {@code null} if it is not present.
     *
     */
    Integer getSnmpIn();
    
    /**
     * Return snmpOut, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Output interface index.
     *     </code>
     * </pre>
     *
     * @return {@code java.lang.Integer} snmpOut, or {@code null} if it is not present.
     *
     */
    Integer getSnmpOut();
    
    /**
     * Return srcAs, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Source BGP autonomous system number.
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev130715.AsNumber} srcAs, or {@code null} if it is not present.
     *
     */
    AsNumber getSrcAs();
    
    /**
     * Return dstAs, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Destination BGP autonomous system number.
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev130715.AsNumber} dstAs, or {@code null} if it is not present.
     *
     */
    AsNumber getDstAs();
    
    /**
     * Return bgpNextHop, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Next-hop router's IPv4 address in the BGP domain.
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev130715.Ipv4Address} bgpNextHop, or {@code null} if it is not present.
     *
     */
    Ipv4Address getBgpNextHop();
    
    /**
     * Return bgpNextHopIpv6, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Next-hop router's IPv6 address in the BGP domain.
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev130715.Ipv6Address} bgpNextHopIpv6, or {@code null} if it is not present.
     *
     */
    Ipv6Address getBgpNextHopIpv6();
    
    /**
     * Return srcVlan, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Virtual LAN identifier associated with ingress interface.
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.VlanId} srcVlan, or {@code null} if it is not present.
     *
     */
    VlanId getSrcVlan();
    
    /**
     * Return dstVlan, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Virtual LAN identifier associated with egress interface.
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.VlanId} dstVlan, or {@code null} if it is not present.
     *
     */
    VlanId getDstVlan();
    
    /**
     * Return icmpType, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Internet Control Message Protocol (ICMP) packet type. Reported as ICMP Type *
     *         256 + ICMP code.
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yangtools.yang.common.Uint16} icmpType, or {@code null} if it is not present.
     *
     */
    Uint16 getIcmpType();
    
    /**
     * Return igmpType, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Internet Group Management Protocol (IGMP) packet type.
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yangtools.yang.common.Uint8} igmpType, or {@code null} if it is not present.
     *
     */
    Uint8 getIgmpType();
    
    /**
     * Return totBytesExp, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Counter for the number of bytes exported by the Observation Domain.
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715.Counter64} totBytesExp, or {@code null} if it is not present.
     *
     */
    Counter64 getTotBytesExp();
    
    /**
     * Return totPktsExp, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Counter for the number of packets exported by the Observation Domain.
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715.Counter64} totPktsExp, or {@code null} if it is not present.
     *
     */
    Counter64 getTotPktsExp();
    
    /**
     * Return totFlowsExp, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Counter for the number of flows exported by the Observation Domain.
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715.Counter64} totFlowsExp, or {@code null} if it is not present.
     *
     */
    Counter64 getTotFlowsExp();

}

