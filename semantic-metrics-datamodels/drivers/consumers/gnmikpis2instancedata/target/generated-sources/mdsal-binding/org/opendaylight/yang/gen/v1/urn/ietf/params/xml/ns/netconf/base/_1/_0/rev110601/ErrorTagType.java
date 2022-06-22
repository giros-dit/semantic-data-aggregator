package org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.netconf.base._1._0.rev110601;
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
public enum ErrorTagType implements Enumeration {
    /**
     * The request requires a resource that
     * already is in use.
     */
    InUse(0, "in-use"),
    
    /**
     * The request specifies an unacceptable value for one
     * or more parameters.
     */
    InvalidValue(1, "invalid-value"),
    
    /**
     * The request or response (that would be generated) is
     * too large for the implementation to handle.
     */
    TooBig(2, "too-big"),
    
    /**
     * An expected attribute is missing.
     */
    MissingAttribute(3, "missing-attribute"),
    
    /**
     * An attribute value is not correct; e.g., wrong type,
     * out of range, pattern mismatch.
     */
    BadAttribute(4, "bad-attribute"),
    
    /**
     * An unexpected attribute is present.
     */
    UnknownAttribute(5, "unknown-attribute"),
    
    /**
     * An expected element is missing.
     */
    MissingElement(6, "missing-element"),
    
    /**
     * An element value is not correct; e.g., wrong type,
     * out of range, pattern mismatch.
     */
    BadElement(7, "bad-element"),
    
    /**
     * An unexpected element is present.
     */
    UnknownElement(8, "unknown-element"),
    
    /**
     * An unexpected namespace is present.
     */
    UnknownNamespace(9, "unknown-namespace"),
    
    /**
     * Access to the requested protocol operation or
     * data model is denied because authorization failed.
     */
    AccessDenied(10, "access-denied"),
    
    /**
     * Access to the requested lock is denied because the
     * lock is currently held by another entity.
     */
    LockDenied(11, "lock-denied"),
    
    /**
     * Request could not be completed because of
     * insufficient resources.
     */
    ResourceDenied(12, "resource-denied"),
    
    /**
     * Request to roll back some configuration change (via
     * rollback-on-error or &amp;lt;discard-changes&amp;gt; operations)
     * was not completed for some reason.
     */
    RollbackFailed(13, "rollback-failed"),
    
    /**
     * Request could not be completed because the relevant
     * data model content already exists.  For example,
     * a 'create' operation was attempted on data that
     * already exists.
     */
    DataExists(14, "data-exists"),
    
    /**
     * Request could not be completed because the relevant
     * data model content does not exist.  For example,
     * a 'delete' operation was attempted on
     * data that does not exist.
     */
    DataMissing(15, "data-missing"),
    
    /**
     * Request could not be completed because the requested
     * operation is not supported by this implementation.
     */
    OperationNotSupported(16, "operation-not-supported"),
    
    /**
     * Request could not be completed because the requested
     * operation failed for some reason not covered by
     * any other error condition.
     */
    OperationFailed(17, "operation-failed"),
    
    /**
     * This error-tag is obsolete, and SHOULD NOT be sent
     * by servers conforming to this document.
     */
    PartialOperation(18, "partial-operation"),
    
    /**
     * A message could not be handled because it failed to
     * be parsed correctly.  For example, the message is not
     * well-formed XML or it uses an invalid character set.
     */
    MalformedMessage(19, "malformed-message")
    ;

    private static final Map<String, ErrorTagType> NAME_MAP;
    private static final Map<Integer, ErrorTagType> VALUE_MAP;

    static {
        final Builder<String, ErrorTagType> nb = ImmutableMap.builder();
        final Builder<Integer, ErrorTagType> vb = ImmutableMap.builder();
        for (ErrorTagType enumItem : ErrorTagType.values()) {
            vb.put(enumItem.value, enumItem);
            nb.put(enumItem.name, enumItem);
        }

        NAME_MAP = nb.build();
        VALUE_MAP = vb.build();
    }

    private final String name;
    private final int value;

    private ErrorTagType(int value, String name) {
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
     * @return corresponding ErrorTagType item, if present
     * @throws NullPointerException if name is null
     */
    public static Optional<ErrorTagType> forName(String name) {
        return Optional.ofNullable(NAME_MAP.get(Objects.requireNonNull(name)));
    }

    /**
     * Return the enumeration member whose {@link #getIntValue()} matches specified value.
     *
     * @param intValue integer value
     * @return corresponding ErrorTagType item, or null if no such item exists
     */
    public static ErrorTagType forValue(int intValue) {
        return VALUE_MAP.get(intValue);
    }
}
