package org.idey.algo.string;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StringCodec {
    public static String compress(String str){
        if(str==null){
            throw new IllegalArgumentException("Invalid String");
        }else{
            StringBuilder builder = new StringBuilder();
            char[] array = str.toCharArray();
            int repeatedCharCount=1;
            char repeatedChar = array[0];
            for(int i=1;i<array.length;i++){
                if(repeatedChar==array[i]){
                    repeatedCharCount++;
                }else{
                    builder.append(repeatedChar).append(repeatedCharCount);
                    repeatedChar = array[i];
                    repeatedCharCount=1;
                }
            }
            builder.append(repeatedChar).append(repeatedCharCount);
            return builder.toString();

        }
    }


    public static List<String> breakWords(String str){
        if(str==null || str.length()==0){
            throw new IllegalArgumentException("Invalid words");
        }else{
            char[] array = str.toCharArray();
            String subString = "";
            List<String> words = new ArrayList<>();
            boolean isQuoted = false;
            for(char ch:array){
                if(ch!=' ' && ch!='"'){
                    subString += ch;
                }else if(ch=='"'){
                    subString += ch;
                    isQuoted = !isQuoted;
                }else{
                    if(isQuoted){
                        subString += ch;
                    }else{
                        if(!"".equals(subString)){
                            words.add(subString);
                            subString="";
                            isQuoted = false;
                        }
                    }
                }
            }
            if(!subString.equals("")){
                words.add(subString);
            }
            return words;
        }
    }

    public static boolean isPalinDrome(String str){
        if(str==null || str.length()==0){
            throw new IllegalArgumentException("Invalid words");
        }else{
            str = str.trim();
            char[] array = str.toCharArray();
            Set<Character> sets = new HashSet<>();
            for(char ch:array){
                boolean add = sets.add(ch);
                if(!add){
                    sets.remove(ch);
                }
            }
            return sets.size()<=1;
        }

    }

    public static void main(String[] args) {
        System.out.println(compress("apple"));
        System.out.println(breakWords("  I am indranil\"abc  \"hello  "));
        System.out.println(isPalinDrome("oo1n"));

    }
}
