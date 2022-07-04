package org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.openconfig.interfaces.aggregated.rev220513.interfaces._interface;
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
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.openconfig.interfaces.aggregated.rev220513.interfaces._interface.throughput.kpi.notification.ThroughputKpi;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715.DateAndTime;
import org.opendaylight.yangtools.concepts.Builder;
import org.opendaylight.yangtools.yang.binding.AbstractAugmentable;
import org.opendaylight.yangtools.yang.binding.Augmentation;

/**
 * Class that builds {@link ThroughputKpiNotificationBuilder} instances. Overall design of the class is that of a
 * <a href="https://en.wikipedia.org/wiki/Fluent_interface">fluent interface</a>, where method chaining is used.
 *
 * <p>
 * In general, this class is supposed to be used like this template:
 * <pre>
 *   <code>
 *     ThroughputKpiNotificationBuilder createTarget(int fooXyzzy, int barBaz) {
 *         return new ThroughputKpiNotificationBuilderBuilder()
 *             .setFoo(new FooBuilder().setXyzzy(fooXyzzy).build())
 *             .setBar(new BarBuilder().setBaz(barBaz).build())
 *             .build();
 *     }
 *   </code>
 * </pre>
 *
 * <p>
 * This pattern is supported by the immutable nature of ThroughputKpiNotificationBuilder, as instances can be freely passed around without
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
 * @see ThroughputKpiNotificationBuilder
 * @see Builder
 *
 */
@Generated("mdsal-binding-generator")
public class ThroughputKpiNotificationBuilder implements Builder<ThroughputKpiNotification> {

    private DateAndTime _eventTime;
    private ThroughputKpi _throughputKpi;


    Map<Class<? extends Augmentation<ThroughputKpiNotification>>, Augmentation<ThroughputKpiNotification>> augmentation = Collections.emptyMap();

    public ThroughputKpiNotificationBuilder() {
    }
    
    

    public ThroughputKpiNotificationBuilder(ThroughputKpiNotification base) {
        Map<Class<? extends Augmentation<ThroughputKpiNotification>>, Augmentation<ThroughputKpiNotification>> aug = base.augmentations();
        if (!aug.isEmpty()) {
            this.augmentation = new HashMap<>(aug);
        }
        this._eventTime = base.getEventTime();
        this._throughputKpi = base.getThroughputKpi();
    }


    public DateAndTime getEventTime() {
        return _eventTime;
    }
    
    public ThroughputKpi getThroughputKpi() {
        return _throughputKpi;
    }

    @SuppressWarnings({ "unchecked", "checkstyle:methodTypeParameterName"})
    public <E$$ extends Augmentation<ThroughputKpiNotification>> E$$ augmentation(Class<E$$> augmentationType) {
        return (E$$) augmentation.get(Objects.requireNonNull(augmentationType));
    }

    
    public ThroughputKpiNotificationBuilder setEventTime(final DateAndTime value) {
        this._eventTime = value;
        return this;
    }
    
    public ThroughputKpiNotificationBuilder setThroughputKpi(final ThroughputKpi value) {
        this._throughputKpi = value;
        return this;
    }
    
    /**
      * Add an augmentation to this builder's product.
      *
      * @param augmentation augmentation to be added
      * @return this builder
      * @throws NullPointerException if {@code augmentation} is null
      */
    public ThroughputKpiNotificationBuilder addAugmentation(Augmentation<ThroughputKpiNotification> augmentation) {
        Class<? extends Augmentation<ThroughputKpiNotification>> augmentationType = augmentation.implementedInterface();
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
    public ThroughputKpiNotificationBuilder removeAugmentation(Class<? extends Augmentation<ThroughputKpiNotification>> augmentationType) {
        if (this.augmentation instanceof HashMap) {
            this.augmentation.remove(augmentationType);
        }
        return this;
    }

    @Override
    public ThroughputKpiNotification build() {
        return new ThroughputKpiNotificationImpl(this);
    }

    private static final class ThroughputKpiNotificationImpl
        extends AbstractAugmentable<ThroughputKpiNotification>
        implements ThroughputKpiNotification {
    
        private final DateAndTime _eventTime;
        private final ThroughputKpi _throughputKpi;
    
        ThroughputKpiNotificationImpl(ThroughputKpiNotificationBuilder base) {
            super(base.augmentation);
            this._eventTime = base.getEventTime();
            this._throughputKpi = base.getThroughputKpi();
        }
    
        @Override
        public DateAndTime getEventTime() {
            return _eventTime;
        }
        
        @Override
        public ThroughputKpi getThroughputKpi() {
            return _throughputKpi;
        }
    
        private int hash = 0;
        private volatile boolean hashValid = false;
        
        @Override
        public int hashCode() {
            if (hashValid) {
                return hash;
            }
        
            final int result = ThroughputKpiNotification.bindingHashCode(this);
            hash = result;
            hashValid = true;
            return result;
        }
    
        @Override
        public boolean equals(Object obj) {
            return ThroughputKpiNotification.bindingEquals(this, obj);
        }
    
        @Override
        public String toString() {
            return ThroughputKpiNotification.bindingToString(this);
        }
    }
}
