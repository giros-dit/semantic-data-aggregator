package org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.netflow;
import java.lang.Class;
import java.lang.Integer;
import java.lang.Long;
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
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.netflow.export.packet.FlowDataRecord;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715.Counter32;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715.Timestamp;
import org.opendaylight.yangtools.concepts.Builder;
import org.opendaylight.yangtools.yang.binding.AbstractAugmentable;
import org.opendaylight.yangtools.yang.binding.Augmentation;
import org.opendaylight.yangtools.yang.binding.CodeHelpers;
import org.opendaylight.yangtools.yang.common.Uint16;
import org.opendaylight.yangtools.yang.common.Uint32;

/**
 * Class that builds {@link ExportPacketBuilder} instances. Overall design of the class is that of a
 * <a href="https://en.wikipedia.org/wiki/Fluent_interface">fluent interface</a>, where method chaining is used.
 * 
 * <p>
 * In general, this class is supposed to be used like this template:
 * <pre>
 *   <code>
 *     ExportPacketBuilder createTarget(int fooXyzzy, int barBaz) {
 *         return new ExportPacketBuilderBuilder()
 *             .setFoo(new FooBuilder().setXyzzy(fooXyzzy).build())
 *             .setBar(new BarBuilder().setBaz(barBaz).build())
 *             .build();
 *     }
 *   </code>
 * </pre>
 * 
 * <p>
 * This pattern is supported by the immutable nature of ExportPacketBuilder, as instances can be freely passed around without
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
 * @see ExportPacketBuilder
 * @see Builder
 *
 */
@Generated("mdsal-binding-generator")
public class ExportPacketBuilder implements Builder<ExportPacket> {

    private Uint16 _count;
    private List<FlowDataRecord> _flowDataRecord;
    private Counter32 _sequenceNumber;
    private Uint32 _sourceId;
    private Timestamp _systemUptime;
    private Timestamp _unixSeconds;


    Map<Class<? extends Augmentation<ExportPacket>>, Augmentation<ExportPacket>> augmentation = Collections.emptyMap();

    public ExportPacketBuilder() {
    }
    
    

    public ExportPacketBuilder(ExportPacket base) {
        Map<Class<? extends Augmentation<ExportPacket>>, Augmentation<ExportPacket>> aug = base.augmentations();
        if (!aug.isEmpty()) {
            this.augmentation = new HashMap<>(aug);
        }
        this._count = base.getCount();
        this._flowDataRecord = base.getFlowDataRecord();
        this._sequenceNumber = base.getSequenceNumber();
        this._sourceId = base.getSourceId();
        this._systemUptime = base.getSystemUptime();
        this._unixSeconds = base.getUnixSeconds();
    }


    public Uint16 getCount() {
        return _count;
    }
    
    public List<FlowDataRecord> getFlowDataRecord() {
        return _flowDataRecord;
    }
    
    public Counter32 getSequenceNumber() {
        return _sequenceNumber;
    }
    
    public Uint32 getSourceId() {
        return _sourceId;
    }
    
    public Timestamp getSystemUptime() {
        return _systemUptime;
    }
    
    public Timestamp getUnixSeconds() {
        return _unixSeconds;
    }

    @SuppressWarnings({ "unchecked", "checkstyle:methodTypeParameterName"})
    public <E$$ extends Augmentation<ExportPacket>> E$$ augmentation(Class<E$$> augmentationType) {
        return (E$$) augmentation.get(Objects.requireNonNull(augmentationType));
    }

    
    public ExportPacketBuilder setCount(final Uint16 value) {
        this._count = value;
        return this;
    }
    
    /**
     * Utility migration setter.
     *
     * @param value field value in legacy type
     * @return this builder
     * @deprecated Use {@link #setCount(Uint16)} instead.
     */
    @Deprecated(forRemoval = true)
    public ExportPacketBuilder setCount(final Integer value) {
        return setCount(CodeHelpers.compatUint(value));
    }
    public ExportPacketBuilder setFlowDataRecord(final List<FlowDataRecord> values) {
        this._flowDataRecord = values;
        return this;
    }
    
    
    public ExportPacketBuilder setSequenceNumber(final Counter32 value) {
        this._sequenceNumber = value;
        return this;
    }
    
    public ExportPacketBuilder setSourceId(final Uint32 value) {
        this._sourceId = value;
        return this;
    }
    
    /**
     * Utility migration setter.
     *
     * @param value field value in legacy type
     * @return this builder
     * @deprecated Use {@link #setSourceId(Uint32)} instead.
     */
    @Deprecated(forRemoval = true)
    public ExportPacketBuilder setSourceId(final Long value) {
        return setSourceId(CodeHelpers.compatUint(value));
    }
    
    public ExportPacketBuilder setSystemUptime(final Timestamp value) {
        this._systemUptime = value;
        return this;
    }
    
    public ExportPacketBuilder setUnixSeconds(final Timestamp value) {
        this._unixSeconds = value;
        return this;
    }
    
    /**
      * Add an augmentation to this builder's product.
      *
      * @param augmentation augmentation to be added
      * @return this builder
      * @throws NullPointerException if {@code augmentation} is null
      */
    public ExportPacketBuilder addAugmentation(Augmentation<ExportPacket> augmentation) {
        Class<? extends Augmentation<ExportPacket>> augmentationType = augmentation.implementedInterface();
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
    public ExportPacketBuilder removeAugmentation(Class<? extends Augmentation<ExportPacket>> augmentationType) {
        if (this.augmentation instanceof HashMap) {
            this.augmentation.remove(augmentationType);
        }
        return this;
    }

    @Override
    public ExportPacket build() {
        return new ExportPacketImpl(this);
    }

    private static final class ExportPacketImpl
        extends AbstractAugmentable<ExportPacket>
        implements ExportPacket {
    
        private final Uint16 _count;
        private final List<FlowDataRecord> _flowDataRecord;
        private final Counter32 _sequenceNumber;
        private final Uint32 _sourceId;
        private final Timestamp _systemUptime;
        private final Timestamp _unixSeconds;
    
        ExportPacketImpl(ExportPacketBuilder base) {
            super(base.augmentation);
            this._count = base.getCount();
            this._flowDataRecord = CodeHelpers.emptyToNull(base.getFlowDataRecord());
            this._sequenceNumber = base.getSequenceNumber();
            this._sourceId = base.getSourceId();
            this._systemUptime = base.getSystemUptime();
            this._unixSeconds = base.getUnixSeconds();
        }
    
        @Override
        public Uint16 getCount() {
            return _count;
        }
        
        @Override
        public List<FlowDataRecord> getFlowDataRecord() {
            return _flowDataRecord;
        }
        
        @Override
        public Counter32 getSequenceNumber() {
            return _sequenceNumber;
        }
        
        @Override
        public Uint32 getSourceId() {
            return _sourceId;
        }
        
        @Override
        public Timestamp getSystemUptime() {
            return _systemUptime;
        }
        
        @Override
        public Timestamp getUnixSeconds() {
            return _unixSeconds;
        }
    
        private int hash = 0;
        private volatile boolean hashValid = false;
        
        @Override
        public int hashCode() {
            if (hashValid) {
                return hash;
            }
        
            final int result = ExportPacket.bindingHashCode(this);
            hash = result;
            hashValid = true;
            return result;
        }
    
        @Override
        public boolean equals(Object obj) {
            return ExportPacket.bindingEquals(this, obj);
        }
    
        @Override
        public String toString() {
            return ExportPacket.bindingToString(this);
        }
    }
}
