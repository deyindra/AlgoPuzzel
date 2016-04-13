package org.idey.algo.datastructure.stack;


import java.util.Stack;

public class MinMaxStack<T extends Comparable<T>> extends java.util.Stack<T> {
    private Stack<T> minStack;

    public MinMaxStack() {
        this.minStack = new Stack<>();
    }

    @Override
    public T push(T item) {
        if(isEmpty())
        {
            minStack.push(item);
        }
        else
        {
            T y = minStack.peek();
            if( item.compareTo(y)<=0)
                minStack.push(item);
        }
        super.push(item);
        return item;
    }

    @Override
    public synchronized T pop() {
        T x = super.pop();
        T y = minStack.pop();

        if(!x.equals(y)){
            minStack.push(y);
        }
        return x;
    }

    public T getMin(){
        return minStack.peek();
    }

    public static void main(String[] args) {
        MinMaxStack<Integer> minMaxStack = new MinMaxStack<>();
        minMaxStack.push(1);
        minMaxStack.push(2);
        minMaxStack.push(-12);

        System.out.println(minMaxStack.getMin());
        minMaxStack.pop();

        System.out.println(minMaxStack.getMin());

    }
}
