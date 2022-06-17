package org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.interfaces.rev180220;
import java.lang.Deprecated;
import javax.annotation.processing.Generated;
import org.opendaylight.yangtools.yang.binding.DataRoot;

/**
 * This module contains a collection of YANG definitions for managing network 
 * interfaces. Copyright (c) 2018 IETF Trust and the persons identified as authors 
 * of the code. All rights reserved. Redistribution and use in source and binary 
 * forms, with or without modification, is permitted pursuant to, and subject to 
 * the license terms contained in, the Simplified BSD License set forth in Section 
 * 4.c of the IETF Trust's Legal Provisions Relating to IETF Documents 
 * (https://trustee.ietf.org/license-info). This version of this YANG module is 
 * part of RFC 8343; see the RFC itself for full legal notices.
 *
 * <p>
 * This class represents the following YANG schema fragment defined in module <b>ietf-interfaces</b>
 * <pre>
 * module ietf-interfaces {
 *   yang-version 1.1;
 *   namespace urn:ietf:params:xml:ns:yang:ietf-interfaces;
 *   prefix if;
 *   import ietf-yang-types {
 *     prefix yang;
 *   }
 *   revision 2018-02-20 {
 *   }
 *   revision 2014-05-08 {
 *   }
 *   typedef interface-ref {
 *     type leafref {
 *       path /if:interfaces/if:interface/if:name;
 *     }
 *   }
 *   identity interface-type {
 *   }
 *   feature arbitrary-names {
 *   }
 *   feature pre-provisioning {
 *   }
 *   feature if-mib {
 *   }
 *   container interfaces {
 *     list interface {
 *       key name;
 *       leaf name {
 *         type string;
 *       }
 *       leaf description {
 *         type string;
 *       }
 *       leaf type {
 *         type identityref {
 *           base interface-type;
 *         }
 *       }
 *       leaf enabled {
 *         type boolean;
 *         default true;
 *       }
 *       leaf link-up-down-trap-enable {
 *         if-feature if-mib;
 *         type enumeration {
 *           enum enabled {
 *             value 1;
 *           }
 *           enum disabled {
 *             value 2;
 *           }
 *         }
 *       }
 *       leaf admin-status {
 *         if-feature if-mib;
 *         type enumeration {
 *           enum up {
 *             value 1;
 *           }
 *           enum down {
 *             value 2;
 *           }
 *           enum testing {
 *             value 3;
 *           }
 *         }
 *         config false;
 *       }
 *       leaf oper-status {
 *         type enumeration {
 *           enum up {
 *             value 1;
 *           }
 *           enum down {
 *             value 2;
 *           }
 *           enum testing {
 *             value 3;
 *           }
 *           enum unknown {
 *             value 4;
 *           }
 *           enum dormant {
 *             value 5;
 *           }
 *           enum not-present {
 *             value 6;
 *           }
 *           enum lower-layer-down {
 *             value 7;
 *           }
 *         }
 *         config false;
 *       }
 *       leaf last-change {
 *         type yang:date-and-time;
 *         config false;
 *       }
 *       leaf if-index {
 *         if-feature if-mib;
 *         type int32 {
 *           range 1..2147483647;
 *         }
 *         config false;
 *       }
 *       leaf phys-address {
 *         type yang:phys-address;
 *         config false;
 *       }
 *       leaf-list higher-layer-if {
 *         type interface-ref;
 *         config false;
 *       }
 *       leaf-list lower-layer-if {
 *         type interface-ref;
 *         config false;
 *       }
 *       leaf speed {
 *         type yang:gauge64;
 *         units bits/second;
 *         config false;
 *       }
 *       container statistics {
 *         config false;
 *         leaf discontinuity-time {
 *           type yang:date-and-time;
 *         }
 *         leaf in-octets {
 *           type yang:counter64;
 *         }
 *         leaf in-unicast-pkts {
 *           type yang:counter64;
 *         }
 *         leaf in-broadcast-pkts {
 *           type yang:counter64;
 *         }
 *         leaf in-multicast-pkts {
 *           type yang:counter64;
 *         }
 *         leaf in-discards {
 *           type yang:counter32;
 *         }
 *         leaf in-errors {
 *           type yang:counter32;
 *         }
 *         leaf in-unknown-protos {
 *           type yang:counter32;
 *         }
 *         leaf out-octets {
 *           type yang:counter64;
 *         }
 *         leaf out-unicast-pkts {
 *           type yang:counter64;
 *         }
 *         leaf out-broadcast-pkts {
 *           type yang:counter64;
 *         }
 *         leaf out-multicast-pkts {
 *           type yang:counter64;
 *         }
 *         leaf out-discards {
 *           type yang:counter32;
 *         }
 *         leaf out-errors {
 *           type yang:counter32;
 *         }
 *       }
 *     }
 *   }
 *   typedef interface-state-ref {
 *     type leafref {
 *       path /if:interfaces-state/if:interface/if:name;
 *     }
 *     status deprecated;
 *   }
 *   container interfaces-state {
 *     config false;
 *     status deprecated;
 *     list interface {
 *       key name;
 *       status deprecated;
 *       leaf name {
 *         type string;
 *         status deprecated;
 *       }
 *       leaf type {
 *         type identityref {
 *           base interface-type;
 *         }
 *         status deprecated;
 *       }
 *       leaf admin-status {
 *         if-feature if-mib;
 *         type enumeration {
 *           enum up {
 *             value 1;
 *           }
 *           enum down {
 *             value 2;
 *           }
 *           enum testing {
 *             value 3;
 *           }
 *         }
 *         status deprecated;
 *       }
 *       leaf oper-status {
 *         type enumeration {
 *           enum up {
 *             value 1;
 *           }
 *           enum down {
 *             value 2;
 *           }
 *           enum testing {
 *             value 3;
 *           }
 *           enum unknown {
 *             value 4;
 *           }
 *           enum dormant {
 *             value 5;
 *           }
 *           enum not-present {
 *             value 6;
 *           }
 *           enum lower-layer-down {
 *             value 7;
 *           }
 *         }
 *         status deprecated;
 *       }
 *       leaf last-change {
 *         type yang:date-and-time;
 *         status deprecated;
 *       }
 *       leaf if-index {
 *         if-feature if-mib;
 *         type int32 {
 *           range 1..2147483647;
 *         }
 *         status deprecated;
 *       }
 *       leaf phys-address {
 *         type yang:phys-address;
 *         status deprecated;
 *       }
 *       leaf-list higher-layer-if {
 *         type interface-state-ref;
 *         status deprecated;
 *       }
 *       leaf-list lower-layer-if {
 *         type interface-state-ref;
 *         status deprecated;
 *       }
 *       leaf speed {
 *         type yang:gauge64;
 *         units bits/second;
 *         status deprecated;
 *       }
 *       container statistics {
 *         status deprecated;
 *         leaf discontinuity-time {
 *           type yang:date-and-time;
 *           status deprecated;
 *         }
 *         leaf in-octets {
 *           type yang:counter64;
 *           status deprecated;
 *         }
 *         leaf in-unicast-pkts {
 *           type yang:counter64;
 *           status deprecated;
 *         }
 *         leaf in-broadcast-pkts {
 *           type yang:counter64;
 *           status deprecated;
 *         }
 *         leaf in-multicast-pkts {
 *           type yang:counter64;
 *           status deprecated;
 *         }
 *         leaf in-discards {
 *           type yang:counter32;
 *           status deprecated;
 *         }
 *         leaf in-errors {
 *           type yang:counter32;
 *           status deprecated;
 *         }
 *         leaf in-unknown-protos {
 *           type yang:counter32;
 *           status deprecated;
 *         }
 *         leaf out-octets {
 *           type yang:counter64;
 *           status deprecated;
 *         }
 *         leaf out-unicast-pkts {
 *           type yang:counter64;
 *           status deprecated;
 *         }
 *         leaf out-broadcast-pkts {
 *           type yang:counter64;
 *           status deprecated;
 *         }
 *         leaf out-multicast-pkts {
 *           type yang:counter64;
 *           status deprecated;
 *         }
 *         leaf out-discards {
 *           type yang:counter32;
 *           status deprecated;
 *         }
 *         leaf out-errors {
 *           type yang:counter32;
 *           status deprecated;
 *         }
 *       }
 *     }
 *   }
 * }
 * </pre>
 *
 */
@Generated("mdsal-binding-generator")
public interface IetfInterfacesData
    extends
    DataRoot
{




    /**
     * Return interfaces, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Interface parameters.
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.interfaces.rev180220.Interfaces} interfaces, or {@code null} if it is not present.
     *
     */
    Interfaces getInterfaces();
    
    /**
     * Return interfacesState, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Data nodes for the operational state of interfaces.
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.interfaces.rev180220.InterfacesState} interfacesState, or {@code null} if it is not present.
     *
     */
    @Deprecated
    InterfacesState getInterfacesState();

}

