package org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.netconf.with.defaults.rev110601;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import javax.annotation.processing.Generated;
import org.opendaylight.yangtools.concepts.Builder;
import org.opendaylight.yangtools.yang.binding.CodeHelpers;
import org.opendaylight.yangtools.yang.binding.DataObject;

/**
 * Class that builds {@link CopyConfigInput1Builder} instances. Overall design of the class is that of a
 * <a href="https://en.wikipedia.org/wiki/Fluent_interface">fluent interface</a>, where method chaining is used.
 *
 * <p>
 * In general, this class is supposed to be used like this template:
 * <pre>
 *   <code>
 *     CopyConfigInput1Builder createTarget(int fooXyzzy, int barBaz) {
 *         return new CopyConfigInput1BuilderBuilder()
 *             .setFoo(new FooBuilder().setXyzzy(fooXyzzy).build())
 *             .setBar(new BarBuilder().setBaz(barBaz).build())
 *             .build();
 *     }
 *   </code>
 * </pre>
 *
 * <p>
 * This pattern is supported by the immutable nature of CopyConfigInput1Builder, as instances can be freely passed around without
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
 * @see CopyConfigInput1Builder
 * @see Builder
 *
 */
@Generated("mdsal-binding-generator")
public class CopyConfigInput1Builder implements Builder<CopyConfigInput1> {

    private WithDefaultsMode _withDefaults;



    public CopyConfigInput1Builder() {
    }
    
    
    public CopyConfigInput1Builder(org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.netconf.with.defaults.rev110601.WithDefaultsParameters arg) {
        this._withDefaults = arg.getWithDefaults();
    }

    public CopyConfigInput1Builder(CopyConfigInput1 base) {
        this._withDefaults = base.getWithDefaults();
    }

    /**
     * Set fields from given grouping argument. Valid argument is instance of one of following types:
     * <ul>
     * <li>org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.netconf.with.defaults.rev110601.WithDefaultsParameters</li>
     * </ul>
     *
     * @param arg grouping object
     * @throws IllegalArgumentException if given argument is none of valid types or has property with incompatible value
    */
    public void fieldsFrom(DataObject arg) {
        boolean isValidArg = false;
        if (arg instanceof org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.netconf.with.defaults.rev110601.WithDefaultsParameters) {
            this._withDefaults = ((org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.netconf.with.defaults.rev110601.WithDefaultsParameters)arg).getWithDefaults();
            isValidArg = true;
        }
        CodeHelpers.validValue(isValidArg, arg, "[org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.netconf.with.defaults.rev110601.WithDefaultsParameters]");
    }

    public WithDefaultsMode getWithDefaults() {
        return _withDefaults;
    }

    
    public CopyConfigInput1Builder setWithDefaults(final WithDefaultsMode value) {
        this._withDefaults = value;
        return this;
    }
    

    @Override
    public CopyConfigInput1 build() {
        return new CopyConfigInput1Impl(this);
    }

    private static final class CopyConfigInput1Impl
        implements CopyConfigInput1 {
    
        private final WithDefaultsMode _withDefaults;
    
        CopyConfigInput1Impl(CopyConfigInput1Builder base) {
            this._withDefaults = base.getWithDefaults();
        }
    
        @Override
        public WithDefaultsMode getWithDefaults() {
            return _withDefaults;
        }
    
        private int hash = 0;
        private volatile boolean hashValid = false;
        
        @Override
        public int hashCode() {
            if (hashValid) {
                return hash;
            }
        
            final int result = CopyConfigInput1.bindingHashCode(this);
            hash = result;
            hashValid = true;
            return result;
        }
    
        @Override
        public boolean equals(Object obj) {
            return CopyConfigInput1.bindingEquals(this, obj);
        }
    
        @Override
        public String toString() {
            return CopyConfigInput1.bindingToString(this);
        }
    }
}
