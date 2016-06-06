package org.idey.algo.iterator.tree;

import org.idey.algo.datastructure.tree.TreeNode;

import java.util.NoSuchElementException;

public class ZigZagLevelOrderIterator<E> extends AbstractTreeBFSIterator<E> {
    private boolean fromLeftToRight;
    public ZigZagLevelOrderIterator(TreeNode<E> root) {
        super(root);
        this.queue.add(root);
        fromLeftToRight=true;
    }

    @Override
    public E next() {
        if(!hasNext()) {
            throw new NoSuchElementException("All nodes have been visited!");
        }
        TreeNode<E> current = queue.remove();
        TreeNode<E> left;
        TreeNode<E> right;

        if(fromLeftToRight){
            left = current.getLeft();
            right = current.getRight();
        }else{
            left = current.getRight();
            right = current.getLeft();
        }
        if(left!=null)
            queue.add(left);
        if(right!=null)
            queue.add(right);

        if(left!=null || right!=null){
            fromLeftToRight=!fromLeftToRight;
        }

        return current.getData();
    }


}
