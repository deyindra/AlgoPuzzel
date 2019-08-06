package org.idey.algo.datastructure.stack;

import java.util.LinkedList;
import java.util.Queue;

public class StackUsing2Queus<T> implements Stack<T> {
    private Queue<T> queue = new LinkedList<>();
    private Queue<T> tmpqueue = new LinkedList<>();


    @Override
    public boolean isEmpty() {
        return queue.size()==0;
    }

    @Override
    public void push(T object) {
        if(queue.isEmpty()){
            queue.offer(object);
        }else{
            while (!queue.isEmpty()){
                tmpqueue.offer(queue.poll());
            }
            queue.offer(object);
            while (!tmpqueue.isEmpty()){
                queue.offer(tmpqueue.poll());
            }
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
        StackUsing2Queus<Integer> stackUsing2Queus = new StackUsing2Queus<>();
        stackUsing2Queus.push(1);
        stackUsing2Queus.push(2);
        stackUsing2Queus.push(3);
        while (!stackUsing2Queus.isEmpty()){
            System.out.println(stackUsing2Queus.pop());
        }
    }
}
