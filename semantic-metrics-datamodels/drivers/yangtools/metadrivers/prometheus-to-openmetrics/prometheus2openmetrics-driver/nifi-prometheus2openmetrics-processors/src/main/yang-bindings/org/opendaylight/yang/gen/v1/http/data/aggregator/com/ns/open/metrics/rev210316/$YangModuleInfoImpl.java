package org.opendaylight.yang.gen.v1.http.data.aggregator.com.ns.open.metrics.rev210316;

import com.google.common.collect.ImmutableSet;
import java.lang.Override;
import java.lang.String;
import org.eclipse.jdt.annotation.NonNull;
import org.opendaylight.yangtools.yang.binding.ResourceYangModuleInfo;
import org.opendaylight.yangtools.yang.binding.YangModuleInfo;
import org.opendaylight.yangtools.yang.common.QName;

@javax.annotation.processing.Generated("mdsal-binding-generator")
public final class $YangModuleInfoImpl extends ResourceYangModuleInfo {
    private static final @NonNull QName NAME = QName.create("http://data-aggregator.com/ns/open-metrics", "2021-03-16", "openmetrics").intern();
    private static final @NonNull YangModuleInfo INSTANCE = new $YangModuleInfoImpl();

    private final @NonNull ImmutableSet<YangModuleInfo> importedModules;

    public static @NonNull YangModuleInfo getInstance() {
        return INSTANCE;
    }

    public static @NonNull QName qnameOf(final String localName) {
        return QName.create(NAME, localName).intern();
    }

    private $YangModuleInfoImpl() {
        importedModules = ImmutableSet.of();
    }
    
    @Override
    public QName getName() {
        return NAME;
    }
    
    @Override
    protected String resourceName() {
        return "/META-INF/yang/openmetrics@2021-03-16.yang";
    }
    
    @Override
    public ImmutableSet<YangModuleInfo> getImportedModules() {
        return importedModules;
    }
}
