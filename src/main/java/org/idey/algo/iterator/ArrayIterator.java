package org.idey.algo.iterator;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayIterator<T> implements Iterator<T>, Iterable<T> {
    private T[] array;
    private int current;

    public ArrayIterator(T[] array) {
        this.array = array;
        this.current = 0;
    }

    @SuppressWarnings("unchecked")
    public ArrayIterator(Collection<T> collection) {
        if(collection!=null && !collection.isEmpty()){
            this.array = (T[])(collection.toArray());
        }
        this.current = 0;
    }

    @Override
    public Iterator<T> iterator() {
        return this;
    }

    @Override
    public boolean hasNext() {
        return (array!=null && array.length!=0 && current<array.length);
    }

    @Override
    public T next() {
        if(!hasNext()){
            throw new NoSuchElementException("No Element found");
        }
        T obj = array[current];
        current++;
        return obj;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("remove");
    }

}