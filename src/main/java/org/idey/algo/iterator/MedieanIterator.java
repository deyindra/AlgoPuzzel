package org.idey.algo.iterator;

import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;

public class MedieanIterator<T extends Comparable<T>> implements Iterator<T> {
    private T median;
    private T[] array;
    private int size;
    private int currentIndex;
    private PriorityQueue<T> minHeap = new PriorityQueue<>();
    private PriorityQueue<T> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

    public MedieanIterator(T[] array, int size) {
        if(array==null || array.length==0){
            throw new IllegalArgumentException("Invalid Array...");
        }
        if(size<=0 || size>array.length){
            throw new IllegalArgumentException("Invalid size "+size);
        }
        this.array = array;
        this.size = size;
        currentIndex=1;
        maxHeap.offer(array[0]);
        for(;currentIndex<this.size;currentIndex++){
            add(array[currentIndex]);
        }
        median = maxHeap.peek();
        currentIndex--;
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
        T prevMediean = median;
        currentIndex++;
        if(currentIndex<=array.length-1) {
            add(array[currentIndex]);
            remove(array[currentIndex - this.size]);
            median = maxHeap.peek();
        }
        return prevMediean;
    }

    private void add(T val) {
        T preMedian = maxHeap.peek();
        if (val.compareTo(preMedian)>0) {
            minHeap.offer(val);
        } else {
            maxHeap.offer(val);
        }
        balance();
    }


    private void remove(T val) {
        T preMedian = maxHeap.peek();
        if (val.compareTo(preMedian)>0) {
            minHeap.remove(val);
        } else {
            maxHeap.remove(val);
        }
        balance();
    }

    private void balance() {
        if (maxHeap.size() > minHeap.size() + 1) {
            minHeap.offer(maxHeap.poll());
        } else if (maxHeap.size() < minHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }

}
