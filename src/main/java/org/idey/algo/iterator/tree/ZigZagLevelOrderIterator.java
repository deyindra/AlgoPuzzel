package org.idey.algo.iterator.tree;

import org.idey.algo.datastructure.tree.TreeNode;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

public class ZigZagLevelOrderIterator<E> extends AbstractTreeDFSIterator<E> {
    private Stack<TreeNode<E>> anotherStack;
    private Stack<TreeNode<E>> currentStack;
    private int nodesInCurrentLevel;
    private int nodesInNextLevel;

    public ZigZagLevelOrderIterator(TreeNode<E> root) {
        super(root);
        this.anotherStack = new Stack<>();
        this.stack.push(root);
        this.currentStack = this.stack;
        this.nodesInCurrentLevel = 1;
        this.nodesInNextLevel=0;
    }

    @Override
    public boolean hasNext() {
        return !stack.isEmpty() || !anotherStack.isEmpty();
    }

    @Override
    public E next() {
        if(!hasNext()) {
            throw new NoSuchElementException();
        }
        TreeNode<E> visited = currentStack.pop();
        nodesInCurrentLevel--;
        if(currentStack == this.stack) {
            if(visited.getRight()!=null) {
                anotherStack.push(visited.getRight());
                nodesInNextLevel++;
            }
            if(visited.getLeft()!=null) {
                anotherStack.push(visited.getLeft());
                nodesInNextLevel++;
            }
        } else if(currentStack == this.anotherStack) {
            if(visited.getLeft()!=null) {
                stack.push(visited.getLeft());
                nodesInNextLevel++;
            }

            if(visited.getRight()!=null) {
                stack.push(visited.getRight());
                nodesInNextLevel++;
            }
        }

        if(nodesInCurrentLevel == 0) {
            nodesInCurrentLevel = nodesInNextLevel;
            nodesInNextLevel = 0;
            currentStack = (currentStack == this.stack) ? this.anotherStack : this.stack;
        }
        return visited.getData();
    }

    public static void main(String[] args) {
        TreeNode<Integer> root = new TreeNode<>(1);
        root.addLeft(new TreeNode<>(2));
        root.addRight(new TreeNode<>(3));
        root.getLeft().addLeft(new TreeNode<>(4));
        root.getLeft().addRight(new TreeNode<>(5));

        root.getRight().addLeft(new TreeNode<>(6));
        root.getRight().addRight(new TreeNode<>(7));

        root.getLeft().getLeft().addLeft(new TreeNode<>(8));
        root.getLeft().getLeft().addRight(new TreeNode<>(9));

        Iterator<Integer> it = new ZigZagLevelOrderIterator<>(root);
        while (it.hasNext()){
            System.out.print(it.next());
        }


    }
}
