package org.idey.algo.iterator.tree;

import org.idey.algo.datastructure.tree.TreeNode;

import java.util.NoSuchElementException;

public class AdvancedLevelOrderIterator<E> extends LevelOrderIterator<E> {
    private int nodesInCurrentLevel;
    private int nodesInNextLevel;
    private int currentLevel;
    private final int maxlevel;
    public AdvancedLevelOrderIterator(TreeNode<E> root, int maxLevel) {
        super(root);
        assert(maxLevel>=0);
        this.maxlevel=maxLevel;
        this.currentLevel=0;
        this.nodesInCurrentLevel=1;
        this.nodesInNextLevel=0;
    }

    @Override
    public boolean hasNext() {
        return super.hasNext() && currentLevel<=maxlevel;
    }

    @Override
    public E next() {
        if(!hasNext()) {
            throw new NoSuchElementException("All nodes have been visited!");
        }

        TreeNode<E> current = queue.remove();
        nodesInCurrentLevel--;
        TreeNode<E> left = current.getLeft();
        TreeNode<E> right = current.getRight();

        if (left != null){
            queue.add(left);
            nodesInNextLevel++;
        }
        if (right != null){
            queue.add(right);
            nodesInNextLevel++;
        }

        if(nodesInCurrentLevel==0){
            nodesInCurrentLevel = nodesInNextLevel;
            nodesInNextLevel=0;
            currentLevel++;
        }
        return current.getData();
    }
}
