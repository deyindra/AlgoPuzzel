package org.idey.algo.datastructure.queue;


import java.util.Stack;

public class QueueUsing2Stacks<T> implements Queue<T> {
    private Stack<T> stack=new Stack<T>();
    private Stack<T> tempStack=new Stack<>();

    @Override
    public boolean isEmpty() {
        return stack.size()==0;
    }

    @Override
    public void offer(T object) {
        if(stack.isEmpty()){
            stack.push(object);
        }else{
            while (!stack.isEmpty()){
                tempStack.push(stack.pop());
            }
            stack.push(object);
            while (!tempStack.isEmpty()){
                stack.push(tempStack.pop());
            }
        }
    }

    @Override
    public T poll() {
        return stack.pop();
    }

    @Override
    public int size() {
        return stack.size();
    }

    public static void main(String[] args) {
        QueueUsing2Stacks<Integer> q = new QueueUsing2Stacks<>();
        q.offer(1);
        q.offer(2);
        while (!q.isEmpty()){
            System.out.println(q.poll());
        }
    }
}
