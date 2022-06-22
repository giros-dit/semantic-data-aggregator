package org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.eve.rev210504;
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
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.eve.rev210504.context.set.Labels;
import org.opendaylight.yangtools.concepts.Builder;
import org.opendaylight.yangtools.yang.binding.AbstractAugmentable;
import org.opendaylight.yangtools.yang.binding.Augmentation;
import org.opendaylight.yangtools.yang.binding.CodeHelpers;
import org.opendaylight.yangtools.yang.binding.DataObject;

/**
 * Class that builds {@link EveRecordBuilder} instances. Overall design of the class is that of a
 * <a href="https://en.wikipedia.org/wiki/Fluent_interface">fluent interface</a>, where method chaining is used.
 * 
 * <p>
 * In general, this class is supposed to be used like this template:
 * <pre>
 *   <code>
 *     EveRecordBuilder createTarget(int fooXyzzy, int barBaz) {
 *         return new EveRecordBuilderBuilder()
 *             .setFoo(new FooBuilder().setXyzzy(fooXyzzy).build())
 *             .setBar(new BarBuilder().setBaz(barBaz).build())
 *             .build();
 *     }
 *   </code>
 * </pre>
 * 
 * <p>
 * This pattern is supported by the immutable nature of EveRecordBuilder, as instances can be freely passed around without
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
 * @see EveRecordBuilder
 * @see Builder
 *
 */
@Generated("mdsal-binding-generator")
public class EveRecordBuilder implements Builder<EveRecord> {

    private String _deviceId;
    private Labels _labels;
    private BigDecimal _timestamp;
    private String _unit;
    private BigDecimal _value;


    Map<Class<? extends Augmentation<EveRecord>>, Augmentation<EveRecord>> augmentation = Collections.emptyMap();

    public EveRecordBuilder() {
    }
    
    
    
    public EveRecordBuilder(org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.eve.rev210504.ContextSet arg) {
        this._labels = arg.getLabels();
    }

    public EveRecordBuilder(EveRecord base) {
        Map<Class<? extends Augmentation<EveRecord>>, Augmentation<EveRecord>> aug = base.augmentations();
        if (!aug.isEmpty()) {
            this.augmentation = new HashMap<>(aug);
        }
        this._deviceId = base.getDeviceId();
        this._labels = base.getLabels();
        this._timestamp = base.getTimestamp();
        this._unit = base.getUnit();
        this._value = base.getValue();
    }

    /**
     * Set fields from given grouping argument. Valid argument is instance of one of following types:
     * <ul>
     * <li>org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.eve.rev210504.ContextSet</li>
     * </ul>
     *
     * @param arg grouping object
     * @throws IllegalArgumentException if given argument is none of valid types or has property with incompatible value
    */
    public void fieldsFrom(DataObject arg) {
        boolean isValidArg = false;
        if (arg instanceof org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.eve.rev210504.ContextSet) {
            this._labels = ((org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.eve.rev210504.ContextSet)arg).getLabels();
            isValidArg = true;
        }
        CodeHelpers.validValue(isValidArg, arg, "[org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.eve.rev210504.ContextSet]");
    }

    public String getDeviceId() {
        return _deviceId;
    }
    
    public Labels getLabels() {
        return _labels;
    }
    
    public BigDecimal getTimestamp() {
        return _timestamp;
    }
    
    public String getUnit() {
        return _unit;
    }
    
    public BigDecimal getValue() {
        return _value;
    }

    @SuppressWarnings({ "unchecked", "checkstyle:methodTypeParameterName"})
    public <E$$ extends Augmentation<EveRecord>> E$$ augmentation(Class<E$$> augmentationType) {
        return (E$$) augmentation.get(Objects.requireNonNull(augmentationType));
    }

    
    public EveRecordBuilder setDeviceId(final String value) {
        this._deviceId = value;
        return this;
    }
    
    public EveRecordBuilder setLabels(final Labels value) {
        this._labels = value;
        return this;
    }
    
    
    public EveRecordBuilder setTimestamp(final BigDecimal value) {
        if (value != null) {
            
        }
        this._timestamp = value;
        return this;
    }
    
    public EveRecordBuilder setUnit(final String value) {
        this._unit = value;
        return this;
    }
    
    
    public EveRecordBuilder setValue(final BigDecimal value) {
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
    public EveRecordBuilder addAugmentation(Augmentation<EveRecord> augmentation) {
        Class<? extends Augmentation<EveRecord>> augmentationType = augmentation.implementedInterface();
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
    public EveRecordBuilder removeAugmentation(Class<? extends Augmentation<EveRecord>> augmentationType) {
        if (this.augmentation instanceof HashMap) {
            this.augmentation.remove(augmentationType);
        }
        return this;
    }

    @Override
    public EveRecord build() {
        return new EveRecordImpl(this);
    }

    private static final class EveRecordImpl
        extends AbstractAugmentable<EveRecord>
        implements EveRecord {
    
        private final String _deviceId;
        private final Labels _labels;
        private final BigDecimal _timestamp;
        private final String _unit;
        private final BigDecimal _value;
    
        EveRecordImpl(EveRecordBuilder base) {
            super(base.augmentation);
            this._deviceId = base.getDeviceId();
            this._labels = base.getLabels();
            this._timestamp = base.getTimestamp();
            this._unit = base.getUnit();
            this._value = base.getValue();
        }
    
        @Override
        public String getDeviceId() {
            return _deviceId;
        }
        
        @Override
        public Labels getLabels() {
            return _labels;
        }
        
        @Override
        public BigDecimal getTimestamp() {
            return _timestamp;
        }
        
        @Override
        public String getUnit() {
            return _unit;
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
        
            final int result = EveRecord.bindingHashCode(this);
            hash = result;
            hashValid = true;
            return result;
        }
    
        @Override
        public boolean equals(Object obj) {
            return EveRecord.bindingEquals(this, obj);
        }
    
        @Override
        public String toString() {
            return EveRecord.bindingToString(this);
        }
    }
}
