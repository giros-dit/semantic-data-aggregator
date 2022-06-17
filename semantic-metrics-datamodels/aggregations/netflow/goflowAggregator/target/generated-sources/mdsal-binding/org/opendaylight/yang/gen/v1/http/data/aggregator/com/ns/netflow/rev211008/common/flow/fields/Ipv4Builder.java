package org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.common.flow.fields;
import java.lang.Class;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import javax.annotation.processing.Generated;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.PrefixLengthIpv4;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev130715.Ipv4Address;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev130715.Ipv4Prefix;
import org.opendaylight.yangtools.concepts.Builder;
import org.opendaylight.yangtools.yang.binding.AbstractAugmentable;
import org.opendaylight.yangtools.yang.binding.Augmentation;
import org.opendaylight.yangtools.yang.binding.CodeHelpers;
import org.opendaylight.yangtools.yang.common.Uint16;

/**
 * Class that builds {@link Ipv4Builder} instances. Overall design of the class is that of a
 * <a href="https://en.wikipedia.org/wiki/Fluent_interface">fluent interface</a>, where method chaining is used.
 *
 * <p>
 * In general, this class is supposed to be used like this template:
 * <pre>
 *   <code>
 *     Ipv4Builder createTarget(int fooXyzzy, int barBaz) {
 *         return new Ipv4BuilderBuilder()
 *             .setFoo(new FooBuilder().setXyzzy(fooXyzzy).build())
 *             .setBar(new BarBuilder().setBaz(barBaz).build())
 *             .build();
 *     }
 *   </code>
 * </pre>
 *
 * <p>
 * This pattern is supported by the immutable nature of Ipv4Builder, as instances can be freely passed around without
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
 * @see Ipv4Builder
 * @see Builder
 *
 */
@Generated("mdsal-binding-generator")
public class Ipv4Builder implements Builder<Ipv4> {

    private Ipv4Address _dstAddress;
    private PrefixLengthIpv4 _dstMask;
    private Ipv4Prefix _dstPrefix;
    private Uint16 _identification;
    private Ipv4Address _nextHop;
    private Ipv4Address _srcAddress;
    private PrefixLengthIpv4 _srcMask;
    private Ipv4Prefix _srcPrefix;


    Map<Class<? extends Augmentation<Ipv4>>, Augmentation<Ipv4>> augmentation = Collections.emptyMap();

    public Ipv4Builder() {
    }
    
    

    public Ipv4Builder(Ipv4 base) {
        Map<Class<? extends Augmentation<Ipv4>>, Augmentation<Ipv4>> aug = base.augmentations();
        if (!aug.isEmpty()) {
            this.augmentation = new HashMap<>(aug);
        }
        this._dstAddress = base.getDstAddress();
        this._dstMask = base.getDstMask();
        this._dstPrefix = base.getDstPrefix();
        this._identification = base.getIdentification();
        this._nextHop = base.getNextHop();
        this._srcAddress = base.getSrcAddress();
        this._srcMask = base.getSrcMask();
        this._srcPrefix = base.getSrcPrefix();
    }


    public Ipv4Address getDstAddress() {
        return _dstAddress;
    }
    
    public PrefixLengthIpv4 getDstMask() {
        return _dstMask;
    }
    
    public Ipv4Prefix getDstPrefix() {
        return _dstPrefix;
    }
    
    public Uint16 getIdentification() {
        return _identification;
    }
    
    public Ipv4Address getNextHop() {
        return _nextHop;
    }
    
    public Ipv4Address getSrcAddress() {
        return _srcAddress;
    }
    
    public PrefixLengthIpv4 getSrcMask() {
        return _srcMask;
    }
    
    public Ipv4Prefix getSrcPrefix() {
        return _srcPrefix;
    }

    @SuppressWarnings({ "unchecked", "checkstyle:methodTypeParameterName"})
    public <E$$ extends Augmentation<Ipv4>> E$$ augmentation(Class<E$$> augmentationType) {
        return (E$$) augmentation.get(Objects.requireNonNull(augmentationType));
    }

    
    public Ipv4Builder setDstAddress(final Ipv4Address value) {
        this._dstAddress = value;
        return this;
    }
    
    public Ipv4Builder setDstMask(final PrefixLengthIpv4 value) {
        this._dstMask = value;
        return this;
    }
    
    public Ipv4Builder setDstPrefix(final Ipv4Prefix value) {
        this._dstPrefix = value;
        return this;
    }
    
    public Ipv4Builder setIdentification(final Uint16 value) {
        this._identification = value;
        return this;
    }
    
    /**
     * Utility migration setter.
     *
     * @param value field value in legacy type
     * @return this builder
     * @deprecated Use {@link #setIdentification(Uint16)} instead.
     */
    @Deprecated(forRemoval = true)
    public Ipv4Builder setIdentification(final Integer value) {
        return setIdentification(CodeHelpers.compatUint(value));
    }
    
    public Ipv4Builder setNextHop(final Ipv4Address value) {
        this._nextHop = value;
        return this;
    }
    
    public Ipv4Builder setSrcAddress(final Ipv4Address value) {
        this._srcAddress = value;
        return this;
    }
    
    public Ipv4Builder setSrcMask(final PrefixLengthIpv4 value) {
        this._srcMask = value;
        return this;
    }
    
    public Ipv4Builder setSrcPrefix(final Ipv4Prefix value) {
        this._srcPrefix = value;
        return this;
    }
    
    /**
      * Add an augmentation to this builder's product.
      *
      * @param augmentation augmentation to be added
      * @return this builder
      * @throws NullPointerException if {@code augmentation} is null
      */
    public Ipv4Builder addAugmentation(Augmentation<Ipv4> augmentation) {
        Class<? extends Augmentation<Ipv4>> augmentationType = augmentation.implementedInterface();
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
    public Ipv4Builder removeAugmentation(Class<? extends Augmentation<Ipv4>> augmentationType) {
        if (this.augmentation instanceof HashMap) {
            this.augmentation.remove(augmentationType);
        }
        return this;
    }

    @Override
    public Ipv4 build() {
        return new Ipv4Impl(this);
    }

    private static final class Ipv4Impl
        extends AbstractAugmentable<Ipv4>
        implements Ipv4 {
    
        private final Ipv4Address _dstAddress;
        private final PrefixLengthIpv4 _dstMask;
        private final Ipv4Prefix _dstPrefix;
        private final Uint16 _identification;
        private final Ipv4Address _nextHop;
        private final Ipv4Address _srcAddress;
        private final PrefixLengthIpv4 _srcMask;
        private final Ipv4Prefix _srcPrefix;
    
        Ipv4Impl(Ipv4Builder base) {
            super(base.augmentation);
            this._dstAddress = base.getDstAddress();
            this._dstMask = base.getDstMask();
            this._dstPrefix = base.getDstPrefix();
            this._identification = base.getIdentification();
            this._nextHop = base.getNextHop();
            this._srcAddress = base.getSrcAddress();
            this._srcMask = base.getSrcMask();
            this._srcPrefix = base.getSrcPrefix();
        }
    
        @Override
        public Ipv4Address getDstAddress() {
            return _dstAddress;
        }
        
        @Override
        public PrefixLengthIpv4 getDstMask() {
            return _dstMask;
        }
        
        @Override
        public Ipv4Prefix getDstPrefix() {
            return _dstPrefix;
        }
        
        @Override
        public Uint16 getIdentification() {
            return _identification;
        }
        
        @Override
        public Ipv4Address getNextHop() {
            return _nextHop;
        }
        
        @Override
        public Ipv4Address getSrcAddress() {
            return _srcAddress;
        }
        
        @Override
        public PrefixLengthIpv4 getSrcMask() {
            return _srcMask;
        }
        
        @Override
        public Ipv4Prefix getSrcPrefix() {
            return _srcPrefix;
        }
    
        private int hash = 0;
        private volatile boolean hashValid = false;
        
        @Override
        public int hashCode() {
            if (hashValid) {
                return hash;
            }
        
            final int result = Ipv4.bindingHashCode(this);
            hash = result;
            hashValid = true;
            return result;
        }
    
        @Override
        public boolean equals(Object obj) {
            return Ipv4.bindingEquals(this, obj);
        }
    
        @Override
        public String toString() {
            return Ipv4.bindingToString(this);
        }
    }
}
