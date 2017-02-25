package org.idey.algo.array;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Permutation {

    public static  <T> void perm2(List<T> actualList, List<T> prefixList, int k) {
        int N = prefixList.size();
        int M = actualList.size();
        if(N==k){
            System.out.println(prefixList);
        }else{
            for(int i=0;i<M;i++){
                T object = actualList.get(i);
                if(actualList.subList(i+1,M).indexOf(object)!=-1)
                        continue;
                List<T> newPrefixList = new ArrayList<T>();
                newPrefixList.addAll(prefixList);
                newPrefixList.add(object);
                List<T> newActualList = new ArrayList<T>();
                newActualList.addAll(actualList.subList(0,i));
                newActualList.addAll(actualList.subList(i+1,M));
                perm2(newActualList,newPrefixList,k);
            }
        }
    }



    public static void main(String[] args) {
        perm2(Arrays.asList('A','A','B'),new ArrayList<>(),2);
    }
}
