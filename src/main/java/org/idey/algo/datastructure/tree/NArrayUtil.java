package org.idey.algo.datastructure.tree;


import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class NArrayUtil {
    public static <T> NArrayTreeNode<T> reverseTree(NArrayTreeNode<T> node){
        if(node==null){
            return null;
        }
        node.getChilds().forEach(NArrayUtil::reverseTree);
        List<NArrayTreeNode<T>> list = node.getChilds();
        for(int i=0,j=list.size()-1;i<j;i++,j--){
            NArrayTreeNode<T> left = list.get(i);
            NArrayTreeNode<T> right = list.get(j);
            list.set(i,right);
            list.set(j,left);
        }
        return node;
    }


    public static void main(String[] args) {
        NArrayTreeNode<Integer> root = new NArrayTreeNode<>(1);
        NArrayTreeNode<Integer> child1 = new NArrayTreeNode<>(2);
        NArrayTreeNode<Integer> child2 = new NArrayTreeNode<>(3);
        NArrayTreeNode<Integer> child3 = new NArrayTreeNode<>(4);

        NArrayTreeNode<Integer> child5 = new NArrayTreeNode<>(5);
        NArrayTreeNode<Integer> child6 = new NArrayTreeNode<>(6);

        child1.addChild(child5).addChild(child6);
        root.addChild(child1).addChild(child2).addChild(child3);

        reverseTree(root);

        int nodeInCurrentLevel = 1;
        int nodeInNextLevel =0;

        Queue<NArrayTreeNode<Integer>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            NArrayTreeNode<Integer> node = queue.poll();
            nodeInCurrentLevel--;
            System.out.print(node.getData());
            for(NArrayTreeNode<Integer> c:node.getChilds()){
                if(c!=null){
                    queue.offer(c);
                    nodeInNextLevel++;
                }
            }

            if(nodeInCurrentLevel==0){
                nodeInCurrentLevel=nodeInNextLevel;
                nodeInNextLevel=0;
                System.out.print("\n");
            }

        }

    }
}
