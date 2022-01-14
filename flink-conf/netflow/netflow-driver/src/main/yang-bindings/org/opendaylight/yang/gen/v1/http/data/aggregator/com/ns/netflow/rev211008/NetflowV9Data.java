package org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008;
import javax.annotation.processing.Generated;
import org.opendaylight.yangtools.yang.binding.DataRoot;

/**
 * YANG model to represent the information collected in a Netflow version 9 flow
 *
 * <p>
 * This class represents the following YANG schema fragment defined in module <b>netflow-v9</b>
 * <pre>
 * module netflow-v9 {
 *   yang-version 1.1;
 *   namespace "http://data-aggregator.com/ns/netflow";
 *   prefix net-v9;
 *   import ietf-inet-types {
 *     prefix inet;
 *   }
 *   import ietf-yang-types {
 *     prefix yang;
 *   }
 *   revision 2021-10-08 {
 *   }
 *   typedef name-type {
 *     type string {
 *       length 1..max;
 *       pattern \S(.*\S)?;
 *     }
 *   }
 *   typedef sys-uptime {
 *     type yang:timeticks;
 *     units milliseconds;
 *   }
 *   typedef ipv6-flow-label {
 *     type uint32 {
 *       range 0..1048575;
 *     }
 *   }
 *   typedef vlan-id {
 *     type uint16 {
 *       range 0..4095;
 *     }
 *   }
 *   typedef prefix-length-ipv4 {
 *     type uint8 {
 *       range 0..32;
 *     }
 *   }
 *   typedef prefix-length-ipv6 {
 *     type uint8 {
 *       range 0..128;
 *     }
 *   }
 *   grouping common-fields {
 *     leaf bytes-in {
 *       type yang:counter64;
 *       units bytes;
 *     }
 *     leaf bytes-out {
 *       type yang:counter64;
 *       units bytes;
 *     }
 *     leaf bytes-mul {
 *       type yang:counter64;
 *       units bytes;
 *     }
 *     leaf pkts-in {
 *       type yang:counter64;
 *       units packets;
 *     }
 *     leaf pkts-out {
 *       type yang:counter64;
 *       units packets;
 *     }
 *     leaf pkts-mul {
 *       type yang:counter64;
 *       units packets;
 *     }
 *     leaf first-switched {
 *       type sys-uptime;
 *     }
 *     leaf last-switched {
 *       type sys-uptime;
 *     }
 *     leaf flows {
 *       type yang:counter64;
 *       units flows;
 *     }
 *     leaf direction {
 *       type enumeration {
 *         enum ingress {
 *           value 0;
 *         }
 *         enum egress {
 *           value 1;
 *         }
 *       }
 *     }
 *     leaf ip-version {
 *       type enumeration {
 *         enum ipv4 {
 *           value 4;
 *         }
 *         enum ipv6 {
 *           value 6;
 *         }
 *       }
 *     }
 *     leaf protocol {
 *       type enumeration {
 *         enum icmp {
 *           value 1;
 *         }
 *         enum tcp {
 *           value 6;
 *         }
 *         enum udp {
 *           value 17;
 *         }
 *       }
 *     }
 *     leaf src-tos {
 *       type uint8;
 *     }
 *     leaf dst-tos {
 *       type uint8;
 *     }
 *     leaf tcp-flags {
 *       type bits {
 *         bit fin {
 *           position 0;
 *         }
 *         bit syn {
 *           position 1;
 *         }
 *         bit rst {
 *           position 2;
 *         }
 *         bit psh {
 *           position 3;
 *         }
 *         bit ack {
 *           position 4;
 *         }
 *         bit urg {
 *           position 5;
 *         }
 *         bit ece {
 *           position 6;
 *         }
 *         bit cwr {
 *           position 7;
 *         }
 *       }
 *     }
 *     leaf src-port {
 *       type inet:port-number;
 *     }
 *     leaf dst-port {
 *       type inet:port-number;
 *     }
 *     leaf src-address {
 *       type inet:ipv4-address;
 *     }
 *     leaf dst-address {
 *       type inet:ipv4-address;
 *     }
 *     leaf next-hop {
 *       type inet:ipv4-address;
 *     }
 *     leaf src-address-ipv6 {
 *       type inet:ipv6-address;
 *     }
 *     leaf dst-address-ipv6 {
 *       type inet:ipv6-address;
 *     }
 *     leaf next-hop-ipv6 {
 *       type inet:ipv6-address;
 *     }
 *     leaf src-mask {
 *       type prefix-length-ipv4;
 *     }
 *     leaf dst-mask {
 *       type prefix-length-ipv4;
 *     }
 *     leaf src-mask-ipv6 {
 *       type prefix-length-ipv6;
 *     }
 *     leaf dst-mask-ipv6 {
 *       type prefix-length-ipv6;
 *     }
 *     leaf src-mac {
 *       type yang:mac-address;
 *     }
 *     leaf dst-mac {
 *       type yang:mac-address;
 *     }
 *     leaf flow-label-ipv6 {
 *       type ipv6-flow-label;
 *     }
 *     leaf opt-headers-ipv6 {
 *       type uint32;
 *     }
 *     leaf snmp-in {
 *       type int32 {
 *         range 1..2147483647;
 *       }
 *     }
 *     leaf snmp-out {
 *       type int32 {
 *         range 1..2147483647;
 *       }
 *     }
 *     leaf src-as {
 *       type inet:as-number;
 *     }
 *     leaf dst-as {
 *       type inet:as-number;
 *     }
 *     leaf bgp-next-hop {
 *       type inet:ipv4-address;
 *     }
 *     leaf bgp-next-hop-ipv6 {
 *       type inet:ipv6-address;
 *     }
 *     leaf src-vlan {
 *       type vlan-id;
 *     }
 *     leaf dst-vlan {
 *       type vlan-id;
 *     }
 *     leaf icmp-type {
 *       type uint16;
 *     }
 *     leaf igmp-type {
 *       type uint8;
 *     }
 *     leaf tot-bytes-exp {
 *       type yang:counter64;
 *       units bytes;
 *     }
 *     leaf tot-pkts-exp {
 *       type yang:counter64;
 *       units packets;
 *     }
 *     leaf tot-flows-exp {
 *       type yang:counter64;
 *       units flows;
 *     }
 *   }
 *   container netflow {
 *     config false;
 *     list fields-flow-record {
 *       ordered-by user;
 *       key name;
 *       leaf name {
 *         type string;
 *       }
 *       uses common-fields;
 *     }
 *   }
 * }
 * </pre>
 *
 */
@Generated("mdsal-binding-generator")
public interface NetflowV9Data
    extends
    DataRoot
{




    /**
     * Return netflow, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Netflow Exporter or Collector data node.
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.Netflow} netflow, or {@code null} if it is not present.
     *
     */
    Netflow getNetflow();

}

