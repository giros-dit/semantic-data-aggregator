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
import org.opendaylight.yangtools.concepts.Builder;
import org.opendaylight.yangtools.yang.binding.AbstractAugmentable;
import org.opendaylight.yangtools.yang.binding.Augmentation;

/**
 * Class that builds {@link ApplicationBuilder} instances. Overall design of the class is that of a
 * <a href="https://en.wikipedia.org/wiki/Fluent_interface">fluent interface</a>, where method chaining is used.
 * 
 * <p>
 * In general, this class is supposed to be used like this template:
 * <pre>
 *   <code>
 *     ApplicationBuilder createTarget(int fooXyzzy, int barBaz) {
 *         return new ApplicationBuilderBuilder()
 *             .setFoo(new FooBuilder().setXyzzy(fooXyzzy).build())
 *             .setBar(new BarBuilder().setBaz(barBaz).build())
 *             .build();
 *     }
 *   </code>
 * </pre>
 * 
 * <p>
 * This pattern is supported by the immutable nature of ApplicationBuilder, as instances can be freely passed around without
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
 * @see ApplicationBuilder
 * @see Builder
 *
 */
@Generated("mdsal-binding-generator")
public class ApplicationBuilder implements Builder<Application> {

    private String _desc;
    private String _name;
    private String _tag;


    Map<Class<? extends Augmentation<Application>>, Augmentation<Application>> augmentation = Collections.emptyMap();

    public ApplicationBuilder() {
    }
    
    

    public ApplicationBuilder(Application base) {
        Map<Class<? extends Augmentation<Application>>, Augmentation<Application>> aug = base.augmentations();
        if (!aug.isEmpty()) {
            this.augmentation = new HashMap<>(aug);
        }
        this._desc = base.getDesc();
        this._name = base.getName();
        this._tag = base.getTag();
    }


    public String getDesc() {
        return _desc;
    }
    
    public String getName() {
        return _name;
    }
    
    public String getTag() {
        return _tag;
    }

    @SuppressWarnings({ "unchecked", "checkstyle:methodTypeParameterName"})
    public <E$$ extends Augmentation<Application>> E$$ augmentation(Class<E$$> augmentationType) {
        return (E$$) augmentation.get(Objects.requireNonNull(augmentationType));
    }

    
    public ApplicationBuilder setDesc(final String value) {
        this._desc = value;
        return this;
    }
    
    public ApplicationBuilder setName(final String value) {
        this._name = value;
        return this;
    }
    
    public ApplicationBuilder setTag(final String value) {
        this._tag = value;
        return this;
    }
    
    /**
      * Add an augmentation to this builder's product.
      *
      * @param augmentation augmentation to be added
      * @return this builder
      * @throws NullPointerException if {@code augmentation} is null
      */
    public ApplicationBuilder addAugmentation(Augmentation<Application> augmentation) {
        Class<? extends Augmentation<Application>> augmentationType = augmentation.implementedInterface();
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
    public ApplicationBuilder removeAugmentation(Class<? extends Augmentation<Application>> augmentationType) {
        if (this.augmentation instanceof HashMap) {
            this.augmentation.remove(augmentationType);
        }
        return this;
    }

    @Override
    public Application build() {
        return new ApplicationImpl(this);
    }

    private static final class ApplicationImpl
        extends AbstractAugmentable<Application>
        implements Application {
    
        private final String _desc;
        private final String _name;
        private final String _tag;
    
        ApplicationImpl(ApplicationBuilder base) {
            super(base.augmentation);
            this._desc = base.getDesc();
            this._name = base.getName();
            this._tag = base.getTag();
        }
    
        @Override
        public String getDesc() {
            return _desc;
        }
        
        @Override
        public String getName() {
            return _name;
        }
        
        @Override
        public String getTag() {
            return _tag;
        }
    
        private int hash = 0;
        private volatile boolean hashValid = false;
        
        @Override
        public int hashCode() {
            if (hashValid) {
                return hash;
            }
        
            final int result = Application.bindingHashCode(this);
            hash = result;
            hashValid = true;
            return result;
        }
    
        @Override
        public boolean equals(Object obj) {
            return Application.bindingEquals(this, obj);
        }
    
        @Override
        public String toString() {
            return Application.bindingToString(this);
        }
    }
}
