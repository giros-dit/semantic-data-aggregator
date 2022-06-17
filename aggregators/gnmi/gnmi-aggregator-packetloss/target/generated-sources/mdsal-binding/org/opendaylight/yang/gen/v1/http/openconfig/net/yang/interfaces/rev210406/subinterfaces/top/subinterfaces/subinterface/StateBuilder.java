package org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.subinterfaces.top.subinterfaces.subinterface;
import java.lang.Boolean;
import java.lang.Class;
import java.lang.Deprecated;
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
import org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.InterfaceCommonState.AdminStatus;
import org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.InterfaceCommonState.OperStatus;
import org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406._interface.counters.state.Counters;
import org.opendaylight.yang.gen.v1.http.openconfig.net.yang.openconfig.types.rev190416.Timeticks64;
import org.opendaylight.yangtools.concepts.Builder;
import org.opendaylight.yangtools.yang.binding.AbstractAugmentable;
import org.opendaylight.yangtools.yang.binding.Augmentation;
import org.opendaylight.yangtools.yang.binding.CodeHelpers;
import org.opendaylight.yangtools.yang.binding.DataObject;
import org.opendaylight.yangtools.yang.common.Uint32;

/**
 * Class that builds {@link StateBuilder} instances. Overall design of the class is that of a
 * <a href="https://en.wikipedia.org/wiki/Fluent_interface">fluent interface</a>, where method chaining is used.
 *
 * <p>
 * In general, this class is supposed to be used like this template:
 * <pre>
 *   <code>
 *     StateBuilder createTarget(int fooXyzzy, int barBaz) {
 *         return new StateBuilderBuilder()
 *             .setFoo(new FooBuilder().setXyzzy(fooXyzzy).build())
 *             .setBar(new BarBuilder().setBaz(barBaz).build())
 *             .build();
 *     }
 *   </code>
 * </pre>
 *
 * <p>
 * This pattern is supported by the immutable nature of StateBuilder, as instances can be freely passed around without
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
 * @see StateBuilder
 * @see Builder
 *
 */
@Generated("mdsal-binding-generator")
public class StateBuilder implements Builder<State> {

    private AdminStatus _adminStatus;
    private Counters _counters;
    private Boolean _cpu;
    private String _description;
    private Boolean _enabled;
    private Uint32 _ifindex;
    private Uint32 _index;
    private Timeticks64 _lastChange;
    private Boolean _logical;
    private Boolean _management;
    private String _name;
    private OperStatus _operStatus;


    Map<Class<? extends Augmentation<State>>, Augmentation<State>> augmentation = Collections.emptyMap();

    public StateBuilder() {
    }
    
    
    
    public StateBuilder(org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.SubinterfacesConfig arg) {
        this._index = arg.getIndex();
        this._description = arg.getDescription();
        this._enabled = arg.getEnabled();
    }
    public StateBuilder(org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.InterfaceCommonConfig arg) {
        this._description = arg.getDescription();
        this._enabled = arg.getEnabled();
    }
    
    public StateBuilder(org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.SubinterfacesState arg) {
        this._name = arg.getName();
        this._ifindex = arg.getIfindex();
        this._adminStatus = arg.getAdminStatus();
        this._operStatus = arg.getOperStatus();
        this._lastChange = arg.getLastChange();
        this._logical = arg.getLogical();
        this._management = arg.getManagement();
        this._cpu = arg.getCpu();
        this._counters = arg.getCounters();
    }
    public StateBuilder(org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.InterfaceCommonState arg) {
        this._ifindex = arg.getIfindex();
        this._adminStatus = arg.getAdminStatus();
        this._operStatus = arg.getOperStatus();
        this._lastChange = arg.getLastChange();
        this._logical = arg.getLogical();
        this._management = arg.getManagement();
        this._cpu = arg.getCpu();
    }
    public StateBuilder(org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.InterfaceCountersState arg) {
        this._counters = arg.getCounters();
    }

    public StateBuilder(State base) {
        Map<Class<? extends Augmentation<State>>, Augmentation<State>> aug = base.augmentations();
        if (!aug.isEmpty()) {
            this.augmentation = new HashMap<>(aug);
        }
        this._adminStatus = base.getAdminStatus();
        this._counters = base.getCounters();
        this._cpu = base.getCpu();
        this._description = base.getDescription();
        this._enabled = base.getEnabled();
        this._ifindex = base.getIfindex();
        this._index = base.getIndex();
        this._lastChange = base.getLastChange();
        this._logical = base.getLogical();
        this._management = base.getManagement();
        this._name = base.getName();
        this._operStatus = base.getOperStatus();
    }

    /**
     * Set fields from given grouping argument. Valid argument is instance of one of following types:
     * <ul>
     * <li>org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.SubinterfacesConfig</li>
     * <li>org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.SubinterfacesState</li>
     * <li>org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.InterfaceCommonState</li>
     * <li>org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.InterfaceCommonConfig</li>
     * <li>org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.InterfaceCountersState</li>
     * </ul>
     *
     * @param arg grouping object
     * @throws IllegalArgumentException if given argument is none of valid types or has property with incompatible value
    */
    public void fieldsFrom(DataObject arg) {
        boolean isValidArg = false;
        if (arg instanceof org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.SubinterfacesConfig) {
            this._index = ((org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.SubinterfacesConfig)arg).getIndex();
            isValidArg = true;
        }
        if (arg instanceof org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.SubinterfacesState) {
            this._name = ((org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.SubinterfacesState)arg).getName();
            isValidArg = true;
        }
        if (arg instanceof org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.InterfaceCommonState) {
            this._ifindex = ((org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.InterfaceCommonState)arg).getIfindex();
            this._adminStatus = ((org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.InterfaceCommonState)arg).getAdminStatus();
            this._operStatus = ((org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.InterfaceCommonState)arg).getOperStatus();
            this._lastChange = ((org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.InterfaceCommonState)arg).getLastChange();
            this._logical = ((org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.InterfaceCommonState)arg).getLogical();
            this._management = ((org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.InterfaceCommonState)arg).getManagement();
            this._cpu = ((org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.InterfaceCommonState)arg).getCpu();
            isValidArg = true;
        }
        if (arg instanceof org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.InterfaceCommonConfig) {
            this._description = ((org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.InterfaceCommonConfig)arg).getDescription();
            this._enabled = ((org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.InterfaceCommonConfig)arg).getEnabled();
            isValidArg = true;
        }
        if (arg instanceof org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.InterfaceCountersState) {
            this._counters = ((org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.InterfaceCountersState)arg).getCounters();
            isValidArg = true;
        }
        CodeHelpers.validValue(isValidArg, arg, "[org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.SubinterfacesConfig, org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.SubinterfacesState, org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.InterfaceCommonState, org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.InterfaceCommonConfig, org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.InterfaceCountersState]");
    }

    public AdminStatus getAdminStatus() {
        return _adminStatus;
    }
    
    public Counters getCounters() {
        return _counters;
    }
    
    public Boolean getCpu() {
        return _cpu;
    }
    
    @Deprecated(forRemoval = true)
    public final Boolean isCpu() {
        return getCpu();
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
    
    public Uint32 getIfindex() {
        return _ifindex;
    }
    
    public Uint32 getIndex() {
        return _index;
    }
    
    public Timeticks64 getLastChange() {
        return _lastChange;
    }
    
    public Boolean getLogical() {
        return _logical;
    }
    
    @Deprecated(forRemoval = true)
    public final Boolean isLogical() {
        return getLogical();
    }
    
    public Boolean getManagement() {
        return _management;
    }
    
    @Deprecated(forRemoval = true)
    public final Boolean isManagement() {
        return getManagement();
    }
    
    public String getName() {
        return _name;
    }
    
    public OperStatus getOperStatus() {
        return _operStatus;
    }

    @SuppressWarnings({ "unchecked", "checkstyle:methodTypeParameterName"})
    public <E$$ extends Augmentation<State>> E$$ augmentation(Class<E$$> augmentationType) {
        return (E$$) augmentation.get(Objects.requireNonNull(augmentationType));
    }

    
    public StateBuilder setAdminStatus(final AdminStatus value) {
        this._adminStatus = value;
        return this;
    }
    
    public StateBuilder setCounters(final Counters value) {
        this._counters = value;
        return this;
    }
    
    public StateBuilder setCpu(final Boolean value) {
        this._cpu = value;
        return this;
    }
    
    public StateBuilder setDescription(final String value) {
        this._description = value;
        return this;
    }
    
    public StateBuilder setEnabled(final Boolean value) {
        this._enabled = value;
        return this;
    }
    
    public StateBuilder setIfindex(final Uint32 value) {
        this._ifindex = value;
        return this;
    }
    
    /**
     * Utility migration setter.
     *
     * @param value field value in legacy type
     * @return this builder
     * @deprecated Use {@link #setIfindex(Uint32)} instead.
     */
    @Deprecated(forRemoval = true)
    public StateBuilder setIfindex(final Long value) {
        return setIfindex(CodeHelpers.compatUint(value));
    }
    
    public StateBuilder setIndex(final Uint32 value) {
        this._index = value;
        return this;
    }
    
    /**
     * Utility migration setter.
     *
     * @param value field value in legacy type
     * @return this builder
     * @deprecated Use {@link #setIndex(Uint32)} instead.
     */
    @Deprecated(forRemoval = true)
    public StateBuilder setIndex(final Long value) {
        return setIndex(CodeHelpers.compatUint(value));
    }
    
    public StateBuilder setLastChange(final Timeticks64 value) {
        this._lastChange = value;
        return this;
    }
    
    public StateBuilder setLogical(final Boolean value) {
        this._logical = value;
        return this;
    }
    
    public StateBuilder setManagement(final Boolean value) {
        this._management = value;
        return this;
    }
    
    public StateBuilder setName(final String value) {
        this._name = value;
        return this;
    }
    
    public StateBuilder setOperStatus(final OperStatus value) {
        this._operStatus = value;
        return this;
    }
    
    /**
      * Add an augmentation to this builder's product.
      *
      * @param augmentation augmentation to be added
      * @return this builder
      * @throws NullPointerException if {@code augmentation} is null
      */
    public StateBuilder addAugmentation(Augmentation<State> augmentation) {
        Class<? extends Augmentation<State>> augmentationType = augmentation.implementedInterface();
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
    public StateBuilder removeAugmentation(Class<? extends Augmentation<State>> augmentationType) {
        if (this.augmentation instanceof HashMap) {
            this.augmentation.remove(augmentationType);
        }
        return this;
    }

    @Override
    public State build() {
        return new StateImpl(this);
    }

    private static final class StateImpl
        extends AbstractAugmentable<State>
        implements State {
    
        private final org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.InterfaceCommonState.AdminStatus _adminStatus;
        private final Counters _counters;
        private final Boolean _cpu;
        private final String _description;
        private final Boolean _enabled;
        private final Uint32 _ifindex;
        private final Uint32 _index;
        private final Timeticks64 _lastChange;
        private final Boolean _logical;
        private final Boolean _management;
        private final String _name;
        private final org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.InterfaceCommonState.OperStatus _operStatus;
    
        StateImpl(StateBuilder base) {
            super(base.augmentation);
            this._adminStatus = base.getAdminStatus();
            this._counters = base.getCounters();
            this._cpu = base.getCpu();
            this._description = base.getDescription();
            this._enabled = base.getEnabled();
            this._ifindex = base.getIfindex();
            this._index = base.getIndex();
            this._lastChange = base.getLastChange();
            this._logical = base.getLogical();
            this._management = base.getManagement();
            this._name = base.getName();
            this._operStatus = base.getOperStatus();
        }
    
        @Override
        public org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.InterfaceCommonState.AdminStatus getAdminStatus() {
            return _adminStatus;
        }
        
        @Override
        public Counters getCounters() {
            return _counters;
        }
        
        @Override
        public Boolean getCpu() {
            return _cpu;
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
        public Uint32 getIfindex() {
            return _ifindex;
        }
        
        @Override
        public Uint32 getIndex() {
            return _index;
        }
        
        @Override
        public Timeticks64 getLastChange() {
            return _lastChange;
        }
        
        @Override
        public Boolean getLogical() {
            return _logical;
        }
        
        @Override
        public Boolean getManagement() {
            return _management;
        }
        
        @Override
        public String getName() {
            return _name;
        }
        
        @Override
        public org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406.InterfaceCommonState.OperStatus getOperStatus() {
            return _operStatus;
        }
    
        private int hash = 0;
        private volatile boolean hashValid = false;
        
        @Override
        public int hashCode() {
            if (hashValid) {
                return hash;
            }
        
            final int result = State.bindingHashCode(this);
            hash = result;
            hashValid = true;
            return result;
        }
    
        @Override
        public boolean equals(Object obj) {
            return State.bindingEquals(this, obj);
        }
    
        @Override
        public String toString() {
            return State.bindingToString(this);
        }
    }
}
