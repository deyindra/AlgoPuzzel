package org.idey.algo.oops.module.dependency.command;

import org.idey.algo.iterator.graph.DepthFirstSearchIterator;

import java.util.Iterator;

public class DependencyCommand extends AbstractCommand{
    private String[] dependcyModulesNames;

    public DependencyCommand(String mainModuleName,String... dependcyModulesNames) {
        super(mainModuleName);
        if(dependcyModulesNames !=null && dependcyModulesNames.length>0)
            this.dependcyModulesNames = dependcyModulesNames;
        else
            this.dependcyModulesNames = new String[]{};
    }

    @Override
    public void perform() {
        if(dependcyModulesNames.length==0){
            registry.getModuleGraph().addEdge(mainModuleName,null);
        }else{
            for(String dependecyModuleName: dependcyModulesNames){
                if(registry.getModuleGraph().isVertexExist(dependecyModuleName) &&
                        isPathExists(dependecyModuleName,mainModuleName)){
                    throw new IllegalArgumentException(String.format("Circular Dependecy detected between %s and %s",
                            dependecyModuleName, mainModuleName));
                }else{
                    registry.getModuleGraph().addEdge(mainModuleName,dependecyModuleName);
                }
            }
        }
    }


    private boolean isPathExists(String source, String destination){
        Iterator<String> depthFirstIterator = new DepthFirstSearchIterator<>(registry.getModuleGraph(), source);
        while (depthFirstIterator.hasNext()){
            if(depthFirstIterator.next().equals(destination)){
                return  true;
            }
        }
        return false;
    }

}
