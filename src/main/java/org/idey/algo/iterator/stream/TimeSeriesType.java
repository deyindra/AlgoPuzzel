package org.idey.algo.iterator.stream;

import java.sql.Timestamp;

public class TimeSeriesType<T extends Comparable<T>> implements Comparable<TimeSeriesType<T>> {
    private Timestamp ts;
    private T object;

    public TimeSeriesType(T object) {
        this.object = object;
        ts = new Timestamp(System.currentTimeMillis());
    }

    public T getObject() {
        return object;
    }

    public Timestamp getTs() {
        return ts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TimeSeriesType<?> that = (TimeSeriesType<?>) o;

        if (!ts.equals(that.ts)) return false;
        if (object != null ? !object.equals(that.object) : that.object != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = ts.hashCode();
        result = 31 * result + (object != null ? object.hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(TimeSeriesType<T> o) {
        if(o==null){
            return 1;
        }else if(this==o){
             return 0;
        }else{
            int compare = this.ts.compareTo(o.ts);
            if(compare==0){
                if(this.object==null && o.object==null){
                    return 0;
                }else{
                    if(this.object==null){
                        return -1;
                    }else{
                        return this.object.compareTo(o.object);
                    }
                }
            }
            return compare;
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TimeSeriesType{");
        sb.append("object=").append(object);
        sb.append(", ts=").append(ts);
        sb.append('}');
        return sb.toString();
    }
}
