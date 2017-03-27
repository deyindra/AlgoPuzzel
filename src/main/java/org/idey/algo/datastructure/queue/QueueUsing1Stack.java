package org.idey.algo.datastructure.queue;


import java.util.Stack;

public class QueueUsing1Stack<T> implements Queue<T> {
    private Stack<T> stack;

    public QueueUsing1Stack() {
        this.stack = new Stack<T>();
    }

    @Override
    public void offer(T object) {
        stack.push(object);
    }

    @Override
    public T poll() {
        T top = stack.pop();
        if(stack.isEmpty())
            return top;
        else
        {
            T result = poll();
            stack.push(top);
            return result;
        }
    }

    @Override
    public int size() {
        return stack.size();
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        QueueUsing1Stack<Integer> object = new QueueUsing1Stack<>();
        object.offer(1);
        object.offer(2);
        object.offer(3);
        while (!object.isEmpty()){
            System.out.println(object.poll());
        }
    }
}
