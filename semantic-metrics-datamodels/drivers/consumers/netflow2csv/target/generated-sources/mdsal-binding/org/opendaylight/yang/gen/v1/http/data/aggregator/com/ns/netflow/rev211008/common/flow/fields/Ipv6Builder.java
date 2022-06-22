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
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.PrefixLengthIpv6;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev130715.Ipv6Address;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev130715.Ipv6FlowLabel;
import org.opendaylight.yangtools.concepts.Builder;
import org.opendaylight.yangtools.yang.binding.AbstractAugmentable;
import org.opendaylight.yangtools.yang.binding.Augmentation;
import org.opendaylight.yangtools.yang.binding.CodeHelpers;
import org.opendaylight.yangtools.yang.common.Uint32;

/**
 * Class that builds {@link Ipv6Builder} instances. Overall design of the class is that of a
 * <a href="https://en.wikipedia.org/wiki/Fluent_interface">fluent interface</a>, where method chaining is used.
 *
 * <p>
 * In general, this class is supposed to be used like this template:
 * <pre>
 *   <code>
 *     Ipv6Builder createTarget(int fooXyzzy, int barBaz) {
 *         return new Ipv6BuilderBuilder()
 *             .setFoo(new FooBuilder().setXyzzy(fooXyzzy).build())
 *             .setBar(new BarBuilder().setBaz(barBaz).build())
 *             .build();
 *     }
 *   </code>
 * </pre>
 *
 * <p>
 * This pattern is supported by the immutable nature of Ipv6Builder, as instances can be freely passed around without
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
 * @see Ipv6Builder
 * @see Builder
 *
 */
@Generated("mdsal-binding-generator")
public class Ipv6Builder implements Builder<Ipv6> {

    private Ipv6Address _dstAddress;
    private PrefixLengthIpv6 _dstMask;
    private Ipv6FlowLabel _flowLabel;
    private Ipv6Address _nextHop;
    private Uint32 _optHeaders;
    private Ipv6Address _srcAddress;
    private PrefixLengthIpv6 _srcMask;


    Map<Class<? extends Augmentation<Ipv6>>, Augmentation<Ipv6>> augmentation = Collections.emptyMap();

    public Ipv6Builder() {
    }
    
    

    public Ipv6Builder(Ipv6 base) {
        Map<Class<? extends Augmentation<Ipv6>>, Augmentation<Ipv6>> aug = base.augmentations();
        if (!aug.isEmpty()) {
            this.augmentation = new HashMap<>(aug);
        }
        this._dstAddress = base.getDstAddress();
        this._dstMask = base.getDstMask();
        this._flowLabel = base.getFlowLabel();
        this._nextHop = base.getNextHop();
        this._optHeaders = base.getOptHeaders();
        this._srcAddress = base.getSrcAddress();
        this._srcMask = base.getSrcMask();
    }


    public Ipv6Address getDstAddress() {
        return _dstAddress;
    }
    
    public PrefixLengthIpv6 getDstMask() {
        return _dstMask;
    }
    
    public Ipv6FlowLabel getFlowLabel() {
        return _flowLabel;
    }
    
    public Ipv6Address getNextHop() {
        return _nextHop;
    }
    
    public Uint32 getOptHeaders() {
        return _optHeaders;
    }
    
    public Ipv6Address getSrcAddress() {
        return _srcAddress;
    }
    
    public PrefixLengthIpv6 getSrcMask() {
        return _srcMask;
    }

    @SuppressWarnings({ "unchecked", "checkstyle:methodTypeParameterName"})
    public <E$$ extends Augmentation<Ipv6>> E$$ augmentation(Class<E$$> augmentationType) {
        return (E$$) augmentation.get(Objects.requireNonNull(augmentationType));
    }

    
    public Ipv6Builder setDstAddress(final Ipv6Address value) {
        this._dstAddress = value;
        return this;
    }
    
    public Ipv6Builder setDstMask(final PrefixLengthIpv6 value) {
        this._dstMask = value;
        return this;
    }
    
    public Ipv6Builder setFlowLabel(final Ipv6FlowLabel value) {
        this._flowLabel = value;
        return this;
    }
    
    public Ipv6Builder setNextHop(final Ipv6Address value) {
        this._nextHop = value;
        return this;
    }
    
    public Ipv6Builder setOptHeaders(final Uint32 value) {
        this._optHeaders = value;
        return this;
    }
    
    /**
     * Utility migration setter.
     *
     * @param value field value in legacy type
     * @return this builder
     * @deprecated Use {@link #setOptHeaders(Uint32)} instead.
     */
    @Deprecated(forRemoval = true)
    public Ipv6Builder setOptHeaders(final Long value) {
        return setOptHeaders(CodeHelpers.compatUint(value));
    }
    
    public Ipv6Builder setSrcAddress(final Ipv6Address value) {
        this._srcAddress = value;
        return this;
    }
    
    public Ipv6Builder setSrcMask(final PrefixLengthIpv6 value) {
        this._srcMask = value;
        return this;
    }
    
    /**
      * Add an augmentation to this builder's product.
      *
      * @param augmentation augmentation to be added
      * @return this builder
      * @throws NullPointerException if {@code augmentation} is null
      */
    public Ipv6Builder addAugmentation(Augmentation<Ipv6> augmentation) {
        Class<? extends Augmentation<Ipv6>> augmentationType = augmentation.implementedInterface();
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
    public Ipv6Builder removeAugmentation(Class<? extends Augmentation<Ipv6>> augmentationType) {
        if (this.augmentation instanceof HashMap) {
            this.augmentation.remove(augmentationType);
        }
        return this;
    }

    @Override
    public Ipv6 build() {
        return new Ipv6Impl(this);
    }

    private static final class Ipv6Impl
        extends AbstractAugmentable<Ipv6>
        implements Ipv6 {
    
        private final Ipv6Address _dstAddress;
        private final PrefixLengthIpv6 _dstMask;
        private final Ipv6FlowLabel _flowLabel;
        private final Ipv6Address _nextHop;
        private final Uint32 _optHeaders;
        private final Ipv6Address _srcAddress;
        private final PrefixLengthIpv6 _srcMask;
    
        Ipv6Impl(Ipv6Builder base) {
            super(base.augmentation);
            this._dstAddress = base.getDstAddress();
            this._dstMask = base.getDstMask();
            this._flowLabel = base.getFlowLabel();
            this._nextHop = base.getNextHop();
            this._optHeaders = base.getOptHeaders();
            this._srcAddress = base.getSrcAddress();
            this._srcMask = base.getSrcMask();
        }
    
        @Override
        public Ipv6Address getDstAddress() {
            return _dstAddress;
        }
        
        @Override
        public PrefixLengthIpv6 getDstMask() {
            return _dstMask;
        }
        
        @Override
        public Ipv6FlowLabel getFlowLabel() {
            return _flowLabel;
        }
        
        @Override
        public Ipv6Address getNextHop() {
            return _nextHop;
        }
        
        @Override
        public Uint32 getOptHeaders() {
            return _optHeaders;
        }
        
        @Override
        public Ipv6Address getSrcAddress() {
            return _srcAddress;
        }
        
        @Override
        public PrefixLengthIpv6 getSrcMask() {
            return _srcMask;
        }
    
        private int hash = 0;
        private volatile boolean hashValid = false;
        
        @Override
        public int hashCode() {
            if (hashValid) {
                return hash;
            }
        
            final int result = Ipv6.bindingHashCode(this);
            hash = result;
            hashValid = true;
            return result;
        }
    
        @Override
        public boolean equals(Object obj) {
            return Ipv6.bindingEquals(this, obj);
        }
    
        @Override
        public String toString() {
            return Ipv6.bindingToString(this);
        }
    }
}
