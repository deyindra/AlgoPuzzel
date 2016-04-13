package org.idey.algo.oops.module.dependency.command;

import java.util.Set;

public class InstallDependencyCommand extends AbstractCommand{

    public InstallDependencyCommand(String moduleName) {
        super(moduleName);
    }

    @Override
    public void perform() {
        Set<String> installationRegistry = registry.getInstallationRegistry();
        generateDependencyString(mainModuleName, installationRegistry);
    }

    private void generateDependencyString(String moduleName, Set<String> installationRegistry){
        Iterable<String> dependencies = registry.getModuleGraph().getNeighbors(moduleName);
        for(String depency:dependencies){
            generateDependencyString(depency,installationRegistry);

        }
        if(!installationRegistry.contains(moduleName)) {
            LOGGER.info(String.format("Installating %s ...", moduleName));
            installationRegistry.add(moduleName);
        }else{
            LOGGER.info(String.format("%s is already installed...",moduleName));
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

        Command installaDepency= new InstallDependencyCommand("A");
        installaDepency.perform();

        Command installDependency1= new InstallDependencyCommand("X");
        installDependency1.perform();


    }
}
