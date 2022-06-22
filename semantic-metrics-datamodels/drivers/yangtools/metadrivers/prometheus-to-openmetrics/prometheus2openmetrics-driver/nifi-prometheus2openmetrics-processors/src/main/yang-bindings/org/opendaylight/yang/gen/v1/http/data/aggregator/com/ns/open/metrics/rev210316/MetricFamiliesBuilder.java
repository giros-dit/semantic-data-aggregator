package org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316;
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
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.metric.families.MetricFamily;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.metric.families.MetricFamilyKey;
import org.opendaylight.yangtools.concepts.Builder;
import org.opendaylight.yangtools.yang.binding.AbstractAugmentable;
import org.opendaylight.yangtools.yang.binding.Augmentation;
import org.opendaylight.yangtools.yang.binding.CodeHelpers;

/**
 * Class that builds {@link MetricFamiliesBuilder} instances. Overall design of the class is that of a
 * <a href="https://en.wikipedia.org/wiki/Fluent_interface">fluent interface</a>, where method chaining is used.
 *
 * <p>
 * In general, this class is supposed to be used like this template:
 * <pre>
 *   <code>
 *     MetricFamiliesBuilder createTarget(int fooXyzzy, int barBaz) {
 *         return new MetricFamiliesBuilderBuilder()
 *             .setFoo(new FooBuilder().setXyzzy(fooXyzzy).build())
 *             .setBar(new BarBuilder().setBaz(barBaz).build())
 *             .build();
 *     }
 *   </code>
 * </pre>
 *
 * <p>
 * This pattern is supported by the immutable nature of MetricFamiliesBuilder, as instances can be freely passed around without
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
 * @see MetricFamiliesBuilder
 * @see Builder
 *
 */
@Generated("mdsal-binding-generator")
public class MetricFamiliesBuilder implements Builder<MetricFamilies> {

    private Map<MetricFamilyKey, MetricFamily> _metricFamily;


    Map<Class<? extends Augmentation<MetricFamilies>>, Augmentation<MetricFamilies>> augmentation = Collections.emptyMap();

    public MetricFamiliesBuilder() {
    }
    
    

    public MetricFamiliesBuilder(MetricFamilies base) {
        Map<Class<? extends Augmentation<MetricFamilies>>, Augmentation<MetricFamilies>> aug = base.augmentations();
        if (!aug.isEmpty()) {
            this.augmentation = new HashMap<>(aug);
        }
        this._metricFamily = base.getMetricFamily();
    }


    public Map<MetricFamilyKey, MetricFamily> getMetricFamily() {
        return _metricFamily;
    }

    @SuppressWarnings({ "unchecked", "checkstyle:methodTypeParameterName"})
    public <E$$ extends Augmentation<MetricFamilies>> E$$ augmentation(Class<E$$> augmentationType) {
        return (E$$) augmentation.get(Objects.requireNonNull(augmentationType));
    }

    public MetricFamiliesBuilder setMetricFamily(final Map<MetricFamilyKey, MetricFamily> values) {
        this._metricFamily = values;
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
      * @deprecated Use {@link #setMetricFamily(Map)} instead.
      */
    @Deprecated(forRemoval = true)
    public MetricFamiliesBuilder setMetricFamily(final List<MetricFamily> values) {
        return setMetricFamily(CodeHelpers.compatMap(values));
    }
    
    /**
      * Add an augmentation to this builder's product.
      *
      * @param augmentation augmentation to be added
      * @return this builder
      * @throws NullPointerException if {@code augmentation} is null
      */
    public MetricFamiliesBuilder addAugmentation(Augmentation<MetricFamilies> augmentation) {
        Class<? extends Augmentation<MetricFamilies>> augmentationType = augmentation.implementedInterface();
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
    public MetricFamiliesBuilder removeAugmentation(Class<? extends Augmentation<MetricFamilies>> augmentationType) {
        if (this.augmentation instanceof HashMap) {
            this.augmentation.remove(augmentationType);
        }
        return this;
    }

    @Override
    public MetricFamilies build() {
        return new MetricFamiliesImpl(this);
    }

    private static final class MetricFamiliesImpl
        extends AbstractAugmentable<MetricFamilies>
        implements MetricFamilies {
    
        private final Map<MetricFamilyKey, MetricFamily> _metricFamily;
    
        MetricFamiliesImpl(MetricFamiliesBuilder base) {
            super(base.augmentation);
            this._metricFamily = CodeHelpers.emptyToNull(base.getMetricFamily());
        }
    
        @Override
        public Map<MetricFamilyKey, MetricFamily> getMetricFamily() {
            return _metricFamily;
        }
    
        private int hash = 0;
        private volatile boolean hashValid = false;
        
        @Override
        public int hashCode() {
            if (hashValid) {
                return hash;
            }
        
            final int result = MetricFamilies.bindingHashCode(this);
            hash = result;
            hashValid = true;
            return result;
        }
    
        @Override
        public boolean equals(Object obj) {
            return MetricFamilies.bindingEquals(this, obj);
        }
    
        @Override
        public String toString() {
            return MetricFamilies.bindingToString(this);
        }
    }
}
