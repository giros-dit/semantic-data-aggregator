package org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.logparser.so.metrics.rev210927;
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
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.logparser.so.metrics.rev210927.metric.set.Metrics;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715.DateAndTime;
import org.opendaylight.yangtools.concepts.Builder;
import org.opendaylight.yangtools.yang.binding.AbstractAugmentable;
import org.opendaylight.yangtools.yang.binding.Augmentation;
import org.opendaylight.yangtools.yang.binding.CodeHelpers;
import org.opendaylight.yangtools.yang.binding.DataObject;

/**
 * Class that builds {@link LogparserSoMetricsBuilder} instances. Overall design of the class is that of a
 * <a href="https://en.wikipedia.org/wiki/Fluent_interface">fluent interface</a>, where method chaining is used.
 *
 * <p>
 * In general, this class is supposed to be used like this template:
 * <pre>
 *   <code>
 *     LogparserSoMetricsBuilder createTarget(int fooXyzzy, int barBaz) {
 *         return new LogparserSoMetricsBuilderBuilder()
 *             .setFoo(new FooBuilder().setXyzzy(fooXyzzy).build())
 *             .setBar(new BarBuilder().setBaz(barBaz).build())
 *             .build();
 *     }
 *   </code>
 * </pre>
 *
 * <p>
 * This pattern is supported by the immutable nature of LogparserSoMetricsBuilder, as instances can be freely passed around without
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
 * @see LogparserSoMetricsBuilder
 * @see Builder
 *
 */
@Generated("mdsal-binding-generator")
public class LogparserSoMetricsBuilder implements Builder<LogparserSoMetrics> {

    private DateAndTime _currentTime;
    private Metrics _metrics;
    private String _nSDID;
    private String _nSID;
    private LogparserSoMetrics.Operation _operation;


    Map<Class<? extends Augmentation<LogparserSoMetrics>>, Augmentation<LogparserSoMetrics>> augmentation = Collections.emptyMap();

    public LogparserSoMetricsBuilder() {
    }
    
    
    
    public LogparserSoMetricsBuilder(org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.logparser.so.metrics.rev210927.MetricSet arg) {
        this._metrics = arg.getMetrics();
    }

    public LogparserSoMetricsBuilder(LogparserSoMetrics base) {
        Map<Class<? extends Augmentation<LogparserSoMetrics>>, Augmentation<LogparserSoMetrics>> aug = base.augmentations();
        if (!aug.isEmpty()) {
            this.augmentation = new HashMap<>(aug);
        }
        this._currentTime = base.getCurrentTime();
        this._metrics = base.getMetrics();
        this._nSDID = base.getNSDID();
        this._nSID = base.getNSID();
        this._operation = base.getOperation();
    }

    /**
     * Set fields from given grouping argument. Valid argument is instance of one of following types:
     * <ul>
     * <li>org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.logparser.so.metrics.rev210927.MetricSet</li>
     * </ul>
     *
     * @param arg grouping object
     * @throws IllegalArgumentException if given argument is none of valid types or has property with incompatible value
    */
    public void fieldsFrom(DataObject arg) {
        boolean isValidArg = false;
        if (arg instanceof org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.logparser.so.metrics.rev210927.MetricSet) {
            this._metrics = ((org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.logparser.so.metrics.rev210927.MetricSet)arg).getMetrics();
            isValidArg = true;
        }
        CodeHelpers.validValue(isValidArg, arg, "[org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.logparser.so.metrics.rev210927.MetricSet]");
    }

    public DateAndTime getCurrentTime() {
        return _currentTime;
    }
    
    public Metrics getMetrics() {
        return _metrics;
    }
    
    public String getNSDID() {
        return _nSDID;
    }
    
    public String getNSID() {
        return _nSID;
    }
    
    public LogparserSoMetrics.Operation getOperation() {
        return _operation;
    }

    @SuppressWarnings({ "unchecked", "checkstyle:methodTypeParameterName"})
    public <E$$ extends Augmentation<LogparserSoMetrics>> E$$ augmentation(Class<E$$> augmentationType) {
        return (E$$) augmentation.get(Objects.requireNonNull(augmentationType));
    }

    
    public LogparserSoMetricsBuilder setCurrentTime(final DateAndTime value) {
        this._currentTime = value;
        return this;
    }
    
    public LogparserSoMetricsBuilder setMetrics(final Metrics value) {
        this._metrics = value;
        return this;
    }
    
    public LogparserSoMetricsBuilder setNSDID(final String value) {
        this._nSDID = value;
        return this;
    }
    
    public LogparserSoMetricsBuilder setNSID(final String value) {
        this._nSID = value;
        return this;
    }
    
    public LogparserSoMetricsBuilder setOperation(final LogparserSoMetrics.Operation value) {
        this._operation = value;
        return this;
    }
    
    /**
      * Add an augmentation to this builder's product.
      *
      * @param augmentation augmentation to be added
      * @return this builder
      * @throws NullPointerException if {@code augmentation} is null
      */
    public LogparserSoMetricsBuilder addAugmentation(Augmentation<LogparserSoMetrics> augmentation) {
        Class<? extends Augmentation<LogparserSoMetrics>> augmentationType = augmentation.implementedInterface();
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
    public LogparserSoMetricsBuilder removeAugmentation(Class<? extends Augmentation<LogparserSoMetrics>> augmentationType) {
        if (this.augmentation instanceof HashMap) {
            this.augmentation.remove(augmentationType);
        }
        return this;
    }

    @Override
    public LogparserSoMetrics build() {
        return new LogparserSoMetricsImpl(this);
    }

    private static final class LogparserSoMetricsImpl
        extends AbstractAugmentable<LogparserSoMetrics>
        implements LogparserSoMetrics {
    
        private final DateAndTime _currentTime;
        private final Metrics _metrics;
        private final String _nSDID;
        private final String _nSID;
        private final LogparserSoMetrics.Operation _operation;
    
        LogparserSoMetricsImpl(LogparserSoMetricsBuilder base) {
            super(base.augmentation);
            this._currentTime = base.getCurrentTime();
            this._metrics = base.getMetrics();
            this._nSDID = base.getNSDID();
            this._nSID = base.getNSID();
            this._operation = base.getOperation();
        }
    
        @Override
        public DateAndTime getCurrentTime() {
            return _currentTime;
        }
        
        @Override
        public Metrics getMetrics() {
            return _metrics;
        }
        
        @Override
        public String getNSDID() {
            return _nSDID;
        }
        
        @Override
        public String getNSID() {
            return _nSID;
        }
        
        @Override
        public LogparserSoMetrics.Operation getOperation() {
            return _operation;
        }
    
        private int hash = 0;
        private volatile boolean hashValid = false;
        
        @Override
        public int hashCode() {
            if (hashValid) {
                return hash;
            }
        
            final int result = LogparserSoMetrics.bindingHashCode(this);
            hash = result;
            hashValid = true;
            return result;
        }
    
        @Override
        public boolean equals(Object obj) {
            return LogparserSoMetrics.bindingEquals(this, obj);
        }
    
        @Override
        public String toString() {
            return LogparserSoMetrics.bindingToString(this);
        }
    }
}
