package org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008;
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
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.netflow.CollectorGoflow2;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.netflow.ExportPacket;
import org.opendaylight.yangtools.concepts.Builder;
import org.opendaylight.yangtools.yang.binding.AbstractAugmentable;
import org.opendaylight.yangtools.yang.binding.Augmentation;

/**
 * Class that builds {@link NetflowBuilder} instances. Overall design of the class is that of a
 * <a href="https://en.wikipedia.org/wiki/Fluent_interface">fluent interface</a>, where method chaining is used.
 *
 * <p>
 * In general, this class is supposed to be used like this template:
 * <pre>
 *   <code>
 *     NetflowBuilder createTarget(int fooXyzzy, int barBaz) {
 *         return new NetflowBuilderBuilder()
 *             .setFoo(new FooBuilder().setXyzzy(fooXyzzy).build())
 *             .setBar(new BarBuilder().setBaz(barBaz).build())
 *             .build();
 *     }
 *   </code>
 * </pre>
 *
 * <p>
 * This pattern is supported by the immutable nature of NetflowBuilder, as instances can be freely passed around without
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
 * @see NetflowBuilder
 * @see Builder
 *
 */
@Generated("mdsal-binding-generator")
public class NetflowBuilder implements Builder<Netflow> {

    private CollectorGoflow2 _collectorGoflow2;
    private ExportPacket _exportPacket;


    Map<Class<? extends Augmentation<Netflow>>, Augmentation<Netflow>> augmentation = Collections.emptyMap();

    public NetflowBuilder() {
    }
    
    

    public NetflowBuilder(Netflow base) {
        Map<Class<? extends Augmentation<Netflow>>, Augmentation<Netflow>> aug = base.augmentations();
        if (!aug.isEmpty()) {
            this.augmentation = new HashMap<>(aug);
        }
        this._collectorGoflow2 = base.getCollectorGoflow2();
        this._exportPacket = base.getExportPacket();
    }


    public CollectorGoflow2 getCollectorGoflow2() {
        return _collectorGoflow2;
    }
    
    public ExportPacket getExportPacket() {
        return _exportPacket;
    }

    @SuppressWarnings({ "unchecked", "checkstyle:methodTypeParameterName"})
    public <E$$ extends Augmentation<Netflow>> E$$ augmentation(Class<E$$> augmentationType) {
        return (E$$) augmentation.get(Objects.requireNonNull(augmentationType));
    }

    
    public NetflowBuilder setCollectorGoflow2(final CollectorGoflow2 value) {
        this._collectorGoflow2 = value;
        return this;
    }
    
    public NetflowBuilder setExportPacket(final ExportPacket value) {
        this._exportPacket = value;
        return this;
    }
    
    /**
      * Add an augmentation to this builder's product.
      *
      * @param augmentation augmentation to be added
      * @return this builder
      * @throws NullPointerException if {@code augmentation} is null
      */
    public NetflowBuilder addAugmentation(Augmentation<Netflow> augmentation) {
        Class<? extends Augmentation<Netflow>> augmentationType = augmentation.implementedInterface();
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
    public NetflowBuilder removeAugmentation(Class<? extends Augmentation<Netflow>> augmentationType) {
        if (this.augmentation instanceof HashMap) {
            this.augmentation.remove(augmentationType);
        }
        return this;
    }

    @Override
    public Netflow build() {
        return new NetflowImpl(this);
    }

    private static final class NetflowImpl
        extends AbstractAugmentable<Netflow>
        implements Netflow {
    
        private final CollectorGoflow2 _collectorGoflow2;
        private final ExportPacket _exportPacket;
    
        NetflowImpl(NetflowBuilder base) {
            super(base.augmentation);
            this._collectorGoflow2 = base.getCollectorGoflow2();
            this._exportPacket = base.getExportPacket();
        }
    
        @Override
        public CollectorGoflow2 getCollectorGoflow2() {
            return _collectorGoflow2;
        }
        
        @Override
        public ExportPacket getExportPacket() {
            return _exportPacket;
        }
    
        private int hash = 0;
        private volatile boolean hashValid = false;
        
        @Override
        public int hashCode() {
            if (hashValid) {
                return hash;
            }
        
            final int result = Netflow.bindingHashCode(this);
            hash = result;
            hashValid = true;
            return result;
        }
    
        @Override
        public boolean equals(Object obj) {
            return Netflow.bindingEquals(this, obj);
        }
    
        @Override
        public String toString() {
            return Netflow.bindingToString(this);
        }
    }
}
