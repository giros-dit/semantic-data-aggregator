package org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.logparser.so.metrics.rev210927;
import javax.annotation.processing.Generated;
import org.eclipse.jdt.annotation.NonNull;
import org.opendaylight.yangtools.yang.common.QName;

/**
 * Time interaction between SOE-SLA Manager to configure the AI/ML workflow to 
 * drive scaling operations. The creation and configuration of the data 
 * pipeline consist of: i) interaction with 5Gr-VoMs to create a Kafka Topic, ii) 
 * interaction with the 5Gr-AIML platform to download the required model, iii) 
 * creation of inference job at Apache Spark, iv) update of Network Service 
 * Instantiation Resource (NSIR) database.
 *
 * <p>
 * This class represents the following YANG schema fragment defined in module <b>logparser-so</b>
 * <pre>
 * identity Create_AIML_alerts {
 *   base metric-identity;
 * }
 * </pre>The schema path to identify an instance is
 * <i>logparser-so/Create_AIML_alerts</i>
 *
 */
@Generated("mdsal-binding-generator")
public interface CreateAIMLAlerts
    extends
    MetricIdentity
{



    public static final @NonNull QName QNAME = $YangModuleInfoImpl.qnameOf("Create_AIML_alerts");


}

