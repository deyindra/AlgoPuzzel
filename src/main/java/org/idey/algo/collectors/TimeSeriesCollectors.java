package org.idey.algo.collectors;

import org.idey.algo.iterator.stream.TimeSeriesType;

import java.sql.Timestamp;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Stream;

public class TimeSeriesCollectors<T extends Comparable<T>> implements Collector<TimeSeriesType<T>, List<List<TimeSeriesType<T>>>, List<T>>{
    private PriorityQueue<TimeSeriesType<T>> pq=null;
    private Timestamp lastTimestamp;
    private AggregateFunction<T> function;
    public TimeSeriesCollectors(AggregateFunction<T> function) {
        this.pq = new PriorityQueue<>();
        this.function=function;
    }

    @Override
    public BiConsumer<List<List<TimeSeriesType<T>>>, TimeSeriesType<T>> accumulator() {
        return ((lists, tTimeSeriesType) -> {
           if(lastTimestamp==null){
               lastTimestamp = tTimeSeriesType.getTs();
           }else{
               Timestamp ts = tTimeSeriesType.getTs();
               if(ts.getTime() - lastTimestamp.getTime()>20){
                   drainPQ(lists);
                   lastTimestamp = ts;
               }
           }
           pq.offer(tTimeSeriesType);
        });
    }

    private void drainPQ(List<List<TimeSeriesType<T>>> lists){
        final List<TimeSeriesType<T>> batch = new ArrayList<>();
        while (!pq.isEmpty()){
            batch.add(pq.poll());
        }
        lists.add(batch);
    }

    @Override
    public Supplier<List<List<TimeSeriesType<T>>>> supplier() {
        return LinkedList::new;
    }

    @Override
    public BinaryOperator<List<List<TimeSeriesType<T>>>> combiner() {
        return (l1, l2) -> {
            throw new UnsupportedOperationException("Combining not possible");
        };
    }

    @Override
    public Function<List<List<TimeSeriesType<T>>>, List<T>> finisher() {
        return (lists -> {
            drainPQ(lists);
            return function.apply(lists);
        });
    }

    @Override
    public Set<Characteristics> characteristics() {
        return EnumSet.noneOf(Characteristics.class);
    }

    public static void main(String[] args) throws InterruptedException {
        List<TimeSeriesType<Integer>> list = new ArrayList<>();
        TimeSeriesType<Integer> ts1 = new TimeSeriesType<>(1);
        list.add(ts1);
        Thread.sleep(15);
        TimeSeriesType<Integer> ts2 = new TimeSeriesType<>(2);
        TimeSeriesType<Integer> ts3 = new TimeSeriesType<>(3);
        list.add(ts2);
        list.add(ts3);
        Thread.sleep(10);
        TimeSeriesType<Integer> ts4 = new TimeSeriesType<>(4);
        TimeSeriesType<Integer> ts5 = new TimeSeriesType<>(5);
        list.add(ts4);
        list.add(ts5);
        Thread.sleep(20);
        TimeSeriesType<Integer> ts6 = new TimeSeriesType<>(6);
        list.add(ts6);

        Stream<TimeSeriesType<Integer>> s = list.stream();

        s.collect(new TimeSeriesCollectors<>(new MaxAggregateFunction<Integer>())).forEach(System.out::println);


    }
}
