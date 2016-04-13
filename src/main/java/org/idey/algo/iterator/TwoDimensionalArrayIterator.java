package org.idey.algo.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class TwoDimensionalArrayIterator<T> implements Iterator<T>, Iterable<T> {
    private T[][] array;
    private int currentRow;
    private int currentColumn;
    private T obj;
    private boolean hasNext;

    public TwoDimensionalArrayIterator(T[][] array) {
        this.array = array;
        currentColumn =0;
        currentRow = 0;
        setNextObject();
    }

    @Override
    public Iterator<T> iterator() {
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

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Remove does not supported");
    }

    private void setNextObject(){
        hasNext=false;
        if(array!=null && array.length!=0){
            do{
                if(currentRow==array.length){
                    break;
                }else{
                    T[] subArray = array[currentRow];
                    if(subArray==null || currentColumn==subArray.length){
                        currentColumn=0;
                        currentRow++;
                    }else {
                        obj = subArray[currentColumn];
                        currentColumn++;
                        hasNext = true;
                    }
                }
            }while(!hasNext);
        }
    }

}