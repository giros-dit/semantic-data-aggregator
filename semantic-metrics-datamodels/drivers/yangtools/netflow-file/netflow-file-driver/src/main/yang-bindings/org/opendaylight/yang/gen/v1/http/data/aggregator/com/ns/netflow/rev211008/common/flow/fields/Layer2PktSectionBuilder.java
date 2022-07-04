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
 * Class that builds {@link Layer2PktSectionBuilder} instances. Overall design of the class is that of a
 * <a href="https://en.wikipedia.org/wiki/Fluent_interface">fluent interface</a>, where method chaining is used.
 * 
 * <p>
 * In general, this class is supposed to be used like this template:
 * <pre>
 *   <code>
 *     Layer2PktSectionBuilder createTarget(int fooXyzzy, int barBaz) {
 *         return new Layer2PktSectionBuilderBuilder()
 *             .setFoo(new FooBuilder().setXyzzy(fooXyzzy).build())
 *             .setBar(new BarBuilder().setBaz(barBaz).build())
 *             .build();
 *     }
 *   </code>
 * </pre>
 * 
 * <p>
 * This pattern is supported by the immutable nature of Layer2PktSectionBuilder, as instances can be freely passed around without
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
 * @see Layer2PktSectionBuilder
 * @see Builder
 *
 */
@Generated("mdsal-binding-generator")
public class Layer2PktSectionBuilder implements Builder<Layer2PktSection> {

    private String _data;
    private Uint16 _offset;
    private Uint16 _size;


    Map<Class<? extends Augmentation<Layer2PktSection>>, Augmentation<Layer2PktSection>> augmentation = Collections.emptyMap();

    public Layer2PktSectionBuilder() {
    }
    
    

    public Layer2PktSectionBuilder(Layer2PktSection base) {
        Map<Class<? extends Augmentation<Layer2PktSection>>, Augmentation<Layer2PktSection>> aug = base.augmentations();
        if (!aug.isEmpty()) {
            this.augmentation = new HashMap<>(aug);
        }
        this._data = base.getData();
        this._offset = base.getOffset();
        this._size = base.getSize();
    }


    public String getData() {
        return _data;
    }
    
    public Uint16 getOffset() {
        return _offset;
    }
    
    public Uint16 getSize() {
        return _size;
    }

    @SuppressWarnings({ "unchecked", "checkstyle:methodTypeParameterName"})
    public <E$$ extends Augmentation<Layer2PktSection>> E$$ augmentation(Class<E$$> augmentationType) {
        return (E$$) augmentation.get(Objects.requireNonNull(augmentationType));
    }

    
    public Layer2PktSectionBuilder setData(final String value) {
        this._data = value;
        return this;
    }
    
    public Layer2PktSectionBuilder setOffset(final Uint16 value) {
        this._offset = value;
        return this;
    }
    
    /**
     * Utility migration setter.
     *
     * @param value field value in legacy type
     * @return this builder
     * @deprecated Use {@link #setOffset(Uint16)} instead.
     */
    @Deprecated(forRemoval = true)
    public Layer2PktSectionBuilder setOffset(final Integer value) {
        return setOffset(CodeHelpers.compatUint(value));
    }
    
    public Layer2PktSectionBuilder setSize(final Uint16 value) {
        this._size = value;
        return this;
    }
    
    /**
     * Utility migration setter.
     *
     * @param value field value in legacy type
     * @return this builder
     * @deprecated Use {@link #setSize(Uint16)} instead.
     */
    @Deprecated(forRemoval = true)
    public Layer2PktSectionBuilder setSize(final Integer value) {
        return setSize(CodeHelpers.compatUint(value));
    }
    
    /**
      * Add an augmentation to this builder's product.
      *
      * @param augmentation augmentation to be added
      * @return this builder
      * @throws NullPointerException if {@code augmentation} is null
      */
    public Layer2PktSectionBuilder addAugmentation(Augmentation<Layer2PktSection> augmentation) {
        Class<? extends Augmentation<Layer2PktSection>> augmentationType = augmentation.implementedInterface();
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
    public Layer2PktSectionBuilder removeAugmentation(Class<? extends Augmentation<Layer2PktSection>> augmentationType) {
        if (this.augmentation instanceof HashMap) {
            this.augmentation.remove(augmentationType);
        }
        return this;
    }

    @Override
    public Layer2PktSection build() {
        return new Layer2PktSectionImpl(this);
    }

    private static final class Layer2PktSectionImpl
        extends AbstractAugmentable<Layer2PktSection>
        implements Layer2PktSection {
    
        private final String _data;
        private final Uint16 _offset;
        private final Uint16 _size;
    
        Layer2PktSectionImpl(Layer2PktSectionBuilder base) {
            super(base.augmentation);
            this._data = base.getData();
            this._offset = base.getOffset();
            this._size = base.getSize();
        }
    
        @Override
        public String getData() {
            return _data;
        }
        
        @Override
        public Uint16 getOffset() {
            return _offset;
        }
        
        @Override
        public Uint16 getSize() {
            return _size;
        }
    
        private int hash = 0;
        private volatile boolean hashValid = false;
        
        @Override
        public int hashCode() {
            if (hashValid) {
                return hash;
            }
        
            final int result = Layer2PktSection.bindingHashCode(this);
            hash = result;
            hashValid = true;
            return result;
        }
    
        @Override
        public boolean equals(Object obj) {
            return Layer2PktSection.bindingEquals(this, obj);
        }
    
        @Override
        public String toString() {
            return Layer2PktSection.bindingToString(this);
        }
    }
}
