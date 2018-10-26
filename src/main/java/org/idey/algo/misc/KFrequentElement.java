package org.idey.algo.misc;


import java.util.*;

public class KFrequentElement<T extends  Comparable<T>> {
    private int limit;
    private boolean isMax;

    private Map<T, ElementCounter<T>> map;
    private PriorityQueue<ElementCounter<T>> priorityQueue;

    public KFrequentElement(int limit, final boolean isMax) {
        this.limit = limit;
        this.isMax = isMax;
        map = new HashMap<>();
        priorityQueue = new PriorityQueue<>((Comparator<ElementCounter<T>>) (o1, o2) -> isMax ?
                (o1.counter-o2.counter):-(o1.counter-o2.counter));
    }

    private class ElementCounter<T>{
        private T object;
        private int counter;

        public ElementCounter(T object) {
            this.object = object;
            counter=0;
        }


        public void increement(){
            counter++;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("ElementCounter{");
            sb.append("object=").append(object);
            sb.append(", counter=").append(counter);
            sb.append('}');
            return sb.toString();
        }
    }

    public KFrequentElement<T> add(T object){
        ElementCounter<T> elementCounter = map.get(object);
        if(elementCounter==null){
            elementCounter = new ElementCounter<>(object);
            map.put(object,elementCounter);
        }
        elementCounter.increement();
        if(priorityQueue.contains(elementCounter)){
            priorityQueue.remove(elementCounter);
            priorityQueue.offer(elementCounter);
        }else{
            int size = priorityQueue.size();
            if(size<limit){
                priorityQueue.offer(elementCounter);
            }else{
                ElementCounter<T> head = priorityQueue.peek();
                if(isMax ? (head.counter-elementCounter.counter)<0 :
                        (head.counter-elementCounter.counter)>0){
                    priorityQueue.poll();
                    map.remove(head.object);
                    priorityQueue.offer(elementCounter);
                }
            }

        }
        return this;
    }

    public Stack<T> returnElements(){
        Stack<T> stack= new Stack<>();
        while (!priorityQueue.isEmpty()){
            stack.push(priorityQueue.poll().object);
        }
        return stack;
    }

    public static void main(String[] args) {
        KFrequentElement<String> frequentElement = new KFrequentElement<>(2, true);
        frequentElement.add("Apple");
        frequentElement.add("Banana");
        frequentElement.add("Mango");
        frequentElement.add("Apple");
        frequentElement.add("Mango");
        frequentElement.add("Mango");

        System.out.println(frequentElement.returnElements());

    }


}
