package org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.subinterfaces.top.subinterfaces.subinterface;
import java.lang.Boolean;
import java.lang.Class;
import java.lang.Deprecated;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import javax.annotation.processing.Generated;
import org.opendaylight.yangtools.concepts.Builder;
import org.opendaylight.yangtools.yang.binding.AbstractAugmentable;
import org.opendaylight.yangtools.yang.binding.Augmentation;
import org.opendaylight.yangtools.yang.binding.CodeHelpers;
import org.opendaylight.yangtools.yang.binding.DataObject;
import org.opendaylight.yangtools.yang.common.Uint32;

/**
 * Class that builds {@link ConfigBuilder} instances. Overall design of the class is that of a
 * <a href="https://en.wikipedia.org/wiki/Fluent_interface">fluent interface</a>, where method chaining is used.
 *
 * <p>
 * In general, this class is supposed to be used like this template:
 * <pre>
 *   <code>
 *     ConfigBuilder createTarget(int fooXyzzy, int barBaz) {
 *         return new ConfigBuilderBuilder()
 *             .setFoo(new FooBuilder().setXyzzy(fooXyzzy).build())
 *             .setBar(new BarBuilder().setBaz(barBaz).build())
 *             .build();
 *     }
 *   </code>
 * </pre>
 *
 * <p>
 * This pattern is supported by the immutable nature of ConfigBuilder, as instances can be freely passed around without
 * worrying about synchronization issues.
 *
 * <p>
 * As a side note: method chaining results in:
 * <ul>
 *   <li>very efficient Java bytecode, as the method invocation result, in this case the Builder reference, is
 *       on the stack, so further method invocations just need to fill method arguments for the next method
 *       invocation, which is terminated by {@link #build()}, which is then returned from the method</li>
 *   <li>better understanding by humans, as the scope of mutable state (the builder) is kept to a minimum and is
 *       very localized</li>
 *   <li>better optimization oportunities, as the object scope is minimized in terms of invocation (rather than
 *       method) stack, making <a href="https://en.wikipedia.org/wiki/Escape_analysis">escape analysis</a> a lot
 *       easier. Given enough compiler (JIT/AOT) prowess, the cost of th builder object can be completely
 *       eliminated</li>
 * </ul>
 *
 * @see ConfigBuilder
 * @see Builder
 *
 */
@Generated("mdsal-binding-generator")
public class ConfigBuilder implements Builder<Config> {

    private String _description;
    private Boolean _enabled;
    private Uint32 _index;


    Map<Class<? extends Augmentation<Config>>, Augmentation<Config>> augmentation = Collections.emptyMap();

    public ConfigBuilder() {
    }
    
    
    
    public ConfigBuilder(org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.SubinterfacesConfig arg) {
        this._index = arg.getIndex();
        this._description = arg.getDescription();
        this._enabled = arg.getEnabled();
    }
    public ConfigBuilder(org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.InterfaceCommonConfig arg) {
        this._description = arg.getDescription();
        this._enabled = arg.getEnabled();
    }

    public ConfigBuilder(Config base) {
        Map<Class<? extends Augmentation<Config>>, Augmentation<Config>> aug = base.augmentations();
        if (!aug.isEmpty()) {
            this.augmentation = new HashMap<>(aug);
        }
        this._description = base.getDescription();
        this._enabled = base.getEnabled();
        this._index = base.getIndex();
    }

    /**
     * Set fields from given grouping argument. Valid argument is instance of one of following types:
     * <ul>
     * <li>org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.SubinterfacesConfig</li>
     * <li>org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.InterfaceCommonConfig</li>
     * </ul>
     *
     * @param arg grouping object
     * @throws IllegalArgumentException if given argument is none of valid types or has property with incompatible value
    */
    public void fieldsFrom(DataObject arg) {
        boolean isValidArg = false;
        if (arg instanceof org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.SubinterfacesConfig) {
            this._index = ((org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.SubinterfacesConfig)arg).getIndex();
            isValidArg = true;
        }
        if (arg instanceof org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.InterfaceCommonConfig) {
            this._description = ((org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.InterfaceCommonConfig)arg).getDescription();
            this._enabled = ((org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.InterfaceCommonConfig)arg).getEnabled();
            isValidArg = true;
        }
        CodeHelpers.validValue(isValidArg, arg, "[org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.SubinterfacesConfig, org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.InterfaceCommonConfig]");
    }

    public String getDescription() {
        return _description;
    }
    
    public Boolean getEnabled() {
        return _enabled;
    }
    
    @Deprecated(forRemoval = true)
    public final Boolean isEnabled() {
        return getEnabled();
    }
    
    public Uint32 getIndex() {
        return _index;
    }

    @SuppressWarnings({ "unchecked", "checkstyle:methodTypeParameterName"})
    public <E$$ extends Augmentation<Config>> E$$ augmentation(Class<E$$> augmentationType) {
        return (E$$) augmentation.get(Objects.requireNonNull(augmentationType));
    }

    
    public ConfigBuilder setDescription(final String value) {
        this._description = value;
        return this;
    }
    
    public ConfigBuilder setEnabled(final Boolean value) {
        this._enabled = value;
        return this;
    }
    
    public ConfigBuilder setIndex(final Uint32 value) {
        this._index = value;
        return this;
    }
    
    /**
     * Utility migration setter.
     *
     * @param value field value in legacy type
     * @return this builder
     * @deprecated Use {@link #setIndex(Uint32)} instead.
     */
    @Deprecated(forRemoval = true)
    public ConfigBuilder setIndex(final Long value) {
        return setIndex(CodeHelpers.compatUint(value));
    }
    
    /**
      * Add an augmentation to this builder's product.
      *
      * @param augmentation augmentation to be added
      * @return this builder
      * @throws NullPointerException if {@code augmentation} is null
      */
    public ConfigBuilder addAugmentation(Augmentation<Config> augmentation) {
        Class<? extends Augmentation<Config>> augmentationType = augmentation.implementedInterface();
        if (!(this.augmentation instanceof HashMap)) {
            this.augmentation = new HashMap<>();
        }
    
        this.augmentation.put(augmentationType, augmentation);
        return this;
    }
    
    /**
      * Remove an augmentation from this builder's product. If this builder does not track such an augmentation
      * type, this method does nothing.
      *
      * @param augmentationType augmentation type to be removed
      * @return this builder
      */
    public ConfigBuilder removeAugmentation(Class<? extends Augmentation<Config>> augmentationType) {
        if (this.augmentation instanceof HashMap) {
            this.augmentation.remove(augmentationType);
        }
        return this;
    }

    @Override
    public Config build() {
        return new ConfigImpl(this);
    }

    private static final class ConfigImpl
        extends AbstractAugmentable<Config>
        implements Config {
    
        private final String _description;
        private final Boolean _enabled;
        private final Uint32 _index;
    
        ConfigImpl(ConfigBuilder base) {
            super(base.augmentation);
            this._description = base.getDescription();
            this._enabled = base.getEnabled();
            this._index = base.getIndex();
        }
    
        @Override
        public String getDescription() {
            return _description;
        }
        
        @Override
        public Boolean getEnabled() {
            return _enabled;
        }
        
        @Override
        public Uint32 getIndex() {
            return _index;
        }
    
        private int hash = 0;
        private volatile boolean hashValid = false;
        
        @Override
        public int hashCode() {
            if (hashValid) {
                return hash;
            }
        
            final int result = Config.bindingHashCode(this);
            hash = result;
            hashValid = true;
            return result;
        }
    
        @Override
        public boolean equals(Object obj) {
            return Config.bindingEquals(this, obj);
        }
    
        @Override
        public String toString() {
            return Config.bindingToString(this);
        }
    }
}
