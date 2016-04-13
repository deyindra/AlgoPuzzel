package org.idey.algo.string;

import java.util.Arrays;

public class ReverseWord {

    private static void reverse(char[] array, int start, int end){
        assert (end>=0 && end<=array.length-1);
        assert(start>=0 && start<=end);

        for(int i=start,j=end;i<j;i++,j--){
            char temp = array[i];
            array[i]=array[j];
            array[j]=temp;
        }
    }

    public static void reverseWord(String str){
        char[] array = str.toCharArray();
        int startOfWord=0;
        for(int i=0;i<array.length;i++){
            if(i==array.length-1){
                reverse(array,startOfWord,i);
            }else if(array[i]==' '){
                reverse(array,startOfWord,i-1);
                for(;i<array.length && array[i]==' ';i++)
                startOfWord=i+1;
            }
        }
        reverse(array,0,array.length-1);
        System.out.println(new String(array));
    }

    public static void main(String[] args) {
        reverseWord("I   am   ok");
    }
}
