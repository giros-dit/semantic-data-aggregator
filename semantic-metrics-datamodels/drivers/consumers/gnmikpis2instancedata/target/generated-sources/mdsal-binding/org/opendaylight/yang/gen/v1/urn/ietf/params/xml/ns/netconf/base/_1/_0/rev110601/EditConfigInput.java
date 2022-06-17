package org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.netconf.base._1._0.rev110601;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;
import java.lang.Class;
import java.lang.Integer;
import java.lang.NullPointerException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import javax.annotation.processing.Generated;
import org.eclipse.jdt.annotation.NonNull;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.netconf.base._1._0.rev110601.edit.config.input.EditContent;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.netconf.base._1._0.rev110601.edit.config.input.Target;
import org.opendaylight.yangtools.yang.binding.Augmentable;
import org.opendaylight.yangtools.yang.binding.CodeHelpers;
import org.opendaylight.yangtools.yang.binding.Enumeration;
import org.opendaylight.yangtools.yang.binding.RpcInput;
import org.opendaylight.yangtools.yang.common.QName;

/**
 *
 * <p>
 * This class represents the following YANG schema fragment defined in module <b>ietf-netconf</b>
 * <pre>
 * input input {
 *   container target {
 *     choice config-target {
 *       case candidate {
 *         leaf candidate {
 *           if-feature candidate;
 *           type empty;
 *         }
 *       }
 *       case running {
 *         leaf running {
 *           if-feature writable-running;
 *           type empty;
 *         }
 *       }
 *     }
 *   }
 *   leaf default-operation {
 *     type enumeration {
 *       enum merge {
 *       }
 *       enum replace {
 *       }
 *       enum none {
 *       }
 *     }
 *     default merge;
 *   }
 *   leaf test-option {
 *     if-feature validate;
 *     type enumeration {
 *       enum test-then-set {
 *       }
 *       enum set {
 *       }
 *       enum test-only {
 *       }
 *     }
 *     default test-then-set;
 *   }
 *   leaf error-option {
 *     type enumeration {
 *       enum stop-on-error {
 *       }
 *       enum continue-on-error {
 *       }
 *       enum rollback-on-error {
 *       }
 *     }
 *     default stop-on-error;
 *   }
 *   choice edit-content {
 *     case config {
 *       anyxml config {
 *       }
 *     }
 *     case url {
 *       leaf url {
 *         if-feature url;
 *         type inet:uri;
 *       }
 *     }
 *   }
 * }
 * </pre>The schema path to identify an instance is
 * <i>ietf-netconf/edit-config/input</i>
 *
 */
@Generated("mdsal-binding-generator")
public interface EditConfigInput
    extends
    RpcInput,
    Augmentable<EditConfigInput>
{


    @Generated("mdsal-binding-generator")
    public enum DefaultOperation implements Enumeration {
        /**
         * The default operation is merge.
         */
        Merge(0, "merge"),
        
        /**
         * The default operation is replace.
         */
        Replace(1, "replace"),
        
        /**
         * There is no default operation.
         */
        None(2, "none")
        ;
    
        private static final Map<String, DefaultOperation> NAME_MAP;
        private static final Map<Integer, DefaultOperation> VALUE_MAP;
    
        static {
            final Builder<String, DefaultOperation> nb = ImmutableMap.builder();
            final Builder<Integer, DefaultOperation> vb = ImmutableMap.builder();
            for (DefaultOperation enumItem : DefaultOperation.values()) {
                vb.put(enumItem.value, enumItem);
                nb.put(enumItem.name, enumItem);
            }
    
            NAME_MAP = nb.build();
            VALUE_MAP = vb.build();
        }
    
        private final String name;
        private final int value;
    
        private DefaultOperation(int value, String name) {
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
         * @return corresponding DefaultOperation item, if present
         * @throws NullPointerException if name is null
         */
        public static Optional<DefaultOperation> forName(String name) {
            return Optional.ofNullable(NAME_MAP.get(Objects.requireNonNull(name)));
        }
    
        /**
         * Return the enumeration member whose {@link #getIntValue()} matches specified value.
         *
         * @param intValue integer value
         * @return corresponding DefaultOperation item, or null if no such item exists
         */
        public static DefaultOperation forValue(int intValue) {
            return VALUE_MAP.get(intValue);
        }
    }
    
    @Generated("mdsal-binding-generator")
    public enum TestOption implements Enumeration {
        /**
         * The server will test and then set if no errors.
         */
        TestThenSet(0, "test-then-set"),
        
        /**
         * The server will set without a test first.
         */
        Set(1, "set"),
        
        /**
         * The server will only test and not set, even
         * if there are no errors.
         */
        TestOnly(2, "test-only")
        ;
    
        private static final Map<String, TestOption> NAME_MAP;
        private static final Map<Integer, TestOption> VALUE_MAP;
    
        static {
            final Builder<String, TestOption> nb = ImmutableMap.builder();
            final Builder<Integer, TestOption> vb = ImmutableMap.builder();
            for (TestOption enumItem : TestOption.values()) {
                vb.put(enumItem.value, enumItem);
                nb.put(enumItem.name, enumItem);
            }
    
            NAME_MAP = nb.build();
            VALUE_MAP = vb.build();
        }
    
        private final String name;
        private final int value;
    
        private TestOption(int value, String name) {
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
         * @return corresponding TestOption item, if present
         * @throws NullPointerException if name is null
         */
        public static Optional<TestOption> forName(String name) {
            return Optional.ofNullable(NAME_MAP.get(Objects.requireNonNull(name)));
        }
    
        /**
         * Return the enumeration member whose {@link #getIntValue()} matches specified value.
         *
         * @param intValue integer value
         * @return corresponding TestOption item, or null if no such item exists
         */
        public static TestOption forValue(int intValue) {
            return VALUE_MAP.get(intValue);
        }
    }
    
    @Generated("mdsal-binding-generator")
    public enum ErrorOption implements Enumeration {
        /**
         * The server will stop on errors.
         */
        StopOnError(0, "stop-on-error"),
        
        /**
         * The server may continue on errors.
         */
        ContinueOnError(1, "continue-on-error"),
        
        /**
         * The server will roll back on errors.
         * This value can only be used if the 'rollback-on-error'
         * feature is supported.
         */
        RollbackOnError(2, "rollback-on-error")
        ;
    
        private static final Map<String, ErrorOption> NAME_MAP;
        private static final Map<Integer, ErrorOption> VALUE_MAP;
    
        static {
            final Builder<String, ErrorOption> nb = ImmutableMap.builder();
            final Builder<Integer, ErrorOption> vb = ImmutableMap.builder();
            for (ErrorOption enumItem : ErrorOption.values()) {
                vb.put(enumItem.value, enumItem);
                nb.put(enumItem.name, enumItem);
            }
    
            NAME_MAP = nb.build();
            VALUE_MAP = vb.build();
        }
    
        private final String name;
        private final int value;
    
        private ErrorOption(int value, String name) {
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
         * @return corresponding ErrorOption item, if present
         * @throws NullPointerException if name is null
         */
        public static Optional<ErrorOption> forName(String name) {
            return Optional.ofNullable(NAME_MAP.get(Objects.requireNonNull(name)));
        }
    
        /**
         * Return the enumeration member whose {@link #getIntValue()} matches specified value.
         *
         * @param intValue integer value
         * @return corresponding ErrorOption item, or null if no such item exists
         */
        public static ErrorOption forValue(int intValue) {
            return VALUE_MAP.get(intValue);
        }
    }

    public static final @NonNull QName QNAME = $YangModuleInfoImpl.qnameOf("input");

    @Override
    default Class<org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.netconf.base._1._0.rev110601.EditConfigInput> implementedInterface() {
        return org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.netconf.base._1._0.rev110601.EditConfigInput.class;
    }
    
    /**
     * Default implementation of {@link Object#hashCode()} contract for this interface.
     * Implementations of this interface are encouraged to defer to this method to get consistent hashing
     * results across all implementations.
     *
     * @param obj Object for which to generate hashCode() result.
     * @return Hash code value of data modeled by this interface.
     * @throws NullPointerException if {@code obj} is null
     */
    static int bindingHashCode(final org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.netconf.base._1._0.rev110601.@NonNull EditConfigInput obj) {
        final int prime = 31;
        int result = 1;
        result = prime * result + Objects.hashCode(obj.getDefaultOperation());
        result = prime * result + Objects.hashCode(obj.getEditContent());
        result = prime * result + Objects.hashCode(obj.getErrorOption());
        result = prime * result + Objects.hashCode(obj.getTarget());
        result = prime * result + Objects.hashCode(obj.getTestOption());
        result = prime * result + obj.augmentations().hashCode();
        return result;
    }
    
    /**
     * Default implementation of {@link Object#equals(Object)} contract for this interface.
     * Implementations of this interface are encouraged to defer to this method to get consistent equality
     * results across all implementations.
     *
     * @param thisObj Object acting as the receiver of equals invocation
     * @param obj Object acting as argument to equals invocation
     * @return True if thisObj and obj are considered equal
     * @throws NullPointerException if {@code thisObj} is null
     */
    static boolean bindingEquals(final org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.netconf.base._1._0.rev110601.@NonNull EditConfigInput thisObj, final Object obj) {
        if (thisObj == obj) {
            return true;
        }
        final org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.netconf.base._1._0.rev110601.EditConfigInput other = CodeHelpers.checkCast(org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.netconf.base._1._0.rev110601.EditConfigInput.class, obj);
        if (other == null) {
            return false;
        }
        if (!Objects.equals(thisObj.getDefaultOperation(), other.getDefaultOperation())) {
            return false;
        }
        if (!Objects.equals(thisObj.getEditContent(), other.getEditContent())) {
            return false;
        }
        if (!Objects.equals(thisObj.getErrorOption(), other.getErrorOption())) {
            return false;
        }
        if (!Objects.equals(thisObj.getTarget(), other.getTarget())) {
            return false;
        }
        if (!Objects.equals(thisObj.getTestOption(), other.getTestOption())) {
            return false;
        }
        return thisObj.augmentations().equals(other.augmentations());
    }
    
    /**
     * Default implementation of {@link Object#toString()} contract for this interface.
     * Implementations of this interface are encouraged to defer to this method to get consistent string
     * representations across all implementations.
     *
     * @param obj Object for which to generate toString() result.
     * @return {@link String} value of data modeled by this interface.
     * @throws NullPointerException if {@code obj} is null
     */
    static String bindingToString(final org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.netconf.base._1._0.rev110601.@NonNull EditConfigInput obj) {
        final MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper("EditConfigInput");
        CodeHelpers.appendValue(helper, "defaultOperation", obj.getDefaultOperation());
        CodeHelpers.appendValue(helper, "editContent", obj.getEditContent());
        CodeHelpers.appendValue(helper, "errorOption", obj.getErrorOption());
        CodeHelpers.appendValue(helper, "target", obj.getTarget());
        CodeHelpers.appendValue(helper, "testOption", obj.getTestOption());
        CodeHelpers.appendValue(helper, "augmentation", obj.augmentations().values());
        return helper.toString();
    }
    
    /**
     * Return target, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         Particular configuration to edit.
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.netconf.base._1._0.rev110601.edit.config.input.Target} target, or {@code null} if it is not present.
     *
     */
    Target getTarget();
    
    /**
     * Return defaultOperation, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         The default operation to use.
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.netconf.base._1._0.rev110601.EditConfigInput.DefaultOperation} defaultOperation, or {@code null} if it is not present.
     *
     */
    DefaultOperation getDefaultOperation();
    
    /**
     * Return testOption, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         The test option to use.
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.netconf.base._1._0.rev110601.EditConfigInput.TestOption} testOption, or {@code null} if it is not present.
     *
     */
    TestOption getTestOption();
    
    /**
     * Return errorOption, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         The error option to use.
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.netconf.base._1._0.rev110601.EditConfigInput.ErrorOption} errorOption, or {@code null} if it is not present.
     *
     */
    ErrorOption getErrorOption();
    
    /**
     * Return editContent, or {@code null} if it is not present.
     *
     * <pre>
     *     <code>
     *         The content for the edit operation.
     *     </code>
     * </pre>
     *
     * @return {@code org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.netconf.base._1._0.rev110601.edit.config.input.EditContent} editContent, or {@code null} if it is not present.
     *
     */
    EditContent getEditContent();

}

