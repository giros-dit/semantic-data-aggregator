package org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.interfaces.rev180220.interfaces._interface;
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
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715.Counter32;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715.Counter64;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715.DateAndTime;
import org.opendaylight.yangtools.concepts.Builder;
import org.opendaylight.yangtools.yang.binding.AbstractAugmentable;
import org.opendaylight.yangtools.yang.binding.Augmentation;

/**
 * Class that builds {@link StatisticsBuilder} instances. Overall design of the class is that of a
 * <a href="https://en.wikipedia.org/wiki/Fluent_interface">fluent interface</a>, where method chaining is used.
 *
 * <p>
 * In general, this class is supposed to be used like this template:
 * <pre>
 *   <code>
 *     StatisticsBuilder createTarget(int fooXyzzy, int barBaz) {
 *         return new StatisticsBuilderBuilder()
 *             .setFoo(new FooBuilder().setXyzzy(fooXyzzy).build())
 *             .setBar(new BarBuilder().setBaz(barBaz).build())
 *             .build();
 *     }
 *   </code>
 * </pre>
 *
 * <p>
 * This pattern is supported by the immutable nature of StatisticsBuilder, as instances can be freely passed around without
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
 * @see StatisticsBuilder
 * @see Builder
 *
 */
@Generated("mdsal-binding-generator")
public class StatisticsBuilder implements Builder<Statistics> {

    private DateAndTime _discontinuityTime;
    private Counter64 _inBroadcastPkts;
    private Counter32 _inDiscards;
    private Counter32 _inErrors;
    private Counter64 _inMulticastPkts;
    private Counter64 _inOctets;
    private Counter64 _inUnicastPkts;
    private Counter32 _inUnknownProtos;
    private Counter64 _outBroadcastPkts;
    private Counter32 _outDiscards;
    private Counter32 _outErrors;
    private Counter64 _outMulticastPkts;
    private Counter64 _outOctets;
    private Counter64 _outUnicastPkts;


    Map<Class<? extends Augmentation<Statistics>>, Augmentation<Statistics>> augmentation = Collections.emptyMap();

    public StatisticsBuilder() {
    }
    
    

    public StatisticsBuilder(Statistics base) {
        Map<Class<? extends Augmentation<Statistics>>, Augmentation<Statistics>> aug = base.augmentations();
        if (!aug.isEmpty()) {
            this.augmentation = new HashMap<>(aug);
        }
        this._discontinuityTime = base.getDiscontinuityTime();
        this._inBroadcastPkts = base.getInBroadcastPkts();
        this._inDiscards = base.getInDiscards();
        this._inErrors = base.getInErrors();
        this._inMulticastPkts = base.getInMulticastPkts();
        this._inOctets = base.getInOctets();
        this._inUnicastPkts = base.getInUnicastPkts();
        this._inUnknownProtos = base.getInUnknownProtos();
        this._outBroadcastPkts = base.getOutBroadcastPkts();
        this._outDiscards = base.getOutDiscards();
        this._outErrors = base.getOutErrors();
        this._outMulticastPkts = base.getOutMulticastPkts();
        this._outOctets = base.getOutOctets();
        this._outUnicastPkts = base.getOutUnicastPkts();
    }


    public DateAndTime getDiscontinuityTime() {
        return _discontinuityTime;
    }
    
    public Counter64 getInBroadcastPkts() {
        return _inBroadcastPkts;
    }
    
    public Counter32 getInDiscards() {
        return _inDiscards;
    }
    
    public Counter32 getInErrors() {
        return _inErrors;
    }
    
    public Counter64 getInMulticastPkts() {
        return _inMulticastPkts;
    }
    
    public Counter64 getInOctets() {
        return _inOctets;
    }
    
    public Counter64 getInUnicastPkts() {
        return _inUnicastPkts;
    }
    
    public Counter32 getInUnknownProtos() {
        return _inUnknownProtos;
    }
    
    public Counter64 getOutBroadcastPkts() {
        return _outBroadcastPkts;
    }
    
    public Counter32 getOutDiscards() {
        return _outDiscards;
    }
    
    public Counter32 getOutErrors() {
        return _outErrors;
    }
    
    public Counter64 getOutMulticastPkts() {
        return _outMulticastPkts;
    }
    
    public Counter64 getOutOctets() {
        return _outOctets;
    }
    
    public Counter64 getOutUnicastPkts() {
        return _outUnicastPkts;
    }

    @SuppressWarnings({ "unchecked", "checkstyle:methodTypeParameterName"})
    public <E$$ extends Augmentation<Statistics>> E$$ augmentation(Class<E$$> augmentationType) {
        return (E$$) augmentation.get(Objects.requireNonNull(augmentationType));
    }

    
    public StatisticsBuilder setDiscontinuityTime(final DateAndTime value) {
        this._discontinuityTime = value;
        return this;
    }
    
    public StatisticsBuilder setInBroadcastPkts(final Counter64 value) {
        this._inBroadcastPkts = value;
        return this;
    }
    
    public StatisticsBuilder setInDiscards(final Counter32 value) {
        this._inDiscards = value;
        return this;
    }
    
    public StatisticsBuilder setInErrors(final Counter32 value) {
        this._inErrors = value;
        return this;
    }
    
    public StatisticsBuilder setInMulticastPkts(final Counter64 value) {
        this._inMulticastPkts = value;
        return this;
    }
    
    public StatisticsBuilder setInOctets(final Counter64 value) {
        this._inOctets = value;
        return this;
    }
    
    public StatisticsBuilder setInUnicastPkts(final Counter64 value) {
        this._inUnicastPkts = value;
        return this;
    }
    
    public StatisticsBuilder setInUnknownProtos(final Counter32 value) {
        this._inUnknownProtos = value;
        return this;
    }
    
    public StatisticsBuilder setOutBroadcastPkts(final Counter64 value) {
        this._outBroadcastPkts = value;
        return this;
    }
    
    public StatisticsBuilder setOutDiscards(final Counter32 value) {
        this._outDiscards = value;
        return this;
    }
    
    public StatisticsBuilder setOutErrors(final Counter32 value) {
        this._outErrors = value;
        return this;
    }
    
    public StatisticsBuilder setOutMulticastPkts(final Counter64 value) {
        this._outMulticastPkts = value;
        return this;
    }
    
    public StatisticsBuilder setOutOctets(final Counter64 value) {
        this._outOctets = value;
        return this;
    }
    
    public StatisticsBuilder setOutUnicastPkts(final Counter64 value) {
        this._outUnicastPkts = value;
        return this;
    }
    
    /**
      * Add an augmentation to this builder's product.
      *
      * @param augmentation augmentation to be added
      * @return this builder
      * @throws NullPointerException if {@code augmentation} is null
      */
    public StatisticsBuilder addAugmentation(Augmentation<Statistics> augmentation) {
        Class<? extends Augmentation<Statistics>> augmentationType = augmentation.implementedInterface();
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
    public StatisticsBuilder removeAugmentation(Class<? extends Augmentation<Statistics>> augmentationType) {
        if (this.augmentation instanceof HashMap) {
            this.augmentation.remove(augmentationType);
        }
        return this;
    }

    @Override
    public Statistics build() {
        return new StatisticsImpl(this);
    }

    private static final class StatisticsImpl
        extends AbstractAugmentable<Statistics>
        implements Statistics {
    
        private final DateAndTime _discontinuityTime;
        private final Counter64 _inBroadcastPkts;
        private final Counter32 _inDiscards;
        private final Counter32 _inErrors;
        private final Counter64 _inMulticastPkts;
        private final Counter64 _inOctets;
        private final Counter64 _inUnicastPkts;
        private final Counter32 _inUnknownProtos;
        private final Counter64 _outBroadcastPkts;
        private final Counter32 _outDiscards;
        private final Counter32 _outErrors;
        private final Counter64 _outMulticastPkts;
        private final Counter64 _outOctets;
        private final Counter64 _outUnicastPkts;
    
        StatisticsImpl(StatisticsBuilder base) {
            super(base.augmentation);
            this._discontinuityTime = base.getDiscontinuityTime();
            this._inBroadcastPkts = base.getInBroadcastPkts();
            this._inDiscards = base.getInDiscards();
            this._inErrors = base.getInErrors();
            this._inMulticastPkts = base.getInMulticastPkts();
            this._inOctets = base.getInOctets();
            this._inUnicastPkts = base.getInUnicastPkts();
            this._inUnknownProtos = base.getInUnknownProtos();
            this._outBroadcastPkts = base.getOutBroadcastPkts();
            this._outDiscards = base.getOutDiscards();
            this._outErrors = base.getOutErrors();
            this._outMulticastPkts = base.getOutMulticastPkts();
            this._outOctets = base.getOutOctets();
            this._outUnicastPkts = base.getOutUnicastPkts();
        }
    
        @Override
        public DateAndTime getDiscontinuityTime() {
            return _discontinuityTime;
        }
        
        @Override
        public Counter64 getInBroadcastPkts() {
            return _inBroadcastPkts;
        }
        
        @Override
        public Counter32 getInDiscards() {
            return _inDiscards;
        }
        
        @Override
        public Counter32 getInErrors() {
            return _inErrors;
        }
        
        @Override
        public Counter64 getInMulticastPkts() {
            return _inMulticastPkts;
        }
        
        @Override
        public Counter64 getInOctets() {
            return _inOctets;
        }
        
        @Override
        public Counter64 getInUnicastPkts() {
            return _inUnicastPkts;
        }
        
        @Override
        public Counter32 getInUnknownProtos() {
            return _inUnknownProtos;
        }
        
        @Override
        public Counter64 getOutBroadcastPkts() {
            return _outBroadcastPkts;
        }
        
        @Override
        public Counter32 getOutDiscards() {
            return _outDiscards;
        }
        
        @Override
        public Counter32 getOutErrors() {
            return _outErrors;
        }
        
        @Override
        public Counter64 getOutMulticastPkts() {
            return _outMulticastPkts;
        }
        
        @Override
        public Counter64 getOutOctets() {
            return _outOctets;
        }
        
        @Override
        public Counter64 getOutUnicastPkts() {
            return _outUnicastPkts;
        }
    
        private int hash = 0;
        private volatile boolean hashValid = false;
        
        @Override
        public int hashCode() {
            if (hashValid) {
                return hash;
            }
        
            final int result = Statistics.bindingHashCode(this);
            hash = result;
            hashValid = true;
            return result;
        }
    
        @Override
        public boolean equals(Object obj) {
            return Statistics.bindingEquals(this, obj);
        }
    
        @Override
        public String toString() {
            return Statistics.bindingToString(this);
        }
    }
}
