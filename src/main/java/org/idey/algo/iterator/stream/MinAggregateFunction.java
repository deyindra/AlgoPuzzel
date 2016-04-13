package org.idey.algo.iterator.stream;


import java.util.Optional;
import java.util.List;

public class MinAggregateFunction<T extends Comparable<T>> extends AggregateFunction<T> {
    @Override
    public T apply(List<TimeSeriesType<T>> timeSeriesTypes) {
        Optional<TimeSeriesType<T>> optional = timeSeriesTypes.stream().min(new CustomComparator<T>());
        if(optional.isPresent()){
            return optional.get().getObject();
        }else{
            return null;
        }
    }
}
