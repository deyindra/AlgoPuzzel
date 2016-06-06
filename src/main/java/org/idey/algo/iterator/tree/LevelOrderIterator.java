package org.idey.algo.iterator.tree;

import org.idey.algo.datastructure.tree.TreeNode;

import java.util.NoSuchElementException;

public class LevelOrderIterator<E> extends AbstractTreeBFSIterator<E> {
    public LevelOrderIterator(TreeNode<E> root) {
        super(root);
        this.queue.add(root);
    }

    @Override
    public E next() {
        if(!hasNext()) {
            throw new NoSuchElementException("All nodes have been visited!");
        }
        TreeNode<E> current = queue.remove();

        TreeNode<E> left = current.getLeft();
        TreeNode<E> right = current.getRight();

        if (left != null) queue.add(left);
        if (right != null) queue.add(right);

        return current.getData();
    }


}
