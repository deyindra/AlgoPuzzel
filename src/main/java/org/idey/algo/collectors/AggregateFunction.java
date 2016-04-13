package org.idey.algo.collectors;


import org.idey.algo.iterator.stream.TimeSeriesType;

import java.util.List;
import java.util.function.Function;

public abstract class AggregateFunction<T extends Comparable<T>> implements Function<List<List<TimeSeriesType<T>>>, List<T>> {

    @Override
    public <V> Function<List<List<TimeSeriesType<T>>>, V> andThen(Function<? super List<T>, ? extends V> after) {
        throw new UnsupportedOperationException("Not supported...");
    }

    @Override
    public <V> Function<V, List<T>> compose(Function<? super V, ? extends List<List<TimeSeriesType<T>>>> before) {
        throw new UnsupportedOperationException("Not supported...");
    }
}
