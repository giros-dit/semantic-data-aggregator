package org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.structure.ext.rev200617;

import com.google.common.collect.ImmutableSet;
import java.lang.Override;
import java.lang.String;
import org.eclipse.jdt.annotation.NonNull;
import org.opendaylight.yangtools.yang.binding.ResourceYangModuleInfo;
import org.opendaylight.yangtools.yang.binding.YangModuleInfo;
import org.opendaylight.yangtools.yang.common.QName;

@javax.annotation.processing.Generated("mdsal-binding-generator")
public final class $YangModuleInfoImpl extends ResourceYangModuleInfo {
    private static final @NonNull QName NAME = QName.create("urn:ietf:params:xml:ns:yang:ietf-yang-structure-ext", "2020-06-17", "ietf-yang-structure-ext").intern();
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
        return "/META-INF/yang/ietf-yang-structure-ext@2020-06-17.yang";
    }
    
    @Override
    public ImmutableSet<YangModuleInfo> getImportedModules() {
        return importedModules;
    }
}
