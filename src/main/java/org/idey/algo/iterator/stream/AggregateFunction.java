package org.idey.algo.iterator.stream;

import java.util.List;
import java.util.function.Function;

public abstract class AggregateFunction<T extends Comparable<T>> implements Function<List<TimeSeriesType<T>>, T> {
    @Override
    public <V> Function<List<TimeSeriesType<T>>, V> andThen(Function<? super T, ? extends V> after) {
        throw new UnsupportedOperationException("Not supported...");
    }

    @Override
    public <V> Function<V, T> compose(Function<? super V, ? extends List<TimeSeriesType<T>>> before) {
        throw new UnsupportedOperationException("Not supported...");
    }
}
