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

    public static String reverseWord(String str){
        char[] array = str.toCharArray();
        int start =0;
        int end = array.length - 1;
        reverse(array,start,end);

        //skip all the leading ans trailing space
        while (start<=end && array[start]==' ')
            start++;

        //skip all the leading ans trailing space
        while (end>=start && array[end]==' ')
            end--;


        for(int j=start;j<=end;j++){
            if(array[j] == ' '){
                reverse(array,start,j-1);
                j++;
                while (array[j] == ' ')
                    j++;
                start = j;
            }
        }
        reverse(array,start,end);
        return String.valueOf(array);
    }

    public static void main(String[] args) {
        System.out.println("======"+reverseWord("    I   am   ok  ")+"=======");
    }
}
