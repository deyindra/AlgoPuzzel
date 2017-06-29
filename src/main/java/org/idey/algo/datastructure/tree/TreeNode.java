package org.idey.algo.datastructure.tree;

public class TreeNode<E> {
    E data;
    TreeNode<E> left;
    TreeNode<E> right;

    public TreeNode(E data) {
        this.data = data;
    }

    public TreeNode<E> addLeft(TreeNode<E> left){
        this.left = left;
        return this;
    }

    public TreeNode<E> addRight(TreeNode<E> right){
        this.right = right;
        return this;
    }

    public TreeNode<E> getLeft() {
        return left;
    }

    public TreeNode<E> getRight() {
        return right;
    }

    public E getData() {
        return data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TreeNode<?> treeNode = (TreeNode<?>) o;

        if (data != null ? !data.equals(treeNode.data) : treeNode.data != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return data != null ? data.hashCode() : 0;
    }

    @Override
    public String toString() {
        return data.toString();
    }
}
