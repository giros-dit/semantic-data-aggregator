package org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.sub.unnumbered.top;
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
import org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406._interface.ref.InterfaceRef;
import org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.sub.unnumbered.top.unnumbered.Config;
import org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.sub.unnumbered.top.unnumbered.State;
import org.opendaylight.yangtools.concepts.Builder;
import org.opendaylight.yangtools.yang.binding.AbstractAugmentable;
import org.opendaylight.yangtools.yang.binding.Augmentation;
import org.opendaylight.yangtools.yang.binding.CodeHelpers;
import org.opendaylight.yangtools.yang.binding.DataObject;

/**
 * Class that builds {@link UnnumberedBuilder} instances. Overall design of the class is that of a
 * <a href="https://en.wikipedia.org/wiki/Fluent_interface">fluent interface</a>, where method chaining is used.
 *
 * <p>
 * In general, this class is supposed to be used like this template:
 * <pre>
 *   <code>
 *     UnnumberedBuilder createTarget(int fooXyzzy, int barBaz) {
 *         return new UnnumberedBuilderBuilder()
 *             .setFoo(new FooBuilder().setXyzzy(fooXyzzy).build())
 *             .setBar(new BarBuilder().setBaz(barBaz).build())
 *             .build();
 *     }
 *   </code>
 * </pre>
 *
 * <p>
 * This pattern is supported by the immutable nature of UnnumberedBuilder, as instances can be freely passed around without
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
 * @see UnnumberedBuilder
 * @see Builder
 *
 */
@Generated("mdsal-binding-generator")
public class UnnumberedBuilder implements Builder<Unnumbered> {

    private Config _config;
    private InterfaceRef _interfaceRef;
    private State _state;


    Map<Class<? extends Augmentation<Unnumbered>>, Augmentation<Unnumbered>> augmentation = Collections.emptyMap();

    public UnnumberedBuilder() {
    }
    
    
    
    public UnnumberedBuilder(org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.InterfaceRef arg) {
        this._interfaceRef = arg.getInterfaceRef();
    }

    public UnnumberedBuilder(Unnumbered base) {
        Map<Class<? extends Augmentation<Unnumbered>>, Augmentation<Unnumbered>> aug = base.augmentations();
        if (!aug.isEmpty()) {
            this.augmentation = new HashMap<>(aug);
        }
        this._config = base.getConfig();
        this._interfaceRef = base.getInterfaceRef();
        this._state = base.getState();
    }

    /**
     * Set fields from given grouping argument. Valid argument is instance of one of following types:
     * <ul>
     * <li>org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.InterfaceRef</li>
     * </ul>
     *
     * @param arg grouping object
     * @throws IllegalArgumentException if given argument is none of valid types or has property with incompatible value
    */
    public void fieldsFrom(DataObject arg) {
        boolean isValidArg = false;
        if (arg instanceof org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.InterfaceRef) {
            this._interfaceRef = ((org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.InterfaceRef)arg).getInterfaceRef();
            isValidArg = true;
        }
        CodeHelpers.validValue(isValidArg, arg, "[org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.InterfaceRef]");
    }

    public Config getConfig() {
        return _config;
    }
    
    public InterfaceRef getInterfaceRef() {
        return _interfaceRef;
    }
    
    public State getState() {
        return _state;
    }

    @SuppressWarnings({ "unchecked", "checkstyle:methodTypeParameterName"})
    public <E$$ extends Augmentation<Unnumbered>> E$$ augmentation(Class<E$$> augmentationType) {
        return (E$$) augmentation.get(Objects.requireNonNull(augmentationType));
    }

    
    public UnnumberedBuilder setConfig(final Config value) {
        this._config = value;
        return this;
    }
    
    public UnnumberedBuilder setInterfaceRef(final InterfaceRef value) {
        this._interfaceRef = value;
        return this;
    }
    
    public UnnumberedBuilder setState(final State value) {
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
    public UnnumberedBuilder addAugmentation(Augmentation<Unnumbered> augmentation) {
        Class<? extends Augmentation<Unnumbered>> augmentationType = augmentation.implementedInterface();
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
    public UnnumberedBuilder removeAugmentation(Class<? extends Augmentation<Unnumbered>> augmentationType) {
        if (this.augmentation instanceof HashMap) {
            this.augmentation.remove(augmentationType);
        }
        return this;
    }

    @Override
    public Unnumbered build() {
        return new UnnumberedImpl(this);
    }

    private static final class UnnumberedImpl
        extends AbstractAugmentable<Unnumbered>
        implements Unnumbered {
    
        private final Config _config;
        private final InterfaceRef _interfaceRef;
        private final State _state;
    
        UnnumberedImpl(UnnumberedBuilder base) {
            super(base.augmentation);
            this._config = base.getConfig();
            this._interfaceRef = base.getInterfaceRef();
            this._state = base.getState();
        }
    
        @Override
        public Config getConfig() {
            return _config;
        }
        
        @Override
        public InterfaceRef getInterfaceRef() {
            return _interfaceRef;
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
        
            final int result = Unnumbered.bindingHashCode(this);
            hash = result;
            hashValid = true;
            return result;
        }
    
        @Override
        public boolean equals(Object obj) {
            return Unnumbered.bindingEquals(this, obj);
        }
    
        @Override
        public String toString() {
            return Unnumbered.bindingToString(this);
        }
    }
}
