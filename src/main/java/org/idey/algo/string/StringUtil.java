package org.idey.algo.string;

import java.util.ArrayList;
import java.util.List;

public class StringUtil {
    private static void generateCombination(final char[] array, final int index, final String prefix, final List<String> combinations){
        if(index<array.length && array[index]=='?'){
            generateCombination(array,index+1,prefix+'0',combinations);
            generateCombination(array,index+1,prefix+'1',combinations);
        }else if(index<array.length){
            generateCombination(array,index+1,prefix+array[index],combinations);
        }else{
            combinations.add(prefix);
        }
    }

    public static List<String> generateCombination(String str){
        List<String> combinations = new ArrayList<>();
        generateCombination(str.toCharArray(),0,"",combinations);
        return combinations;
    }

    public static void main(String[] args) {
        System.out.println(generateCombination("A?B???"));
    }
}
