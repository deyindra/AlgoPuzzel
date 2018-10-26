package org.idey.algo.iterator.stream;

import java.sql.Timestamp;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class FixedTimeSeriesBasedStreamIterator<T extends Comparable<T>> implements Iterator<T> {
    private Iterator<TimeSeriesType<T>> iterator;
    private List<TimeSeriesType<T>> List;
    private boolean hasNext=false;
    private Timestamp lastTimestamp;
    private AggregateFunction<T> aggregateFunction;
    private TimeUnit timeUnit;
    private long windowSize;
    private T object;


    public FixedTimeSeriesBasedStreamIterator(Stream<TimeSeriesType<T>> stream,
                                              AggregateFunction<T> function, TimeUnit timeUnit, long windowSize){
        this.iterator = stream.iterator();
        this.aggregateFunction = function;
        List = new ArrayList<>();
        this.timeUnit = timeUnit;
        this.windowSize = windowSize;
        setAdvance();
    }


    public FixedTimeSeriesBasedStreamIterator(Stream<TimeSeriesType<T>> stream, AggregateFunction<T> function, long windowSize){
        this(stream,function,TimeUnit.MILLISECONDS,windowSize);
    }

    @Override
    public boolean hasNext() {
        return hasNext;
    }

    @Override
    public T next() {
        if(!hasNext){
            throw new NoSuchElementException("No More Elements...");
        }
        T prevObject = object;
        setAdvance();
        return prevObject;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Method not supported...");
    }

    private void setAdvance(){
        hasNext=false;
        while (iterator.hasNext()){
            TimeSeriesType<T> type = iterator.next();
            Timestamp ts = type.getTs();
            if(lastTimestamp==null){
                lastTimestamp = ts;
            }else{
                if(isGreaterThan(ts)){
                    lastTimestamp=ts;
                    object = aggregateFunction.apply(List);
                    List.clear();
                    hasNext=true;
                }
            }
            List.add(type);
            if(hasNext){
                break;
            }
        }
        //this is for the last iterator set...
        if(!hasNext){
            if(!List.isEmpty()){
                object = aggregateFunction.apply(List);
                List.clear();
                hasNext=true;
            }
        }
    }

    private boolean isGreaterThan(Timestamp ts){
        long diff = ts.getTime() - lastTimestamp.getTime();
        return (timeUnit.convert(diff,TimeUnit.MILLISECONDS)) >= windowSize;
    }




}
