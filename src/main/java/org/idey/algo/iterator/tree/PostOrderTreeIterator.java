package org.idey.algo.iterator.tree;

import org.idey.algo.datastructure.tree.TreeNode;

import java.util.NoSuchElementException;

public class PostOrderTreeIterator<E> extends AbstractTreeDFSIterator<E> {

    public PostOrderTreeIterator(TreeNode<E> root) {
        super(root);
        setNextNode(root);
    }

    private void setNextNode(TreeNode<E> current){
        while (current != null) {
            stack.push(current);
            TreeNode<E> left = current.getLeft();
            if (left != null) {
                current = left;
            } else {
                current = current.getRight();
            }
        }
    }

    @Override
    public E next() {
        if (!hasNext()) {
            throw new NoSuchElementException("All nodes have been visited!");
        }
        TreeNode<E> res = stack.pop();
        if (!stack.isEmpty()) {
            TreeNode<E> top = stack.peek();
            if (res == top.getLeft()) {
                setNextNode(top.getRight()); // find next leaf in right sub-tree
            }
        }
        return res.getData();
    }



}
