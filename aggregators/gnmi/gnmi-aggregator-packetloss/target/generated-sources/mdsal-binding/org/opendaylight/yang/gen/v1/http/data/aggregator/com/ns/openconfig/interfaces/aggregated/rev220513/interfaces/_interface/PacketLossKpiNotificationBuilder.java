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
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.openconfig.interfaces.aggregated.rev220513.interfaces._interface.packet.loss.kpi.notification.PacketLossKpi;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715.DateAndTime;
import org.opendaylight.yangtools.concepts.Builder;
import org.opendaylight.yangtools.yang.binding.AbstractAugmentable;
import org.opendaylight.yangtools.yang.binding.Augmentation;

/**
 * Class that builds {@link PacketLossKpiNotificationBuilder} instances. Overall design of the class is that of a
 * <a href="https://en.wikipedia.org/wiki/Fluent_interface">fluent interface</a>, where method chaining is used.
 *
 * <p>
 * In general, this class is supposed to be used like this template:
 * <pre>
 *   <code>
 *     PacketLossKpiNotificationBuilder createTarget(int fooXyzzy, int barBaz) {
 *         return new PacketLossKpiNotificationBuilderBuilder()
 *             .setFoo(new FooBuilder().setXyzzy(fooXyzzy).build())
 *             .setBar(new BarBuilder().setBaz(barBaz).build())
 *             .build();
 *     }
 *   </code>
 * </pre>
 *
 * <p>
 * This pattern is supported by the immutable nature of PacketLossKpiNotificationBuilder, as instances can be freely passed around without
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
 * @see PacketLossKpiNotificationBuilder
 * @see Builder
 *
 */
@Generated("mdsal-binding-generator")
public class PacketLossKpiNotificationBuilder implements Builder<PacketLossKpiNotification> {

    private DateAndTime _eventTime;
    private PacketLossKpi _packetLossKpi;


    Map<Class<? extends Augmentation<PacketLossKpiNotification>>, Augmentation<PacketLossKpiNotification>> augmentation = Collections.emptyMap();

    public PacketLossKpiNotificationBuilder() {
    }
    
    

    public PacketLossKpiNotificationBuilder(PacketLossKpiNotification base) {
        Map<Class<? extends Augmentation<PacketLossKpiNotification>>, Augmentation<PacketLossKpiNotification>> aug = base.augmentations();
        if (!aug.isEmpty()) {
            this.augmentation = new HashMap<>(aug);
        }
        this._eventTime = base.getEventTime();
        this._packetLossKpi = base.getPacketLossKpi();
    }


    public DateAndTime getEventTime() {
        return _eventTime;
    }
    
    public PacketLossKpi getPacketLossKpi() {
        return _packetLossKpi;
    }

    @SuppressWarnings({ "unchecked", "checkstyle:methodTypeParameterName"})
    public <E$$ extends Augmentation<PacketLossKpiNotification>> E$$ augmentation(Class<E$$> augmentationType) {
        return (E$$) augmentation.get(Objects.requireNonNull(augmentationType));
    }

    
    public PacketLossKpiNotificationBuilder setEventTime(final DateAndTime value) {
        this._eventTime = value;
        return this;
    }
    
    public PacketLossKpiNotificationBuilder setPacketLossKpi(final PacketLossKpi value) {
        this._packetLossKpi = value;
        return this;
    }
    
    /**
      * Add an augmentation to this builder's product.
      *
      * @param augmentation augmentation to be added
      * @return this builder
      * @throws NullPointerException if {@code augmentation} is null
      */
    public PacketLossKpiNotificationBuilder addAugmentation(Augmentation<PacketLossKpiNotification> augmentation) {
        Class<? extends Augmentation<PacketLossKpiNotification>> augmentationType = augmentation.implementedInterface();
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
    public PacketLossKpiNotificationBuilder removeAugmentation(Class<? extends Augmentation<PacketLossKpiNotification>> augmentationType) {
        if (this.augmentation instanceof HashMap) {
            this.augmentation.remove(augmentationType);
        }
        return this;
    }

    @Override
    public PacketLossKpiNotification build() {
        return new PacketLossKpiNotificationImpl(this);
    }

    private static final class PacketLossKpiNotificationImpl
        extends AbstractAugmentable<PacketLossKpiNotification>
        implements PacketLossKpiNotification {
    
        private final DateAndTime _eventTime;
        private final PacketLossKpi _packetLossKpi;
    
        PacketLossKpiNotificationImpl(PacketLossKpiNotificationBuilder base) {
            super(base.augmentation);
            this._eventTime = base.getEventTime();
            this._packetLossKpi = base.getPacketLossKpi();
        }
    
        @Override
        public DateAndTime getEventTime() {
            return _eventTime;
        }
        
        @Override
        public PacketLossKpi getPacketLossKpi() {
            return _packetLossKpi;
        }
    
        private int hash = 0;
        private volatile boolean hashValid = false;
        
        @Override
        public int hashCode() {
            if (hashValid) {
                return hash;
            }
        
            final int result = PacketLossKpiNotification.bindingHashCode(this);
            hash = result;
            hashValid = true;
            return result;
        }
    
        @Override
        public boolean equals(Object obj) {
            return PacketLossKpiNotification.bindingEquals(this, obj);
        }
    
        @Override
        public String toString() {
            return PacketLossKpiNotification.bindingToString(this);
        }
    }
}
