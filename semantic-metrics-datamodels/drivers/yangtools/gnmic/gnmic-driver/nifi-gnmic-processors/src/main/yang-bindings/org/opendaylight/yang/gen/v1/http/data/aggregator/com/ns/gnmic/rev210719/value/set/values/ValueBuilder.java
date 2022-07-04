package org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.gnmic.rev210719.value.set.values;
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
import org.opendaylight.yangtools.concepts.Builder;
import org.opendaylight.yangtools.yang.binding.AbstractAugmentable;
import org.opendaylight.yangtools.yang.binding.Augmentation;

/**
 * Class that builds {@link ValueBuilder} instances. Overall design of the class is that of a
 * <a href="https://en.wikipedia.org/wiki/Fluent_interface">fluent interface</a>, where method chaining is used.
 * 
 * <p>
 * In general, this class is supposed to be used like this template:
 * <pre>
 *   <code>
 *     ValueBuilder createTarget(int fooXyzzy, int barBaz) {
 *         return new ValueBuilderBuilder()
 *             .setFoo(new FooBuilder().setXyzzy(fooXyzzy).build())
 *             .setBar(new BarBuilder().setBaz(barBaz).build())
 *             .build();
 *     }
 *   </code>
 * </pre>
 * 
 * <p>
 * This pattern is supported by the immutable nature of ValueBuilder, as instances can be freely passed around without
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
 * @see ValueBuilder
 * @see Builder
 *
 */
@Generated("mdsal-binding-generator")
public class ValueBuilder implements Builder<Value> {

    private String _name;
    private String _value;
    private ValueKey key;


    Map<Class<? extends Augmentation<Value>>, Augmentation<Value>> augmentation = Collections.emptyMap();

    public ValueBuilder() {
    }
    
    
    

    public ValueBuilder(Value base) {
        Map<Class<? extends Augmentation<Value>>, Augmentation<Value>> aug = base.augmentations();
        if (!aug.isEmpty()) {
            this.augmentation = new HashMap<>(aug);
        }
        this.key = base.key();
        this._name = base.getName();
        this._value = base.getValue();
    }


    public ValueKey key() {
        return key;
    }
    
    public String getName() {
        return _name;
    }
    
    public String getValue() {
        return _value;
    }

    @SuppressWarnings({ "unchecked", "checkstyle:methodTypeParameterName"})
    public <E$$ extends Augmentation<Value>> E$$ augmentation(Class<E$$> augmentationType) {
        return (E$$) augmentation.get(Objects.requireNonNull(augmentationType));
    }

    public ValueBuilder withKey(final ValueKey key) {
        this.key = key;
        return this;
    }
    
    public ValueBuilder setName(final String value) {
        this._name = value;
        return this;
    }
    
    public ValueBuilder setValue(final String value) {
        this._value = value;
        return this;
    }
    
    /**
      * Add an augmentation to this builder's product.
      *
      * @param augmentation augmentation to be added
      * @return this builder
      * @throws NullPointerException if {@code augmentation} is null
      */
    public ValueBuilder addAugmentation(Augmentation<Value> augmentation) {
        Class<? extends Augmentation<Value>> augmentationType = augmentation.implementedInterface();
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
    public ValueBuilder removeAugmentation(Class<? extends Augmentation<Value>> augmentationType) {
        if (this.augmentation instanceof HashMap) {
            this.augmentation.remove(augmentationType);
        }
        return this;
    }

    @Override
    public Value build() {
        return new ValueImpl(this);
    }

    private static final class ValueImpl
        extends AbstractAugmentable<Value>
        implements Value {
    
        private final String _name;
        private final String _value;
        private final ValueKey key;
    
        ValueImpl(ValueBuilder base) {
            super(base.augmentation);
            if (base.key() != null) {
                this.key = base.key();
            } else {
                this.key = new ValueKey(base.getName());
            }
            this._name = key.getName();
            this._value = base.getValue();
        }
    
        @Override
        public ValueKey key() {
            return key;
        }
        
        @Override
        public String getName() {
            return _name;
        }
        
        @Override
        public String getValue() {
            return _value;
        }
    
        private int hash = 0;
        private volatile boolean hashValid = false;
        
        @Override
        public int hashCode() {
            if (hashValid) {
                return hash;
            }
        
            final int result = Value.bindingHashCode(this);
            hash = result;
            hashValid = true;
            return result;
        }
    
        @Override
        public boolean equals(Object obj) {
            return Value.bindingEquals(this, obj);
        }
    
        @Override
        public String toString() {
            return Value.bindingToString(this);
        }
    }
}
