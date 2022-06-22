package org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.gnmic.rev210719.tag.set.tags;
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
import org.opendaylight.yangtools.concepts.Builder;
import org.opendaylight.yangtools.yang.binding.AbstractAugmentable;
import org.opendaylight.yangtools.yang.binding.Augmentation;

/**
 * Class that builds {@link TagBuilder} instances. Overall design of the class is that of a
 * <a href="https://en.wikipedia.org/wiki/Fluent_interface">fluent interface</a>, where method chaining is used.
 * 
 * <p>
 * In general, this class is supposed to be used like this template:
 * <pre>
 *   <code>
 *     TagBuilder createTarget(int fooXyzzy, int barBaz) {
 *         return new TagBuilderBuilder()
 *             .setFoo(new FooBuilder().setXyzzy(fooXyzzy).build())
 *             .setBar(new BarBuilder().setBaz(barBaz).build())
 *             .build();
 *     }
 *   </code>
 * </pre>
 * 
 * <p>
 * This pattern is supported by the immutable nature of TagBuilder, as instances can be freely passed around without
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
 * @see TagBuilder
 * @see Builder
 *
 */
@Generated("mdsal-binding-generator")
public class TagBuilder implements Builder<Tag> {

    private String _name;
    private String _value;
    private TagKey key;


    Map<Class<? extends Augmentation<Tag>>, Augmentation<Tag>> augmentation = Collections.emptyMap();

    public TagBuilder() {
    }
    
    
    

    public TagBuilder(Tag base) {
        Map<Class<? extends Augmentation<Tag>>, Augmentation<Tag>> aug = base.augmentations();
        if (!aug.isEmpty()) {
            this.augmentation = new HashMap<>(aug);
        }
        this.key = base.key();
        this._name = base.getName();
        this._value = base.getValue();
    }


    public TagKey key() {
        return key;
    }
    
    public String getName() {
        return _name;
    }
    
    public String getValue() {
        return _value;
    }

    @SuppressWarnings({ "unchecked", "checkstyle:methodTypeParameterName"})
    public <E$$ extends Augmentation<Tag>> E$$ augmentation(Class<E$$> augmentationType) {
        return (E$$) augmentation.get(Objects.requireNonNull(augmentationType));
    }

    public TagBuilder withKey(final TagKey key) {
        this.key = key;
        return this;
    }
    
    public TagBuilder setName(final String value) {
        this._name = value;
        return this;
    }
    
    public TagBuilder setValue(final String value) {
        this._value = value;
        return this;
    }
    
    /**
      * Add an augmentation to this builder's product.
      *
      * @param augmentation augmentation to be added
      * @return this builder
      * @throws NullPointerException if {@code augmentation} is null
      */
    public TagBuilder addAugmentation(Augmentation<Tag> augmentation) {
        Class<? extends Augmentation<Tag>> augmentationType = augmentation.implementedInterface();
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
    public TagBuilder removeAugmentation(Class<? extends Augmentation<Tag>> augmentationType) {
        if (this.augmentation instanceof HashMap) {
            this.augmentation.remove(augmentationType);
        }
        return this;
    }

    @Override
    public Tag build() {
        return new TagImpl(this);
    }

    private static final class TagImpl
        extends AbstractAugmentable<Tag>
        implements Tag {
    
        private final String _name;
        private final String _value;
        private final TagKey key;
    
        TagImpl(TagBuilder base) {
            super(base.augmentation);
            if (base.key() != null) {
                this.key = base.key();
            } else {
                this.key = new TagKey(base.getName());
            }
            this._name = key.getName();
            this._value = base.getValue();
        }
    
        @Override
        public TagKey key() {
            return key;
        }
        
        @Override
        public String getName() {
            return _name;
        }
        
        @Override
        public String getValue() {
            return _value;
        }
    
        private int hash = 0;
        private volatile boolean hashValid = false;
        
        @Override
        public int hashCode() {
            if (hashValid) {
                return hash;
            }
        
            final int result = Tag.bindingHashCode(this);
            hash = result;
            hashValid = true;
            return result;
        }
    
        @Override
        public boolean equals(Object obj) {
            return Tag.bindingEquals(this, obj);
        }
    
        @Override
        public String toString() {
            return Tag.bindingToString(this);
        }
    }
}
