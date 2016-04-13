package org.idey.algo.iterator.tree;

import org.idey.algo.datastructure.tree.TreeNode;

import java.util.NoSuchElementException;

public class InOrderTreeIterator<E> extends AbstractTreeDFSIterator<E> {

    public InOrderTreeIterator(TreeNode<E> root) {
        super(root);
        for (TreeNode<E> current = root; current != null; current = current.getLeft()) {
            this.stack.push(current);
        }
    }


    @Override
    public E next() {
        if (!hasNext()) throw new NoSuchElementException("No more nodes remain to iterate");
        TreeNode<E> current = stack.pop();
        for (TreeNode<E> child = current.getRight(); child != null; child = child.getLeft()) {
            stack.push(child);
        }
        return current.getData();
    }



}
