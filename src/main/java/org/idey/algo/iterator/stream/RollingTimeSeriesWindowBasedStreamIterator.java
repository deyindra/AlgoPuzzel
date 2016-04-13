package org.idey.algo.iterator.stream;

import java.sql.Timestamp;
import java.util.*;

public class RollingTimeSeriesWindowBasedStreamIterator<T extends Comparable<T>> implements Iterator<List<T>> {
    private Iterator<TimeSeriesType<T>> iterator;
    private Timestamp startTimeStamp;
    private Map<Long, List<T>> map;
    private boolean hasNext=false;
    private TimeEnum timeEnum;
    private long windowSize;

    public RollingTimeSeriesWindowBasedStreamIterator(Iterator<TimeSeriesType<T>> it,
                                                      TimeEnum timeEnum, int windowSize) {

        if(windowSize<0){
            throw new IllegalArgumentException("Invalid Window size");
        }
        this.timeEnum = timeEnum;
        this.windowSize = windowSize;
        startTimeStamp = new Timestamp(System.currentTimeMillis());
        this.iterator = it;
        map = new LinkedHashMap<>();
        setAdvance();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<T> next() {
        return null;
    }

    @Override
    public boolean hasNext() {
        return hasNext;
    }

    private void setAdvance(){
        hasNext = false;
        if(iterator!=null && iterator.hasNext()){
            if(!map.isEmpty()){
                Long key = map.keySet().iterator().next();
                map.remove(key);
            }
            while (iterator.hasNext() && map.size()<windowSize){
                TimeSeriesType<T> tTimeSeriesType = iterator.next();
                Long key = timeEnum.convert(startTimeStamp,tTimeSeriesType.getTs());
                key = key+1L;
                List<T> list = map.get(key);
                if(list==null){
                    list = new ArrayList<>();
                }
                list.add(tTimeSeriesType.getObject());
                map.put(key,list);
            }
            hasNext=true;
        }
    }
}
