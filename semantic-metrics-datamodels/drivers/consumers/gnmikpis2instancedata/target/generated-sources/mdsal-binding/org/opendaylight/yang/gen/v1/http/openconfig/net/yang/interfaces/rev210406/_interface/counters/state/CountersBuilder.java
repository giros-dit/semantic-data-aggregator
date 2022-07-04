package org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406._interface.counters.state;
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
import org.opendaylight.yang.gen.v1.http.openconfig.net.yang.openconfig.types.rev190416.Timeticks64;
import org.opendaylight.yang.gen.v1.http.openconfig.net.yang.types.yang.rev210714.Counter64;
import org.opendaylight.yangtools.concepts.Builder;
import org.opendaylight.yangtools.yang.binding.AbstractAugmentable;
import org.opendaylight.yangtools.yang.binding.Augmentation;

/**
 * Class that builds {@link CountersBuilder} instances. Overall design of the class is that of a
 * <a href="https://en.wikipedia.org/wiki/Fluent_interface">fluent interface</a>, where method chaining is used.
 *
 * <p>
 * In general, this class is supposed to be used like this template:
 * <pre>
 *   <code>
 *     CountersBuilder createTarget(int fooXyzzy, int barBaz) {
 *         return new CountersBuilderBuilder()
 *             .setFoo(new FooBuilder().setXyzzy(fooXyzzy).build())
 *             .setBar(new BarBuilder().setBaz(barBaz).build())
 *             .build();
 *     }
 *   </code>
 * </pre>
 *
 * <p>
 * This pattern is supported by the immutable nature of CountersBuilder, as instances can be freely passed around without
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
 * @see CountersBuilder
 * @see Builder
 *
 */
@Generated("mdsal-binding-generator")
public class CountersBuilder implements Builder<Counters> {

    private Counter64 _carrierTransitions;
    private Counter64 _inBroadcastPkts;
    private Counter64 _inDiscards;
    private Counter64 _inErrors;
    private Counter64 _inFcsErrors;
    private Counter64 _inMulticastPkts;
    private Counter64 _inOctets;
    private Counter64 _inPkts;
    private Counter64 _inUnicastPkts;
    private Counter64 _inUnknownProtos;
    private Timeticks64 _lastClear;
    private Counter64 _outBroadcastPkts;
    private Counter64 _outDiscards;
    private Counter64 _outErrors;
    private Counter64 _outMulticastPkts;
    private Counter64 _outOctets;
    private Counter64 _outPkts;
    private Counter64 _outUnicastPkts;


    Map<Class<? extends Augmentation<Counters>>, Augmentation<Counters>> augmentation = Collections.emptyMap();

    public CountersBuilder() {
    }
    
    

    public CountersBuilder(Counters base) {
        Map<Class<? extends Augmentation<Counters>>, Augmentation<Counters>> aug = base.augmentations();
        if (!aug.isEmpty()) {
            this.augmentation = new HashMap<>(aug);
        }
        this._carrierTransitions = base.getCarrierTransitions();
        this._inBroadcastPkts = base.getInBroadcastPkts();
        this._inDiscards = base.getInDiscards();
        this._inErrors = base.getInErrors();
        this._inFcsErrors = base.getInFcsErrors();
        this._inMulticastPkts = base.getInMulticastPkts();
        this._inOctets = base.getInOctets();
        this._inPkts = base.getInPkts();
        this._inUnicastPkts = base.getInUnicastPkts();
        this._inUnknownProtos = base.getInUnknownProtos();
        this._lastClear = base.getLastClear();
        this._outBroadcastPkts = base.getOutBroadcastPkts();
        this._outDiscards = base.getOutDiscards();
        this._outErrors = base.getOutErrors();
        this._outMulticastPkts = base.getOutMulticastPkts();
        this._outOctets = base.getOutOctets();
        this._outPkts = base.getOutPkts();
        this._outUnicastPkts = base.getOutUnicastPkts();
    }


    public Counter64 getCarrierTransitions() {
        return _carrierTransitions;
    }
    
    public Counter64 getInBroadcastPkts() {
        return _inBroadcastPkts;
    }
    
    public Counter64 getInDiscards() {
        return _inDiscards;
    }
    
    public Counter64 getInErrors() {
        return _inErrors;
    }
    
    public Counter64 getInFcsErrors() {
        return _inFcsErrors;
    }
    
    public Counter64 getInMulticastPkts() {
        return _inMulticastPkts;
    }
    
    public Counter64 getInOctets() {
        return _inOctets;
    }
    
    public Counter64 getInPkts() {
        return _inPkts;
    }
    
    public Counter64 getInUnicastPkts() {
        return _inUnicastPkts;
    }
    
    public Counter64 getInUnknownProtos() {
        return _inUnknownProtos;
    }
    
    public Timeticks64 getLastClear() {
        return _lastClear;
    }
    
    public Counter64 getOutBroadcastPkts() {
        return _outBroadcastPkts;
    }
    
    public Counter64 getOutDiscards() {
        return _outDiscards;
    }
    
    public Counter64 getOutErrors() {
        return _outErrors;
    }
    
    public Counter64 getOutMulticastPkts() {
        return _outMulticastPkts;
    }
    
    public Counter64 getOutOctets() {
        return _outOctets;
    }
    
    public Counter64 getOutPkts() {
        return _outPkts;
    }
    
    public Counter64 getOutUnicastPkts() {
        return _outUnicastPkts;
    }

    @SuppressWarnings({ "unchecked", "checkstyle:methodTypeParameterName"})
    public <E$$ extends Augmentation<Counters>> E$$ augmentation(Class<E$$> augmentationType) {
        return (E$$) augmentation.get(Objects.requireNonNull(augmentationType));
    }

    
    public CountersBuilder setCarrierTransitions(final Counter64 value) {
        this._carrierTransitions = value;
        return this;
    }
    
    public CountersBuilder setInBroadcastPkts(final Counter64 value) {
        this._inBroadcastPkts = value;
        return this;
    }
    
    public CountersBuilder setInDiscards(final Counter64 value) {
        this._inDiscards = value;
        return this;
    }
    
    public CountersBuilder setInErrors(final Counter64 value) {
        this._inErrors = value;
        return this;
    }
    
    public CountersBuilder setInFcsErrors(final Counter64 value) {
        this._inFcsErrors = value;
        return this;
    }
    
    public CountersBuilder setInMulticastPkts(final Counter64 value) {
        this._inMulticastPkts = value;
        return this;
    }
    
    public CountersBuilder setInOctets(final Counter64 value) {
        this._inOctets = value;
        return this;
    }
    
    public CountersBuilder setInPkts(final Counter64 value) {
        this._inPkts = value;
        return this;
    }
    
    public CountersBuilder setInUnicastPkts(final Counter64 value) {
        this._inUnicastPkts = value;
        return this;
    }
    
    public CountersBuilder setInUnknownProtos(final Counter64 value) {
        this._inUnknownProtos = value;
        return this;
    }
    
    public CountersBuilder setLastClear(final Timeticks64 value) {
        this._lastClear = value;
        return this;
    }
    
    public CountersBuilder setOutBroadcastPkts(final Counter64 value) {
        this._outBroadcastPkts = value;
        return this;
    }
    
    public CountersBuilder setOutDiscards(final Counter64 value) {
        this._outDiscards = value;
        return this;
    }
    
    public CountersBuilder setOutErrors(final Counter64 value) {
        this._outErrors = value;
        return this;
    }
    
    public CountersBuilder setOutMulticastPkts(final Counter64 value) {
        this._outMulticastPkts = value;
        return this;
    }
    
    public CountersBuilder setOutOctets(final Counter64 value) {
        this._outOctets = value;
        return this;
    }
    
    public CountersBuilder setOutPkts(final Counter64 value) {
        this._outPkts = value;
        return this;
    }
    
    public CountersBuilder setOutUnicastPkts(final Counter64 value) {
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
    public CountersBuilder addAugmentation(Augmentation<Counters> augmentation) {
        Class<? extends Augmentation<Counters>> augmentationType = augmentation.implementedInterface();
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
    public CountersBuilder removeAugmentation(Class<? extends Augmentation<Counters>> augmentationType) {
        if (this.augmentation instanceof HashMap) {
            this.augmentation.remove(augmentationType);
        }
        return this;
    }

    @Override
    public Counters build() {
        return new CountersImpl(this);
    }

    private static final class CountersImpl
        extends AbstractAugmentable<Counters>
        implements Counters {
    
        private final Counter64 _carrierTransitions;
        private final Counter64 _inBroadcastPkts;
        private final Counter64 _inDiscards;
        private final Counter64 _inErrors;
        private final Counter64 _inFcsErrors;
        private final Counter64 _inMulticastPkts;
        private final Counter64 _inOctets;
        private final Counter64 _inPkts;
        private final Counter64 _inUnicastPkts;
        private final Counter64 _inUnknownProtos;
        private final Timeticks64 _lastClear;
        private final Counter64 _outBroadcastPkts;
        private final Counter64 _outDiscards;
        private final Counter64 _outErrors;
        private final Counter64 _outMulticastPkts;
        private final Counter64 _outOctets;
        private final Counter64 _outPkts;
        private final Counter64 _outUnicastPkts;
    
        CountersImpl(CountersBuilder base) {
            super(base.augmentation);
            this._carrierTransitions = base.getCarrierTransitions();
            this._inBroadcastPkts = base.getInBroadcastPkts();
            this._inDiscards = base.getInDiscards();
            this._inErrors = base.getInErrors();
            this._inFcsErrors = base.getInFcsErrors();
            this._inMulticastPkts = base.getInMulticastPkts();
            this._inOctets = base.getInOctets();
            this._inPkts = base.getInPkts();
            this._inUnicastPkts = base.getInUnicastPkts();
            this._inUnknownProtos = base.getInUnknownProtos();
            this._lastClear = base.getLastClear();
            this._outBroadcastPkts = base.getOutBroadcastPkts();
            this._outDiscards = base.getOutDiscards();
            this._outErrors = base.getOutErrors();
            this._outMulticastPkts = base.getOutMulticastPkts();
            this._outOctets = base.getOutOctets();
            this._outPkts = base.getOutPkts();
            this._outUnicastPkts = base.getOutUnicastPkts();
        }
    
        @Override
        public Counter64 getCarrierTransitions() {
            return _carrierTransitions;
        }
        
        @Override
        public Counter64 getInBroadcastPkts() {
            return _inBroadcastPkts;
        }
        
        @Override
        public Counter64 getInDiscards() {
            return _inDiscards;
        }
        
        @Override
        public Counter64 getInErrors() {
            return _inErrors;
        }
        
        @Override
        public Counter64 getInFcsErrors() {
            return _inFcsErrors;
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
        public Counter64 getInPkts() {
            return _inPkts;
        }
        
        @Override
        public Counter64 getInUnicastPkts() {
            return _inUnicastPkts;
        }
        
        @Override
        public Counter64 getInUnknownProtos() {
            return _inUnknownProtos;
        }
        
        @Override
        public Timeticks64 getLastClear() {
            return _lastClear;
        }
        
        @Override
        public Counter64 getOutBroadcastPkts() {
            return _outBroadcastPkts;
        }
        
        @Override
        public Counter64 getOutDiscards() {
            return _outDiscards;
        }
        
        @Override
        public Counter64 getOutErrors() {
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
        public Counter64 getOutPkts() {
            return _outPkts;
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
        
            final int result = Counters.bindingHashCode(this);
            hash = result;
            hashValid = true;
            return result;
        }
    
        @Override
        public boolean equals(Object obj) {
            return Counters.bindingEquals(this, obj);
        }
    
        @Override
        public String toString() {
            return Counters.bindingToString(this);
        }
    }
}
