package org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.gnmic.rev210719.value.set;
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
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.gnmic.rev210719.value.set.values.Value;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.gnmic.rev210719.value.set.values.ValueKey;
import org.opendaylight.yangtools.concepts.Builder;
import org.opendaylight.yangtools.yang.binding.AbstractAugmentable;
import org.opendaylight.yangtools.yang.binding.Augmentation;
import org.opendaylight.yangtools.yang.binding.CodeHelpers;

/**
 * Class that builds {@link ValuesBuilder} instances. Overall design of the class is that of a
 * <a href="https://en.wikipedia.org/wiki/Fluent_interface">fluent interface</a>, where method chaining is used.
 * 
 * <p>
 * In general, this class is supposed to be used like this template:
 * <pre>
 *   <code>
 *     ValuesBuilder createTarget(int fooXyzzy, int barBaz) {
 *         return new ValuesBuilderBuilder()
 *             .setFoo(new FooBuilder().setXyzzy(fooXyzzy).build())
 *             .setBar(new BarBuilder().setBaz(barBaz).build())
 *             .build();
 *     }
 *   </code>
 * </pre>
 * 
 * <p>
 * This pattern is supported by the immutable nature of ValuesBuilder, as instances can be freely passed around without
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
 * @see ValuesBuilder
 * @see Builder
 *
 */
@Generated("mdsal-binding-generator")
public class ValuesBuilder implements Builder<Values> {

    private Map<ValueKey, Value> _value;


    Map<Class<? extends Augmentation<Values>>, Augmentation<Values>> augmentation = Collections.emptyMap();

    public ValuesBuilder() {
    }
    
    

    public ValuesBuilder(Values base) {
        Map<Class<? extends Augmentation<Values>>, Augmentation<Values>> aug = base.augmentations();
        if (!aug.isEmpty()) {
            this.augmentation = new HashMap<>(aug);
        }
        this._value = base.getValue();
    }


    public Map<ValueKey, Value> getValue() {
        return _value;
    }

    @SuppressWarnings({ "unchecked", "checkstyle:methodTypeParameterName"})
    public <E$$ extends Augmentation<Values>> E$$ augmentation(Class<E$$> augmentationType) {
        return (E$$) augmentation.get(Objects.requireNonNull(augmentationType));
    }

    public ValuesBuilder setValue(final Map<ValueKey, Value> values) {
        this._value = values;
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
      * @deprecated Use {@link #setValue(Map)} instead.
      */
    @Deprecated(forRemoval = true)
    public ValuesBuilder setValue(final List<Value> values) {
        return setValue(CodeHelpers.compatMap(values));
    }
    
    /**
      * Add an augmentation to this builder's product.
      *
      * @param augmentation augmentation to be added
      * @return this builder
      * @throws NullPointerException if {@code augmentation} is null
      */
    public ValuesBuilder addAugmentation(Augmentation<Values> augmentation) {
        Class<? extends Augmentation<Values>> augmentationType = augmentation.implementedInterface();
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
    public ValuesBuilder removeAugmentation(Class<? extends Augmentation<Values>> augmentationType) {
        if (this.augmentation instanceof HashMap) {
            this.augmentation.remove(augmentationType);
        }
        return this;
    }

    @Override
    public Values build() {
        return new ValuesImpl(this);
    }

    private static final class ValuesImpl
        extends AbstractAugmentable<Values>
        implements Values {
    
        private final Map<ValueKey, Value> _value;
    
        ValuesImpl(ValuesBuilder base) {
            super(base.augmentation);
            this._value = CodeHelpers.emptyToNull(base.getValue());
        }
    
        @Override
        public Map<ValueKey, Value> getValue() {
            return _value;
        }
    
        private int hash = 0;
        private volatile boolean hashValid = false;
        
        @Override
        public int hashCode() {
            if (hashValid) {
                return hash;
            }
        
            final int result = Values.bindingHashCode(this);
            hash = result;
            hashValid = true;
            return result;
        }
    
        @Override
        public boolean equals(Object obj) {
            return Values.bindingEquals(this, obj);
        }
    
        @Override
        public String toString() {
            return Values.bindingToString(this);
        }
    }
}
