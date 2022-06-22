package org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.openconfig.interfaces.notification.wrapper.rev220513;
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
import org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.interfaces.top.Interfaces;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715.DateAndTime;
import org.opendaylight.yangtools.concepts.Builder;
import org.opendaylight.yangtools.yang.binding.AbstractAugmentable;
import org.opendaylight.yangtools.yang.binding.Augmentation;
import org.opendaylight.yangtools.yang.binding.CodeHelpers;
import org.opendaylight.yangtools.yang.binding.DataObject;

/**
 * Class that builds {@link NotificationBuilder} instances. Overall design of the class is that of a
 * <a href="https://en.wikipedia.org/wiki/Fluent_interface">fluent interface</a>, where method chaining is used.
 *
 * <p>
 * In general, this class is supposed to be used like this template:
 * <pre>
 *   <code>
 *     NotificationBuilder createTarget(int fooXyzzy, int barBaz) {
 *         return new NotificationBuilderBuilder()
 *             .setFoo(new FooBuilder().setXyzzy(fooXyzzy).build())
 *             .setBar(new BarBuilder().setBaz(barBaz).build())
 *             .build();
 *     }
 *   </code>
 * </pre>
 *
 * <p>
 * This pattern is supported by the immutable nature of NotificationBuilder, as instances can be freely passed around without
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
 * @see NotificationBuilder
 * @see Builder
 *
 */
@Generated("mdsal-binding-generator")
public class NotificationBuilder implements Builder<Notification> {

    private DateAndTime _eventTime;
    private Interfaces _interfaces;


    Map<Class<? extends Augmentation<Notification>>, Augmentation<Notification>> augmentation = Collections.emptyMap();

    public NotificationBuilder() {
    }
    
    
    
    public NotificationBuilder(org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.InterfacesTop arg) {
        this._interfaces = arg.getInterfaces();
    }

    public NotificationBuilder(Notification base) {
        Map<Class<? extends Augmentation<Notification>>, Augmentation<Notification>> aug = base.augmentations();
        if (!aug.isEmpty()) {
            this.augmentation = new HashMap<>(aug);
        }
        this._eventTime = base.getEventTime();
        this._interfaces = base.getInterfaces();
    }

    /**
     * Set fields from given grouping argument. Valid argument is instance of one of following types:
     * <ul>
     * <li>org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.InterfacesTop</li>
     * </ul>
     *
     * @param arg grouping object
     * @throws IllegalArgumentException if given argument is none of valid types or has property with incompatible value
    */
    public void fieldsFrom(DataObject arg) {
        boolean isValidArg = false;
        if (arg instanceof org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.InterfacesTop) {
            this._interfaces = ((org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.InterfacesTop)arg).getInterfaces();
            isValidArg = true;
        }
        CodeHelpers.validValue(isValidArg, arg, "[org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.InterfacesTop]");
    }

    public DateAndTime getEventTime() {
        return _eventTime;
    }
    
    public Interfaces getInterfaces() {
        return _interfaces;
    }

    @SuppressWarnings({ "unchecked", "checkstyle:methodTypeParameterName"})
    public <E$$ extends Augmentation<Notification>> E$$ augmentation(Class<E$$> augmentationType) {
        return (E$$) augmentation.get(Objects.requireNonNull(augmentationType));
    }

    
    public NotificationBuilder setEventTime(final DateAndTime value) {
        this._eventTime = value;
        return this;
    }
    
    public NotificationBuilder setInterfaces(final Interfaces value) {
        this._interfaces = value;
        return this;
    }
    
    /**
      * Add an augmentation to this builder's product.
      *
      * @param augmentation augmentation to be added
      * @return this builder
      * @throws NullPointerException if {@code augmentation} is null
      */
    public NotificationBuilder addAugmentation(Augmentation<Notification> augmentation) {
        Class<? extends Augmentation<Notification>> augmentationType = augmentation.implementedInterface();
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
    public NotificationBuilder removeAugmentation(Class<? extends Augmentation<Notification>> augmentationType) {
        if (this.augmentation instanceof HashMap) {
            this.augmentation.remove(augmentationType);
        }
        return this;
    }

    @Override
    public Notification build() {
        return new NotificationImpl(this);
    }

    private static final class NotificationImpl
        extends AbstractAugmentable<Notification>
        implements Notification {
    
        private final DateAndTime _eventTime;
        private final Interfaces _interfaces;
    
        NotificationImpl(NotificationBuilder base) {
            super(base.augmentation);
            this._eventTime = base.getEventTime();
            this._interfaces = base.getInterfaces();
        }
    
        @Override
        public DateAndTime getEventTime() {
            return _eventTime;
        }
        
        @Override
        public Interfaces getInterfaces() {
            return _interfaces;
        }
    
        private int hash = 0;
        private volatile boolean hashValid = false;
        
        @Override
        public int hashCode() {
            if (hashValid) {
                return hash;
            }
        
            final int result = Notification.bindingHashCode(this);
            hash = result;
            hashValid = true;
            return result;
        }
    
        @Override
        public boolean equals(Object obj) {
            return Notification.bindingEquals(this, obj);
        }
    
        @Override
        public String toString() {
            return Notification.bindingToString(this);
        }
    }
}
