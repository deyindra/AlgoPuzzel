package org.idey.algo.datastructure.tree;

import java.util.Comparator;

public class TreeNodeComparator<E extends Comparable<E>> implements Comparator<TreeNode<E>> {
    @Override
    public int compare(TreeNode<E> o1, TreeNode<E> o2) {
        if(o1 == null && o2 == null){
            return 0;
        }else if(o1 == null){
            return -1;
        }else if (o2 == null){
            return 1;
        }else{
            E data1 = o1.data;
            E data2 = o2.data;
            if(data1==null && data2==null){
                return 0;
            }else if(data1==null){
                return -1;
            }else{
                return data1.compareTo(data2);
            }
        }
    }


}
