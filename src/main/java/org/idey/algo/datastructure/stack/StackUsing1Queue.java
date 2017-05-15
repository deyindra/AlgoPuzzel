package org.idey.algo.datastructure.stack;

import java.util.LinkedList;
import java.util.Queue;

public class StackUsing1Queue<T> implements Stack<T> {
    private Queue<T> queue = new LinkedList<>();


    @Override
    public boolean isEmpty() {
        return queue.size()==0;
    }

    @Override
    public void push(T object) {
        int size = queue.size();
        queue.offer(object);
        for(int i=0;i<size;i++){
            queue.offer(queue.poll());
        }
    }

    @Override
    public T pop() {
        return queue.poll();
    }

    @Override
    public int size() {
        return queue.size();
    }

    public static void main(String[] args) {
        StackUsing1Queue<Integer> stackUsing2Queus = new StackUsing1Queue<>();
        stackUsing2Queus.push(1);
        stackUsing2Queus.push(2);
        stackUsing2Queus.push(3);

        while (!stackUsing2Queus.isEmpty()){
            System.out.println(stackUsing2Queus.pop());
        }
    }
}
