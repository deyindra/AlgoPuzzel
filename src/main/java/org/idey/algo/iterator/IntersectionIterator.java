package org.idey.algo.iterator;

import java.util.*;

/**
 * This class return common intersection elements from N Iterator with Sorted Elements
 *
 */
public class IntersectionIterator<T extends Comparable<T>> implements Iterator<T>{
    private Iterator<T>[] iterators;
    private boolean hasNext =false;
    private T commonObject;
    // Custom comparator to determine the 1st max elements in the iterator array
    private Comparator<T> comparator;

    public IntersectionIterator(Iterator<T>[] iterators) {
        if(iterators==null || iterators.length<=1){
            throw new IllegalArgumentException("Invalid Iterators...");
        }else{
            for(Iterator<T> it:iterators){
                if(it==null){
                    throw new IllegalArgumentException("Invalid null Iterator...");
                }
            }
        }
        this.iterators = iterators;
        this.comparator = (o1, o2) -> {
            if(o1==null && o2==null){
                return 0;
            }else if(o1==o2){
                return 0;
            }else if(o1==null){
                return 1;
            }else{
                return -o1.compareTo(o2);
            }
        };
        setAdvance();
    }

    @Override
    public T next() {
        if(!hasNext){
            throw new NoSuchElementException("No more Elements...");
        }
        T prevCommonObject = commonObject;
        setAdvance();
        return prevCommonObject;
    }



    @Override
    public boolean hasNext() {
        return hasNext;
    }



    private void setAdvance(){
        hasNext=false;
        T maxObject;
        Set<Integer> indexes = new HashSet<>();
        if(this.iterators[0].hasNext()){
            maxObject=this.iterators[0].next();
            indexes.add(0);
        }else{
            return;
        }

        for(int count=1;count<iterators.length;count++){
            if(!iterators[count].hasNext()){
                return;
            }else{
                T object = iterators[count].next();
                if(comparator.compare(maxObject,object)>0){
                    maxObject=object;
                    indexes.clear();
                    indexes.add(count);
                }else if(comparator.compare(maxObject,object)==0){
                    indexes.add(count);
                }
            }
        }
        while (indexes.size()<this.iterators.length){
            for(int count=0;count<this.iterators.length;count++){
                if(!indexes.contains(count)){
                    Iterator<T> it = this.iterators[count];
                    boolean isHasNext;
                    while ((isHasNext=it.hasNext())){
                        T object = it.next();
                        if(maxObject.compareTo(object)==0){
                            indexes.add(count);
                            break;
                        }else if(maxObject.compareTo(object)<0){
                            maxObject=object;
                            indexes.clear();
                            indexes.add(count);
                            break;
                        }
                    }
                    if(!isHasNext){
                        return;
                    }

                }
            }

        }
        commonObject=maxObject;
        hasNext=true;
    }

}

