package org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.gnmic.rev210719;
import java.lang.Class;
import java.lang.Override;
import javax.annotation.processing.Generated;
import org.eclipse.jdt.annotation.NonNull;
import org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.gnmic.rev210719.tag.set.Tags;
import org.opendaylight.yangtools.yang.binding.DataObject;
import org.opendaylight.yangtools.yang.common.QName;

/**
 * Tags associated with an event.
 * 
 * <p>
 * This class represents the following YANG schema fragment defined in module <b>gnmic</b>
 * <pre>
 * grouping tag-set {
 *   container tags {
 *     list tag {
 *       key name;
 *       leaf name {
 *         type string;
 *       }
 *       leaf value {
 *         type string;
 *       }
 *     }
 *   }
 * }
 * </pre>The schema path to identify an instance is
 * <i>gnmic/tag-set</i>
 *
 */
@Generated("mdsal-binding-generator")
public interface TagSet
    extends
    DataObject
{



    public static final @NonNull QName QNAME = $YangModuleInfoImpl.qnameOf("tag-set");

    @Override
    Class<? extends TagSet> implementedInterface();
    
    /**
     * Return tags, or {@code null} if it is not present.
     * 
     * <pre>
     *     <code>
     *         Enclosing container for the list of tags associated with an event.
     *     </code>
     * </pre>
     * 
     * @return {@code org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.gnmic.rev210719.tag.set.Tags} tags, or {@code null} if it is not present.
     *
     */
    Tags getTags();

}

