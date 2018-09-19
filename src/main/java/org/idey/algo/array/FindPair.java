package org.idey.algo.array;


import java.util.HashSet;
import java.util.Set;

public class FindPair {
    public static void printPairUnsorted(int[] array, int sum){
        Set<Integer> s = new HashSet<>();
        for(int v:array){
            int temp = sum - v;
            if(s.contains(temp)){
                System.out.println(String.format("Pair found %d %d", temp,v));
            }
            s.add(v);
        }
    }

    public static void printPairSorted(int[] array, int sum){
        int i=0;
        int j=array.length-1;
        while (i<j){
           int resultSum = array[i]+array[j];
            if(resultSum < sum){
                i++;
            }else if (resultSum > sum){
                j--;
            }else{
                System.out.println(String.format("Pair found %d %d", array[i],array[j]));
                i ++ ;
                j --;
            }
        }
    }

    public static void main(String[] args) {
        printPairUnsorted(new int[]{-3,-4,-5,-4,1,2},-2);
        printPairSorted(new int[]{-5,-4,-4,-3,1,2}, -2);
    }
}
