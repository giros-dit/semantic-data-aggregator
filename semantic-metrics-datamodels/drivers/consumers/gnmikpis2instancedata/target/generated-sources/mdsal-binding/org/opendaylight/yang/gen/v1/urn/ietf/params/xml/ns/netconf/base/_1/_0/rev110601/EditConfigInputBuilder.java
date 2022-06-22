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
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.netconf.base._1._0.rev110601.edit.config.input.EditContent;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.netconf.base._1._0.rev110601.edit.config.input.Target;
import org.opendaylight.yangtools.concepts.Builder;
import org.opendaylight.yangtools.yang.binding.AbstractAugmentable;
import org.opendaylight.yangtools.yang.binding.Augmentation;

/**
 * Class that builds {@link EditConfigInputBuilder} instances. Overall design of the class is that of a
 * <a href="https://en.wikipedia.org/wiki/Fluent_interface">fluent interface</a>, where method chaining is used.
 *
 * <p>
 * In general, this class is supposed to be used like this template:
 * <pre>
 *   <code>
 *     EditConfigInputBuilder createTarget(int fooXyzzy, int barBaz) {
 *         return new EditConfigInputBuilderBuilder()
 *             .setFoo(new FooBuilder().setXyzzy(fooXyzzy).build())
 *             .setBar(new BarBuilder().setBaz(barBaz).build())
 *             .build();
 *     }
 *   </code>
 * </pre>
 *
 * <p>
 * This pattern is supported by the immutable nature of EditConfigInputBuilder, as instances can be freely passed around without
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
 * @see EditConfigInputBuilder
 * @see Builder
 *
 */
@Generated("mdsal-binding-generator")
public class EditConfigInputBuilder implements Builder<EditConfigInput> {

    private EditConfigInput.DefaultOperation _defaultOperation;
    private EditContent _editContent;
    private EditConfigInput.ErrorOption _errorOption;
    private Target _target;
    private EditConfigInput.TestOption _testOption;


    Map<Class<? extends Augmentation<EditConfigInput>>, Augmentation<EditConfigInput>> augmentation = Collections.emptyMap();

    public EditConfigInputBuilder() {
    }
    
    

    public EditConfigInputBuilder(EditConfigInput base) {
        Map<Class<? extends Augmentation<EditConfigInput>>, Augmentation<EditConfigInput>> aug = base.augmentations();
        if (!aug.isEmpty()) {
            this.augmentation = new HashMap<>(aug);
        }
        this._defaultOperation = base.getDefaultOperation();
        this._editContent = base.getEditContent();
        this._errorOption = base.getErrorOption();
        this._target = base.getTarget();
        this._testOption = base.getTestOption();
    }


    public EditConfigInput.DefaultOperation getDefaultOperation() {
        return _defaultOperation;
    }
    
    public EditContent getEditContent() {
        return _editContent;
    }
    
    public EditConfigInput.ErrorOption getErrorOption() {
        return _errorOption;
    }
    
    public Target getTarget() {
        return _target;
    }
    
    public EditConfigInput.TestOption getTestOption() {
        return _testOption;
    }

    @SuppressWarnings({ "unchecked", "checkstyle:methodTypeParameterName"})
    public <E$$ extends Augmentation<EditConfigInput>> E$$ augmentation(Class<E$$> augmentationType) {
        return (E$$) augmentation.get(Objects.requireNonNull(augmentationType));
    }

    
    public EditConfigInputBuilder setDefaultOperation(final EditConfigInput.DefaultOperation value) {
        this._defaultOperation = value;
        return this;
    }
    
    public EditConfigInputBuilder setEditContent(final EditContent value) {
        this._editContent = value;
        return this;
    }
    
    public EditConfigInputBuilder setErrorOption(final EditConfigInput.ErrorOption value) {
        this._errorOption = value;
        return this;
    }
    
    public EditConfigInputBuilder setTarget(final Target value) {
        this._target = value;
        return this;
    }
    
    public EditConfigInputBuilder setTestOption(final EditConfigInput.TestOption value) {
        this._testOption = value;
        return this;
    }
    
    /**
      * Add an augmentation to this builder's product.
      *
      * @param augmentation augmentation to be added
      * @return this builder
      * @throws NullPointerException if {@code augmentation} is null
      */
    public EditConfigInputBuilder addAugmentation(Augmentation<EditConfigInput> augmentation) {
        Class<? extends Augmentation<EditConfigInput>> augmentationType = augmentation.implementedInterface();
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
    public EditConfigInputBuilder removeAugmentation(Class<? extends Augmentation<EditConfigInput>> augmentationType) {
        if (this.augmentation instanceof HashMap) {
            this.augmentation.remove(augmentationType);
        }
        return this;
    }

    @Override
    public EditConfigInput build() {
        return new EditConfigInputImpl(this);
    }

    private static final class EditConfigInputImpl
        extends AbstractAugmentable<EditConfigInput>
        implements EditConfigInput {
    
        private final EditConfigInput.DefaultOperation _defaultOperation;
        private final EditContent _editContent;
        private final EditConfigInput.ErrorOption _errorOption;
        private final Target _target;
        private final EditConfigInput.TestOption _testOption;
    
        EditConfigInputImpl(EditConfigInputBuilder base) {
            super(base.augmentation);
            this._defaultOperation = base.getDefaultOperation();
            this._editContent = base.getEditContent();
            this._errorOption = base.getErrorOption();
            this._target = base.getTarget();
            this._testOption = base.getTestOption();
        }
    
        @Override
        public EditConfigInput.DefaultOperation getDefaultOperation() {
            return _defaultOperation;
        }
        
        @Override
        public EditContent getEditContent() {
            return _editContent;
        }
        
        @Override
        public EditConfigInput.ErrorOption getErrorOption() {
            return _errorOption;
        }
        
        @Override
        public Target getTarget() {
            return _target;
        }
        
        @Override
        public EditConfigInput.TestOption getTestOption() {
            return _testOption;
        }
    
        private int hash = 0;
        private volatile boolean hashValid = false;
        
        @Override
        public int hashCode() {
            if (hashValid) {
                return hash;
            }
        
            final int result = EditConfigInput.bindingHashCode(this);
            hash = result;
            hashValid = true;
            return result;
        }
    
        @Override
        public boolean equals(Object obj) {
            return EditConfigInput.bindingEquals(this, obj);
        }
    
        @Override
        public String toString() {
            return EditConfigInput.bindingToString(this);
        }
    }
}
