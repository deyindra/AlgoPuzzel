package org.idey.algo.datastructure.tree;

import java.util.*;

public class CountUniValTree<E> {
    private TreeNode<E> root;
    public CountUniValTree(TreeNode<E> root) {
        this.root = root;
    }

    private boolean countSingleRec(TreeNode<E> root, Counter c) {
        // Return false to indicate NULL
        if (root == null) {
            return true;
        }

        // Recursively count in left and right subtrees also
        boolean left = countSingleRec(root.getLeft(), c);
        boolean right = countSingleRec(root.getRight(), c);

        // If any of the subtrees is not singly, then this
        // cannot be singly.
        if (!left || !right) {
            return false;
        }

        // If left subtree is singly and non-empty, but data
        // doesn't match
        if (root.getLeft() != null && !root.equals(root.getLeft())) {
            return false;
        }

        // Same for right subtree
        if (root.getRight() != null && !root.equals(root.getRight())) {
            return false;

        }

        // If none of the above conditions is true, then
        // tree rooted under root is single valued, increment
        // count and return true.
        c.cout++;
        return true;

    }

    private class Counter{
        private int cout=0;
    }

    public int countUnival(){
        Counter c = new Counter();
        countSingleRec(root,c);
        return c.cout;
    }


    public static void main(String[] args){
        TreeNode<Integer> A = new TreeNode<>(1);
        TreeNode<Integer> B = new TreeNode<>(2);
        TreeNode<Integer> C = new TreeNode<>(3);
        TreeNode<Integer> D = new TreeNode<>(4);
        TreeNode<Integer> E = new TreeNode<>(5);

        TreeNode<Integer> F = new TreeNode<>(6);
        TreeNode<Integer> G = new TreeNode<>(7);

        D.addLeft(F).addRight(G);
        C.addLeft(D).addRight(E);
        A.addLeft(B).addRight(C);

        TreeNode<Integer> X = new TreeNode<>(100);

//        CountUniValTree<Integer> countUniValTree = new CountUniValTree<>(A);
//        System.out.println(countUniValTree.countUnival());
//        System.out.println(heightRecurrsive(A));
//        System.out.println(heightIterative(A));
//        printTopView(A);
//        System.out.println("\n");
//        printBottomView(A);
//
//        System.out.println("\n");
//        printLevelOrder(A);

        System.out.println(LCA(A,G,B));
        System.out.println(LCAMulti(A,new LinkedHashSet<>(Arrays.asList(G,F,X))));

    }

    public static <E> int heightRecurrsive(TreeNode<E> node){
        if(node==null){
            return 0;
        }else{
            return 1+Math.max(heightRecurrsive(node.getLeft()), heightRecurrsive(node.getRight()));
       }
    }

    public static <E> int heightIterative(TreeNode<E> node){
        int currentDepth=0;
        int nodeAtCurrentLevel=1;
        int nodeAtNextLevel=0;
        Queue<TreeNode<E>> queue = new LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()){
            node=queue.remove();
            nodeAtCurrentLevel--;

            if(node.getLeft()!=null){
                queue.add(node.getLeft());
                nodeAtNextLevel++;
            }

            if(node.getRight()!=null){
                queue.add(node.getRight());
                nodeAtNextLevel++;
            }

            if(nodeAtCurrentLevel==0){
                nodeAtCurrentLevel=nodeAtNextLevel;
                nodeAtNextLevel=0;
                currentDepth++;
            }
        }
        return currentDepth;
    }


    public static <E> void printTopView(TreeNode<E> root){
        Set<Integer> levelSets = new HashSet<>();
        Queue<QueueNode<E>> queueNodes = new LinkedList<>();
        queueNodes.add(new QueueNode<E>(root,0));

        while (!queueNodes.isEmpty()){
            QueueNode<E> e = queueNodes.remove();
            int level = e.level;
            TreeNode<E> node = e.node;
            if(!levelSets.contains(level)){
                System.out.print(node);
                levelSets.add(level);
            }

            if(node.getLeft()!=null){
                queueNodes.add(new QueueNode<E>(node.getLeft(), level-1));
            }

            if(node.getRight()!=null){
                queueNodes.add(new QueueNode<E>(node.getRight(), level+1));
            }

        }
    }


    public static <E> void printBottomView(TreeNode<E> root){
        Map<Integer, TreeNode<E>> levelMap = new TreeMap<>();
        Queue<QueueNode<E>> queueNodes = new LinkedList<>();
        queueNodes.add(new QueueNode<E>(root,0));

        while (!queueNodes.isEmpty()){
            QueueNode<E> e = queueNodes.remove();
            int level = e.level;
            TreeNode<E> node = e.node;

            levelMap.put(level,node);

            if(node.getLeft()!=null){
                queueNodes.add(new QueueNode<E>(node.getLeft(), level-1));
            }

            if(node.getRight()!=null){
                queueNodes.add(new QueueNode<E>(node.getRight(), level+1));
            }
        }
        levelMap.values().forEach(System.out::print);
    }

    private static class QueueNode<E>{
        private TreeNode<E> node;
        private int level;

        public QueueNode(TreeNode<E> node, int level) {
            this.node = node;
            this.level = level;
        }
    }


    public static <E> void printLevelOrder(TreeNode<E> node){
        int nodeAtCurrentLevel=1;
        int nodeAtNextLevel=0;
        Queue<TreeNode<E>> queue = new LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()){
            node=queue.remove();
            nodeAtCurrentLevel--;

            System.out.print(node + " ");

            if(node.getLeft()!=null){
                queue.add(node.getLeft());
                nodeAtNextLevel++;
            }

            if(node.getRight()!=null){
                queue.add(node.getRight());
                nodeAtNextLevel++;
            }

            if(nodeAtCurrentLevel==0){
                nodeAtCurrentLevel=nodeAtNextLevel;
                nodeAtNextLevel=0;
                System.out.print("\n");
            }
        }
    }

    private static <E> TreeNode<E> returnLCA(boolean flag, TreeNode<E> root, TreeNode<E> left, TreeNode<E> right){
        if(flag) {
            return root;
        }else if(left!=null && right!=null){
            return  root;
        }
        else if(left!=null){
            return left;
        }else if(right!=null){
            return right;
        }else{
            return null;
        }
    }

    private static <E> TreeNode<E> LCA(TreeNode<E> root, BitSet set, final TreeNode<E> first, final TreeNode<E> second){
        boolean flag = false;

        if(root==null)
            return null;
        if(root.equals(first)){
            set.set(0,true);
            flag=true;
        }
        if(root.equals(second)){
            set.set(1,true);
            flag=true;
        }

        TreeNode<E>  left = LCA(root.getLeft(), set,first, second);
        TreeNode<E>  right = LCA(root.getRight(), set, first, second);

        return returnLCA(flag,root,left,right);
    }


    private static <E> TreeNode<E> LCAMulti(TreeNode<E> root, BitSet set, Map<TreeNode<E>, Integer> lcaMap){
        boolean flag = false;

        if(root==null)
            return null;

        for(Map.Entry<TreeNode<E>, Integer> entry:lcaMap.entrySet()){
            TreeNode<E> node = entry.getKey();
            int bitPosition = entry.getValue();
            if(root.equals(node)){
                set.set(bitPosition,true);
                flag=true;
            }
        }

        TreeNode<E>  left = LCAMulti(root.getLeft(), set,lcaMap);
        TreeNode<E>  right = LCAMulti(root.getRight(), set,lcaMap);

        return returnLCA(flag,root,left,right);
    }

    public static <E> TreeNode<E> LCA(TreeNode<E> root, final TreeNode<E> first, final TreeNode<E> second){
        BitSet booleanSets = new BitSet(2);
        booleanSets.set(0,2,false);

        TreeNode<E> lca = LCA(root,booleanSets,first,second);

        if(booleanSets.get(0) && booleanSets.get(1))
            return lca;
        else {
            return null;
        }
    }




    public static <E> TreeNode<E> LCAMulti(TreeNode<E> root, Set<TreeNode<E>> lcaSets){
        Map<TreeNode<E>, Integer> map = new LinkedHashMap<>();
        int count=0;
        for(TreeNode<E> val:lcaSets){
            map.put(val,count);
            count++;
        }
        BitSet booleanSets = new BitSet(count);
        booleanSets.set(0,count,false);

        TreeNode<E> lca = LCAMulti(root,booleanSets,map);

        boolean finalResult = true;
        for(int i=0;i<count;i++){
            finalResult = finalResult && booleanSets.get(i);
        }

        if(finalResult){
            return lca;
        }else{
            return null;
        }

    }

}
