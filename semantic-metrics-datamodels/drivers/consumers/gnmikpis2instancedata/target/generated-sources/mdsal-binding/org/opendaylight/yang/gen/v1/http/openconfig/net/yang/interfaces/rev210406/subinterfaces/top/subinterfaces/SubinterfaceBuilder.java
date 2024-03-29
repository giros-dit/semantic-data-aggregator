package org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.subinterfaces.top.subinterfaces;
import java.lang.Class;
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
import org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.subinterfaces.top.subinterfaces.subinterface.Config;
import org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.subinterfaces.top.subinterfaces.subinterface.State;
import org.opendaylight.yangtools.concepts.Builder;
import org.opendaylight.yangtools.yang.binding.AbstractAugmentable;
import org.opendaylight.yangtools.yang.binding.Augmentation;
import org.opendaylight.yangtools.yang.binding.CodeHelpers;
import org.opendaylight.yangtools.yang.common.Uint32;

/**
 * Class that builds {@link SubinterfaceBuilder} instances. Overall design of the class is that of a
 * <a href="https://en.wikipedia.org/wiki/Fluent_interface">fluent interface</a>, where method chaining is used.
 *
 * <p>
 * In general, this class is supposed to be used like this template:
 * <pre>
 *   <code>
 *     SubinterfaceBuilder createTarget(int fooXyzzy, int barBaz) {
 *         return new SubinterfaceBuilderBuilder()
 *             .setFoo(new FooBuilder().setXyzzy(fooXyzzy).build())
 *             .setBar(new BarBuilder().setBaz(barBaz).build())
 *             .build();
 *     }
 *   </code>
 * </pre>
 *
 * <p>
 * This pattern is supported by the immutable nature of SubinterfaceBuilder, as instances can be freely passed around without
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
 * @see SubinterfaceBuilder
 * @see Builder
 *
 */
@Generated("mdsal-binding-generator")
public class SubinterfaceBuilder implements Builder<Subinterface> {

    private Config _config;
    private Uint32 _index;
    private State _state;
    private SubinterfaceKey key;


    Map<Class<? extends Augmentation<Subinterface>>, Augmentation<Subinterface>> augmentation = Collections.emptyMap();

    public SubinterfaceBuilder() {
    }
    
    
    

    public SubinterfaceBuilder(Subinterface base) {
        Map<Class<? extends Augmentation<Subinterface>>, Augmentation<Subinterface>> aug = base.augmentations();
        if (!aug.isEmpty()) {
            this.augmentation = new HashMap<>(aug);
        }
        this.key = base.key();
        this._index = base.getIndex();
        this._config = base.getConfig();
        this._state = base.getState();
    }


    public SubinterfaceKey key() {
        return key;
    }
    
    public Config getConfig() {
        return _config;
    }
    
    public Uint32 getIndex() {
        return _index;
    }
    
    public State getState() {
        return _state;
    }

    @SuppressWarnings({ "unchecked", "checkstyle:methodTypeParameterName"})
    public <E$$ extends Augmentation<Subinterface>> E$$ augmentation(Class<E$$> augmentationType) {
        return (E$$) augmentation.get(Objects.requireNonNull(augmentationType));
    }

    public SubinterfaceBuilder withKey(final SubinterfaceKey key) {
        this.key = key;
        return this;
    }
    
    public SubinterfaceBuilder setConfig(final Config value) {
        this._config = value;
        return this;
    }
    
    public SubinterfaceBuilder setIndex(final Uint32 value) {
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
    public SubinterfaceBuilder setIndex(final Long value) {
        return setIndex(CodeHelpers.compatUint(value));
    }
    
    public SubinterfaceBuilder setState(final State value) {
        this._state = value;
        return this;
    }
    
    /**
      * Add an augmentation to this builder's product.
      *
      * @param augmentation augmentation to be added
      * @return this builder
      * @throws NullPointerException if {@code augmentation} is null
      */
    public SubinterfaceBuilder addAugmentation(Augmentation<Subinterface> augmentation) {
        Class<? extends Augmentation<Subinterface>> augmentationType = augmentation.implementedInterface();
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
    public SubinterfaceBuilder removeAugmentation(Class<? extends Augmentation<Subinterface>> augmentationType) {
        if (this.augmentation instanceof HashMap) {
            this.augmentation.remove(augmentationType);
        }
        return this;
    }

    @Override
    public Subinterface build() {
        return new SubinterfaceImpl(this);
    }

    private static final class SubinterfaceImpl
        extends AbstractAugmentable<Subinterface>
        implements Subinterface {
    
        private final Config _config;
        private final Uint32 _index;
        private final State _state;
        private final SubinterfaceKey key;
    
        SubinterfaceImpl(SubinterfaceBuilder base) {
            super(base.augmentation);
            if (base.key() != null) {
                this.key = base.key();
            } else {
                this.key = new SubinterfaceKey(base.getIndex());
            }
            this._index = key.getIndex();
            this._config = base.getConfig();
            this._state = base.getState();
        }
    
        @Override
        public SubinterfaceKey key() {
            return key;
        }
        
        @Override
        public Config getConfig() {
            return _config;
        }
        
        @Override
        public Uint32 getIndex() {
            return _index;
        }
        
        @Override
        public State getState() {
            return _state;
        }
    
        private int hash = 0;
        private volatile boolean hashValid = false;
        
        @Override
        public int hashCode() {
            if (hashValid) {
                return hash;
            }
        
            final int result = Subinterface.bindingHashCode(this);
            hash = result;
            hashValid = true;
            return result;
        }
    
        @Override
        public boolean equals(Object obj) {
            return Subinterface.bindingEquals(this, obj);
        }
    
        @Override
        public String toString() {
            return Subinterface.bindingToString(this);
        }
    }
}
