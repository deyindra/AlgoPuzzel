package org.idey.algo.collectors;

import org.idey.algo.iterator.stream.TimeSeriesType;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public  class MaxAggregateFunction<T extends Comparable<T>> extends AggregateFunction<T>{
    @Override
    public List<T> apply(List<List<TimeSeriesType<T>>> lists) {
        final List<T> list = new ArrayList<>();
        for(List<TimeSeriesType<T>> valList:lists){
            Optional<TimeSeriesType<T>> optional = valList.stream().sorted().max(new CustomComparator<>());
            if(optional.isPresent()){
                list.add(optional.get().getObject());
            }
        }
        return list;
    }
}
