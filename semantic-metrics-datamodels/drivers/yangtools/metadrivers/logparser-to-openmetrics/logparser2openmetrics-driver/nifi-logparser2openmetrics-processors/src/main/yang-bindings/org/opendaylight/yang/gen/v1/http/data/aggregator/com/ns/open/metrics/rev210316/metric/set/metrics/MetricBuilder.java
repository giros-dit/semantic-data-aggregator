package org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.metric.set.metrics;
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
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.label.set.Labels;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.metric.point.set.MetricPoints;
import org.opendaylight.yangtools.concepts.Builder;
import org.opendaylight.yangtools.yang.binding.AbstractAugmentable;
import org.opendaylight.yangtools.yang.binding.Augmentation;
import org.opendaylight.yangtools.yang.binding.CodeHelpers;
import org.opendaylight.yangtools.yang.binding.DataObject;

/**
 * Class that builds {@link MetricBuilder} instances. Overall design of the class is that of a
 * <a href="https://en.wikipedia.org/wiki/Fluent_interface">fluent interface</a>, where method chaining is used.
 *
 * <p>
 * In general, this class is supposed to be used like this template:
 * <pre>
 *   <code>
 *     MetricBuilder createTarget(int fooXyzzy, int barBaz) {
 *         return new MetricBuilderBuilder()
 *             .setFoo(new FooBuilder().setXyzzy(fooXyzzy).build())
 *             .setBar(new BarBuilder().setBaz(barBaz).build())
 *             .build();
 *     }
 *   </code>
 * </pre>
 *
 * <p>
 * This pattern is supported by the immutable nature of MetricBuilder, as instances can be freely passed around without
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
 * @see MetricBuilder
 * @see Builder
 *
 */
@Generated("mdsal-binding-generator")
public class MetricBuilder implements Builder<Metric> {

    private String _labelSetId;
    private Labels _labels;
    private MetricPoints _metricPoints;
    private String _name;
    private MetricKey key;


    Map<Class<? extends Augmentation<Metric>>, Augmentation<Metric>> augmentation = Collections.emptyMap();

    public MetricBuilder() {
    }
    
    
    
    public MetricBuilder(org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.LabelSet arg) {
        this._labels = arg.getLabels();
    }
    
    public MetricBuilder(org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.MetricPointSet arg) {
        this._metricPoints = arg.getMetricPoints();
    }
    

    public MetricBuilder(Metric base) {
        Map<Class<? extends Augmentation<Metric>>, Augmentation<Metric>> aug = base.augmentations();
        if (!aug.isEmpty()) {
            this.augmentation = new HashMap<>(aug);
        }
        this.key = base.key();
        this._labelSetId = base.getLabelSetId();
        this._labels = base.getLabels();
        this._metricPoints = base.getMetricPoints();
        this._name = base.getName();
    }

    /**
     * Set fields from given grouping argument. Valid argument is instance of one of following types:
     * <ul>
     * <li>org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.LabelSet</li>
     * <li>org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.MetricPointSet</li>
     * </ul>
     *
     * @param arg grouping object
     * @throws IllegalArgumentException if given argument is none of valid types or has property with incompatible value
    */
    public void fieldsFrom(DataObject arg) {
        boolean isValidArg = false;
        if (arg instanceof org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.LabelSet) {
            this._labels = ((org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.LabelSet)arg).getLabels();
            isValidArg = true;
        }
        if (arg instanceof org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.MetricPointSet) {
            this._metricPoints = ((org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.MetricPointSet)arg).getMetricPoints();
            isValidArg = true;
        }
        CodeHelpers.validValue(isValidArg, arg, "[org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.LabelSet, org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316.MetricPointSet]");
    }

    public MetricKey key() {
        return key;
    }
    
    public String getLabelSetId() {
        return _labelSetId;
    }
    
    public Labels getLabels() {
        return _labels;
    }
    
    public MetricPoints getMetricPoints() {
        return _metricPoints;
    }
    
    public String getName() {
        return _name;
    }

    @SuppressWarnings({ "unchecked", "checkstyle:methodTypeParameterName"})
    public <E$$ extends Augmentation<Metric>> E$$ augmentation(Class<E$$> augmentationType) {
        return (E$$) augmentation.get(Objects.requireNonNull(augmentationType));
    }

    public MetricBuilder withKey(final MetricKey key) {
        this.key = key;
        return this;
    }
    
    public MetricBuilder setLabelSetId(final String value) {
        this._labelSetId = value;
        return this;
    }
    
    public MetricBuilder setLabels(final Labels value) {
        this._labels = value;
        return this;
    }
    
    public MetricBuilder setMetricPoints(final MetricPoints value) {
        this._metricPoints = value;
        return this;
    }
    
    public MetricBuilder setName(final String value) {
        this._name = value;
        return this;
    }
    
    /**
      * Add an augmentation to this builder's product.
      *
      * @param augmentation augmentation to be added
      * @return this builder
      * @throws NullPointerException if {@code augmentation} is null
      */
    public MetricBuilder addAugmentation(Augmentation<Metric> augmentation) {
        Class<? extends Augmentation<Metric>> augmentationType = augmentation.implementedInterface();
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
    public MetricBuilder removeAugmentation(Class<? extends Augmentation<Metric>> augmentationType) {
        if (this.augmentation instanceof HashMap) {
            this.augmentation.remove(augmentationType);
        }
        return this;
    }

    @Override
    public Metric build() {
        return new MetricImpl(this);
    }

    private static final class MetricImpl
        extends AbstractAugmentable<Metric>
        implements Metric {
    
        private final String _labelSetId;
        private final Labels _labels;
        private final MetricPoints _metricPoints;
        private final String _name;
        private final MetricKey key;
    
        MetricImpl(MetricBuilder base) {
            super(base.augmentation);
            if (base.key() != null) {
                this.key = base.key();
            } else {
                this.key = new MetricKey(base.getLabelSetId());
            }
            this._labelSetId = key.getLabelSetId();
            this._labels = base.getLabels();
            this._metricPoints = base.getMetricPoints();
            this._name = base.getName();
        }
    
        @Override
        public MetricKey key() {
            return key;
        }
        
        @Override
        public String getLabelSetId() {
            return _labelSetId;
        }
        
        @Override
        public Labels getLabels() {
            return _labels;
        }
        
        @Override
        public MetricPoints getMetricPoints() {
            return _metricPoints;
        }
        
        @Override
        public String getName() {
            return _name;
        }
    
        private int hash = 0;
        private volatile boolean hashValid = false;
        
        @Override
        public int hashCode() {
            if (hashValid) {
                return hash;
            }
        
            final int result = Metric.bindingHashCode(this);
            hash = result;
            hashValid = true;
            return result;
        }
    
        @Override
        public boolean equals(Object obj) {
            return Metric.bindingEquals(this, obj);
        }
    
        @Override
        public String toString() {
            return Metric.bindingToString(this);
        }
    }
}
