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
public enum EditOperationType implements Enumeration {
    /**
     * The configuration data identified by the
     * element containing this attribute is merged
     * with the configuration at the corresponding
     * level in the configuration datastore identified
     * by the target parameter.
     */
    Merge(0, "merge"),
    
    /**
     * The configuration data identified by the element
     * containing this attribute replaces any related
     * configuration in the configuration datastore
     * identified by the target parameter.  If no such
     * configuration data exists in the configuration
     * datastore, it is created.  Unlike a
     * &amp;lt;copy-config&amp;gt; operation, which replaces the
     * entire target configuration, only the configuration
     * actually present in the config parameter is affected.
     */
    Replace(1, "replace"),
    
    /**
     * The configuration data identified by the element
     * containing this attribute is added to the
     * configuration if and only if the configuration
     * data does not already exist in the configuration
     * datastore.  If the configuration data exists, an
     * &amp;lt;rpc-error&amp;gt; element is returned with an
     * &amp;lt;error-tag&amp;gt; value of 'data-exists'.
     */
    Create(2, "create"),
    
    /**
     * The configuration data identified by the element
     * containing this attribute is deleted from the
     * configuration if and only if the configuration
     * data currently exists in the configuration
     * datastore.  If the configuration data does not
     * exist, an &amp;lt;rpc-error&amp;gt; element is returned with
     * an &amp;lt;error-tag&amp;gt; value of 'data-missing'.
     */
    Delete(3, "delete"),
    
    /**
     * The configuration data identified by the element
     * containing this attribute is deleted from the
     * configuration if the configuration
     * data currently exists in the configuration
     * datastore.  If the configuration data does not
     * exist, the 'remove' operation is silently ignored
     * by the server.
     */
    Remove(4, "remove")
    ;

    private static final Map<String, EditOperationType> NAME_MAP;
    private static final Map<Integer, EditOperationType> VALUE_MAP;

    static {
        final Builder<String, EditOperationType> nb = ImmutableMap.builder();
        final Builder<Integer, EditOperationType> vb = ImmutableMap.builder();
        for (EditOperationType enumItem : EditOperationType.values()) {
            vb.put(enumItem.value, enumItem);
            nb.put(enumItem.name, enumItem);
        }

        NAME_MAP = nb.build();
        VALUE_MAP = vb.build();
    }

    private final String name;
    private final int value;

    private EditOperationType(int value, String name) {
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
     * @return corresponding EditOperationType item, if present
     * @throws NullPointerException if name is null
     */
    public static Optional<EditOperationType> forName(String name) {
        return Optional.ofNullable(NAME_MAP.get(Objects.requireNonNull(name)));
    }

    /**
     * Return the enumeration member whose {@link #getIntValue()} matches specified value.
     *
     * @param intValue integer value
     * @return corresponding EditOperationType item, or null if no such item exists
     */
    public static EditOperationType forValue(int intValue) {
        return VALUE_MAP.get(intValue);
    }
}
