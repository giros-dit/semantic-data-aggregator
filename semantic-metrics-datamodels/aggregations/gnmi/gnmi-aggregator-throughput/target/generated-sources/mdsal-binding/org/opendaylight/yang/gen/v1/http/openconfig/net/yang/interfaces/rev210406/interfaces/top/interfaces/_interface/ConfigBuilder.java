package org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.interfaces.top.interfaces._interface;
import java.lang.Boolean;
import java.lang.Class;
import java.lang.Deprecated;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import javax.annotation.processing.Generated;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.interfaces.rev180220.InterfaceType;
import org.opendaylight.yangtools.concepts.Builder;
import org.opendaylight.yangtools.yang.binding.AbstractAugmentable;
import org.opendaylight.yangtools.yang.binding.Augmentation;
import org.opendaylight.yangtools.yang.binding.CodeHelpers;
import org.opendaylight.yangtools.yang.binding.DataObject;
import org.opendaylight.yangtools.yang.common.Uint16;

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
    private Boolean _loopbackMode;
    private Uint16 _mtu;
    private String _name;
    private Class<? extends InterfaceType> _type;


    Map<Class<? extends Augmentation<Config>>, Augmentation<Config>> augmentation = Collections.emptyMap();

    public ConfigBuilder() {
    }
    
    
    
    public ConfigBuilder(org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.InterfacePhysConfig arg) {
        this._name = arg.getName();
        this._type = arg.getType();
        this._mtu = arg.getMtu();
        this._loopbackMode = arg.getLoopbackMode();
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
        this._loopbackMode = base.getLoopbackMode();
        this._mtu = base.getMtu();
        this._name = base.getName();
        this._type = base.getType();
    }

    /**
     * Set fields from given grouping argument. Valid argument is instance of one of following types:
     * <ul>
     * <li>org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.InterfaceCommonConfig</li>
     * <li>org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.InterfacePhysConfig</li>
     * </ul>
     *
     * @param arg grouping object
     * @throws IllegalArgumentException if given argument is none of valid types or has property with incompatible value
    */
    public void fieldsFrom(DataObject arg) {
        boolean isValidArg = false;
        if (arg instanceof org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.InterfaceCommonConfig) {
            this._description = ((org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.InterfaceCommonConfig)arg).getDescription();
            this._enabled = ((org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.InterfaceCommonConfig)arg).getEnabled();
            isValidArg = true;
        }
        if (arg instanceof org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.InterfacePhysConfig) {
            this._name = ((org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.InterfacePhysConfig)arg).getName();
            this._type = ((org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.InterfacePhysConfig)arg).getType();
            this._mtu = ((org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.InterfacePhysConfig)arg).getMtu();
            this._loopbackMode = ((org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.InterfacePhysConfig)arg).getLoopbackMode();
            isValidArg = true;
        }
        CodeHelpers.validValue(isValidArg, arg, "[org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.InterfaceCommonConfig, org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.InterfacePhysConfig]");
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
    
    public Boolean getLoopbackMode() {
        return _loopbackMode;
    }
    
    @Deprecated(forRemoval = true)
    public final Boolean isLoopbackMode() {
        return getLoopbackMode();
    }
    
    public Uint16 getMtu() {
        return _mtu;
    }
    
    public String getName() {
        return _name;
    }
    
    public Class<? extends InterfaceType> getType() {
        return _type;
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
    
    public ConfigBuilder setLoopbackMode(final Boolean value) {
        this._loopbackMode = value;
        return this;
    }
    
    public ConfigBuilder setMtu(final Uint16 value) {
        this._mtu = value;
        return this;
    }
    
    /**
     * Utility migration setter.
     *
     * @param value field value in legacy type
     * @return this builder
     * @deprecated Use {@link #setMtu(Uint16)} instead.
     */
    @Deprecated(forRemoval = true)
    public ConfigBuilder setMtu(final Integer value) {
        return setMtu(CodeHelpers.compatUint(value));
    }
    
    public ConfigBuilder setName(final String value) {
        this._name = value;
        return this;
    }
    
    public ConfigBuilder setType(final Class<? extends InterfaceType> value) {
        this._type = value;
        return this;
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
        private final Boolean _loopbackMode;
        private final Uint16 _mtu;
        private final String _name;
        private final Class<? extends InterfaceType> _type;
    
        ConfigImpl(ConfigBuilder base) {
            super(base.augmentation);
            this._description = base.getDescription();
            this._enabled = base.getEnabled();
            this._loopbackMode = base.getLoopbackMode();
            this._mtu = base.getMtu();
            this._name = base.getName();
            this._type = base.getType();
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
        public Boolean getLoopbackMode() {
            return _loopbackMode;
        }
        
        @Override
        public Uint16 getMtu() {
            return _mtu;
        }
        
        @Override
        public String getName() {
            return _name;
        }
        
        @Override
        public Class<? extends InterfaceType> getType() {
            return _type;
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
