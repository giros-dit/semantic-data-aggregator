package org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.metric.families;
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
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.MetricType;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.metric.set.Metrics;
import org.opendaylight.yangtools.concepts.Builder;
import org.opendaylight.yangtools.yang.binding.AbstractAugmentable;
import org.opendaylight.yangtools.yang.binding.Augmentation;
import org.opendaylight.yangtools.yang.binding.CodeHelpers;
import org.opendaylight.yangtools.yang.binding.DataObject;

/**
 * Class that builds {@link MetricFamilyBuilder} instances. Overall design of the class is that of a
 * <a href="https://en.wikipedia.org/wiki/Fluent_interface">fluent interface</a>, where method chaining is used.
 *
 * <p>
 * In general, this class is supposed to be used like this template:
 * <pre>
 *   <code>
 *     MetricFamilyBuilder createTarget(int fooXyzzy, int barBaz) {
 *         return new MetricFamilyBuilderBuilder()
 *             .setFoo(new FooBuilder().setXyzzy(fooXyzzy).build())
 *             .setBar(new BarBuilder().setBaz(barBaz).build())
 *             .build();
 *     }
 *   </code>
 * </pre>
 *
 * <p>
 * This pattern is supported by the immutable nature of MetricFamilyBuilder, as instances can be freely passed around without
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
 * @see MetricFamilyBuilder
 * @see Builder
 *
 */
@Generated("mdsal-binding-generator")
public class MetricFamilyBuilder implements Builder<MetricFamily> {

    private String _help;
    private MetricType _metricType;
    private Metrics _metrics;
    private String _name;
    private String _unit;
    private MetricFamilyKey key;


    Map<Class<? extends Augmentation<MetricFamily>>, Augmentation<MetricFamily>> augmentation = Collections.emptyMap();

    public MetricFamilyBuilder() {
    }
    
    
    
    public MetricFamilyBuilder(org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.MetricSet arg) {
        this._metrics = arg.getMetrics();
    }
    

    public MetricFamilyBuilder(MetricFamily base) {
        Map<Class<? extends Augmentation<MetricFamily>>, Augmentation<MetricFamily>> aug = base.augmentations();
        if (!aug.isEmpty()) {
            this.augmentation = new HashMap<>(aug);
        }
        this.key = base.key();
        this._name = base.getName();
        this._help = base.getHelp();
        this._metricType = base.getMetricType();
        this._metrics = base.getMetrics();
        this._unit = base.getUnit();
    }

    /**
     * Set fields from given grouping argument. Valid argument is instance of one of following types:
     * <ul>
     * <li>org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.MetricSet</li>
     * </ul>
     *
     * @param arg grouping object
     * @throws IllegalArgumentException if given argument is none of valid types or has property with incompatible value
    */
    public void fieldsFrom(DataObject arg) {
        boolean isValidArg = false;
        if (arg instanceof org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.MetricSet) {
            this._metrics = ((org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.MetricSet)arg).getMetrics();
            isValidArg = true;
        }
        CodeHelpers.validValue(isValidArg, arg, "[org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.MetricSet]");
    }

    public MetricFamilyKey key() {
        return key;
    }
    
    public String getHelp() {
        return _help;
    }
    
    public MetricType getMetricType() {
        return _metricType;
    }
    
    public Metrics getMetrics() {
        return _metrics;
    }
    
    public String getName() {
        return _name;
    }
    
    public String getUnit() {
        return _unit;
    }

    @SuppressWarnings({ "unchecked", "checkstyle:methodTypeParameterName"})
    public <E$$ extends Augmentation<MetricFamily>> E$$ augmentation(Class<E$$> augmentationType) {
        return (E$$) augmentation.get(Objects.requireNonNull(augmentationType));
    }

    public MetricFamilyBuilder withKey(final MetricFamilyKey key) {
        this.key = key;
        return this;
    }
    
    public MetricFamilyBuilder setHelp(final String value) {
        this._help = value;
        return this;
    }
    
    public MetricFamilyBuilder setMetricType(final MetricType value) {
        this._metricType = value;
        return this;
    }
    
    public MetricFamilyBuilder setMetrics(final Metrics value) {
        this._metrics = value;
        return this;
    }
    
    public MetricFamilyBuilder setName(final String value) {
        this._name = value;
        return this;
    }
    
    public MetricFamilyBuilder setUnit(final String value) {
        this._unit = value;
        return this;
    }
    
    /**
      * Add an augmentation to this builder's product.
      *
      * @param augmentation augmentation to be added
      * @return this builder
      * @throws NullPointerException if {@code augmentation} is null
      */
    public MetricFamilyBuilder addAugmentation(Augmentation<MetricFamily> augmentation) {
        Class<? extends Augmentation<MetricFamily>> augmentationType = augmentation.implementedInterface();
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
    public MetricFamilyBuilder removeAugmentation(Class<? extends Augmentation<MetricFamily>> augmentationType) {
        if (this.augmentation instanceof HashMap) {
            this.augmentation.remove(augmentationType);
        }
        return this;
    }

    @Override
    public MetricFamily build() {
        return new MetricFamilyImpl(this);
    }

    private static final class MetricFamilyImpl
        extends AbstractAugmentable<MetricFamily>
        implements MetricFamily {
    
        private final String _help;
        private final MetricType _metricType;
        private final Metrics _metrics;
        private final String _name;
        private final String _unit;
        private final MetricFamilyKey key;
    
        MetricFamilyImpl(MetricFamilyBuilder base) {
            super(base.augmentation);
            if (base.key() != null) {
                this.key = base.key();
            } else {
                this.key = new MetricFamilyKey(base.getName());
            }
            this._name = key.getName();
            this._help = base.getHelp();
            this._metricType = base.getMetricType();
            this._metrics = base.getMetrics();
            this._unit = base.getUnit();
        }
    
        @Override
        public MetricFamilyKey key() {
            return key;
        }
        
        @Override
        public String getHelp() {
            return _help;
        }
        
        @Override
        public MetricType getMetricType() {
            return _metricType;
        }
        
        @Override
        public Metrics getMetrics() {
            return _metrics;
        }
        
        @Override
        public String getName() {
            return _name;
        }
        
        @Override
        public String getUnit() {
            return _unit;
        }
    
        private int hash = 0;
        private volatile boolean hashValid = false;
        
        @Override
        public int hashCode() {
            if (hashValid) {
                return hash;
            }
        
            final int result = MetricFamily.bindingHashCode(this);
            hash = result;
            hashValid = true;
            return result;
        }
    
        @Override
        public boolean equals(Object obj) {
            return MetricFamily.bindingEquals(this, obj);
        }
    
        @Override
        public String toString() {
            return MetricFamily.bindingToString(this);
        }
    }
}
