package org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.netflow;
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
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev130715.Ipv4Address;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev130715.Ipv6Address;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715.Timestamp;
import org.opendaylight.yangtools.concepts.Builder;
import org.opendaylight.yangtools.yang.binding.AbstractAugmentable;
import org.opendaylight.yangtools.yang.binding.Augmentation;

/**
 * Class that builds {@link CollectorGoflow2Builder} instances. Overall design of the class is that of a
 * <a href="https://en.wikipedia.org/wiki/Fluent_interface">fluent interface</a>, where method chaining is used.
 * 
 * <p>
 * In general, this class is supposed to be used like this template:
 * <pre>
 *   <code>
 *     CollectorGoflow2Builder createTarget(int fooXyzzy, int barBaz) {
 *         return new CollectorGoflow2BuilderBuilder()
 *             .setFoo(new FooBuilder().setXyzzy(fooXyzzy).build())
 *             .setBar(new BarBuilder().setBaz(barBaz).build())
 *             .build();
 *     }
 *   </code>
 * </pre>
 * 
 * <p>
 * This pattern is supported by the immutable nature of CollectorGoflow2Builder, as instances can be freely passed around without
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
 * @see CollectorGoflow2Builder
 * @see Builder
 *
 */
@Generated("mdsal-binding-generator")
public class CollectorGoflow2Builder implements Builder<CollectorGoflow2> {

    private Ipv4Address _samplerAddress;
    private Ipv6Address _samplerAddressIpv6;
    private Timestamp _timeReceived;


    Map<Class<? extends Augmentation<CollectorGoflow2>>, Augmentation<CollectorGoflow2>> augmentation = Collections.emptyMap();

    public CollectorGoflow2Builder() {
    }
    
    

    public CollectorGoflow2Builder(CollectorGoflow2 base) {
        Map<Class<? extends Augmentation<CollectorGoflow2>>, Augmentation<CollectorGoflow2>> aug = base.augmentations();
        if (!aug.isEmpty()) {
            this.augmentation = new HashMap<>(aug);
        }
        this._samplerAddress = base.getSamplerAddress();
        this._samplerAddressIpv6 = base.getSamplerAddressIpv6();
        this._timeReceived = base.getTimeReceived();
    }


    public Ipv4Address getSamplerAddress() {
        return _samplerAddress;
    }
    
    public Ipv6Address getSamplerAddressIpv6() {
        return _samplerAddressIpv6;
    }
    
    public Timestamp getTimeReceived() {
        return _timeReceived;
    }

    @SuppressWarnings({ "unchecked", "checkstyle:methodTypeParameterName"})
    public <E$$ extends Augmentation<CollectorGoflow2>> E$$ augmentation(Class<E$$> augmentationType) {
        return (E$$) augmentation.get(Objects.requireNonNull(augmentationType));
    }

    
    public CollectorGoflow2Builder setSamplerAddress(final Ipv4Address value) {
        this._samplerAddress = value;
        return this;
    }
    
    public CollectorGoflow2Builder setSamplerAddressIpv6(final Ipv6Address value) {
        this._samplerAddressIpv6 = value;
        return this;
    }
    
    public CollectorGoflow2Builder setTimeReceived(final Timestamp value) {
        this._timeReceived = value;
        return this;
    }
    
    /**
      * Add an augmentation to this builder's product.
      *
      * @param augmentation augmentation to be added
      * @return this builder
      * @throws NullPointerException if {@code augmentation} is null
      */
    public CollectorGoflow2Builder addAugmentation(Augmentation<CollectorGoflow2> augmentation) {
        Class<? extends Augmentation<CollectorGoflow2>> augmentationType = augmentation.implementedInterface();
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
    public CollectorGoflow2Builder removeAugmentation(Class<? extends Augmentation<CollectorGoflow2>> augmentationType) {
        if (this.augmentation instanceof HashMap) {
            this.augmentation.remove(augmentationType);
        }
        return this;
    }

    @Override
    public CollectorGoflow2 build() {
        return new CollectorGoflow2Impl(this);
    }

    private static final class CollectorGoflow2Impl
        extends AbstractAugmentable<CollectorGoflow2>
        implements CollectorGoflow2 {
    
        private final Ipv4Address _samplerAddress;
        private final Ipv6Address _samplerAddressIpv6;
        private final Timestamp _timeReceived;
    
        CollectorGoflow2Impl(CollectorGoflow2Builder base) {
            super(base.augmentation);
            this._samplerAddress = base.getSamplerAddress();
            this._samplerAddressIpv6 = base.getSamplerAddressIpv6();
            this._timeReceived = base.getTimeReceived();
        }
    
        @Override
        public Ipv4Address getSamplerAddress() {
            return _samplerAddress;
        }
        
        @Override
        public Ipv6Address getSamplerAddressIpv6() {
            return _samplerAddressIpv6;
        }
        
        @Override
        public Timestamp getTimeReceived() {
            return _timeReceived;
        }
    
        private int hash = 0;
        private volatile boolean hashValid = false;
        
        @Override
        public int hashCode() {
            if (hashValid) {
                return hash;
            }
        
            final int result = CollectorGoflow2.bindingHashCode(this);
            hash = result;
            hashValid = true;
            return result;
        }
    
        @Override
        public boolean equals(Object obj) {
            return CollectorGoflow2.bindingEquals(this, obj);
        }
    
        @Override
        public String toString() {
            return CollectorGoflow2.bindingToString(this);
        }
    }
}
