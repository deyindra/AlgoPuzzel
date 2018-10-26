package org.idey.algo.datastructure.tree.process;

import java.util.HashSet;
import java.util.Set;

public class Pid {
    private int processId;
    private Set<Pid> children;

    public Pid(int processId) {
        this.processId = processId;
        this.children = new HashSet<>();
    }

    public int getProcessId() {
        return processId;
    }

    public Set<Pid> getChildren() {
        return children;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pid pid = (Pid) o;

        return processId == pid.processId;
    }

    @Override
    public int hashCode() {
        return processId;
    }
}
