package org.idey.algo.datastructure.tree;

import org.idey.algo.datastructure.list.LinkedList;
import org.idey.algo.datastructure.list.LinkedNode;

public class TreeUtil {
    public static <E extends Comparable<E>> boolean isBST(TreeNode<E> node, TreeNode<E> prevNode){
        if(node!=null){
            if(!isBST(node.getLeft(),prevNode)){
                return false;
            }
            if(prevNode!=null && node.getData().compareTo(prevNode.getData())<=0){
                return false;
            }
            prevNode = node;
            return isBST(node.getRight(),prevNode);
        }
        return true;
    }

    public static <E extends Comparable<E>> boolean isBST(TreeNode<E> node){
        return isBST(node,null);
    }

    public static <E> void reverseTree(TreeNode<E> root){
        if(root==null)
            return;

        reverseTree(root.getLeft());
        reverseTree(root.getRight());

        TreeNode<E> left = root.getLeft();
        TreeNode<E> right = root.getRight();
        root.addRight(left);
        root.addLeft(right);
    }

    public static void main(String args[]) {

//        TreeNode<Integer> root = new TreeNode<>(4);
//        root.addLeft(new TreeNode<>(2)).addRight(new TreeNode<>(5));
//        root.getLeft().addLeft(new TreeNode<>(2));
//        root.getLeft().addRight(new TreeNode<>(3));
//
//
//        if(isBST(root))
//            System.out.println("IS BST");
//        else
//            System.out.println("Not a BST");
//
//        deleteTree(root);

        TreeNode<Integer> treeNode = buildBst(new Integer[]{1,2,3,6,7});
        System.out.println(isBST(treeNode));

        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.addNode(new LinkedNode<>(1)).addNode(new LinkedNode<>(2)).addNode(new LinkedNode<>(3)).addNode(new LinkedNode<>(4)).addNode(new LinkedNode<>(5)).addNode(new LinkedNode<>(6));
        TreeNode<Integer> treeNode1 = buildBst(linkedList);
        System.out.println(isBST(treeNode1));
        System.out.println(ceil(treeNode,5));
        System.out.println(floor(treeNode,5));

    }


    public static <E> boolean isSymetricTree(TreeNode<E> left, TreeNode<E> right){
        if(left==null && right==null){
            return true;
        }

        if(left!=null && right!=null && left.equals(right)){
            return isSymetricTree(left.getLeft(),right.getRight()) &&
                    isSymetricTree(left.getRight(), right.getLeft());
        }
        return false;
    }

    public static <E> void deleteTree(TreeNode<E> root){
        if(root==null){
            return;
        }
        if(root.getLeft()!=null){
            deleteTree(root.getLeft());
            root.addLeft(null);
        }
        if(root.getRight()!=null){
            deleteTree(root.getRight());
            root.addRight(null);
        }
    }


    private static <E> TreeNode<E> buildBst(E[] array,final int low, final int high){
        if(low>high){
            return null;
        }
        int mid = low+(high-low)/2;
        TreeNode<E> treeNode = new TreeNode<>(array[mid]);
        treeNode.addLeft(buildBst(array,low,mid-1));
        treeNode.addRight(buildBst(array, mid + 1, high));
        return treeNode;
    }

    public static <E> TreeNode<E> buildBst(E[] array){
        return buildBst(array,0,array.length-1);
    }


    public static <E extends Comparable<E>> TreeNode<E> buildBst(LinkedNode[] linkedNode, int count){
        if(count<=0){
            return null;
        }
        TreeNode<E> left = buildBst(linkedNode,count/2);
        TreeNode<E> root = new TreeNode<>((E)linkedNode[0].getObject());
        root.addLeft(left);
        linkedNode[0]= linkedNode[0].getNext();
        TreeNode<E> right = buildBst(linkedNode,count-count/2-1);
        root.addRight(right);
        return root;
    }

    public static <E extends Comparable<E>> TreeNode<E> buildBst(LinkedList<E> linkedList){
        LinkedNode<E>[] array = new LinkedNode[]{linkedList.getStart()};
        return buildBst(array,linkedList.getLength());
    }

    public static <E extends Comparable<E>> E search(TreeNode<E> node, E value){
        if(node==null){
            return null;
        }
        if(value.equals(node.getData())){
            return value;
        }else if(value.compareTo(node.getData())<0){
            return search(node.getLeft(), value);
        }else{
            return search(node.getRight(), value);
        }
    }


    public static <E extends Comparable<E>> E ceil(TreeNode<E> node, E value){
        TreeNode<E> smaller = null;
        while (node!=null){
            int cmp = value.compareTo(node.getData());
            if(cmp==0){
                return value;
            }else if(cmp<0){
                smaller=node;
                node=node.getLeft();
            }else{
                node=node.getRight();
            }
        }
        return smaller==null?null:smaller.getData();
    }


    public static <E extends Comparable<E>> E floor(TreeNode<E> node, E value){
        TreeNode<E> larger = null;
        while (node!=null){
            int cmp = value.compareTo(node.getData());
            if(cmp==0){
                return value;
            }else if(cmp>0){
                larger=node;
                node=node.getRight();
            }else{
                node=node.getLeft();
            }
        }
        return larger==null?null:larger.getData();
    }
}
