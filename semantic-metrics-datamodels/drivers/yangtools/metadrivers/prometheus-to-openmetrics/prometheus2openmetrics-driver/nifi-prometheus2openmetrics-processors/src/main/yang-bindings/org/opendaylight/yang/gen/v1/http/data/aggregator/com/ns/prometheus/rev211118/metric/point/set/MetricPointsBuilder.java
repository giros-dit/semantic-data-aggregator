package org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.prometheus.rev211118.metric.point.set;
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
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.prometheus.rev211118.metric.point.set.metric.points.MetricPointsChoice;
import org.opendaylight.yangtools.concepts.Builder;
import org.opendaylight.yangtools.yang.binding.AbstractAugmentable;
import org.opendaylight.yangtools.yang.binding.Augmentation;

/**
 * Class that builds {@link MetricPointsBuilder} instances. Overall design of the class is that of a
 * <a href="https://en.wikipedia.org/wiki/Fluent_interface">fluent interface</a>, where method chaining is used.
 *
 * <p>
 * In general, this class is supposed to be used like this template:
 * <pre>
 *   <code>
 *     MetricPointsBuilder createTarget(int fooXyzzy, int barBaz) {
 *         return new MetricPointsBuilderBuilder()
 *             .setFoo(new FooBuilder().setXyzzy(fooXyzzy).build())
 *             .setBar(new BarBuilder().setBaz(barBaz).build())
 *             .build();
 *     }
 *   </code>
 * </pre>
 *
 * <p>
 * This pattern is supported by the immutable nature of MetricPointsBuilder, as instances can be freely passed around without
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
 * @see MetricPointsBuilder
 * @see Builder
 *
 */
@Generated("mdsal-binding-generator")
public class MetricPointsBuilder implements Builder<MetricPoints> {

    private MetricPointsChoice _metricPointsChoice;


    Map<Class<? extends Augmentation<MetricPoints>>, Augmentation<MetricPoints>> augmentation = Collections.emptyMap();

    public MetricPointsBuilder() {
    }
    
    

    public MetricPointsBuilder(MetricPoints base) {
        Map<Class<? extends Augmentation<MetricPoints>>, Augmentation<MetricPoints>> aug = base.augmentations();
        if (!aug.isEmpty()) {
            this.augmentation = new HashMap<>(aug);
        }
        this._metricPointsChoice = base.getMetricPointsChoice();
    }


    public MetricPointsChoice getMetricPointsChoice() {
        return _metricPointsChoice;
    }

    @SuppressWarnings({ "unchecked", "checkstyle:methodTypeParameterName"})
    public <E$$ extends Augmentation<MetricPoints>> E$$ augmentation(Class<E$$> augmentationType) {
        return (E$$) augmentation.get(Objects.requireNonNull(augmentationType));
    }

    
    public MetricPointsBuilder setMetricPointsChoice(final MetricPointsChoice value) {
        this._metricPointsChoice = value;
        return this;
    }
    
    /**
      * Add an augmentation to this builder's product.
      *
      * @param augmentation augmentation to be added
      * @return this builder
      * @throws NullPointerException if {@code augmentation} is null
      */
    public MetricPointsBuilder addAugmentation(Augmentation<MetricPoints> augmentation) {
        Class<? extends Augmentation<MetricPoints>> augmentationType = augmentation.implementedInterface();
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
    public MetricPointsBuilder removeAugmentation(Class<? extends Augmentation<MetricPoints>> augmentationType) {
        if (this.augmentation instanceof HashMap) {
            this.augmentation.remove(augmentationType);
        }
        return this;
    }

    @Override
    public MetricPoints build() {
        return new MetricPointsImpl(this);
    }

    private static final class MetricPointsImpl
        extends AbstractAugmentable<MetricPoints>
        implements MetricPoints {
    
        private final MetricPointsChoice _metricPointsChoice;
    
        MetricPointsImpl(MetricPointsBuilder base) {
            super(base.augmentation);
            this._metricPointsChoice = base.getMetricPointsChoice();
        }
    
        @Override
        public MetricPointsChoice getMetricPointsChoice() {
            return _metricPointsChoice;
        }
    
        private int hash = 0;
        private volatile boolean hashValid = false;
        
        @Override
        public int hashCode() {
            if (hashValid) {
                return hash;
            }
        
            final int result = MetricPoints.bindingHashCode(this);
            hash = result;
            hashValid = true;
            return result;
        }
    
        @Override
        public boolean equals(Object obj) {
            return MetricPoints.bindingEquals(this, obj);
        }
    
        @Override
        public String toString() {
            return MetricPoints.bindingToString(this);
        }
    }
}
