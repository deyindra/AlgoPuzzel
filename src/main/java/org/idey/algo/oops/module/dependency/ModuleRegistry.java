package org.idey.algo.oops.module.dependency;


import org.idey.algo.datastructure.graph.Graph;

import java.util.HashSet;
import java.util.Set;

public class ModuleRegistry {
    private static volatile ModuleRegistry INSTANCE;
    private Graph<String> moduleGraph;
    private Set<String> installationRegistry;
    public ModuleRegistry() {
        this.moduleGraph = new Graph<>();
        installationRegistry = new HashSet<>();
    }

    public static ModuleRegistry getInstance(){
        if(INSTANCE==null){
            synchronized (ModuleRegistry.class){
                if(INSTANCE==null){
                    INSTANCE = new ModuleRegistry();
                }
            }
        }
        return INSTANCE;
    }

    public Graph<String> getModuleGraph(){
        return moduleGraph;
    }

    public Set<String> getInstallationRegistry() {
        return installationRegistry;
    }
}
