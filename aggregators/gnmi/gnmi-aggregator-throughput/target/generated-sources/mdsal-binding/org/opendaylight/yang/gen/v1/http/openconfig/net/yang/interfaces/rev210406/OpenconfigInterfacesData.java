package org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406;
import javax.annotation.processing.Generated;
import org.opendaylight.yangtools.yang.binding.DataRoot;

/**
 * Model for managing network interfaces and subinterfaces. This module also 
 * defines convenience types / groupings for other models to create references to 
 * interfaces: base-interface-ref (type) - reference to a base interface 
 * interface-ref (grouping) - container for reference to a interface + 
 * interface-ref-state (grouping) - container for read-only (opstate) reference to 
 * interface + subinterface This model reuses data items defined in the IETF YANG 
 * model for interfaces described by RFC 7223 with an alternate structure 
 * (particularly for operational state data) and with additional configuration 
 * items. Portions of this code were derived from IETF RFC 7223. Please reproduce 
 * this note if possible. IETF code is subject to the following copyright and 
 * license: Copyright (c) IETF Trust and the persons identified as authors of the 
 * code. All rights reserved. Redistribution and use in source and binary forms, 
 * with or without modification, is permitted pursuant to, and subject to the 
 * license terms contained in, the Simplified BSD License set forth in Section 4.c 
 * of the IETF Trust's Legal Provisions Relating to IETF Documents 
 * (http://trustee.ietf.org/license-info).
 *
 * <p>
 * This class represents the following YANG schema fragment defined in module <b>openconfig-interfaces</b>
 * <pre>
 * module openconfig-interfaces {
 *   yang-version 1;
 *   namespace "http://openconfig.net/yang/interfaces";
 *   prefix oc-if;
 *   import ietf-interfaces {
 *     prefix ietf-if;
 *   }
 *   import openconfig-yang-types {
 *     prefix oc-yang;
 *   }
 *   import openconfig-types {
 *     prefix oc-types;
 *   }
 *   import openconfig-extensions {
 *     prefix oc-ext;
 *   }
 *   oc-ext:openconfig-version 2.5.0;
 *   revision 2021-04-06 {
 *   }
 *   revision 2019-11-19 {
 *   }
 *   revision 2019-07-10 {
 *   }
 *   revision 2018-11-21 {
 *   }
 *   revision 2018-08-07 {
 *   }
 *   revision 2018-07-02 {
 *   }
 *   revision 2018-04-24 {
 *   }
 *   revision 2018-01-05 {
 *   }
 *   revision 2017-12-22 {
 *   }
 *   revision 2017-12-21 {
 *   }
 *   revision 2017-07-14 {
 *   }
 *   revision 2017-04-03 {
 *   }
 *   revision 2016-12-22 {
 *   }
 *   oc-ext:regexp-posix;
 *   oc-ext:catalog-organization openconfig;
 *   oc-ext:origin openconfig;
 *   typedef base-interface-ref {
 *     type leafref {
 *       path /oc-if:interfaces/oc-if:interface/oc-if:name;
 *     }
 *   }
 *   typedef interface-id {
 *     type string;
 *   }
 *   grouping interface-ref-common {
 *     leaf interface {
 *       type leafref {
 *         path /oc-if:interfaces/oc-if:interface/oc-if:name;
 *       }
 *     }
 *     leaf subinterface {
 *       type leafref {
 *         path /oc-if:interfaces/oc-if:interface[oc-if:name=current()/../interface]/oc-if:subinterfaces/oc-if:subinterface/oc-if:index;
 *       }
 *     }
 *   }
 *   grouping interface-ref-state-container {
 *     container state {
 *       config false;
 *       uses interface-ref-common;
 *     }
 *   }
 *   grouping interface-ref {
 *     container interface-ref {
 *       container config {
 *         oc-ext:telemetry-on-change;
 *         uses interface-ref-common;
 *       }
 *       uses interface-ref-state-container;
 *     }
 *   }
 *   grouping interface-ref-state {
 *     container interface-ref {
 *       uses interface-ref-state-container;
 *     }
 *   }
 *   grouping base-interface-ref-state {
 *     container state {
 *       config false;
 *       leaf interface {
 *         type base-interface-ref;
 *       }
 *     }
 *   }
 *   grouping interface-common-config {
 *     leaf description {
 *       type string;
 *     }
 *     leaf enabled {
 *       type boolean;
 *       default true;
 *     }
 *   }
 *   grouping interface-phys-config {
 *     leaf name {
 *       type string;
 *     }
 *     leaf type {
 *       type identityref {
 *         base interface-type;
 *       }
 *     }
 *     leaf mtu {
 *       type uint16;
 *     }
 *     leaf loopback-mode {
 *       type boolean;
 *       default false;
 *     }
 *     uses interface-common-config;
 *   }
 *   grouping interface-phys-holdtime-config {
 *     leaf up {
 *       type uint32;
 *       units milliseconds;
 *       default 0;
 *     }
 *     leaf down {
 *       type uint32;
 *       units milliseconds;
 *       default 0;
 *     }
 *   }
 *   grouping interface-phys-holdtime-state {
 *   }
 *   grouping interface-phys-holdtime-top {
 *     container hold-time {
 *       container config {
 *         oc-ext:telemetry-on-change;
 *         uses interface-phys-holdtime-config;
 *       }
 *       container state {
 *         config false;
 *         uses interface-phys-holdtime-config;
 *         uses interface-phys-holdtime-state;
 *       }
 *     }
 *   }
 *   grouping interface-common-state {
 *     oc-ext:operational;
 *     leaf ifindex {
 *       type uint32;
 *       oc-ext:telemetry-on-change;
 *     }
 *     leaf admin-status {
 *       type enumeration {
 *         enum UP {
 *         }
 *         enum DOWN {
 *         }
 *         enum TESTING {
 *         }
 *       }
 *       oc-ext:telemetry-on-change;
 *     }
 *     leaf oper-status {
 *       type enumeration {
 *         enum UP {
 *           value 1;
 *         }
 *         enum DOWN {
 *           value 2;
 *         }
 *         enum TESTING {
 *           value 3;
 *         }
 *         enum UNKNOWN {
 *           value 4;
 *         }
 *         enum DORMANT {
 *           value 5;
 *         }
 *         enum NOT_PRESENT {
 *           value 6;
 *         }
 *         enum LOWER_LAYER_DOWN {
 *           value 7;
 *         }
 *       }
 *       oc-ext:telemetry-on-change;
 *     }
 *     leaf last-change {
 *       type oc-types:timeticks64;
 *       oc-ext:telemetry-on-change;
 *     }
 *     leaf logical {
 *       type boolean;
 *       oc-ext:telemetry-on-change;
 *     }
 *     leaf management {
 *       type boolean;
 *       oc-ext:telemetry-on-change;
 *     }
 *     leaf cpu {
 *       type boolean;
 *       oc-ext:telemetry-on-change;
 *     }
 *   }
 *   grouping interface-counters-state {
 *     oc-ext:operational;
 *     container counters {
 *       leaf in-octets {
 *         type oc-yang:counter64;
 *       }
 *       leaf in-pkts {
 *         type oc-yang:counter64;
 *       }
 *       leaf in-unicast-pkts {
 *         type oc-yang:counter64;
 *       }
 *       leaf in-broadcast-pkts {
 *         type oc-yang:counter64;
 *       }
 *       leaf in-multicast-pkts {
 *         type oc-yang:counter64;
 *       }
 *       leaf in-discards {
 *         type oc-yang:counter64;
 *       }
 *       leaf in-errors {
 *         type oc-yang:counter64;
 *       }
 *       leaf in-unknown-protos {
 *         type oc-yang:counter64;
 *       }
 *       leaf in-fcs-errors {
 *         type oc-yang:counter64;
 *       }
 *       leaf out-octets {
 *         type oc-yang:counter64;
 *       }
 *       leaf out-pkts {
 *         type oc-yang:counter64;
 *       }
 *       leaf out-unicast-pkts {
 *         type oc-yang:counter64;
 *       }
 *       leaf out-broadcast-pkts {
 *         type oc-yang:counter64;
 *       }
 *       leaf out-multicast-pkts {
 *         type oc-yang:counter64;
 *       }
 *       leaf out-discards {
 *         type oc-yang:counter64;
 *       }
 *       leaf out-errors {
 *         type oc-yang:counter64;
 *       }
 *       leaf carrier-transitions {
 *         type oc-yang:counter64;
 *         oc-ext:telemetry-on-change;
 *       }
 *       leaf last-clear {
 *         type oc-types:timeticks64;
 *         oc-ext:telemetry-on-change;
 *       }
 *     }
 *   }
 *   grouping sub-unnumbered-config {
 *     leaf enabled {
 *       type boolean;
 *       default false;
 *     }
 *   }
 *   grouping sub-unnumbered-state {
 *   }
 *   grouping sub-unnumbered-top {
 *     container unnumbered {
 *       container config {
 *         oc-ext:telemetry-on-change;
 *         uses sub-unnumbered-config;
 *       }
 *       container state {
 *         config false;
 *         uses sub-unnumbered-config;
 *         uses sub-unnumbered-state;
 *       }
 *       uses oc-if:interface-ref;
 *     }
 *   }
 *   grouping subinterfaces-config {
 *     leaf index {
 *       type uint32;
 *       default 0;
 *     }
 *     uses interface-common-config;
 *   }
 *   grouping subinterfaces-state {
 *     oc-ext:operational;
 *     leaf name {
 *       type string;
 *       oc-ext:telemetry-on-change;
 *     }
 *     uses interface-common-state;
 *     uses interface-counters-state;
 *   }
 *   grouping subinterfaces-top {
 *     container subinterfaces {
 *       list subinterface {
 *         key index;
 *         leaf index {
 *           type leafref {
 *             path ../config/index;
 *           }
 *         }
 *         container config {
 *           oc-ext:telemetry-on-change;
 *           uses subinterfaces-config;
 *         }
 *         container state {
 *           config false;
 *           uses subinterfaces-config;
 *           uses subinterfaces-state;
 *         }
 *       }
 *     }
 *   }
 *   grouping interfaces-top {
 *     container interfaces {
 *       list interface {
 *         key name;
 *         leaf name {
 *           type leafref {
 *             path ../config/name;
 *           }
 *         }
 *         container config {
 *           oc-ext:telemetry-on-change;
 *           uses interface-phys-config;
 *         }
 *         container state {
 *           config false;
 *           uses interface-phys-config;
 *           uses interface-common-state;
 *           uses interface-counters-state;
 *         }
 *         uses interface-phys-holdtime-top;
 *         uses subinterfaces-top;
 *       }
 *     }
 *   }
 *   uses interfaces-top;
 * }
 * </pre>
 *
 */
@Generated("mdsal-binding-generator")
public interface OpenconfigInterfacesData
    extends
    InterfacesTop,
    DataRoot
{





}

