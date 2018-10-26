package org.idey.algo.dynamic;



import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WaterBucket {
    private static int gcd(int a, int b){
        if(a==0)
            return b;
        return gcd(b%a, a);
    }

    private static List<Pair> pour(final int fromCap, final int toCap, final int d){
        List<Pair> list = new ArrayList<>();
        int from = fromCap;
        int to = 0;
        list.add(new Pair(to, from));

        while (from != d && to!=d){
            int temp = Math.min(from, toCap-to);
            to += temp;
            from -= temp;
            list.add(new Pair(to,from));

            if(from == d || to == d)
                break;
            if (from == 0)
            {
                from = fromCap;
                list.add(new Pair(to,from));
            }

            // If second jug becomes full, empty it
            if (to == toCap)
            {
                to = 0;
                list.add(new Pair(to,from));
            }
        }
        return list;
    }

    public static List<Pair> minStpes(int to, int from, int d){
        if(to > from){
            int temp = from;
            from = to;
            to = temp;
        }

        if(d>from){
            return Collections.emptyList();
        }
        if ((d % gcd(from,to)) != 0){
            return Collections.emptyList();
        }

        List<Pair> list1 = pour(to,from,d);
        List<Pair> list2 = pour(from,to,d);
        if(list1.size()<list2.size())
            return list1;
        else
            return list2;
    }


    private static class Pair{
        private int firstValue;
        private int secondValue;

        private Pair(int firstValue, int secondValue) {
            this.firstValue = firstValue;
            this.secondValue = secondValue;
        }

        @Override
        public String toString() {
            return String.format("(%d,%d)",firstValue,secondValue);
        }
    }

    public static void main(String[] args) {
        System.out.println(minStpes(4,11,9));
    }
}


