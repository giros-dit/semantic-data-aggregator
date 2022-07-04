package org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;
import java.lang.Integer;
import java.lang.Override;
import java.lang.String;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import javax.annotation.processing.Generated;
import org.opendaylight.yangtools.yang.binding.Enumeration;

@Generated("mdsal-binding-generator")
public enum MetricType implements Enumeration {
    /**
     * Unknown must use unknown MetricPoint values.
     */
    Unknown(0, "unknown"),
    
    /**
     * Gauge must use gauge MetricPoint values.
     */
    Gauge(1, "gauge"),
    
    /**
     * Counter must use counter MetricPoint values.
     */
    Counter(2, "counter"),
    
    /**
     * State set must use state set MetricPoint values.
     */
    StateSet(3, "state_set"),
    
    /**
     * Info must use info MetricPoint values.
     */
    Info(4, "info"),
    
    /**
     * Histogram must use histogram value MetricPoint values.
     */
    Histogram(5, "histogram"),
    
    /**
     * Gauge histogram must use histogram value MetricPoint values.
     */
    GaugeHistogram(6, "gauge_histogram"),
    
    /**
     * Summary quantiles must use summary value MetricPoint values.
     */
    Summary(7, "summary")
    ;

    private static final Map<String, MetricType> NAME_MAP;
    private static final Map<Integer, MetricType> VALUE_MAP;

    static {
        final Builder<String, MetricType> nb = ImmutableMap.builder();
        final Builder<Integer, MetricType> vb = ImmutableMap.builder();
        for (MetricType enumItem : MetricType.values()) {
            vb.put(enumItem.value, enumItem);
            nb.put(enumItem.name, enumItem);
        }

        NAME_MAP = nb.build();
        VALUE_MAP = vb.build();
    }

    private final String name;
    private final int value;

    private MetricType(int value, String name) {
        this.value = value;
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getIntValue() {
        return value;
    }

    /**
     * Return the enumeration member whose {@link #getName()} matches specified value.
     *
     * @param name YANG assigned name
     * @return corresponding MetricType item, if present
     * @throws NullPointerException if name is null
     */
    public static Optional<MetricType> forName(String name) {
        return Optional.ofNullable(NAME_MAP.get(Objects.requireNonNull(name)));
    }

    /**
     * Return the enumeration member whose {@link #getIntValue()} matches specified value.
     *
     * @param intValue integer value
     * @return corresponding MetricType item, or null if no such item exists
     */
    public static MetricType forValue(int intValue) {
        return VALUE_MAP.get(intValue);
    }
}
