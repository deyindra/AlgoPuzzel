package org.idey.algo.divnconcur;

public class Range<T extends Comparable<T>> implements Comparable<Range<T>> {
    private T lower;
    private T upper;

    public Range(T lower, T upper) {
        assert(lower!=null && upper!=null && upper.compareTo(lower)>0);
        this.lower = lower;
        this.upper = upper;
    }
    //Asummption they are no overlapping
    @Override
    public int compareTo(Range<T> o) {
        if(o==null){
            return 1;
        }else if(this==o) {
            return 0;
        }else{
            if(this.equals(o)){
                return 0;
            }else{
                if (isOverLapped(o)) {
                    throw new IllegalArgumentException("Range can not be over lapped");
                }else{
                    if(o.upper.compareTo(this.lower)<0){
                        return 1;
                    }else{
                       return -1;
                    }
                }
            }
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Range<?> range = (Range<?>) o;

        if (!lower.equals(range.lower)) return false;
        if (!upper.equals(range.upper)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = lower.hashCode();
        result = 31 * result + upper.hashCode();
        return result;
    }

    private boolean isOverLapped(Range<T> o){
        if(o.checkBoundary(lower)==Bound.BETWEEN
                || o.checkBoundary(upper)==Bound.BETWEEN
                || checkBoundary(o.lower)==Bound.BETWEEN){
            return true;
        }
        return false;
    }


    public Bound checkBoundary(T object){
        if(object.compareTo(lower)<0){
            return Bound.LOWER;
        }else if(object.compareTo(lower)>=0 && object.compareTo(upper)<=0){
            return Bound.BETWEEN;
        }else{
            return Bound.UPPER;
        }
    }


    public enum Bound{
        LOWER, BETWEEN, UPPER
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Range{");
        sb.append("lower=").append(lower);
        sb.append(", upper=").append(upper);
        sb.append('}');
        return sb.toString();
    }



}
