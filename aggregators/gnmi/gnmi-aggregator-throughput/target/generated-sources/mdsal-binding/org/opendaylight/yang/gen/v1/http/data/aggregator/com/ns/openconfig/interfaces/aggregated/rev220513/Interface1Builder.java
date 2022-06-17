package org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.openconfig.interfaces.aggregated.rev220513;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import javax.annotation.processing.Generated;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.openconfig.interfaces.aggregated.rev220513.interfaces._interface.PacketLossKpiNotification;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.openconfig.interfaces.aggregated.rev220513.interfaces._interface.ThroughputKpiNotification;
import org.opendaylight.yangtools.concepts.Builder;

/**
 * Class that builds {@link Interface1Builder} instances. Overall design of the class is that of a
 * <a href="https://en.wikipedia.org/wiki/Fluent_interface">fluent interface</a>, where method chaining is used.
 *
 * <p>
 * In general, this class is supposed to be used like this template:
 * <pre>
 *   <code>
 *     Interface1Builder createTarget(int fooXyzzy, int barBaz) {
 *         return new Interface1BuilderBuilder()
 *             .setFoo(new FooBuilder().setXyzzy(fooXyzzy).build())
 *             .setBar(new BarBuilder().setBaz(barBaz).build())
 *             .build();
 *     }
 *   </code>
 * </pre>
 *
 * <p>
 * This pattern is supported by the immutable nature of Interface1Builder, as instances can be freely passed around without
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
 * @see Interface1Builder
 * @see Builder
 *
 */
@Generated("mdsal-binding-generator")
public class Interface1Builder implements Builder<Interface1> {

    private PacketLossKpiNotification _packetLossKpiNotification;
    private ThroughputKpiNotification _throughputKpiNotification;



    public Interface1Builder() {
    }
    

    public Interface1Builder(Interface1 base) {
        this._packetLossKpiNotification = base.getPacketLossKpiNotification();
        this._throughputKpiNotification = base.getThroughputKpiNotification();
    }


    public PacketLossKpiNotification getPacketLossKpiNotification() {
        return _packetLossKpiNotification;
    }
    
    public ThroughputKpiNotification getThroughputKpiNotification() {
        return _throughputKpiNotification;
    }

    
    public Interface1Builder setPacketLossKpiNotification(final PacketLossKpiNotification value) {
        this._packetLossKpiNotification = value;
        return this;
    }
    
    public Interface1Builder setThroughputKpiNotification(final ThroughputKpiNotification value) {
        this._throughputKpiNotification = value;
        return this;
    }
    

    @Override
    public Interface1 build() {
        return new Interface1Impl(this);
    }

    private static final class Interface1Impl
        implements Interface1 {
    
        private final PacketLossKpiNotification _packetLossKpiNotification;
        private final ThroughputKpiNotification _throughputKpiNotification;
    
        Interface1Impl(Interface1Builder base) {
            this._packetLossKpiNotification = base.getPacketLossKpiNotification();
            this._throughputKpiNotification = base.getThroughputKpiNotification();
        }
    
        @Override
        public PacketLossKpiNotification getPacketLossKpiNotification() {
            return _packetLossKpiNotification;
        }
        
        @Override
        public ThroughputKpiNotification getThroughputKpiNotification() {
            return _throughputKpiNotification;
        }
    
        private int hash = 0;
        private volatile boolean hashValid = false;
        
        @Override
        public int hashCode() {
            if (hashValid) {
                return hash;
            }
        
            final int result = Interface1.bindingHashCode(this);
            hash = result;
            hashValid = true;
            return result;
        }
    
        @Override
        public boolean equals(Object obj) {
            return Interface1.bindingEquals(this, obj);
        }
    
        @Override
        public String toString() {
            return Interface1.bindingToString(this);
        }
    }
}
