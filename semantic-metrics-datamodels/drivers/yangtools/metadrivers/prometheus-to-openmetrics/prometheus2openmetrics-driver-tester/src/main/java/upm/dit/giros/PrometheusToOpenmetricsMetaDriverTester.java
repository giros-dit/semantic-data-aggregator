package upm.dit.giros;

/**
 * Prometheus to Openmetrics YANGTools Meta Driver Tester.
 */
public class PrometheusToOpenmetricsMetaDriverTester 
{
    public static void main( String[] args ) {
        PrometheusSourceDriverTester prometheus_source = new PrometheusSourceDriverTester();
        PrometheusToOpenmetricsTransformerDriverTester prometheus_transformer = new PrometheusToOpenmetricsTransformerDriverTester("linklatency","gauge","","");
        prometheus_source.start();
        prometheus_transformer.start();
    }
}
