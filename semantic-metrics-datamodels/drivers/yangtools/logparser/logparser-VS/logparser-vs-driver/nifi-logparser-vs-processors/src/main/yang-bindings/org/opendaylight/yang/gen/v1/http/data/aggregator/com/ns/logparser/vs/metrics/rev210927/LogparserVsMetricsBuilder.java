package org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.logparser.vs.metrics.rev210927;
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
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.logparser.vs.metrics.rev210927.metric.set.Metrics;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715.DateAndTime;
import org.opendaylight.yangtools.concepts.Builder;
import org.opendaylight.yangtools.yang.binding.AbstractAugmentable;
import org.opendaylight.yangtools.yang.binding.Augmentation;
import org.opendaylight.yangtools.yang.binding.CodeHelpers;
import org.opendaylight.yangtools.yang.binding.DataObject;

/**
 * Class that builds {@link LogparserVsMetricsBuilder} instances. Overall design of the class is that of a
 * <a href="https://en.wikipedia.org/wiki/Fluent_interface">fluent interface</a>, where method chaining is used.
 *
 * <p>
 * In general, this class is supposed to be used like this template:
 * <pre>
 *   <code>
 *     LogparserVsMetricsBuilder createTarget(int fooXyzzy, int barBaz) {
 *         return new LogparserVsMetricsBuilderBuilder()
 *             .setFoo(new FooBuilder().setXyzzy(fooXyzzy).build())
 *             .setBar(new BarBuilder().setBaz(barBaz).build())
 *             .build();
 *     }
 *   </code>
 * </pre>
 *
 * <p>
 * This pattern is supported by the immutable nature of LogparserVsMetricsBuilder, as instances can be freely passed around without
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
 * @see LogparserVsMetricsBuilder
 * @see Builder
 *
 */
@Generated("mdsal-binding-generator")
public class LogparserVsMetricsBuilder implements Builder<LogparserVsMetrics> {

    private DateAndTime _currentTime;
    private String _instanceName;
    private Metrics _metrics;
    private LogparserVsMetrics.Operation _operation;
    private String _vSBID;
    private String _vSIID;
    private String _vSSIID;


    Map<Class<? extends Augmentation<LogparserVsMetrics>>, Augmentation<LogparserVsMetrics>> augmentation = Collections.emptyMap();

    public LogparserVsMetricsBuilder() {
    }
    
    
    
    public LogparserVsMetricsBuilder(org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.logparser.vs.metrics.rev210927.MetricSet arg) {
        this._metrics = arg.getMetrics();
    }

    public LogparserVsMetricsBuilder(LogparserVsMetrics base) {
        Map<Class<? extends Augmentation<LogparserVsMetrics>>, Augmentation<LogparserVsMetrics>> aug = base.augmentations();
        if (!aug.isEmpty()) {
            this.augmentation = new HashMap<>(aug);
        }
        this._currentTime = base.getCurrentTime();
        this._instanceName = base.getInstanceName();
        this._metrics = base.getMetrics();
        this._operation = base.getOperation();
        this._vSBID = base.getVSBID();
        this._vSIID = base.getVSIID();
        this._vSSIID = base.getVSSIID();
    }

    /**
     * Set fields from given grouping argument. Valid argument is instance of one of following types:
     * <ul>
     * <li>org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.logparser.vs.metrics.rev210927.MetricSet</li>
     * </ul>
     *
     * @param arg grouping object
     * @throws IllegalArgumentException if given argument is none of valid types or has property with incompatible value
    */
    public void fieldsFrom(DataObject arg) {
        boolean isValidArg = false;
        if (arg instanceof org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.logparser.vs.metrics.rev210927.MetricSet) {
            this._metrics = ((org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.logparser.vs.metrics.rev210927.MetricSet)arg).getMetrics();
            isValidArg = true;
        }
        CodeHelpers.validValue(isValidArg, arg, "[org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.logparser.vs.metrics.rev210927.MetricSet]");
    }

    public DateAndTime getCurrentTime() {
        return _currentTime;
    }
    
    public String getInstanceName() {
        return _instanceName;
    }
    
    public Metrics getMetrics() {
        return _metrics;
    }
    
    public LogparserVsMetrics.Operation getOperation() {
        return _operation;
    }
    
    public String getVSBID() {
        return _vSBID;
    }
    
    public String getVSIID() {
        return _vSIID;
    }
    
    public String getVSSIID() {
        return _vSSIID;
    }

    @SuppressWarnings({ "unchecked", "checkstyle:methodTypeParameterName"})
    public <E$$ extends Augmentation<LogparserVsMetrics>> E$$ augmentation(Class<E$$> augmentationType) {
        return (E$$) augmentation.get(Objects.requireNonNull(augmentationType));
    }

    
    public LogparserVsMetricsBuilder setCurrentTime(final DateAndTime value) {
        this._currentTime = value;
        return this;
    }
    
    public LogparserVsMetricsBuilder setInstanceName(final String value) {
        this._instanceName = value;
        return this;
    }
    
    public LogparserVsMetricsBuilder setMetrics(final Metrics value) {
        this._metrics = value;
        return this;
    }
    
    public LogparserVsMetricsBuilder setOperation(final LogparserVsMetrics.Operation value) {
        this._operation = value;
        return this;
    }
    
    public LogparserVsMetricsBuilder setVSBID(final String value) {
        this._vSBID = value;
        return this;
    }
    
    public LogparserVsMetricsBuilder setVSIID(final String value) {
        this._vSIID = value;
        return this;
    }
    
    public LogparserVsMetricsBuilder setVSSIID(final String value) {
        this._vSSIID = value;
        return this;
    }
    
    /**
      * Add an augmentation to this builder's product.
      *
      * @param augmentation augmentation to be added
      * @return this builder
      * @throws NullPointerException if {@code augmentation} is null
      */
    public LogparserVsMetricsBuilder addAugmentation(Augmentation<LogparserVsMetrics> augmentation) {
        Class<? extends Augmentation<LogparserVsMetrics>> augmentationType = augmentation.implementedInterface();
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
    public LogparserVsMetricsBuilder removeAugmentation(Class<? extends Augmentation<LogparserVsMetrics>> augmentationType) {
        if (this.augmentation instanceof HashMap) {
            this.augmentation.remove(augmentationType);
        }
        return this;
    }

    @Override
    public LogparserVsMetrics build() {
        return new LogparserVsMetricsImpl(this);
    }

    private static final class LogparserVsMetricsImpl
        extends AbstractAugmentable<LogparserVsMetrics>
        implements LogparserVsMetrics {
    
        private final DateAndTime _currentTime;
        private final String _instanceName;
        private final Metrics _metrics;
        private final LogparserVsMetrics.Operation _operation;
        private final String _vSBID;
        private final String _vSIID;
        private final String _vSSIID;
    
        LogparserVsMetricsImpl(LogparserVsMetricsBuilder base) {
            super(base.augmentation);
            this._currentTime = base.getCurrentTime();
            this._instanceName = base.getInstanceName();
            this._metrics = base.getMetrics();
            this._operation = base.getOperation();
            this._vSBID = base.getVSBID();
            this._vSIID = base.getVSIID();
            this._vSSIID = base.getVSSIID();
        }
    
        @Override
        public DateAndTime getCurrentTime() {
            return _currentTime;
        }
        
        @Override
        public String getInstanceName() {
            return _instanceName;
        }
        
        @Override
        public Metrics getMetrics() {
            return _metrics;
        }
        
        @Override
        public LogparserVsMetrics.Operation getOperation() {
            return _operation;
        }
        
        @Override
        public String getVSBID() {
            return _vSBID;
        }
        
        @Override
        public String getVSIID() {
            return _vSIID;
        }
        
        @Override
        public String getVSSIID() {
            return _vSSIID;
        }
    
        private int hash = 0;
        private volatile boolean hashValid = false;
        
        @Override
        public int hashCode() {
            if (hashValid) {
                return hash;
            }
        
            final int result = LogparserVsMetrics.bindingHashCode(this);
            hash = result;
            hashValid = true;
            return result;
        }
    
        @Override
        public boolean equals(Object obj) {
            return LogparserVsMetrics.bindingEquals(this, obj);
        }
    
        @Override
        public String toString() {
            return LogparserVsMetrics.bindingToString(this);
        }
    }
}
