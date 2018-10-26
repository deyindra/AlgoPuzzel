package org.idey.algo.datastructure.tree.process;

import java.util.HashSet;
import java.util.Set;

public class ProcessUtil {
    //Populate Children
    public Pid populateAllChild(Process process, int id){
        Set<Integer> processIds = process.getAllProcess();
        if(!processIds.contains(id)){
            throw new IllegalArgumentException();
        }
        Pid pid = new Pid(id);
        populateAllChild(process,pid,processIds);
        return pid;
    }
    public void populateAllChild(Process p, Pid pid, Set<Integer> processIds){
        for(int id:processIds){
            if(id!=pid.getProcessId() && p.getParentId(id)==pid.getProcessId()){
                Pid childProcess = new Pid(id);
                pid.getChildren().add(childProcess);
                populateAllChild(p,childProcess,processIds);
            }
        }
    }

    //Kill the process and child process
    public int killAll(Process p, int id){
        Pid pid = populateAllChild(p,id);
        return killAll(p, pid);
    }

    //Kill the process and child process
    public int killAll(Process p, Pid pid){
        Set<Pid> childToBeDeleted = new HashSet<>();
        for(Pid chid:pid.getChildren()){
            int childStatus = killAll(p,chid);
            if(childStatus==0){
                childToBeDeleted.add(chid);
            }
        }
        //Delete the child process which are killed successfully
        pid.getChildren().removeAll(childToBeDeleted);
        if(pid.getChildren().size()==0)
            return killProcess(p, pid);
        else
            return 1;
    }


    public int killProcess(Process p, Pid pid){
        return p.kill(pid.getProcessId(),9);
    }

}
