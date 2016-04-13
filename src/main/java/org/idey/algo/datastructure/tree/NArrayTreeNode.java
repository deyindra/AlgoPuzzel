package org.idey.algo.datastructure.tree;

import java.util.ArrayList;
import java.util.List;

public class NArrayTreeNode<E> {
    private E data;
    private List<NArrayTreeNode<E>> childs = new ArrayList<>();

    public NArrayTreeNode(E data) {
        this.data = data;
    }

    public NArrayTreeNode<E> addChild(NArrayTreeNode<E> child){
        this.childs.add(child);
        return this;
    }


    public List<NArrayTreeNode<E>> getChilds() {
        return childs;
    }

    public E getData() {
        return data;
    }

    @Override
    public String toString() {
        StringBuilder builder=new StringBuilder();
        builder.append(data.toString());
        for(NArrayTreeNode child:childs){
            builder.append(child.toString());
        }
        return builder.toString();
    }
}
