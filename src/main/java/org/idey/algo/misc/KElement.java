package org.idey.algo.misc;


import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Stack;

public class KElement<T extends Comparable<T>> {
    private int limit;
    private boolean isMax;
    private PriorityQueue<T> priorityQueue;

    public KElement(int limit, final boolean isMax) {
        this.limit = limit;
        this.isMax = isMax;
        this.priorityQueue = new PriorityQueue<>(new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                return (isMax) ? (o1.compareTo(o2)) : -(o1.compareTo(o2));
            }
        });
    }

    public KElement<T> add(T object){
        int size = priorityQueue.size();
        if(size<limit){
            priorityQueue.offer(object);
        }else{
            T head = priorityQueue.peek();
            if( isMax ? (head.compareTo(object)<0) : (head.compareTo(object)>0) ){
                priorityQueue.poll();
                priorityQueue.offer(object);
            }
        }
        return this;
    }

    public Stack<T> returnElements(){
        Stack<T> stack= new Stack<>();
        while (!priorityQueue.isEmpty()){
            stack.push(priorityQueue.poll());
        }
        return stack;
     }

    public static void main(String[] args) {
        KElement<Integer> kElement = new KElement<>(3,false);
        kElement.add(5).add(3).add(11).add(1).add(4).add(7);
        Stack<Integer> stack = kElement.returnElements();
        while (!stack.isEmpty()){
            System.out.println(stack.pop());
        }
    }
}
