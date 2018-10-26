package org.idey.algo.iterator;

import java.util.ListIterator;
import java.util.NoSuchElementException;

public class ArrayBasedListIterator<T> implements ListIterator<T> {
    private T[] array;
    private int current;
    private int prevIndex;
    private int nextIndex;

    public ArrayBasedListIterator(T[] array) {
        this.array = array;
        this.current = 0;
        prevIndex = -1;
        nextIndex = 0;
    }


    @Override
    public boolean hasNext() {
        return (array!=null && array.length!=0 && nextIndex<array.length);
    }

    @Override
    public T next() {
        if(!hasNext()){
            throw new NoSuchElementException("No more element");
        }
        current = nextIndex;
        prevIndex = nextIndex;
        nextIndex++;
        return array[current];
    }

    @Override
    public boolean hasPrevious() {
        return (array!=null && array.length!=0 && prevIndex>=0);
    }

    @Override
    public T previous() {
        if(!hasPrevious()){
            throw new NoSuchElementException("No more element");
        }
        current = prevIndex;
        nextIndex = prevIndex;
        prevIndex--;
        return array[current];
    }

    @Override
    public int nextIndex() {
        if(!hasNext()){
            throw new NoSuchElementException("No more element");
        }
        return nextIndex;
    }

    @Override
    public int previousIndex() {
        if(!hasPrevious()){
            throw new NoSuchElementException("No more element");
        }
        return prevIndex;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Remove");
    }

    @Override
    public void set(T t) {
        throw new UnsupportedOperationException("set");
    }

    @Override
    public void add(T t) {
        throw new UnsupportedOperationException("add");
    }
}