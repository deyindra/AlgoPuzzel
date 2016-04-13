package org.idey.algo.divnconcur;

import java.util.*;

public class RangeSearch {
    private static <T extends Comparable<T>> Range<T> rangeSearch(Range<T>[] array, T object){
        int start=0;
        int end = array.length-1;
        while (end>=start){
            int mid = start+(end-start)/2;
            Range<T> range = array[mid];
            if(range.checkBoundary(object)== Range.Bound.BETWEEN){
                return range;
            }else if(range.checkBoundary(object)== Range.Bound.UPPER){
                start=mid+1;
            }else{
                end=mid-1;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Range<Integer> range = rangeSearch(new Range[]{new Range<Integer>(Integer.MIN_VALUE, 10),
                new Range<Integer>(11, 20),
                new Range<Integer>(21, 30),
                new Range<Integer>(31, 50)},16);

        System.out.println(range);

        System.out.println(groupObjectsBasedOnRanges(new Range[]{new Range<Integer>(Integer.MIN_VALUE, 10),
                new Range<Integer>(11, 20),
                new Range<Integer>(21, 30),
                new Range<Integer>(31, 50)}, new Integer[]{-1,-2,10,11,15,20,22,23,32,40,50,56,57}));

    }


    public static <T extends Comparable<T>> Collection<List<T>> groupObjectsBasedOnRanges(Range<T>[] array, T[] objectArray){
        Map<Range<T>, List<T>> rangeMapping = new HashMap<>();
        for(T object:objectArray){
            Range<T> range = rangeSearch(array,object);
            List<T> list = rangeMapping.get(range);
            if(list==null){
                list = new ArrayList<>();
            }
            list.add(object);
            rangeMapping.put(range,list);
        }
        return rangeMapping.values();
    }

}
