package org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.aggregated.rev220302;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import javax.annotation.processing.Generated;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715.Timestamp;
import org.opendaylight.yangtools.concepts.Builder;

/**
 * Class that builds {@link FlowDataRecord1Builder} instances. Overall design of the class is that of a
 * <a href="https://en.wikipedia.org/wiki/Fluent_interface">fluent interface</a>, where method chaining is used.
 * 
 * <p>
 * In general, this class is supposed to be used like this template:
 * <pre>
 *   <code>
 *     FlowDataRecord1Builder createTarget(int fooXyzzy, int barBaz) {
 *         return new FlowDataRecord1BuilderBuilder()
 *             .setFoo(new FooBuilder().setXyzzy(fooXyzzy).build())
 *             .setBar(new BarBuilder().setBaz(barBaz).build())
 *             .build();
 *     }
 *   </code>
 * </pre>
 * 
 * <p>
 * This pattern is supported by the immutable nature of FlowDataRecord1Builder, as instances can be freely passed around without
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
 * @see FlowDataRecord1Builder
 * @see Builder
 *
 */
@Generated("mdsal-binding-generator")
public class FlowDataRecord1Builder implements Builder<FlowDataRecord1> {

    private PerDecimal _bytesInPerPacket;
    private PerDecimal _bytesInPerSecond;
    private PerDecimal _bytesOutPerPacket;
    private PerDecimal _bytesOutPerSecond;
    private Timestamp _flowDuration;
    private PerDecimal _pktsInPerSecond;
    private PerDecimal _pktsOutPerSecond;
    private PerDecimal _ratioBytesInPerOut;
    private PerDecimal _ratioPktsInPerOut;



    public FlowDataRecord1Builder() {
    }
    

    public FlowDataRecord1Builder(FlowDataRecord1 base) {
        this._bytesInPerPacket = base.getBytesInPerPacket();
        this._bytesInPerSecond = base.getBytesInPerSecond();
        this._bytesOutPerPacket = base.getBytesOutPerPacket();
        this._bytesOutPerSecond = base.getBytesOutPerSecond();
        this._flowDuration = base.getFlowDuration();
        this._pktsInPerSecond = base.getPktsInPerSecond();
        this._pktsOutPerSecond = base.getPktsOutPerSecond();
        this._ratioBytesInPerOut = base.getRatioBytesInPerOut();
        this._ratioPktsInPerOut = base.getRatioPktsInPerOut();
    }


    public PerDecimal getBytesInPerPacket() {
        return _bytesInPerPacket;
    }
    
    public PerDecimal getBytesInPerSecond() {
        return _bytesInPerSecond;
    }
    
    public PerDecimal getBytesOutPerPacket() {
        return _bytesOutPerPacket;
    }
    
    public PerDecimal getBytesOutPerSecond() {
        return _bytesOutPerSecond;
    }
    
    public Timestamp getFlowDuration() {
        return _flowDuration;
    }
    
    public PerDecimal getPktsInPerSecond() {
        return _pktsInPerSecond;
    }
    
    public PerDecimal getPktsOutPerSecond() {
        return _pktsOutPerSecond;
    }
    
    public PerDecimal getRatioBytesInPerOut() {
        return _ratioBytesInPerOut;
    }
    
    public PerDecimal getRatioPktsInPerOut() {
        return _ratioPktsInPerOut;
    }

    
    public FlowDataRecord1Builder setBytesInPerPacket(final PerDecimal value) {
        this._bytesInPerPacket = value;
        return this;
    }
    
    public FlowDataRecord1Builder setBytesInPerSecond(final PerDecimal value) {
        this._bytesInPerSecond = value;
        return this;
    }
    
    public FlowDataRecord1Builder setBytesOutPerPacket(final PerDecimal value) {
        this._bytesOutPerPacket = value;
        return this;
    }
    
    public FlowDataRecord1Builder setBytesOutPerSecond(final PerDecimal value) {
        this._bytesOutPerSecond = value;
        return this;
    }
    
    public FlowDataRecord1Builder setFlowDuration(final Timestamp value) {
        this._flowDuration = value;
        return this;
    }
    
    public FlowDataRecord1Builder setPktsInPerSecond(final PerDecimal value) {
        this._pktsInPerSecond = value;
        return this;
    }
    
    public FlowDataRecord1Builder setPktsOutPerSecond(final PerDecimal value) {
        this._pktsOutPerSecond = value;
        return this;
    }
    
    public FlowDataRecord1Builder setRatioBytesInPerOut(final PerDecimal value) {
        this._ratioBytesInPerOut = value;
        return this;
    }
    
    public FlowDataRecord1Builder setRatioPktsInPerOut(final PerDecimal value) {
        this._ratioPktsInPerOut = value;
        return this;
    }
    

    @Override
    public FlowDataRecord1 build() {
        return new FlowDataRecord1Impl(this);
    }

    private static final class FlowDataRecord1Impl
        implements FlowDataRecord1 {
    
        private final PerDecimal _bytesInPerPacket;
        private final PerDecimal _bytesInPerSecond;
        private final PerDecimal _bytesOutPerPacket;
        private final PerDecimal _bytesOutPerSecond;
        private final Timestamp _flowDuration;
        private final PerDecimal _pktsInPerSecond;
        private final PerDecimal _pktsOutPerSecond;
        private final PerDecimal _ratioBytesInPerOut;
        private final PerDecimal _ratioPktsInPerOut;
    
        FlowDataRecord1Impl(FlowDataRecord1Builder base) {
            this._bytesInPerPacket = base.getBytesInPerPacket();
            this._bytesInPerSecond = base.getBytesInPerSecond();
            this._bytesOutPerPacket = base.getBytesOutPerPacket();
            this._bytesOutPerSecond = base.getBytesOutPerSecond();
            this._flowDuration = base.getFlowDuration();
            this._pktsInPerSecond = base.getPktsInPerSecond();
            this._pktsOutPerSecond = base.getPktsOutPerSecond();
            this._ratioBytesInPerOut = base.getRatioBytesInPerOut();
            this._ratioPktsInPerOut = base.getRatioPktsInPerOut();
        }
    
        @Override
        public PerDecimal getBytesInPerPacket() {
            return _bytesInPerPacket;
        }
        
        @Override
        public PerDecimal getBytesInPerSecond() {
            return _bytesInPerSecond;
        }
        
        @Override
        public PerDecimal getBytesOutPerPacket() {
            return _bytesOutPerPacket;
        }
        
        @Override
        public PerDecimal getBytesOutPerSecond() {
            return _bytesOutPerSecond;
        }
        
        @Override
        public Timestamp getFlowDuration() {
            return _flowDuration;
        }
        
        @Override
        public PerDecimal getPktsInPerSecond() {
            return _pktsInPerSecond;
        }
        
        @Override
        public PerDecimal getPktsOutPerSecond() {
            return _pktsOutPerSecond;
        }
        
        @Override
        public PerDecimal getRatioBytesInPerOut() {
            return _ratioBytesInPerOut;
        }
        
        @Override
        public PerDecimal getRatioPktsInPerOut() {
            return _ratioPktsInPerOut;
        }
    
        private int hash = 0;
        private volatile boolean hashValid = false;
        
        @Override
        public int hashCode() {
            if (hashValid) {
                return hash;
            }
        
            final int result = FlowDataRecord1.bindingHashCode(this);
            hash = result;
            hashValid = true;
            return result;
        }
    
        @Override
        public boolean equals(Object obj) {
            return FlowDataRecord1.bindingEquals(this, obj);
        }
    
        @Override
        public String toString() {
            return FlowDataRecord1.bindingToString(this);
        }
    }
}
