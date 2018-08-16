package org.idey.algo.iterator.tree;

import org.idey.algo.datastructure.tree.TreeNode;

import java.util.NoSuchElementException;

public class PreOrderTreeIterator<E> extends AbstractTreeDFSIterator<E> {

    public PreOrderTreeIterator(TreeNode<E> root) {
        super(root);
        stack.push(root);
    }


    @Override
    public E next() {
        if (!hasNext()) throw new NoSuchElementException("No more nodes remain to iterate");

        final TreeNode<E> node = stack.pop();

        TreeNode<E> left = node.getLeft();
        TreeNode<E> right = node.getRight();


        if (right != null) stack.push(right);
        if (left != null) stack.push(left);

        return node.getData();
    }


}
