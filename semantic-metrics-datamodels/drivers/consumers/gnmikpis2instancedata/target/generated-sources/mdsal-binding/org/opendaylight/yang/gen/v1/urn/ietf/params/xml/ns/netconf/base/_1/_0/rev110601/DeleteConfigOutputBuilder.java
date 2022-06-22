package org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.netconf.base._1._0.rev110601;
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
 * Class that builds {@link DeleteConfigOutputBuilder} instances. Overall design of the class is that of a
 * <a href="https://en.wikipedia.org/wiki/Fluent_interface">fluent interface</a>, where method chaining is used.
 *
 * <p>
 * In general, this class is supposed to be used like this template:
 * <pre>
 *   <code>
 *     DeleteConfigOutputBuilder createTarget(int fooXyzzy, int barBaz) {
 *         return new DeleteConfigOutputBuilderBuilder()
 *             .setFoo(new FooBuilder().setXyzzy(fooXyzzy).build())
 *             .setBar(new BarBuilder().setBaz(barBaz).build())
 *             .build();
 *     }
 *   </code>
 * </pre>
 *
 * <p>
 * This pattern is supported by the immutable nature of DeleteConfigOutputBuilder, as instances can be freely passed around without
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
 * @see DeleteConfigOutputBuilder
 * @see Builder
 *
 */
@Generated("mdsal-binding-generator")
public class DeleteConfigOutputBuilder implements Builder<DeleteConfigOutput> {



    Map<Class<? extends Augmentation<DeleteConfigOutput>>, Augmentation<DeleteConfigOutput>> augmentation = Collections.emptyMap();

    public DeleteConfigOutputBuilder() {
    }
    
    

    public DeleteConfigOutputBuilder(DeleteConfigOutput base) {
        Map<Class<? extends Augmentation<DeleteConfigOutput>>, Augmentation<DeleteConfigOutput>> aug = base.augmentations();
        if (!aug.isEmpty()) {
            this.augmentation = new HashMap<>(aug);
        }
    }



    @SuppressWarnings({ "unchecked", "checkstyle:methodTypeParameterName"})
    public <E$$ extends Augmentation<DeleteConfigOutput>> E$$ augmentation(Class<E$$> augmentationType) {
        return (E$$) augmentation.get(Objects.requireNonNull(augmentationType));
    }

    
    /**
      * Add an augmentation to this builder's product.
      *
      * @param augmentation augmentation to be added
      * @return this builder
      * @throws NullPointerException if {@code augmentation} is null
      */
    public DeleteConfigOutputBuilder addAugmentation(Augmentation<DeleteConfigOutput> augmentation) {
        Class<? extends Augmentation<DeleteConfigOutput>> augmentationType = augmentation.implementedInterface();
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
    public DeleteConfigOutputBuilder removeAugmentation(Class<? extends Augmentation<DeleteConfigOutput>> augmentationType) {
        if (this.augmentation instanceof HashMap) {
            this.augmentation.remove(augmentationType);
        }
        return this;
    }

    @Override
    public DeleteConfigOutput build() {
        return new DeleteConfigOutputImpl(this);
    }

    private static final class DeleteConfigOutputImpl
        extends AbstractAugmentable<DeleteConfigOutput>
        implements DeleteConfigOutput {
    
    
        DeleteConfigOutputImpl(DeleteConfigOutputBuilder base) {
            super(base.augmentation);
        }
    
    
        private int hash = 0;
        private volatile boolean hashValid = false;
        
        @Override
        public int hashCode() {
            if (hashValid) {
                return hash;
            }
        
            final int result = DeleteConfigOutput.bindingHashCode(this);
            hash = result;
            hashValid = true;
            return result;
        }
    
        @Override
        public boolean equals(Object obj) {
            return DeleteConfigOutput.bindingEquals(this, obj);
        }
    
        @Override
        public String toString() {
            return DeleteConfigOutput.bindingToString(this);
        }
    }
}
