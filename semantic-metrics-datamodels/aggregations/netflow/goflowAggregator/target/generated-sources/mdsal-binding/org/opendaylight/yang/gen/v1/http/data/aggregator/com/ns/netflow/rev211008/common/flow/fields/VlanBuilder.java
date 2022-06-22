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
import org.opendaylight.yangtools.concepts.Builder;
import org.opendaylight.yangtools.yang.binding.AbstractAugmentable;
import org.opendaylight.yangtools.yang.binding.Augmentation;
import org.opendaylight.yangtools.yang.binding.CodeHelpers;
import org.opendaylight.yangtools.yang.common.Uint16;

/**
 * Class that builds {@link VlanBuilder} instances. Overall design of the class is that of a
 * <a href="https://en.wikipedia.org/wiki/Fluent_interface">fluent interface</a>, where method chaining is used.
 *
 * <p>
 * In general, this class is supposed to be used like this template:
 * <pre>
 *   <code>
 *     VlanBuilder createTarget(int fooXyzzy, int barBaz) {
 *         return new VlanBuilderBuilder()
 *             .setFoo(new FooBuilder().setXyzzy(fooXyzzy).build())
 *             .setBar(new BarBuilder().setBaz(barBaz).build())
 *             .build();
 *     }
 *   </code>
 * </pre>
 *
 * <p>
 * This pattern is supported by the immutable nature of VlanBuilder, as instances can be freely passed around without
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
 * @see VlanBuilder
 * @see Builder
 *
 */
@Generated("mdsal-binding-generator")
public class VlanBuilder implements Builder<Vlan> {

    private Uint16 _dstId;
    private Uint16 _srcId;


    Map<Class<? extends Augmentation<Vlan>>, Augmentation<Vlan>> augmentation = Collections.emptyMap();

    public VlanBuilder() {
    }
    
    

    public VlanBuilder(Vlan base) {
        Map<Class<? extends Augmentation<Vlan>>, Augmentation<Vlan>> aug = base.augmentations();
        if (!aug.isEmpty()) {
            this.augmentation = new HashMap<>(aug);
        }
        this._dstId = base.getDstId();
        this._srcId = base.getSrcId();
    }


    public Uint16 getDstId() {
        return _dstId;
    }
    
    public Uint16 getSrcId() {
        return _srcId;
    }

    @SuppressWarnings({ "unchecked", "checkstyle:methodTypeParameterName"})
    public <E$$ extends Augmentation<Vlan>> E$$ augmentation(Class<E$$> augmentationType) {
        return (E$$) augmentation.get(Objects.requireNonNull(augmentationType));
    }

    
    private static void checkDstIdRange(final int value) {
        if (value <= 4095) {
            return;
        }
        CodeHelpers.throwInvalidRange("[[0..4095]]", value);
    }
    
    public VlanBuilder setDstId(final Uint16 value) {
        if (value != null) {
            checkDstIdRange(value.intValue());
            
        }
        this._dstId = value;
        return this;
    }
    
    /**
     * Utility migration setter.
     *
     * @param value field value in legacy type
     * @return this builder
     * @deprecated Use {@link #setDstId(Uint16)} instead.
     */
    @Deprecated(forRemoval = true)
    public VlanBuilder setDstId(final Integer value) {
        return setDstId(CodeHelpers.compatUint(value));
    }
    
    private static void checkSrcIdRange(final int value) {
        if (value <= 4095) {
            return;
        }
        CodeHelpers.throwInvalidRange("[[0..4095]]", value);
    }
    
    public VlanBuilder setSrcId(final Uint16 value) {
        if (value != null) {
            checkSrcIdRange(value.intValue());
            
        }
        this._srcId = value;
        return this;
    }
    
    /**
     * Utility migration setter.
     *
     * @param value field value in legacy type
     * @return this builder
     * @deprecated Use {@link #setSrcId(Uint16)} instead.
     */
    @Deprecated(forRemoval = true)
    public VlanBuilder setSrcId(final Integer value) {
        return setSrcId(CodeHelpers.compatUint(value));
    }
    
    /**
      * Add an augmentation to this builder's product.
      *
      * @param augmentation augmentation to be added
      * @return this builder
      * @throws NullPointerException if {@code augmentation} is null
      */
    public VlanBuilder addAugmentation(Augmentation<Vlan> augmentation) {
        Class<? extends Augmentation<Vlan>> augmentationType = augmentation.implementedInterface();
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
    public VlanBuilder removeAugmentation(Class<? extends Augmentation<Vlan>> augmentationType) {
        if (this.augmentation instanceof HashMap) {
            this.augmentation.remove(augmentationType);
        }
        return this;
    }

    @Override
    public Vlan build() {
        return new VlanImpl(this);
    }

    private static final class VlanImpl
        extends AbstractAugmentable<Vlan>
        implements Vlan {
    
        private final Uint16 _dstId;
        private final Uint16 _srcId;
    
        VlanImpl(VlanBuilder base) {
            super(base.augmentation);
            this._dstId = base.getDstId();
            this._srcId = base.getSrcId();
        }
    
        @Override
        public Uint16 getDstId() {
            return _dstId;
        }
        
        @Override
        public Uint16 getSrcId() {
            return _srcId;
        }
    
        private int hash = 0;
        private volatile boolean hashValid = false;
        
        @Override
        public int hashCode() {
            if (hashValid) {
                return hash;
            }
        
            final int result = Vlan.bindingHashCode(this);
            hash = result;
            hashValid = true;
            return result;
        }
    
        @Override
        public boolean equals(Object obj) {
            return Vlan.bindingEquals(this, obj);
        }
    
        @Override
        public String toString() {
            return Vlan.bindingToString(this);
        }
    }
}
