package org.idey.algo.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MorrisPatternSearch {
    private static <T> int[] computeTemporaryArray(T pattern[]){
        int [] lps = new int[pattern.length];
        int index =0;
        for(int i=1; i < pattern.length;){
            if(equals(pattern[i],pattern[index])){
                lps[i] = index + 1;
                index++;
                i++;
            }else{
                if(index != 0){
                    index = lps[index-1];
                }else{
                    lps[i] =0;
                    i++;
                }
            }
        }
        return lps;
    }

    public static <T> boolean equals(T a, T b){
        if(a==null)
                return (b==null);
        else
              return a.equals(b);
    }

    /**
     * KMP algorithm of pattern matching.
     */
    public static <T> List<Integer> KMP(T []text, T []pattern){

        int lps[] = computeTemporaryArray(pattern);
        int i=0;
        int j=0;
        List<Integer> list = new ArrayList<>();
        while(i < text.length && j < pattern.length){
            if(equals(text[i] , pattern[j])){
                i++;
                j++;
            }else{
                if(j!=0){
                    j = lps[j-1];
                }else{
                    i++;
                }
            }
            if(j == pattern.length){
                list.add(i - pattern.length);
                j = lps[j-1];
            }

        }
        return list;
    }

    public static void main(String[] args) {
        System.out.println(KMP(new Integer[]{1,2,1,2,1}, new Integer[]{1,2,1,2,1}));
        System.out.println(Arrays.toString(computeTemporaryArray(new Character[]{'a','b','c','a','b','y'})));

    }

}
