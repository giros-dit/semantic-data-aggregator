package org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008;
import javax.annotation.processing.Generated;
import org.opendaylight.yangtools.yang.binding.DataRoot;

/**
 * YANG model to represent the information collected in a Netflow version 9 export 
 * packet
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
 *   typedef protocol-type {
 *     type enumeration {
 *       enum icmp {
 *         value 1;
 *       }
 *       enum igmp {
 *         value 2;
 *       }
 *       enum tcp {
 *         value 6;
 *       }
 *       enum egp {
 *         value 8;
 *       }
 *       enum igp {
 *         value 9;
 *       }
 *       enum udp {
 *         value 17;
 *       }
 *       enum icmp-ipv6 {
 *         value 58;
 *       }
 *       enum pim {
 *         value 103;
 *       }
 *       enum l2tp {
 *         value 115;
 *       }
 *       enum sctp {
 *         value 132;
 *       }
 *       enum eth {
 *         value 143;
 *       }
 *     }
 *   }
 *   typedef engine-type {
 *     type enumeration {
 *       enum rp {
 *         value 0;
 *       }
 *       enum vip-linecard {
 *         value 1;
 *       }
 *       enum pdc/dfc {
 *         value 2;
 *       }
 *     }
 *   }
 *   typedef top-label-type {
 *     type enumeration {
 *       enum unknown {
 *         value 0;
 *       }
 *       enum te-midpt {
 *         value 1;
 *       }
 *       enum atom {
 *         value 2;
 *       }
 *       enum vpn {
 *         value 3;
 *       }
 *       enum bgp {
 *         value 4;
 *       }
 *       enum ldp {
 *         value 5;
 *       }
 *     }
 *   }
 *   typedef forwarding-status-type {
 *     type enumeration {
 *       enum unknown {
 *         value 0;
 *       }
 *       enum unknown-forwarded {
 *         value 64;
 *       }
 *       enum forwarded-frag {
 *         value 65;
 *       }
 *       enum forwarded-not-frag {
 *         value 66;
 *       }
 *       enum unknown-dropped {
 *         value 128;
 *       }
 *       enum acl-deny {
 *         value 129;
 *       }
 *       enum acl-drop {
 *         value 130;
 *       }
 *       enum unroutable {
 *         value 131;
 *       }
 *       enum adjacency {
 *         value 132;
 *       }
 *       enum fragment-df {
 *         value 133;
 *       }
 *       enum bad-header-check {
 *         value 134;
 *       }
 *       enum bad-tot-len {
 *         value 135;
 *       }
 *       enum bad-header-len {
 *         value 136;
 *       }
 *       enum bad-ttl {
 *         value 137;
 *       }
 *       enum policer {
 *         value 138;
 *       }
 *       enum wred {
 *         value 139;
 *       }
 *       enum rpf {
 *         value 140;
 *       }
 *       enum for-us {
 *         value 141;
 *       }
 *       enum bad-out-interf {
 *         value 142;
 *       }
 *       enum hardware {
 *         value 143;
 *       }
 *       enum unknown-consumed {
 *         value 192;
 *       }
 *       enum term-punt-adjacency {
 *         value 193;
 *       }
 *       enum term-incomp-adjacency {
 *         value 194;
 *       }
 *       enum term-for-us {
 *         value 195;
 *       }
 *     }
 *   }
 *   typedef igmp-type {
 *     type enumeration {
 *       enum membership-query {
 *         value 17;
 *       }
 *       enum membership-report-v1 {
 *         value 18;
 *       }
 *       enum dvmrp {
 *         value 19;
 *       }
 *       enum pim-v1 {
 *         value 20;
 *       }
 *       enum trace-messages {
 *         value 21;
 *       }
 *       enum membership-report-v2 {
 *         value 22;
 *       }
 *       enum leave-group-v2 {
 *         value 23;
 *       }
 *       enum mul-traceroute-resp {
 *         value 30;
 *       }
 *       enum mul-traceroute {
 *         value 31;
 *       }
 *       enum membership-report-v3 {
 *         value 34;
 *       }
 *       enum mul-router-advert {
 *         value 48;
 *       }
 *       enum mul-router-sol {
 *         value 49;
 *       }
 *       enum mul-router-term {
 *         value 50;
 *       }
 *     }
 *   }
 *   typedef sampling-mode-type {
 *     type enumeration {
 *       enum deterministic {
 *         value 1;
 *       }
 *       enum random {
 *         value 2;
 *       }
 *     }
 *   }
 *   typedef ip-version-type {
 *     type enumeration {
 *       enum ipv4 {
 *         value 4;
 *       }
 *       enum ipv6 {
 *         value 6;
 *       }
 *     }
 *   }
 *   typedef direction-type {
 *     type enumeration {
 *       enum ingress {
 *         value 0;
 *       }
 *       enum egress {
 *         value 1;
 *       }
 *     }
 *   }
 *   typedef tcp-flags-type {
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
 *   grouping common-flow-fields {
 *     leaf bytes-in {
 *       type yang:counter64;
 *       units bytes;
 *     }
 *     leaf bytes-out {
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
 *     leaf flows {
 *       type yang:counter64;
 *       units flows;
 *     }
 *     leaf protocol {
 *       type net-v9:protocol-type;
 *     }
 *     leaf src-tos {
 *       type uint8;
 *     }
 *     leaf dst-tos {
 *       type uint8;
 *     }
 *     leaf tcp-flags {
 *       type net-v9:tcp-flags-type;
 *     }
 *     leaf src-port {
 *       type inet:port-number;
 *     }
 *     leaf dst-port {
 *       type inet:port-number;
 *     }
 *     leaf snmp-in {
 *       type int32 {
 *         range 0..2147483647;
 *       }
 *     }
 *     leaf snmp-out {
 *       type int32 {
 *         range 0..2147483647;
 *       }
 *     }
 *     leaf bytes-out-mul {
 *       type yang:counter64;
 *       units bytes;
 *     }
 *     leaf pkts-out-mul {
 *       type yang:counter64;
 *       units packets;
 *     }
 *     leaf first-switched {
 *       type uint64;
 *       units milliseconds;
 *     }
 *     leaf last-switched {
 *       type uint64;
 *       units milliseconds;
 *     }
 *     leaf min-pkt-len {
 *       type uint16;
 *     }
 *     leaf max-pkt-len {
 *       type uint16;
 *     }
 *     leaf icmp-type {
 *       type uint16;
 *     }
 *     leaf igmp-type {
 *       type net-v9:igmp-type;
 *     }
 *     leaf sampler-name {
 *       type string;
 *     }
 *     leaf sampling-interval {
 *       type uint32;
 *     }
 *     leaf sampling-algorithm {
 *       type net-v9:sampling-mode-type;
 *     }
 *     leaf flow-active-tout {
 *       type uint16;
 *       units seconds;
 *       default 1800;
 *     }
 *     leaf flow-inactive-tout {
 *       type uint16;
 *       units seconds;
 *       default 15;
 *     }
 *     leaf engine-type {
 *       type net-v9:engine-type;
 *     }
 *     leaf engine-id {
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
 *     leaf flow-sampler-id {
 *       type uint8;
 *     }
 *     leaf flow-sampler-mode {
 *       type net-v9:sampling-mode-type;
 *     }
 *     leaf flow-sampler-random {
 *       type uint32;
 *     }
 *     leaf min-ttl {
 *       type uint8;
 *     }
 *     leaf max-ttl {
 *       type uint8;
 *     }
 *     leaf src-mac-in {
 *       type yang:mac-address;
 *     }
 *     leaf dst-mac-in {
 *       type yang:mac-address;
 *     }
 *     leaf src-mac-out {
 *       type yang:mac-address;
 *     }
 *     leaf dst-mac-out {
 *       type yang:mac-address;
 *     }
 *     leaf ip-version {
 *       type net-v9:ip-version-type;
 *     }
 *     leaf direction {
 *       type net-v9:direction-type;
 *     }
 *     leaf if-name {
 *       type string;
 *     }
 *     leaf if-desc {
 *       type string;
 *     }
 *     leaf frag-offset {
 *       type uint16;
 *     }
 *     leaf forwarding-status {
 *       type net-v9:forwarding-status-type;
 *     }
 *     leaf postip-dscp {
 *       type inet:dscp;
 *     }
 *     leaf repl-factor-mul {
 *       type uint32;
 *     }
 *     container ipv4 {
 *       leaf src-address {
 *         type inet:ipv4-address;
 *       }
 *       leaf dst-address {
 *         type inet:ipv4-address;
 *       }
 *       leaf src-mask {
 *         type prefix-length-ipv4;
 *       }
 *       leaf dst-mask {
 *         type prefix-length-ipv4;
 *       }
 *       leaf src-prefix {
 *         type inet:ipv4-prefix;
 *       }
 *       leaf dst-prefix {
 *         type inet:ipv4-prefix;
 *       }
 *       leaf next-hop {
 *         type inet:ipv4-address;
 *       }
 *       leaf identification {
 *         type uint16;
 *       }
 *     }
 *     container ipv6 {
 *       leaf src-address {
 *         type inet:ipv6-address;
 *       }
 *       leaf dst-address {
 *         type inet:ipv6-address;
 *       }
 *       leaf src-mask {
 *         type prefix-length-ipv6;
 *       }
 *       leaf dst-mask {
 *         type prefix-length-ipv6;
 *       }
 *       leaf next-hop {
 *         type inet:ipv6-address;
 *       }
 *       leaf flow-label {
 *         type inet:ipv6-flow-label;
 *       }
 *       leaf opt-headers {
 *         type uint32;
 *       }
 *     }
 *     container mpls {
 *       leaf pal-rd {
 *         type uint64;
 *       }
 *       leaf prefix-len {
 *         type prefix-length-ipv4;
 *       }
 *       leaf top-label-type {
 *         type net-v9:top-label-type;
 *       }
 *       leaf top-label-ip {
 *         type inet:ipv4-address;
 *       }
 *       leaf label-1 {
 *         type uint32 {
 *           range 0..16777215;
 *         }
 *       }
 *       leaf label-2 {
 *         type uint32 {
 *           range 0..16777215;
 *         }
 *       }
 *       leaf label-3 {
 *         type uint32 {
 *           range 0..16777215;
 *         }
 *       }
 *       leaf label-4 {
 *         type uint32 {
 *           range 0..16777215;
 *         }
 *       }
 *       leaf label-5 {
 *         type uint32 {
 *           range 0..16777215;
 *         }
 *       }
 *       leaf label-6 {
 *         type uint32 {
 *           range 0..16777215;
 *         }
 *       }
 *       leaf label-7 {
 *         type uint32 {
 *           range 0..16777215;
 *         }
 *       }
 *       leaf label-8 {
 *         type uint32 {
 *           range 0..16777215;
 *         }
 *       }
 *       leaf label-9 {
 *         type uint32 {
 *           range 0..16777215;
 *         }
 *       }
 *       leaf label-10 {
 *         type uint32 {
 *           range 0..16777215;
 *         }
 *       }
 *     }
 *     container bgp {
 *       leaf src-as {
 *         type inet:as-number;
 *       }
 *       leaf dst-as {
 *         type inet:as-number;
 *       }
 *       leaf next-hop {
 *         type inet:ipv4-address;
 *       }
 *       leaf next-hop-ipv6 {
 *         type inet:ipv6-address;
 *       }
 *       leaf src-traffic-id {
 *         type uint32;
 *       }
 *       leaf dst-traffic-id {
 *         type uint32;
 *       }
 *     }
 *     container vlan {
 *       leaf src-id {
 *         type uint16 {
 *           range 0..4095;
 *         }
 *       }
 *       leaf dst-id {
 *         type uint16 {
 *           range 0..4095;
 *         }
 *       }
 *     }
 *     container permanent-flow {
 *       leaf bytes-in {
 *         type yang:counter64;
 *         units bytes;
 *       }
 *       leaf pkts-in {
 *         type yang:counter64;
 *         units packets;
 *       }
 *     }
 *     container application {
 *       leaf desc {
 *         type string;
 *       }
 *       leaf tag {
 *         type string;
 *       }
 *       leaf name {
 *         type string;
 *       }
 *     }
 *     container layer2-pkt-section {
 *       leaf offset {
 *         type uint16;
 *       }
 *       leaf size {
 *         type uint16;
 *       }
 *       leaf data {
 *         type string;
 *       }
 *     }
 *   }
 *   container netflow {
 *     config false;
 *     container collector-goflow2 {
 *       leaf time-received {
 *         type yang:timestamp;
 *         units seconds;
 *       }
 *       leaf sampler-address {
 *         type inet:ipv4-address;
 *       }
 *       leaf sampler-address-ipv6 {
 *         type inet:ipv6-address;
 *       }
 *     }
 *     container export-packet {
 *       leaf sequence-number {
 *         type yang:counter32;
 *       }
 *       leaf count {
 *         type uint16;
 *       }
 *       leaf system-uptime {
 *         type yang:timestamp;
 *         units milliseconds;
 *       }
 *       leaf unix-seconds {
 *         type yang:timestamp;
 *         units seconds;
 *       }
 *       leaf source-id {
 *         type uint32;
 *       }
 *       list flow-data-record {
 *         ordered-by user;
 *         key flow-id;
 *         leaf flow-id {
 *           type int32;
 *         }
 *         uses common-flow-fields;
 *       }
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
     *         This container collects all information related to the NetFlow collecting or
     *         exporting process
     *     </code>
     * </pre>
     * 
     * @return {@code org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.Netflow} netflow, or {@code null} if it is not present.
     *
     */
    Netflow getNetflow();

}

