package org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.interfaces.top.interfaces;
import java.lang.Class;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import javax.annotation.processing.Generated;
import org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406._interface.phys.holdtime.top.HoldTime;
import org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.interfaces.top.interfaces._interface.Config;
import org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.interfaces.top.interfaces._interface.State;
import org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.subinterfaces.top.Subinterfaces;
import org.opendaylight.yangtools.concepts.Builder;
import org.opendaylight.yangtools.yang.binding.AbstractAugmentable;
import org.opendaylight.yangtools.yang.binding.Augmentation;
import org.opendaylight.yangtools.yang.binding.CodeHelpers;
import org.opendaylight.yangtools.yang.binding.DataObject;

/**
 * Class that builds {@link InterfaceBuilder} instances. Overall design of the class is that of a
 * <a href="https://en.wikipedia.org/wiki/Fluent_interface">fluent interface</a>, where method chaining is used.
 *
 * <p>
 * In general, this class is supposed to be used like this template:
 * <pre>
 *   <code>
 *     InterfaceBuilder createTarget(int fooXyzzy, int barBaz) {
 *         return new InterfaceBuilderBuilder()
 *             .setFoo(new FooBuilder().setXyzzy(fooXyzzy).build())
 *             .setBar(new BarBuilder().setBaz(barBaz).build())
 *             .build();
 *     }
 *   </code>
 * </pre>
 *
 * <p>
 * This pattern is supported by the immutable nature of InterfaceBuilder, as instances can be freely passed around without
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
 * @see InterfaceBuilder
 * @see Builder
 *
 */
@Generated("mdsal-binding-generator")
public class InterfaceBuilder implements Builder<Interface> {

    private Config _config;
    private HoldTime _holdTime;
    private String _name;
    private State _state;
    private Subinterfaces _subinterfaces;
    private InterfaceKey key;


    Map<Class<? extends Augmentation<Interface>>, Augmentation<Interface>> augmentation = Collections.emptyMap();

    public InterfaceBuilder() {
    }
    
    
    
    public InterfaceBuilder(org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.InterfacePhysHoldtimeTop arg) {
        this._holdTime = arg.getHoldTime();
    }
    
    public InterfaceBuilder(org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.SubinterfacesTop arg) {
        this._subinterfaces = arg.getSubinterfaces();
    }
    

    public InterfaceBuilder(Interface base) {
        Map<Class<? extends Augmentation<Interface>>, Augmentation<Interface>> aug = base.augmentations();
        if (!aug.isEmpty()) {
            this.augmentation = new HashMap<>(aug);
        }
        this.key = base.key();
        this._name = base.getName();
        this._config = base.getConfig();
        this._holdTime = base.getHoldTime();
        this._state = base.getState();
        this._subinterfaces = base.getSubinterfaces();
    }

    /**
     * Set fields from given grouping argument. Valid argument is instance of one of following types:
     * <ul>
     * <li>org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.SubinterfacesTop</li>
     * <li>org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.InterfacePhysHoldtimeTop</li>
     * </ul>
     *
     * @param arg grouping object
     * @throws IllegalArgumentException if given argument is none of valid types or has property with incompatible value
    */
    public void fieldsFrom(DataObject arg) {
        boolean isValidArg = false;
        if (arg instanceof org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.SubinterfacesTop) {
            this._subinterfaces = ((org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.SubinterfacesTop)arg).getSubinterfaces();
            isValidArg = true;
        }
        if (arg instanceof org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.InterfacePhysHoldtimeTop) {
            this._holdTime = ((org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.InterfacePhysHoldtimeTop)arg).getHoldTime();
            isValidArg = true;
        }
        CodeHelpers.validValue(isValidArg, arg, "[org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.SubinterfacesTop, org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.InterfacePhysHoldtimeTop]");
    }

    public InterfaceKey key() {
        return key;
    }
    
    public Config getConfig() {
        return _config;
    }
    
    public HoldTime getHoldTime() {
        return _holdTime;
    }
    
    public String getName() {
        return _name;
    }
    
    public State getState() {
        return _state;
    }
    
    public Subinterfaces getSubinterfaces() {
        return _subinterfaces;
    }

    @SuppressWarnings({ "unchecked", "checkstyle:methodTypeParameterName"})
    public <E$$ extends Augmentation<Interface>> E$$ augmentation(Class<E$$> augmentationType) {
        return (E$$) augmentation.get(Objects.requireNonNull(augmentationType));
    }

    public InterfaceBuilder withKey(final InterfaceKey key) {
        this.key = key;
        return this;
    }
    
    public InterfaceBuilder setConfig(final Config value) {
        this._config = value;
        return this;
    }
    
    public InterfaceBuilder setHoldTime(final HoldTime value) {
        this._holdTime = value;
        return this;
    }
    
    public InterfaceBuilder setName(final String value) {
        this._name = value;
        return this;
    }
    
    public InterfaceBuilder setState(final State value) {
        this._state = value;
        return this;
    }
    
    public InterfaceBuilder setSubinterfaces(final Subinterfaces value) {
        this._subinterfaces = value;
        return this;
    }
    
    /**
      * Add an augmentation to this builder's product.
      *
      * @param augmentation augmentation to be added
      * @return this builder
      * @throws NullPointerException if {@code augmentation} is null
      */
    public InterfaceBuilder addAugmentation(Augmentation<Interface> augmentation) {
        Class<? extends Augmentation<Interface>> augmentationType = augmentation.implementedInterface();
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
    public InterfaceBuilder removeAugmentation(Class<? extends Augmentation<Interface>> augmentationType) {
        if (this.augmentation instanceof HashMap) {
            this.augmentation.remove(augmentationType);
        }
        return this;
    }

    @Override
    public Interface build() {
        return new InterfaceImpl(this);
    }

    private static final class InterfaceImpl
        extends AbstractAugmentable<Interface>
        implements Interface {
    
        private final Config _config;
        private final HoldTime _holdTime;
        private final String _name;
        private final State _state;
        private final Subinterfaces _subinterfaces;
        private final InterfaceKey key;
    
        InterfaceImpl(InterfaceBuilder base) {
            super(base.augmentation);
            if (base.key() != null) {
                this.key = base.key();
            } else {
                this.key = new InterfaceKey(base.getName());
            }
            this._name = key.getName();
            this._config = base.getConfig();
            this._holdTime = base.getHoldTime();
            this._state = base.getState();
            this._subinterfaces = base.getSubinterfaces();
        }
    
        @Override
        public InterfaceKey key() {
            return key;
        }
        
        @Override
        public Config getConfig() {
            return _config;
        }
        
        @Override
        public HoldTime getHoldTime() {
            return _holdTime;
        }
        
        @Override
        public String getName() {
            return _name;
        }
        
        @Override
        public State getState() {
            return _state;
        }
        
        @Override
        public Subinterfaces getSubinterfaces() {
            return _subinterfaces;
        }
    
        private int hash = 0;
        private volatile boolean hashValid = false;
        
        @Override
        public int hashCode() {
            if (hashValid) {
                return hash;
            }
        
            final int result = Interface.bindingHashCode(this);
            hash = result;
            hashValid = true;
            return result;
        }
    
        @Override
        public boolean equals(Object obj) {
            return Interface.bindingEquals(this, obj);
        }
    
        @Override
        public String toString() {
            return Interface.bindingToString(this);
        }
    }
}
