package org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.metric.point.set.metric.points.metric.points.choice;
import java.lang.Class;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import javax.annotation.processing.Generated;
import org.opendaylight.yangtools.concepts.Builder;
import org.opendaylight.yangtools.yang.binding.AbstractAugmentable;
import org.opendaylight.yangtools.yang.binding.Augmentation;

/**
 * Class that builds {@link MetricPointSingleBuilder} instances. Overall design of the class is that of a
 * <a href="https://en.wikipedia.org/wiki/Fluent_interface">fluent interface</a>, where method chaining is used.
 *
 * <p>
 * In general, this class is supposed to be used like this template:
 * <pre>
 *   <code>
 *     MetricPointSingleBuilder createTarget(int fooXyzzy, int barBaz) {
 *         return new MetricPointSingleBuilderBuilder()
 *             .setFoo(new FooBuilder().setXyzzy(fooXyzzy).build())
 *             .setBar(new BarBuilder().setBaz(barBaz).build())
 *             .build();
 *     }
 *   </code>
 * </pre>
 *
 * <p>
 * This pattern is supported by the immutable nature of MetricPointSingleBuilder, as instances can be freely passed around without
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
 * @see MetricPointSingleBuilder
 * @see Builder
 *
 */
@Generated("mdsal-binding-generator")
public class MetricPointSingleBuilder implements Builder<MetricPointSingle> {

    private Long _timestamp;
    private BigDecimal _value;


    Map<Class<? extends Augmentation<MetricPointSingle>>, Augmentation<MetricPointSingle>> augmentation = Collections.emptyMap();

    public MetricPointSingleBuilder() {
    }
    
    
    

    public MetricPointSingleBuilder(MetricPointSingle base) {
        Map<Class<? extends Augmentation<MetricPointSingle>>, Augmentation<MetricPointSingle>> aug = base.augmentations();
        if (!aug.isEmpty()) {
            this.augmentation = new HashMap<>(aug);
        }
        this._timestamp = base.getTimestamp();
        this._value = base.getValue();
    }


    public Long getTimestamp() {
        return _timestamp;
    }
    
    public BigDecimal getValue() {
        return _value;
    }

    @SuppressWarnings({ "unchecked", "checkstyle:methodTypeParameterName"})
    public <E$$ extends Augmentation<MetricPointSingle>> E$$ augmentation(Class<E$$> augmentationType) {
        return (E$$) augmentation.get(Objects.requireNonNull(augmentationType));
    }

    
    public MetricPointSingleBuilder setTimestamp(final Long value) {
        this._timestamp = value;
        return this;
    }
    
    
    public MetricPointSingleBuilder setValue(final BigDecimal value) {
        if (value != null) {
            
        }
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
    public MetricPointSingleBuilder addAugmentation(Augmentation<MetricPointSingle> augmentation) {
        Class<? extends Augmentation<MetricPointSingle>> augmentationType = augmentation.implementedInterface();
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
    public MetricPointSingleBuilder removeAugmentation(Class<? extends Augmentation<MetricPointSingle>> augmentationType) {
        if (this.augmentation instanceof HashMap) {
            this.augmentation.remove(augmentationType);
        }
        return this;
    }

    @Override
    public MetricPointSingle build() {
        return new MetricPointSingleImpl(this);
    }

    private static final class MetricPointSingleImpl
        extends AbstractAugmentable<MetricPointSingle>
        implements MetricPointSingle {
    
        private final Long _timestamp;
        private final BigDecimal _value;
    
        MetricPointSingleImpl(MetricPointSingleBuilder base) {
            super(base.augmentation);
            this._timestamp = base.getTimestamp();
            this._value = base.getValue();
        }
    
        @Override
        public Long getTimestamp() {
            return _timestamp;
        }
        
        @Override
        public BigDecimal getValue() {
            return _value;
        }
    
        private int hash = 0;
        private volatile boolean hashValid = false;
        
        @Override
        public int hashCode() {
            if (hashValid) {
                return hash;
            }
        
            final int result = MetricPointSingle.bindingHashCode(this);
            hash = result;
            hashValid = true;
            return result;
        }
    
        @Override
        public boolean equals(Object obj) {
            return MetricPointSingle.bindingEquals(this, obj);
        }
    
        @Override
        public String toString() {
            return MetricPointSingle.bindingToString(this);
        }
    }
}
