package org.idey.algo.datastructure.tree;

import org.idey.algo.datastructure.list.LinkedList;
import org.idey.algo.datastructure.list.LinkedNode;
import org.idey.algo.iterator.tree.InOrderTreeIterator;

import java.util.*;

public class TreeUtil {
    public static <E extends Comparable<E>> boolean isBST(TreeNode<E> root, TreeNodeWrapper<E> wrapper){
       if(root==null)
           return true;
       else {
           if (!isBST(root.getLeft(), wrapper)) {
               return false;
           }
           if (wrapper.getNode() != null && root.getData().compareTo(wrapper.getNode().getData()) < 0) {
               return false;
           }
           wrapper.setNode(root);
           return isBST(root.getRight(), wrapper);
       }
    }

    private static class TreeNodeWrapper<E>{
        private TreeNode<E> node;

        public TreeNodeWrapper(TreeNode<E> node) {
            this.node = node;
        }

        public TreeNode<E> getNode() {
            return node;
        }

        public void setNode(TreeNode<E> node) {
            this.node = node;
        }
    }

    public static <E extends Comparable<E>> boolean isBST(TreeNode<E> node){
        return isBST(node, new TreeNodeWrapper<>(null));
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

        TreeNode<Integer> root = new TreeNode<>(4);
        root.addLeft(new TreeNode<>(2)).addRight(new TreeNode<>(5));
        root.getLeft().addLeft(new TreeNode<>(2));
        root.getLeft().addRight(new TreeNode<>(7));


        if(isBST(root))
            System.out.println("IS BST");
        else
            System.out.println("Not a BST");
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
        System.out.println(findAllNodes(buildBst(new Integer[]{1,2,3,4,5,7,8,9,10}), 6));

        TreeNode<Integer> sumRoot  = new TreeNode<>(3);
        sumRoot.addLeft(new TreeNode<>(1)).addRight(new TreeNode<>(2));
        sumRoot.getLeft().addLeft(new TreeNode<>(5)).addRight(new TreeNode<>(6));
        sumRoot.getRight().addLeft(new TreeNode<>(7)).addRight(new TreeNode<>(8));
        sumRoot.getLeft().getRight().addLeft(new TreeNode<>(11));

        System.out.println(isSum(sumRoot).isSum);

        System.out.println(sumOfLeftLeaves(sumRoot));
        System.out.println(sumOfLeaves(sumRoot));
        System.out.println(sumOfRightLeaves(sumRoot));

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

    public static <E extends Comparable<E>> TreeNode<E> search(Comparator<TreeNode<E>> cmp, TreeNode<E> node, TreeNode<E> value){
        if(node==null){
            return null;
        }
        if(cmp.compare(value,node)==0){
            return value;
        }else if(cmp.compare(node,value)>0){
            return search(cmp, node.getLeft(), value);
        }else{
            return search(cmp,node.getRight(), value);
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


    public static <E extends Comparable<E>> TreeNode<E> lcaBSTRecursive(Comparator<TreeNode<E>> cmp,TreeNode<E> node, TreeNode<E> n1, TreeNode<E> n2){
        if(node==null){
            return null;
        }

       if(cmp.compare(n1, node)==0 || cmp.compare(n2, node)==0){
           return node;
       }

       if(cmp.compare(node,n2)>0){
           return lcaBSTRecursive(cmp,node.getLeft(),n1,n2);
       }else if(cmp.compare(n1,node)>0){
           return lcaBSTRecursive(cmp,node.getRight(),n1,n2);
       }else{
           return node;
       }
   }

    public static <E extends Comparable<E>> TreeNode<E> lcaBST(Comparator<TreeNode<E>> cmp,TreeNode<E> node, TreeNode<E> n1, TreeNode<E> n2){
        if(node == null || search(cmp, node,n1)==null || search(cmp, node,n2)==null){
            return null;
        }else{
            TreeNode<E> lca = null;
            if(cmp.compare(n1,n2)>0){
                lca = lcaBSTRecursive(cmp,node,n2, n1);
            }else{
                lca = lcaBSTRecursive(cmp,node,n1, n2);
            }
            return lca;
        }


    }

    private static class Sum{
        int sum=0;
        boolean isSum;

        public Sum(int sum, boolean isSum) {
            this.sum = sum;
            this.isSum = isSum;
        }
    }

    private static Sum isSum(TreeNode<Integer> tree){
        if(tree==null){
            return new Sum(0,true);
        }

        if(tree.getLeft()==null || tree.getRight()==null){
            return new Sum(tree.data,true);
        }

        Sum left = isSum(tree.getLeft());
        Sum right = isSum(tree.getRight());
        if(!left.isSum || !right.isSum || tree.data!=left.sum+right.sum){
            return new Sum(tree.data+left.sum+right.sum,false);
        }
        return new Sum(2*tree.data,true);
    }

    public static int sumOfLeaves(TreeNode<Integer> tree){
        int result = 0;
        if(tree==null){
            return result;
        }else{
            if(tree.getLeft()==null && tree.getRight()==null){
                result += tree.data;
            }
            result+=sumOfLeaves(tree.getLeft());
            result+=sumOfLeaves(tree.getRight());

            return result;
        }
    }

    public static int sumOfLeftLeaves(TreeNode<Integer> tree){
        int result=0;
        if(tree==null){
            return result;
        }else{
            TreeNode<Integer> left = tree.getLeft();
            if(left!=null) {
                if (left.getLeft() == null && left.getRight() == null) {
                    result = result + left.data;
                } else {
                    result += sumOfLeftLeaves(left);
                }
            }
            result = result+sumOfLeftLeaves(tree.getRight());
        }
        return result;
    }

    public static int sumOfRightLeaves(TreeNode<Integer> tree){
        int result=0;
        if(tree==null){
            return result;
        }else{
            TreeNode<Integer> right = tree.getRight();
            if(right!=null) {
                if (right.getLeft() == null && right.getRight() == null) {
                    result = result + right.data;
                } else {
                    result += sumOfRightLeaves(right);
                }
            }
            result = result+sumOfRightLeaves(tree.getLeft());
        }
        return result;
    }



    //Time O(n) and Space O(longN)
    public static List<Integer> findAllNodes(final TreeNode<Integer> tree, final int sum){
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode<Integer>> left = new Stack<>();
        Stack<TreeNode<Integer>> right = new Stack<>();

        TreeNode<Integer> leftCurrent = tree;
        TreeNode<Integer> rightCurrent = tree;

        while (!left.isEmpty() || !right.isEmpty() || leftCurrent!=null || rightCurrent!=null){
            if (leftCurrent != null || rightCurrent != null) {
                if (leftCurrent != null) {
                    left.push(leftCurrent);
                    leftCurrent = leftCurrent.getLeft();
                }

                if (rightCurrent != null) {
                    right.push(rightCurrent);
                    rightCurrent = rightCurrent.getRight();
                }
            }else{
                TreeNode<Integer> leftValue = left.peek();
                TreeNode<Integer> rightValue = right.peek();

                int leftVal = leftValue.getData() ;
                int rightVal = rightValue.getData();

                if(leftValue==rightValue){
                    break;
                }

                if(leftVal + rightVal == sum){
                    list.add(leftVal);
                    list.add(rightVal);
                    leftCurrent = left.pop();
                    leftCurrent = leftCurrent.getRight();
                    rightCurrent = right.pop();
                    rightCurrent = rightCurrent.getLeft();
                }else if(leftVal + rightVal < sum){
                    leftCurrent = left.pop();
                    leftCurrent = leftCurrent.getRight();
                }else{
                    rightCurrent = right.pop();
                    rightCurrent = rightCurrent.getLeft();
                }
            }

        }
        return list;
    }
}
