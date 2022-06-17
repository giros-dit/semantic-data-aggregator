package org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.subinterfaces.top;
import java.lang.Class;
import java.lang.Deprecated;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.annotation.processing.Generated;
import org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.subinterfaces.top.subinterfaces.Subinterface;
import org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.subinterfaces.top.subinterfaces.SubinterfaceKey;
import org.opendaylight.yangtools.concepts.Builder;
import org.opendaylight.yangtools.yang.binding.AbstractAugmentable;
import org.opendaylight.yangtools.yang.binding.Augmentation;
import org.opendaylight.yangtools.yang.binding.CodeHelpers;

/**
 * Class that builds {@link SubinterfacesBuilder} instances. Overall design of the class is that of a
 * <a href="https://en.wikipedia.org/wiki/Fluent_interface">fluent interface</a>, where method chaining is used.
 *
 * <p>
 * In general, this class is supposed to be used like this template:
 * <pre>
 *   <code>
 *     SubinterfacesBuilder createTarget(int fooXyzzy, int barBaz) {
 *         return new SubinterfacesBuilderBuilder()
 *             .setFoo(new FooBuilder().setXyzzy(fooXyzzy).build())
 *             .setBar(new BarBuilder().setBaz(barBaz).build())
 *             .build();
 *     }
 *   </code>
 * </pre>
 *
 * <p>
 * This pattern is supported by the immutable nature of SubinterfacesBuilder, as instances can be freely passed around without
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
 * @see SubinterfacesBuilder
 * @see Builder
 *
 */
@Generated("mdsal-binding-generator")
public class SubinterfacesBuilder implements Builder<Subinterfaces> {

    private Map<SubinterfaceKey, Subinterface> _subinterface;


    Map<Class<? extends Augmentation<Subinterfaces>>, Augmentation<Subinterfaces>> augmentation = Collections.emptyMap();

    public SubinterfacesBuilder() {
    }
    
    

    public SubinterfacesBuilder(Subinterfaces base) {
        Map<Class<? extends Augmentation<Subinterfaces>>, Augmentation<Subinterfaces>> aug = base.augmentations();
        if (!aug.isEmpty()) {
            this.augmentation = new HashMap<>(aug);
        }
        this._subinterface = base.getSubinterface();
    }


    public Map<SubinterfaceKey, Subinterface> getSubinterface() {
        return _subinterface;
    }

    @SuppressWarnings({ "unchecked", "checkstyle:methodTypeParameterName"})
    public <E$$ extends Augmentation<Subinterfaces>> E$$ augmentation(Class<E$$> augmentationType) {
        return (E$$) augmentation.get(Objects.requireNonNull(augmentationType));
    }

    public SubinterfacesBuilder setSubinterface(final Map<SubinterfaceKey, Subinterface> values) {
        this._subinterface = values;
        return this;
    }
    
    /**
      * Utility migration setter.
      *
      * <b>IMPORTANT NOTE</b>: This method does not completely match previous mechanics, as the list is processed as
      *                        during this method's execution. Any future modifications of the list are <b>NOT</b>
      *                        reflected in this builder nor its products.
      *
      * @param values Legacy List of values
      * @return this builder
      * @throws IllegalArgumentException if the list contains entries with the same key
      * @throws NullPointerException if the list contains a null entry
      * @deprecated Use {@link #setSubinterface(Map)} instead.
      */
    @Deprecated(forRemoval = true)
    public SubinterfacesBuilder setSubinterface(final List<Subinterface> values) {
        return setSubinterface(CodeHelpers.compatMap(values));
    }
    
    /**
      * Add an augmentation to this builder's product.
      *
      * @param augmentation augmentation to be added
      * @return this builder
      * @throws NullPointerException if {@code augmentation} is null
      */
    public SubinterfacesBuilder addAugmentation(Augmentation<Subinterfaces> augmentation) {
        Class<? extends Augmentation<Subinterfaces>> augmentationType = augmentation.implementedInterface();
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
    public SubinterfacesBuilder removeAugmentation(Class<? extends Augmentation<Subinterfaces>> augmentationType) {
        if (this.augmentation instanceof HashMap) {
            this.augmentation.remove(augmentationType);
        }
        return this;
    }

    @Override
    public Subinterfaces build() {
        return new SubinterfacesImpl(this);
    }

    private static final class SubinterfacesImpl
        extends AbstractAugmentable<Subinterfaces>
        implements Subinterfaces {
    
        private final Map<SubinterfaceKey, Subinterface> _subinterface;
    
        SubinterfacesImpl(SubinterfacesBuilder base) {
            super(base.augmentation);
            this._subinterface = CodeHelpers.emptyToNull(base.getSubinterface());
        }
    
        @Override
        public Map<SubinterfaceKey, Subinterface> getSubinterface() {
            return _subinterface;
        }
    
        private int hash = 0;
        private volatile boolean hashValid = false;
        
        @Override
        public int hashCode() {
            if (hashValid) {
                return hash;
            }
        
            final int result = Subinterfaces.bindingHashCode(this);
            hash = result;
            hashValid = true;
            return result;
        }
    
        @Override
        public boolean equals(Object obj) {
            return Subinterfaces.bindingEquals(this, obj);
        }
    
        @Override
        public String toString() {
            return Subinterfaces.bindingToString(this);
        }
    }
}
