package org.idey.algo.iterator;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author indranildey
 * Iterator which will navigate to the element which is meet the filtered condition
 */
public class FilteredIterator<T> implements Iterator,Iterable {
    private Iterator<T> iterator;
    private boolean hasNext;
    private Filter<T> filter;
    private T obj;

    public FilteredIterator(Iterator<T> iterator, Filter<T> filter) {
        this.iterator = iterator;
        this.filter = filter;
        setNextObject();
    }

    public FilteredIterator(T[] array, Filter<T> filter) {
        if(array!=null && array.length!=0){
            this.iterator = Arrays.asList(array).iterator();
            this.filter = filter;
        }else{
            this.iterator=null;
            this.filter=filter;
        }
        setNextObject();
    }

    @Override
    public Iterator iterator() {
        return this;
    }

    @Override
    public boolean hasNext() {
        return hasNext;
    }

    @Override
    public T next() {
        if(!hasNext){
            throw new NoSuchElementException("No more element...");
        }
        T prevObject = obj;
        setNextObject();
        return prevObject;
    }


    public interface Filter<T>{
        boolean isValid(T obj);
    }


    private void setNextObject(){
        hasNext = false;
        if(iterator!=null){
            while (iterator.hasNext()){
                T object = iterator.next();
                if(filter.isValid(object)){
                    obj = object;
                    hasNext = true;
                    break;
                }
            }
        }
    }
}



