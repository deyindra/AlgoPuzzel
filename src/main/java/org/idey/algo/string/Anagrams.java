package org.idey.algo.string;

import org.idey.algo.math.PrimeNumber;

import java.util.*;


public class Anagrams {
    private static int[] primeTables;

    private static void populatePrimeTable(){
        if(primeTables==null){
            primeTables = new int[128];
            PrimeNumber primeNumber = new PrimeNumber(10000);
            int j=0;
            for(int i=0;i<=10000;i++){
                if(primeNumber.isPrime(i) && j<primeTables.length){
                    primeTables[j]=i;
                    j++;
                }
            }
        }
    }

    private static long generatePrimeHash(String str){
        populatePrimeTable();
        assert (str!=null && str.trim().length()>0);
        char[] array = str.toCharArray();
        long hash=1;
        for(char ch:array){
            int index=(int)ch;
            hash = hash*primeTables[index];
        }
        return hash;
    }


    public Collection<List<String>> groupAnagrams(List<String> list){
        Map<Long, List<String>> map=null;
        if(list!=null && !list.isEmpty()){
            map = new HashMap<>();
            for(String str:list){
                long prime = generatePrimeHash(str);
                List<String> subList = map.get(prime);
                if(subList==null){
                    subList = new ArrayList<>();
                }
                subList.add(str);
                map.put(prime,subList);
            }
        }
        if(map!=null){
            return map.values();
        }
        return null;
    }

    public Collection<List<String>> groupAnagrams2(List<String> list){
        Map<AnagramClass, List<String>> map=null;
        if(list!=null && !list.isEmpty()){
            map = new HashMap<>();
            for(String str:list){
                AnagramClass anagrams = new AnagramClass(str);
                List<String> subList = map.get(anagrams);
                if(subList==null){
                    subList = new ArrayList<>();
                }
                subList.add(str);
                map.put(anagrams,subList);
            }
        }
        if(map!=null){
            return map.values();
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(new Anagrams().groupAnagrams2(Arrays.asList("cat", "act", "dog", "god", "test")));
    }

}
