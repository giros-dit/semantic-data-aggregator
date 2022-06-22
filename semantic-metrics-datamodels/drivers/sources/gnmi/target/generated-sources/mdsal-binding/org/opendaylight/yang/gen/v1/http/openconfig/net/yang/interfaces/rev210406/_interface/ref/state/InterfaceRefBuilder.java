package org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406._interface.ref.state;
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
import org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406._interface.ref.state.container.State;
import org.opendaylight.yangtools.concepts.Builder;
import org.opendaylight.yangtools.yang.binding.AbstractAugmentable;
import org.opendaylight.yangtools.yang.binding.Augmentation;
import org.opendaylight.yangtools.yang.binding.CodeHelpers;
import org.opendaylight.yangtools.yang.binding.DataObject;

/**
 * Class that builds {@link InterfaceRefBuilder} instances. Overall design of the class is that of a
 * <a href="https://en.wikipedia.org/wiki/Fluent_interface">fluent interface</a>, where method chaining is used.
 *
 * <p>
 * In general, this class is supposed to be used like this template:
 * <pre>
 *   <code>
 *     InterfaceRefBuilder createTarget(int fooXyzzy, int barBaz) {
 *         return new InterfaceRefBuilderBuilder()
 *             .setFoo(new FooBuilder().setXyzzy(fooXyzzy).build())
 *             .setBar(new BarBuilder().setBaz(barBaz).build())
 *             .build();
 *     }
 *   </code>
 * </pre>
 *
 * <p>
 * This pattern is supported by the immutable nature of InterfaceRefBuilder, as instances can be freely passed around without
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
 * @see InterfaceRefBuilder
 * @see Builder
 *
 */
@Generated("mdsal-binding-generator")
public class InterfaceRefBuilder implements Builder<InterfaceRef> {

    private State _state;


    Map<Class<? extends Augmentation<InterfaceRef>>, Augmentation<InterfaceRef>> augmentation = Collections.emptyMap();

    public InterfaceRefBuilder() {
    }
    
    
    
    public InterfaceRefBuilder(org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.InterfaceRefStateContainer arg) {
        this._state = arg.getState();
    }

    public InterfaceRefBuilder(InterfaceRef base) {
        Map<Class<? extends Augmentation<InterfaceRef>>, Augmentation<InterfaceRef>> aug = base.augmentations();
        if (!aug.isEmpty()) {
            this.augmentation = new HashMap<>(aug);
        }
        this._state = base.getState();
    }

    /**
     * Set fields from given grouping argument. Valid argument is instance of one of following types:
     * <ul>
     * <li>org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.InterfaceRefStateContainer</li>
     * </ul>
     *
     * @param arg grouping object
     * @throws IllegalArgumentException if given argument is none of valid types or has property with incompatible value
    */
    public void fieldsFrom(DataObject arg) {
        boolean isValidArg = false;
        if (arg instanceof org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.InterfaceRefStateContainer) {
            this._state = ((org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.InterfaceRefStateContainer)arg).getState();
            isValidArg = true;
        }
        CodeHelpers.validValue(isValidArg, arg, "[org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.InterfaceRefStateContainer]");
    }

    public State getState() {
        return _state;
    }

    @SuppressWarnings({ "unchecked", "checkstyle:methodTypeParameterName"})
    public <E$$ extends Augmentation<InterfaceRef>> E$$ augmentation(Class<E$$> augmentationType) {
        return (E$$) augmentation.get(Objects.requireNonNull(augmentationType));
    }

    
    public InterfaceRefBuilder setState(final State value) {
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
    public InterfaceRefBuilder addAugmentation(Augmentation<InterfaceRef> augmentation) {
        Class<? extends Augmentation<InterfaceRef>> augmentationType = augmentation.implementedInterface();
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
    public InterfaceRefBuilder removeAugmentation(Class<? extends Augmentation<InterfaceRef>> augmentationType) {
        if (this.augmentation instanceof HashMap) {
            this.augmentation.remove(augmentationType);
        }
        return this;
    }

    @Override
    public InterfaceRef build() {
        return new InterfaceRefImpl(this);
    }

    private static final class InterfaceRefImpl
        extends AbstractAugmentable<InterfaceRef>
        implements InterfaceRef {
    
        private final State _state;
    
        InterfaceRefImpl(InterfaceRefBuilder base) {
            super(base.augmentation);
            this._state = base.getState();
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
        
            final int result = InterfaceRef.bindingHashCode(this);
            hash = result;
            hashValid = true;
            return result;
        }
    
        @Override
        public boolean equals(Object obj) {
            return InterfaceRef.bindingEquals(this, obj);
        }
    
        @Override
        public String toString() {
            return InterfaceRef.bindingToString(this);
        }
    }
}
