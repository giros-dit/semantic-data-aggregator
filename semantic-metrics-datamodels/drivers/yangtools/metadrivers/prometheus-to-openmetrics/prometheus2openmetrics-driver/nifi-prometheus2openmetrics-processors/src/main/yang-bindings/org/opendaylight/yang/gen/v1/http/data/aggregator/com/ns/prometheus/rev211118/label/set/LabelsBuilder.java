package org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.prometheus.rev211118.label.set;
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
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.prometheus.rev211118.label.set.labels.Label;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.prometheus.rev211118.label.set.labels.LabelKey;
import org.opendaylight.yangtools.concepts.Builder;
import org.opendaylight.yangtools.yang.binding.AbstractAugmentable;
import org.opendaylight.yangtools.yang.binding.Augmentation;
import org.opendaylight.yangtools.yang.binding.CodeHelpers;

/**
 * Class that builds {@link LabelsBuilder} instances. Overall design of the class is that of a
 * <a href="https://en.wikipedia.org/wiki/Fluent_interface">fluent interface</a>, where method chaining is used.
 *
 * <p>
 * In general, this class is supposed to be used like this template:
 * <pre>
 *   <code>
 *     LabelsBuilder createTarget(int fooXyzzy, int barBaz) {
 *         return new LabelsBuilderBuilder()
 *             .setFoo(new FooBuilder().setXyzzy(fooXyzzy).build())
 *             .setBar(new BarBuilder().setBaz(barBaz).build())
 *             .build();
 *     }
 *   </code>
 * </pre>
 *
 * <p>
 * This pattern is supported by the immutable nature of LabelsBuilder, as instances can be freely passed around without
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
 * @see LabelsBuilder
 * @see Builder
 *
 */
@Generated("mdsal-binding-generator")
public class LabelsBuilder implements Builder<Labels> {

    private Map<LabelKey, Label> _label;


    Map<Class<? extends Augmentation<Labels>>, Augmentation<Labels>> augmentation = Collections.emptyMap();

    public LabelsBuilder() {
    }
    
    

    public LabelsBuilder(Labels base) {
        Map<Class<? extends Augmentation<Labels>>, Augmentation<Labels>> aug = base.augmentations();
        if (!aug.isEmpty()) {
            this.augmentation = new HashMap<>(aug);
        }
        this._label = base.getLabel();
    }


    public Map<LabelKey, Label> getLabel() {
        return _label;
    }

    @SuppressWarnings({ "unchecked", "checkstyle:methodTypeParameterName"})
    public <E$$ extends Augmentation<Labels>> E$$ augmentation(Class<E$$> augmentationType) {
        return (E$$) augmentation.get(Objects.requireNonNull(augmentationType));
    }

    public LabelsBuilder setLabel(final Map<LabelKey, Label> values) {
        this._label = values;
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
      * @deprecated Use {@link #setLabel(Map)} instead.
      */
    @Deprecated(forRemoval = true)
    public LabelsBuilder setLabel(final List<Label> values) {
        return setLabel(CodeHelpers.compatMap(values));
    }
    
    /**
      * Add an augmentation to this builder's product.
      *
      * @param augmentation augmentation to be added
      * @return this builder
      * @throws NullPointerException if {@code augmentation} is null
      */
    public LabelsBuilder addAugmentation(Augmentation<Labels> augmentation) {
        Class<? extends Augmentation<Labels>> augmentationType = augmentation.implementedInterface();
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
    public LabelsBuilder removeAugmentation(Class<? extends Augmentation<Labels>> augmentationType) {
        if (this.augmentation instanceof HashMap) {
            this.augmentation.remove(augmentationType);
        }
        return this;
    }

    @Override
    public Labels build() {
        return new LabelsImpl(this);
    }

    private static final class LabelsImpl
        extends AbstractAugmentable<Labels>
        implements Labels {
    
        private final Map<LabelKey, Label> _label;
    
        LabelsImpl(LabelsBuilder base) {
            super(base.augmentation);
            this._label = CodeHelpers.emptyToNull(base.getLabel());
        }
    
        @Override
        public Map<LabelKey, Label> getLabel() {
            return _label;
        }
    
        private int hash = 0;
        private volatile boolean hashValid = false;
        
        @Override
        public int hashCode() {
            if (hashValid) {
                return hash;
            }
        
            final int result = Labels.bindingHashCode(this);
            hash = result;
            hashValid = true;
            return result;
        }
    
        @Override
        public boolean equals(Object obj) {
            return Labels.bindingEquals(this, obj);
        }
    
        @Override
        public String toString() {
            return Labels.bindingToString(this);
        }
    }
}
