package org.idey.algo.string;

import java.util.ArrayList;
import java.util.List;

public class StringUtil {
    private static void generateCombination(final char[] array, final int index, final String prefix, final char[] newArray,
                                            final List<String> combinations){
        if(index<array.length && array[index]=='?'){
            for(char ch:newArray){
                generateCombination(array,index+1,prefix+ch,newArray,combinations);
            }
        }else if(index<array.length){
            generateCombination(array,index+1,prefix+array[index],newArray,combinations);
        }else{
            combinations.add(prefix);
        }
    }

    public static List<String> generateCombination(String str,final char[] newArray){
        List<String> combinations = new ArrayList<>();
        generateCombination(str.toCharArray(),0,"",newArray, combinations);
        System.out.println(combinations.size());
        return combinations;
    }

    public static void main(String[] args) {
        System.out.println(generateCombination("A?B???", new char[]{'0','1','2','3'}));
    }
}
