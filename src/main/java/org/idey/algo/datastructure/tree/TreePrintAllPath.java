package org.idey.algo.datastructure.tree;



import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TreePrintAllPath {
    public static <E>  List<List<E>> getAllNodeInthePath(TreeNode<E> root){
        List<List<E>> lists = new ArrayList<>();
        List<E> list = new ArrayList<>();
        addPath(root,0,list,lists);
        return lists;
    }


    public static <E> List<List<E>> findAllPath(TreeNode<E> root){
        List<List<E>> lists = new ArrayList<>();
        findAllPossiblePath(root,lists, new LinkedList<E>());
        return lists;
    }


    private static <E> void findAllPossiblePath (TreeNode<E> node, List<List<E>> paths, LinkedList<E> path) {
        if(node==null){
            return;
        }
        path.add(node.getData());

        if (node.getLeft()==null && node.getRight()==null) {
            paths.add(new ArrayList<E>(path));
        }else {
            findAllPossiblePath(node.getLeft(), paths, path);
            findAllPossiblePath(node.getRight(), paths, path);
        }
        path.removeLast();
    }


    private static <E> void addPath(TreeNode<E> node, int index, List<E> list, List<List<E>> lists){
        if(node==null)
            return;

        list.add(index,node.getData());
        index++;

        if(node.getLeft()==null && node.getRight()==null ){
           List<E> newList = new ArrayList<>();
           newList.addAll(list.subList(0,index));
           lists.add(newList);
        }else{
            addPath(node.getLeft(),index,list,lists);
            addPath(node.getRight(),index,list,lists);
        }
    }



    public static void main(String[] args){
        TreeNode<Integer> A = new TreeNode<>(1);
        TreeNode<Integer> B = new TreeNode<>(2);
        TreeNode<Integer> C = new TreeNode<>(3);
        TreeNode<Integer> D = new TreeNode<>(4);
        TreeNode<Integer> E = new TreeNode<>(5);
        TreeNode<Integer> F = new TreeNode<>(6);

        E.addLeft(F);
        C.addLeft(D).addRight(E);
        A.addLeft(B).addRight(C);

        List<List<Integer>> list = getAllNodeInthePath(A);
        System.out.println(list);

        List<List<Integer>> list1 = findAllPath(A);
        System.out.println(list1);


    }


}
