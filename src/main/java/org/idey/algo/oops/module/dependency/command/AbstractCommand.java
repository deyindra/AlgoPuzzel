package org.idey.algo.oops.module.dependency.command;

import org.idey.algo.oops.module.dependency.ModuleRegistry;

import java.util.logging.Logger;

public abstract class AbstractCommand implements Command{
    protected static final Logger LOGGER = Logger.getLogger(AbstractCommand.class.getName());
    protected ModuleRegistry registry;
    protected String mainModuleName;

    protected AbstractCommand(String mainModuleName) {
        if(mainModuleName!=null && !mainModuleName.isEmpty())
            this.mainModuleName = mainModuleName.trim();
        else
            throw new IllegalArgumentException("Invalid ModuleName...");
        registry = ModuleRegistry.getInstance();
    }
}
