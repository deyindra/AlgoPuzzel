package org.idey.algo.iterator;

import java.util.*;

public class PowerSetIterator<E> implements Iterator<Set<E>>,Iterable<Set<E>>{
    private E[] arr = null;
    private BitSet bset = null;

    @SuppressWarnings("unchecked")
    public PowerSetIterator(Set<E> set)
    {
        arr = (E[])set.toArray();
        bset = new BitSet(arr.length + 1);
    }

    @Override
    public boolean hasNext() {
        return !bset.get(arr.length);
    }

    @Override
    public Set<E> next() {
        Set<E> returnSet = new TreeSet<>();
        for(int i = 0; i < arr.length; i++)
        {
            if(bset.get(i))
                returnSet.add(arr[i]);
        }
        //increment bset
        for(int i = 0; i < bset.size(); i++)
        {
            if(!bset.get(i))
            {
                bset.set(i);
                break;
            }else
                bset.clear(i);
        }

        return returnSet;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Not Supported!");
    }

    @Override
    public Iterator<Set<E>> iterator() {
        return this;
    }


    public static void main(String[] args) {
        Set<String> s = new HashSet<>(Arrays.asList("A", "B", "C"));
        PowerSetIterator<String> stringPowerSetIterator = new PowerSetIterator<>(s);
        while (stringPowerSetIterator.hasNext()){
            System.out.println(stringPowerSetIterator.next());
        }
    }

}