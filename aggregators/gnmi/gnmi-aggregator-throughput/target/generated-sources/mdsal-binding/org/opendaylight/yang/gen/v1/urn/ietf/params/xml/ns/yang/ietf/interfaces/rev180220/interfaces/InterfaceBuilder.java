package org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.interfaces.rev180220.interfaces;
import java.lang.Boolean;
import java.lang.Class;
import java.lang.Deprecated;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.annotation.processing.Generated;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.interfaces.rev180220.InterfaceType;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.interfaces.rev180220.interfaces._interface.Statistics;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715.DateAndTime;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715.Gauge64;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715.PhysAddress;
import org.opendaylight.yangtools.concepts.Builder;
import org.opendaylight.yangtools.yang.binding.AbstractAugmentable;
import org.opendaylight.yangtools.yang.binding.Augmentation;
import org.opendaylight.yangtools.yang.binding.CodeHelpers;

/**
 * Class that builds {@link InterfaceBuilder} instances. Overall design of the class is that of a
 * <a href="https://en.wikipedia.org/wiki/Fluent_interface">fluent interface</a>, where method chaining is used.
 *
 * <p>
 * In general, this class is supposed to be used like this template:
 * <pre>
 *   <code>
 *     InterfaceBuilder createTarget(int fooXyzzy, int barBaz) {
 *         return new InterfaceBuilderBuilder()
 *             .setFoo(new FooBuilder().setXyzzy(fooXyzzy).build())
 *             .setBar(new BarBuilder().setBaz(barBaz).build())
 *             .build();
 *     }
 *   </code>
 * </pre>
 *
 * <p>
 * This pattern is supported by the immutable nature of InterfaceBuilder, as instances can be freely passed around without
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
 * @see InterfaceBuilder
 * @see Builder
 *
 */
@Generated("mdsal-binding-generator")
public class InterfaceBuilder implements Builder<Interface> {

    private Interface.AdminStatus _adminStatus;
    private String _description;
    private Boolean _enabled;
    private List<String> _higherLayerIf;
    private Integer _ifIndex;
    private DateAndTime _lastChange;
    private Interface.LinkUpDownTrapEnable _linkUpDownTrapEnable;
    private List<String> _lowerLayerIf;
    private String _name;
    private Interface.OperStatus _operStatus;
    private PhysAddress _physAddress;
    private Gauge64 _speed;
    private Statistics _statistics;
    private Class<? extends InterfaceType> _type;
    private InterfaceKey key;


    Map<Class<? extends Augmentation<Interface>>, Augmentation<Interface>> augmentation = Collections.emptyMap();

    public InterfaceBuilder() {
    }
    
    
    

    public InterfaceBuilder(Interface base) {
        Map<Class<? extends Augmentation<Interface>>, Augmentation<Interface>> aug = base.augmentations();
        if (!aug.isEmpty()) {
            this.augmentation = new HashMap<>(aug);
        }
        this.key = base.key();
        this._name = base.getName();
        this._adminStatus = base.getAdminStatus();
        this._description = base.getDescription();
        this._enabled = base.getEnabled();
        this._higherLayerIf = base.getHigherLayerIf();
        this._ifIndex = base.getIfIndex();
        this._lastChange = base.getLastChange();
        this._linkUpDownTrapEnable = base.getLinkUpDownTrapEnable();
        this._lowerLayerIf = base.getLowerLayerIf();
        this._operStatus = base.getOperStatus();
        this._physAddress = base.getPhysAddress();
        this._speed = base.getSpeed();
        this._statistics = base.getStatistics();
        this._type = base.getType();
    }


    public InterfaceKey key() {
        return key;
    }
    
    public Interface.AdminStatus getAdminStatus() {
        return _adminStatus;
    }
    
    public String getDescription() {
        return _description;
    }
    
    public Boolean getEnabled() {
        return _enabled;
    }
    
    @Deprecated(forRemoval = true)
    public final Boolean isEnabled() {
        return getEnabled();
    }
    
    public List<String> getHigherLayerIf() {
        return _higherLayerIf;
    }
    
    public Integer getIfIndex() {
        return _ifIndex;
    }
    
    public DateAndTime getLastChange() {
        return _lastChange;
    }
    
    public Interface.LinkUpDownTrapEnable getLinkUpDownTrapEnable() {
        return _linkUpDownTrapEnable;
    }
    
    public List<String> getLowerLayerIf() {
        return _lowerLayerIf;
    }
    
    public String getName() {
        return _name;
    }
    
    public Interface.OperStatus getOperStatus() {
        return _operStatus;
    }
    
    public PhysAddress getPhysAddress() {
        return _physAddress;
    }
    
    public Gauge64 getSpeed() {
        return _speed;
    }
    
    public Statistics getStatistics() {
        return _statistics;
    }
    
    public Class<? extends InterfaceType> getType() {
        return _type;
    }

    @SuppressWarnings({ "unchecked", "checkstyle:methodTypeParameterName"})
    public <E$$ extends Augmentation<Interface>> E$$ augmentation(Class<E$$> augmentationType) {
        return (E$$) augmentation.get(Objects.requireNonNull(augmentationType));
    }

    public InterfaceBuilder withKey(final InterfaceKey key) {
        this.key = key;
        return this;
    }
    
    public InterfaceBuilder setAdminStatus(final Interface.AdminStatus value) {
        this._adminStatus = value;
        return this;
    }
    
    public InterfaceBuilder setDescription(final String value) {
        this._description = value;
        return this;
    }
    
    public InterfaceBuilder setEnabled(final Boolean value) {
        this._enabled = value;
        return this;
    }
    public InterfaceBuilder setHigherLayerIf(final List<String> values) {
        this._higherLayerIf = values;
        return this;
    }
    
    
    private static void checkIfIndexRange(final int value) {
        if (value >= 1) {
            return;
        }
        CodeHelpers.throwInvalidRange("[[1..2147483647]]", value);
    }
    
    public InterfaceBuilder setIfIndex(final Integer value) {
        if (value != null) {
            checkIfIndexRange(value);
            
        }
        this._ifIndex = value;
        return this;
    }
    
    public InterfaceBuilder setLastChange(final DateAndTime value) {
        this._lastChange = value;
        return this;
    }
    
    public InterfaceBuilder setLinkUpDownTrapEnable(final Interface.LinkUpDownTrapEnable value) {
        this._linkUpDownTrapEnable = value;
        return this;
    }
    public InterfaceBuilder setLowerLayerIf(final List<String> values) {
        this._lowerLayerIf = values;
        return this;
    }
    
    
    public InterfaceBuilder setName(final String value) {
        this._name = value;
        return this;
    }
    
    public InterfaceBuilder setOperStatus(final Interface.OperStatus value) {
        this._operStatus = value;
        return this;
    }
    
    public InterfaceBuilder setPhysAddress(final PhysAddress value) {
        this._physAddress = value;
        return this;
    }
    
    public InterfaceBuilder setSpeed(final Gauge64 value) {
        this._speed = value;
        return this;
    }
    
    public InterfaceBuilder setStatistics(final Statistics value) {
        this._statistics = value;
        return this;
    }
    
    public InterfaceBuilder setType(final Class<? extends InterfaceType> value) {
        this._type = value;
        return this;
    }
    
    /**
      * Add an augmentation to this builder's product.
      *
      * @param augmentation augmentation to be added
      * @return this builder
      * @throws NullPointerException if {@code augmentation} is null
      */
    public InterfaceBuilder addAugmentation(Augmentation<Interface> augmentation) {
        Class<? extends Augmentation<Interface>> augmentationType = augmentation.implementedInterface();
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
    public InterfaceBuilder removeAugmentation(Class<? extends Augmentation<Interface>> augmentationType) {
        if (this.augmentation instanceof HashMap) {
            this.augmentation.remove(augmentationType);
        }
        return this;
    }

    @Override
    public Interface build() {
        return new InterfaceImpl(this);
    }

    private static final class InterfaceImpl
        extends AbstractAugmentable<Interface>
        implements Interface {
    
        private final Interface.AdminStatus _adminStatus;
        private final String _description;
        private final Boolean _enabled;
        private final List<String> _higherLayerIf;
        private final Integer _ifIndex;
        private final DateAndTime _lastChange;
        private final Interface.LinkUpDownTrapEnable _linkUpDownTrapEnable;
        private final List<String> _lowerLayerIf;
        private final String _name;
        private final Interface.OperStatus _operStatus;
        private final PhysAddress _physAddress;
        private final Gauge64 _speed;
        private final Statistics _statistics;
        private final Class<? extends InterfaceType> _type;
        private final InterfaceKey key;
    
        InterfaceImpl(InterfaceBuilder base) {
            super(base.augmentation);
            if (base.key() != null) {
                this.key = base.key();
            } else {
                this.key = new InterfaceKey(base.getName());
            }
            this._name = key.getName();
            this._adminStatus = base.getAdminStatus();
            this._description = base.getDescription();
            this._enabled = base.getEnabled();
            this._higherLayerIf = base.getHigherLayerIf();
            this._ifIndex = base.getIfIndex();
            this._lastChange = base.getLastChange();
            this._linkUpDownTrapEnable = base.getLinkUpDownTrapEnable();
            this._lowerLayerIf = base.getLowerLayerIf();
            this._operStatus = base.getOperStatus();
            this._physAddress = base.getPhysAddress();
            this._speed = base.getSpeed();
            this._statistics = base.getStatistics();
            this._type = base.getType();
        }
    
        @Override
        public InterfaceKey key() {
            return key;
        }
        
        @Override
        public Interface.AdminStatus getAdminStatus() {
            return _adminStatus;
        }
        
        @Override
        public String getDescription() {
            return _description;
        }
        
        @Override
        public Boolean getEnabled() {
            return _enabled;
        }
        
        @Override
        public List<String> getHigherLayerIf() {
            return _higherLayerIf;
        }
        
        @Override
        public Integer getIfIndex() {
            return _ifIndex;
        }
        
        @Override
        public DateAndTime getLastChange() {
            return _lastChange;
        }
        
        @Override
        public Interface.LinkUpDownTrapEnable getLinkUpDownTrapEnable() {
            return _linkUpDownTrapEnable;
        }
        
        @Override
        public List<String> getLowerLayerIf() {
            return _lowerLayerIf;
        }
        
        @Override
        public String getName() {
            return _name;
        }
        
        @Override
        public Interface.OperStatus getOperStatus() {
            return _operStatus;
        }
        
        @Override
        public PhysAddress getPhysAddress() {
            return _physAddress;
        }
        
        @Override
        public Gauge64 getSpeed() {
            return _speed;
        }
        
        @Override
        public Statistics getStatistics() {
            return _statistics;
        }
        
        @Override
        public Class<? extends InterfaceType> getType() {
            return _type;
        }
    
        private int hash = 0;
        private volatile boolean hashValid = false;
        
        @Override
        public int hashCode() {
            if (hashValid) {
                return hash;
            }
        
            final int result = Interface.bindingHashCode(this);
            hash = result;
            hashValid = true;
            return result;
        }
    
        @Override
        public boolean equals(Object obj) {
            return Interface.bindingEquals(this, obj);
        }
    
        @Override
        public String toString() {
            return Interface.bindingToString(this);
        }
    }
}
