package org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.common.flow.fields;
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
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715.Counter64;
import org.opendaylight.yangtools.concepts.Builder;
import org.opendaylight.yangtools.yang.binding.AbstractAugmentable;
import org.opendaylight.yangtools.yang.binding.Augmentation;

/**
 * Class that builds {@link PermanentFlowBuilder} instances. Overall design of the class is that of a
 * <a href="https://en.wikipedia.org/wiki/Fluent_interface">fluent interface</a>, where method chaining is used.
 * 
 * <p>
 * In general, this class is supposed to be used like this template:
 * <pre>
 *   <code>
 *     PermanentFlowBuilder createTarget(int fooXyzzy, int barBaz) {
 *         return new PermanentFlowBuilderBuilder()
 *             .setFoo(new FooBuilder().setXyzzy(fooXyzzy).build())
 *             .setBar(new BarBuilder().setBaz(barBaz).build())
 *             .build();
 *     }
 *   </code>
 * </pre>
 * 
 * <p>
 * This pattern is supported by the immutable nature of PermanentFlowBuilder, as instances can be freely passed around without
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
 * @see PermanentFlowBuilder
 * @see Builder
 *
 */
@Generated("mdsal-binding-generator")
public class PermanentFlowBuilder implements Builder<PermanentFlow> {

    private Counter64 _bytesIn;
    private Counter64 _pktsIn;


    Map<Class<? extends Augmentation<PermanentFlow>>, Augmentation<PermanentFlow>> augmentation = Collections.emptyMap();

    public PermanentFlowBuilder() {
    }
    
    

    public PermanentFlowBuilder(PermanentFlow base) {
        Map<Class<? extends Augmentation<PermanentFlow>>, Augmentation<PermanentFlow>> aug = base.augmentations();
        if (!aug.isEmpty()) {
            this.augmentation = new HashMap<>(aug);
        }
        this._bytesIn = base.getBytesIn();
        this._pktsIn = base.getPktsIn();
    }


    public Counter64 getBytesIn() {
        return _bytesIn;
    }
    
    public Counter64 getPktsIn() {
        return _pktsIn;
    }

    @SuppressWarnings({ "unchecked", "checkstyle:methodTypeParameterName"})
    public <E$$ extends Augmentation<PermanentFlow>> E$$ augmentation(Class<E$$> augmentationType) {
        return (E$$) augmentation.get(Objects.requireNonNull(augmentationType));
    }

    
    public PermanentFlowBuilder setBytesIn(final Counter64 value) {
        this._bytesIn = value;
        return this;
    }
    
    public PermanentFlowBuilder setPktsIn(final Counter64 value) {
        this._pktsIn = value;
        return this;
    }
    
    /**
      * Add an augmentation to this builder's product.
      *
      * @param augmentation augmentation to be added
      * @return this builder
      * @throws NullPointerException if {@code augmentation} is null
      */
    public PermanentFlowBuilder addAugmentation(Augmentation<PermanentFlow> augmentation) {
        Class<? extends Augmentation<PermanentFlow>> augmentationType = augmentation.implementedInterface();
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
    public PermanentFlowBuilder removeAugmentation(Class<? extends Augmentation<PermanentFlow>> augmentationType) {
        if (this.augmentation instanceof HashMap) {
            this.augmentation.remove(augmentationType);
        }
        return this;
    }

    @Override
    public PermanentFlow build() {
        return new PermanentFlowImpl(this);
    }

    private static final class PermanentFlowImpl
        extends AbstractAugmentable<PermanentFlow>
        implements PermanentFlow {
    
        private final Counter64 _bytesIn;
        private final Counter64 _pktsIn;
    
        PermanentFlowImpl(PermanentFlowBuilder base) {
            super(base.augmentation);
            this._bytesIn = base.getBytesIn();
            this._pktsIn = base.getPktsIn();
        }
    
        @Override
        public Counter64 getBytesIn() {
            return _bytesIn;
        }
        
        @Override
        public Counter64 getPktsIn() {
            return _pktsIn;
        }
    
        private int hash = 0;
        private volatile boolean hashValid = false;
        
        @Override
        public int hashCode() {
            if (hashValid) {
                return hash;
            }
        
            final int result = PermanentFlow.bindingHashCode(this);
            hash = result;
            hashValid = true;
            return result;
        }
    
        @Override
        public boolean equals(Object obj) {
            return PermanentFlow.bindingEquals(this, obj);
        }
    
        @Override
        public String toString() {
            return PermanentFlow.bindingToString(this);
        }
    }
}
