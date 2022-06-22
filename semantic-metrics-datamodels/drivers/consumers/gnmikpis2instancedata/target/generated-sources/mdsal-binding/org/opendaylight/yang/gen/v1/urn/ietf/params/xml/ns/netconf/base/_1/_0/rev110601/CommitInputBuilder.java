package org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.netconf.base._1._0.rev110601;
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
import org.opendaylight.yangtools.concepts.Builder;
import org.opendaylight.yangtools.yang.binding.AbstractAugmentable;
import org.opendaylight.yangtools.yang.binding.Augmentation;
import org.opendaylight.yangtools.yang.binding.CodeHelpers;
import org.opendaylight.yangtools.yang.common.Empty;
import org.opendaylight.yangtools.yang.common.Uint32;

/**
 * Class that builds {@link CommitInputBuilder} instances. Overall design of the class is that of a
 * <a href="https://en.wikipedia.org/wiki/Fluent_interface">fluent interface</a>, where method chaining is used.
 *
 * <p>
 * In general, this class is supposed to be used like this template:
 * <pre>
 *   <code>
 *     CommitInputBuilder createTarget(int fooXyzzy, int barBaz) {
 *         return new CommitInputBuilderBuilder()
 *             .setFoo(new FooBuilder().setXyzzy(fooXyzzy).build())
 *             .setBar(new BarBuilder().setBaz(barBaz).build())
 *             .build();
 *     }
 *   </code>
 * </pre>
 *
 * <p>
 * This pattern is supported by the immutable nature of CommitInputBuilder, as instances can be freely passed around without
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
 * @see CommitInputBuilder
 * @see Builder
 *
 */
@Generated("mdsal-binding-generator")
public class CommitInputBuilder implements Builder<CommitInput> {

    private Uint32 _confirmTimeout;
    private Empty _confirmed;
    private String _persist;
    private String _persistId;


    Map<Class<? extends Augmentation<CommitInput>>, Augmentation<CommitInput>> augmentation = Collections.emptyMap();

    public CommitInputBuilder() {
    }
    
    

    public CommitInputBuilder(CommitInput base) {
        Map<Class<? extends Augmentation<CommitInput>>, Augmentation<CommitInput>> aug = base.augmentations();
        if (!aug.isEmpty()) {
            this.augmentation = new HashMap<>(aug);
        }
        this._confirmTimeout = base.getConfirmTimeout();
        this._confirmed = base.getConfirmed();
        this._persist = base.getPersist();
        this._persistId = base.getPersistId();
    }


    public Uint32 getConfirmTimeout() {
        return _confirmTimeout;
    }
    
    public Empty getConfirmed() {
        return _confirmed;
    }
    
    public String getPersist() {
        return _persist;
    }
    
    public String getPersistId() {
        return _persistId;
    }

    @SuppressWarnings({ "unchecked", "checkstyle:methodTypeParameterName"})
    public <E$$ extends Augmentation<CommitInput>> E$$ augmentation(Class<E$$> augmentationType) {
        return (E$$) augmentation.get(Objects.requireNonNull(augmentationType));
    }

    
    private static void checkConfirmTimeoutRange(final long value) {
        if (value >= 1L) {
            return;
        }
        CodeHelpers.throwInvalidRange("[[1..4294967295]]", value);
    }
    
    public CommitInputBuilder setConfirmTimeout(final Uint32 value) {
        if (value != null) {
            checkConfirmTimeoutRange(value.longValue());
            
        }
        this._confirmTimeout = value;
        return this;
    }
    
    /**
     * Utility migration setter.
     *
     * @param value field value in legacy type
     * @return this builder
     * @deprecated Use {@link #setConfirmTimeout(Uint32)} instead.
     */
    @Deprecated(forRemoval = true)
    public CommitInputBuilder setConfirmTimeout(final Long value) {
        return setConfirmTimeout(CodeHelpers.compatUint(value));
    }
    
    public CommitInputBuilder setConfirmed(final Empty value) {
        this._confirmed = value;
        return this;
    }
    
    public CommitInputBuilder setPersist(final String value) {
        this._persist = value;
        return this;
    }
    
    public CommitInputBuilder setPersistId(final String value) {
        this._persistId = value;
        return this;
    }
    
    /**
      * Add an augmentation to this builder's product.
      *
      * @param augmentation augmentation to be added
      * @return this builder
      * @throws NullPointerException if {@code augmentation} is null
      */
    public CommitInputBuilder addAugmentation(Augmentation<CommitInput> augmentation) {
        Class<? extends Augmentation<CommitInput>> augmentationType = augmentation.implementedInterface();
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
    public CommitInputBuilder removeAugmentation(Class<? extends Augmentation<CommitInput>> augmentationType) {
        if (this.augmentation instanceof HashMap) {
            this.augmentation.remove(augmentationType);
        }
        return this;
    }

    @Override
    public CommitInput build() {
        return new CommitInputImpl(this);
    }

    private static final class CommitInputImpl
        extends AbstractAugmentable<CommitInput>
        implements CommitInput {
    
        private final Uint32 _confirmTimeout;
        private final Empty _confirmed;
        private final String _persist;
        private final String _persistId;
    
        CommitInputImpl(CommitInputBuilder base) {
            super(base.augmentation);
            this._confirmTimeout = base.getConfirmTimeout();
            this._confirmed = base.getConfirmed();
            this._persist = base.getPersist();
            this._persistId = base.getPersistId();
        }
    
        @Override
        public Uint32 getConfirmTimeout() {
            return _confirmTimeout;
        }
        
        @Override
        public Empty getConfirmed() {
            return _confirmed;
        }
        
        @Override
        public String getPersist() {
            return _persist;
        }
        
        @Override
        public String getPersistId() {
            return _persistId;
        }
    
        private int hash = 0;
        private volatile boolean hashValid = false;
        
        @Override
        public int hashCode() {
            if (hashValid) {
                return hash;
            }
        
            final int result = CommitInput.bindingHashCode(this);
            hash = result;
            hashValid = true;
            return result;
        }
    
        @Override
        public boolean equals(Object obj) {
            return CommitInput.bindingEquals(this, obj);
        }
    
        @Override
        public String toString() {
            return CommitInput.bindingToString(this);
        }
    }
}
