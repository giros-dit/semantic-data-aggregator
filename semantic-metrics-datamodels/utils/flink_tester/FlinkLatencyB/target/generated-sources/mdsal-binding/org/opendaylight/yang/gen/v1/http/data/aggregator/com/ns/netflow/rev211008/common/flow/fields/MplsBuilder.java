package org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.common.flow.fields;
import java.lang.Class;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.math.BigInteger;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import javax.annotation.processing.Generated;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.PrefixLengthIpv4;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.netflow.rev211008.TopLabelType;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev130715.Ipv4Address;
import org.opendaylight.yangtools.concepts.Builder;
import org.opendaylight.yangtools.yang.binding.AbstractAugmentable;
import org.opendaylight.yangtools.yang.binding.Augmentation;
import org.opendaylight.yangtools.yang.binding.CodeHelpers;
import org.opendaylight.yangtools.yang.common.Uint32;
import org.opendaylight.yangtools.yang.common.Uint64;

/**
 * Class that builds {@link MplsBuilder} instances. Overall design of the class is that of a
 * <a href="https://en.wikipedia.org/wiki/Fluent_interface">fluent interface</a>, where method chaining is used.
 * 
 * <p>
 * In general, this class is supposed to be used like this template:
 * <pre>
 *   <code>
 *     MplsBuilder createTarget(int fooXyzzy, int barBaz) {
 *         return new MplsBuilderBuilder()
 *             .setFoo(new FooBuilder().setXyzzy(fooXyzzy).build())
 *             .setBar(new BarBuilder().setBaz(barBaz).build())
 *             .build();
 *     }
 *   </code>
 * </pre>
 * 
 * <p>
 * This pattern is supported by the immutable nature of MplsBuilder, as instances can be freely passed around without
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
 * @see MplsBuilder
 * @see Builder
 *
 */
@Generated("mdsal-binding-generator")
public class MplsBuilder implements Builder<Mpls> {

    private Uint32 _label1;
    private Uint32 _label10;
    private Uint32 _label2;
    private Uint32 _label3;
    private Uint32 _label4;
    private Uint32 _label5;
    private Uint32 _label6;
    private Uint32 _label7;
    private Uint32 _label8;
    private Uint32 _label9;
    private Uint64 _palRd;
    private PrefixLengthIpv4 _prefixLen;
    private Ipv4Address _topLabelIp;
    private TopLabelType _topLabelType;


    Map<Class<? extends Augmentation<Mpls>>, Augmentation<Mpls>> augmentation = Collections.emptyMap();

    public MplsBuilder() {
    }
    
    

    public MplsBuilder(Mpls base) {
        Map<Class<? extends Augmentation<Mpls>>, Augmentation<Mpls>> aug = base.augmentations();
        if (!aug.isEmpty()) {
            this.augmentation = new HashMap<>(aug);
        }
        this._label1 = base.getLabel1();
        this._label10 = base.getLabel10();
        this._label2 = base.getLabel2();
        this._label3 = base.getLabel3();
        this._label4 = base.getLabel4();
        this._label5 = base.getLabel5();
        this._label6 = base.getLabel6();
        this._label7 = base.getLabel7();
        this._label8 = base.getLabel8();
        this._label9 = base.getLabel9();
        this._palRd = base.getPalRd();
        this._prefixLen = base.getPrefixLen();
        this._topLabelIp = base.getTopLabelIp();
        this._topLabelType = base.getTopLabelType();
    }


    public Uint32 getLabel1() {
        return _label1;
    }
    
    public Uint32 getLabel10() {
        return _label10;
    }
    
    public Uint32 getLabel2() {
        return _label2;
    }
    
    public Uint32 getLabel3() {
        return _label3;
    }
    
    public Uint32 getLabel4() {
        return _label4;
    }
    
    public Uint32 getLabel5() {
        return _label5;
    }
    
    public Uint32 getLabel6() {
        return _label6;
    }
    
    public Uint32 getLabel7() {
        return _label7;
    }
    
    public Uint32 getLabel8() {
        return _label8;
    }
    
    public Uint32 getLabel9() {
        return _label9;
    }
    
    public Uint64 getPalRd() {
        return _palRd;
    }
    
    public PrefixLengthIpv4 getPrefixLen() {
        return _prefixLen;
    }
    
    public Ipv4Address getTopLabelIp() {
        return _topLabelIp;
    }
    
    public TopLabelType getTopLabelType() {
        return _topLabelType;
    }

    @SuppressWarnings({ "unchecked", "checkstyle:methodTypeParameterName"})
    public <E$$ extends Augmentation<Mpls>> E$$ augmentation(Class<E$$> augmentationType) {
        return (E$$) augmentation.get(Objects.requireNonNull(augmentationType));
    }

    
    private static void checkLabel1Range(final long value) {
        if (value <= 16777215L) {
            return;
        }
        CodeHelpers.throwInvalidRange("[[0..16777215]]", value);
    }
    
    public MplsBuilder setLabel1(final Uint32 value) {
        if (value != null) {
            checkLabel1Range(value.longValue());
            
        }
        this._label1 = value;
        return this;
    }
    
    /**
     * Utility migration setter.
     *
     * @param value field value in legacy type
     * @return this builder
     * @deprecated Use {@link #setLabel1(Uint32)} instead.
     */
    @Deprecated(forRemoval = true)
    public MplsBuilder setLabel1(final Long value) {
        return setLabel1(CodeHelpers.compatUint(value));
    }
    
    private static void checkLabel10Range(final long value) {
        if (value <= 16777215L) {
            return;
        }
        CodeHelpers.throwInvalidRange("[[0..16777215]]", value);
    }
    
    public MplsBuilder setLabel10(final Uint32 value) {
        if (value != null) {
            checkLabel10Range(value.longValue());
            
        }
        this._label10 = value;
        return this;
    }
    
    /**
     * Utility migration setter.
     *
     * @param value field value in legacy type
     * @return this builder
     * @deprecated Use {@link #setLabel10(Uint32)} instead.
     */
    @Deprecated(forRemoval = true)
    public MplsBuilder setLabel10(final Long value) {
        return setLabel10(CodeHelpers.compatUint(value));
    }
    
    private static void checkLabel2Range(final long value) {
        if (value <= 16777215L) {
            return;
        }
        CodeHelpers.throwInvalidRange("[[0..16777215]]", value);
    }
    
    public MplsBuilder setLabel2(final Uint32 value) {
        if (value != null) {
            checkLabel2Range(value.longValue());
            
        }
        this._label2 = value;
        return this;
    }
    
    /**
     * Utility migration setter.
     *
     * @param value field value in legacy type
     * @return this builder
     * @deprecated Use {@link #setLabel2(Uint32)} instead.
     */
    @Deprecated(forRemoval = true)
    public MplsBuilder setLabel2(final Long value) {
        return setLabel2(CodeHelpers.compatUint(value));
    }
    
    private static void checkLabel3Range(final long value) {
        if (value <= 16777215L) {
            return;
        }
        CodeHelpers.throwInvalidRange("[[0..16777215]]", value);
    }
    
    public MplsBuilder setLabel3(final Uint32 value) {
        if (value != null) {
            checkLabel3Range(value.longValue());
            
        }
        this._label3 = value;
        return this;
    }
    
    /**
     * Utility migration setter.
     *
     * @param value field value in legacy type
     * @return this builder
     * @deprecated Use {@link #setLabel3(Uint32)} instead.
     */
    @Deprecated(forRemoval = true)
    public MplsBuilder setLabel3(final Long value) {
        return setLabel3(CodeHelpers.compatUint(value));
    }
    
    private static void checkLabel4Range(final long value) {
        if (value <= 16777215L) {
            return;
        }
        CodeHelpers.throwInvalidRange("[[0..16777215]]", value);
    }
    
    public MplsBuilder setLabel4(final Uint32 value) {
        if (value != null) {
            checkLabel4Range(value.longValue());
            
        }
        this._label4 = value;
        return this;
    }
    
    /**
     * Utility migration setter.
     *
     * @param value field value in legacy type
     * @return this builder
     * @deprecated Use {@link #setLabel4(Uint32)} instead.
     */
    @Deprecated(forRemoval = true)
    public MplsBuilder setLabel4(final Long value) {
        return setLabel4(CodeHelpers.compatUint(value));
    }
    
    private static void checkLabel5Range(final long value) {
        if (value <= 16777215L) {
            return;
        }
        CodeHelpers.throwInvalidRange("[[0..16777215]]", value);
    }
    
    public MplsBuilder setLabel5(final Uint32 value) {
        if (value != null) {
            checkLabel5Range(value.longValue());
            
        }
        this._label5 = value;
        return this;
    }
    
    /**
     * Utility migration setter.
     *
     * @param value field value in legacy type
     * @return this builder
     * @deprecated Use {@link #setLabel5(Uint32)} instead.
     */
    @Deprecated(forRemoval = true)
    public MplsBuilder setLabel5(final Long value) {
        return setLabel5(CodeHelpers.compatUint(value));
    }
    
    private static void checkLabel6Range(final long value) {
        if (value <= 16777215L) {
            return;
        }
        CodeHelpers.throwInvalidRange("[[0..16777215]]", value);
    }
    
    public MplsBuilder setLabel6(final Uint32 value) {
        if (value != null) {
            checkLabel6Range(value.longValue());
            
        }
        this._label6 = value;
        return this;
    }
    
    /**
     * Utility migration setter.
     *
     * @param value field value in legacy type
     * @return this builder
     * @deprecated Use {@link #setLabel6(Uint32)} instead.
     */
    @Deprecated(forRemoval = true)
    public MplsBuilder setLabel6(final Long value) {
        return setLabel6(CodeHelpers.compatUint(value));
    }
    
    private static void checkLabel7Range(final long value) {
        if (value <= 16777215L) {
            return;
        }
        CodeHelpers.throwInvalidRange("[[0..16777215]]", value);
    }
    
    public MplsBuilder setLabel7(final Uint32 value) {
        if (value != null) {
            checkLabel7Range(value.longValue());
            
        }
        this._label7 = value;
        return this;
    }
    
    /**
     * Utility migration setter.
     *
     * @param value field value in legacy type
     * @return this builder
     * @deprecated Use {@link #setLabel7(Uint32)} instead.
     */
    @Deprecated(forRemoval = true)
    public MplsBuilder setLabel7(final Long value) {
        return setLabel7(CodeHelpers.compatUint(value));
    }
    
    private static void checkLabel8Range(final long value) {
        if (value <= 16777215L) {
            return;
        }
        CodeHelpers.throwInvalidRange("[[0..16777215]]", value);
    }
    
    public MplsBuilder setLabel8(final Uint32 value) {
        if (value != null) {
            checkLabel8Range(value.longValue());
            
        }
        this._label8 = value;
        return this;
    }
    
    /**
     * Utility migration setter.
     *
     * @param value field value in legacy type
     * @return this builder
     * @deprecated Use {@link #setLabel8(Uint32)} instead.
     */
    @Deprecated(forRemoval = true)
    public MplsBuilder setLabel8(final Long value) {
        return setLabel8(CodeHelpers.compatUint(value));
    }
    
    private static void checkLabel9Range(final long value) {
        if (value <= 16777215L) {
            return;
        }
        CodeHelpers.throwInvalidRange("[[0..16777215]]", value);
    }
    
    public MplsBuilder setLabel9(final Uint32 value) {
        if (value != null) {
            checkLabel9Range(value.longValue());
            
        }
        this._label9 = value;
        return this;
    }
    
    /**
     * Utility migration setter.
     *
     * @param value field value in legacy type
     * @return this builder
     * @deprecated Use {@link #setLabel9(Uint32)} instead.
     */
    @Deprecated(forRemoval = true)
    public MplsBuilder setLabel9(final Long value) {
        return setLabel9(CodeHelpers.compatUint(value));
    }
    
    public MplsBuilder setPalRd(final Uint64 value) {
        this._palRd = value;
        return this;
    }
    
    /**
     * Utility migration setter.
     *
     * @param value field value in legacy type
     * @return this builder
     * @deprecated Use {@link #setPalRd(Uint64)} instead.
     */
    @Deprecated(forRemoval = true)
    public MplsBuilder setPalRd(final BigInteger value) {
        return setPalRd(CodeHelpers.compatUint(value));
    }
    
    public MplsBuilder setPrefixLen(final PrefixLengthIpv4 value) {
        this._prefixLen = value;
        return this;
    }
    
    public MplsBuilder setTopLabelIp(final Ipv4Address value) {
        this._topLabelIp = value;
        return this;
    }
    
    public MplsBuilder setTopLabelType(final TopLabelType value) {
        this._topLabelType = value;
        return this;
    }
    
    /**
      * Add an augmentation to this builder's product.
      *
      * @param augmentation augmentation to be added
      * @return this builder
      * @throws NullPointerException if {@code augmentation} is null
      */
    public MplsBuilder addAugmentation(Augmentation<Mpls> augmentation) {
        Class<? extends Augmentation<Mpls>> augmentationType = augmentation.implementedInterface();
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
    public MplsBuilder removeAugmentation(Class<? extends Augmentation<Mpls>> augmentationType) {
        if (this.augmentation instanceof HashMap) {
            this.augmentation.remove(augmentationType);
        }
        return this;
    }

    @Override
    public Mpls build() {
        return new MplsImpl(this);
    }

    private static final class MplsImpl
        extends AbstractAugmentable<Mpls>
        implements Mpls {
    
        private final Uint32 _label1;
        private final Uint32 _label10;
        private final Uint32 _label2;
        private final Uint32 _label3;
        private final Uint32 _label4;
        private final Uint32 _label5;
        private final Uint32 _label6;
        private final Uint32 _label7;
        private final Uint32 _label8;
        private final Uint32 _label9;
        private final Uint64 _palRd;
        private final PrefixLengthIpv4 _prefixLen;
        private final Ipv4Address _topLabelIp;
        private final TopLabelType _topLabelType;
    
        MplsImpl(MplsBuilder base) {
            super(base.augmentation);
            this._label1 = base.getLabel1();
            this._label10 = base.getLabel10();
            this._label2 = base.getLabel2();
            this._label3 = base.getLabel3();
            this._label4 = base.getLabel4();
            this._label5 = base.getLabel5();
            this._label6 = base.getLabel6();
            this._label7 = base.getLabel7();
            this._label8 = base.getLabel8();
            this._label9 = base.getLabel9();
            this._palRd = base.getPalRd();
            this._prefixLen = base.getPrefixLen();
            this._topLabelIp = base.getTopLabelIp();
            this._topLabelType = base.getTopLabelType();
        }
    
        @Override
        public Uint32 getLabel1() {
            return _label1;
        }
        
        @Override
        public Uint32 getLabel10() {
            return _label10;
        }
        
        @Override
        public Uint32 getLabel2() {
            return _label2;
        }
        
        @Override
        public Uint32 getLabel3() {
            return _label3;
        }
        
        @Override
        public Uint32 getLabel4() {
            return _label4;
        }
        
        @Override
        public Uint32 getLabel5() {
            return _label5;
        }
        
        @Override
        public Uint32 getLabel6() {
            return _label6;
        }
        
        @Override
        public Uint32 getLabel7() {
            return _label7;
        }
        
        @Override
        public Uint32 getLabel8() {
            return _label8;
        }
        
        @Override
        public Uint32 getLabel9() {
            return _label9;
        }
        
        @Override
        public Uint64 getPalRd() {
            return _palRd;
        }
        
        @Override
        public PrefixLengthIpv4 getPrefixLen() {
            return _prefixLen;
        }
        
        @Override
        public Ipv4Address getTopLabelIp() {
            return _topLabelIp;
        }
        
        @Override
        public TopLabelType getTopLabelType() {
            return _topLabelType;
        }
    
        private int hash = 0;
        private volatile boolean hashValid = false;
        
        @Override
        public int hashCode() {
            if (hashValid) {
                return hash;
            }
        
            final int result = Mpls.bindingHashCode(this);
            hash = result;
            hashValid = true;
            return result;
        }
    
        @Override
        public boolean equals(Object obj) {
            return Mpls.bindingEquals(this, obj);
        }
    
        @Override
        public String toString() {
            return Mpls.bindingToString(this);
        }
    }
}
