package org.idey.algo.iterator.tree;


import org.idey.algo.datastructure.tree.TreeNode;

import java.util.Stack;

public abstract class AbstractTreeDFSIterator<E> extends AbstractTreeIterator<E> {
    protected final Stack<TreeNode<E>> stack;

    protected AbstractTreeDFSIterator(TreeNode<E> root) {
        super(root);
        stack = new Stack<>();
    }

    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }

}
