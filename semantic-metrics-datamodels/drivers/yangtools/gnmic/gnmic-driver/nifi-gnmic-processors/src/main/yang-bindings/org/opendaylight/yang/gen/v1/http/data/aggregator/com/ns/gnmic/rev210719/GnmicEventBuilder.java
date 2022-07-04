package org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.gnmic.rev210719;
import java.lang.Class;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import javax.annotation.processing.Generated;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.gnmic.rev210719.tag.set.Tags;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.gnmic.rev210719.value.set.Values;
import org.opendaylight.yangtools.concepts.Builder;
import org.opendaylight.yangtools.yang.binding.AbstractAugmentable;
import org.opendaylight.yangtools.yang.binding.Augmentation;
import org.opendaylight.yangtools.yang.binding.CodeHelpers;
import org.opendaylight.yangtools.yang.binding.DataObject;

/**
 * Class that builds {@link GnmicEventBuilder} instances. Overall design of the class is that of a
 * <a href="https://en.wikipedia.org/wiki/Fluent_interface">fluent interface</a>, where method chaining is used.
 * 
 * <p>
 * In general, this class is supposed to be used like this template:
 * <pre>
 *   <code>
 *     GnmicEventBuilder createTarget(int fooXyzzy, int barBaz) {
 *         return new GnmicEventBuilderBuilder()
 *             .setFoo(new FooBuilder().setXyzzy(fooXyzzy).build())
 *             .setBar(new BarBuilder().setBaz(barBaz).build())
 *             .build();
 *     }
 *   </code>
 * </pre>
 * 
 * <p>
 * This pattern is supported by the immutable nature of GnmicEventBuilder, as instances can be freely passed around without
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
 * @see GnmicEventBuilder
 * @see Builder
 *
 */
@Generated("mdsal-binding-generator")
public class GnmicEventBuilder implements Builder<GnmicEvent> {

    private String _name;
    private Tags _tags;
    private Long _timestamp;
    private Values _values;


    Map<Class<? extends Augmentation<GnmicEvent>>, Augmentation<GnmicEvent>> augmentation = Collections.emptyMap();

    public GnmicEventBuilder() {
    }
    
    
    
    public GnmicEventBuilder(org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.gnmic.rev210719.ValueSet arg) {
        this._values = arg.getValues();
    }
    
    public GnmicEventBuilder(org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.gnmic.rev210719.TagSet arg) {
        this._tags = arg.getTags();
    }

    public GnmicEventBuilder(GnmicEvent base) {
        Map<Class<? extends Augmentation<GnmicEvent>>, Augmentation<GnmicEvent>> aug = base.augmentations();
        if (!aug.isEmpty()) {
            this.augmentation = new HashMap<>(aug);
        }
        this._name = base.getName();
        this._tags = base.getTags();
        this._timestamp = base.getTimestamp();
        this._values = base.getValues();
    }

    /**
     * Set fields from given grouping argument. Valid argument is instance of one of following types:
     * <ul>
     * <li>org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.gnmic.rev210719.TagSet</li>
     * <li>org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.gnmic.rev210719.ValueSet</li>
     * </ul>
     *
     * @param arg grouping object
     * @throws IllegalArgumentException if given argument is none of valid types or has property with incompatible value
    */
    public void fieldsFrom(DataObject arg) {
        boolean isValidArg = false;
        if (arg instanceof org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.gnmic.rev210719.TagSet) {
            this._tags = ((org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.gnmic.rev210719.TagSet)arg).getTags();
            isValidArg = true;
        }
        if (arg instanceof org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.gnmic.rev210719.ValueSet) {
            this._values = ((org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.gnmic.rev210719.ValueSet)arg).getValues();
            isValidArg = true;
        }
        CodeHelpers.validValue(isValidArg, arg, "[org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.gnmic.rev210719.TagSet, org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.gnmic.rev210719.ValueSet]");
    }

    public String getName() {
        return _name;
    }
    
    public Tags getTags() {
        return _tags;
    }
    
    public Long getTimestamp() {
        return _timestamp;
    }
    
    public Values getValues() {
        return _values;
    }

    @SuppressWarnings({ "unchecked", "checkstyle:methodTypeParameterName"})
    public <E$$ extends Augmentation<GnmicEvent>> E$$ augmentation(Class<E$$> augmentationType) {
        return (E$$) augmentation.get(Objects.requireNonNull(augmentationType));
    }

    
    public GnmicEventBuilder setName(final String value) {
        this._name = value;
        return this;
    }
    
    public GnmicEventBuilder setTags(final Tags value) {
        this._tags = value;
        return this;
    }
    
    public GnmicEventBuilder setTimestamp(final Long value) {
        this._timestamp = value;
        return this;
    }
    
    public GnmicEventBuilder setValues(final Values value) {
        this._values = value;
        return this;
    }
    
    /**
      * Add an augmentation to this builder's product.
      *
      * @param augmentation augmentation to be added
      * @return this builder
      * @throws NullPointerException if {@code augmentation} is null
      */
    public GnmicEventBuilder addAugmentation(Augmentation<GnmicEvent> augmentation) {
        Class<? extends Augmentation<GnmicEvent>> augmentationType = augmentation.implementedInterface();
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
    public GnmicEventBuilder removeAugmentation(Class<? extends Augmentation<GnmicEvent>> augmentationType) {
        if (this.augmentation instanceof HashMap) {
            this.augmentation.remove(augmentationType);
        }
        return this;
    }

    @Override
    public GnmicEvent build() {
        return new GnmicEventImpl(this);
    }

    private static final class GnmicEventImpl
        extends AbstractAugmentable<GnmicEvent>
        implements GnmicEvent {
    
        private final String _name;
        private final Tags _tags;
        private final Long _timestamp;
        private final Values _values;
    
        GnmicEventImpl(GnmicEventBuilder base) {
            super(base.augmentation);
            this._name = base.getName();
            this._tags = base.getTags();
            this._timestamp = base.getTimestamp();
            this._values = base.getValues();
        }
    
        @Override
        public String getName() {
            return _name;
        }
        
        @Override
        public Tags getTags() {
            return _tags;
        }
        
        @Override
        public Long getTimestamp() {
            return _timestamp;
        }
        
        @Override
        public Values getValues() {
            return _values;
        }
    
        private int hash = 0;
        private volatile boolean hashValid = false;
        
        @Override
        public int hashCode() {
            if (hashValid) {
                return hash;
            }
        
            final int result = GnmicEvent.bindingHashCode(this);
            hash = result;
            hashValid = true;
            return result;
        }
    
        @Override
        public boolean equals(Object obj) {
            return GnmicEvent.bindingEquals(this, obj);
        }
    
        @Override
        public String toString() {
            return GnmicEvent.bindingToString(this);
        }
    }
}
