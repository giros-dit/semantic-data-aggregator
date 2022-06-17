package org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.openconfig.interfaces.aggregated.rev220513.interfaces._interface.throughput.kpi.notification;
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
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.openconfig.interfaces.aggregated.rev220513.PerDecimal;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715.Timestamp;
import org.opendaylight.yangtools.concepts.Builder;
import org.opendaylight.yangtools.yang.binding.AbstractAugmentable;
import org.opendaylight.yangtools.yang.binding.Augmentation;

/**
 * Class that builds {@link ThroughputKpiBuilder} instances. Overall design of the class is that of a
 * <a href="https://en.wikipedia.org/wiki/Fluent_interface">fluent interface</a>, where method chaining is used.
 *
 * <p>
 * In general, this class is supposed to be used like this template:
 * <pre>
 *   <code>
 *     ThroughputKpiBuilder createTarget(int fooXyzzy, int barBaz) {
 *         return new ThroughputKpiBuilderBuilder()
 *             .setFoo(new FooBuilder().setXyzzy(fooXyzzy).build())
 *             .setBar(new BarBuilder().setBaz(barBaz).build())
 *             .build();
 *     }
 *   </code>
 * </pre>
 *
 * <p>
 * This pattern is supported by the immutable nature of ThroughputKpiBuilder, as instances can be freely passed around without
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
 * @see ThroughputKpiBuilder
 * @see Builder
 *
 */
@Generated("mdsal-binding-generator")
public class ThroughputKpiBuilder implements Builder<ThroughputKpi> {

    private Timestamp _duration;
    private PerDecimal _throughputIn;
    private PerDecimal _throughputOut;


    Map<Class<? extends Augmentation<ThroughputKpi>>, Augmentation<ThroughputKpi>> augmentation = Collections.emptyMap();

    public ThroughputKpiBuilder() {
    }
    
    

    public ThroughputKpiBuilder(ThroughputKpi base) {
        Map<Class<? extends Augmentation<ThroughputKpi>>, Augmentation<ThroughputKpi>> aug = base.augmentations();
        if (!aug.isEmpty()) {
            this.augmentation = new HashMap<>(aug);
        }
        this._duration = base.getDuration();
        this._throughputIn = base.getThroughputIn();
        this._throughputOut = base.getThroughputOut();
    }


    public Timestamp getDuration() {
        return _duration;
    }
    
    public PerDecimal getThroughputIn() {
        return _throughputIn;
    }
    
    public PerDecimal getThroughputOut() {
        return _throughputOut;
    }

    @SuppressWarnings({ "unchecked", "checkstyle:methodTypeParameterName"})
    public <E$$ extends Augmentation<ThroughputKpi>> E$$ augmentation(Class<E$$> augmentationType) {
        return (E$$) augmentation.get(Objects.requireNonNull(augmentationType));
    }

    
    public ThroughputKpiBuilder setDuration(final Timestamp value) {
        this._duration = value;
        return this;
    }
    
    public ThroughputKpiBuilder setThroughputIn(final PerDecimal value) {
        this._throughputIn = value;
        return this;
    }
    
    public ThroughputKpiBuilder setThroughputOut(final PerDecimal value) {
        this._throughputOut = value;
        return this;
    }
    
    /**
      * Add an augmentation to this builder's product.
      *
      * @param augmentation augmentation to be added
      * @return this builder
      * @throws NullPointerException if {@code augmentation} is null
      */
    public ThroughputKpiBuilder addAugmentation(Augmentation<ThroughputKpi> augmentation) {
        Class<? extends Augmentation<ThroughputKpi>> augmentationType = augmentation.implementedInterface();
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
    public ThroughputKpiBuilder removeAugmentation(Class<? extends Augmentation<ThroughputKpi>> augmentationType) {
        if (this.augmentation instanceof HashMap) {
            this.augmentation.remove(augmentationType);
        }
        return this;
    }

    @Override
    public ThroughputKpi build() {
        return new ThroughputKpiImpl(this);
    }

    private static final class ThroughputKpiImpl
        extends AbstractAugmentable<ThroughputKpi>
        implements ThroughputKpi {
    
        private final Timestamp _duration;
        private final PerDecimal _throughputIn;
        private final PerDecimal _throughputOut;
    
        ThroughputKpiImpl(ThroughputKpiBuilder base) {
            super(base.augmentation);
            this._duration = base.getDuration();
            this._throughputIn = base.getThroughputIn();
            this._throughputOut = base.getThroughputOut();
        }
    
        @Override
        public Timestamp getDuration() {
            return _duration;
        }
        
        @Override
        public PerDecimal getThroughputIn() {
            return _throughputIn;
        }
        
        @Override
        public PerDecimal getThroughputOut() {
            return _throughputOut;
        }
    
        private int hash = 0;
        private volatile boolean hashValid = false;
        
        @Override
        public int hashCode() {
            if (hashValid) {
                return hash;
            }
        
            final int result = ThroughputKpi.bindingHashCode(this);
            hash = result;
            hashValid = true;
            return result;
        }
    
        @Override
        public boolean equals(Object obj) {
            return ThroughputKpi.bindingEquals(this, obj);
        }
    
        @Override
        public String toString() {
            return ThroughputKpi.bindingToString(this);
        }
    }
}
