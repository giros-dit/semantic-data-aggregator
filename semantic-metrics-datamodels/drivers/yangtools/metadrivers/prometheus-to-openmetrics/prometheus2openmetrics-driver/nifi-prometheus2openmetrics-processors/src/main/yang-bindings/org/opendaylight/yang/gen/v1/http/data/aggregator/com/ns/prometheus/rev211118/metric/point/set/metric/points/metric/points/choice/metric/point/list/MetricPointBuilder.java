package org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.prometheus.rev211118.metric.point.set.metric.points.metric.points.choice.metric.point.list;
import java.lang.Class;
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
 * Class that builds {@link MetricPointBuilder} instances. Overall design of the class is that of a
 * <a href="https://en.wikipedia.org/wiki/Fluent_interface">fluent interface</a>, where method chaining is used.
 *
 * <p>
 * In general, this class is supposed to be used like this template:
 * <pre>
 *   <code>
 *     MetricPointBuilder createTarget(int fooXyzzy, int barBaz) {
 *         return new MetricPointBuilderBuilder()
 *             .setFoo(new FooBuilder().setXyzzy(fooXyzzy).build())
 *             .setBar(new BarBuilder().setBaz(barBaz).build())
 *             .build();
 *     }
 *   </code>
 * </pre>
 *
 * <p>
 * This pattern is supported by the immutable nature of MetricPointBuilder, as instances can be freely passed around without
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
 * @see MetricPointBuilder
 * @see Builder
 *
 */
@Generated("mdsal-binding-generator")
public class MetricPointBuilder implements Builder<MetricPoint> {

    private BigDecimal _timestamp;
    private BigDecimal _value;
    private MetricPointKey key;


    Map<Class<? extends Augmentation<MetricPoint>>, Augmentation<MetricPoint>> augmentation = Collections.emptyMap();

    public MetricPointBuilder() {
    }
    
    
    

    public MetricPointBuilder(MetricPoint base) {
        Map<Class<? extends Augmentation<MetricPoint>>, Augmentation<MetricPoint>> aug = base.augmentations();
        if (!aug.isEmpty()) {
            this.augmentation = new HashMap<>(aug);
        }
        this.key = base.key();
        this._timestamp = base.getTimestamp();
        this._value = base.getValue();
    }


    public MetricPointKey key() {
        return key;
    }
    
    public BigDecimal getTimestamp() {
        return _timestamp;
    }
    
    public BigDecimal getValue() {
        return _value;
    }

    @SuppressWarnings({ "unchecked", "checkstyle:methodTypeParameterName"})
    public <E$$ extends Augmentation<MetricPoint>> E$$ augmentation(Class<E$$> augmentationType) {
        return (E$$) augmentation.get(Objects.requireNonNull(augmentationType));
    }

    public MetricPointBuilder withKey(final MetricPointKey key) {
        this.key = key;
        return this;
    }
    
    
    public MetricPointBuilder setTimestamp(final BigDecimal value) {
        if (value != null) {
            
        }
        this._timestamp = value;
        return this;
    }
    
    
    public MetricPointBuilder setValue(final BigDecimal value) {
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
    public MetricPointBuilder addAugmentation(Augmentation<MetricPoint> augmentation) {
        Class<? extends Augmentation<MetricPoint>> augmentationType = augmentation.implementedInterface();
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
    public MetricPointBuilder removeAugmentation(Class<? extends Augmentation<MetricPoint>> augmentationType) {
        if (this.augmentation instanceof HashMap) {
            this.augmentation.remove(augmentationType);
        }
        return this;
    }

    @Override
    public MetricPoint build() {
        return new MetricPointImpl(this);
    }

    private static final class MetricPointImpl
        extends AbstractAugmentable<MetricPoint>
        implements MetricPoint {
    
        private final BigDecimal _timestamp;
        private final BigDecimal _value;
        private final MetricPointKey key;
    
        MetricPointImpl(MetricPointBuilder base) {
            super(base.augmentation);
            if (base.key() != null) {
                this.key = base.key();
            } else {
                this.key = new MetricPointKey(base.getTimestamp());
            }
            this._timestamp = key.getTimestamp();
            this._value = base.getValue();
        }
    
        @Override
        public MetricPointKey key() {
            return key;
        }
        
        @Override
        public BigDecimal getTimestamp() {
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
        
            final int result = MetricPoint.bindingHashCode(this);
            hash = result;
            hashValid = true;
            return result;
        }
    
        @Override
        public boolean equals(Object obj) {
            return MetricPoint.bindingEquals(this, obj);
        }
    
        @Override
        public String toString() {
            return MetricPoint.bindingToString(this);
        }
    }
}
