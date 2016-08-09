package org.idey.algo.iterator;

import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;

public class MedieanIterator<T extends Comparable<T>> implements Iterator<T> {
    private T[] array;
    private T median;
    private int currentIndex;
    private final int size;
    private PriorityQueue<T> minHeap = new PriorityQueue<T>();
    private PriorityQueue<T> maxHeap = new PriorityQueue<T>(Collections.reverseOrder());


    public MedieanIterator(T[] array, int size) {
        if(array==null || array.length==0){
            throw new IllegalArgumentException("Invalid Array");
        }
        if(size<=0 || size>array.length){
            throw new IllegalArgumentException("Invalid window size");
        }
        this.array = array;
        this.size = size;
        maxHeap.offer(this.array[currentIndex]);
        currentIndex=1;
        for(;currentIndex<size;currentIndex++){
            add(array[currentIndex]);
        }
        currentIndex--;
        median = maxHeap.peek();
    }

    @Override
    public boolean hasNext() {
        return currentIndex<=array.length-1;
    }

    @Override
    public T next() {
        if(!hasNext()){
            throw new NoSuchElementException("No more element");
        }
        T prevMedian = median;
        currentIndex++;
        if(currentIndex<=array.length-1){
            add(array[currentIndex]);
            remove(array[currentIndex-size]);
            median = maxHeap.peek();
        }
        return prevMedian;
    }

    public void add(T obj){
        T peek = maxHeap.peek();
        if(obj.compareTo(peek)>0){
            minHeap.offer(obj);
        }else{
            maxHeap.offer(obj);
        }
        balance();
    }

    public void remove(T obj){
        T peek = maxHeap.peek();
        if(obj.compareTo(peek)>0){
            minHeap.remove(obj);
        }else{
            maxHeap.remove(obj);
        }
        balance();
    }

    private void balance(){
        if(maxHeap.size()>minHeap.size()+1){
            minHeap.offer(maxHeap.poll());
        }else if(maxHeap.size()<minHeap.size()){
            maxHeap.offer(minHeap.poll());
        }
    }

    public static void main(String[] args) {
        Iterator<Integer> medianIterator = new MedieanIterator<>(new Integer[]{1,5,3,2,4},3);
        while (medianIterator.hasNext()){
            System.out.println(medianIterator.next());
        }
    }
}
