package org.idey.algo.oops.module.dependency.command;

import java.util.HashSet;
import java.util.Set;

public class PrintDependencyCommand extends AbstractCommand{
    private Set<String> visible;
    public PrintDependencyCommand(String moduleName) {
        super(moduleName);
        visible = new HashSet<>();
    }

    @Override
    public void perform() {
        StringBuilder builder = new StringBuilder("");
        generateDependencyString(mainModuleName,builder,"");
        LOGGER.info(builder.toString());
    }

    private void generateDependencyString(String moduleName, StringBuilder builder, String seperator){
        Iterable<String> dependencies = registry.getModuleGraph().getNeighbors(moduleName);
        for(String depency:dependencies){
            if(!visible.contains(depency)) {
                generateDependencyString(depency, builder, ",");
                visible.add(depency);
            }
        }
        if(!visible.contains(moduleName)) {
            builder.append(moduleName).append(seperator);
            visible.add(moduleName);
        }
    }

    public static void main(String[] args) {
        Command dependencyCommand1 = new DependencyCommand("A", "B", "C");
        dependencyCommand1.perform();

        Command dependencyCommand2 = new DependencyCommand("B", "D");
        dependencyCommand2.perform();

        Command dependencyCommand3 = new DependencyCommand("C", "D");
        dependencyCommand3.perform();

        Command dependencyCommand4 = new DependencyCommand("D", "E");
        dependencyCommand4.perform();

        Command dependencyCommand5 = new DependencyCommand("X", "B");
        dependencyCommand5.perform();

        Command printDependency= new PrintDependencyCommand("A");
        printDependency.perform();

        Command printDependency1= new PrintDependencyCommand("X");
        printDependency1.perform();


    }
}
