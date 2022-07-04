package org.opendaylight.yang.gen.v1.http.openconfig.net.yang.interfaces.rev210406;

import com.google.common.collect.ImmutableSet;
import java.lang.Override;
import java.lang.String;
import java.util.HashSet;
import java.util.Set;
import org.eclipse.jdt.annotation.NonNull;
import org.opendaylight.yangtools.yang.binding.ResourceYangModuleInfo;
import org.opendaylight.yangtools.yang.binding.YangModuleInfo;
import org.opendaylight.yangtools.yang.common.QName;

@javax.annotation.processing.Generated("mdsal-binding-generator")
public final class $YangModuleInfoImpl extends ResourceYangModuleInfo {
    private static final @NonNull QName NAME = QName.create("http://openconfig.net/yang/interfaces", "2021-04-06", "openconfig-interfaces").intern();
    private static final @NonNull YangModuleInfo INSTANCE = new $YangModuleInfoImpl();

    private final @NonNull ImmutableSet<YangModuleInfo> importedModules;

    public static @NonNull YangModuleInfo getInstance() {
        return INSTANCE;
    }

    public static @NonNull QName qnameOf(final String localName) {
        return QName.create(NAME, localName).intern();
    }

    private $YangModuleInfoImpl() {
        Set<YangModuleInfo> set = new HashSet<>();
        set.add(org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.interfaces.rev180220.$YangModuleInfoImpl.getInstance());
        set.add(org.opendaylight.yang.gen.v1.http.openconfig.net.yang.types.yang.rev210714.$YangModuleInfoImpl.getInstance());
        set.add(org.opendaylight.yang.gen.v1.http.openconfig.net.yang.openconfig.types.rev190416.$YangModuleInfoImpl.getInstance());
        set.add(org.opendaylight.yang.gen.v1.http.openconfig.net.yang.openconfig.ext.rev200616.$YangModuleInfoImpl.getInstance());
        importedModules = ImmutableSet.copyOf(set);
    }
    
    @Override
    public QName getName() {
        return NAME;
    }
    
    @Override
    protected String resourceName() {
        return "/META-INF/yang/openconfig-interfaces@2021-04-06.yang";
    }
    
    @Override
    public ImmutableSet<YangModuleInfo> getImportedModules() {
        return importedModules;
    }
}
