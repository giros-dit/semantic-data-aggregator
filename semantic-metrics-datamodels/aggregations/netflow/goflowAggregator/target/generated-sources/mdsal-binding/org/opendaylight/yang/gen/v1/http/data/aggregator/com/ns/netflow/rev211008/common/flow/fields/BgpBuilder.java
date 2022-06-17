package org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.common.flow.fields;
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
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev130715.AsNumber;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev130715.Ipv4Address;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev130715.Ipv6Address;
import org.opendaylight.yangtools.concepts.Builder;
import org.opendaylight.yangtools.yang.binding.AbstractAugmentable;
import org.opendaylight.yangtools.yang.binding.Augmentation;
import org.opendaylight.yangtools.yang.binding.CodeHelpers;
import org.opendaylight.yangtools.yang.common.Uint32;

/**
 * Class that builds {@link BgpBuilder} instances. Overall design of the class is that of a
 * <a href="https://en.wikipedia.org/wiki/Fluent_interface">fluent interface</a>, where method chaining is used.
 *
 * <p>
 * In general, this class is supposed to be used like this template:
 * <pre>
 *   <code>
 *     BgpBuilder createTarget(int fooXyzzy, int barBaz) {
 *         return new BgpBuilderBuilder()
 *             .setFoo(new FooBuilder().setXyzzy(fooXyzzy).build())
 *             .setBar(new BarBuilder().setBaz(barBaz).build())
 *             .build();
 *     }
 *   </code>
 * </pre>
 *
 * <p>
 * This pattern is supported by the immutable nature of BgpBuilder, as instances can be freely passed around without
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
 * @see BgpBuilder
 * @see Builder
 *
 */
@Generated("mdsal-binding-generator")
public class BgpBuilder implements Builder<Bgp> {

    private AsNumber _dstAs;
    private Uint32 _dstTrafficId;
    private Ipv4Address _nextHop;
    private Ipv6Address _nextHopIpv6;
    private AsNumber _srcAs;
    private Uint32 _srcTrafficId;


    Map<Class<? extends Augmentation<Bgp>>, Augmentation<Bgp>> augmentation = Collections.emptyMap();

    public BgpBuilder() {
    }
    
    

    public BgpBuilder(Bgp base) {
        Map<Class<? extends Augmentation<Bgp>>, Augmentation<Bgp>> aug = base.augmentations();
        if (!aug.isEmpty()) {
            this.augmentation = new HashMap<>(aug);
        }
        this._dstAs = base.getDstAs();
        this._dstTrafficId = base.getDstTrafficId();
        this._nextHop = base.getNextHop();
        this._nextHopIpv6 = base.getNextHopIpv6();
        this._srcAs = base.getSrcAs();
        this._srcTrafficId = base.getSrcTrafficId();
    }


    public AsNumber getDstAs() {
        return _dstAs;
    }
    
    public Uint32 getDstTrafficId() {
        return _dstTrafficId;
    }
    
    public Ipv4Address getNextHop() {
        return _nextHop;
    }
    
    public Ipv6Address getNextHopIpv6() {
        return _nextHopIpv6;
    }
    
    public AsNumber getSrcAs() {
        return _srcAs;
    }
    
    public Uint32 getSrcTrafficId() {
        return _srcTrafficId;
    }

    @SuppressWarnings({ "unchecked", "checkstyle:methodTypeParameterName"})
    public <E$$ extends Augmentation<Bgp>> E$$ augmentation(Class<E$$> augmentationType) {
        return (E$$) augmentation.get(Objects.requireNonNull(augmentationType));
    }

    
    public BgpBuilder setDstAs(final AsNumber value) {
        this._dstAs = value;
        return this;
    }
    
    public BgpBuilder setDstTrafficId(final Uint32 value) {
        this._dstTrafficId = value;
        return this;
    }
    
    /**
     * Utility migration setter.
     *
     * @param value field value in legacy type
     * @return this builder
     * @deprecated Use {@link #setDstTrafficId(Uint32)} instead.
     */
    @Deprecated(forRemoval = true)
    public BgpBuilder setDstTrafficId(final Long value) {
        return setDstTrafficId(CodeHelpers.compatUint(value));
    }
    
    public BgpBuilder setNextHop(final Ipv4Address value) {
        this._nextHop = value;
        return this;
    }
    
    public BgpBuilder setNextHopIpv6(final Ipv6Address value) {
        this._nextHopIpv6 = value;
        return this;
    }
    
    public BgpBuilder setSrcAs(final AsNumber value) {
        this._srcAs = value;
        return this;
    }
    
    public BgpBuilder setSrcTrafficId(final Uint32 value) {
        this._srcTrafficId = value;
        return this;
    }
    
    /**
     * Utility migration setter.
     *
     * @param value field value in legacy type
     * @return this builder
     * @deprecated Use {@link #setSrcTrafficId(Uint32)} instead.
     */
    @Deprecated(forRemoval = true)
    public BgpBuilder setSrcTrafficId(final Long value) {
        return setSrcTrafficId(CodeHelpers.compatUint(value));
    }
    
    /**
      * Add an augmentation to this builder's product.
      *
      * @param augmentation augmentation to be added
      * @return this builder
      * @throws NullPointerException if {@code augmentation} is null
      */
    public BgpBuilder addAugmentation(Augmentation<Bgp> augmentation) {
        Class<? extends Augmentation<Bgp>> augmentationType = augmentation.implementedInterface();
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
    public BgpBuilder removeAugmentation(Class<? extends Augmentation<Bgp>> augmentationType) {
        if (this.augmentation instanceof HashMap) {
            this.augmentation.remove(augmentationType);
        }
        return this;
    }

    @Override
    public Bgp build() {
        return new BgpImpl(this);
    }

    private static final class BgpImpl
        extends AbstractAugmentable<Bgp>
        implements Bgp {
    
        private final AsNumber _dstAs;
        private final Uint32 _dstTrafficId;
        private final Ipv4Address _nextHop;
        private final Ipv6Address _nextHopIpv6;
        private final AsNumber _srcAs;
        private final Uint32 _srcTrafficId;
    
        BgpImpl(BgpBuilder base) {
            super(base.augmentation);
            this._dstAs = base.getDstAs();
            this._dstTrafficId = base.getDstTrafficId();
            this._nextHop = base.getNextHop();
            this._nextHopIpv6 = base.getNextHopIpv6();
            this._srcAs = base.getSrcAs();
            this._srcTrafficId = base.getSrcTrafficId();
        }
    
        @Override
        public AsNumber getDstAs() {
            return _dstAs;
        }
        
        @Override
        public Uint32 getDstTrafficId() {
            return _dstTrafficId;
        }
        
        @Override
        public Ipv4Address getNextHop() {
            return _nextHop;
        }
        
        @Override
        public Ipv6Address getNextHopIpv6() {
            return _nextHopIpv6;
        }
        
        @Override
        public AsNumber getSrcAs() {
            return _srcAs;
        }
        
        @Override
        public Uint32 getSrcTrafficId() {
            return _srcTrafficId;
        }
    
        private int hash = 0;
        private volatile boolean hashValid = false;
        
        @Override
        public int hashCode() {
            if (hashValid) {
                return hash;
            }
        
            final int result = Bgp.bindingHashCode(this);
            hash = result;
            hashValid = true;
            return result;
        }
    
        @Override
        public boolean equals(Object obj) {
            return Bgp.bindingEquals(this, obj);
        }
    
        @Override
        public String toString() {
            return Bgp.bindingToString(this);
        }
    }
}
