package org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.openconfig.interfaces.aggregated.rev220513.interfaces._interface.packet.loss.kpi.notification;
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
 * Class that builds {@link PacketLossKpiBuilder} instances. Overall design of the class is that of a
 * <a href="https://en.wikipedia.org/wiki/Fluent_interface">fluent interface</a>, where method chaining is used.
 *
 * <p>
 * In general, this class is supposed to be used like this template:
 * <pre>
 *   <code>
 *     PacketLossKpiBuilder createTarget(int fooXyzzy, int barBaz) {
 *         return new PacketLossKpiBuilderBuilder()
 *             .setFoo(new FooBuilder().setXyzzy(fooXyzzy).build())
 *             .setBar(new BarBuilder().setBaz(barBaz).build())
 *             .build();
 *     }
 *   </code>
 * </pre>
 *
 * <p>
 * This pattern is supported by the immutable nature of PacketLossKpiBuilder, as instances can be freely passed around without
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
 * @see PacketLossKpiBuilder
 * @see Builder
 *
 */
@Generated("mdsal-binding-generator")
public class PacketLossKpiBuilder implements Builder<PacketLossKpi> {

    private Timestamp _duration;
    private PerDecimal _packetLossIn;
    private PerDecimal _packetLossOut;


    Map<Class<? extends Augmentation<PacketLossKpi>>, Augmentation<PacketLossKpi>> augmentation = Collections.emptyMap();

    public PacketLossKpiBuilder() {
    }
    
    

    public PacketLossKpiBuilder(PacketLossKpi base) {
        Map<Class<? extends Augmentation<PacketLossKpi>>, Augmentation<PacketLossKpi>> aug = base.augmentations();
        if (!aug.isEmpty()) {
            this.augmentation = new HashMap<>(aug);
        }
        this._duration = base.getDuration();
        this._packetLossIn = base.getPacketLossIn();
        this._packetLossOut = base.getPacketLossOut();
    }


    public Timestamp getDuration() {
        return _duration;
    }
    
    public PerDecimal getPacketLossIn() {
        return _packetLossIn;
    }
    
    public PerDecimal getPacketLossOut() {
        return _packetLossOut;
    }

    @SuppressWarnings({ "unchecked", "checkstyle:methodTypeParameterName"})
    public <E$$ extends Augmentation<PacketLossKpi>> E$$ augmentation(Class<E$$> augmentationType) {
        return (E$$) augmentation.get(Objects.requireNonNull(augmentationType));
    }

    
    public PacketLossKpiBuilder setDuration(final Timestamp value) {
        this._duration = value;
        return this;
    }
    
    public PacketLossKpiBuilder setPacketLossIn(final PerDecimal value) {
        this._packetLossIn = value;
        return this;
    }
    
    public PacketLossKpiBuilder setPacketLossOut(final PerDecimal value) {
        this._packetLossOut = value;
        return this;
    }
    
    /**
      * Add an augmentation to this builder's product.
      *
      * @param augmentation augmentation to be added
      * @return this builder
      * @throws NullPointerException if {@code augmentation} is null
      */
    public PacketLossKpiBuilder addAugmentation(Augmentation<PacketLossKpi> augmentation) {
        Class<? extends Augmentation<PacketLossKpi>> augmentationType = augmentation.implementedInterface();
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
    public PacketLossKpiBuilder removeAugmentation(Class<? extends Augmentation<PacketLossKpi>> augmentationType) {
        if (this.augmentation instanceof HashMap) {
            this.augmentation.remove(augmentationType);
        }
        return this;
    }

    @Override
    public PacketLossKpi build() {
        return new PacketLossKpiImpl(this);
    }

    private static final class PacketLossKpiImpl
        extends AbstractAugmentable<PacketLossKpi>
        implements PacketLossKpi {
    
        private final Timestamp _duration;
        private final PerDecimal _packetLossIn;
        private final PerDecimal _packetLossOut;
    
        PacketLossKpiImpl(PacketLossKpiBuilder base) {
            super(base.augmentation);
            this._duration = base.getDuration();
            this._packetLossIn = base.getPacketLossIn();
            this._packetLossOut = base.getPacketLossOut();
        }
    
        @Override
        public Timestamp getDuration() {
            return _duration;
        }
        
        @Override
        public PerDecimal getPacketLossIn() {
            return _packetLossIn;
        }
        
        @Override
        public PerDecimal getPacketLossOut() {
            return _packetLossOut;
        }
    
        private int hash = 0;
        private volatile boolean hashValid = false;
        
        @Override
        public int hashCode() {
            if (hashValid) {
                return hash;
            }
        
            final int result = PacketLossKpi.bindingHashCode(this);
            hash = result;
            hashValid = true;
            return result;
        }
    
        @Override
        public boolean equals(Object obj) {
            return PacketLossKpi.bindingEquals(this, obj);
        }
    
        @Override
        public String toString() {
            return PacketLossKpi.bindingToString(this);
        }
    }
}
