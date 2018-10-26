package org.idey.algo.iterator.tree;

import org.idey.algo.datastructure.tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public abstract class AbstractTreeBFSIterator<E> extends AbstractTreeIterator<E> {
    protected Queue<TreeNode<E>> queue;

    public AbstractTreeBFSIterator(TreeNode<E> root) {
        super(root);
        this.queue = new LinkedList<>();
    }

    @Override
    public boolean hasNext() {
        return !queue.isEmpty();
    }
}
