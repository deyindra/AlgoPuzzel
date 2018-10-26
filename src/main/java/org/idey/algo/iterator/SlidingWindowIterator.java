package org.idey.algo.iterator;

import java.util.*;

public class SlidingWindowIterator<T> implements Iterator<List<T>> {
    private Iterator<T> iterator;
    private int size;
    private int step;
    private LinkedList<T> deque;
    private boolean hasNext;

    public SlidingWindowIterator(Iterator<T> iterator, int size, int step) {
        this.iterator = iterator;
        this.size = size;
        this.step = step;
        this.deque = new LinkedList<>();
        this.hasNext = false;
        setAdvance();
    }

    @Override
    public boolean hasNext() {
        return hasNext;
    }

    @Override
    public List<T> next() {
        if(!hasNext()){
            throw new NoSuchElementException();
        }
        List<T> list = Collections.unmodifiableList(new LinkedList<>(deque));
        setAdvance();
        return list;
    }


    private void setAdvance(){
        hasNext = false;
        if(iterator!=null && iterator.hasNext()){
            if(!deque.isEmpty()) {
                if (size > step) {
                    for (int count = 0; !deque.isEmpty() && count < step; count++) {
                        deque.poll();
                    }
                } else {
                    deque.clear();
                    for (int count = 0; iterator.hasNext() && count < step - size; count++) {
                        iterator.next();
                    }
                }
            }
            while (iterator.hasNext() && deque.size()<size){
                deque.offer(iterator.next());
            }
            if(!deque.isEmpty()){
                hasNext = true;
            }

        }
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Invalid Operation");
    }

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1,2,3,4,5);
        Iterator<List<Integer>> it = new SlidingWindowIterator<>(list.iterator(),3,3);
        while (it.hasNext()){
            System.out.println(it.next());
        }
    }
}
