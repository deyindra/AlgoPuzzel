package org.idey.algo.iterator.tree;

import org.idey.algo.datastructure.tree.TreeNode;

import java.util.Iterator;

public abstract class AbstractTreeIterator<E> implements Iterator<E> {
    protected AbstractTreeIterator(TreeNode<E> root){
        assert(root!=null);
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("remove is not supported");
    }

}
