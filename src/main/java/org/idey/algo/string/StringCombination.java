package org.idey.algo.string;

import java.util.*;

public class StringCombination {
    private static void generateCombination(char[] array, int index,char[] combination,List<String> result, final char replaceChar){
        if(index>=array.length){
            result.add(new String(array));
        }else{
            if(array[index]==replaceChar){
                for(char ch:combination){
                    array[index]=ch;
                    generateCombination(array,index+1,combination,result,replaceChar);
                }
            }else{
                generateCombination(array,index+1,combination,result,replaceChar);
            }
        }
    }

    public static List<String> generateCombination(String str,char[] combination,char replaceChar){
        List<String> list = new ArrayList<>();
        generateCombination(str.toCharArray(),0,combination,list,replaceChar);
        return list;
    }


    public static void generatePermutation(String str, final int index, final String prefix, List<String> list,
                                                   final char[] allChars, final char replaceAbleChar){
        if(str.equals("")){
            list.add(prefix);
        }else{
            char[] array = str.toCharArray();
            if(array[index]!=replaceAbleChar){
                generatePermutation(str.substring(index+1),0,prefix+array[index],list,allChars,replaceAbleChar);
            }else{

                for(char ch:allChars){
                    generatePermutation(str.substring(index+1),0,prefix+ch,list,allChars,replaceAbleChar);
                }
            }
        }
    }

    public static List<String> generatePermutation(String str,
                                           char[] allChars, char replaceAbleChar){

        List<String> list = new ArrayList<>();
        generatePermutation(str, 0,"", list, allChars, replaceAbleChar);
        return list;

    }

    public static void main(String[] args) {
        String str = "A?B?C";
        List<String> list = generatePermutation(str, new char[]{'X', 'Y', 'Z'}, '?');
        System.out.println(list);
    }
}
