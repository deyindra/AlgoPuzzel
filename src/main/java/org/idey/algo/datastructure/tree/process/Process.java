package org.idey.algo.datastructure.tree.process;

import java.util.Set;

public interface Process {
    Set<Integer> getAllProcess();
    int getParentId(int id);
    int kill(int id, int signal);
}
