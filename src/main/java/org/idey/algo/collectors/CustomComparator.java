package org.idey.algo.collectors;

import org.idey.algo.iterator.stream.TimeSeriesType;

import java.util.Comparator;

public class CustomComparator<T extends Comparable<T>> implements Comparator<TimeSeriesType<T>> {
    @Override
    public int compare(TimeSeriesType<T> o1, TimeSeriesType<T> o2) {
        if(o1==null && o2==null){
            return 0;
        }else if(o1==o2){
            return 0;
        }else if(o1==null){
            return -1;
        }else{
            if(o2==null){
                return 1;
            }else{
                T obj1 = o1.getObject();
                T obj2 = o2.getObject();
                if(obj1==null && obj2==null){
                    return 0;
                }else if(obj1==obj2){
                    return 0;
                }else if(obj1==null){
                    return -1;
                }else{
                    return obj1.compareTo(obj2);
                }
            }
        }
    }
}
