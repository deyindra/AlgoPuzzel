package org.idey.algo.iterator;

import java.util.*;

public class RollWindowIterator<T> implements Iterator<List<T>> {
    private Iterator<T> it;
    private LinkedList<T> list;
    private int size;
    private boolean hasNext;

    public RollWindowIterator(int size, Iterator<T> it) {
        if(size<0){
            throw new IllegalArgumentException("Invalid Window size....");
        }
        this.size = size;
        this.it = it;
        list = new LinkedList<>();
        setAdvance();
    }

    @Override
    public boolean hasNext() {
        return hasNext;
    }

    @Override
    public List<T> next() {
        if(!hasNext){
            throw new NoSuchElementException();
        }
        List<T> list = new LinkedList<>(this.list);
        setAdvance();
        return list;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }


    private void setAdvance(){
        hasNext = false;
        if(it!=null && it.hasNext()){
            if(!list.isEmpty()){
                list.poll();
            }
            while (it.hasNext() && list.size()<size){
                list.offer(it.next());
            }
            hasNext=true;
        }
    }

    public static void main(String[] args) {
        RollWindowIterator<Integer> it = new RollWindowIterator<>(2,Arrays.asList(1,2,3).iterator());
        while (it.hasNext()){
            System.out.println(it.next());
        }
    }

}
